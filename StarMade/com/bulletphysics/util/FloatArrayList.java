/*    */ package com.bulletphysics.util;
/*    */ 
/*    */ public class FloatArrayList
/*    */ {
/* 32 */   private float[] array = new float[16];
/*    */   private int size;
/*    */ 
/*    */   public void add(float value)
/*    */   {
/* 36 */     if (this.size == this.array.length) {
/* 37 */       expand();
/*    */     }
/*    */ 
/* 40 */     this.array[(this.size++)] = value;
/*    */   }
/*    */ 
/*    */   private void expand() {
/* 44 */     float[] newArray = new float[this.array.length << 1];
/* 45 */     System.arraycopy(this.array, 0, newArray, 0, this.array.length);
/* 46 */     this.array = newArray;
/*    */   }
/*    */ 
/*    */   public float remove(int index) {
/* 50 */     if (index >= this.size) throw new IndexOutOfBoundsException();
/* 51 */     float old = this.array[index];
/* 52 */     System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
/* 53 */     this.size -= 1;
/* 54 */     return old;
/*    */   }
/*    */ 
/*    */   public float get(int index) {
/* 58 */     if (index >= this.size) throw new IndexOutOfBoundsException();
/* 59 */     return this.array[index];
/*    */   }
/*    */ 
/*    */   public void set(int index, float value) {
/* 63 */     if (index >= this.size) throw new IndexOutOfBoundsException();
/* 64 */     this.array[index] = value;
/*    */   }
/*    */ 
/*    */   public int size() {
/* 68 */     return this.size;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.util.FloatArrayList
 * JD-Core Version:    0.6.2
 */