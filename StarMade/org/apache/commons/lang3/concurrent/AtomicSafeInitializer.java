/*  1:   */package org.apache.commons.lang3.concurrent;
/*  2:   */
/*  3:   */import java.util.concurrent.atomic.AtomicReference;
/*  4:   */
/* 56:   */public abstract class AtomicSafeInitializer<T>
/* 57:   */  implements ConcurrentInitializer<T>
/* 58:   */{
/* 59:59 */  private final AtomicReference<AtomicSafeInitializer<T>> factory = new AtomicReference();
/* 60:   */  
/* 63:63 */  private final AtomicReference<T> reference = new AtomicReference();
/* 64:   */  
/* 68:   */  public final T get()
/* 69:   */    throws ConcurrentException
/* 70:   */  {
/* 71:   */    T result;
/* 72:   */    
/* 75:75 */    while ((result = this.reference.get()) == null) {
/* 76:76 */      if (this.factory.compareAndSet(null, this)) {
/* 77:77 */        this.reference.set(initialize());
/* 78:   */      }
/* 79:   */    }
/* 80:   */    
/* 81:81 */    return result;
/* 82:   */  }
/* 83:   */  
/* 84:   */  protected abstract T initialize()
/* 85:   */    throws ConcurrentException;
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.AtomicSafeInitializer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */