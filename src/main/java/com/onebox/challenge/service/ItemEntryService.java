package com.onebox.challenge.service;

import com.onebox.challenge.model.Cart;
import com.onebox.challenge.model.CartItemEntry;

import java.util.List;

public interface ItemEntryService {
    List<CartItemEntry> getEntriesForCartById(Long cartId);
    Cart addItemToCart(Long cartId, Long itemId, int amount);
    Cart modifyItemAmount(Long cartId, Long itemId, int amount);

    Cart removeItemsFromCart(Long cartId, Long itemId);
}
