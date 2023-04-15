package com.treeproject.identity.repository;

import com.treeproject.identity.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findTopByUserIdAndIsActive(int userId, boolean isActive);
    Optional<Token> findByTokenAndIsActive(String token, boolean isActive);

    @Transactional
    @Modifying
    @Query("UPDATE Token t SET t.isActive = false WHERE t.userId =:userId ")
    void expiredAllActiveUserToken(@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("UPDATE Token t SET t.isActive = false WHERE t.token =:token ")
    void destroyToken(@Param("token") String token);
}
