package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Byte2ByteMap
  extends Byte2ByteFunction, Map<Byte, Byte>
{
  public abstract ObjectSet<Map.Entry<Byte, Byte>> entrySet();
  
  public abstract ObjectSet<Entry> byte2ByteEntrySet();
  
  public abstract ByteSet keySet();
  
  public abstract ByteCollection values();
  
  public abstract boolean containsValue(byte paramByte);
  
  public static abstract interface Entry
    extends Map.Entry<Byte, Byte>
  {
    public abstract byte getByteKey();
    
    public abstract byte setValue(byte paramByte);
    
    public abstract byte getByteValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Byte2ByteMap.Entry>
  {
    public abstract ObjectIterator<Byte2ByteMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ByteMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */