package org.schema.game.common.controller.elements;

import class_48;
import class_69;

public abstract class BlockMetaDataDummy
{
  public class_48 pos;
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    this.pos = ((class_48)paramclass_69[0].a4());
    fromTagStructrePriv(paramclass_69[1]);
  }
  
  protected abstract void fromTagStructrePriv(class_69 paramclass_69);
  
  public class_48 getControllerPos()
  {
    return this.pos;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.BlockMetaDataDummy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */