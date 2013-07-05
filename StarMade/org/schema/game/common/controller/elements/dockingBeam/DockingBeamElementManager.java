/*     */ package org.schema.game.common.controller.elements.dockingBeam;
/*     */ 
/*     */ import ct;
/*     */ import cz;
/*     */ import jL;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import javax.vecmath.Vector3f;
/*     */ import jv;
/*     */ import kd;
/*     */ import lA;
/*     */ import lE;
/*     */ import le;
/*     */ import mF;
/*     */ import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*     */ import org.schema.game.common.controller.elements.ManagerContainer;
/*     */ import org.schema.game.common.controller.elements.UsableElementManager;
/*     */ import org.schema.game.common.data.element.ElementDocking;
/*     */ import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.network.objects.NetworkPlayer;
/*     */ import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteField;
/*     */ import q;
/*     */ import x;
/*     */ import xq;
/*     */ 
/*     */ public class DockingBeamElementManager extends UsableElementManager
/*     */ {
/*  26 */   private Vector3f shootingDirTemp = new Vector3f();
/*     */   private DockingBeamHandler dockingBeamManager;
/*     */ 
/*     */   public DockingBeamElementManager(SegmentController paramSegmentController)
/*     */   {
/*  30 */     super(paramSegmentController);
/*  31 */     this.dockingBeamManager = new DockingBeamHandler(paramSegmentController, this);
/*     */   }
/*     */ 
/*     */   public DockingBeamHandler getDockingBeamManager()
/*     */   {
/*  38 */     return this.dockingBeamManager;
/*     */   }
/*     */ 
/*     */   public ElementCollectionManager getNewCollectionManager(le paramle)
/*     */   {
/*  43 */     throw new IllegalAccessError("This should not be called. ever");
/*     */   }
/*     */ 
/*     */   public void handle(lA paramlA)
/*     */   {
/*     */     try
/*     */     {
/*  51 */       Object localObject1;
/*  51 */       if (((Boolean)paramlA.jdField_a_of_type_LE.a().activeControllerMask.get(1).get()).booleanValue()) { if (((!(localObject1 = paramlA).jdField_a_of_type_LE.isOnServer()) && ((((lA)localObject1).jdField_a_of_type_Cw instanceof mF)) && (((ct)((lA)localObject1).jdField_a_of_type_LE.getState()).a() != ((mF)((lA)localObject1).jdField_a_of_type_Cw).getSectorId()) ? 0 : 1) != 0);
/*     */       } else {
/*  53 */         return;
/*     */       }
/*     */ 
/*  56 */       if ((
/*  56 */         localObject1 = (q)paramlA.jdField_a_of_type_JavaLangObject)
/*  56 */         .equals(kd.a)) {
/*  57 */         Object localObject2 = new q((q)paramlA.jdField_a_of_type_JavaLangObject);
/*     */ 
/*  59 */         Object localObject3 = null;
/*     */ 
/*  62 */         if ((
/*  62 */           localObject2 = getSegmentBuffer().a((q)localObject2, getSegmentController().isOnServer(), new le())) == null)
/*     */         {
/*  63 */           return;
/*     */         }
/*     */ 
/*  67 */         if (((le)localObject2).a() == 1) {
/*     */           try {
/*  69 */             localObject3 = checkShipConfig(paramlA);
/*  70 */             int i = paramlA.jdField_a_of_type_LE.d();
/*  71 */             if (!((cz)localObject3).a(i)) {
/*  72 */               return;
/*     */             }
/*     */ 
/*  75 */             if (!(
/*  75 */               localObject3 = ((cz)localObject3).a(i))
/*  75 */               .equals(localObject1))
/*     */             {
/*  77 */               for (paramlA = getSegmentController().getDockingController().a().iterator(); paramlA.hasNext(); ) {
/*  78 */                 if ((
/*  78 */                   localObject1 = (ElementDocking)paramlA.next()).to
/*  78 */                   .a(new q()).equals(localObject3)) {
/*  79 */                   if (getSegmentController().isOnServer()) {
/*  80 */                     System.err.println("[ELEMENTMANAGER] Requesting undock from " + ((ElementDocking)localObject1).from.a().a() + " " + ((ElementDocking)localObject1).from.a().a().getState());
/*  81 */                     ((ElementDocking)localObject1).from.a().a().getDockingController().b();
/*     */                   } else {
/*  83 */                     ((ct)getState()).a().d("Undocking from\n" + ((ElementDocking)localObject1).from.a().a().toNiceString());
/*     */                   }
/*     */                 }
/*     */               }
/*  87 */               return;
/*     */             }
/*     */           }
/*     */           catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException) {
/*  91 */             return;
/*     */           }
/*     */         }
/*  94 */         if (getSegmentController().getDockingController().a() != null) {
/*  95 */           if (getSegmentController().isOnServer()) {
/*  96 */             System.err.println("[ELEMENTMANAGER] Requesting undock from " + getSegmentController().getDockingController().a());
/*  97 */             getSegmentController().getDockingController().b();
/*  98 */             return;
/*     */           }
/* 100 */           if (getSegmentController().getDockingController().a()) {
/* 101 */             ((ct)getState()).a().d("Undocking");
/*     */           }
/* 103 */           return;
/*     */         }
/*     */ 
/* 108 */         this.shootingDirTemp.set(paramlA.jdField_a_of_type_LE.a());
/* 109 */         this.shootingDirTemp.scale(100.0F);
/* 110 */         Vector3f localVector3f = new Vector3f();
/* 111 */         getSegmentController().getAbsoluteElementWorldPosition(new q(), localVector3f);
/*     */ 
/* 113 */         (
/* 115 */           localObject3 = new Vector3f())
/* 115 */           .set(localVector3f);
/*     */ 
/* 117 */         ((Vector3f)localObject3).add(this.shootingDirTemp);
/* 118 */         this.dockingBeamManager.addBeam((q)localObject1, localVector3f, (Vector3f)localObject3, paramlA.jdField_a_of_type_LE, 0.0F);
/*     */ 
/* 120 */         getManagerContainer().onAction();
/*     */       }return;
/*     */     } catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException) {
/* 123 */       System.err.println("[CLIENT][WARNING] " + getSegmentController() + " Cannot DOCK! segment not yet in buffer " + localCannotImmediateRequestOnClientException.a() + ". -> requested");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void update(xq paramxq) {
/* 128 */     this.dockingBeamManager.update(paramxq);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBeam.DockingBeamElementManager
 * JD-Core Version:    0.6.2
 */