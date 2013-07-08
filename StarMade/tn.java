/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import java.io.PrintStream;
/*   3:    */import java.util.ArrayList;
/*   4:    */import javax.vecmath.Tuple3f;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import org.schema.game.common.controller.SegmentController;
/*   7:    */import org.schema.game.common.data.element.ElementDocking;
/*   8:    */import org.schema.game.common.data.world.Segment;
/*   9:    */import org.schema.game.common.data.world.Universe;
/*  10:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  11:    */
/*  22:    */public final class tn
/*  23:    */  extends sM
/*  24:    */{
/*  25: 25 */  private final Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  26: 26 */  private Vector3f b = new Vector3f();
/*  27:    */  
/*  28:    */  private long jdField_a_of_type_Long;
/*  29:    */  
/*  30:    */  private static final long serialVersionUID = 1L;
/*  31:    */  
/*  33:    */  public tn(wk paramwk)
/*  34:    */  {
/*  35: 35 */    super(paramwk);
/*  36:    */  }
/*  37:    */  
/*  79:    */  public final Vector3f a()
/*  80:    */  {
/*  81: 81 */    return this.jdField_a_of_type_JavaxVecmathVector3f;
/*  82:    */  }
/*  83:    */  
/*  84:    */  private static boolean a(SegmentController paramSegmentController1, SegmentController paramSegmentController2) {
/*  85: 85 */    return (paramSegmentController1.getDockingController().a() != null) && (paramSegmentController1.getDockingController().a().to.a().a() == paramSegmentController2);
/*  86:    */  }
/*  87:    */  
/*  88:    */  public final boolean c() {
/*  89: 89 */    this.jdField_a_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
/*  90: 90 */    this.jdField_a_of_type_Long = System.currentTimeMillis();
/*  91: 91 */    return false;
/*  92:    */  }
/*  93:    */  
/*  94:    */  public final boolean b() {
/*  95: 95 */    return false;
/*  96:    */  }
/*  97:    */  
/*  98:    */  public final boolean d() {
/*  99: 99 */    if (System.currentTimeMillis() - this.jdField_a_of_type_Long > 5000L) {
/* 100:100 */      a(new tx());
/* 101:101 */      return false;
/* 102:    */    }
/* 103:    */    
/* 104:    */    Object localObject1;
/* 105:105 */    if ((localObject1 = ((sL)a().a).a()) != null)
/* 106:    */    {
/* 107:107 */      ((mF)localObject1).calcWorldTransformRelative(((SegmentController)a()).getSectorId(), ((vg)((SegmentController)a()).getState()).a().getSector(((SegmentController)a()).getSectorId()).a);
/* 108:108 */      Object localObject3 = localObject1;Object localObject2 = this; if ((localObject3 instanceof SegmentController)) {} System.err.println("[AI][TURRET] Dead Entity. Getting new Target");((tn)localObject2).b.sub(((mF)localObject3).getClientTransform().origin, ((mF)((tn)localObject2).a().a()).getWorldTransform().origin); if ((((tn)localObject2).b.length() > ((tn)localObject2).a().b() ? 0 : ((localObject3 instanceof kd)) && (((kd)localObject3).c()) && (((tn)localObject2).b.length() > ((tn)localObject2).a().b() / 2.0F) && (!((tn)localObject2).a().a(kq.a).a().equals("Selected Target")) ? 0 : ((localObject3 instanceof cw)) && (!(localObject3 instanceof lD)) && (((cw)localObject3).a().isEmpty()) && (!((wp)localObject3).a().a()) ? 0 : ((localObject3 instanceof lD)) && (((lD)localObject3).isHidden()) ? 0 : ((localObject3 instanceof kd)) && (((kd)localObject3).a()) ? 0 : a((SegmentController)localObject3, (SegmentController)((tn)localObject2).a()) ? 0 : a((SegmentController)((tn)localObject2).a(), (SegmentController)localObject3) ? 0 : 1) == 0) {
/* 109:109 */        a(new tx());
/* 110:110 */        return false;
/* 111:    */      }
/* 112:112 */      localObject2 = ((SegmentController)a()).getWorldTransform().origin;
/* 113:    */      
/* 115:115 */      localObject1 = new Vector3f(((mF)localObject1).getClientTransform().origin);
/* 116:    */      
/* 117:117 */      this.jdField_a_of_type_JavaxVecmathVector3f.sub((Tuple3f)localObject1, (Tuple3f)localObject2);
/* 118:    */      
/* 119:119 */      (
/* 120:120 */        localObject1 = new Vector3f(this.jdField_a_of_type_JavaxVecmathVector3f)).normalize();
/* 121:121 */      localObject2 = GlUtil.c(new Vector3f(), ((SegmentController)a()).getWorldTransform());
/* 122:    */      
/* 123:123 */      (
/* 124:124 */        localObject3 = new Vector3f((Vector3f)localObject2)).negate();
/* 125:125 */      ((Vector3f)localObject3).sub((Tuple3f)localObject1);
/* 126:126 */      new Vector3f((Vector3f)localObject2)
/* 127:127 */        .sub((Tuple3f)localObject1);
/* 128:    */      
/* 129:129 */      if (((Vector3f)localObject1).epsilonEquals((Tuple3f)localObject2, 0.4F)) {
/* 130:130 */        a(new tu());
/* 131:131 */        return true;
/* 132:    */      }
/* 133:    */    } else {
/* 134:134 */      System.err.println("[AI] " + a() + " HAS NO TARGET: resetting");
/* 135:135 */      a(new tx());
/* 136:    */    }
/* 137:137 */    return false;
/* 138:    */  }
/* 139:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     tn
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */