package org.newdawn.slick.util.pathfinding.navmesh;

import java.util.ArrayList;

public class NavMesh
{
  private ArrayList spaces = new ArrayList();
  
  public NavMesh() {}
  
  public NavMesh(ArrayList spaces)
  {
    this.spaces.addAll(spaces);
  }
  
  public int getSpaceCount()
  {
    return this.spaces.size();
  }
  
  public Space getSpace(int index)
  {
    return (Space)this.spaces.get(index);
  }
  
  public void addSpace(Space space)
  {
    this.spaces.add(space);
  }
  
  public Space findSpace(float local_x, float local_y)
  {
    for (int local_i = 0; local_i < this.spaces.size(); local_i++)
    {
      Space space = getSpace(local_i);
      if (space.contains(local_x, local_y)) {
        return space;
      }
    }
    return null;
  }
  
  public NavPath findPath(float local_sx, float local_sy, float local_tx, float local_ty, boolean optimize)
  {
    Space source = findSpace(local_sx, local_sy);
    Space target = findSpace(local_tx, local_ty);
    if ((source == null) || (target == null)) {
      return null;
    }
    for (int local_i = 0; local_i < this.spaces.size(); local_i++) {
      ((Space)this.spaces.get(local_i)).clearCost();
    }
    target.fill(source, local_tx, local_ty, 0.0F);
    if (target.getCost() == 3.4028235E+38F) {
      return null;
    }
    if (source.getCost() == 3.4028235E+38F) {
      return null;
    }
    NavPath local_i = new NavPath();
    local_i.push(new Link(local_sx, local_sy, null));
    if (source.pickLowestCost(target, local_i))
    {
      local_i.push(new Link(local_tx, local_ty, null));
      if (optimize) {
        optimize(local_i);
      }
      return local_i;
    }
    return null;
  }
  
  private boolean isClear(float local_x1, float local_y1, float local_x2, float local_y2, float step)
  {
    float local_dx = local_x2 - local_x1;
    float local_dy = local_y2 - local_y1;
    float len = (float)Math.sqrt(local_dx * local_dx + local_dy * local_dy);
    local_dx *= step;
    local_dx /= len;
    local_dy *= step;
    local_dy /= len;
    int steps = (int)(len / step);
    for (int local_i = 0; local_i < steps; local_i++)
    {
      float local_x = local_x1 + local_dx * local_i;
      float local_y = local_y1 + local_dy * local_i;
      if (findSpace(local_x, local_y) == null) {
        return false;
      }
    }
    return true;
  }
  
  private void optimize(NavPath path)
  {
    int local_pt = 0;
    while (local_pt < path.length() - 2)
    {
      float local_sx = path.getX(local_pt);
      float local_sy = path.getY(local_pt);
      float local_nx = path.getX(local_pt + 2);
      float local_ny = path.getY(local_pt + 2);
      if (isClear(local_sx, local_sy, local_nx, local_ny, 0.1F)) {
        path.remove(local_pt + 1);
      } else {
        local_pt++;
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.pathfinding.navmesh.NavMesh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */