package org.starterProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.starterProj.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
