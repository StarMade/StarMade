/*   1:    */package org.apache.commons.lang3;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Arrays;
/*   5:    */import java.util.EnumSet;
/*   6:    */import java.util.LinkedHashMap;
/*   7:    */import java.util.List;
/*   8:    */import java.util.Map;
/*   9:    */
/*  49:    */public class EnumUtils
/*  50:    */{
/*  51:    */  public static <E extends Enum<E>> Map<String, E> getEnumMap(Class<E> enumClass)
/*  52:    */  {
/*  53: 53 */    Map<String, E> map = new LinkedHashMap();
/*  54: 54 */    for (E e : (Enum[])enumClass.getEnumConstants()) {
/*  55: 55 */      map.put(e.name(), e);
/*  56:    */    }
/*  57: 57 */    return map;
/*  58:    */  }
/*  59:    */  
/*  68:    */  public static <E extends Enum<E>> List<E> getEnumList(Class<E> enumClass)
/*  69:    */  {
/*  70: 70 */    return new ArrayList(Arrays.asList(enumClass.getEnumConstants()));
/*  71:    */  }
/*  72:    */  
/*  83:    */  public static <E extends Enum<E>> boolean isValidEnum(Class<E> enumClass, String enumName)
/*  84:    */  {
/*  85: 85 */    if (enumName == null) {
/*  86: 86 */      return false;
/*  87:    */    }
/*  88:    */    try {
/*  89: 89 */      Enum.valueOf(enumClass, enumName);
/*  90: 90 */      return true;
/*  91:    */    } catch (IllegalArgumentException ex) {}
/*  92: 92 */    return false;
/*  93:    */  }
/*  94:    */  
/* 106:    */  public static <E extends Enum<E>> E getEnum(Class<E> enumClass, String enumName)
/* 107:    */  {
/* 108:108 */    if (enumName == null) {
/* 109:109 */      return null;
/* 110:    */    }
/* 111:    */    try {
/* 112:112 */      return Enum.valueOf(enumClass, enumName);
/* 113:    */    } catch (IllegalArgumentException ex) {}
/* 114:114 */    return null;
/* 115:    */  }
/* 116:    */  
/* 133:    */  public static <E extends Enum<E>> long generateBitVector(Class<E> enumClass, Iterable<E> values)
/* 134:    */  {
/* 135:135 */    checkBitVectorable(enumClass);
/* 136:136 */    Validate.notNull(values);
/* 137:137 */    long total = 0L;
/* 138:138 */    for (E constant : values) {
/* 139:139 */      total |= 1 << constant.ordinal();
/* 140:    */    }
/* 141:141 */    return total;
/* 142:    */  }
/* 143:    */  
/* 159:    */  public static <E extends Enum<E>> long generateBitVector(Class<E> enumClass, E... values)
/* 160:    */  {
/* 161:161 */    Validate.noNullElements(values);
/* 162:162 */    return generateBitVector(enumClass, Arrays.asList(values));
/* 163:    */  }
/* 164:    */  
/* 177:    */  public static <E extends Enum<E>> EnumSet<E> processBitVector(Class<E> enumClass, long value)
/* 178:    */  {
/* 179:179 */    E[] constants = (Enum[])checkBitVectorable(enumClass).getEnumConstants();
/* 180:180 */    EnumSet<E> results = EnumSet.noneOf(enumClass);
/* 181:181 */    for (E constant : constants) {
/* 182:182 */      if ((value & 1 << constant.ordinal()) != 0L) {
/* 183:183 */        results.add(constant);
/* 184:    */      }
/* 185:    */    }
/* 186:186 */    return results;
/* 187:    */  }
/* 188:    */  
/* 197:    */  private static <E extends Enum<E>> Class<E> checkBitVectorable(Class<E> enumClass)
/* 198:    */  {
/* 199:199 */    Validate.notNull(enumClass, "EnumClass must be defined.", new Object[0]);
/* 200:    */    
/* 201:201 */    E[] constants = (Enum[])enumClass.getEnumConstants();
/* 202:202 */    Validate.isTrue(constants != null, "%s does not seem to be an Enum type", new Object[] { enumClass });
/* 203:203 */    Validate.isTrue(constants.length <= 64, "Cannot store %s %s values in %s bits", new Object[] { Integer.valueOf(constants.length), enumClass.getSimpleName(), Integer.valueOf(64) });
/* 204:    */    
/* 206:206 */    return enumClass;
/* 207:    */  }
/* 208:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.EnumUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */