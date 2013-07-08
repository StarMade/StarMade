package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Short2LongMap
  extends Short2LongFunction, Map<Short, Long>
{
  public abstract ObjectSet<Map.Entry<Short, Long>> entrySet();
  
  public abstract ObjectSet<Entry> short2LongEntrySet();
  
  public abstract ShortSet keySet();
  
  public abstract LongCollection values();
  
  public abstract boolean containsValue(long paramLong);
  
  public static abstract interface Entry
    extends Map.Entry<Short, Long>
  {
    public abstract short getShortKey();
    
    public abstract long setValue(long paramLong);
    
    public abstract long getLongValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Short2LongMap.Entry>
  {
    public abstract ObjectIterator<Short2LongMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2LongMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */