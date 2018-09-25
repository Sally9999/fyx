package com.wsy.fyxw.service.impl;

import java.util.ArrayList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsy.fyxw.dao.RelationMenuRoleDao;
import com.wsy.fyxw.dao.RoleDao;
import com.wsy.fyxw.domain.RelationMenuRole;
import com.wsy.fyxw.domain.ResultInfo;
import com.wsy.fyxw.domain.Role;
import com.wsy.fyxw.dto.RoleTreeDto;
import com.wsy.fyxw.enums.EnumCommonResult;
import com.wsy.fyxw.enums.EnumCommonStatus;
import com.wsy.fyxw.enums.EnumDefaultRoleType;
import com.wsy.fyxw.enums.EnumLogType;
import com.wsy.fyxw.enums.EnumRoleType;
import com.wsy.fyxw.query.RelationMenuRoleQuery;
import com.wsy.fyxw.query.RoleQuery;
import com.wsy.fyxw.service.RoleService;
import com.wsy.fyxw.util.LogUtil;
import com.wsy.fyxw.util.ResultUtil;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private LogUtil logUtil;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RelationMenuRoleDao relationMenuRoleDao;

	@Override
	public RoleQuery getRolePage(RoleQuery query) {
		int count = roleDao.getCount(query);
		ArrayList<Role> list = roleDao.getPage(query);
		query.setTotalRecord(count);
		query.setResultItems(list);
		return query;
	}

	@Override
	public Role getRoleByCode(String roleCode) {
		if (StringUtils.isNotBlank(roleCode)) {
			return roleDao.getByCode(roleCode);
		}
		logger.error("roleCode:{} is blank", roleCode);
		return null;
	}

	@Override
	public ResultInfo saveRole(Role role) {
		ResultInfo result = ResultUtil.setResultInfo(EnumCommonResult.FAILED, null);
		EnumLogType logType = EnumLogType.ROLE_ADD;
		if (null != role.getId() && 0 != role.getId()) {
			// 更新
			if (roleDao.update(role) > 0) {
				result = ResultUtil.setResultInfo(EnumCommonResult.SUCCESS, result);
				logType = EnumLogType.ROLE_UPDATE;
			}
		} else {
			// 新增
			// 唯一性检查
			Role temp = roleDao.getByCode(role.getRoleCode());
			role.setType(EnumRoleType.USER_DEFINED.getCode());// 自定义角色
			if (null != temp) {
				result = ResultUtil.setResultInfo(EnumCommonResult.CODE_EXIST, result);
			} else if (roleDao.add(role) > 0) {
				result = ResultUtil.setResultInfo(EnumCommonResult.SUCCESS, result);
			}
		}
		if (StringUtils.equals(result.getCode(), EnumCommonResult.SUCCESS.getCode())) {
			// 操作成功,记录日志
			String message = logType.getValue() + ":" + role.getRoleName() + "[" + role.getRoleCode() + "]";
			logUtil.writeSuccessLogCommon(EnumDefaultRoleType.SYSTEM.getCode(), logType.getCode(), role.getOperator(),
					message);
		}
		return result;
	}

	@Override
	public ResultInfo deleteRole(Role role) {
		ResultInfo result = ResultUtil.setResultInfo(EnumCommonResult.FAILED, null);
		if (StringUtils.isBlank(role.getRoleCode())) {
			return ResultUtil.setResultInfo(EnumCommonResult.CODE_NULL, result);
		}
		// 查找角色，不能删除系统默认角色
		Role temp = roleDao.getByCode(role.getRoleCode());
		if (null == temp) {
			return ResultUtil.setResultInfo(EnumCommonResult.NOT_FOUND, result);
		}
		if (StringUtils.equals(EnumRoleType.DEFAULT.getCode(), temp.getType())) {
			return ResultUtil.setResultInfo(EnumCommonResult.CANT_DELETE_SYSTEM_CONF, result);
		}
		if (roleDao.deleteByCode(role.getRoleCode()) > 0) {
			result = ResultUtil.setResultInfo(EnumCommonResult.SUCCESS, result);
			String message = EnumLogType.ROLE_DELETE.getValue() + ":" + role.getRoleCode();
			logUtil.writeSuccessLogCommon(EnumDefaultRoleType.SYSTEM.getCode(), EnumLogType.ROLE_DELETE.getCode(),
					role.getOperator(), message);
		}
		return result;
	}

	@Override
	public ResultInfo changeStatus(Role role) {
		ResultInfo result = ResultUtil.setResultInfo(EnumCommonResult.FAILED, null);
		if (StringUtils.isBlank(role.getRoleCode())) {
			return ResultUtil.setResultInfo(EnumCommonResult.CODE_NULL, result);
		}
		if (StringUtils.isBlank(role.getStatus())) {
			return ResultUtil.setResultInfo(EnumCommonResult.STATUS_NULL, result);
		}
		// 查找角色，不能改变系统默认角色状态
		Role temp = roleDao.getByCode(role.getRoleCode());
		if (null == temp) {
			return ResultUtil.setResultInfo(EnumCommonResult.NOT_FOUND, result);
		}
		if (StringUtils.equals(EnumRoleType.DEFAULT.getCode(), temp.getType())) {
			return ResultUtil.setResultInfo(EnumCommonResult.CANT_OPERATE_SYSTEM_CONF, result);
		}
		if (roleDao.changeStatusByCode(role) > 0) {
			result = ResultUtil.setResultInfo(EnumCommonResult.SUCCESS, result);
			String message = EnumLogType.ROLE_STATUS_CHANGE.getValue() + ":" + role.getRoleCode() + "["
					+ role.getStatus() + "]";
			logUtil.writeSuccessLogCommon(EnumDefaultRoleType.SYSTEM.getCode(),
					EnumLogType.ROLE_STATUS_CHANGE.getCode(), role.getOperator(), message);
		}
		return result;
	}

	@Override
	public ResultInfo saveAuthority(ArrayList<String> menuCodes, String roleCode, String operator) {
		ResultInfo result = ResultUtil.setResultInfo(EnumCommonResult.FAILED, null);
		try {
			if (StringUtils.isBlank(roleCode)) {
				return ResultUtil.setResultInfo(EnumCommonResult.ROLE_CODE_NULL, result);
			}
			ArrayList<RelationMenuRole> insertList = new ArrayList<RelationMenuRole>();
			ArrayList<RelationMenuRole> deleteList = new ArrayList<RelationMenuRole>();
			// 查询角色已有的菜单权限
			RelationMenuRoleQuery query = new RelationMenuRoleQuery();
			query.setRoleCode(roleCode);
			ArrayList<RelationMenuRole> list = relationMenuRoleDao.getRelationMenuRoleList(query);
			if (CollectionUtils.isEmpty(menuCodes)) {
				// 删除所有权限
				deleteList = list;
			} else {
				if (CollectionUtils.isNotEmpty(list)) {
					for (RelationMenuRole relationMenuRole : list) {
						// menuCodes 中没有则删除对应权限
						if (!menuCodes.contains(relationMenuRole.getMenuCode())) {
							deleteList.add(relationMenuRole);
						}
					}
					for (String menuCode : menuCodes) {
						boolean flag = false;// 记录是否存在
						for (RelationMenuRole relationMenuRole : list) {
							if (StringUtils.equals(menuCode, relationMenuRole.getMenuCode())) {
								flag = true;
								break;
							}
						}
						if (!flag) {
							// 不存在则新增
							RelationMenuRole relation = new RelationMenuRole();
							relation.setMenuCode(menuCode);
							relation.setRoleCode(roleCode);
							insertList.add(relation);
						}
					}
				} else {
					// 全部新增
					for (String menuCode : menuCodes) {
						RelationMenuRole relation = new RelationMenuRole();
						relation.setMenuCode(menuCode);
						relation.setRoleCode(roleCode);
						insertList.add(relation);
					}
				}
			}
			if (CollectionUtils.isNotEmpty(deleteList)) {
				relationMenuRoleDao.deleteBatch(deleteList);
			}
			if (CollectionUtils.isNotEmpty(insertList)) {
				relationMenuRoleDao.insertBatch(insertList);
			}
			// 记录日志
			String message = roleCode + EnumLogType.ROLE_AUTHORITY_CHANGE.getValue() + ":【" + menuCodes.toString()
					+ "】";
			logUtil.writeSuccessLogCommon(EnumDefaultRoleType.SYSTEM.getCode(),
					EnumLogType.ROLE_AUTHORITY_CHANGE.getCode(), operator, message);
			result = ResultUtil.setResultInfo(EnumCommonResult.SUCCESS, result);
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>saveAuthority failed:{}", e.getMessage());
		}
		return result;
	}

	@Override
	public ArrayList<RoleTreeDto> getRoleListWithAccount(String account) {
		ArrayList<RoleTreeDto> roleTreeList = new ArrayList<RoleTreeDto>();
		RoleQuery query = new RoleQuery();
		query.setStatus(EnumCommonStatus.NORMAL.getCode());
		query.setAccount(account);
		ArrayList<Role> list = roleDao.getList(query);
		if (CollectionUtils.isNotEmpty(list)) {
			for (Role role : list) {
				roleTreeList.add(new RoleTreeDto(role));
			}
		}
		return roleTreeList;
	}

}
