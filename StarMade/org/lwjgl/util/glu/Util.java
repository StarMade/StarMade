/*     */ package org.lwjgl.util.glu;
/*     */ 
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class Util
/*     */ {
/*  54 */   private static IntBuffer scratch = BufferUtils.createIntBuffer(16);
/*     */ 
/*     */   protected static int ceil(int a, int b)
/*     */   {
/*  65 */     return a % b == 0 ? a / b : a / b + 1;
/*     */   }
/*     */ 
/*     */   protected static float[] normalize(float[] v)
/*     */   {
/*  78 */     float r = (float)Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
/*  79 */     if (r == 0.0D) {
/*  80 */       return v;
/*     */     }
/*  82 */     r = 1.0F / r;
/*     */ 
/*  84 */     v[0] *= r;
/*  85 */     v[1] *= r;
/*  86 */     v[2] *= r;
/*     */ 
/*  88 */     return v;
/*     */   }
/*     */ 
/*     */   protected static void cross(float[] v1, float[] v2, float[] result)
/*     */   {
/*  99 */     result[0] = (v1[1] * v2[2] - v1[2] * v2[1]);
/* 100 */     result[1] = (v1[2] * v2[0] - v1[0] * v2[2]);
/* 101 */     result[2] = (v1[0] * v2[1] - v1[1] * v2[0]);
/*     */   }
/*     */ 
/*     */   protected static int compPerPix(int format)
/*     */   {
/* 113 */     switch (format) {
/*     */     case 6400:
/*     */     case 6401:
/*     */     case 6402:
/*     */     case 6403:
/*     */     case 6404:
/*     */     case 6405:
/*     */     case 6406:
/*     */     case 6409:
/* 122 */       return 1;
/*     */     case 6410:
/* 124 */       return 2;
/*     */     case 6407:
/*     */     case 32992:
/* 127 */       return 3;
/*     */     case 6408:
/*     */     case 32993:
/* 130 */       return 4;
/*     */     }
/* 132 */     return -1;
/*     */   }
/*     */ 
/*     */   protected static int nearestPower(int value)
/*     */   {
/* 148 */     int i = 1;
/*     */ 
/* 151 */     if (value == 0)
/* 152 */       return -1;
/*     */     while (true)
/*     */     {
/* 155 */       if (value == 1)
/* 156 */         return i;
/* 157 */       if (value == 3) {
/* 158 */         return i << 2;
/*     */       }
/* 160 */       value >>= 1;
/* 161 */       i <<= 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   protected static int bytesPerPixel(int format, int type)
/*     */   {
/*     */     int n;
/* 176 */     switch (format) {
/*     */     case 6400:
/*     */     case 6401:
/*     */     case 6402:
/*     */     case 6403:
/*     */     case 6404:
/*     */     case 6405:
/*     */     case 6406:
/*     */     case 6409:
/* 185 */       n = 1;
/* 186 */       break;
/*     */     case 6410:
/* 188 */       n = 2;
/* 189 */       break;
/*     */     case 6407:
/*     */     case 32992:
/* 192 */       n = 3;
/* 193 */       break;
/*     */     case 6408:
/*     */     case 32993:
/* 196 */       n = 4;
/* 197 */       break;
/*     */     default:
/* 199 */       n = 0;
/*     */     }
/*     */     int m;
/* 202 */     switch (type) {
/*     */     case 5121:
/* 204 */       m = 1;
/* 205 */       break;
/*     */     case 5120:
/* 207 */       m = 1;
/* 208 */       break;
/*     */     case 6656:
/* 210 */       m = 1;
/* 211 */       break;
/*     */     case 5123:
/* 213 */       m = 2;
/* 214 */       break;
/*     */     case 5122:
/* 216 */       m = 2;
/* 217 */       break;
/*     */     case 5125:
/* 219 */       m = 4;
/* 220 */       break;
/*     */     case 5124:
/* 222 */       m = 4;
/* 223 */       break;
/*     */     case 5126:
/* 225 */       m = 4;
/* 226 */       break;
/*     */     default:
/* 228 */       m = 0;
/*     */     }
/*     */ 
/* 231 */     return n * m;
/*     */   }
/*     */ 
/*     */   protected static int glGetIntegerv(int what)
/*     */   {
/* 242 */     scratch.rewind();
/* 243 */     GL11.glGetInteger(what, scratch);
/* 244 */     return scratch.get();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.Util
 * JD-Core Version:    0.6.2
 */