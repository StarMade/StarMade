package it.unimi.dsi.fastutil.booleans;

import java.util.ListIterator;

public abstract interface BooleanListIterator
  extends ListIterator<Boolean>, BooleanBidirectionalIterator
{
  public abstract void set(boolean paramBoolean);
  
  public abstract void add(boolean paramBoolean);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */