package com.project.superfarm.repository;

import com.project.superfarm.entity.UpperCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpperCategoryRepository extends JpaRepository<UpperCategory,Integer> {


    List<UpperCategory> findAllByUpperCode(Integer id);



}
