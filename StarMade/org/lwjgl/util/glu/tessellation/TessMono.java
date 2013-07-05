/*     */ package org.lwjgl.util.glu.tessellation;
/*     */ 
/*     */ class TessMono
/*     */ {
/*     */   static boolean __gl_meshTessellateMonoRegion(GLUface face)
/*     */   {
/* 123 */     GLUhalfEdge up = face.anEdge;
/* 124 */     assert ((up.Lnext != up) && (up.Lnext.Lnext != up));
/*     */ 
/* 126 */     while (Geom.VertLeq(up.Sym.Org, up.Org)) up = up.Onext.Sym;
/*     */ 
/* 128 */     while (Geom.VertLeq(up.Org, up.Sym.Org)) up = up.Lnext;
/*     */ 
/* 130 */     GLUhalfEdge lo = up.Onext.Sym;
/*     */ 
/* 132 */     while (up.Lnext != lo) {
/* 133 */       if (Geom.VertLeq(up.Sym.Org, lo.Org))
/*     */       {
/* 138 */         while ((lo.Lnext != up) && ((Geom.EdgeGoesLeft(lo.Lnext)) || (Geom.EdgeSign(lo.Org, lo.Sym.Org, lo.Lnext.Sym.Org) <= 0.0D)))
/*     */         {
/* 140 */           GLUhalfEdge tempHalfEdge = Mesh.__gl_meshConnect(lo.Lnext, lo);
/* 141 */           if (tempHalfEdge == null) return false;
/* 142 */           lo = tempHalfEdge.Sym;
/*     */         }
/* 144 */         lo = lo.Onext.Sym;
/*     */       }
/*     */       else {
/* 147 */         while ((lo.Lnext != up) && ((Geom.EdgeGoesRight(up.Onext.Sym)) || (Geom.EdgeSign(up.Sym.Org, up.Org, up.Onext.Sym.Org) >= 0.0D)))
/*     */         {
/* 149 */           GLUhalfEdge tempHalfEdge = Mesh.__gl_meshConnect(up, up.Onext.Sym);
/* 150 */           if (tempHalfEdge == null) return false;
/* 151 */           up = tempHalfEdge.Sym;
/*     */         }
/* 153 */         up = up.Lnext;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 160 */     assert (lo.Lnext != up);
/* 161 */     while (lo.Lnext.Lnext != up) {
/* 162 */       GLUhalfEdge tempHalfEdge = Mesh.__gl_meshConnect(lo.Lnext, lo);
/* 163 */       if (tempHalfEdge == null) return false;
/* 164 */       lo = tempHalfEdge.Sym;
/*     */     }
/*     */ 
/* 167 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean __gl_meshTessellateInterior(GLUmesh mesh)
/*     */   {
/*     */     GLUface next;
/* 179 */     for (GLUface f = mesh.fHead.next; f != mesh.fHead; f = next)
/*     */     {
/* 181 */       next = f.next;
/* 182 */       if ((f.inside) && 
/* 183 */         (!__gl_meshTessellateMonoRegion(f))) return false;
/*     */ 
/*     */     }
/*     */ 
/* 187 */     return true;
/*     */   }
/*     */ 
/*     */   public static void __gl_meshDiscardExterior(GLUmesh mesh)
/*     */   {
/*     */     GLUface next;
/* 200 */     for (GLUface f = mesh.fHead.next; f != mesh.fHead; f = next)
/*     */     {
/* 202 */       next = f.next;
/* 203 */       if (!f.inside)
/* 204 */         Mesh.__gl_meshZapFace(f);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean __gl_meshSetWindingNumber(GLUmesh mesh, int value, boolean keepOnlyBoundary)
/*     */   {
/*     */     GLUhalfEdge eNext;
/* 222 */     for (GLUhalfEdge e = mesh.eHead.next; e != mesh.eHead; e = eNext) {
/* 223 */       eNext = e.next;
/* 224 */       if (e.Sym.Lface.inside != e.Lface.inside)
/*     */       {
/* 227 */         e.winding = (e.Lface.inside ? value : -value);
/*     */       }
/* 231 */       else if (!keepOnlyBoundary) {
/* 232 */         e.winding = 0;
/*     */       }
/* 234 */       else if (!Mesh.__gl_meshDelete(e)) return false;
/*     */ 
/*     */     }
/*     */ 
/* 238 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.TessMono
 * JD-Core Version:    0.6.2
 */