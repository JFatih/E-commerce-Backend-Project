package com.example.e_commerce.repository.securityRepository;

import com.example.e_commerce.entity.user.*;
import com.example.e_commerce.entity.user.orders.Order;
import com.example.e_commerce.exceptions.ApiException;
import com.example.e_commerce.repository.userRepository.CardDetailsRepository;
import com.example.e_commerce.repository.userRepository.OrderRepository;
import jakarta.validation.constraints.Null;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    @Autowired
    private OrderRepository orderRepository;

    private ApplicationUser user;

    private Role testRole;

    private CardDetails testCard;

    private Order testOrder;

    @BeforeEach
    void setUp() {
        user = new ApplicationUser();
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setPassword("Test@123");

        Set<Role> testRoles = new HashSet<>();
        testRole = new Role();
        testRole.setName("Test");
        testRole.setCode(RoleCode.testCode);
        testRoles.add(testRole);

        user.setAuthorities(testRoles);

        user = userRepository.save(user);

//        testCard = new CardDetails();
//        testCard.setApplicationUser(user);
//        testCard.setCardNo("1453145314531453");
//        testCard.setExpireMonth(1);
//        testCard.setExpireYear(2025);
//        testCard.setNameOnCard("USER TEST");
//
//        testCard = cardDetailsRepository.save(testCard);
//        System.out.println(testCard);


//        testOrder = new Order();
//        Address testAddress = new Address();
//        testAddress.setApplicationUser(user);
//        testAddress.setTitle("Test address");
//        testAddress.setName("Test");
//        testAddress.setSurname("Order");
//        testAddress.setPhone("05555555555");
//        testAddress.setCity("Ankara");
//        testAddress.setDistrict("turkey");
//        testAddress.setNeighborhood("Batı");
//        testAddress.setAddress("1234 revenue");
//
//        testOrder.setOrderDate("123456");
//        testOrder.setCardDetails(testCard);
//        testOrder.setCardCcv(123);
//        testOrder.setPrice(10);
//
//        testOrder = orderRepository.save(testOrder);
//        System.out.println(testOrder);



    }

    @AfterEach
    void tearDown() {
        userRepository.delete(user);
        roleRepository.delete(testRole);
    }

    @Test
    void findUserByEmail() {
        Optional<ApplicationUser> user = userRepository.findUserByEmail("test@gmail.com");

        assertTrue(user.isPresent());
        assertEquals("test", user.get().getName());
        assertEquals("test@gmail.com", user.get().getEmail());
    }

    @Test
    void findUserCardsDetails() {
        ApplicationUser foundUser = userRepository.findById(user.getId()).orElseThrow();

        testCard = new CardDetails();
        testCard.setApplicationUser(foundUser);
        testCard.setCardNo("1453145314531453");
        testCard.setExpireMonth(12);
        testCard.setExpireYear(2025);
        testCard.setNameOnCard("USER TEST");

        cardDetailsRepository.save(testCard);

        CardDetails foundCard = userRepository.findUserCardsDetails(user.getEmail()).getFirst();
        assertNotNull(foundCard);
        assertEquals("1453145314531453",testCard.getCardNo());
        assertEquals("USER TEST",testCard.getNameOnCard());
    }

    @Test
    void findUserAllOrders() {

    }
}