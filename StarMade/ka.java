/*   1:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*   2:    */import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*   3:    */import com.bulletphysics.linearmath.Transform;
/*   4:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*   6:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   7:    */import java.io.PrintStream;
/*   8:    */import javax.vecmath.Vector3f;
/*   9:    */import org.schema.game.common.controller.SegmentController;
/*  10:    */import org.schema.game.common.controller.elements.ManagerContainer;
/*  11:    */import org.schema.game.common.controller.elements.PowerAddOn;
/*  12:    */import org.schema.game.common.controller.elements.PowerManagerInterface;
/*  13:    */import org.schema.game.common.controller.elements.ShieldContainerInterface;
/*  14:    */import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*  15:    */import org.schema.game.common.data.element.ControlElementMap;
/*  16:    */import org.schema.game.common.data.world.Segment;
/*  17:    */import org.schema.game.common.data.world.SegmentData;
/*  18:    */import org.schema.game.network.objects.NetworkSegmentController;
/*  19:    */import org.schema.game.network.objects.NetworkSegmentProvider;
/*  20:    */import org.schema.game.network.objects.remote.RemoteSegmentPiece;
/*  21:    */import org.schema.game.network.objects.remote.RemoteSegmentPieceBuffer;
/*  22:    */import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*  23:    */import org.schema.schine.network.NetworkStateContainer;
/*  24:    */import org.schema.schine.network.StateInterface;
/*  25:    */import org.schema.schine.network.SynchronizationContainerController;
/*  26:    */import org.schema.schine.network.objects.NetworkObject;
/*  27:    */import org.schema.schine.network.objects.Sendable;
/*  28:    */import org.schema.schine.network.objects.remote.RemoteBoolean;
/*  29:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  30:    */import org.schema.schine.network.objects.remote.RemoteIntPrimitive;
/*  31:    */import org.schema.schine.network.objects.remote.RemoteLongPrimitive;
/*  32:    */import org.schema.schine.network.objects.remote.RemoteString;
/*  33:    */import org.schema.schine.network.objects.remote.RemoteVector3f;
/*  34:    */import org.schema.schine.network.objects.remote.RemoteVector3i;
/*  35:    */import org.schema.schine.network.objects.remote.RemoteVector4i;
/*  36:    */
/*  40:    */public abstract class ka
/*  41:    */  extends SegmentController
/*  42:    */  implements Sendable
/*  43:    */{
/*  44:    */  private NetworkSegmentController networkEntity;
/*  45: 45 */  private boolean first = true;
/*  46: 46 */  private final ObjectArrayFIFOQueue blockActivationBuffer = new ObjectArrayFIFOQueue();
/*  47:    */  
/*  48:    */  private int lastModifierId;
/*  49:    */  
/*  50:    */  private boolean lastModifierChanged;
/*  51:    */  
/*  52:    */  private kc serverSendableSegmentProvider;
/*  53:    */  
/*  55:    */  public ka(StateInterface paramStateInterface)
/*  56:    */  {
/*  57: 57 */    super(paramStateInterface);
/*  58: 58 */    getControlElementMap().setSendableSegmentController(this);
/*  59:    */  }
/*  60:    */  
/*  63:    */  public ObjectArrayFIFOQueue getBlockActivationBuffer()
/*  64:    */  {
/*  65: 65 */    return this.blockActivationBuffer;
/*  66:    */  }
/*  67:    */  
/*  72:    */  public NetworkSegmentController getNetworkObject()
/*  73:    */  {
/*  74: 74 */    return this.networkEntity;
/*  75:    */  }
/*  76:    */  
/*  83:    */  public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, lb paramlb, float paramFloat) {}
/*  84:    */  
/*  90:    */  public void handleHitMissile(ln paramln, Transform paramTransform) {}
/*  91:    */  
/*  97:    */  public void handleNTDockChanged()
/*  98:    */  {
/*  99: 99 */    getDockingController().a(getNetworkObject());
/* 100:    */  }
/* 101:    */  
/* 103:    */  private void handleReceivedBlockActivations(NetworkSegmentController paramNetworkSegmentController)
/* 104:    */  {
/* 105:105 */    for (int i = 0; i < paramNetworkSegmentController.blockActivationBuffer.getReceiveBuffer().size(); i++) {
/* 106:106 */      s locals = ((RemoteVector4i)paramNetworkSegmentController.blockActivationBuffer.getReceiveBuffer().get(i)).getVector(new s());
/* 107:107 */      synchronized (getBlockActivationBuffer()) {
/* 108:108 */        System.err.println("[SERVER] RECEIVED BLOCK ACTIVATION");
/* 109:109 */        getBlockActivationBuffer().enqueue(locals);
/* 110:    */      }
/* 111:    */    }
/* 112:    */  }
/* 113:    */  
/* 116:116 */  private void handleReceivedControllers(NetworkSegmentController paramNetworkSegmentController) { getControlElementMap().handleReceived(); }
/* 117:    */  
/* 118:    */  private void handleReceivedDirty(NetworkSegmentController paramNetworkSegmentController) {
/* 119:119 */    if (!isOnServer())
/* 120:120 */      synchronized (paramNetworkSegmentController.dirtySegmentBuffer.getReceiveBuffer()) {
/* 121:121 */        for (int i = 0; i < paramNetworkSegmentController.dirtySegmentBuffer.getReceiveBuffer().size(); i++) {
/* 122:122 */          q localq = ((RemoteVector3i)paramNetworkSegmentController.dirtySegmentBuffer.getReceiveBuffer().get(i)).getVector(new q());
/* 123:    */          Segment localSegment;
/* 124:124 */          if ((localSegment = getSegmentBuffer().a(localq)) != null) {
/* 125:125 */            assert (localq.equals(localSegment.a));
/* 126:    */            
/* 131:131 */            ((mw)localSegment).a(-1L);
/* 132:132 */            ((Q)getSegmentProvider()).a(localq);
/* 133:    */          } else {
/* 134:134 */            System.err.println("[CLIENT] WARNING: received dirty null segment " + localq + " on " + this);
/* 135:    */          }
/* 136:    */        }
/* 137:137 */        return;
/* 138:    */      }
/* 139:    */  }
/* 140:    */  
/* 141:    */  private void handleReceivedExplosions(NetworkSegmentController paramNetworkSegmentController) {
/* 142:142 */    if (((ct)getState()).a() == getSectorId()) {
/* 143:143 */      for (int i = 0; i < paramNetworkSegmentController.explosions.getReceiveBuffer().size(); i++) {
/* 144:144 */        Vector3f localVector3f = ((RemoteVector3f)paramNetworkSegmentController.explosions.getReceiveBuffer().get(i)).getVector();
/* 145:145 */        ((ct)getState()).a().a().a(localVector3f, 15.0F);
/* 146:    */        Transform localTransform;
/* 147:147 */        (localTransform = new Transform()).setIdentity();
/* 148:148 */        localTransform.origin.set(localVector3f);
/* 149:149 */        xe.a("0022_explosion", localTransform, 10.0F);
/* 150:    */      }
/* 151:    */    }
/* 152:    */  }
/* 153:    */  
/* 158:    */  private void handleReceivedHarvestConnections(NetworkSegmentController paramNetworkSegmentController) {}
/* 159:    */  
/* 163:    */  protected void handleReceivedModifications(NetworkSegmentController paramNetworkSegmentController)
/* 164:    */  {
/* 165:165 */    if (paramNetworkSegmentController.modificationBuffer.getReceiveBuffer().size() > 32) {
/* 166:166 */      System.err.println(getState() + "; " + this + " [WARNING] !!!!! BIG MODIFICATION RECEIVED: " + paramNetworkSegmentController.modificationBuffer.getReceiveBuffer().size());
/* 167:    */    }
/* 168:168 */    synchronized (paramNetworkSegmentController.modificationBuffer.getReceiveBuffer()) {
/* 169:169 */      for (int i = 0; i < paramNetworkSegmentController.modificationBuffer.getReceiveBuffer().size(); i++)
/* 170:    */      {
/* 171:171 */        le localle = (le)((RemoteSegmentPiece)paramNetworkSegmentController.modificationBuffer.getReceiveBuffer().get(i)).get();
/* 172:172 */        assert ((!(getState() instanceof vg)) || (localle != null)) : "Implication: [serverState -> segmentPiece not null] failed. server pieces must not be null";
/* 173:173 */        if (localle != null) {
/* 174:174 */          if ((this instanceof km)) {
/* 175:175 */            ((km)this).a(true);
/* 176:    */          }
/* 177:177 */          localle.a().a(localle);
/* 178:    */        }
/* 179:    */        
/* 181:181 */        if (isOnServer())
/* 182:    */        {
/* 192:192 */          paramNetworkSegmentController.modificationBuffer.add(new RemoteSegmentPiece(localle, this, getNetworkObject()));
/* 193:    */        }
/* 194:    */      }
/* 195:    */      
/* 196:    */      return;
/* 197:    */    }
/* 198:    */  }
/* 199:    */  
/* 201:    */  public void initFromNetworkObject(NetworkObject paramNetworkObject)
/* 202:    */  {
/* 203:203 */    super.initFromNetworkObject(paramNetworkObject);
/* 204:204 */    paramNetworkObject = (NetworkSegmentController)paramNetworkObject;
/* 205:205 */    if (!isOnServer()) {
/* 206:206 */      getMinPos().b(paramNetworkObject.minSize.getVector());
/* 207:207 */      getMaxPos().b(paramNetworkObject.maxSize.getVector());
/* 208:    */      
/* 209:209 */      if (((this instanceof ld)) && ((((ld)this).a() instanceof PowerManagerInterface)))
/* 210:    */      {
/* 211:211 */        ((PowerManagerInterface)((ld)this).a()).getPowerAddOn().setInitialPower(getNetworkObject().initialPower.getLong());
/* 212:    */      }
/* 213:213 */      if (((this instanceof ld)) && ((((ld)this).a() instanceof ShieldContainerInterface)))
/* 214:    */      {
/* 216:216 */        ((ShieldContainerInterface)((ld)this).a()).getShieldManager().setInitialShields(getNetworkObject().initialShields.getLong());
/* 217:    */      }
/* 218:    */    }
/* 219:    */    
/* 222:222 */    setRealName((String)paramNetworkObject.realName.get());
/* 223:223 */    setUniqueIdentifier((String)getNetworkObject().uniqueIdentifier.get());
/* 224:    */  }
/* 225:    */  
/* 231:    */  public boolean isVolatile()
/* 232:    */  {
/* 233:233 */    return false;
/* 234:    */  }
/* 235:    */  
/* 242:    */  public void newNetworkObject() {}
/* 243:    */  
/* 249:    */  public void onCollision(ManifoldPoint paramManifoldPoint, Sendable paramSendable) {}
/* 250:    */  
/* 256:    */  public void cleanUpOnEntityDelete()
/* 257:    */  {
/* 258:258 */    if (!isOnServer()) {
/* 259:    */      kc localkc;
/* 260:260 */      if (((localkc = ((Q)getSegmentProvider()).a()) != null) && (localkc.a() != null)) {
/* 261:261 */        localkc.a().signalDelete.set(Boolean.valueOf(true), true);
/* 262:    */      }
/* 263:    */    }
/* 264:264 */    else if (this.serverSendableSegmentProvider != null) {
/* 265:265 */      this.serverSendableSegmentProvider.markedForPermanentDelete(true);
/* 266:266 */      this.serverSendableSegmentProvider = null;
/* 267:    */    }
/* 268:    */    
/* 270:270 */    super.cleanUpOnEntityDelete();
/* 271:    */  }
/* 272:    */  
/* 279:    */  public void handleMovingInput(xq paramxq, lA paramlA) {}
/* 280:    */  
/* 287:    */  public String toNiceString()
/* 288:    */  {
/* 289:289 */    return null;
/* 290:    */  }
/* 291:    */  
/* 292:    */  public void setNetworkObject(NetworkSegmentController paramNetworkSegmentController) {
/* 293:293 */    this.networkEntity = paramNetworkSegmentController;
/* 294:    */  }
/* 295:    */  
/* 296:    */  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/* 297:    */  {
/* 298:298 */    super.updateFromNetworkObject(paramNetworkObject);
/* 299:299 */    paramNetworkObject = (NetworkSegmentController)paramNetworkObject;
/* 300:    */    
/* 301:301 */    handleReceivedModifications(paramNetworkObject);
/* 302:302 */    handleReceivedDirty(paramNetworkObject);
/* 303:303 */    handleReceivedBlockActivations(paramNetworkObject);
/* 304:    */    
/* 306:306 */    getControlElementMap().handleReceived();
/* 307:307 */    if (!isOnServer())
/* 308:    */    {
/* 310:310 */      if (getSectorId() == ((ct)getState()).a()) {
/* 311:311 */        handleReceivedExplosions(paramNetworkObject);
/* 312:    */      }
/* 313:313 */      paramNetworkObject.minSize.getVector(getMinPos());
/* 314:314 */      paramNetworkObject.maxSize.getVector(getMaxPos());
/* 316:    */    }
/* 317:317 */    else if (this.lastModifierId != paramNetworkObject.lastModifiedPlayerId.getInt()) {
/* 318:318 */      int i = this.lastModifierId;
/* 319:319 */      this.lastModifierId = paramNetworkObject.lastModifiedPlayerId.getInt();
/* 320:320 */      System.err.println("[SERVER][SEGMENTCONTROLLER] LAST MODIFIER CHANGED TO PID: " + this.lastModifierId + " (from " + i + ")");
/* 321:321 */      this.lastModifierChanged = true;
/* 322:    */    }
/* 323:    */    
/* 324:324 */    setRealName((String)paramNetworkObject.realName.get());
/* 325:    */    
/* 326:326 */    getDockingController().b(paramNetworkObject);
/* 327:    */  }
/* 328:    */  
/* 330:    */  public void updateLocal(xq paramxq)
/* 331:    */  {
/* 332:    */    Object localObject2;
/* 333:333 */    if (this.first) {
/* 334:334 */      if (!isOnServer()) {
/* 335:335 */        long l1 = System.currentTimeMillis();
/* 336:336 */        (
/* 337:337 */          localObject2 = new kc(getState())).initialize();
/* 338:338 */        ((kc)localObject2).a(this);
/* 339:339 */        ((kc)localObject2).setId(getId());
/* 340:340 */        ((Q)getSegmentProvider()).a((kc)localObject2);
/* 341:341 */        ((x)getState().getController()).a().addNewSynchronizedObjectQueued((Sendable)localObject2);
/* 342:    */        
/* 343:    */        long l4;
/* 344:344 */        if ((l4 = System.currentTimeMillis() - l1) > 5L) {
/* 345:345 */          System.err.println("[SENSEGMENTCONTROLLER][CLIENT] WARNING: initializing segmentprovider of " + this + " took " + l4 + " ms");
/* 346:    */        }
/* 347:    */      }
/* 348:348 */      this.first = false;
/* 349:    */    }
/* 350:350 */    if ((isOnServer()) && (this.lastModifierChanged) && (this.lastModifierId != 0))
/* 351:    */    {
/* 352:    */      try {
/* 353:353 */        locallE = (lE)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(this.lastModifierId);
/* 354:354 */        setLastModifier(locallE.getUniqueIdentifier());
/* 355:355 */        System.err.println("[SERVER][SENSEGMENTCONTROLLER] LAST MODIFIER CHANGED TO " + locallE);
/* 356:356 */      } catch (Exception localException2) { lE locallE = null;
/* 357:    */        
/* 358:358 */        localException2.printStackTrace();
/* 359:    */      }
/* 360:    */      
/* 362:360 */      this.lastModifierChanged = false;
/* 363:    */    }
/* 364:    */    try
/* 365:    */    {
/* 366:364 */      if (!getBlockActivationBuffer().isEmpty()) {
/* 367:365 */        assert (isOnServer());
/* 368:366 */        synchronized (getBlockActivationBuffer()) {
/* 369:367 */          while (!getBlockActivationBuffer().isEmpty()) {
/* 370:368 */            s locals = (s)getBlockActivationBuffer().dequeue();
/* 371:369 */            localObject2 = new q(locals.a, locals.b, locals.c);
/* 372:370 */            le localle = getSegmentBuffer().a((q)localObject2, true);
/* 373:    */            
/* 376:    */            int i;
/* 377:    */            
/* 379:    */            boolean bool;
/* 380:    */            
/* 382:380 */            if ((i = Math.abs(locals.d) < 2 ? 1 : 0) == 0)
/* 383:    */            {
/* 384:382 */              System.err.println("[SERVER] NOT DELEGATING REQUEST " + localObject2 + " act(" + locals + ")");
/* 385:383 */              bool = locals.d != -2;
/* 386:    */            } else {
/* 387:385 */              System.err.println("[SERVER] DELEGATING REQUEST " + localObject2 + " act(" + bool + ")");
/* 388:    */              
/* 389:387 */              bool = bool.d != 0;
/* 390:    */            }
/* 391:    */            
/* 392:390 */            if ((localle.a() == 56) && ((localle.a().a() instanceof jA))) {
/* 393:391 */              System.err.println("[SERVER] NOT ACTIVATING GRAVITY BLOCK ON PLANET" + bool);
/* 394:    */            }
/* 395:    */            else
/* 396:    */            {
/* 397:395 */              localle.a(bool);
/* 398:396 */              System.err.println("[SERVER] ACTIVATING BLOCK " + bool);
/* 399:397 */              localle.a().a().applySegmentData(localle.a(new o()), localle.a());
/* 400:    */              
/* 401:399 */              ((mw)localle.a()).a(System.currentTimeMillis());
/* 402:    */              
/* 403:401 */              getNetworkObject().modificationBuffer.add(new RemoteSegmentPiece(localle, this, getNetworkObject()));
/* 404:    */              
/* 405:403 */              if ((i != 0) && ((this instanceof ld)))
/* 406:    */              {
/* 407:405 */                ((ld)this).a().handleBlockActivate(localle, bool); }
/* 408:    */            }
/* 409:    */          }
/* 410:    */        }
/* 411:    */      }
/* 412:410 */      long l2 = System.currentTimeMillis();
/* 413:411 */      getControlElementMap().updateLocal(paramxq);
/* 414:    */      long l3;
/* 415:413 */      if ((l3 = System.currentTimeMillis() - l2) > 20L) {
/* 416:414 */        System.err.println("[SENSEGMENTCONTROLLER][" + getState() + "] WARNING: getControlElementMap().updateLocal(timer) of " + this + " took " + l3 + " ms");
/* 417:    */      }
/* 418:    */    }
/* 419:    */    catch (Exception localException1)
/* 420:    */    {
/* 421:419 */      if (!isOnServer()) {
/* 422:420 */        localException1.printStackTrace();
/* 423:421 */        throw new ErrorDialogException("CLIENT EXCEPTION: " + localException1.getClass().toString() + ": " + localException1.getMessage());
/* 424:    */      }
/* 425:423 */      System.err.println("SERVER EXCEPTION IN SENDABLESEGMENT CONTROLLER");
/* 426:424 */      localException1.printStackTrace();
/* 427:    */    }
/* 428:    */    
/* 431:429 */    super.updateLocal(paramxq);
/* 432:    */  }
/* 433:    */  
/* 440:    */  public void updateToFullNetworkObject()
/* 441:    */  {
/* 442:440 */    super.updateToFullNetworkObject();
/* 443:    */    
/* 445:443 */    assert (getUniqueIdentifier() != null);
/* 446:444 */    getNetworkObject().uniqueIdentifier.set(getUniqueIdentifier());
/* 447:445 */    Object localObject; if (((this instanceof ld)) && ((((ld)this).a() instanceof PowerManagerInterface))) {
/* 448:446 */      localObject = ((PowerManagerInterface)((ld)this).a()).getPowerAddOn();
/* 449:447 */      getNetworkObject().initialPower.set(((PowerAddOn)localObject).getInitialPower());
/* 450:    */    }
/* 451:449 */    if (((this instanceof ld)) && ((((ld)this).a() instanceof ShieldContainerInterface))) {
/* 452:450 */      localObject = ((ShieldContainerInterface)((ld)this).a()).getShieldManager();
/* 453:451 */      getNetworkObject().initialShields.set(((ShieldCollectionManager)localObject).getInitialShields());
/* 454:    */    }
/* 455:    */    
/* 458:456 */    updateToNetworkObject();
/* 459:    */  }
/* 460:    */  
/* 474:    */  public void updateToNetworkObject()
/* 475:    */  {
/* 476:474 */    super.updateToNetworkObject();
/* 477:    */    
/* 478:476 */    assert (getMinPos() != null);
/* 479:477 */    if (isOnServer()) {
/* 480:478 */      getNetworkObject().minSize.set(getMinPos());
/* 481:479 */      getNetworkObject().maxSize.set(getMaxPos());
/* 482:    */    }
/* 483:481 */    getNetworkObject().realName.set(getRealName());
/* 484:    */  }
/* 485:    */  
/* 489:    */  public void writeAllBufferedSegmentsToDatabase()
/* 490:    */  {
/* 491:489 */    long l1 = System.currentTimeMillis();
/* 492:490 */    synchronized (getSegmentProvider().a)
/* 493:    */    {
/* 494:492 */      getSegmentBuffer().a(new kb(this), true);
/* 495:    */    }
/* 496:    */    
/* 504:    */    long l2;
/* 505:    */    
/* 512:510 */    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
/* 513:511 */      System.err.println("[SENDABLESEGMENTVONTROLLER][WRITE] WARNING: segment writing of " + this + " on " + getState() + " took: " + l2 + " ms");
/* 514:    */    }
/* 515:    */  }
/* 516:    */  
/* 517:    */  public void setServerSendableSegmentController(kc paramkc)
/* 518:    */  {
/* 519:517 */    this.serverSendableSegmentProvider = paramkc;
/* 520:    */  }
/* 521:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ka
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */