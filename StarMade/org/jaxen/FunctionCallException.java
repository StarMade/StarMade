package org.jaxen;

public class FunctionCallException
  extends JaxenException
{
  private static final long serialVersionUID = 7908649612495640943L;
  
  public FunctionCallException(String message)
  {
    super(message);
  }
  
  public FunctionCallException(Throwable nestedException)
  {
    super(nestedException);
  }
  
  public FunctionCallException(String message, Exception nestedException)
  {
    super(message, nestedException);
  }
  
  /**
   * @deprecated
   */
  public Throwable getNestedException()
  {
    return getCause();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.FunctionCallException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */