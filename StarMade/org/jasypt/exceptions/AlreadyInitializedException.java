package org.jasypt.exceptions;

public final class AlreadyInitializedException
  extends RuntimeException
{
  private static final long serialVersionUID = 4592515503937873874L;
  
  public AlreadyInitializedException()
  {
    super("Encryption entity already initialized");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.exceptions.AlreadyInitializedException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */