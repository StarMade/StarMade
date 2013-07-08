/*  1:   */package it.unimi.dsi.fastutil.shorts;
/*  2:   */
/* 52:   */public abstract class AbstractShortComparator
/* 53:   */  implements ShortComparator
/* 54:   */{
/* 55:   */  public int compare(Short ok1, Short ok2)
/* 56:   */  {
/* 57:57 */    return compare(ok1.shortValue(), ok2.shortValue());
/* 58:   */  }
/* 59:   */  
/* 60:   */  public abstract int compare(short paramShort1, short paramShort2);
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */