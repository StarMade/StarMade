/*  1:   */package com.bulletphysics.util;
/*  2:   */
/* 16:   */public class ObjectStackList<T>
/* 17:   */  extends StackList<T>
/* 18:   */{
/* 19:   */  private Class<T> cls;
/* 20:   */  
/* 34:   */  public ObjectStackList(Class<T> cls)
/* 35:   */  {
/* 36:36 */    super(false);
/* 37:37 */    this.cls = cls;
/* 38:   */  }
/* 39:   */  
/* 40:   */  protected T create()
/* 41:   */  {
/* 42:   */    try {
/* 43:43 */      return this.cls.newInstance();
/* 44:   */    }
/* 45:   */    catch (InstantiationException e) {
/* 46:46 */      throw new IllegalStateException(e);
/* 47:   */    }
/* 48:   */    catch (IllegalAccessException e) {
/* 49:49 */      throw new IllegalStateException(e);
/* 50:   */    }
/* 51:   */  }
/* 52:   */  
/* 53:   */  protected void copy(T dest, T src)
/* 54:   */  {
/* 55:55 */    throw new UnsupportedOperationException();
/* 56:   */  }
/* 57:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.util.ObjectStackList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */