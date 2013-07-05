/*    */ package org.newdawn.slick.util.pathfinding.heuristics;
/*    */ 
/*    */ import org.newdawn.slick.util.pathfinding.AStarHeuristic;
/*    */ import org.newdawn.slick.util.pathfinding.Mover;
/*    */ import org.newdawn.slick.util.pathfinding.TileBasedMap;
/*    */ 
/*    */ public class ManhattanHeuristic
/*    */   implements AStarHeuristic
/*    */ {
/*    */   private int minimumCost;
/*    */ 
/*    */   public ManhattanHeuristic(int minimumCost)
/*    */   {
/* 23 */     this.minimumCost = minimumCost;
/*    */   }
/*    */ 
/*    */   public float getCost(TileBasedMap map, Mover mover, int x, int y, int tx, int ty)
/*    */   {
/* 31 */     return this.minimumCost * (Math.abs(x - tx) + Math.abs(y - ty));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.pathfinding.heuristics.ManhattanHeuristic
 * JD-Core Version:    0.6.2
 */