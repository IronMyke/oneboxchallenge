package com.onebox.challenge.dto;


import com.onebox.challenge.model.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class CreateCartResponse {
    private Cart createdCart;
}
