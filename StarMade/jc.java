/*    */ import java.io.File;
/*    */ import java.io.PrintStream;
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.Arrays;
/*    */ 
/*    */ public final class jc
/*    */ {
/* 10 */   private static String a = "." + File.separator + "native" + File.separator;
/*    */ 
/*    */   private static void a(String paramString)
/*    */   {
/*    */     Field localField;
/* 12 */     (
/* 13 */       localField = ClassLoader.class.getDeclaredField("usr_paths"))
/* 13 */       .setAccessible(true);
/*    */     String[] arrayOfString1;
/* 17 */     int i = (arrayOfString2 = arrayOfString1 = (String[])localField.get(null)).length;
/*    */ 
/* 17 */     for (int j = 0; j < i; j++)
/* 18 */       if (arrayOfString2[j]
/* 18 */         .equals(paramString))
/*    */         return;
/*    */     String[] tmp67_64 = ((String[])Arrays.copyOf(arrayOfString1, arrayOfString1.length + 1));
/*    */     String[] tmp68_67 = tmp67_64;
/* 24 */     String[] arrayOfString2 = tmp68_67;
/*    */ 
/* 23 */     tmp67_64[
/* 24 */       (tmp68_67.length - 1)] = paramString;
/* 25 */     localField.set(null, arrayOfString2);
/*    */   }
/*    */ 
/*    */   public static void a()
/*    */   {
/* 32 */     System.out.println("[LIBLOADER] OS ARCHITECTURE " + System.getProperty("os.arch"));
/* 33 */     String str = "";
/*    */     boolean bool;
/* 35 */     if ((
/* 35 */       bool = System.getProperty("os.arch").contains("64")))
/*    */     {
/* 36 */       str = File.separator + "x64" + File.separator;
/*    */     }
/* 38 */     if (System.getProperty("os.name").equals("Mac OS X"))
/*    */     {
/* 40 */       a(a + "macosx");
/*    */     }
/* 45 */     else if (System.getProperty("os.name").contains("Linux")) {
/* 46 */       a(a + "linux");
/*    */     }
/*    */     else
/*    */     {
/* 58 */       a(a + "windows" + str);
/* 59 */       if (bool) {
/* 60 */         System.loadLibrary("jinput-dx8_64");
/* 61 */         System.loadLibrary("lwjgl64");
/* 62 */         System.loadLibrary("OpenAL64");
/* 63 */         System.out.println("[LIBLOADER] LOADED WINDOWS 64bit NATIVE LIBRARIES ");
/*    */       } else {
/* 65 */         System.loadLibrary("jinput-dx8");
/* 66 */         System.loadLibrary("lwjgl");
/* 67 */         System.loadLibrary("OpenAL32");
/* 68 */         System.out.println("[LIBLOADER] LOADED WINDOWS 32bit NATIVE LIBRARIES ");
/*    */       }
/*    */     }
/* 71 */     System.out.println("[LIBLOADER] LOADED NATIVE LIBRARIES ");
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jc
 * JD-Core Version:    0.6.2
 */