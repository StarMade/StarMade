/*  1:   */package it.unimi.dsi.fastutil.floats;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.AbstractStack;
/*  4:   */
/* 50:   */public abstract class AbstractFloatStack
/* 51:   */  extends AbstractStack<Float>
/* 52:   */  implements FloatStack
/* 53:   */{
/* 54:   */  public void push(Float o)
/* 55:   */  {
/* 56:56 */    push(o.floatValue());
/* 57:   */  }
/* 58:   */  
/* 59:   */  public Float pop() {
/* 60:60 */    return Float.valueOf(popFloat());
/* 61:   */  }
/* 62:   */  
/* 63:   */  public Float top() {
/* 64:64 */    return Float.valueOf(topFloat());
/* 65:   */  }
/* 66:   */  
/* 67:   */  public Float peek(int i) {
/* 68:68 */    return Float.valueOf(peekFloat(i));
/* 69:   */  }
/* 70:   */  
/* 71:   */  public void push(float k) {
/* 72:72 */    push(Float.valueOf(k));
/* 73:   */  }
/* 74:   */  
/* 75:   */  public float popFloat() {
/* 76:76 */    return pop().floatValue();
/* 77:   */  }
/* 78:   */  
/* 79:   */  public float topFloat() {
/* 80:80 */    return top().floatValue();
/* 81:   */  }
/* 82:   */  
/* 83:   */  public float peekFloat(int i) {
/* 84:84 */    return peek(i).floatValue();
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */