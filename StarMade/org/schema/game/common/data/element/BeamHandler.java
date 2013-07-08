/*   1:    */package org.schema.game.common.data.element;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*   4:    */import ct;
/*   5:    */import dm;
/*   6:    */import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*   9:    */import java.util.Collection;
/*  10:    */import java.util.HashSet;
/*  11:    */import java.util.Iterator;
/*  12:    */import javax.vecmath.Vector3f;
/*  13:    */import jq;
/*  14:    */import lE;
/*  15:    */import le;
/*  16:    */import org.schema.common.FastMath;
/*  17:    */import org.schema.game.common.controller.SegmentController;
/*  18:    */import org.schema.game.common.data.physics.CubeRayCastResult;
/*  19:    */import org.schema.game.common.data.physics.PhysicsExt;
/*  20:    */import org.schema.game.common.data.world.Segment;
/*  21:    */import org.schema.game.common.data.world.SegmentData;
/*  22:    */import org.schema.schine.network.StateInterface;
/*  23:    */import q;
/*  24:    */import x;
/*  25:    */import xq;
/*  26:    */
/* 109:    */public abstract class BeamHandler
/* 110:    */{
/* 111:    */  private final SegmentController segmentController;
/* 112:    */  private jq owner;
/* 113:113 */  private final Long2ObjectOpenHashMap beamStates = new Long2ObjectOpenHashMap();
/* 114:114 */  BeamHandler.BeamState sTmp = new BeamHandler.BeamState(this, new q(), new Vector3f(), new Vector3f(), null, 0.0F);
/* 115:115 */  Vector3f start = new Vector3f();
/* 116:116 */  Vector3f end = new Vector3f();
/* 117:    */  private boolean lastActive;
/* 118:118 */  String[] cannotHitReason = new String[1];
/* 119:    */  
/* 120:120 */  public BeamHandler(SegmentController paramSegmentController, jq paramjq) { this.segmentController = paramSegmentController;
/* 121:121 */    this.owner = paramjq;
/* 122:    */  }
/* 123:    */  
/* 124:    */  public void clearStates() {
/* 125:125 */    this.beamStates.clear();
/* 126:126 */    if (this.drawer != null) {
/* 127:127 */      this.drawer.a(this, false);
/* 128:    */    }
/* 129:    */  }
/* 130:    */  
/* 133:    */  public void addBeam(q paramq, Vector3f paramVector3f1, Vector3f paramVector3f2, lE paramlE, float paramFloat)
/* 134:    */  {
/* 135:135 */    long l = ElementCollection.getIndex(paramq);
/* 136:    */    
/* 137:137 */    if (!this.beamStates.containsKey(l))
/* 138:    */    {
/* 139:139 */      (paramq = new BeamHandler.BeamState(this, paramq, paramVector3f1, paramVector3f2, paramlE, paramFloat)).timeRunning = 0.0F;
/* 140:140 */      this.beamStates.put(l, paramq);
/* 141:141 */      return;
/* 142:    */    }
/* 143:143 */    (paramq = (BeamHandler.BeamState)this.beamStates.get(l)).from.set(paramVector3f1);
/* 144:144 */    paramq.to.set(paramVector3f2);
/* 145:145 */    paramq.timeRunning = 0.0F;
/* 146:146 */    paramq.playerState = paramlE;
/* 147:    */  }
/* 148:    */  
/* 153:    */  public int beamHit(BeamHandler.BeamState paramBeamState, le paramle)
/* 154:    */  {
/* 155:155 */    assert (paramle != null);
/* 156:156 */    if (paramle.equals(paramBeamState.segmentHit)) {
/* 157:157 */      paramBeamState.hitOneSegment += paramBeamState.timeSpent;
/* 158:158 */      paramBeamState.timeSpent = 0.0F;
/* 159:    */    } else {
/* 160:160 */      paramBeamState.segmentHit = paramle;
/* 161:161 */      paramBeamState.hitOneSegment = 0.0F;
/* 162:    */    }
/* 163:163 */    float f = getBeamToHitInSecs(paramBeamState);
/* 164:    */    
/* 170:170 */    paramle = FastMath.a(paramBeamState.hitOneSegment / f);
/* 171:171 */    paramBeamState.hitOneSegment -= paramle * f;
/* 172:172 */    this.cannotHitReason[0] = "";
/* 173:173 */    return paramle;
/* 174:    */  }
/* 175:    */  
/* 180:    */  public abstract boolean canhit(SegmentController paramSegmentController, String[] paramArrayOfString, q paramq);
/* 181:    */  
/* 185:    */  public synchronized BeamHandler.BeamState getBeam(q paramq)
/* 186:    */  {
/* 187:187 */    if ((paramq = (BeamHandler.BeamState)getBeamStates().get(ElementCollection.getIndex(paramq))) != null) {
/* 188:188 */      return paramq;
/* 189:    */    }
/* 190:190 */    throw new BeamHandler.ElementNotFoundException(this);
/* 191:    */  }
/* 192:    */  
/* 195:    */  public Long2ObjectOpenHashMap getBeamStates()
/* 196:    */  {
/* 197:197 */    return this.beamStates;
/* 198:    */  }
/* 199:    */  
/* 200:    */  public abstract float getBeamTimeoutInSecs();
/* 201:    */  
/* 202:    */  public abstract float getBeamToHitInSecs(BeamHandler.BeamState paramBeamState);
/* 203:    */  
/* 204:    */  public SegmentController getSegmentController()
/* 205:    */  {
/* 206:206 */    return this.segmentController;
/* 207:    */  }
/* 208:    */  
/* 209:209 */  public boolean isAnyBeamActiveActive() { return !this.beamStates.isEmpty(); }
/* 210:    */  public abstract void onBeamHit(BeamHandler.BeamState paramBeamState, jq paramjq, q paramq, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, CubeRayCastResult paramCubeRayCastResult, xq paramxq, Collection paramCollection);
/* 211:211 */  private q posTmp = new q();
/* 212:    */  
/* 213:213 */  private final HashSet updatedSegments = new HashSet();
/* 214:    */  
/* 215:    */  public void update(xq paramxq) {
/* 216:216 */    if (getBeamStates().isEmpty()) {
/* 217:217 */      return;
/* 218:    */    }
/* 219:    */    
/* 222:222 */    System.currentTimeMillis();
/* 223:223 */    this.updatedSegments.clear();
/* 224:224 */    ObjectIterator localObjectIterator = getBeamStates().values().iterator();
/* 225:225 */    float f = paramxq.a();
/* 226:226 */    while (localObjectIterator.hasNext())
/* 227:    */    {
/* 228:228 */      if ((localObject = (BeamHandler.BeamState)localObjectIterator.next()).timeRunning > getBeamTimeoutInSecs()) {
/* 229:229 */        localObjectIterator.remove();
/* 230:    */      }
/* 231:    */      else {
/* 232:232 */        this.posTmp.b(((BeamHandler.BeamState)localObject).elementPos.a - 8, ((BeamHandler.BeamState)localObject).elementPos.b - 8, ((BeamHandler.BeamState)localObject).elementPos.c - 8);
/* 233:    */        
/* 235:235 */        getSegmentController().getAbsoluteElementWorldPosition(this.posTmp, ((BeamHandler.BeamState)localObject).from);
/* 236:236 */        this.start.set(((BeamHandler.BeamState)localObject).from);
/* 237:237 */        this.end.set(((BeamHandler.BeamState)localObject).to);
/* 238:238 */        updateBeam(this.start, this.end, (BeamHandler.BeamState)localObject, paramxq, this.updatedSegments);
/* 239:    */      }
/* 240:240 */      localObject.timeRunning += f;
/* 241:    */    }
/* 242:    */    
/* 245:245 */    for (Object localObject = this.updatedSegments.iterator(); ((Iterator)localObject).hasNext();) {
/* 246:246 */      if ((paramxq = (Segment)((Iterator)localObject).next()).a() != null) {
/* 247:247 */        paramxq.a().restructBB(true);
/* 248:    */      }
/* 249:    */    }
/* 250:250 */    System.currentTimeMillis();
/* 251:    */    
/* 253:253 */    if (this.lastActive != isAnyBeamActiveActive())
/* 254:    */    {
/* 255:255 */      if (this.drawer != null)
/* 256:    */      {
/* 257:257 */        this.drawer.a(this, isAnyBeamActiveActive());
/* 258:    */      }
/* 259:    */    }
/* 260:    */    
/* 261:261 */    this.lastActive = isAnyBeamActiveActive();
/* 262:    */  }
/* 263:    */  
/* 264:264 */  private Vector3f dirTmp = new Vector3f();
/* 265:    */  private dm drawer;
/* 266:    */  
/* 267:267 */  public void updateBeam(Vector3f paramVector3f1, Vector3f paramVector3f2, BeamHandler.BeamState paramBeamState, xq paramxq, Collection paramCollection) { Object localObject = getSegmentController();
/* 268:    */    
/* 269:269 */    paramBeamState.timeSpent += paramxq.a();
/* 270:270 */    if (!paramBeamState.checkNecessary(paramxq, paramBeamState)) {
/* 271:271 */      if (paramBeamState.hitPoint != null) {
/* 272:272 */        paramBeamState.hitPoint.sub(paramVector3f1);
/* 273:273 */        this.dirTmp.set(paramVector3f2);
/* 274:274 */        this.dirTmp.sub(paramVector3f1);
/* 275:275 */        this.dirTmp.normalize();
/* 276:276 */        this.dirTmp.scale(paramBeamState.hitPoint.length());
/* 277:277 */        this.dirTmp.add(paramVector3f1);
/* 278:278 */        paramBeamState.hitPoint.set(this.dirTmp);
/* 279:    */      }
/* 280:280 */      return;
/* 281:    */    }
/* 282:    */    
/* 288:288 */    if (((localObject = ((SegmentController)localObject).getPhysics().testRayCollisionPoint(paramVector3f1, paramVector3f2, false, (SegmentController)localObject, null, false, null, true)).hasHit()) && (((CollisionWorld.ClosestRayResultCallback)localObject).collisionObject != null))
/* 289:    */    {
/* 290:290 */      paramBeamState.hitPoint = ((CollisionWorld.ClosestRayResultCallback)localObject).hitPointWorld;
/* 291:    */      
/* 297:297 */      if (((localObject instanceof CubeRayCastResult)) && (((CubeRayCastResult)localObject).getSegment() != null))
/* 298:    */      {
/* 299:299 */        SegmentController localSegmentController = (localObject = (CubeRayCastResult)localObject).getSegment().a();
/* 300:300 */        paramBeamState.cachedLastSegment = ((CubeRayCastResult)localObject).getSegment();
/* 301:301 */        this.cannotHitReason[0] = "";
/* 302:302 */        le localle = new le(((CubeRayCastResult)localObject).getSegment(), ((CubeRayCastResult)localObject).cubePos);
/* 303:303 */        if (canhit(localSegmentController, this.cannotHitReason, localle.a(new q()))) {
/* 304:304 */          onBeamHit(paramBeamState, this.owner, paramBeamState.elementPos, ((CubeRayCastResult)localObject).getSegment(), paramVector3f1, paramVector3f2, (CubeRayCastResult)localObject, paramxq, paramCollection);
/* 305:305 */          return;
/* 306:    */        }
/* 307:307 */        if (!localSegmentController.isOnServer())
/* 308:    */        {
/* 309:309 */          if (((ct)getSegmentController().getState()).a() == getSegmentController()) {
/* 310:310 */            ((x)localSegmentController.getState().getController()).d(localSegmentController.toNiceString() + "\ncannot be hit by this beam.\n" + this.cannotHitReason[0]);
/* 311:    */          }
/* 312:    */          
/* 313:    */        }
/* 314:    */      }
/* 315:    */    }
/* 316:    */    else
/* 317:    */    {
/* 318:318 */      paramBeamState.hitPoint = null;
/* 319:319 */      paramBeamState.cachedLastSegment = null;
/* 320:    */    }
/* 321:321 */    paramBeamState.segmentHit = null;
/* 322:322 */    paramBeamState.hitOneSegment = 0.0F;
/* 323:    */  }
/* 324:    */  
/* 325:    */  public void setDrawer(dm paramdm) {
/* 326:326 */    this.drawer = paramdm;
/* 327:    */  }
/* 328:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.BeamHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */