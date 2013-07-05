/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.broadphase.Dbvt;
/*    */ import com.bulletphysics.collision.broadphase.Dbvt.Node;
/*    */ import com.bulletphysics.collision.broadphase.DbvtAabbMm;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class DbvtExt extends Dbvt
/*    */ {
/* 10 */   Vector3f tmp = new Vector3f();
/*    */ 
/*    */   public boolean update(Dbvt.Node paramNode, DbvtAabbMm paramDbvtAabbMm, Vector3f paramVector3f, float paramFloat) {
/* 13 */     if (paramNode.volume.Contain(paramDbvtAabbMm)) {
/* 14 */       return false;
/*    */     }
/* 16 */     this.tmp.set(paramFloat, paramFloat, paramFloat);
/* 17 */     paramDbvtAabbMm.Expand(this.tmp);
/* 18 */     paramDbvtAabbMm.SignedExpand(paramVector3f);
/* 19 */     update(paramNode, paramDbvtAabbMm);
/* 20 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.DbvtExt
 * JD-Core Version:    0.6.2
 */