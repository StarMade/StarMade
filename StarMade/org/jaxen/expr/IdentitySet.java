/*  1:   */package org.jaxen.expr;
/*  2:   */
/*  3:   */import java.util.HashSet;
/*  4:   */
/* 47:   */final class IdentitySet
/* 48:   */{
/* 49:49 */  private HashSet contents = new HashSet();
/* 50:   */  
/* 54:   */  void add(Object object)
/* 55:   */  {
/* 56:56 */    IdentityWrapper wrapper = new IdentityWrapper(object);
/* 57:57 */    this.contents.add(wrapper);
/* 58:   */  }
/* 59:   */  
/* 60:   */  public boolean contains(Object object) {
/* 61:61 */    IdentityWrapper wrapper = new IdentityWrapper(object);
/* 62:62 */    return this.contents.contains(wrapper);
/* 63:   */  }
/* 64:   */  
/* 65:   */  private static class IdentityWrapper
/* 66:   */  {
/* 67:   */    private Object object;
/* 68:   */    
/* 69:   */    IdentityWrapper(Object object) {
/* 70:70 */      this.object = object;
/* 71:   */    }
/* 72:   */    
/* 73:   */    public boolean equals(Object o) {
/* 74:74 */      IdentityWrapper w = (IdentityWrapper)o;
/* 75:75 */      return this.object == w.object;
/* 76:   */    }
/* 77:   */    
/* 78:   */    public int hashCode() {
/* 79:79 */      return System.identityHashCode(this.object);
/* 80:   */    }
/* 81:   */  }
/* 82:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.IdentitySet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */