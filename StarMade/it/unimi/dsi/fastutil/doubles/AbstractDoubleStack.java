/*  1:   */package it.unimi.dsi.fastutil.doubles;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.AbstractStack;
/*  4:   */
/* 50:   */public abstract class AbstractDoubleStack
/* 51:   */  extends AbstractStack<Double>
/* 52:   */  implements DoubleStack
/* 53:   */{
/* 54:   */  public void push(Double o)
/* 55:   */  {
/* 56:56 */    push(o.doubleValue());
/* 57:   */  }
/* 58:   */  
/* 59:   */  public Double pop() {
/* 60:60 */    return Double.valueOf(popDouble());
/* 61:   */  }
/* 62:   */  
/* 63:   */  public Double top() {
/* 64:64 */    return Double.valueOf(topDouble());
/* 65:   */  }
/* 66:   */  
/* 67:   */  public Double peek(int i) {
/* 68:68 */    return Double.valueOf(peekDouble(i));
/* 69:   */  }
/* 70:   */  
/* 71:   */  public void push(double k) {
/* 72:72 */    push(Double.valueOf(k));
/* 73:   */  }
/* 74:   */  
/* 75:   */  public double popDouble() {
/* 76:76 */    return pop().doubleValue();
/* 77:   */  }
/* 78:   */  
/* 79:   */  public double topDouble() {
/* 80:80 */    return top().doubleValue();
/* 81:   */  }
/* 82:   */  
/* 83:   */  public double peekDouble(int i) {
/* 84:84 */    return peek(i).doubleValue();
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */