package com.example.soul.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UploadController {

    private final Path uploadRoot = Paths.get("uploads");

    /**
     * 文件上传接口
     * 用于上传用户头像等文件
     */
    /**
     * 上传文件（头像）
     *
     * @param file 上传的文件
     * @return 文件访问路径（URL）
     */
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message("file is required"));
        }

        try {
            Files.createDirectories(uploadRoot);

            String original = file.getOriginalFilename();
            String safeExt = extractExtension(original);
            String filename = UUID.randomUUID().toString().replace("-", "") + safeExt;

            Path target = uploadRoot.resolve(filename).normalize();
            Path rootAbs = uploadRoot.toAbsolutePath().normalize();
            Path targetAbs = target.toAbsolutePath().normalize();
            if (!targetAbs.startsWith(rootAbs)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message("invalid filename"));
            }

            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

            String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(filename)
                    .toUriString();

            Map<String, Object> res = new LinkedHashMap<>();
            res.put("success", true);
            res.put("url", url);
            return ResponseEntity.ok(res);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message("upload failed"));
        }
    }

    private Map<String, Object> message(String msg) {
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("message", msg);
        return res;
    }

    private String extractExtension(String filename) {
        if (filename == null) {
            return "";
        }
        String clean = filename.trim();
        int idx = clean.lastIndexOf('.');
        if (idx < 0 || idx == clean.length() - 1) {
            return "";
        }
        String ext = clean.substring(idx).toLowerCase();
        // Minimal allow-list; default to no extension if suspicious.
        if (ext.matches("\\.(png|jpg|jpeg|gif|webp|bmp|svg)")) {
            return ext;
        }
        return "";
    }
}
