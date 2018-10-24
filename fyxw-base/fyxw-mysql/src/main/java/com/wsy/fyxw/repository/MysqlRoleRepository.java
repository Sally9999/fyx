package com.wsy.fyxw.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wsy.fyxw.domain.Role;
import com.wsy.fyxw.query.RoleQuery;

@Mapper
public interface MysqlRoleRepository extends MysqlBaseRepository<Role, RoleQuery> {

	public List<Role> getRoleListByAccount(String acccount);
}
