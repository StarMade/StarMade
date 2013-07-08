package org.lwjgl.util.mapped;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface CacheLinePad
{
  boolean before() default false;
  
  boolean after() default true;
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.CacheLinePad
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */