/*  1:   */package org.newdawn.slick.util.pathfinding.heuristics;
/*  2:   */
/*  3:   */import org.newdawn.slick.util.pathfinding.AStarHeuristic;
/*  4:   */import org.newdawn.slick.util.pathfinding.Mover;
/*  5:   */import org.newdawn.slick.util.pathfinding.TileBasedMap;
/*  6:   */
/* 13:   */public class ClosestHeuristic
/* 14:   */  implements AStarHeuristic
/* 15:   */{
/* 16:   */  public float getCost(TileBasedMap map, Mover mover, int x, int y, int tx, int ty)
/* 17:   */  {
/* 18:18 */    float dx = tx - x;
/* 19:19 */    float dy = ty - y;
/* 20:   */    
/* 21:21 */    float result = (float)Math.sqrt(dx * dx + dy * dy);
/* 22:   */    
/* 23:23 */    return result;
/* 24:   */  }
/* 25:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.pathfinding.heuristics.ClosestHeuristic
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */