package com.google.code.tempusfugit.concurrency.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Not
{
  Class<? extends Annotation>[] value();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.annotations.Not
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */