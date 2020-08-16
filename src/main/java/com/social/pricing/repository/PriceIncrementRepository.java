package com.social.pricing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.social.pricing.entity.ModulesPriceVariationByYear;

@Repository
public interface PriceIncrementRepository extends JpaRepository<ModulesPriceVariationByYear, Integer> {

	@Query(value="Select percentage_increase from module_pices_variation_per_year e where e.year=:buyingYear", nativeQuery= true)
	double findPriceInceremntedInBuyingYear(@Param(value = "buyingYear")String buyingYear);

}
