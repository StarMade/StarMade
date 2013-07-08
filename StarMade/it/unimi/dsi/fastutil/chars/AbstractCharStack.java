package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.AbstractStack;

public abstract class AbstractCharStack
  extends AbstractStack<Character>
  implements CharStack
{
  public void push(Character local_o)
  {
    push(local_o.charValue());
  }
  
  public Character pop()
  {
    return Character.valueOf(popChar());
  }
  
  public Character top()
  {
    return Character.valueOf(topChar());
  }
  
  public Character peek(int local_i)
  {
    return Character.valueOf(peekChar(local_i));
  }
  
  public void push(char local_k)
  {
    push(Character.valueOf(local_k));
  }
  
  public char popChar()
  {
    return pop().charValue();
  }
  
  public char topChar()
  {
    return top().charValue();
  }
  
  public char peekChar(int local_i)
  {
    return peek(local_i).charValue();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */