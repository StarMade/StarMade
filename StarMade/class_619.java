import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteField;
import org.schema.schine.network.objects.remote.RemoteIntegerArray;

public class class_619
{
  private com.bulletphysics.util.ObjectArrayList jdField_field_902_of_type_ComBulletphysicsUtilObjectArrayList = new com.bulletphysics.util.ObjectArrayList();
  private final Set jdField_field_902_of_type_JavaUtilSet = new HashSet();
  private final class_748 jdField_field_902_of_type_Class_748;
  private class_755 jdField_field_902_of_type_Class_755;
  private boolean jdField_field_902_of_type_Boolean;
  private final com.bulletphysics.util.ObjectArrayList jdField_field_903_of_type_ComBulletphysicsUtilObjectArrayList;
  private boolean jdField_field_903_of_type_Boolean;
  
  public class_619(class_748 paramclass_748)
  {
    new HashSet();
    this.jdField_field_902_of_type_Class_755 = new class_755();
    this.jdField_field_903_of_type_ComBulletphysicsUtilObjectArrayList = new com.bulletphysics.util.ObjectArrayList();
    this.jdField_field_902_of_type_Class_748 = paramclass_748;
  }
  
  public final class_748 a()
  {
    return this.jdField_field_902_of_type_Class_748;
  }
  
  public final Set a1()
  {
    return this.jdField_field_902_of_type_JavaUtilSet;
  }
  
  private void a2(class_941 paramclass_941, class_748 paramclass_748)
  {
    Iterator localIterator = this.jdField_field_902_of_type_JavaUtilSet.iterator();
    while (localIterator.hasNext())
    {
      class_755 localclass_755;
      if ((localclass_755 = (class_755)localIterator.next()).jdField_field_1015_of_type_Class_748 == paramclass_748)
      {
        if ((!paramclass_748.isOnServer()) && ((localclass_755.jdField_field_1015_of_type_Class_365 instanceof class_797)) && (localclass_755.jdField_field_1015_of_type_Class_748 == ((class_371)paramclass_748.getState()).a20())) {
          ((class_371)paramclass_748.getState()).a32((class_797)localclass_755.jdField_field_1015_of_type_Class_365);
        }
        localclass_755.jdField_field_1015_of_type_Class_365.a27(paramclass_941, localclass_755);
      }
    }
  }
  
  public final void a3()
  {
    Iterator localIterator = this.jdField_field_902_of_type_JavaUtilSet.iterator();
    while (localIterator.hasNext())
    {
      class_755 localclass_755 = (class_755)localIterator.next();
      RemoteIntegerArray localRemoteIntegerArray;
      (localRemoteIntegerArray = new RemoteIntegerArray(9, this.jdField_field_902_of_type_Class_748.a127())).set(0, Integer.valueOf(-1));
      localRemoteIntegerArray.set(1, Integer.valueOf(0));
      localRemoteIntegerArray.set(2, Integer.valueOf(0));
      localRemoteIntegerArray.set(3, Integer.valueOf(0));
      localRemoteIntegerArray.set(4, Integer.valueOf(localclass_755.jdField_field_1015_of_type_Class_365.getId()));
      localRemoteIntegerArray.set(5, Integer.valueOf(((class_48)localclass_755.jdField_field_1015_of_type_JavaLangObject).field_475));
      localRemoteIntegerArray.set(6, Integer.valueOf(((class_48)localclass_755.jdField_field_1015_of_type_JavaLangObject).field_476));
      localRemoteIntegerArray.set(7, Integer.valueOf(((class_48)localclass_755.jdField_field_1015_of_type_JavaLangObject).field_477));
      localRemoteIntegerArray.set(8, Integer.valueOf(localclass_755.jdField_field_1015_of_type_Class_365.isHidden() ? 1 : 0));
      this.jdField_field_902_of_type_Class_748.a127().controlRequestParameterBuffer.add(localRemoteIntegerArray);
    }
  }
  
  private void b(class_365 paramclass_3651, class_365 paramclass_3652, class_48 paramclass_481, class_48 paramclass_482, boolean paramBoolean)
  {
    RemoteIntegerArray localRemoteIntegerArray = new RemoteIntegerArray(9, this.jdField_field_902_of_type_Class_748.a127());
    if (paramclass_3651 != null) {
      localRemoteIntegerArray.set(0, Integer.valueOf(paramclass_3651.getId()));
    } else {
      localRemoteIntegerArray.set(0, Integer.valueOf(-1));
    }
    if (paramclass_481 != null)
    {
      localRemoteIntegerArray.set(1, Integer.valueOf(paramclass_481.field_475));
      localRemoteIntegerArray.set(2, Integer.valueOf(paramclass_481.field_476));
      localRemoteIntegerArray.set(3, Integer.valueOf(paramclass_481.field_477));
    }
    else
    {
      localRemoteIntegerArray.set(1, Integer.valueOf(0));
      localRemoteIntegerArray.set(2, Integer.valueOf(0));
      localRemoteIntegerArray.set(3, Integer.valueOf(0));
    }
    if (paramclass_3652 != null) {
      localRemoteIntegerArray.set(4, Integer.valueOf(paramclass_3652.getId()));
    } else {
      localRemoteIntegerArray.set(4, Integer.valueOf(-1));
    }
    if (paramclass_482 != null)
    {
      localRemoteIntegerArray.set(5, Integer.valueOf(paramclass_482.field_475));
      localRemoteIntegerArray.set(6, Integer.valueOf(paramclass_482.field_476));
      localRemoteIntegerArray.set(7, Integer.valueOf(paramclass_482.field_477));
    }
    else
    {
      localRemoteIntegerArray.set(5, Integer.valueOf(0));
      localRemoteIntegerArray.set(6, Integer.valueOf(0));
      localRemoteIntegerArray.set(7, Integer.valueOf(0));
    }
    localRemoteIntegerArray.set(8, Integer.valueOf(paramBoolean ? 1 : -1));
    this.jdField_field_902_of_type_Class_748.a127().controlRequestParameterBuffer.add(localRemoteIntegerArray);
  }
  
  public final void a4(class_365 paramclass_3651, class_365 arg2, class_48 paramclass_481, class_48 paramclass_482, boolean paramBoolean)
  {
    System.err.println(this.jdField_field_902_of_type_Class_748 + " request control: " + paramclass_3651 + " -> " + ???);
    if (this.jdField_field_902_of_type_Class_748.isOnServer())
    {
      paramclass_3651 = new class_620(this.jdField_field_902_of_type_Class_748, paramclass_3651 != null ? paramclass_3651.getId() : -1, paramclass_481 != null ? paramclass_481 : new class_48(), ??? != null ? ???.getId() : -1, paramclass_482 != null ? paramclass_482 : new class_48(), paramBoolean);
      synchronized (this.jdField_field_902_of_type_ComBulletphysicsUtilObjectArrayList)
      {
        this.jdField_field_902_of_type_ComBulletphysicsUtilObjectArrayList.add(paramclass_3651);
        return;
      }
    }
    b(paramclass_3651, ???, paramclass_481, paramclass_482, paramBoolean);
  }
  
  public final void a5(NetworkPlayer paramNetworkPlayer)
  {
    for (int i = 0; i < paramNetworkPlayer.controlRequestParameterBuffer.getReceiveBuffer().size(); i++)
    {
      RemoteIntegerArray localRemoteIntegerArray;
      int j = ((Integer)(localRemoteIntegerArray = (RemoteIntegerArray)paramNetworkPlayer.controlRequestParameterBuffer.getReceiveBuffer().get(i)).get(0).get()).intValue();
      class_48 localclass_481 = new class_48(((Integer)localRemoteIntegerArray.get(1).get()).intValue(), ((Integer)localRemoteIntegerArray.get(2).get()).intValue(), ((Integer)localRemoteIntegerArray.get(3).get()).intValue());
      int k = ((Integer)localRemoteIntegerArray.get(4).get()).intValue();
      class_48 localclass_482 = new class_48(((Integer)localRemoteIntegerArray.get(5).get()).intValue(), ((Integer)localRemoteIntegerArray.get(6).get()).intValue(), ((Integer)localRemoteIntegerArray.get(7).get()).intValue());
      boolean bool = ((Integer)localRemoteIntegerArray.get(8).get()).intValue() == 1;
      class_620 localclass_620 = new class_620(this.jdField_field_902_of_type_Class_748, j, localclass_481, k, localclass_482, bool);
      System.err.println(this.jdField_field_902_of_type_Class_748.getState() + "; " + this + " CONTROLLER REQUEST RECEIVED  " + localclass_620);
      synchronized (this.jdField_field_902_of_type_ComBulletphysicsUtilObjectArrayList)
      {
        this.jdField_field_902_of_type_ComBulletphysicsUtilObjectArrayList.add(localclass_620);
      }
    }
  }
  
  private void e()
  {
    if (!this.jdField_field_902_of_type_ComBulletphysicsUtilObjectArrayList.isEmpty()) {
      synchronized (this.jdField_field_902_of_type_ComBulletphysicsUtilObjectArrayList)
      {
        while (!this.jdField_field_902_of_type_ComBulletphysicsUtilObjectArrayList.isEmpty())
        {
          Object localObject3 = (class_620)this.jdField_field_902_of_type_ComBulletphysicsUtilObjectArrayList.remove(0);
          class_619 localclass_619 = this;
          if ((!field_904) && (((class_620)localObject3).jdField_field_905_of_type_Class_748 != localclass_619.jdField_field_902_of_type_Class_748)) {
            throw new AssertionError();
          }
          class_48 localclass_481 = ((class_620)localObject3).jdField_field_905_of_type_Class_48;
          class_48 localclass_482 = ((class_620)localObject3).jdField_field_906_of_type_Class_48;
          boolean bool1 = ((class_620)localObject3).jdField_field_905_of_type_Boolean;
          Sendable localSendable1 = (Sendable)localclass_619.jdField_field_902_of_type_Class_748.getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(((class_620)localObject3).jdField_field_905_of_type_Int);
          localObject3 = (Sendable)localclass_619.jdField_field_902_of_type_Class_748.getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(((class_620)localObject3).jdField_field_906_of_type_Int);
          Object localObject4;
          if ((localSendable1 != null) && ((localSendable1 instanceof class_365)))
          {
            localObject4 = (class_365)localSendable1;
            localclass_619.a8((class_365)localObject4, localclass_481, localclass_619.jdField_field_902_of_type_Class_748, bool1);
          }
          if (localObject3 != null)
          {
            if ((localObject3 instanceof class_365))
            {
              localObject4 = (class_365)localObject3;
              class_48 localclass_484 = localclass_481;
              Sendable localSendable2 = localSendable1;
              class_48 localclass_483 = localclass_482;
              Object localObject5 = localObject4;
              class_748 localclass_748 = localclass_619.jdField_field_902_of_type_Class_748;
              localObject4 = localclass_619;
              class_755 localclass_755;
              (localclass_755 = new class_755()).jdField_field_1015_of_type_Class_365 = localObject5;
              localclass_755.jdField_field_1015_of_type_JavaLangObject = localclass_483;
              localclass_755.jdField_field_1015_of_type_Class_748 = localclass_748;
              boolean bool2 = false;
              synchronized (((class_619)localObject4).jdField_field_902_of_type_JavaUtilSet)
              {
                bool2 = ((class_619)localObject4).jdField_field_902_of_type_JavaUtilSet.add(localclass_755);
                if (!localObject5.a26().contains(localclass_748)) {
                  localObject5.a26().add(localclass_748);
                }
                localObject5.a28(localclass_748, localSendable2, localclass_484);
                if ((!((class_619)localObject4).jdField_field_902_of_type_Class_748.isOnServer()) && (((class_619)localObject4).jdField_field_902_of_type_Class_748 == ((class_371)((class_619)localObject4).jdField_field_902_of_type_Class_748.getState()).a20()) && ((localObject5 instanceof class_75))) {
                  class_969.a().a11((class_75)localObject5);
                }
              }
              if (bool2)
              {
                System.err.println("[CONTROLLER][ADD-UNIT] (" + localclass_748.getState() + "): " + localclass_748 + " Added to controllers: " + localObject5);
                if (((class_619)localObject4).jdField_field_902_of_type_Class_748.a7()) {
                  ((class_371)((class_619)localObject4).jdField_field_902_of_type_Class_748.getState()).a4().a8((class_619)localObject4);
                }
              }
            }
          }
          else {
            localObject1.jdField_field_902_of_type_JavaUtilSet.clear();
          }
          if (localObject1.jdField_field_902_of_type_Class_748.isOnServer()) {
            localObject1.b((class_365)localSendable1, (class_365)localObject3, localclass_481, localclass_482, bool1);
          }
        }
        return;
      }
    }
  }
  
  private boolean a6(class_755 paramclass_755)
  {
    synchronized (this.jdField_field_902_of_type_JavaUtilSet)
    {
      return this.jdField_field_902_of_type_JavaUtilSet.contains(paramclass_755);
    }
  }
  
  public final void a7(class_748 paramclass_748)
  {
    if ((!field_904) && (!this.jdField_field_902_of_type_Class_748.isOnServer())) {
      throw new AssertionError();
    }
    synchronized (this.jdField_field_902_of_type_JavaUtilSet)
    {
      Iterator localIterator = this.jdField_field_902_of_type_JavaUtilSet.iterator();
      while (localIterator.hasNext())
      {
        class_755 localclass_755;
        if ((localclass_755 = (class_755)localIterator.next()).jdField_field_1015_of_type_Class_748 == paramclass_748)
        {
          localIterator.remove();
          localclass_755.jdField_field_1015_of_type_Class_365.a29(paramclass_748, true);
          localclass_755.jdField_field_1015_of_type_Class_365.a26().remove(paramclass_748);
          if ((!this.jdField_field_902_of_type_Class_748.isOnServer()) && (this.jdField_field_902_of_type_Class_748 == ((class_371)this.jdField_field_902_of_type_Class_748.getState()).a20()) && ((localclass_755.jdField_field_1015_of_type_Class_365 instanceof class_75))) {
            class_969.a().a11((class_75)localclass_755.jdField_field_1015_of_type_Class_365);
          }
        }
      }
      System.err.println("REMOVED ALL UNITS OF " + this.jdField_field_902_of_type_Class_748 + " ON " + this.jdField_field_902_of_type_Class_748.getState() + "! LEFT " + this.jdField_field_902_of_type_JavaUtilSet);
    }
    System.err.println("[NOTIFIED] REMOVED ALL UNITS OF " + this.jdField_field_902_of_type_Class_748 + " ON " + this.jdField_field_902_of_type_Class_748.getState() + "! LEFT " + this.jdField_field_902_of_type_JavaUtilSet);
  }
  
  private Transform a8(class_365 paramclass_365, class_48 paramclass_48, class_748 paramclass_748, boolean paramBoolean)
  {
    this.jdField_field_902_of_type_Class_755.jdField_field_1015_of_type_Class_365 = paramclass_365;
    this.jdField_field_902_of_type_Class_755.jdField_field_1015_of_type_JavaLangObject = paramclass_48;
    this.jdField_field_902_of_type_Class_755.jdField_field_1015_of_type_Class_748 = paramclass_748;
    boolean bool = false;
    Transform localTransform = new Transform();
    synchronized (this.jdField_field_902_of_type_JavaUtilSet)
    {
      localTransform.set(((class_797)paramclass_365).getWorldTransform());
      if (paramclass_48 != null)
      {
        paramclass_48 = new Vector3f(paramclass_48.field_475 - 8, paramclass_48.field_476 - 8, paramclass_48.field_477 - 8);
        localTransform.transform(paramclass_48);
        localTransform.origin.set(paramclass_48);
      }
      System.err.println("[CONTROLLERSTATE][REMOVE-UNIT] " + paramclass_748.getState() + "; REMOVING CONTROLLER UNIT FROM " + paramclass_748 + ": " + paramclass_365 + "; detach pos: " + localTransform.origin);
      paramclass_365.a26().remove(paramclass_748);
      paramclass_365.a29(paramclass_748, paramBoolean);
      if ((!paramclass_748.isOnServer()) && (paramclass_748 == ((class_371)paramclass_748.getState()).a20()) && ((paramclass_365 instanceof class_75))) {
        class_969.a().b3((class_75)paramclass_365);
      }
      bool = this.jdField_field_902_of_type_JavaUtilSet.remove(this.jdField_field_902_of_type_Class_755);
    }
    if ((bool) && (paramclass_748.a7())) {
      ((class_371)paramclass_748.getState()).a4().a8(this);
    }
    return localTransform;
  }
  
  public final void a9(boolean paramBoolean)
  {
    this.jdField_field_902_of_type_Boolean = paramBoolean;
  }
  
