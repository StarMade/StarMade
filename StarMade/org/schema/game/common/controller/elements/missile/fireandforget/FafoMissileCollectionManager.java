/*  1:   */package org.schema.game.common.controller.elements.missile.fireandforget;
/*  2:   */
/*  3:   */import ct;
/*  4:   */import dj;
/*  5:   */import fe;
/*  6:   */import le;
/*  7:   */import org.schema.game.common.controller.SegmentController;
/*  8:   */import org.schema.game.common.controller.elements.DistributionCollectionManager;
/*  9:   */import org.schema.game.common.controller.elements.missile.MissileUnit;
/* 10:   */
/* 11:   */public class FafoMissileCollectionManager extends DistributionCollectionManager
/* 12:   */{
/* 13:   */  public FafoMissileCollectionManager(le paramle, SegmentController paramSegmentController)
/* 14:   */  {
/* 15:15 */    super(paramle, (short)48, paramSegmentController);
/* 16:   */  }
/* 17:   */  
/* 20:   */  public int getMargin()
/* 21:   */  {
/* 22:22 */    return 0;
/* 23:   */  }
/* 24:   */  
/* 27:   */  protected Class getType()
/* 28:   */  {
/* 29:29 */    return MissileUnit.class;
/* 30:   */  }
/* 31:   */  
/* 36:   */  protected void onChangedCollection()
/* 37:   */  {
/* 38:38 */    if (!getSegmentController().isOnServer()) {
/* 39:39 */      ((ct)getSegmentController().getState()).a().a().a(this);
/* 40:   */    }
/* 41:   */  }
/* 42:   */  
/* 44:   */  public boolean needsUpdate()
/* 45:   */  {
/* 46:46 */    return false;
/* 47:   */  }
/* 48:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.fireandforget.FafoMissileCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */