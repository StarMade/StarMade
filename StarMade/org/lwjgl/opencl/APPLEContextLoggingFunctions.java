/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/*  9:   */final class APPLEContextLoggingFunctions
/* 10:   */{
/* 11:   */  static void clLogMessagesToSystemLogAPPLE(ByteBuffer errstr, ByteBuffer private_info, ByteBuffer user_data)
/* 12:   */  {
/* 13:13 */    long function_pointer = CLCapabilities.clLogMessagesToSystemLogAPPLE;
/* 14:14 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 15:15 */    BufferChecks.checkDirect(errstr);
/* 16:16 */    BufferChecks.checkDirect(private_info);
/* 17:17 */    BufferChecks.checkDirect(user_data);
/* 18:18 */    nclLogMessagesToSystemLogAPPLE(MemoryUtil.getAddress(errstr), MemoryUtil.getAddress(private_info), private_info.remaining(), MemoryUtil.getAddress(user_data), function_pointer);
/* 19:   */  }
/* 20:   */  
/* 21:   */  static native void nclLogMessagesToSystemLogAPPLE(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 22:   */  
/* 23:23 */  static void clLogMessagesToStdoutAPPLE(ByteBuffer errstr, ByteBuffer private_info, ByteBuffer user_data) { long function_pointer = CLCapabilities.clLogMessagesToStdoutAPPLE;
/* 24:24 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 25:25 */    BufferChecks.checkDirect(errstr);
/* 26:26 */    BufferChecks.checkDirect(private_info);
/* 27:27 */    BufferChecks.checkDirect(user_data);
/* 28:28 */    nclLogMessagesToStdoutAPPLE(MemoryUtil.getAddress(errstr), MemoryUtil.getAddress(private_info), private_info.remaining(), MemoryUtil.getAddress(user_data), function_pointer);
/* 29:   */  }
/* 30:   */  
/* 31:   */  static native void nclLogMessagesToStdoutAPPLE(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 32:   */  
/* 33:33 */  static void clLogMessagesToStderrAPPLE(ByteBuffer errstr, ByteBuffer private_info, ByteBuffer user_data) { long function_pointer = CLCapabilities.clLogMessagesToStderrAPPLE;
/* 34:34 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 35:35 */    BufferChecks.checkDirect(errstr);
/* 36:36 */    BufferChecks.checkDirect(private_info);
/* 37:37 */    BufferChecks.checkDirect(user_data);
/* 38:38 */    nclLogMessagesToStderrAPPLE(MemoryUtil.getAddress(errstr), MemoryUtil.getAddress(private_info), private_info.remaining(), MemoryUtil.getAddress(user_data), function_pointer);
/* 39:   */  }
/* 40:   */  
/* 41:   */  static native void nclLogMessagesToStderrAPPLE(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/* 42:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.APPLEContextLoggingFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */