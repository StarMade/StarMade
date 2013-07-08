package org.schema.game.common.controller.elements.missile;

import class_48;
import class_972;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.schema.common.FastMath;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.BlockActivationListenerInterface;
import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;
import org.schema.game.common.controller.elements.ManagerReloadInterface;
import org.schema.game.common.controller.elements.NTDistributionReceiverInterface;
import org.schema.game.common.controller.elements.NTReceiveInterface;
import org.schema.game.common.controller.elements.NTSenderInterface;
import org.schema.game.common.controller.elements.UsableDistributionControllableElementManager;

public abstract class MissileElementManager
  extends UsableDistributionControllableElementManager
  implements BlockActivationListenerInterface, ManagerReloadInterface, NTDistributionReceiverInterface, NTReceiveInterface, NTSenderInterface
{
  public MissileElementManager(short paramShort1, short paramShort2, SegmentController paramSegmentController)
  {
    super(paramShort1, paramShort2, paramSegmentController);
  }
  
  public void drawReloads(class_972 paramclass_972, class_48 paramclass_48)
  {
    float f;
    for (int i = 0; i < getCollectionManagers().size(); f++) {
      if (((ControlBlockElementCollectionManager)getCollectionManagers().get(i)).getControllerPos().equals(paramclass_48))
      {
        paramclass_48 = ((ControlBlockElementCollectionManager)getCollectionManagers().get(i)).getCollection().iterator();
        while (paramclass_48.hasNext())
        {
          MissileUnit localMissileUnit = (MissileUnit)paramclass_48.next();
          int j = (int)(System.currentTimeMillis() - localMissileUnit.getLastShoot());
          f = Math.min(2.0F, j / localMissileUnit.getRelaodTime());
          j = 9;
          if (f <= 1.0F) {
            j = (int)FastMath.a5(9.0F + f * 8.0F, 9.0F, 17.0F);
          }
          paramclass_972.a_2(j);
          paramclass_972.b();
        }
        return;
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.missile.MissileElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */