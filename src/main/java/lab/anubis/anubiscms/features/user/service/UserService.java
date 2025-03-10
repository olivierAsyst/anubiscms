package lab.anubis.anubiscms.features.user.service;

import lab.anubis.anubiscms.features.user.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO getUserbyId(Long userId);
    void updateUserRole(Long userId, String roleName);
}
