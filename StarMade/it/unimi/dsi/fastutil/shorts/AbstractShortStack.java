/*  1:   */package it.unimi.dsi.fastutil.shorts;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.AbstractStack;
/*  4:   */
/* 50:   */public abstract class AbstractShortStack
/* 51:   */  extends AbstractStack<Short>
/* 52:   */  implements ShortStack
/* 53:   */{
/* 54:   */  public void push(Short o)
/* 55:   */  {
/* 56:56 */    push(o.shortValue());
/* 57:   */  }
/* 58:   */  
/* 59:   */  public Short pop() {
/* 60:60 */    return Short.valueOf(popShort());
/* 61:   */  }
/* 62:   */  
/* 63:   */  public Short top() {
/* 64:64 */    return Short.valueOf(topShort());
/* 65:   */  }
/* 66:   */  
/* 67:   */  public Short peek(int i) {
/* 68:68 */    return Short.valueOf(peekShort(i));
/* 69:   */  }
/* 70:   */  
/* 71:   */  public void push(short k) {
/* 72:72 */    push(Short.valueOf(k));
/* 73:   */  }
/* 74:   */  
/* 75:   */  public short popShort() {
/* 76:76 */    return pop().shortValue();
/* 77:   */  }
/* 78:   */  
/* 79:   */  public short topShort() {
/* 80:80 */    return top().shortValue();
/* 81:   */  }
/* 82:   */  
/* 83:   */  public short peekShort(int i) {
/* 84:84 */    return peek(i).shortValue();
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */