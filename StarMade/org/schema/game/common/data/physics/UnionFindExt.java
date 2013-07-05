/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.linearmath.MiscUtil;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class UnionFindExt
/*     */ {
/*     */   private final ObjectArrayList elements;
/* 153 */   private static final Comparator elementComparator = new UnionFindExt.1();
/*     */ 
/*     */   public UnionFindExt()
/*     */   {
/*  40 */     this.elements = new ObjectArrayList();
/*     */   }
/*     */ 
/*     */   public void sortIslands()
/*     */   {
/*  48 */     int i = this.elements.size();
/*     */ 
/*  50 */     for (int j = 0; j < i; j++)
/*     */     {
/*     */       UnionFindExt.Element localElement;
/*  51 */       (
/*  52 */         localElement = (UnionFindExt.Element)this.elements.getQuick(j)).id = 
/*  52 */         find(j);
/*  53 */       localElement.sz = j;
/*     */     }
/*     */ 
/*  62 */     MiscUtil.quickSort(this.elements, elementComparator);
/*     */   }
/*     */ 
/*     */   public void reset(int paramInt) {
/*  66 */     allocate(paramInt);
/*     */ 
/*  68 */     for (int i = 0; i < paramInt; i++)
/*     */     {
/*     */       UnionFindExt.Element localElement;
/*  69 */       (
/*  70 */         localElement = (UnionFindExt.Element)this.elements.getQuick(i)).id = 
/*  70 */         i;
/*  71 */       localElement.sz = 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getNumElements() {
/*  76 */     return this.elements.size();
/*     */   }
/*     */ 
/*     */   public boolean isRoot(int paramInt) {
/*  80 */     return paramInt == ((UnionFindExt.Element)this.elements.getQuick(paramInt)).id;
/*     */   }
/*     */ 
/*     */   public UnionFindExt.Element getElement(int paramInt) {
/*  84 */     return (UnionFindExt.Element)this.elements.getQuick(paramInt);
/*     */   }
/*     */ 
/*     */   public void allocate(int paramInt) {
/*  88 */     MiscUtil.resize(this.elements, paramInt, UnionFindExt.Element.class);
/*     */ 
/*  90 */     while (this.elements.size() < paramInt) {
/*  91 */       this.elements.add(new UnionFindExt.Element());
/*     */     }
/*     */ 
/*  94 */     while (this.elements.size() > paramInt)
/*  95 */       this.elements.removeQuick(this.elements.size() - 1);
/*     */   }
/*     */ 
/*     */   public void free()
/*     */   {
/* 100 */     this.elements.clear();
/*     */   }
/*     */ 
/*     */   public int find(int paramInt1, int paramInt2) {
/* 104 */     if (find(paramInt1) == find(paramInt2)) return 1; return 0;
/*     */   }
/*     */ 
/*     */   public void unite(int paramInt1, int paramInt2) {
/* 108 */     paramInt1 = find(paramInt1); paramInt2 = find(paramInt2);
/* 109 */     if (paramInt1 == paramInt2) {
/* 110 */       return;
/*     */     }
/*     */ 
/* 124 */     ((UnionFindExt.Element)this.elements.getQuick(paramInt1)).id = paramInt2;
/* 125 */     ((UnionFindExt.Element)this.elements.getQuick(paramInt2)).sz += ((UnionFindExt.Element)this.elements.getQuick(paramInt1)).sz;
/*     */   }
/*     */ 
/*     */   public int find(int paramInt)
/*     */   {
/* 133 */     while (paramInt != ((UnionFindExt.Element)this.elements.getQuick(paramInt)).id)
/*     */     {
/* 137 */       ((UnionFindExt.Element)this.elements.getQuick(paramInt)).id = ((UnionFindExt.Element)this.elements.getQuick(((UnionFindExt.Element)this.elements.getQuick(paramInt)).id)).id;
/*     */ 
/* 139 */       paramInt = ((UnionFindExt.Element)this.elements.getQuick(paramInt)).id;
/*     */     }
/*     */ 
/* 143 */     return paramInt;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.UnionFindExt
 * JD-Core Version:    0.6.2
 */