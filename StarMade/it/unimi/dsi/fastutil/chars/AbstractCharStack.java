/*  1:   */package it.unimi.dsi.fastutil.chars;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.AbstractStack;
/*  4:   */
/* 50:   */public abstract class AbstractCharStack
/* 51:   */  extends AbstractStack<Character>
/* 52:   */  implements CharStack
/* 53:   */{
/* 54:   */  public void push(Character o)
/* 55:   */  {
/* 56:56 */    push(o.charValue());
/* 57:   */  }
/* 58:   */  
/* 59:   */  public Character pop() {
/* 60:60 */    return Character.valueOf(popChar());
/* 61:   */  }
/* 62:   */  
/* 63:   */  public Character top() {
/* 64:64 */    return Character.valueOf(topChar());
/* 65:   */  }
/* 66:   */  
/* 67:   */  public Character peek(int i) {
/* 68:68 */    return Character.valueOf(peekChar(i));
/* 69:   */  }
/* 70:   */  
/* 71:   */  public void push(char k) {
/* 72:72 */    push(Character.valueOf(k));
/* 73:   */  }
/* 74:   */  
/* 75:   */  public char popChar() {
/* 76:76 */    return pop().charValue();
/* 77:   */  }
/* 78:   */  
/* 79:   */  public char topChar() {
/* 80:80 */    return top().charValue();
/* 81:   */  }
/* 82:   */  
/* 83:   */  public char peekChar(int i) {
/* 84:84 */    return peek(i).charValue();
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */