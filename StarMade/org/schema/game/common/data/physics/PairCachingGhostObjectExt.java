/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.dispatch.CollisionObject;
/*   4:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ConvexResultCallback;
/*   5:    */import com.bulletphysics.collision.dispatch.PairCachingGhostObject;
/*   6:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*   7:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   8:    */import com.bulletphysics.linearmath.AabbUtil2;
/*   9:    */import com.bulletphysics.linearmath.Transform;
/*  10:    */import com.bulletphysics.util.ObjectArrayList;
/*  11:    */import java.io.PrintStream;
/*  12:    */import javax.vecmath.Matrix3f;
/*  13:    */import javax.vecmath.Matrix4f;
/*  14:    */import javax.vecmath.Quat4f;
/*  15:    */import javax.vecmath.Vector3f;
/*  16:    */import org.schema.common.util.linAlg.TransformTools;
/*  17:    */import org.schema.game.common.controller.SegmentController;
/*  18:    */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*  19:    */
/*  22:    */public abstract class PairCachingGhostObjectExt
/*  23:    */  extends PairCachingGhostObject
/*  24:    */{
/*  25:    */  private PhysicsDataContainer pCon;
/*  26:    */  private SegmentController attached;
/*  27: 27 */  Vector3f castShapeAabbMin = new Vector3f();
/*  28:    */  
/*  29: 29 */  Vector3f castShapeAabbMax = new Vector3f();
/*  30: 30 */  Transform convexFromTrans = new Transform();
/*  31:    */  
/*  33: 33 */  Transform convexToTrans = new Transform();
/*  34: 34 */  Vector3f linVel = new Vector3f();
/*  35:    */  
/*  37: 37 */  Vector3f angVel = new Vector3f();
/*  38: 38 */  Transform tmpTrans = new Transform();
/*  39: 39 */  Vector3f collisionObjectAabbMin = new Vector3f();
/*  40: 40 */  Vector3f collisionObjectAabbMax = new Vector3f();
/*  41: 41 */  Vector3f hitNormal = new Vector3f();
/*  42: 42 */  Transform R = new Transform();
/*  43: 43 */  Quat4f quat = new Quat4f();
/*  44:    */  
/*  45: 45 */  public PairCachingGhostObjectExt(PhysicsDataContainer paramPhysicsDataContainer) { this.pCon = paramPhysicsDataContainer; }
/*  46:    */  
/*  49: 49 */  private Matrix3f tmp = new Matrix3f();
/*  50: 50 */  private final Vector3f axis = new Vector3f();
/*  51: 51 */  private final Matrix3f dmat = new Matrix3f();
/*  52: 52 */  private final Quat4f dorn = new Quat4f();
/*  53:    */  
/*  57:    */  public String toString()
/*  58:    */  {
/*  59: 59 */    return "PCGhostObjExt(" + getUserPointer() + ")@" + hashCode();
/*  60:    */  }
/*  61:    */  
/*  63:    */  public void setWorldTransform(Transform paramTransform)
/*  64:    */  {
/*  65: 65 */    this.worldTransform.set(paramTransform);
/*  66:    */  }
/*  67:    */  
/*  69:    */  public void convexSweepTest(ConvexShape paramConvexShape, Transform paramTransform1, Transform paramTransform2, CollisionWorld.ConvexResultCallback paramConvexResultCallback, float paramFloat)
/*  70:    */  {
/*  71: 71 */    this.convexFromTrans.set(paramTransform1);
/*  72: 72 */    this.convexToTrans.set(paramTransform2);
/*  73:    */    
/*  74: 74 */    this.quat.x = 0.0F;
/*  75: 75 */    this.quat.y = 0.0F;
/*  76: 76 */    this.quat.x = 0.0F;
/*  77: 77 */    this.quat.w = 0.0F;
/*  78:    */    
/*  80: 80 */    assert (paramTransform1.getMatrix(new Matrix4f()).determinant() != 0.0F) : paramTransform1.getMatrix(new Matrix4f());
/*  81: 81 */    assert (paramTransform2.getMatrix(new Matrix4f()).determinant() != 0.0F) : paramTransform2.getMatrix(new Matrix4f());
/*  82: 82 */    TransformTools.a(this.convexFromTrans, this.convexToTrans, 1.0F, this.linVel, this.angVel, this.axis, this.tmp, this.dmat, this.dorn);
/*  83:    */    
/*  84: 84 */    this.R.setIdentity();
/*  85: 85 */    this.R.setRotation(this.convexFromTrans.getRotation(this.quat));
/*  86: 86 */    paramConvexShape.calculateTemporalAabb(this.R, this.linVel, this.angVel, 1.0F, this.castShapeAabbMin, this.castShapeAabbMax);
/*  87:    */    
/*  93: 93 */    long l = System.currentTimeMillis();
/*  94: 94 */    for (int i = 0; i < this.overlappingObjects.size(); i++)
/*  95:    */    {
/*  96:    */      CollisionObject localCollisionObject;
/*  97: 97 */      if (((localCollisionObject = (CollisionObject)this.overlappingObjects.getQuick(i)) != this.pCon.getObject()) && 
/*  98: 98 */        (paramConvexResultCallback.needsCollision(localCollisionObject.getBroadphaseHandle())))
/*  99:    */      {
/* 107:107 */        localCollisionObject.getCollisionShape().getAabb(localCollisionObject.getWorldTransform(this.tmpTrans), this.collisionObjectAabbMin, this.collisionObjectAabbMax);
/* 108:108 */        AabbUtil2.aabbExpand(this.collisionObjectAabbMin, this.collisionObjectAabbMax, this.castShapeAabbMin, this.castShapeAabbMax);
/* 109:109 */        float[] arrayOfFloat = { 1.0F };
/* 110:110 */        this.hitNormal.set(0.0F, 0.0F, 0.0F);
/* 111:111 */        if (AabbUtil2.rayAabb(paramTransform1.origin, paramTransform2.origin, this.collisionObjectAabbMin, this.collisionObjectAabbMax, arrayOfFloat, this.hitNormal))
/* 112:    */        {
/* 114:114 */          ModifiedDynamicsWorld.objectQuerySingle(paramConvexShape, this.convexFromTrans, this.convexToTrans, localCollisionObject, localCollisionObject.getCollisionShape(), localCollisionObject.getWorldTransform(this.tmpTrans), paramConvexResultCallback, paramFloat);
/* 115:    */        }
/* 116:    */      }
/* 117:    */    }
/* 118:    */    
/* 128:128 */    if ((i = (int)(System.currentTimeMillis() - l)) > 15) {
/* 129:129 */      System.err.println("[GHOST-OBJECT] SWEEP TEST TIME: " + i);
/* 130:    */    }
/* 131:    */  }
/* 132:    */  
/* 135:    */  public SegmentController getAttached()
/* 136:    */  {
/* 137:137 */    return this.attached;
/* 138:    */  }
/* 139:    */  
/* 142:    */  public void setAttached(SegmentController paramSegmentController)
/* 143:    */  {
/* 144:144 */    this.attached = paramSegmentController;
/* 145:    */  }
/* 146:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.PairCachingGhostObjectExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */