/*    */ package org.dom4j.util;
/*    */ 
/*    */ public class SimpleSingleton
/*    */   implements SingletonStrategy
/*    */ {
/* 23 */   private String singletonClassName = null;
/*    */ 
/* 25 */   private Object singletonInstance = null;
/*    */ 
/*    */   public Object instance()
/*    */   {
/* 31 */     return this.singletonInstance;
/*    */   }
/*    */ 
/*    */   public void reset() {
/* 35 */     if (this.singletonClassName != null) {
/* 36 */       Class clazz = null;
/*    */       try {
/* 38 */         clazz = Thread.currentThread().getContextClassLoader().loadClass(this.singletonClassName);
/*    */ 
/* 40 */         this.singletonInstance = clazz.newInstance();
/*    */       } catch (Exception ignore) {
/*    */         try {
/* 43 */           clazz = Class.forName(this.singletonClassName);
/* 44 */           this.singletonInstance = clazz.newInstance();
/*    */         }
/*    */         catch (Exception ignore2) {
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public void setSingletonClassName(String singletonClassName) {
/* 53 */     this.singletonClassName = singletonClassName;
/* 54 */     reset();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.SimpleSingleton
 * JD-Core Version:    0.6.2
 */