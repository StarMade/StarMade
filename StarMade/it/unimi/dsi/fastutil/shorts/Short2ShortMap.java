package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Short2ShortMap
  extends Short2ShortFunction, Map<Short, Short>
{
  public abstract ObjectSet<Map.Entry<Short, Short>> entrySet();
  
  public abstract ObjectSet<Entry> short2ShortEntrySet();
  
  public abstract ShortSet keySet();
  
  public abstract ShortCollection values();
  
  public abstract boolean containsValue(short paramShort);
  
  public static abstract interface Entry
    extends Map.Entry<Short, Short>
  {
    public abstract short getShortKey();
    
    public abstract short setValue(short paramShort);
    
    public abstract short getShortValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Short2ShortMap.Entry>
  {
    public abstract ObjectIterator<Short2ShortMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ShortMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */