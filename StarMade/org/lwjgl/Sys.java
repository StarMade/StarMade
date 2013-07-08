/*   1:    */package org.lwjgl;
/*   2:    */
/*   3:    */import java.io.File;
/*   4:    */import java.lang.reflect.Method;
/*   5:    */import java.net.MalformedURLException;
/*   6:    */import java.net.URL;
/*   7:    */import java.security.AccessController;
/*   8:    */import java.security.PrivilegedAction;
/*   9:    */import java.security.PrivilegedExceptionAction;
/*  10:    */import org.lwjgl.input.Mouse;
/*  11:    */
/*  58:    */public final class Sys
/*  59:    */{
/*  60:    */  private static final String JNI_LIBRARY_NAME = "lwjgl";
/*  61:    */  private static final String VERSION = "2.9.0";
/*  62:    */  private static final String POSTFIX64BIT = "64";
/*  63:    */  
/*  64:    */  private static void doLoadLibrary(String lib_name)
/*  65:    */  {
/*  66: 66 */    AccessController.doPrivileged(new PrivilegedAction() {
/*  67:    */      public Object run() {
/*  68: 68 */        String library_path = System.getProperty("org.lwjgl.librarypath");
/*  69: 69 */        if (library_path != null) {
/*  70: 70 */          System.load(library_path + File.separator + System.mapLibraryName(this.val$lib_name));
/*  71:    */        }
/*  72:    */        else {
/*  73: 73 */          System.loadLibrary(this.val$lib_name);
/*  74:    */        }
/*  75: 75 */        return null;
/*  76:    */      }
/*  77:    */    });
/*  78:    */  }
/*  79:    */  
/*  80:    */  private static void loadLibrary(String lib_name)
/*  81:    */  {
/*  82: 82 */    String osArch = System.getProperty("os.arch");
/*  83: 83 */    boolean is64bit = ("amd64".equals(osArch)) || ("x86_64".equals(osArch));
/*  84: 84 */    if (is64bit) {
/*  85:    */      try {
/*  86: 86 */        doLoadLibrary(lib_name + "64");
/*  87: 87 */        return;
/*  88:    */      } catch (UnsatisfiedLinkError e) {
/*  89: 89 */        LWJGLUtil.log("Failed to load 64 bit library: " + e.getMessage());
/*  90:    */      }
/*  91:    */    }
/*  92:    */    
/*  93:    */    try
/*  94:    */    {
/*  95: 95 */      doLoadLibrary(lib_name);
/*  96:    */    } catch (UnsatisfiedLinkError e) {
/*  97: 97 */      if (implementation.has64Bit()) {
/*  98:    */        try {
/*  99: 99 */          doLoadLibrary(lib_name + "64");
/* 100:100 */          return;
/* 101:    */        } catch (UnsatisfiedLinkError e2) {
/* 102:102 */          LWJGLUtil.log("Failed to load 64 bit library: " + e2.getMessage());
/* 103:    */        }
/* 104:    */      }
/* 105:    */      
/* 106:106 */      throw e;
/* 107:    */    }
/* 108:    */  }
/* 109:    */  
/* 111:111 */  private static final SysImplementation implementation = ;
/* 112:112 */  static { loadLibrary("lwjgl");
/* 113:113 */    is64Bit = implementation.getPointerSize() == 8;
/* 114:    */    
/* 115:115 */    int native_jni_version = implementation.getJNIVersion();
/* 116:116 */    int required_version = implementation.getRequiredJNIVersion();
/* 117:117 */    if (native_jni_version != required_version) {
/* 118:118 */      throw new LinkageError("Version mismatch: jar version is '" + required_version + "', native library version is '" + native_jni_version + "'");
/* 119:    */    }
/* 120:120 */    implementation.setDebug(LWJGLUtil.DEBUG);
/* 121:    */  }
/* 122:    */  
/* 123:    */  private static SysImplementation createImplementation() {
/* 124:124 */    switch () {
/* 125:    */    case 1: 
/* 126:126 */      return new LinuxSysImplementation();
/* 127:    */    case 3: 
/* 128:128 */      return new WindowsSysImplementation();
/* 129:    */    case 2: 
/* 130:130 */      return new MacOSXSysImplementation();
/* 131:    */    }
/* 132:132 */    throw new IllegalStateException("Unsupported platform");
/* 133:    */  }
/* 134:    */  
/* 144:    */  public static String getVersion()
/* 145:    */  {
/* 146:146 */    return "2.9.0";
/* 147:    */  }
/* 148:    */  
/* 155:    */  public static boolean is64Bit()
/* 156:    */  {
/* 157:157 */    return is64Bit;
/* 158:    */  }
/* 159:    */  
/* 165:    */  public static long getTimerResolution()
/* 166:    */  {
/* 167:167 */    return implementation.getTimerResolution();
/* 168:    */  }
/* 169:    */  
/* 176:    */  public static long getTime()
/* 177:    */  {
/* 178:178 */    return implementation.getTime() & 0xFFFFFFFF;
/* 179:    */  }
/* 180:    */  
/* 189:    */  private static final boolean is64Bit;
/* 190:    */  
/* 198:    */  public static void alert(String title, String message)
/* 199:    */  {
/* 200:200 */    boolean grabbed = Mouse.isGrabbed();
/* 201:201 */    if (grabbed) {
/* 202:202 */      Mouse.setGrabbed(false);
/* 203:    */    }
/* 204:204 */    if (title == null)
/* 205:205 */      title = "";
/* 206:206 */    if (message == null)
/* 207:207 */      message = "";
/* 208:208 */    implementation.alert(title, message);
/* 209:209 */    if (grabbed) {
/* 210:210 */      Mouse.setGrabbed(true);
/* 211:    */    }
/* 212:    */  }
/* 213:    */  
/* 227:    */  public static boolean openURL(String url)
/* 228:    */  {
/* 229:    */    try
/* 230:    */    {
/* 231:231 */      Class<?> serviceManagerClass = Class.forName("javax.jnlp.ServiceManager");
/* 232:232 */      Method lookupMethod = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/* 233:    */        public Method run() throws Exception {
/* 234:234 */          return this.val$serviceManagerClass.getMethod("lookup", new Class[] { String.class });
/* 235:    */        }
/* 236:236 */      });
/* 237:237 */      Object basicService = lookupMethod.invoke(serviceManagerClass, new Object[] { "javax.jnlp.BasicService" });
/* 238:238 */      Class<?> basicServiceClass = Class.forName("javax.jnlp.BasicService");
/* 239:239 */      Method showDocumentMethod = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/* 240:    */        public Method run() throws Exception {
/* 241:241 */          return this.val$basicServiceClass.getMethod("showDocument", new Class[] { URL.class });
/* 242:    */        }
/* 243:    */      });
/* 244:    */      try {
/* 245:245 */        Boolean ret = (Boolean)showDocumentMethod.invoke(basicService, new Object[] { new URL(url) });
/* 246:246 */        return ret.booleanValue();
/* 247:    */      } catch (MalformedURLException e) {
/* 248:248 */        e.printStackTrace(System.err);
/* 249:249 */        return false;
/* 250:    */      }
/* 251:    */      
/* 252:252 */      return implementation.openURL(url);
/* 253:    */    }
/* 254:    */    catch (Exception ue) {}
/* 255:    */  }
/* 256:    */  
/* 263:    */  public static String getClipboard()
/* 264:    */  {
/* 265:265 */    return implementation.getClipboard();
/* 266:    */  }
/* 267:    */  
/* 268:    */  public static void initialize() {}
/* 269:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.Sys
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */