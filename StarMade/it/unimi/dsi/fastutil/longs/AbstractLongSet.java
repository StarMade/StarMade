/*  1:   */package it.unimi.dsi.fastutil.longs;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.HashCommon;
/*  4:   */import java.util.Set;
/*  5:   */
/* 43:   */public abstract class AbstractLongSet
/* 44:   */  extends AbstractLongCollection
/* 45:   */  implements Cloneable, LongSet
/* 46:   */{
/* 47:   */  public abstract LongIterator iterator();
/* 48:   */  
/* 49:   */  public boolean equals(Object o)
/* 50:   */  {
/* 51:51 */    if (o == this) return true;
/* 52:52 */    if (!(o instanceof Set)) return false;
/* 53:53 */    Set<?> s = (Set)o;
/* 54:54 */    if (s.size() != size()) return false;
/* 55:55 */    return containsAll(s);
/* 56:   */  }
/* 57:   */  
/* 63:   */  public int hashCode()
/* 64:   */  {
/* 65:65 */    int h = 0;int n = size();
/* 66:66 */    LongIterator i = iterator();
/* 67:   */    
/* 68:68 */    while (n-- != 0) {
/* 69:69 */      long k = i.nextLong();
/* 70:70 */      h += HashCommon.long2int(k);
/* 71:   */    }
/* 72:72 */    return h;
/* 73:   */  }
/* 74:   */  
/* 75:75 */  public boolean remove(long k) { throw new UnsupportedOperationException(); }
/* 76:   */  
/* 81:   */  public boolean rem(long k)
/* 82:   */  {
/* 83:83 */    return remove(k);
/* 84:   */  }
/* 85:   */  
/* 86:   */  public boolean remove(Object o) {
/* 87:87 */    return remove(((Long)o).longValue());
/* 88:   */  }
/* 89:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */