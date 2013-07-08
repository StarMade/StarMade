/*  1:   */package org.dom4j.util;
/*  2:   */
/* 20:   */public class SimpleSingleton
/* 21:   */  implements SingletonStrategy
/* 22:   */{
/* 23:23 */  private String singletonClassName = null;
/* 24:   */  
/* 25:25 */  private Object singletonInstance = null;
/* 26:   */  
/* 29:   */  public Object instance()
/* 30:   */  {
/* 31:31 */    return this.singletonInstance;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public void reset() {
/* 35:35 */    if (this.singletonClassName != null) {
/* 36:36 */      Class clazz = null;
/* 37:   */      try {
/* 38:38 */        clazz = Thread.currentThread().getContextClassLoader().loadClass(this.singletonClassName);
/* 39:   */        
/* 40:40 */        this.singletonInstance = clazz.newInstance();
/* 41:   */      } catch (Exception ignore) {
/* 42:   */        try {
/* 43:43 */          clazz = Class.forName(this.singletonClassName);
/* 44:44 */          this.singletonInstance = clazz.newInstance();
/* 45:   */        }
/* 46:   */        catch (Exception ignore2) {}
/* 47:   */      }
/* 48:   */    }
/* 49:   */  }
/* 50:   */  
/* 51:   */  public void setSingletonClassName(String singletonClassName)
/* 52:   */  {
/* 53:53 */    this.singletonClassName = singletonClassName;
/* 54:54 */    reset();
/* 55:   */  }
/* 56:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.SimpleSingleton
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */