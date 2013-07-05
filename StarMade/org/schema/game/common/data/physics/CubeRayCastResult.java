/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*    */ import javax.vecmath.Vector3f;
/*    */ import o;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.data.world.Segment;
/*    */ 
/*    */ public class CubeRayCastResult extends CollisionWorld.ClosestRayResultCallback
/*    */ {
/*    */   private Object owner;
/*    */   private Object userData;
/* 16 */   private boolean respectShields = false;
/* 17 */   private boolean ignoereNotPhysical = false;
/*    */   public SegmentController filter;
/*    */   public boolean onlyCubeMeshes;
/* 20 */   public o cubePos = new o();
/*    */   private Segment segment;
/*    */   private Segment lastHitSegment;
/*    */ 
/*    */   public CubeRayCastResult(Vector3f paramVector3f1, Vector3f paramVector3f2, Object paramObject, SegmentController paramSegmentController)
/*    */   {
/* 26 */     super(paramVector3f1, paramVector3f2);
/* 27 */     this.owner = paramObject;
/* 28 */     this.filter = paramSegmentController;
/*    */   }
/*    */ 
/*    */   public Segment getLastHitSegment()
/*    */   {
/* 34 */     return this.lastHitSegment;
/*    */   }
/*    */ 
/*    */   public Object getOwner() {
/* 38 */     return this.owner;
/*    */   }
/*    */ 
/*    */   public Object getUserData()
/*    */   {
/* 44 */     return this.userData;
/*    */   }
/*    */ 
/*    */   public boolean isIgnoereNotPhysical()
/*    */   {
/* 51 */     return this.ignoereNotPhysical;
/*    */   }
/*    */ 
/*    */   public boolean isRespectShields()
/*    */   {
/* 57 */     return this.respectShields;
/*    */   }
/*    */ 
/*    */   public void setIgnoereNotPhysical(boolean paramBoolean)
/*    */   {
/* 64 */     this.ignoereNotPhysical = paramBoolean;
/*    */   }
/*    */ 
/*    */   public void setLastHitSegment(Segment paramSegment)
/*    */   {
/* 70 */     this.lastHitSegment = paramSegment;
/*    */   }
/*    */ 
/*    */   public void setRespectShields(boolean paramBoolean)
/*    */   {
/* 76 */     this.respectShields = paramBoolean;
/*    */   }
/*    */ 
/*    */   public void setUserData(Object paramObject)
/*    */   {
/* 82 */     this.userData = paramObject;
/*    */   }
/*    */ 
/*    */   public Segment getSegment()
/*    */   {
/* 88 */     return this.segment;
/*    */   }
/*    */ 
/*    */   public void setSegment(Segment paramSegment)
/*    */   {
/* 94 */     this.segment = paramSegment;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeRayCastResult
 * JD-Core Version:    0.6.2
 */