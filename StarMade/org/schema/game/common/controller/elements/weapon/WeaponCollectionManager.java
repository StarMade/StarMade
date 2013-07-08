/*  1:   */package org.schema.game.common.controller.elements.weapon;
/*  2:   */
/*  3:   */import ct;
/*  4:   */import dj;
/*  5:   */import fe;
/*  6:   */import le;
/*  7:   */import org.schema.game.common.controller.SegmentController;
/*  8:   */import org.schema.game.common.controller.elements.DistributionCollectionManager;
/*  9:   */
/* 10:   */public class WeaponCollectionManager
/* 11:   */  extends DistributionCollectionManager
/* 12:   */{
/* 13:   */  public WeaponCollectionManager(le paramle, SegmentController paramSegmentController)
/* 14:   */  {
/* 15:15 */    super(paramle, (short)16, paramSegmentController);
/* 16:   */  }
/* 17:   */  
/* 20:   */  public int getMargin()
/* 21:   */  {
/* 22:22 */    return 0;
/* 23:   */  }
/* 24:   */  
/* 28:   */  protected Class getType()
/* 29:   */  {
/* 30:30 */    return WeaponUnit.class;
/* 31:   */  }
/* 32:   */  
/* 34:   */  protected void onChangedCollection()
/* 35:   */  {
/* 36:36 */    if (!getSegmentController().isOnServer()) {
/* 37:37 */      ((ct)getSegmentController().getState()).a().a().a(this);
/* 38:   */    }
/* 39:   */  }
/* 40:   */  
/* 41:   */  public boolean needsUpdate()
/* 42:   */  {
/* 43:43 */    return false;
/* 44:   */  }
/* 45:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.weapon.WeaponCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */