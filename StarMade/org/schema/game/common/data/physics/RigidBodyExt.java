/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*   4:    */import com.bulletphysics.dynamics.RigidBody;
/*   5:    */import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
/*   6:    */import com.bulletphysics.linearmath.MotionState;
/*   7:    */import com.bulletphysics.linearmath.Transform;
/*   8:    */import javax.vecmath.Matrix3f;
/*   9:    */import javax.vecmath.Quat4f;
/*  10:    */import javax.vecmath.Vector3f;
/*  11:    */import org.schema.common.util.linAlg.TransformTools;
/*  12:    */import org.schema.game.common.controller.SegmentController;
/*  13:    */import q;
/*  14:    */
/*  16:    */public class RigidBodyExt
/*  17:    */  extends RigidBody
/*  18:    */  implements RelativeBody
/*  19:    */{
/*  20:    */  private final SegmentController segmentController;
/*  21:    */  public String virtualString;
/*  22:    */  public q virtualSec;
/*  23:    */  
/*  24:    */  public RigidBodyExt(SegmentController paramSegmentController, float paramFloat, MotionState paramMotionState, CollisionShape paramCollisionShape)
/*  25:    */  {
/*  26: 26 */    super(paramFloat, paramMotionState, paramCollisionShape);
/*  27: 27 */    this.segmentController = paramSegmentController;
/*  28: 28 */    this.interpolationWorldTransform.setIdentity();
/*  29:    */  }
/*  30:    */  
/*  32:    */  public RigidBodyExt(SegmentController paramSegmentController, float paramFloat, MotionState paramMotionState, CollisionShape paramCollisionShape, Vector3f paramVector3f)
/*  33:    */  {
/*  34: 34 */    super(paramFloat, paramMotionState, paramCollisionShape, paramVector3f);
/*  35: 35 */    this.segmentController = paramSegmentController;
/*  36: 36 */    this.interpolationWorldTransform.setIdentity();
/*  37:    */  }
/*  38:    */  
/*  39:    */  public RigidBodyExt(SegmentController paramSegmentController, RigidBodyConstructionInfo paramRigidBodyConstructionInfo)
/*  40:    */  {
/*  41: 41 */    super(paramRigidBodyConstructionInfo);
/*  42: 42 */    this.segmentController = paramSegmentController;
/*  43: 43 */    this.interpolationWorldTransform.setIdentity();
/*  44:    */  }
/*  45:    */  
/*  78: 78 */  private final Vector3f angularVeloTmp = new Vector3f();
/*  79: 79 */  private final Vector3f linearVeloTmp = new Vector3f();
/*  80: 80 */  private final Vector3f axis = new Vector3f();
/*  81: 81 */  private final Matrix3f tmp = new Matrix3f();
/*  82: 82 */  private final Matrix3f dmat = new Matrix3f();
/*  83: 83 */  private final Quat4f dorn = new Quat4f();
/*  84:    */  
/*  89:    */  public void saveKinematicState(float paramFloat)
/*  90:    */  {
/*  91: 91 */    if (paramFloat != 0.0F)
/*  92:    */    {
/*  93: 93 */      if (getMotionState() != null) {
/*  94: 94 */        getMotionState().getWorldTransform(this.worldTransform);
/*  95:    */      }
/*  96: 96 */      getLinearVelocity(this.linearVeloTmp);
/*  97: 97 */      getAngularVelocity(this.angularVeloTmp);
/*  98:    */      
/* 100:100 */      if ((this.linearVeloTmp.lengthSquared() > 0.0F) || (this.angularVeloTmp.lengthSquared() > 0.0F))
/* 101:    */      {
/* 102:102 */        TransformTools.a(this.interpolationWorldTransform, this.worldTransform, paramFloat, this.linearVeloTmp, this.angularVeloTmp, this.axis, this.tmp, this.dmat, this.dorn);
/* 103:    */        
/* 107:107 */        setLinearVelocity(this.linearVeloTmp);
/* 108:108 */        setAngularVelocity(this.angularVeloTmp);
/* 109:    */      }
/* 110:    */      
/* 111:111 */      this.interpolationLinearVelocity.set(this.linearVeloTmp);
/* 112:112 */      this.interpolationAngularVelocity.set(this.angularVeloTmp);
/* 113:113 */      this.interpolationWorldTransform.set(this.worldTransform);
/* 114:    */    }
/* 115:    */  }
/* 116:    */  
/* 130:    */  public Vector3f getInvInertiaDiagLocal(Vector3f paramVector3f)
/* 131:    */  {
/* 132:132 */    return super.getInvInertiaDiagLocal(paramVector3f);
/* 133:    */  }
/* 134:    */  
/* 142:    */  public String toString()
/* 143:    */  {
/* 144:144 */    return "RigBEx" + (this.virtualString != null ? this.virtualString : "Orig") + "@" + hashCode() + "(" + getCollisionShape() + ")";
/* 145:    */  }
/* 146:    */  
/* 151:    */  public void setLinearVelocity(Vector3f paramVector3f)
/* 152:    */  {
/* 153:153 */    if (this.virtualSec == null) {
/* 154:154 */      super.setLinearVelocity(paramVector3f);
/* 155:    */    }
/* 156:    */  }
/* 157:    */  
/* 170:    */  public void setAngularVelocity(Vector3f paramVector3f)
/* 171:    */  {
/* 172:172 */    if (this.virtualSec == null) {
/* 173:173 */      super.setAngularVelocity(paramVector3f);
/* 174:    */    }
/* 175:    */  }
/* 176:    */  
/* 188:    */  public SegmentController getSegmentController()
/* 189:    */  {
/* 190:190 */    return this.segmentController;
/* 191:    */  }
/* 192:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.RigidBodyExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */