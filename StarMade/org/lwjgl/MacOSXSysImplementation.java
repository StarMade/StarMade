package org.lwjgl;

import com.apple.eio.FileManager;
import java.awt.Toolkit;

final class MacOSXSysImplementation
  extends J2SESysImplementation
{
  private static final int JNI_VERSION = 25;
  
  public int getRequiredJNIVersion()
  {
    return 25;
  }
  
  public boolean openURL(String url)
  {
    try
    {
      FileManager.openURL(url);
      return true;
    }
    catch (Exception local_e)
    {
      LWJGLUtil.log("Exception occurred while trying to invoke browser: " + local_e);
    }
    return false;
  }
  
  static
  {
    Toolkit.getDefaultToolkit();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.MacOSXSysImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */