package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Char2ByteMap
  extends Char2ByteFunction, Map<Character, Byte>
{
  public abstract ObjectSet<Map.Entry<Character, Byte>> entrySet();
  
  public abstract ObjectSet<Entry> char2ByteEntrySet();
  
  public abstract CharSet keySet();
  
  public abstract ByteCollection values();
  
  public abstract boolean containsValue(byte paramByte);
  
  public static abstract interface Entry
    extends Map.Entry<Character, Byte>
  {
    public abstract char getCharKey();
    
    public abstract byte setValue(byte paramByte);
    
    public abstract byte getByteValue();
  }
  
  public static abstract interface FastEntrySet
    extends ObjectSet<Char2ByteMap.Entry>
  {
    public abstract ObjectIterator<Char2ByteMap.Entry> fastIterator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2ByteMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */