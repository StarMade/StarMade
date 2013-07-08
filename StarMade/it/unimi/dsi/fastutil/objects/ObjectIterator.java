package it.unimi.dsi.fastutil.objects;

import java.util.Iterator;

public abstract interface ObjectIterator<K>
  extends Iterator<K>
{
  public abstract int skip(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */