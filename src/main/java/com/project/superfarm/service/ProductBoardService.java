package com.project.superfarm.service;

import com.project.superfarm.entity.board.ProductBoard;
import com.project.superfarm.entity.board.ProductBoardList;
import com.project.superfarm.entity.product.Product;
import com.project.superfarm.model.ProductListModel;
import com.project.superfarm.repository.ProductRepository;
import com.project.superfarm.repository.boardRepository.ProductBoardListRepository;
import com.project.superfarm.repository.boardRepository.ProductBoardRepository;
import com.project.superfarm.util.ExceptionList.UrlNotFountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductBoardService {

    @Autowired
    private ProductBoardRepository productBoardRepository;

    @Autowired
    private ProductBoardListRepository productBoardListRepository;

    public List<ProductBoard> loadProductBoardAll() {

        List<ProductBoard> productBoards = productBoardRepository.findAll();

        if (productBoards == null) {
            return null;
        }
        return productBoards;
    }

    public List<ProductBoard> loadProductBoardSearch(String name) {

        List<ProductBoard> productBoards =
                productBoardRepository.loadSearchKeyword(name);
        if (productBoards.size() == 0) {
            return null;
        } else {
            return productBoards;
        }
    }

    public List<ProductBoard> loadProductBoardUpper(int upperCode) {

        List<ProductBoard> productBoards =
                productBoardRepository.findAllByUpperCodeAndProductBoardDeleted(upperCode, "false");
        if (productBoards.size() == 0) {
            return null;
        } else {
            return productBoards;
        }
    }

    public List<ProductBoard> loadProductBoardLower(Integer lowerCode) {

        List<ProductBoard> productBoards =
                productBoardRepository.findAllByLowerCodeAndProductBoardDeleted(lowerCode, "false");

        return productBoards;

    }


    public ProductBoard loadProductDetails(Long num) {

        Optional<ProductBoard> productBoard;
        productBoard = productBoardRepository.findById(num);

        if (productBoard.isPresent()) {
            productBoard.get().setProductTypeName(
                    productBoard.get().getProductList().get(0).getProductType().getProductTypeName());
            return productBoard.get();

        } else {
            throw new UrlNotFountException();
        }
    }


    /**
     * @return List<ProductBoard>
     */
    public List<ProductListModel> loadMainProduct() {

        List<ProductBoardList> productBoardList
                = productBoardListRepository.findByBestProduct();

        return returnModel(productBoardList);
    }

    public ProductBoard loadLowerBestProduct(Long lower) {
        return null;
    }


    public List<ProductListModel> findByLowerProductBoard(Integer lower) {

        List<ProductBoardList> productBoardList
                = productBoardListRepository.findByLowerProductBoard(lower);

        return returnModel(productBoardList);
    }


    public List<ProductListModel> findByUpperProductBoard(Integer upper) {

        List<ProductBoardList> productBoardList
                = productBoardListRepository.findByUpperProductBoard(upper);

        return returnModel(productBoardList);
    }


    public List<ProductListModel> findBySearchProductBoard(String search) {

        List<ProductBoardList> productBoardList
                = productBoardListRepository.findBySearchProductBoard(search);

        return returnModel(productBoardList);
    }

    public List<ProductListModel> findByAllProductBoard() {

        List<ProductBoardList> productBoardList
                = productBoardListRepository.findByAllProductBoard();

        return returnModel(productBoardList);
    }


    // ProductBoardList 타입을 ProductListModel 타입으로 넣어 준다.
    private List<ProductListModel> returnModel(List<ProductBoardList> productBoardList) {

        List<ProductListModel> productListModelList = new ArrayList<>();

        for (ProductBoardList p : productBoardList) {
            productListModelList.add(p.getProductListModel());
        }

        return productListModelList;

    }


}
