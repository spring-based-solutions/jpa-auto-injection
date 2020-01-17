package com.rjh.jpa.config.impl;

import com.rjh.jpa.config.InjectPropertyAware;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

/**
 * @author Null
 * @date 2020-01-16
 */
@Component
public class NowPropertyAware implements InjectPropertyAware<Date> {
    @Override
    public Optional<Date> getPropertyValue() {
        return Optional.of(new Date());
    }

    @Override
    public String getPropertyName() {
        return "now";
    }
}
