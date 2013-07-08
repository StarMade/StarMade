/*  1:   */package it.unimi.dsi.fastutil.ints;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.AbstractStack;
/*  4:   */
/* 50:   */public abstract class AbstractIntStack
/* 51:   */  extends AbstractStack<Integer>
/* 52:   */  implements IntStack
/* 53:   */{
/* 54:   */  public void push(Integer o)
/* 55:   */  {
/* 56:56 */    push(o.intValue());
/* 57:   */  }
/* 58:   */  
/* 59:   */  public Integer pop() {
/* 60:60 */    return Integer.valueOf(popInt());
/* 61:   */  }
/* 62:   */  
/* 63:   */  public Integer top() {
/* 64:64 */    return Integer.valueOf(topInt());
/* 65:   */  }
/* 66:   */  
/* 67:   */  public Integer peek(int i) {
/* 68:68 */    return Integer.valueOf(peekInt(i));
/* 69:   */  }
/* 70:   */  
/* 71:   */  public void push(int k) {
/* 72:72 */    push(Integer.valueOf(k));
/* 73:   */  }
/* 74:   */  
/* 75:   */  public int popInt() {
/* 76:76 */    return pop().intValue();
/* 77:   */  }
/* 78:   */  
/* 79:   */  public int topInt() {
/* 80:80 */    return top().intValue();
/* 81:   */  }
/* 82:   */  
/* 83:   */  public int peekInt(int i) {
/* 84:84 */    return peek(i).intValue();
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */