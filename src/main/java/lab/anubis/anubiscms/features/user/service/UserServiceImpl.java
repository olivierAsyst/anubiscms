package lab.anubis.anubiscms.features.user.service;

import lab.anubis.anubiscms.features.role.model.AppRole;
import lab.anubis.anubiscms.features.role.model.Role;
import lab.anubis.anubiscms.features.role.repository.RoleRepository;
import lab.anubis.anubiscms.features.user.dto.UserDTO;
import lab.anubis.anubiscms.features.user.mapper.UserMapper;
import lab.anubis.anubiscms.features.user.model.User;
import lab.anubis.anubiscms.features.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userConverter;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userConverter) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userConverter::convertToDto).toList();
    }

    @Override
    public UserDTO getUserbyId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return userConverter.convertToDto(user);
    }

    @Override
    public void updateUserRole(Long userId, String roleName) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        AppRole appRole = AppRole.valueOf(roleName);
        Role role = roleRepository.findByRoleName(appRole)
                .orElseThrow(() -> new RuntimeException("Ce role est introuvable"));
        user.setRole(role);
        userRepository.save(user);
    }
}
