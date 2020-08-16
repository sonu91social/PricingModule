package com.social.pricing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "module_pices_variation_per_year")
@Data
@NoArgsConstructor(force=true)
public class ModulesPriceVariationByYear {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "year", nullable = false,updatable = true)
	private String year;
	
	@Column(name = "percentage_increase", nullable = false,updatable = true)
	private double percentageIncrease;

}
