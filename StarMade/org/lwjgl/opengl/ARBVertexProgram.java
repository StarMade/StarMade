/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ 
/*     */ public final class ARBVertexProgram extends ARBProgram
/*     */ {
/*     */   public static final int GL_VERTEX_PROGRAM_ARB = 34336;
/*     */   public static final int GL_VERTEX_PROGRAM_POINT_SIZE_ARB = 34370;
/*     */   public static final int GL_VERTEX_PROGRAM_TWO_SIDE_ARB = 34371;
/*     */   public static final int GL_COLOR_SUM_ARB = 33880;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED_ARB = 34338;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE_ARB = 34339;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE_ARB = 34340;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE_ARB = 34341;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED_ARB = 34922;
/*     */   public static final int GL_CURRENT_VERTEX_ATTRIB_ARB = 34342;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER_ARB = 34373;
/*     */   public static final int GL_PROGRAM_ADDRESS_REGISTERS_ARB = 34992;
/*     */   public static final int GL_MAX_PROGRAM_ADDRESS_REGISTERS_ARB = 34993;
/*     */   public static final int GL_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB = 34994;
/*     */   public static final int GL_MAX_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB = 34995;
/*     */   public static final int GL_MAX_VERTEX_ATTRIBS_ARB = 34921;
/*     */ 
/*     */   public static void glVertexAttrib1sARB(int index, short x)
/*     */   {
/*  61 */     ARBVertexShader.glVertexAttrib1sARB(index, x);
/*     */   }
/*     */ 
/*     */   public static void glVertexAttrib1fARB(int index, float x) {
/*  65 */     ARBVertexShader.glVertexAttrib1fARB(index, x);
/*     */   }
/*     */ 
/*     */   public static void glVertexAttrib1dARB(int index, double x) {
/*  69 */     ARBVertexShader.glVertexAttrib1dARB(index, x);
/*     */   }
/*     */ 
/*     */   public static void glVertexAttrib2sARB(int index, short x, short y) {
/*  73 */     ARBVertexShader.glVertexAttrib2sARB(index, x, y);
/*     */   }
/*     */ 
/*     */   public static void glVertexAttrib2fARB(int index, float x, float y) {
/*  77 */     ARBVertexShader.glVertexAttrib2fARB(index, x, y);
/*     */   }
/*     */ 
/*     */   public static void glVertexAttrib2dARB(int index, double x, double y) {
/*  81 */     ARBVertexShader.glVertexAttrib2dARB(index, x, y);
/*     */   }
/*     */ 
/*     */   public static void glVertexAttrib3sARB(int index, short x, short y, short z) {
/*  85 */     ARBVertexShader.glVertexAttrib3sARB(index, x, y, z);
/*     */   }
/*     */ 
/*     */   public static void glVertexAttrib3fARB(int index, float x, float y, float z) {
/*  89 */     ARBVertexShader.glVertexAttrib3fARB(index, x, y, z);
/*     */   }
/*     */ 
/*     */   public static void glVertexAttrib3dARB(int index, double x, double y, double z) {
/*  93 */     ARBVertexShader.glVertexAttrib3dARB(index, x, y, z);
/*     */   }
/*     */ 
/*     */   public static void glVertexAttrib4sARB(int index, short x, short y, short z, short w) {
/*  97 */     ARBVertexShader.glVertexAttrib4sARB(index, x, y, z, w);
/*     */   }
/*     */ 
/*     */   public static void glVertexAttrib4fARB(int index, float x, float y, float z, float w) {
/* 101 */     ARBVertexShader.glVertexAttrib4fARB(index, x, y, z, w);
/*     */   }
/*     */ 
/*     */   public static void glVertexAttrib4dARB(int index, double x, double y, double z, double w) {
/* 105 */     ARBVertexShader.glVertexAttrib4dARB(index, x, y, z, w);
/*     */   }
/*     */ 
/*     */   public static void glVertexAttrib4NubARB(int index, byte x, byte y, byte z, byte w) {
/* 109 */     ARBVertexShader.glVertexAttrib4NubARB(index, x, y, z, w);
/*     */   }
/*     */ 
/*     */   public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, DoubleBuffer buffer) {
/* 113 */     ARBVertexShader.glVertexAttribPointerARB(index, size, normalized, stride, buffer);
/*     */   }
/*     */   public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, FloatBuffer buffer) {
/* 116 */     ARBVertexShader.glVertexAttribPointerARB(index, size, normalized, stride, buffer);
/*     */   }
/*     */   public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer) {
/* 119 */     ARBVertexShader.glVertexAttribPointerARB(index, size, unsigned, normalized, stride, buffer);
/*     */   }
/*     */   public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer) {
/* 122 */     ARBVertexShader.glVertexAttribPointerARB(index, size, unsigned, normalized, stride, buffer);
/*     */   }
/*     */   public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer) {
/* 125 */     ARBVertexShader.glVertexAttribPointerARB(index, size, unsigned, normalized, stride, buffer);
/*     */   }
/*     */   public static void glVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, long buffer_buffer_offset) {
/* 128 */     ARBVertexShader.glVertexAttribPointerARB(index, size, type, normalized, stride, buffer_buffer_offset);
/*     */   }
/*     */ 
/*     */   public static void glEnableVertexAttribArrayARB(int index) {
/* 132 */     ARBVertexShader.glEnableVertexAttribArrayARB(index);
/*     */   }
/*     */ 
/*     */   public static void glDisableVertexAttribArrayARB(int index) {
/* 136 */     ARBVertexShader.glDisableVertexAttribArrayARB(index);
/*     */   }
/*     */ 
/*     */   public static void glGetVertexAttribARB(int index, int pname, FloatBuffer params) {
/* 140 */     ARBVertexShader.glGetVertexAttribARB(index, pname, params);
/*     */   }
/*     */ 
/*     */   public static void glGetVertexAttribARB(int index, int pname, DoubleBuffer params) {
/* 144 */     ARBVertexShader.glGetVertexAttribARB(index, pname, params);
/*     */   }
/*     */ 
/*     */   public static void glGetVertexAttribARB(int index, int pname, IntBuffer params) {
/* 148 */     ARBVertexShader.glGetVertexAttribARB(index, pname, params);
/*     */   }
/*     */ 
/*     */   public static ByteBuffer glGetVertexAttribPointerARB(int index, int pname, long result_size) {
/* 152 */     return ARBVertexShader.glGetVertexAttribPointerARB(index, pname, result_size);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBVertexProgram
 * JD-Core Version:    0.6.2
 */