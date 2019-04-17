package com.guess.util;

import com.guess.entity.enums.UserRoleEntity;
import com.guess.security.impl.UserDetailsImpl;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

import static com.guess.entity.enums.UserRoleEntity.ADMIN;

@UtilityClass
public class SecurityUtility {

    public static boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetailsImpl;
    }

    public static UUID getUserId() {
        final UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getDetails();

        return userDetails.getUserId();
    }

    public static boolean isAdmin() {
        return isAuthenticated() && getUserRole().equals(ADMIN);
    }

    public static UserRoleEntity getUserRole() {
        final UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getDetails();

        return userDetails.getUserRole();
    }

}
