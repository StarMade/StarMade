/*  1:   */package it.unimi.dsi.fastutil.doubles;
/*  2:   */
/* 52:   */public abstract class AbstractDoubleComparator
/* 53:   */  implements DoubleComparator
/* 54:   */{
/* 55:   */  public int compare(Double ok1, Double ok2)
/* 56:   */  {
/* 57:57 */    return compare(ok1.doubleValue(), ok2.doubleValue());
/* 58:   */  }
/* 59:   */  
/* 60:   */  public abstract int compare(double paramDouble1, double paramDouble2);
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */