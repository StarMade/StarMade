/*   1:    */package org.schema.game.common.controller.elements.harvest;
/*   2:    */
/*   3:    */import ct;
/*   4:    */import dj;
/*   5:    */import do;
/*   6:    */import java.util.Iterator;
/*   7:    */import java.util.List;
/*   8:    */import lA;
/*   9:    */import lE;
/*  10:    */import org.schema.game.common.controller.elements.BlockActivationListenerInterface;
/*  11:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  12:    */import org.schema.game.common.controller.elements.ManagerContainer;
/*  13:    */import org.schema.game.common.controller.elements.UsableControllableElementManager;
/*  14:    */import org.schema.game.common.data.element.ControlElementMap;
/*  15:    */import org.schema.game.network.objects.NetworkPlayer;
/*  16:    */import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*  17:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  18:    */
/*  19:    */public class SalvageElementManager extends UsableControllableElementManager implements BlockActivationListenerInterface
/*  20:    */{
/*  21: 21 */  private javax.vecmath.Vector3f shootingDirTemp = new javax.vecmath.Vector3f();
/*  22: 22 */  private q controlledFromOrig = new q();
/*  23: 23 */  private q controlledFrom = new q();
/*  24:    */  private java.util.ArrayList salvageBeamCollectionManagers;
/*  25:    */  
/*  26:    */  public SalvageElementManager(org.schema.game.common.controller.SegmentController paramSegmentController)
/*  27:    */  {
/*  28: 28 */    super((short)4, (short)24, paramSegmentController);
/*  29: 29 */    this.salvageBeamCollectionManagers = new java.util.ArrayList();
/*  30:    */  }
/*  31:    */  
/*  32:    */  public java.util.ArrayList getCollectionManagers() {
/*  33: 33 */    return this.salvageBeamCollectionManagers;
/*  34:    */  }
/*  35:    */  
/*  37:    */  public ElementCollectionManager getNewCollectionManager(le paramle)
/*  38:    */  {
/*  39: 39 */    return new SalvageBeamCollectionManager(paramle, getSegmentController());
/*  40:    */  }
/*  41:    */  
/*  42:    */  public void handle(lA paramlA)
/*  43:    */  {
/*  44: 44 */    if (!((Boolean)paramlA.a.a().activeControllerMask.get(1).get()).booleanValue()) {
/*  45: 45 */      return;
/*  46:    */    }
/*  47: 47 */    this.shootingDirTemp.set(paramlA.a.a());
/*  48: 48 */    this.shootingDirTemp.normalize();
/*  49: 49 */    this.shootingDirTemp.scale(100.0F);
/*  50:    */    
/*  53: 53 */    if (this.salvageBeamCollectionManagers.isEmpty())
/*  54:    */    {
/*  55: 55 */      return;
/*  56:    */    }
/*  57:    */    
/*  58: 58 */    if (!convertDeligateControls(paramlA, this.controlledFromOrig, this.controlledFrom)) {
/*  59: 59 */      return;
/*  60:    */    }
/*  61:    */    
/*  62: 62 */    int i = this.salvageBeamCollectionManagers.size();
/*  63: 63 */    SalvageBeamCollectionManager localSalvageBeamCollectionManager; int k; for (int j = 0; j < i; j++)
/*  64:    */    {
/*  66: 66 */      k = 0; if ((localSalvageBeamCollectionManager = (SalvageBeamCollectionManager)this.salvageBeamCollectionManagers.get(j)).equalsControllerPos(this.controlledFrom))
/*  67:    */      {
/*  69: 69 */        if ((this.controlledFromOrig.equals(this.controlledFrom) | getControlElementMap().isControlling(this.controlledFromOrig, localSalvageBeamCollectionManager.getControllerPos(), this.controllerId)))
/*  70:    */        {
/*  71: 71 */          for (k = 0; k < localSalvageBeamCollectionManager.getCollection().size();) {
/*  72: 72 */            SalvageUnit localSalvageUnit = (SalvageUnit)localSalvageBeamCollectionManager.getCollection().get(k);
/*  73: 73 */            javax.vecmath.Vector3f localVector3f = new javax.vecmath.Vector3f();
/*  74:    */            
/*  75: 75 */            Object localObject = localSalvageUnit.getOutput();
/*  76:    */            
/*  77: 77 */            localObject = new q(((q)localObject).a - 8, ((q)localObject).b - 8, ((q)localObject).c - 8);
/*  78:    */            
/*  82: 82 */            getSegmentController().getAbsoluteElementWorldPosition((q)localObject, localVector3f);
/*  83:    */            
/*  84: 84 */            (
/*  85: 85 */              localObject = new javax.vecmath.Vector3f()).set(localVector3f);
/*  86:    */            
/*  87: 87 */            ((javax.vecmath.Vector3f)localObject).add(this.shootingDirTemp);
/*  88: 88 */            localSalvageBeamCollectionManager.getHandler().addBeam(localSalvageUnit.getSignificator(), localVector3f, (javax.vecmath.Vector3f)localObject, paramlA.a, localSalvageUnit.getSalvageSpeedFactor());
/*  89: 89 */            getManagerContainer().onAction();
/*  90: 90 */            k++;
/*  91:    */          }
/*  92:    */        }
/*  93:    */      }
/*  94:    */    }
/*  95:    */  }
/*  96:    */  
/* 100:    */  public void onControllerBlockAdd()
/* 101:    */  {
/* 102:102 */    if (!getSegmentController().isOnServer()) {
/* 103:103 */      ((ct)getSegmentController().getState()).a().a().a(this, Boolean.valueOf(true), null);
/* 104:    */    }
/* 105:    */  }
/* 106:    */  
/* 110:    */  public void onControllerBlockRemove()
/* 111:    */  {
/* 112:112 */    if (!getSegmentController().isOnServer()) {
/* 113:113 */      ((ct)getSegmentController().getState()).a().a().a(this, Boolean.valueOf(false), null);
/* 114:    */    }
/* 115:    */  }
/* 116:    */  
/* 117:    */  public void onActivate(le paramle, boolean paramBoolean) {
/* 118:118 */    q localq = paramle.a(new q());
/* 119:119 */    Iterator localIterator; for (int i = 0; i < this.salvageBeamCollectionManagers.size(); i++) {
/* 120:120 */      for (localIterator = ((SalvageBeamCollectionManager)this.salvageBeamCollectionManagers.get(i)).getCollection().iterator(); localIterator.hasNext();) { SalvageUnit localSalvageUnit;
/* 121:121 */        if ((localSalvageUnit = (SalvageUnit)localIterator.next()).contains(localq)) {
/* 122:122 */          localSalvageUnit.setMainPiece(paramle, paramBoolean);
/* 123:    */          
/* 124:124 */          return;
/* 125:    */        }
/* 126:    */      }
/* 127:    */    }
/* 128:    */  }
/* 129:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.harvest.SalvageElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */