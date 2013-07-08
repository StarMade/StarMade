/*  1:   */package org.schema.game.common.controller;
/*  2:   */
/*  4:   */public class SegmentOutOfBoundsException
/*  5:   */  extends Exception
/*  6:   */{
/*  7:   */  private static final long serialVersionUID = -5005376956760724252L;
/*  8:   */  
/* 10:   */  public SegmentOutOfBoundsException(int paramInt1, int paramInt2, int paramInt3, SegmentController paramSegmentController)
/* 11:   */  {
/* 12:12 */    super("segment out of bounds: " + paramInt1 + ", " + paramInt2 + ", " + paramInt3 + "; [16x -> " + paramSegmentController.getMinPos() + " - " + paramSegmentController.getMaxPos() + "] on " + paramSegmentController + " (" + paramSegmentController.getState() + ")");
/* 13:   */  }
/* 14:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.SegmentOutOfBoundsException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */