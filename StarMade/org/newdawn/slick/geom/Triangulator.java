package org.newdawn.slick.geom;

import java.io.Serializable;

public abstract interface Triangulator
  extends Serializable
{
  public abstract int getTriangleCount();
  
  public abstract float[] getTrianglePoint(int paramInt1, int paramInt2);
  
  public abstract void addPolyPoint(float paramFloat1, float paramFloat2);
  
  public abstract void startHole();
  
  public abstract boolean triangulate();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.Triangulator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */