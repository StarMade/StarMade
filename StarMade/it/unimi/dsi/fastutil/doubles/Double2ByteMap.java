package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract interface Double2ByteMap extends Double2ByteFunction, Map<Double, Byte>
{
  public abstract ObjectSet<Map.Entry<Double, Byte>> entrySet();

  public abstract ObjectSet<Entry> double2ByteEntrySet();

  public abstract DoubleSet keySet();

  public abstract ByteCollection values();

  public abstract boolean containsValue(byte paramByte);

  public static abstract interface Entry extends Map.Entry<Double, Byte>
  {
    public abstract double getDoubleKey();

    public abstract byte setValue(byte paramByte);

    public abstract byte getByteValue();
  }

  public static abstract interface FastEntrySet extends ObjectSet<Double2ByteMap.Entry>
  {
    public abstract ObjectIterator<Double2ByteMap.Entry> fastIterator();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ByteMap
 * JD-Core Version:    0.6.2
 */