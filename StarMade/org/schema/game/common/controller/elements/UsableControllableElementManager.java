/*   1:    */package org.schema.game.common.controller.elements;
/*   2:    */
/*   3:    */import Ah;
/*   4:    */import Aj;
/*   5:    */import cz;
/*   6:    */import jE;
/*   7:    */import jJ;
/*   8:    */import jL;
/*   9:    */import java.io.PrintStream;
/*  10:    */import java.util.Set;
/*  11:    */import lA;
/*  12:    */import lE;
/*  13:    */import le;
/*  14:    */import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/*  15:    */import org.schema.game.common.controller.SegmentController;
/*  16:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  17:    */import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
/*  18:    */
/*  19:    */public abstract class UsableControllableElementManager extends UsableElementManager
/*  20:    */{
/*  21:    */  protected short controllerId;
/*  22:    */  private short controllingId;
/*  23:    */  private org.schema.game.common.data.element.ElementInformation controllerInfo;
/*  24:    */  private org.schema.game.common.data.element.ElementInformation controllingInfo;
/*  25:    */  
/*  26:    */  public UsableControllableElementManager(short paramShort1, short paramShort2, SegmentController paramSegmentController)
/*  27:    */  {
/*  28: 28 */    super(paramSegmentController);
/*  29:    */    
/*  33: 33 */    this.controllerId = paramShort1;
/*  34: 34 */    this.controllingId = paramShort2;
/*  35:    */    
/*  36: 36 */    this.controllerInfo = ElementKeyMap.getInfo(paramShort1);
/*  37: 37 */    this.controllingInfo = ElementKeyMap.getInfo(paramShort2);
/*  38:    */    
/*  39: 39 */    assert (this.controllerInfo.getControlling().contains(Short.valueOf(paramShort2))) : (this.controllerInfo.getControlling() + " : " + paramShort2);
/*  40: 40 */    assert (this.controllingInfo.getControlledBy().contains(Short.valueOf(paramShort1))) : (this.controllingInfo.getName() + ": controlled by set " + this.controllingInfo.getControlledBy() + " does not contain " + ElementKeyMap.getInfo(paramShort1) + "(" + paramShort1 + ")");
/*  41:    */  }
/*  42:    */  
/*  43: 43 */  public void addControllerBlockIfNecessary(q paramq1, q paramq2, short arg3) { if (??? == this.controllingId) {
/*  44: 44 */      synchronized (getCollectionManagers()) {
/*  45: 45 */        for (java.util.Iterator localIterator = getCollectionManagers().iterator(); localIterator.hasNext();) { ControlBlockElementCollectionManager localControlBlockElementCollectionManager;
/*  46: 46 */          if ((localControlBlockElementCollectionManager = (ControlBlockElementCollectionManager)localIterator.next()).equalsControllerPos(paramq1)) {
/*  47: 47 */            localControlBlockElementCollectionManager.addModded(paramq2);
/*  48: 48 */            notifyObservers(jJ.a);
/*  49: 49 */            return;
/*  50:    */          }
/*  51:    */        }
/*  52: 52 */        return;
/*  53:    */      }
/*  54:    */    }
/*  55:    */  }
/*  56:    */  
/*  57:    */  protected boolean receiveDistribution(jE paramjE) {
/*  58: 58 */    for (java.util.Iterator localIterator = getCollectionManagers().iterator(); localIterator.hasNext();) { ControlBlockElementCollectionManager localControlBlockElementCollectionManager;
/*  59: 59 */      if (((localControlBlockElementCollectionManager = (ControlBlockElementCollectionManager)localIterator.next()).getControllerPos().equals(paramjE.a)) && 
/*  60: 60 */        (localControlBlockElementCollectionManager.receiveDistribution(paramjE))) {
/*  61: 61 */        return true;
/*  62:    */      }
/*  63:    */    }
/*  64:    */    
/*  67: 67 */    return false;
/*  68:    */  }
/*  69:    */  
/*  70: 70 */  protected boolean convertDeligateControls(lA paramlA, q paramq1, q paramq2) { paramq1.b((q)paramlA.jdField_a_of_type_JavaLangObject);
/*  71: 71 */    paramq2.b((q)paramlA.jdField_a_of_type_JavaLangObject);
/*  72:    */    
/*  74: 74 */    paramq1 = null;
/*  75:    */    
/*  76:    */    try
/*  77:    */    {
/*  78: 78 */      if (((paramq1 = getSegmentBuffer().a(paramq2, true, new le())) != null) && (paramq1.a() == 1)) {
/*  79:    */        try {
/*  80: 80 */          paramq1 = checkShipConfig(paramlA);
/*  81: 81 */          paramlA = paramlA.jdField_a_of_type_LE.d();
/*  82: 82 */          if (!paramq1.a(paramlA)) {
/*  83: 83 */            return false;
/*  84:    */          }
/*  85: 85 */          paramq2.b(paramq1.a(paramlA));
/*  86:    */        }
/*  87:    */        catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException) {
/*  88: 88 */          return false;
/*  89:    */        }
/*  90:    */      }
/*  91:    */    } catch (CannotImmediateRequestOnClientException paramq1) {
/*  92: 92 */      System.err.println("[CLIENT][WARNING] deferring request for deligated control. segment has been requested: " + paramq1.a());
/*  93: 93 */      return false;
/*  94:    */    }
/*  95: 95 */    return true;
/*  96:    */  }
/*  97:    */  
/*  99:    */  public abstract java.util.ArrayList getCollectionManagers();
/* 100:    */  
/* 102:    */  public void onControllerBlockAdd() {}
/* 103:    */  
/* 104:    */  public void onControllerBlockRemove() {}
/* 105:    */  
/* 106:    */  public void removeControllerIfNecessary(q paramq1, q paramq2, short arg3)
/* 107:    */  {
/* 108:108 */    if (??? == this.controllingId)
/* 109:109 */      synchronized (getCollectionManagers()) {
/* 110:110 */        for (java.util.Iterator localIterator = getCollectionManagers().iterator(); localIterator.hasNext();) { ControlBlockElementCollectionManager localControlBlockElementCollectionManager;
/* 111:111 */          if ((localControlBlockElementCollectionManager = (ControlBlockElementCollectionManager)localIterator.next()).equalsControllerPos(paramq1)) {
/* 112:112 */            localControlBlockElementCollectionManager.remove(paramq2);
/* 113:113 */            notifyObservers(jJ.a);
/* 114:114 */            return;
/* 115:    */          }
/* 116:    */        }
/* 117:117 */        return;
/* 118:    */      }
/* 119:    */  }
/* 120:    */  
/* 121:121 */  public boolean hasMetaData() { return (getCollectionManagers().size() > 0) && (((ControlBlockElementCollectionManager)getCollectionManagers().get(0)).hasMetaData()); }
/* 122:    */  
/* 123:    */  public Ah toTagStructure() {
/* 124:124 */    Ah[] arrayOfAh = new Ah[getCollectionManagers().size() + 1];
/* 125:125 */    for (int i = 0; i < getCollectionManagers().size(); i++) {
/* 126:126 */      arrayOfAh[i] = ((ControlBlockElementCollectionManager)getCollectionManagers().get(i)).toTagStructure();
/* 127:    */    }
/* 128:128 */    arrayOfAh[getCollectionManagers().size()] = new Ah(Aj.a, null, null);
/* 129:    */    
/* 130:130 */    return new Ah(Aj.n, "wepContr", arrayOfAh);
/* 131:    */  }
/* 132:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.UsableControllableElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */