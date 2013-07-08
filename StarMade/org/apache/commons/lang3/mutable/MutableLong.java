/*   1:    */package org.apache.commons.lang3.mutable;
/*   2:    */
/*  13:    */public class MutableLong
/*  14:    */  extends Number
/*  15:    */  implements Comparable<MutableLong>, Mutable<Number>
/*  16:    */{
/*  17:    */  private static final long serialVersionUID = 62986528375L;
/*  18:    */  
/*  28:    */  private long value;
/*  29:    */  
/*  40:    */  public MutableLong() {}
/*  41:    */  
/*  52:    */  public MutableLong(long value)
/*  53:    */  {
/*  54: 54 */    this.value = value;
/*  55:    */  }
/*  56:    */  
/*  63:    */  public MutableLong(Number value)
/*  64:    */  {
/*  65: 65 */    this.value = value.longValue();
/*  66:    */  }
/*  67:    */  
/*  74:    */  public MutableLong(String value)
/*  75:    */    throws NumberFormatException
/*  76:    */  {
/*  77: 77 */    this.value = Long.parseLong(value);
/*  78:    */  }
/*  79:    */  
/*  85:    */  public Long getValue()
/*  86:    */  {
/*  87: 87 */    return Long.valueOf(this.value);
/*  88:    */  }
/*  89:    */  
/*  94:    */  public void setValue(long value)
/*  95:    */  {
/*  96: 96 */    this.value = value;
/*  97:    */  }
/*  98:    */  
/* 104:    */  public void setValue(Number value)
/* 105:    */  {
/* 106:106 */    this.value = value.longValue();
/* 107:    */  }
/* 108:    */  
/* 114:    */  public void increment()
/* 115:    */  {
/* 116:116 */    this.value += 1L;
/* 117:    */  }
/* 118:    */  
/* 123:    */  public void decrement()
/* 124:    */  {
/* 125:125 */    this.value -= 1L;
/* 126:    */  }
/* 127:    */  
/* 134:    */  public void add(long operand)
/* 135:    */  {
/* 136:136 */    this.value += operand;
/* 137:    */  }
/* 138:    */  
/* 145:    */  public void add(Number operand)
/* 146:    */  {
/* 147:147 */    this.value += operand.longValue();
/* 148:    */  }
/* 149:    */  
/* 155:    */  public void subtract(long operand)
/* 156:    */  {
/* 157:157 */    this.value -= operand;
/* 158:    */  }
/* 159:    */  
/* 166:    */  public void subtract(Number operand)
/* 167:    */  {
/* 168:168 */    this.value -= operand.longValue();
/* 169:    */  }
/* 170:    */  
/* 178:    */  public int intValue()
/* 179:    */  {
/* 180:180 */    return (int)this.value;
/* 181:    */  }
/* 182:    */  
/* 188:    */  public long longValue()
/* 189:    */  {
/* 190:190 */    return this.value;
/* 191:    */  }
/* 192:    */  
/* 198:    */  public float floatValue()
/* 199:    */  {
/* 200:200 */    return (float)this.value;
/* 201:    */  }
/* 202:    */  
/* 208:    */  public double doubleValue()
/* 209:    */  {
/* 210:210 */    return this.value;
/* 211:    */  }
/* 212:    */  
/* 218:    */  public Long toLong()
/* 219:    */  {
/* 220:220 */    return Long.valueOf(longValue());
/* 221:    */  }
/* 222:    */  
/* 232:    */  public boolean equals(Object obj)
/* 233:    */  {
/* 234:234 */    if ((obj instanceof MutableLong)) {
/* 235:235 */      return this.value == ((MutableLong)obj).longValue();
/* 236:    */    }
/* 237:237 */    return false;
/* 238:    */  }
/* 239:    */  
/* 245:    */  public int hashCode()
/* 246:    */  {
/* 247:247 */    return (int)(this.value ^ this.value >>> 32);
/* 248:    */  }
/* 249:    */  
/* 256:    */  public int compareTo(MutableLong other)
/* 257:    */  {
/* 258:258 */    long anotherVal = other.value;
/* 259:259 */    return this.value == anotherVal ? 0 : this.value < anotherVal ? -1 : 1;
/* 260:    */  }
/* 261:    */  
/* 268:    */  public String toString()
/* 269:    */  {
/* 270:270 */    return String.valueOf(this.value);
/* 271:    */  }
/* 272:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.mutable.MutableLong
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */