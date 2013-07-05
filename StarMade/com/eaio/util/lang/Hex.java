/*     */ package com.eaio.util.lang;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ public final class Hex
/*     */ {
/*  43 */   private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
/*     */ 
/*     */   public static Appendable append(Appendable a, short in)
/*     */   {
/*  54 */     return append(a, in, 4);
/*     */   }
/*     */ 
/*     */   public static Appendable append(Appendable a, short in, int length)
/*     */   {
/*  66 */     return append(a, in, length);
/*     */   }
/*     */ 
/*     */   public static Appendable append(Appendable a, int in)
/*     */   {
/*  77 */     return append(a, in, 8);
/*     */   }
/*     */ 
/*     */   public static Appendable append(Appendable a, int in, int length)
/*     */   {
/*  89 */     return append(a, in, length);
/*     */   }
/*     */ 
/*     */   public static Appendable append(Appendable a, long in)
/*     */   {
/* 100 */     return append(a, in, 16);
/*     */   }
/*     */ 
/*     */   public static Appendable append(Appendable a, long in, int length)
/*     */   {
/*     */     try
/*     */     {
/* 113 */       int lim = (length << 2) - 4;
/* 114 */       while (lim >= 0) {
/* 115 */         a.append(DIGITS[((byte)(int)(in >> lim) & 0xF)]);
/* 116 */         lim -= 4;
/*     */       }
/*     */     }
/*     */     catch (IOException ex)
/*     */     {
/*     */     }
/* 122 */     return a;
/*     */   }
/*     */ 
/*     */   public static Appendable append(Appendable a, byte[] bytes)
/*     */   {
/*     */     try
/*     */     {
/* 134 */       for (byte b : bytes) {
/* 135 */         a.append(DIGITS[((byte)((b & 0xF0) >> 4))]);
/* 136 */         a.append(DIGITS[((byte)(b & 0xF))]);
/*     */       }
/*     */     }
/*     */     catch (IOException ex)
/*     */     {
/*     */     }
/* 142 */     return a;
/*     */   }
/*     */ 
/*     */   public static long parseLong(CharSequence s)
/*     */   {
/* 156 */     long out = 0L;
/* 157 */     byte shifts = 0;
/*     */ 
/* 159 */     for (int i = 0; (i < s.length()) && (shifts < 16); i++) {
/* 160 */       char c = s.charAt(i);
/* 161 */       if ((c > '/') && (c < ':')) {
/* 162 */         shifts = (byte)(shifts + 1);
/* 163 */         out <<= 4;
/* 164 */         out |= c - '0';
/*     */       }
/* 166 */       else if ((c > '@') && (c < 'G')) {
/* 167 */         shifts = (byte)(shifts + 1);
/* 168 */         out <<= 4;
/* 169 */         out |= c - '7';
/*     */       }
/* 171 */       else if ((c > '`') && (c < 'g')) {
/* 172 */         shifts = (byte)(shifts + 1);
/* 173 */         out <<= 4;
/* 174 */         out |= c - 'W';
/*     */       }
/*     */     }
/* 177 */     return out;
/*     */   }
/*     */ 
/*     */   public static short parseShort(String s)
/*     */   {
/* 191 */     short out = 0;
/* 192 */     byte shifts = 0;
/*     */ 
/* 194 */     for (int i = 0; (i < s.length()) && (shifts < 4); i++) {
/* 195 */       char c = s.charAt(i);
/* 196 */       if ((c > '/') && (c < ':')) {
/* 197 */         shifts = (byte)(shifts + 1);
/* 198 */         out = (short)(out << 4);
/* 199 */         out = (short)(out | c - '0');
/*     */       }
/* 201 */       else if ((c > '@') && (c < 'G')) {
/* 202 */         shifts = (byte)(shifts + 1);
/* 203 */         out = (short)(out << 4);
/* 204 */         out = (short)(out | c - '7');
/*     */       }
/* 206 */       else if ((c > '`') && (c < 'g')) {
/* 207 */         shifts = (byte)(shifts + 1);
/* 208 */         out = (short)(out << 4);
/* 209 */         out = (short)(out | c - 'W');
/*     */       }
/*     */     }
/* 212 */     return out;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.eaio.util.lang.Hex
 * JD-Core Version:    0.6.2
 */