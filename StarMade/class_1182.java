import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.schema.game.common.updater.FileUtil;
import org.schema.game.common.updater.Launcher;

public final class class_1182
  extends Observable
{
  private static String jdField_field_1389_of_type_JavaLangString = "http://files.star-made.org/checksum";
  private static String jdField_field_1390_of_type_JavaLangString = "http://files.star-made.org/version";
  private static String field_1391 = "http://files.star-made.org/mirrors";
  private boolean jdField_field_1392_of_type_Boolean = false;
  private boolean jdField_field_1389_of_type_Boolean = false;
  private final ArrayList jdField_field_1392_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_1389_of_type_JavaUtilArrayList = new ArrayList();
  private boolean jdField_field_1390_of_type_Boolean;
  public static String field_1392;
  
  public static boolean a(File paramFile)
  {
    if (paramFile.isDirectory())
    {
      String[] arrayOfString = paramFile.list();
      for (int i = 0; i < arrayOfString.length; i++) {
        if (!a(new File(paramFile, arrayOfString[i]))) {
          return false;
        }
      }
    }
    return paramFile.delete();
  }
  
  public static void a1()
  {
    class_1182 localclass_1182 = new class_1182();
    try
    {
      localclass_1182.d();
      while (localclass_1182.jdField_field_1392_of_type_Boolean) {
        Thread.sleep(100L);
      }
      if (localclass_1182.a4())
      {
        System.err.println("A New Version Is Available!");
        localclass_1182.e();
        return;
      }
      System.err.println("You Are Already on the Newest Version: use -force to force an update");
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException;
    }
  }
  
  public class_1182()
  {
    c();
  }
  
  public final void b()
  {
    File localFile1;
    if (((localFile1 = new File("./StarMade/")).exists()) && (localFile1.list().length > 0))
    {
      setChanged();
      notifyObservers("Backing Up");
      Object localObject = "backup-StarMade-" + class_1266.jdField_field_1446_of_type_Float + "-" + class_1266.jdField_field_1446_of_type_JavaLangString + ".zip";
      System.out.println("Backing Up");
      class_29.a11("./StarMade/", (String)localObject, "backup-StarMade-");
      System.out.println("Copying Backup mFile to install dir...");
      File localFile2;
      if ((localFile2 = new File((String)localObject)).exists())
      {
        class_29.a19(localFile2, new File("./StarMade/" + (String)localObject));
        localFile2.delete();
      }
      setChanged();
      notifyObservers("Deleting old installation");
      System.out.println("Cleaning up current installation");
      localObject = localFile1.listFiles();
      for (int i = 0; i < localObject.length; i++)
      {
        File localFile3;
        if (((localFile3 = localObject[i]).getName().equals("data")) || (localFile3.getName().equals("native")) || (localFile3.getName().startsWith("StarMade")) || (localFile3.getName().equals("MANIFEST.MF")) || (localFile3.getName().equals("version.txt")))
        {
          a(localFile3);
          localFile3.delete();
        }
      }
      System.out.println("DONE, install dir is now: " + Arrays.toString(localFile1.list()));
    }
  }
  
  public final void a2(class_1205 paramclass_1205, String paramString)
  {
    if (!paramString.endsWith("/")) {
      paramString = paramString + "/";
    }
    System.err.println("Extracting " + paramclass_1205);
    ZipFile localZipFile;
    Enumeration localEnumeration = (localZipFile = new ZipFile(paramclass_1205.jdField_field_1423_of_type_JavaLangString)).entries();
    ZipEntry localZipEntry = null;
    new File(paramString).mkdirs();
    while (localEnumeration.hasMoreElements()) {
      if ((localZipEntry = (ZipEntry)localEnumeration.nextElement()).isDirectory())
      {
        System.err.println("Extracting directory: " + localZipEntry.getName());
        new File(paramString + localZipEntry.getName()).mkdir();
      }
      else
      {
        setChanged();
        notifyObservers("Extracting " + localZipEntry.getName());
        System.err.println("Extracting file: " + localZipEntry.getName());
        FileUtil.a1(localZipFile.getInputStream(localZipEntry), new BufferedOutputStream(new FileOutputStream(paramString + localZipEntry.getName())));
      }
    }
    localZipFile.close();
    System.err.println("Deleting archive " + paramclass_1205);
    new File(paramclass_1205.jdField_field_1423_of_type_JavaLangString).delete();
  }
  
  public static String a3()
  {
    return "./StarMade//StarMade.jar";
  }
  
  public final boolean a4()
  {
    if (!this.jdField_field_1389_of_type_Boolean) {
      return false;
    }
    if ((class_1266.jdField_field_1446_of_type_JavaLangString == null) || (class_1266.jdField_field_1446_of_type_JavaLangString.equals("undefined")))
    {
      System.err.println("Version build null or undefined");
      return true;
    }
    if (class_1266.jdField_field_1446_of_type_JavaLangString.equals("latest"))
    {
      System.err.println("newer version always available for develop version!");
      return true;
    }
    if (this.jdField_field_1392_of_type_JavaUtilArrayList.isEmpty())
    {
      System.err.println("versions empty");
      return false;
    }
    System.out.println("checking your version " + class_1266.jdField_field_1446_of_type_JavaLangString + " against latest " + ((class_1205)this.jdField_field_1392_of_type_JavaUtilArrayList.get(this.jdField_field_1392_of_type_JavaUtilArrayList.size() - 1)).a() + " = " + class_1266.jdField_field_1446_of_type_JavaLangString.compareTo(((class_1205)this.jdField_field_1392_of_type_JavaUtilArrayList.get(this.jdField_field_1392_of_type_JavaUtilArrayList.size() - 1)).a()));
    return class_1266.jdField_field_1446_of_type_JavaLangString.compareTo(((class_1205)this.jdField_field_1392_of_type_JavaUtilArrayList.get(this.jdField_field_1392_of_type_JavaUtilArrayList.size() - 1)).a()) < 0;
  }
  
  public static boolean b1()
  {
    return new File("./StarMade//StarMade.jar").exists();
  }
  
  public static void c()
  {
    try
    {
      class_1266.a1("./StarMade/");
      return;
    }
    catch (Exception localException)
    {
      localException;
    }
  }
  
  private static boolean a5(class_1205 paramclass_1205)
  {
    paramclass_1205 = jdField_field_1392_of_type_JavaLangString + paramclass_1205.jdField_field_1423_of_type_JavaLangString;
    try
    {
      (paramclass_1205 = new URL(paramclass_1205).openConnection()).setReadTimeout(15000);
      paramclass_1205.setRequestProperty("User-Agent", "StarMade-Updater_" + Launcher.field_2240);
      paramclass_1205.connect();
      paramclass_1205.getInputStream();
      return true;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      System.err.println("Mirror not available " + jdField_field_1392_of_type_JavaLangString);
    }
    return false;
  }
  
  public final void a6(class_1205 paramclass_1205)
  {
    ReadableByteChannel localReadableByteChannel = null;
    FileOutputStream localFileOutputStream = null;
    try
    {
      if (jdField_field_1392_of_type_JavaLangString == null)
      {
        localObject = new Random();
        jdField_field_1392_of_type_JavaLangString = (String)this.jdField_field_1389_of_type_JavaUtilArrayList.get(((Random)localObject).nextInt(this.jdField_field_1389_of_type_JavaUtilArrayList.size()));
      }
      if (!jdField_field_1392_of_type_JavaLangString.endsWith("/")) {
        jdField_field_1392_of_type_JavaLangString += "/";
      }
      setChanged();
      notifyObservers("connecting...");
      localObject = jdField_field_1392_of_type_JavaLangString + paramclass_1205.jdField_field_1423_of_type_JavaLangString;
      int i = this.jdField_field_1389_of_type_JavaUtilArrayList.size() * 3;
      for (int k = 0; (!a5(paramclass_1205)) && (k < i); k++)
      {
        class_29.a12(new Exception("Mirror: " + (String)localObject + " is isnvalid. Press retry to try another one."));
        Random localRandom = new Random();
        jdField_field_1392_of_type_JavaLangString = (String)this.jdField_field_1389_of_type_JavaUtilArrayList.get(localRandom.nextInt(this.jdField_field_1389_of_type_JavaUtilArrayList.size()));
      }
      URLConnection localURLConnection;
      (localURLConnection = new URL((String)localObject).openConnection()).setReadTimeout(50000);
      localURLConnection.setRequestProperty("User-Agent", "StarMade-Updater_" + Launcher.field_2240);
      localReadableByteChannel = Channels.newChannel(new BufferedInputStream(localURLConnection.getInputStream()));
      localFileOutputStream = new FileOutputStream(paramclass_1205.jdField_field_1423_of_type_JavaLangString);
      int j = 0;
      long[] arrayOfLong = new long[2];
      int n;
      for (int m = -1; j < paramclass_1205.jdField_field_1423_of_type_Long; m = n)
      {
        long l = Math.min(1024L, paramclass_1205.jdField_field_1423_of_type_Long - j);
        localFileOutputStream.getChannel().transferFrom(localReadableByteChannel, j, l);
        j = (int)(j + l);
        arrayOfLong[0] = j;
        arrayOfLong[1] = paramclass_1205.jdField_field_1423_of_type_Long;
        setChanged();
        notifyObservers(arrayOfLong);
        if ((countObservers() == 0) && ((n = (int)((float)arrayOfLong[0] / (float)arrayOfLong[1] * 100.0F)) != m)) {
          System.err.println("LOADED: " + j + " / " + paramclass_1205.jdField_field_1423_of_type_Long + ":     " + n + "%");
        }
      }
      System.err.println("retrieved file " + paramclass_1205);
      String str2 = URLEncoder.encode("\"", "ISO-8859-1");
      String str3 = "{" + str2 + "1" + str2 + ":[" + str2 + "Mirror" + str2 + "," + str2 + jdField_field_1392_of_type_JavaLangString + str2 + "]," + str2 + "2" + str2 + ":[" + str2 + "Build" + str2 + "," + str2 + paramclass_1205.jdField_field_1423_of_type_JavaLangString + str2 + "]}";
      String str1 = "?url=" + (String)localObject + "&download=" + (String)localObject + "&_cvar=" + str3 + "&action_name=Launcher%20Downloads&idsite=1&rec=1&bots=1&idgoal=3";
      paramclass_1205 = "http://stats.star-made.org/piwik.php" + str1;
      System.err.println("ENCODED: " + paramclass_1205);
      (paramclass_1205 = new URL(paramclass_1205).openConnection()).setRequestProperty("User-Agent", "StarMade-Updater_" + Launcher.field_2240);
      paramclass_1205.connect();
      paramclass_1205.getInputStream();
      if (localReadableByteChannel != null) {
        localReadableByteChannel.close();
      }
      localFileOutputStream.close();
      return;
    }
    catch (Exception localException)
    {
      Object localObject;
      class_29.a12((Exception)localObject);
      return;
    }
    finally
    {
      if (localReadableByteChannel != null) {
        localReadableByteChannel.close();
      }
      if (localFileOutputStream != null) {
        localFileOutputStream.close();
      }
    }
  }
  
  public final void d()
  {
    this.jdField_field_1392_of_type_Boolean = true;
    new Thread(new class_1184(this)).start();
  }
  
  public final void e()
  {
    if (this.jdField_field_1390_of_type_Boolean) {
      return;
    }
    this.jdField_field_1390_of_type_Boolean = true;
    setChanged();
    notifyObservers("updating");
    new Thread(new class_1275(this)).start();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1182
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */