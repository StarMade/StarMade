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

public final class class_1228
  extends class_1254
{
  private final Vector3f field_128 = new Vector3f();
  private Vector3f field_162 = new Vector3f();
  private static final long serialVersionUID = 1L;
  
  public class_1228(class_981 paramclass_981)
  {
    super(paramclass_981);
  }
  
  public final Vector3f a4()
  {
    return this.field_128;
  }
  
  private static boolean a3(SegmentController paramSegmentController1, SegmentController paramSegmentController2)
  {
    return (paramSegmentController1.getDockingController().b3()) && (paramSegmentController1.getDockingController().a4().field_1740.a7().a15() == paramSegmentController2);
  }
  
  public final boolean c()
  {
    this.field_128.set(0.0F, 0.0F, 0.0F);
    return false;
  }
  
  public final boolean b()
  {
    return false;
  }
  
  public final boolean d()
  {
    Object localObject1;
    if ((localObject1 = ((class_1256)a6().field_223).a7()) != null)
    {
      ((class_797)localObject1).calcWorldTransformRelative(((SegmentController)a7()).getSectorId(), ((class_1041)((SegmentController)a7()).getState()).a62().getSector(((SegmentController)a7()).getSectorId()).field_136);
      Object localObject3 = localObject1;
      Object localObject2 = this;
      if ((localObject3 instanceof SegmentController)) {}
      ((class_1228)localObject2).field_162.sub(((class_797)localObject3).getClientTransform().origin, ((class_797)((class_1228)localObject2).a6().a()).getWorldTransform().origin);
      if ((((class_1228)localObject2).field_162.length() > ((class_1228)localObject2).a6().b() ? 0 : ((localObject3 instanceof class_747)) && (((class_747)localObject3).c3()) && (((class_1228)localObject2).field_162.length() > ((class_1228)localObject2).a6().b() / 2.0F) ? 0 : ((localObject3 instanceof class_747)) && (((class_747)localObject3).a7()) ? 0 : ((localObject3 instanceof class_365)) && (!(localObject3 instanceof class_750)) && (((class_365)localObject3).a26().isEmpty()) && (!((class_991)localObject3).a().a1()) ? 0 : ((localObject3 instanceof class_750)) && (((class_750)localObject3).isHidden()) ? 0 : a3((SegmentController)localObject3, (SegmentController)((class_1228)localObject2).a7()) ? 0 : a3((SegmentController)((class_1228)localObject2).a7(), (SegmentController)localObject3) ? 0 : 1) == 0)
      {
        a12(new class_1115());
        return false;
      }
      localObject2 = ((SegmentController)a7()).getWorldTransform().origin;
      localObject1 = new Vector3f(((class_797)localObject1).getClientTransform().origin);
      this.field_128.sub((Tuple3f)localObject1, (Tuple3f)localObject2);
      (localObject1 = new Vector3f(this.field_128)).normalize();
      localObject2 = GlUtil.c(new Vector3f(), ((SegmentController)a7()).getWorldTransform());
      (localObject3 = new Vector3f((Vector3f)localObject2)).negate();
      ((Vector3f)localObject3).sub((Tuple3f)localObject1);
      new Vector3f((Vector3f)localObject2).sub((Tuple3f)localObject1);
      if (((Vector3f)localObject1).epsilonEquals((Tuple3f)localObject2, 1.0F))
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
 * Qualified Name:     class_1228
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */