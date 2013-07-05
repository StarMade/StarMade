/*      */ package org.schema.game.common.controller;
/*      */ 
/*      */ import I;
/*      */ import ag;
/*      */ import ah;
/*      */ import az;
/*      */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*      */ import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*      */ import com.bulletphysics.linearmath.Transform;
/*      */ import ct;
/*      */ import cw;
/*      */ import dj;
/*      */ import eH;
/*      */ import es;
/*      */ import ij;
/*      */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*      */ import jH;
/*      */ import jI;
/*      */ import jL;
/*      */ import jO;
/*      */ import jR;
/*      */ import jY;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.ThreadPoolExecutor;
/*      */ import javax.vecmath.Vector3f;
/*      */ import jq;
/*      */ import ju;
/*      */ import jv;
/*      */ import jw;
/*      */ import jx;
/*      */ import kI;
/*      */ import ka;
/*      */ import kd;
/*      */ import ki;
/*      */ import km;
/*      */ import lA;
/*      */ import lE;
/*      */ import lP;
/*      */ import lT;
/*      */ import lb;
/*      */ import lc;
/*      */ import ld;
/*      */ import le;
/*      */ import ln;
/*      */ import lz;
/*      */ import mw;
/*      */ import mx;
/*      */ import o;
/*      */ import org.schema.game.common.controller.elements.ManagerModuleCollection;
/*      */ import org.schema.game.common.controller.elements.ShieldContainerInterface;
/*      */ import org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager;
/*      */ import org.schema.game.common.controller.elements.dockingBlock.DockingBlockManagerInterface;
/*      */ import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*      */ import org.schema.game.common.data.element.BeamHandler;
/*      */ import org.schema.game.common.data.element.BeamHandler.BeamState;
/*      */ import org.schema.game.common.data.element.Element;
/*      */ import org.schema.game.common.data.element.ElementClassNotFoundException;
/*      */ import org.schema.game.common.data.element.ElementDocking;
/*      */ import org.schema.game.common.data.element.ElementInformation;
/*      */ import org.schema.game.common.data.element.ElementKeyMap;
/*      */ import org.schema.game.common.data.physics.CubeRayCastResult;
/*      */ import org.schema.game.common.data.physics.PhysicsExt;
/*      */ import org.schema.game.common.data.world.Segment;
/*      */ import org.schema.game.common.data.world.SegmentData;
/*      */ import org.schema.game.common.data.world.Universe;
/*      */ import org.schema.game.network.objects.NetworkSegmentController;
/*      */ import org.schema.game.network.objects.remote.RemoteSegmentPiece;
/*      */ import org.schema.game.network.objects.remote.RemoteSegmentPieceBuffer;
/*      */ import org.schema.schine.network.NetworkStateContainer;
/*      */ import org.schema.schine.network.StateInterface;
/*      */ import org.schema.schine.network.objects.Sendable;
/*      */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*      */ import org.schema.schine.network.server.ServerMessage;
/*      */ import q;
/*      */ import vf;
/*      */ import vg;
/*      */ import x;
/*      */ import xe;
/*      */ import xq;
/*      */ 
/*      */ public abstract class EditableSendableSegmentController extends ka
/*      */   implements jH
/*      */ {
/*      */   private static final long MIN_TIME_BETWEEN_EDITS = 50L;
/*      */   private long lastEditBlocks;
/*   70 */   private final q tmpPos = new q();
/*      */ 
/*   72 */   private final q absPosCache = new q();
/*      */   private boolean flagCharacterExitCheckByExplosion;
/*      */   private Object flagCoreDestroyedByExplosion;
/*   76 */   private final ArrayList explosionOrdersRunning = new ArrayList();
/*      */ 
/*   78 */   private final q tmpVisPos = new q();
/*      */ 
/*   80 */   public EditableSendableSegmentController(StateInterface paramStateInterface) { super(paramStateInterface); }
/*      */ 
/*      */   public boolean allowedType(short paramShort)
/*      */   {
/*   84 */     if (!ElementKeyMap.getInfo(paramShort).isPlacable()) {
/*   85 */       if (!isOnServer()) {
/*   86 */         if (1 == paramShort) {
/*   87 */           ((ct)getState()).a().b("ERROR\nCore Elements cannot be placed\nthey are used to spawn new ships");
/*      */         }
/*      */         else
/*      */         {
/*   92 */           ((ct)getState()).a().b("ERROR\nThis Element cannot be placed.\nhow did you even get that...");
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*   98 */       return false;
/*      */     }
/*  100 */     return true;
/*      */   }
/*      */ 
/*      */   private void checkCharacterExit() {
/*  104 */     System.err.println("[SegController] CHECKING CHARACTER EXIT");
/*      */     Iterator localIterator;
/*  105 */     if ((this instanceof cw))
/*  106 */       for (localIterator = ((cw)this).a().iterator(); localIterator.hasNext(); ) ((lE)localIterator.next())
/*  107 */           .a().d();
/*      */   }
/*      */ 
/*      */   public boolean isEmptyOnServer()
/*      */   {
/*  113 */     int i = 1;
/*  114 */     for (Iterator localIterator = ((vg)getState()).b().values().iterator(); localIterator.hasNext(); ) {
/*  115 */       if (((lE)localIterator.next())
/*  115 */         .c() == getSectorId()) {
/*  116 */         i = 0;
/*  117 */         break;
/*      */       }
/*      */     }
/*  120 */     if (i != 0) {
/*  121 */       return false;
/*      */     }
/*      */ 
/*  125 */     q localq2 = new q(getMaxPos());
/*      */ 
/*  128 */     q localq1 = new q(getMinPos());
/*      */ 
/*  130 */     System.err.println("[SERVER][SEGMENTCONTROLLER] CHECKING EMPTY FROM " + localq1 + " TO " + localq2 + ": " + this);
/*  131 */     for (int j = localq1.c; j <= localq2.c; j++) {
/*  132 */       for (int k = localq1.jdField_b_of_type_Int; k <= localq2.jdField_b_of_type_Int; k++) {
/*  133 */         for (int m = localq1.jdField_a_of_type_Int; m <= localq2.jdField_a_of_type_Int; m++)
/*      */         {
/*      */           Object localObject;
/*  134 */           (
/*  135 */             localObject = new q()).jdField_a_of_type_Int = 
/*  135 */             ((m << 4) + 8);
/*  136 */           ((q)localObject).jdField_b_of_type_Int = ((k << 4) + 8);
/*  137 */           ((q)localObject).c = ((j << 4) + 8);
/*      */ 
/*  139 */           if (!(
/*  139 */             localObject = getSegmentBuffer().a((q)localObject, true))
/*  139 */             .a().g()) {
/*  140 */             System.err.println("[SERVER][SEGMENTCONTROLLER] CHECKING EMPTY FROM " + localq1 + " TO " + localq2 + ": " + this + " --- IS NOT EMPTY " + ((le)localObject).a());
/*  141 */             return false;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  146 */     System.err.println("[SERVER][SEGMENTCONTROLLER] CHECKING EMPTY FROM " + localq1 + " TO " + localq2 + ": " + this + " --- IS EMPTY");
/*  147 */     return true;
/*      */   }
/*      */   public int damageElement(short paramShort, int paramInt1, SegmentData paramSegmentData, int paramInt2) {
/*      */     try {
/*  151 */       paramShort = ElementKeyMap.getInfo(paramShort);
/*  152 */       paramShort = (int)Math.max(0.0D, paramInt2 - Math.ceil(paramInt2 * paramShort.getArmourPercent()));
/*  153 */       paramSegmentData.setHitpoints(paramInt1, (short)Math.max(0, paramSegmentData.getHitpoints(paramInt1) - paramShort));
/*  154 */       return paramShort; } catch (ElementClassNotFoundException localElementClassNotFoundException) { localElementClassNotFoundException
/*  155 */         .printStackTrace();
/*      */ 
/*  157 */       System.err.println("[WARNING] Exception catched! returning zero damage"); }
/*  158 */     return 0;
/*      */   }
/*      */ 
/*      */   public void destroy() {
/*  162 */     System.out.println("[SEGMENTCONTROLLER] ENTITY " + this + " HAS BEEN DESTROYED... ");
/*  163 */     markedForPermanentDelete(true);
/*  164 */     setMarkedForDeleteVolatile(true);
/*      */   }
/*      */ 
/*      */   public void doDimExtensionIfNecessary(Segment paramSegment, byte paramByte1, byte paramByte2, byte paramByte3) {
/*  168 */     paramSegment.a(paramByte1, paramByte2, paramByte3, this.absPosCache);
/*      */ 
/*  171 */     if (paramByte1 == 0) {
/*  172 */       Segment.a(this.absPosCache.jdField_a_of_type_Int - 1, this.absPosCache.jdField_b_of_type_Int, this.absPosCache.c, this.tmpPos);
/*  173 */       extendDim(0, this.absPosCache, this.tmpPos, -1, 0, 0);
/*      */     }
/*  175 */     if (paramByte2 == 0) {
/*  176 */       Segment.a(this.absPosCache.jdField_a_of_type_Int, this.absPosCache.jdField_b_of_type_Int - 1, this.absPosCache.c, this.tmpPos);
/*  177 */       extendDim(1, this.absPosCache, this.tmpPos, 0, -1, 0);
/*      */     }
/*  179 */     if (paramByte3 == 0) {
/*  180 */       Segment.a(this.absPosCache.jdField_a_of_type_Int, this.absPosCache.jdField_b_of_type_Int, this.absPosCache.c - 1, this.tmpPos);
/*  181 */       extendDim(2, this.absPosCache, this.tmpPos, 0, 0, -1);
/*      */     }
/*      */ 
/*  184 */     if (paramByte1 == 15) {
/*  185 */       Segment.a(this.absPosCache.jdField_a_of_type_Int + 1, this.absPosCache.jdField_b_of_type_Int, this.absPosCache.c, this.tmpPos);
/*  186 */       extendDim(0, this.absPosCache, this.tmpPos, 1, 0, 0);
/*      */     }
/*  188 */     if (paramByte2 == 15) {
/*  189 */       Segment.a(this.absPosCache.jdField_a_of_type_Int, this.absPosCache.jdField_b_of_type_Int + 1, this.absPosCache.c, this.tmpPos);
/*  190 */       extendDim(1, this.absPosCache, this.tmpPos, 0, 1, 0);
/*      */     }
/*  192 */     if (paramByte3 == 15) {
/*  193 */       Segment.a(this.absPosCache.jdField_a_of_type_Int, this.absPosCache.jdField_b_of_type_Int, this.absPosCache.c + 1, this.tmpPos);
/*  194 */       extendDim(2, this.absPosCache, this.tmpPos, 0, 0, 1);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void extendDim(int paramInt1, q paramq1, q paramq2, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/*  205 */     if (!isInboundCoord(paramInt1, paramq2))
/*      */     {
/*  208 */       getMaxPos().jdField_a_of_type_Int += (paramInt2 > 0 ? paramInt2 : 0);
/*  209 */       getMaxPos().jdField_b_of_type_Int += (paramInt3 > 0 ? paramInt3 : 0);
/*  210 */       getMaxPos().c += (paramInt4 > 0 ? paramInt4 : 0);
/*      */ 
/*  212 */       getMinPos().jdField_a_of_type_Int += (paramInt2 < 0 ? paramInt2 : 0);
/*  213 */       getMinPos().jdField_b_of_type_Int += (paramInt3 < 0 ? paramInt3 : 0);
/*  214 */       getMinPos().c += (paramInt4 < 0 ? paramInt4 : 0);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void forceCharacterExit(le paramle)
/*      */   {
/*  221 */     synchronized (getState().getLocalAndRemoteObjectContainer().getLocalObjects()) {
/*  222 */       for (Iterator localIterator = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext(); )
/*      */       {
/*      */         Sendable localSendable;
/*  223 */         if (((
/*  223 */           localSendable = (Sendable)localIterator.next()) instanceof lE))
/*      */         {
/*  224 */           ((lE)localSendable)
/*  225 */             .a(paramle);
/*      */         }
/*      */       }
/*  228 */       return;
/*      */     }
/*      */   }
/*  231 */   protected short getCoreType() { return 1; }
/*      */ 
/*      */ 
/*      */   public void getNearestIntersectingElementPosition(Vector3f paramVector3f1, Vector3f paramVector3f2, q paramq, float paramFloat, ah paramah, az paramaz)
/*      */   {
/*  243 */     if (System.currentTimeMillis() - this.lastEditBlocks < 50L) {
/*  244 */       return;
/*      */     }
/*  246 */     Object localObject1 = new q();
/*      */ 
/*  248 */     if ((
/*  248 */       paramVector3f1 = getNearestPiece(paramVector3f1, paramVector3f2, paramFloat, (q)localObject1, paramq)) == null)
/*      */     {
/*  249 */       System.err.println("[SEGCONTROLLER][ELEMENT][REMOVE] NO NEAREST PIECE FOUND");
/*  250 */       return;
/*      */     }
/*  252 */     paramVector3f2 = paramq.a(1, 1, 1);
/*      */ 
/*  259 */     paramq = Math.min((
/*  259 */       paramVector3f1 = paramVector3f1.a(new q())).jdField_a_of_type_Int, 
/*  259 */       paramVector3f1.jdField_a_of_type_Int + ((q)localObject1).jdField_a_of_type_Int);
/*  260 */     paramFloat = Math.min(paramVector3f1.jdField_b_of_type_Int, paramVector3f1.jdField_b_of_type_Int + ((q)localObject1).jdField_b_of_type_Int);
/*  261 */     Object localObject2 = Math.min(paramVector3f1.c, paramVector3f1.c + ((q)localObject1).c);
/*      */ 
/*  263 */     int j = Math.max(paramVector3f1.jdField_a_of_type_Int, paramVector3f1.jdField_a_of_type_Int + ((q)localObject1).jdField_a_of_type_Int);
/*  264 */     int k = Math.max(paramVector3f1.jdField_b_of_type_Int, paramVector3f1.jdField_b_of_type_Int + ((q)localObject1).jdField_b_of_type_Int);
/*  265 */     Object localObject3 = Math.max(paramVector3f1.c, paramVector3f1.c + ((q)localObject1).c);
/*      */ 
/*  267 */     if (j == paramVector3f1.jdField_a_of_type_Int) {
/*  268 */       paramq++;
/*  269 */       j++;
/*      */     }
/*  271 */     if (k == paramVector3f1.jdField_b_of_type_Int) {
/*  272 */       paramFloat++;
/*  273 */       k++;
/*      */     }
/*  275 */     if (localObject3 == paramVector3f1.c) {
/*  276 */       localObject2++;
/*  277 */       localObject3++;
/*      */     }
/*  279 */     if (paramaz.jdField_a_of_type_Int > 0) {
/*  280 */       paramaz.jdField_a_of_type_Int = 0;
/*  281 */       return;
/*      */     }
/*      */ 
/*  292 */     System.err.println("[SEGCONTROLLER][ELEMENT][REMOVE] REMOVING BLOCKS: SIZE: " + localObject1);
/*      */ 
/*  294 */     paramVector3f1 = new HashSet(8);
/*      */ 
/*  296 */     long l = System.currentTimeMillis();
/*      */ 
/*  298 */     for (localObject1 = localObject2; localObject1 < localObject3; localObject1++) {
/*  299 */       for (int i = paramFloat; i < k; i++) {
/*  300 */         for (int m = paramq; m < j; m++) {
/*  301 */           remove(m, i, localObject1, paramah, paramVector3f2, paramVector3f1);
/*      */ 
/*  304 */           if ((paramaz.jdField_a_of_type_Boolean) && (!paramaz.jdField_b_of_type_Boolean) && (!paramaz.jdField_c_of_type_Boolean))
/*      */           {
/*  307 */             Object localObject8 = (paramaz.jdField_a_of_type_Q.c - 
/*  307 */               localObject1 << 1) + paramaz.jdField_b_of_type_Int;
/*      */ 
/*  309 */             remove(m, i, localObject1 + localObject8, paramah, paramVector3f2, paramVector3f1);
/*      */           }
/*  311 */           else if ((!paramaz.jdField_a_of_type_Boolean) && (paramaz.jdField_b_of_type_Boolean) && (!paramaz.jdField_c_of_type_Boolean))
/*      */           {
/*  314 */             int i4 = (paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int - 
/*  314 */               i << 1) + paramaz.jdField_b_of_type_Int;
/*      */ 
/*  316 */             remove(m, i + i4, localObject1, paramah, paramVector3f2, paramVector3f1);
/*      */           }
/*  318 */           else if ((!paramaz.jdField_a_of_type_Boolean) && (!paramaz.jdField_b_of_type_Boolean) && (paramaz.jdField_c_of_type_Boolean))
/*      */           {
/*  322 */             int i5 = (paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int - 
/*  322 */               m << 1) + paramaz.jdField_b_of_type_Int;
/*      */ 
/*  324 */             remove(m + i5, i, localObject1, paramah, paramVector3f2, paramVector3f1);
/*      */           }
/*  327 */           else if ((paramaz.jdField_a_of_type_Boolean) && (paramaz.jdField_b_of_type_Boolean) && (!paramaz.jdField_c_of_type_Boolean))
/*      */           {
/*  329 */             Object localObject4 = paramaz.jdField_a_of_type_Q.c;
/*  330 */             int i6 = paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int;
/*      */ 
/*  332 */             Object localObject9 = (localObject4 - localObject1 << 1) + paramaz.jdField_b_of_type_Int;
/*  333 */             int n = (i6 - i << 1) + paramaz.jdField_b_of_type_Int;
/*      */ 
/*  335 */             remove(m, i, localObject1 + localObject9, paramah, paramVector3f2, paramVector3f1);
/*  336 */             remove(m, i + n, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  337 */             remove(m, i + n, localObject1 + localObject9, paramah, paramVector3f2, paramVector3f1);
/*      */           }
/*  340 */           else if ((paramaz.jdField_a_of_type_Boolean) && (!paramaz.jdField_b_of_type_Boolean) && (paramaz.jdField_c_of_type_Boolean))
/*      */           {
/*  342 */             Object localObject5 = paramaz.jdField_a_of_type_Q.c;
/*  343 */             int i7 = paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int;
/*      */ 
/*  345 */             Object localObject10 = (localObject5 - localObject1 << 1) + paramaz.jdField_b_of_type_Int;
/*  346 */             int i1 = (i7 - m << 1) + paramaz.jdField_b_of_type_Int;
/*      */ 
/*  348 */             remove(m, i, localObject1 + localObject10, paramah, paramVector3f2, paramVector3f1);
/*  349 */             remove(m + i1, i, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  350 */             remove(m + i1, i, localObject1 + localObject10, paramah, paramVector3f2, paramVector3f1);
/*      */           }
/*  353 */           else if ((!paramaz.jdField_a_of_type_Boolean) && (paramaz.jdField_b_of_type_Boolean) && (paramaz.jdField_c_of_type_Boolean))
/*      */           {
/*  355 */             int i2 = paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int;
/*  356 */             int i8 = paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int;
/*      */ 
/*  358 */             int i11 = (i2 - i << 1) + paramaz.jdField_b_of_type_Int;
/*  359 */             int i3 = (i8 - m << 1) + paramaz.jdField_b_of_type_Int;
/*      */ 
/*  361 */             remove(m, i + i11, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  362 */             remove(m + i3, i, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  363 */             remove(m + i3, i + i11, localObject1, paramah, paramVector3f2, paramVector3f1);
/*      */           }
/*  366 */           else if ((paramaz.jdField_a_of_type_Boolean) && (paramaz.jdField_b_of_type_Boolean) && (paramaz.jdField_c_of_type_Boolean))
/*      */           {
/*  368 */             Object localObject6 = paramaz.jdField_a_of_type_Q.c;
/*  369 */             int i9 = paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int;
/*  370 */             int i12 = paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int;
/*      */ 
/*  372 */             Object localObject7 = (localObject6 - localObject1 << 1) + paramaz.jdField_b_of_type_Int;
/*  373 */             int i10 = (i9 - i << 1) + paramaz.jdField_b_of_type_Int;
/*  374 */             int i13 = (i12 - m << 1) + paramaz.jdField_b_of_type_Int;
/*      */ 
/*  376 */             remove(m + i13, i, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  377 */             remove(m, i + i10, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  378 */             remove(m, i, localObject1 + localObject7, paramah, paramVector3f2, paramVector3f1);
/*      */ 
/*  380 */             remove(m + i13, i + i10, localObject1, paramah, paramVector3f2, paramVector3f1);
/*  381 */             remove(m + i13, i, localObject1 + localObject7, paramah, paramVector3f2, paramVector3f1);
/*  382 */             remove(m, i + i10, localObject1 + localObject7, paramah, paramVector3f2, paramVector3f1);
/*      */ 
/*  384 */             remove(m + i13, i + i10, localObject1 + localObject7, paramah, paramVector3f2, paramVector3f1);
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  392 */     System.err.println("[SEGMENTCONTROLLER] REMOVAL TOOK " + (System.currentTimeMillis() - l));
/*      */ 
/*  394 */     if (paramVector3f2 == 0)
/*  395 */       for (localObject1 = paramVector3f1.iterator(); ((Iterator)localObject1).hasNext(); )
/*      */       {
/*      */         Segment localSegment;
/*  396 */         if (!(
/*  396 */           localSegment = (Segment)((Iterator)localObject1).next())
/*  396 */           .g())
/*  397 */           localSegment.a().restructBB(true);
/*      */       }
/*      */   }
/*      */ 
/*      */   private void remove(int paramInt1, int paramInt2, int paramInt3, ah paramah, boolean paramBoolean, HashSet paramHashSet)
/*      */   {
/*  405 */     if (((
/*  405 */       paramInt1 = getSegmentBuffer().a(new q(paramInt1, paramInt2, paramInt3), false)) != null) && 
/*  405 */       (paramInt1.a() != 0)) {
/*  406 */       paramInt2 = paramInt1.a();
/*  407 */       paramInt3 = 0;
/*  408 */       if (((1 == paramInt2) || (((this instanceof ki)) && (getTotalElements() == 1))) && (
/*  409 */         (paramInt1.a(this.tmpVisPos).equals(kd.jdField_a_of_type_Q)) || ((this instanceof ki)))) {
/*  410 */         paramInt3 = 1;
/*  411 */         if (!isOnServer()) {
/*  412 */           if (getTotalElements() == 1)
/*      */           {
/*  414 */             String str = (this instanceof kd) ? "Are you sure you want to delete the core?\n\nThis will destroy the ship,\nand you will get the core.\n" : "Are you sure you want to delete the last block?\n\nThis will destroy the station!\n\nNo refunds!!";
/*      */ 
/*  418 */             new jw(this, (ct)getState(), "Delete last block?", str, paramInt1, paramInt2, paramah, paramBoolean)
/*  454 */               .c();
/*      */           } else {
/*  456 */             ((ct)getState()).a().d("Cannot delete core!\nIt must be the last\nelement to delete");
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  461 */       if (paramInt3 == 0) {
/*  462 */         removeBlock(paramInt1, paramInt2, paramah, paramBoolean);
/*  463 */         paramHashSet.add(paramInt1.a());
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private short removeBlock(le paramle, short paramShort, ah paramah, boolean paramBoolean)
/*      */   {
/*  478 */     if (paramle.a().a(paramle.a(this.tmpLocalPos), paramBoolean))
/*      */     {
/*  479 */       this.lastEditBlocks = System.currentTimeMillis();
/*  480 */       ((mw)paramle.a()).a(System.currentTimeMillis());
/*      */     }
/*  482 */     paramle.a();
/*  483 */     paramle.a(getState().getId());
/*  484 */     getNetworkObject().modificationBuffer.add(new RemoteSegmentPiece(paramle, this, getNetworkObject()));
/*  485 */     assert (getNetworkObject().modificationBuffer.hasChanged());
/*  486 */     assert (getNetworkObject().isChanged());
/*      */ 
/*  489 */     paramah.a(paramShort);
/*      */ 
/*  492 */     return paramShort;
/*      */   }
/*      */ 
/*      */   public int getNearestIntersection(short paramShort, Vector3f paramVector3f1, Vector3f paramVector3f2, ag paramag, int paramInt1, boolean paramBoolean, ju paramju, q paramq, int paramInt2, float paramFloat, az paramaz)
/*      */   {
/*  500 */     if ((paramShort == 291) && (getElementClassCountMap().a((short)291) > 0)) {
/*  501 */       if (!isOnServer()) {
/*  502 */         ((ct)getState()).a().b("ERROR\nOnly one Faction block is permitted\nper structure");
/*      */       }
/*      */ 
/*  507 */       return 0;
/*      */     }
/*  509 */     if (!allowedType(paramShort)) {
/*  510 */       System.err.println("Type is not allowed on " + this + "; " + paramShort);
/*  511 */       return 0;
/*      */     }
/*      */ 
/*  514 */     if (System.currentTimeMillis() - this.lastEditBlocks < 50L) {
/*  515 */       return 0;
/*      */     }
/*      */ 
/*  518 */     q localq1 = new q();
/*  519 */     le localle = null;
/*  520 */     q localq2 = new q();
/*      */     try
/*      */     {
/*  524 */       localle = getNextToNearestPiece(paramVector3f1, paramVector3f2, localq2, paramFloat, paramBoolean, paramq, localq1);
/*      */ 
/*  526 */       if ((paramaz.jdField_a_of_type_Int > 0) && (localle != null)) {
/*  527 */         paramVector3f1 = localle.a(new q());
/*  528 */         switch (paramaz.jdField_a_of_type_Int) {
/*      */         case 1:
/*  530 */           System.err.println("SYM XY PLANE SET");
/*  531 */           paramaz.jdField_a_of_type_Q.c = paramVector3f1.c;
/*  532 */           paramaz.jdField_a_of_type_Boolean = true;
/*  533 */           break;
/*      */         case 2:
/*  535 */           System.err.println("SYM XZ PLANE SET");
/*  536 */           paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int = paramVector3f1.jdField_b_of_type_Int;
/*  537 */           paramaz.jdField_b_of_type_Boolean = true;
/*  538 */           break;
/*      */         case 4:
/*  540 */           System.err.println("SYM YZ PLANE SET");
/*  541 */           paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int = paramVector3f1.jdField_a_of_type_Int;
/*  542 */           paramaz.jdField_c_of_type_Boolean = true;
/*      */         case 3:
/*      */         }
/*  545 */         paramaz.jdField_a_of_type_Int = 0;
/*  546 */         return 0;
/*      */       }
/*      */ 
/*  549 */       System.err.println("[CLIENT][EDIT] PLACING AT " + localle + "; size: " + paramq + " --> " + localq1 + ": PHY: " + (localle != null ? localle.a().a().getPhysicsDataContainer().getObject() : ""));
/*      */     }
/*      */     catch (CannotImmediateRequestOnClientException paramVector3f1) {
/*  552 */       System.err.println("[CLIENT][WARNING] Cannot ADD! segment not yet in buffer " + paramVector3f1.a() + ". -> requested");
/*  553 */       return 0;
/*      */     }
/*      */ 
/*  556 */     if (localle != null) {
/*  557 */       if (paramju != null) { paramVector3f2 = localle.a(new q()); if (paramVector3f2.jdField_a_of_type_Int != paramVector3f1.jdField_a_of_type_ArrayOfInt[0]) System.err.println("X valid " + paramVector3f2.jdField_a_of_type_Int + "/" + paramVector3f1.jdField_a_of_type_ArrayOfInt[0] + " "); if (paramVector3f2.jdField_a_of_type_Int != paramVector3f1.jdField_a_of_type_ArrayOfInt[0]) { if (paramVector3f2.jdField_b_of_type_Int != paramVector3f1.jdField_a_of_type_ArrayOfInt[1]) System.err.println("Y valid " + paramVector3f2.jdField_b_of_type_Int + "/" + paramVector3f1.jdField_a_of_type_ArrayOfInt[1] + " "); if (paramVector3f2.jdField_b_of_type_Int != paramVector3f1.jdField_a_of_type_ArrayOfInt[1]) if (paramVector3f1.jdField_a_of_type_ArrayOfBoolean[2] != 0) if (paramVector3f2.c != paramVector3f1.jdField_a_of_type_ArrayOfInt[2]) System.err.println("Z valid " + paramVector3f2.c + "/" + paramVector3f1.jdField_a_of_type_ArrayOfInt[2] + " ");  
/*      */         }
/*  557 */         if ((paramVector3f2.c != paramVector3f1.jdField_a_of_type_ArrayOfInt[2] ? 0 : paramVector3f1.jdField_a_of_type_ArrayOfBoolean[1] != 0 ? 0 : (paramVector3f1 = paramju).jdField_a_of_type_ArrayOfBoolean[0] != 0 ? 0 : 1) == 0)
/*  558 */           return 0;
/*      */       }
/*  560 */       if (localle.a().g()) {
/*  561 */         getSegmentProvider().a()
/*  562 */           .assignData(localle.a());
/*      */       }
/*      */ 
/*  565 */       System.err.println("adding new element to " + getClass().getSimpleName() + " at " + localle + ", type " + paramShort);
/*  566 */       paramVector3f1 = new int[2];
/*      */ 
/*  568 */       paramVector3f2 = localle.a(new q());
/*  569 */       paramVector3f1[1] = paramInt2;
/*      */ 
/*  571 */       paramju = localq1.jdField_a_of_type_Int < 0 ? paramVector3f2.jdField_a_of_type_Int + localq1.jdField_a_of_type_Int + 1 : paramVector3f2.jdField_a_of_type_Int;
/*  572 */       paramq = localq1.jdField_b_of_type_Int < 0 ? paramVector3f2.jdField_b_of_type_Int + localq1.jdField_b_of_type_Int + 1 : paramVector3f2.jdField_b_of_type_Int;
/*  573 */       paramInt2 = localq1.c < 0 ? paramVector3f2.c + localq1.c + 1 : paramVector3f2.c;
/*      */ 
/*  575 */       paramFloat = localq1.jdField_a_of_type_Int < 0 ? paramVector3f2.jdField_a_of_type_Int + 1 : paramVector3f2.jdField_a_of_type_Int + localq1.jdField_a_of_type_Int;
/*  576 */       int j = localq1.jdField_b_of_type_Int < 0 ? paramVector3f2.jdField_b_of_type_Int + 1 : paramVector3f2.jdField_b_of_type_Int + localq1.jdField_b_of_type_Int;
/*  577 */       paramVector3f2 = localq1.c < 0 ? paramVector3f2.c + 1 : paramVector3f2.c + localq1.c;
/*      */ 
/*  580 */       for (; (paramInt2 < paramVector3f2) && (paramVector3f1[1] > 0); paramInt2++) {
/*  581 */         for (int i = paramq; (i < j) && (paramVector3f1[1] > 0); i++) {
/*  582 */           for (float f = paramju; (f < paramFloat) && (paramVector3f1[1] > 0); f++)
/*      */           {
/*  584 */             build(f, i, paramInt2, paramShort, paramInt1, paramBoolean, paramag, localq2, paramVector3f1);
/*      */             int m;
/*  613 */             if ((paramaz.jdField_a_of_type_Boolean) && (!paramaz.jdField_b_of_type_Boolean) && (!paramaz.jdField_c_of_type_Boolean))
/*      */             {
/*  616 */               m = (paramaz.jdField_a_of_type_Q.c - 
/*  616 */                 paramInt2 << 1) + paramaz.jdField_b_of_type_Int;
/*      */ 
/*  618 */               build(f, i, paramInt2 + m, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, false, false), paramBoolean, paramag, localq2, paramVector3f1);
/*      */             }
/*  620 */             else if ((!paramaz.jdField_a_of_type_Boolean) && (paramaz.jdField_b_of_type_Boolean) && (!paramaz.jdField_c_of_type_Boolean))
/*      */             {
/*  623 */               m = (paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int - 
/*  623 */                 i << 1) + paramaz.jdField_b_of_type_Int;
/*      */ 
/*  625 */               build(f, i + m, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, true, false), paramBoolean, paramag, localq2, paramVector3f1);
/*      */             }
/*  627 */             else if ((!paramaz.jdField_a_of_type_Boolean) && (!paramaz.jdField_b_of_type_Boolean) && (paramaz.jdField_c_of_type_Boolean))
/*      */             {
/*  631 */               m = (paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int - 
/*  631 */                 f << 1) + paramaz.jdField_b_of_type_Int;
/*      */ 
/*  633 */               build(f + m, i, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, false, true), paramBoolean, paramag, localq2, paramVector3f1);
/*      */             }
/*      */             else
/*      */             {
/*      */               int k;
/*      */               int n;
/*  636 */               if ((paramaz.jdField_a_of_type_Boolean) && (paramaz.jdField_b_of_type_Boolean) && (!paramaz.jdField_c_of_type_Boolean))
/*      */               {
/*  638 */                 k = paramaz.jdField_a_of_type_Q.c;
/*  639 */                 m = paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int;
/*      */ 
/*  641 */                 n = (k - paramInt2 << 1) + paramaz.jdField_b_of_type_Int;
/*  642 */                 k = (m - i << 1) + paramaz.jdField_b_of_type_Int;
/*      */ 
/*  644 */                 build(f, i, paramInt2 + n, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, false, false), paramBoolean, paramag, localq2, paramVector3f1);
/*  645 */                 build(f, i + k, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, true, false), paramBoolean, paramag, localq2, paramVector3f1);
/*  646 */                 build(f, i + k, paramInt2 + n, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, true, false), paramBoolean, paramag, localq2, paramVector3f1);
/*      */               }
/*  649 */               else if ((paramaz.jdField_a_of_type_Boolean) && (!paramaz.jdField_b_of_type_Boolean) && (paramaz.jdField_c_of_type_Boolean))
/*      */               {
/*  651 */                 k = paramaz.jdField_a_of_type_Q.c;
/*  652 */                 m = paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int;
/*      */ 
/*  654 */                 n = (k - paramInt2 << 1) + paramaz.jdField_b_of_type_Int;
/*  655 */                 k = (m - f << 1) + paramaz.jdField_b_of_type_Int;
/*      */ 
/*  657 */                 build(f, i, paramInt2 + n, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, false, false), paramBoolean, paramag, localq2, paramVector3f1);
/*  658 */                 build(f + k, i, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, false, true), paramBoolean, paramag, localq2, paramVector3f1);
/*  659 */                 build(f + k, i, paramInt2 + n, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, false, true), paramBoolean, paramag, localq2, paramVector3f1);
/*      */               }
/*  662 */               else if ((!paramaz.jdField_a_of_type_Boolean) && (paramaz.jdField_b_of_type_Boolean) && (paramaz.jdField_c_of_type_Boolean))
/*      */               {
/*  664 */                 k = paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int;
/*  665 */                 m = paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int;
/*      */ 
/*  667 */                 n = (k - i << 1) + paramaz.jdField_b_of_type_Int;
/*  668 */                 k = (m - f << 1) + paramaz.jdField_b_of_type_Int;
/*      */ 
/*  670 */                 build(f, i + n, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, true, false), paramBoolean, paramag, localq2, paramVector3f1);
/*  671 */                 build(f + k, i, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, false, true), paramBoolean, paramag, localq2, paramVector3f1);
/*  672 */                 build(f + k, i + n, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, true, true), paramBoolean, paramag, localq2, paramVector3f1);
/*      */               }
/*  675 */               else if ((paramaz.jdField_a_of_type_Boolean) && (paramaz.jdField_b_of_type_Boolean) && (paramaz.jdField_c_of_type_Boolean))
/*      */               {
/*  677 */                 k = paramaz.jdField_a_of_type_Q.c;
/*  678 */                 m = paramaz.jdField_b_of_type_Q.jdField_b_of_type_Int;
/*  679 */                 n = paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int;
/*      */ 
/*  681 */                 k = (k - paramInt2 << 1) + paramaz.jdField_b_of_type_Int;
/*  682 */                 m = (m - i << 1) + paramaz.jdField_b_of_type_Int;
/*  683 */                 n = (n - f << 1) + paramaz.jdField_b_of_type_Int;
/*      */ 
/*  685 */                 build(f + n, i, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, false, true), paramBoolean, paramag, localq2, paramVector3f1);
/*  686 */                 build(f, i + m, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, true, false), paramBoolean, paramag, localq2, paramVector3f1);
/*  687 */                 build(f, i, paramInt2 + k, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, false, false), paramBoolean, paramag, localq2, paramVector3f1);
/*      */ 
/*  689 */                 build(f + n, i + m, paramInt2, paramShort, az.a(paramShort, paramBoolean, paramInt1, false, true, true), paramBoolean, paramag, localq2, paramVector3f1);
/*  690 */                 build(f + n, i, paramInt2 + k, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, false, true), paramBoolean, paramag, localq2, paramVector3f1);
/*  691 */                 build(f, i + m, paramInt2 + k, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, true, false), paramBoolean, paramag, localq2, paramVector3f1);
/*      */ 
/*  693 */                 build(f + n, i + m, paramInt2 + k, paramShort, az.a(paramShort, paramBoolean, paramInt1, true, true, true), paramBoolean, paramag, localq2, paramVector3f1);
/*      */               }
/*      */             }
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  702 */       return paramVector3f1[0];
/*      */     }
/*  704 */     System.err.println("no intersection found in world currentSegmentContext");
/*      */ 
/*  707 */     return 0;
/*      */   }
/*      */ 
/*      */   private void build(int paramInt1, int paramInt2, int paramInt3, short paramShort, int paramInt4, boolean paramBoolean, ag paramag, q paramq, int[] paramArrayOfInt) {
/*  711 */     if (paramArrayOfInt[1] > 0)
/*      */     {
/*  713 */       if ((
/*  713 */         paramInt1 = getSegmentBuffer().a(new q(paramInt1, paramInt2, paramInt3), false)) != null)
/*      */       {
/*  716 */         paramInt2 = 0; if (paramInt1.a().a(paramShort, paramInt1.a(this.tmpLocalPos), paramInt4, paramBoolean))
/*      */         {
/*  717 */           this.lastEditBlocks = System.currentTimeMillis();
/*  718 */           ((mw)paramInt1.a()).a(System.currentTimeMillis());
/*      */ 
/*  720 */           paramInt1.a();
/*  721 */           paramInt2 = paramInt1.a(new q());
/*      */ 
/*  723 */           paramag.a(paramInt2, paramq, paramShort);
/*      */ 
/*  725 */           paramInt1.a(getState().getId());
/*      */ 
/*  727 */           getNetworkObject().modificationBuffer.add(new RemoteSegmentPiece(paramInt1, this, getNetworkObject()));
/*      */ 
/*  730 */           assert (getNetworkObject().modificationBuffer.hasChanged());
/*  731 */           assert (getNetworkObject().isChanged());
/*  732 */           paramArrayOfInt[0] += 1;
/*  733 */           paramArrayOfInt[1] -= 1;
/*  734 */           return;
/*  735 */         }System.err.println("Block at " + paramInt1 + " already exists");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public le getNearestPiece(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, q paramq1, q paramq2)
/*      */   {
/*  744 */     Vector3f localVector3f = new Vector3f();
/*  745 */     paramVector3f2.scale(paramFloat);
/*      */ 
/*  747 */     localVector3f.add(paramVector3f1, paramVector3f2);
/*      */ 
/*  752 */     (
/*  754 */       paramVector3f2 = new CubeRayCastResult(paramVector3f1, localVector3f, Boolean.valueOf(false), this))
/*  754 */       .setRespectShields(false);
/*      */ 
/*  756 */     paramVector3f2.onlyCubeMeshes = true;
/*  757 */     paramVector3f2.setIgnoereNotPhysical(true);
/*      */ 
/*  763 */     if (((
/*  763 */       paramVector3f1 = getPhysics().testRayCollisionPoint(paramVector3f1, localVector3f, paramVector3f2, false))
/*  763 */       .hasHit()) && (paramVector3f1.collisionObject != null) && 
/*  764 */       ((paramVector3f1 instanceof CubeRayCastResult)) && (((CubeRayCastResult)paramVector3f1).getSegment() != null))
/*      */     {
/*  766 */       (
/*  767 */         paramVector3f1 = (CubeRayCastResult)paramVector3f1)
/*  767 */         .getSegment().a().getSegmentController();
/*  768 */       paramVector3f2 = paramVector3f1.getSegment();
/*      */ 
/*  772 */       (
/*  779 */         paramFloat = new q(paramVector3f1.getSegment().jdField_a_of_type_Q.jdField_a_of_type_Int, paramVector3f1.getSegment().jdField_a_of_type_Q.jdField_b_of_type_Int, paramVector3f1.getSegment().jdField_a_of_type_Q.c)).jdField_a_of_type_Int
/*  779 */          += paramVector3f1.cubePos.a - 8;
/*  780 */       paramFloat.jdField_b_of_type_Int += paramVector3f1.cubePos.b - 8;
/*  781 */       paramFloat.c += paramVector3f1.cubePos.c - 8;
/*      */ 
/*  791 */       getWorldTransformInverse().transform(paramVector3f1.hitPointWorld);
/*      */ 
/*  796 */       int i = Element.getSide(paramVector3f1.hitPointWorld, paramFloat);
/*      */ 
/*  798 */       System.err.println("SIDE: " + Element.getSideString(i) + ": " + paramVector3f1.hitPointWorld + "; " + paramFloat);
/*      */ 
/*  801 */       paramq2.jdField_a_of_type_Int = (-paramq2.jdField_a_of_type_Int);
/*  802 */       paramq2.jdField_b_of_type_Int = (-paramq2.jdField_b_of_type_Int);
/*  803 */       paramq2.c = (-paramq2.c);
/*      */ 
/*  805 */       switch (i) {
/*      */       case 0:
/*  807 */         paramq1.b(paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, paramq2.c);
/*  808 */         break;
/*      */       case 1:
/*  810 */         paramq1.b(-paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, paramq2.c);
/*  811 */         break;
/*      */       case 2:
/*  813 */         paramq1.b(paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, paramq2.c);
/*  814 */         break;
/*      */       case 3:
/*  816 */         paramq1.b(paramq2.jdField_a_of_type_Int, -paramq2.jdField_b_of_type_Int, paramq2.c);
/*  817 */         break;
/*      */       case 4:
/*  819 */         paramq1.b(paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, paramq2.c);
/*  820 */         break;
/*      */       case 5:
/*  822 */         paramq1.b(paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, -paramq2.c);
/*  823 */         break;
/*      */       default:
/*  824 */         System.err.println("[BUILDMODEDRAWER] WARNING: NO SIDE recognized!!!");
/*      */       }
/*      */ 
/*  830 */       return new le(paramVector3f2, paramVector3f1.cubePos);
/*      */     }
/*      */ 
/*  835 */     return null;
/*      */   }
/*      */ 
/*      */   public NetworkSegmentController getNetworkObject() {
/*  839 */     return super.getNetworkObject();
/*      */   }
/*      */ 
/*      */   public le getNextToNearestPiece(Vector3f paramVector3f1, Vector3f paramVector3f2, q paramq1, float paramFloat, boolean paramBoolean, q paramq2, q paramq3)
/*      */   {
/*  845 */     paramBoolean = new Vector3f();
/*  846 */     paramVector3f2.normalize();
/*  847 */     paramVector3f2.scale(paramFloat);
/*      */ 
/*  849 */     paramBoolean.add(paramVector3f1, paramVector3f2);
/*      */ 
/*  851 */     (
/*  853 */       paramVector3f2 = new CubeRayCastResult(paramVector3f1, paramBoolean, Boolean.valueOf(false), this))
/*  853 */       .setRespectShields(false);
/*  854 */     paramVector3f2.onlyCubeMeshes = true;
/*  855 */     paramVector3f2.setIgnoereNotPhysical(true);
/*      */ 
/*  861 */     if (((
/*  861 */       paramVector3f1 = getPhysics().testRayCollisionPoint(paramVector3f1, paramBoolean, paramVector3f2, false)) != null) && 
/*  861 */       (paramVector3f1.hasHit()) && ((paramVector3f1 instanceof CubeRayCastResult)))
/*      */     {
/*  877 */       if ((
/*  877 */         paramVector3f2 = (CubeRayCastResult)paramVector3f1)
/*  877 */         .getSegment() == null) {
/*  878 */         System.err.println("CUBERESULT SEGMENT NULL");
/*  879 */         return null;
/*      */       }
/*      */ 
/*  882 */       paramFloat = new q(paramVector3f2.getSegment().jdField_a_of_type_Q.jdField_a_of_type_Int, paramVector3f2.getSegment().jdField_a_of_type_Q.jdField_b_of_type_Int, paramVector3f2.getSegment().jdField_a_of_type_Q.c);
/*      */ 
/*  884 */       paramq1.b(paramVector3f2.getSegment().jdField_a_of_type_Q.jdField_a_of_type_Int + paramVector3f2.cubePos.a, paramVector3f2.getSegment().jdField_a_of_type_Q.jdField_b_of_type_Int + paramVector3f2.cubePos.b, paramVector3f2.getSegment().jdField_a_of_type_Q.c + paramVector3f2.cubePos.c);
/*      */ 
/*  889 */       paramFloat.jdField_a_of_type_Int += paramVector3f2.cubePos.a - 8;
/*  890 */       paramFloat.jdField_b_of_type_Int += paramVector3f2.cubePos.b - 8;
/*  891 */       paramFloat.c += paramVector3f2.cubePos.c - 8;
/*      */ 
/*  900 */       if (((ct)getState()).a() == getSectorId()) {
/*  901 */         getWorldTransformInverse().transform(paramVector3f1.hitPointWorld);
/*      */       } else {
/*  903 */         (
/*  904 */           paramq1 = new Transform(getWorldTransformClient()))
/*  904 */           .inverse();
/*  905 */         paramq1.transform(paramVector3f1.hitPointWorld);
/*      */       }
/*      */ 
/*  915 */       paramq1 = Element.getSide(paramVector3f2.hitPointWorld, paramFloat);
/*      */ 
/*  917 */       System.err.println("SIDE: " + Element.getSideString(paramq1) + ": " + paramVector3f2.hitPointWorld + "; " + paramFloat);
/*      */ 
/*  920 */       switch (paramq1)
/*      */       {
/*      */       case 0:
/*      */         float tmp422_420 = paramFloat; tmp422_420.jdField_a_of_type_Int = ((int)(tmp422_420.jdField_a_of_type_Int + 1.0F));
/*  922 */         paramq3.b(paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, paramq2.c);
/*  923 */         break;
/*      */       case 1:
/*      */         float tmp458_456 = paramFloat; tmp458_456.jdField_a_of_type_Int = ((int)(tmp458_456.jdField_a_of_type_Int - 1.0F));
/*  925 */         paramq3.b(-paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, paramq2.c);
/*  926 */         break;
/*      */       case 2:
/*      */         float tmp495_493 = paramFloat; tmp495_493.jdField_b_of_type_Int = ((int)(tmp495_493.jdField_b_of_type_Int + 1.0F));
/*  928 */         paramq3.b(paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, paramq2.c);
/*  929 */         break;
/*      */       case 3:
/*      */         float tmp531_529 = paramFloat; tmp531_529.jdField_b_of_type_Int = ((int)(tmp531_529.jdField_b_of_type_Int - 1.0F));
/*  931 */         paramq3.b(paramq2.jdField_a_of_type_Int, -paramq2.jdField_b_of_type_Int, paramq2.c);
/*  932 */         break;
/*      */       case 4:
/*      */         float tmp568_566 = paramFloat; tmp568_566.c = ((int)(tmp568_566.c + 1.0F));
/*  934 */         paramq3.b(paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, paramq2.c);
/*  935 */         break;
/*      */       case 5:
/*      */         float tmp604_602 = paramFloat; tmp604_602.c = ((int)(tmp604_602.c - 1.0F));
/*  937 */         paramq3.b(paramq2.jdField_a_of_type_Int, paramq2.jdField_b_of_type_Int, -paramq2.c);
/*  938 */         break;
/*      */       default:
/*  939 */         System.err.println("[BUILDMODEDRAWER] WARNING: NO SIDE recognized!!!");
/*      */       }
/*      */ 
/*  944 */       paramFloat.a(8, 8, 8);
/*      */ 
/*  955 */       paramVector3f1 = new le();
/*      */ 
/*  958 */       if (((
/*  958 */         paramVector3f1 = getSegmentBuffer().a(paramFloat, true, paramVector3f1)) != null) && 
/*  958 */         (paramVector3f1.a().g())) {
/*  959 */         getSegmentProvider().a()
/*  960 */           .assignData(paramVector3f1.a());
/*      */       }
/*      */ 
/*  965 */       paramVector3f2 = 0;
/*  966 */       paramq1 = new jO();
/*      */       try
/*      */       {
/*  969 */         if ((paramVector3f1 != null) && (getCollisionChecker().a(paramVector3f1, paramq1)))
/*      */         {
/*  971 */           paramVector3f2 = 1;
/*      */         }
/*      */       }
/*      */       catch (Exception localException) {
/*  975 */         localException.printStackTrace();
/*      */       }
/*      */ 
/*  977 */       if (!getDockingController().a().isEmpty())
/*      */       {
/*  980 */         if (((this instanceof ld)) && ((((ld)this).a() instanceof DockingBlockManagerInterface)))
/*  981 */           for (paramBoolean = getDockingController().a().iterator(); paramBoolean.hasNext(); ) { paramq2 = (ElementDocking)paramBoolean.next();
/*      */ 
/*  984 */             for (paramq3 = ((DockingBlockManagerInterface)((ld)this).a())
/*  984 */               .getDockingBlock().iterator(); paramq3.hasNext(); )
/*  985 */               for (localIterator = ((ManagerModuleCollection)paramq3.next())
/*  985 */                 .getCollectionManagers().iterator(); localIterator.hasNext(); )
/*      */               {
/*      */                 DockingBlockCollectionManager localDockingBlockCollectionManager;
/*  987 */                 if (((
/*  987 */                   localDockingBlockCollectionManager = (DockingBlockCollectionManager)localIterator.next())
/*  987 */                   .getControllerElement().equals(paramq2.to)) && (!localDockingBlockCollectionManager.isValidPositionToBuild(paramFloat)))
/*  988 */                   throw new BlockedByDockedElementException(paramq2.to);
/*      */               }
/*      */           }
/*      */       }
/*      */       Iterator localIterator;
/*  996 */       if (paramVector3f2 != 0) {
/*  997 */         throw new ElementPositionBlockedException(paramq1.a);
/*      */       }
/*      */ 
/* 1002 */       return paramVector3f1;
/*      */     }
/*      */ 
/* 1005 */     return null;
/*      */   }
/*      */ 
/*      */   public void handleBeingSalvaged(BeamHandler.BeamState paramBeamState, jq paramjq, Vector3f paramVector3f, CubeRayCastResult paramCubeRayCastResult, int paramInt)
/*      */   {
/* 1013 */     if ((this instanceof km))
/* 1014 */       ((km)this).a(true);
/*      */   }
/*      */ 
/*      */   public void handleExplosion(Transform paramTransform, float paramFloat1, float paramFloat2, lb paramlb, byte paramByte)
/*      */   {
/* 1025 */     System.err.println("[HIT][EXPLOSION] Handling explosion: " + this + " " + paramTransform + ", R: " + paramFloat1 + ", dam " + paramFloat2 + " from " + paramlb);
/*      */ 
/* 1027 */     if ((this instanceof km)) {
/* 1028 */       ((km)this).a(true);
/*      */     }
/*      */ 
/* 1031 */     if ((paramByte != 1) && 
/* 1032 */       ((this instanceof ld)) && ((((ld)this).a() instanceof ShieldContainerInterface)))
/*      */     {
/* 1035 */       if ((
/* 1035 */         paramByte = (ShieldContainerInterface)((ld)this).a())
/* 1035 */         .getShieldManager().getShields() > 0.0D) {
/* 1036 */         double d1 = paramFloat2 * Math.max(1.0F, paramFloat1 / 2.0F);
/* 1037 */         double d2 = paramByte.getShieldManager().getShields();
/*      */ 
/* 1039 */         if ((
/* 1039 */           paramFloat2 = (float)paramByte.handleShieldHit(paramTransform.origin, null, paramFloat2 * Math.max(1.0F, paramFloat1 / 2.0F))) <= 
/* 1039 */           0.0F) {
/* 1040 */           System.err.println("[EXPLOSION] " + this + " Shield completely absorbed damage: SHIELDS: " + d2 + " -> " + paramByte.getShieldManager().getShields() + " DAM: " + d1 + " -> " + paramFloat2);
/* 1041 */           return;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1048 */     paramByte = paramFloat2;
/*      */ 
/* 1050 */     if (isOnServer())
/*      */     {
/*      */       Object localObject1;
/* 1051 */       if (getFactionId() > 0)
/*      */       {
/* 1054 */         if ((
/* 1054 */           localObject1 = ((vf)getState()).a().a(getFactionId())) != null)
/*      */         {
/* 1055 */           ((lP)localObject1).a(paramlb);
/*      */         }
/* 1057 */         else System.err.println("[SERVER][EDITABLESEGMENTCONTROLLER] ON HIT: faction not found: " + getFactionId());
/*      */ 
/*      */       }
/*      */ 
/* 1061 */       if (((
/* 1061 */         localObject1 = ((vg)getState()).a().getSector(getSectorId())) != null) && 
/* 1061 */         (((mx)localObject1).b())) {
/* 1062 */         if ((paramlb != null) && ((paramlb instanceof cw))) {
/* 1063 */           localObject2 = ((cw)paramlb).a();
/* 1064 */           for (int i = 0; i < ((ArrayList)localObject2).size(); i++) {
/* 1065 */             lE locallE = (lE)((ArrayList)localObject2).get(i);
/* 1066 */             if (System.currentTimeMillis() - locallE.a > 5000L) {
/* 1067 */               locallE.a = System.currentTimeMillis();
/* 1068 */               locallE.a(new ServerMessage("This Sector is Protected!", 2, locallE.getId()));
/*      */             }
/*      */           }
/*      */         }
/* 1072 */         return;
/*      */       }
/*      */ 
/* 1075 */       if (!canAttack(paramlb)) {
/* 1076 */         return;
/*      */       }
/*      */ 
/* 1080 */       Object localObject2 = new lc(this, paramTransform, paramFloat1, paramByte, paramlb);
/* 1081 */       this.explosionOrdersRunning.add(localObject2);
/* 1082 */       getState().getThreadPool().execute((Runnable)localObject2);
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean canAttack(lb paramlb)
/*      */   {
/* 1090 */     if ((isHomeBase()) || ((getDockingController().b()) && (getDockingController().a().to.a().a().isHomeBaseFor(getFactionId()))))
/*      */     {
/* 1092 */       if ((paramlb != null) && ((paramlb instanceof cw))) {
/* 1093 */         paramlb = ((cw)paramlb).a();
/* 1094 */         for (int i = 0; i < paramlb.size(); i++) {
/* 1095 */           lE locallE = (lE)paramlb.get(i);
/* 1096 */           if (System.currentTimeMillis() - locallE.a > 5000L) {
/* 1097 */             locallE.a = System.currentTimeMillis();
/* 1098 */             locallE.a(new ServerMessage("Cannot attack a faction's\nhome base!", 2, locallE.getId()));
/*      */           }
/*      */         }
/*      */       }
/* 1102 */       return false;
/*      */     }
/* 1104 */     return true;
/*      */   }
/*      */ 
/*      */   public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, lb paramlb, float paramFloat) {
/* 1108 */     CubeRayCastResult localCubeRayCastResult = (CubeRayCastResult)paramClosestRayResultCallback;
/* 1109 */     assert (localCubeRayCastResult != null);
/* 1110 */     assert (localCubeRayCastResult.getSegment() != null) : localCubeRayCastResult.hasHit();
/* 1111 */     assert (localCubeRayCastResult.getSegment().a() != null) : localCubeRayCastResult.getSegment().jdField_a_of_type_Q;
/*      */ 
/* 1113 */     if ((localCubeRayCastResult == null) || (localCubeRayCastResult.getSegment() == null) || (localCubeRayCastResult.getSegment().a() == null)) {
/* 1114 */       return;
/*      */     }
/*      */ 
/* 1117 */     if ((this instanceof km)) {
/* 1118 */       ((km)this).a(true);
/*      */     }
/* 1120 */     SegmentData localSegmentData = localCubeRayCastResult.getSegment().a();
/*      */ 
/* 1123 */     int i = SegmentData.getInfoIndex(localCubeRayCastResult.cubePos);
/* 1124 */     short s = localSegmentData.getType(i);
/* 1125 */     localSegmentData.getHitpoints(i);
/*      */ 
/* 1127 */     le localle = new le(localCubeRayCastResult.getSegment(), localCubeRayCastResult.cubePos);
/*      */ 
/* 1129 */     if (isOnServer())
/*      */     {
/*      */       Object localObject;
/* 1130 */       if (getFactionId() > 0)
/*      */       {
/* 1133 */         if ((
/* 1133 */           localObject = ((vf)getState()).a().a(getFactionId())) != null)
/*      */         {
/* 1134 */           ((lP)localObject).a(paramlb);
/*      */         }
/* 1136 */         else System.err.println("[SERVER][EDITABLESEGMENTCONTROLLER] ON HIT: faction not found: " + getFactionId());
/*      */ 
/*      */       }
/*      */ 
/* 1141 */       if (((
/* 1141 */         localObject = ((vg)getState()).a().getSector(getSectorId())) != null) && 
/* 1141 */         (((mx)localObject).b())) {
/* 1142 */         if ((paramlb != null) && ((paramlb instanceof cw))) {
/* 1143 */           localObject = ((cw)paramlb).a();
/* 1144 */           for (paramlb = 0; paramlb < ((ArrayList)localObject).size(); paramlb++) {
/* 1145 */             paramFloat = (lE)((ArrayList)localObject).get(paramlb);
/* 1146 */             if (System.currentTimeMillis() - paramFloat.a > 5000L) {
/* 1147 */               paramFloat.a = System.currentTimeMillis();
/* 1148 */               paramFloat.a(new ServerMessage("This Sector is Protected!", 2, paramFloat.getId()));
/*      */             }
/*      */           }
/*      */         }
/* 1152 */         return;
/*      */       }
/* 1154 */       if (!canAttack(paramlb))
/*      */         return;
/*      */       int j;
/* 1159 */       if ((
/* 1159 */         j = damageElement(s, i, localSegmentData, (int)paramFloat)) > 0)
/*      */       {
/* 1160 */         onDamageServer(j, paramlb);
/*      */       }
/*      */ 
/* 1163 */       if (localSegmentData.getHitpoints(i) <= 0)
/*      */       {
/* 1167 */         if ((s == getCoreType()) && (localle.a(this.absPosCache).equals(kd.jdField_a_of_type_Q))) {
/* 1168 */           localSegmentData.setHitpoints(i, (short)0);
/* 1169 */           onCoreDestroyed(paramlb);
/* 1170 */           onCoreHitAlreadyDestroyed(paramFloat);
/*      */         }
/*      */         else
/*      */         {
/* 1174 */           localCubeRayCastResult.getSegment().a(localCubeRayCastResult.cubePos, true);
/*      */         }
/*      */ 
/* 1177 */         if (isEnterable(s)) {
/* 1178 */           forceCharacterExit(localle);
/*      */         }
/*      */       }
/*      */ 
/* 1182 */       ((mw)localCubeRayCastResult.getSegment()).a(System.currentTimeMillis());
/* 1183 */       localle.a();
/* 1184 */       getNetworkObject().modificationBuffer.add(new RemoteSegmentPiece(localle, this, getNetworkObject()));
/*      */     }
/* 1186 */     if (!isOnServer())
/*      */     {
/* 1188 */       ElementInformation localElementInformation = ElementKeyMap.getInfo(s);
/* 1189 */       int k = (int)Math.max(0.0D, paramFloat - Math.ceil(paramFloat * localElementInformation.getArmourPercent()));
/*      */ 
/* 1191 */       (
/* 1192 */         paramlb = new Transform())
/* 1192 */         .setIdentity();
/* 1193 */       paramlb.origin.set(paramClosestRayResultCallback.hitPointWorld);
/* 1194 */       ij.a.add(new eH(paramlb, String.valueOf(k), 1.0F, 0.0F, 0.0F));
/*      */ 
/* 1196 */       ((ct)getState())
/* 1197 */         .a().a().a(paramClosestRayResultCallback);
/* 1198 */       if (k < 300) {
/* 1199 */         xe.a("0022_spaceship enemy - hit small explosion small enemy ship blow up", paramlb, 5.0F); return;
/* 1200 */       }if (k < 600) {
/* 1201 */         xe.a("0022_spaceship enemy - hit medium explosion medium enemy ship blow up", paramlb, 10.0F); return;
/*      */       }
/* 1203 */       xe.a("0022_spaceship enemy - hit large explosion big enemy ship blow up", paramlb, 30.0F);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void onCoreHitAlreadyDestroyed(float paramFloat)
/*      */   {
/*      */   }
/*      */ 
/*      */   public void handleHitMissile(ln paramln, Transform paramTransform)
/*      */   {
/* 1220 */     handleExplosion(paramTransform, paramln.a(), paramln.a(), paramln.a(), (byte)0);
/*      */   }
/*      */ 
/*      */   public void handleMovingInput(xq paramxq, lA paramlA)
/*      */   {
/*      */   }
/*      */ 
/*      */   public int handleRepair(BeamHandler.BeamState paramBeamState, jq paramjq, q paramq, Vector3f paramVector3f, CubeRayCastResult paramCubeRayCastResult, xq paramxq)
/*      */   {
/* 1230 */     paramq = new le(paramCubeRayCastResult.getSegment(), paramCubeRayCastResult.cubePos);
/*      */ 
/* 1233 */     paramjq = (
/* 1233 */       paramBeamState = paramjq.getHandler().beamHit(paramBeamState, paramq)) * 
/* 1233 */       20;
/*      */ 
/* 1235 */     if ((paramBeamState > 0) && (isOnServer()))
/*      */     {
/* 1237 */       paramq.a().a();
/* 1238 */       paramVector3f = SegmentData.getInfoIndex(paramq.a(this.tmpLocalPos));
/*      */ 
/* 1240 */       paramxq = paramq.a().a().getHitpoints(paramVector3f);
/* 1241 */       int i = ElementKeyMap.getInfo(paramq.a()).getMaxHitPoints();
/* 1242 */       paramq.a().a().setHitpoints(paramVector3f, (short)Math.min(i, paramxq + paramjq));
/*      */ 
/* 1245 */       ((mw)paramq.a()).a(System.currentTimeMillis());
/* 1246 */       paramq.a();
/* 1247 */       paramq.a(getState().getId());
/* 1248 */       ((NetworkSegmentController)paramq.a().a().getNetworkObject()).modificationBuffer.add(new RemoteSegmentPiece(paramq, this, getNetworkObject()));
/*      */     }
/*      */ 
/* 1253 */     if ((paramBeamState > 0) && (!isOnServer()))
/*      */     {
/* 1255 */       ElementKeyMap.getInfo(paramq.a());
/* 1256 */       paramxq = Math.max(0, paramjq);
/*      */       Transform localTransform;
/* 1258 */       (
/* 1259 */         localTransform = new Transform())
/* 1259 */         .setIdentity();
/* 1260 */       localTransform.origin.set(paramCubeRayCastResult.hitPointWorld);
/* 1261 */       ij.a.add(new eH(localTransform, String.valueOf(paramxq), 0.0F, 1.0F, 0.0F));
/*      */     }
/*      */ 
/* 1264 */     return paramBeamState;
/*      */   }
/*      */ 
/*      */   private boolean isEnterable(short paramShort)
/*      */   {
/* 1270 */     return (paramShort != 0) && (ElementKeyMap.getInfo(paramShort).isEnterable());
/*      */   }
/*      */ 
/*      */   public boolean isRepariableFor(jI paramjI, String[] paramArrayOfString, q paramq) {
/* 1274 */     return true;
/*      */   }
/*      */ 
/*      */   public void newNetworkObject()
/*      */   {
/* 1279 */     setNetworkObject(new NetworkSegmentController(getState(), this));
/*      */   }
/*      */ 
/*      */   public void onAddedElement(short paramShort, byte paramByte1, byte paramByte2, byte paramByte3, int paramInt, Segment paramSegment, boolean paramBoolean)
/*      */   {
/* 1285 */     super.onAddedElement(paramShort, paramByte1, paramByte2, paramByte3, paramInt, paramSegment, paramBoolean);
/*      */ 
/* 1287 */     if (isOnServer())
/* 1288 */       doDimExtensionIfNecessary(paramSegment, paramByte1, paramByte2, paramByte3);
/*      */   }
/*      */ 
/*      */   public void onCollision(ManifoldPoint paramManifoldPoint, Sendable paramSendable)
/*      */   {
/*      */   }
/*      */ 
/*      */   public boolean needsManifoldCollision()
/*      */   {
/* 1305 */     return getElementClassCountMap().a((short)14) > 0;
/*      */   }
/*      */ 
/*      */   protected abstract void onCoreDestroyed(lb paramlb);
/*      */ 
/*      */   protected void onDamageServer(int paramInt, lb paramlb)
/*      */   {
/*      */   }
/*      */ 
/*      */   public void startCreatorThread()
/*      */   {
/* 1319 */     if (getCreatorThread() == null)
/* 1320 */       setCreatorThread(new kI(this));
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1328 */     return getSegmentControllerTypeString() + "(" + getId() + ")";
/*      */   }
/*      */ 
/*      */   protected abstract String getSegmentControllerTypeString();
/*      */ 
/*      */   public void updateLocal(xq paramxq)
/*      */   {
/* 1339 */     if (isMarkedForDeleteVolatile()) {
/* 1340 */       System.err.println("[EditableSegmentControleler] " + this + " MARKED TO DELETE ON " + getState());
/*      */     }
/*      */ 
/* 1344 */     if (isOnServer()) {
/* 1345 */       for (int i = 0; i < this.explosionOrdersRunning.size(); i++)
/*      */       {
/*      */         lc locallc;
/* 1347 */         if ((
/* 1347 */           locallc = (lc)this.explosionOrdersRunning.get(i))
/* 1347 */           .a()) {
/* 1348 */           locallc.a();
/* 1349 */           this.explosionOrdersRunning.remove(i);
/* 1350 */           i--;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1355 */     if (getFlagCoreDestroyedByExplosion() != null) {
/* 1356 */       System.err.println("[EditSegController] " + this + " CORE HAS BEEN DESTROYED");
/* 1357 */       if ((getFlagCoreDestroyedByExplosion() instanceof Sendable))
/* 1358 */         onCoreDestroyed((lb)getFlagCoreDestroyedByExplosion());
/*      */       else {
/* 1360 */         onCoreDestroyed(null);
/*      */       }
/* 1362 */       setFlagCoreDestroyedByExplosion(null);
/*      */     }
/* 1364 */     if (isFlagCharacterExitCheckByExplosion()) {
/*      */       try {
/* 1366 */         checkCharacterExit();
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/* 1371 */         localIOException.printStackTrace();
/*      */       }
/*      */       catch (InterruptedException localInterruptedException)
/*      */       {
/* 1371 */         localInterruptedException.printStackTrace();
/*      */       }
/*      */ 
/* 1372 */       setFlagCharacterExitCheckByExplosion(false);
/*      */     }
/* 1374 */     super.updateLocal(paramxq);
/*      */   }
/*      */ 
/*      */   public Object getFlagCoreDestroyedByExplosion()
/*      */   {
/* 1382 */     return this.flagCoreDestroyedByExplosion;
/*      */   }
/*      */ 
/*      */   public void setFlagCoreDestroyedByExplosion(Object paramObject)
/*      */   {
/* 1389 */     this.flagCoreDestroyedByExplosion = paramObject;
/*      */   }
/*      */ 
/*      */   public boolean isFlagCharacterExitCheckByExplosion()
/*      */   {
/* 1395 */     return this.flagCharacterExitCheckByExplosion;
/*      */   }
/*      */ 
/*      */   public void setFlagCharacterExitCheckByExplosion(boolean paramBoolean)
/*      */   {
/* 1402 */     this.flagCharacterExitCheckByExplosion = paramBoolean;
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.EditableSendableSegmentController
 * JD-Core Version:    0.6.2
 */