package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Int2BooleanMap
  extends Int2BooleanFunction, Map<Integer, Boolean>
{
  public abstract ObjectSet<Map.Entry<Integer, Boolean>> entrySet();
  
  public abstract ObjectSet<Entry> int2BooleanEntrySet();
  
  public abstract IntSet keySet();
  
  public abstract BooleanCollection values();
  
  public abstract boolean containsValue(boolean paramBoolean);
  
  public static abstract interface Entry
    extends Map.Entry<Integer, Boolean>
  {
    public abstract int getIntKey();
    
    public abstract boolean setValue(boolean paramBoolean);
    
    public abstract boolean getBooleanValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Int2BooleanMap.Entry>
  {
    public abstract ObjectIterator<Int2BooleanMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2BooleanMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */