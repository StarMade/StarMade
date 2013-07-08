/*  1:   */import java.io.BufferedInputStream;
/*  2:   */import java.io.FileInputStream;
/*  3:   */import java.io.InputStream;
/*  4:   */import java.io.PrintStream;
/*  5:   */import java.security.MessageDigest;
/*  6:   */import java.security.NoSuchAlgorithmException;
/*  7:   */import org.schema.game.common.updater.CheckSumFailedException;
/*  8:   */
/* 12:   */public final class sd
/* 13:   */  implements Comparable
/* 14:   */{
/* 15:   */  String jdField_a_of_type_JavaLangString;
/* 16:   */  long jdField_a_of_type_Long;
/* 17:   */  private String b;
/* 18:   */  private String c;
/* 19:   */  
/* 20:   */  public sd(String paramString1, long paramLong, String paramString2)
/* 21:   */  {
/* 22:22 */    this.jdField_a_of_type_JavaLangString = paramString1;
/* 23:23 */    this.jdField_a_of_type_Long = paramLong;
/* 24:24 */    this.b = new String(paramString1);
/* 25:25 */    this.b = this.b.replace("starmade-build_", "");
/* 26:26 */    this.b = this.b.replace(".zip", "");
/* 27:27 */    this.c = paramString2;
/* 28:   */  }
/* 29:   */  
/* 34:   */  public final String a()
/* 35:   */  {
/* 36:36 */    return this.b;
/* 37:   */  }
/* 38:   */  
/* 42:   */  public final String toString()
/* 43:   */  {
/* 44:44 */    return "FileEntry [name=" + this.jdField_a_of_type_JavaLangString + ", size=" + this.jdField_a_of_type_Long + "]";
/* 45:   */  }
/* 46:   */  
/* 47:   */  public final void a() {
/* 48:48 */    String str = null;
/* 49:   */    try {
/* 50:50 */      str = a(this.jdField_a_of_type_JavaLangString);
/* 51:51 */    } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) { 
/* 52:   */      
/* 53:53 */        localNoSuchAlgorithmException;
/* 54:   */    }
/* 55:   */    
/* 57:55 */    System.err.println("Checking checksum for " + this.jdField_a_of_type_JavaLangString);
/* 58:56 */    System.err.println("star-made.org: " + this.c);
/* 59:57 */    System.err.println("downloaded file: " + str);
/* 60:   */    
/* 61:59 */    if (!this.c.equals(str)) {
/* 62:60 */      throw new CheckSumFailedException("The downloaded mirror file didn't match the checksum give from star-made.org. The file might be modified!");
/* 63:   */    }
/* 64:   */  }
/* 65:   */  
/* 87:   */  public static String a(String paramString)
/* 88:   */  {
/* 89:87 */    paramString = new BufferedInputStream(new FileInputStream(paramString));Object localObject = new byte[1024];MessageDigest localMessageDigest = MessageDigest.getInstance("SHA1"); int j; do { if ((j = paramString.read((byte[])localObject)) > 0) localMessageDigest.update((byte[])localObject, 0, j); } while (j != -1); paramString.close();paramString = localMessageDigest.digest();
/* 90:88 */    localObject = "";
/* 91:   */    
/* 92:90 */    for (int i = 0; i < paramString.length; i++) {
/* 93:91 */      localObject = (String)localObject + Integer.toString((paramString[i] & 0xFF) + 256, 16).substring(1);
/* 94:   */    }
/* 95:93 */    return localObject;
/* 96:   */  }
/* 97:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sd
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */