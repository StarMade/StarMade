package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Reference2BooleanMap<K>
  extends Reference2BooleanFunction<K>, Map<K, Boolean>
{
  public abstract ObjectSet<Map.Entry<K, Boolean>> entrySet();
  
  public abstract ObjectSet<Entry<K>> reference2BooleanEntrySet();
  
  public abstract ReferenceSet<K> keySet();
  
  public abstract BooleanCollection values();
  
  public abstract boolean containsValue(boolean paramBoolean);
  
  public static abstract interface Entry<K>
    extends Map.Entry<K, Boolean>
  {
    public abstract boolean setValue(boolean paramBoolean);
    
    public abstract boolean getBooleanValue();
  }
  
  public static abstract interface FastEntrySet<K>
    extends ObjectSet<Reference2BooleanMap.Entry<K>>
  {
    public abstract ObjectIterator<Reference2BooleanMap.Entry<K>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2BooleanMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */