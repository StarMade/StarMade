import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.physics.KinematicCharacterControllerExt;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.Universe;
import org.schema.schine.ai.stateMachines.FSMException;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

public final class class_1149
  extends class_1254
{
  private final Vector3f jdField_field_128_of_type_JavaxVecmathVector3f = new Vector3f();
  private final Vector3f field_162 = new Vector3f();
  private Vector3f field_158 = new Vector3f();
  private float jdField_field_128_of_type_Float = -1.0F;
  private static final long serialVersionUID = 1L;
  
  public class_1149(class_981 paramclass_981)
  {
    super(paramclass_981);
  }
  
  public final Vector3f a4()
  {
    return this.jdField_field_128_of_type_JavaxVecmathVector3f;
  }
  
  public final Vector3f b2()
  {
    return this.field_162;
  }
  
  private static boolean a3(SegmentController paramSegmentController1, SegmentController paramSegmentController2)
  {
    return (paramSegmentController1.getDockingController().b3()) && (paramSegmentController1.getDockingController().a4().field_1740.a7().a15() == paramSegmentController2);
  }
  
  public final boolean c()
  {
    this.jdField_field_128_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
    try
    {
      d();
    }
    catch (FSMException localFSMException)
    {
      localFSMException;
    }
    return false;
  }
  
  public final boolean b()
  {
    return false;
  }
  
  public final boolean d()
  {
    class_797 localclass_797;
    if ((localclass_797 = ((class_1256)a6().field_223).a7()) != null)
    {
      localclass_797.calcWorldTransformRelative(((SegmentController)a7()).getSectorId(), ((class_1041)((SegmentController)a7()).getState()).a62().getSector(((SegmentController)a7()).getSectorId()).field_136);
      Object localObject2;
      if (localclass_797 != a7())
      {
        localObject2 = localclass_797;
        localObject1 = this;
        if ((localObject2 instanceof SegmentController)) {}
        ((class_1149)localObject1).field_158.sub(((class_797)localObject2).getClientTransform().origin, ((SegmentController)((class_1149)localObject1).a7()).getWorldTransform().origin);
        if ((((class_1149)localObject1).field_158.length() > ((class_1149)localObject1).a6().b() ? 0 : ((localObject2 instanceof class_747)) && (((class_747)localObject2).c3()) && (((class_1149)localObject1).field_158.length() > ((class_1149)localObject1).a6().b() / 2.0F) ? 0 : ((localObject2 instanceof class_747)) && (((class_747)localObject2).a7()) ? 0 : a3((SegmentController)localObject2, (SegmentController)((class_1149)localObject1).a7()) ? 0 : a3((SegmentController)((class_1149)localObject1).a7(), (SegmentController)localObject2) ? 0 : ((class_797)localObject2).isHidden() ? 0 : ((class_797)localObject2).getPhysicsDataContainer().getObject() == null ? 0 : 1) != 0) {}
      }
      else
      {
        a12(new class_1115());
        return false;
      }
      Object localObject1 = null;
      if ((localclass_797.getPhysicsDataContainer().getObject() instanceof RigidBody)) {
        localObject1 = ((RigidBody)localclass_797.getPhysicsDataContainer().getObject()).getLinearVelocity(new Vector3f());
      } else if ((localclass_797 instanceof class_750)) {
        localObject1 = ((class_750)localclass_797).a144().getLinearVelocity(new Vector3f());
      }
      if (localObject1 != null)
      {
        localObject2 = new Vector3f(localclass_797.getClientTransform().origin);
        Vector3f localVector3f;
        (localVector3f = new Vector3f(((SegmentController)a7()).getWorldTransform().origin)).sub((Tuple3f)localObject2);
        class_981 localclass_981 = ((class_991)a7()).a().a();
        float f = 10.0F;
        if ((localclass_981 instanceof class_1260)) {
          f = ((class_1260)localclass_981).a32();
        }
        if (((localclass_797 instanceof class_747)) && (((class_747)localclass_797).c3())) {
          f = Math.max(1.0F, f * 0.1F);
        }
        Object tmp463_462 = localObject2;
        tmp463_462.field_615 = ((float)(tmp463_462.field_615 + (Math.random() - 0.5D) * (localVector3f.length() / f)));
        Object tmp491_490 = localObject2;
        tmp491_490.field_616 = ((float)(tmp491_490.field_616 + (Math.random() - 0.5D) * (localVector3f.length() / f)));
        Object tmp519_518 = localObject2;
        tmp519_518.field_617 = ((float)(tmp519_518.field_617 + (Math.random() - 0.5D) * (localVector3f.length() / f)));
        this.jdField_field_128_of_type_JavaxVecmathVector3f.set((Tuple3f)localObject2);
        this.field_162.set((Tuple3f)localObject1);
      }
      else
      {
        a12(new class_1115());
        return false;
      }
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1149
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */