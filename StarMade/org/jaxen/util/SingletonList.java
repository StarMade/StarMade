package org.jaxen.util;

import java.util.AbstractList;

public class SingletonList
  extends AbstractList
{
  private final Object element;
  
  public SingletonList(Object element)
  {
    this.element = element;
  }
  
  public int size()
  {
    return 1;
  }
  
  public Object get(int index)
  {
    if (index == 0) {
      return this.element;
    }
    throw new IndexOutOfBoundsException(index + " != 0");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.util.SingletonList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */