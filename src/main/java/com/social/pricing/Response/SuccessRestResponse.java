package com.social.pricing.Response;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class SuccessRestResponse {
	
	private boolean success;
	
	private LocalDateTime date;

	private Object data;
	
	private String message;

}
