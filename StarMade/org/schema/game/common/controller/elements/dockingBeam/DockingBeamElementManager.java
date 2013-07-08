package org.schema.game.common.controller.elements.dockingBeam;

import class_359;
import class_371;
import class_48;
import class_52;
import class_707;
import class_747;
import class_748;
import class_755;
import class_796;
import class_797;
import class_886;
import class_941;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Set;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.ManagerContainer;
import org.schema.game.common.controller.elements.UsableElementManager;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
import org.schema.game.common.data.world.Segment;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.objects.remote.RemoteBooleanArray;
import org.schema.schine.network.objects.remote.RemoteField;

public class DockingBeamElementManager
  extends UsableElementManager
{
  private Vector3f shootingDirTemp = new Vector3f();
  private DockingBeamHandler dockingBeamManager = new DockingBeamHandler(paramSegmentController, this);
  
  public DockingBeamElementManager(SegmentController paramSegmentController)
  {
    super(paramSegmentController);
  }
  
  public DockingBeamHandler getDockingBeamManager()
  {
    return this.dockingBeamManager;
  }
  
  public ElementCollectionManager getNewCollectionManager(class_796 paramclass_796)
  {
    throw new IllegalAccessError("This should not be called. ever");
  }
  
  public void handle(class_755 paramclass_755)
  {
    try
    {
      Object localObject1;
      if (((Boolean)paramclass_755.jdField_field_1015_of_type_Class_748.a127().activeControllerMask.get(1).get()).booleanValue())
      {
        if (((!(localObject1 = paramclass_755).jdField_field_1015_of_type_Class_748.isOnServer()) && ((((class_755)localObject1).jdField_field_1015_of_type_Class_365 instanceof class_797)) && (((class_371)((class_755)localObject1).jdField_field_1015_of_type_Class_748.getState()).a8() != ((class_797)((class_755)localObject1).jdField_field_1015_of_type_Class_365).getSectorId()) ? 0 : 1) != 0) {}
      }
      else {
        return;
      }
      if ((localObject1 = (class_48)paramclass_755.jdField_field_1015_of_type_JavaLangObject).equals(class_747.field_136))
      {
        Object localObject2 = new class_48((class_48)paramclass_755.jdField_field_1015_of_type_JavaLangObject);
        Object localObject3 = null;
        if ((localObject2 = getSegmentBuffer().a10((class_48)localObject2, getSegmentController().isOnServer(), new class_796())) == null) {
          return;
        }
        if (((class_796)localObject2).a9() == 1) {
          try
          {
            localObject3 = checkShipConfig(paramclass_755);
            int i = paramclass_755.jdField_field_1015_of_type_Class_748.d1();
            if (!((class_359)localObject3).a18(i)) {
              return;
            }
            if (!(localObject3 = ((class_359)localObject3).a17(i)).equals(localObject1))
            {
              paramclass_755 = getSegmentController().getDockingController().a5().iterator();
              while (paramclass_755.hasNext()) {
                if ((localObject1 = (ElementDocking)paramclass_755.next()).field_1740.a2(new class_48()).equals(localObject3)) {
                  if (getSegmentController().isOnServer())
                  {
                    System.err.println("[ELEMENTMANAGER] Requesting undock from " + ((ElementDocking)localObject1).from.a7().a15() + " " + ((ElementDocking)localObject1).from.a7().a15().getState());
                    ((ElementDocking)localObject1).from.a7().a15().getDockingController().b1();
                  }
                  else
                  {
                    ((class_371)getState()).a4().d1("Undocking from\n" + ((ElementDocking)localObject1).from.a7().a15().toNiceString());
                  }
                }
              }
              return;
            }
          }
          catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException)
          {
            return;
          }
        }
        if (getSegmentController().getDockingController().a4() != null)
        {
          if (getSegmentController().isOnServer())
          {
            System.err.println("[ELEMENTMANAGER] Requesting undock from " + getSegmentController().getDockingController().a4());
            getSegmentController().getDockingController().b1();
            return;
          }
          if (getSegmentController().getDockingController().a1()) {
            ((class_371)getState()).a4().d1("Undocking");
          }
          return;
        }
        this.shootingDirTemp.set(paramclass_755.jdField_field_1015_of_type_Class_748.a8());
        this.shootingDirTemp.scale(100.0F);
        Vector3f localVector3f = new Vector3f();
        getSegmentController().getAbsoluteElementWorldPosition(new class_48(), localVector3f);
        (localObject3 = new Vector3f()).set(localVector3f);
        ((Vector3f)localObject3).add(this.shootingDirTemp);
        this.dockingBeamManager.addBeam((class_48)localObject1, localVector3f, (Vector3f)localObject3, paramclass_755.jdField_field_1015_of_type_Class_748, 0.0F);
        getManagerContainer().onAction();
      }
      return;
    }
    catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException)
    {
      System.err.println("[CLIENT][WARNING] " + getSegmentController() + " Cannot DOCK! segment not yet in buffer " + localCannotImmediateRequestOnClientException.a() + ". -> requested");
    }
  }
  
  public void update(class_941 paramclass_941)
  {
    this.dockingBeamManager.update(paramclass_941);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBeam.DockingBeamElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */