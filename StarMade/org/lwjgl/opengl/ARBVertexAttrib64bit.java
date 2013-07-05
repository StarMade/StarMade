/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.DoubleBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class ARBVertexAttrib64bit
/*    */ {
/*    */   public static final int GL_DOUBLE_VEC2 = 36860;
/*    */   public static final int GL_DOUBLE_VEC3 = 36861;
/*    */   public static final int GL_DOUBLE_VEC4 = 36862;
/*    */   public static final int GL_DOUBLE_MAT2 = 36678;
/*    */   public static final int GL_DOUBLE_MAT3 = 36679;
/*    */   public static final int GL_DOUBLE_MAT4 = 36680;
/*    */   public static final int GL_DOUBLE_MAT2x3 = 36681;
/*    */   public static final int GL_DOUBLE_MAT2x4 = 36682;
/*    */   public static final int GL_DOUBLE_MAT3x2 = 36683;
/*    */   public static final int GL_DOUBLE_MAT3x4 = 36684;
/*    */   public static final int GL_DOUBLE_MAT4x2 = 36685;
/*    */   public static final int GL_DOUBLE_MAT4x3 = 36686;
/*    */ 
/*    */   public static void glVertexAttribL1d(int index, double x)
/*    */   {
/* 29 */     GL41.glVertexAttribL1d(index, x);
/*    */   }
/*    */ 
/*    */   public static void glVertexAttribL2d(int index, double x, double y) {
/* 33 */     GL41.glVertexAttribL2d(index, x, y);
/*    */   }
/*    */ 
/*    */   public static void glVertexAttribL3d(int index, double x, double y, double z) {
/* 37 */     GL41.glVertexAttribL3d(index, x, y, z);
/*    */   }
/*    */ 
/*    */   public static void glVertexAttribL4d(int index, double x, double y, double z, double w) {
/* 41 */     GL41.glVertexAttribL4d(index, x, y, z, w);
/*    */   }
/*    */ 
/*    */   public static void glVertexAttribL1(int index, DoubleBuffer v) {
/* 45 */     GL41.glVertexAttribL1(index, v);
/*    */   }
/*    */ 
/*    */   public static void glVertexAttribL2(int index, DoubleBuffer v) {
/* 49 */     GL41.glVertexAttribL2(index, v);
/*    */   }
/*    */ 
/*    */   public static void glVertexAttribL3(int index, DoubleBuffer v) {
/* 53 */     GL41.glVertexAttribL3(index, v);
/*    */   }
/*    */ 
/*    */   public static void glVertexAttribL4(int index, DoubleBuffer v) {
/* 57 */     GL41.glVertexAttribL4(index, v);
/*    */   }
/*    */ 
/*    */   public static void glVertexAttribLPointer(int index, int size, int stride, DoubleBuffer pointer) {
/* 61 */     GL41.glVertexAttribLPointer(index, size, stride, pointer);
/*    */   }
/*    */   public static void glVertexAttribLPointer(int index, int size, int stride, long pointer_buffer_offset) {
/* 64 */     GL41.glVertexAttribLPointer(index, size, stride, pointer_buffer_offset);
/*    */   }
/*    */ 
/*    */   public static void glGetVertexAttribL(int index, int pname, DoubleBuffer params) {
/* 68 */     GL41.glGetVertexAttribL(index, pname, params);
/*    */   }
/*    */ 
/*    */   public static void glVertexArrayVertexAttribLOffsetEXT(int vaobj, int buffer, int index, int size, int type, int stride, long offset) {
/* 72 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 73 */     long function_pointer = caps.glVertexArrayVertexAttribLOffsetEXT;
/* 74 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 75 */     nglVertexArrayVertexAttribLOffsetEXT(vaobj, buffer, index, size, type, stride, offset, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglVertexArrayVertexAttribLOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBVertexAttrib64bit
 * JD-Core Version:    0.6.2
 */