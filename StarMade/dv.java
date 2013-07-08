/*   1:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*   2:    */import com.bulletphysics.linearmath.Transform;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import org.schema.game.common.data.physics.CubeRayCastResult;
/*   5:    */import org.schema.game.common.data.physics.PhysicsExt;
/*   6:    */import org.schema.schine.graphicsengine.camera.Camera;
/*   7:    */
/*  71:    */public final class dv
/*  72:    */  extends Camera
/*  73:    */  implements wx
/*  74:    */{
/*  75:    */  private ct jdField_a_of_type_Ct;
/*  76:    */  private lD jdField_a_of_type_LD;
/*  77:    */  private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/*  78:    */  private CollisionWorld.ClosestRayResultCallback jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback;
/*  79:    */  private Transform b;
/*  80:    */  
/*  81:    */  public dv(ct paramct, lD paramlD)
/*  82:    */  {
/*  83: 83 */    super(new dC(paramlD));a(paramlD);
/*  84:    */    
/*  85: 85 */    this.jdField_a_of_type_Ct = paramct;
/*  86: 86 */    this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*  87: 87 */    this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/*  88:    */    
/*  89: 89 */    this.jdField_a_of_type_WZ = new wX(this);
/*  90:    */  }
/*  91:    */  
/* 106:    */  public final lD a()
/* 107:    */  {
/* 108:108 */    return this.jdField_a_of_type_LD;
/* 109:    */  }
/* 110:    */  
/* 141:    */  public final void handleKeyEvent()
/* 142:    */  {
/* 143:143 */    ((dz)a()).handleKeyEvent();
/* 144:    */  }
/* 145:    */  
/* 154:    */  protected final int a(int paramInt)
/* 155:    */  {
/* 156:156 */    return Math.max(0, Math.min(paramInt, 2500));
/* 157:    */  }
/* 158:    */  
/* 165:    */  public final void a(lD paramlD)
/* 166:    */  {
/* 167:167 */    this.jdField_a_of_type_LD = paramlD;
/* 168:168 */    ((xb)a()).a(paramlD);
/* 169:    */  }
/* 170:    */  
/* 178:    */  public final void a(xq paramxq)
/* 179:    */  {
/* 180:180 */    Vector3f localVector3f = null; if ((this.jdField_a_of_type_LD.getGravity().b()) || (this.jdField_a_of_type_LD.getGravity().a())) {
/* 181:181 */      if (this.jdField_a_of_type_LD.getGravity().a()) {
/* 182:182 */        ((wX)this.jdField_a_of_type_WZ).jdField_a_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_LD.getGravity().a.getWorldTransform());
/* 183:183 */        this.b = null;
/* 184:    */      }
/* 185:    */      else
/* 186:    */      {
/* 187:187 */        if (this.b == null) {
/* 188:188 */          this.b = new Transform(this.jdField_a_of_type_LD.getGravity().a.getWorldTransform());
/* 189:    */        }
/* 190:190 */        ((wX)this.jdField_a_of_type_WZ).jdField_a_of_type_ComBulletphysicsLinearmathTransform.set(this.b);
/* 191:    */      }
/* 192:    */    } else {
/* 193:193 */      this.b = null;
/* 194:194 */      ((wX)this.jdField_a_of_type_WZ).jdField_a_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 195:    */    }
/* 196:    */    
/* 197:197 */    super.a(paramxq);
/* 198:    */    
/* 200:200 */    Object localObject = this.jdField_a_of_type_LD;paramxq = this;localObject = new Vector3f(((lD)localObject).a().origin);localVector3f = new Vector3f(paramxq.c(new Vector3f())); CubeRayCastResult localCubeRayCastResult; (localCubeRayCastResult = new CubeRayCastResult((Vector3f)localObject, localVector3f, Boolean.valueOf(false), null)).setRespectShields(false);localCubeRayCastResult.onlyCubeMeshes = true;this.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback = (this.jdField_a_of_type_Float > 0.0F ? ((PhysicsExt)paramxq.jdField_a_of_type_Ct.a()).testRayCollisionPoint((Vector3f)localObject, localVector3f, localCubeRayCastResult, false) : null);
/* 201:    */    
/* 203:203 */    if ((this.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback != null) && (this.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback.hasHit()))
/* 204:    */    {
/* 205:205 */      (paramxq = new Vector3f()).sub(c(new Vector3f()), this.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback.hitPointWorld);
/* 206:206 */      paramxq.scale(1.01F);
/* 207:207 */      getWorldTransform().origin.sub(paramxq);
/* 208:    */    }
/* 209:    */  }
/* 210:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dv
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */