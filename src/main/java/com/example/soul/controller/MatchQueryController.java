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
 * 匹配接口
 */
@RestController
@RequestMapping("/api/match")
public class MatchQueryController {

    // 用户数据库
    private final UserRepository userRepository;

    // 构造注入
    public MatchQueryController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 匹配用户
     * GET /api/match
     */
    @GetMapping
    public List<MatchUserResponse> match(

            // 我会的技能
            @RequestParam(required = false) String haveSkill,

            // 我想学的技能
            @RequestParam(required = false) String wantSkill,

            // 返回人数
            @RequestParam(defaultValue = "5") int limit
    ) {

        // 去空格
        String have = normalize(haveSkill);
        String want = normalize(wantSkill);

        // 获取所有用户
        List<User> all = userRepository.findAll();

        // 存放筛选后的用户
        List<User> filtered = new ArrayList<>();

        // 开始筛选
        for (User u : all) {

            boolean ok = true;

            // 判断对方是否会我想学的技能
            if (have != null) {
                ok = ok && containsSkill(
                        u.getSkillWant(),
                        have
                );
            }

            // 判断对方是否想学我会的技能
            if (want != null) {
                ok = ok && containsSkill(
                        u.getSkillOffer(),
                        want
                );
            }

            // 符合条件加入列表
            if (ok) {
                filtered.add(u);
            }
        }

        // 随机排序
        Collections.shuffle(filtered);

        // limit不能小于0
        if (limit < 0) {
            limit = 0;
        }

        // 返回数据
        return filtered.stream()

                // 限制数量
                .limit(limit)

                // 转DTO
                .map(this::toResponse)

                // 转List
                .collect(Collectors.toList());
    }

    /**
     * User 转 Response
     */
    private MatchUserResponse toResponse(User u) {

        // 如果没名字就显示用户名
        String name = u.getName();

        if (name == null || name.trim().isEmpty()) {
            name = u.getUsername();
        }

        // 技能数组
        List<String> skills =
                splitSkills(u.getSkillOffer());

        // 想学数组
        List<String> wants =
                splitSkills(u.getSkillWant());

        // 返回前端数据
        return new MatchUserResponse(
                u.getId(),
                u.getUsername(),
                name,
                u.getAge(),
                u.getGender(),
                u.getNationality(),
                u.getAvatar(),
                skills,
                wants
        );
    }

    /**
     * 技能字符串转数组
     * "Java,Spring"
     * ->
     * ["Java","Spring"]
     */
    private List<String> splitSkills(String raw) {

        String v = normalize(raw);

        // 空返回空数组
        if (v == null) {
            return List.of();
        }

        // 按逗号切割
        String[] parts = v.split(",");

        List<String> res = new ArrayList<>();

        for (String p : parts) {

            String t = normalize(p);

            if (t != null) {
                res.add(t);
            }
        }

        return res;
    }

    /**
     * 判断是否包含技能
     */
    private boolean containsSkill(
            String raw,
            String target
    ) {

        if (raw == null) {
            return false;
        }

        String hay =
                raw.toLowerCase(Locale.ROOT);

        String needle =
                target.toLowerCase(Locale.ROOT);

        // 按逗号切割匹配
        for (String part : hay.split(",")) {

            String t = part.trim();

            if (!t.isEmpty() && t.equals(needle)) {
                return true;
            }
        }

        return hay.contains(needle);
    }

    /**
     * 去空格
     */
    private String normalize(String v) {

        if (v == null) {
            return null;
        }

        String t = v.trim();

        return t.isEmpty() ? null : t;
    }
}