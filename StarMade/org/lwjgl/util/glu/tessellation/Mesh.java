/*   1:    */package org.lwjgl.util.glu.tessellation;
/*   2:    */
/* 102:    */class Mesh
/* 103:    */{
/* 104:    */  static GLUhalfEdge MakeEdge(GLUhalfEdge eNext)
/* 105:    */  {
/* 106:106 */    GLUhalfEdge e = new GLUhalfEdge(true);
/* 107:    */    
/* 108:108 */    GLUhalfEdge eSym = new GLUhalfEdge(false);
/* 109:    */    
/* 112:112 */    if (!eNext.first) {
/* 113:113 */      eNext = eNext.Sym;
/* 114:    */    }
/* 115:    */    
/* 119:119 */    GLUhalfEdge ePrev = eNext.Sym.next;
/* 120:120 */    eSym.next = ePrev;
/* 121:121 */    ePrev.Sym.next = e;
/* 122:122 */    e.next = eNext;
/* 123:123 */    eNext.Sym.next = eSym;
/* 124:    */    
/* 125:125 */    e.Sym = eSym;
/* 126:126 */    e.Onext = e;
/* 127:127 */    e.Lnext = eSym;
/* 128:128 */    e.Org = null;
/* 129:129 */    e.Lface = null;
/* 130:130 */    e.winding = 0;
/* 131:131 */    e.activeRegion = null;
/* 132:    */    
/* 133:133 */    eSym.Sym = e;
/* 134:134 */    eSym.Onext = eSym;
/* 135:135 */    eSym.Lnext = e;
/* 136:136 */    eSym.Org = null;
/* 137:137 */    eSym.Lface = null;
/* 138:138 */    eSym.winding = 0;
/* 139:139 */    eSym.activeRegion = null;
/* 140:    */    
/* 141:141 */    return e;
/* 142:    */  }
/* 143:    */  
/* 149:    */  static void Splice(GLUhalfEdge a, GLUhalfEdge b)
/* 150:    */  {
/* 151:151 */    GLUhalfEdge aOnext = a.Onext;
/* 152:152 */    GLUhalfEdge bOnext = b.Onext;
/* 153:    */    
/* 154:154 */    aOnext.Sym.Lnext = b;
/* 155:155 */    bOnext.Sym.Lnext = a;
/* 156:156 */    a.Onext = bOnext;
/* 157:157 */    b.Onext = aOnext;
/* 158:    */  }
/* 159:    */  
/* 168:    */  static void MakeVertex(GLUvertex newVertex, GLUhalfEdge eOrig, GLUvertex vNext)
/* 169:    */  {
/* 170:170 */    GLUvertex vNew = newVertex;
/* 171:    */    
/* 172:172 */    assert (vNew != null);
/* 173:    */    
/* 175:175 */    GLUvertex vPrev = vNext.prev;
/* 176:176 */    vNew.prev = vPrev;
/* 177:177 */    vPrev.next = vNew;
/* 178:178 */    vNew.next = vNext;
/* 179:179 */    vNext.prev = vNew;
/* 180:    */    
/* 181:181 */    vNew.anEdge = eOrig;
/* 182:182 */    vNew.data = null;
/* 183:    */    
/* 186:186 */    GLUhalfEdge e = eOrig;
/* 187:    */    do {
/* 188:188 */      e.Org = vNew;
/* 189:189 */      e = e.Onext;
/* 190:190 */    } while (e != eOrig);
/* 191:    */  }
/* 192:    */  
/* 200:    */  static void MakeFace(GLUface newFace, GLUhalfEdge eOrig, GLUface fNext)
/* 201:    */  {
/* 202:202 */    GLUface fNew = newFace;
/* 203:    */    
/* 204:204 */    assert (fNew != null);
/* 205:    */    
/* 207:207 */    GLUface fPrev = fNext.prev;
/* 208:208 */    fNew.prev = fPrev;
/* 209:209 */    fPrev.next = fNew;
/* 210:210 */    fNew.next = fNext;
/* 211:211 */    fNext.prev = fNew;
/* 212:    */    
/* 213:213 */    fNew.anEdge = eOrig;
/* 214:214 */    fNew.data = null;
/* 215:215 */    fNew.trail = null;
/* 216:216 */    fNew.marked = false;
/* 217:    */    
/* 221:221 */    fNew.inside = fNext.inside;
/* 222:    */    
/* 224:224 */    GLUhalfEdge e = eOrig;
/* 225:    */    do {
/* 226:226 */      e.Lface = fNew;
/* 227:227 */      e = e.Lnext;
/* 228:228 */    } while (e != eOrig);
/* 229:    */  }
/* 230:    */  
/* 236:    */  static void KillEdge(GLUhalfEdge eDel)
/* 237:    */  {
/* 238:238 */    if (!eDel.first) {
/* 239:239 */      eDel = eDel.Sym;
/* 240:    */    }
/* 241:    */    
/* 243:243 */    GLUhalfEdge eNext = eDel.next;
/* 244:244 */    GLUhalfEdge ePrev = eDel.Sym.next;
/* 245:245 */    eNext.Sym.next = ePrev;
/* 246:246 */    ePrev.Sym.next = eNext;
/* 247:    */  }
/* 248:    */  
/* 252:    */  static void KillVertex(GLUvertex vDel, GLUvertex newOrg)
/* 253:    */  {
/* 254:254 */    GLUhalfEdge eStart = vDel.anEdge;
/* 255:    */    
/* 258:258 */    GLUhalfEdge e = eStart;
/* 259:    */    do {
/* 260:260 */      e.Org = newOrg;
/* 261:261 */      e = e.Onext;
/* 262:262 */    } while (e != eStart);
/* 263:    */    
/* 265:265 */    GLUvertex vPrev = vDel.prev;
/* 266:266 */    GLUvertex vNext = vDel.next;
/* 267:267 */    vNext.prev = vPrev;
/* 268:268 */    vPrev.next = vNext;
/* 269:    */  }
/* 270:    */  
/* 273:    */  static void KillFace(GLUface fDel, GLUface newLface)
/* 274:    */  {
/* 275:275 */    GLUhalfEdge eStart = fDel.anEdge;
/* 276:    */    
/* 279:279 */    GLUhalfEdge e = eStart;
/* 280:    */    do {
/* 281:281 */      e.Lface = newLface;
/* 282:282 */      e = e.Lnext;
/* 283:283 */    } while (e != eStart);
/* 284:    */    
/* 286:286 */    GLUface fPrev = fDel.prev;
/* 287:287 */    GLUface fNext = fDel.next;
/* 288:288 */    fNext.prev = fPrev;
/* 289:289 */    fPrev.next = fNext;
/* 290:    */  }
/* 291:    */  
/* 297:    */  public static GLUhalfEdge __gl_meshMakeEdge(GLUmesh mesh)
/* 298:    */  {
/* 299:299 */    GLUvertex newVertex1 = new GLUvertex();
/* 300:300 */    GLUvertex newVertex2 = new GLUvertex();
/* 301:301 */    GLUface newFace = new GLUface();
/* 302:    */    
/* 304:304 */    GLUhalfEdge e = MakeEdge(mesh.eHead);
/* 305:305 */    if (e == null) { return null;
/* 306:    */    }
/* 307:307 */    MakeVertex(newVertex1, e, mesh.vHead);
/* 308:308 */    MakeVertex(newVertex2, e.Sym, mesh.vHead);
/* 309:309 */    MakeFace(newFace, e, mesh.fHead);
/* 310:310 */    return e;
/* 311:    */  }
/* 312:    */  
/* 336:    */  public static boolean __gl_meshSplice(GLUhalfEdge eOrg, GLUhalfEdge eDst)
/* 337:    */  {
/* 338:338 */    boolean joiningLoops = false;
/* 339:339 */    boolean joiningVertices = false;
/* 340:    */    
/* 341:341 */    if (eOrg == eDst) { return true;
/* 342:    */    }
/* 343:343 */    if (eDst.Org != eOrg.Org)
/* 344:    */    {
/* 345:345 */      joiningVertices = true;
/* 346:346 */      KillVertex(eDst.Org, eOrg.Org);
/* 347:    */    }
/* 348:348 */    if (eDst.Lface != eOrg.Lface)
/* 349:    */    {
/* 350:350 */      joiningLoops = true;
/* 351:351 */      KillFace(eDst.Lface, eOrg.Lface);
/* 352:    */    }
/* 353:    */    
/* 355:355 */    Splice(eDst, eOrg);
/* 356:    */    
/* 357:357 */    if (!joiningVertices) {
/* 358:358 */      GLUvertex newVertex = new GLUvertex();
/* 359:    */      
/* 363:363 */      MakeVertex(newVertex, eDst, eOrg.Org);
/* 364:364 */      eOrg.Org.anEdge = eOrg;
/* 365:    */    }
/* 366:366 */    if (!joiningLoops) {
/* 367:367 */      GLUface newFace = new GLUface();
/* 368:    */      
/* 372:372 */      MakeFace(newFace, eDst, eOrg.Lface);
/* 373:373 */      eOrg.Lface.anEdge = eOrg;
/* 374:    */    }
/* 375:    */    
/* 376:376 */    return true;
/* 377:    */  }
/* 378:    */  
/* 389:    */  static boolean __gl_meshDelete(GLUhalfEdge eDel)
/* 390:    */  {
/* 391:391 */    GLUhalfEdge eDelSym = eDel.Sym;
/* 392:392 */    boolean joiningLoops = false;
/* 393:    */    
/* 397:397 */    if (eDel.Lface != eDel.Sym.Lface)
/* 398:    */    {
/* 399:399 */      joiningLoops = true;
/* 400:400 */      KillFace(eDel.Lface, eDel.Sym.Lface);
/* 401:    */    }
/* 402:    */    
/* 403:403 */    if (eDel.Onext == eDel) {
/* 404:404 */      KillVertex(eDel.Org, null);
/* 405:    */    }
/* 406:    */    else {
/* 407:407 */      eDel.Sym.Lface.anEdge = eDel.Sym.Lnext;
/* 408:408 */      eDel.Org.anEdge = eDel.Onext;
/* 409:    */      
/* 410:410 */      Splice(eDel, eDel.Sym.Lnext);
/* 411:411 */      if (!joiningLoops) {
/* 412:412 */        GLUface newFace = new GLUface();
/* 413:    */        
/* 415:415 */        MakeFace(newFace, eDel, eDel.Lface);
/* 416:    */      }
/* 417:    */    }
/* 418:    */    
/* 422:422 */    if (eDelSym.Onext == eDelSym) {
/* 423:423 */      KillVertex(eDelSym.Org, null);
/* 424:424 */      KillFace(eDelSym.Lface, null);
/* 425:    */    }
/* 426:    */    else {
/* 427:427 */      eDel.Lface.anEdge = eDelSym.Sym.Lnext;
/* 428:428 */      eDelSym.Org.anEdge = eDelSym.Onext;
/* 429:429 */      Splice(eDelSym, eDelSym.Sym.Lnext);
/* 430:    */    }
/* 431:    */    
/* 433:433 */    KillEdge(eDel);
/* 434:    */    
/* 435:435 */    return true;
/* 436:    */  }
/* 437:    */  
/* 450:    */  static GLUhalfEdge __gl_meshAddEdgeVertex(GLUhalfEdge eOrg)
/* 451:    */  {
/* 452:452 */    GLUhalfEdge eNew = MakeEdge(eOrg);
/* 453:    */    
/* 454:454 */    GLUhalfEdge eNewSym = eNew.Sym;
/* 455:    */    
/* 457:457 */    Splice(eNew, eOrg.Lnext);
/* 458:    */    
/* 460:460 */    eNew.Org = eOrg.Sym.Org;
/* 461:    */    
/* 462:462 */    GLUvertex newVertex = new GLUvertex();
/* 463:    */    
/* 464:464 */    MakeVertex(newVertex, eNewSym, eNew.Org);
/* 465:    */    
/* 466:466 */    eNew.Lface = (eNewSym.Lface = eOrg.Lface);
/* 467:    */    
/* 468:468 */    return eNew;
/* 469:    */  }
/* 470:    */  
/* 476:    */  public static GLUhalfEdge __gl_meshSplitEdge(GLUhalfEdge eOrg)
/* 477:    */  {
/* 478:478 */    GLUhalfEdge tempHalfEdge = __gl_meshAddEdgeVertex(eOrg);
/* 479:    */    
/* 480:480 */    GLUhalfEdge eNew = tempHalfEdge.Sym;
/* 481:    */    
/* 483:483 */    Splice(eOrg.Sym, eOrg.Sym.Sym.Lnext);
/* 484:484 */    Splice(eOrg.Sym, eNew);
/* 485:    */    
/* 487:487 */    eOrg.Sym.Org = eNew.Org;
/* 488:488 */    eNew.Sym.Org.anEdge = eNew.Sym;
/* 489:489 */    eNew.Sym.Lface = eOrg.Sym.Lface;
/* 490:490 */    eNew.winding = eOrg.winding;
/* 491:491 */    eNew.Sym.winding = eOrg.Sym.winding;
/* 492:    */    
/* 493:493 */    return eNew;
/* 494:    */  }
/* 495:    */  
/* 507:    */  static GLUhalfEdge __gl_meshConnect(GLUhalfEdge eOrg, GLUhalfEdge eDst)
/* 508:    */  {
/* 509:509 */    boolean joiningLoops = false;
/* 510:510 */    GLUhalfEdge eNew = MakeEdge(eOrg);
/* 511:    */    
/* 512:512 */    GLUhalfEdge eNewSym = eNew.Sym;
/* 513:    */    
/* 514:514 */    if (eDst.Lface != eOrg.Lface)
/* 515:    */    {
/* 516:516 */      joiningLoops = true;
/* 517:517 */      KillFace(eDst.Lface, eOrg.Lface);
/* 518:    */    }
/* 519:    */    
/* 521:521 */    Splice(eNew, eOrg.Lnext);
/* 522:522 */    Splice(eNewSym, eDst);
/* 523:    */    
/* 525:525 */    eNew.Org = eOrg.Sym.Org;
/* 526:526 */    eNewSym.Org = eDst.Org;
/* 527:527 */    eNew.Lface = (eNewSym.Lface = eOrg.Lface);
/* 528:    */    
/* 530:530 */    eOrg.Lface.anEdge = eNewSym;
/* 531:    */    
/* 532:532 */    if (!joiningLoops) {
/* 533:533 */      GLUface newFace = new GLUface();
/* 534:    */      
/* 536:536 */      MakeFace(newFace, eNew, eOrg.Lface);
/* 537:    */    }
/* 538:538 */    return eNew;
/* 539:    */  }
/* 540:    */  
/* 550:    */  static void __gl_meshZapFace(GLUface fZap)
/* 551:    */  {
/* 552:552 */    GLUhalfEdge eStart = fZap.anEdge;
/* 553:    */    
/* 557:557 */    GLUhalfEdge eNext = eStart.Lnext;
/* 558:    */    GLUhalfEdge e;
/* 559:559 */    do { e = eNext;
/* 560:560 */      eNext = e.Lnext;
/* 561:    */      
/* 562:562 */      e.Lface = null;
/* 563:563 */      if (e.Sym.Lface == null)
/* 564:    */      {
/* 566:566 */        if (e.Onext == e) {
/* 567:567 */          KillVertex(e.Org, null);
/* 568:    */        }
/* 569:    */        else {
/* 570:570 */          e.Org.anEdge = e.Onext;
/* 571:571 */          Splice(e, e.Sym.Lnext);
/* 572:    */        }
/* 573:573 */        GLUhalfEdge eSym = e.Sym;
/* 574:574 */        if (eSym.Onext == eSym) {
/* 575:575 */          KillVertex(eSym.Org, null);
/* 576:    */        }
/* 577:    */        else {
/* 578:578 */          eSym.Org.anEdge = eSym.Onext;
/* 579:579 */          Splice(eSym, eSym.Sym.Lnext);
/* 580:    */        }
/* 581:581 */        KillEdge(e);
/* 582:    */      }
/* 583:583 */    } while (e != eStart);
/* 584:    */    
/* 586:586 */    GLUface fPrev = fZap.prev;
/* 587:587 */    GLUface fNext = fZap.next;
/* 588:588 */    fNext.prev = fPrev;
/* 589:589 */    fPrev.next = fNext;
/* 590:    */  }
/* 591:    */  
/* 599:    */  public static GLUmesh __gl_meshNewMesh()
/* 600:    */  {
/* 601:601 */    GLUmesh mesh = new GLUmesh();
/* 602:    */    
/* 603:603 */    GLUvertex v = mesh.vHead;
/* 604:604 */    GLUface f = mesh.fHead;
/* 605:605 */    GLUhalfEdge e = mesh.eHead;
/* 606:606 */    GLUhalfEdge eSym = mesh.eHeadSym;
/* 607:    */    
/* 608:608 */    v.prev = v;v.next = v;
/* 609:609 */    v.anEdge = null;
/* 610:610 */    v.data = null;
/* 611:    */    
/* 612:612 */    f.next = (f.prev = f);
/* 613:613 */    f.anEdge = null;
/* 614:614 */    f.data = null;
/* 615:615 */    f.trail = null;
/* 616:616 */    f.marked = false;
/* 617:617 */    f.inside = false;
/* 618:    */    
/* 619:619 */    e.next = e;
/* 620:620 */    e.Sym = eSym;
/* 621:621 */    e.Onext = null;
/* 622:622 */    e.Lnext = null;
/* 623:623 */    e.Org = null;
/* 624:624 */    e.Lface = null;
/* 625:625 */    e.winding = 0;
/* 626:626 */    e.activeRegion = null;
/* 627:    */    
/* 628:628 */    eSym.next = eSym;
/* 629:629 */    eSym.Sym = e;
/* 630:630 */    eSym.Onext = null;
/* 631:631 */    eSym.Lnext = null;
/* 632:632 */    eSym.Org = null;
/* 633:633 */    eSym.Lface = null;
/* 634:634 */    eSym.winding = 0;
/* 635:635 */    eSym.activeRegion = null;
/* 636:    */    
/* 637:637 */    return mesh;
/* 638:    */  }
/* 639:    */  
/* 643:    */  static GLUmesh __gl_meshUnion(GLUmesh mesh1, GLUmesh mesh2)
/* 644:    */  {
/* 645:645 */    GLUface f1 = mesh1.fHead;
/* 646:646 */    GLUvertex v1 = mesh1.vHead;
/* 647:647 */    GLUhalfEdge e1 = mesh1.eHead;
/* 648:648 */    GLUface f2 = mesh2.fHead;
/* 649:649 */    GLUvertex v2 = mesh2.vHead;
/* 650:650 */    GLUhalfEdge e2 = mesh2.eHead;
/* 651:    */    
/* 653:653 */    if (f2.next != f2) {
/* 654:654 */      f1.prev.next = f2.next;
/* 655:655 */      f2.next.prev = f1.prev;
/* 656:656 */      f2.prev.next = f1;
/* 657:657 */      f1.prev = f2.prev;
/* 658:    */    }
/* 659:    */    
/* 660:660 */    if (v2.next != v2) {
/* 661:661 */      v1.prev.next = v2.next;
/* 662:662 */      v2.next.prev = v1.prev;
/* 663:663 */      v2.prev.next = v1;
/* 664:664 */      v1.prev = v2.prev;
/* 665:    */    }
/* 666:    */    
/* 667:667 */    if (e2.next != e2) {
/* 668:668 */      e1.Sym.next.Sym.next = e2.next;
/* 669:669 */      e2.next.Sym.next = e1.Sym.next;
/* 670:670 */      e2.Sym.next.Sym.next = e1;
/* 671:671 */      e1.Sym.next = e2.Sym.next;
/* 672:    */    }
/* 673:    */    
/* 674:674 */    return mesh1;
/* 675:    */  }
/* 676:    */  
/* 679:    */  static void __gl_meshDeleteMeshZap(GLUmesh mesh)
/* 680:    */  {
/* 681:681 */    GLUface fHead = mesh.fHead;
/* 682:    */    
/* 683:683 */    while (fHead.next != fHead) {
/* 684:684 */      __gl_meshZapFace(fHead.next);
/* 685:    */    }
/* 686:686 */    assert (mesh.vHead.next == mesh.vHead);
/* 687:    */  }
/* 688:    */  
/* 691:    */  public static void __gl_meshDeleteMesh(GLUmesh mesh)
/* 692:    */  {
/* 693:    */    GLUface fNext;
/* 694:    */    
/* 696:696 */    for (GLUface f = mesh.fHead.next; f != mesh.fHead; f = fNext) {
/* 697:697 */      fNext = f.next;
/* 698:    */    }
/* 699:    */    GLUvertex vNext;
/* 700:700 */    for (GLUvertex v = mesh.vHead.next; v != mesh.vHead; v = vNext) {
/* 701:701 */      vNext = v.next;
/* 702:    */    }
/* 703:    */    GLUhalfEdge eNext;
/* 704:704 */    for (GLUhalfEdge e = mesh.eHead.next; e != mesh.eHead; e = eNext)
/* 705:    */    {
/* 706:706 */      eNext = e.next;
/* 707:    */    }
/* 708:    */  }
/* 709:    */  
/* 711:    */  public static void __gl_meshCheckMesh(GLUmesh mesh)
/* 712:    */  {
/* 713:713 */    GLUface fHead = mesh.fHead;
/* 714:714 */    GLUvertex vHead = mesh.vHead;
/* 715:715 */    GLUhalfEdge eHead = mesh.eHead;
/* 716:    */    
/* 720:720 */    GLUface fPrev = fHead;
/* 721:721 */    GLUface f; for (fPrev = fHead; (f = fPrev.next) != fHead; fPrev = f) {
/* 722:722 */      assert (f.prev == fPrev);
/* 723:723 */      GLUhalfEdge e = f.anEdge;
/* 724:    */      do {
/* 725:725 */        assert (e.Sym != e);
/* 726:726 */        assert (e.Sym.Sym == e);
/* 727:727 */        assert (e.Lnext.Onext.Sym == e);
/* 728:728 */        assert (e.Onext.Sym.Lnext == e);
/* 729:729 */        assert (e.Lface == f);
/* 730:730 */        e = e.Lnext;
/* 731:731 */      } while (e != f.anEdge);
/* 732:    */    }
/* 733:733 */    assert ((f.prev == fPrev) && (f.anEdge == null) && (f.data == null));
/* 734:    */    
/* 735:735 */    GLUvertex vPrev = vHead;
/* 736:736 */    GLUvertex v; for (vPrev = vHead; (v = vPrev.next) != vHead; vPrev = v) {
/* 737:737 */      assert (v.prev == vPrev);
/* 738:738 */      GLUhalfEdge e = v.anEdge;
/* 739:    */      do {
/* 740:740 */        assert (e.Sym != e);
/* 741:741 */        assert (e.Sym.Sym == e);
/* 742:742 */        assert (e.Lnext.Onext.Sym == e);
/* 743:743 */        assert (e.Onext.Sym.Lnext == e);
/* 744:744 */        assert (e.Org == v);
/* 745:745 */        e = e.Onext;
/* 746:746 */      } while (e != v.anEdge);
/* 747:    */    }
/* 748:748 */    assert ((v.prev == vPrev) && (v.anEdge == null) && (v.data == null));
/* 749:    */    
/* 750:750 */    GLUhalfEdge ePrev = eHead;
/* 751:751 */    GLUhalfEdge e; for (ePrev = eHead; (e = ePrev.next) != eHead; ePrev = e) {
/* 752:752 */      assert (e.Sym.next == ePrev.Sym);
/* 753:753 */      assert (e.Sym != e);
/* 754:754 */      assert (e.Sym.Sym == e);
/* 755:755 */      assert (e.Org != null);
/* 756:756 */      assert (e.Sym.Org != null);
/* 757:757 */      assert (e.Lnext.Onext.Sym == e);
/* 758:758 */      assert (e.Onext.Sym.Lnext == e);
/* 759:    */    }
/* 760:760 */    assert ((e.Sym.next == ePrev.Sym) && (e.Sym == mesh.eHeadSym) && (e.Sym.Sym == e) && (e.Org == null) && (e.Sym.Org == null) && (e.Lface == null) && (e.Sym.Lface == null));
/* 761:    */  }
/* 762:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.Mesh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */