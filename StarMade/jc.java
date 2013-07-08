/*  1:   */import java.io.File;
/*  2:   */import java.io.PrintStream;
/*  3:   */import java.lang.reflect.Field;
/*  4:   */import java.util.Arrays;
/*  5:   */
/*  8:   */public final class jc
/*  9:   */{
/* 10:10 */  private static String a = "." + File.separator + "native" + File.separator;
/* 11:   */  
/* 12:   */  private static void a(String paramString) { Field localField;
/* 13:13 */    (localField = ClassLoader.class.getDeclaredField("usr_paths")).setAccessible(true);
/* 14:   */    
/* 15:   */    String[] arrayOfString1;
/* 16:   */    
/* 17:17 */    int i = (arrayOfString2 = arrayOfString1 = (String[])localField.get(null)).length; for (int j = 0; j < i; j++) {
/* 18:18 */      if (arrayOfString2[j].equals(paramString)) {
/* 19:   */        return;
/* 20:   */      }
/* 21:   */    }
/* 22:   */    
/* 23:23 */    String[] tmp67_64 = ((String[])Arrays.copyOf(arrayOfString1, arrayOfString1.length + 1)); String[] 
/* 24:24 */      tmp68_67 = tmp67_64;String[] arrayOfString2 = tmp68_67;tmp67_64[(tmp68_67.length - 1)] = paramString;
/* 25:25 */    localField.set(null, arrayOfString2);
/* 26:   */  }
/* 27:   */  
/* 30:   */  public static void a()
/* 31:   */  {
/* 32:32 */    System.out.println("[LIBLOADER] OS ARCHITECTURE " + System.getProperty("os.arch"));
/* 33:33 */    String str = "";
/* 34:   */    boolean bool;
/* 35:35 */    if ((bool = System.getProperty("os.arch").contains("64"))) {
/* 36:36 */      str = File.separator + "x64" + File.separator;
/* 37:   */    }
/* 38:38 */    if (System.getProperty("os.name").equals("Mac OS X"))
/* 39:   */    {
/* 40:40 */      a(a + "macosx");
/* 44:   */    }
/* 45:45 */    else if (System.getProperty("os.name").contains("Linux")) {
/* 46:46 */      a(a + "linux");
/* 51:   */    }
/* 52:   */    else
/* 53:   */    {
/* 58:58 */      a(a + "windows" + str);
/* 59:59 */      if (bool) {
/* 60:60 */        System.loadLibrary("jinput-dx8_64");
/* 61:61 */        System.loadLibrary("lwjgl64");
/* 62:62 */        System.loadLibrary("OpenAL64");
/* 63:63 */        System.out.println("[LIBLOADER] LOADED WINDOWS 64bit NATIVE LIBRARIES ");
/* 64:   */      } else {
/* 65:65 */        System.loadLibrary("jinput-dx8");
/* 66:66 */        System.loadLibrary("lwjgl");
/* 67:67 */        System.loadLibrary("OpenAL32");
/* 68:68 */        System.out.println("[LIBLOADER] LOADED WINDOWS 32bit NATIVE LIBRARIES ");
/* 69:   */      }
/* 70:   */    }
/* 71:71 */    System.out.println("[LIBLOADER] LOADED NATIVE LIBRARIES ");
/* 72:   */  }
/* 73:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */