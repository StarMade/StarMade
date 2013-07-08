/*   1:    */package org.apache.commons.lang3.tuple;
/*   2:    */
/*  15:    */public final class ImmutablePair<L, R>
/*  16:    */  extends Pair<L, R>
/*  17:    */{
/*  18:    */  private static final long serialVersionUID = 4954918890077093841L;
/*  19:    */  
/*  31:    */  public final L left;
/*  32:    */  
/*  43:    */  public final R right;
/*  44:    */  
/*  56:    */  public static <L, R> ImmutablePair<L, R> of(L left, R right)
/*  57:    */  {
/*  58: 58 */    return new ImmutablePair(left, right);
/*  59:    */  }
/*  60:    */  
/*  67:    */  public ImmutablePair(L left, R right)
/*  68:    */  {
/*  69: 69 */    this.left = left;
/*  70: 70 */    this.right = right;
/*  71:    */  }
/*  72:    */  
/*  77:    */  public L getLeft()
/*  78:    */  {
/*  79: 79 */    return this.left;
/*  80:    */  }
/*  81:    */  
/*  85:    */  public R getRight()
/*  86:    */  {
/*  87: 87 */    return this.right;
/*  88:    */  }
/*  89:    */  
/*  98:    */  public R setValue(R value)
/*  99:    */  {
/* 100:100 */    throw new UnsupportedOperationException();
/* 101:    */  }
/* 102:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.tuple.ImmutablePair
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */