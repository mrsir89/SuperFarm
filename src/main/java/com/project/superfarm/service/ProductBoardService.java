package com.project.superfarm.service;

import com.project.superfarm.entity.board.ProductBoard;
import com.project.superfarm.repository.boardRepository.ProductBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductBoardService {

    @Autowired
    private ProductBoardRepository productBoardRepository;

    @Autowired
    private QuestionBoardService questionBoardService;

    @Autowired
    private ReviewBoardService reviewBoardService;

    public List<ProductBoard> loadProductBoardAll() {

        List<ProductBoard> productBoards = productBoardRepository.findAll();

        if(productBoards==null){
            return null;
        }
        return productBoards;
    }

    public List<ProductBoard> loadProductBoardSearch(String name) {

        List<ProductBoard> productBoards =
                productBoardRepository.loadSearchKeyword(name);
        if(productBoards.size()==0){
            return null;
        }else{
            return productBoards;
        }
    }

    public List<ProductBoard> loadProductBoardUpper(int upperCode) {

        List<ProductBoard> productBoards =
                productBoardRepository.findAllByUpperCodeAndProductBoardDeleted(upperCode,"false");
        if(productBoards.size()==0){
            return null;
        }else{
            return productBoards;
        }
    }

    public List<ProductBoard> loadProductBoardLower(int lowerCode){

        List<ProductBoard> productBoards=
                productBoardRepository.findAllByLowerCodeAndProductBoardDeleted(lowerCode,"false");
        if(productBoards.size()==0){
            return null;
        }else{
            return productBoards;
        }

    }


    public ProductBoard loadProductDetails(Long num) throws ClassNotFoundException {

        Optional<ProductBoard> productBoard ;
        productBoard = productBoardRepository.findById(num);
        if(productBoard.isPresent()){
            return productBoard.get();
        }else{
            throw new ClassNotFoundException();
        }
    }
}
