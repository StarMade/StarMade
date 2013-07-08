/*   1:    */package org.schema.game.common.controller.elements;
/*   2:    */
/*   3:    */import L;
/*   4:    */import com.bulletphysics.linearmath.Transform;
/*   5:    */import ct;
/*   6:    */import cw;
/*   7:    */import cz;
/*   8:    */import jL;
/*   9:    */import java.util.ArrayList;
/*  10:    */import java.util.concurrent.ConcurrentHashMap;
/*  11:    */import kW;
/*  12:    */import kd;
/*  13:    */import lA;
/*  14:    */import lE;
/*  15:    */import ld;
/*  16:    */import le;
/*  17:    */import org.schema.game.common.controller.SegmentController;
/*  18:    */import org.schema.game.common.data.element.ControlElementMap;
/*  19:    */import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
/*  20:    */import org.schema.schine.network.StateInterface;
/*  21:    */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*  22:    */import q;
/*  23:    */
/*  27:    */public abstract class UsableElementManager
/*  28:    */  extends kW
/*  29:    */{
/*  30:    */  private final SegmentController segmentController;
/*  31:    */  
/*  32:    */  public UsableElementManager(SegmentController paramSegmentController)
/*  33:    */  {
/*  34: 34 */    this.segmentController = paramSegmentController;
/*  35:    */  }
/*  36:    */  
/*  44:    */  public boolean canHandle(lA paramlA)
/*  45:    */  {
/*  46: 46 */    return paramlA.jdField_a_of_type_LE.b();
/*  47:    */  }
/*  48:    */  
/*  49:    */  public cz checkShipConfig(lA paramlA) {
/*  50: 50 */    if (!paramlA.jdField_a_of_type_LE.a(getSegmentController())) {
/*  51: 51 */      throw new ShipConfigurationNotFoundException("does not exist for that ship");
/*  52:    */    }
/*  53: 53 */    reassignControllerKeysIfNecessary(paramlA.jdField_a_of_type_LE, (q)paramlA.jdField_a_of_type_JavaLangObject);
/*  54:    */    
/*  57: 57 */    return paramlA.jdField_a_of_type_LE.a(getSegmentController());
/*  58:    */  }
/*  59:    */  
/*  60:    */  protected boolean clientIsOwnShip() {
/*  61: 61 */    return ((cw)this.segmentController).isClientOwnObject();
/*  62:    */  }
/*  63:    */  
/*  64: 64 */  protected ArrayList getAttachedPlayers() { return ((kd)this.segmentController).a(); }
/*  65:    */  
/*  66:    */  public ControlElementMap getControlElementMap() {
/*  67: 67 */    return this.segmentController.getControlElementMap();
/*  68:    */  }
/*  69:    */  
/*  71:    */  public ManagerContainer getManagerContainer()
/*  72:    */  {
/*  73: 73 */    return ((ld)this.segmentController).a();
/*  74:    */  }
/*  75:    */  
/*  76:    */  public abstract ElementCollectionManager getNewCollectionManager(le paramle);
/*  77:    */  
/*  78:    */  protected L getParticleController() {
/*  79: 79 */    return ((ParticleHandler)this.segmentController).getParticleController();
/*  80:    */  }
/*  81:    */  
/*  82:    */  protected PhysicsDataContainer getPhysicsDataContainer() {
/*  83: 83 */    return this.segmentController.getPhysicsDataContainer();
/*  84:    */  }
/*  85:    */  
/*  86: 86 */  protected PowerAddOn getPowerManager() { return ((PowerManagerInterface)getManagerContainer()).getPowerAddOn(); }
/*  87:    */  
/*  88:    */  protected jL getSegmentBuffer() {
/*  89: 89 */    return this.segmentController.getSegmentBuffer();
/*  90:    */  }
/*  91:    */  
/*  94:    */  public SegmentController getSegmentController()
/*  95:    */  {
/*  96: 96 */    return this.segmentController;
/*  97:    */  }
/*  98:    */  
/*  99:    */  protected StateInterface getState() {
/* 100:100 */    return this.segmentController.getState();
/* 101:    */  }
/* 102:    */  
/* 103:    */  protected Transform getWorldTransform() {
/* 104:104 */    return this.segmentController.getWorldTransform();
/* 105:    */  }
/* 106:    */  
/* 108:    */  public abstract void handle(lA paramlA);
/* 109:    */  
/* 110:    */  private void reassignControllerKeysIfNecessary(lE paramlE, q paramq)
/* 111:    */  {
/* 112:112 */    paramlE = paramlE.a(getSegmentController());
/* 113:113 */    if (((getState() instanceof ct)) && (
/* 114:114 */      (paramlE.a.size() == 0) || ((paramlE.a.size() == 1) && (paramlE.a(9)) && (paramlE.a(9).a(8, 8, 8)))))
/* 115:    */    {
/* 118:118 */      paramq = getControlElementMap().getControlledElements((short)32767, paramq);
/* 119:119 */      paramlE.a((ct)getState(), paramq);
/* 120:    */    }
/* 121:    */  }
/* 122:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.UsableElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */