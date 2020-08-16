package com.social.pricing.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FunctionlException  extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String message;

}
