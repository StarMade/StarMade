package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Byte2ObjectMap<V>
  extends Byte2ObjectFunction<V>, Map<Byte, V>
{
  public abstract ObjectSet<Map.Entry<Byte, V>> entrySet();
  
  public abstract ObjectSet<Entry<V>> byte2ObjectEntrySet();
  
  public abstract ByteSet keySet();
  
  public abstract ObjectCollection<V> values();
  
  public static abstract interface Entry<V>
    extends Map.Entry<Byte, V>
  {
    public abstract byte getByteKey();
  }
  
  public static abstract interface FastEntrySet<V>
    extends ObjectSet<Byte2ObjectMap.Entry<V>>
  {
    public abstract ObjectIterator<Byte2ObjectMap.Entry<V>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ObjectMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */