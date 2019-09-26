package com.moviesmustsee.dataServices;

import com.moviesmustsee.documents.Role;
import com.moviesmustsee.documents.User;
import com.moviesmustsee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String P_TOKEN = "";

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String emailOrTokenValue) {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }
        User user = userRepository.findByTokenValue(emailOrTokenValue);
        if (user != null) {
            return this.userBuilder(user.getEmail(), new BCryptPasswordEncoder().encode(P_TOKEN),
                    user.getRoles(),
                    user.isActive(),
                    !user.isTokenExpired()
                    );
        } else {
            user = userRepository.findByemail(emailOrTokenValue);
            if (user != null) {
                return this.userBuilder(String.valueOf(user.getEmail()), user.getPassword(), new Role[]{Role.AUTHENTICATED},
                        user.isActive(),true);
            } else {
                throw new UsernameNotFoundException("Username-token not found. " + emailOrTokenValue);
            }
        }
    }

    private org.springframework.security.core.userdetails.User userBuilder(String email,
                                                                           String password,
                                                                           Role[] roles,
                                                                           boolean active,
                                                                           boolean credentialsNonExpired) {
        boolean accountNonExpired = true;
        boolean accountNonLocked = true;

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.roleName()));
        }
        return new org.springframework.security.core.userdetails.User(email, password, active, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

}
