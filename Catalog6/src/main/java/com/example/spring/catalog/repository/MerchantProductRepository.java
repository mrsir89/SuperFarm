package com.example.spring.catalog.repository;

import com.example.spring.catalog.entity.Merchant;
import com.example.spring.catalog.entity.MerchantProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MerchantProductRepository extends JpaRepository<MerchantProduct, String> {

    List<MerchantProduct> findByMerchantId(String merchantId);

    MerchantProduct findByIdAndMerchantId(String id,String merchantId);
}