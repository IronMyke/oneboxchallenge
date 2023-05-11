package com.onebox.challenge.service;

import com.onebox.challenge.model.Cart;
import com.onebox.challenge.model.CartItem;
import com.onebox.challenge.repository.CartRepository;
import com.onebox.challenge.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public final class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public List<CartItem> getAllItems() {
        return this.itemRepository.findAll();
    }

    @Override
    public CartItem getItemById(Long id) {
        return this.itemRepository.findById(id).get();
    }

    @Override
    public CartItem createItem(String description) {
        return this.itemRepository.save(new CartItem(description));
    }

    @Override
    public CartItem updateItem(Long id, String description) {
        final CartItem itemToUpdate = this.itemRepository.findById(id).get();
        itemToUpdate.setDescription(description);
        return itemRepository.save(itemToUpdate);
    }

    @Override
    public CartItem deleteItem(Long id) {
        CartItem itemToDelete = this.itemRepository.findById(id).get();
        List<Cart> cartsToUpdate = itemToDelete.getItemEntries().stream().map((entry) -> entry.getCart()).toList();
        this.itemRepository.deleteById(id);
        cartsToUpdate.forEach((cart)-> cart.setLastUpdated(new Date()));
        this.cartRepository.saveAll(cartsToUpdate);
        return itemToDelete;
    }
}
