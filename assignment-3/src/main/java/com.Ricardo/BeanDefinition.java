package com.Ricardo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

public class BeanDefinition {
    private String id;
    private String className;
    private Map<String, Object> properties;
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface InitMethod {
    }
    public @interface DestMethod {
    }
    public BeanDefinition(String id, String className, Map<String, Object> properties) {
        this.id = id;
        this.className = className;
        this.properties = properties;
    }

    // Get & Set
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}