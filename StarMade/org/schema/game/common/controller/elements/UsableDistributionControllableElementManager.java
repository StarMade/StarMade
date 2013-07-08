/*  1:   */package org.schema.game.common.controller.elements;
/*  2:   */
/*  3:   */import Ah;
/*  4:   */import Aj;
/*  5:   */import java.util.ArrayList;
/*  6:   */import org.schema.game.common.controller.SegmentController;
/*  7:   */
/* 11:   */public abstract class UsableDistributionControllableElementManager
/* 12:   */  extends UsableControllableElementManager
/* 13:   */{
/* 14:   */  public UsableDistributionControllableElementManager(short paramShort1, short paramShort2, SegmentController paramSegmentController)
/* 15:   */  {
/* 16:16 */    super(paramShort1, paramShort2, paramSegmentController);
/* 17:   */  }
/* 18:   */  
/* 19:   */  public Ah toDistributionTagStructure() {
/* 20:20 */    Ah[] arrayOfAh = new Ah[getCollectionManagers().size() + 1];
/* 21:21 */    for (int i = 0; i < getCollectionManagers().size(); i++) {
/* 22:22 */      arrayOfAh[i] = ((DistributionCollectionManager)getCollectionManagers().get(i)).toDistributionTagStructure();
/* 23:   */    }
/* 24:24 */    arrayOfAh[getCollectionManagers().size()] = new Ah(Aj.a, null, null);
/* 25:   */    
/* 26:26 */    return new Ah(Aj.n, "wepContr", arrayOfAh);
/* 27:   */  }
/* 28:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.UsableDistributionControllableElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */