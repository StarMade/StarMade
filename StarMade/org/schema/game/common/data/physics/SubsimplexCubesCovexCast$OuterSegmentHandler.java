/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*   4:    */import com.bulletphysics.linearmath.AabbUtil2;
/*   5:    */import com.bulletphysics.linearmath.Transform;
/*   6:    */import com.bulletphysics.util.ObjectPool;
/*   7:    */import it.unimi.dsi.fastutil.floats.Float2ObjectAVLTreeMap;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   9:    */import jM;
/*  10:    */import java.util.Iterator;
/*  11:    */import javax.vecmath.Vector3f;
/*  12:    */import org.schema.game.common.data.world.Segment;
/*  13:    */import s;
/*  14:    */
/*  57:    */class SubsimplexCubesCovexCast$OuterSegmentHandler
/*  58:    */  implements jM
/*  59:    */{
/*  60:    */  private Transform fromA;
/*  61:    */  private Transform toA;
/*  62:    */  private Transform testCubes;
/*  63:    */  private ConvexCast.CastResult result;
/*  64:    */  private boolean hitSignal;
/*  65:    */  
/*  66:    */  private SubsimplexCubesCovexCast$OuterSegmentHandler(SubsimplexCubesCovexCast paramSubsimplexCubesCovexCast) {}
/*  67:    */  
/*  68:    */  public boolean handle(Segment paramSegment)
/*  69:    */  {
/*  70: 70 */    if ((paramSegment.a() == null) || (paramSegment.g())) {
/*  71: 71 */      return true;
/*  72:    */    }
/*  73: 73 */    SubsimplexCubesCovexCast.access$000(this.this$0).cubesB.getSegmentAabb(paramSegment, this.testCubes, SubsimplexCubesCovexCast.access$000(this.this$0).outMin, SubsimplexCubesCovexCast.access$000(this.this$0).outMax, SubsimplexCubesCovexCast.access$000(this.this$0).localMinOut, SubsimplexCubesCovexCast.access$000(this.this$0).localMaxOut, SubsimplexCubesCovexCast.access$000(this.this$0).aabbVarSet);
/*  74: 74 */    SubsimplexCubesCovexCast.access$000(this.this$0).hitLambda[0] = 1.0F;
/*  75: 75 */    SubsimplexCubesCovexCast.access$000(this.this$0).normal.set(0.0F, 0.0F, 0.0F);
/*  76:    */    
/*  83: 83 */    if (AabbUtil2.testAabbAgainstAabb2(SubsimplexCubesCovexCast.access$000(this.this$0).outMin, SubsimplexCubesCovexCast.access$000(this.this$0).outMax, SubsimplexCubesCovexCast.access$000(this.this$0).castedAABBMin, SubsimplexCubesCovexCast.access$000(this.this$0).castedAABBMax))
/*  84:    */    {
/*  85: 85 */      paramSegment = SubsimplexCubesCovexCast.access$100(this.this$0, paramSegment, SubsimplexCubesCovexCast.access$000(this.this$0).shapeA, this.fromA, this.toA, this.testCubes, this.result);
/*  86:    */      
/*  87: 87 */      for (Iterator localIterator = SubsimplexCubesCovexCast.access$000(this.this$0).sorted.values().iterator(); localIterator.hasNext();) { localObject = (s)localIterator.next();
/*  88: 88 */        this.this$0.pool4.release(localObject); }
/*  89:    */      Object localObject;
/*  90: 90 */      SubsimplexCubesCovexCast.access$000(this.this$0).sorted.clear();
/*  91:    */      
/*  92: 92 */      for (localIterator = SubsimplexCubesCovexCast.access$000(this.this$0).sortedAABB.values().iterator(); localIterator.hasNext();) { localObject = (AABBb)localIterator.next();
/*  93: 93 */        this.this$0.pool.release(((AABBb)localObject).min);
/*  94: 94 */        this.this$0.pool.release(((AABBb)localObject).max);
/*  95: 95 */        this.this$0.aabbpool.release(localObject);
/*  96:    */      }
/*  97: 97 */      SubsimplexCubesCovexCast.access$000(this.this$0).sortedAABB.clear();
/*  98:    */      
/*  99: 99 */      if (paramSegment != 0)
/* 100:    */      {
/* 111:111 */        this.hitSignal = true;
/* 112:112 */        if (((this.this$0.resultCallback instanceof ClosestConvexResultCallbackExt)) && 
/* 113:113 */          (((ClosestConvexResultCallbackExt)this.this$0.resultCallback).checkHasHitOnly)) {
/* 114:114 */          return false;
/* 115:    */        }
/* 116:    */      }
/* 117:    */    }
/* 118:    */    
/* 126:126 */    return true;
/* 127:    */  }
/* 128:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.SubsimplexCubesCovexCast.OuterSegmentHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */