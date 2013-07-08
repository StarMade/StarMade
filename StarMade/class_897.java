import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Arrays;

public final class class_897
{
  private static String field_1124 = "." + File.separator + "native" + File.separator;
  
  private static void a(String paramString)
  {
    Field localField;
    (localField = ClassLoader.class.getDeclaredField("usr_paths")).setAccessible(true);
    String[] arrayOfString1;
    int i = (arrayOfString2 = arrayOfString1 = (String[])localField.get(null)).length;
    for (int j = 0; j < i; j++) {
      if (arrayOfString2[j].equals(paramString)) {
        return;
      }
    }
    String[] tmp67_64 = ((String[])Arrays.copyOf(arrayOfString1, arrayOfString1.length + 1));
    String[] tmp68_67 = tmp67_64;
    String[] arrayOfString2 = tmp68_67;
    tmp67_64[(tmp68_67.length - 1)] = paramString;
    localField.set(null, arrayOfString2);
  }
  
  public static void a1()
  {
    System.out.println("[LIBLOADER] OS ARCHITECTURE " + System.getProperty("os.arch"));
    String str = "";
    boolean bool;
    if ((bool = System.getProperty("os.arch").contains("64"))) {
      str = File.separator + "x64" + File.separator;
    }
    if (System.getProperty("os.name").equals("Mac OS X"))
    {
      a(field_1124 + "macosx");
    }
    else if (System.getProperty("os.name").contains("Linux"))
    {
      a(field_1124 + "linux");
    }
    else
    {
      a(field_1124 + "windows" + str);
      if (bool)
      {
        System.loadLibrary("jinput-dx8_64");
        System.loadLibrary("lwjgl64");
        System.loadLibrary("OpenAL64");
        System.out.println("[LIBLOADER] LOADED WINDOWS 64bit NATIVE LIBRARIES ");
      }
      else
      {
        System.loadLibrary("jinput-dx8");
        System.loadLibrary("lwjgl");
        System.loadLibrary("OpenAL32");
        System.out.println("[LIBLOADER] LOADED WINDOWS 32bit NATIVE LIBRARIES ");
      }
    }
    System.out.println("[LIBLOADER] LOADED NATIVE LIBRARIES ");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_897
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */