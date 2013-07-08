/*   1:    */package com.bulletphysics.dynamics.constraintsolver;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.dynamics.RigidBody;
/*   5:    */import com.bulletphysics.linearmath.QuaternionUtil;
/*   6:    */import com.bulletphysics.linearmath.ScalarUtil;
/*   7:    */import com.bulletphysics.linearmath.Transform;
/*   8:    */import com.bulletphysics.linearmath.TransformUtil;
/*   9:    */import javax.vecmath.Matrix3f;
/*  10:    */import javax.vecmath.Quat4f;
/*  11:    */import javax.vecmath.Vector3f;
/*  12:    */
/*  43:    */public class ConeTwistConstraint
/*  44:    */  extends TypedConstraint
/*  45:    */{
/*  46: 46 */  private JacobianEntry[] jac = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
/*  47:    */  
/*  48: 48 */  private final Transform rbAFrame = new Transform();
/*  49: 49 */  private final Transform rbBFrame = new Transform();
/*  50:    */  
/*  51:    */  private float limitSoftness;
/*  52:    */  
/*  53:    */  private float biasFactor;
/*  54:    */  
/*  55:    */  private float relaxationFactor;
/*  56:    */  private float swingSpan1;
/*  57:    */  private float swingSpan2;
/*  58:    */  private float twistSpan;
/*  59: 59 */  private final Vector3f swingAxis = new Vector3f();
/*  60: 60 */  private final Vector3f twistAxis = new Vector3f();
/*  61:    */  
/*  62:    */  private float kSwing;
/*  63:    */  
/*  64:    */  private float kTwist;
/*  65:    */  
/*  66:    */  private float twistLimitSign;
/*  67:    */  
/*  68:    */  private float swingCorrection;
/*  69:    */  private float twistCorrection;
/*  70:    */  private float accSwingLimitImpulse;
/*  71:    */  private float accTwistLimitImpulse;
/*  72: 72 */  private boolean angularOnly = false;
/*  73:    */  private boolean solveTwistLimit;
/*  74:    */  private boolean solveSwingLimit;
/*  75:    */  
/*  76:    */  public ConeTwistConstraint() {
/*  77: 77 */    super(TypedConstraintType.CONETWIST_CONSTRAINT_TYPE);
/*  78:    */  }
/*  79:    */  
/*  80:    */  public ConeTwistConstraint(RigidBody rbA, RigidBody rbB, Transform rbAFrame, Transform rbBFrame) {
/*  81: 81 */    super(TypedConstraintType.CONETWIST_CONSTRAINT_TYPE, rbA, rbB);
/*  82: 82 */    this.rbAFrame.set(rbAFrame);
/*  83: 83 */    this.rbBFrame.set(rbBFrame);
/*  84:    */    
/*  85: 85 */    this.swingSpan1 = 1.0E+030F;
/*  86: 86 */    this.swingSpan2 = 1.0E+030F;
/*  87: 87 */    this.twistSpan = 1.0E+030F;
/*  88: 88 */    this.biasFactor = 0.3F;
/*  89: 89 */    this.relaxationFactor = 1.0F;
/*  90:    */    
/*  91: 91 */    this.solveTwistLimit = false;
/*  92: 92 */    this.solveSwingLimit = false;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public ConeTwistConstraint(RigidBody rbA, Transform rbAFrame) {
/*  96: 96 */    super(TypedConstraintType.CONETWIST_CONSTRAINT_TYPE, rbA);
/*  97: 97 */    this.rbAFrame.set(rbAFrame);
/*  98: 98 */    this.rbBFrame.set(this.rbAFrame);
/*  99:    */    
/* 100:100 */    this.swingSpan1 = 1.0E+030F;
/* 101:101 */    this.swingSpan2 = 1.0E+030F;
/* 102:102 */    this.twistSpan = 1.0E+030F;
/* 103:103 */    this.biasFactor = 0.3F;
/* 104:104 */    this.relaxationFactor = 1.0F;
/* 105:    */    
/* 106:106 */    this.solveTwistLimit = false;
/* 107:107 */    this.solveSwingLimit = false;
/* 108:    */  }
/* 109:    */  
/* 110:    */  public void buildJacobian()
/* 111:    */  {
/* 112:112 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform(); .Stack tmp11_7 = tmp7_5;tmp11_7.push$javax$vecmath$Vector3f();tmp11_7.push$javax$vecmath$Quat4f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 113:113 */      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 114:114 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 115:    */      
/* 116:116 */      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 117:    */      
/* 118:118 */      this.appliedImpulse = 0.0F;
/* 119:    */      
/* 121:121 */      this.swingCorrection = 0.0F;
/* 122:122 */      this.twistLimitSign = 0.0F;
/* 123:123 */      this.solveTwistLimit = false;
/* 124:124 */      this.solveSwingLimit = false;
/* 125:125 */      this.accTwistLimitImpulse = 0.0F;
/* 126:126 */      this.accSwingLimitImpulse = 0.0F;
/* 127:    */      
/* 128:128 */      if (!this.angularOnly) {
/* 129:129 */        Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.rbAFrame.origin);
/* 130:130 */        this.rbA.getCenterOfMassTransform(tmpTrans).transform(pivotAInW);
/* 131:    */        
/* 132:132 */        Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.rbBFrame.origin);
/* 133:133 */        this.rbB.getCenterOfMassTransform(tmpTrans).transform(pivotBInW);
/* 134:    */        
/* 135:135 */        Vector3f relPos = localStack.get$javax$vecmath$Vector3f();
/* 136:136 */        relPos.sub(pivotBInW, pivotAInW);
/* 137:    */        
/* 139:139 */        Vector3f[] normal = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
/* 140:140 */        if (relPos.lengthSquared() > 1.192093E-007F) {
/* 141:141 */          normal[0].normalize(relPos);
/* 142:    */        }
/* 143:    */        else {
/* 144:144 */          normal[0].set(1.0F, 0.0F, 0.0F);
/* 145:    */        }
/* 146:    */        
/* 147:147 */        TransformUtil.planeSpace1(normal[0], normal[1], normal[2]);
/* 148:    */        
/* 149:149 */        for (int i = 0; i < 3; i++) {
/* 150:150 */          Matrix3f mat1 = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/* 151:151 */          mat1.transpose();
/* 152:    */          
/* 153:153 */          Matrix3f mat2 = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/* 154:154 */          mat2.transpose();
/* 155:    */          
/* 156:156 */          tmp1.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmp));
/* 157:157 */          tmp2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmp));
/* 158:    */          
/* 159:159 */          this.jac[i].init(mat1, mat2, tmp1, tmp2, normal[i], this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbA.getInvMass(), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvMass());
/* 160:    */        }
/* 161:    */      }
/* 162:    */      
/* 172:172 */      Vector3f b1Axis1 = localStack.get$javax$vecmath$Vector3f();Vector3f b1Axis2 = localStack.get$javax$vecmath$Vector3f();Vector3f b1Axis3 = localStack.get$javax$vecmath$Vector3f();
/* 173:173 */      Vector3f b2Axis1 = localStack.get$javax$vecmath$Vector3f();Vector3f b2Axis2 = localStack.get$javax$vecmath$Vector3f();
/* 174:    */      
/* 175:175 */      this.rbAFrame.basis.getColumn(0, b1Axis1);
/* 176:176 */      getRigidBodyA().getCenterOfMassTransform(tmpTrans).basis.transform(b1Axis1);
/* 177:    */      
/* 178:178 */      this.rbBFrame.basis.getColumn(0, b2Axis1);
/* 179:179 */      getRigidBodyB().getCenterOfMassTransform(tmpTrans).basis.transform(b2Axis1);
/* 180:    */      
/* 181:181 */      float swing1 = 0.0F;float swing2 = 0.0F;
/* 182:    */      
/* 183:183 */      float swx = 0.0F;float swy = 0.0F;
/* 184:184 */      float thresh = 10.0F;
/* 185:    */      
/* 188:188 */      if (this.swingSpan1 >= 0.05F) {
/* 189:189 */        this.rbAFrame.basis.getColumn(1, b1Axis2);
/* 190:190 */        getRigidBodyA().getCenterOfMassTransform(tmpTrans).basis.transform(b1Axis2);
/* 191:    */        
/* 192:192 */        swx = b2Axis1.dot(b1Axis1);
/* 193:193 */        swy = b2Axis1.dot(b1Axis2);
/* 194:194 */        swing1 = ScalarUtil.atan2Fast(swy, swx);
/* 195:195 */        float fact = (swy * swy + swx * swx) * thresh * thresh;
/* 196:196 */        fact /= (fact + 1.0F);
/* 197:197 */        swing1 *= fact;
/* 198:    */      }
/* 199:    */      
/* 200:200 */      if (this.swingSpan2 >= 0.05F) {
/* 201:201 */        this.rbAFrame.basis.getColumn(2, b1Axis3);
/* 202:202 */        getRigidBodyA().getCenterOfMassTransform(tmpTrans).basis.transform(b1Axis3);
/* 203:    */        
/* 204:204 */        swx = b2Axis1.dot(b1Axis1);
/* 205:205 */        swy = b2Axis1.dot(b1Axis3);
/* 206:206 */        swing2 = ScalarUtil.atan2Fast(swy, swx);
/* 207:207 */        float fact = (swy * swy + swx * swx) * thresh * thresh;
/* 208:208 */        fact /= (fact + 1.0F);
/* 209:209 */        swing2 *= fact;
/* 210:    */      }
/* 211:    */      
/* 212:212 */      float RMaxAngle1Sq = 1.0F / (this.swingSpan1 * this.swingSpan1);
/* 213:213 */      float RMaxAngle2Sq = 1.0F / (this.swingSpan2 * this.swingSpan2);
/* 214:214 */      float EllipseAngle = Math.abs(swing1 * swing1) * RMaxAngle1Sq + Math.abs(swing2 * swing2) * RMaxAngle2Sq;
/* 215:    */      
/* 216:216 */      if (EllipseAngle > 1.0F) {
/* 217:217 */        this.swingCorrection = (EllipseAngle - 1.0F);
/* 218:218 */        this.solveSwingLimit = true;
/* 219:    */        
/* 221:221 */        tmp1.scale(b2Axis1.dot(b1Axis2), b1Axis2);
/* 222:222 */        tmp2.scale(b2Axis1.dot(b1Axis3), b1Axis3);
/* 223:223 */        tmp.add(tmp1, tmp2);
/* 224:224 */        this.swingAxis.cross(b2Axis1, tmp);
/* 225:225 */        this.swingAxis.normalize();
/* 226:    */        
/* 227:227 */        float swingAxisSign = b2Axis1.dot(b1Axis1) >= 0.0F ? 1.0F : -1.0F;
/* 228:228 */        this.swingAxis.scale(swingAxisSign);
/* 229:    */        
/* 230:230 */        this.kSwing = (1.0F / (getRigidBodyA().computeAngularImpulseDenominator(this.swingAxis) + getRigidBodyB().computeAngularImpulseDenominator(this.swingAxis)));
/* 231:    */      }
/* 232:    */      
/* 236:236 */      if (this.twistSpan >= 0.0F)
/* 237:    */      {
/* 238:238 */        this.rbBFrame.basis.getColumn(1, b2Axis2);
/* 239:239 */        getRigidBodyB().getCenterOfMassTransform(tmpTrans).basis.transform(b2Axis2);
/* 240:    */        
/* 241:241 */        Quat4f rotationArc = QuaternionUtil.shortestArcQuat(b2Axis1, b1Axis1, localStack.get$javax$vecmath$Quat4f());
/* 242:242 */        Vector3f TwistRef = QuaternionUtil.quatRotate(rotationArc, b2Axis2, localStack.get$javax$vecmath$Vector3f());
/* 243:243 */        float twist = ScalarUtil.atan2Fast(TwistRef.dot(b1Axis3), TwistRef.dot(b1Axis2));
/* 244:    */        
/* 245:245 */        float lockedFreeFactor = this.twistSpan > 0.05F ? this.limitSoftness : 0.0F;
/* 246:246 */        if (twist <= -this.twistSpan * lockedFreeFactor) {
/* 247:247 */          this.twistCorrection = (-(twist + this.twistSpan));
/* 248:248 */          this.solveTwistLimit = true;
/* 249:    */          
/* 250:250 */          this.twistAxis.add(b2Axis1, b1Axis1);
/* 251:251 */          this.twistAxis.scale(0.5F);
/* 252:252 */          this.twistAxis.normalize();
/* 253:253 */          this.twistAxis.scale(-1.0F);
/* 254:    */          
/* 255:255 */          this.kTwist = (1.0F / (getRigidBodyA().computeAngularImpulseDenominator(this.twistAxis) + getRigidBodyB().computeAngularImpulseDenominator(this.twistAxis)));
/* 258:    */        }
/* 259:259 */        else if (twist > this.twistSpan * lockedFreeFactor) {
/* 260:260 */          this.twistCorrection = (twist - this.twistSpan);
/* 261:261 */          this.solveTwistLimit = true;
/* 262:    */          
/* 263:263 */          this.twistAxis.add(b2Axis1, b1Axis1);
/* 264:264 */          this.twistAxis.scale(0.5F);
/* 265:265 */          this.twistAxis.normalize();
/* 266:    */          
/* 267:267 */          this.kTwist = (1.0F / (getRigidBodyA().computeAngularImpulseDenominator(this.twistAxis) + getRigidBodyB().computeAngularImpulseDenominator(this.twistAxis)));
/* 268:    */        }
/* 269:    */      }
/* 270:    */    } finally {
/* 271:271 */      .Stack tmp1184_1182 = localStack;tmp1184_1182.pop$com$bulletphysics$linearmath$Transform(); .Stack tmp1188_1184 = tmp1184_1182;tmp1188_1184.pop$javax$vecmath$Vector3f();tmp1188_1184.pop$javax$vecmath$Quat4f();
/* 272:    */    }
/* 273:    */  }
/* 274:    */  
/* 275:275 */  public void solveConstraint(float arg1) { .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 276:276 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 277:    */      
/* 278:278 */      Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/* 279:279 */      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 280:    */      
/* 281:281 */      Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.rbAFrame.origin);
/* 282:282 */      this.rbA.getCenterOfMassTransform(tmpTrans).transform(pivotAInW);
/* 283:    */      
/* 284:284 */      Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.rbBFrame.origin);
/* 285:285 */      this.rbB.getCenterOfMassTransform(tmpTrans).transform(pivotBInW);
/* 286:    */      
/* 287:287 */      float tau = 0.3F;
/* 288:    */      
/* 290:290 */      if (!this.angularOnly) {
/* 291:291 */        Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/* 292:292 */        rel_pos1.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
/* 293:    */        
/* 294:294 */        Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 295:295 */        rel_pos2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
/* 296:    */        
/* 297:297 */        Vector3f vel1 = this.rbA.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
/* 298:298 */        Vector3f vel2 = this.rbB.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
/* 299:299 */        Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 300:300 */        vel.sub(vel1, vel2);
/* 301:    */        
/* 302:302 */        for (int i = 0; i < 3; i++) {
/* 303:303 */          Vector3f normal = this.jac[i].linearJointAxis;
/* 304:304 */          float jacDiagABInv = 1.0F / this.jac[i].getDiagonal();
/* 305:    */          
/* 307:307 */          float rel_vel = normal.dot(vel);
/* 308:    */          
/* 309:309 */          tmp.sub(pivotAInW, pivotBInW);
/* 310:310 */          float depth = -tmp.dot(normal);
/* 311:311 */          float impulse = depth * tau / timeStep * jacDiagABInv - rel_vel * jacDiagABInv;
/* 312:312 */          this.appliedImpulse += impulse;
/* 313:313 */          Vector3f impulse_vector = localStack.get$javax$vecmath$Vector3f();
/* 314:314 */          impulse_vector.scale(impulse, normal);
/* 315:    */          
/* 316:316 */          tmp.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
/* 317:317 */          this.rbA.applyImpulse(impulse_vector, tmp);
/* 318:    */          
/* 319:319 */          tmp.negate(impulse_vector);
/* 320:320 */          tmp2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
/* 321:321 */          this.rbB.applyImpulse(tmp, tmp2);
/* 322:    */        }
/* 323:    */      }
/* 324:    */      
/* 327:327 */      Vector3f angVelA = getRigidBodyA().getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/* 328:328 */      Vector3f angVelB = getRigidBodyB().getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/* 329:    */      
/* 331:331 */      if (this.solveSwingLimit) {
/* 332:332 */        tmp.sub(angVelB, angVelA);
/* 333:333 */        float amplitude = tmp.dot(this.swingAxis) * this.relaxationFactor * this.relaxationFactor + this.swingCorrection * (1.0F / timeStep) * this.biasFactor;
/* 334:334 */        float impulseMag = amplitude * this.kSwing;
/* 335:    */        
/* 337:337 */        float temp = this.accSwingLimitImpulse;
/* 338:338 */        this.accSwingLimitImpulse = Math.max(this.accSwingLimitImpulse + impulseMag, 0.0F);
/* 339:339 */        impulseMag = this.accSwingLimitImpulse - temp;
/* 340:    */        
/* 341:341 */        Vector3f impulse = localStack.get$javax$vecmath$Vector3f();
/* 342:342 */        impulse.scale(impulseMag, this.swingAxis);
/* 343:    */        
/* 344:344 */        this.rbA.applyTorqueImpulse(impulse);
/* 345:    */        
/* 346:346 */        tmp.negate(impulse);
/* 347:347 */        this.rbB.applyTorqueImpulse(tmp);
/* 348:    */      }
/* 349:    */      
/* 351:351 */      if (this.solveTwistLimit) {
/* 352:352 */        tmp.sub(angVelB, angVelA);
/* 353:353 */        float amplitude = tmp.dot(this.twistAxis) * this.relaxationFactor * this.relaxationFactor + this.twistCorrection * (1.0F / timeStep) * this.biasFactor;
/* 354:354 */        float impulseMag = amplitude * this.kTwist;
/* 355:    */        
/* 357:357 */        float temp = this.accTwistLimitImpulse;
/* 358:358 */        this.accTwistLimitImpulse = Math.max(this.accTwistLimitImpulse + impulseMag, 0.0F);
/* 359:359 */        impulseMag = this.accTwistLimitImpulse - temp;
/* 360:    */        
/* 361:361 */        Vector3f impulse = localStack.get$javax$vecmath$Vector3f();
/* 362:362 */        impulse.scale(impulseMag, this.twistAxis);
/* 363:    */        
/* 364:364 */        this.rbA.applyTorqueImpulse(impulse);
/* 365:    */        
/* 366:366 */        tmp.negate(impulse);
/* 367:367 */        this.rbB.applyTorqueImpulse(tmp);
/* 368:    */      }
/* 369:    */    } finally {
/* 370:370 */      .Stack tmp668_666 = localStack;tmp668_666.pop$com$bulletphysics$linearmath$Transform();tmp668_666.pop$javax$vecmath$Vector3f();
/* 371:    */    }
/* 372:    */  }
/* 373:    */  
/* 374:    */  public void updateRHS(float timeStep) {}
/* 375:    */  
/* 376:376 */  public void setAngularOnly(boolean angularOnly) { this.angularOnly = angularOnly; }
/* 377:    */  
/* 378:    */  public void setLimit(float _swingSpan1, float _swingSpan2, float _twistSpan)
/* 379:    */  {
/* 380:380 */    setLimit(_swingSpan1, _swingSpan2, _twistSpan, 0.8F, 0.3F, 1.0F);
/* 381:    */  }
/* 382:    */  
/* 383:    */  public void setLimit(float _swingSpan1, float _swingSpan2, float _twistSpan, float _softness, float _biasFactor, float _relaxationFactor) {
/* 384:384 */    this.swingSpan1 = _swingSpan1;
/* 385:385 */    this.swingSpan2 = _swingSpan2;
/* 386:386 */    this.twistSpan = _twistSpan;
/* 387:    */    
/* 388:388 */    this.limitSoftness = _softness;
/* 389:389 */    this.biasFactor = _biasFactor;
/* 390:390 */    this.relaxationFactor = _relaxationFactor;
/* 391:    */  }
/* 392:    */  
/* 393:    */  public Transform getAFrame(Transform out) {
/* 394:394 */    out.set(this.rbAFrame);
/* 395:395 */    return out;
/* 396:    */  }
/* 397:    */  
/* 398:    */  public Transform getBFrame(Transform out) {
/* 399:399 */    out.set(this.rbBFrame);
/* 400:400 */    return out;
/* 401:    */  }
/* 402:    */  
/* 403:    */  public boolean getSolveTwistLimit() {
/* 404:404 */    return this.solveTwistLimit;
/* 405:    */  }
/* 406:    */  
/* 407:    */  public boolean getSolveSwingLimit() {
/* 408:408 */    return this.solveTwistLimit;
/* 409:    */  }
/* 410:    */  
/* 411:    */  public float getTwistLimitSign() {
/* 412:412 */    return this.twistLimitSign;
/* 413:    */  }
/* 414:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.ConeTwistConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */