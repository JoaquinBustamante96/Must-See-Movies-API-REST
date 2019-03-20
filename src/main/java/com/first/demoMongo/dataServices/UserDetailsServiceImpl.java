package com.first.demoMongo.dataServices;

import com.first.demoMongo.documents.Role;
import com.first.demoMongo.documents.User;
import com.first.demoMongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String P_TOKEN = "";

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String usernameOrTokenValue) {
        User user = userRepository.findByTokenValue(usernameOrTokenValue);
        if (user != null) {
            return this.userBuilder(user.getUsername(), new BCryptPasswordEncoder().encode(P_TOKEN),
                    user.getRoles(),
                    user.isActive(),
                    user.isTokenExpired()
                    );
        } else {
            user = userRepository.findByusername(usernameOrTokenValue);
            if (user != null) {
                return this.userBuilder(String.valueOf(user.getUsername()), user.getPassword(), new Role[]{Role.AUTHENTICATED},
                        user.isActive(),true);
            } else {
                throw new UsernameNotFoundException("Username-token not found. " + usernameOrTokenValue);
            }
        }
    }

    private org.springframework.security.core.userdetails.User userBuilder(String mobile,
                                                                           String password,
                                                                           Role[] roles,
                                                                           boolean active,
                                                                           boolean credentialsNonExpired) {
        boolean accountNonExpired = true;
        boolean accountNonLocked = true;

        boolean enabled = active;
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.roleName()));
        }
        return new org.springframework.security.core.userdetails.User(mobile, password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
    }
}
