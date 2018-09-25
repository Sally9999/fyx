package com.wsy.fyxw.util;

import com.wsy.fyxw.domain.ResultInfo;
import com.wsy.fyxw.enums.EnumCommonResult;

public class ResultUtil {

	/**
	 * 设置返回结果
	 * 
	 * @param enumCommonResult
	 * @param result
	 * @return
	 */
	public static ResultInfo setResultInfo(EnumCommonResult enumCommonResult, ResultInfo result) {
		if (null == result) {
			result = new ResultInfo(enumCommonResult.getCode(), enumCommonResult.getValue());
		} else {
			result.setCode(enumCommonResult.getCode());
			result.setValue(enumCommonResult.getValue());
		}
		return result;
	}
}
