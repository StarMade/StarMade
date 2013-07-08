/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*  4:   */import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*  5:   */import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
/*  6:   */import com.bulletphysics.collision.dispatch.CollisionObject;
/*  7:   */import com.bulletphysics.util.ObjectPool;
/*  8:   */
/* 55:   */public class CompoundCollisionAlgorithmExt$SwappedCreateFunc
/* 56:   */  extends CollisionAlgorithmCreateFunc
/* 57:   */{
/* 58:58 */  private final ObjectPool pool = ObjectPool.get(CompoundCollisionAlgorithmExt.class);
/* 59:   */  
/* 62:   */  public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2)
/* 63:   */  {
/* 64:   */    CompoundCollisionAlgorithmExt localCompoundCollisionAlgorithmExt;
/* 65:   */    
/* 67:67 */    CompoundCollisionAlgorithmExt.access$002(localCompoundCollisionAlgorithmExt = new CompoundCollisionAlgorithmExt(), paramCollisionObject2);
/* 68:68 */    CompoundCollisionAlgorithmExt.access$102(localCompoundCollisionAlgorithmExt, paramCollisionObject1);
/* 69:   */    
/* 70:70 */    localCompoundCollisionAlgorithmExt.init(paramCollisionAlgorithmConstructionInfo);
/* 71:71 */    localCompoundCollisionAlgorithmExt.swapped = true;
/* 72:72 */    return localCompoundCollisionAlgorithmExt;
/* 73:   */  }
/* 74:   */  
/* 77:   */  public void releaseCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm)
/* 78:   */  {
/* 79:79 */    CompoundCollisionAlgorithmExt.access$200(paramCollisionAlgorithm = (CompoundCollisionAlgorithmExt)paramCollisionAlgorithm).pool.release(paramCollisionAlgorithm);
/* 80:   */  }
/* 81:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CompoundCollisionAlgorithmExt.SwappedCreateFunc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */