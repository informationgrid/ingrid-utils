package de.ingrid.utils.metadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PACKAGE)
public @interface MetadataAnnotation {

	String version() default ("unknown");

	String date() default ("1970-01-01");

	IPlugType type() default (IPlugType.OTHER);
}
