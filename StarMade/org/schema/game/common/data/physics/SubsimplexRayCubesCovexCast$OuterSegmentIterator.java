package org.schema.game.common.data.physics;

import class_888;
import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import org.schema.game.common.data.world.Segment;

class SubsimplexRayCubesCovexCast$OuterSegmentIterator
  implements class_888
{
  private Transform fromA;
  private Transform toA;
  private Transform testCubes;
  private Transform toCubes;
  private ConvexCast.CastResult result;
  private boolean hitSignal;
  
  private SubsimplexRayCubesCovexCast$OuterSegmentIterator(SubsimplexRayCubesCovexCast paramSubsimplexRayCubesCovexCast) {}
  
  public boolean handle(Segment paramSegment)
  {
    if (SubsimplexRayCubesCovexCast.debug) {
      System.err.println("CHECKING SEGMENT " + paramSegment);
    }
    SubsimplexRayCubesCovexCast.access$000(this.this$0, paramSegment, this.fromA, this.toA, this.testCubes, this.toCubes, this.result);
    if (SubsimplexRayCubesCovexCast.access$100(this.this$0).hasHit())
    {
      this.hitSignal = true;
      return false;
    }
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.SubsimplexRayCubesCovexCast.OuterSegmentIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */