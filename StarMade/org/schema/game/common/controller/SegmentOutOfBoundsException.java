package org.schema.game.common.controller;

public class SegmentOutOfBoundsException
  extends Exception
{
  private static final long serialVersionUID = -5005376956760724252L;
  
  public SegmentOutOfBoundsException(int paramInt1, int paramInt2, int paramInt3, SegmentController paramSegmentController)
  {
    super("segment out of bounds: " + paramInt1 + ", " + paramInt2 + ", " + paramInt3 + "; [16x -> " + paramSegmentController.getMinPos() + " - " + paramSegmentController.getMaxPos() + "] on " + paramSegmentController + " (" + paramSegmentController.getState() + ")");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.SegmentOutOfBoundsException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */