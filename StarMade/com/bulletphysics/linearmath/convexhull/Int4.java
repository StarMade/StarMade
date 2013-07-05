/*    */ package com.bulletphysics.linearmath.convexhull;
/*    */ 
/*    */ class Int4
/*    */ {
/*    */   public int x;
/*    */   public int y;
/*    */   public int z;
/*    */   public int w;
/*    */ 
/*    */   public Int4()
/*    */   {
/*    */   }
/*    */ 
/*    */   public Int4(int x, int y, int z, int w)
/*    */   {
/* 38 */     this.x = x;
/* 39 */     this.y = y;
/* 40 */     this.z = z;
/* 41 */     this.w = w;
/*    */   }
/*    */ 
/*    */   public void set(int x, int y, int z, int w) {
/* 45 */     this.x = x;
/* 46 */     this.y = y;
/* 47 */     this.z = z;
/* 48 */     this.w = w;
/*    */   }
/*    */ 
/*    */   public int getCoord(int coord) {
/* 52 */     switch (coord) { case 0:
/* 53 */       return this.x;
/*    */     case 1:
/* 54 */       return this.y;
/*    */     case 2:
/* 55 */       return this.z; }
/* 56 */     return this.w;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.Int4
 * JD-Core Version:    0.6.2
 */