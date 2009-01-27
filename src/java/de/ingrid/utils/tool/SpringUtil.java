package de.ingrid.utils.tool;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class SpringUtil {

    private XmlBeanFactory _xmlBeanFactory;

    public SpringUtil(String fileName) {
        Resource resource = new ClassPathResource(fileName);
        if (resource.exists()) {
            _xmlBeanFactory = new XmlBeanFactory(resource);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(String beanName, Class<T> clazz) {
        T t = null;
        if (_xmlBeanFactory != null) {
            Object bean = _xmlBeanFactory.getBean(beanName);
            t = (T) bean;
        }
        return t;
    }

}
