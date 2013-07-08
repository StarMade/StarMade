package org.schema.game.common.data.physics;

import class_886;
import class_888;
import org.schema.game.common.data.world.Segment;

class CubeCubeCollisionAlgorithm$OuterSegmentHandler
  implements class_888
{
  private CubeShape cubeShape1;
  private CubeShape cubeShape0;
  
  private CubeCubeCollisionAlgorithm$OuterSegmentHandler(CubeCubeCollisionAlgorithm paramCubeCubeCollisionAlgorithm) {}
  
  public boolean handle(Segment paramSegment)
  {
    CubeCubeCollisionAlgorithm.access$008(this.this$0);
    if ((paramSegment.a16() == null) || (paramSegment.g())) {
      return true;
    }
    this.cubeShape0.setMargin(CubeCubeCollisionAlgorithm.margin);
    this.cubeShape0.getSegmentAabb(paramSegment, this.this$0.field_264.tmpTrans1, this.this$0.field_264.outOuterMin, this.this$0.field_264.outOuterMax, this.this$0.field_264.localMinOut, this.this$0.field_264.localMaxOut, this.this$0.field_264.aabbVarSet);
    CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$302(CubeCubeCollisionAlgorithm.access$200(this.this$0), paramSegment);
    try
    {
      this.cubeShape1.getSegmentBuffer().b4(CubeCubeCollisionAlgorithm.access$200(this.this$0), this.this$0.field_264.minIntB, this.this$0.field_264.maxIntB);
    }
    catch (Exception localException)
    {
      localException;
    }
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CubeCubeCollisionAlgorithm.OuterSegmentHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */