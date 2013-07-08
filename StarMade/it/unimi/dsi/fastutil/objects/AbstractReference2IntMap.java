package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntIterator;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractReference2IntMap<K>
  extends AbstractReference2IntFunction<K>
  implements Reference2IntMap<K>, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  
  public boolean containsValue(Object local_ov)
  {
    return containsValue(((Integer)local_ov).intValue());
  }
  
  public boolean containsValue(int local_v)
  {
    return values().contains(local_v);
  }
  
  public boolean containsKey(Object local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends K, ? extends Integer> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends K, ? extends Integer>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Reference2IntMap)) {
      while (local_n-- != 0)
      {
        Reference2IntMap.Entry<? extends K> local_e = (Reference2IntMap.Entry)local_i.next();
        put(local_e.getKey(), local_e.getIntValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends K, ? extends Integer> local_e = (Map.Entry)local_i.next();
        put(local_e.getKey(), (Integer)local_e.getValue());
      }
    }
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
  
  public ReferenceSet<K> keySet()
  {
    new AbstractReferenceSet()
    {
      public boolean contains(Object local_k)
      {
        return AbstractReference2IntMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractReference2IntMap.this.size();
      }
      
      public void clear()
      {
        AbstractReference2IntMap.this.clear();
      }
      
      public ObjectIterator<K> iterator()
      {
        new AbstractObjectIterator()
        {
          final ObjectIterator<Map.Entry<K, Integer>> field_3 = AbstractReference2IntMap.this.entrySet().iterator();
          
          public K next()
          {
            return ((Reference2IntMap.Entry)this.field_3.next()).getKey();
          }
          
          public boolean hasNext()
          {
            return this.field_3.hasNext();
          }
        };
      }
    };
  }
  
  public IntCollection values()
  {
    new AbstractIntCollection()
    {
      public boolean contains(int local_k)
      {
        return AbstractReference2IntMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractReference2IntMap.this.size();
      }
      
      public void clear()
      {
        AbstractReference2IntMap.this.clear();
      }
      
      public IntIterator iterator()
      {
        new AbstractIntIterator()
        {
          final ObjectIterator<Map.Entry<K, Integer>> field_70 = AbstractReference2IntMap.this.entrySet().iterator();
          
          public int nextInt()
          {
            return ((Reference2IntMap.Entry)this.field_70.next()).getIntValue();
          }
          
          public boolean hasNext()
          {
            return this.field_70.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<K, Integer>> entrySet()
  {
    return reference2IntEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<K, Integer>> local_i = entrySet().iterator();
    while (local_n-- != 0) {
      local_h += ((Map.Entry)local_i.next()).hashCode();
    }
    return local_h;
  }
  
  public boolean equals(Object local_o)
  {
    if (local_o == this) {
      return true;
    }
    if (!(local_o instanceof Map)) {
      return false;
    }
    Map<?, ?> local_m = (Map)local_o;
    if (local_m.size() != size()) {
      return false;
    }
    return entrySet().containsAll(local_m.entrySet());
  }
  
  public String toString()
  {
    StringBuilder local_s = new StringBuilder();
    ObjectIterator<? extends Map.Entry<K, Integer>> local_i = entrySet().iterator();
    int local_n = size();
    boolean first = true;
    local_s.append("{");
    while (local_n-- != 0)
    {
      if (first) {
        first = false;
      } else {
        local_s.append(", ");
      }
      Reference2IntMap.Entry<K> local_e = (Reference2IntMap.Entry)local_i.next();
      if (this == local_e.getKey()) {
        local_s.append("(this map)");
      } else {
        local_s.append(String.valueOf(local_e.getKey()));
      }
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getIntValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry<K>
    implements Reference2IntMap.Entry<K>
  {
    protected K key;
    protected int value;
    
    public BasicEntry(K key, Integer value)
    {
      this.key = key;
      this.value = value.intValue();
    }
    
    public BasicEntry(K key, int value)
    {
      this.key = key;
      this.value = value;
    }
    
    public K getKey()
    {
      return this.key;
    }
    
    public Integer getValue()
    {
      return Integer.valueOf(this.value);
    }
    
    public int getIntValue()
    {
      return this.value;
    }
    
    public int setValue(int value)
    {
      throw new UnsupportedOperationException();
    }
    
    public Integer setValue(Integer value)
    {
      return Integer.valueOf(setValue(value.intValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<?, ?> local_e = (Map.Entry)local_o;
      return (this.key == local_e.getKey()) && (this.value == ((Integer)local_e.getValue()).intValue());
    }
    
    public int hashCode()
    {
      return (this.key == null ? 0 : System.identityHashCode(this.key)) ^ this.value;
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2IntMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */