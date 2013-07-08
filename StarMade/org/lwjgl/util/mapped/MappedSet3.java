/*  1:   */package org.lwjgl.util.mapped;
/*  2:   */
/*  9:   */public class MappedSet3
/* 10:   */{
/* 11:   */  private final MappedObject a;
/* 12:   */  
/* 18:   */  private final MappedObject b;
/* 19:   */  
/* 25:   */  private final MappedObject c;
/* 26:   */  
/* 31:   */  public int view;
/* 32:   */  
/* 38:   */  MappedSet3(MappedObject a, MappedObject b, MappedObject c)
/* 39:   */  {
/* 40:40 */    this.a = a;
/* 41:41 */    this.b = b;
/* 42:42 */    this.c = c;
/* 43:   */  }
/* 44:   */  
/* 46:   */  void view(int view)
/* 47:   */  {
/* 48:48 */    this.a.setViewAddress(this.a.getViewAddress(view));
/* 49:49 */    this.b.setViewAddress(this.b.getViewAddress(view));
/* 50:50 */    this.c.setViewAddress(this.c.getViewAddress(view));
/* 51:   */  }
/* 52:   */  
/* 53:   */  public void next() {
/* 54:54 */    this.a.next();
/* 55:55 */    this.b.next();
/* 56:56 */    this.c.next();
/* 57:   */  }
/* 58:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedSet3
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */