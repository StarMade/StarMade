/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
/*   3:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   4:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   5:    */import java.nio.FloatBuffer;
/*   6:    */import java.util.ArrayList;
/*   7:    */import java.util.HashSet;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.List;
/*  10:    */import javax.vecmath.Matrix3f;
/*  11:    */import javax.vecmath.Tuple3f;
/*  12:    */import javax.vecmath.Vector3f;
/*  13:    */import javax.vecmath.Vector4f;
/*  14:    */import org.lwjgl.BufferUtils;
/*  15:    */import org.lwjgl.opengl.Display;
/*  16:    */import org.lwjgl.opengl.DisplayMode;
/*  17:    */import org.lwjgl.opengl.GL11;
/*  18:    */import org.lwjgl.util.vector.Matrix4f;
/*  19:    */import org.newdawn.slick.Color;
/*  20:    */import org.schema.common.FastMath;
/*  21:    */import org.schema.game.common.data.world.Universe;
/*  22:    */import org.schema.schine.graphicsengine.camera.Camera;
/*  23:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  24:    */import org.schema.schine.network.client.ClientState;
/*  25:    */
/*  54:    */public class ij
/*  55:    */  extends yz
/*  56:    */{
/*  57:    */  public static ArrayList a;
/*  58:    */  public static HashSet a;
/*  59:    */  private yE jdField_a_of_type_YE;
/*  60:    */  private yE jdField_b_of_type_YE;
/*  61:    */  private yP jdField_a_of_type_YP;
/*  62:    */  
/*  63:    */  public static void a(mF parammF, Vector4f paramVector4f, boolean paramBoolean, ct paramct)
/*  64:    */  {
/*  65: 65 */    if (paramBoolean) {
/*  66: 66 */      paramVector4f.x = (0.3F + jdField_a_of_type_Float);
/*  67: 67 */      paramVector4f.y = (0.3F + jdField_a_of_type_Float);
/*  68: 68 */      paramVector4f.z = (0.3F + jdField_a_of_type_Float);
/*  69: 69 */      return;
/*  70:    */    }
/*  71: 71 */    paramBoolean = paramBoolean ? jdField_a_of_type_Float : 0.0F;
/*  72:    */    
/*  77: 77 */    if ((parammF instanceof cw))
/*  78:    */    {
/*  79: 79 */      ((cw)parammF).a();
/*  80:    */    }
/*  81: 81 */    paramct = paramct.a().a(parammF.getFactionId());
/*  82:    */    
/*  85: 85 */    parammF.getRelationColor(paramct, paramVector4f, paramBoolean);
/*  86:    */  }
/*  87:    */  
/*  95: 95 */  private Int2IntArrayMap jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap = new Int2IntArrayMap();
/*  96:    */  
/*  97:    */  private zG jdField_a_of_type_ZG;
/*  98: 98 */  private Vector3f jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/*  99:    */  
/* 100:    */  private Vector4f jdField_a_of_type_JavaxVecmathVector4f;
/* 101:    */  
/* 102:    */  private long jdField_a_of_type_Long;
/* 103:    */  
/* 104:    */  private boolean jdField_a_of_type_Boolean;
/* 105:    */  
/* 106:    */  private yh jdField_a_of_type_Yh;
/* 107:    */  
/* 108:    */  private zK jdField_a_of_type_ZK;
/* 109:    */  
/* 110:    */  private mF jdField_a_of_type_MF;
/* 111:    */  private static float jdField_a_of_type_Float;
/* 112:    */  private int jdField_a_of_type_Int;
/* 113:    */  private final Transform[] jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform;
/* 114:    */  private final String[] jdField_a_of_type_ArrayOfJavaLangString;
/* 115:    */  private q[] jdField_a_of_type_ArrayOfQ;
/* 116:    */  private q jdField_a_of_type_Q;
/* 117:    */  private Vector3f d;
/* 118:    */  private Vector3f e;
/* 119:    */  private Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform;
/* 120:    */  private static FloatBuffer jdField_b_of_type_JavaNioFloatBuffer;
/* 121:    */  private Transform jdField_c_of_type_ComBulletphysicsLinearmathTransform;
/* 122:    */  private final ArrayList jdField_b_of_type_JavaUtilArrayList;
/* 123:    */  
/* 124:    */  public ij(ClientState paramClientState)
/* 125:    */  {
/* 126:126 */    super(paramClientState);new Vector3f();this.jdField_a_of_type_JavaxVecmathVector4f = new Vector4f(0.0F, 1.0F, 0.0F, 1.0F);new Vector3f();
/* 127:    */    
/* 174:174 */    this.jdField_a_of_type_Q = new q();
/* 175:175 */    this.d = new Vector3f();
/* 176:176 */    this.e = new Vector3f();
/* 177:177 */    this.jdField_b_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/* 178:    */    
/* 267:267 */    this.jdField_c_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/* 268:    */    
/* 553:553 */    this.jdField_b_of_type_JavaUtilArrayList = new ArrayList();yg localyg = xe.a().a("hud-target-c-4x4-gui-");this.jdField_a_of_type_YE = new yE(localyg, paramClientState);this.jdField_b_of_type_YE = new yE(xe.a().a("hud-target-c-4x4-gui-"), paramClientState);this.jdField_a_of_type_YP = new yP(32, 32, paramClientState);this.jdField_a_of_type_ZK = new zJ(8.0F);this.jdField_a_of_type_ZG = ((ct)a()).a().a();this.jdField_a_of_type_ArrayOfQ = new q[6];this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform = new Transform[6];this.jdField_a_of_type_ArrayOfJavaLangString = new String[6];
/* 554:    */    
/* 555:143 */    for (int i = 0; i < this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform.length; i++) {
/* 556:144 */      this.jdField_a_of_type_ArrayOfQ[i] = new q();
/* 557:145 */      this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform[i] = new Transform();
/* 558:146 */      this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform[i].setIdentity();
/* 559:    */    }
/* 560:148 */    a(((ct)paramClientState).a());
/* 561:    */  }
/* 562:    */  
/* 644:    */  private void a(q paramq)
/* 645:    */  {
/* 646:234 */    int i = Universe.getSectorSizeWithMargin();
/* 647:    */    
/* 648:236 */    q localq1 = new q();
/* 649:237 */    Vector3f localVector3f = new Vector3f();
/* 650:238 */    localq1.b(paramq);
/* 651:    */    
/* 652:240 */    for (int j = 0; j < 6; j++)
/* 653:    */    {
/* 654:242 */      this.jdField_a_of_type_ArrayOfQ[j].b(paramq);
/* 655:243 */      localq1.b(paramq);
/* 656:244 */      q localq2 = org.schema.game.common.data.element.Element.DIRECTIONSi[j];
/* 657:245 */      localq1.a(localq2);
/* 658:246 */      this.jdField_a_of_type_ArrayOfQ[j].b(localq1);
/* 659:247 */      localVector3f.set(localq2.jdField_a_of_type_Int * i, localq2.b * i, localq2.c * i);
/* 660:248 */      this.jdField_a_of_type_ArrayOfJavaLangString[j] = ("Sector " + localq1);
/* 661:249 */      this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform[j].origin.set(localVector3f);
/* 662:    */    }
/* 663:    */  }
/* 664:    */  
/* 665:    */  static
/* 666:    */  {
/* 667: 57 */    jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/* 668: 58 */    jdField_a_of_type_JavaUtilHashSet = new HashSet();
/* 669:    */    
/* 703: 93 */    jdField_b_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/* 704:    */    
/* 876:266 */    BufferUtils.createFloatBuffer(16);
/* 877:    */  }
/* 878:    */  
/* 879:    */  public final void b() {
/* 880:270 */    if (this.jdField_a_of_type_Long + 50L < System.currentTimeMillis()) {
/* 881:271 */      this.jdField_a_of_type_Boolean = true;
/* 882:272 */      this.jdField_a_of_type_Long = System.currentTimeMillis();
/* 883:    */    } else {
/* 884:274 */      this.jdField_a_of_type_Boolean = false;
/* 885:    */    }
/* 886:276 */    jdField_a_of_type_Float = 0.5F + this.jdField_a_of_type_ZK.a() * 0.5F;
/* 887:    */    
/* 888:278 */    Object localObject6 = null;Object localObject1 = ((ct)a()).a().a.a.jdField_a_of_type_Ar;
/* 889:    */    
/* 894:284 */    this.jdField_a_of_type_MF = ((ar)localObject1).a();
/* 895:    */    
/* 897:287 */    this.jdField_a_of_type_Yh = null;
/* 898:288 */    if ((xe.a().a() instanceof xb)) {
/* 899:289 */      localObject1 = (xb)xe.a().a();
/* 900:290 */      this.jdField_a_of_type_Yh = ((xb)localObject1).a(); }
/* 901:    */    Object localObject5;
/* 902:    */    Object localObject3;
/* 903:293 */    float f6; Object localObject4; Vector3f localVector3f4; for (int i = 0; i < this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform.length; i++) {
/* 904:294 */      if ((!jdField_b_of_type_Boolean) && (this.jdField_a_of_type_ArrayOfJavaLangString[i] == null)) throw new AssertionError();
/* 905:295 */      this.jdField_c_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform[i]);
/* 906:296 */      localObject5 = this.jdField_c_of_type_ComBulletphysicsLinearmathTransform;int k = i;localObject3 = this; q localq1; q localq2 = mJ.a(localq1 = this.jdField_a_of_type_ArrayOfQ[k], ((ij)localObject3).jdField_a_of_type_Q);long l1 = ((ct)((ij)localObject3).a()).a().calculateStartTime();(localObject6 = new q(localq1)).c(((ct)((ij)localObject3).a()).a().a());((Transform)localObject5).setIdentity(); Vector3f localVector3f1; if (mI.a(((ct)((ij)localObject3).a()).a().a())) { f6 = (float)((System.currentTimeMillis() - l1) % 1200000L) / 1200000.0F;localq2.a(16);localq2.a(8, 8, 8);localq2.c(((ct)((ij)localObject3).a()).a().a());((ij)localObject3).d.set(((q)localObject6).jdField_a_of_type_Int * Universe.getSectorSizeWithMargin(), ((q)localObject6).b * Universe.getSectorSizeWithMargin(), ((q)localObject6).c * Universe.getSectorSizeWithMargin());((ij)localObject3).e.set(localq2.jdField_a_of_type_Int * Universe.getSectorSizeWithMargin(), localq2.b * Universe.getSectorSizeWithMargin(), localq2.c * Universe.getSectorSizeWithMargin());((ij)localObject3).jdField_b_of_type_ComBulletphysicsLinearmathTransform.setIdentity(); if (((ij)localObject3).e.lengthSquared() > 0.0F) { ((Transform)localObject5).origin.add(((ij)localObject3).e);((Transform)localObject5).basis.rotX(6.283186F * f6);(localVector3f1 = new Vector3f()).sub(((ij)localObject3).d, ((ij)localObject3).e);((Transform)localObject5).origin.add(localVector3f1);((Transform)localObject5).basis.transform(((Transform)localObject5).origin); } else { ((Transform)localObject5).basis.rotX(6.283186F * f6);((Transform)localObject5).origin.set(((ij)localObject3).d);((Transform)localObject5).basis.transform(((Transform)localObject5).origin); } } else { ((Transform)localObject5).origin.set(((q)localObject6).jdField_a_of_type_Int * Universe.getSectorSizeWithMargin(), ((q)localObject6).b * Universe.getSectorSizeWithMargin(), ((q)localObject6).c * Universe.getSectorSizeWithMargin()); }
/* 907:297 */      if (localq1.equals(((ct)((ij)localObject3).a()).a().a().b)) {
/* 908:298 */        this.jdField_a_of_type_JavaxVecmathVector4f.set(0.1F + jdField_a_of_type_Float, 0.8F + jdField_a_of_type_Float, 0.6F + jdField_a_of_type_Float, 0.4F + jdField_a_of_type_Float);
/* 909:    */      } else {
/* 910:300 */        this.jdField_a_of_type_JavaxVecmathVector4f.set(0.4F, 0.4F, 0.4F, 0.4F);
/* 911:    */      }
/* 912:302 */      int m = -100 - i;localObject5 = this.jdField_a_of_type_ArrayOfJavaLangString[i];localObject4 = this.jdField_c_of_type_ComBulletphysicsLinearmathTransform;localObject3 = this; if (xe.a() != null) { ((ij)localObject3).jdField_c_of_type_JavaxVecmathVector3f.set(((Transform)localObject4).origin);((ij)localObject3).jdField_c_of_type_JavaxVecmathVector3f.sub(xe.a().a());float f5 = ((ij)localObject3).jdField_c_of_type_JavaxVecmathVector3f.length();localVector3f4 = ((ij)localObject3).jdField_a_of_type_ZG.a(new Vector3f());localObject6 = ((ij)localObject3).jdField_a_of_type_ZG.a(((Transform)localObject4).origin, new Vector3f(), true);(localVector3f1 = new Vector3f((Vector3f)localObject6)).sub(localVector3f4);((ij)localObject3).jdField_a_of_type_YE.a().c(((ij)localObject3).jdField_a_of_type_JavaxVecmathVector4f);((ij)localObject3).jdField_b_of_type_YE.a().c(((ij)localObject3).jdField_a_of_type_JavaxVecmathVector4f); float f3; if (localVector3f1.length() > ((ij)localObject3).jdField_a_of_type_Int) { localVector3f1.normalize();localVector3f1.scale(((ij)localObject3).jdField_a_of_type_Int);localVector3f1.add(localVector3f4);((ij)localObject3).jdField_a_of_type_YE.a_(0);((ij)localObject3).jdField_a_of_type_YE.a().set(localVector3f1); Vector3f localVector3f5; (localVector3f5 = new Vector3f(((ij)localObject3).jdField_a_of_type_YE.a())).sub(localVector3f4);localVector3f5.scale(-1.0F);localVector3f5.normalize();f3 = p.a(new Vector3f(0.0F, 1.0F, 0.0F), localVector3f5);((ij)localObject3).jdField_a_of_type_YE.b(57.295776F * f3);((ij)localObject3).jdField_a_of_type_YE.b();((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(m, 1); } else { f3.add(localVector3f4); if (!((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.containsKey(m)) ((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(m, 1); int i1 = ((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.get(m);((ij)localObject3).jdField_b_of_type_YE.a_(i1); if (((ij)localObject3).jdField_a_of_type_Boolean) ((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(m, Math.min(15, i1 + 1)); ((ij)localObject3).jdField_b_of_type_YE.a().set(f3);((ij)localObject3).jdField_b_of_type_YE.b();((ij)localObject3).jdField_a_of_type_YP.a().set(f3);((ij)localObject3).jdField_a_of_type_YP.b.set(0, localObject5);((ij)localObject3).jdField_a_of_type_YP.b.set(1, (int)f5 + "m");((ij)localObject3).jdField_a_of_type_YP.b();
/* 913:    */        }
/* 914:    */      } }
/* 915:305 */    GlUtil.d();
/* 916:306 */    yg.b(this.jdField_a_of_type_YE.a());
/* 917:307 */    for (Object localObject2 = ((ct)a()).a().values().iterator(); ((Iterator)localObject2).hasNext();)
/* 918:308 */      if ((localObject3 = (mF)((Iterator)localObject2).next()) != this.jdField_a_of_type_Yh) {
/* 919:309 */        localObject4 = localObject3;localObject3 = this;localObject5 = (ct)a(); if ((xe.a() != null) && ((!(localObject4 instanceof kd)) || (!((kd)localObject4).c()))) { ((ij)localObject3).jdField_c_of_type_JavaxVecmathVector3f.set(((mF)localObject4).getWorldTransformClient().origin); if (((ct)localObject5).a() != null) ((ij)localObject3).jdField_c_of_type_JavaxVecmathVector3f.sub(((ct)localObject5).a().getWorldTransform().origin); else ((ij)localObject3).jdField_c_of_type_JavaxVecmathVector3f.sub(xe.a().a()); float f2 = ((ij)localObject3).jdField_c_of_type_JavaxVecmathVector3f.length(); if (((localObject4 != ((ij)localObject3).jdField_a_of_type_MF) && (f2 > 1200.0F)) || (f2 < 1.0F) || (!((mF)localObject4).isHidden())) { a((mF)localObject4, ((ij)localObject3).jdField_a_of_type_JavaxVecmathVector4f, localObject4 == ((ij)localObject3).jdField_a_of_type_MF, (ct)localObject5); if (localObject4 == ((ij)localObject3).jdField_a_of_type_MF) ((ij)localObject3).jdField_a_of_type_JavaxVecmathVector4f.w = 1.0F; else ((ij)localObject3).jdField_a_of_type_JavaxVecmathVector4f.w = Math.min(1.0F, 1200.0F / (f2 * 10.0F)); Vector3f localVector3f3 = ((ij)localObject3).jdField_a_of_type_ZG.a(new Vector3f());localVector3f4 = ((ij)localObject3).jdField_a_of_type_ZG.a(((mF)localObject4).getWorldTransformClient().origin, new Vector3f(), true);(localObject6 = new Vector3f(localVector3f4)).sub(localVector3f3); if (((Vector3f)localObject6).length() > ((ij)localObject3).jdField_a_of_type_Int) { ((Vector3f)localObject6).normalize();((Vector3f)localObject6).scale(((ij)localObject3).jdField_a_of_type_Int);((Vector3f)localObject6).add(localVector3f3);((ij)localObject3).jdField_a_of_type_YE.a().set((Tuple3f)localObject6);((ij)localObject3).jdField_a_of_type_YE.a().c(((ij)localObject3).jdField_a_of_type_JavaxVecmathVector4f);((ij)localObject3).jdField_a_of_type_YE.a().b(0); Vector3f localVector3f2; (localVector3f2 = new Vector3f(((ij)localObject3).jdField_a_of_type_YE.a())).sub(localVector3f3);localVector3f2.scale(-1.0F);localVector3f2.normalize();f6 = p.a(new Vector3f(0.0F, 1.0F, 0.0F), localVector3f2);jdField_b_of_type_JavaNioFloatBuffer.rewind();xe.a.store(jdField_b_of_type_JavaNioFloatBuffer);float f4 = FastMath.i(f6);float f1 = FastMath.j(f6);jdField_b_of_type_JavaNioFloatBuffer.put(0, f1);jdField_b_of_type_JavaNioFloatBuffer.put(4, -f4);jdField_b_of_type_JavaNioFloatBuffer.put(8, 0.0F);jdField_b_of_type_JavaNioFloatBuffer.put(1, f4);jdField_b_of_type_JavaNioFloatBuffer.put(5, f1);jdField_b_of_type_JavaNioFloatBuffer.put(9, 0.0F);jdField_b_of_type_JavaNioFloatBuffer.put(2, 0.0F);jdField_b_of_type_JavaNioFloatBuffer.put(6, 0.0F);jdField_b_of_type_JavaNioFloatBuffer.put(10, 1.0F);jdField_b_of_type_JavaNioFloatBuffer.put(12, xe.a.m30 + ((Vector3f)localObject6).x);jdField_b_of_type_JavaNioFloatBuffer.put(13, xe.a.m31 + ((Vector3f)localObject6).y);jdField_b_of_type_JavaNioFloatBuffer.put(14, xe.a.m32 + ((Vector3f)localObject6).z);jdField_b_of_type_JavaNioFloatBuffer.rewind();GL11.glLoadMatrix(jdField_b_of_type_JavaNioFloatBuffer);yg.a(((ij)localObject3).jdField_a_of_type_YE.a()); if (localObject4 == ((ij)localObject3).jdField_a_of_type_MF) { ((ij)localObject3).jdField_a_of_type_YP.a().set((Tuple3f)localObject6);((ij)localObject3).jdField_a_of_type_YP.b.set(0, ((mF)localObject4).toNiceString());((ij)localObject3).jdField_a_of_type_YP.b.set(1, (int)f2 + "m"); } ((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(((mF)localObject4).getId(), 1); } else { ((ij)localObject3).jdField_b_of_type_YE.a().c(((ij)localObject3).jdField_a_of_type_JavaxVecmathVector4f);((Vector3f)localObject6).add(localVector3f3); if (!((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.containsKey(((mF)localObject4).getId())) ((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(((mF)localObject4).getId(), 1); int n = ((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.get(((mF)localObject4).getId());((ij)localObject3).jdField_b_of_type_YE.a_(n);((ij)localObject3).jdField_b_of_type_YE.a().b(n); if (((ij)localObject3).jdField_a_of_type_Boolean) ((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(((mF)localObject4).getId(), Math.min(15, n + 1)); jdField_b_of_type_JavaNioFloatBuffer.rewind();xe.a.store(jdField_b_of_type_JavaNioFloatBuffer);jdField_b_of_type_JavaNioFloatBuffer.put(12, xe.a.m30 + ((Vector3f)localObject6).x);jdField_b_of_type_JavaNioFloatBuffer.put(13, xe.a.m31 + ((Vector3f)localObject6).y);jdField_b_of_type_JavaNioFloatBuffer.put(14, xe.a.m32 + ((Vector3f)localObject6).z);jdField_b_of_type_JavaNioFloatBuffer.rewind();GL11.glLoadMatrix(jdField_b_of_type_JavaNioFloatBuffer);yg.a(((ij)localObject3).jdField_b_of_type_YE.a());((ij)localObject3).jdField_b_of_type_YE.a().set((Tuple3f)localObject6); if (localObject4 == ((ij)localObject3).jdField_a_of_type_MF) ((ij)localObject3).jdField_b_of_type_JavaUtilArrayList.add(new ik(((mF)localObject4).toNiceString(), (int)f2 + "m", (Vector3f)localObject6)); if (((localObject4 instanceof kd)) && (((kd)localObject4).b() > 0L)) { long l2 = ((kd)localObject4).a() - (System.currentTimeMillis() - ((kd)localObject4).b());((ij)localObject3).jdField_b_of_type_JavaUtilArrayList.add(new ik("CORE OVERHEATING", l2 / 1000L + "sec", (Vector3f)localObject6));
/* 920:    */              }
/* 921:    */            } } } }
/* 922:312 */    yg.c(this.jdField_a_of_type_YE.a());
/* 923:313 */    GlUtil.c();
/* 924:    */    
/* 925:315 */    while (!this.jdField_b_of_type_JavaUtilArrayList.isEmpty()) {
/* 926:316 */      localObject2 = (ik)this.jdField_b_of_type_JavaUtilArrayList.remove(0);
/* 927:317 */      this.jdField_a_of_type_YP.a().set(((ik)localObject2).jdField_a_of_type_JavaxVecmathVector3f);
/* 928:318 */      this.jdField_a_of_type_YP.b.set(0, ((ik)localObject2).jdField_a_of_type_JavaLangString);
/* 929:319 */      this.jdField_a_of_type_YP.b.set(1, ((ik)localObject2).b);
/* 930:320 */      this.jdField_a_of_type_YP.b();
/* 931:    */    }
/* 932:    */    try {
/* 933:323 */      for (int j = 0; j < jdField_a_of_type_JavaUtilArrayList.size(); j++) {
/* 934:324 */        a((ev)jdField_a_of_type_JavaUtilArrayList.get(j), xe.a(), true, 100.0F, this.jdField_a_of_type_ZG);
/* 935:    */      }
/* 936:    */      
/* 939:329 */      return;
/* 940:    */    } catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {}
/* 941:    */  }
/* 942:    */  
/* 943:    */  public final void e() { eN localeN;
/* 944:334 */    if (((ct)a()).a().a.a.jdField_a_of_type_Aa.c) {
/* 945:335 */      localeN = ((ct)a()).a().a();
/* 946:336 */      for (cA localcA : jdField_a_of_type_JavaUtilHashSet) {
/* 947:337 */        q localq = localeN.a().a(new q());
/* 948:338 */        for (int i = 0; i < localcA.jdField_a_of_type_ArrayOfCD.length; i++) {
/* 949:    */          cD localcD;
/* 950:340 */          if (((localcD = localcA.jdField_a_of_type_ArrayOfCD[i]).a()) && 
/* 951:341 */            (localeN.a().b.equals(localcA.jdField_a_of_type_Q)) && 
/* 952:342 */            (localcD.a(eN.a(), localq))) {
/* 953:343 */            a(localcD.a(localcA.jdField_a_of_type_Q), localeN.a(), false, 4000.0F, localeN.a());
/* 954:    */          }
/* 955:    */        }
/* 956:    */      }
/* 957:    */    }
/* 958:    */  }
/* 959:    */  
/* 1230:    */  private void a(ev paramev, Camera paramCamera, boolean paramBoolean, float paramFloat, zG paramzG)
/* 1231:    */  {
/* 1232:622 */    ct localct = (ct)a();
/* 1233:623 */    this.jdField_c_of_type_JavaxVecmathVector3f.set(paramev.a().origin);
/* 1234:    */    
/* 1235:625 */    if ((paramBoolean) && (localct.a() != null) && 
/* 1236:626 */      (!(localct.a() instanceof kd))) {
/* 1237:627 */      this.jdField_c_of_type_JavaxVecmathVector3f.sub(localct.a().getWorldTransform().origin);
/* 1239:    */    }
/* 1240:    */    else
/* 1241:    */    {
/* 1243:633 */      this.jdField_c_of_type_JavaxVecmathVector3f.sub(paramCamera.a());
/* 1244:    */    }
/* 1245:    */    
/* 1246:636 */    if (this.jdField_c_of_type_JavaxVecmathVector3f.length() > paramFloat) {
/* 1247:637 */      return;
/* 1248:    */    }
/* 1249:639 */    paramBoolean = paramzG.a(new Vector3f());
/* 1250:    */    
/* 1251:641 */    paramCamera = paramzG.a(paramev.a().origin, new Vector3f(), true, paramCamera);
/* 1252:    */    
/* 1253:643 */    (
/* 1254:644 */      paramCamera = new Vector3f(paramCamera)).sub(paramBoolean);
/* 1255:    */    
/* 1257:647 */    this.jdField_b_of_type_YE.a().c(this.jdField_a_of_type_JavaxVecmathVector4f);
/* 1258:648 */    paramCamera.add(paramBoolean);
/* 1259:649 */    this.jdField_a_of_type_YP.a().set(paramCamera);
/* 1260:650 */    this.jdField_a_of_type_YP.b.set(0, paramev.jdField_a_of_type_JavaLangString);
/* 1261:651 */    this.jdField_a_of_type_YP.b.set(1, "");
/* 1262:652 */    this.jdField_a_of_type_YP.b(1.0F, 1.0F, 1.0F);
/* 1263:653 */    if (paramev.jdField_a_of_type_JavaxVecmathVector4f != null) {
/* 1264:654 */      this.jdField_a_of_type_YP.a.r = paramev.jdField_a_of_type_JavaxVecmathVector4f.x;
/* 1265:655 */      this.jdField_a_of_type_YP.a.g = paramev.jdField_a_of_type_JavaxVecmathVector4f.y;
/* 1266:656 */      this.jdField_a_of_type_YP.a.b = paramev.jdField_a_of_type_JavaxVecmathVector4f.z;
/* 1267:657 */      this.jdField_a_of_type_YP.a.jdField_a_of_type_Float = paramev.jdField_a_of_type_JavaxVecmathVector4f.w;
/* 1268:    */    }
/* 1269:659 */    this.jdField_a_of_type_YP.b();
/* 1270:660 */    this.jdField_a_of_type_YP.a.r = 1.0F;
/* 1271:661 */    this.jdField_a_of_type_YP.a.g = 1.0F;
/* 1272:662 */    this.jdField_a_of_type_YP.a.b = 1.0F;
/* 1273:663 */    this.jdField_a_of_type_YP.a.jdField_a_of_type_Float = 1.0F;
/* 1274:    */  }
/* 1275:    */  
/* 1276:    */  public final float a() {
/* 1277:667 */    return Display.getDisplayMode().getHeight();
/* 1278:    */  }
/* 1279:    */  
/* 1280:    */  public final float b()
/* 1281:    */  {
/* 1282:672 */    return Display.getDisplayMode().getWidth();
/* 1283:    */  }
/* 1284:    */  
/* 1290:    */  public final void c()
/* 1291:    */  {
/* 1292:682 */    this.jdField_a_of_type_YE.c();
/* 1293:    */    
/* 1299:689 */    this.jdField_a_of_type_YP = new yP(32, 32, d.b(), a());
/* 1300:690 */    this.jdField_a_of_type_YP.a(Color.white);
/* 1301:691 */    this.jdField_a_of_type_YP.b = new ArrayList();
/* 1302:692 */    this.jdField_a_of_type_YP.b.add("");
/* 1303:693 */    this.jdField_a_of_type_YP.b.add("");
/* 1304:694 */    this.jdField_a_of_type_YP.a(2.0F, 2.0F, 0.0F);
/* 1305:    */    
/* 1306:696 */    this.jdField_a_of_type_YP.c();
/* 1307:    */  }
/* 1308:    */  
/* 1309:    */  public final void f()
/* 1310:    */  {
/* 1311:701 */    this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.clear();
/* 1312:702 */    ct localct = (ct)a();
/* 1313:703 */    a(localct.a().a());
/* 1314:    */  }
/* 1315:    */  
/* 1316:    */  public final void a(xq paramxq) {
/* 1317:707 */    this.jdField_a_of_type_Int = ((int)Math.min(xm.a() / 2.4F, xm.b() / 2.4F));
/* 1318:708 */    this.jdField_a_of_type_ZK.a(paramxq);
/* 1319:    */    try {
/* 1320:710 */      for (int i = 0; i < jdField_a_of_type_JavaUtilArrayList.size(); i++) {
/* 1321:711 */        ((ev)jdField_a_of_type_JavaUtilArrayList.get(i)).a(paramxq);
/* 1322:712 */        if (!((ev)jdField_a_of_type_JavaUtilArrayList.get(i)).a()) {
/* 1323:713 */          jdField_a_of_type_JavaUtilArrayList.remove(i);
/* 1324:714 */          i--;
/* 1325:    */        }
/* 1326:    */      }
/* 1327:    */      
/* 1329:719 */      return;
/* 1330:    */    }
/* 1331:    */    catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {}
/* 1332:    */  }
/* 1333:    */  
/* 1334:    */  public final void a() {}
/* 1335:    */  
/* 1336:    */  protected final void d() {}
/* 1337:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ij
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */