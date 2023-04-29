package com.skripsi.waste_bank.configuration;

import com.skripsi.waste_bank.models.Admin;
import com.skripsi.waste_bank.models.Nasabah;
import com.skripsi.waste_bank.repository.AdminRepository;
import com.skripsi.waste_bank.repository.NasabahRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final AdminRepository adminRepository;
    private final NasabahRepository nasabahRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
                Optional<Admin> admin = adminRepository.findByEmail(username);
                if (admin.isEmpty()){
                    System.out.println("Nasabah ==> "+nasabahRepository.findByEmail(username).get());
                    return nasabahRepository.findByEmail(username).orElseThrow();
                }
                return admin.orElseThrow();
            }
        };
//        return username -> adminRepository.findByEmail(username).orElseThrow(()->
//                new UsernameNotFoundException("admin not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
