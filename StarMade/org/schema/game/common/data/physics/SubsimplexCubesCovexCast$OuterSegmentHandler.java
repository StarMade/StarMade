/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import it.unimi.dsi.fastutil.floats.Float2ObjectAVLTreeMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import jM;
/*     */ import java.util.Iterator;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import s;
/*     */ 
/*     */ class SubsimplexCubesCovexCast$OuterSegmentHandler
/*     */   implements jM
/*     */ {
/*     */   private Transform fromA;
/*     */   private Transform toA;
/*     */   private Transform testCubes;
/*     */   private ConvexCast.CastResult result;
/*     */   private boolean hitSignal;
/*     */ 
/*     */   private SubsimplexCubesCovexCast$OuterSegmentHandler(SubsimplexCubesCovexCast paramSubsimplexCubesCovexCast)
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean handle(Segment paramSegment)
/*     */   {
/*  70 */     if ((paramSegment.a() == null) || (paramSegment.g())) {
/*  71 */       return true;
/*     */     }
/*  73 */     SubsimplexCubesCovexCast.access$000(this.this$0).cubesB.getSegmentAabb(paramSegment, this.testCubes, SubsimplexCubesCovexCast.access$000(this.this$0).outMin, SubsimplexCubesCovexCast.access$000(this.this$0).outMax, SubsimplexCubesCovexCast.access$000(this.this$0).localMinOut, SubsimplexCubesCovexCast.access$000(this.this$0).localMaxOut, SubsimplexCubesCovexCast.access$000(this.this$0).aabbVarSet);
/*  74 */     SubsimplexCubesCovexCast.access$000(this.this$0).hitLambda[0] = 1.0F;
/*  75 */     SubsimplexCubesCovexCast.access$000(this.this$0).normal.set(0.0F, 0.0F, 0.0F);
/*     */ 
/*  83 */     if (AabbUtil2.testAabbAgainstAabb2(SubsimplexCubesCovexCast.access$000(this.this$0).outMin, SubsimplexCubesCovexCast.access$000(this.this$0).outMax, SubsimplexCubesCovexCast.access$000(this.this$0).castedAABBMin, SubsimplexCubesCovexCast.access$000(this.this$0).castedAABBMax))
/*     */     {
/*  85 */       paramSegment = SubsimplexCubesCovexCast.access$100(this.this$0, paramSegment, SubsimplexCubesCovexCast.access$000(this.this$0).shapeA, this.fromA, this.toA, this.testCubes, this.result);
/*     */ 
/*  87 */       for (Iterator localIterator = SubsimplexCubesCovexCast.access$000(this.this$0).sorted.values().iterator(); localIterator.hasNext(); ) { localObject = (s)localIterator.next();
/*  88 */         this.this$0.pool4.release(localObject);
/*     */       }
/*  92 */       Object localObject;
/*  90 */       SubsimplexCubesCovexCast.access$000(this.this$0).sorted.clear();
/*     */ 
/*  92 */       for (localIterator = SubsimplexCubesCovexCast.access$000(this.this$0).sortedAABB.values().iterator(); localIterator.hasNext(); ) { localObject = (AABBb)localIterator.next();
/*  93 */         this.this$0.pool.release(((AABBb)localObject).min);
/*  94 */         this.this$0.pool.release(((AABBb)localObject).max);
/*  95 */         this.this$0.aabbpool.release(localObject);
/*     */       }
/*  97 */       SubsimplexCubesCovexCast.access$000(this.this$0).sortedAABB.clear();
/*     */ 
/*  99 */       if (paramSegment != 0)
/*     */       {
/* 111 */         this.hitSignal = true;
/* 112 */         if (((this.this$0.resultCallback instanceof ClosestConvexResultCallbackExt)) && 
/* 113 */           (((ClosestConvexResultCallbackExt)this.this$0.resultCallback).checkHasHitOnly)) {
/* 114 */           return false;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 126 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.SubsimplexCubesCovexCast.OuterSegmentHandler
 * JD-Core Version:    0.6.2
 */