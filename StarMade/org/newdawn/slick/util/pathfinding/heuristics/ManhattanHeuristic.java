/*  1:   */package org.newdawn.slick.util.pathfinding.heuristics;
/*  2:   */
/*  3:   */import org.newdawn.slick.util.pathfinding.AStarHeuristic;
/*  4:   */import org.newdawn.slick.util.pathfinding.Mover;
/*  5:   */import org.newdawn.slick.util.pathfinding.TileBasedMap;
/*  6:   */
/* 16:   */public class ManhattanHeuristic
/* 17:   */  implements AStarHeuristic
/* 18:   */{
/* 19:   */  private int minimumCost;
/* 20:   */  
/* 21:   */  public ManhattanHeuristic(int minimumCost)
/* 22:   */  {
/* 23:23 */    this.minimumCost = minimumCost;
/* 24:   */  }
/* 25:   */  
/* 29:   */  public float getCost(TileBasedMap map, Mover mover, int x, int y, int tx, int ty)
/* 30:   */  {
/* 31:31 */    return this.minimumCost * (Math.abs(x - tx) + Math.abs(y - ty));
/* 32:   */  }
/* 33:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.pathfinding.heuristics.ManhattanHeuristic
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */