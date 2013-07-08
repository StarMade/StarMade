package it.unimi.dsi.fastutil;

public abstract class AbstractStack<K>
  implements Stack<K>
{
  public K top()
  {
    return peek(0);
  }
  
  public K peek(int local_i)
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.AbstractStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */