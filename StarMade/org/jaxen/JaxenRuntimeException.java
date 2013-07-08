package org.jaxen;

import java.io.PrintStream;
import java.io.PrintWriter;

public class JaxenRuntimeException
  extends RuntimeException
{
  private static final long serialVersionUID = -930309761511911193L;
  private Throwable cause;
  private boolean causeSet = false;
  
  public JaxenRuntimeException(Throwable cause)
  {
    super(cause.getMessage());
    initCause(cause);
  }
  
  public JaxenRuntimeException(String message)
  {
    super(message);
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
    if ((JaxenException.javaVersion < 1.4D) && (getCause() != null))
    {
      local_s.print("Caused by: ");
      getCause().printStackTrace(local_s);
    }
  }
  
  public void printStackTrace(PrintWriter local_s)
  {
    super.printStackTrace(local_s);
    if ((JaxenException.javaVersion < 1.4D) && (getCause() != null))
    {
      local_s.print("Caused by: ");
      getCause().printStackTrace(local_s);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.JaxenRuntimeException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */