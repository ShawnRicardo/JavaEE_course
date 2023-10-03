package com.Ricardo;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MiniApplicationContext {
    private Map<String, Object> beans = new HashMap<>();

    public MiniApplicationContext(String configLocation) {
        try {
            SAXReader reader = new SAXReader();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configLocation);
            Document document = reader.read(inputStream);

            Element root = document.getRootElement();
            List<Element> beanElements = root.elements("bean");

            for (Element beanElement : beanElements) {
                String id = beanElement.attributeValue("id");
                String className = beanElement.attributeValue("class");
                Class<?> beanClass = Class.forName(className);
                Object bean = beanClass.newInstance();
                List<Element> propertyElements = beanElement.elements("property");
                for (Element propertyElement : propertyElements) {
                    String propertyName = propertyElement.attributeValue("name");
                    String propertyValue = propertyElement.attributeValue("value");
                    Field field = beanClass.getDeclaredField(propertyName);
                    field.setAccessible(true);
                    if (field.getType() == String.class) {
                        field.set(bean, propertyValue);
                    } else if (field.getType() == int.class || field.getType() == Integer.class) {
                        field.set(bean, Integer.parseInt(propertyValue));
                    }
                    field.setAccessible(false);
                }
                beans.put(id, bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String id) {
        return beans.get(id);
    }
}