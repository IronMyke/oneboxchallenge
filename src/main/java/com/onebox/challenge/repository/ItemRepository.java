package com.onebox.challenge.repository;

import com.onebox.challenge.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<CartItem, Long> {
}
