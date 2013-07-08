package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Long2BooleanMap
  extends Long2BooleanFunction, Map<Long, Boolean>
{
  public abstract ObjectSet<Map.Entry<Long, Boolean>> entrySet();
  
  public abstract ObjectSet<Entry> long2BooleanEntrySet();
  
  public abstract LongSet keySet();
  
  public abstract BooleanCollection values();
  
  public abstract boolean containsValue(boolean paramBoolean);
  
  public static abstract interface Entry
    extends Map.Entry<Long, Boolean>
  {
    public abstract long getLongKey();
    
    public abstract boolean setValue(boolean paramBoolean);
    
    public abstract boolean getBooleanValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Long2BooleanMap.Entry>
  {
    public abstract ObjectIterator<Long2BooleanMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2BooleanMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */