package org.schema.game.common.crashreporter;

import class_804;
import com.google.code.tempusfugit.concurrency.DeadlockDetector;
import com.google.code.tempusfugit.concurrency.ThreadDump;
import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintStream;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrashReporter
  extends Observable
{
  private String field_1788;
  private String field_1789;
  private String field_1790;
  private String field_1791;
  private String field_1792;
  
  public static void main(String[] paramArrayOfString)
  {
    String str = "general error";
    paramArrayOfString = "schema@star-made.org";
    CrashReporter localCrashReporter;
    (localCrashReporter = new CrashReporter()).a2(paramArrayOfString, str);
    localCrashReporter.b();
  }
  
  public static void a()
  {
    Object localObject = new File("./logs/threaddump.txt");
    for (int i = 0; ((File)localObject).exists(); i++) {
      localObject = new File("./logs/threaddump" + i + ".txt");
    }
    ((File)localObject).createNewFile();
    ThreadDump.dumpThreads(localObject = new PrintStream((File)localObject));
    ((PrintStream)localObject).append("\n\n--------------\nDeadlock Check\n");
    DeadlockDetector.printDeadlocks((PrintStream)localObject);
    ((PrintStream)localObject).flush();
    ((PrintStream)localObject).close();
  }
  
  private static void a1(String paramString, BufferedWriter paramBufferedWriter)
  {
    String[] arrayOfString = paramString.split("\n");
    for (int i = 0; i < arrayOfString.length; i++)
    {
      paramBufferedWriter.append(arrayOfString[i]);
      paramBufferedWriter.newLine();
    }
    if (arrayOfString.length == 0) {
      paramBufferedWriter.append(paramString);
    }
  }
  
  public final void a2(String paramString1, String paramString2)
  {
    if (paramString1 != null)
    {
      str1 = paramString1;
      if (Pattern.compile("^[\\w\\-]([\\.\\w\\-\\+])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", 2).matcher(str1).matches()) {}
    }
    else
    {
      throw new IllegalArgumentException("Not a valid Email Address: " + paramString1);
    }
    this.field_1788 = paramString1;
    this.field_1789 = paramString2;
    String str2 = "os.name";
    String str1 = "os.version";
    String str4 = "os.arch";
    this.field_1790 = a3(new String[] { System.getProperty(str2), "VERSION: " + System.getProperty(str1), "ARCHITECTURE: " + System.getProperty(str4) });
    str2 = "java.version";
    str1 = "java.vendor";
    str4 = "java.home";
    String str5 = "java.vm.name";
    paramString1 = "java.vm.version";
    paramString2 = "java.library.path";
    String str3 = "user.dir";
    this.field_1792 = a3(new String[] { System.getProperty(str2), "VENDOR: " + System.getProperty(str1), "HOME: " + System.getProperty(str4), "JVMNAME: " + System.getProperty(str5), "JVMVERSION: " + System.getProperty(paramString1), "LIBPATH: " + System.getProperty(paramString2), "WORKING_DIR: " + System.getProperty(str3) });
    str2 = "Available processors: " + Runtime.getRuntime().availableProcessors() + " cores";
    str1 = "Free memory: " + Runtime.getRuntime().freeMemory() + " bytes";
    long l = Runtime.getRuntime().maxMemory();
    paramString1 = "Maximum memory: " + (l == 9223372036854775807L ? "no limit" : Long.valueOf(l)) + " bytes";
    paramString2 = "Total memory : " + Runtime.getRuntime().totalMemory() + " bytes";
    this.field_1791 = a3(new String[] { str2, "MEMORY: ", str1, paramString1, paramString2 });
  }
  
  private static String a3(String... paramVarArgs)
  {
    StringBuffer localStringBuffer;
    (localStringBuffer = new StringBuffer()).append("\n");
    for (int i = 0; i < paramVarArgs.length; i++)
    {
      localStringBuffer.append("    ");
      localStringBuffer.append(paramVarArgs[i]);
      localStringBuffer.append(";\n");
    }
    return localStringBuffer.toString();
  }
  
  public final void b()
  {
    new Thread(new class_804(this)).start();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.crashreporter.CrashReporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */