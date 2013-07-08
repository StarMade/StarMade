import java.io.PrintStream;
import java.util.regex.Pattern;

final class class_407
  implements class_956
{
  public final boolean a(String paramString, class_1079 paramclass_1079)
  {
    if ((paramString.length() >= 6) && (paramString.length() < 24))
    {
      if (Pattern.matches("[a-zA-Z0-9 _-]+", paramString)) {
        return true;
      }
      System.err.println("MATCH FOUND ^ALPHANUMERIC");
    }
    paramclass_1079.onFailedTextCheck("Please only alphanumeric (and space, _, -) values \nand between 6 and 24 long!");
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_407
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */