package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Int2ByteMap
  extends Int2ByteFunction, Map<Integer, Byte>
{
  public abstract ObjectSet<Map.Entry<Integer, Byte>> entrySet();
  
  public abstract ObjectSet<Entry> int2ByteEntrySet();
  
  public abstract IntSet keySet();
  
  public abstract ByteCollection values();
  
  public abstract boolean containsValue(byte paramByte);
  
  public static abstract interface Entry
    extends Map.Entry<Integer, Byte>
  {
    public abstract int getIntKey();
    
    public abstract byte setValue(byte paramByte);
    
    public abstract byte getByteValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Int2ByteMap.Entry>
  {
    public abstract ObjectIterator<Int2ByteMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ByteMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */