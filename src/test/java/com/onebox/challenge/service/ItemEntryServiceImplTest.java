package com.onebox.challenge.service;

import com.onebox.challenge.model.Cart;
import com.onebox.challenge.model.CartItem;
import com.onebox.challenge.model.CartItemEntry;
import com.onebox.challenge.repository.CartRepository;
import com.onebox.challenge.repository.ItemEntryRepository;
import com.onebox.challenge.repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class ItemEntryServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemEntryRepository entryRepository;

    private ItemEntryServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new ItemEntryServiceImpl(cartRepository, itemRepository, entryRepository);

        lenient().when(cartRepository.save(Mockito.any(Cart.class))).thenAnswer(i -> i.getArguments()[0]);
        lenient().when(itemRepository.save(Mockito.any(CartItem.class))).thenAnswer(i -> i.getArguments()[0]);
        lenient().when(entryRepository.save(Mockito.any(CartItemEntry.class))).thenAnswer(i -> i.getArguments()[0]);

        final Cart cart1 = new Cart();
        final Cart cart2 = new Cart();

        lenient().when(cartRepository.findAll()).thenReturn(Arrays.asList(cart1, cart2));

        lenient().when(cartRepository.findById(1L)).thenReturn(Optional.of(cart1));
        lenient().when(cartRepository.findById(2L)).thenReturn(Optional.of(cart2));

        final CartItem itemA = new CartItem("This is Item A");
        final CartItem itemB = new CartItem("This is Item B");
        final CartItem itemC = new CartItem("This is Item C");

        itemA.setId(1L);
        itemB.setId(2L);
        itemC.setId(3L);

        lenient().when(itemRepository.findAll()).thenReturn(Arrays.asList(itemA, itemB, itemC));

        lenient().when(itemRepository.findById(1L)).thenReturn(Optional.of(itemA));
        lenient().when(itemRepository.findById(2L)).thenReturn(Optional.of(itemB));
        lenient().when(itemRepository.findById(3L)).thenReturn(Optional.of(itemC));

        cart1.getItemEntries().add(new CartItemEntry(cart1, itemA, 10));
        cart1.getItemEntries().add(new CartItemEntry(cart1, itemB, 20));

        cart2.getItemEntries().add(new CartItemEntry(cart2, itemB, 20));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getEntriesForCartById() {
        Object[] itemDescriptions = underTest.getEntriesForCartById(1L).stream().map(entry -> entry.getItem().getDescription()).toArray();
        assertArrayEquals(new String[]{"This is Item A", "This is Item B"}, itemDescriptions, "cart1 contains items A and B, but not C");
    }

    @Test
    void addItemToCart() {
        Cart cart = underTest.addItemToCart(1L, 2L, 50);
        final int actualAmountOfItemB = cart.getItemEntries().stream().filter(entry -> entry.getItem().getId().equals(2L)).findFirst().get().getAmount();

        cart = underTest.addItemToCart(1L, 3L, 50);
        final int actualAmountOfItemC = cart.getItemEntries().stream().filter(entry -> entry.getItem().getId().equals(3L)).findFirst().get().getAmount();

        assertEquals(70, actualAmountOfItemB, "cart1 had 20 of itemB, so after adding 50 we should see 70");
        assertEquals(50, actualAmountOfItemC, "cart1 had none of itemC, so after adding 50 we should see 50");
    }

    @Test
    void modifyItemAmount() {
        Cart cart = underTest.modifyItemAmount(1L, 1L, 42);
        final int actualAmountOfItemA = cart.getItemEntries().stream().filter(entry -> entry.getItem().getId().equals(1L)).findFirst().get().getAmount();
        assertEquals(42, actualAmountOfItemA, "No matter the existing amount of itemA, we set it to 42");
    }

    @Test
    void removeItemsFromCart() {
        Cart cart = underTest.removeItemsFromCart(2L, 2L);
        assertEquals(0, cart.getItemEntries().size(), "there should be no item entries left on cart2");
    }
}