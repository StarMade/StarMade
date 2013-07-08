package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Short2ByteMap
  extends Short2ByteFunction, Map<Short, Byte>
{
  public abstract ObjectSet<Map.Entry<Short, Byte>> entrySet();
  
  public abstract ObjectSet<Entry> short2ByteEntrySet();
  
  public abstract ShortSet keySet();
  
  public abstract ByteCollection values();
  
  public abstract boolean containsValue(byte paramByte);
  
  public static abstract interface Entry
    extends Map.Entry<Short, Byte>
  {
    public abstract short getShortKey();
    
    public abstract byte setValue(byte paramByte);
    
    public abstract byte getByteValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Short2ByteMap.Entry>
  {
    public abstract ObjectIterator<Short2ByteMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ByteMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */