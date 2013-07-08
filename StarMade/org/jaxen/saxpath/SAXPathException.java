package org.jaxen.saxpath;

import java.io.PrintStream;
import java.io.PrintWriter;

public class SAXPathException
  extends Exception
{
  private static final long serialVersionUID = 4826444568928720706L;
  private static double javaVersion = 1.4D;
  private Throwable cause;
  private boolean causeSet = false;
  
  public SAXPathException(String message)
  {
    super(message);
  }
  
  public SAXPathException(Throwable cause)
  {
    super(cause.getMessage());
    initCause(cause);
  }
  
  public SAXPathException(String message, Throwable cause)
  {
    super(message);
    initCause(cause);
  }
  
  public Throwable getCause()
  {
    return this.cause;
  }
  
  public Throwable initCause(Throwable cause)
  {
    if (this.causeSet) {
      throw new IllegalStateException("Cause cannot be reset");
    }
    if (cause == this) {
      throw new IllegalArgumentException("Exception cannot be its own cause");
    }
    this.causeSet = true;
    this.cause = cause;
    return this;
  }
  
  public void printStackTrace(PrintStream local_s)
  {
    super.printStackTrace(local_s);
    if ((javaVersion < 1.4D) && (getCause() != null))
    {
      local_s.print("Caused by: ");
      getCause().printStackTrace(local_s);
    }
  }
  
  public void printStackTrace(PrintWriter local_s)
  {
    super.printStackTrace(local_s);
    if ((javaVersion < 1.4D) && (getCause() != null))
    {
      local_s.print("Caused by: ");
      getCause().printStackTrace(local_s);
    }
  }
  
  static
  {
    try
    {
      String versionString = System.getProperty("java.version");
      versionString = versionString.substring(0, 3);
      javaVersion = Double.valueOf(versionString).doubleValue();
    }
    catch (Exception versionString) {}
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.saxpath.SAXPathException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */