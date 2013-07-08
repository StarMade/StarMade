import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.Universe;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_1137
  extends class_1254
{
  private final Vector3f jdField_field_128_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f field_162 = new Vector3f();
  private long jdField_field_128_of_type_Long;
  private static final long serialVersionUID = 1L;
  
  public class_1137(class_981 paramclass_981)
  {
    super(paramclass_981);
  }
  
  public final Vector3f a4()
  {
    return this.jdField_field_128_of_type_JavaxVecmathVector3f;
  }
  
  private static boolean a3(SegmentController paramSegmentController1, SegmentController paramSegmentController2)
  {
    return (paramSegmentController1.getDockingController().a4() != null) && (paramSegmentController1.getDockingController().a4().field_1740.a7().a15() == paramSegmentController2);
  }
  
  public final boolean c()
  {
    this.jdField_field_128_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
    this.jdField_field_128_of_type_Long = System.currentTimeMillis();
    return false;
  }
  
  public final boolean b()
  {
    return false;
  }
  
  public final boolean d()
  {
    if (System.currentTimeMillis() - this.jdField_field_128_of_type_Long > 5000L)
    {
      a12(new class_1115());
      return false;
    }
    Object localObject1;
    if ((localObject1 = ((class_1256)a6().field_223).a7()) != null)
    {
      ((class_797)localObject1).calcWorldTransformRelative(((SegmentController)a7()).getSectorId(), ((class_1041)((SegmentController)a7()).getState()).a62().getSector(((SegmentController)a7()).getSectorId()).field_136);
      Object localObject3 = localObject1;
      Object localObject2 = this;
      if ((localObject3 instanceof SegmentController)) {}
      System.err.println("[AI][TURRET] Dead Entity. Getting new Target");
      ((class_1137)localObject2).field_162.sub(((class_797)localObject3).getClientTransform().origin, ((class_797)((class_1137)localObject2).a6().a()).getWorldTransform().origin);
      if ((((class_1137)localObject2).field_162.length() > ((class_1137)localObject2).a6().b() ? 0 : ((localObject3 instanceof class_747)) && (((class_747)localObject3).c3()) && (((class_1137)localObject2).field_162.length() > ((class_1137)localObject2).a6().b() / 2.0F) && (!((class_1137)localObject2).a5().a183(class_776.field_1032).a171().equals("Selected Target")) ? 0 : ((localObject3 instanceof class_365)) && (!(localObject3 instanceof class_750)) && (((class_365)localObject3).a26().isEmpty()) && (!((class_991)localObject3).a().a1()) ? 0 : ((localObject3 instanceof class_750)) && (((class_750)localObject3).isHidden()) ? 0 : ((localObject3 instanceof class_747)) && (((class_747)localObject3).a7()) ? 0 : a3((SegmentController)localObject3, (SegmentController)((class_1137)localObject2).a7()) ? 0 : a3((SegmentController)((class_1137)localObject2).a7(), (SegmentController)localObject3) ? 0 : 1) == 0)
      {
        a12(new class_1115());
        return false;
      }
      localObject2 = ((SegmentController)a7()).getWorldTransform().origin;
      localObject1 = new Vector3f(((class_797)localObject1).getClientTransform().origin);
      this.jdField_field_128_of_type_JavaxVecmathVector3f.sub((Tuple3f)localObject1, (Tuple3f)localObject2);
      (localObject1 = new Vector3f(this.jdField_field_128_of_type_JavaxVecmathVector3f)).normalize();
      localObject2 = GlUtil.c(new Vector3f(), ((SegmentController)a7()).getWorldTransform());
      (localObject3 = new Vector3f((Vector3f)localObject2)).negate();
      ((Vector3f)localObject3).sub((Tuple3f)localObject1);
      new Vector3f((Vector3f)localObject2).sub((Tuple3f)localObject1);
      if (((Vector3f)localObject1).epsilonEquals((Tuple3f)localObject2, 0.4F))
      {
        a12(new class_1118());
        return true;
      }
    }
    else
    {
      System.err.println("[AI] " + a7() + " HAS NO TARGET: resetting");
      a12(new class_1115());
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1137
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */