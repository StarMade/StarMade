package org.jasypt.exceptions;

public final class EncryptionInitializationException
  extends RuntimeException
{
  private static final long serialVersionUID = 8929638240023639778L;
  
  public EncryptionInitializationException() {}
  
  public EncryptionInitializationException(Throwable local_t)
  {
    super(local_t);
  }
  
  public EncryptionInitializationException(String msg, Throwable local_t)
  {
    super(msg, local_t);
  }
  
  public EncryptionInitializationException(String msg)
  {
    super(msg);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.exceptions.EncryptionInitializationException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */