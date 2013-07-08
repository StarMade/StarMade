import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.Universe;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteInteger;

public final class class_1135
  extends class_1254
{
  private Vector3f jdField_field_128_of_type_JavaxVecmathVector3f = new Vector3f();
  private long jdField_field_128_of_type_Long = 0L;
  private ArrayList jdField_field_128_of_type_JavaUtilArrayList = new ArrayList();
  private static final long serialVersionUID = 1L;
  
  public class_1135(class_981 paramclass_981)
  {
    super(paramclass_981);
  }
  
  private static boolean a3(SegmentController paramSegmentController1, SegmentController paramSegmentController2)
  {
    return (paramSegmentController1.getDockingController().a4() != null) && (paramSegmentController1.getDockingController().a4().field_1740.a7().a15() == paramSegmentController2);
  }
  
  public final boolean c()
  {
    ((class_1256)a6().field_223).a8(null);
    return false;
  }
  
  public final boolean b()
  {
    return false;
  }
  
  public final boolean d()
  {
    class_1135 localclass_11351;
    Object localObject1;
    Object localObject2;
    Object localObject3;
    if (a5().a183(class_776.field_1032).a171().equals("Selected Target"))
    {
      localclass_11351 = this;
      if (((a6().a() instanceof SegmentController)) && (((SegmentController)localclass_11351.a6().a()).getDockingController().a4() != null) && (((localObject1 = ((SegmentController)localclass_11351.a6().a()).getDockingController().a4().field_1740.a7().a15()) instanceof class_365)))
      {
        class_365 localclass_365 = (class_365)localObject1;
        localObject2 = null;
        Iterator localIterator1 = localclass_365.a26().iterator();
        Object localObject4;
        while (localIterator1.hasNext())
        {
          localObject4 = (localObject3 = (class_748)localIterator1.next()).a124().a1().iterator();
          while (((Iterator)localObject4).hasNext())
          {
            class_755 localclass_755;
            if (((localclass_755 = (class_755)((Iterator)localObject4).next()).jdField_field_1015_of_type_Class_748 == localObject3) && (localclass_755.jdField_field_1015_of_type_JavaLangObject.equals(class_747.field_136))) {
              localObject2 = localObject3;
            }
          }
        }
        if (localObject2 != null)
        {
          int i = ((Integer)((class_748)localObject2).a127().selectedEntityId.get()).intValue();
          if (((localObject3 = (Sendable)localclass_11351.a6().a6().getLocalAndRemoteObjectContainer().getLocalObjects().get(i)) != null) && ((localObject3 instanceof class_797)))
          {
            localObject4 = (class_797)localObject3;
            int j = 0;
            if (((localObject4 instanceof SegmentController)) && (a3((SegmentController)localObject4, (SegmentController)localObject1))) {
              j = 1;
            }
            if (j == 0)
            {
              ((class_1256)localclass_11351.a6().field_223).a8((class_797)localObject4);
              localclass_11351.a12(new class_1224());
            }
          }
        }
      }
    }
    else
    {
      localclass_11351 = this;
      if (System.currentTimeMillis() - localclass_11351.jdField_field_128_of_type_Long > 3000L)
      {
        localclass_11351.jdField_field_128_of_type_Long = System.currentTimeMillis();
        localclass_11351.jdField_field_128_of_type_JavaUtilArrayList.clear();
        synchronized (localclass_11351.a6().a6().getLocalAndRemoteObjectContainer().getLocalObjects())
        {
          localObject2 = (class_797)localclass_11351.a6().a();
          Iterator localIterator2 = localclass_11351.a6().a6().getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values().iterator();
          while (localIterator2.hasNext()) {
            if (((localObject3 = (Sendable)localIterator2.next()) instanceof class_797)) {
              if ((((class_1039)localclass_11351.a6().a6()).a().a165((class_797)localObject2, (class_797)localObject3)) && ((!(localObject3 instanceof SegmentController)) || ((!a3((SegmentController)localclass_11351.a7(), (SegmentController)localObject3)) && (!a3((SegmentController)localObject3, (SegmentController)localclass_11351.a7())))))
              {
                localObject1 = (class_797)localObject3;
                class_1135 localclass_11352 = localclass_11351;
                if ((((localObject1 instanceof class_747)) && (((class_747)localObject1).c3()) && (localclass_11352.jdField_field_128_of_type_JavaxVecmathVector3f.length() > localclass_11352.a6().b() / 2.0F) ? 0 : ((localObject1 instanceof class_747)) && (((class_747)localObject1).a7()) ? 0 : (localObject1 instanceof class_705) ? 0 : 1) != 0) {
                  if ((!(localObject3 instanceof class_365)) || ((localObject3 instanceof class_750)) || (!((class_365)localObject3).a26().isEmpty()) || (((class_991)localObject3).a().a1()))
                  {
                    ((class_797)localObject3).calcWorldTransformRelative(((SegmentController)localclass_11351.a7()).getSectorId(), ((class_1041)((SegmentController)localclass_11351.a7()).getState()).a62().getSector(((SegmentController)localclass_11351.a7()).getSectorId()).field_136);
                    localclass_11351.jdField_field_128_of_type_JavaxVecmathVector3f.sub(((class_797)localObject3).getClientTransform().origin, ((SegmentController)localclass_11351.a7()).getWorldTransform().origin);
                    if (localclass_11351.jdField_field_128_of_type_JavaxVecmathVector3f.length() < localclass_11351.a6().b())
                    {
                      localclass_11351.jdField_field_128_of_type_JavaxVecmathVector3f.length();
                      localclass_11351.jdField_field_128_of_type_JavaUtilArrayList.add((class_797)localObject3);
                    }
                  }
                }
              }
            }
          }
        }
      }
      if (localclass_11351.jdField_field_128_of_type_JavaUtilArrayList.size() > 0) {
        ((class_1256)localclass_11351.a6().field_223).a8((class_797)localclass_11351.jdField_field_128_of_type_JavaUtilArrayList.get(Universe.getRandom().nextInt(localclass_11351.jdField_field_128_of_type_JavaUtilArrayList.size())));
      }
      if (((class_1256)localclass_11351.a6().field_223).a7() != null) {
        localclass_11351.a12(new class_1224());
      }
    }
    ((class_1256)a6().field_223).a7();
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1135
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */