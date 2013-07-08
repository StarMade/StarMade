package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Int2IntMap
  extends Int2IntFunction, Map<Integer, Integer>
{
  public abstract ObjectSet<Map.Entry<Integer, Integer>> entrySet();
  
  public abstract ObjectSet<Entry> int2IntEntrySet();
  
  public abstract IntSet keySet();
  
  public abstract IntCollection values();
  
  public abstract boolean containsValue(int paramInt);
  
  public static abstract interface Entry
    extends Map.Entry<Integer, Integer>
  {
    public abstract int getIntKey();
    
    public abstract int setValue(int paramInt);
    
    public abstract int getIntValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Int2IntMap.Entry>
  {
    public abstract ObjectIterator<Int2IntMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2IntMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */