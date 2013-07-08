/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.broadphase.Dbvt;
/*  4:   */import com.bulletphysics.collision.broadphase.Dbvt.Node;
/*  5:   */import com.bulletphysics.collision.broadphase.DbvtAabbMm;
/*  6:   */import javax.vecmath.Vector3f;
/*  7:   */
/*  8:   */public class DbvtExt extends Dbvt
/*  9:   */{
/* 10:10 */  Vector3f tmp = new Vector3f();
/* 11:   */  
/* 12:   */  public boolean update(Dbvt.Node paramNode, DbvtAabbMm paramDbvtAabbMm, Vector3f paramVector3f, float paramFloat) {
/* 13:13 */    if (paramNode.volume.Contain(paramDbvtAabbMm)) {
/* 14:14 */      return false;
/* 15:   */    }
/* 16:16 */    this.tmp.set(paramFloat, paramFloat, paramFloat);
/* 17:17 */    paramDbvtAabbMm.Expand(this.tmp);
/* 18:18 */    paramDbvtAabbMm.SignedExpand(paramVector3f);
/* 19:19 */    update(paramNode, paramDbvtAabbMm);
/* 20:20 */    return true;
/* 21:   */  }
/* 22:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.DbvtExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */