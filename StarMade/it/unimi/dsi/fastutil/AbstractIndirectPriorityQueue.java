package it.unimi.dsi.fastutil;

public abstract class AbstractIndirectPriorityQueue<K>
  implements IndirectPriorityQueue<K>
{
  public int last()
  {
    throw new UnsupportedOperationException();
  }
  
  public void changed()
  {
    changed(first());
  }
  
  public void changed(int index)
  {
    throw new UnsupportedOperationException();
  }
  
  public void allChanged()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean remove(int index)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean contains(int index)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.AbstractIndirectPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */