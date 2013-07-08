/*   1:    */package org.schema.game.common.controller.elements.dockingBeam;
/*   2:    */
/*   3:    */import ct;
/*   4:    */import cz;
/*   5:    */import jL;
/*   6:    */import java.io.PrintStream;
/*   7:    */import java.util.Iterator;
/*   8:    */import java.util.Set;
/*   9:    */import jv;
/*  10:    */import kd;
/*  11:    */import lE;
/*  12:    */import mF;
/*  13:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  14:    */import org.schema.game.common.controller.elements.ManagerContainer;
/*  15:    */import org.schema.game.common.controller.elements.UsableElementManager;
/*  16:    */import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
/*  17:    */import org.schema.game.common.data.world.Segment;
/*  18:    */import org.schema.game.network.objects.NetworkPlayer;
/*  19:    */import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*  20:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  21:    */import x;
/*  22:    */import xq;
/*  23:    */
/*  24:    */public class DockingBeamElementManager extends UsableElementManager
/*  25:    */{
/*  26: 26 */  private javax.vecmath.Vector3f shootingDirTemp = new javax.vecmath.Vector3f();
/*  27:    */  private DockingBeamHandler dockingBeamManager;
/*  28:    */  
/*  29:    */  public DockingBeamElementManager(org.schema.game.common.controller.SegmentController paramSegmentController) {
/*  30: 30 */    super(paramSegmentController);
/*  31: 31 */    this.dockingBeamManager = new DockingBeamHandler(paramSegmentController, this);
/*  32:    */  }
/*  33:    */  
/*  36:    */  public DockingBeamHandler getDockingBeamManager()
/*  37:    */  {
/*  38: 38 */    return this.dockingBeamManager;
/*  39:    */  }
/*  40:    */  
/*  41:    */  public ElementCollectionManager getNewCollectionManager(le paramle)
/*  42:    */  {
/*  43: 43 */    throw new IllegalAccessError("This should not be called. ever");
/*  44:    */  }
/*  45:    */  
/*  46:    */  public void handle(lA paramlA)
/*  47:    */  {
/*  48:    */    try
/*  49:    */    {
/*  50:    */      Object localObject1;
/*  51: 51 */      if (((Boolean)paramlA.jdField_a_of_type_LE.a().activeControllerMask.get(1).get()).booleanValue()) { if (((!(localObject1 = paramlA).jdField_a_of_type_LE.isOnServer()) && ((((lA)localObject1).jdField_a_of_type_Cw instanceof mF)) && (((ct)((lA)localObject1).jdField_a_of_type_LE.getState()).a() != ((mF)((lA)localObject1).jdField_a_of_type_Cw).getSectorId()) ? 0 : 1) != 0) {}
/*  52:    */      } else {
/*  53: 53 */        return;
/*  54:    */      }
/*  55:    */      
/*  56: 56 */      if ((localObject1 = (q)paramlA.jdField_a_of_type_JavaLangObject).equals(kd.a)) {
/*  57: 57 */        Object localObject2 = new q((q)paramlA.jdField_a_of_type_JavaLangObject);
/*  58:    */        
/*  59: 59 */        Object localObject3 = null;
/*  60:    */        
/*  62: 62 */        if ((localObject2 = getSegmentBuffer().a((q)localObject2, getSegmentController().isOnServer(), new le())) == null) {
/*  63: 63 */          return;
/*  64:    */        }
/*  65:    */        
/*  67: 67 */        if (((le)localObject2).a() == 1) {
/*  68:    */          try {
/*  69: 69 */            localObject3 = checkShipConfig(paramlA);
/*  70: 70 */            int i = paramlA.jdField_a_of_type_LE.d();
/*  71: 71 */            if (!((cz)localObject3).a(i)) {
/*  72: 72 */              return;
/*  73:    */            }
/*  74:    */            
/*  75: 75 */            if (!(localObject3 = ((cz)localObject3).a(i)).equals(localObject1))
/*  76:    */            {
/*  77: 77 */              for (paramlA = getSegmentController().getDockingController().a().iterator(); paramlA.hasNext();) {
/*  78: 78 */                if ((localObject1 = (org.schema.game.common.data.element.ElementDocking)paramlA.next()).to.a(new q()).equals(localObject3)) {
/*  79: 79 */                  if (getSegmentController().isOnServer()) {
/*  80: 80 */                    System.err.println("[ELEMENTMANAGER] Requesting undock from " + ((org.schema.game.common.data.element.ElementDocking)localObject1).from.a().a() + " " + ((org.schema.game.common.data.element.ElementDocking)localObject1).from.a().a().getState());
/*  81: 81 */                    ((org.schema.game.common.data.element.ElementDocking)localObject1).from.a().a().getDockingController().b();
/*  82:    */                  } else {
/*  83: 83 */                    ((ct)getState()).a().d("Undocking from\n" + ((org.schema.game.common.data.element.ElementDocking)localObject1).from.a().a().toNiceString());
/*  84:    */                  }
/*  85:    */                }
/*  86:    */              }
/*  87: 87 */              return;
/*  88:    */            }
/*  89:    */          }
/*  90:    */          catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException) {
/*  91: 91 */            return;
/*  92:    */          }
/*  93:    */        }
/*  94: 94 */        if (getSegmentController().getDockingController().a() != null) {
/*  95: 95 */          if (getSegmentController().isOnServer()) {
/*  96: 96 */            System.err.println("[ELEMENTMANAGER] Requesting undock from " + getSegmentController().getDockingController().a());
/*  97: 97 */            getSegmentController().getDockingController().b();
/*  98: 98 */            return;
/*  99:    */          }
/* 100:100 */          if (getSegmentController().getDockingController().a()) {
/* 101:101 */            ((ct)getState()).a().d("Undocking");
/* 102:    */          }
/* 103:103 */          return;
/* 104:    */        }
/* 105:    */        
/* 108:108 */        this.shootingDirTemp.set(paramlA.jdField_a_of_type_LE.a());
/* 109:109 */        this.shootingDirTemp.scale(100.0F);
/* 110:110 */        javax.vecmath.Vector3f localVector3f = new javax.vecmath.Vector3f();
/* 111:111 */        getSegmentController().getAbsoluteElementWorldPosition(new q(), localVector3f);
/* 112:    */        
/* 113:113 */        (
/* 114:    */        
/* 115:115 */          localObject3 = new javax.vecmath.Vector3f()).set(localVector3f);
/* 116:    */        
/* 117:117 */        ((javax.vecmath.Vector3f)localObject3).add(this.shootingDirTemp);
/* 118:118 */        this.dockingBeamManager.addBeam((q)localObject1, localVector3f, (javax.vecmath.Vector3f)localObject3, paramlA.jdField_a_of_type_LE, 0.0F);
/* 119:    */        
/* 120:120 */        getManagerContainer().onAction();
/* 121:    */      }
/* 122:    */      return;
/* 123:123 */    } catch (org.schema.game.common.controller.CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException) { System.err.println("[CLIENT][WARNING] " + getSegmentController() + " Cannot DOCK! segment not yet in buffer " + localCannotImmediateRequestOnClientException.a() + ". -> requested");
/* 124:    */    }
/* 125:    */  }
/* 126:    */  
/* 127:    */  public void update(xq paramxq) {
/* 128:128 */    this.dockingBeamManager.update(paramxq);
/* 129:    */  }
/* 130:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBeam.DockingBeamElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */