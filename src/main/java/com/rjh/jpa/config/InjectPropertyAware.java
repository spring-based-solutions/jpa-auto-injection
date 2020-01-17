package com.rjh.jpa.config;

import java.util.Optional;

/**
 * 注入属性值的适配器
 * @author Null
 * @date 2020-01-16
 */
public interface InjectPropertyAware<T> {
    /**
     * 属性值
     * @return
     */
    Optional<T> getPropertyValue();

    /**
     * 属性名
     * @return
     */
    String getPropertyName();
}
