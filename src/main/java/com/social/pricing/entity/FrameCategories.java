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
@Table(name = "frame_categories")
@Data
@NoArgsConstructor(force = true)
@ToString
public class FrameCategories {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "frame_type", nullable = false,updatable = true)
	private String frameType;
	
	@Column(name = "frame_price", nullable = false,updatable = true)
	private double framePrice;

	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="frame_id",nullable = false, referencedColumnName = "id")
	@JsonIgnore
	private ManuFacturingCompany manufacturer;
}
