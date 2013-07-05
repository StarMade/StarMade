/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ManagerContainer;
/*     */ import org.schema.game.common.data.element.ControlElementMap;
/*     */ import org.schema.game.network.objects.NetworkSegmentProvider;
/*     */ import org.schema.game.network.objects.remote.RemoteControlStructure;
/*     */ import org.schema.game.network.objects.remote.RemoteControlStructureBuffer;
/*     */ import org.schema.game.network.objects.remote.RemoteInventory;
/*     */ import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
/*     */ import org.schema.game.network.objects.remote.RemoteSegmentRemoteObj;
/*     */ import org.schema.game.network.objects.remote.RemoteSegmentSignature;
/*     */ import org.schema.game.server.controller.GameServerController;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector3i;
/*     */ import org.schema.schine.network.server.ServerStateInterface;
/*     */ 
/*     */ public class kc
/*     */   implements Sendable
/*     */ {
/*     */   private ka jdField_a_of_type_Ka;
/*     */   private StateInterface jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*     */   private final boolean jdField_a_of_type_Boolean;
/*     */   private NetworkSegmentProvider jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider;
/*  39 */   private boolean b = false;
/*     */ 
/*  43 */   private int jdField_a_of_type_Int = -123123;
/*     */   private boolean c;
/*     */   private boolean d;
/*  48 */   private boolean e = true;
/*     */ 
/*     */   public kc(StateInterface paramStateInterface) {
/*  51 */     this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
/*  52 */     this.jdField_a_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
/*     */   }
/*     */ 
/*     */   public void cleanUpOnEntityDelete()
/*     */   {
/*     */   }
/*     */ 
/*     */   public int getId()
/*     */   {
/*  61 */     return this.jdField_a_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final NetworkSegmentProvider a()
/*     */   {
/*  66 */     return this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider;
/*     */   }
/*     */ 
/*     */   public final ka a()
/*     */   {
/*  73 */     if (this.jdField_a_of_type_Ka == null) {
/*  74 */       this.jdField_a_of_type_Ka = ((ka)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().get(getId()));
/*     */     }
/*  76 */     return this.jdField_a_of_type_Ka;
/*     */   }
/*     */ 
/*     */   public StateInterface getState() {
/*  80 */     return this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*     */   }
/*     */   private void a(NetworkSegmentProvider paramNetworkSegmentProvider) {
/*  83 */     synchronized (paramNetworkSegmentProvider.signatureBuffer.getReceiveBuffer()) {
/*  84 */       if (!paramNetworkSegmentProvider.signatureBuffer.getReceiveBuffer().isEmpty())
/*     */       {
/*  87 */         for (int i = 0; i < paramNetworkSegmentProvider.signatureBuffer.getReceiveBuffer().size(); i++)
/*     */         {
/*  89 */           lf locallf = (lf)((RemoteSegmentSignature)paramNetworkSegmentProvider.signatureBuffer.getReceiveBuffer().get(i)).get();
/*     */ 
/*  91 */           synchronized (
/*  92 */             localObjectOpenHashSet = ((Q)a().getSegmentProvider()).b())
/*     */           {
/*  93 */             ObjectOpenHashSet localObjectOpenHashSet;
/*  93 */             if ((!f) && (locallf.jdField_a_of_type_Q == null)) throw new AssertionError();
/*  94 */             localObjectOpenHashSet.add(locallf);
/*     */           }
/*     */         }
/*     */       }
/*  98 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void initFromNetworkObject(NetworkObject paramNetworkObject)
/*     */   {
/* 105 */     setId(((Integer)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.id.get()).intValue());
/*     */   }
/*     */ 
/*     */   public void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 115 */     return (this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider != null) && (((Boolean)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.connectionReady.get()).booleanValue());
/*     */   }
/*     */ 
/*     */   public boolean isMarkedForDeleteVolatile()
/*     */   {
/* 120 */     return this.c;
/*     */   }
/*     */ 
/*     */   public boolean isMarkedForDeleteVolatileSent()
/*     */   {
/* 125 */     return this.d;
/*     */   }
/*     */ 
/*     */   public boolean isOnServer()
/*     */   {
/* 130 */     return this.jdField_a_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public void newNetworkObject()
/*     */   {
/* 136 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider = new NetworkSegmentProvider(this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface, this);
/* 137 */     if (((this.jdField_a_of_type_Ka instanceof ld)) && ((((ld)this.jdField_a_of_type_Ka).a() instanceof mh))) {
/* 138 */       this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.invetoryBuffer = new RemoteInventoryBuffer(((ld)this.jdField_a_of_type_Ka).a(), this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider); return;
/*     */     }
/* 140 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.invetoryBuffer = new RemoteInventoryBuffer(null, this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 145 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.connectionReady.set(Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   public void setId(int paramInt)
/*     */   {
/* 150 */     this.jdField_a_of_type_Int = paramInt;
/*     */   }
/*     */ 
/*     */   public void setMarkedForDeleteVolatile(boolean paramBoolean)
/*     */   {
/* 155 */     this.c = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setMarkedForDeleteVolatileSent(boolean paramBoolean)
/*     */   {
/* 161 */     this.d = paramBoolean;
/*     */   }
/*     */ 
/*     */   public final void a(ka paramka)
/*     */   {
/* 169 */     this.jdField_a_of_type_Ka = paramka;
/*     */   }
/*     */ 
/*     */   public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/*     */   {
/* 176 */     if (a() == null)
/*     */     {
/* 177 */       System.err.println("[SendableSegmentProvider] no longer updating: missing segment controller: " + getId() + ": " + getState());
/* 178 */       return;
/*     */     }
/*     */ 
/* 181 */     a((NetworkSegmentProvider)paramNetworkObject);
/* 182 */     setId(((Integer)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.id.get()).intValue());
/*     */ 
/* 184 */     if (isOnServer())
/*     */     {
/*     */       kc localkc;
/*     */       NetworkSegmentProvider localNetworkSegmentProvider;
/*     */       mw localmw2;
/* 185 */       for (int i = 0; i < ((NetworkSegmentProvider)paramNetworkObject).signatureRequestBuffer.getReceiveBuffer().size(); i++) {
/* 186 */         q localq = ((RemoteVector3i)((NetworkSegmentProvider)paramNetworkObject).signatureRequestBuffer.getReceiveBuffer().get(i)).getVector(); localkc = this;
/*     */         try
/*     */         {
/* 186 */           mw localmw1;
/* 186 */           if ((localmw1 = (mw)localkc.a().getSegmentFromCache(localq.jdField_a_of_type_Int, localq.b, localq.c)) != null) { localNetworkSegmentProvider = localkc.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider; System.currentTimeMillis(); mw.d(); synchronized (localNetworkSegmentProvider) { localNetworkSegmentProvider.signatureBuffer.add(new RemoteSegmentSignature(new lf(new q(localmw1.jdField_a_of_type_Q), localmw1.a(), localmw1.a().getId(), localmw1.g()), localNetworkSegmentProvider)); }  } localNetworkSegmentProvider = localkc.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider; ((vg)localkc.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().b(localkc.a(), new q((q)???), localNetworkSegmentProvider); } catch (Exception localException1) { localmw2 = null; localException1.printStackTrace(); System.err.println("[SendableSegmentProvider] Exception catched for ID: " + localkc.a() + "; if null, the segmentcontroller has probably been removed (id for both: " + localkc.getId() + ")"); }
/*     */       }
/* 188 */       for (i = 0; i < ((NetworkSegmentProvider)paramNetworkObject).segmentRequestBuffer.getReceiveBuffer().size(); i++) {
/* 189 */         ??? = ((RemoteVector3i)((NetworkSegmentProvider)paramNetworkObject).segmentRequestBuffer.getReceiveBuffer().get(i)).getVector(); localkc = this;
/*     */         try { if ((localmw2 = (mw)localkc.a().getSegmentFromCache(((q)???).jdField_a_of_type_Int, ((q)???).b, ((q)???).c)) != null) { localNetworkSegmentProvider = localkc.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider; ( = new lf(new q(localmw2.jdField_a_of_type_Q), localmw2.a(), localmw2.a().getId(), localmw2.g())).jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = localkc.a(); synchronized (localNetworkSegmentProvider) { localNetworkSegmentProvider.segmentBuffer.add(new RemoteSegmentRemoteObj((lf)???, localNetworkSegmentProvider)); }  } localNetworkSegmentProvider = localObject1.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider; ((vg)localObject1.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().a(localObject1.a(), new q((q)???), localNetworkSegmentProvider); } catch (Exception localException2) { ??? = null; localException2.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/* 193 */     if ((isOnServer()) && (!this.b) && (((Boolean)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.requestedInitialControlMap.get()).booleanValue())) {
/* 194 */       this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.initialControlMap.add(new RemoteControlStructure(this, isOnServer()));
/* 195 */       this.b = true;
/*     */     }
/*     */ 
/* 199 */     if ((!isOnServer()) && ((this.jdField_a_of_type_Ka instanceof ld)) && ((((ld)this.jdField_a_of_type_Ka).a() instanceof mh))) {
/* 200 */       ManagerContainer localManagerContainer = ((ld)this.jdField_a_of_type_Ka).a();
/* 201 */       if (this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.invetoryBuffer.getReceiveBuffer().size() > 0) {
/* 202 */         System.err.println("[CLIENT] RECEIVED INITIAL INVETORY LIST FOR " + this.jdField_a_of_type_Ka);
/* 203 */         localManagerContainer.handleInventoryFromNT(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.invetoryBuffer);
/*     */       }
/*     */     }
/*     */ 
/* 207 */     if ((isOnServer()) && (((Boolean)((NetworkSegmentProvider)paramNetworkObject).signalDelete.get()).booleanValue()))
/* 208 */       setMarkedForDeleteVolatile(true);
/*     */   }
/*     */ 
/*     */   public void updateLocal(xq paramxq)
/*     */   {
/* 271 */     if ((a()) && (this.e))
/*     */     {
/* 274 */       synchronized ((
/* 274 */         paramxq = ((ld)this.jdField_a_of_type_Ka).a())
/* 274 */         .getInventories()) {
/* 275 */         for (mf localmf : paramxq.getInventories().values()) {
/* 276 */           this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.invetoryBuffer.add(new RemoteInventory(localmf, paramxq, true, this.jdField_a_of_type_Boolean));
/*     */         }
/*     */       }
/* 279 */       this.e = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void b() {
/* 284 */     if (a()) {
/* 285 */       this.e = true;
/* 286 */       if (isOnServer())
/* 287 */         ((vg)getState()).a(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateToFullNetworkObject()
/*     */   {
/* 294 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.id.set(Integer.valueOf(getId()));
/* 295 */     if (isOnServer())
/* 296 */       this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.connectionReady.set(Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   public void updateToNetworkObject()
/*     */   {
/* 302 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.id.set(Integer.valueOf(getId()));
/* 303 */     if (isOnServer())
/* 304 */       this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.connectionReady.set(Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 309 */     if (((this.jdField_a_of_type_Ka instanceof ld)) && ((((ld)this.jdField_a_of_type_Ka).a() instanceof mh)))
/*     */     {
/* 311 */       ((x)getState().getController()).a(this.jdField_a_of_type_Ka.getId());
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/* 317 */     a().getControlElementMap().setFlagRequested(true);
/* 318 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.requestedInitialControlMap.forceClientUpdates();
/* 319 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.requestedInitialControlMap.set(Boolean.valueOf(true), true);
/*     */   }
/*     */ 
/*     */   public void destroyPersistent()
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean isMarkedForPermanentDelete()
/*     */   {
/* 328 */     return false;
/*     */   }
/*     */ 
/*     */   public void markedForPermanentDelete(boolean paramBoolean)
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean isUpdatable()
/*     */   {
/* 337 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kc
 * JD-Core Version:    0.6.2
 */