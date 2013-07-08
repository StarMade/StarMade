/*  1:   */package org.lwjgl;
/*  2:   */
/*  3:   */import com.apple.eio.FileManager;
/*  4:   */import java.awt.Toolkit;
/*  5:   */
/* 44:   */final class MacOSXSysImplementation
/* 45:   */  extends J2SESysImplementation
/* 46:   */{
/* 47:   */  private static final int JNI_VERSION = 25;
/* 48:   */  
/* 49:   */  static
/* 50:   */  {
/* 51:51 */    Toolkit.getDefaultToolkit();
/* 52:   */  }
/* 53:   */  
/* 54:   */  public int getRequiredJNIVersion() {
/* 55:55 */    return 25;
/* 56:   */  }
/* 57:   */  
/* 58:   */  public boolean openURL(String url) {
/* 59:   */    try {
/* 60:60 */      FileManager.openURL(url);
/* 61:61 */      return true;
/* 62:   */    } catch (Exception e) {
/* 63:63 */      LWJGLUtil.log("Exception occurred while trying to invoke browser: " + e); }
/* 64:64 */    return false;
/* 65:   */  }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.MacOSXSysImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */