package com.onebox.challenge.controller;

import com.onebox.challenge.dto.*;
import com.onebox.challenge.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public final class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public ItemsListResponse getAllItems() {
        return new ItemsListResponse(this.itemService.getAllItems());
    }

    @GetMapping("/items/{id}")
    public SingleItemResponse getItem(@PathVariable Long id) {
        return new SingleItemResponse(this.itemService.getItemById(id));
    }

    @PostMapping("/items")
    public CreateItemResponse createItem (@RequestBody CreateItemRequest createItemRequest) {
        return new CreateItemResponse(this.itemService.createItem(createItemRequest.getDescription()));
    }

    @DeleteMapping("/items/{id}")
    public DeleteItemResponse deleteItem(@PathVariable Long id) {
        return new DeleteItemResponse(this.itemService.deleteItem(id));
    }

    @PutMapping("/items/{id}")
    public ModifyItemResponse deleteItem(@PathVariable Long id, ModifyItemRequest modifyItemRequest) {
        return new ModifyItemResponse(this.itemService.updateItem(id, modifyItemRequest.getDescription()));
    }
}
