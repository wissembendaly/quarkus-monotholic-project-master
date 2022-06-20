package org.starterProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.starterProj.entity.Cart;
import org.starterProj.entity.CartStatus;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart ,Long> {


    List<Cart> findByStatus(CartStatus status);

    List<Cart> findByStatusAndCustomerId(CartStatus status, Long customerId);
}
