/*     */ package org.lwjgl.util.glu.tessellation;
/*     */ 
/*     */ class GLUhalfEdge
/*     */ {
/*     */   public GLUhalfEdge next;
/*     */   public GLUhalfEdge Sym;
/*     */   public GLUhalfEdge Onext;
/*     */   public GLUhalfEdge Lnext;
/*     */   public GLUvertex Org;
/*     */   public GLUface Lface;
/*     */   public ActiveRegion activeRegion;
/*     */   public int winding;
/*     */   public boolean first;
/*     */ 
/*     */   GLUhalfEdge(boolean first)
/*     */   {
/* 103 */     this.first = first;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.GLUhalfEdge
 * JD-Core Version:    0.6.2
 */