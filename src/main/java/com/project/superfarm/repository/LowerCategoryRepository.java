package com.project.superfarm.repository;


import com.project.superfarm.entity.LowerCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LowerCategoryRepository extends JpaRepository<LowerCategory,Integer> {

    List<LowerCategory> findAllByLowerCode(Integer lower_code);
}
