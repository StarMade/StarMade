/*    */ package org.newdawn.slick.util.pathfinding.navmesh;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class NavPath
/*    */ {
/* 12 */   private ArrayList links = new ArrayList();
/*    */ 
/*    */   public void push(Link link)
/*    */   {
/* 26 */     this.links.add(link);
/*    */   }
/*    */ 
/*    */   public int length()
/*    */   {
/* 35 */     return this.links.size();
/*    */   }
/*    */ 
/*    */   public float getX(int step)
/*    */   {
/* 45 */     return ((Link)this.links.get(step)).getX();
/*    */   }
/*    */ 
/*    */   public float getY(int step)
/*    */   {
/* 55 */     return ((Link)this.links.get(step)).getY();
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 64 */     return "[Path length=" + length() + "]";
/*    */   }
/*    */ 
/*    */   public void remove(int i)
/*    */   {
/* 73 */     this.links.remove(i);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.pathfinding.navmesh.NavPath
 * JD-Core Version:    0.6.2
 */