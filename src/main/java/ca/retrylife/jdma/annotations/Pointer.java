package ca.retrylife.jdma.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a pointer to off-heap memory that must be free-d
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER, ElementType.LOCAL_VARIABLE , ElementType.TYPE_USE})
public @interface Pointer {

}