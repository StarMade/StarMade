/*  1:   */package it.unimi.dsi.fastutil.booleans;
/*  2:   */
/*  3:   */import java.util.Set;
/*  4:   */
/* 43:   */public abstract class AbstractBooleanSet
/* 44:   */  extends AbstractBooleanCollection
/* 45:   */  implements Cloneable, BooleanSet
/* 46:   */{
/* 47:   */  public abstract BooleanIterator iterator();
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
/* 66:66 */    BooleanIterator i = iterator();
/* 67:   */    
/* 68:68 */    while (n-- != 0) {
/* 69:69 */      boolean k = i.nextBoolean();
/* 70:70 */      h += (k ? 1231 : 1237);
/* 71:   */    }
/* 72:72 */    return h;
/* 73:   */  }
/* 74:   */  
/* 75:75 */  public boolean remove(boolean k) { throw new UnsupportedOperationException(); }
/* 76:   */  
/* 81:   */  public boolean rem(boolean k)
/* 82:   */  {
/* 83:83 */    return remove(k);
/* 84:   */  }
/* 85:   */  
/* 86:   */  public boolean remove(Object o) {
/* 87:87 */    return remove(((Boolean)o).booleanValue());
/* 88:   */  }
/* 89:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */