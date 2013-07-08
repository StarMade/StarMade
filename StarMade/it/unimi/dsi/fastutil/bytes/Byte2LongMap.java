package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Byte2LongMap
  extends Byte2LongFunction, Map<Byte, Long>
{
  public abstract ObjectSet<Map.Entry<Byte, Long>> entrySet();
  
  public abstract ObjectSet<Entry> byte2LongEntrySet();
  
  public abstract ByteSet keySet();
  
  public abstract LongCollection values();
  
  public abstract boolean containsValue(long paramLong);
  
  public static abstract interface Entry
    extends Map.Entry<Byte, Long>
  {
    public abstract byte getByteKey();
    
    public abstract long setValue(long paramLong);
    
    public abstract long getLongValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Byte2LongMap.Entry>
  {
    public abstract ObjectIterator<Byte2LongMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2LongMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */