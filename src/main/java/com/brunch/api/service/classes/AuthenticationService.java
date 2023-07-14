package com.brunch.api.service.classes;

import com.brunch.api.entity.ApplicationUser;
import com.brunch.api.entity.LoginResponseDTO;
import com.brunch.api.entity.Role;
import com.brunch.api.repository.RoleRepository;
import com.brunch.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public ApplicationUser registerUser(String username, String password, String name, String email){
        String encodPassword = passwordEncoder.encode(password);
        Role userRoles = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRoles);
        return userRepository.save(new ApplicationUser(0, username, encodPassword, email, name,authorities));
    }

    public LoginResponseDTO loginUser(String username, String password){
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            String token = tokenService.generateJwt(auth);
            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);

        }catch (AuthenticationException e){
            return new LoginResponseDTO(null, "");
        }
    }
}
