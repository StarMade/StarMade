/*  1:   */package com.bulletphysics.linearmath.convexhull;
/*  2:   */
/*  7:   */class Int4
/*  8:   */{
/*  9:   */  public int x;
/* 10:   */  
/* 14:   */  public int y;
/* 15:   */  
/* 19:   */  public int z;
/* 20:   */  
/* 24:   */  public int w;
/* 25:   */  
/* 30:   */  public Int4() {}
/* 31:   */  
/* 36:   */  public Int4(int x, int y, int z, int w)
/* 37:   */  {
/* 38:38 */    this.x = x;
/* 39:39 */    this.y = y;
/* 40:40 */    this.z = z;
/* 41:41 */    this.w = w;
/* 42:   */  }
/* 43:   */  
/* 44:   */  public void set(int x, int y, int z, int w) {
/* 45:45 */    this.x = x;
/* 46:46 */    this.y = y;
/* 47:47 */    this.z = z;
/* 48:48 */    this.w = w;
/* 49:   */  }
/* 50:   */  
/* 51:   */  public int getCoord(int coord) {
/* 52:52 */    switch (coord) {
/* 53:53 */    case 0:  return this.x;
/* 54:54 */    case 1:  return this.y;
/* 55:55 */    case 2:  return this.z; }
/* 56:56 */    return this.w;
/* 57:   */  }
/* 58:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.Int4
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */