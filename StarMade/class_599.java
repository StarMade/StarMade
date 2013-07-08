import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.world.Universe;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.Sendable;

public final class class_599
  extends class_609
{
  private float field_172 = 0.0F;
  
  public class_599(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public final void c4(class_941 paramclass_941)
  {
    class_670 localclass_670 = null;
    if (this.field_172)
    {
      if (this.field_172 > 1.0F)
      {
        paramclass_941 = this;
        Object localObject1 = null;
        float f = 0.0F;
        Vector3f localVector3f = new Vector3f();
        Object localObject2 = paramclass_941.getState().getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          Object localObject3;
          if (((localObject3 = (Sendable)((Iterator)localObject2).next()) != paramclass_941.jdField_field_173_of_type_Class_809) && ((localObject3 instanceof class_797)))
          {
            class_797 localclass_797 = (class_797)localObject3;
            Object localObject4 = paramclass_941;
            if (((localclass_797 instanceof class_750)) || ((localclass_797 instanceof class_737)) || ((localclass_797 instanceof class_747)))
            {
              localclass_670 = ((class_1041)((class_599)localObject4).getState()).a62().getSector(((class_597)localObject4).jdField_field_173_of_type_Int);
              localclass_797.calcWorldTransformRelative(localclass_670.a3(), localclass_670.field_136);
            }
            if ((((localObject4 = ((class_1041)((class_599)localObject4).getState()).a62().getSector(localclass_797.getSectorId())) != null) && (localclass_670 != null) && (Math.abs(((class_670)localObject4).field_136.field_475 - localclass_670.field_136.field_475) < 2) && (Math.abs(((class_670)localObject4).field_136.field_476 - localclass_670.field_136.field_476) < 2) && (Math.abs(((class_670)localObject4).field_136.field_477 - localclass_670.field_136.field_477) < 2) ? 1 : localclass_797.getSectorId() == ((class_597)localObject4).jdField_field_173_of_type_Int ? 1 : 0) != 0) {
              if (!(localObject3 = (class_797)localObject3).isHidden())
              {
                localVector3f.sub(((class_797)localObject3).getClientTransform().origin, paramclass_941.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform.origin);
                if ((localObject1 == null) || (localVector3f.length() < f))
                {
                  localObject1 = localObject3;
                  f = localVector3f.length();
                }
              }
            }
          }
        }
        if (localObject1 != paramclass_941.a23())
        {
          paramclass_941.jdField_field_173_of_type_Class_797 = localObject1;
          (localObject2 = new class_613(paramclass_941.jdField_field_173_of_type_Short)).field_179 = (paramclass_941.a23() == null ? -1 : paramclass_941.a23().getId());
          System.err.println("[SERVER][MISSILE] adding target update " + paramclass_941.a23() + " -> " + ((class_613)localObject2).field_179);
          paramclass_941.jdField_field_173_of_type_JavaUtilArrayList.add(localObject2);
        }
        this.field_172 = 0.0F;
        return;
      }
      this.field_172 += paramclass_941.a();
    }
  }
  
  public final byte a21()
  {
    return 2;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_599
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */