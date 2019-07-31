package com.project.superfarm.repository;

import com.project.superfarm.entity.MarketAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketAdminRepository extends JpaRepository<MarketAdmin, Long> {

    MarketAdmin findByAdminid(String username);
}
