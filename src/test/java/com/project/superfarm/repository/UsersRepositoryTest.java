package com.project.superfarm.repository;


import com.project.superfarm.entity.board.ProductBoard;
import com.project.superfarm.entity.product.Product;
import com.project.superfarm.entity.user.Customer;
import com.project.superfarm.entity.user.Users;
import com.project.superfarm.repository.boardRepository.ProductBoardRepository;
import com.project.superfarm.repository.boardRepository.QuestionBoardRepository;
import com.project.superfarm.repository.userRepository.CustomerRepository;
import com.project.superfarm.repository.userRepository.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
