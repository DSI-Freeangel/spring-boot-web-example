package org.demo.service;

import lombok.RequiredArgsConstructor;
import org.demo.entity.Item;
import org.demo.repository.ItemRepository;
import org.demo.model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public Optional<ItemModel> findOne(Integer id) {
        return itemRepository.findById(id).map(this::toModel);
    }

    @Override
    public ItemModel save(ItemModel itemModel) {
        Item item = toEntity(itemModel);
        Item saved = itemRepository.save(item);
        return toModel(saved);
    }

    @Override
    public Page<ItemModel> findAll(Pageable page) {
        Page<Item> itemsPage = itemRepository.findAll(page);
        List<ItemModel> itemList = itemsPage.get()
                .map(this::toModel)
                .collect(Collectors.toList());
        return new PageImpl(itemList, page, itemsPage.getTotalElements());
    }

    @Override
    public void delete(Integer id) {
        itemRepository.deleteById(id);
    }

    private ItemModel toModel(Item item) {
        return ItemModel.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .count(item.getCount())
                .build();
    }

    private Item toEntity(ItemModel itemModel) {
        Item item = new Item();
        BeanUtils.copyProperties(itemModel, item);
        return item;
    }
}