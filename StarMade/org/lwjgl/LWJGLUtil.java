package org.lwjgl;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class LWJGLUtil
{
  public static final int PLATFORM_LINUX = 1;
  public static final int PLATFORM_MACOSX = 2;
  public static final int PLATFORM_WINDOWS = 3;
  public static final String PLATFORM_LINUX_NAME = "linux";
  public static final String PLATFORM_MACOSX_NAME = "macosx";
  public static final String PLATFORM_WINDOWS_NAME = "windows";
  private static final String LWJGL_ICON_DATA_16x16 = "";
  private static final String LWJGL_ICON_DATA_32x32 = "";
  public static final ByteBuffer LWJGLIcon16x16 = loadIcon("");
  public static final ByteBuffer LWJGLIcon32x32 = loadIcon("");
  public static final boolean DEBUG = getPrivilegedBoolean("org.lwjgl.util.Debug");
  public static final boolean CHECKS = !getPrivilegedBoolean("org.lwjgl.util.NoChecks");
  private static final int PLATFORM;
  
  private static ByteBuffer loadIcon(String data)
  {
    int len = data.length();
    ByteBuffer local_bb = BufferUtils.createByteBuffer(len);
    for (int local_i = 0; local_i < len; local_i++) {
      local_bb.put(local_i, (byte)data.charAt(local_i));
    }
    return local_bb.asReadOnlyBuffer();
  }
  
  public static int getPlatform()
  {
    return PLATFORM;
  }
  
  public static String getPlatformName()
  {
    switch ()
    {
    case 1: 
      return "linux";
    case 2: 
      return "macosx";
    case 3: 
      return "windows";
    }
    return "unknown";
  }
  
  public static String[] getLibraryPaths(String libname, String platform_lib_name, ClassLoader classloader)
  {
    return getLibraryPaths(libname, new String[] { platform_lib_name }, classloader);
  }
  
  public static String[] getLibraryPaths(String libname, String[] platform_lib_names, ClassLoader classloader)
  {
    List<String> possible_paths = new ArrayList();
    String classloader_path = getPathFromClassLoader(libname, classloader);
    if (classloader_path != null)
    {
      log("getPathFromClassLoader: Path found: " + classloader_path);
      possible_paths.add(classloader_path);
    }
    for (String platform_lib_name : platform_lib_names)
    {
      String lwjgl_classloader_path = getPathFromClassLoader("lwjgl", classloader);
      if (lwjgl_classloader_path != null)
      {
        log("getPathFromClassLoader: Path found: " + lwjgl_classloader_path);
        possible_paths.add(lwjgl_classloader_path.substring(0, lwjgl_classloader_path.lastIndexOf(File.separator)) + File.separator + platform_lib_name);
      }
      String alternative_path = getPrivilegedProperty("org.lwjgl.librarypath");
      if (alternative_path != null) {
        possible_paths.add(alternative_path + File.separator + platform_lib_name);
      }
      String java_library_path = getPrivilegedProperty("java.library.path");
      StringTokenizer local_st = new StringTokenizer(java_library_path, File.pathSeparator);
      while (local_st.hasMoreTokens())
      {
        String path = local_st.nextToken();
        possible_paths.add(path + File.separator + platform_lib_name);
      }
      String path = getPrivilegedProperty("user.dir");
      possible_paths.add(path + File.separator + platform_lib_name);
      possible_paths.add(platform_lib_name);
    }
    return (String[])possible_paths.toArray(new String[possible_paths.size()]);
  }
  
  static void execPrivileged(String[] cmd_array)
    throws Exception
  {
    try
    {
      Process process = (Process)AccessController.doPrivileged(new PrivilegedExceptionAction()
      {
        public Process run()
          throws Exception
        {
          return Runtime.getRuntime().exec(this.val$cmd_array);
        }
      });
      process.getInputStream().close();
      process.getOutputStream().close();
      process.getErrorStream().close();
    }
    catch (PrivilegedActionException process)
    {
      throw ((Exception)process.getCause());
    }
  }
  
  private static String getPrivilegedProperty(String property_name)
  {
    (String)AccessController.doPrivileged(new PrivilegedAction()
    {
      public String run()
      {
        return System.getProperty(this.val$property_name);
      }
    });
  }
  
  private static String getPathFromClassLoader(final String libname, final ClassLoader classloader)
  {
    try
    {
      log("getPathFromClassLoader: searching for: " + libname);
      Class<?> local_c = classloader.getClass();
      while (local_c != null)
      {
        Class<?> clazz = local_c;
        try
        {
          (String)AccessController.doPrivileged(new PrivilegedExceptionAction()
          {
            public String run()
              throws Exception
            {
              Method findLibrary = this.val$clazz.getDeclaredMethod("findLibrary", new Class[] { String.class });
              findLibrary.setAccessible(true);
              String path = (String)findLibrary.invoke(classloader, new Object[] { libname });
              return path;
            }
          });
        }
        catch (PrivilegedActionException local_e)
        {
          log("Failed to locate findLibrary method: " + local_e.getCause());
          local_c = local_c.getSuperclass();
        }
      }
    }
    catch (Exception local_c)
    {
      log("Failure locating " + local_c + " using classloader:" + local_c);
    }
    return null;
  }
  
  public static boolean getPrivilegedBoolean(String property_name)
  {
    ((Boolean)AccessController.doPrivileged(new PrivilegedAction())
    {
      public Boolean run()
      {
        return Boolean.valueOf(Boolean.getBoolean(this.val$property_name));
      }
    }()).booleanValue();
  }
  
  public static Integer getPrivilegedInteger(String property_name)
  {
    (Integer)AccessController.doPrivileged(new PrivilegedAction()
    {
      public Integer run()
      {
        return Integer.getInteger(this.val$property_name);
      }
    });
  }
  
  public static Integer getPrivilegedInteger(String property_name, final int default_val)
  {
    (Integer)AccessController.doPrivileged(new PrivilegedAction()
    {
      public Integer run()
      {
        return Integer.getInteger(this.val$property_name, default_val);
      }
    });
  }
  
  public static void log(CharSequence msg)
  {
    if (DEBUG) {
      System.err.println("[LWJGL] " + msg);
    }
  }
  
  public static boolean isMacOSXEqualsOrBetterThan(int major_required, int minor_required)
  {
    String os_version = getPrivilegedProperty("os.version");
    StringTokenizer version_tokenizer = new StringTokenizer(os_version, ".");
    int major;
    int minor;
    try
    {
      String major_str = version_tokenizer.nextToken();
      String minor_str = version_tokenizer.nextToken();
      major = Integer.parseInt(major_str);
      minor = Integer.parseInt(minor_str);
    }
    catch (Exception major_str)
    {
      log("Exception occurred while trying to determine OS version: " + major_str);
      return false;
    }
    return (major > major_required) || ((major == major_required) && (minor >= minor_required));
  }
  
  public static Map<Integer, String> getClassTokens(TokenFilter filter, Map<Integer, String> target, Class... tokenClasses)
  {
    return getClassTokens(filter, target, Arrays.asList(tokenClasses));
  }
  
  public static Map<Integer, String> getClassTokens(TokenFilter filter, Map<Integer, String> target, Iterable<Class> tokenClasses)
  {
    if (target == null) {
      target = new HashMap();
    }
    int TOKEN_MODIFIERS = 25;
    Iterator local_i$1 = tokenClasses.iterator();
    while (local_i$1.hasNext())
    {
      Class tokenClass = (Class)local_i$1.next();
      for (Field field : tokenClass.getDeclaredFields()) {
        if (((field.getModifiers() & 0x19) == 25) && (field.getType() == Integer.TYPE)) {
          try
          {
            int value = field.getInt(null);
            if ((filter == null) || (filter.accept(field, value))) {
              if (target.containsKey(Integer.valueOf(value))) {
                target.put(Integer.valueOf(value), toHexString(value));
              } else {
                target.put(Integer.valueOf(value), field.getName());
              }
            }
          }
          catch (IllegalAccessException value) {}
        }
      }
    }
    return target;
  }
  
  public static String toHexString(int value)
  {
    return "0x" + Integer.toHexString(value).toUpperCase();
  }
  
  static
  {
    String osName = getPrivilegedProperty("os.name");
    if (osName.startsWith("Windows")) {
      PLATFORM = 3;
    } else if ((osName.startsWith("Linux")) || (osName.startsWith("FreeBSD")) || (osName.startsWith("SunOS")) || (osName.startsWith("Unix"))) {
      PLATFORM = 1;
    } else if ((osName.startsWith("Mac OS X")) || (osName.startsWith("Darwin"))) {
      PLATFORM = 2;
    } else {
      throw new LinkageError("Unknown platform: " + osName);
    }
  }
  
  public static abstract interface TokenFilter
  {
    public abstract boolean accept(Field paramField, int paramInt);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.LWJGLUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */