/*   1:    */package org.apache.commons.lang3.mutable;
/*   2:    */
/*  13:    */public class MutableByte
/*  14:    */  extends Number
/*  15:    */  implements Comparable<MutableByte>, Mutable<Number>
/*  16:    */{
/*  17:    */  private static final long serialVersionUID = -1585823265L;
/*  18:    */  
/*  28:    */  private byte value;
/*  29:    */  
/*  40:    */  public MutableByte() {}
/*  41:    */  
/*  52:    */  public MutableByte(byte value)
/*  53:    */  {
/*  54: 54 */    this.value = value;
/*  55:    */  }
/*  56:    */  
/*  63:    */  public MutableByte(Number value)
/*  64:    */  {
/*  65: 65 */    this.value = value.byteValue();
/*  66:    */  }
/*  67:    */  
/*  74:    */  public MutableByte(String value)
/*  75:    */    throws NumberFormatException
/*  76:    */  {
/*  77: 77 */    this.value = Byte.parseByte(value);
/*  78:    */  }
/*  79:    */  
/*  85:    */  public Byte getValue()
/*  86:    */  {
/*  87: 87 */    return Byte.valueOf(this.value);
/*  88:    */  }
/*  89:    */  
/*  94:    */  public void setValue(byte value)
/*  95:    */  {
/*  96: 96 */    this.value = value;
/*  97:    */  }
/*  98:    */  
/* 104:    */  public void setValue(Number value)
/* 105:    */  {
/* 106:106 */    this.value = value.byteValue();
/* 107:    */  }
/* 108:    */  
/* 114:    */  public void increment()
/* 115:    */  {
/* 116:116 */    this.value = ((byte)(this.value + 1));
/* 117:    */  }
/* 118:    */  
/* 123:    */  public void decrement()
/* 124:    */  {
/* 125:125 */    this.value = ((byte)(this.value - 1));
/* 126:    */  }
/* 127:    */  
/* 134:    */  public void add(byte operand)
/* 135:    */  {
/* 136:136 */    this.value = ((byte)(this.value + operand));
/* 137:    */  }
/* 138:    */  
/* 145:    */  public void add(Number operand)
/* 146:    */  {
/* 147:147 */    this.value = ((byte)(this.value + operand.byteValue()));
/* 148:    */  }
/* 149:    */  
/* 155:    */  public void subtract(byte operand)
/* 156:    */  {
/* 157:157 */    this.value = ((byte)(this.value - operand));
/* 158:    */  }
/* 159:    */  
/* 166:    */  public void subtract(Number operand)
/* 167:    */  {
/* 168:168 */    this.value = ((byte)(this.value - operand.byteValue()));
/* 169:    */  }
/* 170:    */  
/* 178:    */  public byte byteValue()
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
/* 228:    */  public Byte toByte()
/* 229:    */  {
/* 230:230 */    return Byte.valueOf(byteValue());
/* 231:    */  }
/* 232:    */  
/* 242:    */  public boolean equals(Object obj)
/* 243:    */  {
/* 244:244 */    if ((obj instanceof MutableByte)) {
/* 245:245 */      return this.value == ((MutableByte)obj).byteValue();
/* 246:    */    }
/* 247:247 */    return false;
/* 248:    */  }
/* 249:    */  
/* 255:    */  public int hashCode()
/* 256:    */  {
/* 257:257 */    return this.value;
/* 258:    */  }
/* 259:    */  
/* 266:    */  public int compareTo(MutableByte other)
/* 267:    */  {
/* 268:268 */    byte anotherVal = other.value;
/* 269:269 */    return this.value == anotherVal ? 0 : this.value < anotherVal ? -1 : 1;
/* 270:    */  }
/* 271:    */  
/* 278:    */  public String toString()
/* 279:    */  {
/* 280:280 */    return String.valueOf(this.value);
/* 281:    */  }
/* 282:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.mutable.MutableByte
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */