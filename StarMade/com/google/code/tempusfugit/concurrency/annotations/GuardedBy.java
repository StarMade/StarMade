/*  1:   */package com.google.code.tempusfugit.concurrency.annotations;
/*  2:   */
/*  3:   */import java.lang.annotation.Annotation;
/*  4:   */import java.lang.annotation.Documented;
/*  5:   */import java.lang.annotation.Retention;
/*  6:   */import java.lang.annotation.RetentionPolicy;
/*  7:   */import java.lang.annotation.Target;
/*  8:   */
/* 34:   */@Documented
/* 35:   */@Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD})
/* 36:   */@Retention(RetentionPolicy.RUNTIME)
/* 37:   */public @interface GuardedBy
/* 38:   */{
/* 39:   */  Type lock();
/* 40:   */  
/* 41:   */  String details() default "";
/* 42:   */  
/* 43:   */  public static enum Type
/* 44:   */  {
/* 45:45 */    THIS, 
/* 46:   */    
/* 47:47 */    INNER_CLASS_THIS, 
/* 48:   */    
/* 49:49 */    CLASS, 
/* 50:   */    
/* 51:51 */    ITSELF, 
/* 52:   */    
/* 53:53 */    FIELD, 
/* 54:   */    
/* 55:55 */    STATIC_FIELD, 
/* 56:   */    
/* 57:57 */    METHOD;
/* 58:   */    
/* 59:   */    private Type() {}
/* 60:   */  }
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.annotations.GuardedBy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */