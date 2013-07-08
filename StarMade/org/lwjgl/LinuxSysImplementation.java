package org.lwjgl;

import java.awt.Toolkit;
import java.security.AccessController;
import java.security.PrivilegedAction;

final class LinuxSysImplementation
  extends J2SESysImplementation
{
  private static final int JNI_VERSION = 19;
  
  public int getRequiredJNIVersion()
  {
    return 19;
  }
  
  public boolean openURL(String url)
  {
    String[] browsers = { "xdg-open", "firefox", "mozilla", "opera", "konqueror", "nautilus", "galeon", "netscape" };
    String[] arr$ = browsers;
    int len$ = arr$.length;
    int local_i$ = 0;
    while (local_i$ < len$)
    {
      String browser = arr$[local_i$];
      try
      {
        LWJGLUtil.execPrivileged(new String[] { browser, url });
        return true;
      }
      catch (Exception local_e)
      {
        local_e.printStackTrace(System.err);
        local_i$++;
      }
    }
    return false;
  }
  
  public boolean has64Bit()
  {
    return true;
  }
  
  static
  {
    Toolkit.getDefaultToolkit();
    AccessController.doPrivileged(new PrivilegedAction()
    {
      public Object run()
      {
        try
        {
          System.loadLibrary("jawt");
        }
        catch (UnsatisfiedLinkError local_e) {}
        return null;
      }
    });
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.LinuxSysImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */