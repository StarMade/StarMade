/*  1:   */package org.schema.game.common.controller.elements.factory;
/*  2:   */
/*  3:   */import java.util.Collection;
/*  4:   */import org.schema.game.common.data.element.ElementCollection;
/*  5:   */
/*  6:   */public class FactoryUnit extends ElementCollection
/*  7:   */{
/*  8: 8 */  private q significator = new q();
/*  9:   */  
/* 13:   */  private int capability;
/* 14:   */  
/* 18:   */  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/* 19:   */  {
/* 20:20 */    this.significator.a = (getMax().a - (getMax().a - getMin().a) / 2);
/* 21:21 */    this.significator.b = (getMax().b - (getMax().b - getMin().b) / 2);
/* 22:22 */    this.significator.c = getMax().c;
/* 23:   */  }
/* 24:   */  
/* 29:   */  public String toString()
/* 30:   */  {
/* 31:31 */    return "FactoryUnit";
/* 32:   */  }
/* 33:   */  
/* 37:   */  public q getSignificator()
/* 38:   */  {
/* 39:39 */    return this.significator;
/* 40:   */  }
/* 41:   */  
/* 42:   */  public void refreshFactoryCapabilities(FactoryCollectionManager paramFactoryCollectionManager)
/* 43:   */  {
/* 44:44 */    this.capability = getNeighboringCollection().size();
/* 45:45 */    paramFactoryCollectionManager.addCapability(this.capability);
/* 46:   */  }
/* 47:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.factory.FactoryUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */