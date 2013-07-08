package org.schema.game.common.data.physics;

import class_46;
import class_888;
import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectPool;
import it.unimi.dsi.fastutil.floats.Float2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.util.Iterator;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.world.Segment;

class SubsimplexCubesCovexCast$OuterSegmentHandler
  implements class_888
{
  private Transform fromA;
  private Transform toA;
  private Transform testCubes;
  private ConvexCast.CastResult result;
  private boolean hitSignal;
  
  private SubsimplexCubesCovexCast$OuterSegmentHandler(SubsimplexCubesCovexCast paramSubsimplexCubesCovexCast) {}
  
  public boolean handle(Segment paramSegment)
  {
    if ((paramSegment.a16() == null) || (paramSegment.g())) {
      return true;
    }
    SubsimplexCubesCovexCast.access$000(this.this$0).cubesB.getSegmentAabb(paramSegment, this.testCubes, SubsimplexCubesCovexCast.access$000(this.this$0).outMin, SubsimplexCubesCovexCast.access$000(this.this$0).outMax, SubsimplexCubesCovexCast.access$000(this.this$0).localMinOut, SubsimplexCubesCovexCast.access$000(this.this$0).localMaxOut, SubsimplexCubesCovexCast.access$000(this.this$0).aabbVarSet);
    SubsimplexCubesCovexCast.access$000(this.this$0).hitLambda[0] = 1.0F;
    SubsimplexCubesCovexCast.access$000(this.this$0).normal.set(0.0F, 0.0F, 0.0F);
    if (AabbUtil2.testAabbAgainstAabb2(SubsimplexCubesCovexCast.access$000(this.this$0).outMin, SubsimplexCubesCovexCast.access$000(this.this$0).outMax, SubsimplexCubesCovexCast.access$000(this.this$0).castedAABBMin, SubsimplexCubesCovexCast.access$000(this.this$0).castedAABBMax))
    {
      paramSegment = SubsimplexCubesCovexCast.access$100(this.this$0, paramSegment, SubsimplexCubesCovexCast.access$000(this.this$0).shapeA, this.fromA, this.toA, this.testCubes, this.result);
      Iterator localIterator = SubsimplexCubesCovexCast.access$000(this.this$0).sorted.values().iterator();
      Object localObject;
      while (localIterator.hasNext())
      {
        localObject = (class_46)localIterator.next();
        this.this$0.pool4.release(localObject);
      }
      SubsimplexCubesCovexCast.access$000(this.this$0).sorted.clear();
      localIterator = SubsimplexCubesCovexCast.access$000(this.this$0).sortedAABB.values().iterator();
      while (localIterator.hasNext())
      {
        localObject = (AABBb)localIterator.next();
        this.this$0.pool.release(((AABBb)localObject).min);
        this.this$0.pool.release(((AABBb)localObject).max);
        this.this$0.aabbpool.release(localObject);
      }
      SubsimplexCubesCovexCast.access$000(this.this$0).sortedAABB.clear();
      if (paramSegment != 0)
      {
        this.hitSignal = true;
        if (((this.this$0.resultCallback instanceof ClosestConvexResultCallbackExt)) && (((ClosestConvexResultCallbackExt)this.this$0.resultCallback).checkHasHitOnly)) {
          return false;
        }
      }
    }
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.SubsimplexCubesCovexCast.OuterSegmentHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */