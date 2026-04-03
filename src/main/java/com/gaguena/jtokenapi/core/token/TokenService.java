package com.gaguena.jtokenapi.core.token;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gaguena.jtokenapi.core.user.UserEntity;
import com.gaguena.jtokenapi.core.user.UserRepository;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;

    @Value("${token.expiration.minutes:60}") // default 1 hora
    private long tokenExpirationMinutes;
    /**
     * Cria e salva um token no Mongo
     */
    public TokenEntity createToken(TokenData data) {
        var tokenValue = UUID.randomUUID().toString();

        // regra de expiração definida aqui, não no DTO
        Instant now = Instant.now();
        Instant expiresAt = now.plus(tokenExpirationMinutes, ChronoUnit.MINUTES);// token válido por


        TokenEntity tokenEntity = TokenEntity.builder()
                .userId(data.userId())   // id do usuário
                .token(tokenValue)
                .type(TokenType.BEARER)
                .revoked(Boolean.FALSE)
                .createdAt(now)
                .expiresAt(expiresAt)
                .build();

        return tokenRepository.save(tokenEntity);
    }

    /**
     * Busca token válido (não revogado e não expirado)
     */
    public Optional<TokenEntity> findValidToken(String tokenValue) {
        return tokenRepository.findByTokenAndRevokedFalse(tokenValue)
                .filter(t -> t.getExpiresAt().isAfter(Instant.now()));
    }

    /**
     * Revoga um token
     */
    public void revokeToken(String tokenValue) {
        tokenRepository.findByToken(tokenValue).ifPresent(token -> {
            token.setRevoked(true);
            tokenRepository.save(token);
        });
    }
    
    public Optional<UserEntity> getUserFromToken(String token) {
        return tokenRepository.findByToken(token)
                .filter(t -> !t.isRevoked() && t.getExpiresAt().isAfter(Instant.now()))
                .flatMap(t -> userRepository.findById(t.getUserId()));
    }
}