package com.anhoang.shopping.online.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	@Lazy
	private AuthFailureHandlerImpl authenticationFailureHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            // Cho phép iframe để H2 Console hoạt động
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
            // Cấu hình quyền truy cập
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()   // Cho H2 Console
                .requestMatchers("/user/**").hasRole("USER")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
            )
            // Cấu hình form login
            .formLogin(form -> form
                .loginPage("/signin")
                .loginProcessingUrl("/login")
                .failureHandler(authenticationFailureHandler)
                .successHandler(authenticationSuccessHandler)
                .permitAll()
            )
            // Cấu hình logout
            .logout(logout -> logout.permitAll());

        return http.build();
    }

}