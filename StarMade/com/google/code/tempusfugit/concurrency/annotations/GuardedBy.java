/*    */ package com.google.code.tempusfugit.concurrency.annotations;
/*    */ 
/*    */ import java.lang.annotation.Annotation;
/*    */ import java.lang.annotation.Documented;
/*    */ import java.lang.annotation.Retention;
/*    */ import java.lang.annotation.RetentionPolicy;
/*    */ import java.lang.annotation.Target;
/*    */ 
/*    */ @Documented
/*    */ @Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD})
/*    */ @Retention(RetentionPolicy.RUNTIME)
/*    */ public @interface GuardedBy
/*    */ {
/*    */   public abstract Type lock();
/*    */ 
/*    */   public abstract String details();
/*    */ 
/*    */   public static enum Type
/*    */   {
/* 45 */     THIS, 
/*    */ 
/* 47 */     INNER_CLASS_THIS, 
/*    */ 
/* 49 */     CLASS, 
/*    */ 
/* 51 */     ITSELF, 
/*    */ 
/* 53 */     FIELD, 
/*    */ 
/* 55 */     STATIC_FIELD, 
/*    */ 
/* 57 */     METHOD;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.annotations.GuardedBy
 * JD-Core Version:    0.6.2
 */