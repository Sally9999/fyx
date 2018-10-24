package com.wsy.fyxw.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wsy.fyxw.dao.MenuDao;
import com.wsy.fyxw.dao.MenuGroupDao;
import com.wsy.fyxw.dao.factory.DaoFactory;
import com.wsy.fyxw.domain.Menu;
import com.wsy.fyxw.domain.MenuGroup;
import com.wsy.fyxw.domain.ResultInfo;
import com.wsy.fyxw.dto.MenuGroupTreeDto;
import com.wsy.fyxw.dto.MenuTreeDto;
import com.wsy.fyxw.dto.TreeStateDto;
import com.wsy.fyxw.enums.EnumCommonResult;
import com.wsy.fyxw.enums.EnumCommonStatus;
import com.wsy.fyxw.enums.EnumDefaultRoleType;
import com.wsy.fyxw.enums.EnumLogType;
import com.wsy.fyxw.enums.EnumYesOrNo;
import com.wsy.fyxw.query.MenuGroupQuery;
import com.wsy.fyxw.query.MenuQuery;
import com.wsy.fyxw.service.MenuService;
import com.wsy.fyxw.util.LogUtil;
import com.wsy.fyxw.util.ResultUtil;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private MenuDao menuDao;
	private MenuGroupDao menuGroupDao;
	@Autowired
	private LogUtil logUtil;

	@Autowired
	public MenuServiceImpl(DaoFactory daoFactory, @Value("${datasource.type}") String DATA_SOURCE_TYPE) {
		super();
		this.menuDao = (MenuDao) daoFactory.getDao(DATA_SOURCE_TYPE + "MenuDao");
		this.menuGroupDao = (MenuGroupDao) daoFactory.getDao(DATA_SOURCE_TYPE + "MenuGroupDao");
	}

	/**
	 * 通过角色获取菜单权限
	 * 
	 * @param roleCodeList
	 * @return
	 */
	@Override
	public ArrayList<MenuGroup> getAuthorityByRoleList(ArrayList<String> roleCodeList) {
		// 查询菜单组
		MenuGroupQuery query = new MenuGroupQuery();
		query.setStatus(EnumCommonStatus.NORMAL.getCode());
		ArrayList<MenuGroup> groupList = menuGroupDao.getList(query);
		if (CollectionUtils.isNotEmpty(groupList)) {
			Iterator<MenuGroup> it = groupList.iterator();
			while (it.hasNext()) {
				MenuGroup group = it.next();
				// 查询菜单列表
				MenuQuery menu = new MenuQuery();
				menu.setMenuGroup(group.getGroupCode());
				menu.setRoleCodeList(roleCodeList);
				menu.setStatus(EnumCommonStatus.NORMAL.getCode());
				ArrayList<Menu> menuList = menuDao.getMenuListByRoleAndGroup(menu);
				// menuList为空清除group
				if (CollectionUtils.isEmpty(menuList)) {
					it.remove();
				} else {
					group.setMenuList(menuList);
				}
			}
		}
		return groupList;
	}

	/**
	 * 分页查菜单组列表
	 * 
	 * @param query
	 * @return
	 */
	@Override
	public MenuGroupQuery getMenuGroupPage(MenuGroupQuery query) {
		long count = menuGroupDao.getCount(query);
		ArrayList<MenuGroup> list = menuGroupDao.getPage(query);
		query.setTotalRecord(count);
		query.setResultItems(list);
		return query;
	}

	/**
	 * 分页查菜单列表
	 * 
	 * @param query
	 * @return
	 */
	@Override
	public MenuQuery getMenuPage(MenuQuery query) {
		if (StringUtils.isBlank(query.getMenuGroup())) {
			logger.info("unfind menugroup");
		}
		long count = menuDao.getCount(query);
		ArrayList<Menu> list = menuDao.getPage(query);
		query.setTotalRecord(count);
		query.setResultItems(list);
		return query;
	}

	/**
	 * 根据groupCode获取菜单组详情
	 * 
	 * @param groupCode
	 * @return
	 */
	@Override
	public MenuGroup getMenuGroupByCode(String groupCode) {
		if (StringUtils.isNotBlank(groupCode)) {
			return menuGroupDao.getByCode(groupCode);
		}
		logger.error("groupCode:{} is blank", groupCode);
		return null;
	}

	/**
	 * 根据menuCode获取菜单详情
	 * 
	 * @param menuCode
	 * @return
	 */
	@Override
	public Menu getMenuByCode(String menuCode) {
		if (StringUtils.isNotBlank(menuCode)) {
			return menuDao.getByCode(menuCode);
		}
		logger.error("menuCode:{} is blank", menuCode);
		return null;
	}

	@Override
	public ResultInfo saveMenuGroup(MenuGroup group) {
		ResultInfo result = ResultUtil.setResultInfo(EnumCommonResult.FAILED, null);
		EnumLogType logType = EnumLogType.MENU_GROUP_ADD;
		if (StringUtils.isNotBlank(group.getId())) {
			// 更新
			if (menuGroupDao.update(group) > 0) {
				result = ResultUtil.setResultInfo(EnumCommonResult.SUCCESS, result);
				logType = EnumLogType.MENU_GROUP_UPDATE;
			}
		} else {
			// 新增
			// 唯一性检查
			MenuGroup temp = menuGroupDao.getByCode(group.getGroupCode());
			if (null != temp) {
				result = ResultUtil.setResultInfo(EnumCommonResult.CODE_EXIST, result);
			} else if (menuGroupDao.add(group) > 0) {
				result = ResultUtil.setResultInfo(EnumCommonResult.SUCCESS, result);
			}
		}
		if (StringUtils.equals(result.getCode(), EnumCommonResult.SUCCESS.getCode())) {
			// 操作成功,记录日志
			String message = logType.getValue() + ":" + group.getGroupName() + "[" + group.getGroupCode() + "]";
			logUtil.writeSuccessLogCommon(EnumDefaultRoleType.SYSTEM.getCode(), logType.getCode(), group.getOperator(),
					message);
		}
		return result;
	}

	@Override
	public ResultInfo saveMenu(Menu menu) {
		ResultInfo result = ResultUtil.setResultInfo(EnumCommonResult.FAILED, null);
		EnumLogType logType = EnumLogType.MENU_ADD;
		if (StringUtils.isNotBlank(menu.getId())) {
			// 更新
			if (menuDao.update(menu) > 0) {
				result = ResultUtil.setResultInfo(EnumCommonResult.SUCCESS, result);
				logType = EnumLogType.MENU_UPDATE;
			}
		} else {
			// 新增
			// 唯一性检查
			Menu temp = menuDao.getByCode(menu.getMenuCode());
			if (null != temp) {
				result = ResultUtil.setResultInfo(EnumCommonResult.CODE_EXIST, result);
			} else if (menuDao.add(menu) > 0) {
				result = ResultUtil.setResultInfo(EnumCommonResult.SUCCESS, result);
			}
		}
		if (StringUtils.equals(result.getCode(), EnumCommonResult.SUCCESS.getCode())) {
			// 操作成功,记录日志
			String message = logType.getValue() + ":" + menu.getMenuName() + "[" + menu.getMenuCode() + "]";
			logUtil.writeSuccessLogCommon(EnumDefaultRoleType.SYSTEM.getCode(), logType.getCode(), menu.getOperator(),
					message);
		}
		return result;
	}

	@Override
	public ResultInfo deleteMenuGroup(MenuGroup group) {
		ResultInfo result = ResultUtil.setResultInfo(EnumCommonResult.FAILED, null);
		if (StringUtils.isBlank(group.getGroupCode())) {
			return ResultUtil.setResultInfo(EnumCommonResult.CODE_NULL, result);
		}
		// 查询菜单组中是否有菜单
		MenuQuery query = new MenuQuery();
		query.setMenuGroup(group.getGroupCode());
		if (menuDao.getCount(query) > 0) {
			result.setValue("里面还有菜单啊，要不先处理下？");
		} else if (menuGroupDao.deleteByCode(group.getGroupCode()) > 0) {
			result = ResultUtil.setResultInfo(EnumCommonResult.SUCCESS, result);
			String message = EnumLogType.MENU_GROUP_DELETE.getValue() + ":" + group.getGroupCode();
			logUtil.writeSuccessLogCommon(EnumDefaultRoleType.SYSTEM.getCode(), EnumLogType.MENU_GROUP_DELETE.getCode(),
					group.getOperator(), message);
		}
		return result;
	}

	@Override
	public ResultInfo deleteMenu(Menu menu) {
		ResultInfo result = ResultUtil.setResultInfo(EnumCommonResult.FAILED, null);
		if (StringUtils.isBlank(menu.getMenuCode())) {
			return ResultUtil.setResultInfo(EnumCommonResult.CODE_NULL, result);
		}
		if (menuDao.deleteByCode(menu.getMenuCode()) > 0) {
			result = ResultUtil.setResultInfo(EnumCommonResult.SUCCESS, result);
			String message = EnumLogType.MENU_DELETE.getValue() + ":" + menu.getMenuCode();
			logUtil.writeSuccessLogCommon(EnumDefaultRoleType.SYSTEM.getCode(), EnumLogType.MENU_DELETE.getCode(),
					menu.getOperator(), message);
		}
		return result;
	}

	/**
	 * 获取启用状态菜单信息并设置对应角色是否具有权限
	 * 
	 * @param roleCode
	 * @return
	 */
	@Override
	public ArrayList<MenuGroupTreeDto> getMenuGroupWithRole(String roleCode) {
		ArrayList<MenuGroupTreeDto> groupTreeList = new ArrayList<MenuGroupTreeDto>();
		MenuGroupQuery groupQuery = new MenuGroupQuery();
		groupQuery.setStatus(EnumCommonStatus.NORMAL.getCode());
		ArrayList<MenuGroup> groupList = menuGroupDao.getList(groupQuery);
		if (CollectionUtils.isNotEmpty(groupList)) {
			for (MenuGroup menuGroup : groupList) {
				MenuQuery menuQuery = new MenuQuery();
				menuQuery.setStatus(EnumCommonStatus.NORMAL.getCode());
				menuQuery.setMenuGroup(menuGroup.getGroupCode());
				menuQuery.setRoleCode(roleCode);
				ArrayList<Menu> menuList = menuDao.getList(menuQuery);
				// menuList为空清除group
				if (CollectionUtils.isNotEmpty(menuList)) {
					MenuGroupTreeDto groupTree = new MenuGroupTreeDto(menuGroup);
					List<MenuTreeDto> menuTreeDtos = new ArrayList<MenuTreeDto>();
					Boolean flag = false;
					for (Menu menu : menuList) {
						if (StringUtils.equalsIgnoreCase(menu.getHasAuthority(), EnumYesOrNo.YES.getCode())) {
							flag = true;
						}
						menuTreeDtos.add(new MenuTreeDto(menu));
					}
					if (flag) {
						TreeStateDto treeStateDto = new TreeStateDto();
						treeStateDto.setChecked(true);
						treeStateDto.setExpanded(true);
						groupTree.setState(treeStateDto);
					}
					groupTree.setNodes(menuTreeDtos);
					groupTreeList.add(groupTree);
				}
			}
		}
		return groupTreeList;
	}

	@Override
	public ArrayList<Menu> getAuthentication() {
		return menuDao.getAuthentication();
	}

}
