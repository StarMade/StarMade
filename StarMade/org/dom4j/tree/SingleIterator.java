package org.dom4j.tree;

import java.util.Iterator;

public class SingleIterator
  implements Iterator
{
  private boolean first = true;
  private Object object;
  
  public SingleIterator(Object object)
  {
    this.object = object;
  }
  
  public boolean hasNext()
  {
    return this.first;
  }
  
  public Object next()
  {
    Object answer = this.object;
    this.object = null;
    this.first = false;
    return answer;
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException("remove() is not supported by this iterator");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.SingleIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */