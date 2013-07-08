/*  1:   */package org.lwjgl.util.mapped;
/*  2:   */
/* 11:   */public class MappedSet2
/* 12:   */{
/* 13:   */  private final MappedObject a;
/* 14:   */  
/* 21:   */  private final MappedObject b;
/* 22:   */  
/* 29:   */  public int view;
/* 30:   */  
/* 38:   */  MappedSet2(MappedObject a, MappedObject b)
/* 39:   */  {
/* 40:40 */    this.a = a;
/* 41:41 */    this.b = b;
/* 42:   */  }
/* 43:   */  
/* 45:   */  void view(int view)
/* 46:   */  {
/* 47:47 */    this.a.setViewAddress(this.a.getViewAddress(view));
/* 48:48 */    this.b.setViewAddress(this.b.getViewAddress(view));
/* 49:   */  }
/* 50:   */  
/* 51:   */  public void next() {
/* 52:52 */    this.a.next();
/* 53:53 */    this.b.next();
/* 54:   */  }
/* 55:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedSet2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */