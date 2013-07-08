package org.jaxen.expr;

import java.util.HashSet;

final class IdentitySet
{
  private HashSet contents = new HashSet();
  
  void add(Object object)
  {
    IdentityWrapper wrapper = new IdentityWrapper(object);
    this.contents.add(wrapper);
  }
  
  public boolean contains(Object object)
  {
    IdentityWrapper wrapper = new IdentityWrapper(object);
    return this.contents.contains(wrapper);
  }
  
  private static class IdentityWrapper
  {
    private Object object;
    
    IdentityWrapper(Object object)
    {
      this.object = object;
    }
    
    public boolean equals(Object local_o)
    {
      IdentityWrapper local_w = (IdentityWrapper)local_o;
      return this.object == local_w.object;
    }
    
    public int hashCode()
    {
      return System.identityHashCode(this.object);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.IdentitySet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */