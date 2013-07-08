/*   1:    */package org.apache.commons.lang3.tuple;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Map.Entry;
/*   5:    */import org.apache.commons.lang3.ObjectUtils;
/*   6:    */import org.apache.commons.lang3.builder.CompareToBuilder;
/*   7:    */
/*  53:    */public abstract class Pair<L, R>
/*  54:    */  implements Map.Entry<L, R>, Comparable<Pair<L, R>>, Serializable
/*  55:    */{
/*  56:    */  private static final long serialVersionUID = 4954918890077093841L;
/*  57:    */  
/*  58:    */  public static <L, R> Pair<L, R> of(L left, R right)
/*  59:    */  {
/*  60: 60 */    return new ImmutablePair(left, right);
/*  61:    */  }
/*  62:    */  
/*  71:    */  public abstract L getLeft();
/*  72:    */  
/*  80:    */  public abstract R getRight();
/*  81:    */  
/*  89:    */  public final L getKey()
/*  90:    */  {
/*  91: 91 */    return getLeft();
/*  92:    */  }
/*  93:    */  
/* 101:    */  public R getValue()
/* 102:    */  {
/* 103:103 */    return getRight();
/* 104:    */  }
/* 105:    */  
/* 113:    */  public int compareTo(Pair<L, R> other)
/* 114:    */  {
/* 115:115 */    return new CompareToBuilder().append(getLeft(), other.getLeft()).append(getRight(), other.getRight()).toComparison();
/* 116:    */  }
/* 117:    */  
/* 125:    */  public boolean equals(Object obj)
/* 126:    */  {
/* 127:127 */    if (obj == this) {
/* 128:128 */      return true;
/* 129:    */    }
/* 130:130 */    if ((obj instanceof Map.Entry)) {
/* 131:131 */      Map.Entry<?, ?> other = (Map.Entry)obj;
/* 132:132 */      return (ObjectUtils.equals(getKey(), other.getKey())) && (ObjectUtils.equals(getValue(), other.getValue()));
/* 133:    */    }
/* 134:    */    
/* 135:135 */    return false;
/* 136:    */  }
/* 137:    */  
/* 145:    */  public int hashCode()
/* 146:    */  {
/* 147:147 */    return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() == null ? 0 : getValue().hashCode());
/* 148:    */  }
/* 149:    */  
/* 156:    */  public String toString()
/* 157:    */  {
/* 158:158 */    return '(' + getLeft() + ',' + getRight() + ')';
/* 159:    */  }
/* 160:    */  
/* 171:    */  public String toString(String format)
/* 172:    */  {
/* 173:173 */    return String.format(format, new Object[] { getLeft(), getRight() });
/* 174:    */  }
/* 175:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.tuple.Pair
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */