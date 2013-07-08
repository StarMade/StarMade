/*   1:    */package org.apache.commons.lang3.mutable;
/*   2:    */
/*  13:    */public class MutableFloat
/*  14:    */  extends Number
/*  15:    */  implements Comparable<MutableFloat>, Mutable<Number>
/*  16:    */{
/*  17:    */  private static final long serialVersionUID = 5787169186L;
/*  18:    */  
/*  28:    */  private float value;
/*  29:    */  
/*  40:    */  public MutableFloat() {}
/*  41:    */  
/*  52:    */  public MutableFloat(float value)
/*  53:    */  {
/*  54: 54 */    this.value = value;
/*  55:    */  }
/*  56:    */  
/*  63:    */  public MutableFloat(Number value)
/*  64:    */  {
/*  65: 65 */    this.value = value.floatValue();
/*  66:    */  }
/*  67:    */  
/*  74:    */  public MutableFloat(String value)
/*  75:    */    throws NumberFormatException
/*  76:    */  {
/*  77: 77 */    this.value = Float.parseFloat(value);
/*  78:    */  }
/*  79:    */  
/*  85:    */  public Float getValue()
/*  86:    */  {
/*  87: 87 */    return Float.valueOf(this.value);
/*  88:    */  }
/*  89:    */  
/*  94:    */  public void setValue(float value)
/*  95:    */  {
/*  96: 96 */    this.value = value;
/*  97:    */  }
/*  98:    */  
/* 104:    */  public void setValue(Number value)
/* 105:    */  {
/* 106:106 */    this.value = value.floatValue();
/* 107:    */  }
/* 108:    */  
/* 114:    */  public boolean isNaN()
/* 115:    */  {
/* 116:116 */    return Float.isNaN(this.value);
/* 117:    */  }
/* 118:    */  
/* 123:    */  public boolean isInfinite()
/* 124:    */  {
/* 125:125 */    return Float.isInfinite(this.value);
/* 126:    */  }
/* 127:    */  
/* 133:    */  public void increment()
/* 134:    */  {
/* 135:135 */    this.value += 1.0F;
/* 136:    */  }
/* 137:    */  
/* 142:    */  public void decrement()
/* 143:    */  {
/* 144:144 */    this.value -= 1.0F;
/* 145:    */  }
/* 146:    */  
/* 153:    */  public void add(float operand)
/* 154:    */  {
/* 155:155 */    this.value += operand;
/* 156:    */  }
/* 157:    */  
/* 164:    */  public void add(Number operand)
/* 165:    */  {
/* 166:166 */    this.value += operand.floatValue();
/* 167:    */  }
/* 168:    */  
/* 174:    */  public void subtract(float operand)
/* 175:    */  {
/* 176:176 */    this.value -= operand;
/* 177:    */  }
/* 178:    */  
/* 185:    */  public void subtract(Number operand)
/* 186:    */  {
/* 187:187 */    this.value -= operand.floatValue();
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
/* 219:219 */    return this.value;
/* 220:    */  }
/* 221:    */  
/* 227:    */  public double doubleValue()
/* 228:    */  {
/* 229:229 */    return this.value;
/* 230:    */  }
/* 231:    */  
/* 237:    */  public Float toFloat()
/* 238:    */  {
/* 239:239 */    return Float.valueOf(floatValue());
/* 240:    */  }
/* 241:    */  
/* 274:    */  public boolean equals(Object obj)
/* 275:    */  {
/* 276:276 */    return ((obj instanceof MutableFloat)) && (Float.floatToIntBits(((MutableFloat)obj).value) == Float.floatToIntBits(this.value));
/* 277:    */  }
/* 278:    */  
/* 285:    */  public int hashCode()
/* 286:    */  {
/* 287:287 */    return Float.floatToIntBits(this.value);
/* 288:    */  }
/* 289:    */  
/* 296:    */  public int compareTo(MutableFloat other)
/* 297:    */  {
/* 298:298 */    float anotherVal = other.value;
/* 299:299 */    return Float.compare(this.value, anotherVal);
/* 300:    */  }
/* 301:    */  
/* 308:    */  public String toString()
/* 309:    */  {
/* 310:310 */    return String.valueOf(this.value);
/* 311:    */  }
/* 312:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.mutable.MutableFloat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */