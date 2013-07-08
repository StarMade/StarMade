/*   1:    */package com.bulletphysics.dynamics.vehicle;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.dynamics.RigidBody;
/*   5:    */import com.bulletphysics.dynamics.constraintsolver.ContactConstraint;
/*   6:    */import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*   7:    */import com.bulletphysics.dynamics.constraintsolver.TypedConstraintType;
/*   8:    */import com.bulletphysics.linearmath.MatrixUtil;
/*   9:    */import com.bulletphysics.linearmath.MiscUtil;
/*  10:    */import com.bulletphysics.linearmath.MotionState;
/*  11:    */import com.bulletphysics.linearmath.QuaternionUtil;
/*  12:    */import com.bulletphysics.linearmath.Transform;
/*  13:    */import com.bulletphysics.util.ArrayPool;
/*  14:    */import com.bulletphysics.util.FloatArrayList;
/*  15:    */import com.bulletphysics.util.ObjectArrayList;
/*  16:    */import javax.vecmath.Matrix3f;
/*  17:    */import javax.vecmath.Quat4f;
/*  18:    */import javax.vecmath.Tuple3f;
/*  19:    */import javax.vecmath.Vector3f;
/*  20:    */
/*  46:    */public class RaycastVehicle
/*  47:    */  extends TypedConstraint
/*  48:    */{
/*  49: 49 */  private final ArrayPool<float[]> floatArrays = ArrayPool.get(Float.TYPE);
/*  50:    */  
/*  51: 51 */  private static RigidBody s_fixedObject = new RigidBody(0.0F, null, null);
/*  52:    */  
/*  53:    */  private static final float sideFrictionStiffness2 = 1.0F;
/*  54: 54 */  protected ObjectArrayList<Vector3f> forwardWS = new ObjectArrayList();
/*  55: 55 */  protected ObjectArrayList<Vector3f> axle = new ObjectArrayList();
/*  56: 56 */  protected FloatArrayList forwardImpulse = new FloatArrayList();
/*  57: 57 */  protected FloatArrayList sideImpulse = new FloatArrayList();
/*  58:    */  
/*  59:    */  private float tau;
/*  60:    */  private float damping;
/*  61:    */  private VehicleRaycaster vehicleRaycaster;
/*  62: 62 */  private float pitchControl = 0.0F;
/*  63:    */  
/*  64:    */  private float steeringValue;
/*  65:    */  
/*  66:    */  private float currentVehicleSpeedKmHour;
/*  67:    */  private RigidBody chassisBody;
/*  68: 68 */  private int indexRightAxis = 0;
/*  69: 69 */  private int indexUpAxis = 2;
/*  70: 70 */  private int indexForwardAxis = 1;
/*  71:    */  
/*  72: 72 */  public ObjectArrayList<WheelInfo> wheelInfo = new ObjectArrayList();
/*  73:    */  
/*  74:    */  public RaycastVehicle(VehicleTuning tuning, RigidBody chassis, VehicleRaycaster raycaster)
/*  75:    */  {
/*  76: 76 */    super(TypedConstraintType.VEHICLE_CONSTRAINT_TYPE);
/*  77: 77 */    this.vehicleRaycaster = raycaster;
/*  78: 78 */    this.chassisBody = chassis;
/*  79: 79 */    defaultInit(tuning);
/*  80:    */  }
/*  81:    */  
/*  82:    */  private void defaultInit(VehicleTuning tuning) {
/*  83: 83 */    this.currentVehicleSpeedKmHour = 0.0F;
/*  84: 84 */    this.steeringValue = 0.0F;
/*  85:    */  }
/*  86:    */  
/*  89:    */  public WheelInfo addWheel(Vector3f connectionPointCS, Vector3f wheelDirectionCS0, Vector3f wheelAxleCS, float suspensionRestLength, float wheelRadius, VehicleTuning tuning, boolean isFrontWheel)
/*  90:    */  {
/*  91: 91 */    WheelInfoConstructionInfo ci = new WheelInfoConstructionInfo();
/*  92:    */    
/*  93: 93 */    ci.chassisConnectionCS.set(connectionPointCS);
/*  94: 94 */    ci.wheelDirectionCS.set(wheelDirectionCS0);
/*  95: 95 */    ci.wheelAxleCS.set(wheelAxleCS);
/*  96: 96 */    ci.suspensionRestLength = suspensionRestLength;
/*  97: 97 */    ci.wheelRadius = wheelRadius;
/*  98: 98 */    ci.suspensionStiffness = tuning.suspensionStiffness;
/*  99: 99 */    ci.wheelsDampingCompression = tuning.suspensionCompression;
/* 100:100 */    ci.wheelsDampingRelaxation = tuning.suspensionDamping;
/* 101:101 */    ci.frictionSlip = tuning.frictionSlip;
/* 102:102 */    ci.bIsFrontWheel = isFrontWheel;
/* 103:103 */    ci.maxSuspensionTravelCm = tuning.maxSuspensionTravelCm;
/* 104:    */    
/* 105:105 */    this.wheelInfo.add(new WheelInfo(ci));
/* 106:    */    
/* 107:107 */    WheelInfo wheel = (WheelInfo)this.wheelInfo.getQuick(getNumWheels() - 1);
/* 108:    */    
/* 109:109 */    updateWheelTransformsWS(wheel, false);
/* 110:110 */    updateWheelTransform(getNumWheels() - 1, false);
/* 111:111 */    return wheel;
/* 112:    */  }
/* 113:    */  
/* 114:    */  public Transform getWheelTransformWS(int wheelIndex, Transform out) {
/* 115:115 */    assert (wheelIndex < getNumWheels());
/* 116:116 */    WheelInfo wheel = (WheelInfo)this.wheelInfo.getQuick(wheelIndex);
/* 117:117 */    out.set(wheel.worldTransform);
/* 118:118 */    return out;
/* 119:    */  }
/* 120:    */  
/* 121:    */  public void updateWheelTransform(int wheelIndex) {
/* 122:122 */    updateWheelTransform(wheelIndex, true);
/* 123:    */  }
/* 124:    */  
/* 125:    */  public void updateWheelTransform(int arg1, boolean arg2) {
/* 126:126 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f(); .Stack tmp11_7 = tmp7_5;tmp11_7.push$javax$vecmath$Quat4f();tmp11_7.push$javax$vecmath$Matrix3f();WheelInfo wheel = (WheelInfo)this.wheelInfo.getQuick(wheelIndex);
/* 127:127 */      updateWheelTransformsWS(wheel, interpolatedTransform);
/* 128:128 */      Vector3f up = localStack.get$javax$vecmath$Vector3f();
/* 129:129 */      up.negate(wheel.raycastInfo.wheelDirectionWS);
/* 130:130 */      Vector3f right = wheel.raycastInfo.wheelAxleWS;
/* 131:131 */      Vector3f fwd = localStack.get$javax$vecmath$Vector3f();
/* 132:132 */      fwd.cross(up, right);
/* 133:133 */      fwd.normalize();
/* 134:    */      
/* 138:138 */      float steering = wheel.steering;
/* 139:    */      
/* 140:140 */      Quat4f steeringOrn = localStack.get$javax$vecmath$Quat4f();
/* 141:141 */      QuaternionUtil.setRotation(steeringOrn, up, steering);
/* 142:142 */      Matrix3f steeringMat = localStack.get$javax$vecmath$Matrix3f();
/* 143:143 */      MatrixUtil.setRotation(steeringMat, steeringOrn);
/* 144:    */      
/* 145:145 */      Quat4f rotatingOrn = localStack.get$javax$vecmath$Quat4f();
/* 146:146 */      QuaternionUtil.setRotation(rotatingOrn, right, -wheel.rotation);
/* 147:147 */      Matrix3f rotatingMat = localStack.get$javax$vecmath$Matrix3f();
/* 148:148 */      MatrixUtil.setRotation(rotatingMat, rotatingOrn);
/* 149:    */      
/* 150:150 */      Matrix3f basis2 = localStack.get$javax$vecmath$Matrix3f();
/* 151:151 */      basis2.setRow(0, right.x, fwd.x, up.x);
/* 152:152 */      basis2.setRow(1, right.y, fwd.y, up.y);
/* 153:153 */      basis2.setRow(2, right.z, fwd.z, up.z);
/* 154:    */      
/* 155:155 */      Matrix3f wheelBasis = wheel.worldTransform.basis;
/* 156:156 */      wheelBasis.mul(steeringMat, rotatingMat);
/* 157:157 */      wheelBasis.mul(basis2);
/* 158:    */      
/* 159:159 */      wheel.worldTransform.origin.scaleAdd(wheel.raycastInfo.suspensionLength, wheel.raycastInfo.wheelDirectionWS, wheel.raycastInfo.hardPointWS);
/* 160:160 */    } finally { .Stack tmp296_294 = localStack;tmp296_294.pop$javax$vecmath$Vector3f(); .Stack tmp300_296 = tmp296_294;tmp300_296.pop$javax$vecmath$Quat4f();tmp300_296.pop$javax$vecmath$Matrix3f();
/* 161:    */    }
/* 162:    */  }
/* 163:    */  
/* 164:164 */  public void resetSuspension() { for (int i = 0; i < this.wheelInfo.size(); i++) {
/* 165:165 */      WheelInfo wheel = (WheelInfo)this.wheelInfo.getQuick(i);
/* 166:166 */      wheel.raycastInfo.suspensionLength = wheel.getSuspensionRestLength();
/* 167:167 */      wheel.suspensionRelativeVelocity = 0.0F;
/* 168:    */      
/* 169:169 */      wheel.raycastInfo.contactNormalWS.negate(wheel.raycastInfo.wheelDirectionWS);
/* 170:    */      
/* 171:171 */      wheel.clippedInvContactDotSuspension = 1.0F;
/* 172:    */    }
/* 173:    */  }
/* 174:    */  
/* 175:    */  public void updateWheelTransformsWS(WheelInfo wheel) {
/* 176:176 */    updateWheelTransformsWS(wheel, true);
/* 177:    */  }
/* 178:    */  
/* 179:    */  public void updateWheelTransformsWS(WheelInfo arg1, boolean arg2) {
/* 180:180 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();wheel.raycastInfo.isInContact = false;
/* 181:    */      
/* 182:182 */      Transform chassisTrans = getChassisWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 183:183 */      if ((interpolatedTransform) && (getRigidBody().getMotionState() != null)) {
/* 184:184 */        getRigidBody().getMotionState().getWorldTransform(chassisTrans);
/* 185:    */      }
/* 186:    */      
/* 187:187 */      wheel.raycastInfo.hardPointWS.set(wheel.chassisConnectionPointCS);
/* 188:188 */      chassisTrans.transform(wheel.raycastInfo.hardPointWS);
/* 189:    */      
/* 190:190 */      wheel.raycastInfo.wheelDirectionWS.set(wheel.wheelDirectionCS);
/* 191:191 */      chassisTrans.basis.transform(wheel.raycastInfo.wheelDirectionWS);
/* 192:    */      
/* 193:193 */      wheel.raycastInfo.wheelAxleWS.set(wheel.wheelAxleCS);
/* 194:194 */      chassisTrans.basis.transform(wheel.raycastInfo.wheelAxleWS);
/* 195:195 */    } finally { localStack.pop$com$bulletphysics$linearmath$Transform();
/* 196:    */    } }
/* 197:    */  
/* 198:198 */  public float rayCast(WheelInfo arg1) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();updateWheelTransformsWS(wheel, false);
/* 199:    */      
/* 200:200 */      float depth = -1.0F;
/* 201:    */      
/* 202:202 */      float raylen = wheel.getSuspensionRestLength() + wheel.wheelsRadius;
/* 203:    */      
/* 204:204 */      Vector3f rayvector = localStack.get$javax$vecmath$Vector3f();
/* 205:205 */      rayvector.scale(raylen, wheel.raycastInfo.wheelDirectionWS);
/* 206:206 */      Vector3f source = wheel.raycastInfo.hardPointWS;
/* 207:207 */      wheel.raycastInfo.contactPointWS.add(source, rayvector);
/* 208:208 */      Vector3f target = wheel.raycastInfo.contactPointWS;
/* 209:    */      
/* 210:210 */      float param = 0.0F;
/* 211:    */      
/* 212:212 */      VehicleRaycasterResult rayResults = new VehicleRaycasterResult();
/* 213:    */      
/* 214:214 */      assert (this.vehicleRaycaster != null);
/* 215:    */      
/* 216:216 */      Object object = this.vehicleRaycaster.castRay(source, target, rayResults);
/* 217:    */      
/* 218:218 */      wheel.raycastInfo.groundObject = null;
/* 219:    */      
/* 220:220 */      if (object != null) {
/* 221:221 */        param = rayResults.distFraction;
/* 222:222 */        depth = raylen * rayResults.distFraction;
/* 223:223 */        wheel.raycastInfo.contactNormalWS.set(rayResults.hitNormalInWorld);
/* 224:224 */        wheel.raycastInfo.isInContact = true;
/* 225:    */        
/* 226:226 */        wheel.raycastInfo.groundObject = s_fixedObject;
/* 227:    */        
/* 229:229 */        float hitDistance = param * raylen;
/* 230:230 */        wheel.raycastInfo.suspensionLength = (hitDistance - wheel.wheelsRadius);
/* 231:    */        
/* 233:233 */        float minSuspensionLength = wheel.getSuspensionRestLength() - wheel.maxSuspensionTravelCm * 0.01F;
/* 234:234 */        float maxSuspensionLength = wheel.getSuspensionRestLength() + wheel.maxSuspensionTravelCm * 0.01F;
/* 235:235 */        if (wheel.raycastInfo.suspensionLength < minSuspensionLength) {
/* 236:236 */          wheel.raycastInfo.suspensionLength = minSuspensionLength;
/* 237:    */        }
/* 238:238 */        if (wheel.raycastInfo.suspensionLength > maxSuspensionLength) {
/* 239:239 */          wheel.raycastInfo.suspensionLength = maxSuspensionLength;
/* 240:    */        }
/* 241:    */        
/* 242:242 */        wheel.raycastInfo.contactPointWS.set(rayResults.hitPointInWorld);
/* 243:    */        
/* 244:244 */        float denominator = wheel.raycastInfo.contactNormalWS.dot(wheel.raycastInfo.wheelDirectionWS);
/* 245:    */        
/* 246:246 */        Vector3f chassis_velocity_at_contactPoint = localStack.get$javax$vecmath$Vector3f();
/* 247:247 */        Vector3f relpos = localStack.get$javax$vecmath$Vector3f();
/* 248:248 */        relpos.sub(wheel.raycastInfo.contactPointWS, getRigidBody().getCenterOfMassPosition(localStack.get$javax$vecmath$Vector3f()));
/* 249:    */        
/* 250:250 */        getRigidBody().getVelocityInLocalPoint(relpos, chassis_velocity_at_contactPoint);
/* 251:    */        
/* 252:252 */        float projVel = wheel.raycastInfo.contactNormalWS.dot(chassis_velocity_at_contactPoint);
/* 253:    */        
/* 254:254 */        if (denominator >= -0.1F) {
/* 255:255 */          wheel.suspensionRelativeVelocity = 0.0F;
/* 256:256 */          wheel.clippedInvContactDotSuspension = 10.0F;
/* 257:    */        }
/* 258:    */        else {
/* 259:259 */          float inv = -1.0F / denominator;
/* 260:260 */          wheel.suspensionRelativeVelocity = (projVel * inv);
/* 261:261 */          wheel.clippedInvContactDotSuspension = inv;
/* 262:    */        }
/* 263:    */        
/* 264:    */      }
/* 265:    */      else
/* 266:    */      {
/* 267:267 */        wheel.raycastInfo.suspensionLength = wheel.getSuspensionRestLength();
/* 268:268 */        wheel.suspensionRelativeVelocity = 0.0F;
/* 269:269 */        wheel.raycastInfo.contactNormalWS.negate(wheel.raycastInfo.wheelDirectionWS);
/* 270:270 */        wheel.clippedInvContactDotSuspension = 1.0F;
/* 271:    */      }
/* 272:    */      
/* 273:273 */      return depth; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 274:    */    }
/* 275:    */  }
/* 276:    */  
/* 284:    */  public Transform getChassisWorldTransform(Transform out)
/* 285:    */  {
/* 286:286 */    return getRigidBody().getCenterOfMassTransform(out);
/* 287:    */  }
/* 288:    */  
/* 289:    */  public void updateVehicle(float arg1) {
/* 290:290 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f(); for (int i = 0; i < getNumWheels(); i++) {
/* 291:291 */        updateWheelTransform(i, false);
/* 292:    */      }
/* 293:    */      
/* 294:294 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 295:    */      
/* 296:296 */      this.currentVehicleSpeedKmHour = (3.6F * getRigidBody().getLinearVelocity(tmp).length());
/* 297:    */      
/* 298:298 */      Transform chassisTrans = getChassisWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 299:    */      
/* 300:300 */      Vector3f forwardW = localStack.get$javax$vecmath$Vector3f();
/* 301:301 */      forwardW.set(chassisTrans.basis.getElement(0, this.indexForwardAxis), chassisTrans.basis.getElement(1, this.indexForwardAxis), chassisTrans.basis.getElement(2, this.indexForwardAxis));
/* 302:    */      
/* 306:306 */      if (forwardW.dot(getRigidBody().getLinearVelocity(tmp)) < 0.0F) {
/* 307:307 */        this.currentVehicleSpeedKmHour *= -1.0F;
/* 308:    */      }
/* 309:    */      
/* 314:314 */      int i = 0;
/* 315:315 */      float depth; for (i = 0; i < this.wheelInfo.size(); i++)
/* 316:    */      {
/* 317:317 */        depth = rayCast((WheelInfo)this.wheelInfo.getQuick(i));
/* 318:    */      }
/* 319:    */      
/* 320:320 */      updateSuspension(step);
/* 321:    */      
/* 322:322 */      for (i = 0; i < this.wheelInfo.size(); i++)
/* 323:    */      {
/* 324:324 */        WheelInfo wheel = (WheelInfo)this.wheelInfo.getQuick(i);
/* 325:    */        
/* 326:326 */        float suspensionForce = wheel.wheelsSuspensionForce;
/* 327:    */        
/* 328:328 */        float gMaxSuspensionForce = 6000.0F;
/* 329:329 */        if (suspensionForce > gMaxSuspensionForce) {
/* 330:330 */          suspensionForce = gMaxSuspensionForce;
/* 331:    */        }
/* 332:332 */        Vector3f impulse = localStack.get$javax$vecmath$Vector3f();
/* 333:333 */        impulse.scale(suspensionForce * step, wheel.raycastInfo.contactNormalWS);
/* 334:334 */        Vector3f relpos = localStack.get$javax$vecmath$Vector3f();
/* 335:335 */        relpos.sub(wheel.raycastInfo.contactPointWS, getRigidBody().getCenterOfMassPosition(tmp));
/* 336:    */        
/* 337:337 */        getRigidBody().applyImpulse(impulse, relpos);
/* 338:    */      }
/* 339:    */      
/* 340:340 */      updateFriction(step);
/* 341:    */      
/* 342:342 */      for (i = 0; i < this.wheelInfo.size(); i++) {
/* 343:343 */        WheelInfo wheel = (WheelInfo)this.wheelInfo.getQuick(i);
/* 344:344 */        Vector3f relpos = localStack.get$javax$vecmath$Vector3f();
/* 345:345 */        relpos.sub(wheel.raycastInfo.hardPointWS, getRigidBody().getCenterOfMassPosition(tmp));
/* 346:346 */        Vector3f vel = getRigidBody().getVelocityInLocalPoint(relpos, localStack.get$javax$vecmath$Vector3f());
/* 347:    */        
/* 348:348 */        if (wheel.raycastInfo.isInContact) {
/* 349:349 */          Transform chassisWorldTransform = getChassisWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 350:    */          
/* 351:351 */          Vector3f fwd = localStack.get$javax$vecmath$Vector3f();
/* 352:352 */          fwd.set(chassisWorldTransform.basis.getElement(0, this.indexForwardAxis), chassisWorldTransform.basis.getElement(1, this.indexForwardAxis), chassisWorldTransform.basis.getElement(2, this.indexForwardAxis));
/* 353:    */          
/* 357:357 */          float proj = fwd.dot(wheel.raycastInfo.contactNormalWS);
/* 358:358 */          tmp.scale(proj, wheel.raycastInfo.contactNormalWS);
/* 359:359 */          fwd.sub(tmp);
/* 360:    */          
/* 361:361 */          float proj2 = fwd.dot(vel);
/* 362:    */          
/* 363:363 */          wheel.deltaRotation = (proj2 * step / wheel.wheelsRadius);
/* 364:364 */          wheel.rotation += wheel.deltaRotation;
/* 365:    */        }
/* 366:    */        else
/* 367:    */        {
/* 368:368 */          wheel.rotation += wheel.deltaRotation;
/* 369:    */        }
/* 370:    */        
/* 371:371 */        wheel.deltaRotation *= 0.99F;
/* 372:    */      }
/* 373:373 */    } finally { .Stack tmp592_590 = localStack;tmp592_590.pop$com$bulletphysics$linearmath$Transform();tmp592_590.pop$javax$vecmath$Vector3f();
/* 374:    */    } }
/* 375:    */  
/* 376:376 */  public void setSteeringValue(float steering, int wheel) { assert ((wheel >= 0) && (wheel < getNumWheels()));
/* 377:    */    
/* 378:378 */    WheelInfo wheel_info = getWheelInfo(wheel);
/* 379:379 */    wheel_info.steering = steering;
/* 380:    */  }
/* 381:    */  
/* 382:    */  public float getSteeringValue(int wheel) {
/* 383:383 */    return getWheelInfo(wheel).steering;
/* 384:    */  }
/* 385:    */  
/* 386:    */  public void applyEngineForce(float force, int wheel) {
/* 387:387 */    assert ((wheel >= 0) && (wheel < getNumWheels()));
/* 388:388 */    WheelInfo wheel_info = getWheelInfo(wheel);
/* 389:389 */    wheel_info.engineForce = force;
/* 390:    */  }
/* 391:    */  
/* 392:    */  public WheelInfo getWheelInfo(int index) {
/* 393:393 */    assert ((index >= 0) && (index < getNumWheels()));
/* 394:    */    
/* 395:395 */    return (WheelInfo)this.wheelInfo.getQuick(index);
/* 396:    */  }
/* 397:    */  
/* 398:    */  public void setBrake(float brake, int wheelIndex) {
/* 399:399 */    assert ((wheelIndex >= 0) && (wheelIndex < getNumWheels()));
/* 400:400 */    getWheelInfo(wheelIndex).brake = brake;
/* 401:    */  }
/* 402:    */  
/* 403:    */  public void updateSuspension(float deltaTime) {
/* 404:404 */    float chassisMass = 1.0F / this.chassisBody.getInvMass();
/* 405:    */    
/* 406:406 */    for (int w_it = 0; w_it < getNumWheels(); w_it++) {
/* 407:407 */      WheelInfo wheel_info = (WheelInfo)this.wheelInfo.getQuick(w_it);
/* 408:    */      
/* 409:409 */      if (wheel_info.raycastInfo.isInContact)
/* 410:    */      {
/* 413:413 */        float susp_length = wheel_info.getSuspensionRestLength();
/* 414:414 */        float current_length = wheel_info.raycastInfo.suspensionLength;
/* 415:    */        
/* 416:416 */        float length_diff = susp_length - current_length;
/* 417:    */        
/* 418:418 */        float force = wheel_info.suspensionStiffness * length_diff * wheel_info.clippedInvContactDotSuspension;
/* 419:    */        
/* 423:423 */        float projected_rel_vel = wheel_info.suspensionRelativeVelocity;
/* 424:    */        float susp_damping;
/* 425:    */        float susp_damping;
/* 426:426 */        if (projected_rel_vel < 0.0F) {
/* 427:427 */          susp_damping = wheel_info.wheelsDampingCompression;
/* 428:    */        }
/* 429:    */        else {
/* 430:430 */          susp_damping = wheel_info.wheelsDampingRelaxation;
/* 431:    */        }
/* 432:432 */        force -= susp_damping * projected_rel_vel;
/* 433:    */        
/* 437:437 */        wheel_info.wheelsSuspensionForce = (force * chassisMass);
/* 438:438 */        if (wheel_info.wheelsSuspensionForce < 0.0F) {
/* 439:439 */          wheel_info.wheelsSuspensionForce = 0.0F;
/* 440:    */        }
/* 441:    */      }
/* 442:    */      else {
/* 443:443 */        wheel_info.wheelsSuspensionForce = 0.0F;
/* 444:    */      }
/* 445:    */    }
/* 446:    */  }
/* 447:    */  
/* 448:    */  private float calcRollingFriction(WheelContactPoint arg1) {
/* 449:449 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 450:    */      
/* 451:451 */      float j1 = 0.0F;
/* 452:    */      
/* 453:453 */      Vector3f contactPosWorld = contactPoint.frictionPositionWorld;
/* 454:    */      
/* 455:455 */      Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/* 456:456 */      rel_pos1.sub(contactPosWorld, contactPoint.body0.getCenterOfMassPosition(tmp));
/* 457:457 */      Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 458:458 */      rel_pos2.sub(contactPosWorld, contactPoint.body1.getCenterOfMassPosition(tmp));
/* 459:    */      
/* 460:460 */      float maxImpulse = contactPoint.maxImpulse;
/* 461:    */      
/* 462:462 */      Vector3f vel1 = contactPoint.body0.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
/* 463:463 */      Vector3f vel2 = contactPoint.body1.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
/* 464:464 */      Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 465:465 */      vel.sub(vel1, vel2);
/* 466:    */      
/* 467:467 */      float vrel = contactPoint.frictionDirectionWorld.dot(vel);
/* 468:    */      
/* 470:470 */      j1 = -vrel * contactPoint.jacDiagABInv;
/* 471:471 */      j1 = Math.min(j1, maxImpulse);
/* 472:472 */      return Math.max(j1, -maxImpulse);
/* 473:    */    } finally {
/* 474:474 */      localStack.pop$javax$vecmath$Vector3f();
/* 475:    */    }
/* 476:    */  }
/* 477:    */  
/* 478:    */  public void updateFriction(float arg1) {
/* 479:479 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform(); .Stack tmp11_7 = tmp7_5;tmp11_7.push$javax$vecmath$Vector3f();tmp11_7.push$javax$vecmath$Matrix3f();int numWheel = getNumWheels();
/* 480:480 */      if (numWheel == 0) {
/* 481:481 */        return;
/* 482:    */      }
/* 483:    */      
/* 484:484 */      MiscUtil.resize(this.forwardWS, numWheel, Vector3f.class);
/* 485:485 */      MiscUtil.resize(this.axle, numWheel, Vector3f.class);
/* 486:486 */      MiscUtil.resize(this.forwardImpulse, numWheel, 0.0F);
/* 487:487 */      MiscUtil.resize(this.sideImpulse, numWheel, 0.0F);
/* 488:    */      
/* 489:489 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 490:    */      
/* 491:491 */      int numWheelsOnGround = 0;
/* 492:    */      
/* 494:494 */      for (int i = 0; i < getNumWheels(); i++) {
/* 495:495 */        WheelInfo wheel_info = (WheelInfo)this.wheelInfo.getQuick(i);
/* 496:496 */        RigidBody groundObject = (RigidBody)wheel_info.raycastInfo.groundObject;
/* 497:497 */        if (groundObject != null) {
/* 498:498 */          numWheelsOnGround++;
/* 499:    */        }
/* 500:500 */        this.sideImpulse.set(i, 0.0F);
/* 501:501 */        this.forwardImpulse.set(i, 0.0F);
/* 502:    */      }
/* 503:    */      
/* 505:505 */      Transform wheelTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 506:506 */      for (int i = 0; i < getNumWheels(); i++)
/* 507:    */      {
/* 508:508 */        WheelInfo wheel_info = (WheelInfo)this.wheelInfo.getQuick(i);
/* 509:    */        
/* 510:510 */        RigidBody groundObject = (RigidBody)wheel_info.raycastInfo.groundObject;
/* 511:    */        
/* 512:512 */        if (groundObject != null) {
/* 513:513 */          getWheelTransformWS(i, wheelTrans);
/* 514:    */          
/* 515:515 */          Matrix3f wheelBasis0 = localStack.get$javax$vecmath$Matrix3f(wheelTrans.basis);
/* 516:516 */          ((Vector3f)this.axle.getQuick(i)).set(wheelBasis0.getElement(0, this.indexRightAxis), wheelBasis0.getElement(1, this.indexRightAxis), wheelBasis0.getElement(2, this.indexRightAxis));
/* 517:    */          
/* 521:521 */          Vector3f surfNormalWS = wheel_info.raycastInfo.contactNormalWS;
/* 522:522 */          float proj = ((Vector3f)this.axle.getQuick(i)).dot(surfNormalWS);
/* 523:523 */          tmp.scale(proj, surfNormalWS);
/* 524:524 */          ((Vector3f)this.axle.getQuick(i)).sub(tmp);
/* 525:525 */          ((Vector3f)this.axle.getQuick(i)).normalize();
/* 526:    */          
/* 527:527 */          ((Vector3f)this.forwardWS.getQuick(i)).cross(surfNormalWS, (Vector3f)this.axle.getQuick(i));
/* 528:528 */          ((Vector3f)this.forwardWS.getQuick(i)).normalize();
/* 529:    */          
/* 530:530 */          float[] floatPtr = (float[])this.floatArrays.getFixed(1);
/* 531:531 */          ContactConstraint.resolveSingleBilateral(this.chassisBody, wheel_info.raycastInfo.contactPointWS, groundObject, wheel_info.raycastInfo.contactPointWS, 0.0F, (Vector3f)this.axle.getQuick(i), floatPtr, timeStep);
/* 532:    */          
/* 534:534 */          this.sideImpulse.set(i, floatPtr[0]);
/* 535:535 */          this.floatArrays.release(floatPtr);
/* 536:    */          
/* 537:537 */          this.sideImpulse.set(i, this.sideImpulse.get(i) * 1.0F);
/* 538:    */        }
/* 539:    */      }
/* 540:    */      
/* 542:542 */      float sideFactor = 1.0F;
/* 543:543 */      float fwdFactor = 0.5F;
/* 544:    */      
/* 545:545 */      boolean sliding = false;
/* 546:    */      
/* 547:547 */      for (int wheel = 0; wheel < getNumWheels(); wheel++) {
/* 548:548 */        WheelInfo wheel_info = (WheelInfo)this.wheelInfo.getQuick(wheel);
/* 549:549 */        RigidBody groundObject = (RigidBody)wheel_info.raycastInfo.groundObject;
/* 550:    */        
/* 551:551 */        float rollingFriction = 0.0F;
/* 552:    */        
/* 553:553 */        if (groundObject != null) {
/* 554:554 */          if (wheel_info.engineForce != 0.0F) {
/* 555:555 */            rollingFriction = wheel_info.engineForce * timeStep;
/* 556:    */          }
/* 557:    */          else {
/* 558:558 */            float defaultRollingFrictionImpulse = 0.0F;
/* 559:559 */            float maxImpulse = wheel_info.brake != 0.0F ? wheel_info.brake : defaultRollingFrictionImpulse;
/* 560:560 */            WheelContactPoint contactPt = new WheelContactPoint(this.chassisBody, groundObject, wheel_info.raycastInfo.contactPointWS, (Vector3f)this.forwardWS.getQuick(wheel), maxImpulse);
/* 561:561 */            rollingFriction = calcRollingFriction(contactPt);
/* 562:    */          }
/* 563:    */        }
/* 564:    */        
/* 567:567 */        this.forwardImpulse.set(wheel, 0.0F);
/* 568:568 */        ((WheelInfo)this.wheelInfo.getQuick(wheel)).skidInfo = 1.0F;
/* 569:    */        
/* 570:570 */        if (groundObject != null) {
/* 571:571 */          ((WheelInfo)this.wheelInfo.getQuick(wheel)).skidInfo = 1.0F;
/* 572:    */          
/* 573:573 */          float maximp = wheel_info.wheelsSuspensionForce * timeStep * wheel_info.frictionSlip;
/* 574:574 */          float maximpSide = maximp;
/* 575:    */          
/* 576:576 */          float maximpSquared = maximp * maximpSide;
/* 577:    */          
/* 578:578 */          this.forwardImpulse.set(wheel, rollingFriction);
/* 579:    */          
/* 580:580 */          float x = this.forwardImpulse.get(wheel) * fwdFactor;
/* 581:581 */          float y = this.sideImpulse.get(wheel) * sideFactor;
/* 582:    */          
/* 583:583 */          float impulseSquared = x * x + y * y;
/* 584:    */          
/* 585:585 */          if (impulseSquared > maximpSquared) {
/* 586:586 */            sliding = true;
/* 587:    */            
/* 588:588 */            float factor = maximp / (float)Math.sqrt(impulseSquared);
/* 589:    */            
/* 590:590 */            ((WheelInfo)this.wheelInfo.getQuick(wheel)).skidInfo *= factor;
/* 591:    */          }
/* 592:    */        }
/* 593:    */      }
/* 594:    */      
/* 597:597 */      if (sliding) {
/* 598:598 */        for (int wheel = 0; wheel < getNumWheels(); wheel++) {
/* 599:599 */          if ((this.sideImpulse.get(wheel) != 0.0F) && 
/* 600:600 */            (((WheelInfo)this.wheelInfo.getQuick(wheel)).skidInfo < 1.0F)) {
/* 601:601 */            this.forwardImpulse.set(wheel, this.forwardImpulse.get(wheel) * ((WheelInfo)this.wheelInfo.getQuick(wheel)).skidInfo);
/* 602:602 */            this.sideImpulse.set(wheel, this.sideImpulse.get(wheel) * ((WheelInfo)this.wheelInfo.getQuick(wheel)).skidInfo);
/* 603:    */          }
/* 604:    */        }
/* 605:    */      }
/* 606:    */      
/* 610:610 */      for (int wheel = 0; wheel < getNumWheels(); wheel++) {
/* 611:611 */        WheelInfo wheel_info = (WheelInfo)this.wheelInfo.getQuick(wheel);
/* 612:    */        
/* 613:613 */        Vector3f rel_pos = localStack.get$javax$vecmath$Vector3f();
/* 614:614 */        rel_pos.sub(wheel_info.raycastInfo.contactPointWS, this.chassisBody.getCenterOfMassPosition(tmp));
/* 615:    */        
/* 616:616 */        if (this.forwardImpulse.get(wheel) != 0.0F) {
/* 617:617 */          tmp.scale(this.forwardImpulse.get(wheel), (Tuple3f)this.forwardWS.getQuick(wheel));
/* 618:618 */          this.chassisBody.applyImpulse(tmp, rel_pos);
/* 619:    */        }
/* 620:620 */        if (this.sideImpulse.get(wheel) != 0.0F) {
/* 621:621 */          RigidBody groundObject = (RigidBody)((WheelInfo)this.wheelInfo.getQuick(wheel)).raycastInfo.groundObject;
/* 622:    */          
/* 623:623 */          Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 624:624 */          rel_pos2.sub(wheel_info.raycastInfo.contactPointWS, groundObject.getCenterOfMassPosition(tmp));
/* 625:    */          
/* 626:626 */          Vector3f sideImp = localStack.get$javax$vecmath$Vector3f();
/* 627:627 */          sideImp.scale(this.sideImpulse.get(wheel), (Tuple3f)this.axle.getQuick(wheel));
/* 628:    */          
/* 629:629 */          rel_pos.z *= wheel_info.rollInfluence;
/* 630:630 */          this.chassisBody.applyImpulse(sideImp, rel_pos);
/* 631:    */          
/* 633:633 */          tmp.negate(sideImp);
/* 634:634 */          groundObject.applyImpulse(tmp, rel_pos2);
/* 635:    */        }
/* 636:    */      }
/* 637:    */    } finally {
/* 638:638 */      .Stack tmp1205_1203 = localStack;tmp1205_1203.pop$com$bulletphysics$linearmath$Transform(); .Stack tmp1209_1205 = tmp1205_1203;tmp1209_1205.pop$javax$vecmath$Vector3f();tmp1209_1205.pop$javax$vecmath$Matrix3f();
/* 639:    */    }
/* 640:    */  }
/* 641:    */  
/* 643:    */  public void buildJacobian() {}
/* 644:    */  
/* 646:    */  public void solveConstraint(float timeStep) {}
/* 647:    */  
/* 649:    */  public int getNumWheels()
/* 650:    */  {
/* 651:651 */    return this.wheelInfo.size();
/* 652:    */  }
/* 653:    */  
/* 654:    */  public void setPitchControl(float pitch) {
/* 655:655 */    this.pitchControl = pitch;
/* 656:    */  }
/* 657:    */  
/* 658:    */  public RigidBody getRigidBody() {
/* 659:659 */    return this.chassisBody;
/* 660:    */  }
/* 661:    */  
/* 662:    */  public int getRightAxis() {
/* 663:663 */    return this.indexRightAxis;
/* 664:    */  }
/* 665:    */  
/* 666:    */  public int getUpAxis() {
/* 667:667 */    return this.indexUpAxis;
/* 668:    */  }
/* 669:    */  
/* 670:    */  public int getForwardAxis() {
/* 671:671 */    return this.indexForwardAxis;
/* 672:    */  }
/* 673:    */  
/* 676:    */  public Vector3f getForwardVector(Vector3f arg1)
/* 677:    */  {
/* 678:678 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();Transform chassisTrans = getChassisWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 679:    */      
/* 680:680 */      out.set(chassisTrans.basis.getElement(0, this.indexForwardAxis), chassisTrans.basis.getElement(1, this.indexForwardAxis), chassisTrans.basis.getElement(2, this.indexForwardAxis));
/* 681:    */      
/* 685:685 */      return out; } finally { localStack.pop$com$bulletphysics$linearmath$Transform();
/* 686:    */    }
/* 687:    */  }
/* 688:    */  
/* 690:    */  public float getCurrentSpeedKmHour()
/* 691:    */  {
/* 692:692 */    return this.currentVehicleSpeedKmHour;
/* 693:    */  }
/* 694:    */  
/* 695:    */  public void setCoordinateSystem(int rightIndex, int upIndex, int forwardIndex) {
/* 696:696 */    this.indexRightAxis = rightIndex;
/* 697:697 */    this.indexUpAxis = upIndex;
/* 698:698 */    this.indexForwardAxis = forwardIndex;
/* 699:    */  }
/* 700:    */  
/* 702:    */  private static class WheelContactPoint
/* 703:    */  {
/* 704:    */    public RigidBody body0;
/* 705:    */    public RigidBody body1;
/* 706:706 */    public final Vector3f frictionPositionWorld = new Vector3f();
/* 707:707 */    public final Vector3f frictionDirectionWorld = new Vector3f();
/* 708:    */    public float jacDiagABInv;
/* 709:    */    public float maxImpulse;
/* 710:    */    
/* 711:    */    public WheelContactPoint(RigidBody body0, RigidBody body1, Vector3f frictionPosWorld, Vector3f frictionDirectionWorld, float maxImpulse) {
/* 712:712 */      this.body0 = body0;
/* 713:713 */      this.body1 = body1;
/* 714:714 */      this.frictionPositionWorld.set(frictionPosWorld);
/* 715:715 */      this.frictionDirectionWorld.set(frictionDirectionWorld);
/* 716:716 */      this.maxImpulse = maxImpulse;
/* 717:    */      
/* 718:718 */      float denom0 = body0.computeImpulseDenominator(frictionPosWorld, frictionDirectionWorld);
/* 719:719 */      float denom1 = body1.computeImpulseDenominator(frictionPosWorld, frictionDirectionWorld);
/* 720:720 */      float relaxation = 1.0F;
/* 721:721 */      this.jacDiagABInv = (relaxation / (denom0 + denom1));
/* 722:    */    }
/* 723:    */  }
/* 724:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.vehicle.RaycastVehicle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */