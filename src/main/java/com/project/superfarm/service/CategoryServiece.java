package com.project.superfarm.service;

import com.project.superfarm.entity.UpperCategory;
import com.project.superfarm.repository.UpperCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiece {

    @Autowired
    private UpperCategoryRepository categoryRepository;



    public List<UpperCategory> findall(){
        List<UpperCategory> categories = categoryRepository.findAll();

        if(categories.size()==0){
            return null;
        }
        else{
            return categories;
        }
    }


//    public List<ProductBoard> findUpperCategory(Integer upper_code){
//        List<ProductBoard> categories = productBoardRepository.(upper_code);
//
//        if(categories.size()==0){
//            return null;
//        }
//        else{
//            return categories;
//        }
//    }
//
//
//    public List<ProductBoard> findLowerCategory(Integer upperCode, Integer lowerCode) {
//
//
//
//    }
}
