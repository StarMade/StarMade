/*    */ package com.bulletphysics.linearmath.convexhull;
/*    */ 
/*    */ class Tri extends Int3
/*    */ {
/* 32 */   public Int3 n = new Int3();
/*    */   public int id;
/*    */   public int vmax;
/*    */   public float rise;
/* 44 */   private static int er = -1;
/*    */ 
/* 46 */   private static IntRef erRef = new IntRef()
/*    */   {
/*    */     public int get() {
/* 49 */       return Tri.er;
/*    */     }
/*    */ 
/*    */     public void set(int value)
/*    */     {
/* 54 */       Tri.access$002(value);
/*    */     }
/* 46 */   };
/*    */ 
/*    */   public Tri(int a, int b, int c)
/*    */   {
/* 38 */     super(a, b, c);
/* 39 */     this.n.set(-1, -1, -1);
/* 40 */     this.vmax = -1;
/* 41 */     this.rise = 0.0F;
/*    */   }
/*    */ 
/*    */   public IntRef neib(int a, int b)
/*    */   {
/* 59 */     for (int i = 0; i < 3; i++) {
/* 60 */       int i1 = (i + 1) % 3;
/* 61 */       int i2 = (i + 2) % 3;
/*    */ 
/* 63 */       if ((getCoord(i) == a) && (getCoord(i1) == b)) {
/* 64 */         return this.n.getRef(i2);
/*    */       }
/* 66 */       if ((getCoord(i) == b) && (getCoord(i1) == a)) {
/* 67 */         return this.n.getRef(i2);
/*    */       }
/*    */     }
/* 70 */     if (!$assertionsDisabled) throw new AssertionError();
/* 71 */     return erRef;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.Tri
 * JD-Core Version:    0.6.2
 */