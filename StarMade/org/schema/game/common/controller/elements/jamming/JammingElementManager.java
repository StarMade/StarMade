/*   1:    */package org.schema.game.common.controller.elements.jamming;
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
/*  13:    */import org.schema.game.common.controller.SegmentController;
/*  14:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  15:    */import org.schema.game.common.controller.elements.ManagerActivityInterface;
/*  16:    */import org.schema.game.common.controller.elements.PowerAddOn;
/*  17:    */import org.schema.game.common.controller.elements.PowerManagerInterface;
/*  18:    */import org.schema.game.common.controller.elements.UpdatableCollectionManager;
/*  19:    */import org.schema.game.common.controller.elements.UsableControllableSingleElementManager;
/*  20:    */import org.schema.game.network.objects.NetworkPlayer;
/*  21:    */import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*  22:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  23:    */import q;
/*  24:    */import wm;
/*  25:    */import wp;
/*  26:    */import xq;
/*  27:    */
/*  28:    */public class JammingElementManager
/*  29:    */  extends UsableControllableSingleElementManager implements ManagerActivityInterface, UpdatableCollectionManager
/*  30:    */{
/*  31:    */  private JammingCollectionManager jamManager;
/*  32: 32 */  private boolean jamming = false;
/*  33:    */  
/*  34:    */  private long jamStartTime;
/*  35: 35 */  private q controlledFromOrig = new q();
/*  36:    */  
/*  37: 37 */  private q controlledFrom = new q();
/*  38:    */  
/*  40: 40 */  private float POWER_CONSUME_MULT = 1000.0F;
/*  41:    */  
/*  42:    */  public JammingElementManager(JammingCollectionManager paramJammingCollectionManager, SegmentController paramSegmentController)
/*  43:    */  {
/*  44: 44 */    super(paramJammingCollectionManager, paramSegmentController);
/*  45: 45 */    this.jamManager = paramJammingCollectionManager;
/*  46:    */  }
/*  47:    */  
/*  48:    */  public float getActualJam()
/*  49:    */  {
/*  50: 50 */    return this.jamManager.getTotalJam();
/*  51:    */  }
/*  52:    */  
/*  55:    */  public long getJamStartTime()
/*  56:    */  {
/*  57: 57 */    return this.jamStartTime;
/*  58:    */  }
/*  59:    */  
/*  60:    */  public ElementCollectionManager getNewCollectionManager(le paramle)
/*  61:    */  {
/*  62: 62 */    return new JammingCollectionManager(getSegmentController());
/*  63:    */  }
/*  64:    */  
/*  75:    */  public void handle(lA paramlA)
/*  76:    */  {
/*  77: 77 */    if (getSegmentController().isOnServer()) {
/*  78: 78 */      if (!((Boolean)paramlA.jdField_a_of_type_LE.a().activeControllerMask.get(1).get()).booleanValue()) {
/*  79: 79 */        return;
/*  80:    */      }
/*  81: 81 */      if (System.currentTimeMillis() - this.jamStartTime < 600L) {
/*  82: 82 */        return;
/*  83:    */      }
/*  84: 84 */      if ((!kd.a.equals(paramlA.jdField_a_of_type_JavaLangObject)) || (this.jamManager.getCollection().isEmpty()))
/*  85:    */      {
/*  86: 86 */        return;
/*  87:    */      }
/*  88: 88 */      if (!convertDeligateControls(paramlA, this.controlledFromOrig, this.controlledFrom)) {
/*  89: 89 */        return;
/*  90:    */      }
/*  91: 91 */      getActualJam();
/*  92:    */      try
/*  93:    */      {
/*  94: 94 */        if (this.controlledFrom.equals(((JammingUnit)this.jamManager.getCollection().get(0)).getId().a(new q()))) {
/*  95: 95 */          if (!isJamming()) {
/*  96: 96 */            System.err.println("[JAMMING] NOW JAMMING");
/*  97: 97 */            setJamStartTime(System.currentTimeMillis());
/*  98: 98 */            setJamming(true);return;
/*  99:    */          }
/* 100:100 */          stopJamming();
/* 101:    */        }
/* 102:    */        return;
/* 103:103 */      } catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException) { 
/* 104:    */        
/* 105:105 */          localCannotImmediateRequestOnClientException;
/* 106:    */      }
/* 107:    */    }
/* 108:    */  }
/* 109:    */  
/* 114:    */  public boolean isJamming()
/* 115:    */  {
/* 116:114 */    if (getSegmentController().isOnServer()) {
/* 117:115 */      return this.jamming;
/* 118:    */    }
/* 119:117 */    return ((kd)getSegmentController()).c();
/* 120:    */  }
/* 121:    */  
/* 124:    */  public void onControllerChange() {}
/* 125:    */  
/* 127:    */  public void onHit()
/* 128:    */  {
/* 129:127 */    stopJamming();
/* 130:    */  }
/* 131:    */  
/* 134:    */  public void setJamming(boolean paramBoolean)
/* 135:    */  {
/* 136:134 */    this.jamming = paramBoolean;
/* 137:    */  }
/* 138:    */  
/* 142:    */  public void setJamStartTime(long paramLong)
/* 143:    */  {
/* 144:142 */    this.jamStartTime = paramLong;
/* 145:    */  }
/* 146:    */  
/* 149:    */  public void stopJamming()
/* 150:    */  {
/* 151:149 */    if (isJamming()) {
/* 152:150 */      System.err.println("Stopped jamming -> reloading");
/* 153:151 */      setJamming(false);
/* 154:152 */      setJamStartTime(System.currentTimeMillis());
/* 155:    */    }
/* 156:    */  }
/* 157:    */  
/* 158:    */  public void update(xq paramxq)
/* 159:    */  {
/* 160:158 */    PowerAddOn localPowerAddOn = ((PowerManagerInterface)((ld)getSegmentController()).a()).getPowerAddOn();
/* 161:159 */    paramxq = getSegmentController().getTotalElements() / 20.0F * paramxq.a() * this.POWER_CONSUME_MULT;
/* 162:    */    
/* 163:161 */    if ((isJamming()) && (!localPowerAddOn.consumePowerInstantly(paramxq)) && 
/* 164:162 */      (getSegmentController().isOnServer())) {
/* 165:163 */      stopJamming();
/* 166:    */    }
/* 167:    */    
/* 169:167 */    if ((isJamming()) && (((cw)getSegmentController()).a().isEmpty()) && (!((wp)getSegmentController()).a().a()) && 
/* 170:168 */      (getSegmentController().isOnServer())) {
/* 171:169 */      stopJamming();
/* 172:    */    }
/* 173:    */  }
/* 174:    */  
/* 176:    */  public boolean isActive()
/* 177:    */  {
/* 178:176 */    return isJamming();
/* 179:    */  }
/* 180:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.jamming.JammingElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */