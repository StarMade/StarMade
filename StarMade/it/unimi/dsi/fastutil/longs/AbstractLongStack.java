/*  1:   */package it.unimi.dsi.fastutil.longs;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.AbstractStack;
/*  4:   */
/* 50:   */public abstract class AbstractLongStack
/* 51:   */  extends AbstractStack<Long>
/* 52:   */  implements LongStack
/* 53:   */{
/* 54:   */  public void push(Long o)
/* 55:   */  {
/* 56:56 */    push(o.longValue());
/* 57:   */  }
/* 58:   */  
/* 59:   */  public Long pop() {
/* 60:60 */    return Long.valueOf(popLong());
/* 61:   */  }
/* 62:   */  
/* 63:   */  public Long top() {
/* 64:64 */    return Long.valueOf(topLong());
/* 65:   */  }
/* 66:   */  
/* 67:   */  public Long peek(int i) {
/* 68:68 */    return Long.valueOf(peekLong(i));
/* 69:   */  }
/* 70:   */  
/* 71:   */  public void push(long k) {
/* 72:72 */    push(Long.valueOf(k));
/* 73:   */  }
/* 74:   */  
/* 75:   */  public long popLong() {
/* 76:76 */    return pop().longValue();
/* 77:   */  }
/* 78:   */  
/* 79:   */  public long topLong() {
/* 80:80 */    return top().longValue();
/* 81:   */  }
/* 82:   */  
/* 83:   */  public long peekLong(int i) {
/* 84:84 */    return peek(i).longValue();
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */