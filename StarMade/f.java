/*  1:   */import java.util.ArrayList;
/*  2:   */import java.util.Collections;
/*  3:   */
/* 26:   */public final class f
/* 27:   */  extends ArrayList
/* 28:   */{
/* 29:   */  private static final long serialVersionUID = 5564392963896845136L;
/* 30:   */  
/* 31:   */  public final boolean a(Comparable paramComparable)
/* 32:   */  {
/* 33:33 */    if (paramComparable == null) {
/* 34:34 */      throw new NullPointerException("tried to add null " + paramComparable);
/* 35:   */    }
/* 36:36 */    if (size() == 0) {
/* 37:37 */      return super.add(paramComparable);
/* 38:   */    }
/* 39:   */    
/* 41:   */    int i;
/* 42:   */    
/* 44:44 */    if ((i = Collections.binarySearch(this, paramComparable)) >= 0) {
/* 45:45 */      super.add(i + 1, paramComparable);
/* 46:   */      
/* 47:47 */      return true;
/* 48:   */    }
/* 49:   */    
/* 51:51 */    super.add(-i - 1, paramComparable);
/* 52:   */    
/* 53:53 */    return true;
/* 54:   */  }
/* 55:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */