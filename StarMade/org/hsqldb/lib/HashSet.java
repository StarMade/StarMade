package org.hsqldb.lib;

import org.hsqldb.store.BaseHashMap;
import org.hsqldb.store.BaseHashMap.BaseHashIterator;

public class HashSet
  extends BaseHashMap
  implements Set
{
  public HashSet()
  {
    this(8);
  }
  
  public HashSet(int paramInt)
    throws IllegalArgumentException
  {
    super(paramInt, 3, 0, false);
  }
  
  public void setComparator(ObjectComparator paramObjectComparator)
  {
    super.setComparator(paramObjectComparator);
  }
  
  public boolean contains(Object paramObject)
  {
    return super.containsKey(paramObject);
  }
  
  public boolean containsAll(Collection paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext()) {
      if (!contains(localIterator.next())) {
        return false;
      }
    }
    return true;
  }
  
  public Object get(Object paramObject)
  {
    int i = getLookup(paramObject, paramObject.hashCode());
    if (i < 0) {
      return null;
    }
    return this.objectKeyTable[i];
  }
  
  public boolean add(Object paramObject)
  {
    int i = size();
    super.addOrRemove(0L, 0L, paramObject, null, false);
    return i != size();
  }
  
  public boolean addAll(Collection paramCollection)
  {
    boolean bool = false;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext()) {
      bool |= add(localIterator.next());
    }
    return bool;
  }
  
  public boolean addAll(Object[] paramArrayOfObject)
  {
    boolean bool = false;
    for (int i = 0; i < paramArrayOfObject.length; i++) {
      bool |= add(paramArrayOfObject[i]);
    }
    return bool;
  }
  
  public boolean addAll(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
  {
    boolean bool = false;
    for (int i = paramInt1; (i < paramArrayOfObject.length) && (i < paramInt2); i++) {
      bool |= add(paramArrayOfObject[i]);
    }
    return bool;
  }
  
  public boolean remove(Object paramObject)
  {
    return super.removeObject(paramObject, false) != null;
  }
  
  public boolean removeAll(Collection paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    boolean bool = true;
    while (localIterator.hasNext()) {
      bool &= remove(localIterator.next());
    }
    return bool;
  }
  
  public boolean removeAll(Object[] paramArrayOfObject)
  {
    boolean bool = true;
    for (int i = 0; i < paramArrayOfObject.length; i++) {
      bool &= remove(paramArrayOfObject[i]);
    }
    return bool;
  }
  
  public void toArray(Object[] paramArrayOfObject)
  {
    Iterator localIterator = iterator();
    for (int i = 0; localIterator.hasNext(); i++) {
      paramArrayOfObject[i] = localIterator.next();
    }
  }
  
  public Object[] toArray()
  {
    Object[] arrayOfObject = new Object[size()];
    toArray(arrayOfObject);
    return arrayOfObject;
  }
  
  public Iterator iterator()
  {
    return new BaseHashMap.BaseHashIterator(this, true);
  }
  
  public String toString()
  {
    Iterator localIterator = iterator();
    StringBuffer localStringBuffer = new StringBuffer();
    while (localIterator.hasNext())
    {
      if (localStringBuffer.length() > 0) {
        localStringBuffer.append(", ");
      } else {
        localStringBuffer.append('[');
      }
      localStringBuffer.append(localIterator.next());
    }
    return localStringBuffer.toString() + ']';
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.HashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */