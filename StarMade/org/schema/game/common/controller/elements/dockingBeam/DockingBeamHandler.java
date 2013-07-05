/*     */ package org.schema.game.common.controller.elements.dockingBeam;
/*     */ 
/*     */ import cL;
/*     */ import ct;
/*     */ import dj;
/*     */ import jA;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import jq;
/*     */ import jv;
/*     */ import kd;
/*     */ import ki;
/*     */ import ld;
/*     */ import le;
/*     */ import org.schema.game.common.controller.CollectionNotLoadedException;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ManagerModuleCollection;
/*     */ import org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager;
/*     */ import org.schema.game.common.controller.elements.dockingBlock.DockingBlockManagerInterface;
/*     */ import org.schema.game.common.data.element.BeamHandler;
/*     */ import org.schema.game.common.data.element.BeamHandler.BeamState;
/*     */ import org.schema.game.common.data.element.BeamHandler.ElementNotFoundException;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.common.data.physics.CubeRayCastResult;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.network.objects.NetworkSegmentController;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector4i;
/*     */ import q;
/*     */ import s;
/*     */ import x;
/*     */ import xq;
/*     */ 
/*     */ public class DockingBeamHandler extends BeamHandler
/*     */   implements jq
/*     */ {
/*     */   private static final float BEAM_TIMEOUT_IN_SECS = 0.5F;
/*     */   private static final float TIME_BEAM_TO_HIT_ONE_PIECE_IN_SECS = 0.2F;
/*     */   private final DockingBeamElementManager dockingBeamElementManager;
/*     */   private long lastActivate;
/*     */ 
/*     */   public DockingBeamHandler(SegmentController paramSegmentController, DockingBeamElementManager paramDockingBeamElementManager)
/*     */   {
/*  42 */     super(paramSegmentController, null);
/*  43 */     this.dockingBeamElementManager = paramDockingBeamElementManager;
/*     */   }
/*     */ 
/*     */   public boolean canhit(SegmentController paramSegmentController, String[] paramArrayOfString, q paramq)
/*     */   {
/*  51 */     if ((
/*  51 */       paramSegmentController = (!paramSegmentController.equals(getSegmentController())) && (((paramSegmentController instanceof kd)) || ((paramSegmentController instanceof ki)) || ((paramSegmentController instanceof jA))) ? 1 : 0) == 0)
/*     */     {
/*  52 */       paramArrayOfString[0] = "Must aim at docking module";
/*     */     }
/*  54 */     return paramSegmentController;
/*     */   }
/*     */ 
/*     */   public float getBeamTimeoutInSecs()
/*     */   {
/*  59 */     return 0.5F;
/*     */   }
/*     */ 
/*     */   public float getBeamToHitInSecs(BeamHandler.BeamState paramBeamState)
/*     */   {
/*  64 */     return 0.2F;
/*     */   }
/*     */ 
/*     */   public DockingBeamHandler getHandler()
/*     */   {
/*  70 */     return this;
/*     */   }
/*     */ 
/*     */   public void onBeamHit(BeamHandler.BeamState paramBeamState, jq paramjq, q paramq, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, CubeRayCastResult paramCubeRayCastResult, xq paramxq, Collection paramCollection)
/*     */   {
/*     */     try
/*     */     {
/*  78 */       (
/*  79 */         paramBeamState = new le(paramCubeRayCastResult.getSegment(), paramCubeRayCastResult.cubePos))
/*  79 */         .a();
/*  80 */       if (paramBeamState.a() == 0) {
/*  81 */         System.err.println("[DOCINGBEAM] hitting air");
/*  82 */         return;
/*     */       }
/*  84 */       paramjq = ElementKeyMap.getInfo(paramBeamState.a());
/*     */ 
/*  87 */       if (beamHit(getBeam(paramq), paramBeamState) > 0)
/*     */       {
/*  88 */         paramq = paramCubeRayCastResult.getSegment().a();
/*     */ 
/*  90 */         if (paramjq.isDockable())
/*     */         {
/*  92 */           if (((paramq instanceof ld)) && ((((ld)paramq).a() instanceof DockingBlockManagerInterface)))
/*     */           {
/*  97 */             paramjq = (DockingBlockManagerInterface)((ld)paramq).a();
/*  98 */             paramSegment = paramBeamState.a(new q());
/*  99 */             paramVector3f1 = 0;
/* 100 */             paramVector3f2 = 0;
/* 101 */             paramCubeRayCastResult = 0;
/* 102 */             paramxq = paramq.getDockingController().a() != null ? 1 : 0;
/* 103 */             if (paramBeamState.a() == 112) {
/* 104 */               getSegmentController().getDockingController().a(paramq.getUniqueIdentifier(), paramSegment); return;
/*     */             }
/* 106 */             if (paramq.getDockingController().a() == null)
/*     */             {
/* 108 */               paramjq = paramjq.getDockingBlock().iterator();
/*     */               do { if (!paramjq.hasNext()) break;
/* 109 */                 for (paramCollection = ((ManagerModuleCollection)paramjq.next())
/* 109 */                   .getCollectionManagers().iterator(); paramCollection.hasNext(); )
/*     */                 {
/*     */                   DockingBlockCollectionManager localDockingBlockCollectionManager;
/* 110 */                   if ((
/* 110 */                     localDockingBlockCollectionManager = (DockingBlockCollectionManager)paramCollection.next())
/* 110 */                     .getControllerPos().equals(paramSegment)) {
/* 111 */                     paramVector3f2 = 1;
/* 112 */                     localDockingBlockCollectionManager.refreshActive();
/* 113 */                     if (!localDockingBlockCollectionManager.hasCollision()) {
/*     */                       try {
/* 115 */                         if (localDockingBlockCollectionManager.isObjectDockable(getSegmentController())) {
/* 116 */                           paramVector3f1 = 1;
/* 117 */                           break;
/*     */                         }
/*     */                       } catch (CollectionNotLoadedException localCollectionNotLoadedException) {
/* 120 */                         System.err.println("[DockingBeamHandler] CANNOT DOCK: COLLECTION NOT YET LAODED");
/*     */                       }
/*     */                     }
/*     */                     else
/* 124 */                       paramCubeRayCastResult = 1;
/*     */                   }
/*     */                 }
/*     */               }
/* 128 */               while (paramVector3f1 == 0);
/* 129 */             }if (paramVector3f1 != 0)
/*     */             {
/* 134 */               if (getSegmentController().isOnServer()) {
/* 135 */                 System.err.println("[SERVER] NOW REQUESTING DOCK FROM " + getSegmentController() + " ON " + paramq + " AT " + paramSegment);
/* 136 */                 getSegmentController().getDockingController().a(paramq.getUniqueIdentifier(), paramSegment);
/*     */               }
/*     */ 
/*     */             }
/* 140 */             else if (getSegmentController().isClientOwnObject()) {
/* 141 */               if (paramxq != 0) {
/* 142 */                 ((ct)getSegmentController().getState()).a().b("Cannot dock here!\nCannot dock on already\ndocked target (yet)"); return;
/*     */               }
/* 144 */               if (paramVector3f2 == 0) {
/* 145 */                 ((ct)getSegmentController().getState()).a().b("Cannot dock here!\nno target found"); return;
/*     */               }
/* 147 */               if (paramCubeRayCastResult != 0) {
/* 148 */                 ((ct)getSegmentController().getState()).a().b("Cannot dock here!\ntarget docking area\nis blocked.");
/*     */               }
/*     */               else
/*     */               {
/* 152 */                 if (paramVector3f1 != 0) break label576;
/* 153 */                 if (paramBeamState.a() == 7) {
/* 154 */                   ((ct)getSegmentController().getState()).a().d("General Note: \nturrets may not have blocks\nbelow the core.");
/*     */ 
/* 158 */                   ((ct)getSegmentController().getState()).a().b("The docking area is too small\nfor this turret to dock.\nPlease expand the docking area,\nor use a smaller turret.");
/*     */                 }
/*     */                 else
/*     */                 {
/* 165 */                   ((ct)getSegmentController().getState()).a().b("The docking area is too small\nfor this ship to dock.\nPlease expand the docking area,\nor use a smaller ship.");
/*     */                 }
/*     */ 
/*     */               }
/*     */ 
/* 172 */               ((ct)getSegmentController().getState()).a().a().a(paramBeamState);
/*     */             }
/*     */ 
/* 176 */             label576: return;
/* 177 */           }if (getSegmentController().isClientOwnObject()) {
/* 178 */             ((ct)getSegmentController().getState()).a().b("Cannot dock here!\ntarget not compatible");
/*     */           }
/*     */ 
/*     */         }
/* 183 */         else if (paramjq.canActivate()) {
/* 184 */           if ((!getSegmentController().isOnServer()) && (((ct)getSegmentController().getState()).a().b(paramBeamState.a().a())))
/*     */           {
/* 187 */             if (System.currentTimeMillis() - this.lastActivate > 2000L)
/*     */             {
/* 190 */               if ((paramBeamState.a() == 122) || (paramBeamState.a() == 55) || (paramBeamState.a() == 283) || (paramBeamState.a() == 284) || (paramBeamState.a() == 282) || (paramBeamState.a() == 285) || (paramBeamState.a() == 62))
/*     */               {
/* 198 */                 paramjq = paramBeamState.a(new q());
/* 199 */                 paramSegment = new s(paramjq.a, paramjq.b, paramjq.c, paramBeamState.a() ? 0 : 1);
/*     */ 
/* 202 */                 ((NetworkSegmentController)paramBeamState.a().a().getNetworkObject()).blockActivationBuffer.add(new RemoteVector4i(paramSegment, getSegmentController().getNetworkObject()));
/*     */ 
/* 204 */                 this.lastActivate = System.currentTimeMillis();
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 209 */         else if (getSegmentController().isClientOwnObject()) {
/* 210 */           ((ct)getSegmentController().getState()).a().b("Cannot dock here!\ntarget not compatible");
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 221 */       return;
/*     */     }
/*     */     catch (BeamHandler.ElementNotFoundException localElementNotFoundException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public DockingBeamElementManager getDockingBeamElementManager()
/*     */   {
/* 230 */     return this.dockingBeamElementManager;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBeam.DockingBeamHandler
 * JD-Core Version:    0.6.2
 */