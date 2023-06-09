package com.illutech.advertsite.config.customconfigs;

import com.illutech.advertsite.entities.Users;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
@RequiredArgsConstructor
@NoArgsConstructor
@Service
public class CustomUserDetails implements UserDetails {
    private Users user;
    public CustomUserDetails(Users user) {
        super();
        this.user = user;//alternative for using @Autowired annotation which is not really a good idea.
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getUserType()));
    }
    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
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
        return true;
    }
}
