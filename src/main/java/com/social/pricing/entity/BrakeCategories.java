package com.social.pricing.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "brake_categories")
@Data
@NoArgsConstructor(force = true)
@ToString
public class BrakeCategories {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "brake_type", nullable = false,updatable = true)
	private String brakeType;
	
	@Column(name = "brake_price", nullable = false,updatable = true)
	private double brakePrice;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="brake_id",nullable = false, referencedColumnName = "id")
	@JsonIgnore
	private ManuFacturingCompany manufacturer;

}
