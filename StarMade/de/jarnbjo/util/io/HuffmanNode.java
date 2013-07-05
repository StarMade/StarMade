/*     */ package de.jarnbjo.util.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ public final class HuffmanNode
/*     */ {
/*     */   private HuffmanNode parent;
/*  36 */   private int depth = 0;
/*     */   protected HuffmanNode o0;
/*     */   protected HuffmanNode o1;
/*     */   protected Integer value;
/*  39 */   private boolean full = false;
/*     */ 
/*     */   public HuffmanNode()
/*     */   {
/*  46 */     this(null);
/*     */   }
/*     */ 
/*     */   protected HuffmanNode(HuffmanNode parent) {
/*  50 */     this.parent = parent;
/*  51 */     if (parent != null)
/*  52 */       this.depth = (parent.getDepth() + 1);
/*     */   }
/*     */ 
/*     */   protected HuffmanNode(HuffmanNode parent, int value)
/*     */   {
/*  57 */     this(parent);
/*  58 */     this.value = new Integer(value);
/*  59 */     this.full = true;
/*     */   }
/*     */ 
/*     */   protected int read(BitInputStream bis) throws IOException {
/*  63 */     HuffmanNode iter = this;
/*  64 */     while (iter.value == null) {
/*  65 */       iter = bis.getBit() ? iter.o1 : iter.o0;
/*     */     }
/*  67 */     return iter.value.intValue();
/*     */   }
/*     */ 
/*     */   protected HuffmanNode get0() {
/*  71 */     return this.o0 == null ? set0(new HuffmanNode(this)) : this.o0;
/*     */   }
/*     */ 
/*     */   protected HuffmanNode get1() {
/*  75 */     return this.o1 == null ? set1(new HuffmanNode(this)) : this.o1;
/*     */   }
/*     */ 
/*     */   protected Integer getValue() {
/*  79 */     return this.value;
/*     */   }
/*     */ 
/*     */   private HuffmanNode getParent() {
/*  83 */     return this.parent;
/*     */   }
/*     */ 
/*     */   protected int getDepth() {
/*  87 */     return this.depth;
/*     */   }
/*     */ 
/*     */   private boolean isFull() {
/*  91 */     return this.full = (this.o0 != null) && (this.o0.isFull()) && (this.o1 != null) && (this.o1.isFull()) ? 1 : 0;
/*     */   }
/*     */ 
/*     */   private HuffmanNode set0(HuffmanNode value) {
/*  95 */     return this.o0 = value;
/*     */   }
/*     */ 
/*     */   private HuffmanNode set1(HuffmanNode value) {
/*  99 */     return this.o1 = value;
/*     */   }
/*     */ 
/*     */   private void setValue(Integer value) {
/* 103 */     this.full = true;
/* 104 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public boolean setNewValue(int depth, int value)
/*     */   {
/* 116 */     if (isFull()) {
/* 117 */       return false;
/*     */     }
/* 119 */     if (depth == 1) {
/* 120 */       if (this.o0 == null) {
/* 121 */         set0(new HuffmanNode(this, value));
/* 122 */         return true;
/*     */       }
/* 124 */       if (this.o1 == null) {
/* 125 */         set1(new HuffmanNode(this, value));
/* 126 */         return true;
/*     */       }
/*     */ 
/* 129 */       return false;
/*     */     }
/*     */ 
/* 133 */     return get0().setNewValue(depth - 1, value) ? true : get1().setNewValue(depth - 1, value);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.util.io.HuffmanNode
 * JD-Core Version:    0.6.2
 */