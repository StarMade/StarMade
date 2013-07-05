/*     */ package org.schema.game.common.controller.elements.dockingBlock;
/*     */ 
/*     */ import Ad;
/*     */ import Af;
/*     */ import X;
/*     */ import am;
/*     */ import aq;
/*     */ import ar;
/*     */ import au;
/*     */ import ax;
/*     */ import bi;
/*     */ import bj;
/*     */ import ct;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*     */ import jD;
/*     */ import jL;
/*     */ import jR;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import le;
/*     */ import org.schema.game.common.controller.CollectionNotLoadedException;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.BlockMetaDataDummy;
/*     */ import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;
/*     */ import org.schema.game.common.data.element.ControlElementMap;
/*     */ import q;
/*     */ import x;
/*     */ import xO;
/*     */ 
/*     */ public abstract class DockingBlockCollectionManager extends ControlBlockElementCollectionManager
/*     */ {
/*     */   public static final String TAG_ID = "A";
/*     */   public static final int defaultDockingHalfSize = 3;
/*     */   protected int enhancers;
/*  24 */   public byte orientation = 0;
/*     */   private boolean collision;
/*  27 */   private q minArea = new q(0, 0, 0);
/*  28 */   private q maxArea = new q(0, 0, 0);
/*  29 */   private static String dockingOnlineMsg = "Docking Module online!";
/*     */ 
/*  31 */   private static String dockingOfflineMsg = "Docking Module offline!\nArea is blocked!";
/*     */ 
/*  84 */   private final q min = new q();
/*  85 */   private final q max = new q();
/*     */ 
/*     */   public DockingBlockCollectionManager(le paramle, SegmentController paramSegmentController, short paramShort)
/*     */   {
/*  36 */     super(paramle, paramShort, paramSegmentController);
/*     */   }
/*     */ 
/*     */   public void getDockingArea(q paramq1, q paramq2)
/*     */   {
/*  44 */     paramq2.a(this.maxArea, this.minArea);
/*  45 */     paramq2.c();
/*  46 */     paramq2.a(3, 3, 3);
/*  47 */     paramq1.b(paramq2);
/*  48 */     paramq1.b();
/*     */ 
/*  52 */     paramq1.a(2);
/*  53 */     paramq2.a(2);
/*     */   }
/*     */ 
/*     */   public void getDockingAreaAbsolute(q paramq1, q paramq2)
/*     */   {
/*  60 */     getDockingArea(paramq1, paramq2);
/*     */ 
/*  62 */     Vector3f localVector3f = new Vector3f(Math.abs(paramq1.a - paramq2.a) / 2.0F, Math.abs(paramq1.b - paramq2.b) / 2.0F, Math.abs(paramq1.c - paramq2.c) / 2.0F);
/*     */ 
/*  64 */     q localq = new q(getControllerPos());
/*     */ 
/*  67 */     switch (org.schema.game.common.data.element.Element.orientationBackMapping[getControllerElement().b()]) {
/*     */     case 0:
/*  69 */       localq.b((int)(localq.a + localVector3f.x / 2.0F + 1.0F), localq.b, localq.c); break;
/*     */     case 1:
/*  70 */       localq.b((int)(localq.a - localVector3f.x / 2.0F - 1.0F), localq.b, localq.c); break;
/*     */     case 2:
/*  72 */       localq.b(localq.a, (int)(localq.b + localVector3f.y / 2.0F + 1.0F), localq.c); break;
/*     */     case 3:
/*  73 */       localq.b(localq.a, (int)(localq.b - localVector3f.y / 2.0F - 1.0F), localq.c); break;
/*     */     case 4:
/*  75 */       localq.b(localq.a, localq.b, (int)(localq.c + localVector3f.z / 2.0F + 1.0F)); break;
/*     */     case 5:
/*  76 */       localq.b(localq.a, localq.b, (int)(localq.c - localVector3f.z / 2.0F - 1.0F));
/*     */     }
/*  78 */     paramq1.a();
/*  79 */     paramq2.a();
/*  80 */     paramq1.a(localq);
/*  81 */     paramq2.a(localq);
/*     */   }
/*     */ 
/*     */   public boolean isObjectDockable(SegmentController paramSegmentController)
/*     */   {
/*  89 */     if (this.enhancers != getSegmentController().getControlElementMap().getControlledElements(getEnhancerClazz(), getControllerPos()).a.size()) {
/*  90 */       throw new CollectionNotLoadedException();
/*     */     }
/*  92 */     if (!paramSegmentController.getBoundingBox().a()) {
/*  93 */       System.err.println("Exception Catched (OK): SegmentController tested to dock " + paramSegmentController + " to " + getSegmentController() + ": BB is not yet loaded");
/*  94 */       throw new CollectionNotLoadedException();
/*     */     }
/*     */ 
/*  98 */     getDockingMoved(this.min, this.max);
/*     */ 
/* 100 */     q localq = new q(paramSegmentController.getSegmentBuffer().a().b.x, paramSegmentController.getSegmentBuffer().a().b.y, paramSegmentController.getSegmentBuffer().a().b.z);
/*     */ 
/* 105 */     paramSegmentController = new q(paramSegmentController.getSegmentBuffer().a().a.x, paramSegmentController.getSegmentBuffer().a().a.y, paramSegmentController.getSegmentBuffer().a().a.z);
/*     */ 
/* 118 */     paramSegmentController.b -= this.min.b;
/* 119 */     localq.b -= this.max.b;
/*     */ 
/* 121 */     if ((paramSegmentController.a < this.min.a) || (paramSegmentController.b < this.min.b) || (paramSegmentController.c < this.min.c) || (localq.a > this.max.a) || (localq.b > this.max.b) || (localq.c > this.max.c))
/*     */     {
/* 123 */       System.err.println("[DOCKINGBLOCK] !NOT! DOCKABLE: Docking[" + this.min + "; " + this.max + "] / Segment[" + paramSegmentController + "; " + localq + "]");
/*     */ 
/* 125 */       return false;
/*     */     }
/* 127 */     System.err.println("[DOCKINGBLOCK] IS DOCKABLE: [" + this.min + "; " + this.max + "] / [" + paramSegmentController + "; " + localq + "]");
/*     */ 
/* 130 */     return true;
/*     */   }
/*     */   public abstract void getDockingMoved(q paramq1, q paramq2);
/*     */ 
/*     */   public int getMargin() {
/* 135 */     return 0;
/*     */   }
/*     */ 
/*     */   protected Class getType()
/*     */   {
/* 141 */     return DockingBlockUnit.class;
/*     */   }
/*     */   public boolean hasAreaCollision() {
/* 144 */     q localq1 = new q();
/* 145 */     q localq2 = new q();
/* 146 */     getDockingAreaAbsolute(localq1, localq2);
/*     */ 
/* 148 */     Vector3f localVector3f1 = new Vector3f();
/* 149 */     Vector3f localVector3f2 = new Vector3f();
/* 150 */     localVector3f1.set(localq1.a - 8, localq1.b - 8, localq1.c - 8);
/* 151 */     localVector3f2.set(localq2.a - 8, localq2.b - 8, localq2.c - 8);
/*     */ 
/* 153 */     return getSegmentController().getCollisionChecker().a(localq1, localq2);
/*     */   }
/*     */ 
/*     */   public boolean hasCollision() {
/* 157 */     return this.collision;
/*     */   }
/*     */ 
/*     */   public boolean isValidPositionToBuild(q paramq)
/*     */   {
/* 163 */     q localq1 = new q();
/* 164 */     q localq2 = new q();
/* 165 */     getDockingAreaAbsolute(localq1, localq2);
/*     */ 
/* 167 */     System.err.println("[DOCKING] CHECKING TO BUILD POSITION " + paramq + " ----> " + localq1 + "; " + localq2);
/* 168 */     return (paramq.a > localq2.a) || (paramq.b > localq2.b) || (paramq.c > localq2.c) || (paramq.a < localq1.a) || (paramq.b < localq1.b) || (paramq.c < localq1.c);
/*     */   }
/*     */ 
/*     */   protected void onChangedCollection()
/*     */   {
/* 177 */     int i = -1;
/* 178 */     this.enhancers = 0;
/* 179 */     for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext(); )
/*     */     {
/*     */       DockingBlockUnit localDockingBlockUnit;
/*     */       int j;
/* 184 */       if ((
/* 184 */         j = Math.abs((
/* 180 */         localDockingBlockUnit = (DockingBlockUnit)localIterator.next())
/* 180 */         .getMax().a - localDockingBlockUnit.getMin().a) * Math.abs(localDockingBlockUnit.getMax().b - localDockingBlockUnit.getMin().b) * Math.abs(localDockingBlockUnit.getMax().c - localDockingBlockUnit.getMin().c)) > 
/* 184 */         i) {
/* 185 */         this.minArea.b(localDockingBlockUnit.getMin());
/* 186 */         this.maxArea.b(localDockingBlockUnit.getMax());
/* 187 */         this.maxArea.a(1, 1, 1);
/* 188 */         i = j;
/*     */       }
/* 190 */       this.enhancers += localDockingBlockUnit.size();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void refreshActive()
/*     */   {
/* 200 */     boolean bool = this.collision;
/* 201 */     this.collision = hasAreaCollision();
/*     */ 
/* 203 */     if ((!getSegmentController().isOnServer()) && (bool != this.collision))
/*     */     {
/*     */       ct localct;
/*     */       aq localaq;
/* 207 */       if (((
/* 207 */         localaq = (
/* 205 */         localct = (ct)getSegmentController().getState())
/* 205 */         .a().a().a())
/* 207 */         .a().a().c()) || (localaq.a().a().a().a().c()))
/*     */       {
/* 209 */         if (this.collision) {
/* 210 */           localct.a().a(dockingOnlineMsg);
/* 211 */           localct.a().b(dockingOfflineMsg); return;
/*     */         }
/* 213 */         localct.a().a(dockingOfflineMsg);
/* 214 */         localct.a().d(dockingOnlineMsg);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected boolean hasMetaData()
/*     */   {
/* 226 */     return true;
/*     */   }
/*     */ 
/*     */   protected void applyMetaData(BlockMetaDataDummy paramBlockMetaDataDummy)
/*     */   {
/* 234 */     this.orientation = ((DockingMetaDataDummy)paramBlockMetaDataDummy).orientation;
/*     */   }
/*     */ 
/*     */   protected Ad toTagStructurePriv() {
/* 238 */     return new Ad(Af.b, null, Byte.valueOf(this.orientation));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager
 * JD-Core Version:    0.6.2
 */