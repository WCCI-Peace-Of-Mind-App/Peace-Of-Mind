package org.wecancodeit.peaceofmind;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PomWebSecurityAdapter extends WebSecurityConfigurerAdapter {
  @Bean
  public UserDetailsService userDetailsService()
  {
    return new PomUserDetailsService();
  }
  
  @Bean
  public PasswordEncoder passwordEncoder()
  {
    return new BCryptPasswordEncoder();
  }
  
  public PomWebSecurityAdapter()
  {
    super();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http.authorizeRequests()
        .antMatchers("/h2-console/**", "/css/**", "/js/**").permitAll()
        .anyRequest().authenticated()
        .and().csrf().ignoringAntMatchers("/h2-console/**")
        .and()
    .formLogin()
        .loginPage("/login").permitAll()
        .and()
    .logout()
        .logoutSuccessUrl("/login?logout")
        .permitAll()
        .and()
    .csrf()
        .disable()
    .headers()
        .frameOptions().disable();
  }
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception
  {
    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
  }
}
