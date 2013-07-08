/*   1:    */package org.schema.game.common.controller.elements.weapon;
/*   2:    */
/*   3:    */import L;
/*   4:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*   5:    */import ct;
/*   6:    */import jE;
/*   7:    */import java.util.Iterator;
/*   8:    */import java.util.List;
/*   9:    */import javax.vecmath.Tuple3f;
/*  10:    */import jv;
/*  11:    */import kd;
/*  12:    */import lA;
/*  13:    */import lE;
/*  14:    */import ld;
/*  15:    */import le;
/*  16:    */import org.schema.common.FastMath;
/*  17:    */import org.schema.game.common.controller.elements.BlockActivationListenerInterface;
/*  18:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  19:    */import org.schema.game.common.controller.elements.ManagerContainer;
/*  20:    */import org.schema.game.common.controller.elements.ManagerReloadInterface;
/*  21:    */import org.schema.game.common.controller.elements.NTDistributionReceiverInterface;
/*  22:    */import org.schema.game.common.controller.elements.NTReceiveInterface;
/*  23:    */import org.schema.game.common.controller.elements.NTSenderInterface;
/*  24:    */import org.schema.game.common.controller.elements.PowerAddOn;
/*  25:    */import org.schema.game.common.controller.elements.PowerManagerInterface;
/*  26:    */import org.schema.game.common.controller.elements.UsableDistributionControllableElementManager;
/*  27:    */import org.schema.game.common.data.element.ControlElementMap;
/*  28:    */import org.schema.game.common.data.element.ElementDocking;
/*  29:    */import org.schema.game.common.data.physics.PhysicsExt;
/*  30:    */import org.schema.game.common.data.world.Segment;
/*  31:    */import org.schema.game.network.objects.NetworkPlayer;
/*  32:    */import org.schema.schine.network.objects.NetworkEntity;
/*  33:    */import org.schema.schine.network.objects.NetworkObject;
/*  34:    */import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*  35:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  36:    */import org.schema.schine.network.objects.remote.RemoteVector3i;
/*  37:    */import x;
/*  38:    */import xe;
/*  39:    */import yE;
/*  40:    */
/*  41:    */public class WeaponElementManager extends UsableDistributionControllableElementManager implements BlockActivationListenerInterface, ManagerReloadInterface, NTDistributionReceiverInterface, NTReceiveInterface, NTSenderInterface
/*  42:    */{
/*  43: 43 */  private javax.vecmath.Vector3f shootingDirTemp = new javax.vecmath.Vector3f();
/*  44: 44 */  private q controlledFromOrig = new q();
/*  45: 45 */  private q controlledFrom = new q();
/*  46:    */  private final java.util.ArrayList weaponManagers;
/*  47:    */  
/*  48:    */  public WeaponElementManager(org.schema.game.common.controller.SegmentController paramSegmentController) {
/*  49: 49 */    super((short)6, (short)16, paramSegmentController);
/*  50: 50 */    this.weaponManagers = new java.util.ArrayList();
/*  51:    */  }
/*  52:    */  
/*  53:    */  private boolean consumePower(float paramFloat) {
/*  54: 54 */    if (getPowerManager().consumePowerInstantly(paramFloat)) {
/*  55: 55 */      return true;
/*  56:    */    }
/*  57: 57 */    if (getSegmentController().getDockingController().a() != null) {
/*  58:    */      org.schema.game.common.controller.SegmentController localSegmentController;
/*  59: 59 */      if ((((localSegmentController = getSegmentController().getDockingController().a().to.a().a()) instanceof ld)) && ((((ld)localSegmentController).a() instanceof PowerManagerInterface)))
/*  60:    */      {
/*  61: 61 */        return ((PowerManagerInterface)((ld)localSegmentController).a()).getPowerAddOn().consumePowerInstantly(paramFloat);
/*  62:    */      }
/*  63:    */    }
/*  64: 64 */    return false;
/*  65:    */  }
/*  66:    */  
/*  67:    */  public java.util.ArrayList getCollectionManagers() {
/*  68: 68 */    return this.weaponManagers;
/*  69:    */  }
/*  70:    */  
/*  73: 73 */  public ElementCollectionManager getNewCollectionManager(le paramle) { return new WeaponCollectionManager(paramle, getSegmentController()); }
/*  74:    */  
/*  75: 75 */  public static boolean debug = false;
/*  76:    */  
/*  79:    */  public void handle(lA paramlA)
/*  80:    */  {
/*  81: 81 */    if (!((Boolean)paramlA.a.a().activeControllerMask.get(1).get()).booleanValue()) {
/*  82: 82 */      if (debug) {
/*  83: 83 */        System.err.println("NOT ACTIVE");
/*  84:    */      }
/*  85: 85 */      return;
/*  86:    */    }
/*  87: 87 */    if (getCollectionManagers().isEmpty()) {
/*  88: 88 */      if (debug) {
/*  89: 89 */        System.err.println("NO WEAPONS");
/*  90:    */      }
/*  91:    */      
/*  92: 92 */      return;
/*  93:    */    }
/*  94: 94 */    if (!convertDeligateControls(paramlA, this.controlledFromOrig, this.controlledFrom)) {
/*  95: 95 */      if (debug) {
/*  96: 96 */        System.err.println("NO SLOT");
/*  97:    */      }
/*  98: 98 */      return;
/*  99:    */    }
/* 100:100 */    if ((getPowerManager().getPower() <= 0.5D) && (clientIsOwnShip()))
/* 101:    */    {
/* 102:102 */      ((ct)getState()).a().b("WARNING!\n \nNo Power Supply \nfor weapon systems");
/* 103:    */    }
/* 104:104 */    if (debug) {
/* 105:105 */      System.err.println("FIREING CONTROLLERS: " + getState() + ", " + this.weaponManagers.size() + " FROM: " + this.controlledFrom);
/* 106:    */    }
/* 107:107 */    for (int i = 0; i < getCollectionManagers().size(); i++) {
/* 108:    */      WeaponCollectionManager localWeaponCollectionManager;
/* 109:109 */      int j = (localWeaponCollectionManager = (WeaponCollectionManager)getCollectionManagers().get(i)).getControllerElement().a(this.controlledFrom);
/* 110:110 */      if (debug) {
/* 111:111 */        System.err.println("SELECTED:: " + j + " " + getState());
/* 112:    */      }
/* 113:113 */      if (j != 0)
/* 114:    */      {
/* 115:115 */        j = this.controlledFromOrig.equals(this.controlledFrom) | getControlElementMap().isControlling(this.controlledFromOrig, localWeaponCollectionManager.getControllerPos(), this.controllerId);
/* 116:116 */        if (debug) {
/* 117:117 */          System.err.println("Controlling " + j + " " + getState());
/* 118:    */        }
/* 119:    */        
/* 120:120 */        if (j != 0)
/* 121:    */        {
/* 122:122 */          if (this.controlledFromOrig.equals(kd.a)) {
/* 123:123 */            this.controlledFromOrig.b(paramlA.a.a().cockpit.getVector());
/* 124:    */          }
/* 125:125 */          if (debug) {
/* 126:126 */            System.err.println("Controlling " + j + " " + getState() + ": " + localWeaponCollectionManager.getCollection().size());
/* 127:    */          }
/* 128:128 */          for (j = 0; j < localWeaponCollectionManager.getCollection().size(); j++) {
/* 129:129 */            WeaponUnit localWeaponUnit = (WeaponUnit)localWeaponCollectionManager.getCollection().get(j);
/* 130:130 */            if (debug) {
/* 131:131 */              System.err.println("CAN SHOOT: " + localWeaponUnit.canShoot() + "; " + getPowerManager().getPower());
/* 132:    */            }
/* 133:133 */            if ((localWeaponUnit.canShoot()) && (consumePower(localWeaponUnit.getPowerConsumed()))) {
/* 134:134 */              if (debug) {
/* 135:135 */                System.err.println("2CAN SHOOT: " + localWeaponUnit.canShoot() + "; " + getPowerManager().getPower() + ": " + getState() + ": " + getSegmentController().getSectorId() + ";; " + getSegmentController().getPhysics());
/* 136:    */              }
/* 137:    */              
/* 138:138 */              Object localObject = localWeaponUnit.getOutput();
/* 139:    */              
/* 140:140 */              javax.vecmath.Vector3f localVector3f1 = new javax.vecmath.Vector3f(((q)localObject).a - 8, ((q)localObject).b - 8, ((q)localObject).c - 8);
/* 141:    */              
/* 144:144 */              if (debug) {
/* 145:145 */                System.err.println("WEAPON OUTPUT ON " + getState() + " -> " + localObject);
/* 146:    */              }
/* 147:147 */              if (getSegmentController().isOnServer()) {
/* 148:148 */                getSegmentController().getWorldTransform().transform(localVector3f1);
/* 149:    */              } else {
/* 150:150 */                getSegmentController().getWorldTransformClient().transform(localVector3f1);
/* 151:    */              }
/* 152:    */              
/* 156:156 */              (localObject = new q(this.controlledFromOrig)).c(kd.a);
/* 157:    */              
/* 159:159 */              localObject = getSegmentController().getAbsoluteElementWorldPosition((q)localObject, new javax.vecmath.Vector3f());
/* 160:160 */              javax.vecmath.Vector3f localVector3f2 = new javax.vecmath.Vector3f(paramlA.a.a());
/* 161:161 */              PhysicsExt localPhysicsExt = getSegmentController().getPhysics();
/* 162:162 */              localVector3f2.scale(localWeaponUnit.getDistance());
/* 163:163 */              localVector3f2.add((Tuple3f)localObject);
/* 164:    */              
/* 168:168 */              if ((localObject = localPhysicsExt.testRayCollisionPoint((javax.vecmath.Vector3f)localObject, localVector3f2, false, getSegmentController(), null, true, null, false)).hasHit()) {
/* 169:169 */                this.shootingDirTemp.sub(((CollisionWorld.ClosestRayResultCallback)localObject).hitPointWorld, localVector3f1);
/* 170:    */              } else {
/* 171:171 */                this.shootingDirTemp.sub(localVector3f2, localVector3f1);
/* 172:    */              }
/* 173:    */              
/* 175:175 */              this.shootingDirTemp.normalize();
/* 176:176 */              this.shootingDirTemp.scale(0.01F * localWeaponUnit.getSpeed());
/* 177:177 */              localWeaponUnit.updateLastShoot();
/* 178:    */              
/* 179:179 */              getParticleController().a(getSegmentController(), localVector3f1, this.shootingDirTemp, localWeaponUnit.getDamage(), localWeaponUnit.getDistance());
/* 180:    */              
/* 181:181 */              (
/* 182:182 */                localObject = new com.bulletphysics.linearmath.Transform()).setIdentity();
/* 183:183 */              ((com.bulletphysics.linearmath.Transform)localObject).origin.set(localVector3f1);
/* 184:    */              
/* 186:186 */              getManagerContainer().onAction();
/* 187:    */              
/* 189:189 */              if (!getSegmentController().isOnServer()) {
/* 190:190 */                xe.a("0022_spaceship user - laser gun single fire small", (com.bulletphysics.linearmath.Transform)localObject, 0.99F);
/* 191:    */              }
/* 192:192 */              notifyShooting(localWeaponUnit);
/* 193:    */            }
/* 194:    */          }
/* 195:    */          
/* 197:197 */          if ((localWeaponCollectionManager.getCollection().isEmpty()) && (clientIsOwnShip())) {
/* 198:198 */            ((ct)getState()).a().d("WARNING!\n \nNo Weapons connected \nto entry point");
/* 199:    */          }
/* 200:    */        }
/* 201:    */      }
/* 202:    */    }
/* 203:203 */    if ((getCollectionManagers().isEmpty()) && (clientIsOwnShip()))
/* 204:    */    {
/* 207:207 */      ((ct)getState()).a().d("WARNING!\n \nNo weapon controllers");
/* 208:    */    }
/* 209:    */  }
/* 210:    */  
/* 211:    */  public void notifyShooting(WeaponUnit paramWeaponUnit)
/* 212:    */  {
/* 213:213 */    notifyObservers(paramWeaponUnit, "s");
/* 214:    */  }
/* 215:    */  
/* 219:    */  public void updateFromNT(NetworkObject paramNetworkObject) {}
/* 220:    */  
/* 223:    */  public void updateToFullNT(NetworkObject paramNetworkObject)
/* 224:    */  {
/* 225:225 */    if (getSegmentController().isOnServer())
/* 226:    */    {
/* 236:236 */      for (paramNetworkObject = 0; paramNetworkObject < getCollectionManagers().size(); paramNetworkObject++) {
/* 237:237 */        ((WeaponCollectionManager)getCollectionManagers().get(paramNetworkObject)).sendDistribution();
/* 238:    */      }
/* 239:    */    }
/* 240:    */  }
/* 241:    */  
/* 244:    */  public boolean receiveDistribution(jE paramjE, NetworkEntity paramNetworkEntity)
/* 245:    */  {
/* 246:246 */    return receiveDistribution(paramjE);
/* 247:    */  }
/* 248:    */  
/* 250:    */  public void onActivate(le paramle, boolean paramBoolean)
/* 251:    */  {
/* 252:252 */    q localq = paramle.a(new q());
/* 253:253 */    Iterator localIterator; for (int i = 0; i < getCollectionManagers().size(); i++) {
/* 254:254 */      for (localIterator = ((WeaponCollectionManager)getCollectionManagers().get(i)).getCollection().iterator(); localIterator.hasNext();) { WeaponUnit localWeaponUnit;
/* 255:255 */        if ((localWeaponUnit = (WeaponUnit)localIterator.next()).contains(localq)) {
/* 256:256 */          localWeaponUnit.setMainPiece(paramle, paramBoolean);
/* 257:    */          
/* 258:258 */          return;
/* 259:    */        }
/* 260:    */      }
/* 261:    */    }
/* 262:    */  }
/* 263:    */  
/* 264:    */  public void drawReloads(yE paramyE, q paramq)
/* 265:    */  {
/* 266:    */    float f;
/* 267:267 */    for (int i = 0; i < getCollectionManagers().size(); f++) {
/* 268:268 */      if (((WeaponCollectionManager)getCollectionManagers().get(i)).getControllerPos().equals(paramq))
/* 269:    */      {
/* 270:270 */        for (WeaponUnit localWeaponUnit : ((WeaponCollectionManager)getCollectionManagers().get(i)).getCollection())
/* 271:    */        {
/* 276:276 */          int j = (int)(System.currentTimeMillis() - localWeaponUnit.getLastShoot());
/* 277:277 */          f = Math.min(2.0F, j / localWeaponUnit.getRelaodTime());
/* 278:    */          
/* 279:279 */          j = 9;
/* 280:280 */          if (f <= 1.0F) {
/* 281:281 */            j = (int)FastMath.a(9.0F + f * 8.0F, 9.0F, 17.0F);
/* 282:    */          }
/* 283:    */          
/* 284:284 */          paramyE.a_(j);
/* 285:285 */          paramyE.b();
/* 286:    */        }
/* 287:287 */        return;
/* 288:    */      }
/* 289:    */    }
/* 290:    */  }
/* 291:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.weapon.WeaponElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */