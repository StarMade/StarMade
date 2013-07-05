/*    */ package com.bulletphysics.linearmath.convexhull;
/*    */ 
/*    */ class Int3
/*    */ {
/*    */   public int x;
/*    */   public int y;
/*    */   public int z;
/*    */ 
/*    */   public Int3()
/*    */   {
/*    */   }
/*    */ 
/*    */   public Int3(int x, int y, int z)
/*    */   {
/* 38 */     this.x = x;
/* 39 */     this.y = y;
/* 40 */     this.z = z;
/*    */   }
/*    */ 
/*    */   public Int3(Int3 i) {
/* 44 */     this.x = i.x;
/* 45 */     this.y = i.y;
/* 46 */     this.z = i.z;
/*    */   }
/*    */ 
/*    */   public void set(int x, int y, int z) {
/* 50 */     this.x = x;
/* 51 */     this.y = y;
/* 52 */     this.z = z;
/*    */   }
/*    */ 
/*    */   public void set(Int3 i) {
/* 56 */     this.x = i.x;
/* 57 */     this.y = i.y;
/* 58 */     this.z = i.z;
/*    */   }
/*    */ 
/*    */   public int getCoord(int coord) {
/* 62 */     switch (coord) { case 0:
/* 63 */       return this.x;
/*    */     case 1:
/* 64 */       return this.y; }
/* 65 */     return this.z;
/*    */   }
/*    */ 
/*    */   public void setCoord(int coord, int value)
/*    */   {
/* 70 */     switch (coord) { case 0:
/* 71 */       this.x = value; break;
/*    */     case 1:
/* 72 */       this.y = value; break;
/*    */     case 2:
/* 73 */       this.z = value; }
/*    */   }
/*    */ 
/*    */   public boolean equals(Int3 i)
/*    */   {
/* 78 */     return (this.x == i.x) && (this.y == i.y) && (this.z == i.z);
/*    */   }
/*    */ 
/*    */   public IntRef getRef(final int coord) {
/* 82 */     return new IntRef()
/*    */     {
/*    */       public int get() {
/* 85 */         return Int3.this.getCoord(coord);
/*    */       }
/*    */ 
/*    */       public void set(int value)
/*    */       {
/* 90 */         Int3.this.setCoord(coord, value);
/*    */       }
/*    */     };
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.Int3
 * JD-Core Version:    0.6.2
 */