package com.social.pricing.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.social.pricing.Exception.FunctionlException;
import com.social.pricing.Response.SuccessRestResponse;
import com.social.pricing.entity.BrakeCategories;
import com.social.pricing.entity.ChainCategories;
import com.social.pricing.entity.FrameCategories;
import com.social.pricing.entity.SeatCategories;
import com.social.pricing.entity.WheelCategories;
import com.social.pricing.service.PricingEngineService;

@RestController
@RequestMapping(value = "/getCycle")
public class PricingEngineController {

	private static Logger LOGGER = LoggerFactory.getLogger(PricingEngineController.class);

	private static double totalCyclePrice;
	
	private Map<String,Integer> cycleModules = new LinkedHashMap<String,Integer>();

	@Autowired
	private PricingEngineService pricingEngineService;

	@RequestMapping(method = RequestMethod.GET, value = "/price")
	public ResponseEntity<SuccessRestResponse> getCyclePricingBreakUpDetails(@RequestParam String frameTye,
			@RequestParam String brakeType, @RequestParam String seatType, @RequestParam String wheelType,
			@RequestParam String chainType) throws FunctionlException {

		LOGGER.info("Billing Process Initiated");

		SuccessRestResponse response = new SuccessRestResponse();

		FrameCategories frame = pricingEngineService.getFrameCategory(frameTye);
		if (frame == null) {
			response.setSuccess(false);
			response.setDate(LocalDateTime.now());
			response.setMessage("Cycle Does Not Exist With Frame Category : " + frameTye);
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		} else {
			totalCyclePrice = totalCyclePrice + frame.getFramePrice();
			cycleModules.put("frame", (int) frame.getFramePrice());
		}

		BrakeCategories brake = pricingEngineService.getBrakeCategory(brakeType);
		if (brake == null) {
			response.setSuccess(false);
			response.setDate(LocalDateTime.now());
			response.setMessage("Cycle Does Not Exist With Brake Category : " + brakeType);
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		} else {
			totalCyclePrice = totalCyclePrice + brake.getBrakePrice();
			cycleModules.put("brake", (int) brake.getBrakePrice());
		}
		
		SeatCategories seat = pricingEngineService.getSeatCategory(seatType);
		if (seat == null) {
			response.setSuccess(false);
			response.setDate(LocalDateTime.now());
			response.setMessage("Cycle Does Not Exist With Seat Category : " + seatType);
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		} else {
			totalCyclePrice = totalCyclePrice + seat.getSeatPrice();
			cycleModules.put("seat", (int) seat.getSeatPrice());
		}
		
		WheelCategories wheel = pricingEngineService.getWheelCategory(wheelType);
		if (wheel == null) {
			response.setSuccess(false);
			response.setDate(LocalDateTime.now());
			response.setMessage("Cycle Does Not Exist With Wheel Category : " + wheelType);
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		} else {
			totalCyclePrice = totalCyclePrice + wheel.getWheelPrice();
			cycleModules.put("wheel", (int) wheel.getWheelPrice());
		}
		
		ChainCategories chain = pricingEngineService.getChainCategory(chainType);
		if (chain == null) {
			response.setSuccess(false);
			response.setDate(LocalDateTime.now());
			response.setMessage("Cycle Does Not Exist With Chain Category : " + chainType);
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		} else {
			totalCyclePrice = totalCyclePrice + chain.getChainPrice();
			cycleModules.put("chain", (int) chain.getChainPrice());
			cycleModules.put("totalCyclePrice", (int) totalCyclePrice);
		}
		
		response.setSuccess(true);
		response.setDate(LocalDateTime.now());
		response.setData(cycleModules);
		return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.OK);

	}

}
