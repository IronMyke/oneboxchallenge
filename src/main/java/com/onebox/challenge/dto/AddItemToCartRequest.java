package com.onebox.challenge.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class AddItemToCartRequest {
    private Long itemId;
    private int amount;
}
