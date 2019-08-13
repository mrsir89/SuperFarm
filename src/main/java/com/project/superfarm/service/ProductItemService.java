package com.project.superfarm.service;


import com.project.superfarm.entity.product.Product;
import com.project.superfarm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductItemService {


    @Autowired
    private ProductRepository productRepository;

    /*
        porduct lower category 로 검색

     */
    public List<Product> fromLowerCategory(int lowerCategoryCode){

        List<Product> products = productRepository.loadLowerCategory(lowerCategoryCode);
        if(products.size()==0){
            return null;
        }
        return products;

    }

    public List<Product> fromUpperCategory(int upperCategoryCode){

        List<Product> products = productRepository.loadUpperCategory(upperCategoryCode);
        if(products.size()==0){
            return null;
        }
        return products;
    }
    public List<Product> loadItemAll(){
        List<Product> products = productRepository.findAll();
        if(products.size()==0){
            return null;
        }
        return products;
    }
    public List<Product> loadFindItemName (String itemName){

        List<Product> products = productRepository.loadFindItemName(itemName);
        if(products.size()==0){
            return null;
        }
        return products;
    }


}
