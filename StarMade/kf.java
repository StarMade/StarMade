/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.common.data.element.ship.ShipElement;
/*     */ import org.schema.game.common.data.element.terrain.MineralElement;
/*     */ import org.schema.game.common.data.element.terrain.TerrainElement;
/*     */ import org.schema.game.common.data.world.Universe;
/*     */ import org.schema.game.network.objects.NetworkShop;
/*     */ import org.schema.game.network.objects.remote.RemoteInventoryMultMod;
/*     */ import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.remote.RemoteIntArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/*     */ import org.schema.schine.network.server.ServerMessage;
/*     */ 
/*     */ public class kf extends EditableSendableSegmentController
/*     */   implements Aj, mh
/*     */ {
/*  62 */   private static long jdField_a_of_type_Long = 300000L;
/*     */   private mn jdField_a_of_type_Mn;
/*  66 */   private final HashMap jdField_a_of_type_JavaUtilHashMap = new HashMap();
/*     */ 
/*  68 */   private final Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  69 */   private final Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  70 */   private final Vector3f jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/*  71 */   private final Vector3f jdField_d_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */   private long jdField_b_of_type_Long;
/*     */   private long jdField_c_of_type_Long;
/*     */   private long jdField_d_of_type_Long;
/*     */   private long e;
/* 417 */   private Int2IntOpenHashMap jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntOpenHashMap = new Int2IntOpenHashMap();
/*     */ 
/*     */   public kf(StateInterface paramStateInterface)
/*     */   {
/*  81 */     super(paramStateInterface);
/*     */   }
/*     */ 
/*     */   public void cleanUpOnEntityDelete()
/*     */   {
/* 157 */     super.cleanUpOnEntityDelete();
/* 158 */     synchronized (getState().getLocalAndRemoteObjectContainer().getLocalObjects()) {
/* 159 */       for (Iterator localIterator = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext(); )
/*     */       {
/*     */         Sendable localSendable;
/* 160 */         if (((
/* 160 */           localSendable = (Sendable)localIterator.next()) instanceof kh))
/*     */         {
/* 161 */           ((kh)localSendable).a().remove(this);
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a() {
/* 169 */     int i = Universe.getRandom().nextInt(5) == 0 ? 1 : 0;
/*     */     short[] arrayOfShort;
/* 170 */     int j = (arrayOfShort = ElementKeyMap.typeList()).length; for (int k = 0; k < j; k++)
/*     */     {
/*     */       Short localShort;
/* 171 */       ElementInformation localElementInformation = ElementKeyMap.getInfo((
/* 171 */         localShort = Short.valueOf(arrayOfShort[k]))
/* 171 */         .shortValue());
/* 172 */       if (ShipElement.class.isAssignableFrom(localElementInformation.getType())) {
/* 173 */         if (Universe.getRandom().nextInt(10) != 0)
/*     */         {
/* 176 */           this.jdField_a_of_type_Mn.b(localShort.shortValue(), 100 + Universe.getRandom().nextInt(1000));
/*     */         }
/* 178 */       } else if (TerrainElement.class.isAssignableFrom(localElementInformation.getType())) {
/* 179 */         if (MineralElement.class.isAssignableFrom(localElementInformation.getType())) {
/* 180 */           if (i != 0)
/* 181 */             this.jdField_a_of_type_Mn.b(localShort.shortValue(), 2000 + Universe.getRandom().nextInt(1000));
/* 182 */           else if (Universe.getRandom().nextInt(3) == 0) {
/* 183 */             if (Universe.getRandom().nextInt(10) == 0)
/* 184 */               this.jdField_a_of_type_Mn.b(localShort.shortValue(), 500 + Universe.getRandom().nextInt(500));
/*     */             else
/* 186 */               this.jdField_a_of_type_Mn.b(localShort.shortValue(), Universe.getRandom().nextInt(10));
/*     */           }
/*     */         }
/*     */         else
/* 190 */           this.jdField_a_of_type_Mn.b(localShort.shortValue(), Universe.getRandom().nextInt(1000));
/*     */       }
/* 192 */       else if (ki.class.isAssignableFrom(localElementInformation.getType())) {
/* 193 */         this.jdField_a_of_type_Mn.b(localShort.shortValue(), 100 + Universe.getRandom().nextInt(500));
/*     */       }
/*     */       else
/* 196 */         this.jdField_a_of_type_Mn.b(localShort.shortValue(), Universe.getRandom().nextInt(100));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void fromTagStructure(Ad paramAd)
/*     */   {
/* 251 */     if (paramAd.a().equals("ShopSpaceStation0")) {
/* 252 */       paramAd = (Ad[])paramAd.a();
/* 253 */       if ((!jdField_a_of_type_Boolean) && (!"inventory".equals(paramAd[1].a()))) throw new AssertionError();
/* 254 */       this.jdField_a_of_type_Mn.fromTagStructure(paramAd[1]);
/* 255 */       super.fromTagStructure(paramAd[0]);
/* 256 */       return;
/* 257 */     }super.fromTagStructure(paramAd);
/*     */   }
/*     */ 
/*     */   public mf getInventory(q paramq)
/*     */   {
/* 263 */     return this.jdField_a_of_type_Mn;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 268 */     return null;
/*     */   }
/*     */ 
/*     */   public final int a(ElementInformation paramElementInformation, int paramInt)
/*     */   {
/* 275 */     if (this.jdField_a_of_type_JavaUtilHashMap.containsKey(Short.valueOf(paramElementInformation.getId()))) {
/* 276 */       long l = Math.abs(paramInt) * ((Integer)this.jdField_a_of_type_JavaUtilHashMap.get(Short.valueOf(paramElementInformation.getId()))).intValue();
/* 277 */       return (int)Math.min(2147483647L, l);
/*     */     }
/* 279 */     System.err.println("Shop Exception: " + this + "; " + getState() + ": price not found for " + paramElementInformation.getId() + ": " + this.jdField_a_of_type_JavaUtilHashMap);
/* 280 */     return 0;
/*     */   }
/*     */ 
/*     */   public final mn a()
/*     */   {
/* 287 */     return this.jdField_a_of_type_Mn;
/*     */   }
/*     */ 
/*     */   public void handleExplosion(Transform paramTransform, float paramFloat1, float paramFloat2, lb paramlb, byte paramByte)
/*     */   {
/* 297 */     super.handleExplosion(paramTransform, paramFloat1, paramFloat2, paramlb, paramByte);
/*     */ 
/* 299 */     if (!isOnServer()) {
/* 300 */       if ((paramlb != null) && ((paramlb instanceof cw)) && (((cw)paramlb).a().contains(((ct)getState()).a())))
/*     */       {
/* 302 */         ((ct)getState()).a().b("WARNING:\nAttacking a shop is not a good idea!\n");
/*     */       }
/*     */     }
/* 305 */     else if ((paramlb != null) && ((paramlb instanceof Sendable)))
/* 306 */       a((Sendable)paramlb);
/*     */   }
/*     */ 
/*     */   private void a(Sendable paramSendable)
/*     */   {
/* 312 */     if ((paramSendable != null) && ((paramSendable instanceof cw)) && (((cw)paramSendable).a().size() > 0))
/*     */     {
/* 314 */       if (System.currentTimeMillis() - this.jdField_d_of_type_Long > 2000L)
/*     */       {
/* 316 */         for (lE locallE : ((cw)paramSendable).a()) {
/* 317 */           int i = 0;
/*     */ 
/* 319 */           if (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntOpenHashMap.containsKey(locallE.getId())) {
/* 320 */             i = this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntOpenHashMap.get(locallE.getId());
/*     */           }
/* 322 */           i++;
/* 323 */           if (i < 3) {
/* 324 */             System.err.println("STRIKES: " + i);
/* 325 */             if (i <= 1) {
/* 326 */               locallE.a(new ServerMessage("####### Transmission Start\n\nCease fire immediately!\n\n####### Transmission End", 2, locallE.getId()));
/*     */             }
/*     */             else
/*     */             {
/* 330 */               locallE.a(new ServerMessage("####### Transmission Start\n\nCease fire immediately!\nThis is the last warning!\n\n####### Transmission End", 3, locallE.getId()));
/*     */             }
/*     */ 
/* 335 */             this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntOpenHashMap.put(locallE.getId(), i);
/*     */           }
/* 338 */           else if (System.currentTimeMillis() - this.e > 40000L) {
/* 339 */             locallE.a(new ServerMessage("####### Transmission Start\n\nFleet dispatched!\n\n####### Transmission End", 3, locallE.getId()));
/*     */ 
/* 342 */             this.e = System.currentTimeMillis();
/*     */ 
/* 345 */             ((vg)getState()).a().a((mF)paramSendable);
/*     */           }
/*     */           else {
/* 348 */             switch (Universe.getRandom().nextInt(7)) {
/*     */             case 0:
/* 350 */               locallE.a(new ServerMessage("####### Transmission Start\n\nThe Fleet is on the way to kill you!\n\n####### Transmission End", 3, locallE.getId()));
/*     */ 
/* 353 */               break;
/*     */             case 1:
/* 354 */               locallE.a(new ServerMessage("####### Transmission Start\n\nThe Traiding Guild will\neliminate you!\n\n####### Transmission End", 3, locallE.getId()));
/*     */ 
/* 358 */               break;
/*     */             case 2:
/* 359 */               locallE.a(new ServerMessage("####### Transmission Start\n\nAre you suicidal?!\n\n####### Transmission End", 3, locallE.getId()));
/*     */ 
/* 362 */               break;
/*     */             case 3:
/* 363 */               locallE.a(new ServerMessage("####### Transmission Start\n\nIn space, nobody can hear you scream!\n\n####### Transmission End", 3, locallE.getId()));
/*     */ 
/* 366 */               break;
/*     */             case 4:
/* 367 */               locallE.a(new ServerMessage("####### Transmission Start\n\nFreeze!\n\n####### Transmission End", 3, locallE.getId()));
/*     */ 
/* 370 */               break;
/*     */             case 5:
/* 371 */               locallE.a(new ServerMessage("####### Transmission Start\n\nPlease pull over, sir!\n\n####### Transmission End", 3, locallE.getId()));
/*     */ 
/* 374 */               break;
/*     */             case 6:
/* 375 */               locallE.a(new ServerMessage("####### Transmission Start\n\nNo soup for YOU!\n\n####### Transmission End", 3, locallE.getId()));
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 387 */         this.jdField_d_of_type_Long = System.currentTimeMillis();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, lb paramlb, float paramFloat)
/*     */   {
/* 398 */     if (!isOnServer())
/*     */     {
/* 404 */       ((ct)getState())
/* 405 */         .a().a().a(paramClosestRayResultCallback);
/* 406 */       (
/* 407 */         paramlb = new Transform())
/* 407 */         .setIdentity();
/* 408 */       paramlb.origin.set(paramClosestRayResultCallback.hitPointWorld);
/* 409 */       xe.a("0022_spaceship enemy - hit no explosion metallic impact on enemy ship", paramlb, 5.0F);
/* 410 */       return;
/* 411 */     }if ((paramlb != null) && ((paramlb instanceof Sendable)))
/* 412 */       a((Sendable)paramlb);
/*     */   }
/*     */ 
/*     */   public void initFromNetworkObject(NetworkObject paramNetworkObject)
/*     */   {
/* 425 */     super.initFromNetworkObject(paramNetworkObject);
/*     */ 
/* 427 */     if (!isOnServer()) {
/* 428 */       b();
/* 429 */       paramNetworkObject = (NetworkShop)paramNetworkObject;
/* 430 */       this.jdField_a_of_type_Mn.a(paramNetworkObject);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void initialize()
/*     */   {
/* 439 */     super.initialize();
/* 440 */     this.jdField_a_of_type_Mn = new mn(this, new q(0, 0, 0));
/*     */ 
/* 442 */     setMass(0.0F);
/*     */   }
/*     */ 
/*     */   private boolean a(kh paramkh)
/*     */   {
/* 450 */     if (paramkh.getSectorId() != getSectorId()) {
/* 451 */       return false;
/*     */     }
/* 453 */     paramkh.getTransformedAABB(this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_d_of_type_JavaxVecmathVector3f, 0.0F, new Vector3f(), new Vector3f());
/* 454 */     if (getSegmentBuffer().b()) {
/* 455 */       this.jdField_d_of_type_JavaxVecmathVector3f.sub(this.jdField_c_of_type_JavaxVecmathVector3f);
/* 456 */       this.jdField_d_of_type_JavaxVecmathVector3f.scale(0.5F);
/* 457 */       this.jdField_c_of_type_JavaxVecmathVector3f.add(this.jdField_d_of_type_JavaxVecmathVector3f);
/* 458 */       this.jdField_c_of_type_JavaxVecmathVector3f.sub(getWorldTransform().origin);
/*     */ 
/* 460 */       return this.jdField_c_of_type_JavaxVecmathVector3f.length() < 64.0F;
/*     */     }
/* 462 */     paramkh = new Vector3f(getSegmentBuffer().a().jdField_a_of_type_JavaxVecmathVector3f);
/* 463 */     Vector3f localVector3f = new Vector3f(getSegmentBuffer().a().jdField_b_of_type_JavaxVecmathVector3f);
/*     */ 
/* 465 */     if (!GlUtil.a(paramkh, localVector3f)) {
/* 466 */       getSegmentBuffer().a();
/* 467 */       return false;
/*     */     }
/* 469 */     AabbUtil2.transformAabb(paramkh, localVector3f, 64.0F, getWorldTransform(), this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_b_of_type_JavaxVecmathVector3f);
/*     */ 
/* 472 */     return AabbUtil2.testAabbAgainstAabb2(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_b_of_type_JavaxVecmathVector3f, this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_d_of_type_JavaxVecmathVector3f);
/*     */   }
/*     */ 
/*     */   public final boolean a(String[] paramArrayOfString, q paramq)
/*     */   {
/* 477 */     return false;
/*     */   }
/*     */ 
/*     */   public void newNetworkObject() {
/* 481 */     setNetworkObject(new NetworkShop(getState(), this));
/*     */   }
/*     */ 
/*     */   protected void onCoreDestroyed(lb paramlb)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void onSectorInactiveClient()
/*     */   {
/* 496 */     super.onSectorInactiveClient();
/* 497 */     synchronized (getState().getLocalAndRemoteObjectContainer().getLocalObjects()) {
/* 498 */       for (Iterator localIterator = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext(); )
/*     */       {
/*     */         Sendable localSendable;
/* 499 */         if (((
/* 499 */           localSendable = (Sendable)localIterator.next()) instanceof kh))
/*     */         {
/* 500 */           if ((a((kh)localSendable)) && (((kh)localSendable).getSectorId() == getSectorId()))
/* 501 */             ((kh)localSendable).a().add(this);
/*     */           else
/* 503 */             ((kh)localSendable).a().remove(this);
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public String printInventories()
/*     */   {
/* 513 */     return this.jdField_a_of_type_Mn.toString();
/*     */   }
/*     */ 
/*     */   private void b()
/*     */   {
/* 518 */     if ((!jdField_a_of_type_Boolean) && (isOnServer())) throw new AssertionError();
/*     */ 
/* 520 */     for (int i = 0; i < ((NetworkShop)super.getNetworkObject()).pricesUpdateBuffer.getReceiveBuffer().size(); i++) {
/* 521 */       RemoteIntArray localRemoteIntArray = (RemoteIntArray)((NetworkShop)super.getNetworkObject()).pricesUpdateBuffer.getReceiveBuffer().get(i);
/* 522 */       this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)localRemoteIntArray.getIntArray()[0]), Integer.valueOf(localRemoteIntArray.getIntArray()[1]));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void sendInventoryModification(int paramInt, q paramq)
/*     */   {
/* 531 */     (
/* 533 */       paramq = new RemoteIntArray(3, (NetworkShop)super.getNetworkObject()))
/* 533 */       .set(0, paramInt);
/* 534 */     if (!this.jdField_a_of_type_Mn.a(paramInt)) {
/* 535 */       paramq.set(1, this.jdField_a_of_type_Mn.a(paramInt));
/* 536 */       paramq.set(2, this.jdField_a_of_type_Mn.a(paramInt));
/*     */     }
/* 538 */     ((NetworkShop)super.getNetworkObject()).getInventoryUpdateBuffer().add(paramq);
/*     */   }
/*     */ 
/*     */   private void c() {
/* 542 */     for (Map.Entry localEntry : this.jdField_a_of_type_JavaUtilHashMap.entrySet())
/*     */     {
/*     */       RemoteIntArray localRemoteIntArray;
/* 543 */       (
/* 544 */         localRemoteIntArray = new RemoteIntArray(2, (NetworkShop)super.getNetworkObject()))
/* 544 */         .set(0, ((Short)localEntry.getKey()).intValue());
/* 545 */       localRemoteIntArray.set(1, ((Integer)localEntry.getValue()).intValue());
/* 546 */       ((NetworkShop)super.getNetworkObject()).pricesUpdateBuffer.add(localRemoteIntArray);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void startCreatorThread()
/*     */   {
/* 560 */     if (getCreatorThread() == null)
/* 561 */       setCreatorThread(new kL(this));
/*     */   }
/*     */ 
/*     */   public String toNiceString()
/*     */   {
/* 568 */     return "Shop (" + getTotalElements() + ")";
/*     */   }
/*     */ 
/*     */   public Ad toTagStructure()
/*     */   {
/* 578 */     Ad localAd = this.jdField_a_of_type_Mn.toTagStructure();
/* 579 */     return new Ad(Af.n, "ShopSpaceStation0", new Ad[] { super.toTagStructure(), localAd, new Ad(Af.a, "fin", null) });
/*     */   }
/*     */ 
/*     */   public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/*     */   {
/* 588 */     super.updateFromNetworkObject(paramNetworkObject);
/* 589 */     paramNetworkObject = (NetworkShop)paramNetworkObject;
/* 590 */     this.jdField_a_of_type_Mn.a(paramNetworkObject);
/*     */ 
/* 592 */     if (!isOnServer())
/* 593 */       b();
/*     */   }
/*     */ 
/*     */   public void updateLocal(xq paramxq)
/*     */   {
/* 602 */     super.updateLocal(paramxq);
/*     */     long l1;
/* 605 */     if ((
/* 605 */       l1 = System.currentTimeMillis()) > 
/* 605 */       this.jdField_b_of_type_Long + 500L) {
/* 606 */       synchronized (getState().getLocalAndRemoteObjectContainer().getLocalObjects()) {
/* 607 */         for (Iterator localIterator = getState().getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values().iterator(); localIterator.hasNext(); )
/*     */         {
/*     */           Sendable localSendable;
/* 608 */           if (((
/* 608 */             localSendable = (Sendable)localIterator.next()) instanceof kh))
/*     */           {
/* 609 */             if ((a((kh)localSendable)) && (((kh)localSendable).getSectorId() == getSectorId()))
/* 610 */               ((kh)localSendable).a().add(this);
/*     */             else {
/* 612 */               ((kh)localSendable).a().remove(this);
/*     */             }
/*     */           }
/*     */         }
/* 616 */         this.jdField_b_of_type_Long = l1;
/*     */       }
/*     */     }
/*     */ 
/* 620 */     if ((isOnServer()) && ((vg.jdField_a_of_type_Boolean) || (l1 > this.jdField_c_of_type_Long + jdField_a_of_type_Long)))
/*     */     {
/* 622 */       long l2 = System.currentTimeMillis();
/* 623 */       d();
/*     */       long l3;
/* 625 */       if ((
/* 625 */         l3 = System.currentTimeMillis() - l2) > 
/* 625 */         3L) {
/* 626 */         System.err.println("[SERVER] updating prices for: " + this + " took " + l3);
/*     */       }
/* 628 */       jdField_a_of_type_Long = ()(300000.0D + Math.random() * 10.0D * 60000.0D);
/* 629 */       this.jdField_c_of_type_Long = l1;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void d() {
/* 634 */     if ((!jdField_a_of_type_Boolean) && (!isOnServer())) throw new AssertionError();
/*     */     short[] arrayOfShort;
/* 636 */     int i = (arrayOfShort = ElementKeyMap.typeList()).length; for (int j = 0; j < i; j++)
/*     */     {
/* 638 */       Object localObject1 = ElementKeyMap.getInfo(Short.valueOf(arrayOfShort[j])
/* 638 */         .shortValue());
/* 639 */       Object localObject2 = localObject1; localObject1 = this; mn.e();
/*     */       int m;
/* 639 */       int k = (m = ((kf)localObject1).jdField_a_of_type_Mn.a(localObject2.getId())) >= 0 ? ((kf)localObject1).jdField_a_of_type_Mn.a(m) : 0; double d1 = Math.max(1.0D, Math.min(10000.0D, k)); double d2 = 1.0D - d1 / 10000.0D; double d3 = Math.max(1.0D, Math.min(10000.0D, k - 1)); double d4 = 1.0D - d3 / 10000.0D; long l1 = ()(1.0D * (Math.min(d2, d4) + Math.abs(d2 - d4) / 2.0D)); long l2 = Math.abs(1) * localObject2.getPrice(); long l3 = Math.abs(1) * (localObject2.getPrice() * l1); this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf(((ElementInformation)localObject1).getId()), Integer.valueOf((int)Math.min(2147483647L, l2 + l3)));
/*     */     }
/* 641 */     c();
/*     */   }
/*     */ 
/*     */   public void updateToFullNetworkObject()
/*     */   {
/* 648 */     this.jdField_a_of_type_Mn.b((NetworkShop)super.getNetworkObject());
/* 649 */     super.updateToFullNetworkObject();
/*     */ 
/* 651 */     d();
/*     */   }
/*     */ 
/*     */   public ml getInventoryNetworkObject()
/*     */   {
/* 657 */     return (NetworkShop)super.getNetworkObject();
/*     */   }
/*     */ 
/*     */   public HashMap getInventories()
/*     */   {
/*     */     HashMap localHashMap;
/* 663 */     (
/* 664 */       localHashMap = new HashMap(1))
/* 664 */       .put(new q(), this.jdField_a_of_type_Mn);
/* 665 */     return localHashMap;
/*     */   }
/*     */   public void getRelationColor(lZ paramlZ, Vector4f paramVector4f, float paramFloat) {
/* 668 */     switch (kg.a[paramlZ.ordinal()]) {
/*     */     case 1:
/* 670 */       paramVector4f.x = (paramFloat + 0.3F);
/* 671 */       paramVector4f.y = 0.0F;
/* 672 */       paramVector4f.z = 0.3F;
/* 673 */       return;
/*     */     case 2:
/* 676 */       paramVector4f.x = 0.5F;
/* 677 */       paramVector4f.y = paramFloat;
/* 678 */       paramVector4f.z = 0.5F;
/* 679 */       return;
/*     */     case 3:
/* 682 */       paramVector4f.x = 1.0F;
/* 683 */       paramVector4f.y = paramFloat;
/* 684 */       paramVector4f.z = 1.0F;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void sendInventoryModification(Collection paramCollection, q paramq)
/*     */   {
/*     */     mf localmf;
/* 693 */     if ((
/* 693 */       localmf = getInventory(paramq)) != null)
/*     */     {
/* 694 */       paramCollection = new mi(paramCollection, localmf, paramq);
/*     */ 
/* 696 */       ((NetworkShop)super.getNetworkObject()).getInventoryMultModBuffer().add(new RemoteInventoryMultMod(paramCollection, (NetworkShop)super.getNetworkObject()));
/* 697 */       return;
/*     */     }try {
/* 699 */       throw new IllegalArgumentException("[INVENTORY] Exception: tried to send inventory " + paramq); } catch (Exception localException) { localException
/* 700 */         .printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected String getSegmentControllerTypeString()
/*     */   {
/* 708 */     return "Shop";
/*     */   }
/*     */ 
/*     */   public final String a()
/*     */   {
/* 713 */     return "0022_ambience loop - shop machinery (loop)";
/*     */   }
/*     */ 
/*     */   public final String b()
/*     */   {
/* 718 */     return "0022_ambience loop - shop machinery (loop)";
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 733 */     return 1.5F;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kf
 * JD-Core Version:    0.6.2
 */