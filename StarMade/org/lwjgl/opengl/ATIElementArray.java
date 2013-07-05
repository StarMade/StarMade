/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ import java.nio.ShortBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class ATIElementArray
/*    */ {
/*    */   public static final int GL_ELEMENT_ARRAY_ATI = 34664;
/*    */   public static final int GL_ELEMENT_ARRAY_TYPE_ATI = 34665;
/*    */   public static final int GL_ELEMENT_ARRAY_POINTER_ATI = 34666;
/*    */ 
/*    */   public static void glElementPointerATI(ByteBuffer pPointer)
/*    */   {
/* 17 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 18 */     long function_pointer = caps.glElementPointerATI;
/* 19 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 20 */     BufferChecks.checkDirect(pPointer);
/* 21 */     nglElementPointerATI(5121, MemoryUtil.getAddress(pPointer), function_pointer);
/*    */   }
/*    */   public static void glElementPointerATI(IntBuffer pPointer) {
/* 24 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 25 */     long function_pointer = caps.glElementPointerATI;
/* 26 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 27 */     BufferChecks.checkDirect(pPointer);
/* 28 */     nglElementPointerATI(5125, MemoryUtil.getAddress(pPointer), function_pointer);
/*    */   }
/*    */   public static void glElementPointerATI(ShortBuffer pPointer) {
/* 31 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 32 */     long function_pointer = caps.glElementPointerATI;
/* 33 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 34 */     BufferChecks.checkDirect(pPointer);
/* 35 */     nglElementPointerATI(5123, MemoryUtil.getAddress(pPointer), function_pointer);
/*    */   }
/*    */   static native void nglElementPointerATI(int paramInt, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glDrawElementArrayATI(int mode, int count) {
/* 40 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 41 */     long function_pointer = caps.glDrawElementArrayATI;
/* 42 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 43 */     nglDrawElementArrayATI(mode, count, function_pointer);
/*    */   }
/*    */   static native void nglDrawElementArrayATI(int paramInt1, int paramInt2, long paramLong);
/*    */ 
/*    */   public static void glDrawRangeElementArrayATI(int mode, int start, int end, int count) {
/* 48 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 49 */     long function_pointer = caps.glDrawRangeElementArrayATI;
/* 50 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 51 */     nglDrawRangeElementArrayATI(mode, start, end, count, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglDrawRangeElementArrayATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ATIElementArray
 * JD-Core Version:    0.6.2
 */