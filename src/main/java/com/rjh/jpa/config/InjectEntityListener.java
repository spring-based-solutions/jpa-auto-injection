package com.rjh.jpa.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.CollectionUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Null
 * @date 2020-01-16
 */
@Slf4j
@Configurable
public class InjectEntityListener implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @PrePersist
    public void preSave(Object target) {
        handle(target, true);
    }

    @PreUpdate
    public void preUpdate(Object target) {
        handle(target, false);
    }

    private void handle(Object target, boolean create) {
        if (target != null) {
            Field[] fields = target.getClass().getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    handleProperty(target, field, create);
                }
            }
        }
    }

    private void handleProperty(Object target, Field field, boolean create) {
        InjectProperty annotation = field.getAnnotation(InjectProperty.class);
        if (annotation != null) {
            String propertyName = annotation.value();
            Collection<InjectPropertyAware> handlers = getInjectPropertyHandler();
            Iterator<InjectPropertyAware> it = handlers.iterator();
            while (it.hasNext()) {
                InjectPropertyAware handler = it.next();
                if (handler.getPropertyName().equals(propertyName)) {
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    handler.getPropertyValue().ifPresent(value -> {
                        try {
                            // 属性原始值
                            Object originValue = field.get(target);
                            if (create) { // 新增模式
                                if ((originValue == null) || (originValue != null && annotation.force())) {
                                    field.set(target, value);
                                }
                            } else { // 更新模式
                                if (!annotation.insert() &&
                                        (originValue == null || (originValue != null && annotation.force()))) { // 是否配置为更新时赋值
                                    field.set(target, value);
                                }
                            }
                        } catch (IllegalAccessException e) {
                            log.error("注入属性失败" + target.getClass() + "." + propertyName + "失败", e);
                        }
                    });
                }
            }
        }
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Collection<InjectPropertyAware> getInjectPropertyHandler() {
        Map<String, InjectPropertyAware> map = applicationContext.getBeansOfType(InjectPropertyAware.class);
        if (CollectionUtils.isEmpty(map)) {
            return Collections.EMPTY_LIST;
        }
        return map.values();
    }

}
