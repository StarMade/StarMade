package org.schema.game.common.controller.elements;

import class_359;
import class_48;
import class_748;
import class_755;
import class_796;
import class_886;
import java.io.PrintStream;
import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.player.ShipConfigurationNotFoundException;

public abstract class UsableControllableSingleElementManager
  extends UsableElementManager
{
  private final ElementCollectionManager collection;
  
  public UsableControllableSingleElementManager(ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController)
  {
    super(paramSegmentController);
    this.collection = paramElementCollectionManager;
  }
  
  protected boolean convertDeligateControls(class_755 paramclass_755, class_48 paramclass_481, class_48 paramclass_482)
  {
    paramclass_481.b1((class_48)paramclass_755.jdField_field_1015_of_type_JavaLangObject);
    paramclass_482.b1((class_48)paramclass_755.jdField_field_1015_of_type_JavaLangObject);
    paramclass_481 = null;
    try
    {
      if (((paramclass_481 = getSegmentBuffer().a10(paramclass_482, true, new class_796())) != null) && (paramclass_481.a9() == 1)) {
        try
        {
          paramclass_481 = checkShipConfig(paramclass_755);
          paramclass_755 = paramclass_755.jdField_field_1015_of_type_Class_748.d1();
          if (!paramclass_481.a18(paramclass_755)) {
            return false;
          }
          paramclass_482.b1(paramclass_481.a17(paramclass_755));
        }
        catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException)
        {
          return false;
        }
      }
    }
    catch (CannotImmediateRequestOnClientException paramclass_481)
    {
      System.err.println("[CLIENT][WARNING] deferring request for deligated control. segment has been requested: " + paramclass_481.a());
      return false;
    }
    return true;
  }
  
  public final ElementCollectionManager getCollection()
  {
    return this.collection;
  }
  
  public abstract void onControllerChange();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.UsableControllableSingleElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */