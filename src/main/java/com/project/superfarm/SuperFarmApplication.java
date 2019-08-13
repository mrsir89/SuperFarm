/*
 * project SuperFarm
 * build date  2019.07.21
 *
 * log : 주말스터디 선생님 조언으로 순서잡고 시작단계
 * //////////////////////////////////////////
 * 
 *  2019.08.13  Version 2.0 (GitHub setup)
 * */


package com.project.superfarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/*
2019.07.21 CustomerContact 저장 추가  users/me로 리턴 받을때 contact 추가
 */
@SpringBootApplication
public class SuperFarmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperFarmApplication.class, args);
    }

}
