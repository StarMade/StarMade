/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.linearmath.Transform;
/*  4:   */import com.bulletphysics.util.ObjectPool;
/*  5:   */
/*  6:   */public class CompoundCollisionVariableSet
/*  7:   */{
/*  8: 8 */  public Transform tmpTrans = new Transform();
/*  9: 9 */  public Transform orgTrans = new Transform();
/* 10:10 */  public Transform chieldTrans = new Transform();
/* 11:11 */  public Transform interpolationTrans = new Transform();
/* 12:12 */  public Transform newChildWorldTrans = new Transform();
/* 13:   */  
/* 15:15 */  public Transform tmpTransO = new Transform();
/* 16:16 */  public Transform orgTransO = new Transform();
/* 17:17 */  public Transform chieldTransO = new Transform();
/* 18:18 */  public Transform interpolationTransO = new Transform();
/* 19:19 */  public Transform newChildWorldTransO = new Transform();
/* 20:   */  
/* 21:   */  public int instances;
/* 22:   */  
/* 23:23 */  public final ObjectPool pool = new ObjectPool(CompoundCollisionAlgorithmExt.class);
/* 24:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CompoundCollisionVariableSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */