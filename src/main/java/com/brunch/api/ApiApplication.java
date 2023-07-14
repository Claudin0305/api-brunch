package com.brunch.api;

import com.brunch.api.entity.ApplicationUser;
import com.brunch.api.entity.Role;
import com.brunch.api.repository.RoleRepository;
import com.brunch.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncoder.encode("11111111"), "root@mail.com", "Super Admin",roles);
            userRepository.save(admin);
        };
    }

}
