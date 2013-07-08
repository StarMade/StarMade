package org.hsqldb.lib;

import java.util.NoSuchElementException;
import org.hsqldb.store.BaseHashMap;
import org.hsqldb.store.BaseHashMap.BaseHashIterator;

public class IntValueHashMap
  extends BaseHashMap
{
  Set keySet;
  private Collection values;
  
  public IntValueHashMap()
  {
    this(8);
  }
  
  public IntValueHashMap(int paramInt)
    throws IllegalArgumentException
  {
    super(paramInt, 3, 1, false);
  }
  
  public int get(Object paramObject)
    throws NoSuchElementException
  {
    if (paramObject == null) {
      throw new NoSuchElementException();
    }
    int i = paramObject.hashCode();
    int j = getLookup(paramObject, i);
    if (j != -1) {
      return this.intValueTable[j];
    }
    throw new NoSuchElementException();
  }
  
  public int get(Object paramObject, int paramInt)
  {
    if (paramObject == null) {
      throw new NoSuchElementException();
    }
    int i = paramObject.hashCode();
    int j = getLookup(paramObject, i);
    if (j != -1) {
      return this.intValueTable[j];
    }
    return paramInt;
  }
  
  public boolean get(Object paramObject, int[] paramArrayOfInt)
  {
    if (paramObject == null) {
      throw new NoSuchElementException();
    }
    int i = paramObject.hashCode();
    int j = getLookup(paramObject, i);
    if (j != -1)
    {
      paramArrayOfInt[0] = this.intValueTable[j];
      return true;
    }
    return false;
  }
  
  public Object getKey(int paramInt)
  {
    BaseHashMap.BaseHashIterator localBaseHashIterator = new BaseHashMap.BaseHashIterator(this, false);
    while (localBaseHashIterator.hasNext())
    {
      int i = localBaseHashIterator.nextInt();
      if (i == paramInt) {
        return this.objectKeyTable[localBaseHashIterator.getLookup()];
      }
    }
    return null;
  }
  
  public boolean put(Object paramObject, int paramInt)
  {
    if (paramObject == null) {
      throw new NoSuchElementException();
    }
    int i = size();
    super.addOrRemove(0L, paramInt, paramObject, null, false);
    return i != size();
  }
  
  public boolean remove(Object paramObject)
  {
    int i = size();
    super.addOrRemove(0L, 0L, paramObject, null, true);
    return i != size();
  }
  
  public boolean containsKey(Object paramObject)
  {
    return super.containsKey(paramObject);
  }
  
  public boolean containsValue(int paramInt)
  {
    throw new RuntimeException();
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
  
  public void putAll(IntValueHashMap paramIntValueHashMap)
  {
    Iterator localIterator = paramIntValueHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      put(localObject, paramIntValueHashMap.get(localObject));
    }
  }
  
  class Values
    implements Collection
  {
    Values() {}
    
    public Iterator iterator()
    {
      IntValueHashMap tmp8_5 = IntValueHashMap.this;
      tmp8_5.getClass();
      return new BaseHashMap.BaseHashIterator(tmp8_5, false);
    }
    
    public int size()
    {
      return IntValueHashMap.this.size();
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
      IntValueHashMap.this.clear();
    }
  }
  
  class KeySet
    implements Set
  {
    KeySet() {}
    
    public Iterator iterator()
    {
      IntValueHashMap tmp8_5 = IntValueHashMap.this;
      tmp8_5.getClass();
      return new BaseHashMap.BaseHashIterator(tmp8_5, true);
    }
    
    public int size()
    {
      return IntValueHashMap.this.size();
    }
    
    public boolean contains(Object paramObject)
    {
      return IntValueHashMap.this.containsKey(paramObject);
    }
    
    public Object get(Object paramObject)
    {
      int i = IntValueHashMap.this.getLookup(paramObject, paramObject.hashCode());
      if (i < 0) {
        return null;
      }
      return IntValueHashMap.this.objectKeyTable[i];
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
      IntValueHashMap.this.remove(paramObject);
      return size() != i;
    }
    
    public boolean isEmpty()
    {
      return size() == 0;
    }
    
    public void clear()
    {
      IntValueHashMap.this.clear();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.IntValueHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */