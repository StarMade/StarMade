/*   1:    */package org.schema.game.common.controller.elements.missile.heatseeking;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*   4:    */import com.bulletphysics.linearmath.Transform;
/*   5:    */import ct;
/*   6:    */import jE;
/*   7:    */import java.io.PrintStream;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.List;
/*  10:    */import javax.vecmath.Tuple3f;
/*  11:    */import kd;
/*  12:    */import lA;
/*  13:    */import lE;
/*  14:    */import le;
/*  15:    */import org.schema.game.common.controller.SegmentController;
/*  16:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  17:    */import org.schema.game.common.controller.elements.ManagerContainer;
/*  18:    */import org.schema.game.common.controller.elements.missile.MissileController;
/*  19:    */import org.schema.game.common.controller.elements.missile.MissileElementManager;
/*  20:    */import org.schema.game.common.data.element.ControlElementMap;
/*  21:    */import org.schema.game.common.data.physics.CubeRayCastResult;
/*  22:    */import org.schema.game.common.data.physics.PhysicsExt;
/*  23:    */import org.schema.game.common.data.world.Segment;
/*  24:    */import org.schema.game.network.objects.NetworkPlayer;
/*  25:    */import org.schema.game.server.controller.GameServerController;
/*  26:    */import org.schema.schine.network.StateInterface;
/*  27:    */import org.schema.schine.network.objects.NetworkEntity;
/*  28:    */import org.schema.schine.network.objects.NetworkObject;
/*  29:    */import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*  30:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  31:    */import x;
/*  32:    */import xe;
/*  33:    */
/*  34:    */public class HeatMissileElementManager extends MissileElementManager
/*  35:    */{
/*  36:    */  private Segment cachedLastWeaponFireHitSegment;
/*  37: 37 */  private javax.vecmath.Vector3f shootingDirTemp = new javax.vecmath.Vector3f();
/*  38: 38 */  private q controlledFromOrig = new q();
/*  39: 39 */  private q controlledFrom = new q();
/*  40:    */  private java.util.ArrayList missileManagers;
/*  41:    */  
/*  42:    */  public HeatMissileElementManager(SegmentController paramSegmentController) {
/*  43: 43 */    super((short)46, (short)40, paramSegmentController);
/*  44: 44 */    this.missileManagers = new java.util.ArrayList();
/*  45:    */  }
/*  46:    */  
/*  47:    */  public java.util.ArrayList getCollectionManagers()
/*  48:    */  {
/*  49: 49 */    return this.missileManagers;
/*  50:    */  }
/*  51:    */  
/*  52: 52 */  public MissileController getMissileController() { return ((GameServerController)getSegmentController().getState().getController()).a(); }
/*  53:    */  
/*  57:    */  public ElementCollectionManager getNewCollectionManager(le paramle)
/*  58:    */  {
/*  59: 59 */    return new HeatMissileCollectionManager(paramle, getSegmentController());
/*  60:    */  }
/*  61:    */  
/*  62:    */  public void handle(lA paramlA)
/*  63:    */  {
/*  64: 64 */    if (!((Boolean)paramlA.a.a().activeControllerMask.get(1).get()).booleanValue()) {
/*  65: 65 */      return;
/*  66:    */    }
/*  67: 67 */    if (getCollectionManagers().isEmpty())
/*  68:    */    {
/*  69: 69 */      return;
/*  70:    */    }
/*  71: 71 */    if (!convertDeligateControls(paramlA, this.controlledFromOrig, this.controlledFrom)) {
/*  72: 72 */      return;
/*  73:    */    }
/*  74:    */    
/*  75: 75 */    for (int i = 0; i < getCollectionManagers().size(); 
/*  76:    */        
/*  77: 77 */        i++)
/*  78:    */    {
/*  79:    */      HeatMissileCollectionManager localHeatMissileCollectionManager;
/*  80: 80 */      int j = 0; if ((localHeatMissileCollectionManager = (HeatMissileCollectionManager)getCollectionManagers().get(i)).equalsControllerPos(this.controlledFrom))
/*  81:    */      {
/*  84: 84 */        if ((this.controlledFromOrig.equals(this.controlledFrom) | getControlElementMap().isControlling(this.controlledFromOrig, localHeatMissileCollectionManager.getControllerPos(), this.controllerId)))
/*  85:    */        {
/*  86: 86 */          for (j = 0; j < localHeatMissileCollectionManager.getCollection().size(); j++) {
/*  87:    */            org.schema.game.common.controller.elements.missile.MissileUnit localMissileUnit;
/*  88: 88 */            if ((localMissileUnit = (org.schema.game.common.controller.elements.missile.MissileUnit)localHeatMissileCollectionManager.getCollection().get(j)).canShoot()) {
/*  89: 89 */              Object localObject1 = localMissileUnit.getOutput();
/*  90:    */              
/*  91: 91 */              localObject1 = new javax.vecmath.Vector3f(((q)localObject1).a - 8, ((q)localObject1).b - 8, ((q)localObject1).c - 8);
/*  92:    */              
/*  97: 97 */              getWorldTransform().transform((javax.vecmath.Vector3f)localObject1);
/*  98:    */              
/*  99: 99 */              (
/* 100:100 */                localObject2 = new q(this.controlledFromOrig)).c(kd.a);
/* 101:101 */              Object localObject2 = getSegmentController().getAbsoluteElementWorldPosition((q)localObject2, new javax.vecmath.Vector3f());
/* 102:102 */              javax.vecmath.Vector3f localVector3f = new javax.vecmath.Vector3f(paramlA.a.a());
/* 103:103 */              PhysicsExt localPhysicsExt = getSegmentController().getPhysics();
/* 104:104 */              localVector3f.scale(localMissileUnit.getDistance());
/* 105:105 */              localVector3f.add((Tuple3f)localObject2);
/* 106:    */              
/* 110:110 */              if ((localObject2 = localPhysicsExt.testRayCollisionPoint((javax.vecmath.Vector3f)localObject2, localVector3f, false, getSegmentController(), null, true, this.cachedLastWeaponFireHitSegment, false)).hasHit()) {
/* 111:111 */                if ((localObject2 instanceof CubeRayCastResult)) {
/* 112:112 */                  this.cachedLastWeaponFireHitSegment = ((CubeRayCastResult)localObject2).getSegment();
/* 113:    */                }
/* 114:    */                
/* 115:115 */                this.shootingDirTemp.sub(((CollisionWorld.ClosestRayResultCallback)localObject2).hitPointWorld, (Tuple3f)localObject1);
/* 116:    */              } else {
/* 117:117 */                this.cachedLastWeaponFireHitSegment = null;
/* 118:118 */                this.shootingDirTemp.set(paramlA.a.a());
/* 119:    */              }
/* 120:    */              
/* 124:124 */              this.shootingDirTemp.normalize();
/* 125:125 */              this.shootingDirTemp.scale(localMissileUnit.getSpeed());
/* 126:126 */              localMissileUnit.updateLastShoot();
/* 127:    */              
/* 128:128 */              if (getSegmentController().isOnServer()) {
/* 129:129 */                System.err.println("[SERVER] spawing heat missile " + localObject1);
/* 130:130 */                (
/* 131:131 */                  localObject2 = new Transform()).setIdentity();
/* 132:132 */                ((Transform)localObject2).origin.set((Tuple3f)localObject1);
/* 133:133 */                getMissileController().addHeatMissile(getSegmentController(), (Transform)localObject2, new javax.vecmath.Vector3f(this.shootingDirTemp), localMissileUnit.getSpeed(), localMissileUnit.getBlastRadius(), localMissileUnit.getDamage(), localMissileUnit.getDistance());
/* 134:    */              }
/* 135:    */              
/* 137:137 */              (localObject2 = new Transform()).setIdentity();
/* 138:138 */              ((Transform)localObject2).origin.set((Tuple3f)localObject1);
/* 139:    */              
/* 140:140 */              if (!getSegmentController().isOnServer()) {
/* 141:141 */                xe.a("0022_spaceship user - missile fire 1", (Transform)localObject2, 5.0F);
/* 142:142 */                notifyObservers(localMissileUnit, "s");
/* 143:    */              }
/* 144:144 */              getManagerContainer().onAction();
/* 145:    */            }
/* 146:    */          }
/* 147:    */          
/* 149:149 */          if ((localHeatMissileCollectionManager.getCollection().isEmpty()) && (clientIsOwnShip())) {
/* 150:150 */            ((ct)getState()).a().d("WARNING!\n \nNo Weapons connected \nto entry point");
/* 151:    */          }
/* 152:    */        }
/* 153:    */      }
/* 154:    */    }
/* 155:155 */    if ((getCollectionManagers().isEmpty()) && (clientIsOwnShip()))
/* 156:    */    {
/* 159:159 */      ((ct)getState()).a().d("WARNING!\n \nNo weapon controllers");
/* 160:    */    }
/* 161:    */  }
/* 162:    */  
/* 163:    */  public void notifyShooting(org.schema.game.common.controller.elements.missile.MissileUnit paramMissileUnit) {
/* 164:164 */    notifyObservers(paramMissileUnit, "s");
/* 165:    */  }
/* 166:    */  
/* 168:    */  public void updateFromNT(NetworkObject paramNetworkObject) {}
/* 169:    */  
/* 170:    */  public void updateToFullNT(NetworkObject paramNetworkObject)
/* 171:    */  {
/* 172:172 */    if (getSegmentController().isOnServer())
/* 173:    */    {
/* 183:183 */      for (paramNetworkObject = 0; paramNetworkObject < getCollectionManagers().size(); paramNetworkObject++) {
/* 184:184 */        ((HeatMissileCollectionManager)getCollectionManagers().get(paramNetworkObject)).sendDistribution();
/* 185:    */      }
/* 186:    */    }
/* 187:    */  }
/* 188:    */  
/* 191:    */  public boolean receiveDistribution(jE paramjE, NetworkEntity paramNetworkEntity)
/* 192:    */  {
/* 193:193 */    return receiveDistribution(paramjE);
/* 194:    */  }
/* 195:    */  
/* 197:    */  public void onActivate(le paramle, boolean paramBoolean)
/* 198:    */  {
/* 199:199 */    q localq = paramle.a(new q());
/* 200:200 */    Iterator localIterator; for (int i = 0; i < getCollectionManagers().size(); i++) {
/* 201:201 */      for (localIterator = ((HeatMissileCollectionManager)getCollectionManagers().get(i)).getCollection().iterator(); localIterator.hasNext();) { org.schema.game.common.controller.elements.missile.MissileUnit localMissileUnit;
/* 202:202 */        if ((localMissileUnit = (org.schema.game.common.controller.elements.missile.MissileUnit)localIterator.next()).contains(localq)) {
/* 203:203 */          localMissileUnit.setMainPiece(paramle, paramBoolean);
/* 204:    */          
/* 205:205 */          return;
/* 206:    */        }
/* 207:    */      }
/* 208:    */    }
/* 209:    */  }
/* 210:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.heatseeking.HeatMissileElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */