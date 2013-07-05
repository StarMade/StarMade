package org.hsqldb.lib;

import java.util.Comparator;

public abstract interface ObjectComparator extends Comparator
{
  public abstract int hashCode(Object paramObject);

  public abstract long longKey(Object paramObject);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.ObjectComparator
 * JD-Core Version:    0.6.2
 */