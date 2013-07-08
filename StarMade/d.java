/*   1:    */import java.net.URLConnection;
/*   2:    */import org.lwjgl.opengl.GLContext;
/*   3:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   4:    */
/*   5:    */public final class d implements wE
/*   6:    */{
/*   7:    */  private static e jdField_a_of_type_E;
/*   8:    */  private static e jdField_b_of_type_E;
/*   9:    */  private static java.util.logging.FileHandler jdField_a_of_type_JavaUtilLoggingFileHandler;
/*  10:    */  private static java.util.logging.Logger jdField_a_of_type_JavaUtilLoggingLogger;
/*  11:    */  private static javax.swing.ImageIcon[] jdField_a_of_type_ArrayOfJavaxSwingImageIcon;
/*  12:    */  public float a;
/*  13:    */  private static org.newdawn.slick.UnicodeFont jdField_a_of_type_OrgNewdawnSlickUnicodeFont;
/*  14:    */  private static org.newdawn.slick.UnicodeFont jdField_b_of_type_OrgNewdawnSlickUnicodeFont;
/*  15:    */  private static org.newdawn.slick.UnicodeFont c;
/*  16:    */  private static org.newdawn.slick.UnicodeFont d;
/*  17:    */  private static org.newdawn.slick.UnicodeFont e;
/*  18:    */  
/*  19:    */  public static void a()
/*  20:    */  {
/*  21: 21 */    if (jdField_b_of_type_E != null) {
/*  22: 22 */      jdField_b_of_type_E.close();
/*  23:    */    }
/*  24: 24 */    if (jdField_a_of_type_E != null) {
/*  25: 25 */      jdField_a_of_type_E.close();
/*  26:    */    }
/*  27:    */    
/*  28: 28 */    if (jdField_a_of_type_JavaUtilLoggingFileHandler != null) {
/*  29: 29 */      if (jdField_a_of_type_JavaUtilLoggingLogger != null) {
/*  30: 30 */        jdField_a_of_type_JavaUtilLoggingLogger.removeHandler(jdField_a_of_type_JavaUtilLoggingFileHandler);
/*  31:    */      }
/*  32:    */      
/*  33: 33 */      jdField_a_of_type_JavaUtilLoggingFileHandler.close();
/*  34:    */    }
/*  35:    */  }
/*  36:    */  
/*  37:    */  public static void a(javax.vecmath.Matrix3f paramMatrix3f, javax.vecmath.Quat4f paramQuat4f)
/*  38:    */  {
/*  39: 18 */    if ((f1 = paramMatrix3f.m00 + paramMatrix3f.m11 + paramMatrix3f.m22) > 0.0F) {
/*  40: 19 */      f1 = org.schema.common.FastMath.l(f1 + 1.0F) * 2.0F;
/*  41: 20 */      paramQuat4f.w = (0.25F * f1);
/*  42: 21 */      paramQuat4f.x = ((paramMatrix3f.m21 - paramMatrix3f.m12) / f1);
/*  43: 22 */      paramQuat4f.y = ((paramMatrix3f.m02 - paramMatrix3f.m20) / f1);
/*  44: 23 */      paramQuat4f.z = ((paramMatrix3f.m10 - paramMatrix3f.m01) / f1);
/*  45: 24 */      return; } if ((paramMatrix3f.m00 > paramMatrix3f.m11) && (paramMatrix3f.m00 > paramMatrix3f.m22)) {
/*  46: 25 */      f1 = org.schema.common.FastMath.l(1.0F + paramMatrix3f.m00 - paramMatrix3f.m11 - paramMatrix3f.m22) * 2.0F;
/*  47: 26 */      paramQuat4f.w = ((paramMatrix3f.m21 - paramMatrix3f.m12) / f1);
/*  48: 27 */      paramQuat4f.x = (0.25F * f1);
/*  49: 28 */      paramQuat4f.y = ((paramMatrix3f.m01 + paramMatrix3f.m10) / f1);
/*  50: 29 */      paramQuat4f.z = ((paramMatrix3f.m02 + paramMatrix3f.m20) / f1);
/*  51: 30 */      return; } if (paramMatrix3f.m11 > paramMatrix3f.m22) {
/*  52: 31 */      f1 = org.schema.common.FastMath.l(1.0F + paramMatrix3f.m11 - paramMatrix3f.m00 - paramMatrix3f.m22) * 2.0F;
/*  53: 32 */      paramQuat4f.w = ((paramMatrix3f.m02 - paramMatrix3f.m20) / f1);
/*  54: 33 */      paramQuat4f.x = ((paramMatrix3f.m01 + paramMatrix3f.m10) / f1);
/*  55: 34 */      paramQuat4f.y = (0.25F * f1);
/*  56: 35 */      paramQuat4f.z = ((paramMatrix3f.m12 + paramMatrix3f.m21) / f1);
/*  57: 36 */      return; }
/*  58: 37 */    float f1 = org.schema.common.FastMath.l(1.0F + paramMatrix3f.m22 - paramMatrix3f.m00 - paramMatrix3f.m11) * 2.0F;
/*  59: 38 */    paramQuat4f.w = ((paramMatrix3f.m10 - paramMatrix3f.m01) / f1);
/*  60: 39 */    paramQuat4f.x = ((paramMatrix3f.m02 + paramMatrix3f.m20) / f1);
/*  61: 40 */    paramQuat4f.y = ((paramMatrix3f.m12 + paramMatrix3f.m21) / f1);
/*  62: 41 */    paramQuat4f.z = (0.25F * f1);
/*  63:    */  }
/*  64:    */  
/*  65:    */  private static void a(String paramString1, String paramString2, java.util.zip.ZipOutputStream paramZipOutputStream, String paramString3)
/*  66:    */  {
/*  67: 28 */    paramString1.replace('\\', '/');
/*  68: 29 */    paramString2.replace('\\', '/');
/*  69:    */    
/*  71:    */    java.io.File localFile;
/*  72:    */    
/*  73: 34 */    String[] arrayOfString = (localFile = new java.io.File(paramString2)).list();
/*  74:    */    try {
/*  75: 36 */      for (int i1 = 0; i1 < arrayOfString.length; i1++) {
/*  76: 37 */        if ((paramString3 == null) || (!arrayOfString[i1].startsWith(paramString3))) {
/*  77: 38 */          b(paramString1 + "/" + localFile.getName(), paramString2 + "/" + arrayOfString[i1], paramZipOutputStream, paramString3);
/*  78:    */        }
/*  79:    */      }
/*  80:    */      
/*  81: 42 */      return;
/*  82:    */    }
/*  83:    */    catch (Exception localException) {}
/*  84:    */  }
/*  85:    */  
/*  86:    */  public static org.newdawn.slick.UnicodeFont a()
/*  87:    */  {
/*  88: 33 */    if (jdField_b_of_type_OrgNewdawnSlickUnicodeFont == null) {
/*  89: 34 */      java.awt.Font localFont = new java.awt.Font("Arial", 1, 12);
/*  90: 35 */      (
/*  91: 36 */        d.jdField_b_of_type_OrgNewdawnSlickUnicodeFont = new org.newdawn.slick.UnicodeFont(localFont)).getEffects().add(new org.newdawn.slick.font.effects.OutlineEffect(4, java.awt.Color.black));
/*  92: 37 */      jdField_b_of_type_OrgNewdawnSlickUnicodeFont.getEffects().add(new org.newdawn.slick.font.effects.ColorEffect(java.awt.Color.white));
/*  93: 38 */      jdField_b_of_type_OrgNewdawnSlickUnicodeFont.addAsciiGlyphs();
/*  94:    */      try {
/*  95: 40 */        jdField_b_of_type_OrgNewdawnSlickUnicodeFont.loadGlyphs();
/*  96: 41 */      } catch (org.newdawn.slick.SlickException localSlickException) { 
/*  97:    */        
/*  98: 43 */          localSlickException;
/*  99:    */      }
/* 100:    */    }
/* 101:    */    
/* 102: 45 */    return jdField_b_of_type_OrgNewdawnSlickUnicodeFont;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public static javax.swing.ImageIcon a(int paramInt)
/* 106:    */  {
/* 107: 40 */    if (jdField_a_of_type_ArrayOfJavaxSwingImageIcon == null) {
/* 108:    */      try {
/* 109: 42 */        jdField_a_of_type_ArrayOfJavaxSwingImageIcon = new javax.swing.ImageIcon[768];(localObject = new java.awt.image.BufferedImage[3])[0] = javax.imageio.ImageIO.read(new java.io.File("./data/textures/block/t000.png"));localObject[1] = javax.imageio.ImageIO.read(new java.io.File("./data/textures/block/t001.png"));localObject[2] = javax.imageio.ImageIO.read(new java.io.File("./data/textures/block/t002.png")); for (int i1 = 0; i1 < jdField_a_of_type_ArrayOfJavaxSwingImageIcon.length; i1++) { int i2 = i1 % 256 % 16;int i3 = i1 % 256 / 16;java.awt.image.BufferedImage localBufferedImage = localObject[(i1 / 256)].getSubimage(i2 << 6, i3 << 6, 64, 64);jdField_a_of_type_ArrayOfJavaxSwingImageIcon[i1] = new javax.swing.ImageIcon(localBufferedImage);
/* 110:    */        } } catch (java.io.IOException localIOException) { Object localObject;
/* 111: 44 */        (localObject = 
/* 112:    */        
/* 113: 46 */          localIOException).printStackTrace();a((Exception)localObject);
/* 114:    */      }
/* 115:    */    }
/* 116: 48 */    return jdField_a_of_type_ArrayOfJavaxSwingImageIcon[paramInt]; }
/* 117: 49 */  public static javax.vecmath.Quat4f a(float paramFloat, javax.vecmath.Vector3f paramVector3f, javax.vecmath.Quat4f paramQuat4f) { if ((paramVector3f.x == 0.0F) && (paramVector3f.y == 0.0F) && (paramVector3f.z == 0.0F)) {
/* 118: 50 */      paramQuat4f.set(0.0F, 0.0F, 0.0F, 1.0F);
/* 119:    */    }
/* 120:    */    else {
/* 121: 53 */      float f1 = org.schema.common.FastMath.h(paramFloat = 0.5F * paramFloat);
/* 122: 54 */      paramQuat4f.w = org.schema.common.FastMath.d(paramFloat);
/* 123: 55 */      paramQuat4f.x = (f1 * paramVector3f.x);
/* 124: 56 */      paramQuat4f.y = (f1 * paramVector3f.y);
/* 125: 57 */      paramQuat4f.z = (f1 * paramVector3f.z);
/* 126:    */    }
/* 127: 59 */    return paramQuat4f;
/* 128:    */  }
/* 129:    */  
/* 130:    */  public static org.newdawn.slick.UnicodeFont b()
/* 131:    */  {
/* 132: 49 */    if (c == null) {
/* 133: 50 */      java.awt.Font localFont = new java.awt.Font("Arial", 1, 12);
/* 134: 51 */      (
/* 135: 52 */        d.c = new org.newdawn.slick.UnicodeFont(localFont)).getEffects().add(new org.newdawn.slick.font.effects.OutlineEffect(4, java.awt.Color.black));
/* 136: 53 */      c.getEffects().add(new org.newdawn.slick.font.effects.ColorEffect(java.awt.Color.white));
/* 137: 54 */      c.addAsciiGlyphs();
/* 138:    */      try {
/* 139: 56 */        c.loadGlyphs();
/* 140: 57 */      } catch (org.newdawn.slick.SlickException localSlickException) { 
/* 141:    */        
/* 142: 59 */          localSlickException;
/* 143:    */      }
/* 144:    */    }
/* 145:    */    
/* 146: 61 */    return c;
/* 147:    */  }
/* 148:    */  
/* 149:    */  public static void b()
/* 150:    */  {
/* 151:    */    
/* 152:    */    java.util.logging.LogManager localLogManager;
/* 153: 43 */    (localLogManager = java.util.logging.LogManager.getLogManager()).reset();
/* 154:    */    
/* 155: 45 */    if (!(localObject = new java.io.File("./logs/")).exists()) {
/* 156: 46 */      ((java.io.File)localObject).mkdirs();
/* 157:    */    }
/* 158:    */    
/* 163: 53 */    (d.jdField_a_of_type_JavaUtilLoggingFileHandler = new java.util.logging.FileHandler("./logs/log.txt", 4194304, 20)).setFormatter(new b());
/* 164:    */    
/* 166: 56 */    Object localObject = System.out;
/* 167: 57 */    java.io.PrintStream localPrintStream = System.err;
/* 168:    */    
/* 173: 63 */    (
/* 174: 64 */      d.jdField_a_of_type_JavaUtilLoggingLogger = java.util.logging.Logger.getLogger("stdout")).addHandler(jdField_a_of_type_JavaUtilLoggingFileHandler);
/* 175: 65 */    jdField_b_of_type_E = new e((java.io.PrintStream)localObject, jdField_a_of_type_JavaUtilLoggingLogger, g.a, "OUT");
/* 176: 66 */    System.setOut(new java.io.PrintStream(jdField_b_of_type_E, true));
/* 177: 67 */    localLogManager.addLogger(jdField_a_of_type_JavaUtilLoggingLogger);
/* 178:    */    
/* 179: 69 */    (
/* 180: 70 */      d.jdField_a_of_type_JavaUtilLoggingLogger = java.util.logging.Logger.getLogger("stderr")).addHandler(jdField_a_of_type_JavaUtilLoggingFileHandler);
/* 181: 71 */    jdField_a_of_type_E = new e(localPrintStream, jdField_a_of_type_JavaUtilLoggingLogger, g.b, "ERR");
/* 182: 72 */    System.setErr(new java.io.PrintStream(jdField_a_of_type_E, true));
/* 183: 73 */    localLogManager.addLogger(jdField_a_of_type_JavaUtilLoggingLogger);
/* 184:    */  }
/* 185:    */  
/* 186:    */  public static void a(java.io.File paramFile1, java.io.File paramFile2)
/* 187:    */  {
/* 188: 67 */    paramFile1 = new java.io.FileInputStream(paramFile1);
/* 189: 68 */    paramFile2 = new java.io.FileOutputStream(paramFile2);
/* 190:    */    
/* 191: 70 */    byte[] arrayOfByte = new byte[1024];
/* 192:    */    int i1;
/* 193: 72 */    while ((i1 = paramFile1.read(arrayOfByte)) > 0) {
/* 194: 73 */      paramFile2.write(arrayOfByte, 0, i1);
/* 195:    */    }
/* 196: 75 */    paramFile1.close();
/* 197: 76 */    paramFile2.close();
/* 198:    */  }
/* 199:    */  
/* 200:    */  public static org.newdawn.slick.UnicodeFont c()
/* 201:    */  {
/* 202: 65 */    if (m == null) {
/* 203: 66 */      java.awt.Font localFont = new java.awt.Font("Arial", 1, 16);
/* 204: 67 */      (
/* 205: 68 */        d.m = new org.newdawn.slick.UnicodeFont(localFont)).getEffects().add(new org.newdawn.slick.font.effects.OutlineEffect(4, java.awt.Color.black));
/* 206: 69 */      m.getEffects().add(new org.newdawn.slick.font.effects.ColorEffect(java.awt.Color.white));
/* 207: 70 */      m.addAsciiGlyphs();
/* 208:    */      try {
/* 209: 72 */        m.loadGlyphs();
/* 210: 73 */      } catch (org.newdawn.slick.SlickException localSlickException) { 
/* 211:    */        
/* 212: 75 */          localSlickException;
/* 213:    */      }
/* 214:    */    }
/* 215:    */    
/* 216: 77 */    return m;
/* 217:    */  }
/* 218:    */  
/* 219:    */  private static void b(String paramString1, String paramString2, java.util.zip.ZipOutputStream paramZipOutputStream, String paramString3)
/* 220:    */  {
/* 221: 59 */    paramString1.replace('\\', '/');
/* 222: 60 */    paramString2.replace('\\', '/');
/* 223:    */    
/* 224:    */    java.io.File localFile;
/* 225: 63 */    if ((localFile = new java.io.File(paramString2)).isDirectory()) {
/* 226: 64 */      a(paramString1, paramString2, paramZipOutputStream, paramString3);return;
/* 227:    */    }
/* 228:    */    
/* 229: 67 */    paramString3 = new byte[1024];
/* 230:    */    try
/* 231:    */    {
/* 232: 70 */      paramString2 = new java.io.FileInputStream(paramString2);
/* 233:    */      
/* 237: 75 */      if ((paramString1 = paramString1 + "/" + localFile.getName()).startsWith("/")) {
/* 238: 76 */        paramString1 = paramString1.substring(1);
/* 239:    */      }
/* 240: 78 */      System.err.println("PUTTING: " + paramString1);
/* 241:    */      
/* 244: 82 */      paramString1 = new java.util.zip.ZipEntry(paramString1);
/* 245: 83 */      paramZipOutputStream.putNextEntry(paramString1);
/* 246:    */      
/* 247: 85 */      while ((paramString1 = paramString2.read(paramString3)) > 0) {
/* 248: 86 */        paramZipOutputStream.write(paramString3, 0, paramString1);
/* 249:    */      }
/* 250: 88 */      paramString2.close(); return;
/* 251: 89 */    } catch (Exception localException) { 
/* 252:    */      
/* 253: 91 */        localException;
/* 254:    */    }
/* 255:    */  }
/* 256:    */  
/* 257:    */  public static org.newdawn.slick.UnicodeFont d()
/* 258:    */  {
/* 259: 80 */    if (d == null) {
/* 260: 81 */      java.awt.Font localFont = new java.awt.Font("Arial", 1, 18);
/* 261: 82 */      (
/* 262: 83 */        d.d = new org.newdawn.slick.UnicodeFont(localFont)).getEffects().add(new org.newdawn.slick.font.effects.OutlineEffect(4, java.awt.Color.black));
/* 263: 84 */      d.getEffects().add(new org.newdawn.slick.font.effects.ColorEffect(java.awt.Color.white));
/* 264: 85 */      d.addAsciiGlyphs();
/* 265:    */      try {
/* 266: 87 */        d.loadGlyphs();
/* 267: 88 */      } catch (org.newdawn.slick.SlickException localSlickException) { 
/* 268:    */        
/* 269: 90 */          localSlickException;
/* 270:    */      }
/* 271:    */    }
/* 272:    */    
/* 273: 92 */    return d;
/* 274:    */  }
/* 275:    */  
/* 276:    */  public static void a(Exception paramException)
/* 277:    */  {
/* 278: 44 */    Object[] arrayOfObject = { "Retry", "Exit" };
/* 279: 45 */    paramException.printStackTrace();
/* 280: 46 */    String str1 = "Critical Error";
/* 281:    */    javax.swing.JFrame localJFrame;
/* 282: 48 */    (localJFrame = new javax.swing.JFrame(str1)).setUndecorated(true);
/* 283:    */    
/* 284: 50 */    localJFrame.setVisible(true);
/* 285:    */    
/* 286: 52 */    java.awt.Dimension localDimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
/* 287: 53 */    String str2 = "";
/* 288:    */    
/* 289: 55 */    if ((paramException.getMessage() != null) && (paramException.getMessage().contains("Database lock acquisition"))) {
/* 290: 56 */      str2 = str2 + "\n\nIMPORTANT NOTE: this crash happens when there is still an instance of the game running\ncheck your process manager for \"javaw.exe\" and terminate it, or restart your computer to solve this problem.";
/* 291:    */    }
/* 292:    */    
/* 296: 62 */    if (str2.length() == 0) {
/* 297: 63 */      str2 = "To help to fix this error, \nplease send your /logs directory to schema@star-made.org";
/* 298:    */    }
/* 299:    */    
/* 301: 67 */    localJFrame.setLocation(localDimension.width / 2, localDimension.height / 2);
/* 302: 68 */    switch (javax.swing.JOptionPane.showOptionDialog(localJFrame, paramException.getClass().getSimpleName() + ": " + paramException.getMessage() + "\n\n " + str2 + "\n\n", str1, 1, 0, null, arrayOfObject, arrayOfObject[0]))
/* 303:    */    {
/* 308:    */    case 0: 
/* 309: 75 */      break;
/* 310:    */    case 1: 
/* 311: 77 */      System.err.println("[GLFrame] Error Message: " + paramException.getMessage());
/* 312:    */      
/* 316:    */      try
/* 317:    */      {
/* 318: 84 */        org.schema.game.common.crashreporter.CrashReporter.a();
/* 319: 85 */      } catch (java.io.IOException localIOException) { 
/* 320:    */        
/* 321: 87 */          localIOException;
/* 322:    */      }
/* 323:    */      
/* 324: 88 */      System.exit(0);
/* 325:    */    }
/* 326:    */    
/* 327:    */    
/* 329: 93 */    localJFrame.dispose();
/* 330:    */  }
/* 331:    */  
/* 332: 96 */  public final void a(Float paramFloat) { this.jdField_a_of_type_Float = paramFloat.floatValue();
/* 333:    */  }
/* 334:    */  
/* 335:    */  public static org.newdawn.slick.UnicodeFont e()
/* 336:    */  {
/* 337: 95 */    if (jdField_a_of_type_OrgNewdawnSlickUnicodeFont == null) {
/* 338: 96 */      java.awt.Font localFont = new java.awt.Font("Arial", 1, 24);
/* 339: 97 */      (
/* 340: 98 */        d.jdField_a_of_type_OrgNewdawnSlickUnicodeFont = new org.newdawn.slick.UnicodeFont(localFont)).getEffects().add(new org.newdawn.slick.font.effects.OutlineEffect(4, java.awt.Color.black));
/* 341: 99 */      jdField_a_of_type_OrgNewdawnSlickUnicodeFont.getEffects().add(new org.newdawn.slick.font.effects.ColorEffect(java.awt.Color.white));
/* 342:100 */      jdField_a_of_type_OrgNewdawnSlickUnicodeFont.addAsciiGlyphs();
/* 343:    */      try {
/* 344:102 */        jdField_a_of_type_OrgNewdawnSlickUnicodeFont.loadGlyphs();
/* 345:103 */      } catch (org.newdawn.slick.SlickException localSlickException) { 
/* 346:    */        
/* 347:105 */          localSlickException;
/* 348:    */      }
/* 349:    */    }
/* 350:    */    
/* 351:107 */    return jdField_a_of_type_OrgNewdawnSlickUnicodeFont;
/* 352:    */  }
/* 353:    */  
/* 354:    */  public static void a(int paramInt1, int paramInt2, int paramInt3, mI parammI, int paramInt4, java.util.Random paramRandom)
/* 355:    */  {
/* 356: 56 */    if (paramInt1 == 8) if (((paramInt2 == 8 ? 1 : 0) & (paramInt3 == 5 ? 1 : 0)) != 0) {
/* 357: 57 */        parammI.a(paramInt4, mD.c);
/* 358: 58 */        parammI.a(paramInt4, mC.b);
/* 359: 59 */        return;
/* 360:    */      }
/* 361: 61 */    if (paramInt1 == 100) if (((paramInt2 == 100 ? 1 : 0) & (paramInt3 == 100 ? 1 : 0)) != 0) {
/* 362: 62 */        parammI.a(paramInt4, mD.c);
/* 363: 63 */        parammI.a(paramInt4, mC.b);
/* 364: 64 */        return;
/* 365:    */      }
/* 366: 66 */    if (paramInt1 == 200) if (((paramInt2 == 100 ? 1 : 0) & (paramInt3 == 100 ? 1 : 0)) != 0) {
/* 367: 67 */        parammI.a(paramInt4, mD.c);
/* 368: 68 */        parammI.a(paramInt4, mC.c);
/* 369: 69 */        return;
/* 370:    */      }
/* 371: 71 */    if (paramInt1 == 300) if (((paramInt2 == 100 ? 1 : 0) & (paramInt3 == 100 ? 1 : 0)) != 0) {
/* 372: 72 */        parammI.a(paramInt4, mD.c);
/* 373: 73 */        parammI.a(paramInt4, mC.e);
/* 374: 74 */        return;
/* 375:    */      }
/* 376: 76 */    if (paramInt1 == 400) if (((paramInt2 == 100 ? 1 : 0) & (paramInt3 == 100 ? 1 : 0)) != 0) {
/* 377: 77 */        parammI.a(paramInt4, mD.c);
/* 378: 78 */        parammI.a(paramInt4, mC.jdField_a_of_type_MC);
/* 379: 79 */        return;
/* 380:    */      }
/* 381: 81 */    if (paramInt1 == 500) if (((paramInt2 == 100 ? 1 : 0) & (paramInt3 == 100 ? 1 : 0)) != 0) {
/* 382: 82 */        parammI.a(paramInt4, mD.c);
/* 383: 83 */        parammI.a(paramInt4, mC.d);
/* 384: 84 */        return;
/* 385:    */      }
/* 386: 86 */    if (paramInt1 == 8) if (((paramInt2 == 5 ? 1 : 0) & (paramInt3 == 8 ? 1 : 0)) != 0) {
/* 387: 87 */        parammI.a(paramInt4, mD.a);
/* 388: 88 */        parammI.a(paramInt4, kk.a);
/* 389: 89 */        return;
/* 390:    */      }
/* 391: 91 */    if (paramInt1 == 8) if (((paramInt2 == 5 ? 1 : 0) & (paramInt3 == 5 ? 1 : 0)) != 0) {
/* 392: 92 */        parammI.a(paramInt4, mD.a);
/* 393: 93 */        parammI.a(paramInt4, kk.c);
/* 394: 94 */        return;
/* 395:    */      }
/* 396: 96 */    if (mx.b.a(paramInt1, paramInt2, paramInt3)) {
/* 397: 97 */      parammI.a(paramInt4, mD.d);return; }
/* 398: 98 */    if ((paramInt1 == mx.b.jdField_a_of_type_Int) && (paramInt2 == mx.b.b - 1) && (paramInt3 == mx.b.c)) {
/* 399: 99 */      parammI.a(paramInt4, mD.c);
/* 400:100 */      a(parammI, paramInt4, paramRandom);return;
/* 401:    */    }
/* 402:    */    
/* 403:103 */    if ((paramInt1 = paramRandom.nextInt(250)) < 5) {
/* 404:104 */      parammI.a(paramInt4, mD.c);
/* 405:105 */      a(parammI, paramInt4, paramRandom);return;
/* 406:    */    }
/* 407:    */    
/* 408:108 */    if (paramInt1 < 240) {
/* 409:109 */      parammI.a(paramInt4, mD.b);return;
/* 410:    */    }
/* 411:111 */    parammI.a(paramInt4, mD.a);
/* 412:    */    
/* 413:113 */    if (paramRandom.nextInt(5) == 0) {
/* 414:114 */      parammI.a(paramInt4, kk.c);return;
/* 415:    */    }
/* 416:116 */    parammI.a(paramInt4, kk.a);
/* 417:    */  }
/* 418:    */  
/* 419:    */  public static org.newdawn.slick.UnicodeFont f()
/* 420:    */  {
/* 421:110 */    if (n == null) {
/* 422:111 */      java.awt.Font localFont = new java.awt.Font("Arial", 1, 32);
/* 423:112 */      (
/* 424:113 */        d.n = new org.newdawn.slick.UnicodeFont(localFont)).getEffects().add(new org.newdawn.slick.font.effects.OutlineEffect(4, java.awt.Color.black));
/* 425:114 */      n.getEffects().add(new org.newdawn.slick.font.effects.ColorEffect(java.awt.Color.white));
/* 426:115 */      n.addAsciiGlyphs();
/* 427:    */      try {
/* 428:117 */        n.loadGlyphs();
/* 429:118 */      } catch (org.newdawn.slick.SlickException localSlickException) { 
/* 430:    */        
/* 431:120 */          localSlickException;
/* 432:    */      }
/* 433:    */    }
/* 434:    */    
/* 435:122 */    return n;
/* 436:    */  }
/* 437:    */  
/* 438:    */  public static void a(String paramString1, String paramString2, String paramString3)
/* 439:    */  {
/* 440:109 */    System.out.println("Zipping folder: " + paramString1 + " to " + paramString2 + " (Filter: " + paramString3 + ")");
/* 441:    */    
/* 444:113 */    paramString2.replace('\\', '/');
/* 445:114 */    paramString1.replace('\\', '/');
/* 446:    */    
/* 447:116 */    java.io.FileOutputStream localFileOutputStream = new java.io.FileOutputStream(paramString2);
/* 448:117 */    paramString2 = new java.util.zip.ZipOutputStream(localFileOutputStream);
/* 449:    */    
/* 451:120 */    a("", paramString1, paramString2, paramString3);
/* 452:    */    
/* 453:122 */    paramString2.flush();
/* 454:123 */    paramString2.close();
/* 455:124 */    localFileOutputStream.close();
/* 456:    */  }
/* 457:    */  
/* 458:    */  public static javax.vecmath.Vector3f a(javax.vecmath.Quat4f paramQuat4f, javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2)
/* 459:    */  {
/* 460:104 */    float f1 = paramQuat4f.x;
/* 461:105 */    float f2 = paramQuat4f.y;
/* 462:106 */    float f3 = paramQuat4f.z;
/* 463:107 */    paramQuat4f = paramQuat4f.w;
/* 464:108 */    if (paramVector3f2 == null) {
/* 465:109 */      paramVector3f2 = new javax.vecmath.Vector3f();
/* 466:    */    }
/* 467:111 */    if ((paramVector3f1.x == 0.0F) && (paramVector3f1.y == 0.0F) && (paramVector3f1.z == 0.0F)) {
/* 468:112 */      paramVector3f2.set(0.0F, 0.0F, 0.0F);
/* 469:    */    } else {
/* 470:114 */      float f4 = paramVector3f1.x;float f5 = paramVector3f1.y;paramVector3f1 = paramVector3f1.z;
/* 471:115 */      paramVector3f2.x = (paramQuat4f * paramQuat4f * f4 + f2 * 2.0F * paramQuat4f * paramVector3f1 - f3 * 2.0F * paramQuat4f * f5 + f1 * f1 * f4 + f2 * 2.0F * f1 * f5 + f3 * 2.0F * f1 * paramVector3f1 - f3 * f3 * f4 - f2 * f2 * f4);
/* 472:    */      
/* 474:118 */      paramVector3f2.y = (f1 * 2.0F * f2 * f4 + f2 * f2 * f5 + f3 * 2.0F * f2 * paramVector3f1 + paramQuat4f * 2.0F * f3 * f4 - f3 * f3 * f5 + paramQuat4f * paramQuat4f * f5 - f1 * 2.0F * paramQuat4f * paramVector3f1 - f1 * f1 * f5);
/* 475:    */      
/* 477:121 */      paramVector3f2.z = (f1 * 2.0F * f3 * f4 + f2 * 2.0F * f3 * f5 + f3 * f3 * paramVector3f1 - paramQuat4f * 2.0F * f2 * f4 - f2 * f2 * paramVector3f1 + paramQuat4f * 2.0F * f1 * f5 - f1 * f1 * paramVector3f1 + paramQuat4f * paramQuat4f * paramVector3f1);
/* 478:    */    }
/* 479:    */    
/* 481:125 */    return paramVector3f2;
/* 482:    */  }
/* 483:    */  
/* 484:    */  public static void a(Exception paramException, boolean paramBoolean)
/* 485:    */  {
/* 486: 96 */    Object[] arrayOfObject = { "Ok", "Exit" };
/* 487: 97 */    paramException.printStackTrace();
/* 488: 98 */    String str = "Error";
/* 489:    */    javax.swing.JFrame localJFrame;
/* 490:100 */    (localJFrame = new javax.swing.JFrame(str)).setUndecorated(true);
/* 491:    */    
/* 492:102 */    localJFrame.setVisible(true);
/* 493:    */    
/* 494:104 */    java.awt.Dimension localDimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
/* 495:105 */    localJFrame.setLocation(localDimension.width / 2, localDimension.height / 2);
/* 496:106 */    switch (javax.swing.JOptionPane.showOptionDialog(localJFrame, (paramBoolean ? paramException.getClass().getSimpleName() : "") + paramException.getMessage(), str, 1, 0, null, arrayOfObject, arrayOfObject[0]))
/* 497:    */    {
/* 500:    */    case 0: 
/* 501:111 */      break;
/* 502:    */    case 1: 
/* 503:113 */      System.err.println("[GLFrame] Error Message: " + paramException.getMessage());
/* 504:    */      
/* 508:    */      try
/* 509:    */      {
/* 510:120 */        org.schema.game.common.crashreporter.CrashReporter.a();
/* 511:121 */      } catch (java.io.IOException localIOException) { 
/* 512:    */        
/* 513:123 */          localIOException;
/* 514:    */      }
/* 515:    */      
/* 516:124 */      System.exit(0);
/* 517:    */    }
/* 518:    */    
/* 519:    */    
/* 521:129 */    localJFrame.dispose();
/* 522:    */  }
/* 523:    */  
/* 524:    */  private static void a(mI parammI, int paramInt, java.util.Random paramRandom)
/* 525:    */  {
/* 526:126 */    int i1 = paramRandom.nextInt(mC.values().length);
/* 527:127 */    while (!mC.values()[i1].jdField_a_of_type_Boolean) {
/* 528:128 */      i1 = paramRandom.nextInt(mC.values().length);
/* 529:    */    }
/* 530:130 */    parammI.a(paramInt, mC.values()[i1]);
/* 531:    */  }
/* 532:    */  
/* 533:    */  public static org.newdawn.slick.UnicodeFont g()
/* 534:    */  {
/* 535:125 */    if (h == null) {
/* 536:126 */      java.awt.Font localFont = new java.awt.Font("Arial", 1, 15);
/* 537:127 */      (
/* 538:128 */        d.h = new org.newdawn.slick.UnicodeFont(localFont)).getEffects().add(new org.newdawn.slick.font.effects.OutlineEffect(4, java.awt.Color.black));
/* 539:129 */      h.getEffects().add(new org.newdawn.slick.font.effects.ColorEffect(java.awt.Color.green.darker()));
/* 540:130 */      h.addAsciiGlyphs();
/* 541:    */      try {
/* 542:132 */        h.loadGlyphs();
/* 543:133 */      } catch (org.newdawn.slick.SlickException localSlickException) { 
/* 544:    */        
/* 545:135 */          localSlickException;
/* 546:    */      }
/* 547:    */    }
/* 548:    */    
/* 549:137 */    return h;
/* 550:    */  }
/* 551:    */  
/* 552:140 */  public static org.newdawn.slick.UnicodeFont h() { if (k == null) {
/* 553:141 */      java.awt.Font localFont = new java.awt.Font("Arial", 1, 14);
/* 554:142 */      (
/* 555:143 */        d.k = new org.newdawn.slick.UnicodeFont(localFont)).getEffects().add(new org.newdawn.slick.font.effects.OutlineEffect(4, java.awt.Color.black));
/* 556:144 */      k.getEffects().add(new org.newdawn.slick.font.effects.ColorEffect(java.awt.Color.white));
/* 557:145 */      k.addAsciiGlyphs();
/* 558:    */      try {
/* 559:147 */        k.loadGlyphs();
/* 560:148 */      } catch (org.newdawn.slick.SlickException localSlickException) { 
/* 561:    */        
/* 562:150 */          localSlickException;
/* 563:    */      }
/* 564:    */    }
/* 565:    */    
/* 566:152 */    return k;
/* 567:    */  }
/* 568:    */  
/* 569:    */  public static void a(String paramString1, String paramString2, String paramString3, String paramString4, java.io.File paramFile)
/* 570:    */  {
/* 571:120 */    if ((paramString1 != null) && (paramString4 != null) && (paramFile != null))
/* 572:    */    {
/* 573:122 */      StringBuffer localStringBuffer = new StringBuffer("ftp://");
/* 574:    */      
/* 575:124 */      if ((paramString2 != null) && (paramString3 != null))
/* 576:    */      {
/* 577:126 */        localStringBuffer.append(paramString2);
/* 578:127 */        localStringBuffer.append(':');
/* 579:128 */        localStringBuffer.append(paramString3);
/* 580:129 */        localStringBuffer.append('@');
/* 581:    */      }
/* 582:131 */      localStringBuffer.append(paramString1);
/* 583:132 */      localStringBuffer.append('/');
/* 584:133 */      localStringBuffer.append(paramString4);
/* 585:    */      
/* 589:138 */      localStringBuffer.append(";type=i");
/* 590:    */      
/* 591:140 */      paramString1 = null;
/* 592:141 */      paramString2 = null;
/* 593:    */      
/* 594:    */      try
/* 595:    */      {
/* 596:145 */        paramString3 = new java.net.URL(localStringBuffer.toString()).openConnection();
/* 597:    */        
/* 598:147 */        paramString2 = new java.io.BufferedOutputStream(paramString3.getOutputStream());
/* 599:148 */        paramString1 = new java.io.BufferedInputStream(new java.io.FileInputStream(paramFile));
/* 600:    */        
/* 603:152 */        while ((paramString3 = paramString1.read()) != -1)
/* 604:    */        {
/* 605:154 */          paramString2.write(paramString3);
/* 606:    */        }
/* 607:    */        
/* 608:    */        try
/* 609:    */        {
/* 610:159 */          paramString1.close();
/* 612:    */        }
/* 613:    */        catch (java.io.IOException localIOException1)
/* 614:    */        {
/* 615:164 */          
/* 616:    */          
/* 618:167 */            localIOException1;
/* 619:    */        }
/* 620:    */        try
/* 621:    */        {
/* 622:168 */          paramString2.close(); return;
/* 624:    */        }
/* 625:    */        catch (java.io.IOException localIOException2)
/* 626:    */        {
/* 627:173 */          
/* 628:    */          
/* 630:176 */            localIOException2.printStackTrace(); return;
/* 631:    */        }
/* 632:    */        
/* 635:178 */        System.out.println("Input not available.");
/* 636:    */      }
/* 637:    */      finally
/* 638:    */      {
/* 639:159 */        if (paramString1 != null) {
/* 640:    */          try
/* 641:    */          {
/* 642:162 */            paramString1.close();
/* 643:    */          } catch (java.io.IOException localIOException3) {
/* 644:164 */            
/* 645:    */            
/* 647:167 */              localIOException3;
/* 648:    */          }
/* 649:    */        }
/* 650:    */        
/* 651:168 */        if (paramString2 != null) {
/* 652:    */          try
/* 653:    */          {
/* 654:171 */            paramString2.close();
/* 655:    */          } catch (java.io.IOException localIOException4) {
/* 656:173 */            
/* 657:    */            
/* 659:176 */              localIOException4;
/* 660:    */          }
/* 661:    */        }
/* 662:    */      }
/* 663:    */    }
/* 664:    */  }
/* 665:    */  
/* 666:    */  public static org.newdawn.slick.UnicodeFont i()
/* 667:    */  {
/* 668:156 */    if (f == null)
/* 669:    */    {
/* 673:    */      try
/* 674:    */      {
/* 678:166 */        java.awt.Font localFont = java.awt.Font.createFont(0, new java.io.File("data//font/dotricebold.ttf")).deriveFont(14.0F);
/* 679:    */        
/* 680:168 */        (
/* 681:169 */          d.f = new org.newdawn.slick.UnicodeFont(localFont)).getEffects().add(new org.newdawn.slick.font.effects.OutlineEffect(4, java.awt.Color.black));
/* 682:    */        
/* 683:171 */        f.getEffects().add(new org.newdawn.slick.font.effects.ColorEffect(java.awt.Color.white));
/* 684:    */        
/* 685:173 */        f.addAsciiGlyphs();
/* 686:    */        
/* 687:175 */        f.loadGlyphs();
/* 688:176 */      } catch (org.newdawn.slick.SlickException localSlickException) { 
/* 689:    */        
/* 694:182 */          localSlickException;
/* 695:    */      } catch (java.awt.FontFormatException localFontFormatException) {
/* 696:178 */        
/* 697:    */        
/* 700:182 */          localFontFormatException;
/* 701:    */      } catch (java.io.IOException localIOException) {
/* 702:180 */        
/* 703:    */        
/* 704:182 */          localIOException;
/* 705:    */      }
/* 706:    */    }
/* 707:    */    
/* 709:185 */    return f;
/* 710:    */  }
/* 711:    */  
/* 712:    */  public static org.newdawn.slick.UnicodeFont j() {
/* 713:189 */    if (e == null)
/* 714:    */    {
/* 718:    */      try
/* 719:    */      {
/* 723:199 */        java.awt.Font localFont = java.awt.Font.createFont(0, new java.io.File("data//font/dotricebold.ttf")).deriveFont(20.0F);
/* 724:    */        
/* 725:201 */        (
/* 726:202 */          d.e = new org.newdawn.slick.UnicodeFont(localFont)).getEffects().add(new org.newdawn.slick.font.effects.OutlineEffect(4, java.awt.Color.black));
/* 727:    */        
/* 728:204 */        e.getEffects().add(new org.newdawn.slick.font.effects.ColorEffect(java.awt.Color.white));
/* 729:    */        
/* 730:206 */        e.addAsciiGlyphs();
/* 731:    */        
/* 732:208 */        e.loadGlyphs();
/* 733:209 */      } catch (org.newdawn.slick.SlickException localSlickException) { 
/* 734:    */        
/* 739:215 */          localSlickException;
/* 740:    */      } catch (java.awt.FontFormatException localFontFormatException) {
/* 741:211 */        
/* 742:    */        
/* 745:215 */          localFontFormatException;
/* 746:    */      } catch (java.io.IOException localIOException) {
/* 747:213 */        
/* 748:    */        
/* 749:215 */          localIOException;
/* 750:    */      }
/* 751:    */    }
/* 752:    */    
/* 754:218 */    return e;
/* 755:    */  }
/* 756:    */  
/* 757:    */  public static void a(com.bulletphysics.linearmath.Transform paramTransform1, com.bulletphysics.linearmath.Transform paramTransform2)
/* 758:    */  {
/* 759:128 */    float f1 = paramTransform1.basis.m00;
/* 760:129 */    float f2 = paramTransform1.basis.m01;
/* 761:130 */    float f3 = paramTransform1.basis.m02;
/* 762:131 */    float f4 = paramTransform1.origin.x;
/* 763:132 */    float f5 = paramTransform1.basis.m10;
/* 764:133 */    float f6 = paramTransform1.basis.m11;
/* 765:134 */    float f7 = paramTransform1.basis.m12;
/* 766:135 */    float f8 = paramTransform1.origin.y;
/* 767:136 */    float f9 = paramTransform1.basis.m20;
/* 768:137 */    float f10 = paramTransform1.basis.m21;
/* 769:138 */    float f11 = paramTransform1.basis.m22;
/* 770:139 */    float f12 = paramTransform1.origin.z;
/* 771:140 */    float f13 = paramTransform2.basis.m00;
/* 772:    */    
/* 777:146 */    float f14 = paramTransform2.basis.m01;
/* 778:147 */    float f15 = paramTransform2.basis.m02;
/* 779:148 */    float f16 = paramTransform2.origin.x;
/* 780:149 */    float f17 = paramTransform2.basis.m10;
/* 781:150 */    float f18 = paramTransform2.basis.m11;
/* 782:151 */    float f19 = paramTransform2.basis.m12;
/* 783:152 */    float f20 = paramTransform2.origin.y;
/* 784:153 */    float f21 = paramTransform2.basis.m20;
/* 785:154 */    float f22 = paramTransform2.basis.m21;
/* 786:155 */    float f23 = paramTransform2.basis.m22;
/* 787:156 */    paramTransform2 = paramTransform2.origin.z;
/* 788:157 */    float f24 = f1 * f13 + f2 * f17 + f3 * f21 + f4 * 0.0F;
/* 789:    */    
/* 800:169 */    float f25 = f1 * f14 + f2 * f18 + f3 * f22 + f4 * 0.0F;
/* 801:    */    
/* 802:171 */    float f26 = f1 * f15 + f2 * f19 + f3 * f23 + f4 * 0.0F;
/* 803:    */    
/* 804:173 */    f1 = f1 * f16 + f2 * f20 + f3 * paramTransform2 + f4;
/* 805:    */    
/* 807:176 */    f2 = f5 * f13 + f6 * f17 + f7 * f21 + f8 * 0.0F;
/* 808:    */    
/* 809:178 */    f3 = f5 * f14 + f6 * f18 + f7 * f22 + f8 * 0.0F;
/* 810:    */    
/* 811:180 */    f4 = f5 * f15 + f6 * f19 + f7 * f23 + f8 * 0.0F;
/* 812:    */    
/* 813:182 */    f5 = f5 * f16 + f6 * f20 + f7 * paramTransform2 + f8;
/* 814:    */    
/* 816:185 */    f6 = f9 * f13 + f10 * f17 + f11 * f21 + f12 * 0.0F;
/* 817:    */    
/* 818:187 */    f7 = f9 * f14 + f10 * f18 + f11 * f22 + f12 * 0.0F;
/* 819:    */    
/* 820:189 */    f8 = f9 * f15 + f10 * f19 + f11 * f23 + f12 * 0.0F;
/* 821:    */    
/* 822:191 */    paramTransform2 = f9 * f16 + f10 * f20 + f11 * paramTransform2 + f12;
/* 823:    */    
/* 825:194 */    paramTransform1.basis.m00 = f24;
/* 826:    */    
/* 840:209 */    paramTransform1.basis.m01 = f25;
/* 841:210 */    paramTransform1.basis.m02 = f26;
/* 842:211 */    paramTransform1.origin.x = f1;
/* 843:212 */    paramTransform1.basis.m10 = f2;
/* 844:213 */    paramTransform1.basis.m11 = f3;
/* 845:214 */    paramTransform1.basis.m12 = f4;
/* 846:215 */    paramTransform1.origin.y = f5;
/* 847:216 */    paramTransform1.basis.m20 = f6;
/* 848:217 */    paramTransform1.basis.m21 = f7;
/* 849:218 */    paramTransform1.basis.m22 = f8;
/* 850:219 */    paramTransform1.origin.z = paramTransform2; }
/* 851:    */  
/* 852:221 */  public static org.newdawn.slick.UnicodeFont k() { if (g == null) {
/* 853:222 */      java.awt.Font localFont = new java.awt.Font("Arial", 0, 11);
/* 854:223 */      (
/* 855:224 */        d.g = new org.newdawn.slick.UnicodeFont(localFont)).getEffects().add(new org.newdawn.slick.font.effects.OutlineEffect(2, java.awt.Color.black));
/* 856:225 */      g.getEffects().add(new org.newdawn.slick.font.effects.ColorEffect(java.awt.Color.white));
/* 857:226 */      g.addAsciiGlyphs();
/* 858:    */      try {
/* 859:228 */        g.loadGlyphs();
/* 860:229 */      } catch (org.newdawn.slick.SlickException localSlickException) { 
/* 861:    */        
/* 862:231 */          localSlickException;
/* 863:    */      }
/* 864:    */    }
/* 865:    */    
/* 866:233 */    return g;
/* 867:    */  }
/* 868:    */  
/* 869:236 */  public static org.newdawn.slick.UnicodeFont l() { if (l == null) {
/* 870:237 */      java.awt.Font localFont = new java.awt.Font("Arial", 0, 12);
/* 871:238 */      (
/* 872:239 */        d.l = new org.newdawn.slick.UnicodeFont(localFont)).getEffects().add(new org.newdawn.slick.font.effects.ColorEffect(java.awt.Color.white));
/* 873:240 */      l.addAsciiGlyphs();
/* 874:    */      try {
/* 875:242 */        l.loadGlyphs();
/* 876:243 */      } catch (org.newdawn.slick.SlickException localSlickException) { 
/* 877:    */        
/* 878:245 */          localSlickException;
/* 879:    */      }
/* 880:    */    }
/* 881:    */    
/* 882:247 */    return l;
/* 883:    */  }
/* 884:    */  
/* 885:    */  public static javax.vecmath.Quat4f a(javax.vecmath.Quat4f paramQuat4f1, javax.vecmath.Quat4f paramQuat4f2, float paramFloat, javax.vecmath.Quat4f paramQuat4f3)
/* 886:    */  {
/* 887:216 */    if ((paramQuat4f1.x == paramQuat4f2.x) && (paramQuat4f1.y == paramQuat4f2.y) && (paramQuat4f1.z == paramQuat4f2.z) && (paramQuat4f1.w == paramQuat4f2.w)) {
/* 888:217 */      paramQuat4f3.set(paramQuat4f1);
/* 889:218 */      return paramQuat4f3;
/* 890:    */    }
/* 891:    */    
/* 893:    */    float f1;
/* 894:    */    
/* 895:224 */    if ((f1 = paramQuat4f1.x * paramQuat4f2.x + paramQuat4f1.y * paramQuat4f2.y + paramQuat4f1.z * paramQuat4f2.z + paramQuat4f1.w * paramQuat4f2.w) < 0.0F)
/* 896:    */    {
/* 897:226 */      paramQuat4f2.x = (-paramQuat4f2.x);
/* 898:227 */      paramQuat4f2.y = (-paramQuat4f2.y);
/* 899:228 */      paramQuat4f2.z = (-paramQuat4f2.z);
/* 900:229 */      paramQuat4f2.w = (-paramQuat4f2.w);
/* 901:230 */      f1 = -f1;
/* 902:    */    }
/* 903:    */    
/* 905:234 */    float f2 = 1.0F - paramFloat;
/* 906:235 */    float f3 = paramFloat;
/* 907:    */    
/* 910:239 */    if (1.0F - f1 > 0.1F)
/* 911:    */    {
/* 912:241 */      f1 = org.schema.common.FastMath.b(f1);
/* 913:242 */      f3 = 1.0F / org.schema.common.FastMath.h(f1);
/* 914:    */      
/* 917:246 */      f2 = org.schema.common.FastMath.h(f2 * f1) * f3;
/* 918:247 */      f3 = org.schema.common.FastMath.h(paramFloat * f1) * f3;
/* 919:    */    }
/* 920:    */    
/* 924:253 */    paramQuat4f3.x = (f2 * paramQuat4f1.x + f3 * paramQuat4f2.x);
/* 925:254 */    paramQuat4f3.y = (f2 * paramQuat4f1.y + f3 * paramQuat4f2.y);
/* 926:255 */    paramQuat4f3.z = (f2 * paramQuat4f1.z + f3 * paramQuat4f2.z);
/* 927:256 */    paramQuat4f3.w = (f2 * paramQuat4f1.w + f3 * paramQuat4f2.w);
/* 928:    */    
/* 930:259 */    return paramQuat4f3;
/* 931:    */  }
/* 932:    */  
/* 933:    */  public static org.newdawn.slick.UnicodeFont m()
/* 934:    */  {
/* 935:250 */    if (o == null) {
/* 936:251 */      java.awt.Font localFont = new java.awt.Font("Arial", 0, 12);
/* 937:252 */      (
/* 938:253 */        d.o = new org.newdawn.slick.UnicodeFont(localFont)).getEffects().add(new org.newdawn.slick.font.effects.ColorEffect(java.awt.Color.white));
/* 939:254 */      o.addAsciiGlyphs();
/* 940:    */      try {
/* 941:256 */        o.loadGlyphs();
/* 942:257 */      } catch (org.newdawn.slick.SlickException localSlickException) { 
/* 943:    */        
/* 944:259 */          localSlickException;
/* 945:    */      }
/* 946:    */      
/* 947:260 */      o.setDisplayListCaching(false);
/* 948:    */    }
/* 949:262 */    return o;
/* 950:    */  }
/* 951:    */  
/* 952:265 */  public static org.newdawn.slick.UnicodeFont n() { if (i == null) {
/* 953:266 */      java.awt.Font localFont = new java.awt.Font("Arial", 0, 13);
/* 954:267 */      (
/* 955:268 */        d.i = new org.newdawn.slick.UnicodeFont(localFont)).getEffects().add(new org.newdawn.slick.font.effects.OutlineEffect(4, java.awt.Color.black));
/* 956:269 */      i.getEffects().add(new org.newdawn.slick.font.effects.ColorEffect(java.awt.Color.white));
/* 957:270 */      i.addAsciiGlyphs();
/* 958:    */      try {
/* 959:272 */        i.loadGlyphs();
/* 960:273 */      } catch (org.newdawn.slick.SlickException localSlickException) { 
/* 961:    */        
/* 962:275 */          localSlickException;
/* 963:    */      }
/* 964:    */    }
/* 965:    */    
/* 966:277 */    return i;
/* 967:    */  }
/* 968:    */  
/* 969:280 */  public static org.newdawn.slick.UnicodeFont o() { if (j == null) {
/* 970:281 */      java.awt.Font localFont = new java.awt.Font("Arial", 0, 15);
/* 971:282 */      (
/* 972:283 */        d.j = new org.newdawn.slick.UnicodeFont(localFont)).getEffects().add(new org.newdawn.slick.font.effects.OutlineEffect(4, java.awt.Color.black));
/* 973:284 */      j.getEffects().add(new org.newdawn.slick.font.effects.ColorEffect(java.awt.Color.white));
/* 974:285 */      j.addAsciiGlyphs();
/* 975:    */      try {
/* 976:287 */        j.loadGlyphs();
/* 977:288 */      } catch (org.newdawn.slick.SlickException localSlickException) { 
/* 978:    */        
/* 979:290 */          localSlickException;
/* 980:    */      }
/* 981:    */    }
/* 982:    */    
/* 983:292 */    return j;
/* 984:    */  }
/* 985:    */  
/* 990:    */  private static org.newdawn.slick.UnicodeFont f;
/* 991:    */  
/* 995:    */  private static org.newdawn.slick.UnicodeFont g;
/* 996:    */  
/* 1000:    */  private static org.newdawn.slick.UnicodeFont h;
/* 1001:    */  
/* 1005:    */  private static org.newdawn.slick.UnicodeFont i;
/* 1006:    */  
/* 1010:    */  private static org.newdawn.slick.UnicodeFont j;
/* 1011:    */  
/* 1014:    */  private static org.newdawn.slick.UnicodeFont k;
/* 1015:    */  
/* 1018:    */  private static org.newdawn.slick.UnicodeFont l;
/* 1019:    */  
/* 1022:    */  private static org.newdawn.slick.UnicodeFont m;
/* 1023:    */  
/* 1026:    */  private static org.newdawn.slick.UnicodeFont n;
/* 1027:    */  
/* 1030:    */  private static org.newdawn.slick.UnicodeFont o;
/* 1031:    */  
/* 1034:    */  public static Ag[] a;
/* 1035:    */  
/* 1038:    */  private static java.nio.ByteBuffer a(java.io.File paramFile)
/* 1039:    */  {
/* 1040:349 */    paramFile = new java.io.FileInputStream(paramFile);
/* 1041:    */    try
/* 1042:    */    {
/* 1043:    */      Object localObject1;
/* 1044:353 */      java.nio.ByteBuffer localByteBuffer = GlUtil.a((int)(localObject1 = paramFile.getChannel()).size(), 0);
/* 1045:    */      
/* 1046:355 */      ((java.nio.channels.FileChannel)localObject1).read(localByteBuffer);
/* 1047:356 */      localByteBuffer.rewind();
/* 1048:357 */      return localByteBuffer;
/* 1049:    */    }
/* 1050:    */    finally {
/* 1051:360 */      paramFile.close();
/* 1052:    */    }
/* 1053:    */  }
/* 1054:    */  
/* 1059:    */  public static void a(java.io.File paramFile, zE paramzE)
/* 1060:    */  {
/* 1061:370 */    java.nio.ByteBuffer localByteBuffer = a(paramFile);
/* 1062:    */    
/* 1063:    */    zA localzA1;
/* 1064:    */    
/* 1065:374 */    if (((localzA1 = new zA(localByteBuffer)).k & 0x200000) == 0) {
/* 1066:375 */      throw new java.io.IOException("Not a volume texture: " + paramFile);
/* 1067:    */    }
/* 1068:377 */    if (localzA1.jdField_a_of_type_JavaLangString != null) {
/* 1069:378 */      throw new java.io.IOException("Compression not supported for 3D textures: " + localzA1.jdField_a_of_type_JavaLangString);
/* 1070:    */    }
/* 1071:380 */    paramFile = localzA1.c;
/* 1072:381 */    int i1 = localzA1.b;
/* 1073:382 */    int i2 = localzA1.d;
/* 1074:383 */    int i3 = (localzA1.f & 0x1) == 0 ? 6407 : 6408;
/* 1075:    */    zA localzA2;
/* 1076:385 */    if (localzA2.j == -16777216) { if (!GLContext.getCapabilities().GL_EXT_bgra) throw new java.io.IOException("BGRA format not supported."); if (localzA2.j != -16777216) {} } throw ((localzA2.g == 255) && (localzA2.h == 0) && (localzA2.i == 0) ? 8194 : (localzA2.g == 16711680) && (localzA2.h == 65280) && (localzA2.i == 255) ? 32993 : (localzA2.f & 0x1) == 0 ? 32992 : ((localzA2 = localzA1).g == 255) && (localzA2.h == 65280) && (localzA2.i == 16711680) ? 6408 : (localzA2.f & 0x1) == 0 ? 6407 : new java.io.IOException("Unknown format: " + localzA2.g + "/" + localzA2.h + "/" + localzA2.i + "/" + localzA2.j));
/* 1077:    */  }
/* 1078:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */