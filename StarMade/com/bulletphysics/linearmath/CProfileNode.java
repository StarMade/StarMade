/*     */ package com.bulletphysics.linearmath;
/*     */ 
/*     */ import com.bulletphysics.BulletStats;
/*     */ 
/*     */ class CProfileNode
/*     */ {
/*     */   protected String name;
/*     */   protected int totalCalls;
/*     */   protected float totalTime;
/*     */   protected long startTime;
/*     */   protected int recursionCounter;
/*     */   protected CProfileNode parent;
/*     */   protected CProfileNode child;
/*     */   protected CProfileNode sibling;
/*     */ 
/*     */   public CProfileNode(String name, CProfileNode parent)
/*     */   {
/*  54 */     this.name = name;
/*  55 */     this.totalCalls = 0;
/*  56 */     this.totalTime = 0.0F;
/*  57 */     this.startTime = 0L;
/*  58 */     this.recursionCounter = 0;
/*  59 */     this.parent = parent;
/*  60 */     this.child = null;
/*  61 */     this.sibling = null;
/*     */ 
/*  63 */     reset();
/*     */   }
/*     */ 
/*     */   public CProfileNode getSubNode(String name)
/*     */   {
/*  68 */     CProfileNode child = this.child;
/*  69 */     while (child != null) {
/*  70 */       if (child.name == name) {
/*  71 */         return child;
/*     */       }
/*  73 */       child = child.sibling;
/*     */     }
/*     */ 
/*  78 */     CProfileNode node = new CProfileNode(name, this);
/*  79 */     node.sibling = this.child;
/*  80 */     this.child = node;
/*  81 */     return node;
/*     */   }
/*     */ 
/*     */   public CProfileNode getParent() {
/*  85 */     return this.parent;
/*     */   }
/*     */ 
/*     */   public CProfileNode getSibling() {
/*  89 */     return this.sibling;
/*     */   }
/*     */ 
/*     */   public CProfileNode getChild() {
/*  93 */     return this.child;
/*     */   }
/*     */ 
/*     */   public void cleanupMemory() {
/*  97 */     this.child = null;
/*  98 */     this.sibling = null;
/*     */   }
/*     */ 
/*     */   public void reset() {
/* 102 */     this.totalCalls = 0;
/* 103 */     this.totalTime = 0.0F;
/* 104 */     BulletStats.gProfileClock.reset();
/*     */ 
/* 106 */     if (this.child != null) {
/* 107 */       this.child.reset();
/*     */     }
/* 109 */     if (this.sibling != null)
/* 110 */       this.sibling.reset();
/*     */   }
/*     */ 
/*     */   public void call()
/*     */   {
/* 115 */     this.totalCalls += 1;
/* 116 */     if (this.recursionCounter++ == 0)
/* 117 */       this.startTime = BulletStats.profileGetTicks();
/*     */   }
/*     */ 
/*     */   public boolean Return()
/*     */   {
/* 122 */     if ((--this.recursionCounter == 0) && (this.totalCalls != 0)) {
/* 123 */       long time = BulletStats.profileGetTicks();
/* 124 */       time -= this.startTime;
/* 125 */       this.totalTime += (float)time / BulletStats.profileGetTickRate();
/*     */     }
/* 127 */     return this.recursionCounter == 0;
/*     */   }
/*     */ 
/*     */   public String getName() {
/* 131 */     return this.name;
/*     */   }
/*     */ 
/*     */   public int getTotalCalls() {
/* 135 */     return this.totalCalls;
/*     */   }
/*     */ 
/*     */   public float getTotalTime() {
/* 139 */     return this.totalTime;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.CProfileNode
 * JD-Core Version:    0.6.2
 */