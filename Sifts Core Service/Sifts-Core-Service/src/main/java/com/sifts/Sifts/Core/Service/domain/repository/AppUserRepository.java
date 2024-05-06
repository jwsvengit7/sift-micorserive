package com.sifts.Sifts.Core.Service.domain.repository;

import com.sifts.Sifts.Core.Service.domain.enities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByEmail(String email);
    AppUser findByNIN(String nimNumber);
    boolean existsByEmail(String email);

}
