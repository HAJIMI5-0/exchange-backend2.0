package com.example.soul.controller;

import com.example.soul.dto.MatchUserResponse;
import com.example.soul.entity.User;
import com.example.soul.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * 매칭 API 컨트롤러
 * 匹配功能 API 控制器
 */
@RestController

// 기본 주소 설정
// 基础请求地址
// /api/match
@RequestMapping("/api/match")
public class MatchQueryController {

    // 사용자 데이터베이스 연결
    // 用户数据库连接
    private final UserRepository userRepository;

    // 생성자 주입
    // 构造函数注入
    public MatchQueryController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 사용자 매칭 기능
     * 用户匹配功能
     *
     * GET /api/match
     */
    @GetMapping
    public List<MatchUserResponse> match(

            // 내가 가진 기술
            // 我会的技能
            @RequestParam(required = false) String haveSkill,

            // 내가 배우고 싶은 기술
            // 我想学的技能
            @RequestParam(required = false) String wantSkill,

            // 学习时间段
            // 학습 가능 시간대
            @RequestParam(required = false) String timeSlot,

            // 想学习的等级
            // 배우고 싶은 기술 레벨
            @RequestParam(required = false) String learnLevel,

            // 반환 인원 수
            // 返回人数
            @RequestParam(defaultValue = "5") int limit
    ) {

        // 문자열 공백 제거
        // 去除字符串空格
        String have = normalize(haveSkill);
        String want = normalize(wantSkill);
        String time = normalize(timeSlot);
        String level = normalize(learnLevel);

        // 전체 사용자 조회
        // 查询全部用户
        List<User> all = userRepository.findAll();

        // 조건에 맞는 사용자 저장 리스트
        // 存放符合条件用户的列表
        List<User> filtered = new ArrayList<>();

        // 사용자 반복 검사
        // 循环筛选用户
        for (User u : all) {

            boolean ok = true;

            // 상대방이 내가 배우고 싶은 기술을 가지고 있는지 확인
            // 判断对方是否会我想学的技能
            if (have != null) {

                ok = ok && containsSkill(
                        u.getSkillOffer(),
                        have
                );
            }

            // 상대방이 내가 가진 기술을 배우고 싶어하는지 확인
            // 判断对方是否想学我会的技能
            if (want != null) {

                ok = ok && containsSkill(
                        u.getSkillWant(),
                        want
                );
            }

            // 学习时间段匹配
            // 学습 시간대 매칭
            if (time != null) {

                ok = ok && time.equalsIgnoreCase(
                        normalize(u.getTimeSlot())
                );
            }

            // 学习等级匹配
            // 학습 레벨 매칭
            if (level != null) {

                ok = ok && level.equalsIgnoreCase(
                        normalize(u.getSkillWantLevel())
                );
            }

            // 조건 만족 시 리스트 추가
            // 满足条件则加入列表
            if (ok) {
                filtered.add(u);
            }
        }

        // 랜덤 정렬
        // 随机排序
        Collections.shuffle(filtered);

        // limit 최소값 처리
        // limit 最小值处理
        if (limit < 0) {
            limit = 0;
        }

        // 프론트엔드로 반환
        // 返回前端数据
        return filtered.stream()

                // 반환 개수 제한
                // 限制返回数量
                .limit(limit)

                // DTO 변환
                // 转换为 DTO
                .map(this::toResponse)

                // List 변환
                // 转换为 List
                .collect(Collectors.toList());
    }

    /**
     * User 객체 → MatchUserResponse 변환
     * User 对象 → MatchUserResponse 转换
     */
    private MatchUserResponse toResponse(User u) {

        // 이름 없으면 username 사용
        // 如果没有名字则使用 username
        String name = u.getName();

        if (name == null || name.trim().isEmpty()) {
            name = u.getUsername();
        }

        // 가르칠 기술 리스트
        // 擅长技能列表
        List<String> skills =
                splitSkills(u.getSkillOffer());

        // 배우고 싶은 기술 리스트
        // 想学技能列表
        List<String> wants =
                splitSkills(u.getSkillWant());

        // 프론트엔드 응답 데이터 생성
        // 创建前端返回数据
        return new MatchUserResponse(
                u.getId(),
                u.getUsername(),
                name,
                u.getAge(),
                u.getGender(),
                u.getNationality(),
                u.getAvatar(),
                skills,
                wants,
                u.getTimeSlot(),
                u.getSkillWantLevel()
        );
    }

    /**
     * 기술 문자열 → 배열 변환
     * 技能字符串 → 数组转换
     *
     * "Java,Spring"
     * →
     * ["Java","Spring"]
     */
    private List<String> splitSkills(String raw) {

        String v = normalize(raw);

        // null이면 빈 배열 반환
        // 如果为空则返回空数组
        if (v == null) {
            return List.of();
        }

        // 콤마 기준 분리
        // 按逗号切割
        String[] parts = v.split(",");

        List<String> res = new ArrayList<>();

        for (String p : parts) {

            String t = normalize(p);

            // 공백이 아니면 추가
            // 非空则加入数组
            if (t != null) {
                res.add(t);
            }
        }

        return res;
    }

    /**
     * 기술 포함 여부 확인
     * 判断是否包含技能
     */
    private boolean containsSkill(
            String raw,
            String target
    ) {

        // 값 없으면 false
        // 如果为空返回 false
        if (raw == null) {
            return false;
        }

        // 소문자 변환
        // 转小写
        String hay =
                raw.toLowerCase(Locale.ROOT);

        String needle =
                target.toLowerCase(Locale.ROOT);

        // 콤마 기준 검사
        // 按逗号逐个匹配
        for (String part : hay.split(",")) {

            String t = part.trim();

            // 완전 일치
            // 完全匹配
            if (!t.isEmpty() && t.equals(needle)) {
                return true;
            }
        }

        // 부분 포함 검사
        // 部分包含判断
        return hay.contains(needle);
    }

    /**
     * 문자열 공백 제거
     * 去除字符串空格
     */
    private String normalize(String v) {

        // null 체크
        // null 判断
        if (v == null) {
            return null;
        }

        // 양쪽 공백 제거
        // 去除前后空格
        String t = v.trim();

        // 빈 문자열이면 null 반환
        // 空字符串返回 null
        return t.isEmpty() ? null : t;
    }
}