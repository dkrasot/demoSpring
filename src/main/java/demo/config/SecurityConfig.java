package demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //// alternative OF MethodSecurity TO MethodSecurityConfig (extends GlobalMethodSecurityConfiguration)
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("demo").roles("USER")
                .and()
                .withUser("admin").password("demo").roles("ADMIN")
                .and()
                .withUser("useradmin").password("demo").roles("USER","ADMIN");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/signup", "/profile/**").permitAll()
                .antMatchers(HttpMethod.POST, "/tweets").authenticated()
                .antMatchers(HttpMethod.GET, "/tweets").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login-error")
                .usernameParameter("username")
                .passwordParameter("password").permitAll();
        http.logout()
                .permitAll()
                .logoutUrl("/logout")
                .invalidateHttpSession(true);
    }
}
