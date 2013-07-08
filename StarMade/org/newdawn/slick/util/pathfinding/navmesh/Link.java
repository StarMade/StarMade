/*  1:   */package org.newdawn.slick.util.pathfinding.navmesh;
/*  2:   */
/*  7:   */public class Link
/*  8:   */{
/*  9:   */  private float px;
/* 10:   */  
/* 13:   */  private float py;
/* 14:   */  
/* 17:   */  private Space target;
/* 18:   */  
/* 22:   */  public Link(float px, float py, Space target)
/* 23:   */  {
/* 24:24 */    this.px = px;
/* 25:25 */    this.py = py;
/* 26:26 */    this.target = target;
/* 27:   */  }
/* 28:   */  
/* 35:   */  public float distance2(float tx, float ty)
/* 36:   */  {
/* 37:37 */    float dx = tx - this.px;
/* 38:38 */    float dy = ty - this.py;
/* 39:   */    
/* 40:40 */    return dx * dx + dy * dy;
/* 41:   */  }
/* 42:   */  
/* 47:   */  public float getX()
/* 48:   */  {
/* 49:49 */    return this.px;
/* 50:   */  }
/* 51:   */  
/* 56:   */  public float getY()
/* 57:   */  {
/* 58:58 */    return this.py;
/* 59:   */  }
/* 60:   */  
/* 65:   */  public Space getTarget()
/* 66:   */  {
/* 67:67 */    return this.target;
/* 68:   */  }
/* 69:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.pathfinding.navmesh.Link
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */