package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Int2ObjectMap<V>
  extends Int2ObjectFunction<V>, Map<Integer, V>
{
  public abstract ObjectSet<Map.Entry<Integer, V>> entrySet();
  
  public abstract ObjectSet<Entry<V>> int2ObjectEntrySet();
  
  public abstract IntSet keySet();
  
  public abstract ObjectCollection<V> values();
  
  public static abstract interface Entry<V>
    extends Map.Entry<Integer, V>
  {
    public abstract int getIntKey();
  }
  
  public static abstract interface FastEntrySet<V>
    extends ObjectSet<Int2ObjectMap.Entry<V>>
  {
    public abstract ObjectIterator<Int2ObjectMap.Entry<V>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ObjectMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */