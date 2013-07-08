/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import com.bulletphysics.util.ObjectPool;
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectAVLTreeSet;
/*   4:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   5:    */import java.io.IOException;
/*   6:    */import java.util.HashMap;
/*   7:    */import java.util.Iterator;
/*   8:    */import java.util.List;
/*   9:    */import java.util.Map.Entry;
/*  10:    */import java.util.Set;
/*  11:    */import javax.vecmath.Matrix3f;
/*  12:    */import javax.vecmath.Vector3f;
/*  13:    */import javax.vecmath.Vector4f;
/*  14:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  15:    */import org.schema.game.common.controller.elements.ShipManagerContainer;
/*  16:    */import org.schema.game.common.controller.elements.thrust.ThrusterElementManager;
/*  17:    */import org.schema.game.common.controller.elements.thrust.ThrusterUnit;
/*  18:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  19:    */import org.schema.schine.graphicsengine.forms.Mesh;
/*  20:    */
/*  40:    */public final class er
/*  41:    */  implements xg, zr
/*  42:    */{
/*  43:    */  private kd jdField_a_of_type_Kd;
/*  44:    */  private static zj jdField_a_of_type_Zj;
/*  45: 45 */  private static Matrix3f jdField_a_of_type_JavaxVecmathMatrix3f = new Matrix3f();
/*  46:    */  
/*  48: 48 */  private ObjectAVLTreeSet jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet = new ObjectAVLTreeSet();
/*  49:    */  
/*  50: 50 */  private float jdField_a_of_type_Float = 0.0F;
/*  51:    */  
/*  52: 52 */  private Vector4f jdField_a_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  53:    */  
/*  54: 54 */  private Vector4f jdField_b_of_type_JavaxVecmathVector4f = new Vector4f(0.0F, 0.0F, 1.0F, 1.0F);
/*  55: 55 */  private Vector4f c = new Vector4f();
/*  56: 56 */  private Vector4f d = new Vector4f();
/*  57: 57 */  public er(kd paramkd) { new Vector3f();
/*  58:    */    
/*  59: 59 */    this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*  60:    */    
/*  62: 62 */    this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 0.0F, -0.5F);
/*  63:    */    
/*  64: 64 */    this.jdField_b_of_type_Boolean = true;
/*  65:    */    
/*  76: 76 */    this.jdField_a_of_type_Q = new q();
/*  77:    */    
/*  78: 78 */    this.jdField_a_of_type_Long = -1L;
/*  79:    */    
/*  81: 81 */    this.jdField_a_of_type_ComBulletphysicsUtilObjectPool = ObjectPool.get(eF.class);
/*  82:    */    
/*  83: 83 */    this.jdField_a_of_type_Le = new le();
/*  84:    */    
/*  86: 86 */    this.jdField_a_of_type_Kd = paramkd;
/*  87:    */    
/*  88: 88 */    jdField_a_of_type_JavaxVecmathMatrix3f.rotY(3.141593F);
/*  89:    */  }
/*  90:    */  
/*  94:    */  public final void a() {}
/*  95:    */  
/*  99:    */  public final void b()
/* 100:    */  {
/* 101:101 */    if (this.jdField_b_of_type_Boolean) {
/* 102:102 */      jdField_a_of_type_Zj = zk.s;
/* 103:    */    }
/* 104:104 */    if (this.jdField_a_of_type_Kd.a()) {
/* 105:105 */      return;
/* 106:    */    }
/* 107:107 */    if (!this.jdField_a_of_type_Boolean)
/* 108:    */    {
/* 109:109 */      if (this.jdField_a_of_type_Kd.getWorldTransform() != null)
/* 110:    */      {
/* 111:111 */        this.jdField_a_of_type_Long = System.currentTimeMillis();
/* 112:112 */        this.jdField_a_of_type_Boolean = true;
/* 113:    */      }
/* 114:114 */      return;
/* 115:    */    }
/* 116:    */    
/* 117:117 */    int i = 1;
/* 118:    */    
/* 122:122 */    ObjectBidirectionalIterator localObjectBidirectionalIterator = this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.iterator();
/* 123:123 */    while (localObjectBidirectionalIterator.hasPrevious())
/* 124:    */    {
/* 126:126 */      ((eF)localObjectBidirectionalIterator.previous()).a(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform, this.jdField_a_of_type_JavaxVecmathVector3f);
/* 127:127 */      this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.basis.mul(jdField_a_of_type_JavaxVecmathMatrix3f);
/* 128:    */      
/* 129:129 */      if ((GlUtil.a(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin, xe.a.a())) && (GlUtil.a(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform, eG.a))) {
/* 130:130 */        if (i != 0)
/* 131:    */        {
/* 134:134 */          er localer = this;float f1 = Math.min(0.99F, localer.jdField_a_of_type_Kd.a().length() / localer.jdField_a_of_type_Kd.a());localer.c.set(localer.jdField_a_of_type_JavaxVecmathVector4f);localer.d.set(localer.jdField_b_of_type_JavaxVecmathVector4f);localer.c.scale(f1);localer.d.scale(f1);localer.d.x = (0.5F - f1 / 2.0F);localer.d.z = f1; if (!e.equals(localer.c)) { GlUtil.a(jdField_a_of_type_Zj, "thrustColor0", localer.c);e.set(localer.c); } if (!f.equals(localer.d)) { GlUtil.a(jdField_a_of_type_Zj, "thrustColor1", localer.d);f.set(localer.d); } GlUtil.a(jdField_a_of_type_Zj, "ticks", localer.jdField_a_of_type_Float);
/* 135:135 */          int j = 0;
/* 136:    */        }
/* 137:    */        
/* 138:138 */        GlUtil.d();
/* 139:139 */        GlUtil.b(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 140:140 */        ((Mesh)eG.a.a().get(0)).f();
/* 141:141 */        GlUtil.c();
/* 142:    */      }
/* 143:    */    }
/* 144:    */  }
/* 145:    */  
/* 153:    */  public final kd a()
/* 154:    */  {
/* 155:155 */    return this.jdField_a_of_type_Kd;
/* 156:    */  }
/* 157:    */  
/* 163:    */  public final void d() {}
/* 164:    */  
/* 169:    */  public final void c()
/* 170:    */  {
/* 171:171 */    jdField_a_of_type_Zj = zk.s;
/* 172:    */  }
/* 173:    */  
/* 183:    */  private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/* 184:    */  
/* 193:    */  private boolean jdField_a_of_type_Boolean;
/* 194:    */  
/* 203:    */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f;
/* 204:    */  
/* 213:    */  private boolean jdField_b_of_type_Boolean;
/* 214:    */  
/* 223:    */  private q jdField_a_of_type_Q;
/* 224:    */  
/* 233:    */  private long jdField_a_of_type_Long;
/* 234:    */  
/* 242:    */  private ObjectPool jdField_a_of_type_ComBulletphysicsUtilObjectPool;
/* 243:    */  
/* 251:    */  private le jdField_a_of_type_Le;
/* 252:    */  
/* 260:    */  public final void e()
/* 261:    */  {
/* 262:262 */    this.jdField_a_of_type_Long = System.currentTimeMillis(); }
/* 263:    */  
/* 264:    */  public final void a(xq paramxq) {
/* 265:265 */    if ((this.jdField_a_of_type_Long > 0L) && (System.currentTimeMillis() - this.jdField_a_of_type_Long > 500L)) {
/* 266:266 */      er localer = this; Iterator localIterator; Object localObject1; synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet) { for (localIterator = localer.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.iterator(); localIterator.hasNext(); localer.jdField_a_of_type_ComBulletphysicsUtilObjectPool.release(localObject1)) (localObject1 = (eF)localIterator.next()).a(); localer.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.clear(); for (localIterator = localer.jdField_a_of_type_Kd.a().getThrusterElementManager().getCollection().getCollection().iterator(); localIterator.hasNext();) { Object localObject2; for (localObject1 = ((ThrusterUnit)localIterator.next()).getLastElements().entrySet().iterator(); ((Iterator)localObject1).hasNext(); localer.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.add(localObject2)) { Map.Entry localEntry = (Map.Entry)((Iterator)localObject1).next();localer.jdField_a_of_type_Q.b((q)localEntry.getValue());localer.jdField_a_of_type_Q.c -= 1;localObject2 = null; try { localObject2 = localer.jdField_a_of_type_Kd.getSegmentBuffer().a(localer.jdField_a_of_type_Q, false, localer.jdField_a_of_type_Le); } catch (IOException localIOException) { localIOException; } catch (InterruptedException localInterruptedException) { localInterruptedException; } if ((localObject2 == null) || (((le)localObject2).a() == 0)) (localObject2 = (eF)localer.jdField_a_of_type_ComBulletphysicsUtilObjectPool.get()).a(localer.jdField_a_of_type_Kd, (q)localEntry.getValue()); } } }
/* 267:267 */      this.jdField_a_of_type_Long = -1L;
/* 268:    */    }
/* 269:269 */    this.jdField_a_of_type_Float = ((float)(this.jdField_a_of_type_Float + paramxq.a() / 100.0F * ((Math.random() + 9.999999747378752E-005D) / 0.1000000014901161D)));
/* 270:270 */    if (this.jdField_a_of_type_Float > 1.0F) {
/* 271:271 */      this.jdField_a_of_type_Float = 0.0F;
/* 272:    */    }
/* 273:    */  }
/* 274:    */  
/* 275:275 */  private static Vector4f e = new Vector4f();
/* 276:276 */  private static Vector4f f = new Vector4f();
/* 277:    */  
/* 278:    */  public final void a(zj paramzj) {}
/* 279:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     er
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */