package org.apache.commons.lang3;

public class SerializationException
  extends RuntimeException
{
  private static final long serialVersionUID = 4029025366392702726L;
  
  public SerializationException() {}
  
  public SerializationException(String msg)
  {
    super(msg);
  }
  
  public SerializationException(Throwable cause)
  {
    super(cause);
  }
  
  public SerializationException(String msg, Throwable cause)
  {
    super(msg, cause);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.SerializationException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */