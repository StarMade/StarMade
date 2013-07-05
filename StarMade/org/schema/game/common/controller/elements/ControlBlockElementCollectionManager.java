/*     */ package org.schema.game.common.controller.elements;
/*     */ 
/*     */ import Ad;
/*     */ import Af;
/*     */ import ct;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import jD;
/*     */ import ja;
/*     */ import java.util.ArrayList;
/*     */ import le;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ControlElementMap;
/*     */ import org.schema.game.common.data.element.ElementCollection;
/*     */ import q;
/*     */ 
/*     */ public abstract class ControlBlockElementCollectionManager extends ElementCollectionManager
/*     */ {
/*     */   private final le controllerElement;
/*  24 */   private q tmp = new q();
/*     */ 
/*     */   public ControlBlockElementCollectionManager(le paramle, short paramShort, SegmentController paramSegmentController)
/*     */   {
/*  28 */     super(paramShort, paramSegmentController);
/*  29 */     this.controllerElement = paramle;
/*  30 */     pieceRefresh();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/*  39 */     return ((ControlBlockElementCollectionManager)paramObject).controllerElement.equals(this.controllerElement);
/*     */   }
/*     */ 
/*     */   public boolean equalsControllerPos(q paramq) {
/*  43 */     return (paramq != null) && (getControllerElement().a(paramq));
/*     */   }
/*     */ 
/*     */   public le getControllerElement()
/*     */   {
/*  49 */     return this.controllerElement;
/*     */   }
/*     */ 
/*     */   public q getControllerPos()
/*     */   {
/*  54 */     assert (this.controllerElement.a(this.tmp)) : (this.tmp + ": " + this.controllerElement.a(new q()));
/*  55 */     return this.tmp;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  63 */     return getControllerElement().hashCode();
/*     */   }
/*     */ 
/*     */   protected void pieceRefresh()
/*     */   {
/*  68 */     this.controllerElement.a();
/*  69 */     getControllerElement().a(this.tmp);
/*     */   }
/*     */ 
/*     */   public void updateStructure(long paramLong)
/*     */   {
/*  75 */     if ((hasMetaData()) && (!getContainer().getInitialBlockMetaData().isEmpty()) && (
/*  76 */       (getSegmentController().isOnServer()) || (((ct)getSegmentController().getState()).a().containsKey(getSegmentController().getId()))))
/*     */     {
/*  79 */       for (int i = 0; i < getContainer().getInitialBlockMetaData().size(); i++) {
/*  80 */         BlockMetaDataDummy localBlockMetaDataDummy = (BlockMetaDataDummy)getContainer().getInitialBlockMetaData().get(i);
/*  81 */         if (getControllerPos().equals(localBlockMetaDataDummy.getControllerPos())) {
/*  82 */           applyMetaData(localBlockMetaDataDummy);
/*  83 */           getContainer().getInitialPointDists().remove(i);
/*  84 */           i--;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  90 */     super.updateStructure(paramLong);
/*     */   }
/*     */   protected boolean hasMetaData() {
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   protected void applyMetaData(BlockMetaDataDummy paramBlockMetaDataDummy)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void refreshControlled(ControlElementMap paramControlElementMap)
/*     */   {
/* 110 */     for (ja localja : paramControlElementMap.getControlledElements(getEnhancerClazz(), getControllerPos()).a
/* 110 */       )
/* 111 */       doAdd(ElementCollection.getIndex(localja.a, localja.b, localja.c));
/*     */   }
/*     */ 
/*     */   public Ad toTagStructure() {
/* 115 */     return new Ad(Af.n, null, new Ad[] { new Ad(Af.k, null, getControllerPos()), toTagStructurePriv(), new Ad(Af.a, null, null) });
/*     */   }
/*     */ 
/*     */   protected Ad toTagStructurePriv()
/*     */   {
/* 120 */     return new Ad(Af.b, null, Byte.valueOf((byte)0));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ControlBlockElementCollectionManager
 * JD-Core Version:    0.6.2
 */