/*  1:   */package com.bulletphysics.util;
/*  2:   */
/* 30:   */public class IntArrayList
/* 31:   */{
/* 32:32 */  private int[] array = new int[16];
/* 33:   */  private int size;
/* 34:   */  
/* 35:   */  public void add(int value) {
/* 36:36 */    if (this.size == this.array.length) {
/* 37:37 */      expand();
/* 38:   */    }
/* 39:   */    
/* 40:40 */    this.array[(this.size++)] = value;
/* 41:   */  }
/* 42:   */  
/* 43:   */  private void expand() {
/* 44:44 */    int[] newArray = new int[this.array.length << 1];
/* 45:45 */    System.arraycopy(this.array, 0, newArray, 0, this.array.length);
/* 46:46 */    this.array = newArray;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public int remove(int index) {
/* 50:50 */    if (index >= this.size) throw new IndexOutOfBoundsException();
/* 51:51 */    int old = this.array[index];
/* 52:52 */    System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
/* 53:53 */    this.size -= 1;
/* 54:54 */    return old;
/* 55:   */  }
/* 56:   */  
/* 57:   */  public int get(int index) {
/* 58:58 */    if (index >= this.size) throw new IndexOutOfBoundsException();
/* 59:59 */    return this.array[index];
/* 60:   */  }
/* 61:   */  
/* 62:   */  public void set(int index, int value) {
/* 63:63 */    if (index >= this.size) throw new IndexOutOfBoundsException();
/* 64:64 */    this.array[index] = value;
/* 65:   */  }
/* 66:   */  
/* 67:   */  public int size() {
/* 68:68 */    return this.size;
/* 69:   */  }
/* 70:   */  
/* 71:   */  public void clear() {
/* 72:72 */    this.size = 0;
/* 73:   */  }
/* 74:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.util.IntArrayList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */