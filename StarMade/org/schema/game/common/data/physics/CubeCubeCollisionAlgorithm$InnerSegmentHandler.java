/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*   4:    */import com.bulletphysics.collision.dispatch.ManifoldResult;
/*   5:    */import com.bulletphysics.linearmath.AabbUtil2;
/*   6:    */import jL;
/*   7:    */import jM;
/*   8:    */import org.schema.game.common.data.world.Segment;
/*   9:    */import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*  10:    */import xO;
/*  11:    */
/* 101:    */class CubeCubeCollisionAlgorithm$InnerSegmentHandler
/* 102:    */  implements jM
/* 103:    */{
/* 104:    */  private CubeShape cubeShape1;
/* 105:    */  private CubeShape cubeShape0;
/* 106:    */  private int distinctTests;
/* 107:    */  private long distinctTime;
/* 108:    */  private DispatcherInfo dispatchInfo;
/* 109:    */  private ManifoldResult resultOut;
/* 110:    */  private Segment sOuter;
/* 111:    */  
/* 112:    */  private CubeCubeCollisionAlgorithm$InnerSegmentHandler(CubeCubeCollisionAlgorithm paramCubeCubeCollisionAlgorithm) {}
/* 113:    */  
/* 114:    */  public boolean handle(Segment paramSegment)
/* 115:    */  {
/* 116:116 */    CubeCubeCollisionAlgorithm.access$008(this.this$0);
/* 117:117 */    if ((paramSegment.a() == null) || (paramSegment.g()))
/* 118:    */    {
/* 120:120 */      return true;
/* 121:    */    }
/* 122:122 */    assert (paramSegment.a() != this.sOuter.a()) : (paramSegment.a() + "; " + this.sOuter.a());
/* 123:123 */    if (paramSegment.a() == this.sOuter.a())
/* 124:    */    {
/* 125:125 */      return false;
/* 126:    */    }
/* 127:127 */    assert (paramSegment.a() == this.cubeShape1.getSegmentBuffer().a());
/* 128:    */    
/* 129:129 */    this.cubeShape1.setMargin(CubeCubeCollisionAlgorithm.margin);
/* 130:130 */    this.cubeShape1.getSegmentAabb(paramSegment, this.this$0.v.tmpTrans2, this.this$0.v.outInnerMin, this.this$0.v.outInnerMax, this.this$0.v.localMinOut, this.this$0.v.localMaxOut, this.this$0.v.aabbVarSet);
/* 131:    */    
/* 138:138 */    if (AabbUtil2.testAabbAgainstAabb2(this.this$0.v.outInnerMin, this.this$0.v.outInnerMax, this.this$0.v.outOuterMin, this.this$0.v.outOuterMax))
/* 139:    */    {
/* 140:140 */      this.this$0.v.bbOuterSeg.b(this.this$0.v.outOuterMin, this.this$0.v.outOuterMax);
/* 141:141 */      this.this$0.v.bbInnerSeg.b(this.this$0.v.outInnerMin, this.this$0.v.outInnerMax);
/* 142:    */      
/* 143:143 */      this.this$0.v.bbOuterSeg.a(this.this$0.v.bbInnerSeg, this.this$0.v.bbSectorIntersection);
/* 144:    */      
/* 150:150 */      this.distinctTests += 1;
/* 151:    */      try {
/* 152:152 */        long l = System.nanoTime();
/* 153:153 */        CubeCubeCollisionAlgorithm.access$100(this.this$0, this.cubeShape0, this.cubeShape1, this.sOuter.a(), paramSegment.a(), this.this$0.v.tmpTrans1, this.this$0.v.tmpTrans2, this.dispatchInfo, this.resultOut);
/* 154:    */        
/* 157:157 */        this.distinctTime += System.nanoTime() - l;
/* 158:158 */      } catch (ErrorDialogException localErrorDialogException) { 
/* 159:    */        
/* 162:162 */          localErrorDialogException;
/* 163:    */      }
/* 164:    */    }
/* 165:    */    
/* 168:164 */    return true;
/* 169:    */  }
/* 170:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeCubeCollisionAlgorithm.InnerSegmentHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */