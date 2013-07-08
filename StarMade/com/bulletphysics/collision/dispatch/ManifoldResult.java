/*   1:    */package com.bulletphysics.collision.dispatch;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.BulletGlobals;
/*   5:    */import com.bulletphysics.ContactAddedCallback;
/*   6:    */import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.Result;
/*   7:    */import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*   8:    */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*   9:    */import com.bulletphysics.linearmath.Transform;
/*  10:    */import com.bulletphysics.util.ObjectPool;
/*  11:    */import javax.vecmath.Vector3f;
/*  12:    */
/*  40:    */public class ManifoldResult
/*  41:    */  extends DiscreteCollisionDetectorInterface.Result
/*  42:    */{
/*  43: 43 */  protected final ObjectPool<ManifoldPoint> pointsPool = ObjectPool.get(ManifoldPoint.class);
/*  44:    */  
/*  46:    */  private PersistentManifold manifoldPtr;
/*  47:    */  
/*  48: 48 */  private final Transform rootTransA = new Transform();
/*  49: 49 */  private final Transform rootTransB = new Transform();
/*  50:    */  private CollisionObject body0;
/*  51:    */  private CollisionObject body1;
/*  52:    */  private int partId0;
/*  53:    */  private int partId1;
/*  54:    */  private int index0;
/*  55:    */  private int index1;
/*  56:    */  
/*  57:    */  public ManifoldResult() {}
/*  58:    */  
/*  59:    */  public ManifoldResult(CollisionObject body0, CollisionObject body1)
/*  60:    */  {
/*  61: 61 */    init(body0, body1);
/*  62:    */  }
/*  63:    */  
/*  64:    */  public void init(CollisionObject body0, CollisionObject body1) {
/*  65: 65 */    this.body0 = body0;
/*  66: 66 */    this.body1 = body1;
/*  67: 67 */    body0.getWorldTransform(this.rootTransA);
/*  68: 68 */    body1.getWorldTransform(this.rootTransB);
/*  69:    */  }
/*  70:    */  
/*  71:    */  public PersistentManifold getPersistentManifold() {
/*  72: 72 */    return this.manifoldPtr;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public void setPersistentManifold(PersistentManifold manifoldPtr) {
/*  76: 76 */    this.manifoldPtr = manifoldPtr;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public void setShapeIdentifiers(int partId0, int index0, int partId1, int index1) {
/*  80: 80 */    this.partId0 = partId0;
/*  81: 81 */    this.partId1 = partId1;
/*  82: 82 */    this.index0 = index0;
/*  83: 83 */    this.index1 = index1;
/*  84:    */  }
/*  85:    */  
/*  86:    */  public void addContactPoint(Vector3f arg1, Vector3f arg2, float arg3) {
/*  87: 87 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();assert (this.manifoldPtr != null);
/*  88:    */      
/*  90: 90 */      if (depth > this.manifoldPtr.getContactBreakingThreshold()) {
/*  91: 91 */        return;
/*  92:    */      }
/*  93:    */      
/*  94: 94 */      boolean isSwapped = this.manifoldPtr.getBody0() != this.body0;
/*  95:    */      
/*  96: 96 */      Vector3f pointA = localStack.get$javax$vecmath$Vector3f();
/*  97: 97 */      pointA.scaleAdd(depth, normalOnBInWorld, pointInWorld);
/*  98:    */      
/*  99: 99 */      Vector3f localA = localStack.get$javax$vecmath$Vector3f();
/* 100:100 */      Vector3f localB = localStack.get$javax$vecmath$Vector3f();
/* 101:    */      
/* 102:102 */      if (isSwapped) {
/* 103:103 */        this.rootTransB.invXform(pointA, localA);
/* 104:104 */        this.rootTransA.invXform(pointInWorld, localB);
/* 105:    */      }
/* 106:    */      else {
/* 107:107 */        this.rootTransA.invXform(pointA, localA);
/* 108:108 */        this.rootTransB.invXform(pointInWorld, localB);
/* 109:    */      }
/* 110:    */      
/* 111:111 */      ManifoldPoint newPt = (ManifoldPoint)this.pointsPool.get();
/* 112:112 */      newPt.init(localA, localB, normalOnBInWorld, depth);
/* 113:    */      
/* 114:114 */      newPt.positionWorldOnA.set(pointA);
/* 115:115 */      newPt.positionWorldOnB.set(pointInWorld);
/* 116:    */      
/* 117:117 */      int insertIndex = this.manifoldPtr.getCacheEntry(newPt);
/* 118:    */      
/* 119:119 */      newPt.combinedFriction = calculateCombinedFriction(this.body0, this.body1);
/* 120:120 */      newPt.combinedRestitution = calculateCombinedRestitution(this.body0, this.body1);
/* 121:    */      
/* 123:123 */      newPt.partId0 = this.partId0;
/* 124:124 */      newPt.partId1 = this.partId1;
/* 125:125 */      newPt.index0 = this.index0;
/* 126:126 */      newPt.index1 = this.index1;
/* 127:    */      
/* 129:129 */      if (insertIndex >= 0)
/* 130:    */      {
/* 131:131 */        this.manifoldPtr.replaceContactPoint(newPt, insertIndex);
/* 132:    */      }
/* 133:    */      else {
/* 134:134 */        insertIndex = this.manifoldPtr.addManifoldPoint(newPt);
/* 135:    */      }
/* 136:    */      
/* 138:138 */      if ((BulletGlobals.getContactAddedCallback() != null) && (((this.body0.getCollisionFlags() & 0x8) != 0) || ((this.body1.getCollisionFlags() & 0x8) != 0)))
/* 139:    */      {
/* 143:143 */        CollisionObject obj0 = isSwapped ? this.body1 : this.body0;
/* 144:144 */        CollisionObject obj1 = isSwapped ? this.body0 : this.body1;
/* 145:145 */        BulletGlobals.getContactAddedCallback().contactAdded(this.manifoldPtr.getContactPoint(insertIndex), obj0, this.partId0, this.index0, obj1, this.partId1, this.index1);
/* 146:    */      }
/* 147:    */      
/* 148:148 */      this.pointsPool.release(newPt);
/* 149:149 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 150:    */    }
/* 151:    */  }
/* 152:    */  
/* 153:153 */  private static float calculateCombinedFriction(CollisionObject body0, CollisionObject body1) { float friction = body0.getFriction() * body1.getFriction();
/* 154:    */    
/* 155:155 */    float MAX_FRICTION = 10.0F;
/* 156:156 */    if (friction < -MAX_FRICTION) {
/* 157:157 */      friction = -MAX_FRICTION;
/* 158:    */    }
/* 159:159 */    if (friction > MAX_FRICTION) {
/* 160:160 */      friction = MAX_FRICTION;
/* 161:    */    }
/* 162:162 */    return friction;
/* 163:    */  }
/* 164:    */  
/* 165:    */  private static float calculateCombinedRestitution(CollisionObject body0, CollisionObject body1) {
/* 166:166 */    return body0.getRestitution() * body1.getRestitution();
/* 167:    */  }
/* 168:    */  
/* 169:    */  public void refreshContactPoints() {
/* 170:170 */    assert (this.manifoldPtr != null);
/* 171:171 */    if (this.manifoldPtr.getNumContacts() == 0) {
/* 172:172 */      return;
/* 173:    */    }
/* 174:    */    
/* 175:175 */    boolean isSwapped = this.manifoldPtr.getBody0() != this.body0;
/* 176:    */    
/* 177:177 */    if (isSwapped) {
/* 178:178 */      this.manifoldPtr.refreshContactPoints(this.rootTransB, this.rootTransA);
/* 179:    */    }
/* 180:    */    else {
/* 181:181 */      this.manifoldPtr.refreshContactPoints(this.rootTransA, this.rootTransB);
/* 182:    */    }
/* 183:    */  }
/* 184:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.ManifoldResult
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */