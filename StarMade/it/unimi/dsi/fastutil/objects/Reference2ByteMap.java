package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.bytes.ByteCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Reference2ByteMap<K>
  extends Reference2ByteFunction<K>, Map<K, Byte>
{
  public abstract ObjectSet<Map.Entry<K, Byte>> entrySet();
  
  public abstract ObjectSet<Entry<K>> reference2ByteEntrySet();
  
  public abstract ReferenceSet<K> keySet();
  
  public abstract ByteCollection values();
  
  public abstract boolean containsValue(byte paramByte);
  
  public static abstract interface Entry<K>
    extends Map.Entry<K, Byte>
  {
    public abstract byte setValue(byte paramByte);
    
    public abstract byte getByteValue();
  }
  
  public static abstract interface FastEntrySet<K>
    extends ObjectSet<Reference2ByteMap.Entry<K>>
  {
    public abstract ObjectIterator<Reference2ByteMap.Entry<K>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2ByteMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */