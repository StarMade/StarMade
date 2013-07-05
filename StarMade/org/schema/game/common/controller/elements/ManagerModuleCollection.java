/*     */ package org.schema.game.common.controller.elements;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import le;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import q;
/*     */ import xq;
/*     */ 
/*     */ public class ManagerModuleCollection extends ManagerModuleControllable
/*     */ {
/*     */   private final UsableControllableElementManager elementManager;
/*  21 */   private final q tmpAbsPos = new q();
/*  22 */   private final ObjectArrayFIFOQueue toAddControllers = new ObjectArrayFIFOQueue();
/*     */ 
/*     */   public ManagerModuleCollection(UsableControllableElementManager paramUsableControllableElementManager, short paramShort1, short paramShort2)
/*     */   {
/*  28 */     super(paramUsableControllableElementManager, paramShort2, paramShort1);
/*  29 */     this.elementManager = paramUsableControllableElementManager;
/*     */   }
/*     */ 
/*     */   public void addControlledBlock(q paramq1, q paramq2, short paramShort)
/*     */   {
/*  36 */     this.elementManager.addControllerBlockIfNecessary(paramq1, paramq2, paramShort);
/*     */   }
/*     */ 
/*     */   private void doControllerBlockAdds() {
/*  40 */     if (!this.toAddControllers.isEmpty())
/*  41 */       synchronized (this.toAddControllers) {
/*  42 */         while (!this.toAddControllers.isEmpty())
/*     */         {
/*     */           le localle;
/*  43 */           (
/*  45 */             localle = (le)this.toAddControllers.dequeue())
/*  45 */             .a(this.tmpAbsPos);
/*  46 */           for (Object localObject2 = this.elementManager.getCollectionManagers().iterator(); ((Iterator)localObject2).hasNext(); ) {
/*  47 */             if (((ControlBlockElementCollectionManager)((Iterator)localObject2).next())
/*  47 */               .getControllerPos().equals(this.tmpAbsPos))
/*     */             {
/*  49 */               return;
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/*  57 */           if (((
/*  57 */             localObject2 = this.elementManager.getNewCollectionManager(localle)) instanceof ControlBlockElementCollectionManager))
/*     */           {
/*  58 */             ((ControlBlockElementCollectionManager)localObject2).refreshControlled(localle.a().a().getControlElementMap());
/*     */           }
/*     */ 
/*  62 */           getCollectionManagers().add(localObject2);
/*  63 */           this.elementManager.onControllerBlockAdd();
/*     */ 
/*  65 */           ((ElementCollectionManager)localObject2).pieceRefresh();
/*     */         }
/*     */ 
/*  68 */         return;
/*     */       }
/*     */   }
/*     */ 
/*     */   public void addControllerBlock(byte paramByte1, byte arg2, byte paramByte3, Segment paramSegment) {
/*  73 */     paramByte1 = new le(paramSegment, paramByte1, ???, paramByte3);
/*  74 */     synchronized (this.toAddControllers) {
/*  75 */       this.toAddControllers.enqueue(paramByte1);
/*     */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/*  88 */     for (int i = 0; i < this.elementManager.getCollectionManagers().size(); i++) {
/*  89 */       ((ElementCollectionManager)this.elementManager.getCollectionManagers().get(i))
/*  90 */         .clear();
/*     */     }
/*  92 */     this.elementManager.getCollectionManagers().clear();
/*     */   }
/*     */ 
/*     */   public List getCollectionManagers()
/*     */   {
/*  99 */     return getElementManager().getCollectionManagers();
/*     */   }
/*     */ 
/*     */   public UsableControllableElementManager getElementManager() {
/* 103 */     return this.elementManager;
/*     */   }
/*     */ 
/*     */   public void removeController(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
/*     */   {
/* 108 */     paramSegment.a(paramByte1, paramByte2, paramByte3, this.tmpAbsPos);
/*     */ 
/* 110 */     for (paramByte1 = this.elementManager.getCollectionManagers().iterator(); paramByte1.hasNext(); )
/* 111 */       if ((
/* 111 */         paramByte2 = (ControlBlockElementCollectionManager)paramByte1.next())
/* 111 */         .getControllerPos().equals(this.tmpAbsPos)) {
/* 112 */         paramByte2.stopUpdate();
/* 113 */         this.elementManager.getCollectionManagers().remove(paramByte2);
/* 114 */         return;
/*     */       }
/*     */   }
/*     */ 
/*     */   public void removeControllerBlock(q paramq1, q paramq2, short paramShort)
/*     */   {
/* 126 */     this.elementManager.removeControllerIfNecessary(paramq1, paramq2, paramShort);
/*     */   }
/*     */ 
/*     */   public void update(xq paramxq, long paramLong)
/*     */   {
/* 133 */     doControllerBlockAdds();
/* 134 */     int i = this.elementManager.getCollectionManagers().size();
/* 135 */     for (int j = 0; j < i; j++)
/*     */     {
/*     */       ElementCollectionManager localElementCollectionManager;
/* 136 */       (
/* 137 */         localElementCollectionManager = (ElementCollectionManager)this.elementManager.getCollectionManagers().get(j))
/* 137 */         .updateStructure(paramLong);
/* 138 */       if (localElementCollectionManager.needsUpdate())
/* 139 */         localElementCollectionManager.update(paramxq);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerModuleCollection
 * JD-Core Version:    0.6.2
 */