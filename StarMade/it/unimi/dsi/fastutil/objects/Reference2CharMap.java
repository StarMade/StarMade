package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.chars.CharCollection;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Reference2CharMap<K>
  extends Reference2CharFunction<K>, Map<K, Character>
{
  public abstract ObjectSet<Map.Entry<K, Character>> entrySet();
  
  public abstract ObjectSet<Entry<K>> reference2CharEntrySet();
  
  public abstract ReferenceSet<K> keySet();
  
  public abstract CharCollection values();
  
  public abstract boolean containsValue(char paramChar);
  
  public static abstract interface Entry<K>
    extends Map.Entry<K, Character>
  {
    public abstract char setValue(char paramChar);
    
    public abstract char getCharValue();
  }
  
  public static abstract interface FastEntrySet<K>
    extends ObjectSet<Reference2CharMap.Entry<K>>
  {
    public abstract ObjectIterator<Reference2CharMap.Entry<K>> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2CharMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */