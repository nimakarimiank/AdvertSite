package com.illutech.advertsite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration {
    private static final String[] UserUrls={
            "/advert/userView",
            "/advert/addAdvert",
            "/users/setUsers"};
    private static final String[] AdminUrls={"/advert/pending/",
            "/advert/validate/",
            "/advert/batchValidate",
            "/advert/unValidate/","/advert/virtualDelete/"
            ,"/advert/restoreAdvert/","/advert/hardDelete/",
            "/advert/adminView","/users/set","/users/setAdmins",
            "/users/DeleteById/","/users/getUsers","/users/getAdmins",
            "/advert/userView", "/advert/addAdvert",
            "/users/setUsers"
            };

    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider =new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder( passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests().requestMatchers("/").permitAll()
                .requestMatchers(UserUrls)
                .hasAnyAuthority("USER","ADMIN")
                .requestMatchers(AdminUrls)
                .hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        return http.build();
    }
}
