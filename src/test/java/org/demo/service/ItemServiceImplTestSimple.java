package org.demo.service;

import org.demo.entity.Item;
import org.demo.model.ItemModel;
import org.demo.repository.ItemRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemServiceImplTestSimple {

    private static final Item TEST_ITEM = new Item()
    {{setId(1); setCount(2); setName("Test product"); setDescription("description");}};

    private ItemRepository itemRepository = mock(ItemRepository.class);

    private ItemServiceImpl service = new ItemServiceImpl(itemRepository);

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