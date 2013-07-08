/*   1:    */package org.schema.game.common.controller.elements.missile.dumb;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*   4:    */import com.bulletphysics.linearmath.Transform;
/*   5:    */import ct;
/*   6:    */import jE;
/*   7:    */import java.util.ArrayList;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.List;
/*  10:    */import javax.vecmath.Tuple3f;
/*  11:    */import javax.vecmath.Vector3f;
/*  12:    */import kd;
/*  13:    */import lA;
/*  14:    */import lE;
/*  15:    */import le;
/*  16:    */import org.schema.game.common.controller.SegmentController;
/*  17:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  18:    */import org.schema.game.common.controller.elements.ManagerContainer;
/*  19:    */import org.schema.game.common.controller.elements.missile.MissileController;
/*  20:    */import org.schema.game.common.controller.elements.missile.MissileElementManager;
/*  21:    */import org.schema.game.common.controller.elements.missile.MissileUnit;
/*  22:    */import org.schema.game.common.data.element.ControlElementMap;
/*  23:    */import org.schema.game.common.data.physics.CubeRayCastResult;
/*  24:    */import org.schema.game.common.data.physics.PhysicsExt;
/*  25:    */import org.schema.game.common.data.world.Segment;
/*  26:    */import org.schema.game.network.objects.NetworkPlayer;
/*  27:    */import org.schema.game.server.controller.GameServerController;
/*  28:    */import org.schema.schine.network.StateInterface;
/*  29:    */import org.schema.schine.network.objects.NetworkEntity;
/*  30:    */import org.schema.schine.network.objects.NetworkObject;
/*  31:    */import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*  32:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  33:    */import q;
/*  34:    */import x;
/*  35:    */import xe;
/*  36:    */
/*  37:    */public class DumbMissileElementManager
/*  38:    */  extends MissileElementManager
/*  39:    */{
/*  40:    */  private Segment cachedLastWeaponFireHitSegment;
/*  41: 41 */  private Vector3f shootingDirTemp = new Vector3f();
/*  42: 42 */  private q controlledFromOrig = new q();
/*  43: 43 */  private q controlledFrom = new q();
/*  44:    */  private ArrayList missileManagers;
/*  45:    */  
/*  46:    */  public DumbMissileElementManager(SegmentController paramSegmentController) {
/*  47: 47 */    super((short)38, (short)32, paramSegmentController);
/*  48: 48 */    this.missileManagers = new ArrayList();
/*  49:    */  }
/*  50:    */  
/*  51:    */  public ArrayList getCollectionManagers()
/*  52:    */  {
/*  53: 53 */    return this.missileManagers;
/*  54:    */  }
/*  55:    */  
/*  56: 56 */  public MissileController getMissileController() { return ((GameServerController)getSegmentController().getState().getController()).a(); }
/*  57:    */  
/*  61:    */  public ElementCollectionManager getNewCollectionManager(le paramle)
/*  62:    */  {
/*  63: 63 */    return new DumbMissileCollectionManager(paramle, getSegmentController());
/*  64:    */  }
/*  65:    */  
/*  66:    */  public void handle(lA paramlA)
/*  67:    */  {
/*  68: 68 */    if (!((Boolean)paramlA.a.a().activeControllerMask.get(1).get()).booleanValue()) {
/*  69: 69 */      return;
/*  70:    */    }
/*  71: 71 */    if (getCollectionManagers().isEmpty())
/*  72:    */    {
/*  73: 73 */      return;
/*  74:    */    }
/*  75: 75 */    if (!convertDeligateControls(paramlA, this.controlledFromOrig, this.controlledFrom)) {
/*  76: 76 */      return;
/*  77:    */    }
/*  78:    */    
/*  79: 79 */    for (int i = 0; i < getCollectionManagers().size(); 
/*  80:    */        
/*  81: 81 */        i++)
/*  82:    */    {
/*  83:    */      DumbMissileCollectionManager localDumbMissileCollectionManager;
/*  84: 84 */      int j = 0; if ((localDumbMissileCollectionManager = (DumbMissileCollectionManager)getCollectionManagers().get(i)).equalsControllerPos(this.controlledFrom))
/*  85:    */      {
/*  88: 88 */        if ((this.controlledFromOrig.equals(this.controlledFrom) | getControlElementMap().isControlling(this.controlledFromOrig, localDumbMissileCollectionManager.getControllerPos(), this.controllerId)))
/*  89:    */        {
/*  90: 90 */          for (j = 0; j < localDumbMissileCollectionManager.getCollection().size(); j++) {
/*  91:    */            MissileUnit localMissileUnit;
/*  92: 92 */            if ((localMissileUnit = (MissileUnit)localDumbMissileCollectionManager.getCollection().get(j)).canShoot()) {
/*  93: 93 */              Object localObject1 = localMissileUnit.getOutput();
/*  94:    */              
/*  95: 95 */              localObject1 = new Vector3f(((q)localObject1).a - 8, ((q)localObject1).b - 8, ((q)localObject1).c - 8);
/*  96:    */              
/* 101:101 */              getWorldTransform().transform((Vector3f)localObject1);
/* 102:    */              
/* 103:103 */              (
/* 104:104 */                localObject2 = new q(this.controlledFromOrig)).c(kd.a);
/* 105:105 */              Object localObject2 = getSegmentController().getAbsoluteElementWorldPosition((q)localObject2, new Vector3f());
/* 106:106 */              Vector3f localVector3f = new Vector3f(paramlA.a.a());
/* 107:107 */              PhysicsExt localPhysicsExt = getSegmentController().getPhysics();
/* 108:108 */              localVector3f.scale(localMissileUnit.getDistance());
/* 109:109 */              localVector3f.add((Tuple3f)localObject2);
/* 110:    */              
/* 114:114 */              if ((localObject2 = localPhysicsExt.testRayCollisionPoint((Vector3f)localObject2, localVector3f, false, getSegmentController(), null, true, this.cachedLastWeaponFireHitSegment, false)).hasHit()) {
/* 115:115 */                if ((localObject2 instanceof CubeRayCastResult)) {
/* 116:116 */                  this.cachedLastWeaponFireHitSegment = ((CubeRayCastResult)localObject2).getSegment();
/* 117:    */                }
/* 118:    */                
/* 119:119 */                this.shootingDirTemp.sub(((CollisionWorld.ClosestRayResultCallback)localObject2).hitPointWorld, (Tuple3f)localObject1);
/* 120:    */              } else {
/* 121:121 */                this.cachedLastWeaponFireHitSegment = null;
/* 122:122 */                this.shootingDirTemp.set(paramlA.a.a());
/* 123:    */              }
/* 124:    */              
/* 128:128 */              this.shootingDirTemp.normalize();
/* 129:129 */              this.shootingDirTemp.scale(localMissileUnit.getSpeed());
/* 130:130 */              localMissileUnit.updateLastShoot();
/* 131:    */              
/* 132:132 */              if (getSegmentController().isOnServer())
/* 133:    */              {
/* 134:134 */                (localObject2 = new Transform()).setIdentity();
/* 135:135 */                ((Transform)localObject2).origin.set((Tuple3f)localObject1);
/* 136:    */                
/* 137:137 */                getMissileController().addDumbMissile(getSegmentController(), (Transform)localObject2, new Vector3f(this.shootingDirTemp), localMissileUnit.getSpeed(), localMissileUnit.getBlastRadius(), localMissileUnit.getDamage(), localMissileUnit.getDistance());
/* 138:    */              }
/* 139:    */              
/* 141:141 */              (localObject2 = new Transform()).setIdentity();
/* 142:142 */              ((Transform)localObject2).origin.set((Tuple3f)localObject1);
/* 143:    */              
/* 144:144 */              if (!getSegmentController().isOnServer()) {
/* 145:145 */                xe.a("0022_spaceship user - missile fire 1", (Transform)localObject2, 5.0F);
/* 146:146 */                notifyShooting(localMissileUnit);
/* 147:    */              }
/* 148:148 */              getManagerContainer().onAction();
/* 149:    */            }
/* 150:    */          }
/* 151:    */          
/* 153:153 */          if ((localDumbMissileCollectionManager.getCollection().isEmpty()) && (clientIsOwnShip())) {
/* 154:154 */            ((ct)getState()).a().d("WARNING!\n \nNo Weapons connected \nto entry point");
/* 155:    */          }
/* 156:    */        }
/* 157:    */      }
/* 158:    */    }
/* 159:159 */    if ((getCollectionManagers().isEmpty()) && (clientIsOwnShip()))
/* 160:    */    {
/* 163:163 */      ((ct)getState()).a().d("WARNING!\n \nNo weapon controllers");
/* 164:    */    }
/* 165:    */  }
/* 166:    */  
/* 167:    */  public void notifyShooting(MissileUnit paramMissileUnit) {
/* 168:168 */    notifyObservers(paramMissileUnit, "s");
/* 169:    */  }
/* 170:    */  
/* 173:    */  public void updateFromNT(NetworkObject paramNetworkObject) {}
/* 174:    */  
/* 177:    */  public void updateToFullNT(NetworkObject paramNetworkObject)
/* 178:    */  {
/* 179:179 */    if (getSegmentController().isOnServer())
/* 180:    */    {
/* 190:190 */      for (paramNetworkObject = 0; paramNetworkObject < getCollectionManagers().size(); paramNetworkObject++) {
/* 191:191 */        ((DumbMissileCollectionManager)getCollectionManagers().get(paramNetworkObject)).sendDistribution();
/* 192:    */      }
/* 193:    */    }
/* 194:    */  }
/* 195:    */  
/* 198:    */  public boolean receiveDistribution(jE paramjE, NetworkEntity paramNetworkEntity)
/* 199:    */  {
/* 200:200 */    return receiveDistribution(paramjE);
/* 201:    */  }
/* 202:    */  
/* 203:    */  public void onActivate(le paramle, boolean paramBoolean)
/* 204:    */  {
/* 205:205 */    q localq = paramle.a(new q());
/* 206:206 */    Iterator localIterator; for (int i = 0; i < getCollectionManagers().size(); i++) {
/* 207:207 */      for (localIterator = ((DumbMissileCollectionManager)getCollectionManagers().get(i)).getCollection().iterator(); localIterator.hasNext();) { MissileUnit localMissileUnit;
/* 208:208 */        if ((localMissileUnit = (MissileUnit)localIterator.next()).contains(localq)) {
/* 209:209 */          localMissileUnit.setMainPiece(paramle, paramBoolean);
/* 210:    */          
/* 211:211 */          return;
/* 212:    */        }
/* 213:    */      }
/* 214:    */    }
/* 215:    */  }
/* 216:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.dumb.DumbMissileElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */