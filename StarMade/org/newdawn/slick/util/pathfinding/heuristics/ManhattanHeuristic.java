package org.newdawn.slick.util.pathfinding.heuristics;

import org.newdawn.slick.util.pathfinding.AStarHeuristic;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class ManhattanHeuristic
  implements AStarHeuristic
{
  private int minimumCost;
  
  public ManhattanHeuristic(int minimumCost)
  {
    this.minimumCost = minimumCost;
  }
  
  public float getCost(TileBasedMap map, Mover mover, int local_x, int local_y, int local_tx, int local_ty)
  {
    return this.minimumCost * (Math.abs(local_x - local_tx) + Math.abs(local_y - local_ty));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.pathfinding.heuristics.ManhattanHeuristic
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */