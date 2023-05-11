package com.onebox.challenge.service;

import com.onebox.challenge.model.Cart;
import com.onebox.challenge.model.CartItem;
import com.onebox.challenge.model.CartItemEntry;
import com.onebox.challenge.repository.CartRepository;
import com.onebox.challenge.repository.ItemEntryRepository;
import com.onebox.challenge.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public final class ItemEntryServiceImpl implements ItemEntryService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final ItemEntryRepository itemEntryRepository;

    @Autowired
    public ItemEntryServiceImpl(CartRepository cartRepository, ItemRepository itemRepository, ItemEntryRepository itemEntryRepository){
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.itemEntryRepository = itemEntryRepository;
    }
    @Override
    public List<CartItemEntry> getEntriesForCartById(Long cartId) {
        return this.cartRepository.findById(cartId).get().getItemEntries();
    }

    @Override
    public Cart addItemToCart(Long cartId, Long itemId, int amount) {
        final Cart cart = this.cartRepository.findById(cartId).get();
        final Optional<CartItemEntry> maybeEntry = cart.getItemEntries().stream().filter((entry) -> entry.getItem().getId().equals(itemId)).findFirst();
        if (maybeEntry.isPresent()) {
            final CartItemEntry entry = maybeEntry.get();
            entry.setAmount(entry.getAmount() + amount);
        } else {
            final CartItem item = this.itemRepository.findById(itemId).get();
            cart.getItemEntries().add(this.itemEntryRepository.save(new CartItemEntry(cart, item, amount)));
        }
        cart.setLastUpdated(new Date());
        return this.cartRepository.save(cart);
    }

    @Override
    public Cart modifyItemAmount(Long cartId, Long itemId, int amount) {
        final Cart cart = this.cartRepository.findById(cartId).get();
        final CartItemEntry entryToUpdate = cart.getItemEntries().stream().filter((entry) -> entry.getItem().getId().equals(itemId)).findFirst().get();
        entryToUpdate.setAmount(amount);
        this.itemEntryRepository.save(entryToUpdate);
        cart.setLastUpdated(new Date());
        return this.cartRepository.save(cart);
    }

    @Override
    public Cart removeItemsFromCart(Long cartId, Long itemId) {
        final Cart cart = this.cartRepository.findById(cartId).get();
        final CartItemEntry entryToDelete = cart.getItemEntries().stream().filter((entry) -> entry.getItem().getId().equals(itemId)).findFirst().get();
        this.itemEntryRepository.deleteById(entryToDelete.getId());
        cart.getItemEntries().remove(entryToDelete);
        cart.setLastUpdated(new Date());
        return this.cartRepository.save(cart);
    }
}
