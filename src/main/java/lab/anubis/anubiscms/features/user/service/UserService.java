package lab.anubis.anubiscms.features.user.service;

import lab.anubis.anubiscms.features.user.dto.UserDTO;
import lab.anubis.anubiscms.features.user.model.User;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO getUserbyId(Long userId);

    User findByUsername(String username);

    void updateUserRole(Long userId, String roleName);
}
