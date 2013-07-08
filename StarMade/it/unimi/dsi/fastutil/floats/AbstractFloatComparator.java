/*  1:   */package it.unimi.dsi.fastutil.floats;
/*  2:   */
/* 52:   */public abstract class AbstractFloatComparator
/* 53:   */  implements FloatComparator
/* 54:   */{
/* 55:   */  public int compare(Float ok1, Float ok2)
/* 56:   */  {
/* 57:57 */    return compare(ok1.floatValue(), ok2.floatValue());
/* 58:   */  }
/* 59:   */  
/* 60:   */  public abstract int compare(float paramFloat1, float paramFloat2);
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */