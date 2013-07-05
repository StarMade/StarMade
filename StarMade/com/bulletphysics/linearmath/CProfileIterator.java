/*     */ package com.bulletphysics.linearmath;
/*     */ 
/*     */ public class CProfileIterator
/*     */ {
/*     */   private CProfileNode currentParent;
/*     */   private CProfileNode currentChild;
/*     */ 
/*     */   CProfileIterator(CProfileNode start)
/*     */   {
/*  45 */     this.currentParent = start;
/*  46 */     this.currentChild = this.currentParent.getChild();
/*     */   }
/*     */ 
/*     */   public void first()
/*     */   {
/*  52 */     this.currentChild = this.currentParent.getChild();
/*     */   }
/*     */ 
/*     */   public void next() {
/*  56 */     this.currentChild = this.currentChild.getSibling();
/*     */   }
/*     */ 
/*     */   public boolean isDone() {
/*  60 */     return this.currentChild == null;
/*     */   }
/*     */ 
/*     */   public boolean isRoot() {
/*  64 */     return this.currentParent.getParent() == null;
/*     */   }
/*     */ 
/*     */   public void enterChild(int index)
/*     */   {
/*  71 */     this.currentChild = this.currentParent.getChild();
/*  72 */     while ((this.currentChild != null) && (index != 0)) {
/*  73 */       index--;
/*  74 */       this.currentChild = this.currentChild.getSibling();
/*     */     }
/*     */ 
/*  77 */     if (this.currentChild != null) {
/*  78 */       this.currentParent = this.currentChild;
/*  79 */       this.currentChild = this.currentParent.getChild();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enterParent()
/*     */   {
/*  89 */     if (this.currentParent.getParent() != null) {
/*  90 */       this.currentParent = this.currentParent.getParent();
/*     */     }
/*  92 */     this.currentChild = this.currentParent.getChild();
/*     */   }
/*     */ 
/*     */   public String getCurrentName()
/*     */   {
/*  98 */     return this.currentChild.getName();
/*     */   }
/*     */ 
/*     */   public int getCurrentTotalCalls() {
/* 102 */     return this.currentChild.getTotalCalls();
/*     */   }
/*     */ 
/*     */   public float getCurrentTotalTime() {
/* 106 */     return this.currentChild.getTotalTime();
/*     */   }
/*     */ 
/*     */   public String getCurrentParentName()
/*     */   {
/* 112 */     return this.currentParent.getName();
/*     */   }
/*     */ 
/*     */   public int getCurrentParentTotalCalls() {
/* 116 */     return this.currentParent.getTotalCalls();
/*     */   }
/*     */ 
/*     */   public float getCurrentParentTotalTime() {
/* 120 */     return this.currentParent.getTotalTime();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.CProfileIterator
 * JD-Core Version:    0.6.2
 */