package com.onebox.challenge.controller;

import com.onebox.challenge.dto.*;
import com.onebox.challenge.service.ItemEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemEntriesController {

    private final ItemEntryService itemEntryService;

    @Autowired
    public ItemEntriesController(ItemEntryService itemEntryService){
        this.itemEntryService = itemEntryService;
    }

    @GetMapping("/carts/{id}/items")
    public ItemsEntriesListResponse getAllItemsForCart(@PathVariable Long id) {
        return new ItemsEntriesListResponse(this.itemEntryService.getEntriesForCartById(id));
    }


    @PostMapping("/carts/{id}/items")
    public AddItemToCartResponse addItemToCart (@PathVariable Long id, @RequestBody AddItemToCartRequest addItemToCartRequest) {
        return new AddItemToCartResponse(this.itemEntryService.addItemToCart(id, addItemToCartRequest.getItemId(), addItemToCartRequest.getAmount()));
    }

    @PutMapping("/carts/{cartId}/items/{itemId}")
    public ModifyItemAmountResponse modifyItemAmount (@PathVariable Long cartId, @PathVariable Long itemId, @RequestBody ModifyItemAmountRequest modifyItemAmountRequest) {
        return new ModifyItemAmountResponse(this.itemEntryService.modifyItemAmount(cartId, itemId, modifyItemAmountRequest.getAmount()));
    }

    @DeleteMapping("/carts/{cartId}/items/{itemId}")
    public RemoveItemsFromCartResponse removeItemsFromCart (@PathVariable Long cartId, @PathVariable Long itemId) {
        return new RemoveItemsFromCartResponse(this.itemEntryService.removeItemsFromCart(cartId, itemId));
    }

}
