package com.project.superfarm.repository;

import com.project.superfarm.entity.CustomerLoged;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerLogedRepository extends JpaRepository<CustomerLoged,Long> {


}
