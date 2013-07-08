/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.dispatch.CollisionObject;
/*   4:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestConvexResultCallback;
/*   5:    */import com.bulletphysics.collision.dispatch.CollisionWorld.LocalConvexResult;
/*   6:    */import com.bulletphysics.linearmath.Transform;
/*   7:    */import javax.vecmath.Matrix3f;
/*   8:    */import javax.vecmath.Vector3f;
/*   9:    */import o;
/*  10:    */import org.schema.game.common.data.world.Segment;
/*  11:    */
/*  46:    */class KinematicCharacterControllerExt$KinematicClosestNotMeConvexResultCallback
/*  47:    */  extends CollisionWorld.ClosestConvexResultCallback
/*  48:    */{
/*  49:    */  protected CollisionObject me;
/*  50:    */  protected final Vector3f up;
/*  51:    */  protected float minSlopeDot;
/*  52: 52 */  Transform t = new Transform();
/*  53:    */  private Segment segment;
/*  54:    */  private o cubePos;
/*  55:    */  
/*  56:    */  public KinematicCharacterControllerExt$KinematicClosestNotMeConvexResultCallback(CollisionObject paramCollisionObject, Vector3f paramVector3f, float paramFloat) {
/*  57: 57 */    super(new Vector3f(), new Vector3f());
/*  58: 58 */    this.me = paramCollisionObject;
/*  59: 59 */    this.up = paramVector3f;
/*  60: 60 */    this.minSlopeDot = paramFloat;
/*  61:    */  }
/*  62:    */  
/*  64:    */  public float addSingleResult(CollisionWorld.LocalConvexResult paramLocalConvexResult, boolean paramBoolean)
/*  65:    */  {
/*  66: 66 */    if (paramLocalConvexResult.hitCollisionObject == this.me) {
/*  67: 67 */      return 1.0F;
/*  68:    */    }
/*  69:    */    
/*  70:    */    Vector3f localVector3f;
/*  71: 71 */    if (paramBoolean) {
/*  72: 72 */      localVector3f = paramLocalConvexResult.hitNormalLocal;
/*  73:    */    }
/*  74:    */    else {
/*  75: 75 */      localVector3f = new Vector3f();
/*  76: 76 */      this.hitCollisionObject.getWorldTransform(this.t).basis.transform(paramLocalConvexResult.hitNormalLocal, localVector3f);
/*  77:    */    }
/*  78:    */    
/*  81: 81 */    if (this.up.dot(localVector3f) < this.minSlopeDot) {
/*  82: 82 */      return 1.0F;
/*  83:    */    }
/*  84:    */    
/*  85: 85 */    return super.addSingleResult(paramLocalConvexResult, paramBoolean);
/*  86:    */  }
/*  87:    */  
/*  99:    */  public void addSingleResult(CollisionWorld.LocalConvexResult paramLocalConvexResult, boolean paramBoolean, Segment paramSegment, o paramo)
/* 100:    */  {
/* 101:101 */    addSingleResult(paramLocalConvexResult, paramBoolean);
/* 102:    */    
/* 103:103 */    this.segment = paramSegment;
/* 104:104 */    this.cubePos = new o(paramo);
/* 105:    */  }
/* 106:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */