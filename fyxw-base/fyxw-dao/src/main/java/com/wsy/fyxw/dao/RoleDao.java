package com.wsy.fyxw.dao;

import java.util.List;

import com.wsy.fyxw.domain.Role;
import com.wsy.fyxw.query.RoleQuery;

public interface RoleDao extends BaseDao<Role, RoleQuery> {

	public List<Role> getRoleListByAccount(String acccount);
}
