/*   1:    */package org.apache.commons.lang3.mutable;
/*   2:    */
/*  13:    */public class MutableInt
/*  14:    */  extends Number
/*  15:    */  implements Comparable<MutableInt>, Mutable<Number>
/*  16:    */{
/*  17:    */  private static final long serialVersionUID = 512176391864L;
/*  18:    */  
/*  28:    */  private int value;
/*  29:    */  
/*  40:    */  public MutableInt() {}
/*  41:    */  
/*  52:    */  public MutableInt(int value)
/*  53:    */  {
/*  54: 54 */    this.value = value;
/*  55:    */  }
/*  56:    */  
/*  63:    */  public MutableInt(Number value)
/*  64:    */  {
/*  65: 65 */    this.value = value.intValue();
/*  66:    */  }
/*  67:    */  
/*  74:    */  public MutableInt(String value)
/*  75:    */    throws NumberFormatException
/*  76:    */  {
/*  77: 77 */    this.value = Integer.parseInt(value);
/*  78:    */  }
/*  79:    */  
/*  85:    */  public Integer getValue()
/*  86:    */  {
/*  87: 87 */    return Integer.valueOf(this.value);
/*  88:    */  }
/*  89:    */  
/*  94:    */  public void setValue(int value)
/*  95:    */  {
/*  96: 96 */    this.value = value;
/*  97:    */  }
/*  98:    */  
/* 104:    */  public void setValue(Number value)
/* 105:    */  {
/* 106:106 */    this.value = value.intValue();
/* 107:    */  }
/* 108:    */  
/* 114:    */  public void increment()
/* 115:    */  {
/* 116:116 */    this.value += 1;
/* 117:    */  }
/* 118:    */  
/* 123:    */  public void decrement()
/* 124:    */  {
/* 125:125 */    this.value -= 1;
/* 126:    */  }
/* 127:    */  
/* 134:    */  public void add(int operand)
/* 135:    */  {
/* 136:136 */    this.value += operand;
/* 137:    */  }
/* 138:    */  
/* 145:    */  public void add(Number operand)
/* 146:    */  {
/* 147:147 */    this.value += operand.intValue();
/* 148:    */  }
/* 149:    */  
/* 155:    */  public void subtract(int operand)
/* 156:    */  {
/* 157:157 */    this.value -= operand;
/* 158:    */  }
/* 159:    */  
/* 166:    */  public void subtract(Number operand)
/* 167:    */  {
/* 168:168 */    this.value -= operand.intValue();
/* 169:    */  }
/* 170:    */  
/* 178:    */  public int intValue()
/* 179:    */  {
/* 180:180 */    return this.value;
/* 181:    */  }
/* 182:    */  
/* 188:    */  public long longValue()
/* 189:    */  {
/* 190:190 */    return this.value;
/* 191:    */  }
/* 192:    */  
/* 198:    */  public float floatValue()
/* 199:    */  {
/* 200:200 */    return this.value;
/* 201:    */  }
/* 202:    */  
/* 208:    */  public double doubleValue()
/* 209:    */  {
/* 210:210 */    return this.value;
/* 211:    */  }
/* 212:    */  
/* 218:    */  public Integer toInteger()
/* 219:    */  {
/* 220:220 */    return Integer.valueOf(intValue());
/* 221:    */  }
/* 222:    */  
/* 232:    */  public boolean equals(Object obj)
/* 233:    */  {
/* 234:234 */    if ((obj instanceof MutableInt)) {
/* 235:235 */      return this.value == ((MutableInt)obj).intValue();
/* 236:    */    }
/* 237:237 */    return false;
/* 238:    */  }
/* 239:    */  
/* 245:    */  public int hashCode()
/* 246:    */  {
/* 247:247 */    return this.value;
/* 248:    */  }
/* 249:    */  
/* 256:    */  public int compareTo(MutableInt other)
/* 257:    */  {
/* 258:258 */    int anotherVal = other.value;
/* 259:259 */    return this.value == anotherVal ? 0 : this.value < anotherVal ? -1 : 1;
/* 260:    */  }
/* 261:    */  
/* 268:    */  public String toString()
/* 269:    */  {
/* 270:270 */    return String.valueOf(this.value);
/* 271:    */  }
/* 272:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.mutable.MutableInt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */