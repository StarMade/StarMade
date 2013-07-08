/*  1:   */package org.schema.game.common.controller.elements.missile;
/*  2:   */
/*  3:   */import java.util.ArrayList;
/*  4:   */import org.schema.common.FastMath;
/*  5:   */import org.schema.game.common.controller.SegmentController;
/*  6:   */import org.schema.game.common.controller.elements.BlockActivationListenerInterface;
/*  7:   */import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;
/*  8:   */import org.schema.game.common.controller.elements.ManagerReloadInterface;
/*  9:   */import org.schema.game.common.controller.elements.NTDistributionReceiverInterface;
/* 10:   */import org.schema.game.common.controller.elements.NTReceiveInterface;
/* 11:   */import org.schema.game.common.controller.elements.NTSenderInterface;
/* 12:   */import org.schema.game.common.controller.elements.UsableDistributionControllableElementManager;
/* 13:   */import q;
/* 14:   */import yE;
/* 15:   */
/* 16:   */public abstract class MissileElementManager
/* 17:   */  extends UsableDistributionControllableElementManager
/* 18:   */  implements BlockActivationListenerInterface, ManagerReloadInterface, NTDistributionReceiverInterface, NTReceiveInterface, NTSenderInterface
/* 19:   */{
/* 20:20 */  public MissileElementManager(short paramShort1, short paramShort2, SegmentController paramSegmentController) { super(paramShort1, paramShort2, paramSegmentController); }
/* 21:   */  
/* 22:   */  public void drawReloads(yE paramyE, q paramq) {
/* 23:   */    float f;
/* 24:24 */    for (int i = 0; i < getCollectionManagers().size(); f++) {
/* 25:25 */      if (((ControlBlockElementCollectionManager)getCollectionManagers().get(i)).getControllerPos().equals(paramq))
/* 26:   */      {
/* 27:27 */        for (MissileUnit localMissileUnit : ((ControlBlockElementCollectionManager)getCollectionManagers().get(i)).getCollection())
/* 28:   */        {
/* 33:33 */          int j = (int)(System.currentTimeMillis() - localMissileUnit.getLastShoot());
/* 34:34 */          f = Math.min(2.0F, j / localMissileUnit.getRelaodTime());
/* 35:   */          
/* 36:36 */          j = 9;
/* 37:37 */          if (f <= 1.0F) {
/* 38:38 */            j = (int)FastMath.a(9.0F + f * 8.0F, 9.0F, 17.0F);
/* 39:   */          }
/* 40:   */          
/* 41:41 */          paramyE.a_(j);
/* 42:42 */          paramyE.b();
/* 43:   */        }
/* 44:44 */        return;
/* 45:   */      }
/* 46:   */    }
/* 47:   */  }
/* 48:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.MissileElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */