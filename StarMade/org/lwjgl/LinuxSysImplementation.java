/*  1:   */package org.lwjgl;
/*  2:   */
/*  3:   */import java.awt.Toolkit;
/*  4:   */import java.security.AccessController;
/*  5:   */import java.security.PrivilegedAction;
/*  6:   */
/* 43:   */final class LinuxSysImplementation
/* 44:   */  extends J2SESysImplementation
/* 45:   */{
/* 46:   */  private static final int JNI_VERSION = 19;
/* 47:   */  
/* 48:   */  static
/* 49:   */  {
/* 50:50 */    Toolkit.getDefaultToolkit();
/* 51:   */    
/* 53:53 */    AccessController.doPrivileged(new PrivilegedAction() {
/* 54:   */      public Object run() {
/* 55:   */        try {
/* 56:56 */          System.loadLibrary("jawt");
/* 57:   */        }
/* 58:   */        catch (UnsatisfiedLinkError e) {}
/* 59:   */        
/* 61:61 */        return null;
/* 62:   */      }
/* 63:   */    });
/* 64:   */  }
/* 65:   */  
/* 66:   */  public int getRequiredJNIVersion() {
/* 67:67 */    return 19;
/* 68:   */  }
/* 69:   */  
/* 72:   */  public boolean openURL(String url)
/* 73:   */  {
/* 74:74 */    String[] browsers = { "xdg-open", "firefox", "mozilla", "opera", "konqueror", "nautilus", "galeon", "netscape" };
/* 75:   */    
/* 76:76 */    for (String browser : browsers) {
/* 77:   */      try {
/* 78:78 */        LWJGLUtil.execPrivileged(new String[] { browser, url });
/* 79:79 */        return true;
/* 80:   */      }
/* 81:   */      catch (Exception e) {
/* 82:82 */        e.printStackTrace(System.err);
/* 83:   */      }
/* 84:   */    }
/* 85:   */    
/* 87:87 */    return false;
/* 88:   */  }
/* 89:   */  
/* 90:   */  public boolean has64Bit() {
/* 91:91 */    return true;
/* 92:   */  }
/* 93:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.LinuxSysImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */