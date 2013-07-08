/*   1:    */package org.schema.schine.graphicsengine.camera;
/*   2:    */
/*   3:    */import com.bulletphysics.linearmath.Transform;
/*   4:    */import com.bulletphysics.util.ObjectPool;
/*   5:    */import java.nio.FloatBuffer;
/*   6:    */import javax.vecmath.Matrix3f;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */import org.lwjgl.BufferUtils;
/*   9:    */import org.lwjgl.input.Keyboard;
/*  10:    */import org.lwjgl.util.vector.Matrix4f;
/*  11:    */import org.schema.common.FastMath;
/*  12:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  13:    */import org.schema.schine.network.client.ClientStateInterface;
/*  14:    */import wU;
/*  15:    */import wV;
/*  16:    */import wY;
/*  17:    */import wZ;
/*  18:    */import xX;
/*  19:    */import xa;
/*  20:    */import xe;
/*  21:    */import xm;
/*  22:    */import xq;
/*  23:    */import xu;
/*  24:    */import yh;
/*  25:    */
/*  26:    */public class Camera
/*  27:    */  implements xX, yh
/*  28:    */{
/*  29:    */  public static void main(String[] paramArrayOfString)
/*  30:    */  {
/*  31: 31 */    paramArrayOfString = new float[] { -0.3499902F, -5.811467E-007F, 0.9367533F, 0.0F, 0.5169892F, 0.8339127F, 0.1931583F, 0.0F, -0.7811701F, 0.5518945F, -0.2918607F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F };
/*  32:    */    
/*  34:    */    Transform localTransform;
/*  35:    */    
/*  36: 36 */    (localTransform = new Transform()).setFromOpenGLMatrix(paramArrayOfString);
/*  37:    */    
/*  38: 38 */    (
/*  39: 39 */      paramArrayOfString = new Vector3f(188.99323F, 77.021645F, 294.43661F)).negate();
/*  40: 40 */    Vector3f localVector3f1 = new Vector3f(paramArrayOfString);
/*  41: 41 */    Vector3f localVector3f2 = new Vector3f();
/*  42: 42 */    localTransform.transform(localVector3f1);
/*  43:    */    do
/*  44:    */    {
/*  45: 45 */      localVector3f2.set(paramArrayOfString);
/*  46: 46 */      localTransform.basis.transform(localVector3f2);
/*  47: 47 */    } while (localVector3f2.equals(localVector3f1));
/*  48: 48 */    throw new NullPointerException(localVector3f2 + "; " + localVector3f1);
/*  49:    */  }
/*  50:    */  
/*  55: 55 */  private float jdField_d_of_type_Float = 0.0007F;
/*  56:    */  
/*  60:    */  private float[][] jdField_a_of_type_Array2dOfFloat;
/*  61:    */  
/*  65:    */  protected wV a;
/*  66:    */  
/*  70: 70 */  private float[] jdField_a_of_type_ArrayOfFloat = new float[16];
/*  71:    */  private float[] jdField_b_of_type_ArrayOfFloat;
/*  72:    */  private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/*  73: 73 */  private static FloatBuffer jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*  74:    */  
/*  75: 75 */  private static FloatBuffer jdField_b_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*  76:    */  
/*  78: 78 */  private int jdField_a_of_type_Int = 0;
/*  79:    */  private xa jdField_a_of_type_Xa;
/*  80:    */  public wZ a;
/*  81:    */  
/*  82:    */  public Camera(xa paramxa)
/*  83:    */  {
/*  84: 84 */    new Vector3f();
/*  85:    */    
/*  86: 86 */    new Vector3f();
/*  87:    */    
/*  88: 88 */    new Vector3f();
/*  89: 89 */    new Vector3f();
/*  90: 90 */    this.jdField_b_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*  91: 91 */    this.jdField_c_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*  92:    */    
/*  93: 93 */    this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  94: 94 */    this.jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  95: 95 */    this.jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/*  96: 96 */    this.jdField_d_of_type_JavaxVecmathVector3f = new Vector3f();
/*  97:    */    
/*  98: 98 */    this.jdField_e_of_type_JavaxVecmathVector3f = new Vector3f();
/*  99: 99 */    this.f = new Vector3f();
/* 100:100 */    this.g = new Vector3f();
/* 101:101 */    this.jdField_a_of_type_Boolean = true;
/* 102:    */    
/* 105:105 */    this.jdField_a_of_type_JavaLangThreadLocal = new wU();
/* 106:    */    
/* 111:111 */    this.h = new Vector3f();
/* 112:112 */    this.jdField_b_of_type_Float = 1.0F;
/* 113:113 */    this.jdField_c_of_type_Float = 1.0F;
/* 114:    */    
/* 118:118 */    this.i = new Vector3f();
/* 119:119 */    this.j = new Vector3f();
/* 120:120 */    this.k = new Vector3f();
/* 121:    */    
/* 132:132 */    xa localxa1 = paramxa;paramxa = this;this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();paramxa.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setIdentity();paramxa.jdField_b_of_type_ArrayOfFloat = new float[16];paramxa.a();paramxa.jdField_a_of_type_WZ = new wY(paramxa);paramxa.jdField_a_of_type_Array2dOfFloat = new float[6][4];xa localxa2 = localxa1;(localxa1 = paramxa).jdField_a_of_type_Xa = localxa2;localxa2.a = localxa1;localxa1.a();paramxa.jdField_a_of_type_WV = new wV();
/* 133:    */  }
/* 134:    */  
/* 139:    */  private Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform;
/* 140:    */  
/* 144:    */  private Transform jdField_c_of_type_ComBulletphysicsLinearmathTransform;
/* 145:    */  
/* 148:    */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f;
/* 149:    */  
/* 152:    */  public final Vector3f a(Vector3f paramVector3f)
/* 153:    */  {
/* 154:154 */    return GlUtil.c(paramVector3f, getWorldTransform());
/* 155:    */  }
/* 156:    */  
/* 157:    */  public final Vector3f c() {
/* 158:158 */    return GlUtil.c(new Vector3f(), getWorldTransform());
/* 159:    */  }
/* 160:    */  
/* 161:    */  public final Vector3f d() {
/* 162:162 */    return GlUtil.d(new Vector3f(), getWorldTransform());
/* 163:    */  }
/* 164:    */  
/* 165:165 */  public final Vector3f b(Vector3f paramVector3f) { return GlUtil.d(paramVector3f, getWorldTransform()); }
/* 166:    */  
/* 170:    */  private Vector3f jdField_b_of_type_JavaxVecmathVector3f;
/* 171:    */  
/* 174:    */  private Vector3f jdField_c_of_type_JavaxVecmathVector3f;
/* 175:    */  
/* 178:    */  private Vector3f jdField_d_of_type_JavaxVecmathVector3f;
/* 179:    */  
/* 182:    */  private Vector3f jdField_e_of_type_JavaxVecmathVector3f;
/* 183:    */  
/* 186:    */  private Vector3f f;
/* 187:    */  
/* 189:    */  public final float a()
/* 190:    */  {
/* 191:191 */    return this.jdField_c_of_type_Float * ((Float)xu.U.a()).floatValue();
/* 192:    */  }
/* 193:    */  
/* 195:    */  public final float b()
/* 196:    */  {
/* 197:197 */    return this.jdField_b_of_type_Float * ((Float)xu.U.a()).floatValue();
/* 198:    */  }
/* 199:    */  
/* 205:    */  public final Vector3f c(Vector3f paramVector3f)
/* 206:    */  {
/* 207:207 */    Vector3f localVector3f1 = (Vector3f)a().get();
/* 208:208 */    Vector3f localVector3f2 = (Vector3f)a().get();
/* 209:    */    
/* 210:    */    try
/* 211:    */    {
/* 212:212 */      localVector3f1.set(getWorldTransform().origin);
/* 213:213 */      localVector3f2.set(c());
/* 214:214 */      localVector3f2.negate();
/* 215:215 */      localVector3f2.scale(this.jdField_a_of_type_Float);
/* 216:216 */      localVector3f1.add(localVector3f2);
/* 217:217 */      paramVector3f.set(localVector3f1);
/* 218:218 */      return localVector3f1;
/* 219:    */    } finally {
/* 220:220 */      a().release(localVector3f1);
/* 221:221 */      a().release(localVector3f2);
/* 222:    */    }
/* 223:    */  }
/* 224:    */  
/* 228:    */  private ObjectPool a()
/* 229:    */  {
/* 230:230 */    return (ObjectPool)this.jdField_a_of_type_JavaLangThreadLocal.get();
/* 231:    */  }
/* 232:    */  
/* 233:    */  public final Vector3f a()
/* 234:    */  {
/* 235:235 */    return this.h;
/* 236:    */  }
/* 237:    */  
/* 242:    */  public final Vector3f e()
/* 243:    */  {
/* 244:244 */    return GlUtil.e(new Vector3f(), getWorldTransform());
/* 245:    */  }
/* 246:    */  
/* 249:    */  public final Vector3f f()
/* 250:    */  {
/* 251:251 */    return GlUtil.f(new Vector3f(), getWorldTransform());
/* 252:    */  }
/* 253:    */  
/* 254:254 */  public final Vector3f d(Vector3f paramVector3f) { return GlUtil.f(paramVector3f, getWorldTransform()); }
/* 255:    */  
/* 262:    */  public final xa a()
/* 263:    */  {
/* 264:264 */    if ((!jdField_b_of_type_Boolean) && (this.jdField_a_of_type_Xa == null)) throw new AssertionError();
/* 265:265 */    return this.jdField_a_of_type_Xa;
/* 266:    */  }
/* 267:    */  
/* 270:    */  public Transform getWorldTransform()
/* 271:    */  {
/* 272:272 */    return this.jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/* 273:    */  }
/* 274:    */  
/* 279:    */  private Vector3f g;
/* 280:    */  
/* 284:    */  private boolean jdField_a_of_type_Boolean;
/* 285:    */  
/* 289:    */  public float a;
/* 290:    */  
/* 294:    */  private float jdField_e_of_type_Float;
/* 295:    */  
/* 299:    */  private ThreadLocal jdField_a_of_type_JavaLangThreadLocal;
/* 300:    */  
/* 304:    */  private final Vector3f h;
/* 305:    */  
/* 309:    */  public float b;
/* 310:    */  
/* 314:    */  public float c;
/* 315:    */  
/* 319:    */  private final Vector3f i;
/* 320:    */  
/* 323:    */  private final Vector3f j;
/* 324:    */  
/* 327:    */  private final Vector3f k;
/* 328:    */  
/* 331:    */  public final boolean a(Vector3f paramVector3f1, Vector3f paramVector3f2)
/* 332:    */  {
/* 333:333 */    float f5 = paramVector3f2.z;float f4 = paramVector3f2.y;float f3 = paramVector3f2.x;float f2 = paramVector3f1.z;float f1 = paramVector3f1.y;paramVector3f2 = paramVector3f1.x;paramVector3f1 = this; for (int m = 0; m < 6; m++) if ((paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][0] * paramVector3f2 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][1] * f1 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][2] * f2 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F) && (paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][0] * f3 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][1] * f1 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][2] * f2 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F) && (paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][0] * paramVector3f2 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][1] * f4 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][2] * f2 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F) && (paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][0] * f3 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][1] * f4 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][2] * f2 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F) && (paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][0] * paramVector3f2 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][1] * f1 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][2] * f5 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F) && (paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][0] * f3 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][1] * f1 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][2] * f5 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F) && (paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][0] * paramVector3f2 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][1] * f4 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][2] * f5 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F) && (paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][0] * f3 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][1] * f4 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][2] * f5 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F)) return false; return true;
/* 334:    */  }
/* 335:    */  
/* 360:    */  public final boolean a(float paramFloat1, float paramFloat2, float paramFloat3)
/* 361:    */  {
/* 362:362 */    for (int m = 0; m < 6; m++)
/* 363:    */    {
/* 364:364 */      if (this.jdField_a_of_type_Array2dOfFloat[m][0] * paramFloat1 + this.jdField_a_of_type_Array2dOfFloat[m][1] * paramFloat2 + this.jdField_a_of_type_Array2dOfFloat[m][2] * paramFloat3 + this.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F) {
/* 365:365 */        return false;
/* 366:    */      }
/* 367:    */    }
/* 368:    */    
/* 369:369 */    return true;
/* 370:    */  }
/* 371:    */  
/* 372:372 */  public final boolean a(Vector3f paramVector3f) { return a(paramVector3f.x, paramVector3f.y, paramVector3f.z); }
/* 373:    */  
/* 375:    */  protected int a(int paramInt)
/* 376:    */  {
/* 377:377 */    return Math.max(0, paramInt);
/* 378:    */  }
/* 379:    */  
/* 386:    */  public final void c()
/* 387:    */  {
/* 388:388 */    c(this.h);
/* 389:    */    
/* 391:391 */    c().normalize();
/* 392:392 */    f().normalize();
/* 393:    */    
/* 394:394 */    this.jdField_d_of_type_JavaxVecmathVector3f.cross(f(), c());
/* 395:395 */    this.jdField_d_of_type_JavaxVecmathVector3f.normalize();
/* 396:396 */    this.jdField_d_of_type_JavaxVecmathVector3f.negate();
/* 397:    */    
/* 399:399 */    this.g.set(c());
/* 400:400 */    this.g.negate();
/* 401:    */    
/* 404:404 */    this.jdField_c_of_type_ComBulletphysicsLinearmathTransform.basis.setRow(0, this.jdField_d_of_type_JavaxVecmathVector3f);
/* 405:405 */    this.jdField_c_of_type_ComBulletphysicsLinearmathTransform.basis.setRow(1, f());
/* 406:406 */    this.jdField_c_of_type_ComBulletphysicsLinearmathTransform.basis.setRow(2, this.g);
/* 407:    */    
/* 408:408 */    this.f.set(this.h);
/* 409:    */    
/* 410:410 */    this.f.negate();
/* 411:    */    
/* 412:412 */    this.jdField_c_of_type_ComBulletphysicsLinearmathTransform.origin.set(this.f);
/* 413:    */    
/* 414:414 */    this.jdField_c_of_type_ComBulletphysicsLinearmathTransform.basis.transform(this.jdField_c_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 415:    */    
/* 416:416 */    GlUtil.a(this.jdField_c_of_type_ComBulletphysicsLinearmathTransform);
/* 417:    */    
/* 421:421 */    this.jdField_a_of_type_JavaxVecmathVector3f.set(this.f);
/* 422:422 */    this.jdField_b_of_type_JavaxVecmathVector3f.set(this.g);
/* 423:423 */    this.jdField_c_of_type_JavaxVecmathVector3f.set(f());
/* 424:424 */    this.jdField_e_of_type_JavaxVecmathVector3f.set(this.jdField_d_of_type_JavaxVecmathVector3f);
/* 425:425 */    this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_c_of_type_ComBulletphysicsLinearmathTransform);
/* 426:426 */    d();
/* 427:    */  }
/* 428:    */  
/* 436:    */  public void a()
/* 437:    */  {
/* 438:438 */    getWorldTransform().setIdentity();
/* 439:439 */    a(new Vector3f(0.0F, 0.0F, -1.0F));
/* 440:    */    
/* 441:441 */    b(new Vector3f(1.0F, 0.0F, 0.0F));
/* 442:442 */    c(new Vector3f(0.0F, 1.0F, 0.0F));
/* 443:    */    
/* 444:444 */    getWorldTransform().getOpenGLMatrix(this.jdField_b_of_type_ArrayOfFloat);
/* 445:    */    
/* 450:450 */    this.jdField_a_of_type_Float = this.jdField_e_of_type_Float;
/* 451:    */  }
/* 452:    */  
/* 473:    */  public final void a(float paramFloat)
/* 474:    */  {
/* 475:475 */    this.jdField_e_of_type_Float = paramFloat;
/* 476:476 */    this.jdField_a_of_type_Boolean = true;
/* 477:477 */    a();
/* 478:    */  }
/* 479:    */  
/* 486:    */  public final void a(Vector3f paramVector3f)
/* 487:    */  {
/* 488:488 */    GlUtil.a(paramVector3f, getWorldTransform());
/* 489:    */  }
/* 490:    */  
/* 526:    */  public final void b(Vector3f paramVector3f)
/* 527:    */  {
/* 528:528 */    GlUtil.c(paramVector3f, getWorldTransform());
/* 529:    */  }
/* 530:    */  
/* 531:    */  public final void c(Vector3f paramVector3f) {
/* 532:532 */    GlUtil.d(paramVector3f, getWorldTransform());
/* 533:    */  }
/* 534:    */  
/* 535:    */  public void a(xq paramxq) {
/* 536:    */    boolean bool;
/* 537:537 */    if (((bool = wV.a())) || (this.jdField_a_of_type_Boolean))
/* 538:    */    {
/* 539:539 */      e();
/* 540:    */      
/* 541:541 */      if ((!bool) && (this.jdField_a_of_type_Boolean))
/* 542:    */      {
/* 544:544 */        this.jdField_a_of_type_WZ.a(0.0F, 0.0F, 0.0F, a(), b(), 0.0F);
/* 547:    */      }
/* 548:    */      else
/* 549:    */      {
/* 552:552 */        this.jdField_a_of_type_WZ.a(this.jdField_a_of_type_WV.b / (xm.b() / 2), this.jdField_a_of_type_WV.c / (xm.a() / 2), 0.0F, a(), b(), 0.0F);
/* 553:    */      }
/* 554:    */    }
/* 555:    */    
/* 561:561 */    b(paramxq);
/* 562:    */    
/* 563:563 */    GlUtil.f(this.i, getWorldTransform());
/* 564:564 */    GlUtil.e(this.j, getWorldTransform());
/* 565:565 */    GlUtil.c(this.k, getWorldTransform());
/* 566:    */  }
/* 567:    */  
/* 568:568 */  public final void d() { jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 569:569 */    jdField_b_of_type_JavaNioFloatBuffer.rewind();
/* 570:570 */    xe.b.store(jdField_a_of_type_JavaNioFloatBuffer);
/* 571:571 */    xe.a.store(jdField_b_of_type_JavaNioFloatBuffer);
/* 572:    */    
/* 576:576 */    this.jdField_a_of_type_ArrayOfFloat[0] = (jdField_b_of_type_JavaNioFloatBuffer.get(0) * jdField_a_of_type_JavaNioFloatBuffer.get(0) + jdField_b_of_type_JavaNioFloatBuffer.get(1) * jdField_a_of_type_JavaNioFloatBuffer.get(4) + jdField_b_of_type_JavaNioFloatBuffer.get(2) * jdField_a_of_type_JavaNioFloatBuffer.get(8) + jdField_b_of_type_JavaNioFloatBuffer.get(3) * jdField_a_of_type_JavaNioFloatBuffer.get(12));
/* 577:577 */    this.jdField_a_of_type_ArrayOfFloat[1] = (jdField_b_of_type_JavaNioFloatBuffer.get(0) * jdField_a_of_type_JavaNioFloatBuffer.get(1) + jdField_b_of_type_JavaNioFloatBuffer.get(1) * jdField_a_of_type_JavaNioFloatBuffer.get(5) + jdField_b_of_type_JavaNioFloatBuffer.get(2) * jdField_a_of_type_JavaNioFloatBuffer.get(9) + jdField_b_of_type_JavaNioFloatBuffer.get(3) * jdField_a_of_type_JavaNioFloatBuffer.get(13));
/* 578:578 */    this.jdField_a_of_type_ArrayOfFloat[2] = (jdField_b_of_type_JavaNioFloatBuffer.get(0) * jdField_a_of_type_JavaNioFloatBuffer.get(2) + jdField_b_of_type_JavaNioFloatBuffer.get(1) * jdField_a_of_type_JavaNioFloatBuffer.get(6) + jdField_b_of_type_JavaNioFloatBuffer.get(2) * jdField_a_of_type_JavaNioFloatBuffer.get(10) + jdField_b_of_type_JavaNioFloatBuffer.get(3) * jdField_a_of_type_JavaNioFloatBuffer.get(14));
/* 579:579 */    this.jdField_a_of_type_ArrayOfFloat[3] = (jdField_b_of_type_JavaNioFloatBuffer.get(0) * jdField_a_of_type_JavaNioFloatBuffer.get(3) + jdField_b_of_type_JavaNioFloatBuffer.get(1) * jdField_a_of_type_JavaNioFloatBuffer.get(7) + jdField_b_of_type_JavaNioFloatBuffer.get(2) * jdField_a_of_type_JavaNioFloatBuffer.get(11) + jdField_b_of_type_JavaNioFloatBuffer.get(3) * jdField_a_of_type_JavaNioFloatBuffer.get(15));
/* 580:    */    
/* 581:581 */    this.jdField_a_of_type_ArrayOfFloat[4] = (jdField_b_of_type_JavaNioFloatBuffer.get(4) * jdField_a_of_type_JavaNioFloatBuffer.get(0) + jdField_b_of_type_JavaNioFloatBuffer.get(5) * jdField_a_of_type_JavaNioFloatBuffer.get(4) + jdField_b_of_type_JavaNioFloatBuffer.get(6) * jdField_a_of_type_JavaNioFloatBuffer.get(8) + jdField_b_of_type_JavaNioFloatBuffer.get(7) * jdField_a_of_type_JavaNioFloatBuffer.get(12));
/* 582:582 */    this.jdField_a_of_type_ArrayOfFloat[5] = (jdField_b_of_type_JavaNioFloatBuffer.get(4) * jdField_a_of_type_JavaNioFloatBuffer.get(1) + jdField_b_of_type_JavaNioFloatBuffer.get(5) * jdField_a_of_type_JavaNioFloatBuffer.get(5) + jdField_b_of_type_JavaNioFloatBuffer.get(6) * jdField_a_of_type_JavaNioFloatBuffer.get(9) + jdField_b_of_type_JavaNioFloatBuffer.get(7) * jdField_a_of_type_JavaNioFloatBuffer.get(13));
/* 583:583 */    this.jdField_a_of_type_ArrayOfFloat[6] = (jdField_b_of_type_JavaNioFloatBuffer.get(4) * jdField_a_of_type_JavaNioFloatBuffer.get(2) + jdField_b_of_type_JavaNioFloatBuffer.get(5) * jdField_a_of_type_JavaNioFloatBuffer.get(6) + jdField_b_of_type_JavaNioFloatBuffer.get(6) * jdField_a_of_type_JavaNioFloatBuffer.get(10) + jdField_b_of_type_JavaNioFloatBuffer.get(7) * jdField_a_of_type_JavaNioFloatBuffer.get(14));
/* 584:584 */    this.jdField_a_of_type_ArrayOfFloat[7] = (jdField_b_of_type_JavaNioFloatBuffer.get(4) * jdField_a_of_type_JavaNioFloatBuffer.get(3) + jdField_b_of_type_JavaNioFloatBuffer.get(5) * jdField_a_of_type_JavaNioFloatBuffer.get(7) + jdField_b_of_type_JavaNioFloatBuffer.get(6) * jdField_a_of_type_JavaNioFloatBuffer.get(11) + jdField_b_of_type_JavaNioFloatBuffer.get(7) * jdField_a_of_type_JavaNioFloatBuffer.get(15));
/* 585:    */    
/* 586:586 */    this.jdField_a_of_type_ArrayOfFloat[8] = (jdField_b_of_type_JavaNioFloatBuffer.get(8) * jdField_a_of_type_JavaNioFloatBuffer.get(0) + jdField_b_of_type_JavaNioFloatBuffer.get(9) * jdField_a_of_type_JavaNioFloatBuffer.get(4) + jdField_b_of_type_JavaNioFloatBuffer.get(10) * jdField_a_of_type_JavaNioFloatBuffer.get(8) + jdField_b_of_type_JavaNioFloatBuffer.get(11) * jdField_a_of_type_JavaNioFloatBuffer.get(12));
/* 587:587 */    this.jdField_a_of_type_ArrayOfFloat[9] = (jdField_b_of_type_JavaNioFloatBuffer.get(8) * jdField_a_of_type_JavaNioFloatBuffer.get(1) + jdField_b_of_type_JavaNioFloatBuffer.get(9) * jdField_a_of_type_JavaNioFloatBuffer.get(5) + jdField_b_of_type_JavaNioFloatBuffer.get(10) * jdField_a_of_type_JavaNioFloatBuffer.get(9) + jdField_b_of_type_JavaNioFloatBuffer.get(11) * jdField_a_of_type_JavaNioFloatBuffer.get(13));
/* 588:588 */    this.jdField_a_of_type_ArrayOfFloat[10] = (jdField_b_of_type_JavaNioFloatBuffer.get(8) * jdField_a_of_type_JavaNioFloatBuffer.get(2) + jdField_b_of_type_JavaNioFloatBuffer.get(9) * jdField_a_of_type_JavaNioFloatBuffer.get(6) + jdField_b_of_type_JavaNioFloatBuffer.get(10) * jdField_a_of_type_JavaNioFloatBuffer.get(10) + jdField_b_of_type_JavaNioFloatBuffer.get(11) * jdField_a_of_type_JavaNioFloatBuffer.get(14));
/* 589:589 */    this.jdField_a_of_type_ArrayOfFloat[11] = (jdField_b_of_type_JavaNioFloatBuffer.get(8) * jdField_a_of_type_JavaNioFloatBuffer.get(3) + jdField_b_of_type_JavaNioFloatBuffer.get(9) * jdField_a_of_type_JavaNioFloatBuffer.get(7) + jdField_b_of_type_JavaNioFloatBuffer.get(10) * jdField_a_of_type_JavaNioFloatBuffer.get(11) + jdField_b_of_type_JavaNioFloatBuffer.get(11) * jdField_a_of_type_JavaNioFloatBuffer.get(15));
/* 590:    */    
/* 591:591 */    this.jdField_a_of_type_ArrayOfFloat[12] = (jdField_b_of_type_JavaNioFloatBuffer.get(12) * jdField_a_of_type_JavaNioFloatBuffer.get(0) + jdField_b_of_type_JavaNioFloatBuffer.get(13) * jdField_a_of_type_JavaNioFloatBuffer.get(4) + jdField_b_of_type_JavaNioFloatBuffer.get(14) * jdField_a_of_type_JavaNioFloatBuffer.get(8) + jdField_b_of_type_JavaNioFloatBuffer.get(15) * jdField_a_of_type_JavaNioFloatBuffer.get(12));
/* 592:592 */    this.jdField_a_of_type_ArrayOfFloat[13] = (jdField_b_of_type_JavaNioFloatBuffer.get(12) * jdField_a_of_type_JavaNioFloatBuffer.get(1) + jdField_b_of_type_JavaNioFloatBuffer.get(13) * jdField_a_of_type_JavaNioFloatBuffer.get(5) + jdField_b_of_type_JavaNioFloatBuffer.get(14) * jdField_a_of_type_JavaNioFloatBuffer.get(9) + jdField_b_of_type_JavaNioFloatBuffer.get(15) * jdField_a_of_type_JavaNioFloatBuffer.get(13));
/* 593:593 */    this.jdField_a_of_type_ArrayOfFloat[14] = (jdField_b_of_type_JavaNioFloatBuffer.get(12) * jdField_a_of_type_JavaNioFloatBuffer.get(2) + jdField_b_of_type_JavaNioFloatBuffer.get(13) * jdField_a_of_type_JavaNioFloatBuffer.get(6) + jdField_b_of_type_JavaNioFloatBuffer.get(14) * jdField_a_of_type_JavaNioFloatBuffer.get(10) + jdField_b_of_type_JavaNioFloatBuffer.get(15) * jdField_a_of_type_JavaNioFloatBuffer.get(14));
/* 594:594 */    this.jdField_a_of_type_ArrayOfFloat[15] = (jdField_b_of_type_JavaNioFloatBuffer.get(12) * jdField_a_of_type_JavaNioFloatBuffer.get(3) + jdField_b_of_type_JavaNioFloatBuffer.get(13) * jdField_a_of_type_JavaNioFloatBuffer.get(7) + jdField_b_of_type_JavaNioFloatBuffer.get(14) * jdField_a_of_type_JavaNioFloatBuffer.get(11) + jdField_b_of_type_JavaNioFloatBuffer.get(15) * jdField_a_of_type_JavaNioFloatBuffer.get(15));
/* 595:    */    
/* 597:597 */    this.jdField_a_of_type_Array2dOfFloat[0][0] = (this.jdField_a_of_type_ArrayOfFloat[3] - this.jdField_a_of_type_ArrayOfFloat[0]);
/* 598:598 */    this.jdField_a_of_type_Array2dOfFloat[0][1] = (this.jdField_a_of_type_ArrayOfFloat[7] - this.jdField_a_of_type_ArrayOfFloat[4]);
/* 599:599 */    this.jdField_a_of_type_Array2dOfFloat[0][2] = (this.jdField_a_of_type_ArrayOfFloat[11] - this.jdField_a_of_type_ArrayOfFloat[8]);
/* 600:600 */    this.jdField_a_of_type_Array2dOfFloat[0][3] = (this.jdField_a_of_type_ArrayOfFloat[15] - this.jdField_a_of_type_ArrayOfFloat[12]);
/* 601:    */    
/* 603:603 */    float f1 = FastMath.l(this.jdField_a_of_type_Array2dOfFloat[0][0] * this.jdField_a_of_type_Array2dOfFloat[0][0] + this.jdField_a_of_type_Array2dOfFloat[0][1] * this.jdField_a_of_type_Array2dOfFloat[0][1] + this.jdField_a_of_type_Array2dOfFloat[0][2] * this.jdField_a_of_type_Array2dOfFloat[0][2]);
/* 604:604 */    this.jdField_a_of_type_Array2dOfFloat[0][0] /= f1;
/* 605:605 */    this.jdField_a_of_type_Array2dOfFloat[0][1] /= f1;
/* 606:606 */    this.jdField_a_of_type_Array2dOfFloat[0][2] /= f1;
/* 607:607 */    this.jdField_a_of_type_Array2dOfFloat[0][3] /= f1;
/* 608:    */    
/* 610:610 */    this.jdField_a_of_type_Array2dOfFloat[1][0] = (this.jdField_a_of_type_ArrayOfFloat[3] + this.jdField_a_of_type_ArrayOfFloat[0]);
/* 611:611 */    this.jdField_a_of_type_Array2dOfFloat[1][1] = (this.jdField_a_of_type_ArrayOfFloat[7] + this.jdField_a_of_type_ArrayOfFloat[4]);
/* 612:612 */    this.jdField_a_of_type_Array2dOfFloat[1][2] = (this.jdField_a_of_type_ArrayOfFloat[11] + this.jdField_a_of_type_ArrayOfFloat[8]);
/* 613:613 */    this.jdField_a_of_type_Array2dOfFloat[1][3] = (this.jdField_a_of_type_ArrayOfFloat[15] + this.jdField_a_of_type_ArrayOfFloat[12]);
/* 614:    */    
/* 616:616 */    f1 = FastMath.l(this.jdField_a_of_type_Array2dOfFloat[1][0] * this.jdField_a_of_type_Array2dOfFloat[1][0] + this.jdField_a_of_type_Array2dOfFloat[1][1] * this.jdField_a_of_type_Array2dOfFloat[1][1] + this.jdField_a_of_type_Array2dOfFloat[1][2] * this.jdField_a_of_type_Array2dOfFloat[1][2]);
/* 617:617 */    this.jdField_a_of_type_Array2dOfFloat[1][0] /= f1;
/* 618:618 */    this.jdField_a_of_type_Array2dOfFloat[1][1] /= f1;
/* 619:619 */    this.jdField_a_of_type_Array2dOfFloat[1][2] /= f1;
/* 620:620 */    this.jdField_a_of_type_Array2dOfFloat[1][3] /= f1;
/* 621:    */    
/* 623:623 */    this.jdField_a_of_type_Array2dOfFloat[2][0] = (this.jdField_a_of_type_ArrayOfFloat[3] + this.jdField_a_of_type_ArrayOfFloat[1]);
/* 624:624 */    this.jdField_a_of_type_Array2dOfFloat[2][1] = (this.jdField_a_of_type_ArrayOfFloat[7] + this.jdField_a_of_type_ArrayOfFloat[5]);
/* 625:625 */    this.jdField_a_of_type_Array2dOfFloat[2][2] = (this.jdField_a_of_type_ArrayOfFloat[11] + this.jdField_a_of_type_ArrayOfFloat[9]);
/* 626:626 */    this.jdField_a_of_type_Array2dOfFloat[2][3] = (this.jdField_a_of_type_ArrayOfFloat[15] + this.jdField_a_of_type_ArrayOfFloat[13]);
/* 627:    */    
/* 629:629 */    f1 = FastMath.l(this.jdField_a_of_type_Array2dOfFloat[2][0] * this.jdField_a_of_type_Array2dOfFloat[2][0] + this.jdField_a_of_type_Array2dOfFloat[2][1] * this.jdField_a_of_type_Array2dOfFloat[2][1] + this.jdField_a_of_type_Array2dOfFloat[2][2] * this.jdField_a_of_type_Array2dOfFloat[2][2]);
/* 630:630 */    this.jdField_a_of_type_Array2dOfFloat[2][0] /= f1;
/* 631:631 */    this.jdField_a_of_type_Array2dOfFloat[2][1] /= f1;
/* 632:632 */    this.jdField_a_of_type_Array2dOfFloat[2][2] /= f1;
/* 633:633 */    this.jdField_a_of_type_Array2dOfFloat[2][3] /= f1;
/* 634:    */    
/* 636:636 */    this.jdField_a_of_type_Array2dOfFloat[3][0] = (this.jdField_a_of_type_ArrayOfFloat[3] - this.jdField_a_of_type_ArrayOfFloat[1]);
/* 637:637 */    this.jdField_a_of_type_Array2dOfFloat[3][1] = (this.jdField_a_of_type_ArrayOfFloat[7] - this.jdField_a_of_type_ArrayOfFloat[5]);
/* 638:638 */    this.jdField_a_of_type_Array2dOfFloat[3][2] = (this.jdField_a_of_type_ArrayOfFloat[11] - this.jdField_a_of_type_ArrayOfFloat[9]);
/* 639:639 */    this.jdField_a_of_type_Array2dOfFloat[3][3] = (this.jdField_a_of_type_ArrayOfFloat[15] - this.jdField_a_of_type_ArrayOfFloat[13]);
/* 640:    */    
/* 642:642 */    f1 = FastMath.l(this.jdField_a_of_type_Array2dOfFloat[3][0] * this.jdField_a_of_type_Array2dOfFloat[3][0] + this.jdField_a_of_type_Array2dOfFloat[3][1] * this.jdField_a_of_type_Array2dOfFloat[3][1] + this.jdField_a_of_type_Array2dOfFloat[3][2] * this.jdField_a_of_type_Array2dOfFloat[3][2]);
/* 643:643 */    this.jdField_a_of_type_Array2dOfFloat[3][0] /= f1;
/* 644:644 */    this.jdField_a_of_type_Array2dOfFloat[3][1] /= f1;
/* 645:645 */    this.jdField_a_of_type_Array2dOfFloat[3][2] /= f1;
/* 646:646 */    this.jdField_a_of_type_Array2dOfFloat[3][3] /= f1;
/* 647:    */    
/* 649:649 */    this.jdField_a_of_type_Array2dOfFloat[4][0] = (this.jdField_a_of_type_ArrayOfFloat[3] - this.jdField_a_of_type_ArrayOfFloat[2]);
/* 650:650 */    this.jdField_a_of_type_Array2dOfFloat[4][1] = (this.jdField_a_of_type_ArrayOfFloat[7] - this.jdField_a_of_type_ArrayOfFloat[6]);
/* 651:651 */    this.jdField_a_of_type_Array2dOfFloat[4][2] = (this.jdField_a_of_type_ArrayOfFloat[11] - this.jdField_a_of_type_ArrayOfFloat[10]);
/* 652:652 */    this.jdField_a_of_type_Array2dOfFloat[4][3] = (this.jdField_a_of_type_ArrayOfFloat[15] - this.jdField_a_of_type_ArrayOfFloat[14]);
/* 653:    */    
/* 655:655 */    f1 = FastMath.l(this.jdField_a_of_type_Array2dOfFloat[4][0] * this.jdField_a_of_type_Array2dOfFloat[4][0] + this.jdField_a_of_type_Array2dOfFloat[4][1] * this.jdField_a_of_type_Array2dOfFloat[4][1] + this.jdField_a_of_type_Array2dOfFloat[4][2] * this.jdField_a_of_type_Array2dOfFloat[4][2]);
/* 656:656 */    this.jdField_a_of_type_Array2dOfFloat[4][0] /= f1;
/* 657:657 */    this.jdField_a_of_type_Array2dOfFloat[4][1] /= f1;
/* 658:658 */    this.jdField_a_of_type_Array2dOfFloat[4][2] /= f1;
/* 659:659 */    this.jdField_a_of_type_Array2dOfFloat[4][3] /= f1;
/* 660:    */    
/* 662:662 */    this.jdField_a_of_type_Array2dOfFloat[5][0] = (this.jdField_a_of_type_ArrayOfFloat[3] + this.jdField_a_of_type_ArrayOfFloat[2]);
/* 663:663 */    this.jdField_a_of_type_Array2dOfFloat[5][1] = (this.jdField_a_of_type_ArrayOfFloat[7] + this.jdField_a_of_type_ArrayOfFloat[6]);
/* 664:664 */    this.jdField_a_of_type_Array2dOfFloat[5][2] = (this.jdField_a_of_type_ArrayOfFloat[11] + this.jdField_a_of_type_ArrayOfFloat[10]);
/* 665:665 */    this.jdField_a_of_type_Array2dOfFloat[5][3] = (this.jdField_a_of_type_ArrayOfFloat[15] + this.jdField_a_of_type_ArrayOfFloat[14]);
/* 666:    */    
/* 668:668 */    f1 = FastMath.l(this.jdField_a_of_type_Array2dOfFloat[5][0] * this.jdField_a_of_type_Array2dOfFloat[5][0] + this.jdField_a_of_type_Array2dOfFloat[5][1] * this.jdField_a_of_type_Array2dOfFloat[5][1] + this.jdField_a_of_type_Array2dOfFloat[5][2] * this.jdField_a_of_type_Array2dOfFloat[5][2]);
/* 669:669 */    this.jdField_a_of_type_Array2dOfFloat[5][0] /= f1;
/* 670:670 */    this.jdField_a_of_type_Array2dOfFloat[5][1] /= f1;
/* 671:671 */    this.jdField_a_of_type_Array2dOfFloat[5][2] /= f1;
/* 672:672 */    this.jdField_a_of_type_Array2dOfFloat[5][3] /= f1;
/* 673:    */  }
/* 674:    */  
/* 676:676 */  public void a(ClientStateInterface paramClientStateInterface, xq paramxq) { this.jdField_a_of_type_WV.a(paramClientStateInterface); }
/* 677:    */  
/* 678:    */  protected final void e() {
/* 679:679 */    if ((xu.X.a().equals("ZOOM")) || (Keyboard.isKeyDown(42)))
/* 680:    */    {
/* 682:682 */      int m = this.jdField_a_of_type_Int;
/* 683:683 */      this.jdField_a_of_type_Int = a(this.jdField_a_of_type_Int - this.jdField_a_of_type_WV.jdField_a_of_type_Int);
/* 684:684 */      if (this.jdField_a_of_type_Boolean)
/* 685:    */      {
/* 686:686 */        this.jdField_a_of_type_Int = ((int)this.jdField_e_of_type_Float);
/* 687:687 */        this.jdField_a_of_type_Boolean = false;
/* 688:    */      }
/* 689:    */      
/* 691:691 */      if (m != this.jdField_a_of_type_Int) {
/* 692:692 */        float f1 = this.jdField_a_of_type_Int * this.jdField_d_of_type_Float;
/* 693:693 */        this.jdField_a_of_type_Float = (this.jdField_a_of_type_Int > 0 ? FastMath.f(FastMath.b(f1, 1.3F)) : 0.0F);
/* 694:    */      }
/* 695:    */    }
/* 696:    */  }
/* 697:    */  
/* 698:    */  public final void b(xq paramxq) {
/* 699:699 */    this.jdField_a_of_type_Xa.a(paramxq);
/* 700:700 */    getWorldTransform().origin.set(a().a());
/* 701:    */  }
/* 702:    */  
/* 705:    */  public final Vector3f g()
/* 706:    */  {
/* 707:707 */    return this.i;
/* 708:    */  }
/* 709:    */  
/* 711:    */  public final Vector3f h()
/* 712:    */  {
/* 713:713 */    return this.j;
/* 714:    */  }
/* 715:    */  
/* 717:    */  public final Vector3f i()
/* 718:    */  {
/* 719:719 */    return this.k;
/* 720:    */  }
/* 721:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.graphicsengine.camera.Camera
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */