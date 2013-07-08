package org.schema.game.common.data.physics;

import javax.vecmath.Vector3f;

public class VoronoiSimplexSolverExt$SubSimplexClosestResult
{
  public final Vector3f closestPointOnSimplex = new Vector3f();
  public final VoronoiSimplexSolverExt.UsageBitfield usedVertices = new VoronoiSimplexSolverExt.UsageBitfield();
  public final float[] barycentricCoords = new float[4];
  public boolean degenerate;
  
  public void reset()
  {
    this.degenerate = false;
    setBarycentricCoordinates(0.0F, 0.0F, 0.0F, 0.0F);
    this.usedVertices.reset();
  }
  
  public boolean isValid()
  {
    return (this.barycentricCoords[0] >= 0.0F) && (this.barycentricCoords[1] >= 0.0F) && (this.barycentricCoords[2] >= 0.0F) && (this.barycentricCoords[3] >= 0.0F);
  }
  
  public void setBarycentricCoordinates(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this.barycentricCoords[0] = paramFloat1;
    this.barycentricCoords[1] = paramFloat2;
    this.barycentricCoords[2] = paramFloat3;
    this.barycentricCoords[3] = paramFloat4;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.VoronoiSimplexSolverExt.SubSimplexClosestResult
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */