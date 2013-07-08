/*    1:     */package org.lwjgl.util.glu.tessellation;
/*    2:     */
/*   34:     */class Sweep
/*   35:     */{
/*   36:     */  private static final boolean TOLERANCE_NONZERO = false;
/*   37:     */  
/*   68:     */  private static final double SENTINEL_COORD = 4.0E+150D;
/*   69:     */  
/*  101:     */  private static void DebugEvent(GLUtessellatorImpl tess) {}
/*  102:     */  
/*  133:     */  private static void AddWinding(GLUhalfEdge eDst, GLUhalfEdge eSrc)
/*  134:     */  {
/*  135: 135 */    eDst.winding += eSrc.winding;
/*  136: 136 */    eDst.Sym.winding += eSrc.Sym.winding;
/*  137:     */  }
/*  138:     */  
/*  139:     */  private static ActiveRegion RegionBelow(ActiveRegion r)
/*  140:     */  {
/*  141: 141 */    return (ActiveRegion)Dict.dictKey(Dict.dictPred(r.nodeUp));
/*  142:     */  }
/*  143:     */  
/*  144:     */  private static ActiveRegion RegionAbove(ActiveRegion r) {
/*  145: 145 */    return (ActiveRegion)Dict.dictKey(Dict.dictSucc(r.nodeUp));
/*  146:     */  }
/*  147:     */  
/*  158:     */  static boolean EdgeLeq(GLUtessellatorImpl tess, ActiveRegion reg1, ActiveRegion reg2)
/*  159:     */  {
/*  160: 160 */    GLUvertex event = tess.event;
/*  161:     */    
/*  164: 164 */    GLUhalfEdge e1 = reg1.eUp;
/*  165: 165 */    GLUhalfEdge e2 = reg2.eUp;
/*  166:     */    
/*  167: 167 */    if (e1.Sym.Org == event) {
/*  168: 168 */      if (e2.Sym.Org == event)
/*  169:     */      {
/*  172: 172 */        if (Geom.VertLeq(e1.Org, e2.Org)) {
/*  173: 173 */          return Geom.EdgeSign(e2.Sym.Org, e1.Org, e2.Org) <= 0.0D;
/*  174:     */        }
/*  175: 175 */        return Geom.EdgeSign(e1.Sym.Org, e2.Org, e1.Org) >= 0.0D;
/*  176:     */      }
/*  177: 177 */      return Geom.EdgeSign(e2.Sym.Org, event, e2.Org) <= 0.0D;
/*  178:     */    }
/*  179: 179 */    if (e2.Sym.Org == event) {
/*  180: 180 */      return Geom.EdgeSign(e1.Sym.Org, event, e1.Org) >= 0.0D;
/*  181:     */    }
/*  182:     */    
/*  184: 184 */    double t1 = Geom.EdgeEval(e1.Sym.Org, event, e1.Org);
/*  185: 185 */    double t2 = Geom.EdgeEval(e2.Sym.Org, event, e2.Org);
/*  186: 186 */    return t1 >= t2;
/*  187:     */  }
/*  188:     */  
/*  189:     */  static void DeleteRegion(GLUtessellatorImpl tess, ActiveRegion reg)
/*  190:     */  {
/*  191: 191 */    if (reg.fixUpperEdge)
/*  192:     */    {
/*  196: 196 */      assert (reg.eUp.winding == 0);
/*  197:     */    }
/*  198: 198 */    reg.eUp.activeRegion = null;
/*  199: 199 */    Dict.dictDelete(tess.dict, reg.nodeUp);
/*  200:     */  }
/*  201:     */  
/*  205:     */  static boolean FixUpperEdge(ActiveRegion reg, GLUhalfEdge newEdge)
/*  206:     */  {
/*  207: 207 */    assert (reg.fixUpperEdge);
/*  208: 208 */    if (!Mesh.__gl_meshDelete(reg.eUp)) return false;
/*  209: 209 */    reg.fixUpperEdge = false;
/*  210: 210 */    reg.eUp = newEdge;
/*  211: 211 */    newEdge.activeRegion = reg;
/*  212:     */    
/*  213: 213 */    return true;
/*  214:     */  }
/*  215:     */  
/*  216:     */  static ActiveRegion TopLeftRegion(ActiveRegion reg) {
/*  217: 217 */    GLUvertex org = reg.eUp.Org;
/*  218:     */    
/*  220:     */    do
/*  221:     */    {
/*  222: 222 */      reg = RegionAbove(reg);
/*  223: 223 */    } while (reg.eUp.Org == org);
/*  224:     */    
/*  228: 228 */    if (reg.fixUpperEdge) {
/*  229: 229 */      GLUhalfEdge e = Mesh.__gl_meshConnect(RegionBelow(reg).eUp.Sym, reg.eUp.Lnext);
/*  230: 230 */      if (e == null) return null;
/*  231: 231 */      if (!FixUpperEdge(reg, e)) return null;
/*  232: 232 */      reg = RegionAbove(reg);
/*  233:     */    }
/*  234: 234 */    return reg;
/*  235:     */  }
/*  236:     */  
/*  237:     */  static ActiveRegion TopRightRegion(ActiveRegion reg) {
/*  238: 238 */    GLUvertex dst = reg.eUp.Sym.Org;
/*  239:     */    
/*  240:     */    do
/*  241:     */    {
/*  242: 242 */      reg = RegionAbove(reg);
/*  243: 243 */    } while (reg.eUp.Sym.Org == dst);
/*  244: 244 */    return reg;
/*  245:     */  }
/*  246:     */  
/*  254:     */  static ActiveRegion AddRegionBelow(GLUtessellatorImpl tess, ActiveRegion regAbove, GLUhalfEdge eNewUp)
/*  255:     */  {
/*  256: 256 */    ActiveRegion regNew = new ActiveRegion();
/*  257: 257 */    if (regNew == null) { throw new RuntimeException();
/*  258:     */    }
/*  259: 259 */    regNew.eUp = eNewUp;
/*  260:     */    
/*  261: 261 */    regNew.nodeUp = Dict.dictInsertBefore(tess.dict, regAbove.nodeUp, regNew);
/*  262: 262 */    if (regNew.nodeUp == null) throw new RuntimeException();
/*  263: 263 */    regNew.fixUpperEdge = false;
/*  264: 264 */    regNew.sentinel = false;
/*  265: 265 */    regNew.dirty = false;
/*  266:     */    
/*  267: 267 */    eNewUp.activeRegion = regNew;
/*  268: 268 */    return regNew;
/*  269:     */  }
/*  270:     */  
/*  271:     */  static boolean IsWindingInside(GLUtessellatorImpl tess, int n) {
/*  272: 272 */    switch (tess.windingRule) {
/*  273:     */    case 100130: 
/*  274: 274 */      return (n & 0x1) != 0;
/*  275:     */    case 100131: 
/*  276: 276 */      return n != 0;
/*  277:     */    case 100132: 
/*  278: 278 */      return n > 0;
/*  279:     */    case 100133: 
/*  280: 280 */      return n < 0;
/*  281:     */    case 100134: 
/*  282: 282 */      return (n >= 2) || (n <= -2);
/*  283:     */    }
/*  284:     */    
/*  285:     */    
/*  286: 286 */    throw new InternalError();
/*  287:     */  }
/*  288:     */  
/*  290:     */  static void ComputeWinding(GLUtessellatorImpl tess, ActiveRegion reg)
/*  291:     */  {
/*  292: 292 */    reg.windingNumber = (RegionAbove(reg).windingNumber + reg.eUp.winding);
/*  293: 293 */    reg.inside = IsWindingInside(tess, reg.windingNumber);
/*  294:     */  }
/*  295:     */  
/*  303:     */  static void FinishRegion(GLUtessellatorImpl tess, ActiveRegion reg)
/*  304:     */  {
/*  305: 305 */    GLUhalfEdge e = reg.eUp;
/*  306: 306 */    GLUface f = e.Lface;
/*  307:     */    
/*  308: 308 */    f.inside = reg.inside;
/*  309: 309 */    f.anEdge = e;
/*  310: 310 */    DeleteRegion(tess, reg);
/*  311:     */  }
/*  312:     */  
/*  329:     */  static GLUhalfEdge FinishLeftRegions(GLUtessellatorImpl tess, ActiveRegion regFirst, ActiveRegion regLast)
/*  330:     */  {
/*  331: 331 */    ActiveRegion regPrev = regFirst;
/*  332: 332 */    GLUhalfEdge ePrev = regFirst.eUp;
/*  333: 333 */    while (regPrev != regLast) {
/*  334: 334 */      regPrev.fixUpperEdge = false;
/*  335: 335 */      ActiveRegion reg = RegionBelow(regPrev);
/*  336: 336 */      GLUhalfEdge e = reg.eUp;
/*  337: 337 */      if (e.Org != ePrev.Org) {
/*  338: 338 */        if (!reg.fixUpperEdge)
/*  339:     */        {
/*  345: 345 */          FinishRegion(tess, regPrev);
/*  346: 346 */          break;
/*  347:     */        }
/*  348:     */        
/*  351: 351 */        e = Mesh.__gl_meshConnect(ePrev.Onext.Sym, e.Sym);
/*  352: 352 */        if (e == null) throw new RuntimeException();
/*  353: 353 */        if (!FixUpperEdge(reg, e)) { throw new RuntimeException();
/*  354:     */        }
/*  355:     */      }
/*  356:     */      
/*  357: 357 */      if (ePrev.Onext != e) {
/*  358: 358 */        if (!Mesh.__gl_meshSplice(e.Sym.Lnext, e)) throw new RuntimeException();
/*  359: 359 */        if (!Mesh.__gl_meshSplice(ePrev, e)) throw new RuntimeException();
/*  360:     */      }
/*  361: 361 */      FinishRegion(tess, regPrev);
/*  362: 362 */      ePrev = reg.eUp;
/*  363: 363 */      regPrev = reg;
/*  364:     */    }
/*  365: 365 */    return ePrev;
/*  366:     */  }
/*  367:     */  
/*  382:     */  static void AddRightEdges(GLUtessellatorImpl tess, ActiveRegion regUp, GLUhalfEdge eFirst, GLUhalfEdge eLast, GLUhalfEdge eTopLeft, boolean cleanUp)
/*  383:     */  {
/*  384: 384 */    boolean firstTime = true;
/*  385:     */    
/*  387: 387 */    GLUhalfEdge e = eFirst;
/*  388:     */    do {
/*  389: 389 */      assert (Geom.VertLeq(e.Org, e.Sym.Org));
/*  390: 390 */      AddRegionBelow(tess, regUp, e.Sym);
/*  391: 391 */      e = e.Onext;
/*  392: 392 */    } while (e != eLast);
/*  393:     */    
/*  398: 398 */    if (eTopLeft == null) {
/*  399: 399 */      eTopLeft = RegionBelow(regUp).eUp.Sym.Onext;
/*  400:     */    }
/*  401: 401 */    ActiveRegion regPrev = regUp;
/*  402: 402 */    GLUhalfEdge ePrev = eTopLeft;
/*  403:     */    ActiveRegion reg;
/*  404: 404 */    for (;;) { reg = RegionBelow(regPrev);
/*  405: 405 */      e = reg.eUp.Sym;
/*  406: 406 */      if (e.Org != ePrev.Org)
/*  407:     */        break;
/*  408: 408 */      if (e.Onext != ePrev)
/*  409:     */      {
/*  410: 410 */        if (!Mesh.__gl_meshSplice(e.Sym.Lnext, e)) throw new RuntimeException();
/*  411: 411 */        if (!Mesh.__gl_meshSplice(ePrev.Sym.Lnext, e)) { throw new RuntimeException();
/*  412:     */        }
/*  413:     */      }
/*  414: 414 */      regPrev.windingNumber -= e.winding;
/*  415: 415 */      reg.inside = IsWindingInside(tess, reg.windingNumber);
/*  416:     */      
/*  420: 420 */      regPrev.dirty = true;
/*  421: 421 */      if ((!firstTime) && (CheckForRightSplice(tess, regPrev))) {
/*  422: 422 */        AddWinding(e, ePrev);
/*  423: 423 */        DeleteRegion(tess, regPrev);
/*  424: 424 */        if (!Mesh.__gl_meshDelete(ePrev)) throw new RuntimeException();
/*  425:     */      }
/*  426: 426 */      firstTime = false;
/*  427: 427 */      regPrev = reg;
/*  428: 428 */      ePrev = e;
/*  429:     */    }
/*  430: 430 */    regPrev.dirty = true;
/*  431: 431 */    assert (regPrev.windingNumber - e.winding == reg.windingNumber);
/*  432:     */    
/*  433: 433 */    if (cleanUp)
/*  434:     */    {
/*  435: 435 */      WalkDirtyRegions(tess, regPrev);
/*  436:     */    }
/*  437:     */  }
/*  438:     */  
/*  440:     */  static void CallCombine(GLUtessellatorImpl tess, GLUvertex isect, Object[] data, float[] weights, boolean needed)
/*  441:     */  {
/*  442: 442 */    double[] coords = new double[3];
/*  443:     */    
/*  445: 445 */    coords[0] = isect.coords[0];
/*  446: 446 */    coords[1] = isect.coords[1];
/*  447: 447 */    coords[2] = isect.coords[2];
/*  448:     */    
/*  449: 449 */    Object[] outData = new Object[1];
/*  450: 450 */    tess.callCombineOrCombineData(coords, data, weights, outData);
/*  451: 451 */    isect.data = outData[0];
/*  452: 452 */    if (isect.data == null) {
/*  453: 453 */      if (!needed) {
/*  454: 454 */        isect.data = data[0];
/*  455: 455 */      } else if (!tess.fatalError)
/*  456:     */      {
/*  460: 460 */        tess.callErrorOrErrorData(100156);
/*  461: 461 */        tess.fatalError = true;
/*  462:     */      }
/*  463:     */    }
/*  464:     */  }
/*  465:     */  
/*  470:     */  static void SpliceMergeVertices(GLUtessellatorImpl tess, GLUhalfEdge e1, GLUhalfEdge e2)
/*  471:     */  {
/*  472: 472 */    Object[] data = new Object[4];
/*  473: 473 */    float[] weights = { 0.5F, 0.5F, 0.0F, 0.0F };
/*  474:     */    
/*  475: 475 */    data[0] = e1.Org.data;
/*  476: 476 */    data[1] = e2.Org.data;
/*  477: 477 */    CallCombine(tess, e1.Org, data, weights, false);
/*  478: 478 */    if (!Mesh.__gl_meshSplice(e1, e2)) { throw new RuntimeException();
/*  479:     */    }
/*  480:     */  }
/*  481:     */  
/*  488:     */  static void VertexWeights(GLUvertex isect, GLUvertex org, GLUvertex dst, float[] weights)
/*  489:     */  {
/*  490: 490 */    double t1 = Geom.VertL1dist(org, isect);
/*  491: 491 */    double t2 = Geom.VertL1dist(dst, isect);
/*  492:     */    
/*  493: 493 */    weights[0] = ((float)(0.5D * t2 / (t1 + t2)));
/*  494: 494 */    weights[1] = ((float)(0.5D * t1 / (t1 + t2)));
/*  495: 495 */    isect.coords[0] += weights[0] * org.coords[0] + weights[1] * dst.coords[0];
/*  496: 496 */    isect.coords[1] += weights[0] * org.coords[1] + weights[1] * dst.coords[1];
/*  497: 497 */    isect.coords[2] += weights[0] * org.coords[2] + weights[1] * dst.coords[2];
/*  498:     */  }
/*  499:     */  
/*  507:     */  static void GetIntersectData(GLUtessellatorImpl tess, GLUvertex isect, GLUvertex orgUp, GLUvertex dstUp, GLUvertex orgLo, GLUvertex dstLo)
/*  508:     */  {
/*  509: 509 */    Object[] data = new Object[4];
/*  510: 510 */    float[] weights = new float[4];
/*  511: 511 */    float[] weights1 = new float[2];
/*  512: 512 */    float[] weights2 = new float[2];
/*  513:     */    
/*  514: 514 */    data[0] = orgUp.data;
/*  515: 515 */    data[1] = dstUp.data;
/*  516: 516 */    data[2] = orgLo.data;
/*  517: 517 */    data[3] = dstLo.data; double 
/*  518:     */    
/*  519: 519 */      tmp73_72 = (isect.coords[2] = 0.0D);isect.coords[1] = tmp73_72;isect.coords[0] = tmp73_72;
/*  520: 520 */    VertexWeights(isect, orgUp, dstUp, weights1);
/*  521: 521 */    VertexWeights(isect, orgLo, dstLo, weights2);
/*  522: 522 */    System.arraycopy(weights1, 0, weights, 0, 2);
/*  523: 523 */    System.arraycopy(weights2, 0, weights, 2, 2);
/*  524:     */    
/*  525: 525 */    CallCombine(tess, isect, data, weights, true);
/*  526:     */  }
/*  527:     */  
/*  552:     */  static boolean CheckForRightSplice(GLUtessellatorImpl tess, ActiveRegion regUp)
/*  553:     */  {
/*  554: 554 */    ActiveRegion regLo = RegionBelow(regUp);
/*  555: 555 */    GLUhalfEdge eUp = regUp.eUp;
/*  556: 556 */    GLUhalfEdge eLo = regLo.eUp;
/*  557:     */    
/*  558: 558 */    if (Geom.VertLeq(eUp.Org, eLo.Org)) {
/*  559: 559 */      if (Geom.EdgeSign(eLo.Sym.Org, eUp.Org, eLo.Org) > 0.0D) { return false;
/*  560:     */      }
/*  561:     */      
/*  562: 562 */      if (!Geom.VertEq(eUp.Org, eLo.Org))
/*  563:     */      {
/*  564: 564 */        if (Mesh.__gl_meshSplitEdge(eLo.Sym) == null) throw new RuntimeException();
/*  565: 565 */        if (!Mesh.__gl_meshSplice(eUp, eLo.Sym.Lnext)) throw new RuntimeException();
/*  566: 566 */        regUp.dirty = (regLo.dirty = 1);
/*  567:     */      }
/*  568: 568 */      else if (eUp.Org != eLo.Org)
/*  569:     */      {
/*  570: 570 */        tess.pq.pqDelete(eUp.Org.pqHandle);
/*  571: 571 */        SpliceMergeVertices(tess, eLo.Sym.Lnext, eUp);
/*  572:     */      }
/*  573:     */    } else {
/*  574: 574 */      if (Geom.EdgeSign(eUp.Sym.Org, eLo.Org, eUp.Org) < 0.0D) { return false;
/*  575:     */      }
/*  576:     */      
/*  577: 577 */      RegionAbove(regUp).dirty = (regUp.dirty = 1);
/*  578: 578 */      if (Mesh.__gl_meshSplitEdge(eUp.Sym) == null) throw new RuntimeException();
/*  579: 579 */      if (!Mesh.__gl_meshSplice(eLo.Sym.Lnext, eUp)) throw new RuntimeException();
/*  580:     */    }
/*  581: 581 */    return true;
/*  582:     */  }
/*  583:     */  
/*  601:     */  static boolean CheckForLeftSplice(GLUtessellatorImpl tess, ActiveRegion regUp)
/*  602:     */  {
/*  603: 603 */    ActiveRegion regLo = RegionBelow(regUp);
/*  604: 604 */    GLUhalfEdge eUp = regUp.eUp;
/*  605: 605 */    GLUhalfEdge eLo = regLo.eUp;
/*  606:     */    
/*  608: 608 */    assert (!Geom.VertEq(eUp.Sym.Org, eLo.Sym.Org));
/*  609:     */    
/*  610: 610 */    if (Geom.VertLeq(eUp.Sym.Org, eLo.Sym.Org)) {
/*  611: 611 */      if (Geom.EdgeSign(eUp.Sym.Org, eLo.Sym.Org, eUp.Org) < 0.0D) { return false;
/*  612:     */      }
/*  613:     */      
/*  614: 614 */      RegionAbove(regUp).dirty = (regUp.dirty = 1);
/*  615: 615 */      GLUhalfEdge e = Mesh.__gl_meshSplitEdge(eUp);
/*  616: 616 */      if (e == null) throw new RuntimeException();
/*  617: 617 */      if (!Mesh.__gl_meshSplice(eLo.Sym, e)) throw new RuntimeException();
/*  618: 618 */      e.Lface.inside = regUp.inside;
/*  619:     */    } else {
/*  620: 620 */      if (Geom.EdgeSign(eLo.Sym.Org, eUp.Sym.Org, eLo.Org) > 0.0D) { return false;
/*  621:     */      }
/*  622:     */      
/*  623: 623 */      regUp.dirty = (regLo.dirty = 1);
/*  624: 624 */      GLUhalfEdge e = Mesh.__gl_meshSplitEdge(eLo);
/*  625: 625 */      if (e == null) throw new RuntimeException();
/*  626: 626 */      if (!Mesh.__gl_meshSplice(eUp.Lnext, eLo.Sym)) throw new RuntimeException();
/*  627: 627 */      e.Sym.Lface.inside = regUp.inside;
/*  628:     */    }
/*  629: 629 */    return true;
/*  630:     */  }
/*  631:     */  
/*  641:     */  static boolean CheckForIntersect(GLUtessellatorImpl tess, ActiveRegion regUp)
/*  642:     */  {
/*  643: 643 */    ActiveRegion regLo = RegionBelow(regUp);
/*  644: 644 */    GLUhalfEdge eUp = regUp.eUp;
/*  645: 645 */    GLUhalfEdge eLo = regLo.eUp;
/*  646: 646 */    GLUvertex orgUp = eUp.Org;
/*  647: 647 */    GLUvertex orgLo = eLo.Org;
/*  648: 648 */    GLUvertex dstUp = eUp.Sym.Org;
/*  649: 649 */    GLUvertex dstLo = eLo.Sym.Org;
/*  650:     */    
/*  651: 651 */    GLUvertex isect = new GLUvertex();
/*  652:     */    
/*  655: 655 */    assert (!Geom.VertEq(dstLo, dstUp));
/*  656: 656 */    assert (Geom.EdgeSign(dstUp, tess.event, orgUp) <= 0.0D);
/*  657: 657 */    assert (Geom.EdgeSign(dstLo, tess.event, orgLo) >= 0.0D);
/*  658: 658 */    assert ((orgUp != tess.event) && (orgLo != tess.event));
/*  659: 659 */    assert ((!regUp.fixUpperEdge) && (!regLo.fixUpperEdge));
/*  660:     */    
/*  661: 661 */    if (orgUp == orgLo) { return false;
/*  662:     */    }
/*  663: 663 */    double tMinUp = Math.min(orgUp.t, dstUp.t);
/*  664: 664 */    double tMaxLo = Math.max(orgLo.t, dstLo.t);
/*  665: 665 */    if (tMinUp > tMaxLo) { return false;
/*  666:     */    }
/*  667: 667 */    if (Geom.VertLeq(orgUp, orgLo)) {
/*  668: 668 */      if (Geom.EdgeSign(dstLo, orgUp, orgLo) > 0.0D) return false;
/*  669:     */    }
/*  670: 670 */    else if (Geom.EdgeSign(dstUp, orgLo, orgUp) < 0.0D) { return false;
/*  671:     */    }
/*  672:     */    
/*  674: 674 */    DebugEvent(tess);
/*  675:     */    
/*  676: 676 */    Geom.EdgeIntersect(dstUp, orgUp, dstLo, orgLo, isect);
/*  677:     */    
/*  678: 678 */    assert (Math.min(orgUp.t, dstUp.t) <= isect.t);
/*  679: 679 */    assert (isect.t <= Math.max(orgLo.t, dstLo.t));
/*  680: 680 */    assert (Math.min(dstLo.s, dstUp.s) <= isect.s);
/*  681: 681 */    assert (isect.s <= Math.max(orgLo.s, orgUp.s));
/*  682:     */    
/*  683: 683 */    if (Geom.VertLeq(isect, tess.event))
/*  684:     */    {
/*  690: 690 */      isect.s = tess.event.s;
/*  691: 691 */      isect.t = tess.event.t;
/*  692:     */    }
/*  693:     */    
/*  699: 699 */    GLUvertex orgMin = Geom.VertLeq(orgUp, orgLo) ? orgUp : orgLo;
/*  700: 700 */    if (Geom.VertLeq(orgMin, isect)) {
/*  701: 701 */      isect.s = orgMin.s;
/*  702: 702 */      isect.t = orgMin.t;
/*  703:     */    }
/*  704:     */    
/*  705: 705 */    if ((Geom.VertEq(isect, orgUp)) || (Geom.VertEq(isect, orgLo)))
/*  706:     */    {
/*  707: 707 */      CheckForRightSplice(tess, regUp);
/*  708: 708 */      return false;
/*  709:     */    }
/*  710:     */    
/*  711: 711 */    if (((!Geom.VertEq(dstUp, tess.event)) && (Geom.EdgeSign(dstUp, tess.event, isect) >= 0.0D)) || ((!Geom.VertEq(dstLo, tess.event)) && (Geom.EdgeSign(dstLo, tess.event, isect) <= 0.0D)))
/*  712:     */    {
/*  719: 719 */      if (dstLo == tess.event)
/*  720:     */      {
/*  721: 721 */        if (Mesh.__gl_meshSplitEdge(eUp.Sym) == null) throw new RuntimeException();
/*  722: 722 */        if (!Mesh.__gl_meshSplice(eLo.Sym, eUp)) throw new RuntimeException();
/*  723: 723 */        regUp = TopLeftRegion(regUp);
/*  724: 724 */        if (regUp == null) throw new RuntimeException();
/*  725: 725 */        eUp = RegionBelow(regUp).eUp;
/*  726: 726 */        FinishLeftRegions(tess, RegionBelow(regUp), regLo);
/*  727: 727 */        AddRightEdges(tess, regUp, eUp.Sym.Lnext, eUp, eUp, true);
/*  728: 728 */        return true;
/*  729:     */      }
/*  730: 730 */      if (dstUp == tess.event)
/*  731:     */      {
/*  732: 732 */        if (Mesh.__gl_meshSplitEdge(eLo.Sym) == null) throw new RuntimeException();
/*  733: 733 */        if (!Mesh.__gl_meshSplice(eUp.Lnext, eLo.Sym.Lnext)) throw new RuntimeException();
/*  734: 734 */        regLo = regUp;
/*  735: 735 */        regUp = TopRightRegion(regUp);
/*  736: 736 */        GLUhalfEdge e = RegionBelow(regUp).eUp.Sym.Onext;
/*  737: 737 */        regLo.eUp = eLo.Sym.Lnext;
/*  738: 738 */        eLo = FinishLeftRegions(tess, regLo, null);
/*  739: 739 */        AddRightEdges(tess, regUp, eLo.Onext, eUp.Sym.Onext, e, true);
/*  740: 740 */        return true;
/*  741:     */      }
/*  742:     */      
/*  746: 746 */      if (Geom.EdgeSign(dstUp, tess.event, isect) >= 0.0D) {
/*  747: 747 */        RegionAbove(regUp).dirty = (regUp.dirty = 1);
/*  748: 748 */        if (Mesh.__gl_meshSplitEdge(eUp.Sym) == null) throw new RuntimeException();
/*  749: 749 */        eUp.Org.s = tess.event.s;
/*  750: 750 */        eUp.Org.t = tess.event.t;
/*  751:     */      }
/*  752: 752 */      if (Geom.EdgeSign(dstLo, tess.event, isect) <= 0.0D) {
/*  753: 753 */        regUp.dirty = (regLo.dirty = 1);
/*  754: 754 */        if (Mesh.__gl_meshSplitEdge(eLo.Sym) == null) throw new RuntimeException();
/*  755: 755 */        eLo.Org.s = tess.event.s;
/*  756: 756 */        eLo.Org.t = tess.event.t;
/*  757:     */      }
/*  758:     */      
/*  759: 759 */      return false;
/*  760:     */    }
/*  761:     */    
/*  770: 770 */    if (Mesh.__gl_meshSplitEdge(eUp.Sym) == null) throw new RuntimeException();
/*  771: 771 */    if (Mesh.__gl_meshSplitEdge(eLo.Sym) == null) throw new RuntimeException();
/*  772: 772 */    if (!Mesh.__gl_meshSplice(eLo.Sym.Lnext, eUp)) throw new RuntimeException();
/*  773: 773 */    eUp.Org.s = isect.s;
/*  774: 774 */    eUp.Org.t = isect.t;
/*  775: 775 */    eUp.Org.pqHandle = tess.pq.pqInsert(eUp.Org);
/*  776: 776 */    if (eUp.Org.pqHandle == 9223372036854775807L) {
/*  777: 777 */      tess.pq.pqDeletePriorityQ();
/*  778: 778 */      tess.pq = null;
/*  779: 779 */      throw new RuntimeException();
/*  780:     */    }
/*  781: 781 */    GetIntersectData(tess, eUp.Org, orgUp, dstUp, orgLo, dstLo);
/*  782: 782 */    RegionAbove(regUp).dirty = (regUp.dirty = regLo.dirty = 1);
/*  783: 783 */    return false;
/*  784:     */  }
/*  785:     */  
/*  793:     */  static void WalkDirtyRegions(GLUtessellatorImpl tess, ActiveRegion regUp)
/*  794:     */  {
/*  795: 795 */    ActiveRegion regLo = RegionBelow(regUp);
/*  796:     */    
/*  798:     */    for (;;)
/*  799:     */    {
/*  800: 800 */      if (regLo.dirty) {
/*  801: 801 */        regUp = regLo;
/*  802: 802 */        regLo = RegionBelow(regLo);
/*  803:     */      } else {
/*  804: 804 */        if (!regUp.dirty) {
/*  805: 805 */          regLo = regUp;
/*  806: 806 */          regUp = RegionAbove(regUp);
/*  807: 807 */          if ((regUp == null) || (!regUp.dirty))
/*  808:     */          {
/*  809: 809 */            return;
/*  810:     */          }
/*  811:     */        }
/*  812: 812 */        regUp.dirty = false;
/*  813: 813 */        GLUhalfEdge eUp = regUp.eUp;
/*  814: 814 */        GLUhalfEdge eLo = regLo.eUp;
/*  815:     */        
/*  816: 816 */        if (eUp.Sym.Org != eLo.Sym.Org)
/*  817:     */        {
/*  818: 818 */          if (CheckForLeftSplice(tess, regUp))
/*  819:     */          {
/*  824: 824 */            if (regLo.fixUpperEdge) {
/*  825: 825 */              DeleteRegion(tess, regLo);
/*  826: 826 */              if (!Mesh.__gl_meshDelete(eLo)) throw new RuntimeException();
/*  827: 827 */              regLo = RegionBelow(regUp);
/*  828: 828 */              eLo = regLo.eUp;
/*  829: 829 */            } else if (regUp.fixUpperEdge) {
/*  830: 830 */              DeleteRegion(tess, regUp);
/*  831: 831 */              if (!Mesh.__gl_meshDelete(eUp)) throw new RuntimeException();
/*  832: 832 */              regUp = RegionAbove(regLo);
/*  833: 833 */              eUp = regUp.eUp;
/*  834:     */            }
/*  835:     */          }
/*  836:     */        }
/*  837: 837 */        if (eUp.Org != eLo.Org) {
/*  838: 838 */          if ((eUp.Sym.Org != eLo.Sym.Org) && (!regUp.fixUpperEdge) && (!regLo.fixUpperEdge) && ((eUp.Sym.Org == tess.event) || (eLo.Sym.Org == tess.event)))
/*  839:     */          {
/*  849: 849 */            if (!CheckForIntersect(tess, regUp)) {}
/*  853:     */          }
/*  854:     */          else
/*  855:     */          {
/*  857: 857 */            CheckForRightSplice(tess, regUp);
/*  858:     */          }
/*  859:     */        }
/*  860: 860 */        if ((eUp.Org == eLo.Org) && (eUp.Sym.Org == eLo.Sym.Org))
/*  861:     */        {
/*  862: 862 */          AddWinding(eLo, eUp);
/*  863: 863 */          DeleteRegion(tess, regUp);
/*  864: 864 */          if (!Mesh.__gl_meshDelete(eUp)) throw new RuntimeException();
/*  865: 865 */          regUp = RegionAbove(regLo);
/*  866:     */        }
/*  867:     */      }
/*  868:     */    }
/*  869:     */  }
/*  870:     */  
/*  903:     */  static void ConnectRightVertex(GLUtessellatorImpl tess, ActiveRegion regUp, GLUhalfEdge eBottomLeft)
/*  904:     */  {
/*  905: 905 */    GLUhalfEdge eTopLeft = eBottomLeft.Onext;
/*  906: 906 */    ActiveRegion regLo = RegionBelow(regUp);
/*  907: 907 */    GLUhalfEdge eUp = regUp.eUp;
/*  908: 908 */    GLUhalfEdge eLo = regLo.eUp;
/*  909: 909 */    boolean degenerate = false;
/*  910:     */    
/*  911: 911 */    if (eUp.Sym.Org != eLo.Sym.Org) {
/*  912: 912 */      CheckForIntersect(tess, regUp);
/*  913:     */    }
/*  914:     */    
/*  918: 918 */    if (Geom.VertEq(eUp.Org, tess.event)) {
/*  919: 919 */      if (!Mesh.__gl_meshSplice(eTopLeft.Sym.Lnext, eUp)) throw new RuntimeException();
/*  920: 920 */      regUp = TopLeftRegion(regUp);
/*  921: 921 */      if (regUp == null) throw new RuntimeException();
/*  922: 922 */      eTopLeft = RegionBelow(regUp).eUp;
/*  923: 923 */      FinishLeftRegions(tess, RegionBelow(regUp), regLo);
/*  924: 924 */      degenerate = true;
/*  925:     */    }
/*  926: 926 */    if (Geom.VertEq(eLo.Org, tess.event)) {
/*  927: 927 */      if (!Mesh.__gl_meshSplice(eBottomLeft, eLo.Sym.Lnext)) throw new RuntimeException();
/*  928: 928 */      eBottomLeft = FinishLeftRegions(tess, regLo, null);
/*  929: 929 */      degenerate = true;
/*  930:     */    }
/*  931: 931 */    if (degenerate) {
/*  932: 932 */      AddRightEdges(tess, regUp, eBottomLeft.Onext, eTopLeft, eTopLeft, true); return;
/*  933:     */    }
/*  934:     */    
/*  936:     */    GLUhalfEdge eNew;
/*  937:     */    
/*  939: 939 */    if (Geom.VertLeq(eLo.Org, eUp.Org)) {
/*  940: 940 */      eNew = eLo.Sym.Lnext;
/*  941:     */    } else {
/*  942: 942 */      eNew = eUp;
/*  943:     */    }
/*  944: 944 */    GLUhalfEdge eNew = Mesh.__gl_meshConnect(eBottomLeft.Onext.Sym, eNew);
/*  945: 945 */    if (eNew == null) { throw new RuntimeException();
/*  946:     */    }
/*  947:     */    
/*  950: 950 */    AddRightEdges(tess, regUp, eNew, eNew.Onext, eNew.Onext, false);
/*  951: 951 */    eNew.Sym.activeRegion.fixUpperEdge = true;
/*  952: 952 */    WalkDirtyRegions(tess, regUp);
/*  953:     */  }
/*  954:     */  
/*  972:     */  static void ConnectLeftDegenerate(GLUtessellatorImpl tess, ActiveRegion regUp, GLUvertex vEvent)
/*  973:     */  {
/*  974: 974 */    GLUhalfEdge e = regUp.eUp;
/*  975: 975 */    if (Geom.VertEq(e.Org, vEvent))
/*  976:     */    {
/*  979: 979 */      if (!$assertionsDisabled) throw new AssertionError();
/*  980: 980 */      SpliceMergeVertices(tess, e, vEvent.anEdge);
/*  981: 981 */      return;
/*  982:     */    }
/*  983:     */    
/*  984: 984 */    if (!Geom.VertEq(e.Sym.Org, vEvent))
/*  985:     */    {
/*  986: 986 */      if (Mesh.__gl_meshSplitEdge(e.Sym) == null) throw new RuntimeException();
/*  987: 987 */      if (regUp.fixUpperEdge)
/*  988:     */      {
/*  989: 989 */        if (!Mesh.__gl_meshDelete(e.Onext)) throw new RuntimeException();
/*  990: 990 */        regUp.fixUpperEdge = false;
/*  991:     */      }
/*  992: 992 */      if (!Mesh.__gl_meshSplice(vEvent.anEdge, e)) throw new RuntimeException();
/*  993: 993 */      SweepEvent(tess, vEvent);
/*  994: 994 */      return;
/*  995:     */    }
/*  996:     */    
/* 1000:1000 */    if (!$assertionsDisabled) throw new AssertionError();
/* 1001:1001 */    regUp = TopRightRegion(regUp);
/* 1002:1002 */    ActiveRegion reg = RegionBelow(regUp);
/* 1003:1003 */    GLUhalfEdge eTopRight = reg.eUp.Sym;
/* 1004:1004 */    GLUhalfEdge eLast; GLUhalfEdge eTopLeft = eLast = eTopRight.Onext;
/* 1005:1005 */    if (reg.fixUpperEdge)
/* 1006:     */    {
/* 1009:1009 */      assert (eTopLeft != eTopRight);
/* 1010:1010 */      DeleteRegion(tess, reg);
/* 1011:1011 */      if (!Mesh.__gl_meshDelete(eTopRight)) throw new RuntimeException();
/* 1012:1012 */      eTopRight = eTopLeft.Sym.Lnext;
/* 1013:     */    }
/* 1014:1014 */    if (!Mesh.__gl_meshSplice(vEvent.anEdge, eTopRight)) throw new RuntimeException();
/* 1015:1015 */    if (!Geom.EdgeGoesLeft(eTopLeft))
/* 1016:     */    {
/* 1017:1017 */      eTopLeft = null;
/* 1018:     */    }
/* 1019:1019 */    AddRightEdges(tess, regUp, eTopRight.Onext, eLast, eTopLeft, true);
/* 1020:     */  }
/* 1021:     */  
/* 1039:     */  static void ConnectLeftVertex(GLUtessellatorImpl tess, GLUvertex vEvent)
/* 1040:     */  {
/* 1041:1041 */    ActiveRegion tmp = new ActiveRegion();
/* 1042:     */    
/* 1046:1046 */    tmp.eUp = vEvent.anEdge.Sym;
/* 1047:     */    
/* 1048:1048 */    ActiveRegion regUp = (ActiveRegion)Dict.dictKey(Dict.dictSearch(tess.dict, tmp));
/* 1049:1049 */    ActiveRegion regLo = RegionBelow(regUp);
/* 1050:1050 */    GLUhalfEdge eUp = regUp.eUp;
/* 1051:1051 */    GLUhalfEdge eLo = regLo.eUp;
/* 1052:     */    
/* 1054:1054 */    if (Geom.EdgeSign(eUp.Sym.Org, vEvent, eUp.Org) == 0.0D) {
/* 1055:1055 */      ConnectLeftDegenerate(tess, regUp, vEvent);
/* 1056:1056 */      return;
/* 1057:     */    }
/* 1058:     */    
/* 1062:1062 */    ActiveRegion reg = Geom.VertLeq(eLo.Sym.Org, eUp.Sym.Org) ? regUp : regLo;
/* 1063:     */    
/* 1064:1064 */    if ((regUp.inside) || (reg.fixUpperEdge)) { GLUhalfEdge eNew;
/* 1065:1065 */      if (reg == regUp) {
/* 1066:1066 */        GLUhalfEdge eNew = Mesh.__gl_meshConnect(vEvent.anEdge.Sym, eUp.Lnext);
/* 1067:1067 */        if (eNew == null) throw new RuntimeException();
/* 1068:     */      } else {
/* 1069:1069 */        GLUhalfEdge tempHalfEdge = Mesh.__gl_meshConnect(eLo.Sym.Onext.Sym, vEvent.anEdge);
/* 1070:1070 */        if (tempHalfEdge == null) { throw new RuntimeException();
/* 1071:     */        }
/* 1072:1072 */        eNew = tempHalfEdge.Sym;
/* 1073:     */      }
/* 1074:1074 */      if (reg.fixUpperEdge) {
/* 1075:1075 */        if (!FixUpperEdge(reg, eNew)) throw new RuntimeException();
/* 1076:     */      } else {
/* 1077:1077 */        ComputeWinding(tess, AddRegionBelow(tess, regUp, eNew));
/* 1078:     */      }
/* 1079:1079 */      SweepEvent(tess, vEvent);
/* 1081:     */    }
/* 1082:     */    else
/* 1083:     */    {
/* 1084:1084 */      AddRightEdges(tess, regUp, vEvent.anEdge, vEvent.anEdge, null, true);
/* 1085:     */    }
/* 1086:     */  }
/* 1087:     */  
/* 1095:     */  static void SweepEvent(GLUtessellatorImpl tess, GLUvertex vEvent)
/* 1096:     */  {
/* 1097:1097 */    tess.event = vEvent;
/* 1098:1098 */    DebugEvent(tess);
/* 1099:     */    
/* 1104:1104 */    GLUhalfEdge e = vEvent.anEdge;
/* 1105:1105 */    while (e.activeRegion == null) {
/* 1106:1106 */      e = e.Onext;
/* 1107:1107 */      if (e == vEvent.anEdge)
/* 1108:     */      {
/* 1109:1109 */        ConnectLeftVertex(tess, vEvent);
/* 1110:1110 */        return;
/* 1111:     */      }
/* 1112:     */    }
/* 1113:     */    
/* 1121:1121 */    ActiveRegion regUp = TopLeftRegion(e.activeRegion);
/* 1122:1122 */    if (regUp == null) throw new RuntimeException();
/* 1123:1123 */    ActiveRegion reg = RegionBelow(regUp);
/* 1124:1124 */    GLUhalfEdge eTopLeft = reg.eUp;
/* 1125:1125 */    GLUhalfEdge eBottomLeft = FinishLeftRegions(tess, reg, null);
/* 1126:     */    
/* 1132:1132 */    if (eBottomLeft.Onext == eTopLeft)
/* 1133:     */    {
/* 1134:1134 */      ConnectRightVertex(tess, regUp, eBottomLeft);
/* 1135:     */    } else {
/* 1136:1136 */      AddRightEdges(tess, regUp, eBottomLeft.Onext, eTopLeft, eTopLeft, true);
/* 1137:     */    }
/* 1138:     */  }
/* 1139:     */  
/* 1152:     */  static void AddSentinel(GLUtessellatorImpl tess, double t)
/* 1153:     */  {
/* 1154:1154 */    ActiveRegion reg = new ActiveRegion();
/* 1155:1155 */    if (reg == null) { throw new RuntimeException();
/* 1156:     */    }
/* 1157:1157 */    GLUhalfEdge e = Mesh.__gl_meshMakeEdge(tess.mesh);
/* 1158:1158 */    if (e == null) { throw new RuntimeException();
/* 1159:     */    }
/* 1160:1160 */    e.Org.s = 4.0E+150D;
/* 1161:1161 */    e.Org.t = t;
/* 1162:1162 */    e.Sym.Org.s = -4.0E+150D;
/* 1163:1163 */    e.Sym.Org.t = t;
/* 1164:1164 */    tess.event = e.Sym.Org;
/* 1165:     */    
/* 1166:1166 */    reg.eUp = e;
/* 1167:1167 */    reg.windingNumber = 0;
/* 1168:1168 */    reg.inside = false;
/* 1169:1169 */    reg.fixUpperEdge = false;
/* 1170:1170 */    reg.sentinel = true;
/* 1171:1171 */    reg.dirty = false;
/* 1172:1172 */    reg.nodeUp = Dict.dictInsert(tess.dict, reg);
/* 1173:1173 */    if (reg.nodeUp == null) { throw new RuntimeException();
/* 1174:     */    }
/* 1175:     */  }
/* 1176:     */  
/* 1181:     */  static void InitEdgeDict(GLUtessellatorImpl tess)
/* 1182:     */  {
/* 1183:1183 */    tess.dict = Dict.dictNewDict(tess, new Dict.DictLeq() {
/* 1184:     */      public boolean leq(Object frame, Object key1, Object key2) {
/* 1185:1185 */        return Sweep.EdgeLeq(this.val$tess, (ActiveRegion)key1, (ActiveRegion)key2);
/* 1186:     */      }
/* 1187:     */    });
/* 1188:1188 */    if (tess.dict == null) { throw new RuntimeException();
/* 1189:     */    }
/* 1190:1190 */    AddSentinel(tess, -4.0E+150D);
/* 1191:1191 */    AddSentinel(tess, 4.0E+150D);
/* 1192:     */  }
/* 1193:     */  
/* 1195:     */  static void DoneEdgeDict(GLUtessellatorImpl tess)
/* 1196:     */  {
/* 1197:1197 */    int fixedEdges = 0;
/* 1198:     */    
/* 1199:     */    ActiveRegion reg;
/* 1200:1200 */    while ((reg = (ActiveRegion)Dict.dictKey(Dict.dictMin(tess.dict))) != null)
/* 1201:     */    {
/* 1206:1206 */      if (!reg.sentinel) {
/* 1207:1207 */        assert (reg.fixUpperEdge);
/* 1208:1208 */        if (!$assertionsDisabled) { fixedEdges++; if (fixedEdges != 1) throw new AssertionError();
/* 1209:     */        } }
/* 1210:1210 */      assert (reg.windingNumber == 0);
/* 1211:1211 */      DeleteRegion(tess, reg);
/* 1212:     */    }
/* 1213:     */    
/* 1214:1214 */    Dict.dictDeleteDict(tess.dict);
/* 1215:     */  }
/* 1216:     */  
/* 1221:     */  static void RemoveDegenerateEdges(GLUtessellatorImpl tess)
/* 1222:     */  {
/* 1223:1223 */    GLUhalfEdge eHead = tess.mesh.eHead;
/* 1224:     */    
/* 1225:     */    GLUhalfEdge eNext;
/* 1226:1226 */    for (GLUhalfEdge e = eHead.next; e != eHead; e = eNext) {
/* 1227:1227 */      eNext = e.next;
/* 1228:1228 */      GLUhalfEdge eLnext = e.Lnext;
/* 1229:     */      
/* 1230:1230 */      if ((Geom.VertEq(e.Org, e.Sym.Org)) && (e.Lnext.Lnext != e))
/* 1231:     */      {
/* 1233:1233 */        SpliceMergeVertices(tess, eLnext, e);
/* 1234:1234 */        if (!Mesh.__gl_meshDelete(e)) throw new RuntimeException();
/* 1235:1235 */        e = eLnext;
/* 1236:1236 */        eLnext = e.Lnext;
/* 1237:     */      }
/* 1238:1238 */      if (eLnext.Lnext == e)
/* 1239:     */      {
/* 1241:1241 */        if (eLnext != e) {
/* 1242:1242 */          if ((eLnext == eNext) || (eLnext == eNext.Sym)) {
/* 1243:1243 */            eNext = eNext.next;
/* 1244:     */          }
/* 1245:1245 */          if (!Mesh.__gl_meshDelete(eLnext)) throw new RuntimeException();
/* 1246:     */        }
/* 1247:1247 */        if ((e == eNext) || (e == eNext.Sym)) {
/* 1248:1248 */          eNext = eNext.next;
/* 1249:     */        }
/* 1250:1250 */        if (!Mesh.__gl_meshDelete(e)) { throw new RuntimeException();
/* 1251:     */        }
/* 1252:     */      }
/* 1253:     */    }
/* 1254:     */  }
/* 1255:     */  
/* 1262:     */  static boolean InitPriorityQ(GLUtessellatorImpl tess)
/* 1263:     */  {
/* 1264:1264 */    PriorityQ pq = tess.pq = PriorityQ.pqNewPriorityQ(new PriorityQ.Leq() {
/* 1265:     */      public boolean leq(Object key1, Object key2) {
/* 1266:1266 */        return Geom.VertLeq((GLUvertex)key1, (GLUvertex)key2);
/* 1267:     */      }
/* 1268:     */    });
/* 1269:1269 */    if (pq == null) { return false;
/* 1270:     */    }
/* 1271:1271 */    GLUvertex vHead = tess.mesh.vHead;
/* 1272:1272 */    for (GLUvertex v = vHead.next; v != vHead; v = v.next) {
/* 1273:1273 */      v.pqHandle = pq.pqInsert(v);
/* 1274:1274 */      if (v.pqHandle == 9223372036854775807L) break;
/* 1275:     */    }
/* 1276:1276 */    if ((v != vHead) || (!pq.pqInit())) {
/* 1277:1277 */      tess.pq.pqDeletePriorityQ();
/* 1278:1278 */      tess.pq = null;
/* 1279:1279 */      return false;
/* 1280:     */    }
/* 1281:     */    
/* 1282:1282 */    return true;
/* 1283:     */  }
/* 1284:     */  
/* 1285:     */  static void DonePriorityQ(GLUtessellatorImpl tess)
/* 1286:     */  {
/* 1287:1287 */    tess.pq.pqDeletePriorityQ();
/* 1288:     */  }
/* 1289:     */  
/* 1299:     */  static boolean RemoveDegenerateFaces(GLUmesh mesh)
/* 1300:     */  {
/* 1301:     */    GLUface fNext;
/* 1302:     */    
/* 1310:1310 */    for (GLUface f = mesh.fHead.next; f != mesh.fHead; f = fNext) {
/* 1311:1311 */      fNext = f.next;
/* 1312:1312 */      GLUhalfEdge e = f.anEdge;
/* 1313:1313 */      assert (e.Lnext != e);
/* 1314:     */      
/* 1315:1315 */      if (e.Lnext.Lnext == e)
/* 1316:     */      {
/* 1317:1317 */        AddWinding(e.Onext, e);
/* 1318:1318 */        if (!Mesh.__gl_meshDelete(e)) return false;
/* 1319:     */      }
/* 1320:     */    }
/* 1321:1321 */    return true;
/* 1322:     */  }
/* 1323:     */  
/* 1332:     */  public static boolean __gl_computeInterior(GLUtessellatorImpl tess)
/* 1333:     */  {
/* 1334:1334 */    tess.fatalError = false;
/* 1335:     */    
/* 1342:1342 */    RemoveDegenerateEdges(tess);
/* 1343:1343 */    if (!InitPriorityQ(tess)) return false;
/* 1344:1344 */    InitEdgeDict(tess);
/* 1345:     */    
/* 1346:     */    GLUvertex v;
/* 1347:1347 */    while ((v = (GLUvertex)tess.pq.pqExtractMin()) != null) {
/* 1348:     */      for (;;) {
/* 1349:1349 */        GLUvertex vNext = (GLUvertex)tess.pq.pqMinimum();
/* 1350:1350 */        if ((vNext == null) || (!Geom.VertEq(vNext, v))) {
/* 1351:     */          break;
/* 1352:     */        }
/* 1353:     */        
/* 1366:1366 */        vNext = (GLUvertex)tess.pq.pqExtractMin();
/* 1367:1367 */        SpliceMergeVertices(tess, v.anEdge, vNext.anEdge);
/* 1368:     */      }
/* 1369:1369 */      SweepEvent(tess, v);
/* 1370:     */    }
/* 1371:     */    
/* 1374:1374 */    tess.event = ((ActiveRegion)Dict.dictKey(Dict.dictMin(tess.dict))).eUp.Org;
/* 1375:1375 */    DebugEvent(tess);
/* 1376:1376 */    DoneEdgeDict(tess);
/* 1377:1377 */    DonePriorityQ(tess);
/* 1378:     */    
/* 1379:1379 */    if (!RemoveDegenerateFaces(tess.mesh)) return false;
/* 1380:1380 */    Mesh.__gl_meshCheckMesh(tess.mesh);
/* 1381:     */    
/* 1382:1382 */    return true;
/* 1383:     */  }
/* 1384:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.Sweep
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */