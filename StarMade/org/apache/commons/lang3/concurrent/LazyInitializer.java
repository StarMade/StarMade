/*   1:    */package org.apache.commons.lang3.concurrent;
/*   2:    */
/*  45:    */public abstract class LazyInitializer<T>
/*  46:    */  implements ConcurrentInitializer<T>
/*  47:    */{
/*  48:    */  private volatile T object;
/*  49:    */  
/*  91:    */  public T get()
/*  92:    */    throws ConcurrentException
/*  93:    */  {
/*  94: 94 */    T result = this.object;
/*  95:    */    
/*  96: 96 */    if (result == null) {
/*  97: 97 */      synchronized (this) {
/*  98: 98 */        result = this.object;
/*  99: 99 */        if (result == null) {
/* 100:100 */          this.object = (result = initialize());
/* 101:    */        }
/* 102:    */      }
/* 103:    */    }
/* 104:    */    
/* 105:105 */    return result;
/* 106:    */  }
/* 107:    */  
/* 108:    */  protected abstract T initialize()
/* 109:    */    throws ConcurrentException;
/* 110:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.LazyInitializer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */