package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Int2LongMap
  extends Int2LongFunction, Map<Integer, Long>
{
  public abstract ObjectSet<Map.Entry<Integer, Long>> entrySet();
  
  public abstract ObjectSet<Entry> int2LongEntrySet();
  
  public abstract IntSet keySet();
  
  public abstract LongCollection values();
  
  public abstract boolean containsValue(long paramLong);
  
  public static abstract interface Entry
    extends Map.Entry<Integer, Long>
  {
    public abstract int getIntKey();
    
    public abstract long setValue(long paramLong);
    
    public abstract long getLongValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Int2LongMap.Entry>
  {
    public abstract ObjectIterator<Int2LongMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2LongMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */