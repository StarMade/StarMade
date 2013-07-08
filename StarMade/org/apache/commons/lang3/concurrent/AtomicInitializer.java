/*  1:   */package org.apache.commons.lang3.concurrent;
/*  2:   */
/*  3:   */import java.util.concurrent.atomic.AtomicReference;
/*  4:   */
/* 66:   */public abstract class AtomicInitializer<T>
/* 67:   */  implements ConcurrentInitializer<T>
/* 68:   */{
/* 69:69 */  private final AtomicReference<T> reference = new AtomicReference();
/* 70:   */  
/* 78:   */  public T get()
/* 79:   */    throws ConcurrentException
/* 80:   */  {
/* 81:81 */    T result = this.reference.get();
/* 82:   */    
/* 83:83 */    if (result == null) {
/* 84:84 */      result = initialize();
/* 85:85 */      if (!this.reference.compareAndSet(null, result))
/* 86:   */      {
/* 87:87 */        result = this.reference.get();
/* 88:   */      }
/* 89:   */    }
/* 90:   */    
/* 91:91 */    return result;
/* 92:   */  }
/* 93:   */  
/* 94:   */  protected abstract T initialize()
/* 95:   */    throws ConcurrentException;
/* 96:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.AtomicInitializer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */