/*    */ package com.bulletphysics.util;
/*    */ 
/*    */ public class ObjectStackList<T> extends StackList<T>
/*    */ {
/*    */   private Class<T> cls;
/*    */ 
/*    */   public ObjectStackList(Class<T> cls)
/*    */   {
/* 36 */     super(false);
/* 37 */     this.cls = cls;
/*    */   }
/*    */ 
/*    */   protected T create()
/*    */   {
/*    */     try {
/* 43 */       return this.cls.newInstance();
/*    */     }
/*    */     catch (InstantiationException e) {
/* 46 */       throw new IllegalStateException(e);
/*    */     }
/*    */     catch (IllegalAccessException e) {
/* 49 */       throw new IllegalStateException(e);
/*    */     }
/*    */   }
/*    */ 
/*    */   protected void copy(T dest, T src)
/*    */   {
/* 55 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.util.ObjectStackList
 * JD-Core Version:    0.6.2
 */