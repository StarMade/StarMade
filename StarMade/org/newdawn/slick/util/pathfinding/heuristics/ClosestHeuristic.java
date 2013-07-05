/*    */ package org.newdawn.slick.util.pathfinding.heuristics;
/*    */ 
/*    */ import org.newdawn.slick.util.pathfinding.AStarHeuristic;
/*    */ import org.newdawn.slick.util.pathfinding.Mover;
/*    */ import org.newdawn.slick.util.pathfinding.TileBasedMap;
/*    */ 
/*    */ public class ClosestHeuristic
/*    */   implements AStarHeuristic
/*    */ {
/*    */   public float getCost(TileBasedMap map, Mover mover, int x, int y, int tx, int ty)
/*    */   {
/* 18 */     float dx = tx - x;
/* 19 */     float dy = ty - y;
/*    */ 
/* 21 */     float result = (float)Math.sqrt(dx * dx + dy * dy);
/*    */ 
/* 23 */     return result;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.pathfinding.heuristics.ClosestHeuristic
 * JD-Core Version:    0.6.2
 */