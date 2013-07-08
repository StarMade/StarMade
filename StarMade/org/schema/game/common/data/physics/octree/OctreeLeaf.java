/*   1:    */package org.schema.game.common.data.physics.octree;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*   4:    */import com.bulletphysics.collision.narrowphase.SubsimplexConvexCast;
/*   5:    */import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
/*   6:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   7:    */import com.bulletphysics.linearmath.AabbUtil2;
/*   8:    */import com.bulletphysics.linearmath.Transform;
/*   9:    */import java.io.PrintStream;
/*  10:    */import javax.vecmath.Matrix3f;
/*  11:    */import javax.vecmath.Vector3f;
/*  12:    */import o;
/*  13:    */import org.lwjgl.opengl.GL11;
/*  14:    */import org.schema.game.common.data.physics.BoxShapeExt;
/*  15:    */import org.schema.game.common.data.world.Segment;
/*  16:    */import org.schema.game.common.data.world.SegmentData;
/*  17:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  18:    */import q;
/*  19:    */import xO;
/*  20:    */import zg;
/*  21:    */
/*  33:    */public class OctreeLeaf
/*  34:    */{
/*  35:    */  private final short id;
/*  36:    */  private short cnt;
/*  37:    */  private boolean hasHit;
/*  38:    */  private boolean onServer;
/*  39:    */  private final byte lvl;
/*  40:    */  public int index;
/*  41:    */  public int localIndex;
/*  42:    */  public int nodeIndex;
/*  43:    */  public static final int BLOCK_SIZE = 6;
/*  44:    */  
/*  45:    */  public OctreeLeaf(int paramInt1, byte paramByte, int paramInt2, boolean paramBoolean)
/*  46:    */  {
/*  47: 47 */    assert (paramInt2 >= 0);
/*  48: 48 */    assert (paramByte >= 0);
/*  49: 49 */    this.onServer = paramBoolean;
/*  50: 50 */    this.id = getSet().getId(paramByte, paramInt1, 0);
/*  51: 51 */    this.lvl = paramByte;
/*  52:    */  }
/*  53:    */  
/*  57:    */  public OctreeLeaf(o paramo1, o paramo2, int paramInt1, byte paramByte, int paramInt2, boolean paramBoolean)
/*  58:    */  {
/*  59: 59 */    assert (paramInt2 >= 0);
/*  60: 60 */    assert (paramByte >= 0);
/*  61:    */    
/*  62: 62 */    assert (paramInt1 < 32767);
/*  63:    */    
/*  65: 65 */    this.onServer = paramBoolean;
/*  66: 66 */    this.id = getSet().put(paramByte, paramInt1, 0, paramo1);
/*  67: 67 */    this.lvl = paramByte;
/*  68:    */    
/*  69: 69 */    getSet().put(paramByte, paramInt1, 1, paramo2);
/*  70:    */    
/*  71: 71 */    (
/*  72: 72 */      paramo2 = new o(paramo2)).c(paramo1);
/*  73:    */    
/*  74: 74 */    getSet().put(paramByte, paramInt1, 2, paramo2);
/*  75:    */    
/*  76: 76 */    (
/*  77: 77 */      paramo1 = getDim(new o())).a();
/*  78:    */    
/*  79: 79 */    getSet().put(paramByte, paramInt1, 3, paramo1);
/*  80:    */    
/*  81: 81 */    this.index = paramInt1;
/*  82: 82 */    this.localIndex = (paramInt1 % 8);
/*  83:    */    
/*  84: 84 */    if (paramBoolean) {
/*  85: 85 */      this.nodeIndex = ArrayOctree.getIndex(paramInt1, paramByte - 1);
/*  86: 86 */      (
/*  87: 87 */        paramo1 = new StringBuilder()).append(paramByte);
/*  88: 88 */      for (paramo2 = 0; paramo2 < paramByte; paramo2++) {
/*  89: 89 */        paramo1.append("    ");
/*  90:    */      }
/*  91: 91 */      paramo1.append("#### I " + paramInt1 + " tot " + OctreeVariableSet.nodes + " -> " + ArrayOctree.getIndex(paramInt1, paramByte));
/*  92: 92 */      System.err.println(paramo1);
/*  93:    */      
/*  94: 94 */      OctreeVariableSet.nodes += 1;
/*  95:    */    }
/*  96:    */  }
/*  97:    */  
/*  98:    */  private boolean between(byte paramByte1, byte paramByte2, byte paramByte3) {
/*  99: 99 */    return (paramByte1 >= getStartX()) && (paramByte2 >= getStartY()) && (paramByte3 >= getStartZ()) && (paramByte1 < getEndX()) && (paramByte2 < getEndY()) && (paramByte3 < getEndZ());
/* 100:    */  }
/* 101:    */  
/* 102:    */  public void delete(byte paramByte1, byte paramByte2, byte paramByte3, TreeCache paramTreeCache, int paramInt)
/* 103:    */  {
/* 104:104 */    if (this.cnt <= 0) {
/* 105:105 */      System.err.println("Exception: WARNING Octree Size < 0");
/* 106:    */    }
/* 107:107 */    this.cnt = ((short)Math.max(0, this.cnt - 1));
/* 108:    */  }
/* 109:    */  
/* 110:    */  public void deleteCached(TreeCache paramTreeCache, int paramInt)
/* 111:    */  {
/* 112:112 */    assert (this.cnt > 0);
/* 113:113 */    this.cnt = ((short)(this.cnt - 1));
/* 114:    */  }
/* 115:    */  
/* 116:    */  public void drawOctree(Vector3f paramVector3f, boolean paramBoolean) {
/* 117:117 */    if ((!paramBoolean) && (
/* 118:118 */      (!isHasHit()) || (isEmpty()))) {
/* 119:119 */      return;
/* 120:    */    }
/* 121:    */    
/* 123:123 */    getSet().tmpMin.set(getStartX(), getStartY(), getStartZ());
/* 124:124 */    getSet().tmpMin.scale(1.0F);
/* 125:125 */    getSet().tmpMin.x += -0.5F;
/* 126:126 */    getSet().tmpMin.y += -0.5F;
/* 127:127 */    getSet().tmpMin.z += -0.5F;
/* 128:    */    
/* 130:130 */    getSet().tmpMax.set(getEndX(), getEndY(), getEndZ());
/* 131:131 */    getSet().tmpMax.scale(1.0F);
/* 132:132 */    getSet().tmpMax.x += -0.5F;
/* 133:133 */    getSet().tmpMax.y += -0.5F;
/* 134:134 */    getSet().tmpMax.z += -0.5F;
/* 135:    */    
/* 136:136 */    getSet().tmpMax.sub(paramVector3f);
/* 137:137 */    getSet().tmpMin.sub(paramVector3f);
/* 138:138 */    paramVector3f = zg.a(getSet().tmpMin, getSet().tmpMax);
/* 139:    */    
/* 140:140 */    GL11.glPolygonMode(1032, 6913);
/* 141:141 */    GL11.glDisable(2896);
/* 142:142 */    GL11.glDisable(2884);
/* 143:143 */    GL11.glEnable(2903);
/* 144:144 */    GL11.glDisable(32879);
/* 145:145 */    GL11.glDisable(3553);
/* 146:146 */    GL11.glDisable(3552);
/* 147:147 */    GL11.glEnable(3042);
/* 148:148 */    GlUtil.a(1.0F, 1.0F, 1.0F, 0.2F);
/* 149:149 */    float f = 0.0F;
/* 150:150 */    if (!isEmpty()) {
/* 151:151 */      f = 1.0F;
/* 152:    */    }
/* 153:153 */    if (!paramBoolean)
/* 154:    */    {
/* 155:155 */      GlUtil.a(1.0F, 0.0F, f, 0.9F);
/* 158:    */    }
/* 159:    */    else
/* 160:    */    {
/* 162:162 */      GlUtil.a(0.0F, 1.0F, 0.0F, 1.0F);
/* 163:    */    }
/* 164:    */    
/* 166:166 */    GL11.glBegin(7);
/* 167:167 */    for (paramBoolean = false; paramBoolean < paramVector3f.length; paramBoolean++) {
/* 168:168 */      for (int i = 0; i < paramVector3f[paramBoolean].length; i++) {
/* 169:169 */        GL11.glVertex3f(paramVector3f[paramBoolean][i].x, paramVector3f[paramBoolean][i].y, paramVector3f[paramBoolean][i].z);
/* 170:    */      }
/* 171:    */    }
/* 172:172 */    GL11.glEnd();
/* 173:    */    
/* 174:174 */    GL11.glEnable(2896);
/* 175:175 */    GL11.glDisable(2903);
/* 176:176 */    GL11.glEnable(2884);
/* 177:177 */    GL11.glDisable(3042);
/* 178:178 */    GL11.glPolygonMode(1032, 6914);
/* 179:    */  }
/* 180:    */  
/* 182:    */  protected IntersectionCallback doIntersecting(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2, boolean paramBoolean)
/* 183:    */  {
/* 184:184 */    paramIntersectionCallback.leafCalcs += 1;
/* 185:    */    
/* 186:186 */    getStart(paramOctreeVariableSet, paramOctreeVariableSet.min);
/* 187:187 */    getEnd(paramOctreeVariableSet, paramOctreeVariableSet.max);
/* 188:    */    
/* 189:189 */    paramFloat2 = paramOctreeVariableSet.tmpMin;
/* 190:190 */    paramBoolean = paramOctreeVariableSet.tmpMax;
/* 191:191 */    Vector3f localVector3f1 = paramOctreeVariableSet.tmpMinOut;
/* 192:192 */    Vector3f localVector3f2 = paramOctreeVariableSet.tmpMaxOut;
/* 193:    */    
/* 195:195 */    float f1 = paramSegment.a.a - 0.5F;
/* 196:196 */    float f2 = paramSegment.a.b - 0.5F;
/* 197:197 */    paramSegment = paramSegment.a.c - 0.5F;
/* 198:    */    
/* 199:199 */    paramFloat2.x = (paramOctreeVariableSet.min.a + f1);
/* 200:200 */    paramFloat2.y = (paramOctreeVariableSet.min.b + f2);
/* 201:201 */    paramFloat2.z = (paramOctreeVariableSet.min.c + paramSegment);
/* 202:    */    
/* 203:203 */    paramBoolean.x = (paramOctreeVariableSet.max.a + f1);
/* 204:204 */    paramBoolean.y = (paramOctreeVariableSet.max.b + f2);
/* 205:205 */    paramBoolean.z = (paramOctreeVariableSet.max.c + paramSegment);
/* 206:    */    
/* 208:208 */    transformAabb(paramOctreeVariableSet, paramFloat2, paramBoolean, paramMatrix3f, paramFloat1, paramTransform, localVector3f1, localVector3f2);
/* 209:    */    
/* 210:210 */    paramOctreeVariableSet = AabbUtil2.testAabbAgainstAabb2(localVector3f1, localVector3f2, paramVector3f1, paramVector3f2);
/* 211:211 */    setHasHit(paramOctreeVariableSet);
/* 212:212 */    return paramIntersectionCallback;
/* 213:    */  }
/* 214:    */  
/* 218:    */  public IntersectionCallback findIntersecting(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2, boolean paramBoolean)
/* 219:    */  {
/* 220:220 */    paramIntersectionCallback = doIntersecting(paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2, paramBoolean);
/* 221:    */    
/* 224:224 */    if (this.hasHit)
/* 225:    */    {
/* 226:226 */      paramIntersectionCallback.addHit(paramOctreeVariableSet.tmpMinOut, paramOctreeVariableSet.tmpMaxOut, paramOctreeVariableSet.min.a, paramOctreeVariableSet.min.b, paramOctreeVariableSet.min.c, paramOctreeVariableSet.max.a, paramOctreeVariableSet.max.b, paramOctreeVariableSet.max.c, 255);
/* 227:    */    }
/* 228:    */    
/* 229:229 */    return paramIntersectionCallback;
/* 230:    */  }
/* 231:    */  
/* 233:    */  private void transformAabb(OctreeVariableSet paramOctreeVariableSet, Vector3f paramVector3f1, Vector3f paramVector3f2, Matrix3f paramMatrix3f, float paramFloat, Transform paramTransform, Vector3f paramVector3f3, Vector3f paramVector3f4)
/* 234:    */  {
/* 235:235 */    (paramVector3f2 = paramOctreeVariableSet.localCenter).add(paramVector3f1, OctreeVariableSet.localCentersAdd[this.lvl]);
/* 236:    */    
/* 238:238 */    (
/* 239:239 */      paramVector3f1 = paramOctreeVariableSet.center).set(paramVector3f2);
/* 240:240 */    paramTransform.transform(paramVector3f1);
/* 241:    */    
/* 242:242 */    paramOctreeVariableSet = paramOctreeVariableSet.extend;
/* 243:    */    
/* 246:246 */    paramVector3f2 = OctreeVariableSet.localHalfExtends[this.lvl];
/* 247:    */    
/* 252:252 */    paramOctreeVariableSet.x = (paramMatrix3f.m00 * paramVector3f2.x + paramMatrix3f.m01 * paramVector3f2.y + paramMatrix3f.m02 * paramVector3f2.z);
/* 253:    */    
/* 257:257 */    paramOctreeVariableSet.y = (paramMatrix3f.m10 * paramVector3f2.x + paramMatrix3f.m11 * paramVector3f2.y + paramMatrix3f.m12 * paramVector3f2.z);
/* 258:    */    
/* 262:262 */    paramOctreeVariableSet.z = (paramMatrix3f.m20 * paramVector3f2.x + paramMatrix3f.m21 * paramVector3f2.y + paramMatrix3f.m22 * paramVector3f2.z);
/* 263:    */    
/* 264:264 */    paramVector3f3.sub(paramVector3f1, paramOctreeVariableSet);
/* 265:265 */    paramVector3f4.add(paramVector3f1, paramOctreeVariableSet);
/* 266:    */  }
/* 267:    */  
/* 270:    */  public IntersectionCallback findIntersectingCast(IntersectionCallback paramIntersectionCallback, Transform paramTransform1, BoxShapeExt paramBoxShapeExt, ConvexShape paramConvexShape, float paramFloat1, Segment paramSegment, Transform paramTransform2, Transform paramTransform3, float paramFloat2)
/* 271:    */  {
/* 272:272 */    getSet().tmpMin.set(getStartX(), getStartY(), getStartZ());
/* 273:273 */    getSet().tmpMin.x += paramSegment.a.a - 0.5F;
/* 274:274 */    getSet().tmpMin.y += paramSegment.a.b - 0.5F;
/* 275:275 */    getSet().tmpMin.z += paramSegment.a.c - 0.5F;
/* 276:    */    
/* 277:277 */    getSet().tmpMax.set(getEndX(), getEndY(), getEndZ());
/* 278:278 */    getSet().tmpMax.x += paramSegment.a.a - 0.5F;
/* 279:279 */    getSet().tmpMax.y += paramSegment.a.b - 0.5F;
/* 280:280 */    getSet().tmpMax.z += paramSegment.a.c - 0.5F;
/* 281:    */    
/* 283:283 */    paramBoxShapeExt.setDimFromBB(getSet().tmpMin, getSet().tmpMax);
/* 284:    */    
/* 285:285 */    paramBoxShapeExt = new SubsimplexConvexCast(paramConvexShape, paramBoxShapeExt, new VoronoiSimplexSolver());
/* 286:    */    
/* 287:287 */    (paramConvexShape = new ConvexCast.CastResult()).allowedPenetration = 0.03F;
/* 288:288 */    paramConvexShape.fraction = 1.0F;
/* 289:    */    
/* 290:290 */    paramTransform1 = paramBoxShapeExt.calcTimeOfImpact(paramTransform2, paramTransform3, paramTransform1, paramTransform1, paramConvexShape);
/* 291:291 */    setHasHit(paramTransform1);
/* 292:    */    
/* 293:293 */    if (isHasHit()) {
/* 294:294 */      System.err.println("NODE hit registered (" + paramConvexShape.hitPoint + " in: " + getSet().tmpMin + " - " + getSet().tmpMax + ", dim: " + getDim(new o()) + ": " + getClass());
/* 295:295 */      if (isLeaf())
/* 296:    */      {
/* 297:297 */        if (!isEmpty()) {
/* 298:298 */          paramIntersectionCallback.addHit(getSet().tmpMinOut, getSet().tmpMaxOut, getStartX(), getStartY(), getStartZ(), getEndX(), getEndY(), getEndZ(), 255);
/* 299:    */        }
/* 300:    */      }
/* 301:    */    }
/* 302:    */    
/* 303:303 */    return paramIntersectionCallback;
/* 304:    */  }
/* 305:    */  
/* 308:    */  public IntersectionCallback findIntersectingRay(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
/* 309:    */  {
/* 310:310 */    paramIntersectionCallback.leafCalcs += 1;
/* 311:    */    
/* 316:316 */    paramFloat2 = paramOctreeVariableSet.tmpMin;
/* 317:317 */    Vector3f localVector3f1 = paramOctreeVariableSet.tmpMax;
/* 318:318 */    Vector3f localVector3f2 = paramOctreeVariableSet.tmpMinOut;
/* 319:319 */    Vector3f localVector3f3 = paramOctreeVariableSet.tmpMaxOut;
/* 320:    */    
/* 321:321 */    getStart(paramOctreeVariableSet, paramOctreeVariableSet.min);
/* 322:322 */    getEnd(paramOctreeVariableSet, paramOctreeVariableSet.max);
/* 323:    */    
/* 324:324 */    float f1 = paramSegment.a.a - 0.5F;
/* 325:325 */    float f2 = paramSegment.a.b - 0.5F;
/* 326:326 */    float f3 = paramSegment.a.c - 0.5F;
/* 327:    */    
/* 328:328 */    paramFloat2.x = (paramOctreeVariableSet.min.a + f1);
/* 329:329 */    paramFloat2.y = (paramOctreeVariableSet.min.b + f2);
/* 330:330 */    paramFloat2.z = (paramOctreeVariableSet.min.c + f3);
/* 331:    */    
/* 332:332 */    localVector3f1.x = (paramOctreeVariableSet.max.a + f1);
/* 333:333 */    localVector3f1.y = (paramOctreeVariableSet.max.b + f2);
/* 334:334 */    localVector3f1.z = (paramOctreeVariableSet.max.c + f3);
/* 335:    */    
/* 358:358 */    if ((!$assertionsDisabled) && ((paramFloat2.x > localVector3f1.x) || (paramFloat2.y > localVector3f1.y) || (paramFloat2.z > localVector3f1.z))) { throw new AssertionError("[WARNING] BOUNDING BOX IS FAULTY: " + paramSegment.a + " in " + paramSegment.a().getSegmentController() + ": " + paramFloat2 + " - " + localVector3f1 + "; star/end " + getStart(new o()) + " - " + getEnd(new o()) + "------ " + (paramOctreeVariableSet.tmpMin.x > paramOctreeVariableSet.tmpMax.x) + "," + (paramOctreeVariableSet.tmpMin.y > paramOctreeVariableSet.tmpMax.y) + "," + (paramOctreeVariableSet.tmpMin.z > paramOctreeVariableSet.tmpMax.z));
/* 359:    */    }
/* 360:    */    
/* 366:366 */    transformAabb(paramOctreeVariableSet, paramFloat2, localVector3f1, paramMatrix3f, paramFloat1, paramTransform, localVector3f2, localVector3f3);
/* 367:    */    
/* 389:389 */    paramOctreeVariableSet.param[0] = 1.0F;
/* 390:390 */    paramOctreeVariableSet.normal.x = 0.0F;
/* 391:391 */    paramOctreeVariableSet.normal.y = 0.0F;
/* 392:392 */    paramOctreeVariableSet.normal.z = 0.0F;
/* 393:    */    
/* 394:394 */    paramTransform = AabbUtil2.rayAabb(paramVector3f1, paramVector3f2, localVector3f2, localVector3f3, paramOctreeVariableSet.param, paramOctreeVariableSet.normal);
/* 395:    */    
/* 397:397 */    paramMatrix3f = 0;
/* 398:398 */    if (paramTransform == 0) {
/* 399:399 */      paramMatrix3f = (xO.a(paramVector3f2, localVector3f2, localVector3f3)) || (xO.a(paramVector3f1, localVector3f2, localVector3f3)) ? 1 : 0;
/* 400:    */    }
/* 401:    */    
/* 403:403 */    setHasHit((paramTransform != 0) || (paramMatrix3f != 0));
/* 404:    */    
/* 406:406 */    if ((isLeaf()) && (isHasHit())) {
/* 407:407 */      paramIntersectionCallback.addHit(localVector3f2, localVector3f3, paramOctreeVariableSet.min.a, paramOctreeVariableSet.min.b, paramOctreeVariableSet.min.c, paramOctreeVariableSet.max.a, paramOctreeVariableSet.max.b, paramOctreeVariableSet.max.c, 255);
/* 408:    */    }
/* 409:    */    
/* 411:411 */    return paramIntersectionCallback;
/* 412:    */  }
/* 413:    */  
/* 414:    */  public o getDim(o paramo) {
/* 415:415 */    getSet().get((short)(getId() + 2), paramo);
/* 416:416 */    return paramo;
/* 417:    */  }
/* 418:    */  
/* 419:    */  public o getEnd(o paramo) {
/* 420:420 */    getSet().get((short)(getId() + 1), paramo);
/* 421:421 */    return paramo;
/* 422:    */  }
/* 423:    */  
/* 424:424 */  public Vector3f getEnd(Vector3f paramVector3f) { getSet().get((short)(getId() + 1), paramVector3f);
/* 425:425 */    return paramVector3f;
/* 426:    */  }
/* 427:    */  
/* 428:428 */  public Vector3f getEnd(OctreeVariableSet paramOctreeVariableSet, Vector3f paramVector3f) { paramOctreeVariableSet.get((short)(getId() + 1), paramVector3f);
/* 429:429 */    return paramVector3f;
/* 430:    */  }
/* 431:    */  
/* 432:432 */  public o getEnd(OctreeVariableSet paramOctreeVariableSet, o paramo) { paramOctreeVariableSet.get((short)(getId() + 1), paramo);
/* 433:433 */    return paramo;
/* 434:    */  }
/* 435:    */  
/* 436:436 */  public o getHalfDim(o paramo) { getSet().get((short)(getId() + 3), paramo);
/* 437:437 */    return paramo;
/* 438:    */  }
/* 439:    */  
/* 440:    */  public byte getDimX() {
/* 441:441 */    return getSet().getX((short)(getId() + 2));
/* 442:    */  }
/* 443:    */  
/* 444:    */  public byte getEndX() {
/* 445:445 */    return getSet().getX((short)(getId() + 1));
/* 446:    */  }
/* 447:    */  
/* 448:448 */  public byte getHalfDimX() { return getSet().getX((short)(getId() + 3)); }
/* 449:    */  
/* 453:    */  public byte getDimY()
/* 454:    */  {
/* 455:455 */    return getSet().getY((short)(getId() + 2));
/* 456:    */  }
/* 457:    */  
/* 458:    */  public byte getEndY() {
/* 459:459 */    return getSet().getY((short)(getId() + 1));
/* 460:    */  }
/* 461:    */  
/* 462:462 */  public byte getHalfDimY() { return getSet().getY((short)(getId() + 3)); }
/* 463:    */  
/* 466:    */  public byte getDimZ()
/* 467:    */  {
/* 468:468 */    return getSet().getZ((short)(getId() + 2));
/* 469:    */  }
/* 470:    */  
/* 471:    */  public byte getEndZ() {
/* 472:472 */    return getSet().getZ((short)(getId() + 1));
/* 473:    */  }
/* 474:    */  
/* 475:475 */  public byte getHalfDimZ() { return getSet().getZ((short)(getId() + 3)); }
/* 476:    */  
/* 477:    */  public short getId() {
/* 478:478 */    return this.id;
/* 479:    */  }
/* 480:    */  
/* 481:    */  public int getMaxLevel()
/* 482:    */  {
/* 483:483 */    return getSet().maxLevel;
/* 484:    */  }
/* 485:    */  
/* 486:    */  public OctreeVariableSet getSet() {
/* 487:487 */    return Octree.get(onServer());
/* 488:    */  }
/* 489:    */  
/* 490:490 */  public byte getStartX() { return getSet().getX((short)getId()); }
/* 491:    */  
/* 492:    */  public byte getStartY() {
/* 493:493 */    return getSet().getY((short)getId());
/* 494:    */  }
/* 495:    */  
/* 496:496 */  public byte getStartZ() { return getSet().getZ((short)getId()); }
/* 497:    */  
/* 498:    */  public o getStart(o paramo) {
/* 499:499 */    getSet().get((short)getId(), paramo);
/* 500:500 */    return paramo;
/* 501:    */  }
/* 502:    */  
/* 503:503 */  public Vector3f getStart(Vector3f paramVector3f) { getSet().get((short)getId(), paramVector3f);
/* 504:504 */    return paramVector3f;
/* 505:    */  }
/* 506:    */  
/* 507:507 */  public o getStart(OctreeVariableSet paramOctreeVariableSet, o paramo) { paramOctreeVariableSet.get((short)getId(), paramo);
/* 508:508 */    return paramo;
/* 509:    */  }
/* 510:    */  
/* 511:511 */  public Vector3f getStart(OctreeVariableSet paramOctreeVariableSet, Vector3f paramVector3f) { paramOctreeVariableSet.get((short)getId(), paramVector3f);
/* 512:512 */    return paramVector3f;
/* 513:    */  }
/* 514:    */  
/* 515:515 */  public void insert(byte paramByte1, byte paramByte2, byte paramByte3, TreeCache paramTreeCache, int paramInt) { assert (between(paramByte1, paramByte2, paramByte3)) : ("not in range: " + paramByte1 + ", " + paramByte2 + ", " + paramByte3 + ": [" + getStartX() + " - " + getEndX() + "], half: " + getHalfDimX());
/* 516:    */    
/* 517:517 */    this.cnt = ((short)(this.cnt + 1));
/* 518:    */  }
/* 519:    */  
/* 520:    */  public void insertCached(TreeCache paramTreeCache, int paramInt)
/* 521:    */  {
/* 522:522 */    this.cnt = ((short)(this.cnt + 1));
/* 523:    */  }
/* 524:    */  
/* 525:525 */  public boolean isEmpty() { return this.cnt == 0; }
/* 526:    */  
/* 530:    */  public boolean isHasHit()
/* 531:    */  {
/* 532:532 */    return this.hasHit;
/* 533:    */  }
/* 534:    */  
/* 535:535 */  protected boolean isLeaf() { return true; }
/* 536:    */  
/* 537:    */  protected boolean onServer()
/* 538:    */  {
/* 539:539 */    return this.onServer;
/* 540:    */  }
/* 541:    */  
/* 542:    */  public void reset() {
/* 543:543 */    this.cnt = 0;
/* 544:    */  }
/* 545:    */  
/* 553:    */  public void setHasHit(boolean paramBoolean)
/* 554:    */  {
/* 555:555 */    this.hasHit = paramBoolean;
/* 556:    */  }
/* 557:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.OctreeLeaf
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */