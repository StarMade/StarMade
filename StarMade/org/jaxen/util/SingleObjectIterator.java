/*   1:    */package org.jaxen.util;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import java.util.NoSuchElementException;
/*   5:    */
/*  64:    */public class SingleObjectIterator
/*  65:    */  implements Iterator
/*  66:    */{
/*  67:    */  private Object object;
/*  68:    */  private boolean seen;
/*  69:    */  
/*  70:    */  public SingleObjectIterator(Object object)
/*  71:    */  {
/*  72: 72 */    this.object = object;
/*  73: 73 */    this.seen = false;
/*  74:    */  }
/*  75:    */  
/*  84:    */  public boolean hasNext()
/*  85:    */  {
/*  86: 86 */    return !this.seen;
/*  87:    */  }
/*  88:    */  
/*  99:    */  public Object next()
/* 100:    */  {
/* 101:101 */    if (hasNext())
/* 102:    */    {
/* 103:103 */      this.seen = true;
/* 104:104 */      return this.object;
/* 105:    */    }
/* 106:    */    
/* 107:107 */    throw new NoSuchElementException();
/* 108:    */  }
/* 109:    */  
/* 115:    */  public void remove()
/* 116:    */  {
/* 117:117 */    throw new UnsupportedOperationException();
/* 118:    */  }
/* 119:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.SingleObjectIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */