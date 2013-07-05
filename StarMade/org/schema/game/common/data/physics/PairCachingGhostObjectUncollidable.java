/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*    */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*    */ 
/*    */ public class PairCachingGhostObjectUncollidable extends PairCachingGhostObjectExt
/*    */ {
/*    */   public PairCachingGhostObjectUncollidable(PhysicsDataContainer paramPhysicsDataContainer)
/*    */   {
/* 10 */     super(paramPhysicsDataContainer);
/*    */   }
/*    */ 
/*    */   public boolean checkCollideWith(CollisionObject paramCollisionObject)
/*    */   {
/* 19 */     return !(paramCollisionObject instanceof PairCachingGhostObjectUncollidable);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 26 */     return "PCGhostObjUCMissile(" + getUserPointer() + ")";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.PairCachingGhostObjectUncollidable
 * JD-Core Version:    0.6.2
 */