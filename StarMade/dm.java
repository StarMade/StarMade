/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   4:    */import it.unimi.dsi.fastutil.objects.ObjectHeapPriorityQueue;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*   6:    */import java.util.Iterator;
/*   7:    */import java.util.List;
/*   8:    */import javax.vecmath.Matrix3f;
/*   9:    */import javax.vecmath.Tuple3f;
/*  10:    */import javax.vecmath.Tuple4f;
/*  11:    */import javax.vecmath.Vector3f;
/*  12:    */import javax.vecmath.Vector4f;
/*  13:    */import org.lwjgl.opengl.GL11;
/*  14:    */import org.schema.common.FastMath;
/*  15:    */import org.schema.game.common.controller.SegmentController;
/*  16:    */import org.schema.game.common.data.element.BeamHandler;
/*  17:    */import org.schema.game.common.data.element.BeamHandler.BeamState;
/*  18:    */import org.schema.game.common.data.world.Segment;
/*  19:    */import org.schema.schine.graphicsengine.camera.Camera;
/*  20:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  21:    */import org.schema.schine.graphicsengine.forms.Mesh;
/*  22:    */
/*  37:    */public class dm
/*  38:    */  implements xg
/*  39:    */{
/*  40:    */  private List jdField_a_of_type_JavaUtilList;
/*  41:    */  private static final Vector4f jdField_a_of_type_JavaxVecmathVector4f;
/*  42:    */  private Vector4f jdField_b_of_type_JavaxVecmathVector4f;
/*  43:    */  private static final Vector4f jdField_c_of_type_JavaxVecmathVector4f;
/*  44:    */  private static final Vector4f jdField_d_of_type_JavaxVecmathVector4f;
/*  45:    */  private static final Vector4f jdField_e_of_type_JavaxVecmathVector4f;
/*  46:    */  private static final Vector4f jdField_f_of_type_JavaxVecmathVector4f;
/*  47:    */  private static final Vector4f jdField_g_of_type_JavaxVecmathVector4f;
/*  48:    */  private static final Vector4f jdField_h_of_type_JavaxVecmathVector4f;
/*  49:    */  private static final Vector4f jdField_i_of_type_JavaxVecmathVector4f;
/*  50:    */  private static final Vector4f jdField_j_of_type_JavaxVecmathVector4f;
/*  51:    */  private static Vector3f jdField_a_of_type_JavaxVecmathVector3f;
/*  52:    */  private static Vector3f jdField_b_of_type_JavaxVecmathVector3f;
/*  53:    */  private static Vector3f jdField_c_of_type_JavaxVecmathVector3f;
/*  54: 54 */  private ObjectOpenHashSet jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet = new ObjectOpenHashSet();
/*  55:    */  
/*  57: 57 */  private final zI jdField_a_of_type_ZI = new zI();
/*  58:    */  
/*  59:    */  private static final Vector3f jdField_d_of_type_JavaxVecmathVector3f;
/*  60:    */  
/*  61:    */  private static final Vector3f jdField_e_of_type_JavaxVecmathVector3f;
/*  62:    */  
/*  63: 63 */  private final Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*  64:    */  
/*  65: 65 */  private final q jdField_a_of_type_Q = new q();
/*  66:    */  
/*  67:    */  private static final Vector3f jdField_f_of_type_JavaxVecmathVector3f;
/*  68:    */  
/*  69:    */  private static final Vector3f jdField_g_of_type_JavaxVecmathVector3f;
/*  70:    */  
/*  71:    */  private static final Vector3f jdField_h_of_type_JavaxVecmathVector3f;
/*  72:    */  
/*  73:    */  private boolean jdField_a_of_type_Boolean;
/*  74:    */  
/*  75:    */  private final do jdField_a_of_type_Do;
/*  76:    */  private static Vector3f jdField_i_of_type_JavaxVecmathVector3f;
/*  77:    */  private static float jdField_a_of_type_Float;
/*  78:    */  private static Vector3f jdField_j_of_type_JavaxVecmathVector3f;
/*  79:    */  
/*  80:    */  public dm(do paramdo, List paramList, dl paramdl)
/*  81:    */  {
/*  82: 82 */    this.jdField_a_of_type_Do = paramdo;
/*  83: 83 */    a(paramList, paramdl);
/*  84:    */  }
/*  85:    */  
/*  90:    */  public final void d()
/*  91:    */  {
/*  92: 92 */    for (Iterator localIterator = this.jdField_a_of_type_JavaUtilList.iterator(); localIterator.hasNext();)
/*  93:    */    {
/*  94: 94 */      ((jq)localIterator.next()).getHandler().setDrawer(null);
/*  95:    */    }
/*  96: 96 */    this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.clear();
/*  97:    */  }
/*  98:    */  
/* 101:    */  public final void b()
/* 102:    */  {
/* 103:103 */    if (this.jdField_a_of_type_JavaUtilList == null) {
/* 104:    */      try {
/* 105:105 */        throw new NullPointerException("[CLIENT][ERROR] ########## beam handlers null of beam drawer " + this);
/* 106:106 */      } catch (NullPointerException localNullPointerException) { localNullPointerException.printStackTrace();
/* 107:    */        
/* 109:109 */        return;
/* 110:    */      }
/* 111:    */    }
/* 112:    */    
/* 117:117 */    if (this.jdField_a_of_type_Boolean) {
/* 118:118 */      for (BeamHandler localBeamHandler : this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet) {
/* 119:119 */        a(localBeamHandler);
/* 120:    */      }
/* 121:    */    }
/* 122:    */  }
/* 123:    */  
/* 127:    */  private void a(BeamHandler paramBeamHandler)
/* 128:    */  {
/* 129:129 */    for (BeamHandler.BeamState localBeamState : paramBeamHandler.getBeamStates().values())
/* 130:    */    {
/* 131:131 */      if ((!jdField_b_of_type_Boolean) && (localBeamState == null)) throw new AssertionError();
/* 132:132 */      localBeamState.color.set(this.jdField_b_of_type_JavaxVecmathVector4f);
/* 133:133 */      a(localBeamState, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 134:134 */      do.jdField_a_of_type_Int += 1;
/* 135:    */    }
/* 136:    */  }
/* 137:    */  
/* 138:    */  static
/* 139:    */  {
/* 140: 39 */    jdField_a_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 141:    */    
/* 142: 41 */    jdField_c_of_type_JavaxVecmathVector4f = new Vector4f(0.0F, 0.0F, 1.0F, 1.0F);
/* 143: 42 */    jdField_d_of_type_JavaxVecmathVector4f = new Vector4f(0.0F, 1.0F, 0.0F, 1.0F);
/* 144: 43 */    jdField_e_of_type_JavaxVecmathVector4f = new Vector4f(0.7F, 0.0F, 0.0F, 1.0F);
/* 145: 44 */    jdField_f_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 1.0F, 0.0F, 1.0F);
/* 146: 45 */    jdField_g_of_type_JavaxVecmathVector4f = new Vector4f(0.7F, 0.7F, 0.7F, 1.0F);
/* 147: 46 */    jdField_h_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 0.3F, 1.0F, 1.0F);
/* 148: 47 */    jdField_i_of_type_JavaxVecmathVector4f = new Vector4f();
/* 149: 48 */    jdField_j_of_type_JavaxVecmathVector4f = new Vector4f();
/* 150:    */    
/* 151: 50 */    jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 152: 51 */    jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/* 153: 52 */    jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/* 154:    */    
/* 160: 59 */    jdField_d_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 0.0F, 1.0F);
/* 161:    */    
/* 162: 61 */    jdField_e_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 0.0F, 1.0F);
/* 163:    */    
/* 170: 69 */    jdField_f_of_type_JavaxVecmathVector3f = new Vector3f();
/* 171:    */    
/* 172: 71 */    jdField_g_of_type_JavaxVecmathVector3f = new Vector3f();
/* 173:    */    
/* 174: 73 */    jdField_h_of_type_JavaxVecmathVector3f = new Vector3f();
/* 175:    */    
/* 282:181 */    jdField_i_of_type_JavaxVecmathVector3f = new Vector3f();
/* 283:182 */    new Vector3f(-8.0F, -8.0F, -8.0F);
/* 284:    */    
/* 288:187 */    jdField_j_of_type_JavaxVecmathVector3f = new Vector3f();
/* 289:188 */    new Vector3f(); }
/* 290:    */  
/* 291:    */  public final void a(ObjectHeapPriorityQueue paramObjectHeapPriorityQueue) { Iterator localIterator1;
/* 292:191 */    if (this.jdField_a_of_type_Boolean) {
/* 293:192 */      for (localIterator1 = this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.iterator(); localIterator1.hasNext();) {
/* 294:193 */        for (localIterator2 = ((BeamHandler)localIterator1.next()).getBeamStates().values().iterator(); localIterator2.hasNext();) { BeamHandler.BeamState localBeamState;
/* 295:194 */          (localBeamState = (BeamHandler.BeamState)localIterator2.next()).color.set(this.jdField_b_of_type_JavaxVecmathVector4f);
/* 296:    */          
/* 304:203 */          jdField_j_of_type_JavaxVecmathVector3f.sub(xe.a().a(), localBeamState.from);
/* 305:204 */          localBeamState.camDistStart = jdField_j_of_type_JavaxVecmathVector3f.lengthSquared();
/* 306:    */          
/* 308:207 */          paramObjectHeapPriorityQueue.enqueue(localBeamState);
/* 309:    */        }
/* 310:    */      }
/* 311:    */    }
/* 312:    */    
/* 324:    */    Iterator localIterator2;
/* 325:    */  }
/* 326:    */  
/* 338:    */  public static void a(BeamHandler.BeamState paramBeamState, Transform paramTransform)
/* 339:    */  {
/* 340:239 */    jdField_a_of_type_JavaxVecmathVector3f.set(paramBeamState.from);
/* 341:    */    
/* 342:241 */    if (paramBeamState.hitPoint != null) {
/* 343:242 */      jdField_b_of_type_JavaxVecmathVector3f.set(paramBeamState.hitPoint);
/* 344:    */    } else {
/* 345:244 */      jdField_b_of_type_JavaxVecmathVector3f.set(paramBeamState.to);
/* 346:    */    }
/* 347:    */    
/* 348:247 */    if (!GlUtil.a(jdField_a_of_type_JavaxVecmathVector3f, jdField_b_of_type_JavaxVecmathVector3f, xe.a.a())) {
/* 349:248 */      return;
/* 350:    */    }
/* 351:    */    
/* 352:251 */    jdField_c_of_type_JavaxVecmathVector3f.sub(jdField_a_of_type_JavaxVecmathVector3f, jdField_b_of_type_JavaxVecmathVector3f);
/* 353:252 */    float f1 = jdField_c_of_type_JavaxVecmathVector3f.length();
/* 354:253 */    jdField_d_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 1.0F);
/* 355:254 */    paramTransform.origin.set(jdField_a_of_type_JavaxVecmathVector3f);
/* 356:    */    
/* 358:257 */    jdField_i_of_type_JavaxVecmathVector3f.cross(jdField_c_of_type_JavaxVecmathVector3f, jdField_d_of_type_JavaxVecmathVector3f);
/* 359:258 */    jdField_i_of_type_JavaxVecmathVector3f.normalize();
/* 360:259 */    GlUtil.a(jdField_c_of_type_JavaxVecmathVector3f, paramTransform);
/* 361:260 */    GlUtil.d(jdField_i_of_type_JavaxVecmathVector3f, paramTransform);
/* 362:261 */    jdField_d_of_type_JavaxVecmathVector3f.cross(jdField_c_of_type_JavaxVecmathVector3f, jdField_i_of_type_JavaxVecmathVector3f);
/* 363:262 */    jdField_d_of_type_JavaxVecmathVector3f.normalize();
/* 364:263 */    GlUtil.c(jdField_d_of_type_JavaxVecmathVector3f, paramTransform);
/* 365:    */    
/* 368:267 */    Matrix3f localMatrix3f = paramTransform.basis;Vector3f localVector3f2 = xe.a().a();Object localObject = jdField_b_of_type_JavaxVecmathVector3f;Vector3f localVector3f1 = jdField_a_of_type_JavaxVecmathVector3f;jdField_f_of_type_JavaxVecmathVector3f.sub(localVector3f2, localVector3f1);jdField_g_of_type_JavaxVecmathVector3f.sub((Tuple3f)localObject, localVector3f1);jdField_h_of_type_JavaxVecmathVector3f.cross(jdField_f_of_type_JavaxVecmathVector3f, jdField_g_of_type_JavaxVecmathVector3f);jdField_h_of_type_JavaxVecmathVector3f.normalize();jdField_e_of_type_JavaxVecmathVector3f.set(0.0F, 1.0F, 0.0F);localMatrix3f.transform(jdField_e_of_type_JavaxVecmathVector3f);float f3 = (float)FastMath.a(jdField_e_of_type_JavaxVecmathVector3f.dot(jdField_h_of_type_JavaxVecmathVector3f));jdField_e_of_type_JavaxVecmathVector3f.set(1.0F, 0.0F, 0.0F);localMatrix3f.transform(jdField_e_of_type_JavaxVecmathVector3f);float f2 = (float)FastMath.a(jdField_e_of_type_JavaxVecmathVector3f.dot(jdField_h_of_type_JavaxVecmathVector3f)); if (1.570796F > f2) f3 = 6.283186F - f3; f2 = f3;
/* 369:    */    
/* 370:269 */    localObject = paramBeamState.color; if (!jdField_i_of_type_JavaxVecmathVector4f.equals(jdField_a_of_type_JavaxVecmathVector4f)) { jdField_i_of_type_JavaxVecmathVector4f.set(jdField_a_of_type_JavaxVecmathVector4f);GlUtil.a(do.jdField_a_of_type_Zj, "thrustColor0", jdField_i_of_type_JavaxVecmathVector4f); } if (!jdField_j_of_type_JavaxVecmathVector4f.equals((Tuple4f)localObject)) { jdField_j_of_type_JavaxVecmathVector4f.set((Tuple4f)localObject);GlUtil.a(do.jdField_a_of_type_Zj, "thrustColor1", jdField_j_of_type_JavaxVecmathVector4f); } float f4; if (Float.compare(f4 = f1 / 20.0F, jdField_a_of_type_Float) != 0) { GlUtil.a(do.jdField_a_of_type_Zj, "texCoordMult", f4);jdField_a_of_type_Float = f4;
/* 371:    */    }
/* 372:271 */    GlUtil.d();
/* 373:    */    
/* 376:275 */    GlUtil.b(paramTransform);
/* 377:    */    
/* 379:278 */    GlUtil.a(57.295776F * (f2 + 1.570796F));
/* 380:    */    
/* 384:283 */    do.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh.i();
/* 385:    */    
/* 386:285 */    GlUtil.c();
/* 387:    */  }
/* 388:    */  
/* 389:    */  public final void e()
/* 390:    */  {
/* 391:290 */    for (Iterator localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.iterator(); localIterator.hasNext();) { localObject1 = (BeamHandler)localIterator.next();
/* 392:291 */      localObject2 = localObject1;localObject1 = this; if (((BeamHandler)localObject2).getSegmentController().isClientOwnObject()) { GlUtil.a(zk.u, "selectionColor", 0.7F, 0.77F, 0.1F, 1.0F); for (localObject2 = ((BeamHandler)localObject2).getBeamStates().values().iterator(); ((Iterator)localObject2).hasNext();) { Object localObject3 = (BeamHandler.BeamState)((Iterator)localObject2).next(); if ((!jdField_b_of_type_Boolean) && (localObject3 == null)) throw new AssertionError(); if (((BeamHandler.BeamState)localObject3).segmentHit != null) { ((BeamHandler.BeamState)localObject3).segmentHit.a(); if (((BeamHandler.BeamState)localObject3).segmentHit.a() != 0) { zI localzI = ((dm)localObject1).jdField_a_of_type_ZI;SegmentController localSegmentController = ((BeamHandler.BeamState)localObject3).segmentHit.a().a();Object localObject4 = ((BeamHandler.BeamState)localObject3).segmentHit.a(((dm)localObject1).jdField_a_of_type_Q);(localObject3 = localObject1).jdField_a_of_type_ComBulletphysicsLinearmathTransform.set(localSegmentController.getWorldTransformClient());localObject4 = new Vector3f(((q)localObject4).jdField_a_of_type_Int - 8, ((q)localObject4).b - 8, ((q)localObject4).c - 8);((dm)localObject3).jdField_a_of_type_ComBulletphysicsLinearmathTransform.basis.transform((Tuple3f)localObject4);((dm)localObject3).jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin.add((Tuple3f)localObject4);GlUtil.d();GlUtil.b(((dm)localObject3).jdField_a_of_type_ComBulletphysicsLinearmathTransform); float tmp267_266 = (1.05F + localzI.a() * 0.05F); float tmp268_267 = tmp267_266;GL11.glScalef(tmp267_266, tmp268_267, tmp268_267);do.b.i();GlUtil.c();
/* 393:    */            }
/* 394:    */          }
/* 395:    */        }
/* 396:    */      }
/* 397:    */    }
/* 398:    */    
/* 419:    */    Object localObject1;
/* 420:    */    
/* 441:    */    Object localObject2;
/* 442:    */  }
/* 443:    */  
/* 465:    */  public final void f()
/* 466:    */  {
/* 467:366 */    if (this.jdField_a_of_type_JavaUtilList != null) {
/* 468:367 */      d();
/* 469:    */    }
/* 470:369 */    this.jdField_a_of_type_JavaUtilList = null;
/* 471:    */  }
/* 472:    */  
/* 473:    */  public final void a(List paramList, dl paramdl) {
/* 474:373 */    Object localObject = paramdl;paramdl = this; switch (dn.a[localObject.ordinal()]) {case 1:  paramdl.jdField_b_of_type_JavaxVecmathVector4f = jdField_c_of_type_JavaxVecmathVector4f;break; case 2:  paramdl.jdField_b_of_type_JavaxVecmathVector4f = jdField_d_of_type_JavaxVecmathVector4f;break; case 3:  paramdl.jdField_b_of_type_JavaxVecmathVector4f = jdField_e_of_type_JavaxVecmathVector4f;break; case 4:  paramdl.jdField_b_of_type_JavaxVecmathVector4f = jdField_h_of_type_JavaxVecmathVector4f;break; case 5:  paramdl.jdField_b_of_type_JavaxVecmathVector4f = jdField_f_of_type_JavaxVecmathVector4f;break; case 6:  paramdl.jdField_b_of_type_JavaxVecmathVector4f = jdField_g_of_type_JavaxVecmathVector4f;break; default:  paramdl.jdField_b_of_type_JavaxVecmathVector4f = jdField_d_of_type_JavaxVecmathVector4f; }
/* 475:374 */    this.jdField_a_of_type_JavaUtilList = paramList;
/* 476:375 */    if (this.jdField_a_of_type_JavaUtilList != null) {
/* 477:376 */      paramdl = this;d(); for (localObject = paramdl.jdField_a_of_type_JavaUtilList.iterator(); ((Iterator)localObject).hasNext(); ((jq)((Iterator)localObject).next()).getHandler().setDrawer(paramdl)) {} return;
/* 478:    */    }
/* 479:378 */    d();
/* 480:    */  }
/* 481:    */  
/* 499:    */  public final void a(xq paramxq)
/* 500:    */  {
/* 501:400 */    this.jdField_a_of_type_ZI.a(paramxq);
/* 502:    */  }
/* 503:    */  
/* 534:    */  public final void a(BeamHandler paramBeamHandler, boolean paramBoolean)
/* 535:    */  {
/* 536:435 */    if (paramBoolean) {
/* 537:436 */      this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(paramBeamHandler);
/* 538:    */    } else {
/* 539:438 */      this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.remove(paramBeamHandler);
/* 540:    */    }
/* 541:    */    
/* 542:441 */    if (this.jdField_a_of_type_Boolean != (!this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.isEmpty())) {
/* 543:442 */      this.jdField_a_of_type_Do.a(this, !this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.isEmpty());
/* 544:    */    }
/* 545:    */    
/* 546:445 */    this.jdField_a_of_type_Boolean = (!this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.isEmpty());
/* 547:    */  }
/* 548:    */  
/* 549:    */  public final void a() {}
/* 550:    */  
/* 551:    */  public final void c() {}
/* 552:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */