/*   1:    */package org.schema.game.common.crashreporter;
/*   2:    */
/*   3:    */import com.google.code.tempusfugit.concurrency.DeadlockDetector;
/*   4:    */import com.google.code.tempusfugit.concurrency.ThreadDump;
/*   5:    */import java.io.BufferedWriter;
/*   6:    */import java.io.File;
/*   7:    */import java.io.PrintStream;
/*   8:    */import java.util.Observable;
/*   9:    */import java.util.regex.Matcher;
/*  10:    */import java.util.regex.Pattern;
/*  11:    */import la;
/*  12:    */
/*  25:    */public class CrashReporter
/*  26:    */  extends Observable
/*  27:    */{
/*  28:    */  private String a;
/*  29:    */  private String b;
/*  30:    */  private String c;
/*  31:    */  private String d;
/*  32:    */  private String e;
/*  33:    */  
/*  34:    */  public static void main(String[] paramArrayOfString)
/*  35:    */  {
/*  36: 36 */    String str = "general error";paramArrayOfString = "schema@star-made.org"; CrashReporter localCrashReporter; (localCrashReporter = new CrashReporter()).a(paramArrayOfString, str);localCrashReporter.b();
/*  37:    */  }
/*  38:    */  
/*  46:    */  public static void a()
/*  47:    */  {
/*  48: 48 */    Object localObject = new File("./logs/threaddump.txt");
/*  49: 49 */    int i = 0;
/*  50: 50 */    while (((File)localObject).exists()) {
/*  51: 51 */      localObject = new File("./logs/threaddump" + i + ".txt");
/*  52: 52 */      i++;
/*  53:    */    }
/*  54: 54 */    ((File)localObject).createNewFile();
/*  55:    */    
/*  56: 56 */    ThreadDump.dumpThreads(localObject = new PrintStream((File)localObject));
/*  57: 57 */    ((PrintStream)localObject).append("\n\n--------------\nDeadlock Check\n");
/*  58: 58 */    DeadlockDetector.printDeadlocks((PrintStream)localObject);
/*  59:    */    
/*  60: 60 */    ((PrintStream)localObject).flush();
/*  61: 61 */    ((PrintStream)localObject).close();
/*  62:    */  }
/*  63:    */  
/* 143:    */  private static void a(String paramString, BufferedWriter paramBufferedWriter)
/* 144:    */  {
/* 145:145 */    String[] arrayOfString = paramString.split("\n");
/* 146:146 */    for (int i = 0; i < arrayOfString.length; i++) {
/* 147:147 */      paramBufferedWriter.append(arrayOfString[i]);
/* 148:148 */      paramBufferedWriter.newLine();
/* 149:    */    }
/* 150:150 */    if (arrayOfString.length == 0)
/* 151:151 */      paramBufferedWriter.append(paramString);
/* 152:    */  }
/* 153:    */  
/* 154:    */  public final void a(String paramString1, String paramString2) {
/* 155:155 */    if (paramString1 != null) { str1 = paramString1; if (Pattern.compile("^[\\w\\-]([\\.\\w\\-\\+])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", 2).matcher(str1).matches()) {}
/* 156:156 */    } else { throw new IllegalArgumentException("Not a valid Email Address: " + paramString1);
/* 157:    */    }
/* 158:158 */    this.jdField_a_of_type_JavaLangString = paramString1;
/* 159:159 */    this.b = paramString2;
/* 160:160 */    String str2 = "os.name";String str1 = "os.version";String str4 = "os.arch";this.c = a(new String[] { System.getProperty(str2), "VERSION: " + System.getProperty(str1), "ARCHITECTURE: " + System.getProperty(str4) });
/* 161:161 */    str2 = "java.version";str1 = "java.vendor";str4 = "java.home";String str5 = "java.vm.name";paramString1 = "java.vm.version";paramString2 = "java.library.path";String str3 = "user.dir";this.e = a(new String[] { System.getProperty(str2), "VENDOR: " + System.getProperty(str1), "HOME: " + System.getProperty(str4), "JVMNAME: " + System.getProperty(str5), "JVMVERSION: " + System.getProperty(paramString1), "LIBPATH: " + System.getProperty(paramString2), "WORKING_DIR: " + System.getProperty(str3) });
/* 162:162 */    str2 = "Available processors: " + Runtime.getRuntime().availableProcessors() + " cores";str1 = "Free memory: " + Runtime.getRuntime().freeMemory() + " bytes";long l = Runtime.getRuntime().maxMemory();paramString1 = "Maximum memory: " + (l == 9223372036854775807L ? "no limit" : Long.valueOf(l)) + " bytes";paramString2 = "Total memory : " + Runtime.getRuntime().totalMemory() + " bytes";this.d = a(new String[] { str2, "MEMORY: ", str1, paramString1, paramString2 });
/* 163:    */  }
/* 164:    */  
/* 165:    */  private static String a(String... paramVarArgs) { StringBuffer localStringBuffer;
/* 166:166 */    (localStringBuffer = new StringBuffer()).append("\n");
/* 167:167 */    for (int i = 0; i < paramVarArgs.length; i++) {
/* 168:168 */      localStringBuffer.append("    ");
/* 169:169 */      localStringBuffer.append(paramVarArgs[i]);
/* 170:170 */      localStringBuffer.append(";\n");
/* 171:    */    }
/* 172:172 */    return localStringBuffer.toString();
/* 173:    */  }
/* 174:    */  
/* 261:    */  public final void b()
/* 262:    */  {
/* 263:263 */    new Thread(new la(this)).start();
/* 264:    */  }
/* 265:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.crashreporter.CrashReporter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */