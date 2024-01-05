/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2024 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.2 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
package de.ingrid.utils.tool;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Due to the deprecated dependency to "XmlBeanFactory", this class has been
 * marked deprecated, too.
 * @author Andre
 *
 */
public class SpringUtil {

    private ClassPathXmlApplicationContext context;

    public SpringUtil(String fileName) {
        Resource resource = new ClassPathResource(fileName);
        if (resource.exists()) {
            //_xmlBeanFactory = new XmlBeanFactory(resource);
            context = new ClassPathXmlApplicationContext(fileName);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(String beanName, Class<T> clazz) {
        T t = null;
        if (context != null) {
            t = (T) context.getBean( beanName );
        }
        return t;
    }

}
