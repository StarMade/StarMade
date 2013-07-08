/*   1:    */package org.apache.commons.lang3.mutable;
/*   2:    */
/*  13:    */public class MutableShort
/*  14:    */  extends Number
/*  15:    */  implements Comparable<MutableShort>, Mutable<Number>
/*  16:    */{
/*  17:    */  private static final long serialVersionUID = -2135791679L;
/*  18:    */  
/*  28:    */  private short value;
/*  29:    */  
/*  40:    */  public MutableShort() {}
/*  41:    */  
/*  52:    */  public MutableShort(short value)
/*  53:    */  {
/*  54: 54 */    this.value = value;
/*  55:    */  }
/*  56:    */  
/*  63:    */  public MutableShort(Number value)
/*  64:    */  {
/*  65: 65 */    this.value = value.shortValue();
/*  66:    */  }
/*  67:    */  
/*  74:    */  public MutableShort(String value)
/*  75:    */    throws NumberFormatException
/*  76:    */  {
/*  77: 77 */    this.value = Short.parseShort(value);
/*  78:    */  }
/*  79:    */  
/*  85:    */  public Short getValue()
/*  86:    */  {
/*  87: 87 */    return Short.valueOf(this.value);
/*  88:    */  }
/*  89:    */  
/*  94:    */  public void setValue(short value)
/*  95:    */  {
/*  96: 96 */    this.value = value;
/*  97:    */  }
/*  98:    */  
/* 104:    */  public void setValue(Number value)
/* 105:    */  {
/* 106:106 */    this.value = value.shortValue();
/* 107:    */  }
/* 108:    */  
/* 114:    */  public void increment()
/* 115:    */  {
/* 116:116 */    this.value = ((short)(this.value + 1));
/* 117:    */  }
/* 118:    */  
/* 123:    */  public void decrement()
/* 124:    */  {
/* 125:125 */    this.value = ((short)(this.value - 1));
/* 126:    */  }
/* 127:    */  
/* 134:    */  public void add(short operand)
/* 135:    */  {
/* 136:136 */    this.value = ((short)(this.value + operand));
/* 137:    */  }
/* 138:    */  
/* 145:    */  public void add(Number operand)
/* 146:    */  {
/* 147:147 */    this.value = ((short)(this.value + operand.shortValue()));
/* 148:    */  }
/* 149:    */  
/* 155:    */  public void subtract(short operand)
/* 156:    */  {
/* 157:157 */    this.value = ((short)(this.value - operand));
/* 158:    */  }
/* 159:    */  
/* 166:    */  public void subtract(Number operand)
/* 167:    */  {
/* 168:168 */    this.value = ((short)(this.value - operand.shortValue()));
/* 169:    */  }
/* 170:    */  
/* 178:    */  public short shortValue()
/* 179:    */  {
/* 180:180 */    return this.value;
/* 181:    */  }
/* 182:    */  
/* 188:    */  public int intValue()
/* 189:    */  {
/* 190:190 */    return this.value;
/* 191:    */  }
/* 192:    */  
/* 198:    */  public long longValue()
/* 199:    */  {
/* 200:200 */    return this.value;
/* 201:    */  }
/* 202:    */  
/* 208:    */  public float floatValue()
/* 209:    */  {
/* 210:210 */    return this.value;
/* 211:    */  }
/* 212:    */  
/* 218:    */  public double doubleValue()
/* 219:    */  {
/* 220:220 */    return this.value;
/* 221:    */  }
/* 222:    */  
/* 228:    */  public Short toShort()
/* 229:    */  {
/* 230:230 */    return Short.valueOf(shortValue());
/* 231:    */  }
/* 232:    */  
/* 242:    */  public boolean equals(Object obj)
/* 243:    */  {
/* 244:244 */    if ((obj instanceof MutableShort)) {
/* 245:245 */      return this.value == ((MutableShort)obj).shortValue();
/* 246:    */    }
/* 247:247 */    return false;
/* 248:    */  }
/* 249:    */  
/* 255:    */  public int hashCode()
/* 256:    */  {
/* 257:257 */    return this.value;
/* 258:    */  }
/* 259:    */  
/* 266:    */  public int compareTo(MutableShort other)
/* 267:    */  {
/* 268:268 */    short anotherVal = other.value;
/* 269:269 */    return this.value == anotherVal ? 0 : this.value < anotherVal ? -1 : 1;
/* 270:    */  }
/* 271:    */  
/* 278:    */  public String toString()
/* 279:    */  {
/* 280:280 */    return String.valueOf(this.value);
/* 281:    */  }
/* 282:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.mutable.MutableShort
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */