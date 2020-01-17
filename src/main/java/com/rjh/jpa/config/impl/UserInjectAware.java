package com.rjh.jpa.config.impl;

import com.rjh.jpa.config.InjectPropertyAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Null
 * @date 2020-01-16
 */
@Component
public class UserInjectAware implements InjectPropertyAware<String> {

    @Override
    public Optional<String> getPropertyValue() {
        return Optional.of(String.valueOf(System.currentTimeMillis()));
    }

    @Override
    public String getPropertyName() {
        return "userId";
    }
}
