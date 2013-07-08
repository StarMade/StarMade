package org.schema.game.common.controller.elements;

import class_941;

public class ManagerModuleControllable
  extends ManagerModule
{
  private final short controllerID;
  
  public ManagerModuleControllable(UsableElementManager paramUsableElementManager, short paramShort1, short paramShort2)
  {
    super(paramUsableElementManager, paramShort1);
    this.controllerID = paramShort2;
  }
  
  public short getControllerID()
  {
    return this.controllerID;
  }
  
  public void update(class_941 paramclass_941, long paramLong) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerModuleControllable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */