package org.newdawn.slick.util.pathfinding.navmesh;

import java.util.ArrayList;
import java.util.HashMap;

public class Space
{
  private float field_2101;
  private float field_2102;
  private float width;
  private float height;
  private HashMap links = new HashMap();
  private ArrayList linksList = new ArrayList();
  private float cost;
  
  public Space(float local_x, float local_y, float width, float height)
  {
    this.field_2101 = local_x;
    this.field_2102 = local_y;
    this.width = width;
    this.height = height;
  }
  
  public float getWidth()
  {
    return this.width;
  }
  
  public float getHeight()
  {
    return this.height;
  }
  
  public float getX()
  {
    return this.field_2101;
  }
  
  public float getY()
  {
    return this.field_2102;
  }
  
  public void link(Space other)
  {
    if ((inTolerance(this.field_2101, other.field_2101 + other.width)) || (inTolerance(this.field_2101 + this.width, other.field_2101)))
    {
      float linkx = this.field_2101;
      if (this.field_2101 + this.width == other.field_2101) {
        linkx = this.field_2101 + this.width;
      }
      float top = Math.max(this.field_2102, other.field_2102);
      float bottom = Math.min(this.field_2102 + this.height, other.field_2102 + other.height);
      float linky = top + (bottom - top) / 2.0F;
      Link link = new Link(linkx, linky, other);
      this.links.put(other, link);
      this.linksList.add(link);
    }
    if ((inTolerance(this.field_2102, other.field_2102 + other.height)) || (inTolerance(this.field_2102 + this.height, other.field_2102)))
    {
      float linkx = this.field_2102;
      if (this.field_2102 + this.height == other.field_2102) {
        linkx = this.field_2102 + this.height;
      }
      float top = Math.max(this.field_2101, other.field_2101);
      float bottom = Math.min(this.field_2101 + this.width, other.field_2101 + other.width);
      float linky = top + (bottom - top) / 2.0F;
      Link link = new Link(linky, linkx, other);
      this.links.put(other, link);
      this.linksList.add(link);
    }
  }
  
  private boolean inTolerance(float local_a, float local_b)
  {
    return local_a == local_b;
  }
  
  public boolean hasJoinedEdge(Space other)
  {
    if ((inTolerance(this.field_2101, other.field_2101 + other.width)) || (inTolerance(this.field_2101 + this.width, other.field_2101)))
    {
      if ((this.field_2102 >= other.field_2102) && (this.field_2102 <= other.field_2102 + other.height)) {
        return true;
      }
      if ((this.field_2102 + this.height >= other.field_2102) && (this.field_2102 + this.height <= other.field_2102 + other.height)) {
        return true;
      }
      if ((other.field_2102 >= this.field_2102) && (other.field_2102 <= this.field_2102 + this.height)) {
        return true;
      }
      if ((other.field_2102 + other.height >= this.field_2102) && (other.field_2102 + other.height <= this.field_2102 + this.height)) {
        return true;
      }
    }
    if ((inTolerance(this.field_2102, other.field_2102 + other.height)) || (inTolerance(this.field_2102 + this.height, other.field_2102)))
    {
      if ((this.field_2101 >= other.field_2101) && (this.field_2101 <= other.field_2101 + other.width)) {
        return true;
      }
      if ((this.field_2101 + this.width >= other.field_2101) && (this.field_2101 + this.width <= other.field_2101 + other.width)) {
        return true;
      }
      if ((other.field_2101 >= this.field_2101) && (other.field_2101 <= this.field_2101 + this.width)) {
        return true;
      }
      if ((other.field_2101 + other.width >= this.field_2101) && (other.field_2101 + other.width <= this.field_2101 + this.width)) {
        return true;
      }
    }
    return false;
  }
  
  public Space merge(Space other)
  {
    float minx = Math.min(this.field_2101, other.field_2101);
    float miny = Math.min(this.field_2102, other.field_2102);
    float newwidth = this.width + other.width;
    float newheight = this.height + other.height;
    if (this.field_2101 == other.field_2101) {
      newwidth = this.width;
    } else {
      newheight = this.height;
    }
    return new Space(minx, miny, newwidth, newheight);
  }
  
  public boolean canMerge(Space other)
  {
    if (!hasJoinedEdge(other)) {
      return false;
    }
    if ((this.field_2101 == other.field_2101) && (this.width == other.width)) {
      return true;
    }
    return (this.field_2102 == other.field_2102) && (this.height == other.height);
  }
  
  public int getLinkCount()
  {
    return this.linksList.size();
  }
  
  public Link getLink(int index)
  {
    return (Link)this.linksList.get(index);
  }
  
  public boolean contains(float local_xp, float local_yp)
  {
    return (local_xp >= this.field_2101) && (local_xp < this.field_2101 + this.width) && (local_yp >= this.field_2102) && (local_yp < this.field_2102 + this.height);
  }
  
  public void fill(Space target, float local_sx, float local_sy, float cost)
  {
    if (cost >= this.cost) {
      return;
    }
    this.cost = cost;
    if (target == this) {
      return;
    }
    for (int local_i = 0; local_i < getLinkCount(); local_i++)
    {
      Link link = getLink(local_i);
      float extraCost = link.distance2(local_sx, local_sy);
      float nextCost = cost + extraCost;
      link.getTarget().fill(target, link.getX(), link.getY(), nextCost);
    }
  }
  
  public void clearCost()
  {
    this.cost = 3.4028235E+38F;
  }
  
  public float getCost()
  {
    return this.cost;
  }
  
  public boolean pickLowestCost(Space target, NavPath path)
  {
    if (target == this) {
      return true;
    }
    if (this.links.size() == 0) {
      return false;
    }
    Link bestLink = null;
    for (int local_i = 0; local_i < getLinkCount(); local_i++)
    {
      Link link = getLink(local_i);
      if ((bestLink == null) || (link.getTarget().getCost() < bestLink.getTarget().getCost())) {
        bestLink = link;
      }
    }
    path.push(bestLink);
    return bestLink.getTarget().pickLowestCost(target, path);
  }
  
  public String toString()
  {
    return "[Space " + this.field_2101 + "," + this.field_2102 + " " + this.width + "," + this.height + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.pathfinding.navmesh.Space
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */