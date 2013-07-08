/*  1:   */package org.jaxen.util;
/*  2:   */
/*  3:   */import java.util.AbstractList;
/*  4:   */
/* 67:   */public class SingletonList
/* 68:   */  extends AbstractList
/* 69:   */{
/* 70:   */  private final Object element;
/* 71:   */  
/* 72:   */  public SingletonList(Object element)
/* 73:   */  {
/* 74:74 */    this.element = element;
/* 75:   */  }
/* 76:   */  
/* 81:   */  public int size()
/* 82:   */  {
/* 83:83 */    return 1;
/* 84:   */  }
/* 85:   */  
/* 93:   */  public Object get(int index)
/* 94:   */  {
/* 95:95 */    if (index == 0) {
/* 96:96 */      return this.element;
/* 97:   */    }
/* 98:98 */    throw new IndexOutOfBoundsException(index + " != 0");
/* 99:   */  }
/* 100:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.SingletonList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */