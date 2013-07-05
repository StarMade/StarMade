/*    */ package com.google.code.tempusfugit;
/*    */ 
/*    */ public final class WithException<E extends Exception>
/*    */ {
/*    */   private final Class<E> type;
/*    */ 
/*    */   public static <E extends Exception> WithException<E> with(Class<E> type)
/*    */   {
/* 24 */     return new WithException(type);
/*    */   }
/*    */ 
/*    */   private WithException(Class<E> type) {
/* 28 */     this.type = type;
/*    */   }
/*    */ 
/*    */   public Class<E> getType() {
/* 32 */     return this.type;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.WithException
 * JD-Core Version:    0.6.2
 */