package org.dom4j;

import java.io.PrintStream;
import java.io.PrintWriter;

public class DocumentException
  extends Exception
{
  private Throwable nestedException;
  
  public DocumentException()
  {
    super("Error occurred in DOM4J application.");
  }
  
  public DocumentException(String message)
  {
    super(message);
  }
  
  public DocumentException(Throwable nestedException)
  {
    super(nestedException.getMessage());
    this.nestedException = nestedException;
  }
  
  public DocumentException(String message, Throwable nestedException)
  {
    super(message);
    this.nestedException = nestedException;
  }
  
  public Throwable getNestedException()
  {
    return this.nestedException;
  }
  
  public String getMessage()
  {
    if (this.nestedException != null) {
      return super.getMessage() + " Nested exception: " + this.nestedException.getMessage();
    }
    return super.getMessage();
  }
  
  public void printStackTrace()
  {
    super.printStackTrace();
    if (this.nestedException != null)
    {
      System.err.print("Nested exception: ");
      this.nestedException.printStackTrace();
    }
  }
  
  public void printStackTrace(PrintStream out)
  {
    super.printStackTrace(out);
    if (this.nestedException != null)
    {
      out.println("Nested exception: ");
      this.nestedException.printStackTrace(out);
    }
  }
  
  public void printStackTrace(PrintWriter writer)
  {
    super.printStackTrace(writer);
    if (this.nestedException != null)
    {
      writer.println("Nested exception: ");
      this.nestedException.printStackTrace(writer);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.DocumentException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */