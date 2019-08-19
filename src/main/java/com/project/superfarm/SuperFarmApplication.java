/*
 * project SuperFarm
 * build date  2019.07.21
 *
 * log : 주말스터디 선생님 조언으로 순서잡고 시작단계
 * */


package com.project.superfarm;

import com.project.superfarm.util.file.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/*
2019.07.21 CustomerContact 저장 추가  users/me로 리턴 받을때 contact 추가
 */

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class SuperFarmApplication {

    /**
     *
     * @param : merging test
     */

    public static void main(String[] args) {
        SpringApplication.run(SuperFarmApplication.class, args);
    }

}
