package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Byte2ReferenceMap<V>
  extends Byte2ReferenceFunction<V>, Map<Byte, V>
{
  public abstract ObjectSet<Map.Entry<Byte, V>> entrySet();
  
  public abstract ObjectSet<Entry<V>> byte2ReferenceEntrySet();
  
  public abstract ByteSet keySet();
  
  public abstract ReferenceCollection<V> values();
  
  public static abstract interface Entry<V>
    extends Map.Entry<Byte, V>
  {
    public abstract byte getByteKey();
  }
  
  public static abstract interface FastEntrySet<V>
    extends ObjectSet<Byte2ReferenceMap.Entry<V>>
  {
    public abstract ObjectIterator<Byte2ReferenceMap.Entry<V>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ReferenceMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */