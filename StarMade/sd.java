/*    */ import java.io.BufferedInputStream;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.InputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import org.schema.game.common.updater.CheckSumFailedException;
/*    */ 
/*    */ public final class sd
/*    */   implements Comparable
/*    */ {
/*    */   String jdField_a_of_type_JavaLangString;
/*    */   long jdField_a_of_type_Long;
/*    */   private String b;
/*    */   private String c;
/*    */ 
/*    */   public sd(String paramString1, long paramLong, String paramString2)
/*    */   {
/* 22 */     this.jdField_a_of_type_JavaLangString = paramString1;
/* 23 */     this.jdField_a_of_type_Long = paramLong;
/* 24 */     this.b = new String(paramString1);
/* 25 */     this.b = this.b.replace("starmade-build_", "");
/* 26 */     this.b = this.b.replace(".zip", "");
/* 27 */     this.c = paramString2;
/*    */   }
/*    */ 
/*    */   public final String a()
/*    */   {
/* 36 */     return this.b;
/*    */   }
/*    */ 
/*    */   public final String toString()
/*    */   {
/* 44 */     return "FileEntry [name=" + this.jdField_a_of_type_JavaLangString + ", size=" + this.jdField_a_of_type_Long + "]";
/*    */   }
/*    */ 
/*    */   public final void a() {
/* 48 */     String str = null;
/*    */     try {
/* 50 */       str = a(this.jdField_a_of_type_JavaLangString);
/*    */     }
/*    */     catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
/* 53 */       localNoSuchAlgorithmException.printStackTrace();
/*    */     }
/*    */ 
/* 55 */     System.err.println("Checking checksum for " + this.jdField_a_of_type_JavaLangString);
/* 56 */     System.err.println("star-made.org: " + this.c);
/* 57 */     System.err.println("downloaded file: " + str);
/*    */ 
/* 59 */     if (!this.c.equals(str))
/* 60 */       throw new CheckSumFailedException("The downloaded mirror file didn't match the checksum give from star-made.org. The file might be modified!");
/*    */   }
/*    */ 
/*    */   public static String a(String paramString)
/*    */   {
/* 87 */     paramString = new BufferedInputStream(new FileInputStream(paramString)); Object localObject = new byte[1024]; MessageDigest localMessageDigest = MessageDigest.getInstance("SHA1");
/*    */     int j;
/*    */     do
/* 87 */       if ((j = paramString.read((byte[])localObject)) > 0) localMessageDigest.update((byte[])localObject, 0, j); 
/* 87 */     while (j != -1); paramString.close(); paramString = localMessageDigest.digest();
/* 88 */     localObject = "";
/*    */ 
/* 90 */     for (int i = 0; i < paramString.length; i++) {
/* 91 */       localObject = (String)localObject + Integer.toString((paramString[i] & 0xFF) + 256, 16).substring(1);
/*    */     }
/* 93 */     return localObject;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sd
 * JD-Core Version:    0.6.2
 */