/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ public class BitField
/*     */ {
/*     */   private final int _mask;
/*     */   private final int _shift_count;
/*     */ 
/*     */   public BitField(int mask)
/*     */   {
/*  38 */     this._mask = mask;
/*  39 */     int count = 0;
/*  40 */     int bit_pattern = mask;
/*     */ 
/*  42 */     if (bit_pattern != 0) {
/*  43 */       while ((bit_pattern & 0x1) == 0) {
/*  44 */         count++;
/*  45 */         bit_pattern >>= 1;
/*     */       }
/*     */     }
/*  48 */     this._shift_count = count;
/*     */   }
/*     */ 
/*     */   public int getValue(int holder)
/*     */   {
/*  66 */     return getRawValue(holder) >> this._shift_count;
/*     */   }
/*     */ 
/*     */   public short getShortValue(short holder)
/*     */   {
/*  84 */     return (short)getValue(holder);
/*     */   }
/*     */ 
/*     */   public int getRawValue(int holder)
/*     */   {
/*  95 */     return holder & this._mask;
/*     */   }
/*     */ 
/*     */   public short getShortRawValue(short holder)
/*     */   {
/* 106 */     return (short)getRawValue(holder);
/*     */   }
/*     */ 
/*     */   public boolean isSet(int holder)
/*     */   {
/* 123 */     return (holder & this._mask) != 0;
/*     */   }
/*     */ 
/*     */   public boolean isAllSet(int holder)
/*     */   {
/* 139 */     return (holder & this._mask) == this._mask;
/*     */   }
/*     */ 
/*     */   public int setValue(int holder, int value)
/*     */   {
/* 153 */     return holder & (this._mask ^ 0xFFFFFFFF) | value << this._shift_count & this._mask;
/*     */   }
/*     */ 
/*     */   public short setShortValue(short holder, short value)
/*     */   {
/* 167 */     return (short)setValue(holder, value);
/*     */   }
/*     */ 
/*     */   public int clear(int holder)
/*     */   {
/* 179 */     return holder & (this._mask ^ 0xFFFFFFFF);
/*     */   }
/*     */ 
/*     */   public short clearShort(short holder)
/*     */   {
/* 191 */     return (short)clear(holder);
/*     */   }
/*     */ 
/*     */   public byte clearByte(byte holder)
/*     */   {
/* 204 */     return (byte)clear(holder);
/*     */   }
/*     */ 
/*     */   public int set(int holder)
/*     */   {
/* 216 */     return holder | this._mask;
/*     */   }
/*     */ 
/*     */   public short setShort(short holder)
/*     */   {
/* 228 */     return (short)set(holder);
/*     */   }
/*     */ 
/*     */   public byte setByte(byte holder)
/*     */   {
/* 241 */     return (byte)set(holder);
/*     */   }
/*     */ 
/*     */   public int setBoolean(int holder, boolean flag)
/*     */   {
/* 254 */     return flag ? set(holder) : clear(holder);
/*     */   }
/*     */ 
/*     */   public short setShortBoolean(short holder, boolean flag)
/*     */   {
/* 267 */     return flag ? setShort(holder) : clearShort(holder);
/*     */   }
/*     */ 
/*     */   public byte setByteBoolean(byte holder, boolean flag)
/*     */   {
/* 280 */     return flag ? setByte(holder) : clearByte(holder);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.BitField
 * JD-Core Version:    0.6.2
 */