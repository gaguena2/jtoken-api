package com.gaguena.jtokenapi.core.token;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends MongoRepository<TokenEntity, String> {

    /**
     * Busca token pelo valor
     */
    Optional<TokenEntity> findByToken(String token);

    /**
     * Busca token válido (não revogado)
     */
    Optional<TokenEntity> findByTokenAndRevokedFalse(String token);

    /**
     * Busca tokens que vão expirar em um intervalo
     */
    List<TokenEntity> findByExpiresAtBetweenAndRevokedFalse(
            Instant start,
            Instant end
    );

    /**
     * Remove tokens de um usuário
     */
    void deleteByUserId(String userId);
}