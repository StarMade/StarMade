package org.schema.game.common.controller.elements.missile.dumb;

import class_227;
import class_261;
import class_371;
import class_796;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.DistributionCollectionManager;
import org.schema.game.common.controller.elements.missile.MissileUnit;

public class DumbMissileCollectionManager
  extends DistributionCollectionManager
{
  public DumbMissileCollectionManager(class_796 paramclass_796, SegmentController paramSegmentController)
  {
    super(paramclass_796, (short)32, paramSegmentController);
  }
  
  public int getMargin()
  {
    return 0;
  }
  
  protected Class getType()
  {
    return MissileUnit.class;
  }
  
  protected void onChangedCollection()
  {
    if (!getSegmentController().isOnServer()) {
      ((class_371)getSegmentController().getState()).a27().a92().a17(this);
    }
  }
  
  public boolean needsUpdate()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.missile.dumb.DumbMissileCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */