package org.hsqldb.lib;

import java.util.NoSuchElementException;
import org.hsqldb.store.BaseHashMap;
import org.hsqldb.store.BaseHashMap.BaseHashIterator;

public class IntKeyIntValueHashMap
  extends BaseHashMap
{
  private Set keySet;
  private Collection values;
  
  public IntKeyIntValueHashMap()
  {
    this(8);
  }
  
  public IntKeyIntValueHashMap(int paramInt)
    throws IllegalArgumentException
  {
    super(paramInt, 1, 1, false);
  }
  
  public int get(int paramInt)
    throws NoSuchElementException
  {
    int i = getLookup(paramInt);
    if (i != -1) {
      return this.intValueTable[i];
    }
    throw new NoSuchElementException();
  }
  
  public int get(int paramInt1, int paramInt2)
  {
    int i = getLookup(paramInt1);
    if (i != -1) {
      return this.intValueTable[i];
    }
    return paramInt2;
  }
  
  public boolean get(int paramInt, int[] paramArrayOfInt)
  {
    int i = getLookup(paramInt);
    if (i != -1)
    {
      paramArrayOfInt[0] = this.intValueTable[i];
      return true;
    }
    return false;
  }
  
  public boolean put(int paramInt1, int paramInt2)
  {
    int i = size();
    super.addOrRemove(paramInt1, paramInt2, null, null, false);
    return i != size();
  }
  
  public boolean remove(int paramInt)
  {
    int i = size();
    super.addOrRemove(paramInt, 0L, null, null, true);
    return i != size();
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
      IntKeyIntValueHashMap tmp8_5 = IntKeyIntValueHashMap.this;
      tmp8_5.getClass();
      return new BaseHashMap.BaseHashIterator(tmp8_5, false);
    }
    
    public int size()
    {
      return IntKeyIntValueHashMap.this.size();
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
      IntKeyIntValueHashMap.this.clear();
    }
  }
  
  class KeySet
    implements Set
  {
    KeySet() {}
    
    public Iterator iterator()
    {
      IntKeyIntValueHashMap tmp8_5 = IntKeyIntValueHashMap.this;
      tmp8_5.getClass();
      return new BaseHashMap.BaseHashIterator(tmp8_5, true);
    }
    
    public int size()
    {
      return IntKeyIntValueHashMap.this.size();
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
      IntKeyIntValueHashMap.this.clear();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.IntKeyIntValueHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */