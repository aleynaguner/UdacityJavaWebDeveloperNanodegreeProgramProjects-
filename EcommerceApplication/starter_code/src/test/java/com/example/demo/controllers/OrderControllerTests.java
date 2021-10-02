package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderControllerTests {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void returnNotFoundWhenSubmitAndUserIsNull() {
        when(userRepository.findByUsername("username")).thenReturn(null);
        assertEquals(orderController.submit("username"), ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Test
    public void returnNotFoundWhenSubmitSuccessfull() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");

        Item item = new Item();
        item.setId(0L);
        item.setName("item");
        item.setPrice(new BigDecimal("0.0"));
        item.setDescription("description");

        List<Item> itemList = new ArrayList<>();
        itemList.add(item);

        Cart cart = new Cart();
        cart.setId(0L);
        cart.setItems(itemList);
        cart.setTotal(new BigDecimal("0.0"));

        user.setCart(cart);

        UserOrder order = UserOrder.createFromCart(user.getCart());

        when(userRepository.findByUsername("username")).thenReturn(user);
        assertEquals(200, orderController.submit("username").getStatusCodeValue());
    }

    @Test
    public void returnNotFoundWhenGetOrderForUserAndUserIsNull() {
        when(userRepository.findByUsername("username")).thenReturn(null);
        assertEquals(orderController.getOrdersForUser("username"), ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Test
    public void returnNotFoundWhenGetOrderForUserSuccessfull() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");

        Item item = new Item();
        item.setId(0L);
        item.setName("item");
        item.setPrice(new BigDecimal("0.0"));
        item.setDescription("description");

        List<Item> itemList = new ArrayList<>();
        itemList.add(item);

        Cart cart = new Cart();
        cart.setId(0L);
        cart.setItems(itemList);
        cart.setTotal(new BigDecimal("0.0"));

        user.setCart(cart);

        UserOrder order = UserOrder.createFromCart(user.getCart());
        List<UserOrder> orderList = new ArrayList<>();
        orderList.add(order);

        when(userRepository.findByUsername("username")).thenReturn(user);
        when(orderRepository.findByUser(user)).thenReturn(orderList);

        assertEquals(200, orderController.getOrdersForUser("username").getStatusCodeValue());
    }

}
