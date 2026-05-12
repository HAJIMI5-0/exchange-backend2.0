package com.example.soul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HealthController {


    //字如其名 健康检查接口
    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("status", "ok");
        res.put("serverTime", LocalDateTime.now().toString());
        return res;
    }
}

