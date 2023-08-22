package com.brunch.api.security.services;

import com.brunch.api.entity.Local;
import com.brunch.api.entity.Pays;
import com.brunch.api.entity.User;
import com.brunch.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public List<User> getAllUsers() {
        Sort sort = Sort.by("createdAt").descending();
        return userRepository.findAll(sort);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User update(Long id, User userUpdate){
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return null;
        }
        user.setRoles(userUpdate.getRoles());
        user.setEmail(userUpdate.getEmail());
        user.setName(userUpdate.getName());
        user.setPassword(userUpdate.getPassword());
        return userRepository.save(user);
    }
}
