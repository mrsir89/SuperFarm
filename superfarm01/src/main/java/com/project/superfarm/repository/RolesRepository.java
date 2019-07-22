package com.project.superfarm.repository;

import com.project.superfarm.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles,String> {

    Roles findByName(String name);

}
