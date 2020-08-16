package com.social.pricing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.social.pricing.entity.ChainCategories;

@Repository
public interface ChainAssemblyRepository extends JpaRepository<ChainCategories, Integer> {

	
	@Query(value="Select * from manufacturer m Inner Join chain_categories fr ON m.id=fr.chain_id AND fr.chain_type=:chainType",nativeQuery = true)
	ChainCategories findByChainCategory(@Param("chainType")String chainType);

}
