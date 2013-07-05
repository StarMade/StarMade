package it.unimi.dsi.fastutil.floats;

import java.util.ListIterator;

public abstract interface FloatListIterator extends ListIterator<Float>, FloatBidirectionalIterator
{
  public abstract void set(float paramFloat);

  public abstract void add(float paramFloat);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatListIterator
 * JD-Core Version:    0.6.2
 */