package com.project.superfarm.repository;

import com.project.superfarm.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    //c_id로 customer 정보 가져오기
    //Customer findByc_id(String id);

    Customer findByCid(String cid);

    @Query(value = "UPDATE customer set c_point +=? WHERE c_num=?",nativeQuery = true)
    void updatePoint(int point, long c_num);





}
