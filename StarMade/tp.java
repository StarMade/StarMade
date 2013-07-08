/*   1:    */import com.bulletphysics.dynamics.RigidBody;
/*   2:    */import com.bulletphysics.linearmath.Transform;
/*   3:    */import javax.vecmath.Tuple3f;
/*   4:    */import javax.vecmath.Vector3f;
/*   5:    */import org.schema.game.common.controller.SegmentController;
/*   6:    */import org.schema.game.common.data.element.ElementDocking;
/*   7:    */import org.schema.game.common.data.physics.KinematicCharacterControllerExt;
/*   8:    */import org.schema.game.common.data.world.Segment;
/*   9:    */import org.schema.game.common.data.world.Universe;
/*  10:    */import org.schema.schine.ai.stateMachines.FSMException;
/*  11:    */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*  12:    */
/*  29:    */public final class tp
/*  30:    */  extends sM
/*  31:    */{
/*  32: 32 */  private final Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  33: 33 */  private final Vector3f b = new Vector3f();
/*  34: 34 */  private Vector3f c = new Vector3f();
/*  35: 35 */  private float jdField_a_of_type_Float = -1.0F;
/*  36:    */  
/*  37:    */  private static final long serialVersionUID = 1L;
/*  38:    */  
/*  40:    */  public tp(wk paramwk)
/*  41:    */  {
/*  42: 42 */    super(paramwk);
/*  43:    */  }
/*  44:    */  
/*  81:    */  public final Vector3f a()
/*  82:    */  {
/*  83: 83 */    return this.jdField_a_of_type_JavaxVecmathVector3f;
/*  84:    */  }
/*  85:    */  
/*  86: 86 */  public final Vector3f b() { return this.b; }
/*  87:    */  
/*  88:    */  private static boolean a(SegmentController paramSegmentController1, SegmentController paramSegmentController2) {
/*  89: 89 */    return (paramSegmentController1.getDockingController().b()) && (paramSegmentController1.getDockingController().a().to.a().a() == paramSegmentController2);
/*  90:    */  }
/*  91:    */  
/*  92:    */  public final boolean c() {
/*  93: 93 */    this.jdField_a_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
/*  94:    */    
/* 111:    */    try
/* 112:    */    {
/* 113:113 */      d();
/* 114:114 */    } catch (FSMException localFSMException) { 
/* 115:    */      
/* 116:116 */        localFSMException;
/* 117:    */    }
/* 118:    */    
/* 119:117 */    return false;
/* 120:    */  }
/* 121:    */  
/* 122:    */  public final boolean b() {
/* 123:121 */    return false;
/* 124:    */  }
/* 125:    */  
/* 127:    */  public final boolean d()
/* 128:    */  {
/* 129:    */    mF localmF;
/* 130:128 */    if ((localmF = ((sL)a().a).a()) != null)
/* 131:    */    {
/* 132:130 */      localmF.calcWorldTransformRelative(((SegmentController)a()).getSectorId(), ((vg)((SegmentController)a()).getState()).a().getSector(((SegmentController)a()).getSectorId()).a);
/* 133:    */      Object localObject2;
/* 134:132 */      if (localmF != a()) { localObject2 = localmF;localObject1 = this; if ((localObject2 instanceof SegmentController)) {} ((tp)localObject1).c.sub(((mF)localObject2).getClientTransform().origin, ((SegmentController)((tp)localObject1).a()).getWorldTransform().origin); if ((((tp)localObject1).c.length() > ((tp)localObject1).a().b() ? 0 : ((localObject2 instanceof kd)) && (((kd)localObject2).c()) && (((tp)localObject1).c.length() > ((tp)localObject1).a().b() / 2.0F) ? 0 : ((localObject2 instanceof kd)) && (((kd)localObject2).a()) ? 0 : a((SegmentController)localObject2, (SegmentController)((tp)localObject1).a()) ? 0 : a((SegmentController)((tp)localObject1).a(), (SegmentController)localObject2) ? 0 : ((mF)localObject2).isHidden() ? 0 : ((mF)localObject2).getPhysicsDataContainer().getObject() == null ? 0 : 1) != 0) {}
/* 135:133 */      } else { a(new tx());
/* 136:134 */        return false;
/* 137:    */      }
/* 138:136 */      Object localObject1 = null;
/* 139:137 */      if ((localmF.getPhysicsDataContainer().getObject() instanceof RigidBody))
/* 140:    */      {
/* 141:139 */        localObject1 = ((RigidBody)localmF.getPhysicsDataContainer().getObject()).getLinearVelocity(new Vector3f());
/* 145:    */      }
/* 146:144 */      else if ((localmF instanceof lD)) {
/* 147:145 */        localObject1 = ((lD)localmF).a().getLinearVelocity(new Vector3f());
/* 148:    */      }
/* 149:    */      
/* 150:148 */      if (localObject1 != null)
/* 151:    */      {
/* 155:153 */        localObject2 = new Vector3f(localmF.getClientTransform().origin);
/* 156:    */        
/* 157:    */        Vector3f localVector3f;
/* 158:156 */        (localVector3f = new Vector3f(((SegmentController)a()).getWorldTransform().origin)).sub((Tuple3f)localObject2);
/* 159:157 */        wk localwk = ((wp)a()).a().a();
/* 160:158 */        float f = 10.0F;
/* 161:159 */        if ((localwk instanceof sJ)) {
/* 162:160 */          f = ((sJ)localwk).a();
/* 163:    */        }
/* 164:162 */        if (((localmF instanceof kd)) && (((kd)localmF).c())) {
/* 165:163 */          f = Math.max(1.0F, f * 0.1F);
/* 166:    */        }
/* 167:    */        
/* 168:166 */        Object tmp463_462 = localObject2;tmp463_462.x = ((float)(tmp463_462.x + (Math.random() - 0.5D) * (localVector3f.length() / f))); Object 
/* 169:167 */          tmp491_490 = localObject2;tmp491_490.y = ((float)(tmp491_490.y + (Math.random() - 0.5D) * (localVector3f.length() / f))); Object 
/* 170:168 */          tmp519_518 = localObject2;tmp519_518.z = ((float)(tmp519_518.z + (Math.random() - 0.5D) * (localVector3f.length() / f)));
/* 171:    */        
/* 172:170 */        this.jdField_a_of_type_JavaxVecmathVector3f.set((Tuple3f)localObject2);
/* 173:171 */        this.b.set((Tuple3f)localObject1);
/* 174:    */      } else {
/* 175:173 */        a(new tx());
/* 176:174 */        return false;
/* 177:    */      }
/* 178:    */    }
/* 179:    */    
/* 180:178 */    return false;
/* 181:    */  }
/* 182:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     tp
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */