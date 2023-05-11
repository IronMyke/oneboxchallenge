package com.onebox.challenge.controller;

import com.onebox.challenge.dto.CartDeletedResponse;
import com.onebox.challenge.dto.CartListResponse;
import com.onebox.challenge.dto.CreateCartResponse;
import com.onebox.challenge.dto.SingleCartResponse;
import com.onebox.challenge.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public final class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping("/carts")
    public CartListResponse getAllCarts(){
        return new CartListResponse(this.cartService.getAll());
    }

    @PostMapping("/carts")
    public CreateCartResponse createCart(){
        return new CreateCartResponse(this.cartService.createCart());
    }

    @GetMapping("/carts/{id}")
    public SingleCartResponse getCart(@PathVariable("id") Long id){
        return new SingleCartResponse(this.cartService.getById(id));
    }

    @DeleteMapping("/carts/{id}")
    public CartDeletedResponse deleteCart(@PathVariable("id") Long id){
        return new CartDeletedResponse(this.cartService.deleteById(id));
    }
}
