package org.lwjgl.util.mapped;

import java.util.Iterator;

final class MappedForeach<T extends MappedObject>
  implements Iterable<T>
{
  final T mapped;
  final int elementCount;
  
  MappedForeach(T mapped, int elementCount)
  {
    this.mapped = mapped;
    this.elementCount = elementCount;
  }
  
  public Iterator<T> iterator()
  {
    new Iterator()
    {
      private int index;
      
      public boolean hasNext()
      {
        return this.index < MappedForeach.this.elementCount;
      }
      
      public T next()
      {
        MappedForeach.this.mapped.setViewAddress(MappedForeach.this.mapped.getViewAddress(this.index++));
        return MappedForeach.this.mapped;
      }
      
      public void remove()
      {
        throw new UnsupportedOperationException();
      }
    };
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.mapped.MappedForeach
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */