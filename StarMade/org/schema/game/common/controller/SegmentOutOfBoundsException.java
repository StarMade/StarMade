/*    */ package org.schema.game.common.controller;
/*    */ 
/*    */ public class SegmentOutOfBoundsException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = -5005376956760724252L;
/*    */ 
/*    */   public SegmentOutOfBoundsException(int paramInt1, int paramInt2, int paramInt3, SegmentController paramSegmentController)
/*    */   {
/* 12 */     super("segment out of bounds: " + paramInt1 + ", " + paramInt2 + ", " + paramInt3 + "; [16x -> " + paramSegmentController.getMinPos() + " - " + paramSegmentController.getMaxPos() + "] on " + paramSegmentController + " (" + paramSegmentController.getState() + ")");
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.SegmentOutOfBoundsException
 * JD-Core Version:    0.6.2
 */