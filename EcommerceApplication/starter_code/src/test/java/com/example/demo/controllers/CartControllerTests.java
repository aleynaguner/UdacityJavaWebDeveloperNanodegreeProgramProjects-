package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
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
public class CartControllerTests {

    @InjectMocks
    private CartController cartController;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void returnNotFoundWhenAddToCartAndUserIsNull() {
        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("username");
        request.setItemId(1L);
        request.setQuantity(10);

        assertEquals(cartController.addTocart(request), ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Test
    public void returnNotFoundWhenAddToCartAndItemIdNull() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");

        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("username");
        request.setItemId(1L);
        request.setQuantity(10);

        when(userRepository.findByUsername(request.getUsername())).thenReturn(user);

        assertEquals(cartController.addTocart(request), ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Test
    public void returnOkWhenAddToCart() {
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

        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("username");
        request.setItemId(1L);
        request.setQuantity(10);

        when(userRepository.findByUsername(request.getUsername())).thenReturn(user);
        when(itemRepository.findById(request.getItemId())).thenReturn(java.util.Optional.of(item));
        when(cartRepository.save(cart)).thenReturn(cart);

        assertEquals(cartController.addTocart(request), ResponseEntity.ok(cart));
    }

    @Test
    public void returnNotFoundWhenRemoveFromCartAndUserIsNull() {
        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("username");
        request.setItemId(1L);
        request.setQuantity(10);

        assertEquals(cartController.removeFromcart(request), ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Test
    public void returnNotFoundWhenRemoveFromCartAndItemIdNull() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");

        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("username");
        request.setItemId(1L);
        request.setQuantity(10);

        when(userRepository.findByUsername(request.getUsername())).thenReturn(user);

        assertEquals(cartController.removeFromcart(request), ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Test
    public void returnOkWhenRemoveFromCart() {
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

        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("username");
        request.setItemId(1L);
        request.setQuantity(10);

        when(userRepository.findByUsername(request.getUsername())).thenReturn(user);
        when(itemRepository.findById(request.getItemId())).thenReturn(java.util.Optional.of(item));
        when(cartRepository.save(cart)).thenReturn(cart);

        assertEquals(cartController.removeFromcart(request), ResponseEntity.ok(cart));
    }

}
