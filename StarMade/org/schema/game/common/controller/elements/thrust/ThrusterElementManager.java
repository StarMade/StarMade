/*   1:    */package org.schema.game.common.controller.elements.thrust;
/*   2:    */
/*   3:    */import cv;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.util.ArrayList;
/*   6:    */import jv;
/*   7:    */import kX;
/*   8:    */import kd;
/*   9:    */import le;
/*  10:    */import lg;
/*  11:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  12:    */import org.schema.game.common.controller.elements.UsableControllableSingleElementManager;
/*  13:    */import org.schema.game.common.data.physics.PhysicsExt;
/*  14:    */import org.schema.game.network.objects.NetworkPlayer;
/*  15:    */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*  16:    */import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*  17:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  18:    */import q;
/*  19:    */import vg;
/*  20:    */import x;
/*  21:    */
/*  22:    */public class ThrusterElementManager extends UsableControllableSingleElementManager
/*  23:    */{
/*  24:    */  public static final float MAX_VELOCITY_UNIT = 2.0F;
/*  25: 25 */  private javax.vecmath.Vector3f velocity = new javax.vecmath.Vector3f();
/*  26:    */  
/*  27:    */  private long lastMoveUpdate;
/*  28:    */  
/*  29: 29 */  private javax.vecmath.Vector3f linearVelocityTmp = new javax.vecmath.Vector3f();
/*  30:    */  
/*  31:    */  private ThrusterCollectionManager thrustManager;
/*  32:    */  
/*  33:    */  public ThrusterElementManager(org.schema.game.common.controller.SegmentController paramSegmentController)
/*  34:    */  {
/*  35: 35 */    super(new ThrusterCollectionManager(paramSegmentController), paramSegmentController);
/*  36: 36 */    this.thrustManager = ((ThrusterCollectionManager)getCollection());
/*  37:    */    
/*  38: 38 */    if (!paramSegmentController.isOnServer()) {
/*  39: 39 */      addObserver((kX)paramSegmentController.getState());
/*  40:    */    }
/*  41:    */  }
/*  42:    */  
/*  43:    */  public boolean canHandle(lA paramlA)
/*  44:    */  {
/*  45: 45 */    return true;
/*  46:    */  }
/*  47:    */  
/*  48:    */  public float getActualThrust() {
/*  49:    */    float f;
/*  50: 50 */    if ((f = this.thrustManager.getTotalThrust()) == 0.0F) {
/*  51: 51 */      return 0.0F;
/*  52:    */    }
/*  53: 53 */    return Math.max(0.5F, f);
/*  54:    */  }
/*  55:    */  
/*  58:    */  public float getMaxVelocity()
/*  59:    */  {
/*  60: 60 */    if (getSegmentController().isOnServer()) {
/*  61: 61 */      return ((vg)getSegmentController().getState()).a().a();
/*  62:    */    }
/*  63: 63 */    return ((ct)getSegmentController().getState()).a().a();
/*  64:    */  }
/*  65:    */  
/*  73:    */  public ElementCollectionManager getNewCollectionManager(le paramle)
/*  74:    */  {
/*  75: 75 */    return new ThrusterCollectionManager(getSegmentController());
/*  76:    */  }
/*  77:    */  
/*  80:    */  public javax.vecmath.Vector3f getVelocity()
/*  81:    */  {
/*  82: 82 */    return this.velocity;
/*  83:    */  }
/*  84:    */  
/*  85: 85 */  private final javax.vecmath.Vector3f up = new javax.vecmath.Vector3f();
/*  86: 86 */  private final javax.vecmath.Vector3f down = new javax.vecmath.Vector3f();
/*  87: 87 */  private final javax.vecmath.Vector3f left = new javax.vecmath.Vector3f();
/*  88: 88 */  private final javax.vecmath.Vector3f right = new javax.vecmath.Vector3f();
/*  89: 89 */  private final javax.vecmath.Vector3f forward = new javax.vecmath.Vector3f();
/*  90: 90 */  private final javax.vecmath.Vector3f backward = new javax.vecmath.Vector3f();
/*  91: 91 */  private final javax.vecmath.Vector3f dir = new javax.vecmath.Vector3f();
/*  92:    */  
/*  94:    */  public void handle(lA paramlA)
/*  95:    */  {
/*  96: 96 */    if (getSegmentController().getMass() <= 0.0F) {
/*  97: 97 */      return;
/*  98:    */    }
/*  99: 99 */    if (!((Boolean)paramlA.jdField_a_of_type_LE.a().activeControllerMask.get(1).get()).booleanValue()) {
/* 100:100 */      return;
/* 101:    */    }
/* 102:    */    
/* 103:103 */    if (!kd.a.equals(paramlA.jdField_a_of_type_JavaLangObject))
/* 104:    */    {
/* 105:105 */      return;
/* 106:    */    }
/* 107:107 */    float f1 = 3.0F / Math.max(1.0F, getSegmentController().getMass() / 8.7F);
/* 108:108 */    f1 = Math.max(0.1F, f1);
/* 109:    */    
/* 111:111 */    float f2 = getActualThrust();
/* 112:    */    
/* 113:113 */    if (getSegmentController().getDockingController().a() != null) {
/* 114:114 */      paramlA.jdField_a_of_type_LE.c(this.up);
/* 115:115 */      this.down.set(this.up);
/* 116:116 */      this.down.negate();
/* 117:117 */      paramlA.jdField_a_of_type_LE.b(this.left);
/* 118:118 */      this.right.set(this.left);
/* 119:119 */      this.right.negate();
/* 120:120 */      paramlA.jdField_a_of_type_LE.a(this.forward);
/* 121:121 */      this.backward.set(this.forward);
/* 122:122 */      this.backward.negate();
/* 123:    */      
/* 125:125 */      getSegmentController().getPhysics().orientate(getSegmentController(), this.forward, this.up, this.right, f1);
/* 126:    */      
/* 130:130 */      return;
/* 131:    */    }
/* 132:    */    
/* 133:133 */    if (f2 == 0.0F) {
/* 134:134 */      if (clientIsOwnShip()) {
/* 135:135 */        ((ct)getState()).a().d("WARNING!\n \nNo thrusters connected to core");
/* 136:    */      }
/* 137:137 */      f2 = 0.1F;
/* 138:138 */    } else if ((f2 <= 0.5F) && 
/* 139:139 */      (clientIsOwnShip())) {
/* 140:140 */      ((ct)getState()).a().d("WARNING!\n \nNot enough Thrusters for\n the mass of your ship\n-> Ship can only move slow");
/* 141:    */    }
/* 142:    */    
/* 144:144 */    paramlA.jdField_a_of_type_LE.c(this.up);
/* 145:145 */    this.down.set(this.up);
/* 146:146 */    this.down.negate();
/* 147:147 */    paramlA.jdField_a_of_type_LE.b(this.left);
/* 148:148 */    this.right.set(this.left);
/* 149:149 */    this.right.negate();
/* 150:150 */    paramlA.jdField_a_of_type_LE.a(this.forward);
/* 151:151 */    this.backward.set(this.forward);
/* 152:152 */    this.backward.negate();
/* 153:    */    
/* 154:154 */    this.dir.set(0.0F, 0.0F, 0.0F);
/* 155:    */    
/* 156:156 */    if (paramlA.jdField_a_of_type_LE.a(cv.i)) {
/* 157:157 */      this.dir.add(this.forward);
/* 158:    */    }
/* 159:159 */    if (paramlA.jdField_a_of_type_LE.a(cv.j)) {
/* 160:160 */      this.dir.add(this.backward);
/* 161:    */    }
/* 162:162 */    if (paramlA.jdField_a_of_type_LE.a(cv.g)) {
/* 163:163 */      this.dir.add(this.right);
/* 164:    */    }
/* 165:165 */    if (paramlA.jdField_a_of_type_LE.a(cv.h)) {
/* 166:166 */      this.dir.add(this.left);
/* 167:    */    }
/* 168:168 */    if (paramlA.jdField_a_of_type_LE.a(cv.k)) {
/* 169:169 */      this.dir.add(this.up);
/* 170:    */    }
/* 171:171 */    if (paramlA.jdField_a_of_type_LE.a(cv.l)) {
/* 172:172 */      this.dir.add(this.down);
/* 173:    */    }
/* 174:174 */    com.bulletphysics.dynamics.RigidBody localRigidBody = (com.bulletphysics.dynamics.RigidBody)getPhysicsDataContainer().getObject();
/* 175:    */    
/* 176:176 */    if (this.lastMoveUpdate + 30L < System.currentTimeMillis()) {
/* 177:177 */      if (this.dir.length() > 0.0F) {
/* 178:178 */        paramlA = this.thrustManager.getTotalThrust();
/* 179:179 */        if (getPowerManager().getPower() < this.thrustManager.getTotalThrust()) {
/* 180:180 */          if (getPowerManager().getPower() <= 0.0D) {
/* 181:181 */            return;
/* 182:    */          }
/* 183:183 */          double d1 = getPowerManager().getPower();
/* 184:184 */          getPowerManager().consumePowerInstantly(d1);
/* 185:185 */          f2 = (float)d1;
/* 187:    */        }
/* 188:188 */        else if (!getPowerManager().consumePowerInstantly(paramlA)) {
/* 189:189 */          if (clientIsOwnShip()) {
/* 190:190 */            ((ct)getState()).a().d("WARNING!\n \nThrusters have no power");
/* 191:    */          }
/* 192:192 */          return;
/* 193:    */        }
/* 194:    */        
/* 196:196 */        localRigidBody.activate();
/* 197:    */        
/* 198:198 */        this.dir.scale(f2 * 0.5F);
/* 199:199 */        localRigidBody.applyCentralImpulse(this.dir);
/* 200:200 */        localRigidBody.getLinearVelocity(this.linearVelocityTmp);
/* 201:201 */        if (this.linearVelocityTmp.length() > getMaxVelocity()) {
/* 202:202 */          this.linearVelocityTmp.normalize();
/* 203:203 */          this.linearVelocityTmp.scale(getMaxVelocity());
/* 204:204 */          localRigidBody.setLinearVelocity(this.linearVelocityTmp);
/* 205:    */        }
/* 206:    */        
/* 208:    */      }
/* 209:209 */      else if (paramlA.jdField_a_of_type_LE.a(cv.p))
/* 210:    */      {
/* 211:211 */        if ((paramlA = localRigidBody.getLinearVelocity(new javax.vecmath.Vector3f())).length() > 1.0F) {
/* 212:212 */          float f3 = this.thrustManager.getTotalThrust();
/* 213:213 */          if (getPowerManager().getPower() < this.thrustManager.getTotalThrust()) {
/* 214:214 */            if (getPowerManager().getPower() > 0.0D) {
/* 215:215 */              double d2 = getPowerManager().getPower();
/* 216:    */              
/* 218:218 */              getPowerManager().consumePowerInstantly(d2);
/* 219:219 */              f2 = (float)d2;
/* 220:    */              break label885;
/* 221:    */            }
/* 222:222 */          } else { if (getPowerManager().consumePowerInstantly(f3)) break label885;
/* 223:223 */            if (clientIsOwnShip())
/* 224:224 */              ((ct)getState()).a().d("WARNING!\n \nThrusters have no power");
/* 225:    */          }
/* 226:226 */          f2 = 0.1F;
/* 227:    */          
/* 229:    */          label885:
/* 230:    */          
/* 231:    */          javax.vecmath.Vector3f localVector3f1;
/* 232:    */          
/* 233:233 */          (localVector3f1 = new javax.vecmath.Vector3f(paramlA)).normalize();
/* 234:234 */          localVector3f1.negate();
/* 235:235 */          localVector3f1.scale(f2 * 0.5F);
/* 236:    */          javax.vecmath.Vector3f localVector3f2;
/* 237:237 */          (localVector3f2 = new javax.vecmath.Vector3f(localVector3f1)).scale(localRigidBody.getInvMass());
/* 238:    */          
/* 239:239 */          if (paramlA.length() < localVector3f2.length()) {
/* 240:240 */            System.err.println("INSTA ZERO: " + paramlA.length() + " < " + localVector3f1.length());
/* 241:    */          }
/* 242:    */          else
/* 243:    */          {
/* 244:244 */            localRigidBody.applyCentralImpulse(localVector3f1);
/* 245:    */            break label1010;
/* 246:    */          }
/* 247:    */        }
/* 248:248 */        paramlA.set(0.0F, 0.0F, 0.0F);
/* 249:249 */        localRigidBody.setLinearVelocity(paramlA);
/* 250:    */      }
/* 251:    */      
/* 253:    */      label1010:
/* 254:    */      
/* 256:256 */      if (!getAttachedPlayers().isEmpty()) {
/* 257:257 */        getSegmentController().getPhysics().orientate(getSegmentController(), this.forward, this.up, this.right, f1);
/* 258:    */      }
/* 259:    */      
/* 260:260 */      localRigidBody.getLinearVelocity(getVelocity());
/* 261:261 */      this.lastMoveUpdate = System.currentTimeMillis();
/* 262:    */    }
/* 263:    */  }
/* 264:    */  
/* 269:    */  public void onControllerChange() {}
/* 270:    */  
/* 274:    */  public void setVelocity(javax.vecmath.Vector3f paramVector3f)
/* 275:    */  {
/* 276:276 */    this.velocity = paramVector3f;
/* 277:    */  }
/* 278:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.thrust.ThrusterElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */