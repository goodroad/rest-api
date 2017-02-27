package com.goodroad;

import com.goodroad.storage.StorageProperties;
import com.goodroad.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(final StorageService storageService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                storageService.deleteAll();
//                storageService.init();
            }
        };
    }

    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @EnableWebSecurity
    static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        private DataSource dataSource;

        /**
         * This section defines the user accounts which can be used for authentication as well as the roles each user has.
         *
         * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
         */
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//            auth.inMemoryAuthentication().//
//                withUser("admin").password("admin").roles("USER", "ADMIN");
//        }


        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.jdbcAuthentication().dataSource(this.dataSource);
        }

        /**
         * This section defines the security policy for the app.
         * <p>
         * <ul>
         * <li>BASIC authentication is supported (enough for this REST-based demo).</li>
         * <li>/employees is secured using URL security shown below.</li>
         * <li>CSRF headers are disabled since we are only testing the REST interface, not a web one.</li>
         * </ul>
         * NOTE: GET is not shown which defaults to permitted.
         *
         * @param http
         * @throws Exception
         * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.httpBasic().and().authorizeRequests()
                .antMatchers( "/admin/**").hasRole("ADMIN")
                .and().csrf().disable();

        }
    }
}
