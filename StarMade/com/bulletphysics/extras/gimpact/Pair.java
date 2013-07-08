/*  1:   */package com.bulletphysics.extras.gimpact;
/*  2:   */
/* 11:   */class Pair
/* 12:   */{
/* 13:   */  public int index1;
/* 14:   */  
/* 22:   */  public int index2;
/* 23:   */  
/* 32:   */  public Pair() {}
/* 33:   */  
/* 42:   */  public Pair(int index1, int index2)
/* 43:   */  {
/* 44:44 */    this.index1 = index1;
/* 45:45 */    this.index2 = index2;
/* 46:   */  }
/* 47:   */  
/* 48:   */  public Pair(Pair p) {
/* 49:49 */    this.index1 = p.index1;
/* 50:50 */    this.index2 = p.index2;
/* 51:   */  }
/* 52:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.Pair
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */