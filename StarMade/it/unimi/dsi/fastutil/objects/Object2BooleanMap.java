package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Object2BooleanMap<K>
  extends Object2BooleanFunction<K>, Map<K, Boolean>
{
  public abstract ObjectSet<Map.Entry<K, Boolean>> entrySet();
  
  public abstract ObjectSet<Entry<K>> object2BooleanEntrySet();
  
  public abstract ObjectSet<K> keySet();
  
  public abstract BooleanCollection values();
  
  public abstract boolean containsValue(boolean paramBoolean);
  
  public static abstract interface Entry<K>
    extends Map.Entry<K, Boolean>
  {
    public abstract boolean setValue(boolean paramBoolean);
    
    public abstract boolean getBooleanValue();
  }
  
  public static abstract interface FastEntrySet<K>
    extends ObjectSet<Object2BooleanMap.Entry<K>>
  {
    public abstract ObjectIterator<Object2BooleanMap.Entry<K>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2BooleanMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */