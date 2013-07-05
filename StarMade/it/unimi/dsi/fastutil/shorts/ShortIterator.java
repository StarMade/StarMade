package it.unimi.dsi.fastutil.shorts;

import java.util.Iterator;

public abstract interface ShortIterator extends Iterator<Short>
{
  public abstract short nextShort();

  public abstract int skip(int paramInt);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortIterator
 * JD-Core Version:    0.6.2
 */