/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import jM;
/*    */ import java.io.PrintStream;
/*    */ import org.schema.game.common.data.world.Segment;
/*    */ 
/*    */ class SubsimplexRayCubesCovexCast$OuterSegmentIterator
/*    */   implements jM
/*    */ {
/*    */   private Transform fromA;
/*    */   private Transform toA;
/*    */   private Transform testCubes;
/*    */   private Transform toCubes;
/*    */   private ConvexCast.CastResult result;
/*    */   private boolean hitSignal;
/*    */ 
/*    */   private SubsimplexRayCubesCovexCast$OuterSegmentIterator(SubsimplexRayCubesCovexCast paramSubsimplexRayCubesCovexCast)
/*    */   {
/*    */   }
/*    */ 
/*    */   public boolean handle(Segment paramSegment)
/*    */   {
/* 57 */     if (SubsimplexRayCubesCovexCast.debug) {
/* 58 */       System.err.println("CHECKING SEGMENT " + paramSegment);
/*    */     }
/*    */ 
/* 61 */     SubsimplexRayCubesCovexCast.access$000(this.this$0, paramSegment, this.fromA, this.toA, this.testCubes, this.toCubes, this.result);
/*    */ 
/* 64 */     if (SubsimplexRayCubesCovexCast.access$100(this.this$0).hasHit()) {
/* 65 */       this.hitSignal = true;
/* 66 */       return false;
/*    */     }
/* 68 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.SubsimplexRayCubesCovexCast.OuterSegmentIterator
 * JD-Core Version:    0.6.2
 */