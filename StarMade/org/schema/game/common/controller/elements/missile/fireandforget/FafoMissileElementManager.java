/*   1:    */package org.schema.game.common.controller.elements.missile.fireandforget;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*   4:    */import com.bulletphysics.linearmath.Transform;
/*   5:    */import ct;
/*   6:    */import jE;
/*   7:    */import java.util.ArrayList;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.List;
/*  10:    */import javax.vecmath.Tuple3f;
/*  11:    */import kd;
/*  12:    */import lA;
/*  13:    */import lE;
/*  14:    */import le;
/*  15:    */import mF;
/*  16:    */import org.schema.game.common.controller.SegmentController;
/*  17:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  18:    */import org.schema.game.common.controller.elements.ManagerContainer;
/*  19:    */import org.schema.game.common.controller.elements.missile.MissileController;
/*  20:    */import org.schema.game.common.controller.elements.missile.MissileElementManager;
/*  21:    */import org.schema.game.common.data.element.ControlElementMap;
/*  22:    */import org.schema.game.common.data.physics.CubeRayCastResult;
/*  23:    */import org.schema.game.common.data.physics.PhysicsExt;
/*  24:    */import org.schema.game.common.data.world.Segment;
/*  25:    */import org.schema.game.network.objects.NetworkPlayer;
/*  26:    */import org.schema.game.server.controller.GameServerController;
/*  27:    */import org.schema.schine.network.StateInterface;
/*  28:    */import org.schema.schine.network.objects.NetworkEntity;
/*  29:    */import org.schema.schine.network.objects.NetworkObject;
/*  30:    */import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*  31:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  32:    */import x;
/*  33:    */import xe;
/*  34:    */
/*  35:    */public class FafoMissileElementManager extends MissileElementManager
/*  36:    */{
/*  37:    */  private Segment cachedLastWeaponFireHitSegment;
/*  38: 38 */  private javax.vecmath.Vector3f shootingDirTemp = new javax.vecmath.Vector3f();
/*  39: 39 */  private q controlledFromOrig = new q();
/*  40: 40 */  private q controlledFrom = new q();
/*  41:    */  private ArrayList missileManagers;
/*  42:    */  
/*  43:    */  public FafoMissileElementManager(SegmentController paramSegmentController) {
/*  44: 44 */    super((short)54, (short)48, paramSegmentController);
/*  45: 45 */    this.missileManagers = new ArrayList();
/*  46:    */  }
/*  47:    */  
/*  48:    */  public ArrayList getCollectionManagers()
/*  49:    */  {
/*  50: 50 */    return this.missileManagers;
/*  51:    */  }
/*  52:    */  
/*  53: 53 */  private MissileController getMissileController() { return ((GameServerController)getSegmentController().getState().getController()).a(); }
/*  54:    */  
/*  58:    */  public ElementCollectionManager getNewCollectionManager(le paramle)
/*  59:    */  {
/*  60: 60 */    return new FafoMissileCollectionManager(paramle, getSegmentController());
/*  61:    */  }
/*  62:    */  
/*  63:    */  public void handle(lA paramlA)
/*  64:    */  {
/*  65: 65 */    if (!((Boolean)paramlA.a.a().activeControllerMask.get(1).get()).booleanValue()) {
/*  66: 66 */      return;
/*  67:    */    }
/*  68: 68 */    mF localmF = paramlA.a.a();
/*  69:    */    
/*  70: 70 */    if (getCollectionManagers().isEmpty())
/*  71:    */    {
/*  72: 72 */      return;
/*  73:    */    }
/*  74: 74 */    if (!convertDeligateControls(paramlA, this.controlledFromOrig, this.controlledFrom)) {
/*  75: 75 */      return;
/*  76:    */    }
/*  77:    */    
/*  78: 78 */    for (int i = 0; i < getCollectionManagers().size(); 
/*  79:    */        
/*  80: 80 */        i++)
/*  81:    */    {
/*  82:    */      FafoMissileCollectionManager localFafoMissileCollectionManager;
/*  83: 83 */      int j = 0; if ((localFafoMissileCollectionManager = (FafoMissileCollectionManager)getCollectionManagers().get(i)).equalsControllerPos(this.controlledFrom))
/*  84:    */      {
/*  87: 87 */        if ((this.controlledFromOrig.equals(this.controlledFrom) | getControlElementMap().isControlling(this.controlledFromOrig, localFafoMissileCollectionManager.getControllerPos(), this.controllerId)))
/*  88:    */        {
/*  89: 89 */          for (j = 0; j < localFafoMissileCollectionManager.getCollection().size(); j++) {
/*  90:    */            org.schema.game.common.controller.elements.missile.MissileUnit localMissileUnit;
/*  91: 91 */            if ((localMissileUnit = (org.schema.game.common.controller.elements.missile.MissileUnit)localFafoMissileCollectionManager.getCollection().get(j)).canShoot()) {
/*  92: 92 */              Object localObject1 = localMissileUnit.getOutput();
/*  93:    */              
/*  94: 94 */              localObject1 = new javax.vecmath.Vector3f(((q)localObject1).a - 8, ((q)localObject1).b - 8, ((q)localObject1).c - 8);
/*  95:    */              
/* 100:100 */              getWorldTransform().transform((javax.vecmath.Vector3f)localObject1);
/* 101:    */              
/* 102:102 */              (
/* 103:103 */                localObject2 = new q(this.controlledFromOrig)).c(kd.a);
/* 104:104 */              Object localObject2 = getSegmentController().getAbsoluteElementWorldPosition((q)localObject2, new javax.vecmath.Vector3f());
/* 105:105 */              javax.vecmath.Vector3f localVector3f = new javax.vecmath.Vector3f(paramlA.a.a());
/* 106:106 */              PhysicsExt localPhysicsExt = getSegmentController().getPhysics();
/* 107:107 */              localVector3f.scale(localMissileUnit.getDistance());
/* 108:108 */              localVector3f.add((Tuple3f)localObject2);
/* 109:    */              
/* 113:113 */              if ((localObject2 = localPhysicsExt.testRayCollisionPoint((javax.vecmath.Vector3f)localObject2, localVector3f, false, getSegmentController(), null, true, this.cachedLastWeaponFireHitSegment, false)).hasHit()) {
/* 114:114 */                if ((localObject2 instanceof CubeRayCastResult)) {
/* 115:115 */                  this.cachedLastWeaponFireHitSegment = ((CubeRayCastResult)localObject2).getSegment();
/* 116:    */                }
/* 117:    */                
/* 118:118 */                this.shootingDirTemp.sub(((CollisionWorld.ClosestRayResultCallback)localObject2).hitPointWorld, (Tuple3f)localObject1);
/* 119:    */              } else {
/* 120:120 */                this.cachedLastWeaponFireHitSegment = null;
/* 121:121 */                this.shootingDirTemp.set(paramlA.a.a());
/* 122:    */              }
/* 123:    */              
/* 127:127 */              this.shootingDirTemp.normalize();
/* 128:128 */              localMissileUnit.updateLastShoot();
/* 129:    */              
/* 130:130 */              if (getSegmentController().isOnServer())
/* 131:    */              {
/* 132:132 */                (localObject2 = new Transform()).setIdentity();
/* 133:133 */                ((Transform)localObject2).origin.set((Tuple3f)localObject1);
/* 134:134 */                getMissileController().addFafoMissile(getSegmentController(), (Transform)localObject2, new javax.vecmath.Vector3f(this.shootingDirTemp), localMissileUnit.getSpeed(), localMissileUnit.getBlastRadius(), localMissileUnit.getDamage(), localMissileUnit.getDistance(), localmF);
/* 135:    */              }
/* 136:    */              
/* 138:138 */              (localObject2 = new Transform()).setIdentity();
/* 139:139 */              ((Transform)localObject2).origin.set((Tuple3f)localObject1);
/* 140:    */              
/* 141:141 */              if (!getSegmentController().isOnServer()) {
/* 142:142 */                xe.a("0022_spaceship user - missile fire 1", (Transform)localObject2, 5.0F);
/* 143:143 */                notifyObservers(localMissileUnit, "s");
/* 144:    */              }
/* 145:145 */              getManagerContainer().onAction();
/* 146:    */            }
/* 147:    */          }
/* 148:    */          
/* 150:150 */          if ((localFafoMissileCollectionManager.getCollection().isEmpty()) && (clientIsOwnShip())) {
/* 151:151 */            ((ct)getState()).a().d("WARNING!\n \nNo Weapons connected \nto entry point");
/* 152:    */          }
/* 153:    */        }
/* 154:    */      }
/* 155:    */    }
/* 156:156 */    if ((getCollectionManagers().isEmpty()) && (clientIsOwnShip()))
/* 157:    */    {
/* 160:160 */      ((ct)getState()).a().d("WARNING!\n \nNo weapon controllers");
/* 161:    */    }
/* 162:    */  }
/* 163:    */  
/* 165:    */  public void updateFromNT(NetworkObject paramNetworkObject) {}
/* 166:    */  
/* 168:    */  public void updateToFullNT(NetworkObject paramNetworkObject)
/* 169:    */  {
/* 170:170 */    if (getSegmentController().isOnServer())
/* 171:    */    {
/* 181:181 */      for (paramNetworkObject = 0; paramNetworkObject < getCollectionManagers().size(); paramNetworkObject++) {
/* 182:182 */        ((FafoMissileCollectionManager)getCollectionManagers().get(paramNetworkObject)).sendDistribution();
/* 183:    */      }
/* 184:    */    }
/* 185:    */  }
/* 186:    */  
/* 189:    */  public boolean receiveDistribution(jE paramjE, NetworkEntity paramNetworkEntity)
/* 190:    */  {
/* 191:191 */    return receiveDistribution(paramjE);
/* 192:    */  }
/* 193:    */  
/* 194:    */  public void onActivate(le paramle, boolean paramBoolean)
/* 195:    */  {
/* 196:196 */    q localq = paramle.a(new q());
/* 197:197 */    Iterator localIterator; for (int i = 0; i < getCollectionManagers().size(); i++) {
/* 198:198 */      for (localIterator = ((FafoMissileCollectionManager)getCollectionManagers().get(i)).getCollection().iterator(); localIterator.hasNext();) { org.schema.game.common.controller.elements.missile.MissileUnit localMissileUnit;
/* 199:199 */        if ((localMissileUnit = (org.schema.game.common.controller.elements.missile.MissileUnit)localIterator.next()).contains(localq)) {
/* 200:200 */          localMissileUnit.setMainPiece(paramle, paramBoolean);
/* 201:    */          
/* 202:202 */          return;
/* 203:    */        }
/* 204:    */      }
/* 205:    */    }
/* 206:    */  }
/* 207:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.fireandforget.FafoMissileElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */