/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import jL;
/*   4:    */import jM;
/*   5:    */import org.schema.game.common.data.world.Segment;
/*   6:    */
/* 168:    */class CubeCubeCollisionAlgorithm$OuterSegmentHandler
/* 169:    */  implements jM
/* 170:    */{
/* 171:    */  private CubeShape cubeShape1;
/* 172:    */  private CubeShape cubeShape0;
/* 173:    */  
/* 174:    */  private CubeCubeCollisionAlgorithm$OuterSegmentHandler(CubeCubeCollisionAlgorithm paramCubeCubeCollisionAlgorithm) {}
/* 175:    */  
/* 176:    */  public boolean handle(Segment paramSegment)
/* 177:    */  {
/* 178:178 */    CubeCubeCollisionAlgorithm.access$008(this.this$0);
/* 179:179 */    if ((paramSegment.a() == null) || (paramSegment.g())) {
/* 180:180 */      return true;
/* 181:    */    }
/* 182:182 */    this.cubeShape0.setMargin(CubeCubeCollisionAlgorithm.margin);
/* 183:183 */    this.cubeShape0.getSegmentAabb(paramSegment, this.this$0.v.tmpTrans1, this.this$0.v.outOuterMin, this.this$0.v.outOuterMax, this.this$0.v.localMinOut, this.this$0.v.localMaxOut, this.this$0.v.aabbVarSet);
/* 184:    */    
/* 191:191 */    CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$302(CubeCubeCollisionAlgorithm.access$200(this.this$0), paramSegment);
/* 192:    */    try
/* 193:    */    {
/* 194:194 */      this.cubeShape1.getSegmentBuffer().b(CubeCubeCollisionAlgorithm.access$200(this.this$0), this.this$0.v.minIntB, this.this$0.v.maxIntB);
/* 195:195 */    } catch (Exception localException) { 
/* 196:    */      
/* 197:197 */        localException;
/* 198:    */    }
/* 199:    */    
/* 201:199 */    return true;
/* 202:    */  }
/* 203:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeCubeCollisionAlgorithm.OuterSegmentHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */