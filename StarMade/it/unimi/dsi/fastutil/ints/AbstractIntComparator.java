/*  1:   */package it.unimi.dsi.fastutil.ints;
/*  2:   */
/* 52:   */public abstract class AbstractIntComparator
/* 53:   */  implements IntComparator
/* 54:   */{
/* 55:   */  public int compare(Integer ok1, Integer ok2)
/* 56:   */  {
/* 57:57 */    return compare(ok1.intValue(), ok2.intValue());
/* 58:   */  }
/* 59:   */  
/* 60:   */  public abstract int compare(int paramInt1, int paramInt2);
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */