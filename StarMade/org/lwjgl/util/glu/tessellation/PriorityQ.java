/*     */ package org.lwjgl.util.glu.tessellation;
/*     */ 
/*     */ abstract class PriorityQ
/*     */ {
/*     */   public static final int INIT_SIZE = 32;
/*     */ 
/*     */   public static boolean LEQ(Leq leq, Object x, Object y)
/*     */   {
/* 111 */     return Geom.VertLeq((GLUvertex)x, (GLUvertex)y);
/*     */   }
/*     */ 
/*     */   static PriorityQ pqNewPriorityQ(Leq leq) {
/* 115 */     return new PriorityQSort(leq);
/*     */   }
/*     */ 
/*     */   abstract void pqDeletePriorityQ();
/*     */ 
/*     */   abstract boolean pqInit();
/*     */ 
/*     */   abstract int pqInsert(Object paramObject);
/*     */ 
/*     */   abstract Object pqExtractMin();
/*     */ 
/*     */   abstract void pqDelete(int paramInt);
/*     */ 
/*     */   abstract Object pqMinimum();
/*     */ 
/*     */   abstract boolean pqIsEmpty();
/*     */ 
/*     */   public static abstract interface Leq
/*     */   {
/*     */     public abstract boolean leq(Object paramObject1, Object paramObject2);
/*     */   }
/*     */ 
/*     */   public static class PQhandleElem
/*     */   {
/*     */     Object key;
/*     */     int node;
/*     */   }
/*     */ 
/*     */   public static class PQnode
/*     */   {
/*     */     int handle;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.PriorityQ
 * JD-Core Version:    0.6.2
 */