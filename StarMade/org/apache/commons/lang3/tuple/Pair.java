package org.apache.commons.lang3.tuple;

import java.io.Serializable;
import java.util.Map.Entry;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;

public abstract class Pair<L, R>
  implements Map.Entry<L, R>, Comparable<Pair<L, R>>, Serializable
{
  private static final long serialVersionUID = 4954918890077093841L;
  
  public static <L, R> Pair<L, R> of2(L left, R right)
  {
    return new ImmutablePair(left, right);
  }
  
  public abstract L getLeft();
  
  public abstract R getRight();
  
  public final L getKey()
  {
    return getLeft();
  }
  
  public R getValue()
  {
    return getRight();
  }
  
  public int compareTo(Pair<L, R> other)
  {
    return new CompareToBuilder().append(getLeft(), other.getLeft()).append(getRight(), other.getRight()).toComparison();
  }
  
  public boolean equals(Object obj)
  {
    if (obj == this) {
      return true;
    }
    if ((obj instanceof Map.Entry))
    {
      Map.Entry<?, ?> other = (Map.Entry)obj;
      return (ObjectUtils.equals(getKey(), other.getKey())) && (ObjectUtils.equals(getValue(), other.getValue()));
    }
    return false;
  }
  
  public int hashCode()
  {
    return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() == null ? 0 : getValue().hashCode());
  }
  
  public String toString()
  {
    return '(' + getLeft() + ',' + getRight() + ')';
  }
  
  public String toString(String format)
  {
    return String.format(format, new Object[] { getLeft(), getRight() });
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.tuple.Pair
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */