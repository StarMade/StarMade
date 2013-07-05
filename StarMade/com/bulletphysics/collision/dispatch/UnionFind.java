/*     */ package com.bulletphysics.collision.dispatch;
/*     */ 
/*     */ import com.bulletphysics.linearmath.MiscUtil;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class UnionFind
/*     */ {
/*     */   private final ObjectArrayList<Element> elements;
/* 143 */   private static final Comparator<Element> elementComparator = new Comparator() {
/*     */     public int compare(UnionFind.Element o1, UnionFind.Element o2) {
/* 145 */       return o1.id < o2.id ? -1 : 1;
/*     */     }
/* 143 */   };
/*     */ 
/*     */   public UnionFind()
/*     */   {
/*  40 */     this.elements = new ObjectArrayList();
/*     */   }
/*     */ 
/*     */   public void sortIslands()
/*     */   {
/*  48 */     int numElements = this.elements.size();
/*     */ 
/*  50 */     for (int i = 0; i < numElements; i++) {
/*  51 */       ((Element)this.elements.getQuick(i)).id = find(i);
/*  52 */       ((Element)this.elements.getQuick(i)).sz = i;
/*     */     }
/*     */ 
/*  61 */     MiscUtil.quickSort(this.elements, elementComparator);
/*     */   }
/*     */ 
/*     */   public void reset(int N) {
/*  65 */     allocate(N);
/*     */ 
/*  67 */     for (int i = 0; i < N; i++) {
/*  68 */       ((Element)this.elements.getQuick(i)).id = i;
/*  69 */       ((Element)this.elements.getQuick(i)).sz = 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getNumElements() {
/*  74 */     return this.elements.size();
/*     */   }
/*     */ 
/*     */   public boolean isRoot(int x) {
/*  78 */     return x == ((Element)this.elements.getQuick(x)).id;
/*     */   }
/*     */ 
/*     */   public Element getElement(int index) {
/*  82 */     return (Element)this.elements.getQuick(index);
/*     */   }
/*     */ 
/*     */   public void allocate(int N) {
/*  86 */     MiscUtil.resize(this.elements, N, Element.class);
/*     */   }
/*     */ 
/*     */   public void free() {
/*  90 */     this.elements.clear();
/*     */   }
/*     */ 
/*     */   public int find(int p, int q) {
/*  94 */     return find(p) == find(q) ? 1 : 0;
/*     */   }
/*     */ 
/*     */   public void unite(int p, int q) {
/*  98 */     int i = find(p); int j = find(q);
/*  99 */     if (i == j) {
/* 100 */       return;
/*     */     }
/*     */ 
/* 114 */     ((Element)this.elements.getQuick(i)).id = j;
/* 115 */     ((Element)this.elements.getQuick(j)).sz += ((Element)this.elements.getQuick(i)).sz;
/*     */   }
/*     */ 
/*     */   public int find(int x)
/*     */   {
/* 123 */     while (x != ((Element)this.elements.getQuick(x)).id)
/*     */     {
/* 127 */       ((Element)this.elements.getQuick(x)).id = ((Element)this.elements.getQuick(((Element)this.elements.getQuick(x)).id)).id;
/*     */ 
/* 129 */       x = ((Element)this.elements.getQuick(x)).id;
/*     */     }
/*     */ 
/* 133 */     return x;
/*     */   }
/*     */ 
/*     */   public static class Element
/*     */   {
/*     */     public int id;
/*     */     public int sz;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.UnionFind
 * JD-Core Version:    0.6.2
 */