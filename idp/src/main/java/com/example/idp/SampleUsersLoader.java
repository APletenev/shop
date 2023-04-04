package com.example.idp;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Profile("dev")
@AllArgsConstructor
public class SampleUsersLoader {

    private JdbcUserDetailsManager jdbcUserDetailsManager;

    private final Logger log = Logger.getLogger(this.getClass().getName());

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        log.info("Development environment preparing. Loading sample data..");
        try {
            jdbcUserDetailsManager.createUser(
                    User.withUsername("admin").password("{bcrypt}"+"$2a$12$z0XvKwsTBcObssE6KffWmuekgfrnEs0wiI4BRAqtsK/M4lKGdJrWS").roles("ADMIN").build());
            jdbcUserDetailsManager.createUser(
                    User.withUsername("user").password("{bcrypt}"+"$2a$12$UNZp13hRqxsdLVhyxMqVE.XfEwM/H6A2p8CzHM7dHp4VFcUxpJbuu").roles("USER").build());
        } catch (DuplicateKeyException e) {
            log.info("users already exist");
        }

    }
}