package it.cascino.util;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
//import javax.enterprise.inject.Alternative;
//import javax.enterprise.inject.Stereotype;
import javax.inject.Qualifier;

//@Stereotype
//@Alternative
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Documented
public @interface DatabaseDB2AS400DS{
}
