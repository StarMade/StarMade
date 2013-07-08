package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Double2ReferenceMap<V>
  extends Double2ReferenceFunction<V>, Map<Double, V>
{
  public abstract ObjectSet<Map.Entry<Double, V>> entrySet();
  
  public abstract ObjectSet<Entry<V>> double2ReferenceEntrySet();
  
  public abstract DoubleSet keySet();
  
  public abstract ReferenceCollection<V> values();
  
  public static abstract interface Entry<V>
    extends Map.Entry<Double, V>
  {
    public abstract double getDoubleKey();
  }
  
  public static abstract interface FastEntrySet<V>
    extends ObjectSet<Double2ReferenceMap.Entry<V>>
  {
    public abstract ObjectIterator<Double2ReferenceMap.Entry<V>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ReferenceMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */