package com.sena.inventarioback.config;

import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//            .authorizeRequests()
//            .antMatchers("/api/auth/**").permitAll() // Allow access to authentication endpoints
//            .anyRequest().authenticated()
//            .and()
//            .apply(new JwtConfigurer(jwtTokenProvider));
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}