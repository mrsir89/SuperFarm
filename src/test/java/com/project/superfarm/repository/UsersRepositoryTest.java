package com.project.superfarm.repository;


import com.project.superfarm.entity.board.ProductBoard;
import com.project.superfarm.entity.board.ProductBoardList;
import com.project.superfarm.entity.product.Product;
import com.project.superfarm.entity.user.Customer;
import com.project.superfarm.entity.user.Users;
import com.project.superfarm.model.ProductBoardCreateModel;
import com.project.superfarm.model.ProductListModel;
import com.project.superfarm.repository.boardRepository.ProductBoardListRepository;
import com.project.superfarm.repository.boardRepository.ProductBoardRepository;
import com.project.superfarm.repository.boardRepository.QuestionBoardRepository;
import com.project.superfarm.repository.userRepository.CustomerRepository;
import com.project.superfarm.repository.userRepository.UsersRepository;
import com.project.superfarm.service.ProductBoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UpperCategoryRepository upperCategoryRepository;

    @Autowired
    private LowerCategoryRepository lowerCategoryRepository;

    @Autowired
    private ProductBoardRepository productBoardRepository;

    @Autowired
    private QuestionBoardRepository questionBoardRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductBoardListRepository productBoardListRepository;



    // 바뀐 productBoard
    @Test
    public void newProductBoardTest(){

        List<ProductBoardList> productBoardList=productBoardListRepository.findByAllTest();

        System.out.println(productBoardList.toString());


//        List<ProductListModel> productListModelList = new ArrayList<>();
//        for (ProductBoardList p : productBoardList
//        ) {
//            productListModelList.add(p.getProductListModel());
//        }
//        for (ProductListModel a :productListModelList
//             ) {
//            System.out.println(a.toString());;
//        }

    }

    @Test
    public void productCreateTest(){
        ProductBoard productBoard = new ProductBoard();

        productBoard.setProductBoardTitle("임시 저장 테스트");
        productBoard.setLowerCode(0);
        productBoard.setUpperCode(0);
        // 임시저장 lower 와 uppder 코드가 0이면 임시 저장
        productBoard.setUpperCode(0);
        // 임시저장 lower 와 uppder 코드가 0이면 임시 저장
        productBoard.setLowerCode(0);

        ProductBoard test = productBoardRepository.save(productBoard);
        System.out.println(test.toString());
    }
    @Test
    public void productBoardTeset(){
        List<ProductBoard> productBoardList = productBoardRepository.findAll();
        System.out.println("실행 중?");
        System.out.println(productBoardList.get(1).getProductBoardNum());

        for (ProductBoard p :productBoardList) {
            System.out.println("===========");
            System.out.println(p.getProductBoardNum());
        }
    }

    @Test
    public void userTest(){

        Users<Customer> user = usersRepository.findByUserId("tester01").get();
        System.out.println(user.toString());

    }

    @Test
    public void productTestSelectProductType(){


        Optional<Product>product = productRepository.findById(1L);
        System.out.println(product.toString());
    }



}
