/*     */ package com.jcraft.jorbis;
/*     */ 
/*     */ import com.jcraft.jogg.Buffer;
/*     */ import com.jcraft.jogg.Packet;
/*     */ 
/*     */ public class Comment
/*     */ {
/*  34 */   private static byte[] _vorbis = "vorbis".getBytes();
/*  35 */   private static byte[] _vendor = "Xiphophorus libVorbis I 20000508".getBytes();
/*     */   private static final int OV_EIMPL = -130;
/*     */   public byte[][] user_comments;
/*     */   public int[] comment_lengths;
/*     */   public int comments;
/*     */   public byte[] vendor;
/*     */ 
/*     */   public void init()
/*     */   {
/*  46 */     this.user_comments = ((byte[][])null);
/*  47 */     this.comments = 0;
/*  48 */     this.vendor = null;
/*     */   }
/*     */ 
/*     */   public void add(String comment) {
/*  52 */     add(comment.getBytes());
/*     */   }
/*     */ 
/*     */   private void add(byte[] comment) {
/*  56 */     byte[][] foo = new byte[this.comments + 2][];
/*  57 */     if (this.user_comments != null) {
/*  58 */       System.arraycopy(this.user_comments, 0, foo, 0, this.comments);
/*     */     }
/*  60 */     this.user_comments = foo;
/*     */ 
/*  62 */     int[] goo = new int[this.comments + 2];
/*  63 */     if (this.comment_lengths != null) {
/*  64 */       System.arraycopy(this.comment_lengths, 0, goo, 0, this.comments);
/*     */     }
/*  66 */     this.comment_lengths = goo;
/*     */ 
/*  68 */     byte[] bar = new byte[comment.length + 1];
/*  69 */     System.arraycopy(comment, 0, bar, 0, comment.length);
/*  70 */     this.user_comments[this.comments] = bar;
/*  71 */     this.comment_lengths[this.comments] = comment.length;
/*  72 */     this.comments += 1;
/*  73 */     this.user_comments[this.comments] = null;
/*     */   }
/*     */ 
/*     */   public void add_tag(String tag, String contents) {
/*  77 */     if (contents == null)
/*  78 */       contents = "";
/*  79 */     add(tag + "=" + contents);
/*     */   }
/*     */ 
/*     */   static boolean tagcompare(byte[] s1, byte[] s2, int n) {
/*  83 */     int c = 0;
/*     */ 
/*  85 */     while (c < n) {
/*  86 */       byte u1 = s1[c];
/*  87 */       byte u2 = s2[c];
/*  88 */       if ((90 >= u1) && (u1 >= 65))
/*  89 */         u1 = (byte)(u1 - 65 + 97);
/*  90 */       if ((90 >= u2) && (u2 >= 65))
/*  91 */         u2 = (byte)(u2 - 65 + 97);
/*  92 */       if (u1 != u2) {
/*  93 */         return false;
/*     */       }
/*  95 */       c++;
/*     */     }
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */   public String query(String tag) {
/* 101 */     return query(tag, 0);
/*     */   }
/*     */ 
/*     */   public String query(String tag, int count) {
/* 105 */     int foo = query(tag.getBytes(), count);
/* 106 */     if (foo == -1)
/* 107 */       return null;
/* 108 */     byte[] comment = this.user_comments[foo];
/* 109 */     for (int i = 0; i < this.comment_lengths[foo]; i++) {
/* 110 */       if (comment[i] == 61) {
/* 111 */         return new String(comment, i + 1, this.comment_lengths[foo] - (i + 1));
/*     */       }
/*     */     }
/* 114 */     return null;
/*     */   }
/*     */ 
/*     */   private int query(byte[] tag, int count) {
/* 118 */     int i = 0;
/* 119 */     int found = 0;
/* 120 */     int fulltaglen = tag.length + 1;
/* 121 */     byte[] fulltag = new byte[fulltaglen];
/* 122 */     System.arraycopy(tag, 0, fulltag, 0, tag.length);
/* 123 */     fulltag[tag.length] = 61;
/*     */ 
/* 125 */     for (i = 0; i < this.comments; i++) {
/* 126 */       if (tagcompare(this.user_comments[i], fulltag, fulltaglen)) {
/* 127 */         if (count == found)
/*     */         {
/* 130 */           return i;
/*     */         }
/*     */ 
/* 133 */         found++;
/*     */       }
/*     */     }
/*     */ 
/* 137 */     return -1;
/*     */   }
/*     */ 
/*     */   int unpack(Buffer opb) {
/* 141 */     int vendorlen = opb.read(32);
/* 142 */     if (vendorlen < 0) {
/* 143 */       clear();
/* 144 */       return -1;
/*     */     }
/* 146 */     this.vendor = new byte[vendorlen + 1];
/* 147 */     opb.read(this.vendor, vendorlen);
/* 148 */     this.comments = opb.read(32);
/* 149 */     if (this.comments < 0) {
/* 150 */       clear();
/* 151 */       return -1;
/*     */     }
/* 153 */     this.user_comments = new byte[this.comments + 1][];
/* 154 */     this.comment_lengths = new int[this.comments + 1];
/*     */ 
/* 156 */     for (int i = 0; i < this.comments; i++) {
/* 157 */       int len = opb.read(32);
/* 158 */       if (len < 0) {
/* 159 */         clear();
/* 160 */         return -1;
/*     */       }
/* 162 */       this.comment_lengths[i] = len;
/* 163 */       this.user_comments[i] = new byte[len + 1];
/* 164 */       opb.read(this.user_comments[i], len);
/*     */     }
/* 166 */     if (opb.read(1) != 1) {
/* 167 */       clear();
/* 168 */       return -1;
/*     */     }
/*     */ 
/* 171 */     return 0;
/*     */   }
/*     */ 
/*     */   int pack(Buffer opb)
/*     */   {
/* 176 */     opb.write(3, 8);
/* 177 */     opb.write(_vorbis);
/*     */ 
/* 180 */     opb.write(_vendor.length, 32);
/* 181 */     opb.write(_vendor);
/*     */ 
/* 184 */     opb.write(this.comments, 32);
/* 185 */     if (this.comments != 0) {
/* 186 */       for (int i = 0; i < this.comments; i++) {
/* 187 */         if (this.user_comments[i] != null) {
/* 188 */           opb.write(this.comment_lengths[i], 32);
/* 189 */           opb.write(this.user_comments[i]);
/*     */         }
/*     */         else {
/* 192 */           opb.write(0, 32);
/*     */         }
/*     */       }
/*     */     }
/* 196 */     opb.write(1, 1);
/* 197 */     return 0;
/*     */   }
/*     */ 
/*     */   public int header_out(Packet op) {
/* 201 */     Buffer opb = new Buffer();
/* 202 */     opb.writeinit();
/*     */ 
/* 204 */     if (pack(opb) != 0) {
/* 205 */       return -130;
/*     */     }
/* 207 */     op.packet_base = new byte[opb.bytes()];
/* 208 */     op.packet = 0;
/* 209 */     op.bytes = opb.bytes();
/* 210 */     System.arraycopy(opb.buffer(), 0, op.packet_base, 0, op.bytes);
/* 211 */     op.b_o_s = 0;
/* 212 */     op.e_o_s = 0;
/* 213 */     op.granulepos = 0L;
/* 214 */     return 0;
/*     */   }
/*     */ 
/*     */   void clear() {
/* 218 */     for (int i = 0; i < this.comments; i++)
/* 219 */       this.user_comments[i] = null;
/* 220 */     this.user_comments = ((byte[][])null);
/* 221 */     this.vendor = null;
/*     */   }
/*     */ 
/*     */   public String getVendor() {
/* 225 */     return new String(this.vendor, 0, this.vendor.length - 1);
/*     */   }
/*     */ 
/*     */   public String getComment(int i) {
/* 229 */     if (this.comments <= i)
/* 230 */       return null;
/* 231 */     return new String(this.user_comments[i], 0, this.user_comments[i].length - 1);
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 235 */     String foo = "Vendor: " + new String(this.vendor, 0, this.vendor.length - 1);
/* 236 */     for (int i = 0; i < this.comments; i++) {
/* 237 */       foo = foo + "\nComment: " + new String(this.user_comments[i], 0, this.user_comments[i].length - 1);
/*     */     }
/*     */ 
/* 240 */     foo = foo + "\n";
/* 241 */     return foo;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Comment
 * JD-Core Version:    0.6.2
 */