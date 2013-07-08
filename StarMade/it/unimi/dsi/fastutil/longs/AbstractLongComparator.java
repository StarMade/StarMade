/*  1:   */package it.unimi.dsi.fastutil.longs;
/*  2:   */
/* 52:   */public abstract class AbstractLongComparator
/* 53:   */  implements LongComparator
/* 54:   */{
/* 55:   */  public int compare(Long ok1, Long ok2)
/* 56:   */  {
/* 57:57 */    return compare(ok1.longValue(), ok2.longValue());
/* 58:   */  }
/* 59:   */  
/* 60:   */  public abstract int compare(long paramLong1, long paramLong2);
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */