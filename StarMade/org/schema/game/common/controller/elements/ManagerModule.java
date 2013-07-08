package org.schema.game.common.controller.elements;

import class_48;
import class_941;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

public abstract class ManagerModule
{
  private final UsableElementManager elementManager;
  private final short elementID;
  private ManagerModule next;
  
  public ManagerModule(UsableElementManager paramUsableElementManager, short paramShort)
  {
    this.elementManager = paramUsableElementManager;
    this.elementID = paramShort;
  }
  
  public void addControlledBlock(class_48 paramclass_481, class_48 paramclass_482, short paramShort) {}
  
  public void clear() {}
  
  public short getElementID()
  {
    return this.elementID;
  }
  
  public UsableElementManager getElementManager()
  {
    return this.elementManager;
  }
  
  public void removeControllerBlock(class_48 paramclass_481, class_48 paramclass_482, short paramShort) {}
  
  public String toString()
  {
    return "(" + getElementManager().getClass().getSimpleName() + ": " + ElementKeyMap.getInfo(this.elementID).getName() + ")";
  }
  
  public abstract void update(class_941 paramclass_941, long paramLong);
  
  public ManagerModule getNext()
  {
    return this.next;
  }
  
  public void setNext(ManagerModule paramManagerModule)
  {
    this.next = paramManagerModule;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerModule
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */