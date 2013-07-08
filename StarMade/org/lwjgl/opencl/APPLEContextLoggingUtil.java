/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */
/* 48:   */public final class APPLEContextLoggingUtil
/* 49:   */{
/* 50:   */  public static final CLContextCallback SYSTEM_LOG_CALLBACK;
/* 51:   */  public static final CLContextCallback STD_OUT_CALLBACK;
/* 52:   */  public static final CLContextCallback STD_ERR_CALLBACK;
/* 53:   */  
/* 54:   */  static
/* 55:   */  {
/* 56:56 */    if (CLCapabilities.CL_APPLE_ContextLoggingFunctions) {
/* 57:57 */      SYSTEM_LOG_CALLBACK = new CLContextCallback(CallbackUtil.getLogMessageToSystemLogAPPLE()) {
/* 58:58 */        protected void handleMessage(String errinfo, ByteBuffer private_info) { throw new UnsupportedOperationException();
/* 59:   */        }
/* 60:60 */      };
/* 61:61 */      STD_OUT_CALLBACK = new CLContextCallback(CallbackUtil.getLogMessageToStdoutAPPLE()) {
/* 62:62 */        protected void handleMessage(String errinfo, ByteBuffer private_info) { throw new UnsupportedOperationException();
/* 63:   */        }
/* 64:64 */      };
/* 65:65 */      STD_ERR_CALLBACK = new CLContextCallback(CallbackUtil.getLogMessageToStderrAPPLE()) {
/* 66:66 */        protected void handleMessage(String errinfo, ByteBuffer private_info) { throw new UnsupportedOperationException(); }
/* 67:   */      };
/* 68:   */    } else {
/* 69:69 */      SYSTEM_LOG_CALLBACK = null;
/* 70:70 */      STD_OUT_CALLBACK = null;
/* 71:71 */      STD_ERR_CALLBACK = null;
/* 72:   */    }
/* 73:   */  }
/* 74:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.APPLEContextLoggingUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */