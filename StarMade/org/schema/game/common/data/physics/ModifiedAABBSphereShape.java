/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.shapes.SphereShape;
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class ModifiedAABBSphereShape extends SphereShape
/*    */ {
/*    */   private float ext;
/* 11 */   Vector3f extent = new Vector3f();
/*    */ 
/* 13 */   public ModifiedAABBSphereShape(float paramFloat1, float paramFloat2) { super(paramFloat1);
/* 14 */     this.ext = paramFloat2;
/*    */   }
/*    */ 
/*    */   public void getAabb(Transform paramTransform, Vector3f paramVector3f1, Vector3f paramVector3f2)
/*    */   {
/* 21 */     super.getAabb(paramTransform, paramVector3f1, paramVector3f2);
/* 22 */     paramTransform = paramTransform.origin;
/* 23 */     this.extent.set(getMargin() + this.ext, getMargin() + this.ext, getMargin() + this.ext);
/* 24 */     paramVector3f1.sub(paramTransform, this.extent);
/* 25 */     paramVector3f2.add(paramTransform, this.extent);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.ModifiedAABBSphereShape
 * JD-Core Version:    0.6.2
 */