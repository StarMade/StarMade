/*   1:    */package org.lwjgl;
/*   2:    */
/*   3:    */import java.lang.reflect.Method;
/*   4:    */import java.nio.ByteBuffer;
/*   5:    */import java.security.AccessController;
/*   6:    */import java.security.PrivilegedActionException;
/*   7:    */import java.security.PrivilegedExceptionAction;
/*   8:    */import org.lwjgl.opengl.Display;
/*   9:    */
/*  49:    */final class WindowsSysImplementation
/*  50:    */  extends DefaultSysImplementation
/*  51:    */{
/*  52:    */  private static final int JNI_VERSION = 24;
/*  53:    */  
/*  54:    */  public int getRequiredJNIVersion()
/*  55:    */  {
/*  56: 56 */    return 24;
/*  57:    */  }
/*  58:    */  
/*  59:    */  public long getTimerResolution() {
/*  60: 60 */    return 1000L;
/*  61:    */  }
/*  62:    */  
/*  63:    */  public long getTime() {
/*  64: 64 */    return nGetTime();
/*  65:    */  }
/*  66:    */  
/*  67:    */  private static native long nGetTime();
/*  68:    */  
/*  69: 69 */  public boolean has64Bit() { return true; }
/*  70:    */  
/*  71:    */  private static long getHwnd()
/*  72:    */  {
/*  73: 73 */    if (!Display.isCreated()) {
/*  74: 74 */      return 0L;
/*  75:    */    }
/*  76:    */    
/*  77:    */    try
/*  78:    */    {
/*  79: 79 */      ((Long)AccessController.doPrivileged(new PrivilegedExceptionAction()) {
/*  80:    */        public Long run() throws Exception {
/*  81: 81 */          Method getImplementation_method = Display.class.getDeclaredMethod("getImplementation", new Class[0]);
/*  82: 82 */          getImplementation_method.setAccessible(true);
/*  83: 83 */          Object display_impl = getImplementation_method.invoke(null, new Object[0]);
/*  84: 84 */          Class<?> WindowsDisplay_class = Class.forName("org.lwjgl.opengl.WindowsDisplay");
/*  85: 85 */          Method getHwnd_method = WindowsDisplay_class.getDeclaredMethod("getHwnd", new Class[0]);
/*  86: 86 */          getHwnd_method.setAccessible(true);
/*  87: 87 */          return (Long)getHwnd_method.invoke(display_impl, new Object[0]);
/*  88:    */        }
/*  89:    */      }()).longValue();
/*  90:    */    } catch (PrivilegedActionException e) {
/*  91: 91 */      throw new Error(e);
/*  92:    */    }
/*  93:    */  }
/*  94:    */  
/*  95:    */  public void alert(String title, String message) {
/*  96: 96 */    if (!Display.isCreated()) {
/*  97: 97 */      initCommonControls();
/*  98:    */    }
/*  99:    */    
/* 100:100 */    LWJGLUtil.log(String.format("*** Alert *** %s\n%s\n", new Object[] { title, message }));
/* 101:    */    
/* 102:102 */    ByteBuffer titleText = MemoryUtil.encodeUTF16(title);
/* 103:103 */    ByteBuffer messageText = MemoryUtil.encodeUTF16(message);
/* 104:104 */    nAlert(getHwnd(), MemoryUtil.getAddress(titleText), MemoryUtil.getAddress(messageText));
/* 105:    */  }
/* 106:    */  
/* 107:    */  private static native void nAlert(long paramLong1, long paramLong2, long paramLong3);
/* 108:    */  
/* 109:    */  private static native void initCommonControls();
/* 110:    */  
/* 111:111 */  public boolean openURL(String url) { try { LWJGLUtil.execPrivileged(new String[] { "rundll32", "url.dll,FileProtocolHandler", url });
/* 112:112 */      return true;
/* 113:    */    } catch (Exception e) {
/* 114:114 */      LWJGLUtil.log("Failed to open url (" + url + "): " + e.getMessage()); }
/* 115:115 */    return false;
/* 116:    */  }
/* 117:    */  
/* 118:    */  public String getClipboard()
/* 119:    */  {
/* 120:120 */    return nGetClipboard();
/* 121:    */  }
/* 122:    */  
/* 123:    */  private static native String nGetClipboard();
/* 124:    */  
/* 125:    */  static {}
/* 126:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.WindowsSysImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */