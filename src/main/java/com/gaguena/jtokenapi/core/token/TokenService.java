package com.gaguena.jtokenapi.core.token;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    /**
     * Cria e salva um token no Mongo
     */
    public TokenEntity createToken(TokenData data) {
        

        return null;
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
}