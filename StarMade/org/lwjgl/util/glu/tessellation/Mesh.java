/*     */ package org.lwjgl.util.glu.tessellation;
/*     */ 
/*     */ class Mesh
/*     */ {
/*     */   static GLUhalfEdge MakeEdge(GLUhalfEdge eNext)
/*     */   {
/* 106 */     GLUhalfEdge e = new GLUhalfEdge(true);
/*     */ 
/* 108 */     GLUhalfEdge eSym = new GLUhalfEdge(false);
/*     */ 
/* 112 */     if (!eNext.first) {
/* 113 */       eNext = eNext.Sym;
/*     */     }
/*     */ 
/* 119 */     GLUhalfEdge ePrev = eNext.Sym.next;
/* 120 */     eSym.next = ePrev;
/* 121 */     ePrev.Sym.next = e;
/* 122 */     e.next = eNext;
/* 123 */     eNext.Sym.next = eSym;
/*     */ 
/* 125 */     e.Sym = eSym;
/* 126 */     e.Onext = e;
/* 127 */     e.Lnext = eSym;
/* 128 */     e.Org = null;
/* 129 */     e.Lface = null;
/* 130 */     e.winding = 0;
/* 131 */     e.activeRegion = null;
/*     */ 
/* 133 */     eSym.Sym = e;
/* 134 */     eSym.Onext = eSym;
/* 135 */     eSym.Lnext = e;
/* 136 */     eSym.Org = null;
/* 137 */     eSym.Lface = null;
/* 138 */     eSym.winding = 0;
/* 139 */     eSym.activeRegion = null;
/*     */ 
/* 141 */     return e;
/*     */   }
/*     */ 
/*     */   static void Splice(GLUhalfEdge a, GLUhalfEdge b)
/*     */   {
/* 151 */     GLUhalfEdge aOnext = a.Onext;
/* 152 */     GLUhalfEdge bOnext = b.Onext;
/*     */ 
/* 154 */     aOnext.Sym.Lnext = b;
/* 155 */     bOnext.Sym.Lnext = a;
/* 156 */     a.Onext = bOnext;
/* 157 */     b.Onext = aOnext;
/*     */   }
/*     */ 
/*     */   static void MakeVertex(GLUvertex newVertex, GLUhalfEdge eOrig, GLUvertex vNext)
/*     */   {
/* 170 */     GLUvertex vNew = newVertex;
/*     */ 
/* 172 */     assert (vNew != null);
/*     */ 
/* 175 */     GLUvertex vPrev = vNext.prev;
/* 176 */     vNew.prev = vPrev;
/* 177 */     vPrev.next = vNew;
/* 178 */     vNew.next = vNext;
/* 179 */     vNext.prev = vNew;
/*     */ 
/* 181 */     vNew.anEdge = eOrig;
/* 182 */     vNew.data = null;
/*     */ 
/* 186 */     GLUhalfEdge e = eOrig;
/*     */     do {
/* 188 */       e.Org = vNew;
/* 189 */       e = e.Onext;
/* 190 */     }while (e != eOrig);
/*     */   }
/*     */ 
/*     */   static void MakeFace(GLUface newFace, GLUhalfEdge eOrig, GLUface fNext)
/*     */   {
/* 202 */     GLUface fNew = newFace;
/*     */ 
/* 204 */     assert (fNew != null);
/*     */ 
/* 207 */     GLUface fPrev = fNext.prev;
/* 208 */     fNew.prev = fPrev;
/* 209 */     fPrev.next = fNew;
/* 210 */     fNew.next = fNext;
/* 211 */     fNext.prev = fNew;
/*     */ 
/* 213 */     fNew.anEdge = eOrig;
/* 214 */     fNew.data = null;
/* 215 */     fNew.trail = null;
/* 216 */     fNew.marked = false;
/*     */ 
/* 221 */     fNew.inside = fNext.inside;
/*     */ 
/* 224 */     GLUhalfEdge e = eOrig;
/*     */     do {
/* 226 */       e.Lface = fNew;
/* 227 */       e = e.Lnext;
/* 228 */     }while (e != eOrig);
/*     */   }
/*     */ 
/*     */   static void KillEdge(GLUhalfEdge eDel)
/*     */   {
/* 238 */     if (!eDel.first) {
/* 239 */       eDel = eDel.Sym;
/*     */     }
/*     */ 
/* 243 */     GLUhalfEdge eNext = eDel.next;
/* 244 */     GLUhalfEdge ePrev = eDel.Sym.next;
/* 245 */     eNext.Sym.next = ePrev;
/* 246 */     ePrev.Sym.next = eNext;
/*     */   }
/*     */ 
/*     */   static void KillVertex(GLUvertex vDel, GLUvertex newOrg)
/*     */   {
/* 254 */     GLUhalfEdge eStart = vDel.anEdge;
/*     */ 
/* 258 */     GLUhalfEdge e = eStart;
/*     */     do {
/* 260 */       e.Org = newOrg;
/* 261 */       e = e.Onext;
/* 262 */     }while (e != eStart);
/*     */ 
/* 265 */     GLUvertex vPrev = vDel.prev;
/* 266 */     GLUvertex vNext = vDel.next;
/* 267 */     vNext.prev = vPrev;
/* 268 */     vPrev.next = vNext;
/*     */   }
/*     */ 
/*     */   static void KillFace(GLUface fDel, GLUface newLface)
/*     */   {
/* 275 */     GLUhalfEdge eStart = fDel.anEdge;
/*     */ 
/* 279 */     GLUhalfEdge e = eStart;
/*     */     do {
/* 281 */       e.Lface = newLface;
/* 282 */       e = e.Lnext;
/* 283 */     }while (e != eStart);
/*     */ 
/* 286 */     GLUface fPrev = fDel.prev;
/* 287 */     GLUface fNext = fDel.next;
/* 288 */     fNext.prev = fPrev;
/* 289 */     fPrev.next = fNext;
/*     */   }
/*     */ 
/*     */   public static GLUhalfEdge __gl_meshMakeEdge(GLUmesh mesh)
/*     */   {
/* 299 */     GLUvertex newVertex1 = new GLUvertex();
/* 300 */     GLUvertex newVertex2 = new GLUvertex();
/* 301 */     GLUface newFace = new GLUface();
/*     */ 
/* 304 */     GLUhalfEdge e = MakeEdge(mesh.eHead);
/* 305 */     if (e == null) return null;
/*     */ 
/* 307 */     MakeVertex(newVertex1, e, mesh.vHead);
/* 308 */     MakeVertex(newVertex2, e.Sym, mesh.vHead);
/* 309 */     MakeFace(newFace, e, mesh.fHead);
/* 310 */     return e;
/*     */   }
/*     */ 
/*     */   public static boolean __gl_meshSplice(GLUhalfEdge eOrg, GLUhalfEdge eDst)
/*     */   {
/* 338 */     boolean joiningLoops = false;
/* 339 */     boolean joiningVertices = false;
/*     */ 
/* 341 */     if (eOrg == eDst) return true;
/*     */ 
/* 343 */     if (eDst.Org != eOrg.Org)
/*     */     {
/* 345 */       joiningVertices = true;
/* 346 */       KillVertex(eDst.Org, eOrg.Org);
/*     */     }
/* 348 */     if (eDst.Lface != eOrg.Lface)
/*     */     {
/* 350 */       joiningLoops = true;
/* 351 */       KillFace(eDst.Lface, eOrg.Lface);
/*     */     }
/*     */ 
/* 355 */     Splice(eDst, eOrg);
/*     */ 
/* 357 */     if (!joiningVertices) {
/* 358 */       GLUvertex newVertex = new GLUvertex();
/*     */ 
/* 363 */       MakeVertex(newVertex, eDst, eOrg.Org);
/* 364 */       eOrg.Org.anEdge = eOrg;
/*     */     }
/* 366 */     if (!joiningLoops) {
/* 367 */       GLUface newFace = new GLUface();
/*     */ 
/* 372 */       MakeFace(newFace, eDst, eOrg.Lface);
/* 373 */       eOrg.Lface.anEdge = eOrg;
/*     */     }
/*     */ 
/* 376 */     return true;
/*     */   }
/*     */ 
/*     */   static boolean __gl_meshDelete(GLUhalfEdge eDel)
/*     */   {
/* 391 */     GLUhalfEdge eDelSym = eDel.Sym;
/* 392 */     boolean joiningLoops = false;
/*     */ 
/* 397 */     if (eDel.Lface != eDel.Sym.Lface)
/*     */     {
/* 399 */       joiningLoops = true;
/* 400 */       KillFace(eDel.Lface, eDel.Sym.Lface);
/*     */     }
/*     */ 
/* 403 */     if (eDel.Onext == eDel) {
/* 404 */       KillVertex(eDel.Org, null);
/*     */     }
/*     */     else {
/* 407 */       eDel.Sym.Lface.anEdge = eDel.Sym.Lnext;
/* 408 */       eDel.Org.anEdge = eDel.Onext;
/*     */ 
/* 410 */       Splice(eDel, eDel.Sym.Lnext);
/* 411 */       if (!joiningLoops) {
/* 412 */         GLUface newFace = new GLUface();
/*     */ 
/* 415 */         MakeFace(newFace, eDel, eDel.Lface);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 422 */     if (eDelSym.Onext == eDelSym) {
/* 423 */       KillVertex(eDelSym.Org, null);
/* 424 */       KillFace(eDelSym.Lface, null);
/*     */     }
/*     */     else {
/* 427 */       eDel.Lface.anEdge = eDelSym.Sym.Lnext;
/* 428 */       eDelSym.Org.anEdge = eDelSym.Onext;
/* 429 */       Splice(eDelSym, eDelSym.Sym.Lnext);
/*     */     }
/*     */ 
/* 433 */     KillEdge(eDel);
/*     */ 
/* 435 */     return true;
/*     */   }
/*     */ 
/*     */   static GLUhalfEdge __gl_meshAddEdgeVertex(GLUhalfEdge eOrg)
/*     */   {
/* 452 */     GLUhalfEdge eNew = MakeEdge(eOrg);
/*     */ 
/* 454 */     GLUhalfEdge eNewSym = eNew.Sym;
/*     */ 
/* 457 */     Splice(eNew, eOrg.Lnext);
/*     */ 
/* 460 */     eNew.Org = eOrg.Sym.Org;
/*     */ 
/* 462 */     GLUvertex newVertex = new GLUvertex();
/*     */ 
/* 464 */     MakeVertex(newVertex, eNewSym, eNew.Org);
/*     */ 
/* 466 */     eNew.Lface = (eNewSym.Lface = eOrg.Lface);
/*     */ 
/* 468 */     return eNew;
/*     */   }
/*     */ 
/*     */   public static GLUhalfEdge __gl_meshSplitEdge(GLUhalfEdge eOrg)
/*     */   {
/* 478 */     GLUhalfEdge tempHalfEdge = __gl_meshAddEdgeVertex(eOrg);
/*     */ 
/* 480 */     GLUhalfEdge eNew = tempHalfEdge.Sym;
/*     */ 
/* 483 */     Splice(eOrg.Sym, eOrg.Sym.Sym.Lnext);
/* 484 */     Splice(eOrg.Sym, eNew);
/*     */ 
/* 487 */     eOrg.Sym.Org = eNew.Org;
/* 488 */     eNew.Sym.Org.anEdge = eNew.Sym;
/* 489 */     eNew.Sym.Lface = eOrg.Sym.Lface;
/* 490 */     eNew.winding = eOrg.winding;
/* 491 */     eNew.Sym.winding = eOrg.Sym.winding;
/*     */ 
/* 493 */     return eNew;
/*     */   }
/*     */ 
/*     */   static GLUhalfEdge __gl_meshConnect(GLUhalfEdge eOrg, GLUhalfEdge eDst)
/*     */   {
/* 509 */     boolean joiningLoops = false;
/* 510 */     GLUhalfEdge eNew = MakeEdge(eOrg);
/*     */ 
/* 512 */     GLUhalfEdge eNewSym = eNew.Sym;
/*     */ 
/* 514 */     if (eDst.Lface != eOrg.Lface)
/*     */     {
/* 516 */       joiningLoops = true;
/* 517 */       KillFace(eDst.Lface, eOrg.Lface);
/*     */     }
/*     */ 
/* 521 */     Splice(eNew, eOrg.Lnext);
/* 522 */     Splice(eNewSym, eDst);
/*     */ 
/* 525 */     eNew.Org = eOrg.Sym.Org;
/* 526 */     eNewSym.Org = eDst.Org;
/* 527 */     eNew.Lface = (eNewSym.Lface = eOrg.Lface);
/*     */ 
/* 530 */     eOrg.Lface.anEdge = eNewSym;
/*     */ 
/* 532 */     if (!joiningLoops) {
/* 533 */       GLUface newFace = new GLUface();
/*     */ 
/* 536 */       MakeFace(newFace, eNew, eOrg.Lface);
/*     */     }
/* 538 */     return eNew;
/*     */   }
/*     */ 
/*     */   static void __gl_meshZapFace(GLUface fZap)
/*     */   {
/* 552 */     GLUhalfEdge eStart = fZap.anEdge;
/*     */ 
/* 557 */     GLUhalfEdge eNext = eStart.Lnext;
/*     */     GLUhalfEdge e;
/*     */     do
/*     */     {
/* 559 */       e = eNext;
/* 560 */       eNext = e.Lnext;
/*     */ 
/* 562 */       e.Lface = null;
/* 563 */       if (e.Sym.Lface == null)
/*     */       {
/* 566 */         if (e.Onext == e) {
/* 567 */           KillVertex(e.Org, null);
/*     */         }
/*     */         else {
/* 570 */           e.Org.anEdge = e.Onext;
/* 571 */           Splice(e, e.Sym.Lnext);
/*     */         }
/* 573 */         GLUhalfEdge eSym = e.Sym;
/* 574 */         if (eSym.Onext == eSym) {
/* 575 */           KillVertex(eSym.Org, null);
/*     */         }
/*     */         else {
/* 578 */           eSym.Org.anEdge = eSym.Onext;
/* 579 */           Splice(eSym, eSym.Sym.Lnext);
/*     */         }
/* 581 */         KillEdge(e);
/*     */       }
/*     */     }
/* 583 */     while (e != eStart);
/*     */ 
/* 586 */     GLUface fPrev = fZap.prev;
/* 587 */     GLUface fNext = fZap.next;
/* 588 */     fNext.prev = fPrev;
/* 589 */     fPrev.next = fNext;
/*     */   }
/*     */ 
/*     */   public static GLUmesh __gl_meshNewMesh()
/*     */   {
/* 601 */     GLUmesh mesh = new GLUmesh();
/*     */ 
/* 603 */     GLUvertex v = mesh.vHead;
/* 604 */     GLUface f = mesh.fHead;
/* 605 */     GLUhalfEdge e = mesh.eHead;
/* 606 */     GLUhalfEdge eSym = mesh.eHeadSym;
/*     */ 
/* 608 */     v.prev = v; v.next = v;
/* 609 */     v.anEdge = null;
/* 610 */     v.data = null;
/*     */ 
/* 612 */     f.next = (f.prev = f);
/* 613 */     f.anEdge = null;
/* 614 */     f.data = null;
/* 615 */     f.trail = null;
/* 616 */     f.marked = false;
/* 617 */     f.inside = false;
/*     */ 
/* 619 */     e.next = e;
/* 620 */     e.Sym = eSym;
/* 621 */     e.Onext = null;
/* 622 */     e.Lnext = null;
/* 623 */     e.Org = null;
/* 624 */     e.Lface = null;
/* 625 */     e.winding = 0;
/* 626 */     e.activeRegion = null;
/*     */ 
/* 628 */     eSym.next = eSym;
/* 629 */     eSym.Sym = e;
/* 630 */     eSym.Onext = null;
/* 631 */     eSym.Lnext = null;
/* 632 */     eSym.Org = null;
/* 633 */     eSym.Lface = null;
/* 634 */     eSym.winding = 0;
/* 635 */     eSym.activeRegion = null;
/*     */ 
/* 637 */     return mesh;
/*     */   }
/*     */ 
/*     */   static GLUmesh __gl_meshUnion(GLUmesh mesh1, GLUmesh mesh2)
/*     */   {
/* 645 */     GLUface f1 = mesh1.fHead;
/* 646 */     GLUvertex v1 = mesh1.vHead;
/* 647 */     GLUhalfEdge e1 = mesh1.eHead;
/* 648 */     GLUface f2 = mesh2.fHead;
/* 649 */     GLUvertex v2 = mesh2.vHead;
/* 650 */     GLUhalfEdge e2 = mesh2.eHead;
/*     */ 
/* 653 */     if (f2.next != f2) {
/* 654 */       f1.prev.next = f2.next;
/* 655 */       f2.next.prev = f1.prev;
/* 656 */       f2.prev.next = f1;
/* 657 */       f1.prev = f2.prev;
/*     */     }
/*     */ 
/* 660 */     if (v2.next != v2) {
/* 661 */       v1.prev.next = v2.next;
/* 662 */       v2.next.prev = v1.prev;
/* 663 */       v2.prev.next = v1;
/* 664 */       v1.prev = v2.prev;
/*     */     }
/*     */ 
/* 667 */     if (e2.next != e2) {
/* 668 */       e1.Sym.next.Sym.next = e2.next;
/* 669 */       e2.next.Sym.next = e1.Sym.next;
/* 670 */       e2.Sym.next.Sym.next = e1;
/* 671 */       e1.Sym.next = e2.Sym.next;
/*     */     }
/*     */ 
/* 674 */     return mesh1;
/*     */   }
/*     */ 
/*     */   static void __gl_meshDeleteMeshZap(GLUmesh mesh)
/*     */   {
/* 681 */     GLUface fHead = mesh.fHead;
/*     */ 
/* 683 */     while (fHead.next != fHead) {
/* 684 */       __gl_meshZapFace(fHead.next);
/*     */     }
/* 686 */     assert (mesh.vHead.next == mesh.vHead);
/*     */   }
/*     */ 
/*     */   public static void __gl_meshDeleteMesh(GLUmesh mesh)
/*     */   {
/*     */     GLUface fNext;
/* 696 */     for (GLUface f = mesh.fHead.next; f != mesh.fHead; f = fNext)
/* 697 */       fNext = f.next;
/*     */     GLUvertex vNext;
/* 700 */     for (GLUvertex v = mesh.vHead.next; v != mesh.vHead; v = vNext)
/* 701 */       vNext = v.next;
/*     */     GLUhalfEdge eNext;
/* 704 */     for (GLUhalfEdge e = mesh.eHead.next; e != mesh.eHead; e = eNext)
/*     */     {
/* 706 */       eNext = e.next;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void __gl_meshCheckMesh(GLUmesh mesh)
/*     */   {
/* 713 */     GLUface fHead = mesh.fHead;
/* 714 */     GLUvertex vHead = mesh.vHead;
/* 715 */     GLUhalfEdge eHead = mesh.eHead;
/*     */ 
/* 720 */     GLUface fPrev = fHead;
/*     */     GLUface f;
/* 721 */     for (fPrev = fHead; (f = fPrev.next) != fHead; fPrev = f) {
/* 722 */       assert (f.prev == fPrev);
/* 723 */       GLUhalfEdge e = f.anEdge;
/*     */       do {
/* 725 */         assert (e.Sym != e);
/* 726 */         assert (e.Sym.Sym == e);
/* 727 */         assert (e.Lnext.Onext.Sym == e);
/* 728 */         assert (e.Onext.Sym.Lnext == e);
/* 729 */         assert (e.Lface == f);
/* 730 */         e = e.Lnext;
/* 731 */       }while (e != f.anEdge);
/*     */     }
/* 733 */     assert ((f.prev == fPrev) && (f.anEdge == null) && (f.data == null));
/*     */ 
/* 735 */     GLUvertex vPrev = vHead;
/*     */     GLUvertex v;
/* 736 */     for (vPrev = vHead; (v = vPrev.next) != vHead; vPrev = v) {
/* 737 */       assert (v.prev == vPrev);
/* 738 */       GLUhalfEdge e = v.anEdge;
/*     */       do {
/* 740 */         assert (e.Sym != e);
/* 741 */         assert (e.Sym.Sym == e);
/* 742 */         assert (e.Lnext.Onext.Sym == e);
/* 743 */         assert (e.Onext.Sym.Lnext == e);
/* 744 */         assert (e.Org == v);
/* 745 */         e = e.Onext;
/* 746 */       }while (e != v.anEdge);
/*     */     }
/* 748 */     assert ((v.prev == vPrev) && (v.anEdge == null) && (v.data == null));
/*     */ 
/* 750 */     GLUhalfEdge ePrev = eHead;
/*     */     GLUhalfEdge e;
/* 751 */     for (ePrev = eHead; (e = ePrev.next) != eHead; ePrev = e) {
/* 752 */       assert (e.Sym.next == ePrev.Sym);
/* 753 */       assert (e.Sym != e);
/* 754 */       assert (e.Sym.Sym == e);
/* 755 */       assert (e.Org != null);
/* 756 */       assert (e.Sym.Org != null);
/* 757 */       assert (e.Lnext.Onext.Sym == e);
/* 758 */       assert (e.Onext.Sym.Lnext == e);
/*     */     }
/* 760 */     assert ((e.Sym.next == ePrev.Sym) && (e.Sym == mesh.eHeadSym) && (e.Sym.Sym == e) && (e.Org == null) && (e.Sym.Org == null) && (e.Lface == null) && (e.Sym.Lface == null));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.Mesh
 * JD-Core Version:    0.6.2
 */