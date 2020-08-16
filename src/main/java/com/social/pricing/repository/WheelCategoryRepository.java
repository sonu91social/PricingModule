package com.social.pricing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.social.pricing.entity.WheelCategories;

@Repository
public interface WheelCategoryRepository extends JpaRepository<WheelCategories, Integer> {

	@Query(value="Select * from manufacturer m Inner Join wheel_categories fr ON m.id=fr.wheel_id AND fr.wheel_type=:wheelType",nativeQuery = true)
	WheelCategories findByWheelCategory(@Param(value = "wheelType")  String wheelType);

}
