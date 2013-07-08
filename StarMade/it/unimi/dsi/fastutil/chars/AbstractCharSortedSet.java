/*  1:   */package it.unimi.dsi.fastutil.chars;
/*  2:   */
/* 44:   */public abstract class AbstractCharSortedSet
/* 45:   */  extends AbstractCharSet
/* 46:   */  implements CharSortedSet
/* 47:   */{
/* 48:   */  public CharSortedSet headSet(Character to)
/* 49:   */  {
/* 50:50 */    return headSet(to.charValue());
/* 51:   */  }
/* 52:   */  
/* 53:   */  public CharSortedSet tailSet(Character from) {
/* 54:54 */    return tailSet(from.charValue());
/* 55:   */  }
/* 56:   */  
/* 57:   */  public CharSortedSet subSet(Character from, Character to) {
/* 58:58 */    return subSet(from.charValue(), to.charValue());
/* 59:   */  }
/* 60:   */  
/* 61:   */  public Character first() {
/* 62:62 */    return Character.valueOf(firstChar());
/* 63:   */  }
/* 64:   */  
/* 65:   */  public Character last() {
/* 66:66 */    return Character.valueOf(lastChar());
/* 67:   */  }
/* 68:   */  
/* 69:   */  @Deprecated
/* 70:   */  public CharBidirectionalIterator charIterator() {
/* 71:71 */    return iterator();
/* 72:   */  }
/* 73:   */  
/* 74:   */  public abstract CharBidirectionalIterator iterator();
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */