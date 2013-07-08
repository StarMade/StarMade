/*  1:   */package org.dom4j.tree;
/*  2:   */
/*  3:   */import java.util.Iterator;
/*  4:   */
/* 19:   */public class SingleIterator
/* 20:   */  implements Iterator
/* 21:   */{
/* 22:22 */  private boolean first = true;
/* 23:   */  private Object object;
/* 24:   */  
/* 25:   */  public SingleIterator(Object object)
/* 26:   */  {
/* 27:27 */    this.object = object;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public boolean hasNext() {
/* 31:31 */    return this.first;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public Object next() {
/* 35:35 */    Object answer = this.object;
/* 36:36 */    this.object = null;
/* 37:37 */    this.first = false;
/* 38:   */    
/* 39:39 */    return answer;
/* 40:   */  }
/* 41:   */  
/* 42:   */  public void remove() {
/* 43:43 */    throw new UnsupportedOperationException("remove() is not supported by this iterator");
/* 44:   */  }
/* 45:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.SingleIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */