package com.demo.service;

import com.demo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{


    @Autowired
      RoleService roleService;

    @Override
    public Role getRoleByDescription(String roleDesc) {

        return roleService.getRoleByDescription(roleDesc);
    }
}
