package org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EnumUtils
{
  public static <E extends Enum<E>> Map<String, E> getEnumMap(Class<E> enumClass)
  {
    Map<String, E> map = new LinkedHashMap();
    for (E local_e : (Enum[])enumClass.getEnumConstants()) {
      map.put(local_e.name(), local_e);
    }
    return map;
  }
  
  public static <E extends Enum<E>> List<E> getEnumList(Class<E> enumClass)
  {
    return new ArrayList(Arrays.asList(enumClass.getEnumConstants()));
  }
  
  public static <E extends Enum<E>> boolean isValidEnum(Class<E> enumClass, String enumName)
  {
    if (enumName == null) {
      return false;
    }
    try
    {
      Enum.valueOf(enumClass, enumName);
      return true;
    }
    catch (IllegalArgumentException local_ex) {}
    return false;
  }
  
  public static <E extends Enum<E>> E getEnum(Class<E> enumClass, String enumName)
  {
    if (enumName == null) {
      return null;
    }
    try
    {
      return Enum.valueOf(enumClass, enumName);
    }
    catch (IllegalArgumentException local_ex) {}
    return null;
  }
  
  public static <E extends Enum<E>> long generateBitVector(Class<E> enumClass, Iterable<E> values)
  {
    checkBitVectorable(enumClass);
    Validate.notNull(values);
    long total = 0L;
    Iterator local_i$ = values.iterator();
    while (local_i$.hasNext())
    {
      E constant = (Enum)local_i$.next();
      total |= 1 << constant.ordinal();
    }
    return total;
  }
  
  public static <E extends Enum<E>> long generateBitVector(Class<E> enumClass, E... values)
  {
    Validate.noNullElements(values);
    return generateBitVector(enumClass, Arrays.asList(values));
  }
  
  public static <E extends Enum<E>> EnumSet<E> processBitVector(Class<E> enumClass, long value)
  {
    E[] constants = (Enum[])checkBitVectorable(enumClass).getEnumConstants();
    EnumSet<E> results = EnumSet.noneOf(enumClass);
    for (E constant : constants) {
      if ((value & 1 << constant.ordinal()) != 0L) {
        results.add(constant);
      }
    }
    return results;
  }
  
  private static <E extends Enum<E>> Class<E> checkBitVectorable(Class<E> enumClass)
  {
    Validate.notNull(enumClass, "EnumClass must be defined.", new Object[0]);
    E[] constants = (Enum[])enumClass.getEnumConstants();
    Validate.isTrue(constants != null, "%s does not seem to be an Enum type", new Object[] { enumClass });
    Validate.isTrue(constants.length <= 64, "Cannot store %s %s values in %s bits", new Object[] { Integer.valueOf(constants.length), enumClass.getSimpleName(), Integer.valueOf(64) });
    return enumClass;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.EnumUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */