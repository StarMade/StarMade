/*  1:   */package org.lwjgl.util.mapped;
/*  2:   */
/*  3:   */import java.util.Iterator;
/*  4:   */
/* 39:   */final class MappedForeach<T extends MappedObject>
/* 40:   */  implements Iterable<T>
/* 41:   */{
/* 42:   */  final T mapped;
/* 43:   */  final int elementCount;
/* 44:   */  
/* 45:   */  MappedForeach(T mapped, int elementCount)
/* 46:   */  {
/* 47:47 */    this.mapped = mapped;
/* 48:48 */    this.elementCount = elementCount;
/* 49:   */  }
/* 50:   */  
/* 51:   */  public Iterator<T> iterator() {
/* 52:52 */    new Iterator()
/* 53:   */    {
/* 54:   */      private int index;
/* 55:   */      
/* 56:   */      public boolean hasNext() {
/* 57:57 */        return this.index < MappedForeach.this.elementCount;
/* 58:   */      }
/* 59:   */      
/* 60:   */      public T next() {
/* 61:61 */        MappedForeach.this.mapped.setViewAddress(MappedForeach.this.mapped.getViewAddress(this.index++));
/* 62:62 */        return MappedForeach.this.mapped;
/* 63:   */      }
/* 64:   */      
/* 65:   */      public void remove() {
/* 66:66 */        throw new UnsupportedOperationException();
/* 67:   */      }
/* 68:   */    };
/* 69:   */  }
/* 70:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedForeach
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */