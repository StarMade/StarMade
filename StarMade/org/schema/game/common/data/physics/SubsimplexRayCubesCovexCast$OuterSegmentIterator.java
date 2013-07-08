/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*  4:   */import com.bulletphysics.linearmath.Transform;
/*  5:   */import jM;
/*  6:   */import java.io.PrintStream;
/*  7:   */import org.schema.game.common.data.world.Segment;
/*  8:   */
/* 43:   */class SubsimplexRayCubesCovexCast$OuterSegmentIterator
/* 44:   */  implements jM
/* 45:   */{
/* 46:   */  private Transform fromA;
/* 47:   */  private Transform toA;
/* 48:   */  private Transform testCubes;
/* 49:   */  private Transform toCubes;
/* 50:   */  private ConvexCast.CastResult result;
/* 51:   */  private boolean hitSignal;
/* 52:   */  
/* 53:   */  private SubsimplexRayCubesCovexCast$OuterSegmentIterator(SubsimplexRayCubesCovexCast paramSubsimplexRayCubesCovexCast) {}
/* 54:   */  
/* 55:   */  public boolean handle(Segment paramSegment)
/* 56:   */  {
/* 57:57 */    if (SubsimplexRayCubesCovexCast.debug) {
/* 58:58 */      System.err.println("CHECKING SEGMENT " + paramSegment);
/* 59:   */    }
/* 60:   */    
/* 61:61 */    SubsimplexRayCubesCovexCast.access$000(this.this$0, paramSegment, this.fromA, this.toA, this.testCubes, this.toCubes, this.result);
/* 62:   */    
/* 64:64 */    if (SubsimplexRayCubesCovexCast.access$100(this.this$0).hasHit()) {
/* 65:65 */      this.hitSignal = true;
/* 66:66 */      return false;
/* 67:   */    }
/* 68:68 */    return true;
/* 69:   */  }
/* 70:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.SubsimplexRayCubesCovexCast.OuterSegmentIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */