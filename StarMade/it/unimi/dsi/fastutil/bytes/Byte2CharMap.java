package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Byte2CharMap extends Byte2CharFunction, Map<Byte, Character>
{
  public abstract ObjectSet<Map.Entry<Byte, Character>> entrySet();

  public abstract ObjectSet<Entry> byte2CharEntrySet();

  public abstract ByteSet keySet();

  public abstract CharCollection values();

  public abstract boolean containsValue(char paramChar);

  public static abstract interface Entry extends Map.Entry<Byte, Character>
  {
    public abstract byte getByteKey();

    public abstract char setValue(char paramChar);

    public abstract char getCharValue();
  }

  public static abstract interface FastEntrySet extends ObjectSet<Byte2CharMap.Entry>
  {
    public abstract ObjectIterator<Byte2CharMap.Entry> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2CharMap
 * JD-Core Version:    0.6.2
 */