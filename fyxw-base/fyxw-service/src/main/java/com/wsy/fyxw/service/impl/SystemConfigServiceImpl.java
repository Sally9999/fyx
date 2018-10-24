package com.wsy.fyxw.service.impl;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wsy.fyxw.dao.SystemConfigDao;
import com.wsy.fyxw.dao.factory.DaoFactory;
import com.wsy.fyxw.domain.ResultInfo;
import com.wsy.fyxw.domain.SystemConfig;
import com.wsy.fyxw.enums.EnumCommonResult;
import com.wsy.fyxw.enums.EnumCommonStatus;
import com.wsy.fyxw.enums.EnumDefaultRoleType;
import com.wsy.fyxw.enums.EnumLogType;
import com.wsy.fyxw.enums.EnumYesOrNo;
import com.wsy.fyxw.query.SystemConfigQuery;
import com.wsy.fyxw.redis.RedisUtil;
import com.wsy.fyxw.service.SystemConfigService;
import com.wsy.fyxw.util.LogUtil;
import com.wsy.fyxw.util.ResultUtil;

@Service("systemConfigService")
public class SystemConfigServiceImpl implements SystemConfigService {

	private SystemConfigDao systemConfigDao;
	@Autowired
	private LogUtil logUtil;
	@Autowired
	private RedisUtil redisUtil;
	private static final String GROUP = "SYSTEM_CONFIG";

	@Autowired
	public SystemConfigServiceImpl(DaoFactory daoFactory, @Value("${datasource.type}") String DATA_SOURCE_TYPE) {
		super();
		this.systemConfigDao = (SystemConfigDao) daoFactory.getDao(DATA_SOURCE_TYPE + "SystemConfigDao");
	}

	@Override
	@Cacheable(value = GROUP, key = "#p0")
	public String getSystemConfigByKey(String key) {
		return systemConfigDao.getSystemConfigByKey(key);
	}

	@Override
	public SystemConfigQuery getConfigPage(SystemConfigQuery query) {
		long count = systemConfigDao.getCount(query);
		ArrayList<SystemConfig> list = systemConfigDao.getPage(query);
		query.setTotalRecord(count);
		query.setResultItems(list);
		return query;
	}

	@Override
	public ResultInfo save(SystemConfig config) {
		ResultInfo result = ResultUtil.setResultInfo(EnumCommonResult.FAILED, null);
		EnumLogType logType = EnumLogType.SYSTEM_CONFIG_CHANGE;
		if (StringUtils.isNotBlank(config.getId())) {
			// 更新,状态设为启用
			config.setStatus(EnumCommonStatus.NORMAL.getCode());
			if (systemConfigDao.update(config) > 0) {
				result = ResultUtil.setResultInfo(EnumCommonResult.SUCCESS, result);
				// 操作成功,记录日志
				String message = logType.getValue() + ":" + config.getAttrKey() + "[" + config.getAttrValue() + "]";
				logUtil.writeSuccessLogCommon(EnumDefaultRoleType.SYSTEM.getCode(), logType.getCode(),
						config.getOperator(), message);
				redisUtil.set(GROUP + "::" + config.getAttrKey(), config.getAttrValue());
			}
		} else {
			result = ResultUtil.setResultInfo(EnumCommonResult.NOT_FOUND, result);
		}
		return result;
	}

	@Override
	public ResultInfo changeStatus(SystemConfig config) {
		ResultInfo result = ResultUtil.setResultInfo(EnumCommonResult.FAILED, null);
		if (StringUtils.isBlank(config.getAttrKey())) {
			return ResultUtil.setResultInfo(EnumCommonResult.CODE_NULL, result);
		}
		if (StringUtils.isBlank(config.getStatus())) {
			return ResultUtil.setResultInfo(EnumCommonResult.STATUS_NULL, result);
		}
		// 查找配置，不能改变系统必须的配置状态
		SystemConfig temp = systemConfigDao.getByCode(config.getAttrKey());
		if (null == temp) {
			return ResultUtil.setResultInfo(EnumCommonResult.NOT_FOUND, result);
		}
		if (StringUtils.equals(EnumYesOrNo.YES.getCode(), temp.getRequired())) {
			return ResultUtil.setResultInfo(EnumCommonResult.CANT_OPERATE_SYSTEM_CONF, result);
		}
		if (systemConfigDao.changeStatusByCode(config) > 0) {
			result = ResultUtil.setResultInfo(EnumCommonResult.SUCCESS, result);
			String message = EnumLogType.SYSTEM_CONFIG_STATUS_CHANGE.getValue() + ":" + config.getAttrKey() + "["
					+ config.getStatus() + "]";
			logUtil.writeSuccessLogCommon(EnumDefaultRoleType.SYSTEM.getCode(),
					EnumLogType.SYSTEM_CONFIG_STATUS_CHANGE.getCode(), config.getOperator(), message);
			if (StringUtils.equals(config.getStatus(), EnumCommonStatus.DISABLED.getCode())) {
				// 禁用后删除缓存
				String key = GROUP + "::" + config.getAttrKey();
				if (redisUtil.hasKey(key)) {
					redisUtil.del(key);
				}
			}
		}
		return result;
	}

}
