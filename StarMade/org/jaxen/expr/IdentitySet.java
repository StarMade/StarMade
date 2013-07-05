/*    */ package org.jaxen.expr;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ 
/*    */ final class IdentitySet
/*    */ {
/* 49 */   private HashSet contents = new HashSet();
/*    */ 
/*    */   void add(Object object)
/*    */   {
/* 56 */     IdentityWrapper wrapper = new IdentityWrapper(object);
/* 57 */     this.contents.add(wrapper);
/*    */   }
/*    */ 
/*    */   public boolean contains(Object object) {
/* 61 */     IdentityWrapper wrapper = new IdentityWrapper(object);
/* 62 */     return this.contents.contains(wrapper);
/*    */   }
/*    */ 
/*    */   private static class IdentityWrapper
/*    */   {
/*    */     private Object object;
/*    */ 
/*    */     IdentityWrapper(Object object) {
/* 70 */       this.object = object;
/*    */     }
/*    */ 
/*    */     public boolean equals(Object o) {
/* 74 */       IdentityWrapper w = (IdentityWrapper)o;
/* 75 */       return this.object == w.object;
/*    */     }
/*    */ 
/*    */     public int hashCode() {
/* 79 */       return System.identityHashCode(this.object);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.IdentitySet
 * JD-Core Version:    0.6.2
 */