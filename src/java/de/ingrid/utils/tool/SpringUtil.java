package de.ingrid.utils.tool;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class SpringUtil {

	private XmlBeanFactory _xmlBeanFactory;

	public SpringUtil(String fileName) {
		Resource resource = new ClassPathResource(fileName);
		_xmlBeanFactory = new XmlBeanFactory(resource);
	}

	@SuppressWarnings("unchecked")
	public <T> T getBean(String beanName, Class<T> clazz) {
		Object bean = _xmlBeanFactory.getBean(beanName);
		return (T) bean;
	}

}
