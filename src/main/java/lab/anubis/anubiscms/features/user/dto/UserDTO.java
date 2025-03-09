package lab.anubis.anubiscms.features.user.dto;

import lab.anubis.anubiscms.features.role.model.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserDTO(
        Long userId,
        String userName,
        String email,
        boolean accountNonLocked,
        boolean accountNonExpired,
        boolean credentialsNonExpired,
        boolean enabled,
        LocalDate credentialsExpiryDate,
        LocalDate accountExpiryDate,
        String twoFactorSecret,
        boolean isTwoFactorEnabled,
        String signUpMethod,
        Role role,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {}
