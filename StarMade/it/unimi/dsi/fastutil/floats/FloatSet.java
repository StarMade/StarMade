package it.unimi.dsi.fastutil.floats;

import java.util.Set;

public abstract interface FloatSet
  extends FloatCollection, Set<Float>
{
  public abstract FloatIterator iterator();
  
  public abstract boolean remove(float paramFloat);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */