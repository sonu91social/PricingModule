package com.social.pricing.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Manufacturer")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(exclude = { "frame","brake","seat","wheel","chain"})
public class ManuFacturingCompany {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "company_name", unique = true)
	@NotBlank(message = "CompanyName Cannot be empty")
	private String companyName;
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "manufacturer")
	@NotNull
	private Set<FrameCategories> frame = new LinkedHashSet<FrameCategories>();
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "manufacturer")
	@NotNull
	private Set<BrakeCategories> brake = new LinkedHashSet<BrakeCategories>();
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "manufacturer")
	@NotNull
	private Set<SeatCategories> seat =new LinkedHashSet<SeatCategories>();
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "manufacturer")
	@NotNull
	private Set<WheelCategories> wheel = new LinkedHashSet<WheelCategories>();
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "manufacturer")
	@NotNull
	private Set<ChainCategories> chain =new LinkedHashSet<ChainCategories>();

}
