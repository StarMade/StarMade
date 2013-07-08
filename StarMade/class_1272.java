import java.io.File;
import java.io.IOException;

public final class class_1272
{
  public final String field_1457;
  public final String field_1458;
  
  public class_1272(String paramString1, String paramString2)
  {
    this.field_1457 = paramString2;
    this.field_1458 = paramString1;
  }
  
  public static boolean a()
  {
    try
    {
      return new File(class_1270.a2("StarMade"), "cred").exists();
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1272
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */