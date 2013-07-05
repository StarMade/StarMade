/*    */ package com.bulletphysics.extras.gimpact;
/*    */ 
/*    */ class PairSet
/*    */ {
/*    */   private Pair[] array;
/* 37 */   private int size = 0;
/*    */ 
/*    */   public PairSet() {
/* 40 */     this.array = new Pair[32];
/* 41 */     for (int i = 0; i < this.array.length; i++)
/* 42 */       this.array[i] = new Pair();
/*    */   }
/*    */ 
/*    */   public void clear()
/*    */   {
/* 47 */     this.size = 0;
/*    */   }
/*    */ 
/*    */   public int size() {
/* 51 */     return this.size;
/*    */   }
/*    */ 
/*    */   public Pair get(int index) {
/* 55 */     if (index >= this.size) throw new IndexOutOfBoundsException();
/* 56 */     return this.array[index];
/*    */   }
/*    */ 
/*    */   private void expand()
/*    */   {
/* 61 */     Pair[] newArray = new Pair[this.array.length << 1];
/* 62 */     for (int i = this.array.length; i < newArray.length; i++) {
/* 63 */       newArray[i] = new Pair();
/*    */     }
/* 65 */     System.arraycopy(this.array, 0, newArray, 0, this.array.length);
/* 66 */     this.array = newArray;
/*    */   }
/*    */ 
/*    */   public void push_pair(int index1, int index2) {
/* 70 */     if (this.size == this.array.length) {
/* 71 */       expand();
/*    */     }
/* 73 */     this.array[this.size].index1 = index1;
/* 74 */     this.array[this.size].index2 = index2;
/* 75 */     this.size += 1;
/*    */   }
/*    */ 
/*    */   public void push_pair_inv(int index1, int index2) {
/* 79 */     if (this.size == this.array.length) {
/* 80 */       expand();
/*    */     }
/* 82 */     this.array[this.size].index1 = index2;
/* 83 */     this.array[this.size].index2 = index1;
/* 84 */     this.size += 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.PairSet
 * JD-Core Version:    0.6.2
 */