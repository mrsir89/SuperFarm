package com.project.superfarm.repository;


import com.project.superfarm.entity.Board.ProductBoard;
import com.project.superfarm.repository.boardRepository.ProductBoardRepository;
import com.project.superfarm.repository.userRepository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsersRepositoryTest {

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
    public void cutomerTest(){

    }



}
