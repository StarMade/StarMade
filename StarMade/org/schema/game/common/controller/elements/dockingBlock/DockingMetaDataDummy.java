package org.schema.game.common.controller.elements.dockingBlock;

import class_69;
import org.schema.game.common.controller.elements.BlockMetaDataDummy;

public class DockingMetaDataDummy
  extends BlockMetaDataDummy
{
  public byte orientation;
  
  protected void fromTagStructrePriv(class_69 paramclass_69)
  {
    this.orientation = ((Byte)paramclass_69.a4()).byteValue();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.DockingMetaDataDummy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */