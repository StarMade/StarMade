/*     */ package org.lwjgl.util.glu.tessellation;
/*     */ 
/*     */ import org.lwjgl.util.glu.GLUtessellator;
/*     */ import org.lwjgl.util.glu.GLUtessellatorCallback;
/*     */ import org.lwjgl.util.glu.GLUtessellatorCallbackAdapter;
/*     */ 
/*     */ public class GLUtessellatorImpl
/*     */   implements GLUtessellator
/*     */ {
/*     */   public static final int TESS_MAX_CACHE = 100;
/*     */   private int state;
/*     */   private GLUhalfEdge lastEdge;
/*     */   GLUmesh mesh;
/* 104 */   double[] normal = new double[3];
/* 105 */   double[] sUnit = new double[3];
/* 106 */   double[] tUnit = new double[3];
/*     */   private double relTolerance;
/*     */   int windingRule;
/*     */   boolean fatalError;
/*     */   Dict dict;
/*     */   PriorityQ pq;
/*     */   GLUvertex event;
/*     */   boolean flagBoundary;
/*     */   boolean boundaryOnly;
/*     */   GLUface lonelyTriList;
/*     */   private boolean flushCacheOnNextVertex;
/*     */   int cacheCount;
/* 131 */   CachedVertex[] cache = new CachedVertex[100];
/*     */   private Object polygonData;
/*     */   private GLUtessellatorCallback callBegin;
/*     */   private GLUtessellatorCallback callEdgeFlag;
/*     */   private GLUtessellatorCallback callVertex;
/*     */   private GLUtessellatorCallback callEnd;
/*     */   private GLUtessellatorCallback callError;
/*     */   private GLUtessellatorCallback callCombine;
/*     */   private GLUtessellatorCallback callBeginData;
/*     */   private GLUtessellatorCallback callEdgeFlagData;
/*     */   private GLUtessellatorCallback callVertexData;
/*     */   private GLUtessellatorCallback callEndData;
/*     */   private GLUtessellatorCallback callErrorData;
/*     */   private GLUtessellatorCallback callCombineData;
/*     */   private static final double GLU_TESS_DEFAULT_TOLERANCE = 0.0D;
/* 154 */   private static GLUtessellatorCallback NULL_CB = new GLUtessellatorCallbackAdapter();
/*     */ 
/*     */   public GLUtessellatorImpl()
/*     */   {
/* 160 */     this.state = 0;
/*     */ 
/* 162 */     this.normal[0] = 0.0D;
/* 163 */     this.normal[1] = 0.0D;
/* 164 */     this.normal[2] = 0.0D;
/*     */ 
/* 166 */     this.relTolerance = 0.0D;
/* 167 */     this.windingRule = 100130;
/* 168 */     this.flagBoundary = false;
/* 169 */     this.boundaryOnly = false;
/*     */ 
/* 171 */     this.callBegin = NULL_CB;
/* 172 */     this.callEdgeFlag = NULL_CB;
/* 173 */     this.callVertex = NULL_CB;
/* 174 */     this.callEnd = NULL_CB;
/* 175 */     this.callError = NULL_CB;
/* 176 */     this.callCombine = NULL_CB;
/*     */ 
/* 179 */     this.callBeginData = NULL_CB;
/* 180 */     this.callEdgeFlagData = NULL_CB;
/* 181 */     this.callVertexData = NULL_CB;
/* 182 */     this.callEndData = NULL_CB;
/* 183 */     this.callErrorData = NULL_CB;
/* 184 */     this.callCombineData = NULL_CB;
/*     */ 
/* 186 */     this.polygonData = null;
/*     */ 
/* 188 */     for (int i = 0; i < this.cache.length; i++)
/* 189 */       this.cache[i] = new CachedVertex();
/*     */   }
/*     */ 
/*     */   public static GLUtessellator gluNewTess()
/*     */   {
/* 195 */     return new GLUtessellatorImpl();
/*     */   }
/*     */ 
/*     */   private void makeDormant()
/*     */   {
/* 202 */     if (this.mesh != null) {
/* 203 */       Mesh.__gl_meshDeleteMesh(this.mesh);
/*     */     }
/* 205 */     this.state = 0;
/* 206 */     this.lastEdge = null;
/* 207 */     this.mesh = null;
/*     */   }
/*     */ 
/*     */   private void requireState(int newState) {
/* 211 */     if (this.state != newState) gotoState(newState); 
/*     */   }
/*     */ 
/*     */   private void gotoState(int newState)
/*     */   {
/* 215 */     while (this.state != newState)
/*     */     {
/* 219 */       if (this.state < newState) {
/* 220 */         if (this.state == 0) {
/* 221 */           callErrorOrErrorData(100151);
/* 222 */           gluTessBeginPolygon(null);
/* 223 */         } else if (this.state == 1) {
/* 224 */           callErrorOrErrorData(100152);
/* 225 */           gluTessBeginContour();
/*     */         }
/*     */       }
/* 228 */       else if (this.state == 2) {
/* 229 */         callErrorOrErrorData(100154);
/* 230 */         gluTessEndContour();
/* 231 */       } else if (this.state == 1) {
/* 232 */         callErrorOrErrorData(100153);
/*     */ 
/* 234 */         makeDormant();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void gluDeleteTess()
/*     */   {
/* 241 */     requireState(0);
/*     */   }
/*     */ 
/*     */   public void gluTessProperty(int which, double value) {
/* 245 */     switch (which) {
/*     */     case 100142:
/* 247 */       if ((value >= 0.0D) && (value <= 1.0D)) { this.relTolerance = value;
/*     */         return; }
/*     */       break;
/*     */     case 100140:
/* 252 */       int windingRule = (int)value;
/* 253 */       if (windingRule == value)
/*     */       {
/* 255 */         switch (windingRule) {
/*     */         case 100130:
/*     */         case 100131:
/*     */         case 100132:
/*     */         case 100133:
/*     */         case 100134:
/* 261 */           this.windingRule = windingRule;
/*     */           return;
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 100141:
/* 268 */       this.boundaryOnly = (value != 0.0D);
/* 269 */       return;
/*     */     default:
/* 272 */       callErrorOrErrorData(100900);
/* 273 */       return;
/*     */     }
/* 275 */     callErrorOrErrorData(100901);
/*     */   }
/*     */ 
/*     */   public void gluGetTessProperty(int which, double[] value, int value_offset)
/*     */   {
/* 280 */     switch (which)
/*     */     {
/*     */     case 100142:
/* 283 */       assert ((0.0D <= this.relTolerance) && (this.relTolerance <= 1.0D));
/* 284 */       value[value_offset] = this.relTolerance;
/* 285 */       break;
/*     */     case 100140:
/* 287 */       assert ((this.windingRule == 100130) || (this.windingRule == 100131) || (this.windingRule == 100132) || (this.windingRule == 100133) || (this.windingRule == 100134));
/*     */ 
/* 292 */       value[value_offset] = this.windingRule;
/* 293 */       break;
/*     */     case 100141:
/* 295 */       assert ((this.boundaryOnly == true) || (!this.boundaryOnly));
/* 296 */       value[value_offset] = (this.boundaryOnly ? 1.0D : 0.0D);
/* 297 */       break;
/*     */     default:
/* 299 */       value[value_offset] = 0.0D;
/* 300 */       callErrorOrErrorData(100900);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void gluTessNormal(double x, double y, double z)
/*     */   {
/* 306 */     this.normal[0] = x;
/* 307 */     this.normal[1] = y;
/* 308 */     this.normal[2] = z;
/*     */   }
/*     */ 
/*     */   public void gluTessCallback(int which, GLUtessellatorCallback aCallback) {
/* 312 */     switch (which) {
/*     */     case 100100:
/* 314 */       this.callBegin = (aCallback == null ? NULL_CB : aCallback);
/* 315 */       return;
/*     */     case 100106:
/* 317 */       this.callBeginData = (aCallback == null ? NULL_CB : aCallback);
/* 318 */       return;
/*     */     case 100104:
/* 320 */       this.callEdgeFlag = (aCallback == null ? NULL_CB : aCallback);
/*     */ 
/* 324 */       this.flagBoundary = (aCallback != null);
/* 325 */       return;
/*     */     case 100110:
/* 327 */       this.callEdgeFlagData = (this.callBegin = aCallback == null ? NULL_CB : aCallback);
/*     */ 
/* 331 */       this.flagBoundary = (aCallback != null);
/* 332 */       return;
/*     */     case 100101:
/* 334 */       this.callVertex = (aCallback == null ? NULL_CB : aCallback);
/* 335 */       return;
/*     */     case 100107:
/* 337 */       this.callVertexData = (aCallback == null ? NULL_CB : aCallback);
/* 338 */       return;
/*     */     case 100102:
/* 340 */       this.callEnd = (aCallback == null ? NULL_CB : aCallback);
/* 341 */       return;
/*     */     case 100108:
/* 343 */       this.callEndData = (aCallback == null ? NULL_CB : aCallback);
/* 344 */       return;
/*     */     case 100103:
/* 346 */       this.callError = (aCallback == null ? NULL_CB : aCallback);
/* 347 */       return;
/*     */     case 100109:
/* 349 */       this.callErrorData = (aCallback == null ? NULL_CB : aCallback);
/* 350 */       return;
/*     */     case 100105:
/* 352 */       this.callCombine = (aCallback == null ? NULL_CB : aCallback);
/* 353 */       return;
/*     */     case 100111:
/* 355 */       this.callCombineData = (aCallback == null ? NULL_CB : aCallback);
/* 356 */       return;
/*     */     }
/*     */ 
/* 361 */     callErrorOrErrorData(100900);
/*     */   }
/*     */ 
/*     */   private boolean addVertex(double[] coords, Object vertexData)
/*     */   {
/* 369 */     GLUhalfEdge e = this.lastEdge;
/* 370 */     if (e == null)
/*     */     {
/* 373 */       e = Mesh.__gl_meshMakeEdge(this.mesh);
/* 374 */       if (e == null) return false;
/* 375 */       if (!Mesh.__gl_meshSplice(e, e.Sym)) return false;
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 380 */       if (Mesh.__gl_meshSplitEdge(e) == null) return false;
/* 381 */       e = e.Lnext;
/*     */     }
/*     */ 
/* 385 */     e.Org.data = vertexData;
/* 386 */     e.Org.coords[0] = coords[0];
/* 387 */     e.Org.coords[1] = coords[1];
/* 388 */     e.Org.coords[2] = coords[2];
/*     */ 
/* 395 */     e.winding = 1;
/* 396 */     e.Sym.winding = -1;
/*     */ 
/* 398 */     this.lastEdge = e;
/*     */ 
/* 400 */     return true;
/*     */   }
/*     */ 
/*     */   private void cacheVertex(double[] coords, Object vertexData) {
/* 404 */     if (this.cache[this.cacheCount] == null) {
/* 405 */       this.cache[this.cacheCount] = new CachedVertex();
/*     */     }
/*     */ 
/* 408 */     CachedVertex v = this.cache[this.cacheCount];
/*     */ 
/* 410 */     v.data = vertexData;
/* 411 */     v.coords[0] = coords[0];
/* 412 */     v.coords[1] = coords[1];
/* 413 */     v.coords[2] = coords[2];
/* 414 */     this.cacheCount += 1;
/*     */   }
/*     */ 
/*     */   private boolean flushCache()
/*     */   {
/* 419 */     CachedVertex[] v = this.cache;
/*     */ 
/* 421 */     this.mesh = Mesh.__gl_meshNewMesh();
/* 422 */     if (this.mesh == null) return false;
/*     */ 
/* 424 */     for (int i = 0; i < this.cacheCount; i++) {
/* 425 */       CachedVertex vertex = v[i];
/* 426 */       if (!addVertex(vertex.coords, vertex.data)) return false;
/*     */     }
/* 428 */     this.cacheCount = 0;
/* 429 */     this.flushCacheOnNextVertex = false;
/*     */ 
/* 431 */     return true;
/*     */   }
/*     */ 
/*     */   public void gluTessVertex(double[] coords, int coords_offset, Object vertexData)
/*     */   {
/* 436 */     boolean tooLarge = false;
/*     */ 
/* 438 */     double[] clamped = new double[3];
/*     */ 
/* 440 */     requireState(2);
/*     */ 
/* 442 */     if (this.flushCacheOnNextVertex) {
/* 443 */       if (!flushCache()) {
/* 444 */         callErrorOrErrorData(100902);
/* 445 */         return;
/*     */       }
/* 447 */       this.lastEdge = null;
/*     */     }
/* 449 */     for (int i = 0; i < 3; i++) {
/* 450 */       double x = coords[(i + coords_offset)];
/* 451 */       if (x < -1.0E+150D) {
/* 452 */         x = -1.0E+150D;
/* 453 */         tooLarge = true;
/*     */       }
/* 455 */       if (x > 1.0E+150D) {
/* 456 */         x = 1.0E+150D;
/* 457 */         tooLarge = true;
/*     */       }
/* 459 */       clamped[i] = x;
/*     */     }
/* 461 */     if (tooLarge) {
/* 462 */       callErrorOrErrorData(100155);
/*     */     }
/*     */ 
/* 465 */     if (this.mesh == null) {
/* 466 */       if (this.cacheCount < 100) {
/* 467 */         cacheVertex(clamped, vertexData);
/* 468 */         return;
/*     */       }
/* 470 */       if (!flushCache()) {
/* 471 */         callErrorOrErrorData(100902);
/* 472 */         return;
/*     */       }
/*     */     }
/*     */ 
/* 476 */     if (!addVertex(clamped, vertexData))
/* 477 */       callErrorOrErrorData(100902);
/*     */   }
/*     */ 
/*     */   public void gluTessBeginPolygon(Object data)
/*     */   {
/* 483 */     requireState(0);
/*     */ 
/* 485 */     this.state = 1;
/* 486 */     this.cacheCount = 0;
/* 487 */     this.flushCacheOnNextVertex = false;
/* 488 */     this.mesh = null;
/*     */ 
/* 490 */     this.polygonData = data;
/*     */   }
/*     */ 
/*     */   public void gluTessBeginContour()
/*     */   {
/* 495 */     requireState(1);
/*     */ 
/* 497 */     this.state = 2;
/* 498 */     this.lastEdge = null;
/* 499 */     if (this.cacheCount > 0)
/*     */     {
/* 504 */       this.flushCacheOnNextVertex = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void gluTessEndContour()
/*     */   {
/* 510 */     requireState(2);
/* 511 */     this.state = 1;
/*     */   }
/*     */ 
/*     */   public void gluTessEndPolygon()
/*     */   {
/*     */     try
/*     */     {
/* 518 */       requireState(1);
/* 519 */       this.state = 0;
/*     */ 
/* 521 */       if (this.mesh == null) {
/* 522 */         if (!this.flagBoundary)
/*     */         {
/* 529 */           if (Render.__gl_renderCache(this)) {
/* 530 */             this.polygonData = null;
/* 531 */             return;
/*     */           }
/*     */         }
/* 534 */         if (!flushCache()) throw new RuntimeException();
/*     */ 
/*     */       }
/*     */ 
/* 540 */       Normal.__gl_projectPolygon(this);
/*     */ 
/* 548 */       if (!Sweep.__gl_computeInterior(this)) {
/* 549 */         throw new RuntimeException();
/*     */       }
/*     */ 
/* 552 */       GLUmesh mesh = this.mesh;
/* 553 */       if (!this.fatalError) {
/* 554 */         boolean rc = true;
/*     */ 
/* 560 */         if (this.boundaryOnly)
/* 561 */           rc = TessMono.__gl_meshSetWindingNumber(mesh, 1, true);
/*     */         else {
/* 563 */           rc = TessMono.__gl_meshTessellateInterior(mesh);
/*     */         }
/* 565 */         if (!rc) throw new RuntimeException();
/*     */ 
/* 567 */         Mesh.__gl_meshCheckMesh(mesh);
/*     */ 
/* 569 */         if ((this.callBegin != NULL_CB) || (this.callEnd != NULL_CB) || (this.callVertex != NULL_CB) || (this.callEdgeFlag != NULL_CB) || (this.callBeginData != NULL_CB) || (this.callEndData != NULL_CB) || (this.callVertexData != NULL_CB) || (this.callEdgeFlagData != NULL_CB))
/*     */         {
/* 575 */           if (this.boundaryOnly)
/* 576 */             Render.__gl_renderBoundary(this, mesh);
/*     */           else {
/* 578 */             Render.__gl_renderMesh(this, mesh);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 596 */       Mesh.__gl_meshDeleteMesh(mesh);
/* 597 */       this.polygonData = null;
/* 598 */       mesh = null;
/*     */     } catch (Exception e) {
/* 600 */       e.printStackTrace();
/* 601 */       callErrorOrErrorData(100902);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void gluBeginPolygon()
/*     */   {
/* 610 */     gluTessBeginPolygon(null);
/* 611 */     gluTessBeginContour();
/*     */   }
/*     */ 
/*     */   public void gluNextContour(int type)
/*     */   {
/* 617 */     gluTessEndContour();
/* 618 */     gluTessBeginContour();
/*     */   }
/*     */ 
/*     */   public void gluEndPolygon()
/*     */   {
/* 623 */     gluTessEndContour();
/* 624 */     gluTessEndPolygon();
/*     */   }
/*     */ 
/*     */   void callBeginOrBeginData(int a) {
/* 628 */     if (this.callBeginData != NULL_CB)
/* 629 */       this.callBeginData.beginData(a, this.polygonData);
/*     */     else
/* 631 */       this.callBegin.begin(a);
/*     */   }
/*     */ 
/*     */   void callVertexOrVertexData(Object a) {
/* 635 */     if (this.callVertexData != NULL_CB)
/* 636 */       this.callVertexData.vertexData(a, this.polygonData);
/*     */     else
/* 638 */       this.callVertex.vertex(a);
/*     */   }
/*     */ 
/*     */   void callEdgeFlagOrEdgeFlagData(boolean a) {
/* 642 */     if (this.callEdgeFlagData != NULL_CB)
/* 643 */       this.callEdgeFlagData.edgeFlagData(a, this.polygonData);
/*     */     else
/* 645 */       this.callEdgeFlag.edgeFlag(a);
/*     */   }
/*     */ 
/*     */   void callEndOrEndData() {
/* 649 */     if (this.callEndData != NULL_CB)
/* 650 */       this.callEndData.endData(this.polygonData);
/*     */     else
/* 652 */       this.callEnd.end();
/*     */   }
/*     */ 
/*     */   void callCombineOrCombineData(double[] coords, Object[] vertexData, float[] weights, Object[] outData) {
/* 656 */     if (this.callCombineData != NULL_CB)
/* 657 */       this.callCombineData.combineData(coords, vertexData, weights, outData, this.polygonData);
/*     */     else
/* 659 */       this.callCombine.combine(coords, vertexData, weights, outData);
/*     */   }
/*     */ 
/*     */   void callErrorOrErrorData(int a) {
/* 663 */     if (this.callErrorData != NULL_CB)
/* 664 */       this.callErrorData.errorData(a, this.polygonData);
/*     */     else
/* 666 */       this.callError.error(a);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.GLUtessellatorImpl
 * JD-Core Version:    0.6.2
 */