/*   1:    */package org.schema.game.common.controller.elements.repair;
/*   2:    */
/*   3:    */import ct;
/*   4:    */import dj;
/*   5:    */import do;
/*   6:    */import java.util.Iterator;
/*   7:    */import java.util.List;
/*   8:    */import lA;
/*   9:    */import lE;
/*  10:    */import le;
/*  11:    */import org.schema.game.common.controller.elements.BlockActivationListenerInterface;
/*  12:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  13:    */import org.schema.game.common.controller.elements.ManagerContainer;
/*  14:    */import org.schema.game.common.controller.elements.UsableControllableElementManager;
/*  15:    */import org.schema.game.common.data.element.ControlElementMap;
/*  16:    */import org.schema.game.network.objects.NetworkPlayer;
/*  17:    */import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*  18:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  19:    */
/*  20:    */public class RepairElementManager extends UsableControllableElementManager implements BlockActivationListenerInterface
/*  21:    */{
/*  22: 22 */  private javax.vecmath.Vector3f shootingDirTemp = new javax.vecmath.Vector3f();
/*  23: 23 */  private q controlledFromOrig = new q();
/*  24: 24 */  private q controlledFrom = new q();
/*  25:    */  private java.util.ArrayList repairBeamManagers;
/*  26:    */  
/*  27:    */  public RepairElementManager(org.schema.game.common.controller.SegmentController paramSegmentController)
/*  28:    */  {
/*  29: 29 */    super((short)39, (short)30, paramSegmentController);
/*  30: 30 */    this.repairBeamManagers = new java.util.ArrayList();
/*  31:    */  }
/*  32:    */  
/*  33:    */  public java.util.ArrayList getCollectionManagers()
/*  34:    */  {
/*  35: 35 */    return this.repairBeamManagers;
/*  36:    */  }
/*  37:    */  
/*  39:    */  public ElementCollectionManager getNewCollectionManager(le paramle)
/*  40:    */  {
/*  41: 41 */    return new RepairBeamCollectionManager(paramle, getSegmentController());
/*  42:    */  }
/*  43:    */  
/*  45:    */  public void handle(lA paramlA)
/*  46:    */  {
/*  47: 47 */    if (!((Boolean)paramlA.a.a().activeControllerMask.get(1).get()).booleanValue()) {
/*  48: 48 */      return;
/*  49:    */    }
/*  50: 50 */    this.shootingDirTemp.set(paramlA.a.a());
/*  51: 51 */    this.shootingDirTemp.scale(100.0F);
/*  52:    */    
/*  55: 55 */    if (getCollectionManagers().isEmpty())
/*  56:    */    {
/*  57: 57 */      return;
/*  58:    */    }
/*  59:    */    
/*  60: 60 */    if (!convertDeligateControls(paramlA, this.controlledFromOrig, this.controlledFrom)) {
/*  61: 61 */      return;
/*  62:    */    }
/*  63:    */    
/*  65: 65 */    for (int i = 0; i < getCollectionManagers().size(); i++)
/*  66:    */    {
/*  67:    */      RepairBeamCollectionManager localRepairBeamCollectionManager;
/*  68: 68 */      int j = 0; if ((localRepairBeamCollectionManager = (RepairBeamCollectionManager)getCollectionManagers().get(i)).equalsControllerPos(this.controlledFrom))
/*  69:    */      {
/*  71: 71 */        if ((this.controlledFromOrig.equals(this.controlledFrom) | getControlElementMap().isControlling(this.controlledFromOrig, localRepairBeamCollectionManager.getControllerPos(), this.controllerId))) {
/*  72: 72 */          for (j = 0; j < localRepairBeamCollectionManager.getCollection().size(); j++) {
/*  73: 73 */            RepairUnit localRepairUnit = (RepairUnit)localRepairBeamCollectionManager.getCollection().get(j);
/*  74: 74 */            javax.vecmath.Vector3f localVector3f = new javax.vecmath.Vector3f();
/*  75:    */            
/*  76: 76 */            Object localObject = localRepairUnit.getOutput();
/*  77:    */            
/*  78: 78 */            localObject = new q(((q)localObject).a - 8, ((q)localObject).b - 8, ((q)localObject).c - 8);
/*  79:    */            
/*  83: 83 */            getSegmentController().getAbsoluteElementWorldPosition((q)localObject, localVector3f);
/*  84:    */            
/*  85: 85 */            (
/*  86: 86 */              localObject = new javax.vecmath.Vector3f()).set(localVector3f);
/*  87:    */            
/*  88: 88 */            ((javax.vecmath.Vector3f)localObject).add(this.shootingDirTemp);
/*  89:    */            
/*  90: 90 */            localRepairBeamCollectionManager.getHandler().addBeam(localRepairUnit.getSignificator(), localVector3f, (javax.vecmath.Vector3f)localObject, paramlA.a, localRepairUnit.getRepairSpeedFactor());
/*  91: 91 */            getManagerContainer().onAction();
/*  92:    */          }
/*  93:    */        }
/*  94:    */      }
/*  95:    */    }
/*  96:    */  }
/*  97:    */  
/* 103:    */  public void onControllerBlockAdd()
/* 104:    */  {
/* 105:105 */    if (!getSegmentController().isOnServer()) {
/* 106:106 */      ((ct)getSegmentController().getState()).a().a().a(this, Boolean.valueOf(true), null);
/* 107:    */    }
/* 108:    */  }
/* 109:    */  
/* 113:    */  public void onControllerBlockRemove()
/* 114:    */  {
/* 115:115 */    if (!getSegmentController().isOnServer()) {
/* 116:116 */      ((ct)getSegmentController().getState()).a().a().a(this, Boolean.valueOf(false), null);
/* 117:    */    }
/* 118:    */  }
/* 119:    */  
/* 120:    */  public void onActivate(le paramle, boolean paramBoolean) {
/* 121:121 */    q localq = paramle.a(new q());
/* 122:122 */    Iterator localIterator; for (int i = 0; i < getCollectionManagers().size(); i++) {
/* 123:123 */      for (localIterator = ((RepairBeamCollectionManager)getCollectionManagers().get(i)).getCollection().iterator(); localIterator.hasNext();) { RepairUnit localRepairUnit;
/* 124:124 */        if ((localRepairUnit = (RepairUnit)localIterator.next()).contains(localq)) {
/* 125:125 */          localRepairUnit.setMainPiece(paramle, paramBoolean);
/* 126:    */          
/* 127:127 */          return;
/* 128:    */        }
/* 129:    */      }
/* 130:    */    }
/* 131:    */  }
/* 132:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.repair.RepairElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */