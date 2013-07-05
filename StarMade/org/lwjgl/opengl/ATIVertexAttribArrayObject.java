/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.FloatBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class ATIVertexAttribArrayObject
/*    */ {
/*    */   public static void glVertexAttribArrayObjectATI(int index, int size, int type, boolean normalized, int stride, int buffer, int offset)
/*    */   {
/* 13 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 14 */     long function_pointer = caps.glVertexAttribArrayObjectATI;
/* 15 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 16 */     nglVertexAttribArrayObjectATI(index, size, type, normalized, stride, buffer, offset, function_pointer);
/*    */   }
/*    */   static native void nglVertexAttribArrayObjectATI(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*    */ 
/*    */   public static void glGetVertexAttribArrayObjectATI(int index, int pname, FloatBuffer params) {
/* 21 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 22 */     long function_pointer = caps.glGetVertexAttribArrayObjectfvATI;
/* 23 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 24 */     BufferChecks.checkBuffer(params, 4);
/* 25 */     nglGetVertexAttribArrayObjectfvATI(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*    */   }
/*    */   static native void nglGetVertexAttribArrayObjectfvATI(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glGetVertexAttribArrayObjectATI(int index, int pname, IntBuffer params) {
/* 30 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 31 */     long function_pointer = caps.glGetVertexAttribArrayObjectivATI;
/* 32 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 33 */     BufferChecks.checkBuffer(params, 4);
/* 34 */     nglGetVertexAttribArrayObjectivATI(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglGetVertexAttribArrayObjectivATI(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ATIVertexAttribArrayObject
 * JD-Core Version:    0.6.2
 */