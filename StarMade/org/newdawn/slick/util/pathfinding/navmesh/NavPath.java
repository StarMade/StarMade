package org.newdawn.slick.util.pathfinding.navmesh;

import java.util.ArrayList;

public class NavPath
{
  private ArrayList links = new ArrayList();
  
  public void push(Link link)
  {
    this.links.add(link);
  }
  
  public int length()
  {
    return this.links.size();
  }
  
  public float getX(int step)
  {
    return ((Link)this.links.get(step)).getX();
  }
  
  public float getY(int step)
  {
    return ((Link)this.links.get(step)).getY();
  }
  
  public String toString()
  {
    return "[Path length=" + length() + "]";
  }
  
  public void remove(int local_i)
  {
    this.links.remove(local_i);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.pathfinding.navmesh.NavPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */