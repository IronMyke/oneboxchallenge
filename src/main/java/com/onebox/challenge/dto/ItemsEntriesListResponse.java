package com.onebox.challenge.dto;

import com.onebox.challenge.model.CartItemEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public final class ItemsEntriesListResponse {
    private List<CartItemEntry> itemEntries;
}
