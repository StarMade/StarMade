/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.dynamics.RigidBody;
/*   4:    */import com.bulletphysics.dynamics.constraintsolver.Generic6DofConstraint;
/*   5:    */import com.bulletphysics.dynamics.constraintsolver.JacobianEntry;
/*   6:    */import com.bulletphysics.dynamics.constraintsolver.RotationalLimitMotor;
/*   7:    */import com.bulletphysics.dynamics.constraintsolver.TranslationalLimitMotor;
/*   8:    */import com.bulletphysics.linearmath.MatrixUtil;
/*   9:    */import com.bulletphysics.linearmath.VectorUtil;
/*  10:    */import java.io.PrintStream;
/*  11:    */import org.schema.common.FastMath;
/*  12:    */import org.schema.schine.network.StateInterface;
/*  13:    */
/*  14:    */public class Extended6DotConstraint extends Generic6DofConstraint
/*  15:    */{
/*  16:    */  private StateInterface state;
/*  17:    */  
/*  18:    */  private static float getMatrixElem(javax.vecmath.Matrix3f paramMatrix3f, int paramInt)
/*  19:    */  {
/*  20: 20 */    int i = paramInt % 3;
/*  21: 21 */    paramInt /= 3;
/*  22: 22 */    return paramMatrix3f.getElement(i, paramInt);
/*  23:    */  }
/*  24:    */  
/*  33:    */  private static boolean matrixToEulerXYZ(javax.vecmath.Matrix3f paramMatrix3f, javax.vecmath.Vector3f paramVector3f)
/*  34:    */  {
/*  35: 35 */    if (getMatrixElem(paramMatrix3f, 2) < 1.0F) {
/*  36: 36 */      if (getMatrixElem(paramMatrix3f, 2) > -1.0F) {
/*  37: 37 */        paramVector3f.x = ((float)Math.atan2(-getMatrixElem(paramMatrix3f, 5), getMatrixElem(paramMatrix3f, 8)));
/*  38: 38 */        paramVector3f.y = ((float)Math.asin(getMatrixElem(paramMatrix3f, 2)));
/*  39: 39 */        paramVector3f.z = ((float)Math.atan2(-getMatrixElem(paramMatrix3f, 1), getMatrixElem(paramMatrix3f, 0)));
/*  40: 40 */        return true;
/*  41:    */      }
/*  42:    */      
/*  43: 43 */      System.err.println("WARNING.  Not unique.  XA - ZA = -atan2(r10,r11)");
/*  44:    */      
/*  45: 45 */      paramVector3f.x = (-(float)Math.atan2(getMatrixElem(paramMatrix3f, 3), getMatrixElem(paramMatrix3f, 4)));
/*  46: 46 */      paramVector3f.y = -1.570796F;
/*  47: 47 */      paramVector3f.z = 0.0F;
/*  48: 48 */      return false;
/*  49:    */    }
/*  50:    */    
/*  52: 52 */    System.err.println("WARNING.  Not unique.  XAngle + ZAngle = atan2(r10,r11)");
/*  53:    */    
/*  54: 54 */    paramVector3f.x = ((float)Math.atan2(getMatrixElem(paramMatrix3f, 3), getMatrixElem(paramMatrix3f, 4)));
/*  55: 55 */    paramVector3f.y = 1.570796F;
/*  56: 56 */    paramVector3f.z = 0.0F;
/*  57:    */    
/*  59: 59 */    return false;
/*  60:    */  }
/*  61:    */  
/*  65: 65 */  protected final RotationalLimitMotorAbsVelocity[] angularLimits = { new RotationalLimitMotorAbsVelocity(), new RotationalLimitMotorAbsVelocity(), new RotationalLimitMotorAbsVelocity() };
/*  66:    */  
/*  67: 67 */  public final javax.vecmath.Vector3f linearJointAxis = new javax.vecmath.Vector3f();
/*  68:    */  
/*  69: 69 */  public final javax.vecmath.Vector3f aJ = new javax.vecmath.Vector3f();
/*  70: 70 */  public final javax.vecmath.Vector3f bJ = new javax.vecmath.Vector3f();
/*  71: 71 */  public final javax.vecmath.Vector3f m_0MinvJt = new javax.vecmath.Vector3f();
/*  72: 72 */  public final javax.vecmath.Vector3f m_1MinvJt = new javax.vecmath.Vector3f();
/*  73:    */  
/*  74:    */  public Extended6DotConstraint(StateInterface paramStateInterface, RigidBody paramRigidBody1, RigidBody paramRigidBody2, com.bulletphysics.linearmath.Transform paramTransform1, com.bulletphysics.linearmath.Transform paramTransform2, boolean paramBoolean)
/*  75:    */  {
/*  76: 76 */    super(paramRigidBody1, paramRigidBody2, paramTransform1, paramTransform2, paramBoolean);
/*  77: 77 */    this.state = paramStateInterface;
/*  78:    */  }
/*  79:    */  
/*  83:    */  protected void buildAngularJacobian(int paramInt, javax.vecmath.Vector3f paramVector3f)
/*  84:    */  {
/*  85:    */    javax.vecmath.Matrix3f localMatrix3f1;
/*  86:    */    
/*  88: 88 */    (localMatrix3f1 = this.rbA.getCenterOfMassTransform(new com.bulletphysics.linearmath.Transform()).basis).transpose();
/*  89:    */    
/*  90:    */    javax.vecmath.Matrix3f localMatrix3f2;
/*  91: 91 */    (localMatrix3f2 = this.rbB.getCenterOfMassTransform(new com.bulletphysics.linearmath.Transform()).basis).transpose();
/*  92: 92 */    if (!checkJacobian(paramVector3f, localMatrix3f1, localMatrix3f2, this.rbA.getInvInertiaDiagLocal(new javax.vecmath.Vector3f()), this.rbB.getInvInertiaDiagLocal(new javax.vecmath.Vector3f())))
/*  93:    */    {
/*  97: 97 */      System.err.println("Exception: Jacobian Entry init failed!");
/*  98: 98 */      return;
/*  99:    */    }
/* 100:    */    
/* 102:102 */    super.buildAngularJacobian(paramInt, paramVector3f);
/* 103:    */  }
/* 104:    */  
/* 105:    */  public void buildJacobian()
/* 106:    */  {
/* 107:107 */    this.linearLimits.accumulatedImpulse.set(0.0F, 0.0F, 0.0F);
/* 108:108 */    for (int i = 0; i < 3; i++) {
/* 109:109 */      this.angularLimits[i].accumulatedImpulse = 0.0F;
/* 110:    */    }
/* 111:    */    
/* 113:113 */    calculateTransforms();
/* 114:    */    
/* 115:115 */    new javax.vecmath.Vector3f();
/* 116:    */    
/* 118:118 */    calcAnchorPos();
/* 119:119 */    javax.vecmath.Vector3f localVector3f1 = new javax.vecmath.Vector3f(this.anchorPos);
/* 120:120 */    javax.vecmath.Vector3f localVector3f2 = new javax.vecmath.Vector3f(this.anchorPos);
/* 121:    */    
/* 126:126 */    javax.vecmath.Vector3f localVector3f3 = new javax.vecmath.Vector3f();
/* 127:    */    
/* 128:128 */    for (int j = 0; j < 3; j++) {
/* 129:129 */      if (this.linearLimits.isLimited(j)) {
/* 130:130 */        if (this.useLinearReferenceFrameA) {
/* 131:131 */          this.calculatedTransformA.basis.getColumn(j, localVector3f3);
/* 132:    */        }
/* 133:    */        else {
/* 134:134 */          this.calculatedTransformB.basis.getColumn(j, localVector3f3);
/* 135:    */        }
/* 136:    */        
/* 137:137 */        buildLinearJacobian(j, localVector3f3, localVector3f1, localVector3f2);
/* 138:    */      }
/* 139:    */    }
/* 140:    */    
/* 145:145 */    for (j = 0; j < 3; j++)
/* 146:    */    {
/* 147:147 */      if (testAngularLimitMotor(j)) {
/* 148:148 */        getAxis(j, localVector3f3);
/* 149:    */        
/* 150:150 */        buildAngularJacobian(j, localVector3f3);
/* 151:    */      }
/* 152:    */    }
/* 153:    */  }
/* 154:    */  
/* 157:    */  protected void calculateAngleInfo()
/* 158:    */  {
/* 159:159 */    Object localObject1 = new javax.vecmath.Matrix3f();
/* 160:    */    
/* 161:161 */    Object localObject2 = new javax.vecmath.Matrix3f();
/* 162:162 */    ((javax.vecmath.Matrix3f)localObject1).set(this.calculatedTransformA.basis);
/* 163:163 */    MatrixUtil.invert((javax.vecmath.Matrix3f)localObject1);
/* 164:164 */    ((javax.vecmath.Matrix3f)localObject2).mul((javax.vecmath.Matrix3f)localObject1, this.calculatedTransformB.basis);
/* 165:    */    
/* 166:166 */    matrixToEulerXYZ((javax.vecmath.Matrix3f)localObject2, this.calculatedAxisAngleDiff);
/* 167:    */    
/* 185:185 */    localObject1 = new javax.vecmath.Vector3f();
/* 186:186 */    this.calculatedTransformB.basis.getColumn(0, (javax.vecmath.Vector3f)localObject1);
/* 187:    */    
/* 188:188 */    localObject2 = new javax.vecmath.Vector3f();
/* 189:189 */    this.calculatedTransformA.basis.getColumn(2, (javax.vecmath.Vector3f)localObject2);
/* 190:    */    
/* 191:191 */    this.calculatedAxis[1].cross((javax.vecmath.Vector3f)localObject2, (javax.vecmath.Vector3f)localObject1);
/* 192:192 */    this.calculatedAxis[0].cross(this.calculatedAxis[1], (javax.vecmath.Vector3f)localObject2);
/* 193:193 */    this.calculatedAxis[2].cross((javax.vecmath.Vector3f)localObject1, this.calculatedAxis[1]);
/* 194:    */  }
/* 195:    */  
/* 210:    */  public boolean checkJacobian(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Matrix3f paramMatrix3f1, javax.vecmath.Matrix3f paramMatrix3f2, javax.vecmath.Vector3f paramVector3f2, javax.vecmath.Vector3f paramVector3f3)
/* 211:    */  {
/* 212:212 */    this.linearJointAxis.set(0.0F, 0.0F, 0.0F);
/* 213:    */    
/* 214:214 */    this.aJ.set(paramVector3f1);
/* 215:215 */    paramMatrix3f1.transform(this.aJ);
/* 216:    */    
/* 217:217 */    this.bJ.set(paramVector3f1);
/* 218:218 */    this.bJ.negate();
/* 219:219 */    paramMatrix3f2.transform(this.bJ);
/* 220:    */    
/* 221:221 */    VectorUtil.mul(this.m_0MinvJt, paramVector3f2, this.aJ);
/* 222:222 */    VectorUtil.mul(this.m_1MinvJt, paramVector3f3, this.bJ);
/* 223:    */    
/* 225:225 */    return this.m_0MinvJt.dot(this.aJ) + this.m_1MinvJt.dot(this.bJ) > 0.0F;
/* 226:    */  }
/* 227:    */  
/* 234:    */  public RotationalLimitMotor getRotationalLimitMotor(int paramInt)
/* 235:    */  {
/* 236:236 */    return this.angularLimits[paramInt];
/* 237:    */  }
/* 238:    */  
/* 245:    */  public boolean isLimited(int paramInt)
/* 246:    */  {
/* 247:247 */    if (paramInt < 3) {
/* 248:248 */      return this.linearLimits.isLimited(paramInt);
/* 249:    */    }
/* 250:    */    
/* 251:251 */    return this.angularLimits[(paramInt - 3)].isLimited();
/* 252:    */  }
/* 253:    */  
/* 256:    */  public void matrixToEuler(javax.vecmath.Matrix3f paramMatrix3f, javax.vecmath.Vector3f paramVector3f)
/* 257:    */  {
/* 258:    */    float f1;
/* 259:    */    
/* 261:    */    float f3;
/* 262:    */    
/* 264:264 */    if (FastMath.a(f2 = (float)Math.cos(f1 = (float)-Math.asin(paramMatrix3f.m02))) > 0.005D)
/* 265:    */    {
/* 270:270 */      f3 = paramMatrix3f.m22 / f2;
/* 271:    */      
/* 273:273 */      f4 = (float)Math.atan2(-paramMatrix3f.m12 / f2, f3);
/* 274:    */      
/* 275:275 */      f3 = paramMatrix3f.m00 / f2;
/* 276:    */      
/* 278:278 */      f2 = (float)Math.atan2(-paramMatrix3f.m01 / f2, f3);
/* 279:    */    }
/* 280:    */    else
/* 281:    */    {
/* 282:282 */      f4 = 0.0F;
/* 283:    */      
/* 284:284 */      f3 = paramMatrix3f.m11;
/* 285:    */      
/* 287:287 */      f2 = (float)Math.atan2(paramMatrix3f.m10, f3);
/* 288:    */    }
/* 289:    */    
/* 290:290 */    float f4 = FastMath.a(f4, 0.0F, 6.283186F);
/* 291:291 */    paramMatrix3f = FastMath.a(f1, 0.0F, 6.283186F);
/* 292:292 */    float f2 = FastMath.a(f2, 0.0F, 6.283186F);
/* 293:    */    
/* 294:294 */    paramVector3f.x = f4;
/* 295:295 */    paramVector3f.y = paramMatrix3f;
/* 296:296 */    paramVector3f.z = f2;
/* 297:    */    
/* 298:298 */    System.err.println("Euler: " + paramVector3f);
/* 299:    */  }
/* 300:    */  
/* 301:    */  public void setAngularLowerLimit(javax.vecmath.Vector3f paramVector3f) {
/* 302:302 */    this.angularLimits[0].loLimit = paramVector3f.x;
/* 303:303 */    this.angularLimits[1].loLimit = paramVector3f.y;
/* 304:304 */    this.angularLimits[2].loLimit = paramVector3f.z;
/* 305:    */  }
/* 306:    */  
/* 307:    */  public void setAngularUpperLimit(javax.vecmath.Vector3f paramVector3f)
/* 308:    */  {
/* 309:309 */    this.angularLimits[0].hiLimit = paramVector3f.x;
/* 310:310 */    this.angularLimits[1].hiLimit = paramVector3f.y;
/* 311:311 */    this.angularLimits[2].hiLimit = paramVector3f.z;
/* 312:    */  }
/* 313:    */  
/* 317:    */  public void setLimit(int paramInt, float paramFloat1, float paramFloat2)
/* 318:    */  {
/* 319:319 */    if (paramInt < 3) {
/* 320:320 */      VectorUtil.setCoord(this.linearLimits.lowerLimit, paramInt, paramFloat1);
/* 321:321 */      VectorUtil.setCoord(this.linearLimits.upperLimit, paramInt, paramFloat2);return;
/* 322:    */    }
/* 323:    */    
/* 324:324 */    this.angularLimits[(paramInt - 3)].loLimit = paramFloat1;
/* 325:325 */    this.angularLimits[(paramInt - 3)].hiLimit = paramFloat2;
/* 326:    */  }
/* 327:    */  
/* 331:    */  public void solveConstraint(float paramFloat)
/* 332:    */  {
/* 333:333 */    this.timeStep = paramFloat;
/* 334:    */    
/* 341:341 */    javax.vecmath.Vector3f localVector3f1 = new javax.vecmath.Vector3f(this.calculatedTransformA.origin);
/* 342:342 */    javax.vecmath.Vector3f localVector3f2 = new javax.vecmath.Vector3f(this.calculatedTransformB.origin);
/* 343:    */    
/* 345:345 */    javax.vecmath.Vector3f localVector3f3 = new javax.vecmath.Vector3f();
/* 346:346 */    for (paramFloat = 0; paramFloat < 3; paramFloat++) {
/* 347:347 */      if (this.linearLimits.isLimited(paramFloat)) {
/* 348:348 */        float f2 = 1.0F / this.jacLinear[paramFloat].getDiagonal();
/* 349:    */        
/* 350:350 */        if (this.useLinearReferenceFrameA) {
/* 351:351 */          this.calculatedTransformA.basis.getColumn(paramFloat, localVector3f3);
/* 352:    */        }
/* 353:    */        else {
/* 354:354 */          this.calculatedTransformB.basis.getColumn(paramFloat, localVector3f3);
/* 355:    */        }
/* 356:    */        
/* 357:357 */        this.linearLimits.solveLinearAxis(this.timeStep, f2, this.rbA, localVector3f1, this.rbB, localVector3f2, paramFloat, localVector3f3, this.anchorPos);
/* 358:    */      }
/* 359:    */    }
/* 360:    */    
/* 368:368 */    localVector3f1 = new javax.vecmath.Vector3f();
/* 369:    */    
/* 370:370 */    for (paramFloat = 0; paramFloat < 3; paramFloat++) {
/* 371:371 */      if (this.angularLimits[paramFloat].needApplyTorques())
/* 372:    */      {
/* 373:373 */        getAxis(paramFloat, localVector3f1);
/* 374:    */        float f1;
/* 375:375 */        if (this.jacAng[paramFloat].getDiagonal() == 0.0F) {
/* 376:376 */          f1 = 0.1F;
/* 377:    */        } else {
/* 378:378 */          f1 = 1.0F / this.jacAng[paramFloat].getDiagonal();
/* 379:    */        }
/* 380:380 */        this.angularLimits[paramFloat].solveAngularLimits(this.timeStep, localVector3f1, f1, this.rbA, this.rbB);
/* 381:    */      }
/* 382:    */    }
/* 383:    */  }
/* 384:    */  
/* 395:    */  public boolean testAngularLimitMotor(int paramInt)
/* 396:    */  {
/* 397:397 */    float f = VectorUtil.getCoord(this.calculatedAxisAngleDiff, paramInt);
/* 398:    */    
/* 400:400 */    this.angularLimits[paramInt].testLimitValue(f);
/* 401:401 */    return this.angularLimits[paramInt].needApplyTorques();
/* 402:    */  }
/* 403:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.Extended6DotConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */