package it.unimi.dsi.fastutil.floats;

import java.util.Iterator;

public abstract interface FloatIterator
  extends Iterator<Float>
{
  public abstract float nextFloat();
  
  public abstract int skip(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */