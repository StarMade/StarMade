/*     */ package org.lwjgl.util.glu;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class Project extends Util
/*     */ {
/*  52 */   private static final float[] IDENTITY_MATRIX = { 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F };
/*     */ 
/*  59 */   private static final FloatBuffer matrix = BufferUtils.createFloatBuffer(16);
/*  60 */   private static final FloatBuffer finalMatrix = BufferUtils.createFloatBuffer(16);
/*     */ 
/*  62 */   private static final FloatBuffer tempMatrix = BufferUtils.createFloatBuffer(16);
/*  63 */   private static final float[] in = new float[4];
/*  64 */   private static final float[] out = new float[4];
/*     */ 
/*  66 */   private static final float[] forward = new float[3];
/*  67 */   private static final float[] side = new float[3];
/*  68 */   private static final float[] up = new float[3];
/*     */ 
/*     */   private static void __gluMakeIdentityf(FloatBuffer m)
/*     */   {
/*  74 */     int oldPos = m.position();
/*  75 */     m.put(IDENTITY_MATRIX);
/*  76 */     m.position(oldPos);
/*     */   }
/*     */ 
/*     */   private static void __gluMultMatrixVecf(FloatBuffer m, float[] in, float[] out)
/*     */   {
/*  87 */     for (int i = 0; i < 4; i++)
/*  88 */       out[i] = (in[0] * m.get(m.position() + 0 + i) + in[1] * m.get(m.position() + 4 + i) + in[2] * m.get(m.position() + 8 + i) + in[3] * m.get(m.position() + 12 + i));
/*     */   }
/*     */ 
/*     */   private static boolean __gluInvertMatrixf(FloatBuffer src, FloatBuffer inverse)
/*     */   {
/* 106 */     FloatBuffer temp = tempMatrix;
/*     */ 
/* 109 */     for (int i = 0; i < 16; i++) {
/* 110 */       temp.put(i, src.get(i + src.position()));
/*     */     }
/* 112 */     __gluMakeIdentityf(inverse);
/*     */ 
/* 114 */     for (i = 0; i < 4; i++)
/*     */     {
/* 118 */       int swap = i;
/* 119 */       for (int j = i + 1; j < 4; j++)
/*     */       {
/* 123 */         if (Math.abs(temp.get(j * 4 + i)) > Math.abs(temp.get(i * 4 + i))) {
/* 124 */           swap = j;
/*     */         }
/*     */       }
/*     */ 
/* 128 */       if (swap != i)
/*     */       {
/* 132 */         for (int k = 0; k < 4; k++) {
/* 133 */           float t = temp.get(i * 4 + k);
/* 134 */           temp.put(i * 4 + k, temp.get(swap * 4 + k));
/* 135 */           temp.put(swap * 4 + k, t);
/*     */ 
/* 137 */           t = inverse.get(i * 4 + k);
/* 138 */           inverse.put(i * 4 + k, inverse.get(swap * 4 + k));
/*     */ 
/* 140 */           inverse.put(swap * 4 + k, t);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 145 */       if (temp.get(i * 4 + i) == 0.0F)
/*     */       {
/* 150 */         return false;
/*     */       }
/*     */ 
/* 153 */       float t = temp.get(i * 4 + i);
/* 154 */       for (int k = 0; k < 4; k++) {
/* 155 */         temp.put(i * 4 + k, temp.get(i * 4 + k) / t);
/* 156 */         inverse.put(i * 4 + k, inverse.get(i * 4 + k) / t);
/*     */       }
/* 158 */       for (j = 0; j < 4; j++) {
/* 159 */         if (j != i) {
/* 160 */           t = temp.get(j * 4 + i);
/* 161 */           for (k = 0; k < 4; k++) {
/* 162 */             temp.put(j * 4 + k, temp.get(j * 4 + k) - temp.get(i * 4 + k) * t);
/* 163 */             inverse.put(j * 4 + k, inverse.get(j * 4 + k) - inverse.get(i * 4 + k) * t);
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 171 */     return true;
/*     */   }
/*     */ 
/*     */   private static void __gluMultMatricesf(FloatBuffer a, FloatBuffer b, FloatBuffer r)
/*     */   {
/* 180 */     for (int i = 0; i < 4; i++)
/* 181 */       for (int j = 0; j < 4; j++)
/* 182 */         r.put(r.position() + i * 4 + j, a.get(a.position() + i * 4 + 0) * b.get(b.position() + 0 + j) + a.get(a.position() + i * 4 + 1) * b.get(b.position() + 4 + j) + a.get(a.position() + i * 4 + 2) * b.get(b.position() + 8 + j) + a.get(a.position() + i * 4 + 3) * b.get(b.position() + 12 + j));
/*     */   }
/*     */ 
/*     */   public static void gluPerspective(float fovy, float aspect, float zNear, float zFar)
/*     */   {
/* 198 */     float radians = fovy / 2.0F * 3.141593F / 180.0F;
/*     */ 
/* 200 */     float deltaZ = zFar - zNear;
/* 201 */     float sine = (float)Math.sin(radians);
/*     */ 
/* 203 */     if ((deltaZ == 0.0F) || (sine == 0.0F) || (aspect == 0.0F)) {
/* 204 */       return;
/*     */     }
/*     */ 
/* 207 */     float cotangent = (float)Math.cos(radians) / sine;
/*     */ 
/* 209 */     __gluMakeIdentityf(matrix);
/*     */ 
/* 211 */     matrix.put(0, cotangent / aspect);
/* 212 */     matrix.put(5, cotangent);
/* 213 */     matrix.put(10, -(zFar + zNear) / deltaZ);
/* 214 */     matrix.put(11, -1.0F);
/* 215 */     matrix.put(14, -2.0F * zNear * zFar / deltaZ);
/* 216 */     matrix.put(15, 0.0F);
/*     */ 
/* 218 */     GL11.glMultMatrix(matrix);
/*     */   }
/*     */ 
/*     */   public static void gluLookAt(float eyex, float eyey, float eyez, float centerx, float centery, float centerz, float upx, float upy, float upz)
/*     */   {
/* 244 */     float[] forward = forward;
/* 245 */     float[] side = side;
/* 246 */     float[] up = up;
/*     */ 
/* 248 */     forward[0] = (centerx - eyex);
/* 249 */     forward[1] = (centery - eyey);
/* 250 */     forward[2] = (centerz - eyez);
/*     */ 
/* 252 */     up[0] = upx;
/* 253 */     up[1] = upy;
/* 254 */     up[2] = upz;
/*     */ 
/* 256 */     normalize(forward);
/*     */ 
/* 259 */     cross(forward, up, side);
/* 260 */     normalize(side);
/*     */ 
/* 263 */     cross(side, forward, up);
/*     */ 
/* 265 */     __gluMakeIdentityf(matrix);
/* 266 */     matrix.put(0, side[0]);
/* 267 */     matrix.put(4, side[1]);
/* 268 */     matrix.put(8, side[2]);
/*     */ 
/* 270 */     matrix.put(1, up[0]);
/* 271 */     matrix.put(5, up[1]);
/* 272 */     matrix.put(9, up[2]);
/*     */ 
/* 274 */     matrix.put(2, -forward[0]);
/* 275 */     matrix.put(6, -forward[1]);
/* 276 */     matrix.put(10, -forward[2]);
/*     */ 
/* 278 */     GL11.glMultMatrix(matrix);
/* 279 */     GL11.glTranslatef(-eyex, -eyey, -eyez);
/*     */   }
/*     */ 
/*     */   public static boolean gluProject(float objx, float objy, float objz, FloatBuffer modelMatrix, FloatBuffer projMatrix, IntBuffer viewport, FloatBuffer win_pos)
/*     */   {
/* 302 */     float[] in = in;
/* 303 */     float[] out = out;
/*     */ 
/* 305 */     in[0] = objx;
/* 306 */     in[1] = objy;
/* 307 */     in[2] = objz;
/* 308 */     in[3] = 1.0F;
/*     */ 
/* 310 */     __gluMultMatrixVecf(modelMatrix, in, out);
/* 311 */     __gluMultMatrixVecf(projMatrix, out, in);
/*     */ 
/* 313 */     if (in[3] == 0.0D) {
/* 314 */       return false;
/*     */     }
/* 316 */     in[3] = (1.0F / in[3] * 0.5F);
/*     */ 
/* 319 */     in[0] = (in[0] * in[3] + 0.5F);
/* 320 */     in[1] = (in[1] * in[3] + 0.5F);
/* 321 */     in[2] = (in[2] * in[3] + 0.5F);
/*     */ 
/* 324 */     win_pos.put(0, in[0] * viewport.get(viewport.position() + 2) + viewport.get(viewport.position() + 0));
/* 325 */     win_pos.put(1, in[1] * viewport.get(viewport.position() + 3) + viewport.get(viewport.position() + 1));
/* 326 */     win_pos.put(2, in[2]);
/*     */ 
/* 328 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean gluUnProject(float winx, float winy, float winz, FloatBuffer modelMatrix, FloatBuffer projMatrix, IntBuffer viewport, FloatBuffer obj_pos)
/*     */   {
/* 350 */     float[] in = in;
/* 351 */     float[] out = out;
/*     */ 
/* 353 */     __gluMultMatricesf(modelMatrix, projMatrix, finalMatrix);
/*     */ 
/* 355 */     if (!__gluInvertMatrixf(finalMatrix, finalMatrix)) {
/* 356 */       return false;
/*     */     }
/* 358 */     in[0] = winx;
/* 359 */     in[1] = winy;
/* 360 */     in[2] = winz;
/* 361 */     in[3] = 1.0F;
/*     */ 
/* 364 */     in[0] = ((in[0] - viewport.get(viewport.position() + 0)) / viewport.get(viewport.position() + 2));
/* 365 */     in[1] = ((in[1] - viewport.get(viewport.position() + 1)) / viewport.get(viewport.position() + 3));
/*     */ 
/* 368 */     in[0] = (in[0] * 2.0F - 1.0F);
/* 369 */     in[1] = (in[1] * 2.0F - 1.0F);
/* 370 */     in[2] = (in[2] * 2.0F - 1.0F);
/*     */ 
/* 372 */     __gluMultMatrixVecf(finalMatrix, in, out);
/*     */ 
/* 374 */     if (out[3] == 0.0D) {
/* 375 */       return false;
/*     */     }
/* 377 */     out[3] = (1.0F / out[3]);
/*     */ 
/* 379 */     obj_pos.put(obj_pos.position() + 0, out[0] * out[3]);
/* 380 */     obj_pos.put(obj_pos.position() + 1, out[1] * out[3]);
/* 381 */     obj_pos.put(obj_pos.position() + 2, out[2] * out[3]);
/*     */ 
/* 383 */     return true;
/*     */   }
/*     */ 
/*     */   public static void gluPickMatrix(float x, float y, float deltaX, float deltaY, IntBuffer viewport)
/*     */   {
/* 401 */     if ((deltaX <= 0.0F) || (deltaY <= 0.0F)) {
/* 402 */       return;
/*     */     }
/*     */ 
/* 406 */     GL11.glTranslatef((viewport.get(viewport.position() + 2) - 2.0F * (x - viewport.get(viewport.position() + 0))) / deltaX, (viewport.get(viewport.position() + 3) - 2.0F * (y - viewport.get(viewport.position() + 1))) / deltaY, 0.0F);
/*     */ 
/* 410 */     GL11.glScalef(viewport.get(viewport.position() + 2) / deltaX, viewport.get(viewport.position() + 3) / deltaY, 1.0F);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.Project
 * JD-Core Version:    0.6.2
 */