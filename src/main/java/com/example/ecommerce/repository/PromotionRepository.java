package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    /* Required Queries */
    // Find promotions that are active on a given date.
    @Query("""
        SELECT p
        FROM Promotion p
        WHERE :date BETWEEN p.startDate AND p.endDate
    """)
    List<Promotion> findActivePromotions(@Param("date") LocalDate date);


    /* Advanced Queries */
    // Find promotions by code.
    List<Promotion> findByCode(String code);

    // Find promotions starting after a given date.
    List<Promotion> findByStartDateAfter(LocalDate date);

    // Find promotions ending before a given date.
    List<Promotion> findByEndDateBefore(LocalDate date);

    // Find promotions that have no end date.
    List<Promotion> findByEndDateIsNull();

    // Find promotions active today.
    @Query("""
        SELECT p
        FROM Promotion p
        WHERE CURRENT_DATE BETWEEN p.startDate AND p.endDate
    """)
    List<Promotion> findActiveToday();

    boolean existsByCode(String code);
}
