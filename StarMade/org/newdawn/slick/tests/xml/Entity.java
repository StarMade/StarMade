/*    */ package org.newdawn.slick.tests.xml;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class Entity
/*    */ {
/*    */   private float x;
/*    */   private float y;
/*    */   private Inventory invent;
/*    */   private Stats stats;
/*    */ 
/*    */   private void add(Inventory inventory)
/*    */   {
/* 24 */     this.invent = inventory;
/*    */   }
/*    */ 
/*    */   private void add(Stats stats)
/*    */   {
/* 33 */     this.stats = stats;
/*    */   }
/*    */ 
/*    */   public void dump(String prefix)
/*    */   {
/* 42 */     System.out.println(prefix + "Entity " + this.x + "," + this.y);
/* 43 */     this.invent.dump(prefix + "\t");
/* 44 */     this.stats.dump(prefix + "\t");
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.xml.Entity
 * JD-Core Version:    0.6.2
 */