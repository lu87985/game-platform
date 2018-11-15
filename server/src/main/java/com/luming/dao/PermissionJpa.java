package com.luming.dao;

import com.luming.model.pojo.PermissionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author ming.lu@insentek.com
 * @date 2018/11/6
 * 修正历史：
 * 	2018/11/6：文件创建
 */
public interface PermissionJpa extends JpaRepository<PermissionDO, Integer> {
    
    /**
     * 获取角色所有的操作权限
     * @param roleId
     * @return
     */
    @Query(value = "SELECT p.id, p.name,  p.description, p.url, p.pid FROM tb_role_permission AS rp LEFT JOIN tb_permission AS p ON rp.permission_id = p.id WHERE rp.role_id = ?1", nativeQuery = true)
    List<PermissionDO> findPermissionListByRoleId(Integer roleId);
    
    
    /**
     * 所有操作权限
     * @return
     */
    @Query(value = "SELECT p.id, p.name,  p.description, p.url, p.pid FROM tb_permission AS p", nativeQuery = true)
    List<PermissionDO> findPermissionList();
}
