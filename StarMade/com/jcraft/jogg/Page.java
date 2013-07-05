/*     */ package com.jcraft.jogg;
/*     */ 
/*     */ public class Page
/*     */ {
/*  30 */   private static int[] crc_lookup = new int[256];
/*     */   public byte[] header_base;
/*     */   public int header;
/*     */   public int header_len;
/*     */   public byte[] body_base;
/*     */   public int body;
/*     */   public int body_len;
/*     */ 
/*     */   private static int crc_entry(int index)
/*     */   {
/*  38 */     int r = index << 24;
/*  39 */     for (int i = 0; i < 8; i++) {
/*  40 */       if ((r & 0x80000000) != 0) {
/*  41 */         r = r << 1 ^ 0x4C11DB7;
/*     */       }
/*     */       else
/*     */       {
/*  47 */         r <<= 1;
/*     */       }
/*     */     }
/*  50 */     return r & 0xFFFFFFFF;
/*     */   }
/*     */ 
/*     */   int version()
/*     */   {
/*  61 */     return this.header_base[(this.header + 4)] & 0xFF;
/*     */   }
/*     */ 
/*     */   int continued() {
/*  65 */     return this.header_base[(this.header + 5)] & 0x1;
/*     */   }
/*     */ 
/*     */   public int bos() {
/*  69 */     return this.header_base[(this.header + 5)] & 0x2;
/*     */   }
/*     */ 
/*     */   public int eos() {
/*  73 */     return this.header_base[(this.header + 5)] & 0x4;
/*     */   }
/*     */ 
/*     */   public long granulepos() {
/*  77 */     long foo = this.header_base[(this.header + 13)] & 0xFF;
/*  78 */     foo = foo << 8 | this.header_base[(this.header + 12)] & 0xFF;
/*  79 */     foo = foo << 8 | this.header_base[(this.header + 11)] & 0xFF;
/*  80 */     foo = foo << 8 | this.header_base[(this.header + 10)] & 0xFF;
/*  81 */     foo = foo << 8 | this.header_base[(this.header + 9)] & 0xFF;
/*  82 */     foo = foo << 8 | this.header_base[(this.header + 8)] & 0xFF;
/*  83 */     foo = foo << 8 | this.header_base[(this.header + 7)] & 0xFF;
/*  84 */     foo = foo << 8 | this.header_base[(this.header + 6)] & 0xFF;
/*  85 */     return foo;
/*     */   }
/*     */ 
/*     */   public int serialno() {
/*  89 */     return this.header_base[(this.header + 14)] & 0xFF | (this.header_base[(this.header + 15)] & 0xFF) << 8 | (this.header_base[(this.header + 16)] & 0xFF) << 16 | (this.header_base[(this.header + 17)] & 0xFF) << 24;
/*     */   }
/*     */ 
/*     */   int pageno()
/*     */   {
/*  95 */     return this.header_base[(this.header + 18)] & 0xFF | (this.header_base[(this.header + 19)] & 0xFF) << 8 | (this.header_base[(this.header + 20)] & 0xFF) << 16 | (this.header_base[(this.header + 21)] & 0xFF) << 24;
/*     */   }
/*     */ 
/*     */   void checksum()
/*     */   {
/* 101 */     int crc_reg = 0;
/*     */ 
/* 103 */     for (int i = 0; i < this.header_len; i++) {
/* 104 */       crc_reg = crc_reg << 8 ^ crc_lookup[(crc_reg >>> 24 & 0xFF ^ this.header_base[(this.header + i)] & 0xFF)];
/*     */     }
/*     */ 
/* 107 */     for (int i = 0; i < this.body_len; i++) {
/* 108 */       crc_reg = crc_reg << 8 ^ crc_lookup[(crc_reg >>> 24 & 0xFF ^ this.body_base[(this.body + i)] & 0xFF)];
/*     */     }
/*     */ 
/* 111 */     this.header_base[(this.header + 22)] = ((byte)crc_reg);
/* 112 */     this.header_base[(this.header + 23)] = ((byte)(crc_reg >>> 8));
/* 113 */     this.header_base[(this.header + 24)] = ((byte)(crc_reg >>> 16));
/* 114 */     this.header_base[(this.header + 25)] = ((byte)(crc_reg >>> 24));
/*     */   }
/*     */ 
/*     */   public Page copy() {
/* 118 */     return copy(new Page());
/*     */   }
/*     */ 
/*     */   public Page copy(Page p) {
/* 122 */     byte[] tmp = new byte[this.header_len];
/* 123 */     System.arraycopy(this.header_base, this.header, tmp, 0, this.header_len);
/* 124 */     p.header_len = this.header_len;
/* 125 */     p.header_base = tmp;
/* 126 */     p.header = 0;
/* 127 */     tmp = new byte[this.body_len];
/* 128 */     System.arraycopy(this.body_base, this.body, tmp, 0, this.body_len);
/* 129 */     p.body_len = this.body_len;
/* 130 */     p.body_base = tmp;
/* 131 */     p.body = 0;
/* 132 */     return p;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  32 */     for (int i = 0; i < crc_lookup.length; i++)
/*  33 */       crc_lookup[i] = crc_entry(i);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jogg.Page
 * JD-Core Version:    0.6.2
 */