/*  1:   */package org.schema.game.common.controller.elements;
/*  2:   */
/*  3:   */import org.schema.game.common.data.element.ElementCollection;
/*  4:   */import org.schema.game.common.data.world.Segment;
/*  5:   */import q;
/*  6:   */import xq;
/*  7:   */
/* 13:   */public class ManagerModuleSingle
/* 14:   */  extends ManagerModuleControllable
/* 15:   */{
/* 16:   */  private final UsableControllableSingleElementManager elementManager;
/* 17:   */  
/* 18:   */  public ManagerModuleSingle(UsableControllableSingleElementManager paramUsableControllableSingleElementManager, short paramShort1, short paramShort2)
/* 19:   */  {
/* 20:20 */    super(paramUsableControllableSingleElementManager, paramShort2, paramShort1);
/* 21:21 */    this.elementManager = paramUsableControllableSingleElementManager;
/* 22:   */  }
/* 23:   */  
/* 31:   */  public void addControlledBlock(q paramq1, q paramq2, short paramShort)
/* 32:   */  {
/* 33:33 */    getCollectionManager().addModded(paramq2);
/* 34:34 */    this.elementManager.onControllerChange();
/* 35:   */  }
/* 36:   */  
/* 37:   */  public void addElement(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
/* 38:   */  {
/* 39:39 */    getCollectionManager().addModded(ElementCollection.getIndex(paramByte1, paramByte2, paramByte3, paramSegment));
/* 40:   */  }
/* 41:   */  
/* 46:   */  public void clear()
/* 47:   */  {
/* 48:48 */    getCollectionManager().clear();
/* 49:   */  }
/* 50:   */  
/* 55:   */  public ElementCollectionManager getCollectionManager()
/* 56:   */  {
/* 57:57 */    return getElementManager().getCollection();
/* 58:   */  }
/* 59:   */  
/* 61:   */  public UsableControllableSingleElementManager getElementManager()
/* 62:   */  {
/* 63:63 */    return this.elementManager;
/* 64:   */  }
/* 65:   */  
/* 71:   */  public void removeControllerBlock(q paramq1, q paramq2, short paramShort)
/* 72:   */  {
/* 73:73 */    getCollectionManager().remove(paramq2);
/* 74:74 */    this.elementManager.onControllerChange();
/* 75:   */  }
/* 76:   */  
/* 78:   */  public void removeElement(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
/* 79:   */  {
/* 80:80 */    getCollectionManager().remove(ElementCollection.getIndex(paramByte1, paramByte2, paramByte3, paramSegment));
/* 81:   */  }
/* 82:   */  
/* 87:   */  public void update(xq paramxq, long paramLong)
/* 88:   */  {
/* 89:89 */    ElementCollectionManager localElementCollectionManager = this.elementManager.getCollection();
/* 90:90 */    assert (localElementCollectionManager != null);
/* 91:91 */    localElementCollectionManager.updateStructure(paramLong);
/* 92:92 */    if (localElementCollectionManager.needsUpdate()) {
/* 93:93 */      localElementCollectionManager.update(paramxq);
/* 94:   */    }
/* 95:   */  }
/* 96:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerModuleSingle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */