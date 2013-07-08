/*   1:    */package com.bulletphysics.util;
/*   2:    */
/*   3:    */import java.lang.reflect.Array;
/*   4:    */import java.util.Collections;
/*   5:    */import java.util.Comparator;
/*   6:    */import java.util.HashMap;
/*   7:    */import java.util.Map;
/*   8:    */
/*  37:    */public class ArrayPool<T>
/*  38:    */{
/*  39:    */  private Class componentType;
/*  40: 40 */  private ObjectArrayList list = new ObjectArrayList();
/*  41:    */  private Comparator comparator;
/*  42: 42 */  private IntValue key = new IntValue(null);
/*  43:    */  
/*  48:    */  public ArrayPool(Class componentType)
/*  49:    */  {
/*  50: 50 */    this.componentType = componentType;
/*  51:    */    
/*  52: 52 */    if (componentType == Float.TYPE) {
/*  53: 53 */      this.comparator = floatComparator;
/*  54:    */    }
/*  55: 55 */    else if (componentType == Integer.TYPE) {
/*  56: 56 */      this.comparator = intComparator;
/*  57:    */    }
/*  58: 58 */    else if (!componentType.isPrimitive()) {
/*  59: 59 */      this.comparator = objectComparator;
/*  60:    */    }
/*  61:    */    else {
/*  62: 62 */      throw new UnsupportedOperationException("unsupported type " + componentType);
/*  63:    */    }
/*  64:    */  }
/*  65:    */  
/*  66:    */  private T create(int length)
/*  67:    */  {
/*  68: 68 */    return Array.newInstance(this.componentType, length);
/*  69:    */  }
/*  70:    */  
/*  78:    */  public T getFixed(int length)
/*  79:    */  {
/*  80: 80 */    this.key.value = length;
/*  81: 81 */    int index = Collections.binarySearch(this.list, this.key, this.comparator);
/*  82: 82 */    if (index < 0) {
/*  83: 83 */      return create(length);
/*  84:    */    }
/*  85: 85 */    return this.list.remove(index);
/*  86:    */  }
/*  87:    */  
/*  95:    */  public T getAtLeast(int length)
/*  96:    */  {
/*  97: 97 */    this.key.value = length;
/*  98: 98 */    int index = Collections.binarySearch(this.list, this.key, this.comparator);
/*  99: 99 */    if (index < 0) {
/* 100:100 */      index = -index - 1;
/* 101:101 */      if (index < this.list.size()) {
/* 102:102 */        return this.list.remove(index);
/* 103:    */      }
/* 104:    */      
/* 105:105 */      return create(length);
/* 106:    */    }
/* 107:    */    
/* 108:108 */    return this.list.remove(index);
/* 109:    */  }
/* 110:    */  
/* 116:    */  public void release(T array)
/* 117:    */  {
/* 118:118 */    int index = Collections.binarySearch(this.list, array, this.comparator);
/* 119:119 */    if (index < 0) index = -index - 1;
/* 120:120 */    this.list.add(index, array);
/* 121:    */    
/* 123:123 */    if (this.comparator == objectComparator) {
/* 124:124 */      Object[] objArray = (Object[])array;
/* 125:125 */      for (int i = 0; i < objArray.length; i++) {
/* 126:126 */        objArray[i] = null;
/* 127:    */      }
/* 128:    */    }
/* 129:    */  }
/* 130:    */  
/* 133:133 */  private static Comparator floatComparator = new Comparator() {
/* 134:    */    public int compare(Object o1, Object o2) {
/* 135:135 */      int len1 = (o1 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)o1).value : ((float[])o1).length;
/* 136:136 */      int len2 = (o2 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)o2).value : ((float[])o2).length;
/* 137:137 */      return len1 < len2 ? -1 : len1 > len2 ? 1 : 0;
/* 138:    */    }
/* 139:    */  };
/* 140:    */  
/* 141:141 */  private static Comparator intComparator = new Comparator() {
/* 142:    */    public int compare(Object o1, Object o2) {
/* 143:143 */      int len1 = (o1 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)o1).value : ((int[])o1).length;
/* 144:144 */      int len2 = (o2 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)o2).value : ((int[])o2).length;
/* 145:145 */      return len1 < len2 ? -1 : len1 > len2 ? 1 : 0;
/* 146:    */    }
/* 147:    */  };
/* 148:    */  
/* 149:149 */  private static Comparator objectComparator = new Comparator() {
/* 150:    */    public int compare(Object o1, Object o2) {
/* 151:151 */      int len1 = (o1 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)o1).value : ((Object[])o1).length;
/* 152:152 */      int len2 = (o2 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)o2).value : ((Object[])o2).length;
/* 153:153 */      return len1 < len2 ? -1 : len1 > len2 ? 1 : 0;
/* 154:    */    }
/* 155:    */  };
/* 156:    */  
/* 163:163 */  private static ThreadLocal<Map> threadLocal = new ThreadLocal()
/* 164:    */  {
/* 165:    */    protected Map initialValue() {
/* 166:166 */      return new HashMap();
/* 167:    */    }
/* 168:    */  };
/* 169:    */  
/* 176:    */  public static <T> ArrayPool<T> get(Class cls)
/* 177:    */  {
/* 178:178 */    Map map = (Map)threadLocal.get();
/* 179:    */    
/* 180:180 */    ArrayPool<T> pool = (ArrayPool)map.get(cls);
/* 181:181 */    if (pool == null) {
/* 182:182 */      pool = new ArrayPool(cls);
/* 183:183 */      map.put(cls, pool);
/* 184:    */    }
/* 185:    */    
/* 186:186 */    return pool;
/* 187:    */  }
/* 188:    */  
/* 189:    */  public static void cleanCurrentThread() {
/* 190:190 */    threadLocal.remove();
/* 191:    */  }
/* 192:    */  
/* 193:    */  private static class IntValue
/* 194:    */  {
/* 195:    */    public int value;
/* 196:    */  }
/* 197:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.util.ArrayPool
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */