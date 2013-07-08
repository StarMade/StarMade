/*  1:   */package com.bulletphysics.extras.gimpact;
/*  2:   */
/* 19:   */class PairSet
/* 20:   */{
/* 21:   */  private Pair[] array;
/* 22:   */  
/* 37:37 */  private int size = 0;
/* 38:   */  
/* 39:   */  public PairSet() {
/* 40:40 */    this.array = new Pair[32];
/* 41:41 */    for (int i = 0; i < this.array.length; i++) {
/* 42:42 */      this.array[i] = new Pair();
/* 43:   */    }
/* 44:   */  }
/* 45:   */  
/* 46:   */  public void clear() {
/* 47:47 */    this.size = 0;
/* 48:   */  }
/* 49:   */  
/* 50:   */  public int size() {
/* 51:51 */    return this.size;
/* 52:   */  }
/* 53:   */  
/* 54:   */  public Pair get(int index) {
/* 55:55 */    if (index >= this.size) throw new IndexOutOfBoundsException();
/* 56:56 */    return this.array[index];
/* 57:   */  }
/* 58:   */  
/* 59:   */  private void expand()
/* 60:   */  {
/* 61:61 */    Pair[] newArray = new Pair[this.array.length << 1];
/* 62:62 */    for (int i = this.array.length; i < newArray.length; i++) {
/* 63:63 */      newArray[i] = new Pair();
/* 64:   */    }
/* 65:65 */    System.arraycopy(this.array, 0, newArray, 0, this.array.length);
/* 66:66 */    this.array = newArray;
/* 67:   */  }
/* 68:   */  
/* 69:   */  public void push_pair(int index1, int index2) {
/* 70:70 */    if (this.size == this.array.length) {
/* 71:71 */      expand();
/* 72:   */    }
/* 73:73 */    this.array[this.size].index1 = index1;
/* 74:74 */    this.array[this.size].index2 = index2;
/* 75:75 */    this.size += 1;
/* 76:   */  }
/* 77:   */  
/* 78:   */  public void push_pair_inv(int index1, int index2) {
/* 79:79 */    if (this.size == this.array.length) {
/* 80:80 */      expand();
/* 81:   */    }
/* 82:82 */    this.array[this.size].index1 = index2;
/* 83:83 */    this.array[this.size].index2 = index1;
/* 84:84 */    this.size += 1;
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.PairSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */