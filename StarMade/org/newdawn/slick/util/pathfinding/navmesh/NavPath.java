/*  1:   */package org.newdawn.slick.util.pathfinding.navmesh;
/*  2:   */
/*  3:   */import java.util.ArrayList;
/*  4:   */
/* 10:   */public class NavPath
/* 11:   */{
/* 12:12 */  private ArrayList links = new ArrayList();
/* 13:   */  
/* 24:   */  public void push(Link link)
/* 25:   */  {
/* 26:26 */    this.links.add(link);
/* 27:   */  }
/* 28:   */  
/* 33:   */  public int length()
/* 34:   */  {
/* 35:35 */    return this.links.size();
/* 36:   */  }
/* 37:   */  
/* 43:   */  public float getX(int step)
/* 44:   */  {
/* 45:45 */    return ((Link)this.links.get(step)).getX();
/* 46:   */  }
/* 47:   */  
/* 53:   */  public float getY(int step)
/* 54:   */  {
/* 55:55 */    return ((Link)this.links.get(step)).getY();
/* 56:   */  }
/* 57:   */  
/* 62:   */  public String toString()
/* 63:   */  {
/* 64:64 */    return "[Path length=" + length() + "]";
/* 65:   */  }
/* 66:   */  
/* 71:   */  public void remove(int i)
/* 72:   */  {
/* 73:73 */    this.links.remove(i);
/* 74:   */  }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.pathfinding.navmesh.NavPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */