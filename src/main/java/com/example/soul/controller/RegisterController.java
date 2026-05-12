package com.example.soul.controller; // 当前类所在的包

import com.example.soul.dto.RegisterRequest; // 注册请求数据类，接收前端传来的 username、password、phone、email
import com.example.soul.entity.User; // 用户实体类，对应数据库里的用户表
import com.example.soul.service.UserService; // 用户业务层，用来查询和保存用户
import org.slf4j.Logger; // 日志工具
import org.slf4j.LoggerFactory; // 创建日志对象
import org.springframework.web.bind.annotation.PostMapping; // 处理 POST 请求
import org.springframework.web.bind.annotation.RequestBody; // 接收前端 JSON 数据
import org.springframework.web.bind.annotation.RequestMapping; // 设置接口基础路径
import org.springframework.web.bind.annotation.RestController; // 标记这是后端接口控制器

import java.util.HashMap; // 用来创建返回给前端的数据
import java.util.Map; // Map 类型，用来返回 JSON 结构

@RestController // 表示这个类是接口控制器，返回 JSON 数据
@RequestMapping("/api") // 这个类下面所有接口都以 /api 开头
public class RegisterController {

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class); // 创建日志对象

    private final UserService userService; // 注入 UserService，用来处理用户相关逻辑

    public RegisterController(UserService userService) { // 构造方法，Spring 自动传入 UserService
        this.userService = userService;
    }

    // 注册接口
    @PostMapping("/register") // 当前端 POST 请求 /api/register 时，执行这个方法
    public Map<String, Object> register(@RequestBody RegisterRequest request) { // 接收前端传来的注册数据
        Map<String, Object> result = new HashMap<>(); // 创建返回结果

        String username = normalizeRequired(request.getUsername()); // 处理用户名，去掉空格，不能为空
        String password = normalizeRequired(request.getPassword()); // 处理密码，去掉空格，不能为空
        String phone = normalizeNullable(request.getPhone()); // 处理手机号，可以为空
        String email = normalizeNullable(request.getEmail()); // 处理邮箱，可以为空

        if (username == null || password == null) { // 判断用户名或密码是否为空
            result.put("success", false); // 返回失败
            result.put("message", "Username and password are required"); // 返回失败原因
            return result; // 结束方法
        }

        if (userService.existsByUsername(username)) { // 判断用户名是否已经存在
            result.put("success", false); // 返回失败
            result.put("message", "Username already exists"); // 用户名重复
            return result; // 结束方法
        }

        if (phone != null && userService.existsByPhone(phone)) { // 如果手机号不为空，检查手机号是否重复
            result.put("success", false); // 返回失败
            result.put("message", "Phone already exists"); // 手机号重复
            return result; // 结束方法
        }

        if (email != null && userService.existsByEmail(email)) { // 如果邮箱不为空，检查邮箱是否重复
            result.put("success", false); // 返回失败
            result.put("message", "Email already exists"); // 邮箱重复
            return result; // 结束方法
        }

        User user = new User(); // 创建新的用户对象
        user.setUsername(username); // 设置用户名
        user.setPassword(password); // 设置密码
        user.setPhone(phone); // 设置手机号
        user.setEmail(email); // 设置邮箱
        user.setName(username); // 默认把 name 设置成 username

        userService.save(user); // 保存用户到数据库

        result.put("success", true); // 返回注册成功
        result.put("username", user.getUsername()); // 返回注册成功的用户名
        result.put("message", "Register successful"); // 返回成功信息
        log.info("Register success for username={}", user.getUsername()); // 在控制台打印注册成功日志
        return result; // 把结果返回给前端
    }

    private boolean isBlank(String value) { // 判断字符串是否为空
        return value == null || value.trim().isEmpty(); // null 或者去掉空格后为空，都算空
    }

    private String normalizeRequired(String value) { // 处理必填字段
        if (isBlank(value)) { // 如果是空
            return null; // 返回 null
        }
        return value.trim(); // 去掉前后空格后返回
    }

    private String normalizeNullable(String value) { // 处理非必填字段
        if (isBlank(value)) { // 如果是空
            return null; // 返回 null
        }
        return value.trim(); // 去掉前后空格后返回
    }
}