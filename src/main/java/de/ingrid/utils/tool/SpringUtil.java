/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.1 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl5
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
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
