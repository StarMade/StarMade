/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*    */ 
/*    */ public class PairCachingGhostObjectAlignable extends PairCachingGhostObjectExt
/*    */   implements RelativeBody
/*    */ {
/*    */   public Transform localWorldTransform;
/*    */ 
/*    */   public PairCachingGhostObjectAlignable(PhysicsDataContainer paramPhysicsDataContainer)
/*    */   {
/* 13 */     super(paramPhysicsDataContainer);
/*    */   }
/*    */ 
/*    */   public boolean checkCollideWith(CollisionObject paramCollisionObject)
/*    */   {
/* 22 */     return !(paramCollisionObject instanceof PairCachingGhostObjectAlignable);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 29 */     return "PCGhostObjExt(" + getUserPointer() + "->Attached(" + getAttached() + "))@" + hashCode();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.PairCachingGhostObjectAlignable
 * JD-Core Version:    0.6.2
 */