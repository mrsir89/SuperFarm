package com.project.superfarm.repository;


import com.project.superfarm.entity.Customer;
import com.project.superfarm.entity.MarketAdmin;
import com.project.superfarm.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindByUsername() throws Exception {
        Customer customer = customerRepository.findByCid("tester6");
        Assert.assertNotNull(customer);
        Assert.assertEquals(customer.getCid(), "tester6");
    }

    @Test
    public void testInserNewUser() throws Exception {
        Customer newCustomer = new Customer();
        newCustomer.setCid("tester6");
        newCustomer.setC_pwd("Test1234");

        Customer customer = entityManager.persist(newCustomer);
        Assert.assertNotNull(customer); // 널이 아니여지 성공
        Assert.assertEquals(customer.getCid(), newCustomer.getCid());
    }

    @Test
    public void testInserNewAdmin() throws Exception {
        MarketAdmin newAdmin = new MarketAdmin();
        newAdmin.setAdminid("tester6");
        newAdmin.setAdminpwd("Test1234");

        MarketAdmin admin = entityManager.persist(newAdmin);
        Assert.assertNotNull(admin); // 널이 아니여지 성공
        Assert.assertEquals(admin.getAdminid(), admin.getAdminpwd());
    }
}
