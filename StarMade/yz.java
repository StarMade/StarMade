/*   1:    */import java.nio.DoubleBuffer;
/*   2:    */import java.nio.FloatBuffer;
/*   3:    */import java.nio.IntBuffer;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.List;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */import org.lwjgl.BufferUtils;
/*   8:    */import org.lwjgl.input.Mouse;
/*   9:    */import org.lwjgl.opengl.GL11;
/*  10:    */import org.lwjgl.util.vector.Matrix4f;
/*  11:    */import org.lwjgl.util.vector.Vector4f;
/*  12:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  13:    */import org.schema.schine.network.client.ClientState;
/*  14:    */import org.schema.schine.network.client.GUICallbackController;
/*  15:    */
/*  19:    */public abstract class yz
/*  20:    */  extends xM
/*  21:    */  implements xX, xg
/*  22:    */{
/*  23:    */  public final Vector3f b;
/*  24:    */  public boolean f;
/*  25:    */  public ys a;
/*  26:    */  
/*  27:    */  public static void h()
/*  28:    */  {
/*  29: 29 */    GlUtil.a(5889);
/*  30: 30 */    GlUtil.c();
/*  31: 31 */    GlUtil.a(5888);
/*  32: 32 */    GlUtil.c();
/*  33:    */    
/*  34: 34 */    GL11.glEnable(2929);
/*  35: 35 */    GL11.glEnable(2896);
/*  36:    */  }
/*  37:    */  
/*  43:    */  public static void i()
/*  44:    */  {
/*  45: 45 */    GL11.glDisable(2896);
/*  46: 46 */    GL11.glDisable(2929);
/*  47: 47 */    GlUtil.d();
/*  48: 48 */    GlUtil.a(5889);
/*  49: 49 */    GlUtil.d();
/*  50: 50 */    GlUtil.b();
/*  51: 51 */    GlUtil.a(xm.b(), xm.a(), 0.0F);
/*  52: 52 */    GlUtil.a(5888);
/*  53: 53 */    GlUtil.b();
/*  54:    */  }
/*  55:    */  
/*  56:    */  public static void j() {
/*  57: 57 */    a(xm.b(), xm.a());
/*  58:    */  }
/*  59:    */  
/*  60:    */  public static void a(int paramInt1, int paramInt2) {
/*  61: 61 */    GL11.glDisable(2896);
/*  62: 62 */    GL11.glDisable(2929);
/*  63:    */    
/*  64: 64 */    GlUtil.d();
/*  65:    */    
/*  66: 66 */    GlUtil.a(5889);
/*  67:    */    
/*  68: 68 */    GlUtil.d();
/*  69: 69 */    GlUtil.b();
/*  70: 70 */    GlUtil.a(paramInt1, paramInt2, 0.0F, -500.0F, 500.0F);
/*  71:    */    
/*  72: 72 */    GlUtil.a(5888);
/*  73:    */    
/*  74: 74 */    GlUtil.b();
/*  75:    */  }
/*  76:    */  
/*  97: 97 */  private int[] jdField_a_of_type_ArrayOfInt = new int[4];
/*  98:    */  public boolean g;
/*  99:    */  private ClientState jdField_a_of_type_OrgSchemaSchineNetworkClientClientState;
/* 100:    */  public Object a;
/* 101:    */  private boolean jdField_a_of_type_Boolean;
/* 102:    */  
/* 103:    */  static {
/* 104:104 */    BufferUtils.createIntBuffer(16);
/* 105:    */  }
/* 106:    */  
/* 120:120 */  private static FloatBuffer jdField_b_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(3);
/* 121:    */  
/* 122:    */  public Vector4f a;
/* 123:    */  
/* 124:124 */  private static DoubleBuffer jdField_a_of_type_JavaNioDoubleBuffer = BufferUtils.createDoubleBuffer(4);
/* 125:    */  
/* 126:126 */  private static DoubleBuffer jdField_b_of_type_JavaNioDoubleBuffer = BufferUtils.createDoubleBuffer(4);
/* 127:    */  
/* 128:128 */  private static DoubleBuffer c = BufferUtils.createDoubleBuffer(4);
/* 129:129 */  private static DoubleBuffer d = BufferUtils.createDoubleBuffer(4);
/* 130:    */  public static boolean h;
/* 131:    */  
/* 132:    */  public yz(ClientState paramClientState)
/* 133:    */  {
/* 134: 90 */    this.jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/* 135:    */    
/* 143: 99 */    this.jdField_a_of_type_OrgLwjglUtilVectorVector4f = new Vector4f();
/* 144:    */    
/* 179:135 */    this.jdField_a_of_type_OrgSchemaSchineNetworkClientClientState = paramClientState;
/* 180:    */  }
/* 181:    */  
/* 182:138 */  public void a(yz paramyz) { paramyz.b(this);
/* 183:139 */    this.jdField_a_of_type_JavaUtilList.add(paramyz);
/* 184:    */  }
/* 185:    */  
/* 187:    */  public final void k()
/* 188:    */  {
/* 189:145 */    if (h()) {
/* 190:146 */      return;
/* 191:    */    }
/* 192:148 */    GlUtil.d();
/* 193:    */    
/* 195:151 */    this.f = false;
/* 196:    */    
/* 197:153 */    r();
/* 198:    */    
/* 199:155 */    if (this.g) {
/* 200:156 */      l();
/* 201:    */    }
/* 202:    */    
/* 203:159 */    for (Iterator localIterator = this.jdField_a_of_type_JavaUtilList.iterator(); localIterator.hasNext();) {
/* 204:160 */      ((xM)localIterator.next()).b();
/* 205:    */    }
/* 206:162 */    GlUtil.c();
/* 207:    */  }
/* 208:    */  
/* 211:    */  public void l()
/* 212:    */  {
/* 213:169 */    if (h) {
/* 214:170 */      this.f = false;
/* 215:171 */      return;
/* 216:    */    }
/* 217:    */    
/* 219:175 */    Matrix4f localMatrix4f = xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f;
/* 220:    */    
/* 221:177 */    float f2 = new Vector3f(localMatrix4f.m00, localMatrix4f.m01, localMatrix4f.m02).length();
/* 222:178 */    float f3 = new Vector3f(localMatrix4f.m10, localMatrix4f.m11, localMatrix4f.m12).length();
/* 223:    */    
/* 224:180 */    Mouse.getX();
/* 225:181 */    Mouse.getY();
/* 226:182 */    jdField_b_of_type_JavaNioFloatBuffer.rewind();
/* 227:    */    
/* 275:231 */    float f4 = (Mouse.getX() - localMatrix4f.m30) * f2;
/* 276:232 */    float f1 = (xm.a() - Mouse.getY() - localMatrix4f.m31) * f3;
/* 277:233 */    this.jdField_b_of_type_JavaxVecmathVector3f.set(f4, f1, 0.0F);
/* 278:    */    
/* 280:236 */    if (System.currentTimeMillis() - a().getLastDeactivatedMenu() > 200L) {
/* 281:237 */      int j = (this.jdField_b_of_type_JavaxVecmathVector3f.x < b() * f2 * f2) && (this.jdField_b_of_type_JavaxVecmathVector3f.x > 0.0F) ? 1 : 0;
/* 282:    */      
/* 283:239 */      int i = (this.jdField_b_of_type_JavaxVecmathVector3f.y < a() * f3 * f3) && (this.jdField_b_of_type_JavaxVecmathVector3f.y > 0.0F) ? 1 : 0;
/* 284:    */      
/* 285:241 */      this.f = ((j != 0) && (i != 0));
/* 286:    */    } else {
/* 287:243 */      this.f = false;
/* 288:    */    }
/* 289:    */    
/* 290:246 */    if ((a_()) && 
/* 291:247 */      (this.jdField_a_of_type_Ys != null) && 
/* 292:248 */      (!this.jdField_a_of_type_Ys.a())) {
/* 293:249 */      this.jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.getGuiCallbackController().addCallback(this.jdField_a_of_type_Ys, this);
/* 294:    */    }
/* 295:    */    
/* 297:253 */    this.jdField_a_of_type_Boolean = a_();
/* 298:    */  }
/* 299:    */  
/* 301:    */  public final void m()
/* 302:    */  {
/* 303:259 */    GlUtil.d();
/* 304:260 */    r();
/* 305:261 */    l();
/* 306:262 */    GlUtil.c();
/* 307:    */  }
/* 308:    */  
/* 314:    */  public void b(yz paramyz)
/* 315:    */  {
/* 316:272 */    paramyz.b(null);
/* 317:273 */    this.jdField_a_of_type_JavaUtilList.remove(paramyz);
/* 318:    */  }
/* 319:    */  
/* 350:    */  public final void a(Vector4f paramVector4f)
/* 351:    */  {
/* 352:308 */    jdField_a_of_type_JavaNioDoubleBuffer.put(new double[] { 1.0D, 0.0D, 0.0D, -paramVector4f.x }).rewind();
/* 353:309 */    GL11.glClipPlane(12288, jdField_a_of_type_JavaNioDoubleBuffer);
/* 354:310 */    GL11.glEnable(12288);
/* 355:    */    
/* 356:312 */    jdField_b_of_type_JavaNioDoubleBuffer.put(new double[] { -1.0D, 0.0D, 0.0D, paramVector4f.y }).rewind();
/* 357:313 */    GL11.glClipPlane(12289, jdField_b_of_type_JavaNioDoubleBuffer);
/* 358:314 */    GL11.glEnable(12289);
/* 359:    */    
/* 360:316 */    c.put(new double[] { 0.0D, 1.0D, 0.0D, -paramVector4f.z }).rewind();
/* 361:317 */    GL11.glClipPlane(12290, c);
/* 362:318 */    GL11.glEnable(12290);
/* 363:    */    
/* 364:320 */    d.put(new double[] { 0.0D, -1.0D, 0.0D, paramVector4f.w }).rewind();
/* 365:321 */    GL11.glClipPlane(12291, d);
/* 366:322 */    GL11.glEnable(12291);
/* 367:    */    
/* 368:324 */    b();
/* 369:    */    
/* 370:326 */    GL11.glDisable(12288);
/* 371:327 */    GL11.glDisable(12289);
/* 372:328 */    GL11.glDisable(12290);
/* 373:329 */    GL11.glDisable(12291);
/* 374:    */  }
/* 375:    */  
/* 411:    */  public final Vector3f f()
/* 412:    */  {
/* 413:369 */    return this.jdField_b_of_type_JavaxVecmathVector3f;
/* 414:    */  }
/* 415:    */  
/* 417:    */  public ClientState a()
/* 418:    */  {
/* 419:375 */    return this.jdField_a_of_type_OrgSchemaSchineNetworkClientClientState;
/* 420:    */  }
/* 421:    */  
/* 423:    */  public final Object b()
/* 424:    */  {
/* 425:381 */    return this.jdField_a_of_type_JavaLangObject;
/* 426:    */  }
/* 427:    */  
/* 440:    */  public boolean a_()
/* 441:    */  {
/* 442:398 */    return this.f;
/* 443:    */  }
/* 444:    */  
/* 453:    */  public final boolean j()
/* 454:    */  {
/* 455:411 */    if (a().x > xm.b()) {
/* 456:412 */      return false;
/* 457:    */    }
/* 458:414 */    if (a().y > xm.a()) {
/* 459:415 */      return false;
/* 460:    */    }
/* 461:417 */    if (a().x + b() * this.jdField_a_of_type_JavaxVecmathVector3f.x < 0.0F) {
/* 462:418 */      return false;
/* 463:    */    }
/* 464:420 */    if (a().y + a() * this.jdField_a_of_type_JavaxVecmathVector3f.y < 0.0F) {
/* 465:421 */      return false;
/* 466:    */    }
/* 467:423 */    return true;
/* 468:    */  }
/* 469:    */  
/* 475:    */  public final boolean k()
/* 476:    */  {
/* 477:433 */    for (int i = 0; i < 4; i++) {
/* 478:434 */      if (this.jdField_a_of_type_ArrayOfInt[i] != xe.jdField_a_of_type_JavaNioIntBuffer.get(i)) {
/* 479:435 */        for (i = 0; i < 4; i++) {
/* 480:436 */          this.jdField_a_of_type_ArrayOfInt[i] = xe.jdField_a_of_type_JavaNioIntBuffer.get(i);
/* 481:    */        }
/* 482:    */        
/* 483:439 */        return true;
/* 484:    */      }
/* 485:    */    }
/* 486:442 */    return false;
/* 487:    */  }
/* 488:    */  
/* 489:    */  public final void h(int paramInt)
/* 490:    */  {
/* 491:447 */    a(paramInt, xe.jdField_a_of_type_JavaNioIntBuffer.get(0), xe.jdField_a_of_type_JavaNioIntBuffer.get(1), xe.jdField_a_of_type_JavaNioIntBuffer.get(2), xe.jdField_a_of_type_JavaNioIntBuffer.get(3));
/* 492:    */  }
/* 493:    */  
/* 497:    */  public final void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/* 498:    */  {
/* 499:455 */    if ((paramInt1 & 0x10) == 16) {
/* 500:456 */      a().y = ((paramInt5 - a() * this.jdField_a_of_type_JavaxVecmathVector3f.y - paramInt3) / 2.0F);
/* 501:    */    }
/* 502:458 */    if ((paramInt1 & 0x20) == 32) {
/* 503:459 */      a().x = ((paramInt4 - b() * this.jdField_a_of_type_JavaxVecmathVector3f.x - paramInt2) / 2.0F);
/* 504:    */    }
/* 505:461 */    if ((paramInt1 & 0x1) == 1) {
/* 506:462 */      a().x = paramInt2;
/* 507:    */    }
/* 508:464 */    if ((paramInt1 & 0x2) == 2) {
/* 509:465 */      a().x = (paramInt4 - b() * this.jdField_a_of_type_JavaxVecmathVector3f.x);
/* 510:    */    }
/* 511:467 */    if ((paramInt1 & 0x4) == 4) {
/* 512:468 */      a().y = paramInt3;
/* 513:    */    }
/* 514:470 */    if ((paramInt1 & 0x8) == 8) {
/* 515:471 */      a().y = (paramInt5 - a() * this.jdField_a_of_type_JavaxVecmathVector3f.y);
/* 516:    */    }
/* 517:    */  }
/* 518:    */  
/* 536:    */  public void a(ys paramys)
/* 537:    */  {
/* 538:494 */    this.jdField_a_of_type_Ys = paramys;
/* 539:    */  }
/* 540:    */  
/* 579:    */  public final void r()
/* 580:    */  {
/* 581:537 */    GlUtil.b(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 582:538 */    GlUtil.b(this.jdField_a_of_type_JavaxVecmathVector3f.x, this.jdField_a_of_type_JavaxVecmathVector3f.y, this.jdField_a_of_type_JavaxVecmathVector3f.z);
/* 583:    */  }
/* 584:    */  
/* 592:    */  public final boolean l()
/* 593:    */  {
/* 594:550 */    return this.jdField_a_of_type_Boolean;
/* 595:    */  }
/* 596:    */  
/* 597:    */  protected void d() {}
/* 598:    */  
/* 599:    */  public abstract float a();
/* 600:    */  
/* 601:    */  public abstract float b();
/* 602:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yz
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */