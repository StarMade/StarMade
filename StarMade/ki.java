/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ParticleHandler;
/*     */ import org.schema.game.common.controller.elements.SpaceStationManagerContainer;
/*     */ import org.schema.game.common.data.element.ElementDocking;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.Universe;
/*     */ import org.schema.game.network.objects.NetworkSpaceStation;
/*     */ import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*     */ 
/*     */ public class ki extends EditableSendableSegmentController
/*     */   implements cw, ld, ParticleHandler, wp
/*     */ {
/*  51 */   private final ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*     */   private final SpaceStationManagerContainer jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer;
/*     */   private ku jdField_a_of_type_Ku;
/*  55 */   private kk jdField_a_of_type_Kk = kk.jdField_a_of_type_Kk;
/*     */ 
/*  57 */   public ki(StateInterface paramStateInterface) { super(paramStateInterface);
/*  58 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer = new SpaceStationManagerContainer(this);
/*  59 */     this.jdField_a_of_type_Ku = new ku(paramStateInterface, this);
/*     */   }
/*     */ 
/*     */   public boolean isClientOwnObject()
/*     */   {
/*  65 */     return (!isOnServer()) && (this.jdField_a_of_type_JavaUtilArrayList.contains(((ct)getState()).a()));
/*     */   }
/*     */ 
/*     */   public void fromTagStructure(Ad paramAd)
/*     */   {
/*  72 */     if ((!jdField_a_of_type_Boolean) && (!paramAd.a().equals("SpaceStation"))) throw new AssertionError();
/*  73 */     paramAd = (Ad[])paramAd.a();
/*     */ 
/*  75 */     super.fromTagStructure(paramAd[1]);
/*     */   }
/*     */ 
/*     */   public final kp a() {
/*  79 */     return this.jdField_a_of_type_Ku;
/*     */   }
/*     */ 
/*     */   public final ArrayList a() {
/*  83 */     return this.jdField_a_of_type_JavaUtilArrayList;
/*     */   }
/*     */ 
/*     */   protected short getCoreType()
/*     */   {
/*  88 */     return 65;
/*     */   }
/*     */ 
/*     */   public int getCreatorId() {
/*  92 */     return this.jdField_a_of_type_Kk.ordinal();
/*     */   }
/*     */ 
/*     */   public final SpaceStationManagerContainer a()
/*     */   {
/*  99 */     return this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer;
/*     */   }
/*     */ 
/*     */   public L getParticleController()
/*     */   {
/* 113 */     if (!isOnServer()) {
/* 114 */       return ((ct)getState()).getParticleController();
/*     */     }
/* 116 */     return ((vg)getState()).a().getSector(getSectorId()).a();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 121 */     return "SpaceStation[" + getUniqueIdentifier() + "(" + getId() + ")]";
/*     */   }
/*     */ 
/*     */   public final SegmentController a()
/*     */   {
/* 127 */     return this;
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq, lA paramlA)
/*     */   {
/* 132 */     if (((paramlA.a instanceof q)) && 
/* 133 */       (getPhysicsDataContainer().isInitialized()))
/* 134 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.handle(paramlA);
/*     */   }
/*     */ 
/*     */   public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, lb paramlb, float paramFloat)
/*     */   {
/* 166 */     super.handleHit(paramClosestRayResultCallback, paramlb, paramFloat);
/*     */   }
/*     */ 
/*     */   public void initialize()
/*     */   {
/* 182 */     super.initialize();
/*     */ 
/* 184 */     setMass(0.0F);
/*     */   }
/*     */ 
/*     */   public final boolean a(String[] paramArrayOfString, q paramq)
/*     */   {
/* 190 */     if ((isHomeBase()) || ((getDockingController().a() != null) && (getDockingController().a().to.a().a().isHomeBaseFor(getFactionId()))))
/*     */     {
/* 192 */       paramArrayOfString[0] = "Cannot salvage: home base protected";
/* 193 */       return false;
/*     */     }
/* 195 */     return true;
/*     */   }
/*     */ 
/*     */   public void newNetworkObject()
/*     */   {
/* 201 */     setNetworkObject(new NetworkSpaceStation(getState(), this));
/*     */   }
/*     */ 
/*     */   public void onAddedElement(short paramShort, byte paramByte1, byte paramByte2, byte paramByte3, int paramInt, Segment paramSegment, boolean paramBoolean)
/*     */   {
/* 213 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.onAddedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
/* 214 */     super.onAddedElement(paramShort, paramByte1, paramByte2, paramByte3, paramInt, paramSegment, paramBoolean);
/*     */   }
/*     */ 
/*     */   public final void a(lE paramlE, Sendable paramSendable, q paramq)
/*     */   {
/* 223 */     if ((!isOnServer()) && (((ct)getState()).a() == paramlE))
/*     */     {
/* 226 */       if ((
/* 226 */         paramSendable = (ct)getState())
/* 226 */         .a() == paramlE) {
/* 227 */         paramSendable.a().a().a()
/* 229 */           .a().a().c(true);
/* 230 */         System.err.println("Entering space stationc ");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void onCoreDestroyed(lb paramlb)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void a(lE paramlE, boolean paramBoolean)
/*     */   {
/* 246 */     if (!isOnServer())
/*     */     {
/* 249 */       if (((
/* 249 */         paramBoolean = (ct)getState())
/* 249 */         .a() == paramlE) && (((ct)getState()).a() == paramlE))
/* 250 */         paramBoolean.a().a().a()
/* 252 */           .a().a().c(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onRemovedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, boolean paramBoolean)
/*     */   {
/* 268 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.onRemovedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
/* 269 */     super.onRemovedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
/*     */   }
/*     */ 
/*     */   public void setCreatorId(int paramInt)
/*     */   {
/* 274 */     this.jdField_a_of_type_Kk = kk.values()[paramInt];
/*     */   }
/*     */ 
/*     */   public void startCreatorThread()
/*     */   {
/* 280 */     if (getCreatorThread() == null)
/* 281 */       setCreatorThread(new kM(this, this.jdField_a_of_type_Kk));
/*     */   }
/*     */ 
/*     */   public String toNiceString()
/*     */   {
/* 288 */     return getRealName();
/*     */   }
/*     */ 
/*     */   public Ad toTagStructure()
/*     */   {
/* 298 */     return new Ad(Af.n, "SpaceStation", new Ad[] { this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.toTagStructure(), super.toTagStructure(), new Ad(Af.a, null, null) });
/*     */   }
/*     */ 
/*     */   public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/*     */   {
/* 310 */     super.updateFromNetworkObject(paramNetworkObject);
/* 311 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.updateFromNetworkObject(paramNetworkObject);
/*     */   }
/*     */ 
/*     */   public void updateLocal(xq paramxq)
/*     */   {
/* 321 */     super.updateLocal(paramxq);
/*     */     try
/*     */     {
/* 325 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.updateLocal(paramxq); } catch (Exception localException) {
/* 326 */       (
/* 327 */         paramxq = 
/* 329 */         localException).printStackTrace();
/* 328 */       throw new ErrorDialogException(paramxq.getMessage());
/*     */     }
/*     */     try
/*     */     {
/* 332 */       if ((isOnServer()) && (getTotalElements() <= 0) && (System.currentTimeMillis() - getTimeCreated() > 3000L) && (isEmptyOnServer()))
/*     */       {
/* 336 */         System.err.println("[SERVER][SPACESTATION] Empty station: deleting!!!!!!!!!!!!!!!!!!! " + this);
/* 337 */         setMarkedForDeleteVolatile(true);
/*     */       }return; } catch (IOException localIOException) {
/* 343 */       localIOException.printStackTrace();
/*     */       return;
/*     */     } catch (InterruptedException localInterruptedException) {
/* 343 */       localInterruptedException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateToFullNetworkObject()
/*     */   {
/* 356 */     super.updateToFullNetworkObject();
/* 357 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.updateToFullNetworkObject((NetworkSpaceStation)super.getNetworkObject());
/*     */   }
/*     */ 
/*     */   public void getRelationColor(lZ paramlZ, Vector4f paramVector4f, float paramFloat) {
/* 361 */     switch (kj.a[paramlZ.ordinal()]) {
/*     */     case 1:
/* 363 */       paramVector4f.x = (paramFloat + 0.8F);
/* 364 */       paramVector4f.y = (paramFloat + 0.3F);
/* 365 */       paramVector4f.z = (paramFloat + 0.1F);
/* 366 */       return;
/*     */     case 2:
/* 369 */       paramVector4f.x = (paramFloat + 0.3F);
/* 370 */       paramVector4f.y = (paramFloat + 0.8F);
/* 371 */       paramVector4f.z = (paramFloat + 0.1F);
/* 372 */       return;
/*     */     case 3:
/* 375 */       paramVector4f.x = 0.3F;
/* 376 */       paramVector4f.y = (paramFloat + 0.2F);
/* 377 */       paramVector4f.z = 0.7F;
/*     */     }
/*     */   }
/*     */ 
/*     */   protected String getSegmentControllerTypeString()
/*     */   {
/* 385 */     return "Station";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ki
 * JD-Core Version:    0.6.2
 */