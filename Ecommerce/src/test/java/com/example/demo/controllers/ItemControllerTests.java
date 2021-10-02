package com.example.demo.controllers;

import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ItemControllerTests {

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void returnOkWhenGetItemsSuccessfull() {
        Item item = new Item();
        item.setId(0L);
        item.setName("item");
        item.setPrice(new BigDecimal("0.0"));
        item.setDescription("description");

        List<Item> itemList = new ArrayList<>();
        itemList.add(item);

        when(itemRepository.findAll()).thenReturn(itemList);
        assertEquals(200, itemController.getItems().getStatusCodeValue());
        assertEquals(item.getId(), itemController.getItems().getBody().get(0).getId());
    }

    @Test
    public void returnOkWhenGetItemByIdSuccessfull() {
        Item item = new Item();
        item.setId(0L);
        item.setName("item");
        item.setPrice(new BigDecimal("0.0"));
        item.setDescription("description");

        when(itemRepository.findById(0L)).thenReturn(java.util.Optional.of(item));

        assertEquals(item, itemController.getItemById(0L).getBody());
    }

    @Test
    public void returnNotFoundWhenGetItemByNameAndItemsNull() {
        Item item = new Item();
        item.setId(0L);
        item.setName("item");
        item.setPrice(new BigDecimal("0.0"));
        item.setDescription("description");

        List<Item> itemList = new ArrayList<>();

        when(itemRepository.findByName("item")).thenReturn(itemList);

        assertEquals(404, itemController.getItemsByName("item").getStatusCodeValue());
    }

    @Test
    public void returnOkWhenGetItemByNameAndItemsNull() {
        Item item = new Item();
        item.setId(0L);
        item.setName("item");
        item.setPrice(new BigDecimal("0.0"));
        item.setDescription("description");

        List<Item> itemList = new ArrayList<>();
        itemList.add(item);

        when(itemRepository.findByName("item")).thenReturn(itemList);

        assertEquals(200, itemController.getItemsByName("item").getStatusCodeValue());
    }


}
