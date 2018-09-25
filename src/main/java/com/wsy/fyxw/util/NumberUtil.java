package com.wsy.fyxw.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service("numberUtil")
public class NumberUtil {

	public static Integer divide(Integer divisor, Integer dividend, int roundingMode) {
		BigDecimal b1 = new BigDecimal(divisor);
		BigDecimal b2 = new BigDecimal(dividend);
		return b1.divide(b2, roundingMode).intValue();
	}
	
	public static Integer multiple(Integer num1, Integer num2) {
		BigDecimal b1 = new BigDecimal(num1);
		BigDecimal b2 = new BigDecimal(num2);
		return b1.multiply(b2).intValue();
	}
	
	public static Integer add(Integer num1, Integer num2) {
		BigDecimal b1 = new BigDecimal(num1);
		BigDecimal b2 = new BigDecimal(num2);
		return b1.add(b2).intValue();
	}
	
	public static Integer sub(Integer num1, Integer num2) {
		BigDecimal b1 = new BigDecimal(num1);
		BigDecimal b2 = new BigDecimal(num2);
		return b1.subtract(b2).intValue();
	}
}
