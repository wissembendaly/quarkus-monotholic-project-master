package org.starterProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.starterProj.entity.OrderItem;

import java.util.List;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem , Long> {
    List<OrderItem> findAllByOrderId(Long id);
}
