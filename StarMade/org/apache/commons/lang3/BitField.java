/*   1:    */package org.apache.commons.lang3;
/*   2:    */
/*  13:    */public class BitField
/*  14:    */{
/*  15:    */  private final int _mask;
/*  16:    */  
/*  25:    */  private final int _shift_count;
/*  26:    */  
/*  36:    */  public BitField(int mask)
/*  37:    */  {
/*  38: 38 */    this._mask = mask;
/*  39: 39 */    int count = 0;
/*  40: 40 */    int bit_pattern = mask;
/*  41:    */    
/*  42: 42 */    if (bit_pattern != 0) {
/*  43: 43 */      while ((bit_pattern & 0x1) == 0) {
/*  44: 44 */        count++;
/*  45: 45 */        bit_pattern >>= 1;
/*  46:    */      }
/*  47:    */    }
/*  48: 48 */    this._shift_count = count;
/*  49:    */  }
/*  50:    */  
/*  64:    */  public int getValue(int holder)
/*  65:    */  {
/*  66: 66 */    return getRawValue(holder) >> this._shift_count;
/*  67:    */  }
/*  68:    */  
/*  82:    */  public short getShortValue(short holder)
/*  83:    */  {
/*  84: 84 */    return (short)getValue(holder);
/*  85:    */  }
/*  86:    */  
/*  93:    */  public int getRawValue(int holder)
/*  94:    */  {
/*  95: 95 */    return holder & this._mask;
/*  96:    */  }
/*  97:    */  
/* 104:    */  public short getShortRawValue(short holder)
/* 105:    */  {
/* 106:106 */    return (short)getRawValue(holder);
/* 107:    */  }
/* 108:    */  
/* 121:    */  public boolean isSet(int holder)
/* 122:    */  {
/* 123:123 */    return (holder & this._mask) != 0;
/* 124:    */  }
/* 125:    */  
/* 137:    */  public boolean isAllSet(int holder)
/* 138:    */  {
/* 139:139 */    return (holder & this._mask) == this._mask;
/* 140:    */  }
/* 141:    */  
/* 151:    */  public int setValue(int holder, int value)
/* 152:    */  {
/* 153:153 */    return holder & (this._mask ^ 0xFFFFFFFF) | value << this._shift_count & this._mask;
/* 154:    */  }
/* 155:    */  
/* 165:    */  public short setShortValue(short holder, short value)
/* 166:    */  {
/* 167:167 */    return (short)setValue(holder, value);
/* 168:    */  }
/* 169:    */  
/* 177:    */  public int clear(int holder)
/* 178:    */  {
/* 179:179 */    return holder & (this._mask ^ 0xFFFFFFFF);
/* 180:    */  }
/* 181:    */  
/* 189:    */  public short clearShort(short holder)
/* 190:    */  {
/* 191:191 */    return (short)clear(holder);
/* 192:    */  }
/* 193:    */  
/* 202:    */  public byte clearByte(byte holder)
/* 203:    */  {
/* 204:204 */    return (byte)clear(holder);
/* 205:    */  }
/* 206:    */  
/* 214:    */  public int set(int holder)
/* 215:    */  {
/* 216:216 */    return holder | this._mask;
/* 217:    */  }
/* 218:    */  
/* 226:    */  public short setShort(short holder)
/* 227:    */  {
/* 228:228 */    return (short)set(holder);
/* 229:    */  }
/* 230:    */  
/* 239:    */  public byte setByte(byte holder)
/* 240:    */  {
/* 241:241 */    return (byte)set(holder);
/* 242:    */  }
/* 243:    */  
/* 252:    */  public int setBoolean(int holder, boolean flag)
/* 253:    */  {
/* 254:254 */    return flag ? set(holder) : clear(holder);
/* 255:    */  }
/* 256:    */  
/* 265:    */  public short setShortBoolean(short holder, boolean flag)
/* 266:    */  {
/* 267:267 */    return flag ? setShort(holder) : clearShort(holder);
/* 268:    */  }
/* 269:    */  
/* 278:    */  public byte setByteBoolean(byte holder, boolean flag)
/* 279:    */  {
/* 280:280 */    return flag ? setByte(holder) : clearByte(holder);
/* 281:    */  }
/* 282:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.BitField
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */