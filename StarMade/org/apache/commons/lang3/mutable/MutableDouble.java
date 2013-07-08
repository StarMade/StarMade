/*   1:    */package org.apache.commons.lang3.mutable;
/*   2:    */
/*  13:    */public class MutableDouble
/*  14:    */  extends Number
/*  15:    */  implements Comparable<MutableDouble>, Mutable<Number>
/*  16:    */{
/*  17:    */  private static final long serialVersionUID = 1587163916L;
/*  18:    */  
/*  28:    */  private double value;
/*  29:    */  
/*  40:    */  public MutableDouble() {}
/*  41:    */  
/*  52:    */  public MutableDouble(double value)
/*  53:    */  {
/*  54: 54 */    this.value = value;
/*  55:    */  }
/*  56:    */  
/*  63:    */  public MutableDouble(Number value)
/*  64:    */  {
/*  65: 65 */    this.value = value.doubleValue();
/*  66:    */  }
/*  67:    */  
/*  74:    */  public MutableDouble(String value)
/*  75:    */    throws NumberFormatException
/*  76:    */  {
/*  77: 77 */    this.value = Double.parseDouble(value);
/*  78:    */  }
/*  79:    */  
/*  85:    */  public Double getValue()
/*  86:    */  {
/*  87: 87 */    return Double.valueOf(this.value);
/*  88:    */  }
/*  89:    */  
/*  94:    */  public void setValue(double value)
/*  95:    */  {
/*  96: 96 */    this.value = value;
/*  97:    */  }
/*  98:    */  
/* 104:    */  public void setValue(Number value)
/* 105:    */  {
/* 106:106 */    this.value = value.doubleValue();
/* 107:    */  }
/* 108:    */  
/* 114:    */  public boolean isNaN()
/* 115:    */  {
/* 116:116 */    return Double.isNaN(this.value);
/* 117:    */  }
/* 118:    */  
/* 123:    */  public boolean isInfinite()
/* 124:    */  {
/* 125:125 */    return Double.isInfinite(this.value);
/* 126:    */  }
/* 127:    */  
/* 133:    */  public void increment()
/* 134:    */  {
/* 135:135 */    this.value += 1.0D;
/* 136:    */  }
/* 137:    */  
/* 142:    */  public void decrement()
/* 143:    */  {
/* 144:144 */    this.value -= 1.0D;
/* 145:    */  }
/* 146:    */  
/* 153:    */  public void add(double operand)
/* 154:    */  {
/* 155:155 */    this.value += operand;
/* 156:    */  }
/* 157:    */  
/* 164:    */  public void add(Number operand)
/* 165:    */  {
/* 166:166 */    this.value += operand.doubleValue();
/* 167:    */  }
/* 168:    */  
/* 174:    */  public void subtract(double operand)
/* 175:    */  {
/* 176:176 */    this.value -= operand;
/* 177:    */  }
/* 178:    */  
/* 185:    */  public void subtract(Number operand)
/* 186:    */  {
/* 187:187 */    this.value -= operand.doubleValue();
/* 188:    */  }
/* 189:    */  
/* 197:    */  public int intValue()
/* 198:    */  {
/* 199:199 */    return (int)this.value;
/* 200:    */  }
/* 201:    */  
/* 207:    */  public long longValue()
/* 208:    */  {
/* 209:209 */    return this.value;
/* 210:    */  }
/* 211:    */  
/* 217:    */  public float floatValue()
/* 218:    */  {
/* 219:219 */    return (float)this.value;
/* 220:    */  }
/* 221:    */  
/* 227:    */  public double doubleValue()
/* 228:    */  {
/* 229:229 */    return this.value;
/* 230:    */  }
/* 231:    */  
/* 237:    */  public Double toDouble()
/* 238:    */  {
/* 239:239 */    return Double.valueOf(doubleValue());
/* 240:    */  }
/* 241:    */  
/* 272:    */  public boolean equals(Object obj)
/* 273:    */  {
/* 274:274 */    return ((obj instanceof MutableDouble)) && (Double.doubleToLongBits(((MutableDouble)obj).value) == Double.doubleToLongBits(this.value));
/* 275:    */  }
/* 276:    */  
/* 283:    */  public int hashCode()
/* 284:    */  {
/* 285:285 */    long bits = Double.doubleToLongBits(this.value);
/* 286:286 */    return (int)(bits ^ bits >>> 32);
/* 287:    */  }
/* 288:    */  
/* 295:    */  public int compareTo(MutableDouble other)
/* 296:    */  {
/* 297:297 */    double anotherVal = other.value;
/* 298:298 */    return Double.compare(this.value, anotherVal);
/* 299:    */  }
/* 300:    */  
/* 307:    */  public String toString()
/* 308:    */  {
/* 309:309 */    return String.valueOf(this.value);
/* 310:    */  }
/* 311:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.mutable.MutableDouble
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */