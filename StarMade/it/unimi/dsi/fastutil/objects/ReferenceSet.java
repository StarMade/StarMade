package it.unimi.dsi.fastutil.objects;

import java.util.Set;

public abstract interface ReferenceSet<K>
  extends ReferenceCollection<K>, Set<K>
{
  public abstract ObjectIterator<K> iterator();
  
  public abstract boolean remove(Object paramObject);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */