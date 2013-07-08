package com.google.code.tempusfugit;

public class FactoryException
  extends RuntimeException
{
  public FactoryException() {}
  
  public FactoryException(String message)
  {
    super(message);
  }
  
  public FactoryException(String message, Throwable cause)
  {
    super(message, cause);
  }
  
  public FactoryException(Throwable cause)
  {
    super(cause);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.FactoryException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */