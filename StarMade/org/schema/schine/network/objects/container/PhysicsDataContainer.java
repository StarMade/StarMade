/*   1:    */package org.schema.schine.network.objects.container;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.dispatch.CollisionObject;
/*   4:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*   5:    */import com.bulletphysics.collision.shapes.CompoundShapeChild;
/*   6:    */import com.bulletphysics.dynamics.RigidBody;
/*   7:    */import com.bulletphysics.linearmath.MotionState;
/*   8:    */import com.bulletphysics.linearmath.Transform;
/*   9:    */import d;
/*  10:    */import java.io.PrintStream;
/*  11:    */import javax.vecmath.Vector3f;
/*  12:    */
/*  18:    */public class PhysicsDataContainer
/*  19:    */{
/*  20:    */  private static final Transform ident;
/*  21:    */  private CollisionShape shape;
/*  22:    */  private CompoundShapeChild shapeChield;
/*  23:    */  private CollisionObject object;
/*  24:    */  
/*  25:    */  static
/*  26:    */  {
/*  27: 27 */    (PhysicsDataContainer.ident = new Transform()).setIdentity();
/*  28:    */  }
/*  29:    */  
/*  36: 36 */  private Vector3f inertia = new Vector3f();
/*  37:    */  
/*  38:    */  public Transform initialTransform;
/*  39:    */  
/*  40:    */  private boolean initialized;
/*  41:    */  
/*  42: 42 */  private final Transform cacheTransform = new Transform();
/*  43:    */  
/*  52: 52 */  private float lastUpdatedMass = -1.0F;
/*  53:    */  
/*  54:    */  public void onPhysicsAdd() {
/*  55: 55 */    this.lastUpdatedMass = -1.0F;
/*  56:    */  }
/*  57:    */  
/*  58: 58 */  public void onPhysicsRemove() { this.lastUpdatedMass = -1.0F; }
/*  59:    */  
/*  62:    */  public void clearPhysicsInfo()
/*  63:    */  {
/*  64: 64 */    setObject(null);
/*  65: 65 */    this.initialTransform = null;
/*  66: 66 */    setShape(null);
/*  67: 67 */    setShapeChield(null);
/*  68:    */    
/*  69: 69 */    this.initialized = false;
/*  70:    */  }
/*  71:    */  
/*  82:    */  public Transform getCurrentPhysicsTransform()
/*  83:    */  {
/*  84: 84 */    return this.cacheTransform;
/*  85:    */  }
/*  86:    */  
/*  87: 87 */  public CollisionObject getObject() { return this.object; }
/*  88:    */  
/*  89:    */  public CollisionShape getShape() {
/*  90: 90 */    return this.shape;
/*  91:    */  }
/*  92:    */  
/*  95:    */  public CompoundShapeChild getShapeChild()
/*  96:    */  {
/*  97: 97 */    return this.shapeChield;
/*  98:    */  }
/*  99:    */  
/* 102:    */  public boolean isInitialized()
/* 103:    */  {
/* 104:104 */    return this.initialized;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public void setObject(CollisionObject paramCollisionObject) {
/* 108:108 */    if (paramCollisionObject != null)
/* 109:    */    {
/* 115:115 */      if ((paramCollisionObject instanceof RigidBody))
/* 116:    */      {
/* 120:120 */        ((RigidBody)paramCollisionObject).getMotionState().setWorldTransform(paramCollisionObject.getWorldTransform(new Transform()));
/* 121:    */      }
/* 122:122 */      paramCollisionObject.setInterpolationWorldTransform(paramCollisionObject.getWorldTransform(new Transform()));
/* 123:    */    }
/* 124:124 */    this.object = paramCollisionObject;
/* 125:    */  }
/* 126:    */  
/* 127:    */  public void setShape(CollisionShape paramCollisionShape) {
/* 128:128 */    this.shape = paramCollisionShape;
/* 129:    */  }
/* 130:    */  
/* 133:    */  public void setShapeChield(CompoundShapeChild paramCompoundShapeChild)
/* 134:    */  {
/* 135:135 */    this.shapeChield = paramCompoundShapeChild;
/* 136:    */  }
/* 137:    */  
/* 138:    */  public boolean updateMass(float paramFloat, boolean paramBoolean) {
/* 139:139 */    if ((this.initialized) && (getShape() != null) && (getObject() != null))
/* 140:    */    {
/* 141:141 */      if ((paramFloat != this.lastUpdatedMass) || (paramBoolean)) {
/* 142:142 */        getShape().calculateLocalInertia(Math.max(paramFloat, 2.5F), this.inertia);
/* 143:143 */        ((RigidBody)getObject()).setMassProps(paramFloat, this.inertia);
/* 144:144 */        this.lastUpdatedMass = paramFloat;
/* 145:    */        
/* 147:147 */        return true;
/* 148:    */      }
/* 149:    */      
/* 151:    */    }
/* 152:    */    else {
/* 153:153 */      System.err.println("[PHYSICSCONTAINER][WARNING] Could not set mass!");
/* 154:    */    }
/* 155:155 */    return false;
/* 156:    */  }
/* 157:    */  
/* 159:159 */  public void updatePhysical() { updatePhysical(getObject()); }
/* 160:    */  
/* 161:    */  public void updatePhysical(CollisionObject paramCollisionObject) {
/* 162:162 */    if (paramCollisionObject != null) {
/* 163:163 */      if ((paramCollisionObject instanceof RigidBody))
/* 164:    */      {
/* 168:168 */        ((RigidBody)paramCollisionObject).getMotionState().getWorldTransform(this.cacheTransform);
/* 169:    */        
/* 170:170 */        if ((getShapeChild() != null) && 
/* 171:171 */          (!getShapeChild().transform.equals(ident))) {
/* 172:172 */          d.a(this.cacheTransform, getShapeChild().transform);
/* 173:    */        }
/* 174:    */        
/* 175:    */      }
/* 176:    */      else
/* 177:    */      {
/* 178:178 */        paramCollisionObject.getWorldTransform(this.cacheTransform);
/* 179:    */      }
/* 180:180 */      this.initialized = true;
/* 181:    */    }
/* 182:    */  }
/* 183:    */  
/* 184:    */  public void updateManually(Transform paramTransform) {
/* 185:185 */    this.cacheTransform.set(paramTransform);
/* 186:    */  }
/* 187:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.container.PhysicsDataContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */