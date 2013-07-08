import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.schema.game.common.updater.CheckSumFailedException;

public final class class_1205
  implements Comparable
{
  String jdField_field_1423_of_type_JavaLangString;
  long jdField_field_1423_of_type_Long;
  private String field_1424;
  private String field_1425;
  
  public class_1205(String paramString1, long paramLong, String paramString2)
  {
    this.jdField_field_1423_of_type_JavaLangString = paramString1;
    this.jdField_field_1423_of_type_Long = paramLong;
    this.field_1424 = new String(paramString1);
    this.field_1424 = this.field_1424.replace("starmade-build_", "");
    this.field_1424 = this.field_1424.replace(".zip", "");
    this.field_1425 = paramString2;
  }
  
  public final String a()
  {
    return this.field_1424;
  }
  
  public final String toString()
  {
    return "FileEntry [name=" + this.jdField_field_1423_of_type_JavaLangString + ", size=" + this.jdField_field_1423_of_type_Long + "]";
  }
  
  public final void a1()
  {
    String str = null;
    try
    {
      str = a2(this.jdField_field_1423_of_type_JavaLangString);
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException;
    }
    System.err.println("Checking checksum for " + this.jdField_field_1423_of_type_JavaLangString);
    System.err.println("star-made.org: " + this.field_1425);
    System.err.println("downloaded file: " + str);
    if (!this.field_1425.equals(str)) {
      throw new CheckSumFailedException("The downloaded mirror file didn't match the checksum give from star-made.org. The file might be modified!");
    }
  }
  
  public static String a2(String paramString)
  {
    paramString = new BufferedInputStream(new FileInputStream(paramString));
    Object localObject = new byte[1024];
    MessageDigest localMessageDigest = MessageDigest.getInstance("SHA1");
    int j;
    do
    {
      if ((j = paramString.read((byte[])localObject)) > 0) {
        localMessageDigest.update((byte[])localObject, 0, j);
      }
    } while (j != -1);
    paramString.close();
    paramString = localMessageDigest.digest();
    localObject = "";
    for (int i = 0; i < paramString.length; i++) {
      localObject = (String)localObject + Integer.toString((paramString[i] & 0xFF) + 256, 16).substring(1);
    }
    return localObject;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1205
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */