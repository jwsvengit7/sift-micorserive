package com.sifts.Sifts.Auth.Module.domain.repository;

import com.sifts.Sifts.Auth.Module.domain.enities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
