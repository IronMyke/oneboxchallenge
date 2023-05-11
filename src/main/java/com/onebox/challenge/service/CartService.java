package com.onebox.challenge.service;

import com.onebox.challenge.model.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getAll();
    Cart getById(Long id);
    Cart deleteById(Long id);
    Cart createCart();
}
