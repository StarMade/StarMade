/*    */ package com.bulletphysics.collision.broadphase;
/*    */ 
/*    */ public enum DispatchFunc
/*    */ {
/* 32 */   DISPATCH_DISCRETE(1), 
/* 33 */   DISPATCH_CONTINUOUS(2);
/*    */ 
/*    */   private int value;
/*    */ 
/*    */   private DispatchFunc(int value) {
/* 38 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public int getValue() {
/* 42 */     return this.value;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.DispatchFunc
 * JD-Core Version:    0.6.2
 */