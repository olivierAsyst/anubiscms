package lab.anubis.anubiscms.features.user.mapper;

import lab.anubis.anubiscms.features.user.dto.UserDTO;
import lab.anubis.anubiscms.features.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDTO convertToDto(User user){
        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.isAccountNonLocked(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isEnabled(),
                user.getCredentialsExpiryDate(),
                user.getAccountExpiryDate(),
                user.getTwoFactorSecret(),
                user.isTwoFactorEnabled(),
                user.getSignUpMethod(),
                user.getRole(),
                user.getCreatedDate(),
                user.getUpdatedDate()
        );
    }
}
