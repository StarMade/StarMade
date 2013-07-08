package com.google.code.tempusfugit.concurrency.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GuardedBy
{
  Type lock();
  
  String details() default "";
  
  public static enum Type
  {
    THIS,  INNER_CLASS_THIS,  CLASS,  ITSELF,  FIELD,  STATIC_FIELD,  METHOD;
    
    private Type() {}
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.annotations.GuardedBy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */