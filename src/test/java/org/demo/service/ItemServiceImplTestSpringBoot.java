package org.demo.service;

import org.demo.entity.Item;
import org.demo.model.ItemModel;
import org.demo.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ItemServiceImplTestSpringBoot {

    private static final Item TEST_ITEM = new Item()
    {{setId(1); setCount(2); setName("Test product"); setDescription("description");}};

    @MockBean
    private ItemRepository itemRepository;

    @Autowired
    private ItemService service;

    @Test
    public void testItemModelReturned() {
        when(itemRepository.findById(eq(1))).thenReturn(Optional.of(TEST_ITEM));
        Optional<ItemModel> model = service.findOne(1);
        assertTrue(model.isPresent());
        ItemModel itemModel = model.get();
        assertEquals(1, itemModel.getId());
        assertEquals(2, itemModel.getCount());
        assertEquals("Test product", itemModel.getName());
        assertEquals("description", itemModel.getDescription());
    }
}