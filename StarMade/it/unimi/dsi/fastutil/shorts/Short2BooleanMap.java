package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Short2BooleanMap
  extends Short2BooleanFunction, Map<Short, Boolean>
{
  public abstract ObjectSet<Map.Entry<Short, Boolean>> entrySet();
  
  public abstract ObjectSet<Entry> short2BooleanEntrySet();
  
  public abstract ShortSet keySet();
  
  public abstract BooleanCollection values();
  
  public abstract boolean containsValue(boolean paramBoolean);
  
  public static abstract interface Entry
    extends Map.Entry<Short, Boolean>
  {
    public abstract short getShortKey();
    
    public abstract boolean setValue(boolean paramBoolean);
    
    public abstract boolean getBooleanValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Short2BooleanMap.Entry>
  {
    public abstract ObjectIterator<Short2BooleanMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2BooleanMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */