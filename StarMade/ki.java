/*   1:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*   2:    */import java.io.IOException;
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.ArrayList;
/*   5:    */import javax.vecmath.Vector4f;
/*   6:    */import org.schema.game.common.controller.EditableSendableSegmentController;
/*   7:    */import org.schema.game.common.controller.SegmentController;
/*   8:    */import org.schema.game.common.controller.elements.ParticleHandler;
/*   9:    */import org.schema.game.common.controller.elements.SpaceStationManagerContainer;
/*  10:    */import org.schema.game.common.data.element.ElementDocking;
/*  11:    */import org.schema.game.common.data.world.Segment;
/*  12:    */import org.schema.game.common.data.world.Universe;
/*  13:    */import org.schema.game.network.objects.NetworkSpaceStation;
/*  14:    */import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*  15:    */import org.schema.schine.network.StateInterface;
/*  16:    */import org.schema.schine.network.objects.NetworkObject;
/*  17:    */import org.schema.schine.network.objects.Sendable;
/*  18:    */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*  19:    */
/*  48:    */public class ki
/*  49:    */  extends EditableSendableSegmentController
/*  50:    */  implements cw, ld, ParticleHandler, wp
/*  51:    */{
/*  52: 52 */  private final ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  53:    */  
/*  54:    */  private final SpaceStationManagerContainer jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer;
/*  55:    */  private ku jdField_a_of_type_Ku;
/*  56: 56 */  private kk jdField_a_of_type_Kk = kk.jdField_a_of_type_Kk;
/*  57:    */  
/*  58:    */  public ki(StateInterface paramStateInterface)
/*  59:    */  {
/*  60: 60 */    super(paramStateInterface);
/*  61: 61 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer = new SpaceStationManagerContainer(this);
/*  62: 62 */    this.jdField_a_of_type_Ku = new ku(paramStateInterface, this);
/*  63:    */  }
/*  64:    */  
/*  66:    */  public boolean isClientOwnObject()
/*  67:    */  {
/*  68: 68 */    return (!isOnServer()) && (this.jdField_a_of_type_JavaUtilArrayList.contains(((ct)getState()).a()));
/*  69:    */  }
/*  70:    */  
/*  73:    */  public void fromTagStructure(Ah paramAh)
/*  74:    */  {
/*  75: 75 */    if ((!jdField_a_of_type_Boolean) && (!paramAh.a().equals("SpaceStation"))) throw new AssertionError();
/*  76: 76 */    paramAh = (Ah[])paramAh.a();
/*  77:    */    
/*  78: 78 */    super.fromTagStructure(paramAh[1]);
/*  79:    */  }
/*  80:    */  
/*  81:    */  public final kp a() {
/*  82: 82 */    return this.jdField_a_of_type_Ku;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public final ArrayList a() {
/*  86: 86 */    return this.jdField_a_of_type_JavaUtilArrayList;
/*  87:    */  }
/*  88:    */  
/*  89:    */  protected short getCoreType()
/*  90:    */  {
/*  91: 91 */    return 65;
/*  92:    */  }
/*  93:    */  
/*  94:    */  public int getCreatorId() {
/*  95: 95 */    return this.jdField_a_of_type_Kk.ordinal();
/*  96:    */  }
/*  97:    */  
/* 100:    */  public final SpaceStationManagerContainer a()
/* 101:    */  {
/* 102:102 */    return this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer;
/* 103:    */  }
/* 104:    */  
/* 114:    */  public L getParticleController()
/* 115:    */  {
/* 116:116 */    if (!isOnServer()) {
/* 117:117 */      return ((ct)getState()).getParticleController();
/* 118:    */    }
/* 119:119 */    return ((vg)getState()).a().getSector(getSectorId()).a();
/* 120:    */  }
/* 121:    */  
/* 122:    */  public String toString()
/* 123:    */  {
/* 124:124 */    return "SpaceStation[" + getUniqueIdentifier() + "(" + getId() + ")]";
/* 125:    */  }
/* 126:    */  
/* 128:    */  public final SegmentController a()
/* 129:    */  {
/* 130:130 */    return this;
/* 131:    */  }
/* 132:    */  
/* 133:    */  public final void a(xq paramxq, lA paramlA)
/* 134:    */  {
/* 135:135 */    if (((paramlA.a instanceof q)) && 
/* 136:136 */      (getPhysicsDataContainer().isInitialized())) {
/* 137:137 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.handle(paramlA);
/* 138:    */    }
/* 139:    */  }
/* 140:    */  
/* 167:    */  public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, lb paramlb, float paramFloat)
/* 168:    */  {
/* 169:169 */    super.handleHit(paramClosestRayResultCallback, paramlb, paramFloat);
/* 170:    */  }
/* 171:    */  
/* 183:    */  public void initialize()
/* 184:    */  {
/* 185:185 */    super.initialize();
/* 186:    */    
/* 187:187 */    setMass(0.0F);
/* 188:    */  }
/* 189:    */  
/* 192:    */  public final boolean a(String[] paramArrayOfString, q paramq)
/* 193:    */  {
/* 194:194 */    if (((vf)getState()).a().a(getFactionId())) {
/* 195:195 */      paramArrayOfString[0] = "Faction owned station not salvagable\ndestroy faction block first";
/* 196:196 */      return false;
/* 197:    */    }
/* 198:    */    
/* 199:199 */    if ((isHomeBase()) || ((getDockingController().a() != null) && (getDockingController().a().to.a().a().isHomeBaseFor(getFactionId()))))
/* 200:    */    {
/* 201:201 */      paramArrayOfString[0] = "Cannot salvage: home base protected";
/* 202:202 */      return false;
/* 203:    */    }
/* 204:204 */    return true;
/* 205:    */  }
/* 206:    */  
/* 208:    */  public void newNetworkObject()
/* 209:    */  {
/* 210:210 */    setNetworkObject(new NetworkSpaceStation(getState(), this));
/* 211:    */  }
/* 212:    */  
/* 220:    */  public void onAddedElement(short paramShort, byte paramByte1, byte paramByte2, byte paramByte3, int paramInt, Segment paramSegment, boolean paramBoolean)
/* 221:    */  {
/* 222:222 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.onAddedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
/* 223:223 */    super.onAddedElement(paramShort, paramByte1, paramByte2, paramByte3, paramInt, paramSegment, paramBoolean);
/* 224:    */  }
/* 225:    */  
/* 230:    */  public final void a(lE paramlE, Sendable paramSendable, q paramq)
/* 231:    */  {
/* 232:232 */    if ((!isOnServer()) && (((ct)getState()).a() == paramlE))
/* 233:    */    {
/* 235:235 */      if ((paramSendable = (ct)getState()).a() == paramlE)
/* 236:    */      {
/* 238:238 */        paramSendable.a().a().a().a().a().c(true);
/* 239:239 */        System.err.println("Entering space stationc ");
/* 240:    */      }
/* 241:    */    }
/* 242:    */  }
/* 243:    */  
/* 248:    */  protected void onCoreDestroyed(lb paramlb) {}
/* 249:    */  
/* 253:    */  public final void a(lE paramlE, boolean paramBoolean)
/* 254:    */  {
/* 255:255 */    if (!isOnServer())
/* 256:    */    {
/* 258:258 */      if (((paramBoolean = (ct)getState()).a() == paramlE) && (((ct)getState()).a() == paramlE))
/* 259:    */      {
/* 261:261 */        paramBoolean.a().a().a().a().a().c(false);
/* 262:    */      }
/* 263:    */    }
/* 264:    */  }
/* 265:    */  
/* 275:    */  public void onRemovedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, boolean paramBoolean)
/* 276:    */  {
/* 277:277 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.onRemovedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
/* 278:278 */    super.onRemovedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
/* 279:    */  }
/* 280:    */  
/* 281:    */  public void setCreatorId(int paramInt)
/* 282:    */  {
/* 283:283 */    this.jdField_a_of_type_Kk = kk.values()[paramInt];
/* 284:    */  }
/* 285:    */  
/* 287:    */  public void startCreatorThread()
/* 288:    */  {
/* 289:289 */    if (getCreatorThread() == null) {
/* 290:290 */      setCreatorThread(new kM(this, this.jdField_a_of_type_Kk));
/* 291:    */    }
/* 292:    */  }
/* 293:    */  
/* 295:    */  public String toNiceString()
/* 296:    */  {
/* 297:297 */    return getRealName();
/* 298:    */  }
/* 299:    */  
/* 305:    */  public Ah toTagStructure()
/* 306:    */  {
/* 307:307 */    return new Ah(Aj.n, "SpaceStation", new Ah[] { this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.toTagStructure(), super.toTagStructure(), new Ah(Aj.a, null, null) });
/* 308:    */  }
/* 309:    */  
/* 317:    */  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/* 318:    */  {
/* 319:319 */    super.updateFromNetworkObject(paramNetworkObject);
/* 320:320 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.updateFromNetworkObject(paramNetworkObject);
/* 321:    */  }
/* 322:    */  
/* 328:    */  public void updateLocal(xq paramxq)
/* 329:    */  {
/* 330:330 */    super.updateLocal(paramxq);
/* 331:    */    
/* 332:    */    try
/* 333:    */    {
/* 334:334 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.updateLocal(paramxq);
/* 335:    */    } catch (Exception localException) {
/* 336:336 */      (paramxq = 
/* 337:    */      
/* 338:338 */        localException).printStackTrace();throw new ErrorDialogException(paramxq.getMessage());
/* 339:    */    }
/* 340:    */    try
/* 341:    */    {
/* 342:341 */      if ((isOnServer()) && (getTotalElements() <= 0) && (System.currentTimeMillis() - getTimeCreated() > 3000L) && (isEmptyOnServer()))
/* 343:    */      {
/* 346:345 */        System.err.println("[SERVER][SPACESTATION] Empty station: deleting!!!!!!!!!!!!!!!!!!! " + this);
/* 347:346 */        setMarkedForDeleteVolatile(true);
/* 348:    */      }
/* 349:    */      return; } catch (IOException localIOException) { 
/* 350:    */      
/* 353:352 */        localIOException.printStackTrace(); return;
/* 354:    */    } catch (InterruptedException localInterruptedException) {
/* 355:350 */      
/* 356:    */      
/* 357:352 */        localInterruptedException;
/* 358:    */    }
/* 359:    */  }
/* 360:    */  
/* 370:    */  public void updateToFullNetworkObject()
/* 371:    */  {
/* 372:365 */    super.updateToFullNetworkObject();
/* 373:366 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.updateToFullNetworkObject((NetworkSpaceStation)super.getNetworkObject());
/* 374:    */  }
/* 375:    */  
/* 376:    */  public void getRelationColor(lZ paramlZ, Vector4f paramVector4f, float paramFloat) {
/* 377:370 */    switch (kj.a[paramlZ.ordinal()]) {
/* 378:    */    case 1: 
/* 379:372 */      paramVector4f.x = (paramFloat + 0.8F);
/* 380:373 */      paramVector4f.y = (paramFloat + 0.3F);
/* 381:374 */      paramVector4f.z = (paramFloat + 0.1F);
/* 382:375 */      return;
/* 383:    */    
/* 384:    */    case 2: 
/* 385:378 */      paramVector4f.x = (paramFloat + 0.3F);
/* 386:379 */      paramVector4f.y = (paramFloat + 0.8F);
/* 387:380 */      paramVector4f.z = (paramFloat + 0.1F);
/* 388:381 */      return;
/* 389:    */    
/* 390:    */    case 3: 
/* 391:384 */      paramVector4f.x = 0.3F;
/* 392:385 */      paramVector4f.y = (paramFloat + 0.2F);
/* 393:386 */      paramVector4f.z = 0.7F;
/* 394:    */    }
/* 395:    */    
/* 396:    */  }
/* 397:    */  
/* 399:    */  protected String getSegmentControllerTypeString()
/* 400:    */  {
/* 401:394 */    return "Station";
/* 402:    */  }
/* 403:    */  
/* 404:    */  public static void a() {}
/* 405:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ki
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */