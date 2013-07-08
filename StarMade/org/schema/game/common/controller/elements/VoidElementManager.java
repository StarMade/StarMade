/*  1:   */package org.schema.game.common.controller.elements;
/*  2:   */
/*  3:   */import lA;
/*  4:   */import le;
/*  5:   */import org.schema.game.common.controller.SegmentController;
/*  6:   */
/*  8:   */public class VoidElementManager
/*  9:   */  extends UsableControllableSingleElementManager
/* 10:   */{
/* 11:   */  public VoidElementManager(ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController)
/* 12:   */  {
/* 13:13 */    super(paramElementCollectionManager, paramSegmentController);
/* 14:   */  }
/* 15:   */  
/* 17:   */  public ElementCollectionManager getNewCollectionManager(le paramle)
/* 18:   */  {
/* 19:19 */    return null;
/* 20:   */  }
/* 21:   */  
/* 22:   */  public void handle(lA paramlA) {}
/* 23:   */  
/* 24:   */  public void onControllerChange() {}
/* 25:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.VoidElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */