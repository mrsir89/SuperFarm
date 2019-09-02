package com.project.superfarm.service;

import com.project.superfarm.entity.board.ProductBoard;
import com.project.superfarm.entity.product.Product;
import com.project.superfarm.model.ProductListModel;
import com.project.superfarm.repository.ProductRepository;
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


    public ProductBoard loadProductDetails(Long num) throws ClassNotFoundException {

        Optional<ProductBoard> productBoard;
        productBoard = productBoardRepository.findById(num);

        if (productBoard.isPresent()) {
            productBoard.get().setProductTypeName(
                    productBoard.get().getProductList().get(0).getProductType().getProductTypeName());
            return productBoard.get();

        } else {
            throw new ClassNotFoundException();
        }
    }

    /**
     * @return List<ProductBoard>
     */
    public List<ProductBoard> loadMainProduct() {
        return productBoardRepository.findMainProduct();
    }

    public ProductBoard loadLowerBestProduct(Long lower) {
        return null;
    }

    public List<ProductListModel> findByLowerProductBoard(Integer lower) {

        List<ProductBoard> productBoardList = productBoardRepository.findByLowerProductBoard(lower);
        List<ProductListModel> productListModelList = new ArrayList<>();
        for (ProductBoard p : productBoardList
        ) {
            productListModelList.add(p.getProductListModel());
        }

        return productListModelList;
    }
}