  public final void a10(class_941 paramclass_941)
  {
    if (this.jdField_field_903_of_type_Boolean)
    {
      localObject1 = this.jdField_field_902_of_type_JavaUtilSet.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        class_755 localclass_755 = (class_755)((Iterator)localObject1).next();
        if (!this.jdField_field_902_of_type_Class_748.getState().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(localclass_755.jdField_field_1015_of_type_Class_365.getId())) {
          ((Iterator)localObject1).remove();
        }
      }
      this.jdField_field_903_of_type_Boolean = false;
    }
    Object localObject1 = this;
    if (!this.jdField_field_903_of_type_ComBulletphysicsUtilObjectArrayList.isEmpty()) {
      synchronized (((class_619)localObject1).jdField_field_903_of_type_ComBulletphysicsUtilObjectArrayList)
      {
        ArrayList localArrayList1 = new ArrayList();
        ArrayList localArrayList2 = new ArrayList();
        while (!((class_619)localObject1).jdField_field_903_of_type_ComBulletphysicsUtilObjectArrayList.isEmpty())
        {
          class_365 localclass_365 = (class_365)((class_619)localObject1).jdField_field_903_of_type_ComBulletphysicsUtilObjectArrayList.remove(0);
          Iterator localIterator = ((class_619)localObject1).jdField_field_902_of_type_JavaUtilSet.iterator();
          Object localObject2;
          while (localIterator.hasNext()) {
            if ((localObject2 = (class_755)localIterator.next()).jdField_field_1015_of_type_Class_365 == localclass_365) {
              localArrayList1.add(localObject2);
            }
          }
          synchronized (((class_619)localObject1).jdField_field_902_of_type_ComBulletphysicsUtilObjectArrayList)
          {
            localObject2 = ((class_619)localObject1).jdField_field_902_of_type_ComBulletphysicsUtilObjectArrayList.iterator();
            while (((Iterator)localObject2).hasNext())
            {
              class_620 localclass_620;
              if ((localclass_620 = (class_620)((Iterator)localObject2).next()).jdField_field_905_of_type_Int == localclass_365.getId()) {
                localArrayList2.add(localclass_620);
              }
            }
          }
        }
        System.err.println("[PlayerControllerState][CLEANUP] Units: " + localArrayList1);
        System.err.println("[PlayerControllerState][CLEANUP] Requests: " + localArrayList2);
        ((class_619)localObject1).jdField_field_902_of_type_JavaUtilSet.removeAll(localArrayList1);
        synchronized (((class_619)localObject1).jdField_field_902_of_type_ComBulletphysicsUtilObjectArrayList)
        {
          ((class_619)localObject1).jdField_field_902_of_type_ComBulletphysicsUtilObjectArrayList.removeAll(localArrayList2);
        }
      }
    }
    e();
    if (!this.jdField_field_902_of_type_Boolean)
    {
      paramclass_941 = null;
      a2(paramclass_941, this.jdField_field_902_of_type_Class_748);
    }
  }
  
  public final void a11(class_365 paramclass_365)
  {
    synchronized (this.jdField_field_903_of_type_ComBulletphysicsUtilObjectArrayList)
    {
      this.jdField_field_903_of_type_ComBulletphysicsUtilObjectArrayList.add(paramclass_365);
      return;
    }
  }
  
  public final void b1()
  {
    this.jdField_field_903_of_type_Boolean = true;
  }
  
  public static void c() {}
  
  public final void a12(class_796 paramclass_796)
  {
    if ((paramclass_796.a7().a15() instanceof class_365))
    {
      class_755 localclass_7551;
      (localclass_7551 = new class_755()).jdField_field_1015_of_type_Class_365 = ((class_365)paramclass_796.a7().a15());
      localclass_7551.jdField_field_1015_of_type_Class_748 = this.jdField_field_902_of_type_Class_748;
      localclass_7551.jdField_field_1015_of_type_JavaLangObject = paramclass_796.a2(new class_48());
      class_755 localclass_7552 = localclass_7551;
      paramclass_796 = this;
      if (((localclass_7552.jdField_field_1015_of_type_Class_748 == paramclass_796.jdField_field_902_of_type_Class_748) && (paramclass_796.a6(localclass_7552)) ? 1 : 0) != 0)
      {
        System.err.println("Forcing " + this + " to leave a controllable element");
        a4(localclass_7551.jdField_field_1015_of_type_Class_365, null, (class_48)localclass_7551.jdField_field_1015_of_type_JavaLangObject, null, false);
      }
    }
  }
  
  public final void d()
  {
    if ((!field_904) && (!this.jdField_field_902_of_type_Class_748.isOnServer())) {
      throw new AssertionError();
    }
    Iterator localIterator = this.jdField_field_902_of_type_JavaUtilSet.iterator();
    while (localIterator.hasNext())
    {
      class_755 localclass_755;
      if (((localclass_755 = (class_755)localIterator.next()).jdField_field_1015_of_type_Class_365 instanceof SegmentController))
      {
        Object localObject = (SegmentController)localclass_755.jdField_field_1015_of_type_Class_365;
        class_48 localclass_48 = (class_48)localclass_755.jdField_field_1015_of_type_JavaLangObject;
        localObject = ((SegmentController)localObject).getSegmentBuffer().a9(localclass_48, true);
        System.err.println("PARAMETER " + localclass_755.jdField_field_1015_of_type_JavaLangObject + ": POINT " + localObject);
        if ((((class_796)localObject).a9() == 0) || (((class_796)localObject).a5() <= 0))
        {
          localObject = this.jdField_field_902_of_type_Class_748.a121();
          System.err.println("FORCING PLAYER OUT OF SHIP " + this.jdField_field_902_of_type_Class_748 + ": " + this + " from " + localclass_755.jdField_field_1015_of_type_Class_365 + ": " + localObject);
          if ((!field_904) && (localObject == null)) {
            throw new AssertionError();
          }
          a4(localclass_755.jdField_field_1015_of_type_Class_365, (class_365)localObject, localclass_48, new class_48(), false);
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_619
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */