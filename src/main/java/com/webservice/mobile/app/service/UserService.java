package com.webservice.mobile.app.service;

import com.webservice.mobile.app.shared.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUser(String email);
    UserDTO getUserByUserId(String userId);
    UserDTO updateUser(String userId, UserDTO user);
    void deleteUser(String userId);
    List<UserDTO> getUsers(int page,int limit);
}
