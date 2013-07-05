/*     */ package com.jcraft.jogg;
/*     */ 
/*     */ public class SyncState
/*     */ {
/*     */   public byte[] data;
/*     */   int storage;
/*     */   int fill;
/*     */   int returned;
/*     */   int unsynced;
/*     */   int headerbytes;
/*     */   int bodybytes;
/* 102 */   private Page pageseek = new Page();
/* 103 */   private byte[] chksum = new byte[4];
/*     */ 
/*     */   public int clear()
/*     */   {
/*  57 */     this.data = null;
/*  58 */     return 0;
/*     */   }
/*     */ 
/*     */   public int buffer(int size)
/*     */   {
/*  63 */     if (this.returned != 0) {
/*  64 */       this.fill -= this.returned;
/*  65 */       if (this.fill > 0) {
/*  66 */         System.arraycopy(this.data, this.returned, this.data, 0, this.fill);
/*     */       }
/*  68 */       this.returned = 0;
/*     */     }
/*     */ 
/*  71 */     if (size > this.storage - this.fill)
/*     */     {
/*  73 */       int newsize = size + this.fill + 4096;
/*  74 */       if (this.data != null) {
/*  75 */         byte[] foo = new byte[newsize];
/*  76 */         System.arraycopy(this.data, 0, foo, 0, this.data.length);
/*  77 */         this.data = foo;
/*     */       }
/*     */       else {
/*  80 */         this.data = new byte[newsize];
/*     */       }
/*  82 */       this.storage = newsize;
/*     */     }
/*     */ 
/*  85 */     return this.fill;
/*     */   }
/*     */ 
/*     */   public int wrote(int bytes) {
/*  89 */     if (this.fill + bytes > this.storage)
/*  90 */       return -1;
/*  91 */     this.fill += bytes;
/*  92 */     return 0;
/*     */   }
/*     */ 
/*     */   public int pageseek(Page og)
/*     */   {
/* 106 */     int page = this.returned;
/*     */ 
/* 108 */     int bytes = this.fill - this.returned;
/*     */ 
/* 110 */     if (this.headerbytes == 0)
/*     */     {
/* 112 */       if (bytes < 27) {
/* 113 */         return 0;
/*     */       }
/*     */ 
/* 116 */       if ((this.data[page] != 79) || (this.data[(page + 1)] != 103) || (this.data[(page + 2)] != 103) || (this.data[(page + 3)] != 83))
/*     */       {
/* 118 */         this.headerbytes = 0;
/* 119 */         this.bodybytes = 0;
/*     */ 
/* 122 */         int next = 0;
/* 123 */         for (int ii = 0; ii < bytes - 1; ii++) {
/* 124 */           if (this.data[(page + 1 + ii)] == 79) {
/* 125 */             next = page + 1 + ii;
/* 126 */             break;
/*     */           }
/*     */         }
/*     */ 
/* 130 */         if (next == 0) {
/* 131 */           next = this.fill;
/*     */         }
/* 133 */         this.returned = next;
/* 134 */         return -(next - page);
/*     */       }
/* 136 */       int _headerbytes = (this.data[(page + 26)] & 0xFF) + 27;
/* 137 */       if (bytes < _headerbytes) {
/* 138 */         return 0;
/*     */       }
/*     */ 
/* 142 */       for (int i = 0; i < (this.data[(page + 26)] & 0xFF); i++) {
/* 143 */         this.bodybytes += (this.data[(page + 27 + i)] & 0xFF);
/*     */       }
/* 145 */       this.headerbytes = _headerbytes;
/*     */     }
/*     */ 
/* 148 */     if (this.bodybytes + this.headerbytes > bytes) {
/* 149 */       return 0;
/*     */     }
/*     */ 
/* 152 */     synchronized (this.chksum)
/*     */     {
/* 155 */       System.arraycopy(this.data, page + 22, this.chksum, 0, 4);
/* 156 */       this.data[(page + 22)] = 0;
/* 157 */       this.data[(page + 23)] = 0;
/* 158 */       this.data[(page + 24)] = 0;
/* 159 */       this.data[(page + 25)] = 0;
/*     */ 
/* 162 */       Page log = this.pageseek;
/* 163 */       log.header_base = this.data;
/* 164 */       log.header = page;
/* 165 */       log.header_len = this.headerbytes;
/*     */ 
/* 167 */       log.body_base = this.data;
/* 168 */       log.body = (page + this.headerbytes);
/* 169 */       log.body_len = this.bodybytes;
/* 170 */       log.checksum();
/*     */ 
/* 173 */       if ((this.chksum[0] != this.data[(page + 22)]) || (this.chksum[1] != this.data[(page + 23)]) || (this.chksum[2] != this.data[(page + 24)]) || (this.chksum[3] != this.data[(page + 25)]))
/*     */       {
/* 177 */         System.arraycopy(this.chksum, 0, this.data, page + 22, 4);
/*     */ 
/* 180 */         this.headerbytes = 0;
/* 181 */         this.bodybytes = 0;
/*     */ 
/* 183 */         int next = 0;
/* 184 */         for (int ii = 0; ii < bytes - 1; ii++) {
/* 185 */           if (this.data[(page + 1 + ii)] == 79) {
/* 186 */             next = page + 1 + ii;
/* 187 */             break;
/*     */           }
/*     */         }
/*     */ 
/* 191 */         if (next == 0)
/* 192 */           next = this.fill;
/* 193 */         this.returned = next;
/* 194 */         return -(next - page);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 200 */     page = this.returned;
/*     */ 
/* 202 */     if (og != null) {
/* 203 */       og.header_base = this.data;
/* 204 */       og.header = page;
/* 205 */       og.header_len = this.headerbytes;
/* 206 */       og.body_base = this.data;
/* 207 */       og.body = (page + this.headerbytes);
/* 208 */       og.body_len = this.bodybytes;
/*     */     }
/*     */ 
/* 211 */     this.unsynced = 0;
/* 212 */     this.returned += (bytes = this.headerbytes + this.bodybytes);
/* 213 */     this.headerbytes = 0;
/* 214 */     this.bodybytes = 0;
/* 215 */     return bytes;
/*     */   }
/*     */ 
/*     */   public int pageout(Page og)
/*     */   {
/*     */     while (true)
/*     */     {
/* 236 */       int ret = pageseek(og);
/* 237 */       if (ret > 0)
/*     */       {
/* 239 */         return 1;
/*     */       }
/* 241 */       if (ret == 0)
/*     */       {
/* 243 */         return 0;
/*     */       }
/*     */ 
/* 247 */       if (this.unsynced == 0) {
/* 248 */         this.unsynced = 1;
/* 249 */         return -1;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public int reset()
/*     */   {
/* 257 */     this.fill = 0;
/* 258 */     this.returned = 0;
/* 259 */     this.unsynced = 0;
/* 260 */     this.headerbytes = 0;
/* 261 */     this.bodybytes = 0;
/* 262 */     return 0;
/*     */   }
/*     */ 
/*     */   public void init() {
/*     */   }
/*     */ 
/*     */   public int getDataOffset() {
/* 269 */     return this.returned;
/*     */   }
/*     */ 
/*     */   public int getBufferOffset() {
/* 273 */     return this.fill;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jogg.SyncState
 * JD-Core Version:    0.6.2
 */