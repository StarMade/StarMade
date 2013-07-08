/*  1:   */package it.unimi.dsi.fastutil.chars;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*  4:   */
/* 47:   */public abstract class AbstractCharPriorityQueue
/* 48:   */  extends AbstractPriorityQueue<Character>
/* 49:   */  implements CharPriorityQueue
/* 50:   */{
/* 51:51 */  public void enqueue(Character x) { enqueue(x.charValue()); }
/* 52:   */  
/* 53:53 */  public Character dequeue() { return Character.valueOf(dequeueChar()); }
/* 54:   */  
/* 55:55 */  public Character first() { return Character.valueOf(firstChar()); }
/* 56:   */  
/* 57:57 */  public Character last() { return Character.valueOf(lastChar()); }
/* 58:   */  
/* 59:59 */  public char lastChar() { throw new UnsupportedOperationException(); }
/* 60:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */