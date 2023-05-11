package com.onebox.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItemEntry {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @ManyToOne(optional = false)
    private CartItem item;

    @Min(1)
    @Column(name = "amount", nullable = false)
    private int amount;

    @ManyToOne
    @JoinColumn(name ="cart_id", nullable = false)
    @JsonIgnore
    private Cart cart;

    public CartItemEntry(Cart cart, CartItem item, int amount) {
        this.cart = cart;
        this.item = item;
        this.amount = amount;
    }
}
