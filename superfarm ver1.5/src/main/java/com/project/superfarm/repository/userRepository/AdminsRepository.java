package com.project.superfarm.repository.userRepository;


import com.project.superfarm.entity.User.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminsRepository extends JpaRepository<Admin,Long> {


}
