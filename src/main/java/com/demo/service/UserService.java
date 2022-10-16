package com.demo.service;

import com.demo.dto.ChangePasswordForm;
import com.demo.entity.User;

public interface UserService {

    public Iterable<User> getAllUsers();

    User createUser(User formUser)throws Exception;

    public User getUserById(Long id ) throws Exception;

    public User getUserByUsername(String username) throws  Exception;

    public User updateUser(User user) throws Exception;
    public User deleteUser(Long id) throws Exception;

    public User changePassword(ChangePasswordForm form) throws Exception;

    public void deleteUserByUsername(String username);
    public User getLoggedUser() throws Exception;

    public boolean isLoggedUserADMIN();

    public boolean getUserRole(String username) throws Exception;

    public String findByRoles(String roleDesc);

}
