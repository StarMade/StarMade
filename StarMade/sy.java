/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLEncoder;
/*     */ import java.nio.channels.Channels;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Observable;
/*     */ import java.util.Random;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ import org.schema.game.common.updater.FileUtil;
/*     */ import org.schema.game.common.updater.Launcher;
/*     */ 
/*     */ public final class sy extends Observable
/*     */ {
/*  35 */   private static String jdField_b_of_type_JavaLangString = "http://files.star-made.org/checksum";
/*     */ 
/*  37 */   private static String jdField_c_of_type_JavaLangString = "http://files.star-made.org/version";
/*  38 */   private static String d = "http://files.star-made.org/mirrors";
/*     */ 
/*  74 */   private boolean jdField_a_of_type_Boolean = false;
/*  75 */   private boolean jdField_b_of_type_Boolean = false;
/*     */ 
/*  78 */   private final ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  79 */   private final ArrayList jdField_b_of_type_JavaUtilArrayList = new ArrayList();
/*     */   private boolean jdField_c_of_type_Boolean;
/*     */   public static String a;
/*     */ 
/*     */   public static boolean a(File paramFile)
/*     */   {
/*  44 */     if (paramFile.isDirectory()) {
/*  45 */       String[] arrayOfString = paramFile.list();
/*  46 */       for (int i = 0; i < arrayOfString.length; i++)
/*     */       {
/*  48 */         if (!a(new File(paramFile, arrayOfString[i])))
/*     */         {
/*  49 */           return false;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  55 */     return paramFile.delete();
/*     */   }
/*     */   public static void a() {
/*  58 */     sy localsy = new sy();
/*     */     try {
/*  60 */       localsy.d();
/*  61 */       while (localsy.jdField_a_of_type_Boolean) {
/*  62 */         Thread.sleep(100L);
/*     */       }
/*  64 */       if (localsy.a()) {
/*  65 */         System.err.println("A New Version Is Available!");
/*  66 */         localsy.e(); return;
/*     */       }
/*  68 */       System.err.println("You Are Already on the Newest Version: use -force to force an update");
/*     */       return;
/*     */     } catch (InterruptedException localInterruptedException) {
/*  72 */       localInterruptedException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public sy()
/*     */   {
/*  86 */     c();
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*     */     File localFile1;
/*  92 */     if (((
/*  92 */       localFile1 = new File("./StarMade/"))
/*  92 */       .exists()) && (localFile1.list().length > 0)) {
/*  93 */       setChanged();
/*  94 */       notifyObservers("Backing Up");
/*  95 */       Object localObject = "backup-StarMade-" + sG.jdField_a_of_type_Float + "-" + sG.jdField_a_of_type_JavaLangString + ".zip";
/*  96 */       System.out.println("Backing Up");
/*     */ 
/*  99 */       d.a("./StarMade/", (String)localObject, "backup-StarMade-");
/*     */ 
/* 101 */       System.out.println("Copying Backup mFile to install dir...");
/*     */       File localFile2;
/* 103 */       if ((
/* 103 */         localFile2 = new File((String)localObject))
/* 103 */         .exists()) {
/* 104 */         d.a(localFile2, new File("./StarMade/" + (String)localObject));
/* 105 */         localFile2.delete();
/*     */       }
/*     */ 
/* 108 */       setChanged();
/* 109 */       notifyObservers("Deleting old installation");
/* 110 */       System.out.println("Cleaning up current installation");
/*     */ 
/* 117 */       localObject = localFile1.listFiles();
/* 118 */       for (int i = 0; i < localObject.length; i++)
/*     */       {
/*     */         File localFile3;
/* 120 */         if (((
/* 120 */           localFile3 = localObject[i])
/* 120 */           .getName().equals("data")) || (localFile3.getName().equals("native")) || (localFile3.getName().startsWith("StarMade")) || (localFile3.getName().equals("MANIFEST.MF")) || (localFile3.getName().equals("version.txt")))
/*     */         {
/* 127 */           a(localFile3);
/* 128 */           localFile3.delete();
/*     */         }
/*     */       }
/*     */ 
/* 132 */       System.out.println("DONE, install dir is now: " + Arrays.toString(localFile1.list()));
/*     */     }
/*     */   }
/*     */ 
/* 136 */   public final void a(sd paramsd, String paramString) { if (!paramString.endsWith("/")) {
/* 137 */       paramString = paramString + "/";
/*     */     }
/* 139 */     System.err.println("Extracting " + paramsd);
/*     */     ZipFile localZipFile;
/* 141 */     Enumeration localEnumeration = (
/* 141 */       localZipFile = new ZipFile(paramsd.jdField_a_of_type_JavaLangString))
/* 141 */       .entries();
/*     */ 
/* 144 */     ZipEntry localZipEntry = null;
/*     */ 
/* 143 */     new File(paramString)
/* 144 */       .mkdirs();
/*     */ 
/* 146 */     while (localEnumeration.hasMoreElements())
/*     */     {
/* 148 */       if ((
/* 148 */         localZipEntry = (ZipEntry)localEnumeration.nextElement())
/* 148 */         .isDirectory())
/*     */       {
/* 152 */         System.err.println("Extracting directory: " + localZipEntry.getName());
/*     */ 
/* 154 */         new File(paramString + localZipEntry.getName()).mkdir();
/*     */       }
/*     */       else {
/* 157 */         setChanged();
/* 158 */         notifyObservers("Extracting " + localZipEntry.getName());
/* 159 */         System.err.println("Extracting file: " + localZipEntry.getName());
/* 160 */         FileUtil.a(localZipFile.getInputStream(localZipEntry), new BufferedOutputStream(new FileOutputStream(paramString + localZipEntry.getName())));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 165 */     localZipFile.close();
/* 166 */     System.err.println("Deleting archive " + paramsd);
/* 167 */     new File(paramsd.jdField_a_of_type_JavaLangString)
/* 168 */       .delete(); }
/*     */ 
/*     */   public static String a() {
/* 171 */     return "./StarMade//StarMade.jar";
/*     */   }
/*     */   public final boolean a() {
/* 174 */     if (!this.jdField_b_of_type_Boolean) {
/* 175 */       return false;
/*     */     }
/* 177 */     if ((sG.jdField_a_of_type_JavaLangString == null) || (sG.jdField_a_of_type_JavaLangString.equals("undefined"))) {
/* 178 */       System.err.println("Version build null or undefined");
/* 179 */       return true;
/*     */     }
/* 181 */     if (sG.jdField_a_of_type_JavaLangString.equals("latest")) {
/* 182 */       System.err.println("newer version always available for develop version!");
/* 183 */       return true;
/*     */     }
/* 185 */     if (this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/* 186 */       System.err.println("versions empty");
/* 187 */       return false;
/*     */     }
/* 189 */     System.out.println("checking your version " + sG.jdField_a_of_type_JavaLangString + " against latest " + ((sd)this.jdField_a_of_type_JavaUtilArrayList.get(this.jdField_a_of_type_JavaUtilArrayList.size() - 1)).a() + " = " + sG.jdField_a_of_type_JavaLangString.compareTo(((sd)this.jdField_a_of_type_JavaUtilArrayList.get(this.jdField_a_of_type_JavaUtilArrayList.size() - 1)).a()));
/* 190 */     return sG.jdField_a_of_type_JavaLangString.compareTo(((sd)this.jdField_a_of_type_JavaUtilArrayList.get(this.jdField_a_of_type_JavaUtilArrayList.size() - 1)).a()) < 0;
/*     */   }
/*     */ 
/*     */   public static boolean b()
/*     */   {
/* 316 */     return new File("./StarMade//StarMade.jar").exists();
/*     */   }
/*     */ 
/*     */   public static void c() { try { sG.a("./StarMade/");
/*     */       return;
/*     */     }
/*     */     catch (Exception localException) {
/* 324 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static boolean a(sd paramsd)
/*     */   {
/* 328 */     paramsd = jdField_a_of_type_JavaLangString + paramsd.jdField_a_of_type_JavaLangString;
/*     */     try
/*     */     {
/* 332 */       (
/* 333 */         paramsd = new URL(paramsd)
/* 332 */         .openConnection())
/* 333 */         .setReadTimeout(15000);
/* 334 */       paramsd.setRequestProperty("User-Agent", "StarMade-Updater_" + Launcher.a);
/* 335 */       paramsd.connect();
/* 336 */       paramsd.getInputStream();
/* 337 */       return true; } catch (IOException localIOException) { localIOException
/* 338 */         .printStackTrace();
/*     */ 
/* 340 */       System.err.println("Mirror not available " + jdField_a_of_type_JavaLangString);
/*     */     }
/* 342 */     return false;
/*     */   }
/*     */ 
/*     */   public final void a(sd paramsd)
/*     */   {
/* 350 */     ReadableByteChannel localReadableByteChannel = null;
/* 351 */     FileOutputStream localFileOutputStream = null;
/*     */     try
/*     */     {
/* 358 */       if (jdField_a_of_type_JavaLangString == null) {
/* 359 */         localObject = new Random();
/* 360 */         jdField_a_of_type_JavaLangString = (String)this.jdField_b_of_type_JavaUtilArrayList.get(((Random)localObject).nextInt(this.jdField_b_of_type_JavaUtilArrayList.size()));
/*     */       }
/* 362 */       if (!jdField_a_of_type_JavaLangString.endsWith("/")) {
/* 363 */         jdField_a_of_type_JavaLangString += "/";
/*     */       }
/*     */ 
/* 366 */       setChanged();
/* 367 */       notifyObservers("connecting...");
/*     */ 
/* 369 */       localObject = jdField_a_of_type_JavaLangString + paramsd.jdField_a_of_type_JavaLangString;
/*     */ 
/* 371 */       int i = this.jdField_b_of_type_JavaUtilArrayList.size() * 3;
/* 372 */       int k = 0;
/* 373 */       while ((!a(paramsd)) && (k < i))
/*     */       {
/* 375 */         d.a(new Exception("Mirror: " + (String)localObject + " is isnvalid. Press retry to try another one."));
/* 376 */         Random localRandom = new Random();
/* 377 */         jdField_a_of_type_JavaLangString = (String)this.jdField_b_of_type_JavaUtilArrayList.get(localRandom.nextInt(this.jdField_b_of_type_JavaUtilArrayList.size()));
/* 378 */         k++;
/*     */       }
/*     */       URLConnection localURLConnection;
/* 388 */       (
/* 389 */         localURLConnection = new URL((String)localObject)
/* 388 */         .openConnection())
/* 389 */         .setReadTimeout(50000);
/* 390 */       localURLConnection.setRequestProperty("User-Agent", "StarMade-Updater_" + Launcher.a);
/*     */ 
/* 392 */       localReadableByteChannel = Channels.newChannel(new BufferedInputStream(localURLConnection.getInputStream()));
/* 393 */       localFileOutputStream = new FileOutputStream(paramsd.jdField_a_of_type_JavaLangString);
/*     */ 
/* 395 */       int j = 0;
/*     */ 
/* 397 */       long[] arrayOfLong = new long[2];
/* 398 */       int m = -1;
/* 399 */       while (j < paramsd.jdField_a_of_type_Long)
/*     */       {
/* 401 */         long l = Math.min(1024L, paramsd.jdField_a_of_type_Long - j);
/* 402 */         localFileOutputStream.getChannel().transferFrom(localReadableByteChannel, j, l);
/*     */ 
/* 404 */         j = (int)(j + l);
/* 405 */         arrayOfLong[0] = j;
/* 406 */         arrayOfLong[1] = paramsd.jdField_a_of_type_Long;
/* 407 */         setChanged();
/* 408 */         notifyObservers(arrayOfLong);
/* 409 */         if (countObservers() == 0)
/*     */         {
/*     */           int n;
/* 411 */           if ((
/* 411 */             n = (int)((float)arrayOfLong[0] / (float)arrayOfLong[1] * 100.0F)) != 
/* 411 */             m) {
/* 412 */             System.err.println("LOADED: " + j + " / " + paramsd.jdField_a_of_type_Long + ":     " + n + "%");
/* 413 */             m = n;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 441 */       System.err.println("retrieved file " + paramsd);
/* 442 */       String str2 = URLEncoder.encode("\"", "ISO-8859-1");
/*     */ 
/* 444 */       String str3 = "{" + str2 + "1" + str2 + ":[" + str2 + "Mirror" + str2 + "," + str2 + jdField_a_of_type_JavaLangString + str2 + "]," + str2 + "2" + str2 + ":[" + str2 + "Build" + str2 + "," + str2 + paramsd.jdField_a_of_type_JavaLangString + str2 + "]}";
/*     */ 
/* 446 */       String str1 = "?url=" + (String)localObject + "&download=" + (String)localObject + "&_cvar=" + str3 + "&action_name=Launcher%20Downloads&idsite=1&rec=1&bots=1&idgoal=3";
/*     */ 
/* 453 */       paramsd = "http://stats.star-made.org/piwik.php" + str1;
/* 454 */       System.err.println("ENCODED: " + paramsd);
/*     */ 
/* 460 */       (
/* 461 */         paramsd = new URL(paramsd)
/* 460 */         .openConnection())
/* 461 */         .setRequestProperty("User-Agent", "StarMade-Updater_" + Launcher.a);
/*     */ 
/* 463 */       paramsd.connect();
/* 464 */       paramsd.getInputStream();
/*     */ 
/* 472 */       if (localReadableByteChannel != null) {
/* 473 */         localReadableByteChannel.close();
/*     */       }
/* 475 */       localFileOutputStream.close();
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */       Object localObject;
/* 468 */       d.a((Exception)localObject);
/*     */     }
/*     */     finally
/*     */     {
/* 472 */       if (localReadableByteChannel != null) {
/* 473 */         localReadableByteChannel.close();
/*     */       }
/* 475 */       if (localFileOutputStream != null)
/* 476 */         localFileOutputStream.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/* 484 */     this.jdField_a_of_type_Boolean = true;
/* 485 */     new Thread(new sz(this)).start();
/*     */   }
/*     */ 
/*     */   public final void e()
/*     */   {
/* 498 */     if (this.jdField_c_of_type_Boolean) {
/* 499 */       return;
/*     */     }
/*     */ 
/* 502 */     this.jdField_c_of_type_Boolean = true;
/* 503 */     setChanged();
/* 504 */     notifyObservers("updating");
/* 505 */     new Thread(new sA(this)).start();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sy
 * JD-Core Version:    0.6.2
 */