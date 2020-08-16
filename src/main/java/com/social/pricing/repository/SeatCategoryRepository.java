package com.social.pricing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.social.pricing.entity.SeatCategories;

@Repository
public interface SeatCategoryRepository extends JpaRepository<SeatCategories, Integer> {

	@Query(value="Select * from manufacturer m Inner Join seat_categories fr ON m.id=fr.seat_id AND fr.seat_type=:seatType",nativeQuery = true)
	SeatCategories findBySeatCategory(@Param("seatType") String seatType);

}
