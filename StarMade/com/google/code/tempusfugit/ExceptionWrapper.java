/*    */ package com.google.code.tempusfugit;
/*    */ 
/*    */ import java.lang.reflect.Constructor;
/*    */ import java.util.concurrent.Callable;
/*    */ 
/*    */ public class ExceptionWrapper
/*    */ {
/*    */   public static <V> V wrapAsRuntimeException(Callable<V> callable)
/*    */     throws RuntimeException
/*    */   {
/*    */     try
/*    */     {
/* 29 */       return callable.call();
/*    */     } catch (Exception e) {
/* 31 */       throw new RuntimeException(e);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static <V, E extends Exception> V wrapAnyException(Callable<V> callable, WithException<E> wrapped) throws Exception {
/*    */     try {
/* 37 */       return callable.call();
/*    */     } catch (Throwable e) {
/* 39 */       throw ExceptionFactory.newException(wrapped, e).create();
/*    */     }
/*    */   }
/*    */ 
/*    */   static class ExceptionFactory<E extends Exception> implements Factory<E> {
/*    */     private final Class wrapped;
/*    */     private final Throwable throwable;
/*    */ 
/* 48 */     static <E extends Exception> ExceptionFactory<E> newException(WithException<E> wrapped, Throwable throwable) { return new ExceptionFactory(wrapped.getType(), throwable); }
/*    */ 
/*    */     private ExceptionFactory(Class<E> wrapped, Throwable throwable)
/*    */     {
/* 52 */       this.wrapped = wrapped;
/* 53 */       this.throwable = throwable;
/*    */     }
/*    */ 
/*    */     public E create() throws FactoryException
/*    */     {
/*    */       try {
/* 59 */         Constructor constructor = this.wrapped.getConstructor(new Class[] { Throwable.class });
/* 60 */         return (Exception)constructor.newInstance(new Object[] { this.throwable });
/*    */       } catch (Exception e) {
/* 62 */         throw new FactoryException(e);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.ExceptionWrapper
 * JD-Core Version:    0.6.2
 */