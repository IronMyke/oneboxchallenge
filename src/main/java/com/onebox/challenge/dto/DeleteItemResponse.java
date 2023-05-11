package com.onebox.challenge.dto;


import com.onebox.challenge.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class DeleteItemResponse {
    private CartItem deletedItem;
}
