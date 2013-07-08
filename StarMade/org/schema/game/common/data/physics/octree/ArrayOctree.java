/*   1:    */package org.schema.game.common.data.physics.octree;
/*   2:    */
/*   3:    */import com.bulletphysics.linearmath.AabbUtil2;
/*   4:    */import com.bulletphysics.linearmath.Transform;
/*   5:    */import com.bulletphysics.linearmath.VectorUtil;
/*   6:    */import java.io.PrintStream;
/*   7:    */import java.nio.ByteBuffer;
/*   8:    */import java.nio.IntBuffer;
/*   9:    */import java.util.Arrays;
/*  10:    */import javax.vecmath.Matrix3f;
/*  11:    */import javax.vecmath.Vector3f;
/*  12:    */import o;
/*  13:    */import org.lwjgl.BufferUtils;
/*  14:    */import org.schema.game.common.data.world.Segment;
/*  15:    */import q;
/*  16:    */import xO;
/*  17:    */
/*  28:    */public class ArrayOctree
/*  29:    */{
/*  30:    */  private final short[] data;
/*  31:    */  private final boolean[] hits;
/*  32:    */  private final OctreeVariableSet set;
/*  33:    */  static final int MAX_DEEPNESS = 2;
/*  34:    */  static final int COUNT_LVLS = 3;
/*  35:    */  static final int NODES;
/*  36:    */  public static final short HIT_BIT = 16384;
/*  37:    */  public static final int POSSIBLE_LEAF_HITS = 512;
/*  38:    */  static final int BUFFER_SIZE;
/*  39:    */  static ByteBuffer dimBuffer;
/*  40:    */  static IntBuffer indexBuffer;
/*  41:    */  
/*  42:    */  static
/*  43:    */  {
/*  44: 44 */    NODES = 585;
/*  45:    */    
/*  54: 54 */    dimBuffer = BufferUtils.createByteBuffer(ArrayOctree.BUFFER_SIZE = 3510);
/*  55:    */    
/*  56: 56 */    indexBuffer = BufferUtils.createIntBuffer(12288);
/*  57:    */    
/*  60: 60 */    ArrayOctreeGenerator.splitStart(new o(-8.0F, -8.0F, -8.0F), new o(8.0F, 8.0F, 8.0F), (byte)8);
/*  61: 61 */    ArrayOctreeTraverse.create(); }
/*  62:    */  
/*  63: 63 */  private static OctreeVariableSet serverSet = new OctreeVariableSet();
/*  64: 64 */  private static OctreeVariableSet clientSet = new OctreeVariableSet();
/*  65:    */  
/*  66:    */  public ArrayOctree(boolean paramBoolean) {
/*  67: 67 */    this.data = new short[NODES];
/*  68: 68 */    this.hits = new boolean[NODES];
/*  69: 69 */    if (paramBoolean) {
/*  70: 70 */      this.set = serverSet;return;
/*  71:    */    }
/*  72: 72 */    this.set = clientSet;
/*  73:    */  }
/*  74:    */  
/* 133:    */  public static byte getStart(int paramInt1, int paramInt2)
/* 134:    */  {
/* 135:135 */    paramInt2 *= 6;
/* 136:136 */    return dimBuffer.get(paramInt2 + paramInt1);
/* 137:    */  }
/* 138:    */  
/* 139:    */  public static byte getEnd(int paramInt1, int paramInt2) {
/* 140:140 */    paramInt2 *= 6;
/* 141:141 */    return dimBuffer.get(paramInt2 + 3 + paramInt1);
/* 142:    */  }
/* 143:    */  
/* 144:    */  public static void getBox(int paramInt, o paramo1, o paramo2) {
/* 145:145 */    paramInt *= 6;
/* 146:146 */    paramo1.b(dimBuffer.get(paramInt), dimBuffer.get(paramInt + 1), dimBuffer.get(paramInt + 2));
/* 147:147 */    paramo2.b(dimBuffer.get(paramInt + 3), dimBuffer.get(paramInt + 4), dimBuffer.get(paramInt + 5));
/* 148:    */  }
/* 149:    */  
/* 150:    */  public static void main(String[] paramArrayOfString)
/* 151:    */  {
/* 152:152 */    System.err.println(128);
/* 153:    */  }
/* 154:    */  
/* 173:    */  public static int getIndex(int paramInt1, int paramInt2)
/* 174:    */  {
/* 175:175 */    if (paramInt2 == 0) {
/* 176:176 */      return paramInt1 + 1;
/* 177:    */    }
/* 178:    */    
/* 179:179 */    int i = 1;
/* 180:180 */    int j = 8;
/* 181:181 */    for (int k = 0; k < paramInt2; k++) {
/* 182:182 */      i += j;
/* 183:183 */      j <<= 3;
/* 184:    */    }
/* 185:    */    
/* 188:188 */    return i + paramInt1;
/* 189:    */  }
/* 190:    */  
/* 191:    */  private static int getSuperIndex(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/* 192:    */  {
/* 193:193 */    switch (paramInt4)
/* 194:    */    {
/* 195:    */    case 0: 
/* 196:196 */      return paramInt1;
/* 197:197 */    case 1:  return (paramInt1 << 3) + paramInt2;
/* 198:198 */    case 2:  return (paramInt1 << 3 << 3) + (paramInt2 << 3) + paramInt3;
/* 199:    */    }
/* 200:200 */    throw new IllegalArgumentException();
/* 201:    */  }
/* 202:    */  
/* 204:    */  public void insert(byte paramByte1, byte paramByte2, byte paramByte3)
/* 205:    */  {
/* 206:206 */    for (int i = 0; i < 3; i++) {
/* 207:207 */      int j = ArrayOctreeGenerator.getNodeIndex(paramByte1, paramByte2, paramByte3, i);
/* 208:208 */      if (i == 2) {
/* 209:209 */        int k = (paramByte3 % 2 << 2) + (paramByte2 % 2 << 1) + paramByte1 % 2;
/* 210:    */        
/* 211:211 */        k = (short)(1 << k); int 
/* 212:212 */          tmp55_53 = j; short[] tmp55_50 = this.data;tmp55_50[tmp55_53] = ((short)(tmp55_50[tmp55_53] | k));
/* 213:    */        
/* 214:214 */        assert (this.data[j] > 0);
/* 215:    */      } else {
/* 216:216 */        int tmp95_93 = j; short[] tmp95_90 = this.data;tmp95_90[tmp95_93] = ((short)(tmp95_90[tmp95_93] + 1));
/* 217:    */      }
/* 218:    */    }
/* 219:    */  }
/* 220:    */  
/* 221:221 */  public void delete(byte paramByte1, byte paramByte2, byte paramByte3) { for (int i = 0; i < 3; i++) {
/* 222:222 */      int j = ArrayOctreeGenerator.getNodeIndex(paramByte1, paramByte2, paramByte3, i);
/* 223:223 */      if (i == 2)
/* 224:    */      {
/* 225:225 */        int k = (paramByte3 % 2 << 2) + (paramByte2 % 2 << 1) + paramByte1 % 2;
/* 226:    */        
/* 227:227 */        k = (short)(1 << k); int 
/* 228:228 */          tmp55_53 = j; short[] tmp55_50 = this.data;tmp55_50[tmp55_53] = ((short)(tmp55_50[tmp55_53] & (k ^ 0xFFFFFFFF)));
/* 229:    */      } else {
/* 230:230 */        int tmp73_71 = j; short[] tmp73_68 = this.data;tmp73_68[tmp73_71] = ((short)(tmp73_68[tmp73_71] - 1));
/* 231:    */      }
/* 232:    */      
/* 233:233 */      if (this.data[j] < 0) {
/* 234:234 */        System.err.println("[OCTREE] EXCEPTION: NODE < 0");
/* 235:235 */        this.data[j] = 0;
/* 236:    */      }
/* 237:    */    }
/* 238:    */  }
/* 239:    */  
/* 240:240 */  public void reset() { Arrays.fill(this.data, (short)0); }
/* 241:    */  
/* 242:    */  public void setHasHit(int paramInt, boolean paramBoolean) {
/* 243:243 */    this.hits[paramInt] = paramBoolean;
/* 244:    */  }
/* 245:    */  
/* 250:    */  public boolean isHasHit(int paramInt)
/* 251:    */  {
/* 252:252 */    return this.hits[paramInt];
/* 253:    */  }
/* 254:    */  
/* 257:    */  public IntersectionCallback findIntersecting(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
/* 258:    */  {
/* 259:259 */    for (int i = 0; i < 8; i++) {
/* 260:260 */      paramIntersectionCallback = findIntersecting(i, 0, paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2);
/* 261:    */    }
/* 262:262 */    return paramIntersectionCallback;
/* 263:    */  }
/* 264:    */  
/* 267:    */  private IntersectionCallback findIntersecting(int paramInt1, int paramInt2, OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
/* 268:    */  {
/* 269:269 */    int i = getIndex(paramInt1, paramInt2);
/* 270:270 */    if (this.data[i] > 0)
/* 271:    */    {
/* 272:272 */      paramIntersectionCallback = doIntersecting(paramInt1, paramInt2, paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2);
/* 273:    */      
/* 274:274 */      if ((paramInt2 < 2) && 
/* 275:275 */        (isHasHit(i))) {
/* 276:276 */        paramInt1 <<= 3;
/* 277:277 */        for (i = 0; i < 8; i++) {
/* 278:278 */          paramIntersectionCallback = findIntersecting(paramInt1 + i, paramInt2 + 1, paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2);
/* 279:    */        }
/* 280:    */      }
/* 281:    */    }
/* 282:    */    else
/* 283:    */    {
/* 284:284 */      setHasHit(i, false);
/* 285:    */    }
/* 286:286 */    return paramIntersectionCallback;
/* 287:    */  }
/* 288:    */  
/* 291:    */  private IntersectionCallback doIntersecting(int paramInt1, int paramInt2, OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
/* 292:    */  {
/* 293:293 */    paramIntersectionCallback.leafCalcs += 1;
/* 294:    */    
/* 296:296 */    getBox(paramInt1 = getIndex(paramInt1, paramInt2), paramOctreeVariableSet.min, paramOctreeVariableSet.max);
/* 297:    */    
/* 298:298 */    paramFloat2 = paramOctreeVariableSet.tmpMin;
/* 299:299 */    Vector3f localVector3f1 = paramOctreeVariableSet.tmpMax;
/* 300:300 */    Vector3f localVector3f2 = paramOctreeVariableSet.tmpMinOut;
/* 301:301 */    Vector3f localVector3f3 = paramOctreeVariableSet.tmpMaxOut;
/* 302:    */    
/* 304:304 */    float f1 = paramSegment.a.a - 0.5F;
/* 305:305 */    float f2 = paramSegment.a.b - 0.5F;
/* 306:306 */    paramSegment = paramSegment.a.c - 0.5F;
/* 307:    */    
/* 308:308 */    paramFloat2.x = (paramOctreeVariableSet.min.a + f1);
/* 309:309 */    paramFloat2.y = (paramOctreeVariableSet.min.b + f2);
/* 310:310 */    paramFloat2.z = (paramOctreeVariableSet.min.c + paramSegment);
/* 311:    */    
/* 312:312 */    localVector3f1.x = (paramOctreeVariableSet.max.a + f1);
/* 313:313 */    localVector3f1.y = (paramOctreeVariableSet.max.b + f2);
/* 314:314 */    localVector3f1.z = (paramOctreeVariableSet.max.c + paramSegment);
/* 315:    */    
/* 316:316 */    transformAabb(paramOctreeVariableSet, paramInt2, paramFloat2, localVector3f1, paramMatrix3f, paramFloat1, paramTransform, localVector3f2, localVector3f3);
/* 317:    */    
/* 322:322 */    paramSegment = AabbUtil2.testAabbAgainstAabb2(localVector3f2, localVector3f3, paramVector3f1, paramVector3f2);
/* 323:    */    
/* 324:324 */    setHasHit(paramInt1, paramSegment);
/* 325:    */    
/* 327:327 */    if ((paramSegment != 0) && (paramInt2 == 2))
/* 328:    */    {
/* 331:331 */      assert (this.data[paramInt1] > 0);
/* 332:332 */      paramIntersectionCallback.addHit(localVector3f2, localVector3f3, paramOctreeVariableSet.min.a, paramOctreeVariableSet.min.b, paramOctreeVariableSet.min.c, paramOctreeVariableSet.max.a, paramOctreeVariableSet.max.b, paramOctreeVariableSet.max.c, this.data[paramInt1]);
/* 333:    */    }
/* 334:    */    
/* 347:347 */    return paramIntersectionCallback;
/* 348:    */  }
/* 349:    */  
/* 352:    */  public IntersectionCallback findIntersectingRay(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
/* 353:    */  {
/* 354:354 */    for (int i = 0; i < 8; i++) {
/* 355:355 */      paramIntersectionCallback = findIntersectingRay(i, 0, paramOctreeVariableSet, paramIntersectionCallback, paramTransform, paramMatrix3f, paramFloat1, paramSegment, paramVector3f1, paramVector3f2, paramFloat2);
/* 356:    */    }
/* 357:357 */    return paramIntersectionCallback;
/* 358:    */  }
/* 359:    */  
/* 362:    */  private IntersectionCallback findIntersectingRay(int paramInt1, int paramInt2, OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
/* 363:    */  {
/* 364:364 */    int i = getIndex(paramInt1, paramInt2);
/* 365:    */    
/* 366:366 */    if ((this.data[i] > 0) || (paramInt2 == 0))
/* 367:    */    {
/* 368:368 */      paramIntersectionCallback = doIntersectingRay(paramInt1, paramInt2, paramOctreeVariableSet, paramIntersectionCallback, paramTransform, paramMatrix3f, paramFloat1, paramSegment, paramVector3f1, paramVector3f2, paramFloat2);
/* 369:    */      
/* 370:370 */      if ((paramInt2 < 2) && 
/* 371:371 */        (isHasHit(i))) {
/* 372:372 */        paramInt1 <<= 3;
/* 373:373 */        for (i = 0; i < 8; i++) {
/* 374:374 */          paramIntersectionCallback = findIntersectingRay(paramInt1 + i, paramInt2 + 1, paramOctreeVariableSet, paramIntersectionCallback, paramTransform, paramMatrix3f, paramFloat1, paramSegment, paramVector3f1, paramVector3f2, paramFloat2);
/* 375:    */        }
/* 376:    */      }
/* 377:    */    }
/* 378:    */    else {
/* 379:379 */      setHasHit(i, false);
/* 380:    */    }
/* 381:381 */    return paramIntersectionCallback;
/* 382:    */  }
/* 383:    */  
/* 386:    */  private IntersectionCallback doIntersectingRay(int paramInt1, int paramInt2, OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
/* 387:    */  {
/* 388:388 */    paramInt1 = getIndex(paramInt1, paramInt2);
/* 389:    */    
/* 390:390 */    paramIntersectionCallback.leafCalcs += 1;
/* 391:    */    
/* 392:392 */    paramFloat2 = paramOctreeVariableSet.tmpMin;
/* 393:393 */    Vector3f localVector3f1 = paramOctreeVariableSet.tmpMax;
/* 394:394 */    Vector3f localVector3f2 = paramOctreeVariableSet.tmpMinOut;
/* 395:395 */    Vector3f localVector3f3 = paramOctreeVariableSet.tmpMaxOut;
/* 396:    */    
/* 397:397 */    getBox(paramInt1, paramOctreeVariableSet.min, paramOctreeVariableSet.max);
/* 398:    */    
/* 399:399 */    float f1 = paramSegment.a.a - 0.5F;
/* 400:400 */    float f2 = paramSegment.a.b - 0.5F;
/* 401:401 */    paramSegment = paramSegment.a.c - 0.5F;
/* 402:    */    
/* 403:403 */    paramFloat2.x = (paramOctreeVariableSet.min.a + f1);
/* 404:404 */    paramFloat2.y = (paramOctreeVariableSet.min.b + f2);
/* 405:405 */    paramFloat2.z = (paramOctreeVariableSet.min.c + paramSegment);
/* 406:    */    
/* 407:407 */    localVector3f1.x = (paramOctreeVariableSet.max.a + f1);
/* 408:408 */    localVector3f1.y = (paramOctreeVariableSet.max.b + f2);
/* 409:409 */    localVector3f1.z = (paramOctreeVariableSet.max.c + paramSegment);
/* 410:    */    
/* 412:412 */    transformAabb(paramOctreeVariableSet, paramInt2, paramFloat2, localVector3f1, paramMatrix3f, paramFloat1, paramTransform, localVector3f2, localVector3f3);
/* 413:    */    
/* 415:415 */    paramOctreeVariableSet.param[0] = 1.0F;
/* 416:416 */    paramOctreeVariableSet.normal.x = 0.0F;
/* 417:417 */    paramOctreeVariableSet.normal.y = 0.0F;
/* 418:418 */    paramOctreeVariableSet.normal.z = 0.0F;
/* 419:    */    
/* 420:420 */    paramTransform = rayAabb(paramVector3f1, paramVector3f2, localVector3f2, localVector3f3, paramOctreeVariableSet.param, paramOctreeVariableSet.normal);
/* 421:    */    
/* 423:423 */    paramMatrix3f = 0;
/* 424:424 */    if (paramTransform == 0) {
/* 425:425 */      paramMatrix3f = (xO.a(paramVector3f2, localVector3f2, localVector3f3)) || (xO.a(paramVector3f1, localVector3f2, localVector3f3)) ? 1 : 0;
/* 426:    */    }
/* 427:    */    
/* 428:428 */    paramTransform = (paramTransform != 0) || (paramMatrix3f != 0) ? 1 : 0;
/* 429:429 */    setHasHit(paramInt1, paramTransform);
/* 430:    */    
/* 432:432 */    if ((paramTransform != 0) && (paramInt2 == 2)) {
/* 433:433 */      assert (this.data[paramInt1] > 0);
/* 434:434 */      paramIntersectionCallback.addHit(localVector3f2, localVector3f3, paramOctreeVariableSet.min.a, paramOctreeVariableSet.min.b, paramOctreeVariableSet.min.c, paramOctreeVariableSet.max.a, paramOctreeVariableSet.max.b, paramOctreeVariableSet.max.c, this.data[paramInt1]);
/* 435:    */    }
/* 436:    */    
/* 438:438 */    return paramIntersectionCallback;
/* 439:    */  }
/* 440:    */  
/* 444:    */  private void transformAabb(OctreeVariableSet paramOctreeVariableSet, int paramInt, Vector3f paramVector3f1, Vector3f paramVector3f2, Matrix3f paramMatrix3f, float paramFloat, Transform paramTransform, Vector3f paramVector3f3, Vector3f paramVector3f4)
/* 445:    */  {
/* 446:446 */    (paramInt = paramOctreeVariableSet.localCenter).add(paramVector3f2, paramVector3f1);
/* 447:447 */    paramInt.scale(0.5F);
/* 448:    */    
/* 450:    */    Vector3f localVector3f;
/* 451:    */    
/* 453:453 */    (localVector3f = paramOctreeVariableSet.center).set(paramInt);
/* 454:454 */    paramTransform.transform(localVector3f);
/* 455:    */    
/* 456:456 */    paramInt = paramOctreeVariableSet.extend;
/* 457:457 */    (
/* 458:458 */      paramOctreeVariableSet = paramOctreeVariableSet.localHalfExtents).sub(paramVector3f2, paramVector3f1);
/* 459:459 */    paramOctreeVariableSet.scale(0.5F);
/* 460:    */    
/* 461:461 */    paramOctreeVariableSet.x += paramFloat;
/* 462:462 */    paramOctreeVariableSet.y += paramFloat;
/* 463:463 */    paramOctreeVariableSet.z += paramFloat;
/* 464:    */    
/* 465:465 */    paramInt.x = (paramMatrix3f.m00 * paramOctreeVariableSet.x + paramMatrix3f.m01 * paramOctreeVariableSet.y + paramMatrix3f.m02 * paramOctreeVariableSet.z);
/* 466:466 */    paramInt.y = (paramMatrix3f.m10 * paramOctreeVariableSet.x + paramMatrix3f.m11 * paramOctreeVariableSet.y + paramMatrix3f.m12 * paramOctreeVariableSet.z);
/* 467:467 */    paramInt.z = (paramMatrix3f.m20 * paramOctreeVariableSet.x + paramMatrix3f.m21 * paramOctreeVariableSet.y + paramMatrix3f.m22 * paramOctreeVariableSet.z);
/* 468:    */    
/* 469:469 */    paramVector3f3.sub(localVector3f, paramInt);
/* 470:470 */    paramVector3f4.add(localVector3f, paramInt);
/* 471:    */  }
/* 472:    */  
/* 473:473 */  public boolean rayAabb(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, float[] paramArrayOfFloat, Vector3f paramVector3f5) { Vector3f localVector3f1 = this.set.aabbHalfExtent;
/* 474:474 */    Vector3f localVector3f2 = this.set.aabbCenter;
/* 475:475 */    Vector3f localVector3f3 = this.set.source;
/* 476:476 */    Vector3f localVector3f4 = this.set.target;
/* 477:477 */    Vector3f localVector3f6 = this.set.r;
/* 478:478 */    Vector3f localVector3f7 = this.set.hitNormal;
/* 479:    */    
/* 480:480 */    localVector3f1.sub(paramVector3f4, paramVector3f3);
/* 481:481 */    localVector3f1.scale(0.5F);
/* 482:    */    
/* 483:483 */    localVector3f2.add(paramVector3f4, paramVector3f3);
/* 484:484 */    localVector3f2.scale(0.5F);
/* 485:    */    
/* 486:486 */    localVector3f3.sub(paramVector3f1, localVector3f2);
/* 487:487 */    localVector3f4.sub(paramVector3f2, localVector3f2);
/* 488:    */    
/* 489:489 */    paramVector3f1 = AabbUtil2.outcode(localVector3f3, localVector3f1);
/* 490:490 */    paramVector3f2 = AabbUtil2.outcode(localVector3f4, localVector3f1);
/* 491:491 */    if ((paramVector3f1 & paramVector3f2) == 0) {
/* 492:492 */      paramVector3f3 = 0.0F;
/* 493:493 */      paramVector3f4 = paramArrayOfFloat[0];
/* 494:494 */      localVector3f6.sub(localVector3f4, localVector3f3);
/* 495:    */      
/* 496:496 */      float f1 = 1.0F;
/* 497:497 */      localVector3f7.set(0.0F, 0.0F, 0.0F);
/* 498:498 */      Vector3f localVector3f5 = 1;
/* 499:    */      
/* 500:500 */      for (int j = 0; j < 2; j++) {
/* 501:501 */        for (int k = 0; k != 3; k++) { float f2;
/* 502:502 */          if ((paramVector3f1 & localVector3f5) != 0) {
/* 503:503 */            f2 = (-VectorUtil.getCoord(localVector3f3, k) - VectorUtil.getCoord(localVector3f1, k) * f1) / VectorUtil.getCoord(localVector3f6, k);
/* 504:504 */            if (paramVector3f3 <= f2) {
/* 505:505 */              paramVector3f3 = f2;
/* 506:506 */              localVector3f7.set(0.0F, 0.0F, 0.0F);
/* 507:507 */              VectorUtil.setCoord(localVector3f7, k, f1);
/* 508:    */            }
/* 509:    */          }
/* 510:510 */          else if ((paramVector3f2 & localVector3f5) != 0) {
/* 511:511 */            f2 = (-VectorUtil.getCoord(localVector3f3, k) - VectorUtil.getCoord(localVector3f1, k) * f1) / VectorUtil.getCoord(localVector3f6, k);
/* 512:    */            
/* 513:513 */            paramVector3f4 = Math.min(paramVector3f4, f2); }
/* 514:    */          int i;
/* 515:515 */          localVector3f5 <<= 1;
/* 516:    */        }
/* 517:517 */        f1 = -1.0F;
/* 518:    */      }
/* 519:519 */      if (paramVector3f3 <= paramVector3f4) {
/* 520:520 */        paramArrayOfFloat[0] = paramVector3f3;
/* 521:521 */        paramVector3f5.set(localVector3f7);
/* 522:522 */        return true;
/* 523:    */      }
/* 524:    */    }
/* 525:525 */    return false;
/* 526:    */  }
/* 527:    */  
/* 531:531 */  public OctreeVariableSet getSet() { return this.set; }
/* 532:    */  
/* 533:    */  public static OctreeVariableSet getSet(boolean paramBoolean) {
/* 534:534 */    if (paramBoolean) return serverSet; return clientSet;
/* 535:    */  }
/* 536:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.ArrayOctree
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */