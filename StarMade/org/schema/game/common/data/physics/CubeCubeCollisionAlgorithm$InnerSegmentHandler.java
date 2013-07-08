package org.schema.game.common.data.physics;

import class_886;
import class_888;
import class_988;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.dispatch.ManifoldResult;
import com.bulletphysics.linearmath.AabbUtil2;
import org.schema.game.common.data.world.Segment;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;

class CubeCubeCollisionAlgorithm$InnerSegmentHandler
  implements class_888
{
  private CubeShape cubeShape1;
  private CubeShape cubeShape0;
  private int distinctTests;
  private long distinctTime;
  private DispatcherInfo dispatchInfo;
  private ManifoldResult resultOut;
  private Segment sOuter;
  
  private CubeCubeCollisionAlgorithm$InnerSegmentHandler(CubeCubeCollisionAlgorithm paramCubeCubeCollisionAlgorithm) {}
  
  public boolean handle(Segment paramSegment)
  {
    CubeCubeCollisionAlgorithm.access$008(this.this$0);
    if ((paramSegment.a16() == null) || (paramSegment.g())) {
      return true;
    }
    assert (paramSegment.a15() != this.sOuter.a15()) : (paramSegment.a15() + "; " + this.sOuter.a15());
    if (paramSegment.a15() == this.sOuter.a15()) {
      return false;
    }
    assert (paramSegment.a15() == this.cubeShape1.getSegmentBuffer().a12());
    this.cubeShape1.setMargin(CubeCubeCollisionAlgorithm.margin);
    this.cubeShape1.getSegmentAabb(paramSegment, this.this$0.field_264.tmpTrans2, this.this$0.field_264.outInnerMin, this.this$0.field_264.outInnerMax, this.this$0.field_264.localMinOut, this.this$0.field_264.localMaxOut, this.this$0.field_264.aabbVarSet);
    if (AabbUtil2.testAabbAgainstAabb2(this.this$0.field_264.outInnerMin, this.this$0.field_264.outInnerMax, this.this$0.field_264.outOuterMin, this.this$0.field_264.outOuterMax))
    {
      this.this$0.field_264.bbOuterSeg.b2(this.this$0.field_264.outOuterMin, this.this$0.field_264.outOuterMax);
      this.this$0.field_264.bbInnerSeg.b2(this.this$0.field_264.outInnerMin, this.this$0.field_264.outInnerMax);
      this.this$0.field_264.bbOuterSeg.a5(this.this$0.field_264.bbInnerSeg, this.this$0.field_264.bbSectorIntersection);
      this.distinctTests += 1;
      try
      {
        long l = System.nanoTime();
        CubeCubeCollisionAlgorithm.access$100(this.this$0, this.cubeShape0, this.cubeShape1, this.sOuter.a16(), paramSegment.a16(), this.this$0.field_264.tmpTrans1, this.this$0.field_264.tmpTrans2, this.dispatchInfo, this.resultOut);
        this.distinctTime += System.nanoTime() - l;
      }
      catch (ErrorDialogException localErrorDialogException)
      {
        localErrorDialogException;
      }
    }
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CubeCubeCollisionAlgorithm.InnerSegmentHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */