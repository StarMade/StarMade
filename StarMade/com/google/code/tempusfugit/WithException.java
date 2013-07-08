package com.google.code.tempusfugit;

public final class WithException<E extends Exception>
{
  private final Class<E> type;
  
  public static <E extends Exception> WithException<E> with(Class<E> type)
  {
    return new WithException(type);
  }
  
  private WithException(Class<E> type)
  {
    this.type = type;
  }
  
  public Class<E> getType()
  {
    return this.type;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.WithException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */