package com.project.superfarm.repository;

import com.project.superfarm.entity.CustomerContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerContactRepository extends JpaRepository<CustomerContact,Long> {


}
