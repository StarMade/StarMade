package org.jasypt.exceptions;

public final class EncryptionOperationNotPossibleException
  extends RuntimeException
{
  private static final long serialVersionUID = 6304674109588715145L;
  
  public EncryptionOperationNotPossibleException() {}
  
  public EncryptionOperationNotPossibleException(Throwable local_t)
  {
    super(local_t);
  }
  
  public EncryptionOperationNotPossibleException(String message)
  {
    super(message);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.exceptions.EncryptionOperationNotPossibleException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */