package org.hsqldb.lib;

import org.hsqldb.store.BaseHashMap;
import org.hsqldb.store.BaseHashMap.BaseHashIterator;
import org.hsqldb.store.BaseHashMap.MultiValueKeyIterator;

public class MultiValueHashMap extends BaseHashMap
{
  Set keySet;
  Collection values;
  Iterator valueIterator;

  public MultiValueHashMap()
  {
    this(8);
  }

  public MultiValueHashMap(int paramInt)
    throws IllegalArgumentException
  {
    super(paramInt, 3, 3, false);
    this.multiValueTable = new boolean[this.objectValueTable.length];
  }

  public Iterator get(Object paramObject)
  {
    int i = paramObject.hashCode();
    return super.getValuesIterator(paramObject, i);
  }

  public Object put(Object paramObject1, Object paramObject2)
  {
    return super.addOrRemoveMultiVal(0L, 0L, paramObject1, paramObject2, false, false);
  }

  public Object remove(Object paramObject)
  {
    return super.addOrRemoveMultiVal(0L, 0L, paramObject, null, true, false);
  }

  public Object remove(Object paramObject1, Object paramObject2)
  {
    return super.addOrRemoveMultiVal(0L, 0L, paramObject1, paramObject2, false, true);
  }

  public boolean containsKey(Object paramObject)
  {
    return super.containsKey(paramObject);
  }

  public boolean containsValue(Object paramObject)
  {
    return super.containsValue(paramObject);
  }

  public int valueCount(Object paramObject)
  {
    int i = paramObject.hashCode();
    return super.valueCount(paramObject, i);
  }

  public void putAll(HashMap paramHashMap)
  {
    Iterator localIterator = paramHashMap.keySet.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      put(localObject, paramHashMap.get(localObject));
    }
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
      MultiValueHashMap tmp8_5 = MultiValueHashMap.this;
      tmp8_5.getClass();
      return new BaseHashMap.BaseHashIterator(tmp8_5, false);
    }

    public int size()
    {
      return MultiValueHashMap.this.size();
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
      MultiValueHashMap.this.clear();
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
      MultiValueHashMap tmp8_5 = MultiValueHashMap.this;
      tmp8_5.getClass();
      return new BaseHashMap.MultiValueKeyIterator(tmp8_5);
    }

    public int size()
    {
      return MultiValueHashMap.this.size();
    }

    public boolean contains(Object paramObject)
    {
      return MultiValueHashMap.this.containsKey(paramObject);
    }

    public Object get(Object paramObject)
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
      int i = size();
      MultiValueHashMap.this.remove(paramObject);
      return size() != i;
    }

    public boolean isEmpty()
    {
      return size() == 0;
    }

    public void clear()
    {
      MultiValueHashMap.this.clear();
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.MultiValueHashMap
 * JD-Core Version:    0.6.2
 */