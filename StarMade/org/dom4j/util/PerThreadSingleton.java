/*  1:   */package org.dom4j.util;
/*  2:   */
/*  3:   */import java.lang.ref.WeakReference;
/*  4:   */
/* 22:   */public class PerThreadSingleton
/* 23:   */  implements SingletonStrategy
/* 24:   */{
/* 25:25 */  private String singletonClassName = null;
/* 26:   */  
/* 27:27 */  private ThreadLocal perThreadCache = new ThreadLocal();
/* 28:   */  
/* 31:   */  public void reset()
/* 32:   */  {
/* 33:33 */    this.perThreadCache = new ThreadLocal();
/* 34:   */  }
/* 35:   */  
/* 36:   */  public Object instance() {
/* 37:37 */    Object singletonInstancePerThread = null;
/* 38:   */    
/* 39:39 */    WeakReference ref = (WeakReference)this.perThreadCache.get();
/* 40:   */    
/* 42:42 */    if ((ref == null) || (ref.get() == null)) {
/* 43:43 */      Class clazz = null;
/* 44:   */      try {
/* 45:45 */        clazz = Thread.currentThread().getContextClassLoader().loadClass(this.singletonClassName);
/* 46:   */        
/* 47:47 */        singletonInstancePerThread = clazz.newInstance();
/* 48:   */      } catch (Exception ignore) {
/* 49:   */        try {
/* 50:50 */          clazz = Class.forName(this.singletonClassName);
/* 51:51 */          singletonInstancePerThread = clazz.newInstance();
/* 52:   */        }
/* 53:   */        catch (Exception ignore2) {}
/* 54:   */      }
/* 55:55 */      this.perThreadCache.set(new WeakReference(singletonInstancePerThread));
/* 56:   */    } else {
/* 57:57 */      singletonInstancePerThread = ref.get();
/* 58:   */    }
/* 59:59 */    return singletonInstancePerThread;
/* 60:   */  }
/* 61:   */  
/* 62:   */  public void setSingletonClassName(String singletonClassName) {
/* 63:63 */    this.singletonClassName = singletonClassName;
/* 64:   */  }
/* 65:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.PerThreadSingleton
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */