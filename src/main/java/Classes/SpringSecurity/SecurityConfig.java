package Classes.SpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.SessionManagementFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private DataSource dataSource;

    private UserDetailsService usersService;

    private PasswordEncoder passwordEncoder;

    private CORSFilter corsFilter;

    @Autowired
    public SecurityConfig(DataSource dataSource, UserDetailsService usersService, PasswordEncoder passwordEncoder, CORSFilter corsFilter) {
        this.dataSource = dataSource;
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
        this.corsFilter = corsFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(authenticationProvider())
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery("select username, password from users where username=?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(corsFilter, SessionManagementFilter.class);
        http
                .csrf().disable();
        http
                .authorizeRequests().antMatchers("/registration").permitAll();
        http
                .authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll();
//        http
//                .authorizeRequests().antMatchers("/users/*", "/users").denyAll();
        http
                .authorizeRequests().anyRequest().fullyAuthenticated();
        http
                .httpBasic();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(usersService);
        return provider;
    }

}
