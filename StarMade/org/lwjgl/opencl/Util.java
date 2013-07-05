/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.Map;
/*    */ import org.lwjgl.LWJGLUtil;
/*    */ import org.lwjgl.LWJGLUtil.TokenFilter;
/*    */ 
/*    */ public final class Util
/*    */ {
/* 47 */   private static final Map<Integer, String> CL_ERROR_TOKENS = LWJGLUtil.getClassTokens(new LWJGLUtil.TokenFilter() {
/*    */     public boolean accept(Field field, int value) {
/* 49 */       return value < 0;
/*    */     }
/*    */   }
/*    */   , null, new Class[] { CL10.class, CL11.class, KHRGLSharing.class, KHRICD.class, APPLEGLSharing.class, EXTDeviceFission.class });
/*    */ 
/*    */   public static void checkCLError(int errcode)
/*    */   {
/* 57 */     if (errcode != 0)
/* 58 */       throwCLError(errcode);
/*    */   }
/*    */ 
/*    */   private static void throwCLError(int errcode) {
/* 62 */     String errname = (String)CL_ERROR_TOKENS.get(Integer.valueOf(errcode));
/* 63 */     if (errname == null)
/* 64 */       errname = "UNKNOWN";
/* 65 */     throw new OpenCLException("Error Code: " + errname + " (" + LWJGLUtil.toHexString(errcode) + ")");
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.Util
 * JD-Core Version:    0.6.2
 */