package it.unimi.dsi.fastutil.objects;

import java.util.Collection;

public abstract interface ReferenceCollection<K>
  extends Collection<K>, ObjectIterable<K>
{
  public abstract ObjectIterator<K> iterator();
  
  @Deprecated
  public abstract ObjectIterator<K> objectIterator();
  
  public abstract <T> T[] toArray(T[] paramArrayOfT);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */