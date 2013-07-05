/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ 
/*    */ public final class APPLEContextLoggingUtil
/*    */ {
/*    */   public static final CLContextCallback SYSTEM_LOG_CALLBACK;
/*    */   public static final CLContextCallback STD_OUT_CALLBACK;
/*    */   public static final CLContextCallback STD_ERR_CALLBACK;
/*    */ 
/*    */   static
/*    */   {
/* 56 */     if (CLCapabilities.CL_APPLE_ContextLoggingFunctions) {
/* 57 */       SYSTEM_LOG_CALLBACK = new CLContextCallback(CallbackUtil.getLogMessageToSystemLogAPPLE()) {
/* 58 */         protected void handleMessage(String errinfo, ByteBuffer private_info) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */       };
/* 61 */       STD_OUT_CALLBACK = new CLContextCallback(CallbackUtil.getLogMessageToStdoutAPPLE()) {
/* 62 */         protected void handleMessage(String errinfo, ByteBuffer private_info) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */       };
/* 65 */       STD_ERR_CALLBACK = new CLContextCallback(CallbackUtil.getLogMessageToStderrAPPLE()) {
/* 66 */         protected void handleMessage(String errinfo, ByteBuffer private_info) { throw new UnsupportedOperationException(); } } ;
/*    */     }
/*    */     else {
/* 69 */       SYSTEM_LOG_CALLBACK = null;
/* 70 */       STD_OUT_CALLBACK = null;
/* 71 */       STD_ERR_CALLBACK = null;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.APPLEContextLoggingUtil
 * JD-Core Version:    0.6.2
 */