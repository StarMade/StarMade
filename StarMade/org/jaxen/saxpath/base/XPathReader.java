/*    1:     */package org.jaxen.saxpath.base;
/*    2:     */
/*    3:     */import java.util.ArrayList;
/*    4:     */import org.jaxen.saxpath.Axis;
/*    5:     */import org.jaxen.saxpath.SAXPathException;
/*    6:     */import org.jaxen.saxpath.XPathHandler;
/*    7:     */import org.jaxen.saxpath.XPathSyntaxException;
/*    8:     */import org.jaxen.saxpath.helpers.DefaultXPathHandler;
/*    9:     */
/*   67:     */public class XPathReader
/*   68:     */  implements org.jaxen.saxpath.XPathReader
/*   69:     */{
/*   70:     */  private ArrayList tokens;
/*   71:     */  private XPathLexer lexer;
/*   72:     */  private XPathHandler handler;
/*   73:  73 */  private static XPathHandler defaultHandler = new DefaultXPathHandler();
/*   74:     */  
/*   79:     */  public XPathReader()
/*   80:     */  {
/*   81:  81 */    setXPathHandler(defaultHandler);
/*   82:     */  }
/*   83:     */  
/*   84:     */  public void setXPathHandler(XPathHandler handler)
/*   85:     */  {
/*   86:  86 */    this.handler = handler;
/*   87:     */  }
/*   88:     */  
/*   89:     */  public XPathHandler getXPathHandler()
/*   90:     */  {
/*   91:  91 */    return this.handler;
/*   92:     */  }
/*   93:     */  
/*   94:     */  public void parse(String xpath) throws SAXPathException
/*   95:     */  {
/*   96:  96 */    setUpParse(xpath);
/*   97:     */    
/*   98:  98 */    getXPathHandler().startXPath();
/*   99:     */    
/*  100: 100 */    expr();
/*  101:     */    
/*  102: 102 */    getXPathHandler().endXPath();
/*  103:     */    
/*  104: 104 */    if (LA(1) != -1)
/*  105:     */    {
/*  106: 106 */      XPathSyntaxException ex = createSyntaxException("Unexpected '" + LT(1).getTokenText() + "'");
/*  107: 107 */      throw ex;
/*  108:     */    }
/*  109:     */    
/*  110: 110 */    this.lexer = null;
/*  111: 111 */    this.tokens = null;
/*  112:     */  }
/*  113:     */  
/*  114:     */  void setUpParse(String xpath)
/*  115:     */  {
/*  116: 116 */    this.tokens = new ArrayList();
/*  117: 117 */    this.lexer = new XPathLexer(xpath);
/*  118:     */  }
/*  119:     */  
/*  120:     */  private void pathExpr() throws SAXPathException
/*  121:     */  {
/*  122: 122 */    getXPathHandler().startPathExpr();
/*  123:     */    
/*  124: 124 */    switch (LA(1))
/*  125:     */    {
/*  127:     */    case 26: 
/*  128:     */    case 29: 
/*  129: 129 */      filterExpr();
/*  130:     */      
/*  131: 131 */      if ((LA(1) == 12) || (LA(1) == 13))
/*  132:     */      {
/*  133: 133 */        XPathSyntaxException ex = createSyntaxException("Node-set expected");
/*  134: 134 */        throw ex;
/*  135:     */      }
/*  136:     */      
/*  139:     */      break;
/*  140:     */    case 23: 
/*  141:     */    case 25: 
/*  142: 142 */      filterExpr();
/*  143:     */      
/*  144: 144 */      if ((LA(1) == 12) || (LA(1) == 13))
/*  145:     */      {
/*  146: 146 */        locationPath(false); } break;
/*  147:     */    
/*  152:     */    case 16: 
/*  153: 153 */      if (((LA(2) == 23) && (!isNodeTypeName(LT(1)))) || ((LA(2) == 19) && (LA(4) == 23)))
/*  154:     */      {
/*  161: 161 */        filterExpr();
/*  162:     */        
/*  163: 163 */        if ((LA(1) == 12) || (LA(1) == 13))
/*  164:     */        {
/*  165: 165 */          locationPath(false);
/*  166:     */        }
/*  167:     */      }
/*  168:     */      else
/*  169:     */      {
/*  170: 170 */        locationPath(false);
/*  171:     */      }
/*  172: 172 */      break;
/*  173:     */    
/*  175:     */    case 9: 
/*  176:     */    case 14: 
/*  177:     */    case 15: 
/*  178:     */    case 17: 
/*  179: 179 */      locationPath(false);
/*  180: 180 */      break;
/*  181:     */    
/*  183:     */    case 12: 
/*  184:     */    case 13: 
/*  185: 185 */      locationPath(true);
/*  186: 186 */      break;
/*  187:     */    case 10: case 11: case 18: case 19: 
/*  188:     */    case 20: case 21: case 22: case 24: 
/*  189:     */    case 27: case 28: default: 
/*  190: 190 */      XPathSyntaxException ex = createSyntaxException("Unexpected '" + LT(1).getTokenText() + "'");
/*  191: 191 */      throw ex;
/*  192:     */    }
/*  193:     */    
/*  194:     */    
/*  195: 195 */    getXPathHandler().endPathExpr();
/*  196:     */  }
/*  197:     */  
/*  198:     */  private void literal() throws SAXPathException
/*  199:     */  {
/*  200: 200 */    Token token = match(26);
/*  201:     */    
/*  202: 202 */    getXPathHandler().literal(token.getTokenText());
/*  203:     */  }
/*  204:     */  
/*  205:     */  private void functionCall() throws SAXPathException
/*  206:     */  {
/*  207: 207 */    String prefix = null;
/*  208: 208 */    String functionName = null;
/*  209:     */    
/*  210: 210 */    if (LA(2) == 19)
/*  211:     */    {
/*  212: 212 */      prefix = match(16).getTokenText();
/*  213: 213 */      match(19);
/*  214:     */    }
/*  215:     */    else
/*  216:     */    {
/*  217: 217 */      prefix = "";
/*  218:     */    }
/*  219:     */    
/*  220: 220 */    functionName = match(16).getTokenText();
/*  221:     */    
/*  222: 222 */    getXPathHandler().startFunction(prefix, functionName);
/*  223:     */    
/*  225: 225 */    match(23);
/*  226:     */    
/*  227: 227 */    arguments();
/*  228:     */    
/*  229: 229 */    match(24);
/*  230:     */    
/*  231: 231 */    getXPathHandler().endFunction();
/*  232:     */  }
/*  233:     */  
/*  234:     */  private void arguments() throws SAXPathException
/*  235:     */  {
/*  236: 236 */    while (LA(1) != 24)
/*  237:     */    {
/*  238: 238 */      expr();
/*  239:     */      
/*  240: 240 */      if (LA(1) != 30)
/*  241:     */        break;
/*  242: 242 */      match(30);
/*  243:     */    }
/*  244:     */  }
/*  245:     */  
/*  251:     */  private void filterExpr()
/*  252:     */    throws SAXPathException
/*  253:     */  {
/*  254: 254 */    getXPathHandler().startFilterExpr();
/*  255:     */    
/*  256: 256 */    switch (LA(1))
/*  257:     */    {
/*  259:     */    case 29: 
/*  260: 260 */      Token token = match(29);
/*  261:     */      
/*  262: 262 */      getXPathHandler().number(Double.parseDouble(token.getTokenText()));
/*  263: 263 */      break;
/*  264:     */    
/*  266:     */    case 26: 
/*  267: 267 */      literal();
/*  268: 268 */      break;
/*  269:     */    
/*  271:     */    case 23: 
/*  272: 272 */      match(23);
/*  273: 273 */      expr();
/*  274: 274 */      match(24);
/*  275: 275 */      break;
/*  276:     */    
/*  278:     */    case 16: 
/*  279: 279 */      functionCall();
/*  280: 280 */      break;
/*  281:     */    
/*  283:     */    case 25: 
/*  284: 284 */      variableReference();
/*  285:     */    }
/*  286:     */    
/*  287:     */    
/*  289: 289 */    predicates();
/*  290:     */    
/*  291: 291 */    getXPathHandler().endFilterExpr();
/*  292:     */  }
/*  293:     */  
/*  294:     */  private void variableReference() throws SAXPathException
/*  295:     */  {
/*  296: 296 */    match(25);
/*  297:     */    
/*  298: 298 */    String prefix = null;
/*  299: 299 */    String variableName = null;
/*  300:     */    
/*  301: 301 */    if (LA(2) == 19)
/*  302:     */    {
/*  303: 303 */      prefix = match(16).getTokenText();
/*  304: 304 */      match(19);
/*  305:     */    }
/*  306:     */    else
/*  307:     */    {
/*  308: 308 */      prefix = "";
/*  309:     */    }
/*  310:     */    
/*  311: 311 */    variableName = match(16).getTokenText();
/*  312:     */    
/*  313: 313 */    getXPathHandler().variableReference(prefix, variableName);
/*  314:     */  }
/*  315:     */  
/*  316:     */  void locationPath(boolean isAbsolute)
/*  317:     */    throws SAXPathException
/*  318:     */  {
/*  319: 319 */    switch (LA(1))
/*  320:     */    {
/*  322:     */    case 12: 
/*  323:     */    case 13: 
/*  324: 324 */      if (isAbsolute)
/*  325:     */      {
/*  326: 326 */        absoluteLocationPath();
/*  327:     */      }
/*  328:     */      else
/*  329:     */      {
/*  330: 330 */        relativeLocationPath();
/*  331:     */      }
/*  332: 332 */      break;
/*  333:     */    
/*  335:     */    case 9: 
/*  336:     */    case 14: 
/*  337:     */    case 15: 
/*  338:     */    case 16: 
/*  339:     */    case 17: 
/*  340: 340 */      relativeLocationPath();
/*  341: 341 */      break;
/*  342:     */    case 10: 
/*  343:     */    case 11: 
/*  344:     */    default: 
/*  345: 345 */      XPathSyntaxException ex = createSyntaxException("Unexpected '" + LT(1).getTokenText() + "'");
/*  346: 346 */      throw ex;
/*  347:     */    }
/*  348:     */    
/*  349:     */  }
/*  350:     */  
/*  351:     */  private void absoluteLocationPath() throws SAXPathException
/*  352:     */  {
/*  353: 353 */    getXPathHandler().startAbsoluteLocationPath();
/*  354:     */    
/*  355: 355 */    switch (LA(1))
/*  356:     */    {
/*  358:     */    case 12: 
/*  359: 359 */      match(12);
/*  360:     */      
/*  361: 361 */      switch (LA(1))
/*  362:     */      {
/*  365:     */      case 9: 
/*  366:     */      case 14: 
/*  367:     */      case 15: 
/*  368:     */      case 16: 
/*  369:     */      case 17: 
/*  370: 370 */        steps();
/*  371:     */      }
/*  372:     */      
/*  373:     */      
/*  374: 374 */      break;
/*  375:     */    
/*  377:     */    case 13: 
/*  378: 378 */      getXPathHandler().startAllNodeStep(12);
/*  379: 379 */      getXPathHandler().endAllNodeStep();
/*  380:     */      
/*  381: 381 */      match(13);
/*  382: 382 */      switch (LA(1))
/*  383:     */      {
/*  385:     */      case 9: 
/*  386:     */      case 14: 
/*  387:     */      case 15: 
/*  388:     */      case 16: 
/*  389:     */      case 17: 
/*  390: 390 */        steps();
/*  391: 391 */        break;
/*  392:     */      case 10: case 11: case 12: 
/*  393:     */      case 13: default: 
/*  394: 394 */        XPathSyntaxException ex = createSyntaxException("Location path cannot end with //");
/*  395: 395 */        throw ex;
/*  396:     */      }
/*  397:     */      
/*  398:     */      break;
/*  399:     */    }
/*  400:     */    
/*  401: 401 */    getXPathHandler().endAbsoluteLocationPath();
/*  402:     */  }
/*  403:     */  
/*  404:     */  private void relativeLocationPath() throws SAXPathException
/*  405:     */  {
/*  406: 406 */    getXPathHandler().startRelativeLocationPath();
/*  407:     */    
/*  408: 408 */    switch (LA(1))
/*  409:     */    {
/*  411:     */    case 12: 
/*  412: 412 */      match(12);
/*  413: 413 */      break;
/*  414:     */    
/*  416:     */    case 13: 
/*  417: 417 */      getXPathHandler().startAllNodeStep(12);
/*  418: 418 */      getXPathHandler().endAllNodeStep();
/*  419:     */      
/*  420: 420 */      match(13);
/*  421:     */    }
/*  422:     */    
/*  423:     */    
/*  426: 426 */    steps();
/*  427:     */    
/*  428: 428 */    getXPathHandler().endRelativeLocationPath();
/*  429:     */  }
/*  430:     */  
/*  431:     */  private void steps() throws SAXPathException
/*  432:     */  {
/*  433: 433 */    switch (LA(1))
/*  434:     */    {
/*  437:     */    case 9: 
/*  438:     */    case 14: 
/*  439:     */    case 15: 
/*  440:     */    case 16: 
/*  441:     */    case 17: 
/*  442: 442 */      step();
/*  443: 443 */      break;
/*  444:     */    
/*  446:     */    case -1: 
/*  447: 447 */      return;
/*  448:     */    case 0: case 1: case 2: case 3: case 4: 
/*  449:     */    case 5: case 6: case 7: case 8: case 10: 
/*  450:     */    case 11: case 12: case 13: default: 
/*  451: 451 */      XPathSyntaxException ex = createSyntaxException("Expected one of '.', '..', '@', '*', <QName>");
/*  452: 452 */      throw ex;
/*  453:     */    }
/*  454:     */    
/*  455:     */    
/*  456:     */    for (;;)
/*  457:     */    {
/*  458: 458 */      if ((LA(1) == 12) || (LA(1) == 13))
/*  459:     */      {
/*  462: 462 */        switch (LA(1))
/*  463:     */        {
/*  465:     */        case 12: 
/*  466: 466 */          match(12);
/*  467: 467 */          break;
/*  468:     */        
/*  470:     */        case 13: 
/*  471: 471 */          getXPathHandler().startAllNodeStep(12);
/*  472: 472 */          getXPathHandler().endAllNodeStep();
/*  473:     */          
/*  474: 474 */          match(13);
/*  475:     */        
/*  478:     */        }
/*  479:     */        
/*  480:     */      } else {
/*  481: 481 */        return;
/*  482:     */      }
/*  483:     */      
/*  484: 484 */      switch (LA(1))
/*  485:     */      {
/*  487:     */      case 9: 
/*  488:     */      case 14: 
/*  489:     */      case 15: 
/*  490:     */      case 16: 
/*  491:     */      case 17: 
/*  492: 492 */        step();
/*  493:     */      }
/*  494:     */      
/*  495:     */    }
/*  496:     */    
/*  497: 497 */    XPathSyntaxException ex = createSyntaxException("Expected one of '.', '..', '@', '*', <QName>");
/*  498: 498 */    throw ex;
/*  499:     */  }
/*  500:     */  
/*  504:     */  void step()
/*  505:     */    throws SAXPathException
/*  506:     */  {
/*  507: 507 */    int axis = 0;
/*  508:     */    
/*  509: 509 */    switch (LA(1))
/*  510:     */    {
/*  512:     */    case 14: 
/*  513:     */    case 15: 
/*  514: 514 */      abbrStep();
/*  515: 515 */      return;
/*  516:     */    
/*  518:     */    case 17: 
/*  519: 519 */      axis = axisSpecifier();
/*  520: 520 */      break;
/*  521:     */    
/*  523:     */    case 16: 
/*  524: 524 */      if (LA(2) == 20)
/*  525:     */      {
/*  526: 526 */        axis = axisSpecifier();
/*  527:     */      }
/*  528:     */      else
/*  529:     */      {
/*  530: 530 */        axis = 1;
/*  531:     */      }
/*  532: 532 */      break;
/*  533:     */    
/*  535:     */    case 9: 
/*  536: 536 */      axis = 1;
/*  537:     */    }
/*  538:     */    
/*  539:     */    
/*  541: 541 */    nodeTest(axis);
/*  542:     */  }
/*  543:     */  
/*  544:     */  private int axisSpecifier() throws SAXPathException
/*  545:     */  {
/*  546: 546 */    int axis = 0;
/*  547:     */    
/*  548: 548 */    switch (LA(1))
/*  549:     */    {
/*  551:     */    case 17: 
/*  552: 552 */      match(17);
/*  553: 553 */      axis = 9;
/*  554: 554 */      break;
/*  555:     */    
/*  557:     */    case 16: 
/*  558: 558 */      Token token = LT(1);
/*  559:     */      
/*  560: 560 */      axis = Axis.lookup(token.getTokenText());
/*  561:     */      
/*  562: 562 */      if (axis == 0)
/*  563:     */      {
/*  564: 564 */        throwInvalidAxis(token.getTokenText());
/*  565:     */      }
/*  566:     */      
/*  567: 567 */      match(16);
/*  568: 568 */      match(20);
/*  569:     */      
/*  570: 570 */      break;
/*  571:     */    }
/*  572:     */    
/*  573:     */    
/*  574: 574 */    return axis;
/*  575:     */  }
/*  576:     */  
/*  577:     */  private void nodeTest(int axis) throws SAXPathException
/*  578:     */  {
/*  579: 579 */    switch (LA(1))
/*  580:     */    {
/*  582:     */    case 16: 
/*  583: 583 */      switch (LA(2))
/*  584:     */      {
/*  586:     */      case 23: 
/*  587: 587 */        nodeTypeTest(axis);
/*  588: 588 */        break;
/*  589:     */      
/*  591:     */      default: 
/*  592: 592 */        nameTest(axis); }
/*  593: 593 */      break;
/*  594:     */    
/*  599:     */    case 9: 
/*  600: 600 */      nameTest(axis);
/*  601: 601 */      break;
/*  602:     */    
/*  603:     */    default: 
/*  604: 604 */      XPathSyntaxException ex = createSyntaxException("Expected <QName> or *");
/*  605: 605 */      throw ex;
/*  606:     */    }
/*  607:     */    
/*  608:     */  }
/*  609:     */  
/*  610:     */  private void nodeTypeTest(int axis) throws SAXPathException {
/*  611: 611 */    Token nodeTypeToken = match(16);
/*  612: 612 */    String nodeType = nodeTypeToken.getTokenText();
/*  613:     */    
/*  614: 614 */    match(23);
/*  615:     */    
/*  616: 616 */    if ("processing-instruction".equals(nodeType))
/*  617:     */    {
/*  618: 618 */      String piName = "";
/*  619:     */      
/*  620: 620 */      if (LA(1) == 26)
/*  621:     */      {
/*  622: 622 */        piName = match(26).getTokenText();
/*  623:     */      }
/*  624:     */      
/*  625: 625 */      match(24);
/*  626:     */      
/*  627: 627 */      getXPathHandler().startProcessingInstructionNodeStep(axis, piName);
/*  628:     */      
/*  630: 630 */      predicates();
/*  631:     */      
/*  632: 632 */      getXPathHandler().endProcessingInstructionNodeStep();
/*  633:     */    }
/*  634: 634 */    else if ("node".equals(nodeType))
/*  635:     */    {
/*  636: 636 */      match(24);
/*  637:     */      
/*  638: 638 */      getXPathHandler().startAllNodeStep(axis);
/*  639:     */      
/*  640: 640 */      predicates();
/*  641:     */      
/*  642: 642 */      getXPathHandler().endAllNodeStep();
/*  643:     */    }
/*  644: 644 */    else if ("text".equals(nodeType))
/*  645:     */    {
/*  646: 646 */      match(24);
/*  647:     */      
/*  648: 648 */      getXPathHandler().startTextNodeStep(axis);
/*  649:     */      
/*  650: 650 */      predicates();
/*  651:     */      
/*  652: 652 */      getXPathHandler().endTextNodeStep();
/*  653:     */    }
/*  654: 654 */    else if ("comment".equals(nodeType))
/*  655:     */    {
/*  656: 656 */      match(24);
/*  657:     */      
/*  658: 658 */      getXPathHandler().startCommentNodeStep(axis);
/*  659:     */      
/*  660: 660 */      predicates();
/*  661:     */      
/*  662: 662 */      getXPathHandler().endCommentNodeStep();
/*  663:     */    }
/*  664:     */    else
/*  665:     */    {
/*  666: 666 */      XPathSyntaxException ex = createSyntaxException("Expected node-type");
/*  667: 667 */      throw ex;
/*  668:     */    }
/*  669:     */  }
/*  670:     */  
/*  671:     */  private void nameTest(int axis) throws SAXPathException
/*  672:     */  {
/*  673: 673 */    String prefix = null;
/*  674: 674 */    String localName = null;
/*  675:     */    
/*  676: 676 */    switch (LA(2))
/*  677:     */    {
/*  679:     */    case 19: 
/*  680: 680 */      switch (LA(1))
/*  681:     */      {
/*  683:     */      case 16: 
/*  684: 684 */        prefix = match(16).getTokenText();
/*  685: 685 */        match(19);
/*  686:     */      }
/*  687:     */      
/*  688:     */      
/*  689:     */      break;
/*  690:     */    }
/*  691:     */    
/*  692:     */    
/*  693: 693 */    switch (LA(1))
/*  694:     */    {
/*  696:     */    case 16: 
/*  697: 697 */      localName = match(16).getTokenText();
/*  698: 698 */      break;
/*  699:     */    
/*  701:     */    case 9: 
/*  702: 702 */      match(9);
/*  703: 703 */      localName = "*";
/*  704:     */    }
/*  705:     */    
/*  706:     */    
/*  708: 708 */    if (prefix == null)
/*  709:     */    {
/*  710: 710 */      prefix = "";
/*  711:     */    }
/*  712:     */    
/*  713: 713 */    getXPathHandler().startNameStep(axis, prefix, localName);
/*  714:     */    
/*  717: 717 */    predicates();
/*  718:     */    
/*  719: 719 */    getXPathHandler().endNameStep();
/*  720:     */  }
/*  721:     */  
/*  722:     */  private void abbrStep() throws SAXPathException
/*  723:     */  {
/*  724: 724 */    switch (LA(1))
/*  725:     */    {
/*  727:     */    case 14: 
/*  728: 728 */      match(14);
/*  729: 729 */      getXPathHandler().startAllNodeStep(11);
/*  730: 730 */      predicates();
/*  731: 731 */      getXPathHandler().endAllNodeStep();
/*  732: 732 */      break;
/*  733:     */    
/*  735:     */    case 15: 
/*  736: 736 */      match(15);
/*  737: 737 */      getXPathHandler().startAllNodeStep(3);
/*  738: 738 */      predicates();
/*  739: 739 */      getXPathHandler().endAllNodeStep();
/*  740:     */    }
/*  741:     */    
/*  742:     */  }
/*  743:     */  
/*  746:     */  private void predicates()
/*  747:     */    throws SAXPathException
/*  748:     */  {
/*  749: 749 */    while (LA(1) == 21)
/*  750:     */    {
/*  751: 751 */      predicate();
/*  752:     */    }
/*  753:     */  }
/*  754:     */  
/*  759:     */  void predicate()
/*  760:     */    throws SAXPathException
/*  761:     */  {
/*  762: 762 */    getXPathHandler().startPredicate();
/*  763:     */    
/*  764: 764 */    match(21);
/*  765:     */    
/*  766: 766 */    predicateExpr();
/*  767:     */    
/*  768: 768 */    match(22);
/*  769:     */    
/*  770: 770 */    getXPathHandler().endPredicate();
/*  771:     */  }
/*  772:     */  
/*  773:     */  private void predicateExpr() throws SAXPathException
/*  774:     */  {
/*  775: 775 */    expr();
/*  776:     */  }
/*  777:     */  
/*  778:     */  private void expr() throws SAXPathException
/*  779:     */  {
/*  780: 780 */    orExpr();
/*  781:     */  }
/*  782:     */  
/*  783:     */  private void orExpr() throws SAXPathException
/*  784:     */  {
/*  785: 785 */    getXPathHandler().startOrExpr();
/*  786:     */    
/*  787: 787 */    andExpr();
/*  788:     */    
/*  789: 789 */    boolean create = false;
/*  790:     */    
/*  791: 791 */    switch (LA(1))
/*  792:     */    {
/*  794:     */    case 28: 
/*  795: 795 */      create = true;
/*  796: 796 */      match(28);
/*  797: 797 */      orExpr();
/*  798:     */    }
/*  799:     */    
/*  800:     */    
/*  802: 802 */    getXPathHandler().endOrExpr(create);
/*  803:     */  }
/*  804:     */  
/*  805:     */  private void andExpr() throws SAXPathException
/*  806:     */  {
/*  807: 807 */    getXPathHandler().startAndExpr();
/*  808:     */    
/*  809: 809 */    equalityExpr();
/*  810:     */    
/*  811: 811 */    boolean create = false;
/*  812:     */    
/*  813: 813 */    switch (LA(1))
/*  814:     */    {
/*  816:     */    case 27: 
/*  817: 817 */      create = true;
/*  818: 818 */      match(27);
/*  819: 819 */      andExpr();
/*  820:     */    }
/*  821:     */    
/*  822:     */    
/*  824: 824 */    getXPathHandler().endAndExpr(create);
/*  825:     */  }
/*  826:     */  
/*  827:     */  private void equalityExpr() throws SAXPathException
/*  828:     */  {
/*  829: 829 */    relationalExpr();
/*  830:     */    
/*  831: 831 */    int la = LA(1);
/*  832: 832 */    while ((la == 1) || (la == 2))
/*  833:     */    {
/*  834: 834 */      switch (la)
/*  835:     */      {
/*  837:     */      case 1: 
/*  838: 838 */        match(1);
/*  839: 839 */        getXPathHandler().startEqualityExpr();
/*  840: 840 */        relationalExpr();
/*  841: 841 */        getXPathHandler().endEqualityExpr(1);
/*  842: 842 */        break;
/*  843:     */      
/*  845:     */      case 2: 
/*  846: 846 */        match(2);
/*  847: 847 */        getXPathHandler().startEqualityExpr();
/*  848: 848 */        relationalExpr();
/*  849: 849 */        getXPathHandler().endEqualityExpr(2);
/*  850:     */      }
/*  851:     */      
/*  852:     */      
/*  853: 853 */      la = LA(1);
/*  854:     */    }
/*  855:     */  }
/*  856:     */  
/*  857:     */  private void relationalExpr()
/*  858:     */    throws SAXPathException
/*  859:     */  {
/*  860: 860 */    additiveExpr();
/*  861:     */    
/*  862: 862 */    int la = LA(1);
/*  863:     */    
/*  870: 870 */    while ((la == 3) || (la == 5) || (la == 4) || (la == 6)) {
/*  871: 871 */      switch (la)
/*  872:     */      {
/*  874:     */      case 3: 
/*  875: 875 */        match(3);
/*  876: 876 */        getXPathHandler().startRelationalExpr();
/*  877: 877 */        additiveExpr();
/*  878: 878 */        getXPathHandler().endRelationalExpr(3);
/*  879: 879 */        break;
/*  880:     */      
/*  882:     */      case 5: 
/*  883: 883 */        match(5);
/*  884: 884 */        getXPathHandler().startRelationalExpr();
/*  885: 885 */        additiveExpr();
/*  886: 886 */        getXPathHandler().endRelationalExpr(5);
/*  887: 887 */        break;
/*  888:     */      
/*  890:     */      case 6: 
/*  891: 891 */        match(6);
/*  892: 892 */        getXPathHandler().startRelationalExpr();
/*  893: 893 */        additiveExpr();
/*  894: 894 */        getXPathHandler().endRelationalExpr(6);
/*  895: 895 */        break;
/*  896:     */      
/*  898:     */      case 4: 
/*  899: 899 */        match(4);
/*  900: 900 */        getXPathHandler().startRelationalExpr();
/*  901: 901 */        additiveExpr();
/*  902: 902 */        getXPathHandler().endRelationalExpr(4);
/*  903:     */      }
/*  904:     */      
/*  905:     */      
/*  906: 906 */      la = LA(1);
/*  907:     */    }
/*  908:     */  }
/*  909:     */  
/*  910:     */  private void additiveExpr()
/*  911:     */    throws SAXPathException
/*  912:     */  {
/*  913: 913 */    multiplicativeExpr();
/*  914:     */    
/*  915: 915 */    int la = LA(1);
/*  916: 916 */    while ((la == 7) || (la == 8))
/*  917:     */    {
/*  918: 918 */      switch (la)
/*  919:     */      {
/*  921:     */      case 7: 
/*  922: 922 */        match(7);
/*  923: 923 */        getXPathHandler().startAdditiveExpr();
/*  924: 924 */        multiplicativeExpr();
/*  925: 925 */        getXPathHandler().endAdditiveExpr(7);
/*  926: 926 */        break;
/*  927:     */      
/*  929:     */      case 8: 
/*  930: 930 */        match(8);
/*  931: 931 */        getXPathHandler().startAdditiveExpr();
/*  932: 932 */        multiplicativeExpr();
/*  933: 933 */        getXPathHandler().endAdditiveExpr(8);
/*  934:     */      }
/*  935:     */      
/*  936:     */      
/*  937: 937 */      la = LA(1);
/*  938:     */    }
/*  939:     */  }
/*  940:     */  
/*  941:     */  private void multiplicativeExpr() throws SAXPathException
/*  942:     */  {
/*  943: 943 */    unaryExpr();
/*  944:     */    
/*  945: 945 */    int la = LA(1);
/*  946: 946 */    while ((la == 31) || (la == 11) || (la == 10))
/*  947:     */    {
/*  948: 948 */      switch (la)
/*  949:     */      {
/*  951:     */      case 9: 
/*  952:     */      case 31: 
/*  953: 953 */        match(31);
/*  954: 954 */        getXPathHandler().startMultiplicativeExpr();
/*  955: 955 */        unaryExpr();
/*  956: 956 */        getXPathHandler().endMultiplicativeExpr(9);
/*  957: 957 */        break;
/*  958:     */      
/*  960:     */      case 11: 
/*  961: 961 */        match(11);
/*  962: 962 */        getXPathHandler().startMultiplicativeExpr();
/*  963: 963 */        unaryExpr();
/*  964: 964 */        getXPathHandler().endMultiplicativeExpr(11);
/*  965: 965 */        break;
/*  966:     */      
/*  968:     */      case 10: 
/*  969: 969 */        match(10);
/*  970: 970 */        getXPathHandler().startMultiplicativeExpr();
/*  971: 971 */        unaryExpr();
/*  972: 972 */        getXPathHandler().endMultiplicativeExpr(10);
/*  973:     */      }
/*  974:     */      
/*  975:     */      
/*  976: 976 */      la = LA(1);
/*  977:     */    }
/*  978:     */  }
/*  979:     */  
/*  980:     */  private void unaryExpr()
/*  981:     */    throws SAXPathException
/*  982:     */  {
/*  983: 983 */    switch (LA(1))
/*  984:     */    {
/*  986:     */    case 8: 
/*  987: 987 */      getXPathHandler().startUnaryExpr();
/*  988: 988 */      match(8);
/*  989: 989 */      unaryExpr();
/*  990: 990 */      getXPathHandler().endUnaryExpr(12);
/*  991: 991 */      break;
/*  992:     */    
/*  994:     */    default: 
/*  995: 995 */      unionExpr();
/*  996:     */    }
/*  997:     */    
/*  998:     */  }
/*  999:     */  
/* 1002:     */  private void unionExpr()
/* 1003:     */    throws SAXPathException
/* 1004:     */  {
/* 1005:1005 */    getXPathHandler().startUnionExpr();
/* 1006:     */    
/* 1007:1007 */    pathExpr();
/* 1008:     */    
/* 1009:1009 */    boolean create = false;
/* 1010:     */    
/* 1011:1011 */    switch (LA(1))
/* 1012:     */    {
/* 1014:     */    case 18: 
/* 1015:1015 */      match(18);
/* 1016:1016 */      create = true;
/* 1017:1017 */      expr();
/* 1018:     */    }
/* 1019:     */    
/* 1020:     */    
/* 1022:1022 */    getXPathHandler().endUnionExpr(create);
/* 1023:     */  }
/* 1024:     */  
/* 1025:     */  private Token match(int tokenType) throws XPathSyntaxException
/* 1026:     */  {
/* 1027:1027 */    LT(1);
/* 1028:     */    
/* 1029:1029 */    Token token = (Token)this.tokens.get(0);
/* 1030:     */    
/* 1031:1031 */    if (token.getTokenType() == tokenType)
/* 1032:     */    {
/* 1033:1033 */      this.tokens.remove(0);
/* 1034:1034 */      return token;
/* 1035:     */    }
/* 1036:     */    
/* 1038:1038 */    XPathSyntaxException ex = createSyntaxException("Expected: " + TokenTypes.getTokenText(tokenType));
/* 1039:1039 */    throw ex;
/* 1040:     */  }
/* 1041:     */  
/* 1042:     */  private int LA(int position)
/* 1043:     */  {
/* 1044:1044 */    return LT(position).getTokenType();
/* 1045:     */  }
/* 1046:     */  
/* 1049:     */  private Token LT(int position)
/* 1050:     */  {
/* 1051:1051 */    if (this.tokens.size() <= position - 1)
/* 1052:     */    {
/* 1053:1053 */      for (int i = 0; i < position; i++)
/* 1054:     */      {
/* 1055:1055 */        this.tokens.add(this.lexer.nextToken());
/* 1056:     */      }
/* 1057:     */    }
/* 1058:     */    
/* 1059:1059 */    return (Token)this.tokens.get(position - 1);
/* 1060:     */  }
/* 1061:     */  
/* 1062:     */  private boolean isNodeTypeName(Token name)
/* 1063:     */  {
/* 1064:1064 */    String text = name.getTokenText();
/* 1065:     */    
/* 1066:1066 */    if (("node".equals(text)) || ("comment".equals(text)) || ("text".equals(text)) || ("processing-instruction".equals(text)))
/* 1067:     */    {
/* 1074:1074 */      return true;
/* 1075:     */    }
/* 1076:     */    
/* 1077:1077 */    return false;
/* 1078:     */  }
/* 1079:     */  
/* 1080:     */  private XPathSyntaxException createSyntaxException(String message)
/* 1081:     */  {
/* 1082:1082 */    String xpath = this.lexer.getXPath();
/* 1083:1083 */    int position = LT(1).getTokenBegin();
/* 1084:     */    
/* 1085:1085 */    return new XPathSyntaxException(xpath, position, message);
/* 1086:     */  }
/* 1087:     */  
/* 1089:     */  private void throwInvalidAxis(String invalidAxis)
/* 1090:     */    throws SAXPathException
/* 1091:     */  {
/* 1092:1092 */    String xpath = this.lexer.getXPath();
/* 1093:1093 */    int position = LT(1).getTokenBegin();
/* 1094:     */    
/* 1095:1095 */    String message = "Expected valid axis name instead of [" + invalidAxis + "]";
/* 1096:     */    
/* 1097:1097 */    throw new XPathSyntaxException(xpath, position, message);
/* 1098:     */  }
/* 1099:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.base.XPathReader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */