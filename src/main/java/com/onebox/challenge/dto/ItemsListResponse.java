package com.onebox.challenge.dto;


import com.onebox.challenge.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public final class ItemsListResponse {
    private List<CartItem> items;
}
