/*    */ package it.unimi.dsi.fastutil.objects;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public abstract class AbstractReference2ObjectFunction<K, V>
/*    */   implements Reference2ObjectFunction<K, V>, Serializable
/*    */ {
/*    */   public static final long serialVersionUID = -4940583368468432370L;
/*    */   protected V defRetValue;
/*    */ 
/*    */   public void defaultReturnValue(V rv)
/*    */   {
/* 70 */     this.defRetValue = rv;
/*    */   }
/*    */   public V defaultReturnValue() {
/* 73 */     return this.defRetValue;
/*    */   }
/*    */   public V put(K key, V value) {
/* 76 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   public V remove(Object key) {
/* 79 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   public void clear() {
/* 82 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2ObjectFunction
 * JD-Core Version:    0.6.2
 */