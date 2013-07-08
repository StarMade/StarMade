package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.shorts.ShortCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Object2ShortMap<K>
  extends Object2ShortFunction<K>, Map<K, Short>
{
  public abstract ObjectSet<Map.Entry<K, Short>> entrySet();
  
  public abstract ObjectSet<Entry<K>> object2ShortEntrySet();
  
  public abstract ObjectSet<K> keySet();
  
  public abstract ShortCollection values();
  
  public abstract boolean containsValue(short paramShort);
  
  public static abstract interface Entry<K>
    extends Map.Entry<K, Short>
  {
    public abstract short setValue(short paramShort);
    
    public abstract short getShortValue();
  }
  
  public static abstract interface FastEntrySet<K>
    extends ObjectSet<Object2ShortMap.Entry<K>>
  {
    public abstract ObjectIterator<Object2ShortMap.Entry<K>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ShortMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */