package com.onebox.challenge.repository;

import com.onebox.challenge.model.CartItemEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemEntryRepository extends JpaRepository<CartItemEntry, Long> {
}
