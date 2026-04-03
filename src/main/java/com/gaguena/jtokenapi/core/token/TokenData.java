package com.gaguena.jtokenapi.core.token;

public record TokenData(
        String userId,
        String token,
        String type,
        Long expiresInSeconds
) {
}