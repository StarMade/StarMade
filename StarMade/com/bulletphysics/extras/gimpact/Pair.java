/*    */ package com.bulletphysics.extras.gimpact;
/*    */ 
/*    */ class Pair
/*    */ {
/*    */   public int index1;
/*    */   public int index2;
/*    */ 
/*    */   public Pair()
/*    */   {
/*    */   }
/*    */ 
/*    */   public Pair(int index1, int index2)
/*    */   {
/* 44 */     this.index1 = index1;
/* 45 */     this.index2 = index2;
/*    */   }
/*    */ 
/*    */   public Pair(Pair p) {
/* 49 */     this.index1 = p.index1;
/* 50 */     this.index2 = p.index2;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.Pair
 * JD-Core Version:    0.6.2
 */