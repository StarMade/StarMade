/*  1:   */package com.bulletphysics.linearmath.convexhull;
/*  2:   */
/* 29:   */class Tri
/* 30:   */  extends Int3
/* 31:   */{
/* 32:32 */  public Int3 n = new Int3();
/* 33:   */  public int id;
/* 34:   */  public int vmax;
/* 35:   */  public float rise;
/* 36:   */  
/* 37:   */  public Tri(int a, int b, int c) {
/* 38:38 */    super(a, b, c);
/* 39:39 */    this.n.set(-1, -1, -1);
/* 40:40 */    this.vmax = -1;
/* 41:41 */    this.rise = 0.0F;
/* 42:   */  }
/* 43:   */  
/* 44:44 */  private static int er = -1;
/* 45:   */  
/* 46:46 */  private static IntRef erRef = new IntRef()
/* 47:   */  {
/* 48:   */    public int get() {
/* 49:49 */      return Tri.er;
/* 50:   */    }
/* 51:   */    
/* 52:   */    public void set(int value)
/* 53:   */    {
/* 54:54 */      Tri.access$002(value);
/* 55:   */    }
/* 56:   */  };
/* 57:   */  
/* 58:   */  public IntRef neib(int a, int b) {
/* 59:59 */    for (int i = 0; i < 3; i++) {
/* 60:60 */      int i1 = (i + 1) % 3;
/* 61:61 */      int i2 = (i + 2) % 3;
/* 62:   */      
/* 63:63 */      if ((getCoord(i) == a) && (getCoord(i1) == b)) {
/* 64:64 */        return this.n.getRef(i2);
/* 65:   */      }
/* 66:66 */      if ((getCoord(i) == b) && (getCoord(i1) == a)) {
/* 67:67 */        return this.n.getRef(i2);
/* 68:   */      }
/* 69:   */    }
/* 70:70 */    if (!$assertionsDisabled) throw new AssertionError();
/* 71:71 */    return erRef;
/* 72:   */  }
/* 73:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.Tri
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */