package org.newdawn.slick.util.pathfinding.navmesh;

import java.util.ArrayList;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class NavMeshBuilder
  implements PathFindingContext
{
  private int field_342;
  private int field_343;
  private float smallestSpace = 0.2F;
  private boolean tileBased;
  
  public NavMesh build(TileBasedMap map)
  {
    return build(map, true);
  }
  
  public NavMesh build(TileBasedMap map, boolean tileBased)
  {
    this.tileBased = tileBased;
    ArrayList spaces = new ArrayList();
    Space local_x;
    if (tileBased) {
      for (int local_x = 0; local_x < map.getWidthInTiles(); local_x++) {
        for (int local_y = 0; local_y < map.getHeightInTiles(); local_y++) {
          if (!map.blocked(this, local_x, local_y)) {
            spaces.add(new Space(local_x, local_y, 1.0F, 1.0F));
          }
        }
      }
    } else {
      local_x = new Space(0.0F, 0.0F, map.getWidthInTiles(), map.getHeightInTiles());
    }
    while (mergeSpaces(spaces)) {}
    linkSpaces(spaces);
    return new NavMesh(spaces);
  }
  
  private boolean mergeSpaces(ArrayList spaces)
  {
    for (int source = 0; source < spaces.size(); source++)
    {
      Space local_a = (Space)spaces.get(source);
      for (int target = source + 1; target < spaces.size(); target++)
      {
        Space local_b = (Space)spaces.get(target);
        if (local_a.canMerge(local_b))
        {
          spaces.remove(local_a);
          spaces.remove(local_b);
          spaces.add(local_a.merge(local_b));
          return true;
        }
      }
    }
    return false;
  }
  
  private void linkSpaces(ArrayList spaces)
  {
    for (int source = 0; source < spaces.size(); source++)
    {
      Space local_a = (Space)spaces.get(source);
      for (int target = source + 1; target < spaces.size(); target++)
      {
        Space local_b = (Space)spaces.get(target);
        if (local_a.hasJoinedEdge(local_b))
        {
          local_a.link(local_b);
          local_b.link(local_a);
        }
      }
    }
  }
  
  public boolean clear(TileBasedMap map, Space space)
  {
    if (this.tileBased) {
      return true;
    }
    float local_x = 0.0F;
    for (boolean donex = false; local_x < space.getWidth(); donex = true)
    {
      float local_y = 0.0F;
      for (boolean doney = false; local_y < space.getHeight(); doney = true)
      {
        label29:
        this.field_342 = ((int)(space.getX() + local_x));
        this.field_343 = ((int)(space.getY() + local_y));
        if (map.blocked(this, this.field_342, this.field_343)) {
          return false;
        }
        local_y += 0.1F;
        if ((local_y <= space.getHeight()) || (doney)) {
          break label29;
        }
        local_y = space.getHeight();
      }
      local_x += 0.1F;
      if ((local_x > space.getWidth()) && (!donex)) {
        local_x = space.getWidth();
      }
    }
    return true;
  }
  
  private void subsection(TileBasedMap map, Space space, ArrayList spaces)
  {
    if (!clear(map, space))
    {
      float width2 = space.getWidth() / 2.0F;
      float height2 = space.getHeight() / 2.0F;
      if ((width2 < this.smallestSpace) && (height2 < this.smallestSpace)) {
        return;
      }
      subsection(map, new Space(space.getX(), space.getY(), width2, height2), spaces);
      subsection(map, new Space(space.getX(), space.getY() + height2, width2, height2), spaces);
      subsection(map, new Space(space.getX() + width2, space.getY(), width2, height2), spaces);
      subsection(map, new Space(space.getX() + width2, space.getY() + height2, width2, height2), spaces);
    }
    else
    {
      spaces.add(space);
    }
  }
  
  public Mover getMover()
  {
    return null;
  }
  
  public int getSearchDistance()
  {
    return 0;
  }
  
  public int getSourceX()
  {
    return this.field_342;
  }
  
  public int getSourceY()
  {
    return this.field_343;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.pathfinding.navmesh.NavMeshBuilder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */