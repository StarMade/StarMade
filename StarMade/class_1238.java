import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.Universe;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.Sendable;

public final class class_1238
  extends class_1254
{
  private long field_128;
  private static final long serialVersionUID = 1L;
  
  public class_1238(class_981 paramclass_981)
  {
    super(paramclass_981);
  }
  
  private static boolean a3(SegmentController paramSegmentController1, SegmentController paramSegmentController2)
  {
    return (paramSegmentController1.getDockingController().b3()) && (paramSegmentController1.getDockingController().a4().field_1740.a7().a15() == paramSegmentController2);
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
    if (System.currentTimeMillis() - this.field_128 < 3000L) {
      return false;
    }
    if (a6().a1())
    {
      class_1238 localclass_1238 = this;
      class_670 localclass_670 = null;
      if (((class_1256)a6().field_223).a7() == null)
      {
        int i;
        Object localObject2;
        class_797 localclass_7971;
        Object localObject3;
        if ((i = ((class_1256)localclass_1238.a6().field_223).a11()) > 0)
        {
          if (((localObject2 = (Sendable)localclass_1238.a6().a6().getLocalAndRemoteObjectContainer().getLocalObjects().get(i)) != null) && ((localObject2 instanceof class_797)))
          {
            localclass_7971 = (class_797)localObject2;
            if ((localclass_670 = ((class_1041)((SegmentController)localclass_1238.a7()).getState()).a62().getSector(((SegmentController)localclass_1238.a7()).getSectorId())) == null)
            {
              System.err.println("[AI] sector of entity: " + localclass_1238.a7() + " is not loaded: " + ((SegmentController)localclass_1238.a7()).getSectorId());
              ((class_1256)localclass_1238.a6().field_223).a12(-1);
            }
            else
            {
              localclass_7971.calcWorldTransformRelative(((SegmentController)localclass_1238.a7()).getSectorId(), localclass_670.field_136);
              (localObject3 = new Vector3f()).sub(localclass_7971.getClientTransform().origin, ((SegmentController)localclass_1238.a7()).getWorldTransform().origin);
              if ((((Vector3f)localObject3).length() <= 750.0F) && ((!(localObject2 instanceof class_365)) || (!((class_365)localObject2).a26().isEmpty()) || (((class_991)localObject2).a().a1())))
              {
                ((class_1256)localclass_1238.a6().field_223).a8(localclass_7971);
                ((class_1256)localclass_1238.a6().field_223).a12(-1);
                System.err.println("[AI] specfific tar SET " + localObject2);
              }
            }
          }
          else
          {
            ((class_1256)localclass_1238.a6().field_223).a12(-1);
          }
        }
        else
        {
          localObject2 = new ArrayList();
          localclass_7971 = (class_797)localclass_1238.a7();
          synchronized (localclass_1238.a6().a6().getLocalAndRemoteObjectContainer().getLocalObjects())
          {
            localObject3 = localclass_1238.a6().a6().getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values().iterator();
            while (((Iterator)localObject3).hasNext())
            {
              Sendable localSendable;
              if (((localSendable = (Sendable)((Iterator)localObject3).next()) instanceof class_797))
              {
                class_797 localclass_7972;
                if (((localclass_7972 = (class_797)localSendable) != localclass_7971) && ((!(localclass_7972 instanceof class_365)) || (!(localclass_7972 instanceof class_739)) || (((class_365)localclass_7972).a26().isEmpty()) || (((class_739)localclass_7972).a().isEmpty())) && (((class_1039)localclass_1238.a6().a6()).a().a165(localclass_7971, localclass_7972)))
                {
                  Object localObject4 = ((class_1041)((SegmentController)localclass_1238.a7()).getState()).a62().getSector(((SegmentController)localclass_1238.a7()).getSectorId());
                  Object localObject5 = ((class_1041)((SegmentController)localclass_1238.a7()).getState()).a62().getSector(localclass_7972.getSectorId());
                  if ((localObject4 == null) || (localObject5 == null))
                  {
                    System.err.println("[AI][SEARCHFORTARGET] either own or target sector not loaded: " + localObject4 + "; " + localObject5);
                  }
                  else if ((Math.abs(((class_670)localObject4).field_136.field_475 - ((class_670)localObject5).field_136.field_475) <= 3) && (Math.abs(((class_670)localObject4).field_136.field_476 - ((class_670)localObject5).field_136.field_476) <= 3) && (Math.abs(((class_670)localObject4).field_136.field_477 - ((class_670)localObject5).field_136.field_477) <= 3))
                  {
                    localclass_7972.calcWorldTransformRelative(((SegmentController)localclass_1238.a7()).getSectorId(), ((class_670)localObject4).field_136);
                    (localObject4 = new Vector3f()).sub(localclass_7972.getClientTransform().origin, ((SegmentController)localclass_1238.a7()).getWorldTransform().origin);
                    if (((Vector3f)localObject4).length() <= 750.0F) {
                      if (((localSendable instanceof SegmentController)) && ((((SegmentController)localclass_1238.a7()).getDockingController().b3()) || (((SegmentController)localSendable).getDockingController().b3())))
                      {
                        localObject5 = (SegmentController)localSendable;
                        if ((a3((SegmentController)localclass_1238.a7(), (SegmentController)localObject5)) || (a3((SegmentController)localObject5, (SegmentController)localclass_1238.a7()))) {}
                      }
                      else if (((!(localSendable instanceof class_747)) || (!((class_747)localSendable).a7())) && ((!(localSendable instanceof class_747)) || (!((class_747)localSendable).c3()) || (((Vector3f)localObject4).length() <= 375.0F)))
                      {
                        int j = ((localclass_7972 instanceof class_991)) && (((class_991)localclass_7972).a().a().a1()) ? 1 : 0;
                        if ((((localclass_7972 instanceof class_365)) && (!((class_365)localclass_7972).a26().isEmpty()) ? 1 : 0) == 0)
                        {
                          if (j == 0) {}
                        }
                        else {
                          ((ArrayList)localObject2).add(localclass_7972);
                        }
                      }
                    }
                  }
                }
              }
            }
          }
          if (((ArrayList)localObject2).size() > 0) {
            ((class_1246)localObject1.a6().field_223).a8((class_797)((ArrayList)localObject2).get((int)Math.round(Math.random() * (((ArrayList)localObject2).size() - 1))));
          }
        }
      }
      if (((class_1256)a6().field_223).a7() == null) {
        this.field_128 = (System.currentTimeMillis() + Universe.getRandom().nextInt(1000));
      }
      if (((class_1256)a6().field_223).a7() != null) {
        a12(new class_1224());
      } else if (((class_1256)a6().field_223).a9() != null) {
        a12(new class_1124());
      }
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1238
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */