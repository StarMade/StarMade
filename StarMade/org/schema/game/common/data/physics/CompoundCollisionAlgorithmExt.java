/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*   4:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*   5:    */import com.bulletphysics.collision.broadphase.Dispatcher;
/*   6:    */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*   7:    */import com.bulletphysics.collision.dispatch.CollisionObject;
/*   8:    */import com.bulletphysics.collision.dispatch.ManifoldResult;
/*   9:    */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*  10:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*  11:    */import com.bulletphysics.collision.shapes.CompoundShape;
/*  12:    */import com.bulletphysics.dynamics.DynamicsWorld;
/*  13:    */import com.bulletphysics.linearmath.Transform;
/*  14:    */import com.bulletphysics.util.ObjectArrayList;
/*  15:    */import java.io.PrintStream;
/*  16:    */import org.schema.game.common.controller.SegmentController;
/*  17:    */
/*  23:    */public class CompoundCollisionAlgorithmExt
/*  24:    */  extends CollisionAlgorithm
/*  25:    */{
/*  26:    */  private CollisionObject compoundObject;
/*  27:    */  private CollisionObject otherObject;
/*  28:    */  public boolean swapped;
/*  29:    */  private PersistentManifold manifoldPtr;
/*  30: 30 */  private static ThreadLocal threadLocal = new CompoundCollisionAlgorithmExt.1();
/*  31:    */  
/*  84: 84 */  private final ObjectArrayList childCollisionAlgorithms = new ObjectArrayList();
/*  85: 85 */  private final ObjectArrayList childCollisionAlgorithmsCOM = new ObjectArrayList();
/*  86:    */  
/* 100:    */  private CompoundCollisionVariableSet v;
/* 101:    */  
/* 115:    */  public String toString()
/* 116:    */  {
/* 117:117 */    return "CompoundAlgo[" + this.compoundObject + "->" + this.otherObject + "]";
/* 118:    */  }
/* 119:    */  
/* 120:    */  public void init(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo) {
/* 121:121 */    super.init(paramCollisionAlgorithmConstructionInfo);
/* 122:    */    
/* 128:128 */    this.v = ((CompoundCollisionVariableSet)threadLocal.get());
/* 129:129 */    this.v.instances += 1;
/* 130:    */    
/* 131:131 */    assert (this.compoundObject.getCollisionShape().isCompound());
/* 132:    */    
/* 133:133 */    assert ((!(this.compoundObject.getCollisionShape() instanceof CubeShape)) && (!(this.otherObject.getCollisionShape() instanceof CubeShape))) : (this.compoundObject + " --- " + this.otherObject);
/* 134:    */    
/* 135:    */    CompoundShape localCompoundShape;
/* 136:136 */    int i = (localCompoundShape = (CompoundShape)this.compoundObject.getCollisionShape()).getNumChildShapes();
/* 137:    */    
/* 138:138 */    for (int j = 0; j < i; 
/* 139:    */        
/* 140:140 */        j++) {
/* 141:141 */      CollisionShape localCollisionShape1 = this.compoundObject.getCollisionShape();
/* 142:142 */      Object localObject = localCompoundShape.getChildShape(j);
/* 143:143 */      this.compoundObject.internalSetTemporaryCollisionShape((CollisionShape)localObject);
/* 144:    */      
/* 146:146 */      if (this.otherObject.getCollisionShape().isCompound()) {
/* 147:147 */        localObject = (CompoundShape)this.otherObject.getCollisionShape();
/* 148:148 */        for (int k = 0; k < ((CompoundShape)localObject).getNumChildShapes(); k++)
/* 149:    */        {
/* 150:150 */          CollisionShape localCollisionShape2 = this.otherObject.getCollisionShape();
/* 151:151 */          CollisionShape localCollisionShape3 = ((CompoundShape)localObject).getChildShape(k);
/* 152:152 */          this.otherObject.internalSetTemporaryCollisionShape(localCollisionShape3);
/* 153:    */          
/* 154:154 */          this.childCollisionAlgorithmsCOM.add(paramCollisionAlgorithmConstructionInfo.dispatcher1.findAlgorithm(this.compoundObject, this.otherObject));
/* 155:    */          
/* 156:156 */          this.otherObject.internalSetTemporaryCollisionShape(localCollisionShape2);
/* 157:    */        }
/* 158:    */      } else {
/* 159:159 */        this.childCollisionAlgorithms.add(paramCollisionAlgorithmConstructionInfo.dispatcher1.findAlgorithm(this.compoundObject, this.otherObject));
/* 160:    */      }
/* 161:    */      
/* 164:164 */      this.compoundObject.internalSetTemporaryCollisionShape(localCollisionShape1);
/* 165:    */    }
/* 166:    */  }
/* 167:    */  
/* 169:    */  public void destroy()
/* 170:    */  {
/* 171:171 */    if (this.manifoldPtr != null)
/* 172:    */    {
/* 173:173 */      this.dispatcher.releaseManifold(this.manifoldPtr);
/* 174:174 */      this.manifoldPtr = null;
/* 175:    */    }
/* 176:176 */    else if (!$assertionsDisabled) { throw new AssertionError();
/* 177:    */    }
/* 178:    */    
/* 179:179 */    int i = this.childCollisionAlgorithms.size();
/* 180:180 */    for (int j = 0; j < i; j++) {
/* 181:181 */      ((CollisionAlgorithm)this.childCollisionAlgorithms.get(j)).destroy();
/* 182:182 */      this.dispatcher.freeCollisionAlgorithm((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(j));
/* 183:    */    }
/* 184:184 */    this.childCollisionAlgorithms.clear();
/* 185:    */    
/* 187:187 */    i = this.childCollisionAlgorithmsCOM.size();
/* 188:188 */    for (j = 0; j < i; j++)
/* 189:    */    {
/* 190:190 */      this.dispatcher.freeCollisionAlgorithm((CollisionAlgorithm)this.childCollisionAlgorithmsCOM.getQuick(j));
/* 191:    */    }
/* 192:192 */    this.childCollisionAlgorithmsCOM.clear();
/* 193:    */    
/* 194:194 */    this.compoundObject = null;
/* 195:195 */    this.otherObject = null;
/* 196:196 */    this.childCollisionAlgorithms.clear();
/* 197:197 */    this.childCollisionAlgorithmsCOM.clear();
/* 198:198 */    this.v.instances -= 1;
/* 199:    */  }
/* 200:    */  
/* 207:    */  public void processCollision(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/* 208:    */  {
/* 209:209 */    if ((paramCollisionObject1 == this.otherObject) && (paramCollisionObject2 == this.compoundObject))
/* 210:    */    {
/* 211:211 */      paramCollisionObject1 = this.compoundObject;
/* 212:212 */      paramCollisionObject2 = this.otherObject;
/* 213:    */    }
/* 214:    */    
/* 217:217 */    if ((paramCollisionObject1 != this.compoundObject) || (paramCollisionObject2 != this.otherObject)) {
/* 218:218 */      System.err.println("COMPOUND ALGORITHM MULTIUSE ?!?!\n---> " + paramCollisionObject1 + ";         " + this.compoundObject + "\n---> " + paramCollisionObject2 + ";         " + this.otherObject);
/* 219:    */    }
/* 220:    */    
/* 225:225 */    assert (paramCollisionObject1 == this.compoundObject) : (paramCollisionObject1 + "; " + this.compoundObject);
/* 226:226 */    assert (paramCollisionObject2 == this.otherObject) : (paramCollisionObject2 + "; " + this.otherObject);
/* 227:227 */    if (this.manifoldPtr == null) {
/* 228:228 */      this.manifoldPtr = this.dispatcher.getNewManifold(paramCollisionObject1, paramCollisionObject2);
/* 229:    */    }
/* 230:230 */    if ((this.manifoldPtr.getBody0() != paramCollisionObject1) || (this.manifoldPtr.getBody1() != paramCollisionObject2))
/* 231:    */    {
/* 232:232 */      this.dispatcher.releaseManifold(this.manifoldPtr);
/* 233:233 */      System.err.println("[COMPOUNDECOLLISION] Exception: wrong manifold: \n----> " + this.manifoldPtr.getBody0() + " != " + paramCollisionObject1 + " or \n----> " + this.manifoldPtr.getBody1() + " != " + paramCollisionObject2);
/* 234:    */      
/* 238:238 */      localObject1 = ((RigidBodyExt)this.compoundObject).getSegmentController().getPhysics().getDynamicsWorld().getCollisionObjectArray();
/* 239:239 */      for (int i = 0; i < ((ObjectArrayList)localObject1).size(); i++) {
/* 240:240 */        System.err.println("OBJECTS LISTED " + ((ObjectArrayList)localObject1).getQuick(i));
/* 241:    */      }
/* 242:242 */      this.manifoldPtr = this.dispatcher.getNewManifold(paramCollisionObject1, paramCollisionObject2);
/* 243:    */    }
/* 244:244 */    paramManifoldResult.setPersistentManifold(this.manifoldPtr);
/* 245:245 */    assert (this.compoundObject.getCollisionShape().isCompound());
/* 246:246 */    Object localObject1 = (CompoundShape)this.compoundObject.getCollisionShape();
/* 247:    */    
/* 256:256 */    paramCollisionObject1 = this.v.orgTrans;
/* 257:257 */    paramCollisionObject2 = this.v.chieldTrans;
/* 258:258 */    Transform localTransform1 = this.v.interpolationTrans;
/* 259:259 */    Transform localTransform2 = this.v.newChildWorldTrans;
/* 260:    */    
/* 261:261 */    int j = ((CompoundShape)localObject1).getNumChildShapes();
/* 262:    */    
/* 263:263 */    int m = 0;
/* 264:264 */    for (int k = 0; k < j; k++)
/* 265:    */    {
/* 266:266 */      Object localObject2 = ((CompoundShape)localObject1).getChildShape(k);
/* 267:    */      
/* 269:269 */      this.compoundObject.getWorldTransform(paramCollisionObject1);
/* 270:270 */      this.compoundObject.getInterpolationWorldTransform(localTransform1);
/* 271:    */      
/* 272:272 */      ((CompoundShape)localObject1).getChildTransform(k, paramCollisionObject2);
/* 273:273 */      localTransform2.mul(paramCollisionObject1, paramCollisionObject2);
/* 274:274 */      this.compoundObject.setWorldTransform(localTransform2);
/* 275:275 */      this.compoundObject.setInterpolationWorldTransform(localTransform2);
/* 276:    */      
/* 278:278 */      CollisionShape localCollisionShape = this.compoundObject.getCollisionShape();
/* 279:279 */      this.compoundObject.internalSetTemporaryCollisionShape((CollisionShape)localObject2);
/* 280:    */      
/* 282:    */      Object localObject3;
/* 283:    */      
/* 284:284 */      if (this.otherObject.getCollisionShape().isCompound()) {
/* 285:285 */        localObject2 = (CompoundShape)this.otherObject.getCollisionShape();
/* 286:286 */        for (int n = 0; n < ((CompoundShape)localObject2).getNumChildShapes(); n++) {
/* 287:287 */          localObject3 = (CollisionAlgorithm)this.childCollisionAlgorithmsCOM.getQuick(m);
/* 288:    */          
/* 289:289 */          Transform localTransform3 = this.v.orgTransO;
/* 290:290 */          Object localObject4 = this.v.chieldTransO;
/* 291:291 */          Transform localTransform4 = this.v.interpolationTransO;
/* 292:292 */          Object localObject5 = this.v.newChildWorldTransO;
/* 293:    */          
/* 294:294 */          Object localObject6 = ((CompoundShape)localObject2).getChildShape(n);
/* 295:    */          
/* 297:297 */          this.otherObject.getWorldTransform(localTransform3);
/* 298:298 */          this.otherObject.getInterpolationWorldTransform(localTransform4);
/* 299:    */          
/* 300:300 */          ((CompoundShape)localObject2).getChildTransform(n, (Transform)localObject4);
/* 301:301 */          ((Transform)localObject5).mul(localTransform3, (Transform)localObject4);
/* 302:302 */          this.otherObject.setWorldTransform((Transform)localObject5);
/* 303:303 */          this.otherObject.setInterpolationWorldTransform((Transform)localObject5);
/* 304:    */          
/* 306:306 */          localObject4 = this.otherObject.getCollisionShape();
/* 307:307 */          this.otherObject.internalSetTemporaryCollisionShape((CollisionShape)localObject6);
/* 308:    */          
/* 311:311 */          localObject5 = this.compoundObject;
/* 312:    */          
/* 331:331 */          localObject6 = this.otherObject;
/* 332:    */          
/* 340:340 */          ((CollisionAlgorithm)localObject3).processCollision((CollisionObject)localObject5, (CollisionObject)localObject6, paramDispatcherInfo, paramManifoldResult);
/* 341:    */          
/* 343:343 */          this.otherObject.internalSetTemporaryCollisionShape((CollisionShape)localObject4);
/* 344:344 */          this.otherObject.setWorldTransform(localTransform3);
/* 345:345 */          this.otherObject.setInterpolationWorldTransform(localTransform4);
/* 346:346 */          m++;
/* 347:    */        }
/* 348:    */      } else {
/* 349:349 */        localObject2 = (CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(k);
/* 350:350 */        if (((this.compoundObject.getCollisionShape() instanceof CubeShape)) && ((this.otherObject.getCollisionShape() instanceof CubeShape))) {
/* 351:351 */          if (!$assertionsDisabled) throw new AssertionError();
/* 352:352 */          System.err.println("CC COLLISSION sw " + this.swapped + "; " + this.compoundObject.getCollisionShape() + "; " + this.otherObject.getCollisionShape());
/* 353:    */          
/* 359:359 */          CollisionObject localCollisionObject = this.compoundObject;
/* 360:360 */          localObject3 = this.otherObject;
/* 361:361 */          this.dispatcher.findAlgorithm(localCollisionObject, (CollisionObject)localObject3)
/* 362:362 */            .processCollision(localCollisionObject, (CollisionObject)localObject3, paramDispatcherInfo, paramManifoldResult);
/* 364:    */        }
/* 365:    */        else
/* 366:    */        {
/* 367:367 */          ((CollisionAlgorithm)localObject2).processCollision(this.compoundObject, this.otherObject, paramDispatcherInfo, paramManifoldResult);
/* 368:    */        }
/* 369:    */      }
/* 370:    */      
/* 374:374 */      this.compoundObject.internalSetTemporaryCollisionShape(localCollisionShape);
/* 375:375 */      this.compoundObject.setWorldTransform(paramCollisionObject1);
/* 376:376 */      this.compoundObject.setInterpolationWorldTransform(localTransform1);
/* 377:    */    }
/* 378:    */  }
/* 379:    */  
/* 458:    */  public float calculateTimeOfImpact(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/* 459:    */  {
/* 460:460 */    assert (this.compoundObject.getCollisionShape().isCompound());
/* 461:    */    
/* 462:462 */    paramCollisionObject1 = (CompoundShape)this.compoundObject.getCollisionShape();
/* 463:    */    
/* 471:471 */    paramCollisionObject2 = this.v.tmpTrans;
/* 472:472 */    Transform localTransform1 = this.v.orgTrans;
/* 473:473 */    Transform localTransform2 = this.v.chieldTrans;
/* 474:474 */    float f1 = 1.0F;
/* 475:    */    
/* 476:476 */    int i = this.childCollisionAlgorithms.size();
/* 477:    */    
/* 478:478 */    for (int j = 0; j < i; j++)
/* 479:    */    {
/* 480:480 */      CollisionShape localCollisionShape1 = paramCollisionObject1.getChildShape(j);
/* 481:    */      
/* 483:483 */      this.compoundObject.getWorldTransform(localTransform1);
/* 484:    */      
/* 485:485 */      paramCollisionObject1.getChildTransform(j, localTransform2);
/* 486:    */      
/* 487:487 */      paramCollisionObject2.set(localTransform1);
/* 488:488 */      paramCollisionObject2.mul(localTransform2);
/* 489:489 */      this.compoundObject.setWorldTransform(paramCollisionObject2);
/* 490:    */      
/* 491:491 */      CollisionShape localCollisionShape2 = this.compoundObject.getCollisionShape();
/* 492:492 */      this.compoundObject.internalSetTemporaryCollisionShape(localCollisionShape1);
/* 493:    */      float f2;
/* 494:494 */      if ((f2 = ((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(j)).calculateTimeOfImpact(this.compoundObject, this.otherObject, paramDispatcherInfo, paramManifoldResult)) < f1) {
/* 495:495 */        f1 = f2;
/* 496:    */      }
/* 497:    */      
/* 498:498 */      this.compoundObject.internalSetTemporaryCollisionShape(localCollisionShape2);
/* 499:499 */      this.compoundObject.setWorldTransform(localTransform1);
/* 500:    */    }
/* 501:501 */    return f1;
/* 502:    */  }
/* 503:    */  
/* 504:    */  public void getAllContactManifolds(ObjectArrayList paramObjectArrayList)
/* 505:    */  {
/* 506:506 */    for (int i = 0; i < this.childCollisionAlgorithms.size(); i++) {
/* 507:507 */      ((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(i)).getAllContactManifolds(paramObjectArrayList);
/* 508:    */    }
/* 509:    */  }
/* 510:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CompoundCollisionAlgorithmExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */