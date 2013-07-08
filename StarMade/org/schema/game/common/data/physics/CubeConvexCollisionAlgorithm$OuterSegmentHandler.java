/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*   4:    */import com.bulletphysics.collision.dispatch.CollisionObject;
/*   5:    */import com.bulletphysics.collision.dispatch.ManifoldResult;
/*   6:    */import com.bulletphysics.linearmath.AabbUtil2;
/*   7:    */import jM;
/*   8:    */import org.schema.game.common.data.world.Segment;
/*   9:    */import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*  10:    */
/*  89:    */class CubeConvexCollisionAlgorithm$OuterSegmentHandler
/*  90:    */  implements jM
/*  91:    */{
/*  92:    */  CubeShape cubeShape0;
/*  93:    */  CollisionObject col1;
/*  94:    */  DispatcherInfo dispatchInfo;
/*  95:    */  ManifoldResult resultOut;
/*  96:    */  
/*  97:    */  private CubeConvexCollisionAlgorithm$OuterSegmentHandler(CubeConvexCollisionAlgorithm paramCubeConvexCollisionAlgorithm) {}
/*  98:    */  
/*  99:    */  public boolean handle(Segment paramSegment)
/* 100:    */  {
/* 101:101 */    if ((paramSegment.a() == null) || (paramSegment.g())) {
/* 102:102 */      return true;
/* 103:    */    }
/* 104:104 */    this.cubeShape0.getSegmentAabb(paramSegment, CubeConvexCollisionAlgorithm.access$000(this.this$0).cubeMeshTransform, CubeConvexCollisionAlgorithm.access$000(this.this$0).outMin, CubeConvexCollisionAlgorithm.access$000(this.this$0).outMax, CubeConvexCollisionAlgorithm.access$000(this.this$0).localMinOut, CubeConvexCollisionAlgorithm.access$000(this.this$0).localMaxOut, CubeConvexCollisionAlgorithm.access$000(this.this$0).aabbVarSet);
/* 105:    */    
/* 113:113 */    if (AabbUtil2.testAabbAgainstAabb2(CubeConvexCollisionAlgorithm.access$000(this.this$0).shapeMin, CubeConvexCollisionAlgorithm.access$000(this.this$0).shapeMax, CubeConvexCollisionAlgorithm.access$000(this.this$0).outMin, CubeConvexCollisionAlgorithm.access$000(this.this$0).outMax))
/* 114:    */    {
/* 115:    */      try
/* 116:    */      {
/* 117:117 */        this.this$0.processDistinctCollision(this.cubeShape0, this.col1, paramSegment.a(), CubeConvexCollisionAlgorithm.access$000(this.this$0).cubeMeshTransform, CubeConvexCollisionAlgorithm.access$000(this.this$0).convexShapeTransform, this.dispatchInfo, this.resultOut);
/* 118:    */      }
/* 119:    */      catch (ErrorDialogException localErrorDialogException) {
/* 120:120 */        
/* 121:    */        
/* 124:124 */          localErrorDialogException;
/* 125:    */      }
/* 126:    */    }
/* 127:    */    
/* 131:127 */    return true;
/* 132:    */  }
/* 133:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeConvexCollisionAlgorithm.OuterSegmentHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */