/*   1:    */import java.io.BufferedInputStream;
/*   2:    */import java.io.BufferedOutputStream;
/*   3:    */import java.io.File;
/*   4:    */import java.io.FileOutputStream;
/*   5:    */import java.io.IOException;
/*   6:    */import java.io.PrintStream;
/*   7:    */import java.net.URL;
/*   8:    */import java.net.URLConnection;
/*   9:    */import java.net.URLEncoder;
/*  10:    */import java.nio.channels.Channels;
/*  11:    */import java.nio.channels.FileChannel;
/*  12:    */import java.nio.channels.ReadableByteChannel;
/*  13:    */import java.util.ArrayList;
/*  14:    */import java.util.Arrays;
/*  15:    */import java.util.Enumeration;
/*  16:    */import java.util.Observable;
/*  17:    */import java.util.Random;
/*  18:    */import java.util.zip.ZipEntry;
/*  19:    */import java.util.zip.ZipFile;
/*  20:    */import org.schema.game.common.updater.FileUtil;
/*  21:    */import org.schema.game.common.updater.Launcher;
/*  22:    */
/*  32:    */public final class sy
/*  33:    */  extends Observable
/*  34:    */{
/*  35: 35 */  private static String jdField_b_of_type_JavaLangString = "http://files.star-made.org/checksum";
/*  36:    */  
/*  37: 37 */  private static String jdField_c_of_type_JavaLangString = "http://files.star-made.org/version";
/*  38: 38 */  private static String d = "http://files.star-made.org/mirrors";
/*  39:    */  
/*  42:    */  public static boolean a(File paramFile)
/*  43:    */  {
/*  44: 44 */    if (paramFile.isDirectory()) {
/*  45: 45 */      String[] arrayOfString = paramFile.list();
/*  46: 46 */      for (int i = 0; i < arrayOfString.length; i++)
/*  47:    */      {
/*  48: 48 */        if (!a(new File(paramFile, arrayOfString[i]))) {
/*  49: 49 */          return false;
/*  50:    */        }
/*  51:    */      }
/*  52:    */    }
/*  53:    */    
/*  55: 55 */    return paramFile.delete();
/*  56:    */  }
/*  57:    */  
/*  58: 58 */  public static void a() { sy localsy = new sy();
/*  59:    */    try {
/*  60: 60 */      localsy.d();
/*  61: 61 */      while (localsy.jdField_a_of_type_Boolean) {
/*  62: 62 */        Thread.sleep(100L);
/*  63:    */      }
/*  64: 64 */      if (localsy.a()) {
/*  65: 65 */        System.err.println("A New Version Is Available!");
/*  66: 66 */        localsy.e();return;
/*  67:    */      }
/*  68: 68 */      System.err.println("You Are Already on the Newest Version: use -force to force an update"); return;
/*  69:    */    } catch (InterruptedException localInterruptedException) {
/*  70: 70 */      
/*  71:    */      
/*  72: 72 */        localInterruptedException;
/*  73:    */    }
/*  74:    */  }
/*  75:    */  
/*  76: 74 */  private boolean jdField_a_of_type_Boolean = false;
/*  77: 75 */  private boolean jdField_b_of_type_Boolean = false;
/*  78:    */  
/*  80: 78 */  private final ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  81: 79 */  private final ArrayList jdField_b_of_type_JavaUtilArrayList = new ArrayList();
/*  82:    */  
/*  83:    */  private boolean jdField_c_of_type_Boolean;
/*  84:    */  public static String a;
/*  85:    */  
/*  86:    */  public sy()
/*  87:    */  {
/*  88: 86 */    c();
/*  89:    */  }
/*  90:    */  
/*  91:    */  public final void b()
/*  92:    */  {
/*  93:    */    File localFile1;
/*  94: 92 */    if (((localFile1 = new File("./StarMade/")).exists()) && (localFile1.list().length > 0)) {
/*  95: 93 */      setChanged();
/*  96: 94 */      notifyObservers("Backing Up");
/*  97: 95 */      Object localObject = "backup-StarMade-" + sG.jdField_a_of_type_Float + "-" + sG.jdField_a_of_type_JavaLangString + ".zip";
/*  98: 96 */      System.out.println("Backing Up");
/*  99:    */      
/* 101: 99 */      d.a("./StarMade/", (String)localObject, "backup-StarMade-");
/* 102:    */      
/* 103:101 */      System.out.println("Copying Backup mFile to install dir...");
/* 104:    */      File localFile2;
/* 105:103 */      if ((localFile2 = new File((String)localObject)).exists()) {
/* 106:104 */        d.a(localFile2, new File("./StarMade/" + (String)localObject));
/* 107:105 */        localFile2.delete();
/* 108:    */      }
/* 109:    */      
/* 110:108 */      setChanged();
/* 111:109 */      notifyObservers("Deleting old installation");
/* 112:110 */      System.out.println("Cleaning up current installation");
/* 113:    */      
/* 119:117 */      localObject = localFile1.listFiles();
/* 120:118 */      for (int i = 0; i < localObject.length; i++) {
/* 121:    */        File localFile3;
/* 122:120 */        if (((localFile3 = localObject[i]).getName().equals("data")) || (localFile3.getName().equals("native")) || (localFile3.getName().startsWith("StarMade")) || (localFile3.getName().equals("MANIFEST.MF")) || (localFile3.getName().equals("version.txt")))
/* 123:    */        {
/* 129:127 */          a(localFile3);
/* 130:128 */          localFile3.delete();
/* 131:    */        }
/* 132:    */      }
/* 133:    */      
/* 134:132 */      System.out.println("DONE, install dir is now: " + Arrays.toString(localFile1.list()));
/* 135:    */    }
/* 136:    */  }
/* 137:    */  
/* 138:136 */  public final void a(sd paramsd, String paramString) { if (!paramString.endsWith("/")) {
/* 139:137 */      paramString = paramString + "/";
/* 140:    */    }
/* 141:139 */    System.err.println("Extracting " + paramsd);
/* 142:    */    ZipFile localZipFile;
/* 143:141 */    Enumeration localEnumeration = (localZipFile = new ZipFile(paramsd.jdField_a_of_type_JavaLangString)).entries();
/* 144:    */    
/* 146:144 */    ZipEntry localZipEntry = null;new File(paramString).mkdirs();
/* 147:    */    
/* 148:146 */    while (localEnumeration.hasMoreElements())
/* 149:    */    {
/* 150:148 */      if ((localZipEntry = (ZipEntry)localEnumeration.nextElement()).isDirectory())
/* 151:    */      {
/* 154:152 */        System.err.println("Extracting directory: " + localZipEntry.getName());
/* 155:    */        
/* 156:154 */        new File(paramString + localZipEntry.getName()).mkdir();
/* 157:    */      }
/* 158:    */      else {
/* 159:157 */        setChanged();
/* 160:158 */        notifyObservers("Extracting " + localZipEntry.getName());
/* 161:159 */        System.err.println("Extracting file: " + localZipEntry.getName());
/* 162:160 */        FileUtil.a(localZipFile.getInputStream(localZipEntry), new BufferedOutputStream(new FileOutputStream(paramString + localZipEntry.getName())));
/* 163:    */      }
/* 164:    */    }
/* 165:    */    
/* 167:165 */    localZipFile.close();
/* 168:166 */    System.err.println("Deleting archive " + paramsd);
/* 169:167 */    new File(paramsd.jdField_a_of_type_JavaLangString)
/* 170:168 */      .delete();
/* 171:    */  }
/* 172:    */  
/* 173:171 */  public static String a() { return "./StarMade//StarMade.jar"; }
/* 174:    */  
/* 175:    */  public final boolean a() {
/* 176:174 */    if (!this.jdField_b_of_type_Boolean) {
/* 177:175 */      return false;
/* 178:    */    }
/* 179:177 */    if ((sG.jdField_a_of_type_JavaLangString == null) || (sG.jdField_a_of_type_JavaLangString.equals("undefined"))) {
/* 180:178 */      System.err.println("Version build null or undefined");
/* 181:179 */      return true;
/* 182:    */    }
/* 183:181 */    if (sG.jdField_a_of_type_JavaLangString.equals("latest")) {
/* 184:182 */      System.err.println("newer version always available for develop version!");
/* 185:183 */      return true;
/* 186:    */    }
/* 187:185 */    if (this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/* 188:186 */      System.err.println("versions empty");
/* 189:187 */      return false;
/* 190:    */    }
/* 191:189 */    System.out.println("checking your version " + sG.jdField_a_of_type_JavaLangString + " against latest " + ((sd)this.jdField_a_of_type_JavaUtilArrayList.get(this.jdField_a_of_type_JavaUtilArrayList.size() - 1)).a() + " = " + sG.jdField_a_of_type_JavaLangString.compareTo(((sd)this.jdField_a_of_type_JavaUtilArrayList.get(this.jdField_a_of_type_JavaUtilArrayList.size() - 1)).a()));
/* 192:190 */    return sG.jdField_a_of_type_JavaLangString.compareTo(((sd)this.jdField_a_of_type_JavaUtilArrayList.get(this.jdField_a_of_type_JavaUtilArrayList.size() - 1)).a()) < 0;
/* 193:    */  }
/* 194:    */  
/* 316:    */  public static boolean b()
/* 317:    */  {
/* 318:316 */    return new File("./StarMade//StarMade.jar").exists();
/* 319:    */  }
/* 320:    */  
/* 321:    */  public static void c() {
/* 322:    */    try {
/* 323:321 */      sG.a("./StarMade/"); return;
/* 324:322 */    } catch (Exception localException) { 
/* 325:    */      
/* 326:324 */        localException;
/* 327:    */    }
/* 328:    */  }
/* 329:    */  
/* 330:    */  private static boolean a(sd paramsd)
/* 331:    */  {
/* 332:328 */    paramsd = jdField_a_of_type_JavaLangString + paramsd.jdField_a_of_type_JavaLangString;
/* 333:    */    
/* 335:    */    try
/* 336:    */    {
/* 337:333 */      (paramsd = new URL(paramsd).openConnection()).setReadTimeout(15000);
/* 338:334 */      paramsd.setRequestProperty("User-Agent", "StarMade-Updater_" + Launcher.a);
/* 339:335 */      paramsd.connect();
/* 340:336 */      paramsd.getInputStream();
/* 341:337 */      return true;
/* 342:338 */    } catch (IOException localIOException) { localIOException.printStackTrace();
/* 343:    */      
/* 344:340 */      System.err.println("Mirror not available " + jdField_a_of_type_JavaLangString);
/* 345:    */    }
/* 346:342 */    return false;
/* 347:    */  }
/* 348:    */  
/* 352:    */  public final void a(sd paramsd)
/* 353:    */  {
/* 354:350 */    ReadableByteChannel localReadableByteChannel = null;
/* 355:351 */    FileOutputStream localFileOutputStream = null;
/* 356:    */    
/* 360:    */    try
/* 361:    */    {
/* 362:358 */      if (jdField_a_of_type_JavaLangString == null) {
/* 363:359 */        localObject = new Random();
/* 364:360 */        jdField_a_of_type_JavaLangString = (String)this.jdField_b_of_type_JavaUtilArrayList.get(((Random)localObject).nextInt(this.jdField_b_of_type_JavaUtilArrayList.size()));
/* 365:    */      }
/* 366:362 */      if (!jdField_a_of_type_JavaLangString.endsWith("/")) {
/* 367:363 */        jdField_a_of_type_JavaLangString += "/";
/* 368:    */      }
/* 369:    */      
/* 370:366 */      setChanged();
/* 371:367 */      notifyObservers("connecting...");
/* 372:    */      
/* 373:369 */      localObject = jdField_a_of_type_JavaLangString + paramsd.jdField_a_of_type_JavaLangString;
/* 374:    */      
/* 375:371 */      int i = this.jdField_b_of_type_JavaUtilArrayList.size() * 3;
/* 376:372 */      int k = 0;
/* 377:373 */      while ((!a(paramsd)) && (k < i))
/* 378:    */      {
/* 379:375 */        d.a(new Exception("Mirror: " + (String)localObject + " is isnvalid. Press retry to try another one."));
/* 380:376 */        Random localRandom = new Random();
/* 381:377 */        jdField_a_of_type_JavaLangString = (String)this.jdField_b_of_type_JavaUtilArrayList.get(localRandom.nextInt(this.jdField_b_of_type_JavaUtilArrayList.size()));
/* 382:378 */        k++;
/* 383:    */      }
/* 384:    */      
/* 388:    */      URLConnection localURLConnection;
/* 389:    */      
/* 393:389 */      (localURLConnection = new URL((String)localObject).openConnection()).setReadTimeout(50000);
/* 394:390 */      localURLConnection.setRequestProperty("User-Agent", "StarMade-Updater_" + Launcher.a);
/* 395:    */      
/* 396:392 */      localReadableByteChannel = Channels.newChannel(new BufferedInputStream(localURLConnection.getInputStream()));
/* 397:393 */      localFileOutputStream = new FileOutputStream(paramsd.jdField_a_of_type_JavaLangString);
/* 398:    */      
/* 399:395 */      int j = 0;
/* 400:    */      
/* 401:397 */      long[] arrayOfLong = new long[2];
/* 402:398 */      int m = -1;
/* 403:399 */      while (j < paramsd.jdField_a_of_type_Long)
/* 404:    */      {
/* 405:401 */        long l = Math.min(1024L, paramsd.jdField_a_of_type_Long - j);
/* 406:402 */        localFileOutputStream.getChannel().transferFrom(localReadableByteChannel, j, l);
/* 407:    */        
/* 408:404 */        j = (int)(j + l);
/* 409:405 */        arrayOfLong[0] = j;
/* 410:406 */        arrayOfLong[1] = paramsd.jdField_a_of_type_Long;
/* 411:407 */        setChanged();
/* 412:408 */        notifyObservers(arrayOfLong);
/* 413:409 */        if (countObservers() == 0) {
/* 414:    */          int n;
/* 415:411 */          if ((n = (int)((float)arrayOfLong[0] / (float)arrayOfLong[1] * 100.0F)) != m) {
/* 416:412 */            System.err.println("LOADED: " + j + " / " + paramsd.jdField_a_of_type_Long + ":     " + n + "%");
/* 417:413 */            m = n;
/* 418:    */          }
/* 419:    */        }
/* 420:    */      }
/* 421:    */      
/* 445:441 */      System.err.println("retrieved file " + paramsd);
/* 446:442 */      String str2 = URLEncoder.encode("\"", "ISO-8859-1");
/* 447:    */      
/* 448:444 */      String str3 = "{" + str2 + "1" + str2 + ":[" + str2 + "Mirror" + str2 + "," + str2 + jdField_a_of_type_JavaLangString + str2 + "]," + str2 + "2" + str2 + ":[" + str2 + "Build" + str2 + "," + str2 + paramsd.jdField_a_of_type_JavaLangString + str2 + "]}";
/* 449:    */      
/* 450:446 */      String str1 = "?url=" + (String)localObject + "&download=" + (String)localObject + "&_cvar=" + str3 + "&action_name=Launcher%20Downloads&idsite=1&rec=1&bots=1&idgoal=3";
/* 451:    */      
/* 457:453 */      paramsd = "http://stats.star-made.org/piwik.php" + str1;
/* 458:454 */      System.err.println("ENCODED: " + paramsd);
/* 459:    */      
/* 464:460 */      (
/* 465:461 */        paramsd = new URL(paramsd).openConnection()).setRequestProperty("User-Agent", "StarMade-Updater_" + Launcher.a);
/* 466:    */      
/* 467:463 */      paramsd.connect();
/* 468:464 */      paramsd.getInputStream();
/* 469:    */      
/* 476:472 */      if (localReadableByteChannel != null) {
/* 477:473 */        localReadableByteChannel.close();
/* 478:    */      }
/* 479:475 */      localFileOutputStream.close();
/* 480:    */    }
/* 481:    */    catch (Exception localException)
/* 482:    */    {
/* 483:    */      Object localObject;
/* 484:468 */      d.a((Exception)localObject);
/* 485:    */    }
/* 486:    */    finally
/* 487:    */    {
/* 488:472 */      if (localReadableByteChannel != null) {
/* 489:473 */        localReadableByteChannel.close();
/* 490:    */      }
/* 491:475 */      if (localFileOutputStream != null) {
/* 492:476 */        localFileOutputStream.close();
/* 493:    */      }
/* 494:    */    }
/* 495:    */  }
/* 496:    */  
/* 498:    */  public final void d()
/* 499:    */  {
/* 500:484 */    this.jdField_a_of_type_Boolean = true;
/* 501:485 */    new Thread(new sz(this)).start();
/* 502:    */  }
/* 503:    */  
/* 512:    */  public final void e()
/* 513:    */  {
/* 514:498 */    if (this.jdField_c_of_type_Boolean) {
/* 515:499 */      return;
/* 516:    */    }
/* 517:    */    
/* 518:502 */    this.jdField_c_of_type_Boolean = true;
/* 519:503 */    setChanged();
/* 520:504 */    notifyObservers("updating");
/* 521:505 */    new Thread(new sA(this)).start();
/* 522:    */  }
/* 523:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */