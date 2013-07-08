package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Int2ReferenceMap<V>
  extends Int2ReferenceFunction<V>, Map<Integer, V>
{
  public abstract ObjectSet<Map.Entry<Integer, V>> entrySet();
  
  public abstract ObjectSet<Entry<V>> int2ReferenceEntrySet();
  
  public abstract IntSet keySet();
  
  public abstract ReferenceCollection<V> values();
  
  public static abstract interface Entry<V>
    extends Map.Entry<Integer, V>
  {
    public abstract int getIntKey();
  }
  
  public static abstract interface FastEntrySet<V>
    extends ObjectSet<Int2ReferenceMap.Entry<V>>
  {
    public abstract ObjectIterator<Int2ReferenceMap.Entry<V>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ReferenceMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */