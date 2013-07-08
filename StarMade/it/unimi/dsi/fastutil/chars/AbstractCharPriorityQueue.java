package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.AbstractPriorityQueue;

public abstract class AbstractCharPriorityQueue
  extends AbstractPriorityQueue<Character>
  implements CharPriorityQueue
{
  public void enqueue(Character local_x)
  {
    enqueue(local_x.charValue());
  }
  
  public Character dequeue()
  {
    return Character.valueOf(dequeueChar());
  }
  
  public Character first()
  {
    return Character.valueOf(firstChar());
  }
  
  public Character last()
  {
    return Character.valueOf(lastChar());
  }
  
  public char lastChar()
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */