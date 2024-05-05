package com.sifts.Sifts.Core.Service.domain.repository;

import com.sifts.Sifts.Core.Service.domain.enities.AppID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppIDRepository extends JpaRepository<AppID,Long> {
    AppID findByAppName(String appName);
    AppID findByAppNameAndAppPassword(String appName,String appPassword);
}
