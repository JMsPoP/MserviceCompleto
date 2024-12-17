package com.jmtsu.ms.auth.dto;

public record UserRequest(
        String name,
        String email,
        String password  ) {
}
