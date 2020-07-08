package org.demo.service;

import org.demo.model.ItemModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ItemService {
    Optional<ItemModel> findOne(Integer id);

    ItemModel save(ItemModel itemModel);

    Page<ItemModel> findAll(Pageable page);

    void delete(Integer id);
}