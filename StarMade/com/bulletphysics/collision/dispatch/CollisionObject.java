/*   1:    */package com.bulletphysics.collision.dispatch;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*   4:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*   5:    */import com.bulletphysics.linearmath.Transform;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */
/*  41:    */public class CollisionObject
/*  42:    */{
/*  43:    */  public static final int ACTIVE_TAG = 1;
/*  44:    */  public static final int ISLAND_SLEEPING = 2;
/*  45:    */  public static final int WANTS_DEACTIVATION = 3;
/*  46:    */  public static final int DISABLE_DEACTIVATION = 4;
/*  47:    */  public static final int DISABLE_SIMULATION = 5;
/*  48: 48 */  protected Transform worldTransform = new Transform();
/*  49:    */  
/*  52: 52 */  protected final Transform interpolationWorldTransform = new Transform();
/*  53:    */  
/*  55: 55 */  protected final Vector3f interpolationLinearVelocity = new Vector3f();
/*  56: 56 */  protected final Vector3f interpolationAngularVelocity = new Vector3f();
/*  57:    */  
/*  58:    */  protected BroadphaseProxy broadphaseHandle;
/*  59:    */  
/*  60:    */  protected CollisionShape collisionShape;
/*  61:    */  
/*  62:    */  protected CollisionShape rootCollisionShape;
/*  63:    */  
/*  64:    */  protected int collisionFlags;
/*  65:    */  
/*  66:    */  protected int islandTag1;
/*  67:    */  
/*  68:    */  protected int companionId;
/*  69:    */  
/*  70:    */  protected int activationState1;
/*  71:    */  
/*  72:    */  protected float deactivationTime;
/*  73:    */  
/*  74:    */  protected float friction;
/*  75:    */  
/*  76:    */  protected float restitution;
/*  77:    */  protected Object userObjectPointer;
/*  78: 78 */  protected CollisionObjectType internalType = CollisionObjectType.COLLISION_OBJECT;
/*  79:    */  
/*  81:    */  protected float hitFraction;
/*  82:    */  
/*  84:    */  protected float ccdSweptSphereRadius;
/*  85:    */  
/*  86: 86 */  protected float ccdMotionThreshold = 0.0F;
/*  87:    */  protected boolean checkCollideWith;
/*  88:    */  
/*  89:    */  public CollisionObject()
/*  90:    */  {
/*  91: 91 */    this.collisionFlags = 1;
/*  92: 92 */    this.islandTag1 = -1;
/*  93: 93 */    this.companionId = -1;
/*  94: 94 */    this.activationState1 = 1;
/*  95: 95 */    this.friction = 0.5F;
/*  96: 96 */    this.hitFraction = 1.0F;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public boolean checkCollideWithOverride(CollisionObject co) {
/* 100:100 */    return true;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public boolean mergesSimulationIslands()
/* 104:    */  {
/* 105:105 */    return (this.collisionFlags & 0x7) == 0;
/* 106:    */  }
/* 107:    */  
/* 108:    */  public boolean isStaticObject() {
/* 109:109 */    return (this.collisionFlags & 0x1) != 0;
/* 110:    */  }
/* 111:    */  
/* 112:    */  public boolean isKinematicObject() {
/* 113:113 */    return (this.collisionFlags & 0x2) != 0;
/* 114:    */  }
/* 115:    */  
/* 116:    */  public boolean isStaticOrKinematicObject() {
/* 117:117 */    return (this.collisionFlags & 0x3) != 0;
/* 118:    */  }
/* 119:    */  
/* 120:    */  public boolean hasContactResponse() {
/* 121:121 */    return (this.collisionFlags & 0x4) == 0;
/* 122:    */  }
/* 123:    */  
/* 124:    */  public CollisionShape getCollisionShape() {
/* 125:125 */    return this.collisionShape;
/* 126:    */  }
/* 127:    */  
/* 128:    */  public void setCollisionShape(CollisionShape collisionShape) {
/* 129:129 */    this.collisionShape = collisionShape;
/* 130:130 */    this.rootCollisionShape = collisionShape;
/* 131:    */  }
/* 132:    */  
/* 133:    */  public CollisionShape getRootCollisionShape() {
/* 134:134 */    return this.rootCollisionShape;
/* 135:    */  }
/* 136:    */  
/* 140:    */  public void internalSetTemporaryCollisionShape(CollisionShape collisionShape)
/* 141:    */  {
/* 142:142 */    this.collisionShape = collisionShape;
/* 143:    */  }
/* 144:    */  
/* 145:    */  public int getActivationState() {
/* 146:146 */    return this.activationState1;
/* 147:    */  }
/* 148:    */  
/* 149:    */  public void setActivationState(int newState) {
/* 150:150 */    if ((this.activationState1 != 4) && (this.activationState1 != 5)) {
/* 151:151 */      this.activationState1 = newState;
/* 152:    */    }
/* 153:    */  }
/* 154:    */  
/* 155:    */  public float getDeactivationTime() {
/* 156:156 */    return this.deactivationTime;
/* 157:    */  }
/* 158:    */  
/* 159:    */  public void setDeactivationTime(float deactivationTime) {
/* 160:160 */    this.deactivationTime = deactivationTime;
/* 161:    */  }
/* 162:    */  
/* 163:    */  public void forceActivationState(int newState) {
/* 164:164 */    this.activationState1 = newState;
/* 165:    */  }
/* 166:    */  
/* 167:    */  public void activate() {
/* 168:168 */    activate(false);
/* 169:    */  }
/* 170:    */  
/* 171:    */  public void activate(boolean forceActivation) {
/* 172:172 */    if ((forceActivation) || ((this.collisionFlags & 0x3) == 0)) {
/* 173:173 */      setActivationState(1);
/* 174:174 */      this.deactivationTime = 0.0F;
/* 175:    */    }
/* 176:    */  }
/* 177:    */  
/* 178:    */  public boolean isActive() {
/* 179:179 */    return (getActivationState() != 2) && (getActivationState() != 5);
/* 180:    */  }
/* 181:    */  
/* 182:    */  public float getRestitution() {
/* 183:183 */    return this.restitution;
/* 184:    */  }
/* 185:    */  
/* 186:    */  public void setRestitution(float restitution) {
/* 187:187 */    this.restitution = restitution;
/* 188:    */  }
/* 189:    */  
/* 190:    */  public float getFriction() {
/* 191:191 */    return this.friction;
/* 192:    */  }
/* 193:    */  
/* 194:    */  public void setFriction(float friction) {
/* 195:195 */    this.friction = friction;
/* 196:    */  }
/* 197:    */  
/* 198:    */  public CollisionObjectType getInternalType()
/* 199:    */  {
/* 200:200 */    return this.internalType;
/* 201:    */  }
/* 202:    */  
/* 203:    */  public Transform getWorldTransform(Transform out) {
/* 204:204 */    out.set(this.worldTransform);
/* 205:205 */    return out;
/* 206:    */  }
/* 207:    */  
/* 208:    */  public void setWorldTransform(Transform worldTransform) {
/* 209:209 */    this.worldTransform.set(worldTransform);
/* 210:    */  }
/* 211:    */  
/* 212:    */  public BroadphaseProxy getBroadphaseHandle() {
/* 213:213 */    return this.broadphaseHandle;
/* 214:    */  }
/* 215:    */  
/* 216:    */  public void setBroadphaseHandle(BroadphaseProxy broadphaseHandle) {
/* 217:217 */    this.broadphaseHandle = broadphaseHandle;
/* 218:    */  }
/* 219:    */  
/* 220:    */  public Transform getInterpolationWorldTransform(Transform out) {
/* 221:221 */    out.set(this.interpolationWorldTransform);
/* 222:222 */    return out;
/* 223:    */  }
/* 224:    */  
/* 225:    */  public void setInterpolationWorldTransform(Transform interpolationWorldTransform) {
/* 226:226 */    this.interpolationWorldTransform.set(interpolationWorldTransform);
/* 227:    */  }
/* 228:    */  
/* 229:    */  public void setInterpolationLinearVelocity(Vector3f linvel) {
/* 230:230 */    this.interpolationLinearVelocity.set(linvel);
/* 231:    */  }
/* 232:    */  
/* 233:    */  public void setInterpolationAngularVelocity(Vector3f angvel) {
/* 234:234 */    this.interpolationAngularVelocity.set(angvel);
/* 235:    */  }
/* 236:    */  
/* 237:    */  public Vector3f getInterpolationLinearVelocity(Vector3f out) {
/* 238:238 */    out.set(this.interpolationLinearVelocity);
/* 239:239 */    return out;
/* 240:    */  }
/* 241:    */  
/* 242:    */  public Vector3f getInterpolationAngularVelocity(Vector3f out) {
/* 243:243 */    out.set(this.interpolationAngularVelocity);
/* 244:244 */    return out;
/* 245:    */  }
/* 246:    */  
/* 247:    */  public int getIslandTag() {
/* 248:248 */    return this.islandTag1;
/* 249:    */  }
/* 250:    */  
/* 251:    */  public void setIslandTag(int islandTag) {
/* 252:252 */    this.islandTag1 = islandTag;
/* 253:    */  }
/* 254:    */  
/* 255:    */  public int getCompanionId() {
/* 256:256 */    return this.companionId;
/* 257:    */  }
/* 258:    */  
/* 259:    */  public void setCompanionId(int companionId) {
/* 260:260 */    this.companionId = companionId;
/* 261:    */  }
/* 262:    */  
/* 263:    */  public float getHitFraction() {
/* 264:264 */    return this.hitFraction;
/* 265:    */  }
/* 266:    */  
/* 267:    */  public void setHitFraction(float hitFraction) {
/* 268:268 */    this.hitFraction = hitFraction;
/* 269:    */  }
/* 270:    */  
/* 271:    */  public int getCollisionFlags() {
/* 272:272 */    return this.collisionFlags;
/* 273:    */  }
/* 274:    */  
/* 275:    */  public void setCollisionFlags(int collisionFlags) {
/* 276:276 */    this.collisionFlags = collisionFlags;
/* 277:    */  }
/* 278:    */  
/* 279:    */  public float getCcdSweptSphereRadius()
/* 280:    */  {
/* 281:281 */    return this.ccdSweptSphereRadius;
/* 282:    */  }
/* 283:    */  
/* 284:    */  public void setCcdSweptSphereRadius(float ccdSweptSphereRadius)
/* 285:    */  {
/* 286:286 */    this.ccdSweptSphereRadius = ccdSweptSphereRadius;
/* 287:    */  }
/* 288:    */  
/* 289:    */  public float getCcdMotionThreshold() {
/* 290:290 */    return this.ccdMotionThreshold;
/* 291:    */  }
/* 292:    */  
/* 293:    */  public float getCcdSquareMotionThreshold() {
/* 294:294 */    return this.ccdMotionThreshold * this.ccdMotionThreshold;
/* 295:    */  }
/* 296:    */  
/* 298:    */  public void setCcdMotionThreshold(float ccdMotionThreshold)
/* 299:    */  {
/* 300:300 */    this.ccdMotionThreshold = ccdMotionThreshold;
/* 301:    */  }
/* 302:    */  
/* 303:    */  public Object getUserPointer() {
/* 304:304 */    return this.userObjectPointer;
/* 305:    */  }
/* 306:    */  
/* 307:    */  public void setUserPointer(Object userObjectPointer) {
/* 308:308 */    this.userObjectPointer = userObjectPointer;
/* 309:    */  }
/* 310:    */  
/* 311:    */  public boolean checkCollideWith(CollisionObject co) {
/* 312:312 */    if (this.checkCollideWith) {
/* 313:313 */      return checkCollideWithOverride(co);
/* 314:    */    }
/* 315:    */    
/* 316:316 */    return true;
/* 317:    */  }
/* 318:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.CollisionObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */