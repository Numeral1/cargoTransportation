package com.innowise.cargo_transportation.web.security.user_security_dto;

import com.innowise.cargo_transportation.core.entity.ApprovalStatus;
import com.innowise.cargo_transportation.core.entity.ClientEntity;
import com.innowise.cargo_transportation.core.entity.UserEntity;
import com.innowise.cargo_transportation.core.entity.UserRoleEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserDetailsImpl implements UserDetails {
    private final UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<UserRoleEntity> userRole = userEntity.getUserRole();
        Set<GrantedAuthority> authorities = userRole.stream()
                .map(UserRoleEntity::getRole)
                .map(r -> (GrantedAuthority) r::name)
                .collect(Collectors.toSet());
        return authorities;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        ClientEntity clientEntity = userEntity.getClient();
        return clientEntity.getApprovalStatus() != ApprovalStatus.DISABLE;
    }
}
