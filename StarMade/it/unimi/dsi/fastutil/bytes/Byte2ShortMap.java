package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Byte2ShortMap
  extends Byte2ShortFunction, Map<Byte, Short>
{
  public abstract ObjectSet<Map.Entry<Byte, Short>> entrySet();
  
  public abstract ObjectSet<Entry> byte2ShortEntrySet();
  
  public abstract ByteSet keySet();
  
  public abstract ShortCollection values();
  
  public abstract boolean containsValue(short paramShort);
  
  public static abstract interface Entry
    extends Map.Entry<Byte, Short>
  {
    public abstract byte getByteKey();
    
    public abstract short setValue(short paramShort);
    
    public abstract short getShortValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Byte2ShortMap.Entry>
  {
    public abstract ObjectIterator<Byte2ShortMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ShortMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */