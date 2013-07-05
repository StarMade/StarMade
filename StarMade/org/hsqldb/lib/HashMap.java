package org.hsqldb.lib;

import org.hsqldb.store.BaseHashMap;
import org.hsqldb.store.BaseHashMap.BaseHashIterator;

public class HashMap extends BaseHashMap
{
  Set keySet;
  Collection values;

  public HashMap()
  {
    this(8);
  }

  public HashMap(int paramInt)
    throws IllegalArgumentException
  {
    super(paramInt, 3, 3, false);
  }

  public Object get(Object paramObject)
  {
    int i = paramObject.hashCode();
    int j = getLookup(paramObject, i);
    if (j != -1)
      return this.objectValueTable[j];
    return null;
  }

  public Object put(Object paramObject1, Object paramObject2)
  {
    return super.addOrRemove(0L, 0L, paramObject1, paramObject2, false);
  }

  public Object remove(Object paramObject)
  {
    return super.removeObject(paramObject, false);
  }

  public boolean containsKey(Object paramObject)
  {
    return super.containsKey(paramObject);
  }

  public boolean containsValue(Object paramObject)
  {
    return super.containsValue(paramObject);
  }

  public void putAll(HashMap paramHashMap)
  {
    Iterator localIterator = paramHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      put(localObject, paramHashMap.get(localObject));
    }
  }

  public void valuesToArray(Object[] paramArrayOfObject)
  {
    Iterator localIterator = values().iterator();
    for (int i = 0; localIterator.hasNext(); i++)
      paramArrayOfObject[i] = localIterator.next();
  }

  public void keysToArray(Object[] paramArrayOfObject)
  {
    Iterator localIterator = keySet().iterator();
    for (int i = 0; localIterator.hasNext(); i++)
      paramArrayOfObject[i] = localIterator.next();
  }

  public Set keySet()
  {
    if (this.keySet == null)
      this.keySet = new KeySet();
    return this.keySet;
  }

  public Collection values()
  {
    if (this.values == null)
      this.values = new Values();
    return this.values;
  }

  class Values
    implements Collection
  {
    Values()
    {
    }

    public Iterator iterator()
    {
      HashMap tmp8_5 = HashMap.this;
      tmp8_5.getClass();
      return new BaseHashMap.BaseHashIterator(tmp8_5, false);
    }

    public int size()
    {
      return HashMap.this.size();
    }

    public boolean contains(Object paramObject)
    {
      throw new RuntimeException();
    }

    public boolean add(Object paramObject)
    {
      throw new RuntimeException();
    }

    public boolean addAll(Collection paramCollection)
    {
      throw new RuntimeException();
    }

    public boolean remove(Object paramObject)
    {
      throw new RuntimeException();
    }

    public boolean isEmpty()
    {
      return size() == 0;
    }

    public void clear()
    {
      HashMap.this.clear();
    }
  }

  class KeySet
    implements Set
  {
    KeySet()
    {
    }

    public Iterator iterator()
    {
      HashMap tmp8_5 = HashMap.this;
      tmp8_5.getClass();
      return new BaseHashMap.BaseHashIterator(tmp8_5, true);
    }

    public int size()
    {
      return HashMap.this.size();
    }

    public boolean contains(Object paramObject)
    {
      return HashMap.this.containsKey(paramObject);
    }

    public Object get(Object paramObject)
    {
      int i = HashMap.this.getLookup(paramObject, paramObject.hashCode());
      if (i < 0)
        return null;
      return HashMap.this.objectKeyTable[i];
    }

    public boolean add(Object paramObject)
    {
      throw new RuntimeException();
    }

    public boolean addAll(Collection paramCollection)
    {
      throw new RuntimeException();
    }

    public boolean remove(Object paramObject)
    {
      int i = size();
      HashMap.this.remove(paramObject);
      return size() != i;
    }

    public boolean isEmpty()
    {
      return size() == 0;
    }

    public void clear()
    {
      HashMap.this.clear();
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.HashMap
 * JD-Core Version:    0.6.2
 */