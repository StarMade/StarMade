package org.newdawn.slick.util.pathfinding.heuristics;

import org.newdawn.slick.util.pathfinding.AStarHeuristic;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class ClosestSquaredHeuristic
  implements AStarHeuristic
{
  public float getCost(TileBasedMap map, Mover mover, int local_x, int local_y, int local_tx, int local_ty)
  {
    float local_dx = local_tx - local_x;
    float local_dy = local_ty - local_y;
    return local_dx * local_dx + local_dy * local_dy;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.pathfinding.heuristics.ClosestSquaredHeuristic
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */