package com.brunch.api.service.classes;

import com.brunch.api.entity.ApplicationUser;
import com.brunch.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("user is not valid"));
    }
    public List<ApplicationUser> getAllUsers(){
        Sort sort = Sort.by("createdAt").descending();
        return userRepository.findAll(sort);
    }

    public Optional<ApplicationUser> getUserById(Integer userId){
        return userRepository.findById(userId);
    }
}
