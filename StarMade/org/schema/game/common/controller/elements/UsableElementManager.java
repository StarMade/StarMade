package org.schema.game.common.controller.elements;

import class_359;
import class_365;
import class_371;
import class_48;
import class_708;
import class_747;
import class_748;
import class_755;
import class_796;
import class_798;
import class_8;
import class_886;
import com.bulletphysics.linearmath.Transform;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

public abstract class UsableElementManager
  extends class_708
{
  private final SegmentController segmentController;
  
  public UsableElementManager(SegmentController paramSegmentController)
  {
    this.segmentController = paramSegmentController;
  }
  
  public boolean canHandle(class_755 paramclass_755)
  {
    return paramclass_755.jdField_field_1015_of_type_Class_748.b2();
  }
  
  public class_359 checkShipConfig(class_755 paramclass_755)
  {
    if (!paramclass_755.jdField_field_1015_of_type_Class_748.a131(getSegmentController())) {
      throw new ShipConfigurationNotFoundException("does not exist for that ship");
    }
    reassignControllerKeysIfNecessary(paramclass_755.jdField_field_1015_of_type_Class_748, (class_48)paramclass_755.jdField_field_1015_of_type_JavaLangObject);
    return paramclass_755.jdField_field_1015_of_type_Class_748.a128(getSegmentController());
  }
  
  protected boolean clientIsOwnShip()
  {
    return ((class_365)this.segmentController).isClientOwnObject();
  }
  
  protected ArrayList getAttachedPlayers()
  {
    return ((class_747)this.segmentController).a75();
  }
  
  public ControlElementMap getControlElementMap()
  {
    return this.segmentController.getControlElementMap();
  }
  
  public ManagerContainer getManagerContainer()
  {
    return ((class_798)this.segmentController).a();
  }
  
  public abstract ElementCollectionManager getNewCollectionManager(class_796 paramclass_796);
  
  protected class_8 getParticleController()
  {
    return ((ParticleHandler)this.segmentController).getParticleController();
  }
  
  protected PhysicsDataContainer getPhysicsDataContainer()
  {
    return this.segmentController.getPhysicsDataContainer();
  }
  
  protected PowerAddOn getPowerManager()
  {
    return ((PowerManagerInterface)getManagerContainer()).getPowerAddOn();
  }
  
  protected class_886 getSegmentBuffer()
  {
    return this.segmentController.getSegmentBuffer();
  }
  
  public SegmentController getSegmentController()
  {
    return this.segmentController;
  }
  
  protected StateInterface getState()
  {
    return this.segmentController.getState();
  }
  
  protected Transform getWorldTransform()
  {
    return this.segmentController.getWorldTransform();
  }
  
  public abstract void handle(class_755 paramclass_755);
  
  private void reassignControllerKeysIfNecessary(class_748 paramclass_748, class_48 paramclass_48)
  {
    paramclass_748 = paramclass_748.a128(getSegmentController());
    if (((getState() instanceof class_371)) && ((paramclass_748.field_136.size() == 0) || ((paramclass_748.field_136.size() == 1) && (paramclass_748.a18(9)) && (paramclass_748.a17(9).a3(8, 8, 8)))))
    {
      paramclass_48 = getControlElementMap().getControlledElements((short)32767, paramclass_48);
      paramclass_748.a21((class_371)getState(), paramclass_48);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.UsableElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */