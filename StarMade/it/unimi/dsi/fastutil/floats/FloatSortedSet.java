package it.unimi.dsi.fastutil.floats;

import java.util.SortedSet;

public abstract interface FloatSortedSet
  extends FloatSet, SortedSet<Float>
{
  public abstract FloatBidirectionalIterator iterator(float paramFloat);
  
  @Deprecated
  public abstract FloatBidirectionalIterator floatIterator();
  
  public abstract FloatBidirectionalIterator iterator();
  
  public abstract FloatSortedSet subSet(Float paramFloat1, Float paramFloat2);
  
  public abstract FloatSortedSet headSet(Float paramFloat);
  
  public abstract FloatSortedSet tailSet(Float paramFloat);
  
  public abstract FloatComparator comparator();
  
  public abstract FloatSortedSet subSet(float paramFloat1, float paramFloat2);
  
  public abstract FloatSortedSet headSet(float paramFloat);
  
  public abstract FloatSortedSet tailSet(float paramFloat);
  
  public abstract float firstFloat();
  
  public abstract float lastFloat();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */