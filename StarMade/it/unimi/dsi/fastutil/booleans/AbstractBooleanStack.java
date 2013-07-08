/*  1:   */package it.unimi.dsi.fastutil.booleans;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.AbstractStack;
/*  4:   */
/* 50:   */public abstract class AbstractBooleanStack
/* 51:   */  extends AbstractStack<Boolean>
/* 52:   */  implements BooleanStack
/* 53:   */{
/* 54:   */  public void push(Boolean o)
/* 55:   */  {
/* 56:56 */    push(o.booleanValue());
/* 57:   */  }
/* 58:   */  
/* 59:   */  public Boolean pop() {
/* 60:60 */    return Boolean.valueOf(popBoolean());
/* 61:   */  }
/* 62:   */  
/* 63:   */  public Boolean top() {
/* 64:64 */    return Boolean.valueOf(topBoolean());
/* 65:   */  }
/* 66:   */  
/* 67:   */  public Boolean peek(int i) {
/* 68:68 */    return Boolean.valueOf(peekBoolean(i));
/* 69:   */  }
/* 70:   */  
/* 71:   */  public void push(boolean k) {
/* 72:72 */    push(Boolean.valueOf(k));
/* 73:   */  }
/* 74:   */  
/* 75:   */  public boolean popBoolean() {
/* 76:76 */    return pop().booleanValue();
/* 77:   */  }
/* 78:   */  
/* 79:   */  public boolean topBoolean() {
/* 80:80 */    return top().booleanValue();
/* 81:   */  }
/* 82:   */  
/* 83:   */  public boolean peekBoolean(int i) {
/* 84:84 */    return peek(i).booleanValue();
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */