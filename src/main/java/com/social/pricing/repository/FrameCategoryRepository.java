package com.social.pricing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.social.pricing.entity.FrameCategories;

@Repository
public interface FrameCategoryRepository extends JpaRepository<FrameCategories, Integer> {
	
	@Query(value="Select * from manufacturer m Inner Join frame_categories fr ON m.id=fr.frame_id AND fr.frame_type=:frameTye",nativeQuery = true)
	FrameCategories findByFrameCategory(@Param(value = "frameTye") String frameTye);

}
