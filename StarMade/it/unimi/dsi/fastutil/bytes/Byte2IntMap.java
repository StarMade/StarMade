package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Byte2IntMap
  extends Byte2IntFunction, Map<Byte, Integer>
{
  public abstract ObjectSet<Map.Entry<Byte, Integer>> entrySet();
  
  public abstract ObjectSet<Entry> byte2IntEntrySet();
  
  public abstract ByteSet keySet();
  
  public abstract IntCollection values();
  
  public abstract boolean containsValue(int paramInt);
  
  public static abstract interface Entry
    extends Map.Entry<Byte, Integer>
  {
    public abstract byte getByteKey();
    
    public abstract int setValue(int paramInt);
    
    public abstract int getIntValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Byte2IntMap.Entry>
  {
    public abstract ObjectIterator<Byte2IntMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2IntMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */