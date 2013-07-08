package org.hsqldb.lib;

import org.hsqldb.store.BaseHashMap;
import org.hsqldb.store.BaseHashMap.BaseHashIterator;

public class OrderedLongKeyHashMap
  extends BaseHashMap
{
  Set keySet;
  Collection values;
  
  public OrderedLongKeyHashMap()
  {
    this(8);
  }
  
  public OrderedLongKeyHashMap(int paramInt)
    throws IllegalArgumentException
  {
    super(paramInt, 2, 3, false);
    this.isList = true;
  }
  
  public OrderedLongKeyHashMap(int paramInt, boolean paramBoolean)
    throws IllegalArgumentException
  {
    super(paramInt, 2, 3, false);
    this.objectKeyTable = new Object[this.objectValueTable.length];
    this.isTwoObjectValue = true;
    this.isList = true;
    if (paramBoolean) {
      this.objectValueTable2 = new Object[this.objectValueTable.length];
    }
    this.minimizeOnEmpty = true;
  }
  
  public Object get(long paramLong)
  {
    int i = getLookup(paramLong);
    if (i != -1) {
      return this.objectValueTable[i];
    }
    return null;
  }
  
  public Object getValueByIndex(int paramInt)
  {
    return this.objectValueTable[paramInt];
  }
  
  public Object getSecondValueByIndex(int paramInt)
  {
    return this.objectKeyTable[paramInt];
  }
  
  public Object getThirdValueByIndex(int paramInt)
  {
    return this.objectValueTable2[paramInt];
  }
  
  public Object setSecondValueByIndex(int paramInt, Object paramObject)
  {
    Object localObject = this.objectKeyTable[paramInt];
    this.objectKeyTable[paramInt] = paramObject;
    return localObject;
  }
  
  public Object setThirdValueByIndex(int paramInt, Object paramObject)
  {
    Object localObject = this.objectValueTable2[paramInt];
    this.objectValueTable2[paramInt] = paramObject;
    return localObject;
  }
  
  public Object put(long paramLong, Object paramObject)
  {
    return super.addOrRemove(paramLong, paramObject, null, false);
  }
  
  public boolean containsValue(Object paramObject)
  {
    return super.containsValue(paramObject);
  }
  
  public Object remove(long paramLong)
  {
    return super.addOrRemove(paramLong, null, null, false);
  }
  
  public boolean containsKey(long paramLong)
  {
    return super.containsKey(paramLong);
  }
  
  public Object put(long paramLong, Object paramObject1, Object paramObject2)
  {
    return super.addOrRemove(paramLong, paramObject1, paramObject2, false);
  }
  
  public int getLookup(long paramLong)
  {
    return super.getLookup(paramLong);
  }
  
  public Object getFirstByLookup(int paramInt)
  {
    if (paramInt == -1) {
      return null;
    }
    return this.objectValueTable[paramInt];
  }
  
  public Set keySet()
  {
    if (this.keySet == null) {
      this.keySet = new KeySet();
    }
    return this.keySet;
  }
  
  public Collection values()
  {
    if (this.values == null) {
      this.values = new Values();
    }
    return this.values;
  }
  
  class Values
    implements Collection
  {
    Values() {}
    
    public Iterator iterator()
    {
      OrderedLongKeyHashMap tmp8_5 = OrderedLongKeyHashMap.this;
      tmp8_5.getClass();
      return new BaseHashMap.BaseHashIterator(tmp8_5, false);
    }
    
    public int size()
    {
      return OrderedLongKeyHashMap.this.size();
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
      OrderedLongKeyHashMap.this.clear();
    }
  }
  
  class KeySet
    implements Set
  {
    KeySet() {}
    
    public Iterator iterator()
    {
      OrderedLongKeyHashMap tmp8_5 = OrderedLongKeyHashMap.this;
      tmp8_5.getClass();
      return new BaseHashMap.BaseHashIterator(tmp8_5, true);
    }
    
    public int size()
    {
      return OrderedLongKeyHashMap.this.size();
    }
    
    public boolean contains(Object paramObject)
    {
      throw new RuntimeException();
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
      throw new RuntimeException();
    }
    
    public boolean isEmpty()
    {
      return size() == 0;
    }
    
    public void clear()
    {
      OrderedLongKeyHashMap.this.clear();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.OrderedLongKeyHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */