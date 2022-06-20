package org.starterProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.starterProj.entity.Order;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCartCustomerId(Long customerId);
    Optional<Order> findByPaymentId(Long id);
}
