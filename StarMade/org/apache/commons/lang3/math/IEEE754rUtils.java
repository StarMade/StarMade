/*   1:    */package org.apache.commons.lang3.math;
/*   2:    */
/*  35:    */public class IEEE754rUtils
/*  36:    */{
/*  37:    */  public static double min(double[] array)
/*  38:    */  {
/*  39: 39 */    if (array == null)
/*  40: 40 */      throw new IllegalArgumentException("The Array must not be null");
/*  41: 41 */    if (array.length == 0) {
/*  42: 42 */      throw new IllegalArgumentException("Array cannot be empty.");
/*  43:    */    }
/*  44:    */    
/*  46: 46 */    double min = array[0];
/*  47: 47 */    for (int i = 1; i < array.length; i++) {
/*  48: 48 */      min = min(array[i], min);
/*  49:    */    }
/*  50:    */    
/*  51: 51 */    return min;
/*  52:    */  }
/*  53:    */  
/*  62:    */  public static float min(float[] array)
/*  63:    */  {
/*  64: 64 */    if (array == null)
/*  65: 65 */      throw new IllegalArgumentException("The Array must not be null");
/*  66: 66 */    if (array.length == 0) {
/*  67: 67 */      throw new IllegalArgumentException("Array cannot be empty.");
/*  68:    */    }
/*  69:    */    
/*  71: 71 */    float min = array[0];
/*  72: 72 */    for (int i = 1; i < array.length; i++) {
/*  73: 73 */      min = min(array[i], min);
/*  74:    */    }
/*  75:    */    
/*  76: 76 */    return min;
/*  77:    */  }
/*  78:    */  
/*  88:    */  public static double min(double a, double b, double c)
/*  89:    */  {
/*  90: 90 */    return min(min(a, b), c);
/*  91:    */  }
/*  92:    */  
/* 101:    */  public static double min(double a, double b)
/* 102:    */  {
/* 103:103 */    if (Double.isNaN(a)) {
/* 104:104 */      return b;
/* 105:    */    }
/* 106:106 */    if (Double.isNaN(b)) {
/* 107:107 */      return a;
/* 108:    */    }
/* 109:109 */    return Math.min(a, b);
/* 110:    */  }
/* 111:    */  
/* 122:    */  public static float min(float a, float b, float c)
/* 123:    */  {
/* 124:124 */    return min(min(a, b), c);
/* 125:    */  }
/* 126:    */  
/* 135:    */  public static float min(float a, float b)
/* 136:    */  {
/* 137:137 */    if (Float.isNaN(a)) {
/* 138:138 */      return b;
/* 139:    */    }
/* 140:140 */    if (Float.isNaN(b)) {
/* 141:141 */      return a;
/* 142:    */    }
/* 143:143 */    return Math.min(a, b);
/* 144:    */  }
/* 145:    */  
/* 155:    */  public static double max(double[] array)
/* 156:    */  {
/* 157:157 */    if (array == null)
/* 158:158 */      throw new IllegalArgumentException("The Array must not be null");
/* 159:159 */    if (array.length == 0) {
/* 160:160 */      throw new IllegalArgumentException("Array cannot be empty.");
/* 161:    */    }
/* 162:    */    
/* 164:164 */    double max = array[0];
/* 165:165 */    for (int j = 1; j < array.length; j++) {
/* 166:166 */      max = max(array[j], max);
/* 167:    */    }
/* 168:    */    
/* 169:169 */    return max;
/* 170:    */  }
/* 171:    */  
/* 180:    */  public static float max(float[] array)
/* 181:    */  {
/* 182:182 */    if (array == null)
/* 183:183 */      throw new IllegalArgumentException("The Array must not be null");
/* 184:184 */    if (array.length == 0) {
/* 185:185 */      throw new IllegalArgumentException("Array cannot be empty.");
/* 186:    */    }
/* 187:    */    
/* 189:189 */    float max = array[0];
/* 190:190 */    for (int j = 1; j < array.length; j++) {
/* 191:191 */      max = max(array[j], max);
/* 192:    */    }
/* 193:    */    
/* 194:194 */    return max;
/* 195:    */  }
/* 196:    */  
/* 206:    */  public static double max(double a, double b, double c)
/* 207:    */  {
/* 208:208 */    return max(max(a, b), c);
/* 209:    */  }
/* 210:    */  
/* 219:    */  public static double max(double a, double b)
/* 220:    */  {
/* 221:221 */    if (Double.isNaN(a)) {
/* 222:222 */      return b;
/* 223:    */    }
/* 224:224 */    if (Double.isNaN(b)) {
/* 225:225 */      return a;
/* 226:    */    }
/* 227:227 */    return Math.max(a, b);
/* 228:    */  }
/* 229:    */  
/* 240:    */  public static float max(float a, float b, float c)
/* 241:    */  {
/* 242:242 */    return max(max(a, b), c);
/* 243:    */  }
/* 244:    */  
/* 253:    */  public static float max(float a, float b)
/* 254:    */  {
/* 255:255 */    if (Float.isNaN(a)) {
/* 256:256 */      return b;
/* 257:    */    }
/* 258:258 */    if (Float.isNaN(b)) {
/* 259:259 */      return a;
/* 260:    */    }
/* 261:261 */    return Math.max(a, b);
/* 262:    */  }
/* 263:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.math.IEEE754rUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */