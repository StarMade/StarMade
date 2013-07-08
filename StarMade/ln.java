/*   1:    */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*   2:    */import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
/*   3:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ConvexResultCallback;
/*   4:    */import com.bulletphysics.collision.shapes.SphereShape;
/*   5:    */import com.bulletphysics.dynamics.DynamicsWorld;
/*   6:    */import com.bulletphysics.util.ObjectArrayList;
/*   7:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   8:    */import java.util.Random;
/*   9:    */import java.util.Set;
/*  10:    */import javax.vecmath.Tuple3f;
/*  11:    */import org.schema.game.common.data.element.Element;
/*  12:    */import org.schema.game.common.data.element.ElementDocking;
/*  13:    */import org.schema.game.common.data.physics.PhysicsExt;
/*  14:    */import org.schema.game.common.data.world.SectorNotFoundException;
/*  15:    */import org.schema.game.common.data.world.Segment;
/*  16:    */import org.schema.game.network.objects.NetworkClientChannel;
/*  17:    */import org.schema.game.network.objects.remote.RemoteMissileUpdate;
/*  18:    */import org.schema.game.network.objects.remote.RemoteMissileUpdateBuffer;
/*  19:    */import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*  20:    */import org.schema.schine.network.ControllerInterface;
/*  21:    */import org.schema.schine.network.NetworkStateContainer;
/*  22:    */import org.schema.schine.network.objects.Sendable;
/*  23:    */import org.schema.schine.network.server.ServerState;
/*  24:    */
/*  25:    */public abstract class ln implements lb, zP
/*  26:    */{
/*  27:    */  private float jdField_b_of_type_Float;
/*  28:    */  protected final java.util.ArrayList a;
/*  29:    */  protected final java.util.ArrayList b;
/*  30:    */  private float jdField_c_of_type_Float;
/*  31:    */  private com.bulletphysics.linearmath.Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform;
/*  32:    */  lb jdField_a_of_type_Lb;
/*  33:    */  private java.util.ArrayList jdField_c_of_type_JavaUtilArrayList;
/*  34:    */  private float jdField_d_of_type_Float;
/*  35:    */  float jdField_a_of_type_Float;
/*  36:    */  javax.vecmath.Vector3f jdField_a_of_type_JavaxVecmathVector3f;
/*  37:    */  private SphereShape jdField_a_of_type_ComBulletphysicsCollisionShapesSphereShape;
/*  38:    */  private org.schema.game.common.data.physics.PairCachingGhostObjectUncollidable jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable;
/*  39:    */  private org.schema.game.common.data.physics.PairCachingGhostObjectUncollidable jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable;
/*  40:    */  boolean jdField_a_of_type_Boolean;
/*  41:    */  private int jdField_b_of_type_Int;
/*  42:    */  private SphereShape jdField_b_of_type_ComBulletphysicsCollisionShapesSphereShape;
/*  43:    */  final com.bulletphysics.linearmath.Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/*  44:    */  private final org.schema.schine.network.StateInterface jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*  45:    */  short jdField_a_of_type_Short;
/*  46:    */  int jdField_a_of_type_Int;
/*  47:    */  final boolean jdField_b_of_type_Boolean;
/*  48:    */  private org.schema.schine.network.objects.container.PhysicsDataContainer jdField_a_of_type_OrgSchemaSchineNetworkObjectsContainerPhysicsDataContainer;
/*  49:    */  private com.bulletphysics.linearmath.Transform jdField_c_of_type_ComBulletphysicsLinearmathTransform;
/*  50:    */  private final com.bulletphysics.linearmath.Transform jdField_d_of_type_ComBulletphysicsLinearmathTransform;
/*  51:    */  private com.bulletphysics.linearmath.Transform e;
/*  52:    */  private com.bulletphysics.linearmath.Transform f;
/*  53:    */  private com.bulletphysics.linearmath.Transform g;
/*  54:    */  private javax.vecmath.Vector3f jdField_b_of_type_JavaxVecmathVector3f;
/*  55:    */  private javax.vecmath.Vector3f jdField_c_of_type_JavaxVecmathVector3f;
/*  56:    */  private int jdField_c_of_type_Int;
/*  57:    */  private javax.vecmath.Vector3f jdField_d_of_type_JavaxVecmathVector3f;
/*  58:    */  private final java.util.ArrayList jdField_d_of_type_JavaUtilArrayList;
/*  59:    */  private int jdField_d_of_type_Int;
/*  60:    */  private long jdField_a_of_type_Long;
/*  61:    */  private long jdField_b_of_type_Long;
/*  62:    */  
/*  63:    */  public ln(org.schema.schine.network.StateInterface paramStateInterface)
/*  64:    */  {
/*  65: 65 */    this.jdField_a_of_type_JavaUtilArrayList = new java.util.ArrayList();
/*  66: 66 */    this.jdField_b_of_type_JavaUtilArrayList = new java.util.ArrayList();
/*  67: 67 */    new java.util.ArrayList();
/*  68:    */    
/*  69: 69 */    this.jdField_c_of_type_Float = 1.0F;
/*  70: 70 */    this.jdField_b_of_type_ComBulletphysicsLinearmathTransform = new com.bulletphysics.linearmath.Transform();
/*  71:    */    
/*  72: 72 */    this.jdField_c_of_type_JavaUtilArrayList = new java.util.ArrayList();
/*  73:    */    
/*  74: 74 */    this.jdField_a_of_type_Float = 100.0F;
/*  75: 75 */    this.jdField_a_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
/*  76:    */    
/*  82: 82 */    this.jdField_a_of_type_Boolean = true;
/*  83: 83 */    this.jdField_b_of_type_Int = 1;
/*  84:    */    
/*  89: 89 */    this.jdField_a_of_type_Short = -1231;
/*  90:    */    
/*  97: 97 */    this.jdField_c_of_type_ComBulletphysicsLinearmathTransform = new com.bulletphysics.linearmath.Transform();
/*  98:    */    
/* 316:316 */    this.jdField_d_of_type_ComBulletphysicsLinearmathTransform = new com.bulletphysics.linearmath.Transform();
/* 317:317 */    this.e = new com.bulletphysics.linearmath.Transform();
/* 318:318 */    this.f = new com.bulletphysics.linearmath.Transform();
/* 319:319 */    this.g = new com.bulletphysics.linearmath.Transform();
/* 320:320 */    this.jdField_b_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
/* 321:321 */    this.jdField_c_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
/* 322:322 */    this.jdField_c_of_type_Int = -1;
/* 323:323 */    this.jdField_d_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
/* 324:    */    
/* 734:734 */    this.jdField_d_of_type_JavaUtilArrayList = new java.util.ArrayList();this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;this.jdField_b_of_type_Boolean = (paramStateInterface instanceof ServerState);this.jdField_a_of_type_OrgSchemaSchineNetworkObjectsContainerPhysicsDataContainer = new org.schema.schine.network.objects.container.PhysicsDataContainer();this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = new com.bulletphysics.linearmath.Transform();this.jdField_d_of_type_ComBulletphysicsLinearmathTransform.setIdentity();this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/* 735:    */  }
/* 736:    */  
/* 761:    */  public final float a()
/* 762:    */  {
/* 763:133 */    return this.jdField_c_of_type_Float;
/* 764:    */  }
/* 765:    */  
/* 767:    */  public final int a()
/* 768:    */  {
/* 769:139 */    return this.jdField_b_of_type_Int;
/* 770:    */  }
/* 771:    */  
/* 774:    */  public final javax.vecmath.Vector3f a()
/* 775:    */  {
/* 776:146 */    return this.jdField_a_of_type_JavaxVecmathVector3f;
/* 777:    */  }
/* 778:    */  
/* 781:    */  public final float b()
/* 782:    */  {
/* 783:153 */    return Math.min(5.0F, this.jdField_a_of_type_Float);
/* 784:    */  }
/* 785:    */  
/* 808:    */  public final com.bulletphysics.linearmath.Transform a()
/* 809:    */  {
/* 810:180 */    return this.jdField_c_of_type_ComBulletphysicsLinearmathTransform;
/* 811:    */  }
/* 812:    */  
/* 817:    */  public float getMass()
/* 818:    */  {
/* 819:189 */    return 0.0F;
/* 820:    */  }
/* 821:    */  
/* 825:    */  public org.schema.schine.network.objects.container.PhysicsDataContainer getPhysicsDataContainer()
/* 826:    */  {
/* 827:197 */    return this.jdField_a_of_type_OrgSchemaSchineNetworkObjectsContainerPhysicsDataContainer;
/* 828:    */  }
/* 829:    */  
/* 833:    */  public org.schema.schine.network.StateInterface getState()
/* 834:    */  {
/* 835:205 */    return this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/* 836:    */  }
/* 837:    */  
/* 848:    */  public void getTransformedAABB(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, float paramFloat, javax.vecmath.Vector3f paramVector3f3, javax.vecmath.Vector3f paramVector3f4) {}
/* 849:    */  
/* 859:    */  public final lb a()
/* 860:    */  {
/* 861:231 */    return this.jdField_a_of_type_Lb;
/* 862:    */  }
/* 863:    */  
/* 868:    */  public final float c()
/* 869:    */  {
/* 870:240 */    return this.jdField_b_of_type_Float;
/* 871:    */  }
/* 872:    */  
/* 882:    */  public void initPhysics()
/* 883:    */  {
/* 884:254 */    this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable = new org.schema.game.common.data.physics.PairCachingGhostObjectUncollidable(getPhysicsDataContainer());
/* 885:    */    
/* 886:256 */    this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setWorldTransform(this.jdField_c_of_type_ComBulletphysicsLinearmathTransform);
/* 887:    */    
/* 889:259 */    this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable = new org.schema.game.common.data.physics.PairCachingGhostObjectUncollidable(getPhysicsDataContainer());
/* 890:    */    
/* 891:261 */    this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setWorldTransform(this.jdField_c_of_type_ComBulletphysicsLinearmathTransform);
/* 892:    */    
/* 894:264 */    this.jdField_b_of_type_ComBulletphysicsCollisionShapesSphereShape = new SphereShape(this.jdField_c_of_type_Float);
/* 895:265 */    this.jdField_a_of_type_ComBulletphysicsCollisionShapesSphereShape = new SphereShape(0.5F);
/* 896:    */    
/* 898:268 */    this.jdField_a_of_type_ComBulletphysicsCollisionShapesSphereShape.setMargin(0.1F);
/* 899:269 */    this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionShape(this.jdField_a_of_type_ComBulletphysicsCollisionShapesSphereShape);
/* 900:270 */    this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionShape(this.jdField_b_of_type_ComBulletphysicsCollisionShapesSphereShape);
/* 901:    */    
/* 905:275 */    this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setUserPointer(null);
/* 906:    */    
/* 908:278 */    getPhysicsDataContainer().setObject(this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable);
/* 909:279 */    getPhysicsDataContainer().setShape(this.jdField_a_of_type_ComBulletphysicsCollisionShapesSphereShape);
/* 910:    */    
/* 911:281 */    getPhysicsDataContainer().updatePhysical();
/* 912:    */    
/* 913:283 */    this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionFlags(this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.getCollisionFlags() | 0x4);
/* 914:    */    
/* 917:287 */    if (this.jdField_b_of_type_Boolean)
/* 918:    */    {
/* 920:290 */      f();
/* 921:    */    }
/* 922:    */  }
/* 923:    */  
/* 924:    */  public final boolean a() {
/* 925:295 */    return this.jdField_a_of_type_Boolean;
/* 926:    */  }
/* 927:    */  
/* 932:    */  private void f()
/* 933:    */  {
/* 934:304 */    if (this.jdField_b_of_type_Boolean) {
/* 935:305 */      this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionFlags(4);
/* 936:306 */      this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionFlags(4);
/* 937:307 */      a().addObject(getPhysicsDataContainer().getObject(), (short)-9, (short)8);
/* 938:    */      
/* 939:309 */      a().addObject(this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable, (short)-9, (short)16);
/* 940:310 */      this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionFlags(4);
/* 941:311 */      this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionFlags(4);
/* 942:    */    }
/* 943:    */  }
/* 944:    */  
/* 954:    */  public final void a(int paramInt, q paramq)
/* 955:    */  {
/* 956:326 */    this.jdField_c_of_type_Int = -1;
/* 957:    */    
/* 959:329 */    Object localObject2 = null; if (this.jdField_a_of_type_Int == paramInt) {
/* 960:330 */      this.jdField_d_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);return;
/* 961:    */    }
/* 962:332 */    int i = 0;
/* 963:333 */    float f1 = 0.0F;
/* 964:334 */    Object localObject1; if (this.jdField_c_of_type_Int != paramInt) {
/* 965:335 */      i = 1;
/* 966:336 */      localObject1 = new q();
/* 967:    */      q localq;
/* 968:338 */      if (this.jdField_b_of_type_Boolean)
/* 969:    */      {
/* 970:340 */        if ((localObject2 = ((vg)getState()).a().getSector(this.jdField_a_of_type_Int)) != null) {
/* 971:341 */          localq = ((mx)localObject2).a;
/* 972:    */        }
/* 973:    */        
/* 975:    */      }
/* 976:    */      else
/* 977:    */      {
/* 978:348 */        if ((localObject2 = (mv)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().get(this.jdField_a_of_type_Int)) == null)
/* 979:    */        {
/* 981:351 */          System.err.println("Exception: Sector Not Found: " + this.jdField_a_of_type_Int + " for " + this + "; from sector: " + paramInt);
/* 982:352 */          this.jdField_d_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 983:353 */          this.jdField_d_of_type_ComBulletphysicsLinearmathTransform.origin.set(10000.0F, 10000.0F, 1000.0F);
/* 984:354 */          return;
/* 985:    */        }
/* 986:356 */        localq = ((mv)localObject2).a();
/* 987:    */      }
/* 988:    */      
/* 989:359 */      localObject2 = mJ.a(paramq, new q());
/* 990:360 */      if (mI.a(paramq))
/* 991:    */      {
/* 992:362 */        f1 = (float)((System.currentTimeMillis() - this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface.getController().calculateStartTime()) % 1200000L) / 1200000.0F;
/* 993:363 */        if (!mI.a(paramq)) {
/* 994:364 */          f1 = 0.0F;
/* 995:    */        }
/* 996:    */        
/* 999:369 */        ((q)localObject2).a(16);
/* 1000:370 */        ((q)localObject2).a(8, 8, 8);
/* 1001:371 */        ((q)localObject2).c(paramq);
/* 1002:372 */        ((q)localObject1).b((q)localObject2);
/* 1003:    */      } else {
/* 1004:374 */        f1 = 0.0F;
/* 1005:    */      }
/* 1006:    */      
/* 1009:379 */      (localObject2 = new q()).a(localq, paramq);
/* 1010:    */      
/* 1013:383 */      this.jdField_b_of_type_JavaxVecmathVector3f.set(((q)localObject2).jdField_a_of_type_Int * org.schema.game.common.data.world.Universe.getSectorSizeWithMargin(), ((q)localObject2).jdField_b_of_type_Int * org.schema.game.common.data.world.Universe.getSectorSizeWithMargin(), ((q)localObject2).jdField_c_of_type_Int * org.schema.game.common.data.world.Universe.getSectorSizeWithMargin());
/* 1014:    */      
/* 1018:388 */      this.jdField_c_of_type_JavaxVecmathVector3f.set(((q)localObject1).jdField_a_of_type_Int * org.schema.game.common.data.world.Universe.getSectorSizeWithMargin(), ((q)localObject1).jdField_b_of_type_Int * org.schema.game.common.data.world.Universe.getSectorSizeWithMargin(), ((q)localObject1).jdField_c_of_type_Int * org.schema.game.common.data.world.Universe.getSectorSizeWithMargin());
/* 1019:    */      
/* 1023:393 */      this.f.setIdentity();
/* 1024:    */      
/* 1025:395 */      if (((q)localObject1).a() > 0.0F)
/* 1026:    */      {
/* 1028:398 */        this.f.origin.add(this.jdField_c_of_type_JavaxVecmathVector3f);
/* 1029:399 */        this.f.basis.rotX(6.283186F * f1);
/* 1030:    */        
/* 1032:402 */        this.jdField_d_of_type_JavaxVecmathVector3f.sub(this.jdField_b_of_type_JavaxVecmathVector3f, this.jdField_c_of_type_JavaxVecmathVector3f);
/* 1033:403 */        this.f.origin.add(this.jdField_d_of_type_JavaxVecmathVector3f);
/* 1034:    */        
/* 1036:406 */        this.f.basis.transform(this.f.origin);
/* 1038:    */      }
/* 1039:    */      else
/* 1040:    */      {
/* 1041:411 */        this.f.basis.setIdentity();
/* 1042:    */        
/* 1043:413 */        this.f.origin.set(this.jdField_b_of_type_JavaxVecmathVector3f);
/* 1044:    */      }
/* 1045:    */    }
/* 1046:    */    
/* 1050:420 */    if ((this.jdField_c_of_type_Int != paramInt) || (!this.e.equals(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform))) {
/* 1051:421 */      this.g.set(this.f);
/* 1052:    */      
/* 1053:423 */      (
/* 1054:424 */        localObject1 = new com.bulletphysics.linearmath.Transform()).setIdentity();
/* 1055:425 */      float f2 = -6.283186F * f1;
/* 1056:426 */      ((com.bulletphysics.linearmath.Transform)localObject1).basis.rotX(f2);
/* 1057:427 */      d.a((com.bulletphysics.linearmath.Transform)localObject1, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 1058:    */      
/* 1062:432 */      d.a(this.g, (com.bulletphysics.linearmath.Transform)localObject1);
/* 1063:    */      
/* 1068:438 */      this.jdField_d_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 1069:439 */      this.jdField_d_of_type_ComBulletphysicsLinearmathTransform.origin.set(this.g.origin);
/* 1070:    */      
/* 1074:444 */      this.e.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 1075:    */    }
/* 1076:    */    
/* 1081:451 */    if (i != 0) {
/* 1082:452 */      this.jdField_c_of_type_Int = paramInt;
/* 1083:    */    }
/* 1084:    */  }
/* 1085:    */  
/* 1087:    */  public final void a()
/* 1088:    */  {
/* 1089:459 */    if (this.jdField_a_of_type_Int == ((ct)getState()).a()) {
/* 1090:    */      Object localObject1;
/* 1091:461 */      if (((localObject1 = (ct)getState()).a() != null) && (((ct)localObject1).a().a() != null)) {
/* 1092:462 */        System.err.println("[CLIENT] Adding Trail for missile " + this.jdField_d_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 1093:463 */        com.bulletphysics.linearmath.Transform localTransform = this.jdField_d_of_type_ComBulletphysicsLinearmathTransform; synchronized ((localObject1 = ((ct)localObject1).a().a()).jdField_a_of_type_JavaUtilArrayList) { ((ez)localObject1).jdField_a_of_type_JavaUtilArrayList.add(new eA(localTransform, true));return;
/* 1094:    */        } }
/* 1095:465 */      System.err.println("[CLIENT] Cannot add Trail for missile (drawer not initialized)");
/* 1096:    */      
/* 1097:467 */      return; }
/* 1098:468 */    System.err.println("[CLIENT] NOT ADDING TRAIL FOR " + this + "; not in sector");
/* 1099:    */  }
/* 1100:    */  
/* 1101:    */  public final void b()
/* 1102:    */  {
/* 1103:    */    Object localObject1;
/* 1104:474 */    if (((localObject1 = (ct)getState()).a() != null) && (((ct)localObject1).a().a() != null)) {
/* 1105:475 */      com.bulletphysics.linearmath.Transform localTransform = this.jdField_d_of_type_ComBulletphysicsLinearmathTransform; synchronized ((localObject1 = ((ct)localObject1).a().a()).jdField_a_of_type_JavaUtilArrayList) { ((ez)localObject1).jdField_a_of_type_JavaUtilArrayList.add(new eA(localTransform, false)); }
/* 1106:476 */      System.err.println("[CLIENT] Removing Trail for missile");
/* 1107:    */    }
/* 1108:    */  }
/* 1109:    */  
/* 1110:    */  private zQ a() {
/* 1111:481 */    ln localln = this;long l1 = System.currentTimeMillis();mx localmx = ((vg)localln.getState()).a().getSector(localln.jdField_a_of_type_Int); long l2; if ((l2 = System.currentTimeMillis() - l1) > 5L) System.err.println("[SERVER][STO] WARNING: Loading sector " + localln.jdField_a_of_type_Int + " for " + localln + " took " + l2 + " ms"); if (localmx == null) throw new SectorNotFoundException(localln.jdField_a_of_type_Int); return (PhysicsExt)(this.jdField_b_of_type_Boolean ? localmx : (ct)localln.getState()).a();
/* 1112:    */  }
/* 1113:    */  
/* 1116:    */  public final void c()
/* 1117:    */  {
/* 1118:488 */    if (this.jdField_b_of_type_Boolean) {
/* 1119:489 */      a().removeObject(getPhysicsDataContainer().getObject());
/* 1120:490 */      a().removeObject(this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable);
/* 1121:    */    }
/* 1122:    */  }
/* 1123:    */  
/* 1142:    */  private void a(org.schema.game.common.data.physics.ClosestConvexResultCallbackExt paramClosestConvexResultCallbackExt)
/* 1143:    */  {
/* 1144:514 */    ObjectArrayList localObjectArrayList = this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.getOverlappingPairCache().getOverlappingPairArray();
/* 1145:    */    
/* 1147:517 */    Sendable localSendable = null;
/* 1148:518 */    if (paramClosestConvexResultCallbackExt.userData == this.jdField_a_of_type_Lb) return;
/* 1149:    */    int i;
/* 1150:520 */    Object localObject1; Object localObject2; if (paramClosestConvexResultCallbackExt.userData != null) {
/* 1151:521 */      i = 0;
/* 1152:522 */      if ((this.jdField_a_of_type_Lb instanceof org.schema.game.common.controller.SegmentController))
/* 1153:    */      {
/* 1154:524 */        if (((localObject1 = (org.schema.game.common.controller.SegmentController)this.jdField_a_of_type_Lb).getDockingController().a() != null) && (((org.schema.game.common.controller.SegmentController)localObject1).getDockingController().a().to.a().a() == (org.schema.game.common.controller.SegmentController)paramClosestConvexResultCallbackExt.userData)) {
/* 1155:525 */          i = 1;
/* 1156:    */        }
/* 1157:527 */        if (i == 0) {
/* 1158:528 */          for (localObject2 = ((org.schema.game.common.controller.SegmentController)localObject1).getDockingController().a().iterator(); ((java.util.Iterator)localObject2).hasNext();) {
/* 1159:529 */            if (((ElementDocking)((java.util.Iterator)localObject2).next()).from.a().a() == (org.schema.game.common.controller.SegmentController)paramClosestConvexResultCallbackExt.userData) {
/* 1160:530 */              i = 1;
/* 1161:    */            }
/* 1162:    */          }
/* 1163:    */        }
/* 1164:    */      }
/* 1165:535 */      if (i == 0) {
/* 1166:536 */        localSendable = (Sendable)paramClosestConvexResultCallbackExt.userData;
/* 1167:    */      }
/* 1168:    */    } else {
/* 1169:539 */      System.err.println("Exception: warning: callback userdata is null (probably not hit with a segmentcontroller)");
/* 1170:    */    }
/* 1171:541 */    if (localSendable != null) {
/* 1172:542 */      for (i = 0; i < localObjectArrayList.size(); i++)
/* 1173:    */      {
/* 1180:550 */        if (((localObject2 = (localObject1 = (com.bulletphysics.collision.broadphase.BroadphasePair)localObjectArrayList.get(i)).pProxy0.clientObject != this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable ? ((com.bulletphysics.collision.broadphase.BroadphasePair)localObject1).pProxy0 : ((com.bulletphysics.collision.broadphase.BroadphasePair)localObject1).pProxy1).clientObject != null) && ((((com.bulletphysics.collision.broadphase.BroadphaseProxy)localObject2).clientObject instanceof com.bulletphysics.collision.dispatch.CollisionObject)) && (this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.checkCollideWith((com.bulletphysics.collision.dispatch.CollisionObject)((com.bulletphysics.collision.broadphase.BroadphaseProxy)localObject2).clientObject))) {
/* 1181:551 */          paramClosestConvexResultCallbackExt = (com.bulletphysics.collision.dispatch.CollisionObject)((com.bulletphysics.collision.broadphase.BroadphaseProxy)localObject2).clientObject;
/* 1182:    */          
/* 1183:553 */          System.err.println(this + " HIT BOADPHASE " + ((com.bulletphysics.collision.broadphase.BroadphasePair)localObject1).pProxy0.clientObject + " and " + ((com.bulletphysics.collision.broadphase.BroadphasePair)localObject1).pProxy1.clientObject);
/* 1184:    */          
/* 1187:557 */          if ((paramClosestConvexResultCallbackExt.getUserPointer() != null) && ((paramClosestConvexResultCallbackExt.getUserPointer() instanceof Integer))) {
/* 1188:558 */            paramClosestConvexResultCallbackExt = ((Integer)paramClosestConvexResultCallbackExt.getUserPointer()).intValue();
/* 1189:    */            
/* 1190:560 */            paramClosestConvexResultCallbackExt = (Sendable)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(paramClosestConvexResultCallbackExt);
/* 1191:561 */            localObject1 = this.jdField_a_of_type_Lb;
/* 1192:562 */            if ((paramClosestConvexResultCallbackExt == localObject1) && 
/* 1193:563 */              (this.jdField_d_of_type_Float < 5.0F)) {
/* 1194:564 */              System.err.println("[MISSILE]: " + this + " Cannot hit owner: " + this.jdField_d_of_type_Float + "/5");
/* 1197:    */            }
/* 1198:568 */            else if (paramClosestConvexResultCallbackExt != null) {
/* 1199:569 */              System.err.println("[MISSILE]: " + this + " REGISTERED HIT: " + paramClosestConvexResultCallbackExt);
/* 1200:570 */              this.jdField_c_of_type_JavaUtilArrayList.add(paramClosestConvexResultCallbackExt);
/* 1201:    */            }
/* 1202:    */          }
/* 1203:    */        } }
/* 1204:    */    }
/* 1205:    */    java.util.Iterator localIterator;
/* 1206:576 */    if (localSendable != null)
/* 1207:    */    {
/* 1209:579 */      for (localIterator = this.jdField_c_of_type_JavaUtilArrayList.iterator(); localIterator.hasNext();) {
/* 1210:580 */        if (((localObject1 = (Sendable)localIterator.next()) instanceof C)) {
/* 1211:581 */          ((C)localObject1).handleHitMissile(this, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 1212:582 */          this.jdField_a_of_type_Boolean = false;
/* 1213:    */        }
/* 1214:    */      }
/* 1215:    */    }
/* 1216:586 */    this.jdField_c_of_type_JavaUtilArrayList.clear();
/* 1217:    */  }
/* 1218:    */  
/* 1232:    */  public final void d()
/* 1233:    */  {
/* 1234:604 */    this.jdField_a_of_type_Boolean = false;
/* 1235:    */  }
/* 1236:    */  
/* 1239:    */  public final void a(float paramFloat)
/* 1240:    */  {
/* 1241:611 */    this.jdField_c_of_type_Float = paramFloat;
/* 1242:    */  }
/* 1243:    */  
/* 1246:    */  public final void a(int paramInt)
/* 1247:    */  {
/* 1248:618 */    this.jdField_b_of_type_Int = paramInt;
/* 1249:    */  }
/* 1250:    */  
/* 1253:    */  public final void a(javax.vecmath.Vector3f paramVector3f)
/* 1254:    */  {
/* 1255:625 */    this.jdField_a_of_type_JavaxVecmathVector3f = paramVector3f;
/* 1256:    */  }
/* 1257:    */  
/* 1260:    */  public final void b(float paramFloat)
/* 1261:    */  {
/* 1262:632 */    this.jdField_a_of_type_Float = paramFloat;
/* 1263:    */  }
/* 1264:    */  
/* 1276:    */  public final void a(lb paramlb)
/* 1277:    */  {
/* 1278:648 */    this.jdField_a_of_type_Lb = paramlb;
/* 1279:    */  }
/* 1280:    */  
/* 1283:    */  public final void c(float paramFloat)
/* 1284:    */  {
/* 1285:655 */    this.jdField_b_of_type_Float = paramFloat;
/* 1286:    */  }
/* 1287:    */  
/* 1288:    */  protected final void a(com.bulletphysics.linearmath.Transform paramTransform) {
/* 1289:659 */    if (this.jdField_b_of_type_Boolean)
/* 1290:660 */      this.jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setWorldTransform(paramTransform);
/* 1291:661 */    this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.set(paramTransform);
/* 1292:    */  }
/* 1293:    */  
/* 1352:    */  public String toString()
/* 1353:    */  {
/* 1354:724 */    return "Missile(" + this.jdField_a_of_type_Short + " s[" + this.jdField_a_of_type_Int + "] " + this.jdField_a_of_type_Lb + ")";
/* 1355:    */  }
/* 1356:    */  
/* 1363:    */  public abstract void b(xq paramxq);
/* 1364:    */  
/* 1370:    */  public void a(xq paramxq)
/* 1371:    */  {
/* 1372:    */    Object localObject1;
/* 1373:    */    
/* 1378:748 */    if (this.jdField_a_of_type_Boolean) {
/* 1379:    */      try {
/* 1380:    */        try {
/* 1381:751 */          localObject1 = this; if (this.jdField_a_of_type_Lb == null) System.err.println("[MISSILE] Exception: OWNER IS NULL"); Object localObject2; try { localObject2 = ((mF)((ln)localObject1).jdField_a_of_type_Lb).getPhysicsState(); } catch (SectorNotFoundException localSectorNotFoundException) { localObject2 = null;localSectorNotFoundException.printStackTrace();System.err.println("[MISSILE] Exception CATCHED: sectors are not kept alive for missiles. terminate taht missile");((ln)localObject1).jdField_a_of_type_Boolean = false; break label335; } if (localObject2 == null) { System.err.println("[MISSILE] Exception: MISSILE NOT ABLE TO RETRIEVE PHYSICS FOR SECTOR " + ((ln)localObject1).jdField_a_of_type_Int); if (((ln)localObject1).jdField_b_of_type_Boolean) ((ln)localObject1).jdField_a_of_type_Boolean = false; } else { localObject2 = (PhysicsExt)((zW)localObject2).a();((ln)localObject1).jdField_b_of_type_ComBulletphysicsLinearmathTransform.setIdentity();((ln)localObject1).jdField_b_of_type_ComBulletphysicsLinearmathTransform.set(((ln)localObject1).jdField_a_of_type_ComBulletphysicsLinearmathTransform);Object localObject3 = new javax.vecmath.Vector3f(((ln)localObject1).jdField_a_of_type_JavaxVecmathVector3f);((ln)localObject1).jdField_b_of_type_ComBulletphysicsLinearmathTransform.basis.transform((Tuple3f)localObject3);((ln)localObject1).jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin.add((Tuple3f)localObject3); if (((ln)localObject1).jdField_a_of_type_ComBulletphysicsLinearmathTransform.basis.determinant() != 0.0F) { if ((!jdField_c_of_type_Boolean) && (localObject2 == null)) throw new AssertionError(); if ((!jdField_c_of_type_Boolean) && (((ln)localObject1).jdField_a_of_type_ComBulletphysicsLinearmathTransform == null)) throw new AssertionError(); if ((!jdField_c_of_type_Boolean) && (((ln)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable == null)) throw new AssertionError(); (localObject3 = new org.schema.game.common.data.physics.ClosestConvexResultCallbackExt(((ln)localObject1).jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin, ((ln)localObject1).jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin)).checkHasHitOnly = true;((ln)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.convexSweepTest(((ln)localObject1).jdField_a_of_type_ComBulletphysicsCollisionShapesSphereShape, ((ln)localObject1).jdField_a_of_type_ComBulletphysicsLinearmathTransform, ((ln)localObject1).jdField_b_of_type_ComBulletphysicsLinearmathTransform, (CollisionWorld.ConvexResultCallback)localObject3, ((PhysicsExt)localObject2).getDynamicsWorld().getDispatchInfo().allowedCcdPenetration); if (((org.schema.game.common.data.physics.ClosestConvexResultCallbackExt)localObject3).hasHit()) try { ((ln)localObject1).a((org.schema.game.common.data.physics.ClosestConvexResultCallbackExt)localObject3); } catch (Exception localException) { localException; } } else { throw new IllegalStateException("[MISSILE] WORLD TRANSFORM IS STRANGE OR PHYSICS NOT INITIALIZED"); } }
/* 1382:    */          label335:
/* 1383:753 */          this.jdField_b_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setWorldTransform(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 1384:    */        } catch (java.io.IOException localIOException) {
/* 1385:755 */          (localObject1 = 
/* 1386:    */          
/* 1390:760 */            localIOException).printStackTrace();throw new ErrorDialogException(((java.io.IOException)localObject1).getMessage());
/* 1391:    */        } catch (InterruptedException localInterruptedException) {
/* 1392:758 */          (localObject1 = localInterruptedException).printStackTrace();
/* 1393:759 */          throw new ErrorDialogException(((InterruptedException)localObject1).getMessage());
/* 1394:    */        }
/* 1395:    */      }
/* 1396:    */      catch (IllegalStateException localIllegalStateException) {
/* 1397:763 */        localIllegalStateException.printStackTrace();
/* 1398:    */        
/* 1399:765 */        b(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 1400:    */      }
/* 1401:    */    }
/* 1402:    */    
/* 1404:770 */    float f1 = 0.0F;this.jdField_d_of_type_Float += paramxq.a();
/* 1405:    */    
/* 1408:774 */    if ((this.jdField_a_of_type_Boolean) && (this.jdField_d_of_type_Float > this.jdField_a_of_type_Float)) {
/* 1409:775 */      this.jdField_a_of_type_Boolean = false;
/* 1410:776 */      System.err.println("[SERVER] MISSILE DIED FROM LIFETIME");
/* 1411:    */    }
/* 1412:    */    
/* 1413:779 */    if (!this.jdField_a_of_type_Boolean) {
/* 1414:780 */      System.err.println("[SERVER] Deleting missile " + this);
/* 1415:781 */      this.jdField_b_of_type_JavaUtilArrayList.add(new lq(this.jdField_a_of_type_Short));return;
/* 1416:    */    }
/* 1417:    */    
/* 1418:784 */    if (this.jdField_a_of_type_Int != this.jdField_d_of_type_Int)
/* 1419:    */    {
/* 1420:786 */      (localObject1 = new lt(this.jdField_a_of_type_Short)).jdField_a_of_type_JavaxVecmathVector3f.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 1421:787 */      ((lt)localObject1).jdField_b_of_type_JavaxVecmathVector3f.set(this.jdField_a_of_type_JavaxVecmathVector3f);
/* 1422:788 */      ((lt)localObject1).jdField_a_of_type_Int = this.jdField_a_of_type_Int;
/* 1423:789 */      this.jdField_a_of_type_JavaUtilArrayList.add(localObject1);
/* 1424:790 */      this.jdField_d_of_type_Int = this.jdField_a_of_type_Int;
/* 1425:    */      
/* 1427:793 */      return; }
/* 1428:794 */    if (System.currentTimeMillis() - this.jdField_a_of_type_Long > 500L)
/* 1429:    */    {
/* 1430:796 */      (localObject1 = new ls(this.jdField_a_of_type_Short)).jdField_a_of_type_JavaxVecmathVector3f.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 1431:797 */      this.jdField_a_of_type_JavaUtilArrayList.add(localObject1);
/* 1432:798 */      this.jdField_a_of_type_Long = (System.currentTimeMillis() + org.schema.game.common.data.world.Universe.getRandom().nextInt(30));
/* 1433:    */    }
/* 1434:800 */    if (((this instanceof lp)) && 
/* 1435:801 */      (System.currentTimeMillis() - this.jdField_b_of_type_Long > 500L))
/* 1436:    */    {
/* 1437:803 */      (localObject1 = new lr(this.jdField_a_of_type_Short)).jdField_a_of_type_JavaxVecmathVector3f.set(this.jdField_a_of_type_JavaxVecmathVector3f);
/* 1438:804 */      this.jdField_a_of_type_JavaUtilArrayList.add(localObject1);
/* 1439:805 */      this.jdField_b_of_type_Long = (System.currentTimeMillis() + org.schema.game.common.data.world.Universe.getRandom().nextInt(30));
/* 1440:    */    }
/* 1441:    */  }
/* 1442:    */  
/* 1450:    */  private void b(javax.vecmath.Vector3f paramVector3f)
/* 1451:    */  {
/* 1452:818 */    if ((!jdField_c_of_type_Boolean) && (!(this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface instanceof vg))) throw new AssertionError();
/* 1453:819 */    javax.vecmath.Vector3f localVector3f1 = new javax.vecmath.Vector3f(paramVector3f);
/* 1454:    */    mx localmx1;
/* 1455:821 */    if ((localmx1 = ((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().getSector(this.jdField_a_of_type_Int)) != null) {
/* 1456:822 */      q localq1 = localmx1.a;
/* 1457:823 */      int i = -1;
/* 1458:824 */      javax.vecmath.Vector3f localVector3f2 = new javax.vecmath.Vector3f(localVector3f1);
/* 1459:825 */      q localq2 = new q();
/* 1460:826 */      boolean bool = mI.a(localmx1.a);
/* 1461:827 */      for (int j = 0; j < Element.DIRECTIONSi.length; j++) {
/* 1462:    */        Object localObject;
/* 1463:829 */        (localObject = new q(Element.DIRECTIONSi[j])).a(localq1);
/* 1464:    */        com.bulletphysics.linearmath.Transform localTransform;
/* 1465:831 */        (localTransform = new com.bulletphysics.linearmath.Transform()).setIdentity();
/* 1466:    */        
/* 1467:833 */        if (bool) {
/* 1468:834 */          org.schema.game.common.data.world.Universe.calcSecPos(localq1, (q)localObject, this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface.getController().calculateStartTime(), System.currentTimeMillis(), localTransform);
/* 1469:    */        } else {
/* 1470:836 */          localTransform.origin.set(Element.DIRECTIONSi[j].jdField_a_of_type_Int, Element.DIRECTIONSi[j].jdField_b_of_type_Int, Element.DIRECTIONSi[j].jdField_c_of_type_Int);
/* 1471:837 */          localTransform.origin.scale(org.schema.game.common.data.world.Universe.getSectorSizeWithMargin());
/* 1472:    */        }
/* 1473:    */        
/* 1475:841 */        (localObject = new javax.vecmath.Vector3f()).sub(localVector3f1, localTransform.origin);
/* 1476:    */        
/* 1479:845 */        if (((javax.vecmath.Vector3f)localObject).lengthSquared() < localVector3f2.lengthSquared()) {
/* 1480:846 */          localVector3f2.set((Tuple3f)localObject);
/* 1481:847 */          i = j;
/* 1482:    */        }
/* 1483:    */      }
/* 1484:    */      
/* 1485:851 */      if (i >= 0) {
/* 1486:852 */        localq2.b(localq1);
/* 1487:853 */        localq2.a(Element.DIRECTIONSi[i]);
/* 1488:    */      }
/* 1489:    */      else {
/* 1490:856 */        return;
/* 1491:    */      }
/* 1492:    */      try
/* 1493:    */      {
/* 1494:860 */        if (((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().isSectorLoaded(localq2)) {
/* 1495:861 */          mx localmx2 = ((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().getSector(localq2);
/* 1496:862 */          L.a(this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface, localmx1, localmx2, localVector3f1, paramVector3f);
/* 1497:863 */          c();
/* 1498:864 */          this.jdField_a_of_type_Int = localmx2.a();
/* 1499:865 */          f();
/* 1500:    */          
/* 1502:868 */          return;
/* 1503:    */        }
/* 1504:    */        
/* 1505:    */      }
/* 1506:    */      catch (Exception localException)
/* 1507:    */      {
/* 1508:874 */        
/* 1509:    */        
/* 1510:876 */          localException;
/* 1511:    */      }
/* 1512:    */    }
/* 1513:    */    else
/* 1514:    */    {
/* 1515:879 */      System.err.println("[SERVER][PROJECTILE] Stopping projectile: out of loaded sector range");
/* 1516:    */    }
/* 1517:    */    
/* 1518:882 */    this.jdField_a_of_type_Boolean = false;
/* 1519:    */  }
/* 1520:    */  
/* 1523:887 */  public final java.util.ArrayList a() { return this.jdField_d_of_type_JavaUtilArrayList; }
/* 1524:    */  
/* 1525:    */  public final void a(t paramt) {
/* 1526:890 */    for (int i = 0; i < this.jdField_a_of_type_JavaUtilArrayList.size(); i++) {
/* 1527:891 */      lw locallw = (lw)this.jdField_a_of_type_JavaUtilArrayList.get(i);
/* 1528:892 */      paramt.a().missileUpdateBuffer.add(new RemoteMissileUpdate(locallw, this.jdField_b_of_type_Boolean));
/* 1529:    */    }
/* 1530:    */  }
/* 1531:    */  
/* 1532:    */  public final void b(t paramt)
/* 1533:    */  {
/* 1534:898 */    for (int i = 0; i < this.jdField_b_of_type_JavaUtilArrayList.size(); i++) {
/* 1535:899 */      lw locallw = (lw)this.jdField_b_of_type_JavaUtilArrayList.get(i);
/* 1536:900 */      paramt.a().missileUpdateBuffer.add(new RemoteMissileUpdate(locallw, this.jdField_b_of_type_Boolean));
/* 1537:    */      
/* 1538:902 */      System.err.println("[SERVER] sent missile update BB " + locallw);
/* 1539:    */    }
/* 1540:    */  }
/* 1541:    */  
/* 1544:    */  public final short a()
/* 1545:    */  {
/* 1546:910 */    return this.jdField_a_of_type_Short;
/* 1547:    */  }
/* 1548:    */  
/* 1551:    */  public final void a(short paramShort)
/* 1552:    */  {
/* 1553:917 */    this.jdField_a_of_type_Short = paramShort;
/* 1554:    */  }
/* 1555:    */  
/* 1558:    */  public final int b()
/* 1559:    */  {
/* 1560:924 */    return this.jdField_a_of_type_Int;
/* 1561:    */  }
/* 1562:    */  
/* 1565:    */  public final void b(int paramInt)
/* 1566:    */  {
/* 1567:931 */    this.jdField_a_of_type_Int = paramInt;
/* 1568:932 */    this.jdField_d_of_type_Int = paramInt;
/* 1569:    */  }
/* 1570:    */  
/* 1575:    */  public final com.bulletphysics.linearmath.Transform b()
/* 1576:    */  {
/* 1577:941 */    return this.jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/* 1578:    */  }
/* 1579:    */  
/* 1580:    */  public void a(lu paramlu) {
/* 1581:945 */    this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/* 1582:946 */    this.jdField_a_of_type_Short = paramlu.jdField_a_of_type_Short;
/* 1583:947 */    this.jdField_a_of_type_Int = paramlu.jdField_b_of_type_Int;
/* 1584:948 */    if ((!jdField_c_of_type_Boolean) && (paramlu.jdField_a_of_type_Byte != a())) throw new AssertionError(paramlu.jdField_a_of_type_Byte + " --- " + a());
/* 1585:949 */    this.jdField_a_of_type_JavaxVecmathVector3f.set(paramlu.jdField_b_of_type_JavaxVecmathVector3f);
/* 1586:950 */    this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin.set(paramlu.jdField_a_of_type_JavaxVecmathVector3f);
/* 1587:    */  }
/* 1588:    */  
/* 1589:    */  public abstract byte a();
/* 1590:    */  
/* 1591:    */  public final boolean b()
/* 1592:    */  {
/* 1593:957 */    return !this.jdField_a_of_type_JavaUtilArrayList.isEmpty();
/* 1594:    */  }
/* 1595:    */  
/* 1596:    */  public final boolean c() {
/* 1597:961 */    return !this.jdField_b_of_type_JavaUtilArrayList.isEmpty();
/* 1598:    */  }
/* 1599:    */  
/* 1600:    */  public final void e() {
/* 1601:965 */    this.jdField_a_of_type_JavaUtilArrayList.clear();
/* 1602:966 */    this.jdField_b_of_type_JavaUtilArrayList.clear();
/* 1603:    */  }
/* 1604:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ln
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */