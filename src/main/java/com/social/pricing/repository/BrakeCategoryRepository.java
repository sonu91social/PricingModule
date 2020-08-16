package com.social.pricing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.social.pricing.entity.BrakeCategories;

@Repository
public interface BrakeCategoryRepository extends JpaRepository<BrakeCategories, Integer> {

	@Query(value="Select * from manufacturer m Inner Join brake_categories fr ON m.id=fr.brake_id AND fr.brake_type=:brakeType",nativeQuery = true)
	BrakeCategories findByBrakeCategory(@Param(value = "brakeType")String brakeType);

}
