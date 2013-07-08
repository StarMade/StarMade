/*   1:    */package org.schema.game.common.controller.elements;
/*   2:    */
/*   3:    */import com.bulletphysics.linearmath.Transform;
/*   4:    */import ct;
/*   5:    */import dj;
/*   6:    */import eH;
/*   7:    */import eI;
/*   8:    */import eJ;
/*   9:    */import es;
/*  10:    */import ij;
/*  11:    */import java.util.ArrayList;
/*  12:    */import java.util.Collection;
/*  13:    */import javax.vecmath.Vector3f;
/*  14:    */import mh;
/*  15:    */import ml;
/*  16:    */import org.schema.game.common.controller.SegmentController;
/*  17:    */import org.schema.game.common.controller.elements.dockingBlock.DockingBlockManagerInterface;
/*  18:    */import org.schema.game.common.controller.elements.dockingBlock.fixed.FixedDockingBlockElementManager;
/*  19:    */import org.schema.game.common.controller.elements.dockingBlock.turret.TurretDockingBlockElementManager;
/*  20:    */import org.schema.game.common.controller.elements.door.DoorCollectionManager;
/*  21:    */import org.schema.game.common.controller.elements.lift.LiftCollectionManager;
/*  22:    */import org.schema.game.common.controller.elements.missile.dumb.DumbMissileElementManager;
/*  23:    */import org.schema.game.common.controller.elements.missile.fireandforget.FafoMissileElementManager;
/*  24:    */import org.schema.game.common.controller.elements.missile.heatseeking.HeatMissileElementManager;
/*  25:    */import org.schema.game.common.controller.elements.power.PowerCollectionManager;
/*  26:    */import org.schema.game.common.controller.elements.powercap.PowerCapacityCollectionManager;
/*  27:    */import org.schema.game.common.controller.elements.repair.RepairElementManager;
/*  28:    */import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*  29:    */import org.schema.game.common.controller.elements.weapon.WeaponElementManager;
/*  30:    */import org.schema.game.network.objects.NetworkDoorInterface;
/*  31:    */import org.schema.game.network.objects.NetworkLiftInterface;
/*  32:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  33:    */import org.schema.schine.network.objects.remote.RemoteVector4i;
/*  34:    */import q;
/*  35:    */import s;
/*  36:    */import xq;
/*  37:    */
/*  62:    */public class StationaryManagerContainer
/*  63:    */  extends ManagerContainer
/*  64:    */  implements mh, DoorContainerInterface, FactoryAddOnInterface, LiftContainerInterface, ShieldContainerInterface, WeaponManagerInterface, DockingBlockManagerInterface
/*  65:    */{
/*  66:    */  private ManagerModuleSingle shields;
/*  67:    */  private ManagerModuleSingle power;
/*  68:    */  private ManagerModuleSingle lift;
/*  69:    */  private ManagerModuleSingle door;
/*  70:    */  private ManagerModuleCollection repair;
/*  71:    */  private ManagerModuleSingle powerCapacity;
/*  72:    */  private ManagerModuleCollection weapon;
/*  73:    */  private ManagerModuleCollection dumbMissile;
/*  74:    */  private ManagerModuleCollection heatMissile;
/*  75:    */  private ManagerModuleCollection fafoMissile;
/*  76:    */  private ManagerModuleCollection turretDockingBlock;
/*  77:    */  private ManagerModuleCollection fixedDockingBlock;
/*  78:    */  private ManagerContainerFactoryAddOn factory;
/*  79: 79 */  private final ArrayList initialPointDists = new ArrayList();
/*  80:    */  
/*  81:    */  private final PowerAddOn powerAddOn;
/*  82:    */  
/*  83:    */  public StationaryManagerContainer(SegmentController paramSegmentController)
/*  84:    */  {
/*  85: 85 */    super(paramSegmentController);
/*  86: 86 */    this.powerAddOn = new PowerAddOn(this, paramSegmentController);
/*  87:    */  }
/*  88:    */  
/*  91:    */  public Collection getDockingBlock()
/*  92:    */  {
/*  93: 93 */    return this.dockingModules;
/*  94:    */  }
/*  95:    */  
/*  96:    */  private NetworkDoorInterface getDoorInterface()
/*  97:    */  {
/*  98: 98 */    return (NetworkDoorInterface)getSegmentController().getNetworkObject();
/*  99:    */  }
/* 100:    */  
/* 104:    */  public DoorCollectionManager getDoorManager()
/* 105:    */  {
/* 106:106 */    return (DoorCollectionManager)this.door.getCollectionManager();
/* 107:    */  }
/* 108:    */  
/* 109:    */  private NetworkLiftInterface getLiftInterface() {
/* 110:110 */    return (NetworkLiftInterface)getSegmentController().getNetworkObject();
/* 111:    */  }
/* 112:    */  
/* 115:    */  public LiftCollectionManager getLiftManager()
/* 116:    */  {
/* 117:117 */    return (LiftCollectionManager)this.lift.getCollectionManager();
/* 118:    */  }
/* 119:    */  
/* 120:    */  public PowerCollectionManager getPowerManager() {
/* 121:121 */    return (PowerCollectionManager)this.power.getCollectionManager();
/* 122:    */  }
/* 123:    */  
/* 124:    */  public ManagerModuleCollection getRepair() {
/* 125:125 */    return this.repair;
/* 126:    */  }
/* 127:    */  
/* 130:    */  public ShieldCollectionManager getShieldManager()
/* 131:    */  {
/* 132:132 */    return (ShieldCollectionManager)this.shields.getCollectionManager();
/* 133:    */  }
/* 134:    */  
/* 135:    */  public void handleClientRemoteDoor(q paramq) {
/* 136:136 */    getDoorInterface().getDoorActivate().forceClientUpdates();
/* 137:    */    
/* 138:138 */    (paramq = new s(paramq)).d = -1;
/* 139:139 */    getDoorInterface().getDoorActivate().add(new RemoteVector4i(paramq, getSegmentController().getNetworkObject()));
/* 140:    */  }
/* 141:    */  
/* 142:    */  public void handleClientRemoteLift(q paramq) {
/* 143:143 */    getLiftInterface().getLiftActivate().forceClientUpdates();
/* 144:    */    
/* 145:145 */    (paramq = new s(paramq)).d = 1;
/* 146:146 */    getLiftInterface().getLiftActivate().add(new RemoteVector4i(paramq, getSegmentController().getNetworkObject()));
/* 147:    */  }
/* 148:    */  
/* 150:    */  public void initialize()
/* 151:    */  {
/* 152:152 */    getModules().add(this.lift = new ManagerModuleSingle(new VoidElementManager(new LiftCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)113));
/* 153:    */    
/* 154:154 */    getModules().add(this.door = new ManagerModuleSingle(new VoidElementManager(new DoorCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)122));
/* 155:    */    
/* 156:156 */    getModules().add(this.shields = new ManagerModuleSingle(new VoidElementManager(new ShieldCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)3));
/* 157:    */    
/* 158:158 */    getModules().add(this.power = new ManagerModuleSingle(new VoidElementManager(new PowerCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)2));
/* 159:    */    
/* 160:160 */    getModules().add(this.powerCapacity = new ManagerModuleSingle(new VoidElementManager(new PowerCapacityCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)331));
/* 161:    */    
/* 162:162 */    getModules().add(this.turretDockingBlock = new ManagerModuleCollection(new TurretDockingBlockElementManager(getSegmentController()), (short)7, (short)88));
/* 163:    */    
/* 164:164 */    getModules().add(this.fixedDockingBlock = new ManagerModuleCollection(new FixedDockingBlockElementManager(getSegmentController()), (short)289, (short)290));
/* 165:    */    
/* 166:166 */    getModules().add(this.repair = new ManagerModuleCollection(new RepairElementManager(getSegmentController()), (short)39, (short)30));
/* 167:    */    
/* 168:168 */    getModules().add(this.weapon = new ManagerModuleCollection(new WeaponElementManager(getSegmentController()), (short)6, (short)16));
/* 169:    */    
/* 170:170 */    getModules().add(this.dumbMissile = new ManagerModuleCollection(new DumbMissileElementManager(getSegmentController()), (short)38, (short)32));
/* 171:    */    
/* 172:172 */    getModules().add(this.fafoMissile = new ManagerModuleCollection(new FafoMissileElementManager(getSegmentController()), (short)54, (short)48));
/* 173:    */    
/* 174:174 */    getModules().add(this.heatMissile = new ManagerModuleCollection(new HeatMissileElementManager(getSegmentController()), (short)46, (short)40));
/* 175:    */    
/* 176:176 */    this.factory = new ManagerContainerFactoryAddOn();
/* 177:    */    
/* 178:178 */    this.factory.initialize(getModules(), getSegmentController());
/* 179:    */  }
/* 180:    */  
/* 186:    */  public void updateLocal(xq paramxq)
/* 187:    */  {
/* 188:188 */    super.updateLocal(paramxq);
/* 189:189 */    this.factory.update(paramxq, getSegmentController().isOnServer());
/* 190:190 */    this.powerAddOn.update(paramxq);
/* 191:    */  }
/* 192:    */  
/* 194:    */  public void onAction() {}
/* 195:    */  
/* 197:    */  public ml getInventoryNetworkObject()
/* 198:    */  {
/* 199:199 */    return (ml)getSegmentController().getNetworkObject();
/* 200:    */  }
/* 201:    */  
/* 203:    */  public ManagerContainerFactoryAddOn getFactory()
/* 204:    */  {
/* 205:205 */    return this.factory;
/* 206:    */  }
/* 207:    */  
/* 208:    */  public double handleShieldHit(Vector3f paramVector3f, SegmentController paramSegmentController, float paramFloat)
/* 209:    */  {
/* 210:210 */    double d1 = getShieldManager().getShields();
/* 211:    */    
/* 212:212 */    onHit((int)paramFloat);
/* 213:    */    double d2;
/* 214:214 */    if (getShieldManager().getShields() <= 0.0D)
/* 215:    */    {
/* 217:217 */      if (paramFloat < d1) {
/* 218:218 */        d2 = d1;
/* 219:    */      } else {
/* 220:220 */        d2 = paramFloat;
/* 221:    */      }
/* 222:    */    } else {
/* 223:223 */      d2 = 0.0D;
/* 224:    */    }
/* 225:    */    
/* 228:228 */    if (!getSegmentController().isOnServer())
/* 229:    */    {
/* 230:230 */      paramSegmentController = (ct)getState();
/* 231:    */      Transform localTransform;
/* 232:232 */      (localTransform = new Transform()).setIdentity();
/* 233:233 */      localTransform.origin.set(paramVector3f);
/* 234:    */      
/* 236:236 */      ij.a.add(new eH(localTransform, String.valueOf((int)paramFloat), 1.0F, 0.0F, 0.0F));
/* 237:    */      
/* 238:238 */      paramSegmentController.a().a().a(paramVector3f, 4.0F);
/* 239:    */      
/* 241:241 */      if ((paramSegmentController = paramSegmentController.a().a().a(getSegmentController())) != null) {
/* 242:242 */        paramSegmentController.a(paramVector3f);
/* 243:    */      }
/* 244:    */    }
/* 245:    */    
/* 246:246 */    return d2;
/* 247:    */  }
/* 248:    */  
/* 250:    */  public ManagerModuleSingle getShields()
/* 251:    */  {
/* 252:252 */    return this.shields;
/* 253:    */  }
/* 254:    */  
/* 256:    */  public ManagerModuleSingle getPower()
/* 257:    */  {
/* 258:258 */    return this.power;
/* 259:    */  }
/* 260:    */  
/* 262:    */  public ManagerModuleSingle getLift()
/* 263:    */  {
/* 264:264 */    return this.lift;
/* 265:    */  }
/* 266:    */  
/* 268:    */  public ManagerModuleSingle getDoor()
/* 269:    */  {
/* 270:270 */    return this.door;
/* 271:    */  }
/* 272:    */  
/* 274:    */  public ManagerModuleCollection getWeapon()
/* 275:    */  {
/* 276:276 */    return this.weapon;
/* 277:    */  }
/* 278:    */  
/* 280:    */  public ManagerModuleCollection getDumbMissile()
/* 281:    */  {
/* 282:282 */    return this.dumbMissile;
/* 283:    */  }
/* 284:    */  
/* 286:    */  public ManagerModuleCollection getHeatMissile()
/* 287:    */  {
/* 288:288 */    return this.heatMissile;
/* 289:    */  }
/* 290:    */  
/* 292:    */  public ManagerModuleCollection getFafoMissile()
/* 293:    */  {
/* 294:294 */    return this.fafoMissile;
/* 295:    */  }
/* 296:    */  
/* 298:    */  public ManagerModuleCollection getTurretDockingBlock()
/* 299:    */  {
/* 300:300 */    return this.turretDockingBlock;
/* 301:    */  }
/* 302:    */  
/* 304:    */  public ManagerModuleCollection getFixedDockingBlock()
/* 305:    */  {
/* 306:306 */    return this.fixedDockingBlock;
/* 307:    */  }
/* 308:    */  
/* 310:    */  public ArrayList getInitialPointDists()
/* 311:    */  {
/* 312:312 */    return this.initialPointDists;
/* 313:    */  }
/* 314:    */  
/* 317:    */  public PowerAddOn getPowerAddOn()
/* 318:    */  {
/* 319:319 */    return this.powerAddOn;
/* 320:    */  }
/* 321:    */  
/* 322:    */  public PowerCapacityCollectionManager getPowerCapacityManager() {
/* 323:323 */    return (PowerCapacityCollectionManager)this.powerCapacity.getCollectionManager();
/* 324:    */  }
/* 325:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.StationaryManagerContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */