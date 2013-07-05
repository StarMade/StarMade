/*     */ package org.schema.game.common.controller.elements;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue;
/*     */ import it.unimi.dsi.fastutil.longs.LongIterator;
/*     */ import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.schema.game.common.data.element.ElementCollection;
/*     */ import q;
/*     */ 
/*     */ public class ElementCollectionCalculationThread
/*     */ {
/*     */   private ElementCollection lastElementCollection;
/*     */   private LongOpenHashSet closedCollection;
/*     */   private LongArrayFIFOQueue openCollection;
/*     */   private final ElementCollectionManager man;
/*  21 */   private final q absPosTmp = new q();
/*  22 */   private final q absPos = new q();
/*     */   private CollectionCalculationCallback callback;
/*  24 */   private final ArrayList collections = new ArrayList();
/*     */ 
/*  33 */   public static long[] vals = { -1L, 1L, -65534L, 65534L, -4294705156L, 4294705156L };
/*     */ 
/*  35 */   public static void main(String[] paramArrayOfString) { for (paramArrayOfString = -100; paramArrayOfString < 100; paramArrayOfString++)
/*  36 */       for (int i = -100; i < 100; i++)
/*  37 */         for (int j = -100; j < 100; j++)
/*     */         {
/*     */           q localq1;
/*  40 */           long l1 = ElementCollection.getIndex(localq1 = new q(j, i, paramArrayOfString));
/*     */ 
/*  42 */           for (int k = 0; k < 6; k++)
/*     */           {
/*     */             q localq2;
/*  43 */             (
/*  44 */               localq2 = new q(localq1))
/*  44 */               .a(org.schema.game.common.data.element.Element.DIRECTIONSi[k]);
/*  45 */             long l2 = ElementCollection.getIndex(localq2);
/*     */ 
/*  47 */             assert (l1 - vals[k] == l2) : (l1 + "; " + l2 + "; " + (l1 - l2) + "; " + vals[k] + "; " + (l1 + vals[k]));
/*     */           }
/*     */         }
/*     */   }
/*     */ 
/*     */   public ElementCollectionCalculationThread(ElementCollectionManager paramElementCollectionManager)
/*     */   {
/*  59 */     this.man = paramElementCollectionManager;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/*  69 */     return this.man == ((ElementCollectionCalculationThread)paramObject).man;
/*     */   }
/*     */ 
/*     */   public int hashCode() {
/*  73 */     return this.man.hashCode();
/*     */   }
/*     */ 
/*     */   public void initialize(LongOpenHashSet paramLongOpenHashSet, LongArrayFIFOQueue paramLongArrayFIFOQueue, CollectionCalculationCallback paramCollectionCalculationCallback)
/*     */   {
/*  79 */     this.closedCollection = paramLongOpenHashSet;
/*  80 */     this.openCollection = paramLongArrayFIFOQueue;
/*  81 */     this.callback = paramCollectionCalculationCallback;
/*     */   }
/*     */ 
/*     */   public void onFinish() {
/*  85 */     this.lastElementCollection = null;
/*  86 */     this.closedCollection = null;
/*  87 */     this.openCollection = null;
/*     */   }
/*     */ 
/*     */   public void process() {
/*  91 */     while (!this.closedCollection.isEmpty()) {
/*  92 */       if (this.openCollection.isEmpty())
/*     */       {
/*  96 */         assert (!this.closedCollection.isEmpty());
/*     */         LongIterator localLongIterator;
/*  99 */         long l2 = (
/*  99 */           localLongIterator = this.closedCollection.iterator())
/*  99 */           .nextLong();
/* 100 */         localLongIterator.remove();
/* 101 */         this.openCollection.enqueue(l2);
/*     */         ElementCollection localElementCollection;
/* 102 */         (
/* 104 */           localElementCollection = getCollectionInstance())
/* 104 */           .addElement(l2);
/*     */ 
/* 106 */         getCollections().add(localElementCollection);
/*     */ 
/* 108 */         this.lastElementCollection = localElementCollection;
/*     */       } else {
/* 110 */         while (!this.openCollection.isEmpty()) {
/* 111 */           long l1 = this.openCollection.dequeue().longValue();
/*     */ 
/* 114 */           for (int i = 0; i < 6; i++)
/*     */           {
/* 117 */             long l3 = l1 - vals[i];
/* 118 */             if (this.closedCollection.remove(l3)) {
/* 119 */               this.lastElementCollection.addElement(l3);
/* 120 */               this.openCollection.enqueue(l3);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 128 */     onCalculationFinished();
/*     */   }
/*     */ 
/*     */   public void apply() {
/* 132 */     this.man.getCollection().clear();
/* 133 */     this.man.getCollection().addAll(getCollections());
/*     */   }
/*     */ 
/*     */   private void onCalculationFinished() {
/* 137 */     this.callback.callback(this);
/*     */   }
/*     */ 
/*     */   public ElementCollection getCollectionInstance()
/*     */   {
/* 143 */     return getMan().newElementCollection(getMan().getEnhancerClazz(), getMan(), getMan().getSegmentController());
/*     */   }
/*     */ 
/*     */   public ElementCollectionManager getMan()
/*     */   {
/* 149 */     return this.man;
/*     */   }
/*     */ 
/*     */   public void flagUpdateFinished()
/*     */   {
/* 154 */     getMan().flagUpdateFinished(this);
/*     */   }
/*     */ 
/*     */   public ArrayList getCollections()
/*     */   {
/* 162 */     return this.collections;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ElementCollectionCalculationThread
 * JD-Core Version:    0.6.2
 */