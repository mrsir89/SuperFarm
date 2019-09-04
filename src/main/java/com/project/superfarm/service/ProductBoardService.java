package com.project.superfarm.service;

import com.project.superfarm.entity.board.ProductBoard;
import com.project.superfarm.entity.board.ProductBoardList;
import com.project.superfarm.entity.product.Product;
import com.project.superfarm.model.ProductBoardCreateModel;
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


    /**
     * @param num
     * @return ProductBoard
     * @deprecated 프로덕트 보드의 상세 정보 product_board_num(pk) 기준으로 리턴
     */
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
     * 메인화면에 뜨는 product_board 리스트
     * product_best의 1~3까지만 나오게 되어있음
     *
     * @return List<ProductBoardModel>
     */
    public List<ProductListModel> loadMainProduct() {

        List<ProductBoardList> productBoardList
                = productBoardListRepository.findByBestProduct();

        return returnModel(productBoardList);
    }

    public List<ProductListModel>  loadLowerBestProduct(Integer lower) {



        List<ProductBoardList> productBoardLists
                =productBoardListRepository.findByLowerBestProduct(lower);
        return returnModel(productBoardLists);
    }


    /**
     * lower 기준으로 product_board return
     *
     * @param lower
     * @return product_board
     */
    public List<ProductListModel> findByLowerProductBoard(Integer lower) {

        List<ProductBoardList> productBoardList
                = productBoardListRepository.findByLowerProductBoard(lower);

        return returnModel(productBoardList);
    }


    /**
     * upper 기준으로 product_board return
     *
     * @param upper
     * @return List<ProductListModel>
     */
    public List<ProductListModel> findByUpperProductBoard(Integer upper) {

        List<ProductBoardList> productBoardList
                = productBoardListRepository.findByUpperProductBoard(upper);

        return returnModel(productBoardList);
    }


    /**
     * 검색어 기준으로 product_board return
     *
     * @param search
     * @return List<ProductListModel>
     */
    public List<ProductListModel> findBySearchProductBoard(String search) {

        List<ProductBoardList> productBoardList
                = productBoardListRepository.findBySearchProductBoard(search);

        return returnModel(productBoardList);
    }

    /**
     * 전체 product_board return
     *
     * @return List<ProductListModel>
     */
    public List<ProductListModel> findByAllProductBoard() {

        List<ProductBoardList> productBoardList
                = productBoardListRepository.findByAllProductBoard();

        return returnModel(productBoardList);
    }


    /**
     * @param productBoardList
     * @return List<ProductListModel>
     * @deprecated List<productBoard>에서 ProductModel 객체로 set List에 넣어서 다시 return
     * 위의 메소드들 List<ProductListModel> 로 return 하는 method 공용 사용
     */
    private List<ProductListModel> returnModel(List<ProductBoardList> productBoardList) {

        List<ProductListModel> productListModelList = new ArrayList<>();

        for (ProductBoardList p : productBoardList) {
            productListModelList.add(p.getProductListModel());
        }

        return productListModelList;

    }

    /**
     * @deprecated short으로 들어 올경우 임시 저장후 pk 번호 return
     *              notnull 인 부분만 upper lower만 넣어서 저장 한다.
     * @param boardModel
     * @return ProductBoard
     */
    public ProductBoard createProduct(ProductBoardCreateModel boardModel) {

        ProductBoard productBoard = new ProductBoard();

        productBoard.setProductBoardTitle(boardModel.getProductBoardTitle());
        // 임시저장 lower 와 uppder 코드가 0이면 임시 저장
        productBoard.setUpperCode(0);
        // 임시저장 lower 와 uppder 코드가 0이면 임시 저장
        productBoard.setLowerCode(0);

        return productBoardRepository.save(productBoard);
    }

}
