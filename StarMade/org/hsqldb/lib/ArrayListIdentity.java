package org.hsqldb.lib;

public class ArrayListIdentity
  extends HsqlArrayList
  implements HsqlList
{
  public int indexOf(Object paramObject)
  {
    for (int i = 0; i < this.elementCount; i++) {
      if (this.elementData[i] == paramObject) {
        return i;
      }
    }
    return -1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.ArrayListIdentity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */