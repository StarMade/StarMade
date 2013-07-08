/*  1:   */package org.dom4j.tree;
/*  2:   */
/*  3:   */import java.util.Iterator;
/*  4:   */import java.util.NoSuchElementException;
/*  5:   */
/* 21:   *//**
/* 22:   */ * @deprecated
/* 23:   */ */
/* 24:   */public abstract class FilterIterator
/* 25:   */  implements Iterator
/* 26:   */{
/* 27:   */  protected Iterator proxy;
/* 28:   */  private Object next;
/* 29:29 */  private boolean first = true;
/* 30:   */  
/* 31:   */  public FilterIterator(Iterator proxy) {
/* 32:32 */    this.proxy = proxy;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public boolean hasNext() {
/* 36:36 */    if (this.first) {
/* 37:37 */      this.next = findNext();
/* 38:38 */      this.first = false;
/* 39:   */    }
/* 40:   */    
/* 41:41 */    return this.next != null;
/* 42:   */  }
/* 43:   */  
/* 44:   */  public Object next() throws NoSuchElementException {
/* 45:45 */    if (!hasNext()) {
/* 46:46 */      throw new NoSuchElementException();
/* 47:   */    }
/* 48:   */    
/* 49:49 */    Object answer = this.next;
/* 50:50 */    this.next = findNext();
/* 51:   */    
/* 52:52 */    return answer;
/* 53:   */  }
/* 54:   */  
/* 61:   */  public void remove()
/* 62:   */  {
/* 63:63 */    throw new UnsupportedOperationException();
/* 64:   */  }
/* 65:   */  
/* 70:   */  protected abstract boolean matches(Object paramObject);
/* 71:   */  
/* 76:   */  protected Object findNext()
/* 77:   */  {
/* 78:78 */    if (this.proxy != null) {
/* 79:79 */      while (this.proxy.hasNext()) {
/* 80:80 */        Object nextObject = this.proxy.next();
/* 81:   */        
/* 82:82 */        if ((nextObject != null) && (matches(nextObject))) {
/* 83:83 */          return nextObject;
/* 84:   */        }
/* 85:   */      }
/* 86:   */      
/* 87:87 */      this.proxy = null;
/* 88:   */    }
/* 89:   */    
/* 90:90 */    return null;
/* 91:   */  }
/* 92:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.FilterIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */