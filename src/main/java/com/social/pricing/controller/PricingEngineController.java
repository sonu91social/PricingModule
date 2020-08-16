package com.social.pricing.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	private Map<String, Integer> cycleModules = new LinkedHashMap<String, Integer>();

	@Autowired
	private PricingEngineService pricingEngineService;

	/**
	 * @param frameTye
	 * @param brakeType
	 * @param seatType
	 * @param wheelType
	 * @param chainType
	 * @return
	 * @throws FunctionlException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/price/{frameTye}/{brakeType}/{seatType}/{wheelType}/{chainType}/{buyingYear}")
	public ResponseEntity<SuccessRestResponse> getCyclePricingBreakUpDetails(@PathVariable("frameTye") String frameTye,
			@PathVariable("brakeType") String brakeType, @PathVariable("seatType") String seatType,
			@PathVariable("wheelType") String wheelType, @PathVariable("chainType") String chainType,
			@PathVariable("buyingYear") String buyingYear) throws FunctionlException {

		LOGGER.info("Billing Process Initiated");

		double totalCyclePrice = 0;
		SuccessRestResponse response = new SuccessRestResponse();

		FrameCategories frame = pricingEngineService.getFrameCategory(frameTye);
		if (frame == null) {
			response.setSuccess(false);
			response.setDate(LocalDateTime.now());
			response.setMessage("Cycle Does Not Exist With Frame Category : " + frameTye);
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		} else {
			cycleModules.put("frame",
					(int) pricingEngineService.getIncreaseInPriceOfItems(buyingYear, frame.getFramePrice()));
			totalCyclePrice = totalCyclePrice
					+ pricingEngineService.getIncreaseInPriceOfItems(buyingYear, frame.getFramePrice());
		}

		BrakeCategories brake = pricingEngineService.getBrakeCategory(brakeType);
		if (brake == null) {
			response.setSuccess(false);
			response.setDate(LocalDateTime.now());
			response.setMessage("Cycle Does Not Exist With Brake Category : " + brakeType);
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		} else {
			cycleModules.put("brake",
					(int) pricingEngineService.getIncreaseInPriceOfItems(buyingYear, brake.getBrakePrice()));
			totalCyclePrice = totalCyclePrice
					+ pricingEngineService.getIncreaseInPriceOfItems(buyingYear, brake.getBrakePrice());
		}

		SeatCategories seat = pricingEngineService.getSeatCategory(seatType);
		if (seat == null) {
			response.setSuccess(false);
			response.setDate(LocalDateTime.now());
			response.setMessage("Cycle Does Not Exist With Seat Category : " + seatType);
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		} else {

			cycleModules.put("seat",
					(int) pricingEngineService.getIncreaseInPriceOfItems(buyingYear, seat.getSeatPrice()));
			totalCyclePrice = totalCyclePrice
					+ pricingEngineService.getIncreaseInPriceOfItems(buyingYear, seat.getSeatPrice());
		}

		WheelCategories wheel = pricingEngineService.getWheelCategory(wheelType);
		if (wheel == null) {
			response.setSuccess(false);
			response.setDate(LocalDateTime.now());
			response.setMessage("Cycle Does Not Exist With Wheel Category : " + wheelType);
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		} else {
			cycleModules.put("wheel",
					(int) pricingEngineService.getIncreaseInPriceOfItems(buyingYear, wheel.getWheelPrice()));
			totalCyclePrice = totalCyclePrice
					+ pricingEngineService.getIncreaseInPriceOfItems(buyingYear, wheel.getWheelPrice());

		}

		ChainCategories chain = pricingEngineService.getChainCategory(chainType);
		if (chain == null) {
			response.setSuccess(false);
			response.setDate(LocalDateTime.now());
			response.setMessage("Cycle Does Not Exist With Chain Category : " + chainType);
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		} else {
			
			cycleModules.put("chain", (int) pricingEngineService.getIncreaseInPriceOfItems(buyingYear, chain.getChainPrice()));
			totalCyclePrice = totalCyclePrice + pricingEngineService.getIncreaseInPriceOfItems(buyingYear, chain.getChainPrice());
		}

		double finalCyclePrice = pricingEngineService.getTaxCalculator(totalCyclePrice);

		response.setSuccess(true);
		response.setDate(LocalDateTime.now());
		response.setData(cycleModules);
		response.setMessage("Total Cycle Price After 18% GST :" + finalCyclePrice);
		return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.OK);

	}

}
