package com.project.superfarm.controller;


import com.project.superfarm.service.TestTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestTimeController {

    @Autowired
    private TestTimeService testTimeService;

    @RequestMapping(path="/{id}",method = RequestMethod.GET)
    public void TestTime(@RequestParam String id){

        testTimeService.createTime(id);

    }



}
