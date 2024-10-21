package com.example.catalogService.repositories;

import com.example.catalogService.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    @Transactional
    @Modifying
    @Query("delete Review r where r.idReview=:id")
    void deleteReviewById(@Param("id") int id);
}
