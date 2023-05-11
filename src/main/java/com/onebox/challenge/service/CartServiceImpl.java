package com.onebox.challenge.service;

import com.onebox.challenge.model.Cart;
import com.onebox.challenge.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }
    @Override
    public List<Cart> getAll() {
        return this.cartRepository.findAll();
    }

    @Override
    public Cart getById(Long id) {
        return this.cartRepository.findById(id).get();
    }

    @Override
    public Cart deleteById(Long id) {
        final Cart cartToDelete = this.cartRepository.findById(id).get();
        this.cartRepository.deleteById(id);
        return cartToDelete;
    }

    @Override
    public Cart createCart() {
        return this.cartRepository.save(new Cart());
    }
}
