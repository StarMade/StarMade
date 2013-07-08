/*  1:   */package com.bulletphysics.linearmath.convexhull;
/*  2:   */
/*  8:   */class Int3
/*  9:   */{
/* 10:   */  public int x;
/* 11:   */  
/* 16:   */  public int y;
/* 17:   */  
/* 22:   */  public int z;
/* 23:   */  
/* 29:   */  public Int3() {}
/* 30:   */  
/* 36:   */  public Int3(int x, int y, int z)
/* 37:   */  {
/* 38:38 */    this.x = x;
/* 39:39 */    this.y = y;
/* 40:40 */    this.z = z;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public Int3(Int3 i) {
/* 44:44 */    this.x = i.x;
/* 45:45 */    this.y = i.y;
/* 46:46 */    this.z = i.z;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public void set(int x, int y, int z) {
/* 50:50 */    this.x = x;
/* 51:51 */    this.y = y;
/* 52:52 */    this.z = z;
/* 53:   */  }
/* 54:   */  
/* 55:   */  public void set(Int3 i) {
/* 56:56 */    this.x = i.x;
/* 57:57 */    this.y = i.y;
/* 58:58 */    this.z = i.z;
/* 59:   */  }
/* 60:   */  
/* 61:   */  public int getCoord(int coord) {
/* 62:62 */    switch (coord) {
/* 63:63 */    case 0:  return this.x;
/* 64:64 */    case 1:  return this.y; }
/* 65:65 */    return this.z;
/* 66:   */  }
/* 67:   */  
/* 68:   */  public void setCoord(int coord, int value)
/* 69:   */  {
/* 70:70 */    switch (coord) {
/* 71:71 */    case 0:  this.x = value;break;
/* 72:72 */    case 1:  this.y = value;break;
/* 73:73 */    case 2:  this.z = value;
/* 74:   */    }
/* 75:   */  }
/* 76:   */  
/* 77:   */  public boolean equals(Int3 i) {
/* 78:78 */    return (this.x == i.x) && (this.y == i.y) && (this.z == i.z);
/* 79:   */  }
/* 80:   */  
/* 81:   */  public IntRef getRef(final int coord) {
/* 82:82 */    new IntRef()
/* 83:   */    {
/* 84:   */      public int get() {
/* 85:85 */        return Int3.this.getCoord(coord);
/* 86:   */      }
/* 87:   */      
/* 88:   */      public void set(int value)
/* 89:   */      {
/* 90:90 */        Int3.this.setCoord(coord, value);
/* 91:   */      }
/* 92:   */    };
/* 93:   */  }
/* 94:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.Int3
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */