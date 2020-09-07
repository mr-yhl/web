package cn.com.mryhl.mapper;

import cn.com.mryhl.domain.Role;

import java.util.List;

public interface RoleMapper {
    /**
     * 根据用户id查询角色列表
     */
    public List<Role> findByUid(Integer uid);
}
