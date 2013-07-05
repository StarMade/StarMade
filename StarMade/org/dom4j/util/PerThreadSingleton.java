/*    */ package org.dom4j.util;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ 
/*    */ public class PerThreadSingleton
/*    */   implements SingletonStrategy
/*    */ {
/* 25 */   private String singletonClassName = null;
/*    */ 
/* 27 */   private ThreadLocal perThreadCache = new ThreadLocal();
/*    */ 
/*    */   public void reset()
/*    */   {
/* 33 */     this.perThreadCache = new ThreadLocal();
/*    */   }
/*    */ 
/*    */   public Object instance() {
/* 37 */     Object singletonInstancePerThread = null;
/*    */ 
/* 39 */     WeakReference ref = (WeakReference)this.perThreadCache.get();
/*    */ 
/* 42 */     if ((ref == null) || (ref.get() == null)) {
/* 43 */       Class clazz = null;
/*    */       try {
/* 45 */         clazz = Thread.currentThread().getContextClassLoader().loadClass(this.singletonClassName);
/*    */ 
/* 47 */         singletonInstancePerThread = clazz.newInstance();
/*    */       } catch (Exception ignore) {
/*    */         try {
/* 50 */           clazz = Class.forName(this.singletonClassName);
/* 51 */           singletonInstancePerThread = clazz.newInstance();
/*    */         } catch (Exception ignore2) {
/*    */         }
/*    */       }
/* 55 */       this.perThreadCache.set(new WeakReference(singletonInstancePerThread));
/*    */     } else {
/* 57 */       singletonInstancePerThread = ref.get();
/*    */     }
/* 59 */     return singletonInstancePerThread;
/*    */   }
/*    */ 
/*    */   public void setSingletonClassName(String singletonClassName) {
/* 63 */     this.singletonClassName = singletonClassName;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.PerThreadSingleton
 * JD-Core Version:    0.6.2
 */