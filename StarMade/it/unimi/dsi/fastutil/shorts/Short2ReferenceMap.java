package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Short2ReferenceMap<V>
  extends Short2ReferenceFunction<V>, Map<Short, V>
{
  public abstract ObjectSet<Map.Entry<Short, V>> entrySet();
  
  public abstract ObjectSet<Entry<V>> short2ReferenceEntrySet();
  
  public abstract ShortSet keySet();
  
  public abstract ReferenceCollection<V> values();
  
  public static abstract interface Entry<V>
    extends Map.Entry<Short, V>
  {
    public abstract short getShortKey();
  }
  
  public static abstract interface FastEntrySet<V>
    extends ObjectSet<Short2ReferenceMap.Entry<V>>
  {
    public abstract ObjectIterator<Short2ReferenceMap.Entry<V>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ReferenceMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */