/*     */ package com.jcraft.jogg;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class Buffer
/*     */ {
/*     */   private static final int BUFFER_INCREMENT = 256;
/*  32 */   private static final int[] mask = { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, 2147483647, -1 };
/*     */ 
/*  39 */   int ptr = 0;
/*  40 */   byte[] buffer = null;
/*  41 */   int endbit = 0;
/*  42 */   int endbyte = 0;
/*  43 */   int storage = 0;
/*     */ 
/*     */   public void writeinit() {
/*  46 */     this.buffer = new byte[256];
/*  47 */     this.ptr = 0;
/*  48 */     this.buffer[0] = 0;
/*  49 */     this.storage = 256;
/*     */   }
/*     */ 
/*     */   public void write(byte[] s) {
/*  53 */     for (int i = 0; (i < s.length) && 
/*  54 */       (s[i] != 0); i++)
/*     */     {
/*  56 */       write(s[i], 8);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void read(byte[] s, int bytes) {
/*  61 */     int i = 0;
/*  62 */     while (bytes-- != 0)
/*  63 */       s[(i++)] = ((byte)read(8));
/*     */   }
/*     */ 
/*     */   void reset()
/*     */   {
/*  68 */     this.ptr = 0;
/*  69 */     this.buffer[0] = 0;
/*  70 */     this.endbit = (this.endbyte = 0);
/*     */   }
/*     */ 
/*     */   public void writeclear() {
/*  74 */     this.buffer = null;
/*     */   }
/*     */ 
/*     */   public void readinit(byte[] buf, int bytes) {
/*  78 */     readinit(buf, 0, bytes);
/*     */   }
/*     */ 
/*     */   public void readinit(byte[] buf, int start, int bytes) {
/*  82 */     this.ptr = start;
/*  83 */     this.buffer = buf;
/*  84 */     this.endbit = (this.endbyte = 0);
/*  85 */     this.storage = bytes;
/*     */   }
/*     */ 
/*     */   public void write(int value, int bits) {
/*  89 */     if (this.endbyte + 4 >= this.storage) {
/*  90 */       byte[] foo = new byte[this.storage + 256];
/*  91 */       System.arraycopy(this.buffer, 0, foo, 0, this.storage);
/*  92 */       this.buffer = foo;
/*  93 */       this.storage += 256;
/*     */     }
/*     */ 
/*  96 */     value &= mask[bits];
/*  97 */     bits += this.endbit;
/*     */     int tmp78_75 = this.ptr;
/*     */     byte[] tmp78_71 = this.buffer; tmp78_71[tmp78_75] = ((byte)(tmp78_71[tmp78_75] | (byte)(value << this.endbit)));
/*     */ 
/* 100 */     if (bits >= 8) {
/* 101 */       this.buffer[(this.ptr + 1)] = ((byte)(value >>> 8 - this.endbit));
/* 102 */       if (bits >= 16) {
/* 103 */         this.buffer[(this.ptr + 2)] = ((byte)(value >>> 16 - this.endbit));
/* 104 */         if (bits >= 24) {
/* 105 */           this.buffer[(this.ptr + 3)] = ((byte)(value >>> 24 - this.endbit));
/* 106 */           if (bits >= 32) {
/* 107 */             if (this.endbit > 0)
/* 108 */               this.buffer[(this.ptr + 4)] = ((byte)(value >>> 32 - this.endbit));
/*     */             else {
/* 110 */               this.buffer[(this.ptr + 4)] = 0;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 116 */     this.endbyte += bits / 8;
/* 117 */     this.ptr += bits / 8;
/* 118 */     this.endbit = (bits & 0x7);
/*     */   }
/*     */ 
/*     */   public int look(int bits)
/*     */   {
/* 123 */     int m = mask[bits];
/*     */ 
/* 125 */     bits += this.endbit;
/*     */ 
/* 127 */     if ((this.endbyte + 4 >= this.storage) && 
/* 128 */       (this.endbyte + (bits - 1) / 8 >= this.storage)) {
/* 129 */       return -1;
/*     */     }
/*     */ 
/* 132 */     int ret = (this.buffer[this.ptr] & 0xFF) >>> this.endbit;
/* 133 */     if (bits > 8) {
/* 134 */       ret |= (this.buffer[(this.ptr + 1)] & 0xFF) << 8 - this.endbit;
/* 135 */       if (bits > 16) {
/* 136 */         ret |= (this.buffer[(this.ptr + 2)] & 0xFF) << 16 - this.endbit;
/* 137 */         if (bits > 24) {
/* 138 */           ret |= (this.buffer[(this.ptr + 3)] & 0xFF) << 24 - this.endbit;
/* 139 */           if ((bits > 32) && (this.endbit != 0)) {
/* 140 */             ret |= (this.buffer[(this.ptr + 4)] & 0xFF) << 32 - this.endbit;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 145 */     return m & ret;
/*     */   }
/*     */ 
/*     */   public int look1() {
/* 149 */     if (this.endbyte >= this.storage)
/* 150 */       return -1;
/* 151 */     return this.buffer[this.ptr] >> this.endbit & 0x1;
/*     */   }
/*     */ 
/*     */   public void adv(int bits) {
/* 155 */     bits += this.endbit;
/* 156 */     this.ptr += bits / 8;
/* 157 */     this.endbyte += bits / 8;
/* 158 */     this.endbit = (bits & 0x7);
/*     */   }
/*     */ 
/*     */   public void adv1() {
/* 162 */     this.endbit += 1;
/* 163 */     if (this.endbit > 7) {
/* 164 */       this.endbit = 0;
/* 165 */       this.ptr += 1;
/* 166 */       this.endbyte += 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int read(int bits)
/*     */   {
/* 172 */     int m = mask[bits];
/*     */ 
/* 174 */     bits += this.endbit;
/*     */ 
/* 176 */     if (this.endbyte + 4 >= this.storage) {
/* 177 */       int ret = -1;
/* 178 */       if (this.endbyte + (bits - 1) / 8 >= this.storage) {
/* 179 */         this.ptr += bits / 8;
/* 180 */         this.endbyte += bits / 8;
/* 181 */         this.endbit = (bits & 0x7);
/* 182 */         return ret;
/*     */       }
/*     */     }
/*     */ 
/* 186 */     int ret = (this.buffer[this.ptr] & 0xFF) >>> this.endbit;
/* 187 */     if (bits > 8) {
/* 188 */       ret |= (this.buffer[(this.ptr + 1)] & 0xFF) << 8 - this.endbit;
/* 189 */       if (bits > 16) {
/* 190 */         ret |= (this.buffer[(this.ptr + 2)] & 0xFF) << 16 - this.endbit;
/* 191 */         if (bits > 24) {
/* 192 */           ret |= (this.buffer[(this.ptr + 3)] & 0xFF) << 24 - this.endbit;
/* 193 */           if ((bits > 32) && (this.endbit != 0)) {
/* 194 */             ret |= (this.buffer[(this.ptr + 4)] & 0xFF) << 32 - this.endbit;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 200 */     ret &= m;
/*     */ 
/* 202 */     this.ptr += bits / 8;
/* 203 */     this.endbyte += bits / 8;
/* 204 */     this.endbit = (bits & 0x7);
/* 205 */     return ret;
/*     */   }
/*     */ 
/*     */   public int readB(int bits)
/*     */   {
/* 210 */     int m = 32 - bits;
/*     */ 
/* 212 */     bits += this.endbit;
/*     */ 
/* 214 */     if (this.endbyte + 4 >= this.storage)
/*     */     {
/* 216 */       int ret = -1;
/* 217 */       if (this.endbyte * 8 + bits > this.storage * 8) {
/* 218 */         this.ptr += bits / 8;
/* 219 */         this.endbyte += bits / 8;
/* 220 */         this.endbit = (bits & 0x7);
/* 221 */         return ret;
/*     */       }
/*     */     }
/*     */ 
/* 225 */     int ret = (this.buffer[this.ptr] & 0xFF) << 24 + this.endbit;
/* 226 */     if (bits > 8) {
/* 227 */       ret |= (this.buffer[(this.ptr + 1)] & 0xFF) << 16 + this.endbit;
/* 228 */       if (bits > 16) {
/* 229 */         ret |= (this.buffer[(this.ptr + 2)] & 0xFF) << 8 + this.endbit;
/* 230 */         if (bits > 24) {
/* 231 */           ret |= (this.buffer[(this.ptr + 3)] & 0xFF) << this.endbit;
/* 232 */           if ((bits > 32) && (this.endbit != 0))
/* 233 */             ret |= (this.buffer[(this.ptr + 4)] & 0xFF) >> 8 - this.endbit;
/*     */         }
/*     */       }
/*     */     }
/* 237 */     ret = ret >>> (m >> 1) >>> (m + 1 >> 1);
/*     */ 
/* 239 */     this.ptr += bits / 8;
/* 240 */     this.endbyte += bits / 8;
/* 241 */     this.endbit = (bits & 0x7);
/* 242 */     return ret;
/*     */   }
/*     */ 
/*     */   public int read1()
/*     */   {
/* 247 */     if (this.endbyte >= this.storage) {
/* 248 */       int ret = -1;
/* 249 */       this.endbit += 1;
/* 250 */       if (this.endbit > 7) {
/* 251 */         this.endbit = 0;
/* 252 */         this.ptr += 1;
/* 253 */         this.endbyte += 1;
/*     */       }
/* 255 */       return ret;
/*     */     }
/*     */ 
/* 258 */     int ret = this.buffer[this.ptr] >> this.endbit & 0x1;
/*     */ 
/* 260 */     this.endbit += 1;
/* 261 */     if (this.endbit > 7) {
/* 262 */       this.endbit = 0;
/* 263 */       this.ptr += 1;
/* 264 */       this.endbyte += 1;
/*     */     }
/* 266 */     return ret;
/*     */   }
/*     */ 
/*     */   public int bytes() {
/* 270 */     return this.endbyte + (this.endbit + 7) / 8;
/*     */   }
/*     */ 
/*     */   public int bits() {
/* 274 */     return this.endbyte * 8 + this.endbit;
/*     */   }
/*     */ 
/*     */   public byte[] buffer() {
/* 278 */     return this.buffer;
/*     */   }
/*     */ 
/*     */   public static int ilog(int v) {
/* 282 */     int ret = 0;
/* 283 */     while (v > 0) {
/* 284 */       ret++;
/* 285 */       v >>>= 1;
/*     */     }
/* 287 */     return ret;
/*     */   }
/*     */ 
/*     */   public static void report(String in) {
/* 291 */     System.err.println(in);
/* 292 */     System.exit(1);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jogg.Buffer
 * JD-Core Version:    0.6.2
 */