package org.lwjgl;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import org.lwjgl.input.Mouse;

public final class Sys
{
  private static final String JNI_LIBRARY_NAME = "lwjgl";
  private static final String VERSION = "2.9.0";
  private static final String POSTFIX64BIT = "64";
  private static final SysImplementation implementation = ;
  private static final boolean is64Bit;
  
  private static void doLoadLibrary(String lib_name)
  {
    AccessController.doPrivileged(new PrivilegedAction()
    {
      public Object run()
      {
        String library_path = System.getProperty("org.lwjgl.librarypath");
        if (library_path != null) {
          System.load(library_path + File.separator + System.mapLibraryName(this.val$lib_name));
        } else {
          System.loadLibrary(this.val$lib_name);
        }
        return null;
      }
    });
  }
  
  private static void loadLibrary(String lib_name)
  {
    String osArch = System.getProperty("os.arch");
    boolean is64bit = ("amd64".equals(osArch)) || ("x86_64".equals(osArch));
    if (is64bit) {
      try
      {
        doLoadLibrary(lib_name + "64");
        return;
      }
      catch (UnsatisfiedLinkError local_e)
      {
        LWJGLUtil.log("Failed to load 64 bit library: " + local_e.getMessage());
      }
    }
    try
    {
      doLoadLibrary(lib_name);
    }
    catch (UnsatisfiedLinkError local_e)
    {
      if (implementation.has64Bit()) {
        try
        {
          doLoadLibrary(lib_name + "64");
          return;
        }
        catch (UnsatisfiedLinkError local_e2)
        {
          LWJGLUtil.log("Failed to load 64 bit library: " + local_e2.getMessage());
        }
      }
      throw local_e;
    }
  }
  
  private static SysImplementation createImplementation()
  {
    switch ()
    {
    case 1: 
      return new LinuxSysImplementation();
    case 3: 
      return new WindowsSysImplementation();
    case 2: 
      return new MacOSXSysImplementation();
    }
    throw new IllegalStateException("Unsupported platform");
  }
  
  public static String getVersion()
  {
    return "2.9.0";
  }
  
  public static void initialize() {}
  
  public static boolean is64Bit()
  {
    return is64Bit;
  }
  
  public static long getTimerResolution()
  {
    return implementation.getTimerResolution();
  }
  
  public static long getTime()
  {
    return implementation.getTime() & 0xFFFFFFFF;
  }
  
  public static void alert(String title, String message)
  {
    boolean grabbed = Mouse.isGrabbed();
    if (grabbed) {
      Mouse.setGrabbed(false);
    }
    if (title == null) {
      title = "";
    }
    if (message == null) {
      message = "";
    }
    implementation.alert(title, message);
    if (grabbed) {
      Mouse.setGrabbed(true);
    }
  }
  
  public static boolean openURL(String url)
  {
    try
    {
      Class<?> serviceManagerClass = Class.forName("javax.jnlp.ServiceManager");
      Method lookupMethod = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction()
      {
        public Method run()
          throws Exception
        {
          return this.val$serviceManagerClass.getMethod("lookup", new Class[] { String.class });
        }
      });
      Object basicService = lookupMethod.invoke(serviceManagerClass, new Object[] { "javax.jnlp.BasicService" });
      Class<?> basicServiceClass = Class.forName("javax.jnlp.BasicService");
      Method showDocumentMethod = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction()
      {
        public Method run()
          throws Exception
        {
          return this.val$basicServiceClass.getMethod("showDocument", new Class[] { URL.class });
        }
      });
      try
      {
        Boolean ret = (Boolean)showDocumentMethod.invoke(basicService, new Object[] { new URL(url) });
        return ret.booleanValue();
      }
      catch (MalformedURLException ret)
      {
        ret.printStackTrace(System.err);
        return false;
      }
      return implementation.openURL(url);
    }
    catch (Exception serviceManagerClass) {}
  }
  
  public static String getClipboard()
  {
    return implementation.getClipboard();
  }
  
  static
  {
    loadLibrary("lwjgl");
    is64Bit = implementation.getPointerSize() == 8;
    int native_jni_version = implementation.getJNIVersion();
    int required_version = implementation.getRequiredJNIVersion();
    if (native_jni_version != required_version) {
      throw new LinkageError("Version mismatch: jar version is '" + required_version + "', native library version is '" + native_jni_version + "'");
    }
    implementation.setDebug(LWJGLUtil.DEBUG);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.Sys
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */