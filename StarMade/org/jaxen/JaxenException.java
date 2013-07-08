package org.jaxen;

import org.jaxen.saxpath.SAXPathException;

public class JaxenException
  extends SAXPathException
{
  private static final long serialVersionUID = 7132891439526672639L;
  static double javaVersion = 1.4D;
  
  public JaxenException(String message)
  {
    super(message);
  }
  
  public JaxenException(Throwable rootCause)
  {
    super(rootCause);
  }
  
  public JaxenException(String message, Throwable nestedException)
  {
    super(message, nestedException);
  }
  
  static
  {
    try
    {
      String versionString = System.getProperty("java.version");
      versionString = versionString.substring(0, 3);
      javaVersion = Double.valueOf(versionString).doubleValue();
    }
    catch (RuntimeException versionString) {}
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.JaxenException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */