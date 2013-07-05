/*     */ package org.schema.game.common.crashreporter;
/*     */ 
/*     */ import com.google.code.tempusfugit.concurrency.DeadlockDetector;
/*     */ import com.google.code.tempusfugit.concurrency.ThreadDump;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Observable;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import la;
/*     */ 
/*     */ public class CrashReporter extends Observable
/*     */ {
/*     */   private String a;
/*     */   private String b;
/*     */   private String c;
/*     */   private String d;
/*     */   private String e;
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  36 */     String str = "general error"; paramArrayOfString = "schema@star-made.org";
/*     */     CrashReporter localCrashReporter;
/*  36 */     (localCrashReporter = new CrashReporter()).a(paramArrayOfString, str); localCrashReporter.b();
/*     */   }
/*     */ 
/*     */   public static void a()
/*     */   {
/*  48 */     Object localObject = new File("./logs/threaddump.txt");
/*  49 */     int i = 0;
/*  50 */     while (((File)localObject).exists()) {
/*  51 */       localObject = new File("./logs/threaddump" + i + ".txt");
/*  52 */       i++;
/*     */     }
/*  54 */     ((File)localObject).createNewFile();
/*     */ 
/*  56 */     ThreadDump.dumpThreads(localObject = new PrintStream((File)localObject));
/*     */ 
/*  57 */     ((PrintStream)localObject).append("\n\n--------------\nDeadlock Check\n");
/*  58 */     DeadlockDetector.printDeadlocks((PrintStream)localObject);
/*     */ 
/*  60 */     ((PrintStream)localObject).flush();
/*  61 */     ((PrintStream)localObject).close();
/*     */   }
/*     */ 
/*     */   private static void a(String paramString, BufferedWriter paramBufferedWriter)
/*     */   {
/* 145 */     String[] arrayOfString = paramString.split("\n");
/* 146 */     for (int i = 0; i < arrayOfString.length; i++) {
/* 147 */       paramBufferedWriter.append(arrayOfString[i]);
/* 148 */       paramBufferedWriter.newLine();
/*     */     }
/* 150 */     if (arrayOfString.length == 0)
/* 151 */       paramBufferedWriter.append(paramString);
/*     */   }
/*     */ 
/*     */   public final void a(String paramString1, String paramString2) {
/* 155 */     if (paramString1 != null) { str1 = paramString1; if (Pattern.compile("^[\\w\\-]([\\.\\w\\-\\+])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", 2).matcher(str1).matches()); } else { throw new IllegalArgumentException("Not a valid Email Address: " + paramString1); }
/*     */ 
/* 158 */     this.jdField_a_of_type_JavaLangString = paramString1;
/* 159 */     this.b = paramString2;
/* 160 */     String str2 = "os.name"; String str1 = "os.version"; String str4 = "os.arch"; this.c = a(new String[] { System.getProperty(str2), "VERSION: " + System.getProperty(str1), "ARCHITECTURE: " + System.getProperty(str4) });
/* 161 */     str2 = "java.version"; str1 = "java.vendor"; str4 = "java.home"; String str5 = "java.vm.name"; paramString1 = "java.vm.version"; paramString2 = "java.library.path"; String str3 = "user.dir"; this.e = a(new String[] { System.getProperty(str2), "VENDOR: " + System.getProperty(str1), "HOME: " + System.getProperty(str4), "JVMNAME: " + System.getProperty(str5), "JVMVERSION: " + System.getProperty(paramString1), "LIBPATH: " + System.getProperty(paramString2), "WORKING_DIR: " + System.getProperty(str3) });
/* 162 */     str2 = "Available processors: " + Runtime.getRuntime().availableProcessors() + " cores"; str1 = "Free memory: " + Runtime.getRuntime().freeMemory() + " bytes"; long l = Runtime.getRuntime().maxMemory(); paramString1 = "Maximum memory: " + (l == 9223372036854775807L ? "no limit" : Long.valueOf(l)) + " bytes"; paramString2 = "Total memory : " + Runtime.getRuntime().totalMemory() + " bytes"; this.d = a(new String[] { str2, "MEMORY: ", str1, paramString1, paramString2 });
/*     */   }
/*     */ 
/*     */   private static String a(String[] paramArrayOfString)
/*     */   {
/*     */     StringBuffer localStringBuffer;
/* 165 */     (
/* 166 */       localStringBuffer = new StringBuffer())
/* 166 */       .append("\n");
/* 167 */     for (int i = 0; i < paramArrayOfString.length; i++) {
/* 168 */       localStringBuffer.append("    ");
/* 169 */       localStringBuffer.append(paramArrayOfString[i]);
/* 170 */       localStringBuffer.append(";\n");
/*     */     }
/* 172 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 263 */     new Thread(new la(this)).start();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.crashreporter.CrashReporter
 * JD-Core Version:    0.6.2
 */