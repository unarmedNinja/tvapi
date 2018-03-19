package com.tv.service;


import com.tv.db.UserRepository;
import com.tv.models.ApplicationUser;
import com.tv.models.ApplicationUserRole;
import com.tv.models.UserContext;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository applicationUserRepository;
    public UserDetailsServiceImpl(UserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUserName(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }

        List<ApplicationUserRole> appRoles = applicationUserRepository.getRolesForUser(applicationUser);
        applicationUser.setRoles(appRoles);

        if (applicationUser.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");

        List<GrantedAuthority> authorities = convertRolesToAuthorities(applicationUser.getRoles());

     //   UserContext userContext = UserContext.create(applicationUser.getUsername(), authorities);

       // return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());

        return new User(applicationUser.getUsername(), applicationUser.getPassword(), authorities);
    }

    public List<GrantedAuthority> convertRolesToAuthorities (List<ApplicationUserRole> roles){
        List<GrantedAuthority> authorities = roles.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());

        return authorities;
    }
}
