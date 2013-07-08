package com.google.code.tempusfugit;

public abstract interface Factory<T>
{
  public abstract T create()
    throws FactoryException;
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.Factory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */