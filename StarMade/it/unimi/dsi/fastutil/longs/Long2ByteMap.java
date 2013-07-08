package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Long2ByteMap
  extends Long2ByteFunction, Map<Long, Byte>
{
  public abstract ObjectSet<Map.Entry<Long, Byte>> entrySet();
  
  public abstract ObjectSet<Entry> long2ByteEntrySet();
  
  public abstract LongSet keySet();
  
  public abstract ByteCollection values();
  
  public abstract boolean containsValue(byte paramByte);
  
  public static abstract interface Entry
    extends Map.Entry<Long, Byte>
  {
    public abstract long getLongKey();
    
    public abstract byte setValue(byte paramByte);
    
    public abstract byte getByteValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Long2ByteMap.Entry>
  {
    public abstract ObjectIterator<Long2ByteMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ByteMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */