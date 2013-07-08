/*  1:   */package org.schema.game.common.controller.elements.dockingBlock;
/*  2:   */
/*  3:   */import Ah;
/*  4:   */import org.schema.game.common.controller.elements.BlockMetaDataDummy;
/*  5:   */
/*  7:   */public class DockingMetaDataDummy
/*  8:   */  extends BlockMetaDataDummy
/*  9:   */{
/* 10:   */  public byte orientation;
/* 11:   */  
/* 12:   */  protected void fromTagStructrePriv(Ah paramAh)
/* 13:   */  {
/* 14:14 */    this.orientation = ((Byte)paramAh.a()).byteValue();
/* 15:   */  }
/* 16:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.DockingMetaDataDummy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */