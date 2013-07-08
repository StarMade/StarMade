/*  1:   */package org.lwjgl;
/*  2:   */
/* 12:   */abstract class DefaultSysImplementation
/* 13:   */  implements SysImplementation
/* 14:   */{
/* 15:   */  public native int getJNIVersion();
/* 16:   */  
/* 25:   */  public native int getPointerSize();
/* 26:   */  
/* 35:   */  public native void setDebug(boolean paramBoolean);
/* 36:   */  
/* 45:   */  public long getTimerResolution()
/* 46:   */  {
/* 47:47 */    return 1000L;
/* 48:   */  }
/* 49:   */  
/* 50:   */  public boolean has64Bit() {
/* 51:51 */    return false;
/* 52:   */  }
/* 53:   */  
/* 54:   */  public abstract long getTime();
/* 55:   */  
/* 56:   */  public abstract void alert(String paramString1, String paramString2);
/* 57:   */  
/* 58:   */  public abstract String getClipboard();
/* 59:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.DefaultSysImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */