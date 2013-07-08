package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Short2IntMap
  extends Short2IntFunction, Map<Short, Integer>
{
  public abstract ObjectSet<Map.Entry<Short, Integer>> entrySet();
  
  public abstract ObjectSet<Entry> short2IntEntrySet();
  
  public abstract ShortSet keySet();
  
  public abstract IntCollection values();
  
  public abstract boolean containsValue(int paramInt);
  
  public static abstract interface Entry
    extends Map.Entry<Short, Integer>
  {
    public abstract short getShortKey();
    
    public abstract int setValue(int paramInt);
    
    public abstract int getIntValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Short2IntMap.Entry>
  {
    public abstract ObjectIterator<Short2IntMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2IntMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */