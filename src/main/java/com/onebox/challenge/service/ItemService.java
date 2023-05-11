package com.onebox.challenge.service;

import com.onebox.challenge.model.CartItem;

import java.util.List;

public interface ItemService {
    List<CartItem> getAllItems();
    CartItem getItemById(Long id);
    CartItem createItem(String description);
    CartItem updateItem(Long id, String description);
    CartItem deleteItem(Long id);
}
