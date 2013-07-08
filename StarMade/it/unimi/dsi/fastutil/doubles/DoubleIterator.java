package it.unimi.dsi.fastutil.doubles;

import java.util.Iterator;

public abstract interface DoubleIterator
  extends Iterator<Double>
{
  public abstract double nextDouble();
  
  public abstract int skip(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */