/*    1:     */package org.schema.game.common.data.physics;
/*    2:     */
/*    3:     */import com.bulletphysics.collision.dispatch.ManifoldResult;
/*    4:     */import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
/*    5:     */import com.bulletphysics.collision.shapes.BoxShape;
/*    6:     */import com.bulletphysics.linearmath.IDebugDraw;
/*    7:     */import com.bulletphysics.linearmath.Transform;
/*    8:     */import com.bulletphysics.linearmath.VectorUtil;
/*    9:     */import java.util.Arrays;
/*   10:     */import javax.vecmath.Matrix3f;
/*   11:     */import javax.vecmath.Vector2f;
/*   12:     */import javax.vecmath.Vector3f;
/*   13:     */import org.schema.common.FastMath;
/*   14:     */
/*   52:     */public class BoxBoxDetector
/*   53:     */{
/*   54:  54 */  private Vector3f distPoint = new Vector3f();
/*   55:  55 */  private Vector3f pp = new Vector3f();
/*   56:  56 */  private Vector3f normalC = new Vector3f();
/*   57:  57 */  private Vector3f pa = new Vector3f();
/*   58:  58 */  private Vector3f pb = new Vector3f();
/*   59:  59 */  private Vector3f ua = new Vector3f();
/*   60:  60 */  private Vector3f ub = new Vector3f();
/*   61:  61 */  private Vector2f alphaBeta = new Vector2f();
/*   62:  62 */  private Vector3f negNormal = new Vector3f();
/*   63:  63 */  private Vector3f nr = new Vector3f();
/*   64:  64 */  private Vector3f normal2 = new Vector3f();
/*   65:  65 */  private Vector3f anr = new Vector3f();
/*   66:  66 */  private Vector3f ppa = new Vector3f();
/*   67:  67 */  private Vector3f ppb = new Vector3f();
/*   68:  68 */  private Vector3f Sa = new Vector3f();
/*   69:  69 */  private Vector3f Sb = new Vector3f();
/*   70:  70 */  private int[] iret = new int[8];
/*   71:  71 */  private Vector3f pointInWorldFAA = new Vector3f();
/*   72:  72 */  private Vector3f posInWorldFA = new Vector3f();
/*   73:  73 */  private Vector3f center = new Vector3f();
/*   74:  74 */  private Vector3f pointInWorldRes = new Vector3f();
/*   75:  75 */  private Vector3f scaledN = new Vector3f();
/*   76:  76 */  private BoxBoxDetector.CB cb = new BoxBoxDetector.CB(this, null);
/*   77:     */  
/*   80:  80 */  private float fudge_factor = 1.05F;
/*   81:     */  
/*   84:  84 */  private float[] s_quadBuffer = new float[16];
/*   85:     */  
/*   86:  86 */  private float[] s_temp1 = new float[12];
/*   87:     */  
/*   89:  89 */  private float[] s_temp2 = new float[12];
/*   90:     */  
/*   92:  92 */  private float[] s_quad = new float[8];
/*   93:     */  
/*  101: 101 */  private float[] s_ret = new float[16];
/*  102:     */  
/*  103: 103 */  private float[] s_point = new float[24];
/*  104: 104 */  private float[] s_dep = new float[8];
/*  105:     */  
/*  106: 106 */  private float[] s_A = new float[8];
/*  107: 107 */  private float[] s_rectReferenceFace = new float[2];
/*  108: 108 */  private int[] s_availablePoints = new int[8];
/*  109: 109 */  private Vector3f normal = new Vector3f();
/*  110: 110 */  private Vector3f translationA = new Vector3f();
/*  111: 111 */  private Vector3f translationB = new Vector3f();
/*  112: 112 */  private Vector3f box1Margin = new Vector3f();
/*  113: 113 */  private Vector3f box2Margin = new Vector3f();
/*  114: 114 */  private Vector3f box1MarginCache = new Vector3f();
/*  115: 115 */  private Vector3f box2MarginCache = new Vector3f();
/*  116: 116 */  private Vector3f rowA = new Vector3f();
/*  117: 117 */  private Vector3f rowB = new Vector3f();
/*  118: 118 */  private Transform transformA = new Transform();
/*  119: 119 */  private Transform transformB = new Transform();
/*  120: 120 */  private Vector3f pTmp = new Vector3f();
/*  121:     */  
/*  123:     */  public static final boolean USE_CENTER_POINT = false;
/*  124:     */  
/*  125:     */  private Float depth;
/*  126:     */  
/*  127:     */  private boolean cachedBoxSize;
/*  128:     */  
/*  129:     */  public int contacts;
/*  130:     */  
/*  131:     */  public float maxDepth;
/*  132:     */  
/*  134:     */  private static float DDOTSpec(float[] paramArrayOfFloat, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
/*  135:     */  {
/*  136: 136 */    return paramArrayOfFloat[paramInt] * paramFloat1 + paramArrayOfFloat[(paramInt + 1)] * paramFloat2 + paramArrayOfFloat[(paramInt + 2)] * paramFloat3;
/*  137:     */  }
/*  138:     */  
/*  148:     */  private static float DDOT(Vector3f paramVector3f, int paramInt1, float[] paramArrayOfFloat, int paramInt2)
/*  149:     */  {
/*  150: 150 */    return VectorUtil.getCoord(paramVector3f, paramInt1) * paramArrayOfFloat[paramInt2] + VectorUtil.getCoord(paramVector3f, paramInt1 + 1) * paramArrayOfFloat[(paramInt2 + 1)] + VectorUtil.getCoord(paramVector3f, paramInt1 + 2) * paramArrayOfFloat[(paramInt2 + 2)];
/*  151:     */  }
/*  152:     */  
/*  179:     */  private static float DDOT14(Vector3f paramVector3f, int paramInt1, float[] paramArrayOfFloat, int paramInt2)
/*  180:     */  {
/*  181: 181 */    return VectorUtil.getCoord(paramVector3f, paramInt1) * paramArrayOfFloat[paramInt2] + VectorUtil.getCoord(paramVector3f, paramInt1 + 1) * paramArrayOfFloat[(paramInt2 + 4)] + VectorUtil.getCoord(paramVector3f, paramInt1 + 2) * paramArrayOfFloat[(paramInt2 + 8)];
/*  182:     */  }
/*  183:     */  
/*  201:     */  private static float DDOT41(float[] paramArrayOfFloat, int paramInt1, Vector3f paramVector3f, int paramInt2)
/*  202:     */  {
/*  203: 203 */    return paramArrayOfFloat[paramInt1] * VectorUtil.getCoord(paramVector3f, paramInt2) + paramArrayOfFloat[(paramInt1 + 4)] * VectorUtil.getCoord(paramVector3f, paramInt2 + 1) + paramArrayOfFloat[(paramInt1 + 8)] * VectorUtil.getCoord(paramVector3f, paramInt2 + 2);
/*  204:     */  }
/*  205:     */  
/*  207:     */  private static float DDOT41Spec(float[] paramArrayOfFloat, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
/*  208:     */  {
/*  209: 209 */    return paramArrayOfFloat[paramInt] * paramFloat1 + paramArrayOfFloat[(paramInt + 4)] * paramFloat2 + paramArrayOfFloat[(paramInt + 8)] * paramFloat3;
/*  210:     */  }
/*  211:     */  
/*  213:     */  private static float DDOT44(float[] paramArrayOfFloat1, int paramInt1, float[] paramArrayOfFloat2, int paramInt2)
/*  214:     */  {
/*  215: 215 */    return paramArrayOfFloat1[paramInt1] * paramArrayOfFloat2[paramInt2] + paramArrayOfFloat1[(paramInt1 + 4)] * paramArrayOfFloat2[(paramInt2 + 4)] + paramArrayOfFloat1[(paramInt1 + 8)] * paramArrayOfFloat2[(paramInt2 + 8)];
/*  216:     */  }
/*  217:     */  
/*  223:     */  private static void DLineClosestApproach(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, Vector2f paramVector2f, Vector3f paramVector3f5)
/*  224:     */  {
/*  225: 225 */    paramVector3f5.sub(paramVector3f3, paramVector3f1);
/*  226:     */    
/*  227: 227 */    paramVector3f1 = paramVector3f2.dot(paramVector3f4);
/*  228:     */    
/*  229: 229 */    paramVector3f2 = paramVector3f2.dot(paramVector3f5);
/*  230:     */    
/*  231: 231 */    paramVector3f3 = -paramVector3f4.dot(paramVector3f5);
/*  232:     */    
/*  234: 234 */    if ((paramVector3f4 = 1.0F - paramVector3f1 * paramVector3f1) <= 1.0E-004F)
/*  235:     */    {
/*  237: 237 */      paramVector2f.x = 0.0F;
/*  238: 238 */      paramVector2f.y = 0.0F;return;
/*  239:     */    }
/*  240:     */    
/*  242: 242 */    paramVector3f4 = 1.0F / paramVector3f4;
/*  243: 243 */    paramVector2f.x = ((paramVector3f2 + paramVector3f1 * paramVector3f3) * paramVector3f4);
/*  244: 244 */    paramVector2f.y = ((paramVector3f1 * paramVector3f2 + paramVector3f3) * paramVector3f4);
/*  245:     */  }
/*  246:     */  
/*  251:     */  public static void DMULTIPLY0_331(Vector3f paramVector3f1, float[] paramArrayOfFloat, Vector3f paramVector3f2)
/*  252:     */  {
/*  253: 253 */    paramVector3f1.x = DDOTSpec(paramArrayOfFloat, 0, paramVector3f2.x, paramVector3f2.y, paramVector3f2.z);
/*  254: 254 */    paramVector3f1.y = DDOTSpec(paramArrayOfFloat, 4, paramVector3f2.x, paramVector3f2.y, paramVector3f2.z);
/*  255: 255 */    paramVector3f1.z = DDOTSpec(paramArrayOfFloat, 8, paramVector3f2.x, paramVector3f2.y, paramVector3f2.z);
/*  256:     */  }
/*  257:     */  
/*  262:     */  public static void DMULTIPLY1_331(Vector3f paramVector3f1, float[] paramArrayOfFloat, Vector3f paramVector3f2)
/*  263:     */  {
/*  264: 264 */    paramVector3f1.x = DDOT41Spec(paramArrayOfFloat, 0, paramVector3f2.x, paramVector3f2.y, paramVector3f2.z);
/*  265: 265 */    paramVector3f1.y = DDOT41Spec(paramArrayOfFloat, 1, paramVector3f2.x, paramVector3f2.y, paramVector3f2.z);
/*  266: 266 */    paramVector3f1.z = DDOT41Spec(paramArrayOfFloat, 2, paramVector3f2.x, paramVector3f2.y, paramVector3f2.z);
/*  267:     */  }
/*  268:     */  
/*  272:     */  private void CullPoints2(int paramInt1, float[] paramArrayOfFloat, int paramInt2, int paramInt3, int[] paramArrayOfInt)
/*  273:     */  {
/*  274: 274 */    int i = 0;
/*  275:     */    float f2;
/*  276:     */    float f3;
/*  277: 277 */    float f1; if (paramInt1 == 1)
/*  278:     */    {
/*  279: 279 */      f2 = paramArrayOfFloat[0];
/*  280: 280 */      f3 = paramArrayOfFloat[1];
/*  281:     */    }
/*  282: 282 */    else if (paramInt1 == 2)
/*  283:     */    {
/*  284: 284 */      f2 = 0.5F * (paramArrayOfFloat[0] + paramArrayOfFloat[2]);
/*  285: 285 */      f3 = 0.5F * (paramArrayOfFloat[1] + paramArrayOfFloat[3]);
/*  286:     */    }
/*  287:     */    else
/*  288:     */    {
/*  289: 289 */      f1 = 0.0F;
/*  290: 290 */      f2 = 0.0F;
/*  291: 291 */      f3 = 0.0F;
/*  292: 292 */      for (j = 0; j < paramInt1 - 1; j++)
/*  293:     */      {
/*  294: 294 */        f4 = paramArrayOfFloat[(j << 1)] * paramArrayOfFloat[((j << 1) + 3)] - paramArrayOfFloat[((j << 1) + 2)] * paramArrayOfFloat[((j << 1) + 1)];
/*  295: 295 */        f1 += f4;
/*  296: 296 */        f2 += f4 * (paramArrayOfFloat[(j << 1)] + paramArrayOfFloat[((j << 1) + 2)]);
/*  297: 297 */        f3 += f4 * (paramArrayOfFloat[((j << 1) + 1)] + paramArrayOfFloat[((j << 1) + 3)]);
/*  298:     */      }
/*  299: 299 */      float f4 = paramArrayOfFloat[((paramInt1 << 1) - 2)] * paramArrayOfFloat[1] - paramArrayOfFloat[0] * paramArrayOfFloat[((paramInt1 << 1) - 1)];
/*  300: 300 */      if (Math.abs(f1 + f4) > 1.192093E-007F)
/*  301:     */      {
/*  302: 302 */        f1 = 1.0F / (3.0F * (f1 + f4));
/*  303:     */      }
/*  304:     */      else
/*  305:     */      {
/*  306: 306 */        f1 = 1.0E+030F;
/*  307:     */      }
/*  308: 308 */      f2 = f1 * (f2 + f4 * (paramArrayOfFloat[((paramInt1 << 1) - 2)] + paramArrayOfFloat[0]));
/*  309: 309 */      f3 = f1 * (f3 + f4 * (paramArrayOfFloat[((paramInt1 << 1) - 1)] + paramArrayOfFloat[1]));
/*  310:     */    }
/*  311:     */    
/*  313: 313 */    float[] arrayOfFloat = this.s_A;
/*  314: 314 */    for (int j = 0; j < paramInt1; j++)
/*  315:     */    {
/*  316: 316 */      arrayOfFloat[j] = FastMath.a(paramArrayOfFloat[((j << 1) + 1)] - f3, paramArrayOfFloat[(j << 1)] - f2);
/*  317:     */    }
/*  318:     */    
/*  321: 321 */    for (j = 0; j < paramInt1; j++)
/*  322:     */    {
/*  323: 323 */      this.s_availablePoints[j] = 1;
/*  324:     */    }
/*  325: 325 */    this.s_availablePoints[paramInt3] = 0;
/*  326: 326 */    paramArrayOfInt[0] = paramInt3;
/*  327: 327 */    i++;
/*  328: 328 */    for (paramArrayOfFloat = 1; paramArrayOfFloat < paramInt2; paramArrayOfFloat++)
/*  329:     */    {
/*  331: 331 */      if ((f1 = paramArrayOfFloat * (6.283186F / paramInt2) + arrayOfFloat[paramInt3]) > 3.141593F)
/*  332:     */      {
/*  333: 333 */        f1 -= 6.283186F;
/*  334:     */      }
/*  335: 335 */      int k = 1;
/*  336: 336 */      f3 = 3.4028235E+38F;
/*  337:     */      
/*  338: 338 */      paramArrayOfInt[i] = paramInt3;
/*  339:     */      
/*  340: 340 */      for (j = 0; j < paramInt1; j++)
/*  341:     */      {
/*  342: 342 */        if (this.s_availablePoints[j] != 0)
/*  343:     */        {
/*  344:     */          float f5;
/*  345: 345 */          if ((f5 = Math.abs(arrayOfFloat[j] - f1)) > 3.141593F)
/*  346:     */          {
/*  347: 347 */            f5 = 6.283186F - f5;
/*  348:     */          }
/*  349: 349 */          if ((k != 0) || (f5 < f3))
/*  350:     */          {
/*  351: 351 */            k = 0;
/*  352: 352 */            f3 = f5;
/*  353: 353 */            paramArrayOfInt[i] = j;
/*  354:     */          }
/*  355:     */        }
/*  356:     */      }
/*  357:     */      
/*  360: 360 */      this.s_availablePoints[paramArrayOfInt[i]] = 0;
/*  361: 361 */      i++;
/*  362:     */    }
/*  363:     */  }
/*  364:     */  
/*  366:     */  public void GetClosestPoints(BoxShape paramBoxShape1, BoxShape paramBoxShape2, DiscreteCollisionDetectorInterface.ClosestPointInput paramClosestPointInput, ManifoldResult paramManifoldResult, IDebugDraw paramIDebugDraw, boolean paramBoolean)
/*  367:     */  {
/*  368: 368 */    this.contacts = 0;
/*  369: 369 */    this.maxDepth = 0.0F;
/*  370: 370 */    this.transformA.set(paramClosestPointInput.transformA);
/*  371: 371 */    this.transformB.set(paramClosestPointInput.transformB);
/*  372:     */    
/*  375: 375 */    this.normal.set(0.0F, 0.0F, 0.0F);
/*  376:     */    
/*  378: 378 */    this.depth = Float.valueOf(0.0F);
/*  379: 379 */    this.translationA.set(this.transformA.origin);
/*  380:     */    
/*  383: 383 */    this.translationB.set(this.transformB.origin);
/*  384:     */    
/*  385: 385 */    if (!this.cachedBoxSize) {
/*  386: 386 */      paramBoxShape1.getHalfExtentsWithMargin(this.box1MarginCache);
/*  387:     */      
/*  389: 389 */      paramBoxShape2.getHalfExtentsWithMargin(this.box2MarginCache);
/*  390:     */      
/*  392: 392 */      this.cachedBoxSize = true;
/*  393:     */    }
/*  394: 394 */    this.box1Margin.set(this.box1MarginCache);
/*  395: 395 */    this.box2Margin.set(this.box2MarginCache);
/*  396:     */    
/*  400: 400 */    for (paramBoxShape1 = 0; paramBoxShape1 < 3; paramBoxShape1++)
/*  401:     */    {
/*  411: 411 */      this.transformA.basis.getRow(paramBoxShape1, this.rowA);
/*  412: 412 */      this.transformB.basis.getRow(paramBoxShape1, this.rowB);
/*  413:     */      
/*  414: 414 */      this.s_temp1[(0 + 4 * paramBoxShape1)] = this.rowA.x;
/*  415: 415 */      this.s_temp2[(0 + 4 * paramBoxShape1)] = this.rowB.x;
/*  416:     */      
/*  417: 417 */      this.s_temp1[(1 + 4 * paramBoxShape1)] = this.rowA.y;
/*  418: 418 */      this.s_temp2[(1 + 4 * paramBoxShape1)] = this.rowB.y;
/*  419:     */      
/*  420: 420 */      this.s_temp1[(2 + 4 * paramBoxShape1)] = this.rowA.z;
/*  421: 421 */      this.s_temp2[(2 + 4 * paramBoxShape1)] = this.rowB.z;
/*  422:     */    }
/*  423:     */    
/*  450: 450 */    this.cb.reset();
/*  451: 451 */    DBoxBox2(this.translationA, this.s_temp1, this.box1Margin, this.translationB, this.s_temp2, this.box2Margin, this.normal, this.depth, -1, 4, 0, paramManifoldResult);
/*  452:     */  }
/*  453:     */  
/*  468:     */  private int DBoxBox2(Vector3f paramVector3f1, float[] paramArrayOfFloat1, Vector3f paramVector3f2, Vector3f paramVector3f3, float[] paramArrayOfFloat2, Vector3f paramVector3f4, Vector3f paramVector3f5, Float paramFloat, int paramInt1, int paramInt2, int paramInt3, ManifoldResult paramManifoldResult)
/*  469:     */  {
/*  470: 470 */    this.cb.normalR = null;
/*  471:     */    
/*  480: 480 */    this.distPoint.sub(paramVector3f3, paramVector3f1);
/*  481:     */    
/*  482: 482 */    this.pp.set(0.0F, 0.0F, 0.0F);
/*  483:     */    
/*  484: 484 */    DMULTIPLY1_331(this.pp, paramArrayOfFloat1, this.distPoint);
/*  485:     */    
/*  496: 496 */    paramFloat = DDOT44(paramArrayOfFloat1, 0, paramArrayOfFloat2, 0);
/*  497: 497 */    paramInt1 = DDOT44(paramArrayOfFloat1, 0, paramArrayOfFloat2, 1);
/*  498: 498 */    paramInt3 = DDOT44(paramArrayOfFloat1, 0, paramArrayOfFloat2, 2);
/*  499:     */    
/*  500: 500 */    float f1 = DDOT44(paramArrayOfFloat1, 1, paramArrayOfFloat2, 0);
/*  501: 501 */    float f2 = DDOT44(paramArrayOfFloat1, 1, paramArrayOfFloat2, 1);
/*  502: 502 */    float f3 = DDOT44(paramArrayOfFloat1, 1, paramArrayOfFloat2, 2);
/*  503:     */    
/*  504: 504 */    float f4 = DDOT44(paramArrayOfFloat1, 2, paramArrayOfFloat2, 0);
/*  505: 505 */    float f5 = DDOT44(paramArrayOfFloat1, 2, paramArrayOfFloat2, 1);
/*  506: 506 */    float f6 = DDOT44(paramArrayOfFloat1, 2, paramArrayOfFloat2, 2);
/*  507:     */    
/*  509: 509 */    float f7 = Math.abs(paramFloat);
/*  510: 510 */    float f8 = Math.abs(paramInt1);
/*  511: 511 */    float f9 = Math.abs(paramInt3);
/*  512:     */    
/*  513: 513 */    float f10 = Math.abs(f1);
/*  514: 514 */    float f11 = Math.abs(f2);
/*  515: 515 */    float f12 = Math.abs(f3);
/*  516:     */    
/*  517: 517 */    float f13 = Math.abs(f4);
/*  518: 518 */    float f14 = Math.abs(f5);
/*  519: 519 */    float f15 = Math.abs(f6);
/*  520:     */    
/*  530: 530 */    this.cb.invert_normal = false;
/*  531: 531 */    this.cb.code = 0;
/*  532:     */    
/*  538: 538 */    if (TST(this.pp.x, paramVector3f2.x + paramVector3f4.x * f7 + paramVector3f4.y * f8 + paramVector3f4.z * f9, paramArrayOfFloat1, 0, 1, this.cb)) return 0;
/*  539: 539 */    if (TST(this.pp.y, paramVector3f2.y + paramVector3f4.x * f10 + paramVector3f4.y * f11 + paramVector3f4.z * f12, paramArrayOfFloat1, 1, 2, this.cb)) return 0;
/*  540: 540 */    if (TST(this.pp.z, paramVector3f2.z + paramVector3f4.x * f13 + paramVector3f4.y * f14 + paramVector3f4.z * f15, paramArrayOfFloat1, 2, 3, this.cb)) { return 0;
/*  541:     */    }
/*  542:     */    
/*  546: 546 */    if (TST(DDOT41(paramArrayOfFloat2, 0, this.distPoint, 0), paramVector3f2.x * f7 + paramVector3f2.y * f10 + paramVector3f2.z * f13 + paramVector3f4.x, paramArrayOfFloat2, 0, 4, this.cb)) return 0;
/*  547: 547 */    if (TST(DDOT41(paramArrayOfFloat2, 1, this.distPoint, 0), paramVector3f2.x * f8 + paramVector3f2.y * f11 + paramVector3f2.z * f14 + paramVector3f4.y, paramArrayOfFloat2, 1, 5, this.cb)) return 0;
/*  548: 548 */    if (TST(DDOT41(paramArrayOfFloat2, 2, this.distPoint, 0), paramVector3f2.x * f9 + paramVector3f2.y * f12 + paramVector3f2.z * f15 + paramVector3f4.z, paramArrayOfFloat2, 2, 6, this.cb)) { return 0;
/*  549:     */    }
/*  550:     */    
/*  555: 555 */    this.normalC.set(0.0F, 0.0F, 0.0F);
/*  556:     */    
/*  557: 557 */    f7 += 1.0E-005F;
/*  558:     */    
/*  560: 560 */    f8 += 1.0E-005F;
/*  561: 561 */    f9 += 1.0E-005F;
/*  562:     */    
/*  563: 563 */    f10 += 1.0E-005F;
/*  564: 564 */    f11 += 1.0E-005F;
/*  565: 565 */    f12 += 1.0E-005F;
/*  566:     */    
/*  567: 567 */    f13 += 1.0E-005F;
/*  568: 568 */    f14 += 1.0E-005F;
/*  569: 569 */    f15 += 1.0E-005F;
/*  570:     */    
/*  572: 572 */    if (TST2(this.pp.z * f1 - this.pp.y * f4, paramVector3f2.y * f13 + paramVector3f2.z * f10 + paramVector3f4.y * f9 + paramVector3f4.z * f8, 0.0F, -f4, f1, this.normalC, 7, this.cb)) return 0;
/*  573: 573 */    if (TST2(this.pp.z * f2 - this.pp.y * f5, paramVector3f2.y * f14 + paramVector3f2.z * f11 + paramVector3f4.x * f9 + paramVector3f4.z * f7, 0.0F, -f5, f2, this.normalC, 8, this.cb)) return 0;
/*  574: 574 */    if (TST2(this.pp.z * f3 - this.pp.y * f6, paramVector3f2.y * f15 + paramVector3f2.z * f12 + paramVector3f4.x * f8 + paramVector3f4.y * f7, 0.0F, -f6, f3, this.normalC, 9, this.cb)) { return 0;
/*  575:     */    }
/*  576:     */    
/*  577: 577 */    if (TST2(this.pp.x * f4 - this.pp.z * paramFloat, paramVector3f2.x * f13 + paramVector3f2.z * f7 + paramVector3f4.y * f12 + paramVector3f4.z * f11, f4, 0.0F, -paramFloat, this.normalC, 10, this.cb)) return 0;
/*  578: 578 */    if (TST2(this.pp.x * f5 - this.pp.z * paramInt1, paramVector3f2.x * f14 + paramVector3f2.z * f8 + paramVector3f4.x * f12 + paramVector3f4.z * f10, f5, 0.0F, -paramInt1, this.normalC, 11, this.cb)) return 0;
/*  579: 579 */    if (TST2(this.pp.x * f6 - this.pp.z * paramInt3, paramVector3f2.x * f15 + paramVector3f2.z * f9 + paramVector3f4.x * f11 + paramVector3f4.y * f10, f6, 0.0F, -paramInt3, this.normalC, 12, this.cb)) { return 0;
/*  580:     */    }
/*  581:     */    
/*  582: 582 */    if (TST2(this.pp.y * paramFloat - this.pp.x * f1, paramVector3f2.x * f10 + paramVector3f2.y * f7 + paramVector3f4.y * f15 + paramVector3f4.z * f14, -f1, paramFloat, 0.0F, this.normalC, 13, this.cb)) return 0;
/*  583: 583 */    if (TST2(this.pp.y * paramInt1 - this.pp.x * f2, paramVector3f2.x * f11 + paramVector3f2.y * f8 + paramVector3f4.x * f15 + paramVector3f4.z * f13, -f2, paramInt1, 0.0F, this.normalC, 14, this.cb)) return 0;
/*  584: 584 */    if (TST2(this.pp.y * paramInt3 - this.pp.x * f3, paramVector3f2.x * f12 + paramVector3f2.y * f9 + paramVector3f4.x * f14 + paramVector3f4.y * f13, -f3, paramInt3, 0.0F, this.normalC, 15, this.cb)) { return 0;
/*  585:     */    }
/*  586:     */    
/*  601: 601 */    if (this.cb.code == 0)
/*  602:     */    {
/*  603: 603 */      return 0;
/*  604:     */    }
/*  605:     */    
/*  607: 607 */    if (this.cb.normalR != null)
/*  608:     */    {
/*  610: 610 */      paramVector3f5.x = this.cb.normalR[(0 + this.cb.normalROffset)];
/*  611: 611 */      paramVector3f5.y = this.cb.normalR[(4 + this.cb.normalROffset)];
/*  612: 612 */      paramVector3f5.z = this.cb.normalR[(8 + this.cb.normalROffset)];
/*  613: 613 */      paramVector3f5.normalize();
/*  614:     */    }
/*  615:     */    else
/*  616:     */    {
/*  617: 617 */      DMULTIPLY0_331(paramVector3f5, paramArrayOfFloat1, this.normalC);
/*  618:     */    }
/*  619: 619 */    if (this.cb.invert_normal)
/*  620:     */    {
/*  621: 621 */      paramVector3f5.negate();
/*  622:     */    }
/*  623: 623 */    paramFloat = Float.valueOf(-this.cb.s);
/*  624:     */    
/*  629: 629 */    if (this.cb.code > 6)
/*  630:     */    {
/*  635: 635 */      this.pa.set(paramVector3f1);
/*  636: 636 */      for (paramInt1 = 0; paramInt1 < 3; paramInt1++)
/*  637:     */      {
/*  638: 638 */        paramInt3 = DDOT14(paramVector3f5, 0, paramArrayOfFloat1, paramInt1) > 0.0F ? 1.0F : -1.0F;
/*  639:     */        
/*  640: 640 */        for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++)
/*  641:     */        {
/*  642: 642 */          VectorUtil.setCoord(this.pa, paramVector3f1, VectorUtil.getCoord(this.pa, paramVector3f1) + paramInt3 * VectorUtil.getCoord(paramVector3f2, paramInt1) * paramArrayOfFloat1[((paramVector3f1 << 2) + paramInt1)]);
/*  643:     */        }
/*  644:     */      }
/*  645:     */      
/*  647: 647 */      this.pb.set(paramVector3f3);
/*  648:     */      
/*  649: 649 */      for (paramInt1 = 0; paramInt1 < 3; paramInt1++)
/*  650:     */      {
/*  651: 651 */        paramInt3 = DDOT14(paramVector3f5, 0, paramArrayOfFloat2, paramInt1) > 0.0F ? -1.0F : 1.0F;
/*  652: 652 */        for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++)
/*  653:     */        {
/*  654: 654 */          VectorUtil.setCoord(this.pb, paramVector3f1, VectorUtil.getCoord(this.pb, paramVector3f1) + paramInt3 * VectorUtil.getCoord(paramVector3f4, paramInt1) * paramArrayOfFloat2[((paramVector3f1 << 2) + paramInt1)]);
/*  655:     */        }
/*  656:     */      }
/*  657:     */      
/*  662: 662 */      for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++)
/*  663:     */      {
/*  664: 664 */        VectorUtil.setCoord(this.ua, paramVector3f1, paramArrayOfFloat1[((this.cb.code - 7) / 3 + (paramVector3f1 << 2))]);
/*  665:     */      }
/*  666: 666 */      for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++)
/*  667:     */      {
/*  668: 668 */        VectorUtil.setCoord(this.ub, paramVector3f1, paramArrayOfFloat2[((this.cb.code - 7) % 3 + (paramVector3f1 << 2))]);
/*  669:     */      }
/*  670:     */      
/*  671: 671 */      DLineClosestApproach(this.pa, this.ua, this.pb, this.ub, this.alphaBeta, this.pTmp);
/*  672:     */      
/*  673: 673 */      paramInt1 = this.alphaBeta.x;
/*  674: 674 */      paramInt3 = this.alphaBeta.y;
/*  675:     */      
/*  676: 676 */      for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++)
/*  677:     */      {
/*  678: 678 */        paramArrayOfFloat1 = VectorUtil.getCoord(this.pa, paramVector3f1) + VectorUtil.getCoord(this.ua, paramVector3f1) * paramInt1;
/*  679: 679 */        VectorUtil.setCoord(this.pa, paramVector3f1, paramArrayOfFloat1);
/*  680:     */      }
/*  681: 681 */      for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++)
/*  682:     */      {
/*  683: 683 */        paramArrayOfFloat1 = VectorUtil.getCoord(this.pb, paramVector3f1) + VectorUtil.getCoord(this.ub, paramVector3f1) * paramInt3;
/*  684: 684 */        VectorUtil.setCoord(this.pb, paramVector3f1, paramArrayOfFloat1);
/*  685:     */      }
/*  686:     */      
/*  697: 697 */      this.negNormal.set(paramVector3f5);
/*  698: 698 */      this.negNormal.negate();
/*  699: 699 */      paramManifoldResult.addContactPoint(this.negNormal, this.pb, -paramFloat.floatValue());
/*  700: 700 */      this.maxDepth = Math.max(paramFloat.floatValue(), this.maxDepth);
/*  701: 701 */      this.contacts += 1;
/*  702:     */      
/*  705: 705 */      return 1;
/*  706:     */    }
/*  707:     */    
/*  718: 718 */    if (this.cb.code <= 3)
/*  719:     */    {
/*  720: 720 */      paramInt1 = paramArrayOfFloat1;
/*  721: 721 */      paramInt3 = paramArrayOfFloat2;
/*  722: 722 */      this.ppa.set(paramVector3f1);
/*  723: 723 */      this.ppb.set(paramVector3f3);
/*  724: 724 */      this.Sa.set(paramVector3f2);
/*  725: 725 */      this.Sb.set(paramVector3f4);
/*  726:     */    }
/*  727:     */    else
/*  728:     */    {
/*  729: 729 */      paramInt1 = paramArrayOfFloat2;
/*  730: 730 */      paramInt3 = paramArrayOfFloat1;
/*  731: 731 */      this.ppa.set(paramVector3f3);
/*  732: 732 */      this.ppb.set(paramVector3f1);
/*  733: 733 */      this.Sa.set(paramVector3f4);
/*  734: 734 */      this.Sb.set(paramVector3f2);
/*  735:     */    }
/*  736:     */    
/*  739: 739 */    this.nr.set(0.0F, 0.0F, 0.0F);
/*  740:     */    
/*  743: 743 */    if (this.cb.code <= 3)
/*  744:     */    {
/*  745: 745 */      this.normal2.set(paramVector3f5);
/*  746:     */    }
/*  747:     */    else
/*  748:     */    {
/*  749: 749 */      this.normal2.set(paramVector3f5);
/*  750: 750 */      this.normal2.negate();
/*  751:     */    }
/*  752: 752 */    DMULTIPLY1_331(this.nr, paramInt3, this.normal2);
/*  753:     */    
/*  755: 755 */    this.anr.absolute(this.nr);
/*  756:     */    
/*  761: 761 */    if (this.anr.y > this.anr.x)
/*  762:     */    {
/*  763: 763 */      if (this.anr.y > this.anr.z)
/*  764:     */      {
/*  765: 765 */        paramArrayOfFloat1 = 0;
/*  766: 766 */        paramVector3f1 = 1;
/*  767: 767 */        paramVector3f2 = 2;
/*  768:     */      }
/*  769:     */      else
/*  770:     */      {
/*  771: 771 */        paramArrayOfFloat1 = 0;
/*  772: 772 */        paramVector3f2 = 1;
/*  773: 773 */        paramVector3f1 = 2;
/*  774:     */      }
/*  775:     */      
/*  777:     */    }
/*  778: 778 */    else if (this.anr.x > this.anr.z)
/*  779:     */    {
/*  780: 780 */      paramVector3f1 = 0;
/*  781: 781 */      paramArrayOfFloat1 = 1;
/*  782: 782 */      paramVector3f2 = 2;
/*  783:     */    }
/*  784:     */    else
/*  785:     */    {
/*  786: 786 */      paramArrayOfFloat1 = 0;
/*  787: 787 */      paramVector3f2 = 1;
/*  788: 788 */      paramVector3f1 = 2;
/*  789:     */    }
/*  790:     */    
/*  794: 794 */    if (VectorUtil.getCoord(this.nr, paramVector3f1) < 0.0F)
/*  795:     */    {
/*  796: 796 */      for (paramVector3f3 = 0; paramVector3f3 < 3; paramVector3f3++)
/*  797:     */      {
/*  798: 798 */        VectorUtil.setCoord(this.center, paramVector3f3, VectorUtil.getCoord(this.ppb, paramVector3f3) - VectorUtil.getCoord(this.ppa, paramVector3f3) + VectorUtil.getCoord(this.Sb, paramVector3f1) * paramInt3[((paramVector3f3 << 2) + paramVector3f1)]);
/*  800:     */      }
/*  801:     */      
/*  802:     */    }
/*  803:     */    else
/*  804:     */    {
/*  805: 805 */      for (paramVector3f3 = 0; paramVector3f3 < 3; paramVector3f3++)
/*  806:     */      {
/*  807: 807 */        VectorUtil.setCoord(this.center, paramVector3f3, VectorUtil.getCoord(this.ppb, paramVector3f3) - VectorUtil.getCoord(this.ppa, paramVector3f3) - VectorUtil.getCoord(this.Sb, paramVector3f1) * paramInt3[((paramVector3f3 << 2) + paramVector3f1)]);
/*  808:     */      }
/*  809:     */    }
/*  810:     */    
/*  816: 816 */    if (this.cb.code <= 3)
/*  817:     */    {
/*  818: 818 */      paramVector3f3 = this.cb.code - 1;
/*  819:     */    }
/*  820:     */    else
/*  821:     */    {
/*  822: 822 */      paramVector3f3 = this.cb.code - 4;
/*  823:     */    }
/*  824: 824 */    if (paramVector3f3 == 0)
/*  825:     */    {
/*  826: 826 */      paramVector3f1 = 1;
/*  827: 827 */      paramArrayOfFloat2 = 2;
/*  828:     */    }
/*  829: 829 */    else if (paramVector3f3 == 1)
/*  830:     */    {
/*  831: 831 */      paramVector3f1 = 0;
/*  832: 832 */      paramArrayOfFloat2 = 2;
/*  833:     */    }
/*  834:     */    else
/*  835:     */    {
/*  836: 836 */      paramVector3f1 = 0;
/*  837: 837 */      paramArrayOfFloat2 = 1;
/*  838:     */    }
/*  839:     */    
/*  841: 841 */    paramVector3f4 = this.s_quad;
/*  842:     */    
/*  844: 844 */    paramFloat = DDOT14(this.center, 0, paramInt1, paramVector3f1);
/*  845: 845 */    f1 = DDOT14(this.center, 0, paramInt1, paramArrayOfFloat2);
/*  846:     */    
/*  850: 850 */    f2 = DDOT44(paramInt1, paramVector3f1, paramInt3, paramArrayOfFloat1);
/*  851: 851 */    f3 = DDOT44(paramInt1, paramVector3f1, paramInt3, paramVector3f2);
/*  852: 852 */    f4 = DDOT44(paramInt1, paramArrayOfFloat2, paramInt3, paramArrayOfFloat1);
/*  853: 853 */    paramInt1 = DDOT44(paramInt1, paramArrayOfFloat2, paramInt3, paramVector3f2);
/*  854:     */    
/*  855: 855 */    f5 = f2 * VectorUtil.getCoord(this.Sb, paramArrayOfFloat1);
/*  856: 856 */    f6 = f4 * VectorUtil.getCoord(this.Sb, paramArrayOfFloat1);
/*  857: 857 */    f7 = f3 * VectorUtil.getCoord(this.Sb, paramVector3f2);
/*  858: 858 */    f8 = paramInt1 * VectorUtil.getCoord(this.Sb, paramVector3f2);
/*  859: 859 */    paramVector3f4[0] = (paramFloat - f5 - f7);
/*  860: 860 */    paramVector3f4[1] = (f1 - f6 - f8);
/*  861: 861 */    paramVector3f4[2] = (paramFloat - f5 + f7);
/*  862: 862 */    paramVector3f4[3] = (f1 - f6 + f8);
/*  863: 863 */    paramVector3f4[4] = (paramFloat + f5 + f7);
/*  864: 864 */    paramVector3f4[5] = (f1 + f6 + f8);
/*  865: 865 */    paramVector3f4[6] = (paramFloat + f5 - f7);
/*  866: 866 */    paramVector3f4[7] = (f1 + f6 - f8);
/*  867:     */    
/*  871: 871 */    this.s_rectReferenceFace[0] = VectorUtil.getCoord(this.Sa, paramVector3f1);
/*  872: 872 */    this.s_rectReferenceFace[1] = VectorUtil.getCoord(this.Sa, paramArrayOfFloat2);
/*  873:     */    
/*  875: 875 */    float[] arrayOfFloat1 = this.s_ret;
/*  876:     */    float[] arrayOfFloat2;
/*  877: 877 */    if ((arrayOfFloat2 = IntersectRectQuad2(this.s_rectReferenceFace, paramVector3f4, arrayOfFloat1)) <= 0)
/*  878:     */    {
/*  879: 879 */      return 0;
/*  880:     */    }
/*  881:     */    
/*  886: 886 */    float[] arrayOfFloat3 = this.s_point;
/*  887: 887 */    float[] arrayOfFloat4 = this.s_dep;
/*  888: 888 */    paramVector3f1 = 1.0F / (f2 * paramInt1 - f3 * f4);
/*  889: 889 */    f2 *= paramVector3f1;
/*  890: 890 */    f3 *= paramVector3f1;
/*  891: 891 */    f4 *= paramVector3f1;
/*  892: 892 */    paramInt1 *= paramVector3f1;
/*  893: 893 */    paramVector3f1 = 0;
/*  894: 894 */    int j; for (paramArrayOfFloat2 = 0; paramArrayOfFloat2 < arrayOfFloat2; paramArrayOfFloat2++)
/*  895:     */    {
/*  896: 896 */      paramVector3f4 = paramInt1 * (arrayOfFloat1[(paramArrayOfFloat2 << 1)] - paramFloat) - f3 * (arrayOfFloat1[((paramArrayOfFloat2 << 1) + 1)] - f1);
/*  897: 897 */      f9 = -f4 * (arrayOfFloat1[(paramArrayOfFloat2 << 1)] - paramFloat) + f2 * (arrayOfFloat1[((paramArrayOfFloat2 << 1) + 1)] - f1);
/*  898: 898 */      for (j = 0; j < 3; j++)
/*  899:     */      {
/*  900: 900 */        arrayOfFloat3[(paramVector3f1 * 3 + j)] = (VectorUtil.getCoord(this.center, j) + paramVector3f4 * paramInt3[((j << 2) + paramArrayOfFloat1)] + f9 * paramInt3[((j << 2) + paramVector3f2)]);
/*  901:     */      }
/*  902: 902 */      arrayOfFloat4[paramVector3f1] = (VectorUtil.getCoord(this.Sa, paramVector3f3) - DDOT(this.normal2, 0, arrayOfFloat3, paramVector3f1 * 3));
/*  903: 903 */      if (arrayOfFloat4[paramVector3f1] >= 0.0F)
/*  904:     */      {
/*  905: 905 */        arrayOfFloat1[(paramVector3f1 << 1)] = arrayOfFloat1[(paramArrayOfFloat2 << 1)];
/*  906: 906 */        arrayOfFloat1[((paramVector3f1 << 1) + 1)] = arrayOfFloat1[((paramArrayOfFloat2 << 1) + 1)];
/*  907: 907 */        paramVector3f1++;
/*  908:     */      }
/*  909:     */    }
/*  910: 910 */    if (paramVector3f1 <= 0)
/*  911:     */    {
/*  912: 912 */      return 0;
/*  913:     */    }
/*  914:     */    
/*  916: 916 */    if (paramInt2 > paramVector3f1)
/*  917:     */    {
/*  918: 918 */      paramInt2 = paramVector3f1;
/*  919:     */    }
/*  920: 920 */    if (paramInt2 <= 0)
/*  921:     */    {
/*  922: 922 */      paramInt2 = 1;
/*  923:     */    }
/*  924:     */    
/*  925: 925 */    if (paramVector3f1 <= paramInt2)
/*  926:     */    {
/*  927: 927 */      if (this.cb.code < 4)
/*  928:     */      {
/*  930: 930 */        for (paramArrayOfFloat2 = 0; paramArrayOfFloat2 < paramVector3f1; paramArrayOfFloat2++)
/*  931:     */        {
/*  933: 933 */          for (paramVector3f4 = 0; paramVector3f4 < 3; paramVector3f4++)
/*  934:     */          {
/*  935: 935 */            VectorUtil.setCoord(this.pointInWorldFAA, paramVector3f4, arrayOfFloat3[(paramArrayOfFloat2 * 3 + paramVector3f4)] + VectorUtil.getCoord(this.ppa, paramVector3f4));
/*  936:     */          }
/*  937:     */          
/*  941: 941 */          this.negNormal.set(paramVector3f5);
/*  942: 942 */          this.negNormal.negate();
/*  943: 943 */          paramManifoldResult.addContactPoint(this.negNormal, this.pointInWorldFAA, -arrayOfFloat4[paramArrayOfFloat2]);
/*  944: 944 */          this.maxDepth = Math.max(arrayOfFloat4[paramArrayOfFloat2], this.maxDepth);
/*  945: 945 */          this.contacts += 1;
/*  946:     */        }
/*  947:     */        
/*  948:     */      }
/*  949:     */      else
/*  950:     */      {
/*  951: 951 */        for (paramArrayOfFloat2 = 0; paramArrayOfFloat2 < paramVector3f1; paramArrayOfFloat2++)
/*  952:     */        {
/*  954: 954 */          for (paramVector3f4 = 0; paramVector3f4 < 3; paramVector3f4++)
/*  955:     */          {
/*  956: 956 */            VectorUtil.setCoord(this.pointInWorldRes, paramVector3f4, arrayOfFloat3[(paramArrayOfFloat2 * 3 + paramVector3f4)] + VectorUtil.getCoord(this.ppa, paramVector3f4));
/*  957:     */          }
/*  958:     */          
/*  964: 964 */          this.negNormal.set(paramVector3f5);
/*  965: 965 */          this.negNormal.negate();
/*  966: 966 */          paramManifoldResult.addContactPoint(this.negNormal, this.pointInWorldRes, -arrayOfFloat4[paramArrayOfFloat2]);
/*  967: 967 */          this.contacts += 1;
/*  968: 968 */          this.maxDepth = Math.max(arrayOfFloat4[paramArrayOfFloat2], this.maxDepth);
/*  969:     */        }
/*  970:     */        
/*  971:     */      }
/*  972:     */      
/*  973:     */    }
/*  974:     */    else
/*  975:     */    {
/*  976: 976 */      paramArrayOfFloat2 = 0;
/*  977: 977 */      paramVector3f4 = arrayOfFloat4[0];
/*  978: 978 */      for (Vector3f localVector3f = 1; localVector3f < paramVector3f1; localVector3f++)
/*  979:     */      {
/*  980: 980 */        if (arrayOfFloat4[localVector3f] > paramVector3f4)
/*  981:     */        {
/*  982: 982 */          paramVector3f4 = arrayOfFloat4[localVector3f];
/*  983: 983 */          paramArrayOfFloat2 = localVector3f;
/*  984:     */        }
/*  985:     */      }
/*  986: 986 */      Arrays.fill(this.iret, 0);
/*  987:     */      
/*  988: 988 */      CullPoints2(paramVector3f1, arrayOfFloat1, paramInt2, paramArrayOfFloat2, this.iret);
/*  989:     */      
/*  990: 990 */      for (int i = 0; i < paramInt2; i++)
/*  991:     */      {
/*  996: 996 */        for (j = 0; j < 3; j++)
/*  997:     */        {
/*  998: 998 */          VectorUtil.setCoord(this.posInWorldFA, j, arrayOfFloat3[(this.iret[i] * 3 + j)] + VectorUtil.getCoord(this.ppa, j));
/*  999:     */        }
/* 1000:     */        
/* 1006:1006 */        this.negNormal.set(paramVector3f5);
/* 1007:1007 */        this.negNormal.negate();
/* 1008:     */        
/* 1009:1009 */        if (this.cb.code < 4) {
/* 1010:1010 */          paramManifoldResult.addContactPoint(this.negNormal, this.posInWorldFA, -arrayOfFloat4[this.iret[i]]);
/* 1011:1011 */          this.contacts += 1;
/* 1012:     */        }
/* 1013:     */        else {
/* 1014:1014 */          this.scaledN.set(paramVector3f5);
/* 1015:1015 */          this.scaledN.scale(arrayOfFloat4[this.iret[i]]);
/* 1016:1016 */          this.posInWorldFA.sub(this.scaledN);
/* 1017:1017 */          paramManifoldResult.addContactPoint(this.negNormal, this.posInWorldFA, -arrayOfFloat4[this.iret[i]]);
/* 1018:1018 */          this.contacts += 1;
/* 1019:     */        }
/* 1020:     */      }
/* 1021:     */      
/* 1023:1023 */      this.maxDepth = Math.max(paramVector3f4, this.maxDepth);
/* 1024:1024 */      paramVector3f1 = paramInt2;
/* 1025:     */    }
/* 1026:     */    
/* 1027:1027 */    return paramVector3f1;
/* 1028:     */  }
/* 1029:     */  
/* 1033:     */  int IntersectRectQuad2(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3)
/* 1034:     */  {
/* 1035:1035 */    int i = 4;int j = 0;
/* 1036:     */    
/* 1037:1037 */    float[] arrayOfFloat1 = this.s_quadBuffer;
/* 1038:1038 */    float[] arrayOfFloat2 = paramArrayOfFloat3;
/* 1039:     */    
/* 1042:1042 */    for (int k = 0; k <= 1; k++)
/* 1043:     */    {
/* 1045:1045 */      for (int m = -1; m <= 1; m += 2)
/* 1046:     */      {
/* 1048:1048 */        float[] arrayOfFloat3 = paramArrayOfFloat2;
/* 1049:1049 */        float[] arrayOfFloat4 = arrayOfFloat2;
/* 1050:1050 */        int n = 0;
/* 1051:1051 */        int i1 = 0;
/* 1052:1052 */        j = 0;
/* 1053:1053 */        for (; i > 0; i--)
/* 1054:     */        {
/* 1056:1056 */          if (m * arrayOfFloat3[(n + k)] < paramArrayOfFloat1[k])
/* 1057:     */          {
/* 1059:1059 */            arrayOfFloat4[i1] = arrayOfFloat3[n];
/* 1060:1060 */            arrayOfFloat4[(i1 + 1)] = arrayOfFloat3[(n + 1)];
/* 1061:1061 */            i1 += 2;
/* 1062:1062 */            j++;
/* 1063:1063 */            if ((j & 0x8) != 0)
/* 1064:     */            {
/* 1065:1065 */              paramArrayOfFloat2 = arrayOfFloat2;
/* 1066:     */              break label356; } }
/* 1067:     */          float f1;
/* 1068:     */          float f2;
/* 1069:1069 */          if (i > 1)
/* 1070:     */          {
/* 1073:1073 */            f1 = arrayOfFloat3[(n + 2 + k)];
/* 1074:1074 */            f2 = arrayOfFloat3[(n + 2 + (1 - k))];
/* 1075:     */          }
/* 1076:     */          else
/* 1077:     */          {
/* 1078:1078 */            f1 = paramArrayOfFloat2[k];
/* 1079:1079 */            f2 = paramArrayOfFloat2[(1 - k)];
/* 1080:     */          }
/* 1081:     */          
/* 1082:1082 */          if (((m * arrayOfFloat3[(n + k)] < paramArrayOfFloat1[k] ? 1 : 0) ^ (m * f1 < paramArrayOfFloat1[k] ? 1 : 0)) != 0)
/* 1083:     */          {
/* 1085:1085 */            arrayOfFloat3[(n + (1 - k))] += (f2 - arrayOfFloat3[(n + (1 - k))]) / (f1 - arrayOfFloat3[(n + k)]) * (m * paramArrayOfFloat1[k] - arrayOfFloat3[(n + k)]);
/* 1086:     */            
/* 1087:1087 */            arrayOfFloat4[(i1 + k)] = (m * paramArrayOfFloat1[k]);
/* 1088:1088 */            i1 += 2;
/* 1089:1089 */            j++;
/* 1090:1090 */            if ((j & 0x8) != 0)
/* 1091:     */            {
/* 1092:1092 */              paramArrayOfFloat2 = arrayOfFloat2;
/* 1093:     */              break label356;
/* 1094:     */            }
/* 1095:     */          }
/* 1096:1096 */          n += 2;
/* 1097:     */        }
/* 1098:     */        
/* 1099:1099 */        arrayOfFloat2 = (paramArrayOfFloat2 = arrayOfFloat2) == paramArrayOfFloat3 ? arrayOfFloat1 : paramArrayOfFloat3;
/* 1100:1100 */        i = j;
/* 1101:     */      }
/* 1102:     */    }
/* 1103:     */    label356:
/* 1104:1104 */    if (paramArrayOfFloat2 != paramArrayOfFloat3)
/* 1105:     */    {
/* 1106:1106 */      for (k = 0; k < j << 1; k++)
/* 1107:     */      {
/* 1108:1108 */        paramArrayOfFloat3[k] = paramArrayOfFloat2[k];
/* 1109:     */      }
/* 1110:     */    }
/* 1111:1111 */    return j;
/* 1112:     */  }
/* 1113:     */  
/* 1114:     */  private boolean TST(float paramFloat1, float paramFloat2, float[] paramArrayOfFloat, int paramInt1, int paramInt2, BoxBoxDetector.CB paramCB)
/* 1115:     */  {
/* 1116:1116 */    if ((paramFloat2 = Math.abs(paramFloat1) - paramFloat2) > 0.0F)
/* 1117:     */    {
/* 1118:1118 */      return true;
/* 1119:     */    }
/* 1120:1120 */    if (paramFloat2 > paramCB.s)
/* 1121:     */    {
/* 1122:1122 */      paramCB.s = paramFloat2;
/* 1123:1123 */      paramCB.normalR = paramArrayOfFloat;
/* 1124:     */      
/* 1128:1128 */      paramCB.normalROffset = paramInt1;
/* 1129:1129 */      paramCB.invert_normal = (paramFloat1 < 0.0F);
/* 1130:1130 */      paramCB.code = paramInt2;
/* 1131:     */    }
/* 1132:1132 */    return false;
/* 1133:     */  }
/* 1134:     */  
/* 1137:     */  private boolean TST2(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, Vector3f paramVector3f, int paramInt, BoxBoxDetector.CB paramCB)
/* 1138:     */  {
/* 1139:1139 */    if ((paramFloat2 = Math.abs(paramFloat1) - paramFloat2) > 1.192093E-007F)
/* 1140:     */    {
/* 1141:1141 */      return true;
/* 1142:     */    }
/* 1143:     */    float f;
/* 1144:1144 */    if ((f = (float)Math.sqrt(paramFloat3 * paramFloat3 + paramFloat4 * paramFloat4 + paramFloat5 * paramFloat5)) > 1.192093E-007F)
/* 1145:     */    {
/* 1147:1147 */      if ((paramFloat2 = paramFloat2 / f) * this.fudge_factor > paramCB.s)
/* 1148:     */      {
/* 1149:1149 */        paramCB.s = paramFloat2;
/* 1150:1150 */        paramCB.normalR = null;
/* 1151:1151 */        paramVector3f.x = (paramFloat3 / f);paramVector3f.y = (paramFloat4 / f);paramVector3f.z = (paramFloat5 / f);
/* 1152:1152 */        paramCB.invert_normal = (paramFloat1 < 0.0F);
/* 1153:1153 */        paramCB.code = paramInt;
/* 1154:     */      }
/* 1155:     */    }
/* 1156:1156 */    return false;
/* 1157:     */  }
/* 1158:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.BoxBoxDetector
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */