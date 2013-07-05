/*    */ package com.bulletphysics.util;
/*    */ 
/*    */ public class IntArrayList
/*    */ {
/* 32 */   private int[] array = new int[16];
/*    */   private int size;
/*    */ 
/*    */   public void add(int value)
/*    */   {
/* 36 */     if (this.size == this.array.length) {
/* 37 */       expand();
/*    */     }
/*    */ 
/* 40 */     this.array[(this.size++)] = value;
/*    */   }
/*    */ 
/*    */   private void expand() {
/* 44 */     int[] newArray = new int[this.array.length << 1];
/* 45 */     System.arraycopy(this.array, 0, newArray, 0, this.array.length);
/* 46 */     this.array = newArray;
/*    */   }
/*    */ 
/*    */   public int remove(int index) {
/* 50 */     if (index >= this.size) throw new IndexOutOfBoundsException();
/* 51 */     int old = this.array[index];
/* 52 */     System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
/* 53 */     this.size -= 1;
/* 54 */     return old;
/*    */   }
/*    */ 
/*    */   public int get(int index) {
/* 58 */     if (index >= this.size) throw new IndexOutOfBoundsException();
/* 59 */     return this.array[index];
/*    */   }
/*    */ 
/*    */   public void set(int index, int value) {
/* 63 */     if (index >= this.size) throw new IndexOutOfBoundsException();
/* 64 */     this.array[index] = value;
/*    */   }
/*    */ 
/*    */   public int size() {
/* 68 */     return this.size;
/*    */   }
/*    */ 
/*    */   public void clear() {
/* 72 */     this.size = 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.util.IntArrayList
 * JD-Core Version:    0.6.2
 */