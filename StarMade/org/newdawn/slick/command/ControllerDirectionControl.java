/*    */ package org.newdawn.slick.command;
/*    */ 
/*    */ public class ControllerDirectionControl extends ControllerControl
/*    */ {
/* 11 */   public static final Direction LEFT = new Direction(1);
/*    */ 
/* 13 */   public static final Direction UP = new Direction(3);
/*    */ 
/* 15 */   public static final Direction DOWN = new Direction(4);
/*    */ 
/* 17 */   public static final Direction RIGHT = new Direction(2);
/*    */ 
/*    */   public ControllerDirectionControl(int controllerIndex, Direction dir)
/*    */   {
/* 26 */     super(controllerIndex, dir.event, 0);
/*    */   }
/*    */ 
/*    */   private static class Direction
/*    */   {
/*    */     private int event;
/*    */ 
/*    */     public Direction(int event)
/*    */     {
/* 44 */       this.event = event;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.command.ControllerDirectionControl
 * JD-Core Version:    0.6.2
 */