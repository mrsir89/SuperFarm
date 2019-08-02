package com.project.superfarm.repository;

import com.project.superfarm.entity.TestTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TestTimeRepository extends JpaRepository<TestTime,String> {


}
