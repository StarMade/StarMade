/*   1:    */package com.bulletphysics.dynamics.constraintsolver;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*   5:    */import com.bulletphysics.dynamics.RigidBody;
/*   6:    */import com.bulletphysics.linearmath.Transform;
/*   7:    */import com.bulletphysics.util.ObjectPool;
/*   8:    */import javax.vecmath.Matrix3f;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */
/*  40:    */public class ContactConstraint
/*  41:    */{
/*  42: 42 */  public static final ContactSolverFunc resolveSingleCollision = new ContactSolverFunc() {
/*  43:    */    public float resolveContact(RigidBody body1, RigidBody body2, ManifoldPoint contactPoint, ContactSolverInfo info) {
/*  44: 44 */      return ContactConstraint.resolveSingleCollision(body1, body2, contactPoint, info);
/*  45:    */    }
/*  46:    */  };
/*  47:    */  
/*  48: 48 */  public static final ContactSolverFunc resolveSingleFriction = new ContactSolverFunc() {
/*  49:    */    public float resolveContact(RigidBody body1, RigidBody body2, ManifoldPoint contactPoint, ContactSolverInfo info) {
/*  50: 50 */      return ContactConstraint.resolveSingleFriction(body1, body2, contactPoint, info);
/*  51:    */    }
/*  52:    */  };
/*  53:    */  
/*  54: 54 */  public static final ContactSolverFunc resolveSingleCollisionCombined = new ContactSolverFunc() {
/*  55:    */    public float resolveContact(RigidBody body1, RigidBody body2, ManifoldPoint contactPoint, ContactSolverInfo info) {
/*  56: 56 */      return ContactConstraint.resolveSingleCollisionCombined(body1, body2, contactPoint, info);
/*  57:    */    }
/*  58:    */  };
/*  59:    */  
/*  64:    */  public static void resolveSingleBilateral(RigidBody arg0, Vector3f arg1, RigidBody arg2, Vector3f arg3, float arg4, Vector3f arg5, float[] arg6, float arg7)
/*  65:    */  {
/*  66: 66 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();float normalLenSqr = normal.lengthSquared();
/*  67: 67 */      assert (Math.abs(normalLenSqr) < 1.1F);
/*  68: 68 */      if (normalLenSqr > 1.1F) {
/*  69: 69 */        impulse[0] = 0.0F;
/*  70: 70 */        return;
/*  71:    */      }
/*  72:    */      
/*  73: 73 */      ObjectPool<JacobianEntry> jacobiansPool = ObjectPool.get(JacobianEntry.class);
/*  74: 74 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  75:    */      
/*  76: 76 */      Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/*  77: 77 */      rel_pos1.sub(pos1, body1.getCenterOfMassPosition(tmp));
/*  78:    */      
/*  79: 79 */      Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/*  80: 80 */      rel_pos2.sub(pos2, body2.getCenterOfMassPosition(tmp));
/*  81:    */      
/*  84: 84 */      Vector3f vel1 = localStack.get$javax$vecmath$Vector3f();
/*  85: 85 */      body1.getVelocityInLocalPoint(rel_pos1, vel1);
/*  86:    */      
/*  87: 87 */      Vector3f vel2 = localStack.get$javax$vecmath$Vector3f();
/*  88: 88 */      body2.getVelocityInLocalPoint(rel_pos2, vel2);
/*  89:    */      
/*  90: 90 */      Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/*  91: 91 */      vel.sub(vel1, vel2);
/*  92:    */      
/*  93: 93 */      Matrix3f mat1 = body1.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/*  94: 94 */      mat1.transpose();
/*  95:    */      
/*  96: 96 */      Matrix3f mat2 = body2.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/*  97: 97 */      mat2.transpose();
/*  98:    */      
/*  99: 99 */      JacobianEntry jac = (JacobianEntry)jacobiansPool.get();
/* 100:100 */      jac.init(mat1, mat2, rel_pos1, rel_pos2, normal, body1.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), body1.getInvMass(), body2.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), body2.getInvMass());
/* 101:    */      
/* 105:105 */      float jacDiagAB = jac.getDiagonal();
/* 106:106 */      float jacDiagABInv = 1.0F / jacDiagAB;
/* 107:    */      
/* 108:108 */      Vector3f tmp1 = body1.getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/* 109:109 */      mat1.transform(tmp1);
/* 110:    */      
/* 111:111 */      Vector3f tmp2 = body2.getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/* 112:112 */      mat2.transform(tmp2);
/* 113:    */      
/* 114:114 */      float rel_vel = jac.getRelativeVelocity(body1.getLinearVelocity(localStack.get$javax$vecmath$Vector3f()), tmp1, body2.getLinearVelocity(localStack.get$javax$vecmath$Vector3f()), tmp2);
/* 115:    */      
/* 120:120 */      jacobiansPool.release(jac);
/* 121:    */      
/* 123:123 */      float a = jacDiagABInv;
/* 124:    */      
/* 126:126 */      rel_vel = normal.dot(vel);
/* 127:    */      
/* 129:129 */      float contactDamping = 0.2F;
/* 130:    */      
/* 135:135 */      float velocityImpulse = -contactDamping * rel_vel * jacDiagABInv;
/* 136:136 */      impulse[0] = velocityImpulse;
/* 137:    */    } finally {
/* 138:138 */      .Stack tmp389_387 = localStack;tmp389_387.pop$com$bulletphysics$linearmath$Transform();tmp389_387.pop$javax$vecmath$Vector3f();
/* 139:    */    }
/* 140:    */  }
/* 141:    */  
/* 147:    */  public static float resolveSingleCollision(RigidBody arg0, RigidBody arg1, ManifoldPoint arg2, ContactSolverInfo arg3)
/* 148:    */  {
/* 149:149 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/* 150:    */      
/* 151:151 */      Vector3f pos1_ = contactPoint.getPositionWorldOnA(localStack.get$javax$vecmath$Vector3f());
/* 152:152 */      Vector3f pos2_ = contactPoint.getPositionWorldOnB(localStack.get$javax$vecmath$Vector3f());
/* 153:153 */      Vector3f normal = contactPoint.normalWorldOnB;
/* 154:    */      
/* 156:156 */      Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/* 157:157 */      rel_pos1.sub(pos1_, body1.getCenterOfMassPosition(tmpVec));
/* 158:    */      
/* 159:159 */      Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 160:160 */      rel_pos2.sub(pos2_, body2.getCenterOfMassPosition(tmpVec));
/* 161:    */      
/* 162:162 */      Vector3f vel1 = body1.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
/* 163:163 */      Vector3f vel2 = body2.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
/* 164:164 */      Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 165:165 */      vel.sub(vel1, vel2);
/* 166:    */      
/* 168:168 */      float rel_vel = normal.dot(vel);
/* 169:    */      
/* 170:170 */      float Kfps = 1.0F / solverInfo.timeStep;
/* 171:    */      
/* 173:173 */      float Kerp = solverInfo.erp;
/* 174:174 */      float Kcor = Kerp * Kfps;
/* 175:    */      
/* 176:176 */      ConstraintPersistentData cpd = (ConstraintPersistentData)contactPoint.userPersistentData;
/* 177:177 */      assert (cpd != null);
/* 178:178 */      float distance = cpd.penetration;
/* 179:179 */      float positionalError = Kcor * -distance;
/* 180:180 */      float velocityError = cpd.restitution - rel_vel;
/* 181:    */      
/* 182:182 */      float penetrationImpulse = positionalError * cpd.jacDiagABInv;
/* 183:    */      
/* 184:184 */      float velocityImpulse = velocityError * cpd.jacDiagABInv;
/* 185:    */      
/* 186:186 */      float normalImpulse = penetrationImpulse + velocityImpulse;
/* 187:    */      
/* 189:189 */      float oldNormalImpulse = cpd.appliedImpulse;
/* 190:190 */      float sum = oldNormalImpulse + normalImpulse;
/* 191:191 */      cpd.appliedImpulse = (0.0F > sum ? 0.0F : sum);
/* 192:    */      
/* 193:193 */      normalImpulse = cpd.appliedImpulse - oldNormalImpulse;
/* 194:    */      
/* 196:196 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 197:197 */      if (body1.getInvMass() != 0.0F) {
/* 198:198 */        tmp.scale(body1.getInvMass(), contactPoint.normalWorldOnB);
/* 199:199 */        body1.internalApplyImpulse(tmp, cpd.angularComponentA, normalImpulse);
/* 200:    */      }
/* 201:201 */      if (body2.getInvMass() != 0.0F) {
/* 202:202 */        tmp.scale(body2.getInvMass(), contactPoint.normalWorldOnB);
/* 203:203 */        body2.internalApplyImpulse(tmp, cpd.angularComponentB, -normalImpulse);
/* 204:    */      }
/* 205:    */      
/* 210:210 */      return normalImpulse; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 211:    */    }
/* 212:    */  }
/* 213:    */  
/* 217:    */  public static float resolveSingleFriction(RigidBody arg0, RigidBody arg1, ManifoldPoint arg2, ContactSolverInfo arg3)
/* 218:    */  {
/* 219:219 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/* 220:    */      
/* 221:221 */      Vector3f pos1 = contactPoint.getPositionWorldOnA(localStack.get$javax$vecmath$Vector3f());
/* 222:222 */      Vector3f pos2 = contactPoint.getPositionWorldOnB(localStack.get$javax$vecmath$Vector3f());
/* 223:    */      
/* 224:224 */      Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/* 225:225 */      rel_pos1.sub(pos1, body1.getCenterOfMassPosition(tmpVec));
/* 226:    */      
/* 227:227 */      Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 228:228 */      rel_pos2.sub(pos2, body2.getCenterOfMassPosition(tmpVec));
/* 229:    */      
/* 230:230 */      ConstraintPersistentData cpd = (ConstraintPersistentData)contactPoint.userPersistentData;
/* 231:231 */      assert (cpd != null);
/* 232:    */      
/* 233:233 */      float combinedFriction = cpd.friction;
/* 234:    */      
/* 235:235 */      float limit = cpd.appliedImpulse * combinedFriction;
/* 236:    */      
/* 237:237 */      if (cpd.appliedImpulse > 0.0F)
/* 238:    */      {
/* 242:242 */        Vector3f vel1 = localStack.get$javax$vecmath$Vector3f();
/* 243:243 */        body1.getVelocityInLocalPoint(rel_pos1, vel1);
/* 244:    */        
/* 245:245 */        Vector3f vel2 = localStack.get$javax$vecmath$Vector3f();
/* 246:246 */        body2.getVelocityInLocalPoint(rel_pos2, vel2);
/* 247:    */        
/* 248:248 */        Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 249:249 */        vel.sub(vel1, vel2);
/* 250:    */        
/* 254:254 */        float vrel = cpd.frictionWorldTangential0.dot(vel);
/* 255:    */        
/* 257:257 */        float j1 = -vrel * cpd.jacDiagABInvTangent0;
/* 258:258 */        float oldTangentImpulse = cpd.accumulatedTangentImpulse0;
/* 259:259 */        cpd.accumulatedTangentImpulse0 = (oldTangentImpulse + j1);
/* 260:    */        
/* 261:261 */        cpd.accumulatedTangentImpulse0 = Math.min(cpd.accumulatedTangentImpulse0, limit);
/* 262:262 */        cpd.accumulatedTangentImpulse0 = Math.max(cpd.accumulatedTangentImpulse0, -limit);
/* 263:263 */        j1 = cpd.accumulatedTangentImpulse0 - oldTangentImpulse;
/* 264:    */        
/* 268:268 */        float vrel = cpd.frictionWorldTangential1.dot(vel);
/* 269:    */        
/* 271:271 */        float j2 = -vrel * cpd.jacDiagABInvTangent1;
/* 272:272 */        float oldTangentImpulse = cpd.accumulatedTangentImpulse1;
/* 273:273 */        cpd.accumulatedTangentImpulse1 = (oldTangentImpulse + j2);
/* 274:    */        
/* 275:275 */        cpd.accumulatedTangentImpulse1 = Math.min(cpd.accumulatedTangentImpulse1, limit);
/* 276:276 */        cpd.accumulatedTangentImpulse1 = Math.max(cpd.accumulatedTangentImpulse1, -limit);
/* 277:277 */        j2 = cpd.accumulatedTangentImpulse1 - oldTangentImpulse;
/* 278:    */        
/* 281:281 */        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 282:    */        
/* 283:283 */        if (body1.getInvMass() != 0.0F) {
/* 284:284 */          tmp.scale(body1.getInvMass(), cpd.frictionWorldTangential0);
/* 285:285 */          body1.internalApplyImpulse(tmp, cpd.frictionAngularComponent0A, j1);
/* 286:    */          
/* 287:287 */          tmp.scale(body1.getInvMass(), cpd.frictionWorldTangential1);
/* 288:288 */          body1.internalApplyImpulse(tmp, cpd.frictionAngularComponent1A, j2);
/* 289:    */        }
/* 290:290 */        if (body2.getInvMass() != 0.0F) {
/* 291:291 */          tmp.scale(body2.getInvMass(), cpd.frictionWorldTangential0);
/* 292:292 */          body2.internalApplyImpulse(tmp, cpd.frictionAngularComponent0B, -j1);
/* 293:    */          
/* 294:294 */          tmp.scale(body2.getInvMass(), cpd.frictionWorldTangential1);
/* 295:295 */          body2.internalApplyImpulse(tmp, cpd.frictionAngularComponent1B, -j2);
/* 296:    */        }
/* 297:    */      }
/* 298:    */      
/* 302:302 */      return cpd.appliedImpulse; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 303:    */    }
/* 304:    */  }
/* 305:    */  
/* 313:    */  public static float resolveSingleCollisionCombined(RigidBody arg0, RigidBody arg1, ManifoldPoint arg2, ContactSolverInfo arg3)
/* 314:    */  {
/* 315:315 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f();tmp7_5.push$javax$vecmath$Matrix3f();Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/* 316:    */      
/* 317:317 */      Vector3f pos1 = contactPoint.getPositionWorldOnA(localStack.get$javax$vecmath$Vector3f());
/* 318:318 */      Vector3f pos2 = contactPoint.getPositionWorldOnB(localStack.get$javax$vecmath$Vector3f());
/* 319:319 */      Vector3f normal = contactPoint.normalWorldOnB;
/* 320:    */      
/* 321:321 */      Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/* 322:322 */      rel_pos1.sub(pos1, body1.getCenterOfMassPosition(tmpVec));
/* 323:    */      
/* 324:324 */      Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 325:325 */      rel_pos2.sub(pos2, body2.getCenterOfMassPosition(tmpVec));
/* 326:    */      
/* 327:327 */      Vector3f vel1 = body1.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
/* 328:328 */      Vector3f vel2 = body2.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
/* 329:329 */      Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 330:330 */      vel.sub(vel1, vel2);
/* 331:    */      
/* 333:333 */      float rel_vel = normal.dot(vel);
/* 334:    */      
/* 335:335 */      float Kfps = 1.0F / solverInfo.timeStep;
/* 336:    */      
/* 338:338 */      float Kerp = solverInfo.erp;
/* 339:339 */      float Kcor = Kerp * Kfps;
/* 340:    */      
/* 341:341 */      ConstraintPersistentData cpd = (ConstraintPersistentData)contactPoint.userPersistentData;
/* 342:342 */      assert (cpd != null);
/* 343:343 */      float distance = cpd.penetration;
/* 344:344 */      float positionalError = Kcor * -distance;
/* 345:345 */      float velocityError = cpd.restitution - rel_vel;
/* 346:    */      
/* 347:347 */      float penetrationImpulse = positionalError * cpd.jacDiagABInv;
/* 348:    */      
/* 349:349 */      float velocityImpulse = velocityError * cpd.jacDiagABInv;
/* 350:    */      
/* 351:351 */      float normalImpulse = penetrationImpulse + velocityImpulse;
/* 352:    */      
/* 354:354 */      float oldNormalImpulse = cpd.appliedImpulse;
/* 355:355 */      float sum = oldNormalImpulse + normalImpulse;
/* 356:356 */      cpd.appliedImpulse = (0.0F > sum ? 0.0F : sum);
/* 357:    */      
/* 358:358 */      normalImpulse = cpd.appliedImpulse - oldNormalImpulse;
/* 359:    */      
/* 362:362 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 363:363 */      if (body1.getInvMass() != 0.0F) {
/* 364:364 */        tmp.scale(body1.getInvMass(), contactPoint.normalWorldOnB);
/* 365:365 */        body1.internalApplyImpulse(tmp, cpd.angularComponentA, normalImpulse);
/* 366:    */      }
/* 367:367 */      if (body2.getInvMass() != 0.0F) {
/* 368:368 */        tmp.scale(body2.getInvMass(), contactPoint.normalWorldOnB);
/* 369:369 */        body2.internalApplyImpulse(tmp, cpd.angularComponentB, -normalImpulse);
/* 370:    */      }
/* 371:    */      
/* 378:378 */      body1.getVelocityInLocalPoint(rel_pos1, vel1);
/* 379:379 */      body2.getVelocityInLocalPoint(rel_pos2, vel2);
/* 380:380 */      vel.sub(vel1, vel2);
/* 381:    */      
/* 382:382 */      rel_vel = normal.dot(vel);
/* 383:    */      
/* 384:384 */      tmp.scale(rel_vel, normal);
/* 385:385 */      Vector3f lat_vel = localStack.get$javax$vecmath$Vector3f();
/* 386:386 */      lat_vel.sub(vel, tmp);
/* 387:387 */      float lat_rel_vel = lat_vel.length();
/* 388:    */      
/* 389:389 */      float combinedFriction = cpd.friction;
/* 390:    */      
/* 391:391 */      if ((cpd.appliedImpulse > 0.0F) && 
/* 392:392 */        (lat_rel_vel > 1.192093E-007F)) {
/* 393:393 */        lat_vel.scale(1.0F / lat_rel_vel);
/* 394:    */        
/* 395:395 */        Vector3f temp1 = localStack.get$javax$vecmath$Vector3f();
/* 396:396 */        temp1.cross(rel_pos1, lat_vel);
/* 397:397 */        body1.getInvInertiaTensorWorld(localStack.get$javax$vecmath$Matrix3f()).transform(temp1);
/* 398:    */        
/* 399:399 */        Vector3f temp2 = localStack.get$javax$vecmath$Vector3f();
/* 400:400 */        temp2.cross(rel_pos2, lat_vel);
/* 401:401 */        body2.getInvInertiaTensorWorld(localStack.get$javax$vecmath$Matrix3f()).transform(temp2);
/* 402:    */        
/* 403:403 */        Vector3f java_tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 404:404 */        java_tmp1.cross(temp1, rel_pos1);
/* 405:    */        
/* 406:406 */        Vector3f java_tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 407:407 */        java_tmp2.cross(temp2, rel_pos2);
/* 408:    */        
/* 409:409 */        tmp.add(java_tmp1, java_tmp2);
/* 410:    */        
/* 411:411 */        float friction_impulse = lat_rel_vel / (body1.getInvMass() + body2.getInvMass() + lat_vel.dot(tmp));
/* 412:    */        
/* 413:413 */        float normal_impulse = cpd.appliedImpulse * combinedFriction;
/* 414:    */        
/* 415:415 */        friction_impulse = Math.min(friction_impulse, normal_impulse);
/* 416:416 */        friction_impulse = Math.max(friction_impulse, -normal_impulse);
/* 417:    */        
/* 418:418 */        tmp.scale(-friction_impulse, lat_vel);
/* 419:419 */        body1.applyImpulse(tmp, rel_pos1);
/* 420:    */        
/* 421:421 */        tmp.scale(friction_impulse, lat_vel);
/* 422:422 */        body2.applyImpulse(tmp, rel_pos2);
/* 423:    */      }
/* 424:    */      
/* 427:427 */      return normalImpulse; } finally { .Stack tmp665_663 = localStack;tmp665_663.pop$javax$vecmath$Vector3f();tmp665_663.pop$javax$vecmath$Matrix3f();
/* 428:    */    }
/* 429:    */  }
/* 430:    */  
/* 433:    */  public static float resolveSingleFrictionEmpty(RigidBody body1, RigidBody body2, ManifoldPoint contactPoint, ContactSolverInfo solverInfo)
/* 434:    */  {
/* 435:435 */    return 0.0F;
/* 436:    */  }
/* 437:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.ContactConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */