/*   1:    */package org.apache.commons.lang3.tuple;
/*   2:    */
/*  14:    */public class MutablePair<L, R>
/*  15:    */  extends Pair<L, R>
/*  16:    */{
/*  17:    */  private static final long serialVersionUID = 4954918890077093841L;
/*  18:    */  
/*  28:    */  public L left;
/*  29:    */  
/*  39:    */  public R right;
/*  40:    */  
/*  51:    */  public static <L, R> MutablePair<L, R> of(L left, R right)
/*  52:    */  {
/*  53: 53 */    return new MutablePair(left, right);
/*  54:    */  }
/*  55:    */  
/*  62:    */  public MutablePair() {}
/*  63:    */  
/*  69:    */  public MutablePair(L left, R right)
/*  70:    */  {
/*  71: 71 */    this.left = left;
/*  72: 72 */    this.right = right;
/*  73:    */  }
/*  74:    */  
/*  79:    */  public L getLeft()
/*  80:    */  {
/*  81: 81 */    return this.left;
/*  82:    */  }
/*  83:    */  
/*  88:    */  public void setLeft(L left)
/*  89:    */  {
/*  90: 90 */    this.left = left;
/*  91:    */  }
/*  92:    */  
/*  96:    */  public R getRight()
/*  97:    */  {
/*  98: 98 */    return this.right;
/*  99:    */  }
/* 100:    */  
/* 105:    */  public void setRight(R right)
/* 106:    */  {
/* 107:107 */    this.right = right;
/* 108:    */  }
/* 109:    */  
/* 116:    */  public R setValue(R value)
/* 117:    */  {
/* 118:118 */    R result = getRight();
/* 119:119 */    setRight(value);
/* 120:120 */    return result;
/* 121:    */  }
/* 122:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.tuple.MutablePair
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */