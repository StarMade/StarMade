/*  1:   */package it.unimi.dsi.fastutil.chars;
/*  2:   */
/* 52:   */public abstract class AbstractCharComparator
/* 53:   */  implements CharComparator
/* 54:   */{
/* 55:   */  public int compare(Character ok1, Character ok2)
/* 56:   */  {
/* 57:57 */    return compare(ok1.charValue(), ok2.charValue());
/* 58:   */  }
/* 59:   */  
/* 60:   */  public abstract int compare(char paramChar1, char paramChar2);
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */