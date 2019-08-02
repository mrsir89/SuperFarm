package com.example.spring.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CatalogApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CatalogApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(CatalogApplication.class, args);


    }// main end

}// class end




//        System.out.println("=========reverse==========");
//
//
//        int[] nums = {9,8,7,6,5,4,3,2,1,0};
//
//        int size = nums.length;
//        int j=size -1;
//
//        for(int i = 0;i<nums.length/2;i++, j--){
//            int temp = nums[i];
//            nums[i] = nums[j];
//            nums[j] = temp;
//        }


//        System.out.println("======삽입정렬======");
//
//
//        int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
//
//        for (int k = 0; k < nums.length; k++) {
//            for (int i = 1; i < nums.length; i++) {
//                int j = i;
//                while (j > 0 && nums[j - 1] > nums[j]) {
//
//                    int temp = nums[j - 1];
//                    nums[j - 1] = nums[j];
//                    nums[j] = temp;
//                    j--;
//
//                }
//            }
//            System.out.print(nums[k]);
//        }
//
//        System.out.println("\n=====교환정렬, 선택정렬=====");
//
//
//        for (int i = 0; i < nums.length - 1; i++) {
//            int min = i;
//
//            for (int j = i + 1; i < nums.length; j++) {
//                if (nums[min] > nums[j]) {
//                    min = j;
//                }
//            }
//            if(i!=min){
//                int temp = nums[i];
//                nums[i] = nums[min];
//                nums[min] = temp;
//            }
//
//        }



