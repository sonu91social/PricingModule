package com.social.pricing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.pricing.entity.BrakeCategories;
import com.social.pricing.entity.ChainCategories;
import com.social.pricing.entity.FrameCategories;
import com.social.pricing.entity.SeatCategories;
import com.social.pricing.entity.WheelCategories;
import com.social.pricing.repository.BrakeCategoryRepository;
import com.social.pricing.repository.ChainAssemblyRepository;
import com.social.pricing.repository.FrameCategoryRepository;
import com.social.pricing.repository.PriceIncrementRepository;
import com.social.pricing.repository.SeatCategoryRepository;
import com.social.pricing.repository.WheelCategoryRepository;

@Service
public class PricingEngineService {

	@Autowired
	private FrameCategoryRepository frameCategoryRepository;

	@Autowired
	private SeatCategoryRepository seatCategoryRepository;

	@Autowired
	private WheelCategoryRepository wheelCategoryRepository;

	@Autowired
	private BrakeCategoryRepository brakeCategoryRepository;

	@Autowired
	private ChainAssemblyRepository chainAssemblyRepository;
	
	@Autowired
	private PriceIncrementRepository priceIncrementRepository;
	
	private static final double GST= 0.18;

	public FrameCategories getFrameCategory(String frameTye) {
		return frameCategoryRepository.findByFrameCategory(frameTye);
	}

	public BrakeCategories getBrakeCategory(String brakeType) {
		return brakeCategoryRepository.findByBrakeCategory(brakeType);
	}

	public SeatCategories getSeatCategory(String seatType) {
		return seatCategoryRepository.findBySeatCategory(seatType);
	}

	public WheelCategories getWheelCategory(String wheelType) {
		return wheelCategoryRepository.findByWheelCategory(wheelType);
	}

	public ChainCategories getChainCategory(String chainType) {
		return chainAssemblyRepository.findByChainCategory(chainType);
	}

	public double getTaxCalculator(double totalCyclePrice) {
		double tax = totalCyclePrice * GST; 
		 return (totalCyclePrice+tax);
	}

	public double getIncreaseInPriceOfItems(String buyingYear, double price) {
		double percent_increase =priceIncrementRepository.findPriceInceremntedInBuyingYear(buyingYear);
		
		return price + (percent_increase/100)*price;
	}
	
	

}
