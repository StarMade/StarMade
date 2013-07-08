package org.jaxen.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @deprecated
 */
public class LinkedIterator
  implements Iterator
{
  private List iterators = new ArrayList();
  private int cur = 0;
  
  public void addIterator(Iterator local_i)
  {
    this.iterators.add(local_i);
  }
  
  public boolean hasNext()
  {
    boolean has = false;
    if (this.cur < this.iterators.size())
    {
      has = ((Iterator)this.iterators.get(this.cur)).hasNext();
      if ((!has) && (this.cur < this.iterators.size()))
      {
        this.cur += 1;
        has = hasNext();
      }
    }
    else
    {
      has = false;
    }
    return has;
  }
  
  public Object next()
  {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    return ((Iterator)this.iterators.get(this.cur)).next();
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.util.LinkedIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */