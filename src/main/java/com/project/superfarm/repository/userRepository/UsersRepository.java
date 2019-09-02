package com.project.superfarm.repository.userRepository;

import com.project.superfarm.entity.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {



    Optional<Users> findByUserId(String id);

    @Query(value="SELECT user_email FROM users WHERE user_email = ?1",nativeQuery = true)
    Optional<String> overlapEmail(String email);

    @Query(value="SELECT user_id FROM users WHERE user_id =?1",nativeQuery = true)
    Optional<String> overlapId(String id);

    @Modifying
    @Query(value="UPDATE users set user_last_connect = NOW() WHERE user_id=?1",nativeQuery = true)
    void createLog(String userId);

}
