/*     */ package com.bulletphysics.util;
/*     */ 
/*     */ public abstract class StackList<T>
/*     */ {
/*  51 */   private final ObjectArrayList<T> list = new ObjectArrayList();
/*     */   private T returnObj;
/*  54 */   private int[] stack = new int[512];
/*  55 */   private int stackCount = 0;
/*     */ 
/*  57 */   private int pos = 0;
/*     */ 
/*     */   public StackList() {
/*  60 */     this.returnObj = create();
/*     */   }
/*     */ 
/*     */   protected StackList(boolean unused)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void push()
/*     */   {
/*  74 */     this.stack[(this.stackCount++)] = this.pos;
/*     */   }
/*     */ 
/*     */   public final void pop()
/*     */   {
/*  81 */     this.pos = this.stack[(--this.stackCount)];
/*     */   }
/*     */ 
/*     */   public T get()
/*     */   {
/*  93 */     if (this.pos == this.list.size()) {
/*  94 */       expand();
/*     */     }
/*     */ 
/*  97 */     return this.list.getQuick(this.pos++);
/*     */   }
/*     */ 
/*     */   public final T returning(T obj)
/*     */   {
/* 111 */     copy(this.returnObj, obj);
/* 112 */     return this.returnObj;
/*     */   }
/*     */ 
/*     */   protected abstract T create();
/*     */ 
/*     */   protected abstract void copy(T paramT1, T paramT2);
/*     */ 
/*     */   private void expand()
/*     */   {
/* 131 */     this.list.add(create());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.util.StackList
 * JD-Core Version:    0.6.2
 */