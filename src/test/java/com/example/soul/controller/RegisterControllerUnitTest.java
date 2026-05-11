package com.example.soul.controller;

import com.example.soul.dto.RegisterRequest;
import com.example.soul.entity.User;
import com.example.soul.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RegisterControllerUnitTest {

    @Test
    void register_rejectsDuplicateUsername() {
        UserService userService = mock(UserService.class);
        when(userService.existsByUsername("alice")).thenReturn(true);

        RegisterController controller = new RegisterController(userService);

        RegisterRequest req = new RegisterRequest();
        req.setUsername("alice");
        req.setPassword("pw");
        req.setPhone("010-1111-2222");
        req.setEmail("alice@example.com");

        Map<String, Object> res = controller.register(req);
        assertFalse((Boolean) res.get("success"));
        assertEquals("Username already exists", res.get("message"));
    }

    @Test
    void register_rejectsDuplicatePhone() {
        UserService userService = mock(UserService.class);
        when(userService.existsByUsername("bob")).thenReturn(false);
        when(userService.existsByPhone("010-1111-2222")).thenReturn(true);

        RegisterController controller = new RegisterController(userService);

        RegisterRequest req = new RegisterRequest();
        req.setUsername("bob");
        req.setPassword("pw");
        req.setPhone("010-1111-2222");
        req.setEmail("bob@example.com");

        Map<String, Object> res = controller.register(req);
        assertFalse((Boolean) res.get("success"));
        assertEquals("Phone already exists", res.get("message"));
    }

    @Test
    void register_rejectsDuplicateEmail() {
        UserService userService = mock(UserService.class);
        when(userService.existsByUsername("carol")).thenReturn(false);
        when(userService.existsByPhone("010-2222-3333")).thenReturn(false);
        when(userService.existsByEmail("alice@example.com")).thenReturn(true);

        RegisterController controller = new RegisterController(userService);

        RegisterRequest req = new RegisterRequest();
        req.setUsername("carol");
        req.setPassword("pw");
        req.setPhone("010-2222-3333");
        req.setEmail("alice@example.com");

        Map<String, Object> res = controller.register(req);
        assertFalse((Boolean) res.get("success"));
        assertEquals("Email already exists", res.get("message"));
    }

    @Test
    void register_trimsAndNormalizesBlankPhoneEmailToNull() {
        UserService userService = mock(UserService.class);
        when(userService.existsByUsername("dave")).thenReturn(false);
        when(userService.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        RegisterController controller = new RegisterController(userService);

        RegisterRequest req = new RegisterRequest();
        req.setUsername("  dave  ");
        req.setPassword("  pw  ");
        req.setPhone("   ");
        req.setEmail(null);

        Map<String, Object> res = controller.register(req);
        assertTrue((Boolean) res.get("success"));
        assertEquals("dave", res.get("username"));

        ArgumentCaptor<User> saved = ArgumentCaptor.forClass(User.class);
        verify(userService).save(saved.capture());
        assertEquals("dave", saved.getValue().getUsername());
        assertEquals("pw", saved.getValue().getPassword());
        assertNull(saved.getValue().getPhone());
        assertNull(saved.getValue().getEmail());
    }
}

