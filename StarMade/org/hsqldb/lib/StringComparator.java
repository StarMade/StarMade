package org.hsqldb.lib;

import java.util.Comparator;

public class StringComparator
  implements Comparator
{
  public int compare(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2)
      return 0;
    if (paramObject1 == null)
      return -1;
    if (paramObject2 == null)
      return 1;
    return ((String)paramObject1).compareTo((String)paramObject2);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.StringComparator
 * JD-Core Version:    0.6.2
 */