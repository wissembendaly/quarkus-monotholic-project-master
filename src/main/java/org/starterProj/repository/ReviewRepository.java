package org.starterProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.starterProj.entity.Review;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select p.reviews from Product p where p.id = ?1")
    List<Review> findReviewsByProductId(Long id);

}
