/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ final class APPLEContextLoggingFunctions
/*    */ {
/*    */   static void clLogMessagesToSystemLogAPPLE(ByteBuffer errstr, ByteBuffer private_info, ByteBuffer user_data)
/*    */   {
/* 13 */     long function_pointer = CLCapabilities.clLogMessagesToSystemLogAPPLE;
/* 14 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 15 */     BufferChecks.checkDirect(errstr);
/* 16 */     BufferChecks.checkDirect(private_info);
/* 17 */     BufferChecks.checkDirect(user_data);
/* 18 */     nclLogMessagesToSystemLogAPPLE(MemoryUtil.getAddress(errstr), MemoryUtil.getAddress(private_info), private_info.remaining(), MemoryUtil.getAddress(user_data), function_pointer);
/*    */   }
/*    */   static native void nclLogMessagesToSystemLogAPPLE(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*    */ 
/*    */   static void clLogMessagesToStdoutAPPLE(ByteBuffer errstr, ByteBuffer private_info, ByteBuffer user_data) {
/* 23 */     long function_pointer = CLCapabilities.clLogMessagesToStdoutAPPLE;
/* 24 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 25 */     BufferChecks.checkDirect(errstr);
/* 26 */     BufferChecks.checkDirect(private_info);
/* 27 */     BufferChecks.checkDirect(user_data);
/* 28 */     nclLogMessagesToStdoutAPPLE(MemoryUtil.getAddress(errstr), MemoryUtil.getAddress(private_info), private_info.remaining(), MemoryUtil.getAddress(user_data), function_pointer);
/*    */   }
/*    */   static native void nclLogMessagesToStdoutAPPLE(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*    */ 
/*    */   static void clLogMessagesToStderrAPPLE(ByteBuffer errstr, ByteBuffer private_info, ByteBuffer user_data) {
/* 33 */     long function_pointer = CLCapabilities.clLogMessagesToStderrAPPLE;
/* 34 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 35 */     BufferChecks.checkDirect(errstr);
/* 36 */     BufferChecks.checkDirect(private_info);
/* 37 */     BufferChecks.checkDirect(user_data);
/* 38 */     nclLogMessagesToStderrAPPLE(MemoryUtil.getAddress(errstr), MemoryUtil.getAddress(private_info), private_info.remaining(), MemoryUtil.getAddress(user_data), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nclLogMessagesToStderrAPPLE(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.APPLEContextLoggingFunctions
 * JD-Core Version:    0.6.2
 */