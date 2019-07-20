package com.example.spring.catalog.repository;

import com.example.spring.catalog.entity.MerchantProductComposite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantProductCompositeRepository
        extends JpaRepository<MerchantProductComposite, Long> {
}
