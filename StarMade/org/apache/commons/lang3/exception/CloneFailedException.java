package org.apache.commons.lang3.exception;

public class CloneFailedException
  extends RuntimeException
{
  private static final long serialVersionUID = 20091223L;
  
  public CloneFailedException(String message)
  {
    super(message);
  }
  
  public CloneFailedException(Throwable cause)
  {
    super(cause);
  }
  
  public CloneFailedException(String message, Throwable cause)
  {
    super(message, cause);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.exception.CloneFailedException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */