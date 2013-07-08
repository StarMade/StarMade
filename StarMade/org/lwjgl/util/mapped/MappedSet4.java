/*  1:   */package org.lwjgl.util.mapped;
/*  2:   */
/*  8:   */public class MappedSet4
/*  9:   */{
/* 10:   */  private final MappedObject a;
/* 11:   */  
/* 16:   */  private final MappedObject b;
/* 17:   */  
/* 22:   */  private final MappedObject c;
/* 23:   */  
/* 27:   */  private final MappedObject d;
/* 28:   */  
/* 32:   */  public int view;
/* 33:   */  
/* 38:   */  MappedSet4(MappedObject a, MappedObject b, MappedObject c, MappedObject d)
/* 39:   */  {
/* 40:40 */    this.a = a;
/* 41:41 */    this.b = b;
/* 42:42 */    this.c = c;
/* 43:43 */    this.d = d;
/* 44:   */  }
/* 45:   */  
/* 47:   */  void view(int view)
/* 48:   */  {
/* 49:49 */    this.a.setViewAddress(this.a.getViewAddress(view));
/* 50:50 */    this.b.setViewAddress(this.b.getViewAddress(view));
/* 51:51 */    this.c.setViewAddress(this.c.getViewAddress(view));
/* 52:52 */    this.d.setViewAddress(this.d.getViewAddress(view));
/* 53:   */  }
/* 54:   */  
/* 55:   */  public void next() {
/* 56:56 */    this.a.next();
/* 57:57 */    this.b.next();
/* 58:58 */    this.c.next();
/* 59:59 */    this.d.next();
/* 60:   */  }
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedSet4
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */