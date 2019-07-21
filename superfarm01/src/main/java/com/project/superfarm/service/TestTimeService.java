package com.project.superfarm.service;


import com.project.superfarm.entity.TestTime;
import com.project.superfarm.repository.TestTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestTimeService {

    @Autowired
    private TestTimeRepository testTimeRepository;


    public void createTime(String id){

        TestTime testTime = new TestTime();
        testTime.setTest_pk(id);

        testTimeRepository.save(testTime);

    }

}
