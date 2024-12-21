package com.clearingcobwebsbackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clearingcobwebsbackend.entities.PasswordResetToken;

@Repository
public interface ResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

}
