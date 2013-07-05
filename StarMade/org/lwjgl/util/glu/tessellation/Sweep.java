/*      */ package org.lwjgl.util.glu.tessellation;
/*      */ 
/*      */ class Sweep
/*      */ {
/*      */   private static final boolean TOLERANCE_NONZERO = false;
/*      */   private static final double SENTINEL_COORD = 4.0E+150D;
/*      */ 
/*      */   private static void DebugEvent(GLUtessellatorImpl tess)
/*      */   {
/*      */   }
/*      */ 
/*      */   private static void AddWinding(GLUhalfEdge eDst, GLUhalfEdge eSrc)
/*      */   {
/*  135 */     eDst.winding += eSrc.winding;
/*  136 */     eDst.Sym.winding += eSrc.Sym.winding;
/*      */   }
/*      */ 
/*      */   private static ActiveRegion RegionBelow(ActiveRegion r)
/*      */   {
/*  141 */     return (ActiveRegion)Dict.dictKey(Dict.dictPred(r.nodeUp));
/*      */   }
/*      */ 
/*      */   private static ActiveRegion RegionAbove(ActiveRegion r) {
/*  145 */     return (ActiveRegion)Dict.dictKey(Dict.dictSucc(r.nodeUp));
/*      */   }
/*      */ 
/*      */   static boolean EdgeLeq(GLUtessellatorImpl tess, ActiveRegion reg1, ActiveRegion reg2)
/*      */   {
/*  160 */     GLUvertex event = tess.event;
/*      */ 
/*  164 */     GLUhalfEdge e1 = reg1.eUp;
/*  165 */     GLUhalfEdge e2 = reg2.eUp;
/*      */ 
/*  167 */     if (e1.Sym.Org == event) {
/*  168 */       if (e2.Sym.Org == event)
/*      */       {
/*  172 */         if (Geom.VertLeq(e1.Org, e2.Org)) {
/*  173 */           return Geom.EdgeSign(e2.Sym.Org, e1.Org, e2.Org) <= 0.0D;
/*      */         }
/*  175 */         return Geom.EdgeSign(e1.Sym.Org, e2.Org, e1.Org) >= 0.0D;
/*      */       }
/*  177 */       return Geom.EdgeSign(e2.Sym.Org, event, e2.Org) <= 0.0D;
/*      */     }
/*  179 */     if (e2.Sym.Org == event) {
/*  180 */       return Geom.EdgeSign(e1.Sym.Org, event, e1.Org) >= 0.0D;
/*      */     }
/*      */ 
/*  184 */     double t1 = Geom.EdgeEval(e1.Sym.Org, event, e1.Org);
/*  185 */     double t2 = Geom.EdgeEval(e2.Sym.Org, event, e2.Org);
/*  186 */     return t1 >= t2;
/*      */   }
/*      */ 
/*      */   static void DeleteRegion(GLUtessellatorImpl tess, ActiveRegion reg)
/*      */   {
/*  191 */     if (reg.fixUpperEdge)
/*      */     {
/*  196 */       assert (reg.eUp.winding == 0);
/*      */     }
/*  198 */     reg.eUp.activeRegion = null;
/*  199 */     Dict.dictDelete(tess.dict, reg.nodeUp);
/*      */   }
/*      */ 
/*      */   static boolean FixUpperEdge(ActiveRegion reg, GLUhalfEdge newEdge)
/*      */   {
/*  207 */     assert (reg.fixUpperEdge);
/*  208 */     if (!Mesh.__gl_meshDelete(reg.eUp)) return false;
/*  209 */     reg.fixUpperEdge = false;
/*  210 */     reg.eUp = newEdge;
/*  211 */     newEdge.activeRegion = reg;
/*      */ 
/*  213 */     return true;
/*      */   }
/*      */ 
/*      */   static ActiveRegion TopLeftRegion(ActiveRegion reg) {
/*  217 */     GLUvertex org = reg.eUp.Org;
/*      */     do
/*      */     {
/*  222 */       reg = RegionAbove(reg);
/*  223 */     }while (reg.eUp.Org == org);
/*      */ 
/*  228 */     if (reg.fixUpperEdge) {
/*  229 */       GLUhalfEdge e = Mesh.__gl_meshConnect(RegionBelow(reg).eUp.Sym, reg.eUp.Lnext);
/*  230 */       if (e == null) return null;
/*  231 */       if (!FixUpperEdge(reg, e)) return null;
/*  232 */       reg = RegionAbove(reg);
/*      */     }
/*  234 */     return reg;
/*      */   }
/*      */ 
/*      */   static ActiveRegion TopRightRegion(ActiveRegion reg) {
/*  238 */     GLUvertex dst = reg.eUp.Sym.Org;
/*      */     do
/*      */     {
/*  242 */       reg = RegionAbove(reg);
/*  243 */     }while (reg.eUp.Sym.Org == dst);
/*  244 */     return reg;
/*      */   }
/*      */ 
/*      */   static ActiveRegion AddRegionBelow(GLUtessellatorImpl tess, ActiveRegion regAbove, GLUhalfEdge eNewUp)
/*      */   {
/*  256 */     ActiveRegion regNew = new ActiveRegion();
/*  257 */     if (regNew == null) throw new RuntimeException();
/*      */ 
/*  259 */     regNew.eUp = eNewUp;
/*      */ 
/*  261 */     regNew.nodeUp = Dict.dictInsertBefore(tess.dict, regAbove.nodeUp, regNew);
/*  262 */     if (regNew.nodeUp == null) throw new RuntimeException();
/*  263 */     regNew.fixUpperEdge = false;
/*  264 */     regNew.sentinel = false;
/*  265 */     regNew.dirty = false;
/*      */ 
/*  267 */     eNewUp.activeRegion = regNew;
/*  268 */     return regNew;
/*      */   }
/*      */ 
/*      */   static boolean IsWindingInside(GLUtessellatorImpl tess, int n) {
/*  272 */     switch (tess.windingRule) {
/*      */     case 100130:
/*  274 */       return (n & 0x1) != 0;
/*      */     case 100131:
/*  276 */       return n != 0;
/*      */     case 100132:
/*  278 */       return n > 0;
/*      */     case 100133:
/*  280 */       return n < 0;
/*      */     case 100134:
/*  282 */       return (n >= 2) || (n <= -2);
/*      */     }
/*      */ 
/*  286 */     throw new InternalError();
/*      */   }
/*      */ 
/*      */   static void ComputeWinding(GLUtessellatorImpl tess, ActiveRegion reg)
/*      */   {
/*  292 */     reg.windingNumber = (RegionAbove(reg).windingNumber + reg.eUp.winding);
/*  293 */     reg.inside = IsWindingInside(tess, reg.windingNumber);
/*      */   }
/*      */ 
/*      */   static void FinishRegion(GLUtessellatorImpl tess, ActiveRegion reg)
/*      */   {
/*  305 */     GLUhalfEdge e = reg.eUp;
/*  306 */     GLUface f = e.Lface;
/*      */ 
/*  308 */     f.inside = reg.inside;
/*  309 */     f.anEdge = e;
/*  310 */     DeleteRegion(tess, reg);
/*      */   }
/*      */ 
/*      */   static GLUhalfEdge FinishLeftRegions(GLUtessellatorImpl tess, ActiveRegion regFirst, ActiveRegion regLast)
/*      */   {
/*  331 */     ActiveRegion regPrev = regFirst;
/*  332 */     GLUhalfEdge ePrev = regFirst.eUp;
/*  333 */     while (regPrev != regLast) {
/*  334 */       regPrev.fixUpperEdge = false;
/*  335 */       ActiveRegion reg = RegionBelow(regPrev);
/*  336 */       GLUhalfEdge e = reg.eUp;
/*  337 */       if (e.Org != ePrev.Org) {
/*  338 */         if (!reg.fixUpperEdge)
/*      */         {
/*  345 */           FinishRegion(tess, regPrev);
/*  346 */           break;
/*      */         }
/*      */ 
/*  351 */         e = Mesh.__gl_meshConnect(ePrev.Onext.Sym, e.Sym);
/*  352 */         if (e == null) throw new RuntimeException();
/*  353 */         if (!FixUpperEdge(reg, e)) throw new RuntimeException();
/*      */ 
/*      */       }
/*      */ 
/*  357 */       if (ePrev.Onext != e) {
/*  358 */         if (!Mesh.__gl_meshSplice(e.Sym.Lnext, e)) throw new RuntimeException();
/*  359 */         if (!Mesh.__gl_meshSplice(ePrev, e)) throw new RuntimeException();
/*      */       }
/*  361 */       FinishRegion(tess, regPrev);
/*  362 */       ePrev = reg.eUp;
/*  363 */       regPrev = reg;
/*      */     }
/*  365 */     return ePrev;
/*      */   }
/*      */ 
/*      */   static void AddRightEdges(GLUtessellatorImpl tess, ActiveRegion regUp, GLUhalfEdge eFirst, GLUhalfEdge eLast, GLUhalfEdge eTopLeft, boolean cleanUp)
/*      */   {
/*  384 */     boolean firstTime = true;
/*      */ 
/*  387 */     GLUhalfEdge e = eFirst;
/*      */     do {
/*  389 */       assert (Geom.VertLeq(e.Org, e.Sym.Org));
/*  390 */       AddRegionBelow(tess, regUp, e.Sym);
/*  391 */       e = e.Onext;
/*  392 */     }while (e != eLast);
/*      */ 
/*  398 */     if (eTopLeft == null) {
/*  399 */       eTopLeft = RegionBelow(regUp).eUp.Sym.Onext; } ActiveRegion regPrev = regUp;
/*  402 */     GLUhalfEdge ePrev = eTopLeft;
/*      */     ActiveRegion reg;
/*      */     while (true) { reg = RegionBelow(regPrev);
/*  405 */       e = reg.eUp.Sym;
/*  406 */       if (e.Org != ePrev.Org)
/*      */         break;
/*  408 */       if (e.Onext != ePrev)
/*      */       {
/*  410 */         if (!Mesh.__gl_meshSplice(e.Sym.Lnext, e)) throw new RuntimeException();
/*  411 */         if (!Mesh.__gl_meshSplice(ePrev.Sym.Lnext, e)) throw new RuntimeException();
/*      */       }
/*      */ 
/*  414 */       regPrev.windingNumber -= e.winding;
/*  415 */       reg.inside = IsWindingInside(tess, reg.windingNumber);
/*      */ 
/*  420 */       regPrev.dirty = true;
/*  421 */       if ((!firstTime) && (CheckForRightSplice(tess, regPrev))) {
/*  422 */         AddWinding(e, ePrev);
/*  423 */         DeleteRegion(tess, regPrev);
/*  424 */         if (!Mesh.__gl_meshDelete(ePrev)) throw new RuntimeException();
/*      */       }
/*  426 */       firstTime = false;
/*  427 */       regPrev = reg;
/*  428 */       ePrev = e;
/*      */     }
/*  430 */     regPrev.dirty = true;
/*  431 */     assert (regPrev.windingNumber - e.winding == reg.windingNumber);
/*      */ 
/*  433 */     if (cleanUp)
/*      */     {
/*  435 */       WalkDirtyRegions(tess, regPrev);
/*      */     }
/*      */   }
/*      */ 
/*      */   static void CallCombine(GLUtessellatorImpl tess, GLUvertex isect, Object[] data, float[] weights, boolean needed)
/*      */   {
/*  442 */     double[] coords = new double[3];
/*      */ 
/*  445 */     coords[0] = isect.coords[0];
/*  446 */     coords[1] = isect.coords[1];
/*  447 */     coords[2] = isect.coords[2];
/*      */ 
/*  449 */     Object[] outData = new Object[1];
/*  450 */     tess.callCombineOrCombineData(coords, data, weights, outData);
/*  451 */     isect.data = outData[0];
/*  452 */     if (isect.data == null)
/*  453 */       if (!needed) {
/*  454 */         isect.data = data[0];
/*  455 */       } else if (!tess.fatalError)
/*      */       {
/*  460 */         tess.callErrorOrErrorData(100156);
/*  461 */         tess.fatalError = true;
/*      */       }
/*      */   }
/*      */ 
/*      */   static void SpliceMergeVertices(GLUtessellatorImpl tess, GLUhalfEdge e1, GLUhalfEdge e2)
/*      */   {
/*  472 */     Object[] data = new Object[4];
/*  473 */     float[] weights = { 0.5F, 0.5F, 0.0F, 0.0F };
/*      */ 
/*  475 */     data[0] = e1.Org.data;
/*  476 */     data[1] = e2.Org.data;
/*  477 */     CallCombine(tess, e1.Org, data, weights, false);
/*  478 */     if (!Mesh.__gl_meshSplice(e1, e2)) throw new RuntimeException();
/*      */   }
/*      */ 
/*      */   static void VertexWeights(GLUvertex isect, GLUvertex org, GLUvertex dst, float[] weights)
/*      */   {
/*  490 */     double t1 = Geom.VertL1dist(org, isect);
/*  491 */     double t2 = Geom.VertL1dist(dst, isect);
/*      */ 
/*  493 */     weights[0] = ((float)(0.5D * t2 / (t1 + t2)));
/*  494 */     weights[1] = ((float)(0.5D * t1 / (t1 + t2)));
/*  495 */     isect.coords[0] += weights[0] * org.coords[0] + weights[1] * dst.coords[0];
/*  496 */     isect.coords[1] += weights[0] * org.coords[1] + weights[1] * dst.coords[1];
/*  497 */     isect.coords[2] += weights[0] * org.coords[2] + weights[1] * dst.coords[2];
/*      */   }
/*      */ 
/*      */   static void GetIntersectData(GLUtessellatorImpl tess, GLUvertex isect, GLUvertex orgUp, GLUvertex dstUp, GLUvertex orgLo, GLUvertex dstLo)
/*      */   {
/*  509 */     Object[] data = new Object[4];
/*  510 */     float[] weights = new float[4];
/*  511 */     float[] weights1 = new float[2];
/*  512 */     float[] weights2 = new float[2];
/*      */ 
/*  514 */     data[0] = orgUp.data;
/*  515 */     data[1] = dstUp.data;
/*  516 */     data[2] = orgLo.data;
/*  517 */     data[3] = dstLo.data;
/*      */     double tmp73_72 = (isect.coords[2] = 0.0D); isect.coords[1] = tmp73_72; isect.coords[0] = tmp73_72;
/*  520 */     VertexWeights(isect, orgUp, dstUp, weights1);
/*  521 */     VertexWeights(isect, orgLo, dstLo, weights2);
/*  522 */     System.arraycopy(weights1, 0, weights, 0, 2);
/*  523 */     System.arraycopy(weights2, 0, weights, 2, 2);
/*      */ 
/*  525 */     CallCombine(tess, isect, data, weights, true);
/*      */   }
/*      */ 
/*      */   static boolean CheckForRightSplice(GLUtessellatorImpl tess, ActiveRegion regUp)
/*      */   {
/*  554 */     ActiveRegion regLo = RegionBelow(regUp);
/*  555 */     GLUhalfEdge eUp = regUp.eUp;
/*  556 */     GLUhalfEdge eLo = regLo.eUp;
/*      */ 
/*  558 */     if (Geom.VertLeq(eUp.Org, eLo.Org)) {
/*  559 */       if (Geom.EdgeSign(eLo.Sym.Org, eUp.Org, eLo.Org) > 0.0D) return false;
/*      */ 
/*  562 */       if (!Geom.VertEq(eUp.Org, eLo.Org))
/*      */       {
/*  564 */         if (Mesh.__gl_meshSplitEdge(eLo.Sym) == null) throw new RuntimeException();
/*  565 */         if (!Mesh.__gl_meshSplice(eUp, eLo.Sym.Lnext)) throw new RuntimeException();
/*  566 */         regUp.dirty = (regLo.dirty = 1);
/*      */       }
/*  568 */       else if (eUp.Org != eLo.Org)
/*      */       {
/*  570 */         tess.pq.pqDelete(eUp.Org.pqHandle);
/*  571 */         SpliceMergeVertices(tess, eLo.Sym.Lnext, eUp);
/*      */       }
/*      */     } else {
/*  574 */       if (Geom.EdgeSign(eUp.Sym.Org, eLo.Org, eUp.Org) < 0.0D) return false;
/*      */ 
/*  577 */       RegionAbove(regUp).dirty = (regUp.dirty = 1);
/*  578 */       if (Mesh.__gl_meshSplitEdge(eUp.Sym) == null) throw new RuntimeException();
/*  579 */       if (!Mesh.__gl_meshSplice(eLo.Sym.Lnext, eUp)) throw new RuntimeException();
/*      */     }
/*  581 */     return true;
/*      */   }
/*      */ 
/*      */   static boolean CheckForLeftSplice(GLUtessellatorImpl tess, ActiveRegion regUp)
/*      */   {
/*  603 */     ActiveRegion regLo = RegionBelow(regUp);
/*  604 */     GLUhalfEdge eUp = regUp.eUp;
/*  605 */     GLUhalfEdge eLo = regLo.eUp;
/*      */ 
/*  608 */     assert (!Geom.VertEq(eUp.Sym.Org, eLo.Sym.Org));
/*      */ 
/*  610 */     if (Geom.VertLeq(eUp.Sym.Org, eLo.Sym.Org)) {
/*  611 */       if (Geom.EdgeSign(eUp.Sym.Org, eLo.Sym.Org, eUp.Org) < 0.0D) return false;
/*      */ 
/*  614 */       RegionAbove(regUp).dirty = (regUp.dirty = 1);
/*  615 */       GLUhalfEdge e = Mesh.__gl_meshSplitEdge(eUp);
/*  616 */       if (e == null) throw new RuntimeException();
/*  617 */       if (!Mesh.__gl_meshSplice(eLo.Sym, e)) throw new RuntimeException();
/*  618 */       e.Lface.inside = regUp.inside;
/*      */     } else {
/*  620 */       if (Geom.EdgeSign(eLo.Sym.Org, eUp.Sym.Org, eLo.Org) > 0.0D) return false;
/*      */ 
/*  623 */       regUp.dirty = (regLo.dirty = 1);
/*  624 */       GLUhalfEdge e = Mesh.__gl_meshSplitEdge(eLo);
/*  625 */       if (e == null) throw new RuntimeException();
/*  626 */       if (!Mesh.__gl_meshSplice(eUp.Lnext, eLo.Sym)) throw new RuntimeException();
/*  627 */       e.Sym.Lface.inside = regUp.inside;
/*      */     }
/*  629 */     return true;
/*      */   }
/*      */ 
/*      */   static boolean CheckForIntersect(GLUtessellatorImpl tess, ActiveRegion regUp)
/*      */   {
/*  643 */     ActiveRegion regLo = RegionBelow(regUp);
/*  644 */     GLUhalfEdge eUp = regUp.eUp;
/*  645 */     GLUhalfEdge eLo = regLo.eUp;
/*  646 */     GLUvertex orgUp = eUp.Org;
/*  647 */     GLUvertex orgLo = eLo.Org;
/*  648 */     GLUvertex dstUp = eUp.Sym.Org;
/*  649 */     GLUvertex dstLo = eLo.Sym.Org;
/*      */ 
/*  651 */     GLUvertex isect = new GLUvertex();
/*      */ 
/*  655 */     assert (!Geom.VertEq(dstLo, dstUp));
/*  656 */     assert (Geom.EdgeSign(dstUp, tess.event, orgUp) <= 0.0D);
/*  657 */     assert (Geom.EdgeSign(dstLo, tess.event, orgLo) >= 0.0D);
/*  658 */     assert ((orgUp != tess.event) && (orgLo != tess.event));
/*  659 */     assert ((!regUp.fixUpperEdge) && (!regLo.fixUpperEdge));
/*      */ 
/*  661 */     if (orgUp == orgLo) return false;
/*      */ 
/*  663 */     double tMinUp = Math.min(orgUp.t, dstUp.t);
/*  664 */     double tMaxLo = Math.max(orgLo.t, dstLo.t);
/*  665 */     if (tMinUp > tMaxLo) return false;
/*      */ 
/*  667 */     if (Geom.VertLeq(orgUp, orgLo)) {
/*  668 */       if (Geom.EdgeSign(dstLo, orgUp, orgLo) > 0.0D) return false;
/*      */     }
/*  670 */     else if (Geom.EdgeSign(dstUp, orgLo, orgUp) < 0.0D) return false;
/*      */ 
/*  674 */     DebugEvent(tess);
/*      */ 
/*  676 */     Geom.EdgeIntersect(dstUp, orgUp, dstLo, orgLo, isect);
/*      */ 
/*  678 */     assert (Math.min(orgUp.t, dstUp.t) <= isect.t);
/*  679 */     assert (isect.t <= Math.max(orgLo.t, dstLo.t));
/*  680 */     assert (Math.min(dstLo.s, dstUp.s) <= isect.s);
/*  681 */     assert (isect.s <= Math.max(orgLo.s, orgUp.s));
/*      */ 
/*  683 */     if (Geom.VertLeq(isect, tess.event))
/*      */     {
/*  690 */       isect.s = tess.event.s;
/*  691 */       isect.t = tess.event.t;
/*      */     }
/*      */ 
/*  699 */     GLUvertex orgMin = Geom.VertLeq(orgUp, orgLo) ? orgUp : orgLo;
/*  700 */     if (Geom.VertLeq(orgMin, isect)) {
/*  701 */       isect.s = orgMin.s;
/*  702 */       isect.t = orgMin.t;
/*      */     }
/*      */ 
/*  705 */     if ((Geom.VertEq(isect, orgUp)) || (Geom.VertEq(isect, orgLo)))
/*      */     {
/*  707 */       CheckForRightSplice(tess, regUp);
/*  708 */       return false;
/*      */     }
/*      */ 
/*  711 */     if (((!Geom.VertEq(dstUp, tess.event)) && (Geom.EdgeSign(dstUp, tess.event, isect) >= 0.0D)) || ((!Geom.VertEq(dstLo, tess.event)) && (Geom.EdgeSign(dstLo, tess.event, isect) <= 0.0D)))
/*      */     {
/*  719 */       if (dstLo == tess.event)
/*      */       {
/*  721 */         if (Mesh.__gl_meshSplitEdge(eUp.Sym) == null) throw new RuntimeException();
/*  722 */         if (!Mesh.__gl_meshSplice(eLo.Sym, eUp)) throw new RuntimeException();
/*  723 */         regUp = TopLeftRegion(regUp);
/*  724 */         if (regUp == null) throw new RuntimeException();
/*  725 */         eUp = RegionBelow(regUp).eUp;
/*  726 */         FinishLeftRegions(tess, RegionBelow(regUp), regLo);
/*  727 */         AddRightEdges(tess, regUp, eUp.Sym.Lnext, eUp, eUp, true);
/*  728 */         return true;
/*      */       }
/*  730 */       if (dstUp == tess.event)
/*      */       {
/*  732 */         if (Mesh.__gl_meshSplitEdge(eLo.Sym) == null) throw new RuntimeException();
/*  733 */         if (!Mesh.__gl_meshSplice(eUp.Lnext, eLo.Sym.Lnext)) throw new RuntimeException();
/*  734 */         regLo = regUp;
/*  735 */         regUp = TopRightRegion(regUp);
/*  736 */         GLUhalfEdge e = RegionBelow(regUp).eUp.Sym.Onext;
/*  737 */         regLo.eUp = eLo.Sym.Lnext;
/*  738 */         eLo = FinishLeftRegions(tess, regLo, null);
/*  739 */         AddRightEdges(tess, regUp, eLo.Onext, eUp.Sym.Onext, e, true);
/*  740 */         return true;
/*      */       }
/*      */ 
/*  746 */       if (Geom.EdgeSign(dstUp, tess.event, isect) >= 0.0D) {
/*  747 */         RegionAbove(regUp).dirty = (regUp.dirty = 1);
/*  748 */         if (Mesh.__gl_meshSplitEdge(eUp.Sym) == null) throw new RuntimeException();
/*  749 */         eUp.Org.s = tess.event.s;
/*  750 */         eUp.Org.t = tess.event.t;
/*      */       }
/*  752 */       if (Geom.EdgeSign(dstLo, tess.event, isect) <= 0.0D) {
/*  753 */         regUp.dirty = (regLo.dirty = 1);
/*  754 */         if (Mesh.__gl_meshSplitEdge(eLo.Sym) == null) throw new RuntimeException();
/*  755 */         eLo.Org.s = tess.event.s;
/*  756 */         eLo.Org.t = tess.event.t;
/*      */       }
/*      */ 
/*  759 */       return false;
/*      */     }
/*      */ 
/*  770 */     if (Mesh.__gl_meshSplitEdge(eUp.Sym) == null) throw new RuntimeException();
/*  771 */     if (Mesh.__gl_meshSplitEdge(eLo.Sym) == null) throw new RuntimeException();
/*  772 */     if (!Mesh.__gl_meshSplice(eLo.Sym.Lnext, eUp)) throw new RuntimeException();
/*  773 */     eUp.Org.s = isect.s;
/*  774 */     eUp.Org.t = isect.t;
/*  775 */     eUp.Org.pqHandle = tess.pq.pqInsert(eUp.Org);
/*  776 */     if (eUp.Org.pqHandle == 9223372036854775807L) {
/*  777 */       tess.pq.pqDeletePriorityQ();
/*  778 */       tess.pq = null;
/*  779 */       throw new RuntimeException();
/*      */     }
/*  781 */     GetIntersectData(tess, eUp.Org, orgUp, dstUp, orgLo, dstLo);
/*  782 */     RegionAbove(regUp).dirty = (regUp.dirty = regLo.dirty = 1);
/*  783 */     return false;
/*      */   }
/*      */ 
/*      */   static void WalkDirtyRegions(GLUtessellatorImpl tess, ActiveRegion regUp)
/*      */   {
/*  795 */     ActiveRegion regLo = RegionBelow(regUp);
/*      */     while (true)
/*      */     {
/*  800 */       if (regLo.dirty) {
/*  801 */         regUp = regLo;
/*  802 */         regLo = RegionBelow(regLo);
/*      */       } else {
/*  804 */         if (!regUp.dirty) {
/*  805 */           regLo = regUp;
/*  806 */           regUp = RegionAbove(regUp);
/*  807 */           if ((regUp == null) || (!regUp.dirty))
/*      */           {
/*  809 */             return;
/*      */           }
/*      */         }
/*  812 */         regUp.dirty = false;
/*  813 */         GLUhalfEdge eUp = regUp.eUp;
/*  814 */         GLUhalfEdge eLo = regLo.eUp;
/*      */ 
/*  816 */         if (eUp.Sym.Org != eLo.Sym.Org)
/*      */         {
/*  818 */           if (CheckForLeftSplice(tess, regUp))
/*      */           {
/*  824 */             if (regLo.fixUpperEdge) {
/*  825 */               DeleteRegion(tess, regLo);
/*  826 */               if (!Mesh.__gl_meshDelete(eLo)) throw new RuntimeException();
/*  827 */               regLo = RegionBelow(regUp);
/*  828 */               eLo = regLo.eUp;
/*  829 */             } else if (regUp.fixUpperEdge) {
/*  830 */               DeleteRegion(tess, regUp);
/*  831 */               if (!Mesh.__gl_meshDelete(eUp)) throw new RuntimeException();
/*  832 */               regUp = RegionAbove(regLo);
/*  833 */               eUp = regUp.eUp;
/*      */             }
/*      */           }
/*      */         }
/*  837 */         if (eUp.Org != eLo.Org) {
/*  838 */           if ((eUp.Sym.Org != eLo.Sym.Org) && (!regUp.fixUpperEdge) && (!regLo.fixUpperEdge) && ((eUp.Sym.Org == tess.event) || (eLo.Sym.Org == tess.event)))
/*      */           {
/*  849 */             if (!CheckForIntersect(tess, regUp));
/*      */           }
/*      */           else
/*      */           {
/*  857 */             CheckForRightSplice(tess, regUp);
/*      */           }
/*      */         }
/*  860 */         if ((eUp.Org == eLo.Org) && (eUp.Sym.Org == eLo.Sym.Org))
/*      */         {
/*  862 */           AddWinding(eLo, eUp);
/*  863 */           DeleteRegion(tess, regUp);
/*  864 */           if (!Mesh.__gl_meshDelete(eUp)) throw new RuntimeException();
/*  865 */           regUp = RegionAbove(regLo);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   static void ConnectRightVertex(GLUtessellatorImpl tess, ActiveRegion regUp, GLUhalfEdge eBottomLeft)
/*      */   {
/*  905 */     GLUhalfEdge eTopLeft = eBottomLeft.Onext;
/*  906 */     ActiveRegion regLo = RegionBelow(regUp);
/*  907 */     GLUhalfEdge eUp = regUp.eUp;
/*  908 */     GLUhalfEdge eLo = regLo.eUp;
/*  909 */     boolean degenerate = false;
/*      */ 
/*  911 */     if (eUp.Sym.Org != eLo.Sym.Org) {
/*  912 */       CheckForIntersect(tess, regUp);
/*      */     }
/*      */ 
/*  918 */     if (Geom.VertEq(eUp.Org, tess.event)) {
/*  919 */       if (!Mesh.__gl_meshSplice(eTopLeft.Sym.Lnext, eUp)) throw new RuntimeException();
/*  920 */       regUp = TopLeftRegion(regUp);
/*  921 */       if (regUp == null) throw new RuntimeException();
/*  922 */       eTopLeft = RegionBelow(regUp).eUp;
/*  923 */       FinishLeftRegions(tess, RegionBelow(regUp), regLo);
/*  924 */       degenerate = true;
/*      */     }
/*  926 */     if (Geom.VertEq(eLo.Org, tess.event)) {
/*  927 */       if (!Mesh.__gl_meshSplice(eBottomLeft, eLo.Sym.Lnext)) throw new RuntimeException();
/*  928 */       eBottomLeft = FinishLeftRegions(tess, regLo, null);
/*  929 */       degenerate = true;
/*      */     }
/*  931 */     if (degenerate) {
/*  932 */       AddRightEdges(tess, regUp, eBottomLeft.Onext, eTopLeft, eTopLeft, true);
/*      */       return;
/*      */     }
/*      */     GLUhalfEdge eNew;
/*  939 */     if (Geom.VertLeq(eLo.Org, eUp.Org))
/*  940 */       eNew = eLo.Sym.Lnext;
/*      */     else {
/*  942 */       eNew = eUp;
/*      */     }
/*  944 */     GLUhalfEdge eNew = Mesh.__gl_meshConnect(eBottomLeft.Onext.Sym, eNew);
/*  945 */     if (eNew == null) throw new RuntimeException();
/*      */ 
/*  950 */     AddRightEdges(tess, regUp, eNew, eNew.Onext, eNew.Onext, false);
/*  951 */     eNew.Sym.activeRegion.fixUpperEdge = true;
/*  952 */     WalkDirtyRegions(tess, regUp);
/*      */   }
/*      */ 
/*      */   static void ConnectLeftDegenerate(GLUtessellatorImpl tess, ActiveRegion regUp, GLUvertex vEvent)
/*      */   {
/*  974 */     GLUhalfEdge e = regUp.eUp;
/*  975 */     if (Geom.VertEq(e.Org, vEvent))
/*      */     {
/*  979 */       if (!$assertionsDisabled) throw new AssertionError();
/*  980 */       SpliceMergeVertices(tess, e, vEvent.anEdge);
/*  981 */       return;
/*      */     }
/*      */ 
/*  984 */     if (!Geom.VertEq(e.Sym.Org, vEvent))
/*      */     {
/*  986 */       if (Mesh.__gl_meshSplitEdge(e.Sym) == null) throw new RuntimeException();
/*  987 */       if (regUp.fixUpperEdge)
/*      */       {
/*  989 */         if (!Mesh.__gl_meshDelete(e.Onext)) throw new RuntimeException();
/*  990 */         regUp.fixUpperEdge = false;
/*      */       }
/*  992 */       if (!Mesh.__gl_meshSplice(vEvent.anEdge, e)) throw new RuntimeException();
/*  993 */       SweepEvent(tess, vEvent);
/*  994 */       return;
/*      */     }
/*      */ 
/* 1000 */     if (!$assertionsDisabled) throw new AssertionError();
/* 1001 */     regUp = TopRightRegion(regUp);
/* 1002 */     ActiveRegion reg = RegionBelow(regUp);
/* 1003 */     GLUhalfEdge eTopRight = reg.eUp.Sym;
/*      */     GLUhalfEdge eLast;
/* 1004 */     GLUhalfEdge eTopLeft = eLast = eTopRight.Onext;
/* 1005 */     if (reg.fixUpperEdge)
/*      */     {
/* 1009 */       assert (eTopLeft != eTopRight);
/* 1010 */       DeleteRegion(tess, reg);
/* 1011 */       if (!Mesh.__gl_meshDelete(eTopRight)) throw new RuntimeException();
/* 1012 */       eTopRight = eTopLeft.Sym.Lnext;
/*      */     }
/* 1014 */     if (!Mesh.__gl_meshSplice(vEvent.anEdge, eTopRight)) throw new RuntimeException();
/* 1015 */     if (!Geom.EdgeGoesLeft(eTopLeft))
/*      */     {
/* 1017 */       eTopLeft = null;
/*      */     }
/* 1019 */     AddRightEdges(tess, regUp, eTopRight.Onext, eLast, eTopLeft, true);
/*      */   }
/*      */ 
/*      */   static void ConnectLeftVertex(GLUtessellatorImpl tess, GLUvertex vEvent)
/*      */   {
/* 1041 */     ActiveRegion tmp = new ActiveRegion();
/*      */ 
/* 1046 */     tmp.eUp = vEvent.anEdge.Sym;
/*      */ 
/* 1048 */     ActiveRegion regUp = (ActiveRegion)Dict.dictKey(Dict.dictSearch(tess.dict, tmp));
/* 1049 */     ActiveRegion regLo = RegionBelow(regUp);
/* 1050 */     GLUhalfEdge eUp = regUp.eUp;
/* 1051 */     GLUhalfEdge eLo = regLo.eUp;
/*      */ 
/* 1054 */     if (Geom.EdgeSign(eUp.Sym.Org, vEvent, eUp.Org) == 0.0D) {
/* 1055 */       ConnectLeftDegenerate(tess, regUp, vEvent);
/* 1056 */       return;
/*      */     }
/*      */ 
/* 1062 */     ActiveRegion reg = Geom.VertLeq(eLo.Sym.Org, eUp.Sym.Org) ? regUp : regLo;
/*      */ 
/* 1064 */     if ((regUp.inside) || (reg.fixUpperEdge))
/*      */     {
/*      */       GLUhalfEdge eNew;
/* 1065 */       if (reg == regUp) {
/* 1066 */         GLUhalfEdge eNew = Mesh.__gl_meshConnect(vEvent.anEdge.Sym, eUp.Lnext);
/* 1067 */         if (eNew == null) throw new RuntimeException(); 
/*      */       }
/* 1069 */       else { GLUhalfEdge tempHalfEdge = Mesh.__gl_meshConnect(eLo.Sym.Onext.Sym, vEvent.anEdge);
/* 1070 */         if (tempHalfEdge == null) throw new RuntimeException();
/*      */ 
/* 1072 */         eNew = tempHalfEdge.Sym;
/*      */       }
/* 1074 */       if (reg.fixUpperEdge) {
/* 1075 */         if (!FixUpperEdge(reg, eNew)) throw new RuntimeException(); 
/*      */       }
/*      */       else {
/* 1077 */         ComputeWinding(tess, AddRegionBelow(tess, regUp, eNew));
/*      */       }
/* 1079 */       SweepEvent(tess, vEvent);
/*      */     }
/*      */     else
/*      */     {
/* 1084 */       AddRightEdges(tess, regUp, vEvent.anEdge, vEvent.anEdge, null, true);
/*      */     }
/*      */   }
/*      */ 
/*      */   static void SweepEvent(GLUtessellatorImpl tess, GLUvertex vEvent)
/*      */   {
/* 1097 */     tess.event = vEvent;
/* 1098 */     DebugEvent(tess);
/*      */ 
/* 1104 */     GLUhalfEdge e = vEvent.anEdge;
/* 1105 */     while (e.activeRegion == null) {
/* 1106 */       e = e.Onext;
/* 1107 */       if (e == vEvent.anEdge)
/*      */       {
/* 1109 */         ConnectLeftVertex(tess, vEvent);
/* 1110 */         return;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1121 */     ActiveRegion regUp = TopLeftRegion(e.activeRegion);
/* 1122 */     if (regUp == null) throw new RuntimeException();
/* 1123 */     ActiveRegion reg = RegionBelow(regUp);
/* 1124 */     GLUhalfEdge eTopLeft = reg.eUp;
/* 1125 */     GLUhalfEdge eBottomLeft = FinishLeftRegions(tess, reg, null);
/*      */ 
/* 1132 */     if (eBottomLeft.Onext == eTopLeft)
/*      */     {
/* 1134 */       ConnectRightVertex(tess, regUp, eBottomLeft);
/*      */     }
/* 1136 */     else AddRightEdges(tess, regUp, eBottomLeft.Onext, eTopLeft, eTopLeft, true);
/*      */   }
/*      */ 
/*      */   static void AddSentinel(GLUtessellatorImpl tess, double t)
/*      */   {
/* 1154 */     ActiveRegion reg = new ActiveRegion();
/* 1155 */     if (reg == null) throw new RuntimeException();
/*      */ 
/* 1157 */     GLUhalfEdge e = Mesh.__gl_meshMakeEdge(tess.mesh);
/* 1158 */     if (e == null) throw new RuntimeException();
/*      */ 
/* 1160 */     e.Org.s = 4.0E+150D;
/* 1161 */     e.Org.t = t;
/* 1162 */     e.Sym.Org.s = -4.0E+150D;
/* 1163 */     e.Sym.Org.t = t;
/* 1164 */     tess.event = e.Sym.Org;
/*      */ 
/* 1166 */     reg.eUp = e;
/* 1167 */     reg.windingNumber = 0;
/* 1168 */     reg.inside = false;
/* 1169 */     reg.fixUpperEdge = false;
/* 1170 */     reg.sentinel = true;
/* 1171 */     reg.dirty = false;
/* 1172 */     reg.nodeUp = Dict.dictInsert(tess.dict, reg);
/* 1173 */     if (reg.nodeUp == null) throw new RuntimeException();
/*      */   }
/*      */ 
/*      */   static void InitEdgeDict(GLUtessellatorImpl tess)
/*      */   {
/* 1183 */     tess.dict = Dict.dictNewDict(tess, new Dict.DictLeq() {
/*      */       public boolean leq(Object frame, Object key1, Object key2) {
/* 1185 */         return Sweep.EdgeLeq(this.val$tess, (ActiveRegion)key1, (ActiveRegion)key2);
/*      */       }
/*      */     });
/* 1188 */     if (tess.dict == null) throw new RuntimeException();
/*      */ 
/* 1190 */     AddSentinel(tess, -4.0E+150D);
/* 1191 */     AddSentinel(tess, 4.0E+150D);
/*      */   }
/*      */ 
/*      */   static void DoneEdgeDict(GLUtessellatorImpl tess)
/*      */   {
/* 1197 */     int fixedEdges = 0;
/*      */     ActiveRegion reg;
/* 1200 */     while ((reg = (ActiveRegion)Dict.dictKey(Dict.dictMin(tess.dict))) != null)
/*      */     {
/* 1206 */       if (!reg.sentinel) {
/* 1207 */         assert (reg.fixUpperEdge);
/* 1208 */         if (!$assertionsDisabled) { fixedEdges++; if (fixedEdges != 1) throw new AssertionError();  }
/*      */ 
/*      */       }
/* 1210 */       assert (reg.windingNumber == 0);
/* 1211 */       DeleteRegion(tess, reg);
/*      */     }
/*      */ 
/* 1214 */     Dict.dictDeleteDict(tess.dict);
/*      */   }
/*      */ 
/*      */   static void RemoveDegenerateEdges(GLUtessellatorImpl tess)
/*      */   {
/* 1223 */     GLUhalfEdge eHead = tess.mesh.eHead;
/*      */     GLUhalfEdge eNext;
/* 1226 */     for (GLUhalfEdge e = eHead.next; e != eHead; e = eNext) {
/* 1227 */       eNext = e.next;
/* 1228 */       GLUhalfEdge eLnext = e.Lnext;
/*      */ 
/* 1230 */       if ((Geom.VertEq(e.Org, e.Sym.Org)) && (e.Lnext.Lnext != e))
/*      */       {
/* 1233 */         SpliceMergeVertices(tess, eLnext, e);
/* 1234 */         if (!Mesh.__gl_meshDelete(e)) throw new RuntimeException();
/* 1235 */         e = eLnext;
/* 1236 */         eLnext = e.Lnext;
/*      */       }
/* 1238 */       if (eLnext.Lnext == e)
/*      */       {
/* 1241 */         if (eLnext != e) {
/* 1242 */           if ((eLnext == eNext) || (eLnext == eNext.Sym)) {
/* 1243 */             eNext = eNext.next;
/*      */           }
/* 1245 */           if (!Mesh.__gl_meshDelete(eLnext)) throw new RuntimeException();
/*      */         }
/* 1247 */         if ((e == eNext) || (e == eNext.Sym)) {
/* 1248 */           eNext = eNext.next;
/*      */         }
/* 1250 */         if (!Mesh.__gl_meshDelete(e)) throw new RuntimeException();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   static boolean InitPriorityQ(GLUtessellatorImpl tess)
/*      */   {
/* 1264 */     PriorityQ pq = tess.pq = PriorityQ.pqNewPriorityQ(new PriorityQ.Leq() {
/*      */       public boolean leq(Object key1, Object key2) {
/* 1266 */         return Geom.VertLeq((GLUvertex)key1, (GLUvertex)key2);
/*      */       }
/*      */     });
/* 1269 */     if (pq == null) return false;
/*      */ 
/* 1271 */     GLUvertex vHead = tess.mesh.vHead;
/* 1272 */     for (GLUvertex v = vHead.next; v != vHead; v = v.next) {
/* 1273 */       v.pqHandle = pq.pqInsert(v);
/* 1274 */       if (v.pqHandle == 9223372036854775807L) break;
/*      */     }
/* 1276 */     if ((v != vHead) || (!pq.pqInit())) {
/* 1277 */       tess.pq.pqDeletePriorityQ();
/* 1278 */       tess.pq = null;
/* 1279 */       return false;
/*      */     }
/*      */ 
/* 1282 */     return true;
/*      */   }
/*      */ 
/*      */   static void DonePriorityQ(GLUtessellatorImpl tess)
/*      */   {
/* 1287 */     tess.pq.pqDeletePriorityQ();
/*      */   }
/*      */ 
/*      */   static boolean RemoveDegenerateFaces(GLUmesh mesh)
/*      */   {
/*      */     GLUface fNext;
/* 1310 */     for (GLUface f = mesh.fHead.next; f != mesh.fHead; f = fNext) {
/* 1311 */       fNext = f.next;
/* 1312 */       GLUhalfEdge e = f.anEdge;
/* 1313 */       assert (e.Lnext != e);
/*      */ 
/* 1315 */       if (e.Lnext.Lnext == e)
/*      */       {
/* 1317 */         AddWinding(e.Onext, e);
/* 1318 */         if (!Mesh.__gl_meshDelete(e)) return false;
/*      */       }
/*      */     }
/* 1321 */     return true;
/*      */   }
/*      */ 
/*      */   public static boolean __gl_computeInterior(GLUtessellatorImpl tess)
/*      */   {
/* 1334 */     tess.fatalError = false;
/*      */ 
/* 1342 */     RemoveDegenerateEdges(tess);
/* 1343 */     if (!InitPriorityQ(tess)) return false;
/* 1344 */     InitEdgeDict(tess);
/*      */     GLUvertex v;
/* 1347 */     while ((v = (GLUvertex)tess.pq.pqExtractMin()) != null) {
/*      */       while (true) {
/* 1349 */         GLUvertex vNext = (GLUvertex)tess.pq.pqMinimum();
/* 1350 */         if ((vNext == null) || (!Geom.VertEq(vNext, v)))
/*      */         {
/*      */           break;
/*      */         }
/*      */ 
/* 1366 */         vNext = (GLUvertex)tess.pq.pqExtractMin();
/* 1367 */         SpliceMergeVertices(tess, v.anEdge, vNext.anEdge);
/*      */       }
/* 1369 */       SweepEvent(tess, v);
/*      */     }
/*      */ 
/* 1374 */     tess.event = ((ActiveRegion)Dict.dictKey(Dict.dictMin(tess.dict))).eUp.Org;
/* 1375 */     DebugEvent(tess);
/* 1376 */     DoneEdgeDict(tess);
/* 1377 */     DonePriorityQ(tess);
/*      */ 
/* 1379 */     if (!RemoveDegenerateFaces(tess.mesh)) return false;
/* 1380 */     Mesh.__gl_meshCheckMesh(tess.mesh);
/*      */ 
/* 1382 */     return true;
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.Sweep
 * JD-Core Version:    0.6.2
 */