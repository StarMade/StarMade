/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import java.lang.reflect.Field;
/*  4:   */import java.util.Map;
/*  5:   */import org.lwjgl.LWJGLUtil;
/*  6:   */import org.lwjgl.LWJGLUtil.TokenFilter;
/*  7:   */
/* 45:   */public final class Util
/* 46:   */{
/* 47:47 */  private static final Map<Integer, String> CL_ERROR_TOKENS = LWJGLUtil.getClassTokens(new LWJGLUtil.TokenFilter() {
/* 48:   */    public boolean accept(Field field, int value) {
/* 49:49 */      return value < 0;
/* 50:   */    }
/* 51:47 */  }, null, new Class[] { CL10.class, CL11.class, KHRGLSharing.class, KHRICD.class, APPLEGLSharing.class, EXTDeviceFission.class });
/* 52:   */  
/* 59:   */  public static void checkCLError(int errcode)
/* 60:   */  {
/* 61:57 */    if (errcode != 0)
/* 62:58 */      throwCLError(errcode);
/* 63:   */  }
/* 64:   */  
/* 65:   */  private static void throwCLError(int errcode) {
/* 66:62 */    String errname = (String)CL_ERROR_TOKENS.get(Integer.valueOf(errcode));
/* 67:63 */    if (errname == null)
/* 68:64 */      errname = "UNKNOWN";
/* 69:65 */    throw new OpenCLException("Error Code: " + errname + " (" + LWJGLUtil.toHexString(errcode) + ")");
/* 70:   */  }
/* 71:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.Util
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */