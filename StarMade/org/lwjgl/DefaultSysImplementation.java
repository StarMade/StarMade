package org.lwjgl;

abstract class DefaultSysImplementation
  implements SysImplementation
{
  public native int getJNIVersion();
  
  public native int getPointerSize();
  
  public native void setDebug(boolean paramBoolean);
  
  public long getTimerResolution()
  {
    return 1000L;
  }
  
  public boolean has64Bit()
  {
    return false;
  }
  
  public abstract long getTime();
  
  public abstract void alert(String paramString1, String paramString2);
  
  public abstract String getClipboard();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.DefaultSysImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */