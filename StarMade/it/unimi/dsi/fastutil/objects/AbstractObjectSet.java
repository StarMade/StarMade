/*  1:   */package it.unimi.dsi.fastutil.objects;
/*  2:   */
/*  3:   */import java.util.Set;
/*  4:   */
/* 42:   */public abstract class AbstractObjectSet<K>
/* 43:   */  extends AbstractObjectCollection<K>
/* 44:   */  implements Cloneable, ObjectSet<K>
/* 45:   */{
/* 46:   */  public abstract ObjectIterator<K> iterator();
/* 47:   */  
/* 48:   */  public boolean equals(Object o)
/* 49:   */  {
/* 50:50 */    if (o == this) return true;
/* 51:51 */    if (!(o instanceof Set)) return false;
/* 52:52 */    Set<?> s = (Set)o;
/* 53:53 */    if (s.size() != size()) return false;
/* 54:54 */    return containsAll(s);
/* 55:   */  }
/* 56:   */  
/* 62:   */  public int hashCode()
/* 63:   */  {
/* 64:64 */    int h = 0;int n = size();
/* 65:65 */    ObjectIterator<K> i = iterator();
/* 66:   */    
/* 67:67 */    while (n-- != 0) {
/* 68:68 */      K k = i.next();
/* 69:69 */      h += (k == null ? 0 : k.hashCode());
/* 70:   */    }
/* 71:71 */    return h;
/* 72:   */  }
/* 73:   */  
/* 74:74 */  public boolean remove(Object k) { throw new UnsupportedOperationException(); }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */