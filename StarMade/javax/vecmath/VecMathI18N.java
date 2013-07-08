package javax.vecmath;

import java.io.PrintStream;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

class VecMathI18N
{
  static String getString(String paramString)
  {
    String str;
    try
    {
      str = ResourceBundle.getBundle("javax.vecmath.ExceptionStrings").getString(paramString);
    }
    catch (MissingResourceException localMissingResourceException)
    {
      System.err.println("VecMathI18N: Error looking up: " + paramString);
      str = paramString;
    }
    return str;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.VecMathI18N
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */