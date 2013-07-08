package it.unimi.dsi.fastutil;

public abstract class AbstractPriorityQueue<K>
  implements PriorityQueue<K>
{
  public void changed()
  {
    throw new UnsupportedOperationException();
  }
  
  public K last()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.AbstractPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */