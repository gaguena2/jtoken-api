package com.gaguena.jtokenapi.core.token;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tokens")
public class TokenEntity {

    @Id
    private String id;

    private String userId;

    @Indexed(unique = true)
    private String token;

    private TokenType type;

    private boolean revoked;

    private Instant createdAt;

    @Indexed(expireAfterSeconds = 0)
    private Instant expiresAt;
}