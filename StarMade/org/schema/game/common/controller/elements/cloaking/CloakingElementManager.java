/*   1:    */package org.schema.game.common.controller.elements.cloaking;
/*   2:    */
/*   3:    */import cw;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.util.ArrayList;
/*   6:    */import java.util.List;
/*   7:    */import kd;
/*   8:    */import lA;
/*   9:    */import lE;
/*  10:    */import ld;
/*  11:    */import le;
/*  12:    */import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/*  13:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  14:    */import org.schema.game.common.controller.elements.ManagerActivityInterface;
/*  15:    */import org.schema.game.common.controller.elements.PowerAddOn;
/*  16:    */import org.schema.game.common.controller.elements.PowerManagerInterface;
/*  17:    */import org.schema.game.common.controller.elements.UpdatableCollectionManager;
/*  18:    */import org.schema.game.common.controller.elements.UsableControllableSingleElementManager;
/*  19:    */import org.schema.game.network.objects.NetworkPlayer;
/*  20:    */import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*  21:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  22:    */import wm;
/*  23:    */import wp;
/*  24:    */import xq;
/*  25:    */
/*  26:    */public class CloakingElementManager extends UsableControllableSingleElementManager implements ManagerActivityInterface, UpdatableCollectionManager
/*  27:    */{
/*  28:    */  private CloakingCollectionManager cloakManager;
/*  29: 29 */  private boolean cloaked = false;
/*  30:    */  
/*  31:    */  private long cloakStartTime;
/*  32: 32 */  private q controlledFromOrig = new q();
/*  33:    */  
/*  34: 34 */  private q controlledFrom = new q();
/*  35:    */  
/*  36: 36 */  private float POWER_CONSUME_MULT = 2000.0F;
/*  37:    */  
/*  38:    */  public CloakingElementManager(org.schema.game.common.controller.SegmentController paramSegmentController) {
/*  39: 39 */    super(new CloakingCollectionManager(paramSegmentController), paramSegmentController);
/*  40: 40 */    this.cloakManager = ((CloakingCollectionManager)getCollection());
/*  41:    */  }
/*  42:    */  
/*  43:    */  public float getActualCloak()
/*  44:    */  {
/*  45: 45 */    return this.cloakManager.getTotalCloak();
/*  46:    */  }
/*  47:    */  
/*  49:    */  public long getCloakStartTime()
/*  50:    */  {
/*  51: 51 */    return this.cloakStartTime;
/*  52:    */  }
/*  53:    */  
/*  54:    */  public ElementCollectionManager getNewCollectionManager(le paramle)
/*  55:    */  {
/*  56: 56 */    return new CloakingCollectionManager(getSegmentController());
/*  57:    */  }
/*  58:    */  
/*  68:    */  public void handle(lA paramlA)
/*  69:    */  {
/*  70: 70 */    if (getSegmentController().isOnServer()) {
/*  71: 71 */      if (!((Boolean)paramlA.jdField_a_of_type_LE.a().activeControllerMask.get(1).get()).booleanValue()) {
/*  72: 72 */        return;
/*  73:    */      }
/*  74: 74 */      if (System.currentTimeMillis() - getCloakStartTime() < 600L) {
/*  75: 75 */        return;
/*  76:    */      }
/*  77: 77 */      if ((!kd.a.equals(paramlA.jdField_a_of_type_JavaLangObject)) || (this.cloakManager.getCollection().isEmpty()))
/*  78:    */      {
/*  79: 79 */        return;
/*  80:    */      }
/*  81: 81 */      if (!convertDeligateControls(paramlA, this.controlledFromOrig, this.controlledFrom)) {
/*  82: 82 */        return;
/*  83:    */      }
/*  84: 84 */      getActualCloak();
/*  85:    */      try {
/*  86: 86 */        if (this.controlledFrom.equals(((CloakingUnit)this.cloakManager.getCollection().get(0)).getId().a(new q()))) {
/*  87: 87 */          if (!isCloaked()) {
/*  88: 88 */            System.err.println("CLOAKING STARTED");
/*  89: 89 */            setCloakStartTime(System.currentTimeMillis());
/*  90: 90 */            setCloaked(true);return;
/*  91:    */          }
/*  92: 92 */          stopCloak();
/*  93:    */        }
/*  94:    */        return;
/*  95:    */      }
/*  96:    */      catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException) {
/*  97: 97 */        
/*  98:    */        
/*  99: 99 */          localCannotImmediateRequestOnClientException;
/* 100:    */      }
/* 101:    */    }
/* 102:    */  }
/* 103:    */  
/* 108:    */  public boolean isCloaked()
/* 109:    */  {
/* 110:108 */    if (getSegmentController().isOnServer()) {
/* 111:109 */      return this.cloaked;
/* 112:    */    }
/* 113:111 */    return ((kd)getSegmentController()).a();
/* 114:    */  }
/* 115:    */  
/* 117:    */  public void onControllerChange() {}
/* 118:    */  
/* 120:    */  public void onHit()
/* 121:    */  {
/* 122:120 */    stopCloak();
/* 123:    */  }
/* 124:    */  
/* 127:    */  public void setCloaked(boolean paramBoolean)
/* 128:    */  {
/* 129:127 */    this.cloaked = paramBoolean;
/* 130:    */  }
/* 131:    */  
/* 135:    */  public void setCloakStartTime(long paramLong)
/* 136:    */  {
/* 137:135 */    this.cloakStartTime = paramLong;
/* 138:    */  }
/* 139:    */  
/* 140:    */  public void stopCloak()
/* 141:    */  {
/* 142:140 */    if (isCloaked()) {
/* 143:141 */      setCloaked(false);
/* 144:142 */      setCloakStartTime(System.currentTimeMillis());
/* 145:    */    }
/* 146:    */  }
/* 147:    */  
/* 148:    */  public void update(xq paramxq)
/* 149:    */  {
/* 150:148 */    PowerAddOn localPowerAddOn = ((PowerManagerInterface)((ld)getSegmentController()).a()).getPowerAddOn();
/* 151:149 */    paramxq = getSegmentController().getTotalElements() / 20.0F * paramxq.a() * this.POWER_CONSUME_MULT;
/* 152:    */    
/* 153:151 */    if ((isCloaked()) && (!localPowerAddOn.consumePowerInstantly(paramxq)) && 
/* 154:152 */      (getSegmentController().isOnServer())) {
/* 155:153 */      stopCloak();
/* 156:    */    }
/* 157:    */    
/* 159:157 */    if ((isCloaked()) && (((cw)getSegmentController()).a().isEmpty()) && (!((wp)getSegmentController()).a().a()) && 
/* 160:158 */      (getSegmentController().isOnServer())) {
/* 161:159 */      stopCloak();
/* 162:    */    }
/* 163:    */  }
/* 164:    */  
/* 166:    */  public boolean isActive()
/* 167:    */  {
/* 168:166 */    return isCloaked();
/* 169:    */  }
/* 170:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.cloaking.CloakingElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */