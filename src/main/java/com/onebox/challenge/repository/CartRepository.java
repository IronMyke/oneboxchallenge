package com.onebox.challenge.repository;

import com.onebox.challenge.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c from Cart c where c.lastUpdated <= :cutoffDate")
    List<Cart> getAllUpdatedBefore(
            @Param("cutoffDate") Date cutoffDate);
}
