/*  1:   */package org.newdawn.slick.util.pathfinding.heuristics;
/*  2:   */
/*  3:   */import org.newdawn.slick.util.pathfinding.AStarHeuristic;
/*  4:   */import org.newdawn.slick.util.pathfinding.Mover;
/*  5:   */import org.newdawn.slick.util.pathfinding.TileBasedMap;
/*  6:   */
/* 15:   */public class ClosestSquaredHeuristic
/* 16:   */  implements AStarHeuristic
/* 17:   */{
/* 18:   */  public float getCost(TileBasedMap map, Mover mover, int x, int y, int tx, int ty)
/* 19:   */  {
/* 20:20 */    float dx = tx - x;
/* 21:21 */    float dy = ty - y;
/* 22:   */    
/* 23:23 */    return dx * dx + dy * dy;
/* 24:   */  }
/* 25:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.pathfinding.heuristics.ClosestSquaredHeuristic
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */