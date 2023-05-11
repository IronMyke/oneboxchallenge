package com.onebox.challenge.dto;


import com.onebox.challenge.model.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public final class CartListResponse {
    private List<Cart> carts;
}
