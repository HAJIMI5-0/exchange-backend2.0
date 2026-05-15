package com.example.soul.controller;

import com.example.soul.dto.ProfileResponse;
import com.example.soul.dto.ProfileUpdateRequest;
import com.example.soul.entity.User;
import com.example.soul.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 사용자 프로필 API
 * 用户个人资料接口
 *
 * 사용자 정보 조회 및 수정 기능 제공
 * 提供用户信息查询与修改功能
 */
@RestController

// 기본 주소 설정
// 基础请求地址
// /api
@RequestMapping("/api")
public class ProfileController {

    // 사용자 데이터베이스 연결
    // 用户数据库连接
    private final UserRepository userRepository;

    // 생성자 주입
    // 构造函数注入
    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 사용자 프로필 조회
     * 查询用户个人资料
     *
     * GET /api/profile
     *
     * @param username 사용자 이름
     *                 用户名
     *
     * @return 사용자 프로필 정보
     *         用户资料信息
     */
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(
            @RequestParam String username
    ) {

        // username으로 사용자 검색
        // 根据 username 查询用户
        return userRepository.findByUsername(username)

                // 존재하면 프로필 반환
                // 存在则返回资料
                .<ResponseEntity<?>>map(user ->
                        ResponseEntity.ok(toProfile(user)))

                // 없으면 에러 반환
                // 不存在则返回错误
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(message("User not found")));
    }

    /**
     * 사용자 프로필 수정
     * 更新用户个人资料
     *
     * PUT /api/profile
     *
     * @param request 프론트엔드에서 전달한 사용자 데이터
     *                前端传入的用户资料
     *
     * @return 수정된 사용자 정보
     *         更新后的用户资料
     */
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
            @RequestBody ProfileUpdateRequest request
    ) {

        // username 가져오기
        // 获取 username
        String username = request.getUsername();

        // username 비어있는지 확인
        // 检查 username 是否为空
        if (username == null || username.trim().isEmpty()) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(message("username is required"));
        }

        // 사용자 검색
        // 查询用户
        User user = userRepository.findByUsername(username.trim())
                .orElse(null);

        // 사용자 없으면 에러 반환
        // 用户不存在返回错误
        if (user == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(message("User not found"));
        }

        // 사용자 정보 수정
        // 更新用户信息
        user.setName(trimToNull(request.getName()));
        user.setPhone(trimToNull(request.getPhone()));
        user.setEmail(trimToNull(request.getEmail()));
        user.setAddress(trimToNull(request.getAddress()));
        user.setAvatar(trimToNull(request.getAvatar()));
        user.setGender(trimToNull(request.getGender()));
        user.setAge(parseNullableInt(request.getAge()));
        user.setNationality(trimToNull(request.getNationality()));

        // 프론트엔드:
        // teachSkill / learnSkill 사용
        //
        // 백엔드 entity:
        // skillOffer / skillWant 저장
        //
        // 前端使用:
        // teachSkill / learnSkill
        //
        // 后端数据库实体使用:
        // skillOffer / skillWant
        user.setSkillOffer(trimToNull(request.getTeachSkill()));
        user.setSkillWant(trimToNull(request.getLearnSkill()));

        // 데이터 저장
        // 保存数据库
        User saved = userRepository.save(user);

        // 수정된 데이터 반환
        // 返回更新后的数据
        return ResponseEntity.ok(toProfile(saved));
    }

    /**
     * User → ProfileResponse 변환
     * User → ProfileResponse 转换
     */
    private ProfileResponse toProfile(User user) {

        return new ProfileResponse(
                user.getUsername(),
                user.getName(),
                user.getPhone(),
                user.getEmail(),
                user.getAddress(),
                user.getAvatar(),
                user.getGender(),
                user.getAge(),
                user.getSkillOffer(),
                user.getNationality(),
                user.getSkillWant()
        );
    }

    /**
     * 에러 메시지 반환
     * 返回错误信息
     */
    private Map<String, Object> message(String msg) {

        Map<String, Object> res = new LinkedHashMap<>();

        res.put("message", msg);

        return res;
    }

    /**
     * 문자열 공백 제거
     * 清理字符串空格
     */
    private String trimToNull(String value) {

        // null 체크
        // null 判断
        if (value == null) {
            return null;
        }

        // 양쪽 공백 제거
        // 去除前后空格
        String trimmed = value.trim();

        // 빈 문자열이면 null 반환
        // 空字符串返回 null
        return trimmed.isEmpty() ? null : trimmed;
    }

    /**
     * 문자열 나이 → 숫자 변환
     * 字符串年龄 → 数字转换
     */
    private Integer parseNullableInt(String value) {

        String trimmed = trimToNull(value);

        // 값 없으면 null
        // 没值则返回 null
        if (trimmed == null) {
            return null;
        }

        try {

            // 숫자 변환
            // 转换数字
            return Integer.parseInt(trimmed);

        } catch (NumberFormatException e) {

            // 숫자 변환 실패 시 null
            // 转换失败返回 null
            return null;
        }
    }
}