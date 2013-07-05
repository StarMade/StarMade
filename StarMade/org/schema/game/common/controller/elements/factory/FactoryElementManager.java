/*     */ package org.schema.game.common.controller.elements.factory;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Timer;
/*     */ import lA;
/*     */ import le;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*     */ import org.schema.game.common.controller.elements.TimedUpdateInterface;
/*     */ import org.schema.game.common.controller.elements.UsableControllableElementManager;
/*     */ import org.schema.game.common.data.element.BlockFactory;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.common.data.element.FactoryResource;
/*     */ 
/*     */ public class FactoryElementManager extends UsableControllableElementManager
/*     */   implements TimedUpdateInterface
/*     */ {
/*     */   private static final long TIME_STEP = 10000L;
/*     */   private ArrayList managers;
/*     */   private final short enhancerId;
/*     */   private final short facId;
/*     */   private long lastExecution;
/*     */   private long nextExecution;
/*     */ 
/*     */   public FactoryElementManager(SegmentController paramSegmentController, short paramShort1, short paramShort2)
/*     */   {
/*  27 */     super(paramShort1, paramShort2, paramSegmentController);
/*  28 */     this.managers = new ArrayList();
/*  29 */     this.facId = paramShort1;
/*  30 */     this.enhancerId = paramShort2;
/*     */   }
/*     */ 
/*     */   public ArrayList getCollectionManagers()
/*     */   {
/*  37 */     return this.managers;
/*     */   }
/*     */ 
/*     */   public ElementCollectionManager getNewCollectionManager(le paramle)
/*     */   {
/*  43 */     return new FactoryCollectionManager(getEnhancerId(), paramle, getSegmentController());
/*     */   }
/*     */ 
/*     */   public void handle(lA paramlA)
/*     */   {
/*     */   }
/*     */ 
/*     */   public short getEnhancerId()
/*     */   {
/*  56 */     return this.enhancerId;
/*     */   }
/*     */ 
/*     */   public short getFacId()
/*     */   {
/*  63 */     return this.facId;
/*     */   }
/*     */ 
/*     */   public void update(Timer paramTimer)
/*     */   {
/*     */   }
/*     */ 
/*     */   public long getLastExecution()
/*     */   {
/*  79 */     return this.lastExecution;
/*     */   }
/*     */   public long getNextExecution() {
/*  82 */     return this.nextExecution;
/*     */   }
/*     */   public long getTimeStep() {
/*  85 */     return 10000L;
/*     */   }
/*     */ 
/*     */   public int getProductCount() {
/*  89 */     return ElementKeyMap.getInfo(getFacId()).getFactory().input.length;
/*     */   }
/*     */   public FactoryResource[] getInputType(int paramInt) {
/*  92 */     return ElementKeyMap.getInfo(getFacId()).getFactory().input[paramInt];
/*     */   }
/*     */   public FactoryResource[] getOutputType(int paramInt) {
/*  95 */     return ElementKeyMap.getInfo(getFacId()).getFactory().output[paramInt];
/*     */   }
/*     */ 
/*     */   public boolean isInputFactory()
/*     */   {
/* 101 */     return ElementKeyMap.getInfo(getFacId()).getFactory().input == null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.factory.FactoryElementManager
 * JD-Core Version:    0.6.2
 */