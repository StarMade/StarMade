package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Byte2BooleanMap
  extends Byte2BooleanFunction, Map<Byte, Boolean>
{
  public abstract ObjectSet<Map.Entry<Byte, Boolean>> entrySet();
  
  public abstract ObjectSet<Entry> byte2BooleanEntrySet();
  
  public abstract ByteSet keySet();
  
  public abstract BooleanCollection values();
  
  public abstract boolean containsValue(boolean paramBoolean);
  
  public static abstract interface Entry
    extends Map.Entry<Byte, Boolean>
  {
    public abstract byte getByteKey();
    
    public abstract boolean setValue(boolean paramBoolean);
    
    public abstract boolean getBooleanValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Byte2BooleanMap.Entry>
  {
    public abstract ObjectIterator<Byte2BooleanMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2BooleanMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */