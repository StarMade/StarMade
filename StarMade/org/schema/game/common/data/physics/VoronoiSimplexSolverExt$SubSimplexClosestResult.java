/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */
/* 713:    */public class VoronoiSimplexSolverExt$SubSimplexClosestResult
/* 714:    */{
/* 715:715 */  public final Vector3f closestPointOnSimplex = new Vector3f();
/* 716:    */  
/* 719:719 */  public final VoronoiSimplexSolverExt.UsageBitfield usedVertices = new VoronoiSimplexSolverExt.UsageBitfield();
/* 720:720 */  public final float[] barycentricCoords = new float[4];
/* 721:    */  public boolean degenerate;
/* 722:    */  
/* 723:    */  public void reset() {
/* 724:724 */    this.degenerate = false;
/* 725:725 */    setBarycentricCoordinates(0.0F, 0.0F, 0.0F, 0.0F);
/* 726:726 */    this.usedVertices.reset();
/* 727:    */  }
/* 728:    */  
/* 729:    */  public boolean isValid() {
/* 730:730 */    if ((this.barycentricCoords[0] >= 0.0F) && (this.barycentricCoords[1] >= 0.0F) && (this.barycentricCoords[2] >= 0.0F) && (this.barycentricCoords[3] >= 0.0F)) { return true;
/* 731:    */    }
/* 732:    */    
/* 734:734 */    return false;
/* 735:    */  }
/* 736:    */  
/* 737:    */  public void setBarycentricCoordinates(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/* 738:738 */    this.barycentricCoords[0] = paramFloat1;
/* 739:739 */    this.barycentricCoords[1] = paramFloat2;
/* 740:740 */    this.barycentricCoords[2] = paramFloat3;
/* 741:741 */    this.barycentricCoords[3] = paramFloat4;
/* 742:    */  }
/* 743:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.VoronoiSimplexSolverExt.SubSimplexClosestResult
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */