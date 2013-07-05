/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import com.bulletphysics.util.ObjectPool;
/*    */ 
/*    */ public class CompoundCollisionVariableSet
/*    */ {
/*  8 */   public Transform tmpTrans = new Transform();
/*  9 */   public Transform orgTrans = new Transform();
/* 10 */   public Transform chieldTrans = new Transform();
/* 11 */   public Transform interpolationTrans = new Transform();
/* 12 */   public Transform newChildWorldTrans = new Transform();
/*    */ 
/* 15 */   public Transform tmpTransO = new Transform();
/* 16 */   public Transform orgTransO = new Transform();
/* 17 */   public Transform chieldTransO = new Transform();
/* 18 */   public Transform interpolationTransO = new Transform();
/* 19 */   public Transform newChildWorldTransO = new Transform();
/*    */   public int instances;
/* 23 */   public final ObjectPool pool = new ObjectPool(CompoundCollisionAlgorithmExt.class);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CompoundCollisionVariableSet
 * JD-Core Version:    0.6.2
 */