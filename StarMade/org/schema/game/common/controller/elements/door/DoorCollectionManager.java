/*  1:   */package org.schema.game.common.controller.elements.door;
/*  2:   */
/*  3:   */import java.util.Iterator;
/*  4:   */import java.util.List;
/*  5:   */import le;
/*  6:   */import org.schema.game.common.controller.SegmentController;
/*  7:   */import org.schema.game.common.controller.elements.BlockActivationListenerInterface;
/*  8:   */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  9:   */import q;
/* 10:   */
/* 11:   */public class DoorCollectionManager extends ElementCollectionManager implements BlockActivationListenerInterface
/* 12:   */{
/* 13:   */  public DoorCollectionManager(SegmentController paramSegmentController)
/* 14:   */  {
/* 15:15 */    super((short)122, paramSegmentController);
/* 16:   */  }
/* 17:   */  
/* 21:   */  public int getMargin()
/* 22:   */  {
/* 23:23 */    return 0;
/* 24:   */  }
/* 25:   */  
/* 28:   */  protected Class getType()
/* 29:   */  {
/* 30:30 */    return DoorUnit.class;
/* 31:   */  }
/* 32:   */  
/* 35:   */  public void onActivate(le paramle, boolean paramBoolean)
/* 36:   */  {
/* 37:   */    q localq;
/* 38:   */    
/* 40:   */    Iterator localIterator;
/* 41:   */    
/* 42:42 */    if (getSegmentController().isOnServer()) {
/* 43:43 */      localq = paramle.a(new q());
/* 44:44 */      for (localIterator = getCollection().iterator(); localIterator.hasNext();) { DoorUnit localDoorUnit;
/* 45:45 */        if ((localDoorUnit = (DoorUnit)localIterator.next()).contains(localq)) {
/* 46:46 */          localDoorUnit.activate(paramle, paramBoolean);
/* 47:47 */          return;
/* 48:   */        }
/* 49:   */      }
/* 50:   */    }
/* 51:   */  }
/* 52:   */  
/* 59:   */  protected void onChangedCollection() {}
/* 60:   */  
/* 66:   */  public boolean needsUpdate()
/* 67:   */  {
/* 68:68 */    return false;
/* 69:   */  }
/* 70:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.door.DoorCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */