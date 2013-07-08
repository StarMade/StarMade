/*   1:    */package org.jaxen.saxpath.base;
/*   2:    */
/*  15:    */class XPathLexer
/*  16:    */{
/*  17:    */  private String xpath;
/*  18:    */  
/*  30:    */  private int currentPosition;
/*  31:    */  
/*  43:    */  private int endPosition;
/*  44:    */  
/*  56: 56 */  private boolean expectOperator = false;
/*  57:    */  
/*  58:    */  XPathLexer(String xpath)
/*  59:    */  {
/*  60: 60 */    setXPath(xpath);
/*  61:    */  }
/*  62:    */  
/*  63:    */  private void setXPath(String xpath)
/*  64:    */  {
/*  65: 65 */    this.xpath = xpath;
/*  66: 66 */    this.currentPosition = 0;
/*  67: 67 */    this.endPosition = xpath.length();
/*  68:    */  }
/*  69:    */  
/*  70:    */  String getXPath()
/*  71:    */  {
/*  72: 72 */    return this.xpath;
/*  73:    */  }
/*  74:    */  
/*  75:    */  Token nextToken()
/*  76:    */  {
/*  77: 77 */    Token token = null;
/*  78:    */    
/*  79:    */    do
/*  80:    */    {
/*  81: 81 */      token = null;
/*  82:    */      
/*  83: 83 */      switch (LA(1))
/*  84:    */      {
/*  86:    */      case '$': 
/*  87: 87 */        token = dollar();
/*  88: 88 */        break;
/*  89:    */      
/*  92:    */      case '"': 
/*  93:    */      case '\'': 
/*  94: 94 */        token = literal();
/*  95: 95 */        break;
/*  96:    */      
/*  99:    */      case '/': 
/* 100:100 */        token = slashes();
/* 101:101 */        break;
/* 102:    */      
/* 105:    */      case ',': 
/* 106:106 */        token = comma();
/* 107:107 */        break;
/* 108:    */      
/* 111:    */      case '(': 
/* 112:112 */        token = leftParen();
/* 113:113 */        break;
/* 114:    */      
/* 117:    */      case ')': 
/* 118:118 */        token = rightParen();
/* 119:119 */        break;
/* 120:    */      
/* 123:    */      case '[': 
/* 124:124 */        token = leftBracket();
/* 125:125 */        break;
/* 126:    */      
/* 129:    */      case ']': 
/* 130:130 */        token = rightBracket();
/* 131:131 */        break;
/* 132:    */      
/* 135:    */      case '+': 
/* 136:136 */        token = plus();
/* 137:137 */        break;
/* 138:    */      
/* 141:    */      case '-': 
/* 142:142 */        token = minus();
/* 143:143 */        break;
/* 144:    */      
/* 147:    */      case '<': 
/* 148:    */      case '>': 
/* 149:149 */        token = relationalOperator();
/* 150:150 */        break;
/* 151:    */      
/* 154:    */      case '=': 
/* 155:155 */        token = equals();
/* 156:156 */        break;
/* 157:    */      
/* 160:    */      case '!': 
/* 161:161 */        if (LA(2) == '=')
/* 162:    */        {
/* 163:163 */          token = notEquals(); } break;
/* 164:    */      
/* 169:    */      case '|': 
/* 170:170 */        token = pipe();
/* 171:171 */        break;
/* 172:    */      
/* 175:    */      case '@': 
/* 176:176 */        token = at();
/* 177:177 */        break;
/* 178:    */      
/* 181:    */      case ':': 
/* 182:182 */        if (LA(2) == ':')
/* 183:    */        {
/* 184:184 */          token = doubleColon();
/* 185:    */        }
/* 186:    */        else
/* 187:    */        {
/* 188:188 */          token = colon();
/* 189:    */        }
/* 190:190 */        break;
/* 191:    */      
/* 194:    */      case '*': 
/* 195:195 */        token = star();
/* 196:196 */        break;
/* 197:    */      
/* 200:    */      case '.': 
/* 201:201 */        switch (LA(2))
/* 202:    */        {
/* 204:    */        case '0': 
/* 205:    */        case '1': 
/* 206:    */        case '2': 
/* 207:    */        case '3': 
/* 208:    */        case '4': 
/* 209:    */        case '5': 
/* 210:    */        case '6': 
/* 211:    */        case '7': 
/* 212:    */        case '8': 
/* 213:    */        case '9': 
/* 214:214 */          token = number();
/* 215:215 */          break;
/* 216:    */        
/* 218:    */        default: 
/* 219:219 */          token = dots(); }
/* 220:220 */        break;
/* 221:    */      
/* 227:    */      case '0': 
/* 228:    */      case '1': 
/* 229:    */      case '2': 
/* 230:    */      case '3': 
/* 231:    */      case '4': 
/* 232:    */      case '5': 
/* 233:    */      case '6': 
/* 234:    */      case '7': 
/* 235:    */      case '8': 
/* 236:    */      case '9': 
/* 237:237 */        token = number();
/* 238:238 */        break;
/* 239:    */      
/* 242:    */      case '\t': 
/* 243:    */      case '\n': 
/* 244:    */      case '\r': 
/* 245:    */      case ' ': 
/* 246:246 */        token = whitespace();
/* 247:247 */        break;
/* 248:    */      case '\013': case '\f': case '\016': case '\017': case '\020': case '\021': case '\022': case '\023': case '\024': case '\025': case '\026': case '\027': case '\030': case '\031': case '\032': case '\033': case '\034': case '\035': case '\036': case '\037': case '#': 
/* 249:    */      case '%': case '&': case ';': case '?': case 'A': case 'B': case 'C': case 'D': case 'E': case 'F': case 'G': case 'H': case 'I': case 'J': case 'K': case 'L': case 'M': case 'N': case 'O': case 'P': case 'Q': 
/* 250:    */      case 'R': case 'S': case 'T': case 'U': case 'V': case 'W': case 'X': case 'Y': case 'Z': case '\\': case '^': case '_': case '`': case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': case 'h': 
/* 251:    */      case 'i': case 'j': case 'k': case 'l': case 'm': case 'n': case 'o': case 'p': case 'q': case 'r': case 's': case 't': case 'u': case 'v': case 'w': case 'x': case 'y': case 'z': case '{': default: 
/* 252:252 */        if (Verifier.isXMLNCNameStartCharacter(LA(1)))
/* 253:    */        {
/* 254:254 */          token = identifierOrOperatorName();
/* 255:    */        }
/* 256:    */        break;
/* 257:    */      }
/* 258:    */      
/* 259:259 */      if (token == null)
/* 260:    */      {
/* 261:261 */        if (!hasMoreChars())
/* 262:    */        {
/* 263:263 */          token = new Token(-1, getXPath(), this.currentPosition, this.endPosition);
/* 266:    */        }
/* 267:    */        else
/* 268:    */        {
/* 270:270 */          token = new Token(-3, getXPath(), this.currentPosition, this.endPosition);
/* 272:    */        }
/* 273:    */        
/* 275:    */      }
/* 276:    */      
/* 277:    */    }
/* 278:278 */    while (token.getTokenType() == -2);
/* 279:    */    
/* 291:291 */    switch (token.getTokenType())
/* 292:    */    {
/* 294:    */    case 1: 
/* 295:    */    case 2: 
/* 296:    */    case 3: 
/* 297:    */    case 4: 
/* 298:    */    case 5: 
/* 299:    */    case 6: 
/* 300:    */    case 7: 
/* 301:    */    case 8: 
/* 302:    */    case 10: 
/* 303:    */    case 11: 
/* 304:    */    case 12: 
/* 305:    */    case 13: 
/* 306:    */    case 17: 
/* 307:    */    case 18: 
/* 308:    */    case 19: 
/* 309:    */    case 20: 
/* 310:    */    case 21: 
/* 311:    */    case 23: 
/* 312:    */    case 25: 
/* 313:    */    case 27: 
/* 314:    */    case 28: 
/* 315:    */    case 30: 
/* 316:    */    case 31: 
/* 317:317 */      this.expectOperator = false;
/* 318:318 */      break;
/* 319:    */    case 9: case 14: case 15: 
/* 320:    */    case 16: case 22: case 24: 
/* 321:    */    case 26: case 29: default: 
/* 322:322 */      this.expectOperator = true;
/* 323:    */    }
/* 324:    */    
/* 325:    */    
/* 327:327 */    return token;
/* 328:    */  }
/* 329:    */  
/* 330:    */  private Token identifierOrOperatorName()
/* 331:    */  {
/* 332:332 */    Token token = null;
/* 333:333 */    if (this.expectOperator) {
/* 334:334 */      token = operatorName();
/* 335:    */    } else {
/* 336:336 */      token = identifier();
/* 337:    */    }
/* 338:338 */    return token;
/* 339:    */  }
/* 340:    */  
/* 341:    */  private Token identifier()
/* 342:    */  {
/* 343:343 */    Token token = null;
/* 344:    */    
/* 345:345 */    int start = this.currentPosition;
/* 346:    */    
/* 347:347 */    while (hasMoreChars())
/* 348:    */    {
/* 349:349 */      if (!Verifier.isXMLNCNameCharacter(LA(1)))
/* 350:    */        break;
/* 351:351 */      consume();
/* 352:    */    }
/* 353:    */    
/* 359:359 */    token = new Token(16, getXPath(), start, this.currentPosition);
/* 360:    */    
/* 364:364 */    return token;
/* 365:    */  }
/* 366:    */  
/* 367:    */  private Token operatorName()
/* 368:    */  {
/* 369:369 */    Token token = null;
/* 370:    */    
/* 371:371 */    switch (LA(1))
/* 372:    */    {
/* 374:    */    case 'a': 
/* 375:375 */      token = and();
/* 376:376 */      break;
/* 377:    */    
/* 380:    */    case 'o': 
/* 381:381 */      token = or();
/* 382:382 */      break;
/* 383:    */    
/* 386:    */    case 'm': 
/* 387:387 */      token = mod();
/* 388:388 */      break;
/* 389:    */    
/* 392:    */    case 'd': 
/* 393:393 */      token = div();
/* 394:    */    }
/* 395:    */    
/* 396:    */    
/* 398:398 */    return token;
/* 399:    */  }
/* 400:    */  
/* 401:    */  private Token mod()
/* 402:    */  {
/* 403:403 */    Token token = null;
/* 404:    */    
/* 405:405 */    if ((LA(1) == 'm') && (LA(2) == 'o') && (LA(3) == 'd'))
/* 406:    */    {
/* 412:412 */      token = new Token(10, getXPath(), this.currentPosition, this.currentPosition + 3);
/* 413:    */      
/* 417:417 */      consume();
/* 418:418 */      consume();
/* 419:419 */      consume();
/* 420:    */    }
/* 421:    */    
/* 422:422 */    return token;
/* 423:    */  }
/* 424:    */  
/* 425:    */  private Token div()
/* 426:    */  {
/* 427:427 */    Token token = null;
/* 428:    */    
/* 429:429 */    if ((LA(1) == 'd') && (LA(2) == 'i') && (LA(3) == 'v'))
/* 430:    */    {
/* 436:436 */      token = new Token(11, getXPath(), this.currentPosition, this.currentPosition + 3);
/* 437:    */      
/* 441:441 */      consume();
/* 442:442 */      consume();
/* 443:443 */      consume();
/* 444:    */    }
/* 445:    */    
/* 446:446 */    return token;
/* 447:    */  }
/* 448:    */  
/* 449:    */  private Token and()
/* 450:    */  {
/* 451:451 */    Token token = null;
/* 452:    */    
/* 453:453 */    if ((LA(1) == 'a') && (LA(2) == 'n') && (LA(3) == 'd'))
/* 454:    */    {
/* 460:460 */      token = new Token(27, getXPath(), this.currentPosition, this.currentPosition + 3);
/* 461:    */      
/* 465:465 */      consume();
/* 466:466 */      consume();
/* 467:467 */      consume();
/* 468:    */    }
/* 469:    */    
/* 470:470 */    return token;
/* 471:    */  }
/* 472:    */  
/* 473:    */  private Token or()
/* 474:    */  {
/* 475:475 */    Token token = null;
/* 476:    */    
/* 477:477 */    if ((LA(1) == 'o') && (LA(2) == 'r'))
/* 478:    */    {
/* 482:482 */      token = new Token(28, getXPath(), this.currentPosition, this.currentPosition + 2);
/* 483:    */      
/* 487:487 */      consume();
/* 488:488 */      consume();
/* 489:    */    }
/* 490:    */    
/* 491:491 */    return token;
/* 492:    */  }
/* 493:    */  
/* 494:    */  private Token number()
/* 495:    */  {
/* 496:496 */    int start = this.currentPosition;
/* 497:497 */    boolean periodAllowed = true;
/* 498:    */    
/* 500:    */    for (;;)
/* 501:    */    {
/* 502:502 */      switch (LA(1))
/* 503:    */      {
/* 504:    */      case '.': 
/* 505:505 */        if (!periodAllowed)
/* 506:    */          break label99;
/* 507:507 */        periodAllowed = false;
/* 508:508 */        consume();break;
/* 509:    */      
/* 515:    */      case '0': 
/* 516:    */      case '1': 
/* 517:    */      case '2': 
/* 518:    */      case '3': 
/* 519:    */      case '4': 
/* 520:    */      case '5': 
/* 521:    */      case '6': 
/* 522:    */      case '7': 
/* 523:    */      case '8': 
/* 524:    */      case '9': 
/* 525:525 */        consume();
/* 526:    */      }
/* 527:    */      
/* 528:    */    }
/* 529:    */    
/* 530:    */    label99:
/* 531:    */    
/* 532:532 */    return new Token(29, getXPath(), start, this.currentPosition);
/* 533:    */  }
/* 534:    */  
/* 538:    */  private Token whitespace()
/* 539:    */  {
/* 540:540 */    consume();
/* 541:    */    
/* 543:543 */    while (hasMoreChars())
/* 544:    */    {
/* 545:545 */      switch (LA(1))
/* 546:    */      {
/* 548:    */      case '\t': 
/* 549:    */      case '\n': 
/* 550:    */      case '\r': 
/* 551:    */      case ' ': 
/* 552:552 */        consume();
/* 553:553 */        break;
/* 554:    */      }
/* 555:    */      
/* 556:    */    }
/* 557:    */    
/* 563:563 */    return new Token(-2, getXPath(), 0, 0);
/* 564:    */  }
/* 565:    */  
/* 569:    */  private Token comma()
/* 570:    */  {
/* 571:571 */    Token token = new Token(30, getXPath(), this.currentPosition, this.currentPosition + 1);
/* 572:    */    
/* 576:576 */    consume();
/* 577:    */    
/* 578:578 */    return token;
/* 579:    */  }
/* 580:    */  
/* 581:    */  private Token equals()
/* 582:    */  {
/* 583:583 */    Token token = new Token(1, getXPath(), this.currentPosition, this.currentPosition + 1);
/* 584:    */    
/* 588:588 */    consume();
/* 589:    */    
/* 590:590 */    return token;
/* 591:    */  }
/* 592:    */  
/* 593:    */  private Token minus()
/* 594:    */  {
/* 595:595 */    Token token = new Token(8, getXPath(), this.currentPosition, this.currentPosition + 1);
/* 596:    */    
/* 599:599 */    consume();
/* 600:    */    
/* 601:601 */    return token;
/* 602:    */  }
/* 603:    */  
/* 604:    */  private Token plus()
/* 605:    */  {
/* 606:606 */    Token token = new Token(7, getXPath(), this.currentPosition, this.currentPosition + 1);
/* 607:    */    
/* 610:610 */    consume();
/* 611:    */    
/* 612:612 */    return token;
/* 613:    */  }
/* 614:    */  
/* 615:    */  private Token dollar()
/* 616:    */  {
/* 617:617 */    Token token = new Token(25, getXPath(), this.currentPosition, this.currentPosition + 1);
/* 618:    */    
/* 621:621 */    consume();
/* 622:    */    
/* 623:623 */    return token;
/* 624:    */  }
/* 625:    */  
/* 626:    */  private Token pipe()
/* 627:    */  {
/* 628:628 */    Token token = new Token(18, getXPath(), this.currentPosition, this.currentPosition + 1);
/* 629:    */    
/* 633:633 */    consume();
/* 634:    */    
/* 635:635 */    return token;
/* 636:    */  }
/* 637:    */  
/* 638:    */  private Token at()
/* 639:    */  {
/* 640:640 */    Token token = new Token(17, getXPath(), this.currentPosition, this.currentPosition + 1);
/* 641:    */    
/* 645:645 */    consume();
/* 646:    */    
/* 647:647 */    return token;
/* 648:    */  }
/* 649:    */  
/* 650:    */  private Token colon()
/* 651:    */  {
/* 652:652 */    Token token = new Token(19, getXPath(), this.currentPosition, this.currentPosition + 1);
/* 653:    */    
/* 656:656 */    consume();
/* 657:    */    
/* 658:658 */    return token;
/* 659:    */  }
/* 660:    */  
/* 661:    */  private Token doubleColon()
/* 662:    */  {
/* 663:663 */    Token token = new Token(20, getXPath(), this.currentPosition, this.currentPosition + 2);
/* 664:    */    
/* 668:668 */    consume();
/* 669:669 */    consume();
/* 670:    */    
/* 671:671 */    return token;
/* 672:    */  }
/* 673:    */  
/* 674:    */  private Token notEquals()
/* 675:    */  {
/* 676:676 */    Token token = new Token(2, getXPath(), this.currentPosition, this.currentPosition + 2);
/* 677:    */    
/* 681:681 */    consume();
/* 682:682 */    consume();
/* 683:    */    
/* 684:684 */    return token;
/* 685:    */  }
/* 686:    */  
/* 687:    */  private Token relationalOperator()
/* 688:    */  {
/* 689:689 */    Token token = null;
/* 690:    */    
/* 691:691 */    switch (LA(1))
/* 692:    */    {
/* 694:    */    case '<': 
/* 695:695 */      if (LA(2) == '=')
/* 696:    */      {
/* 697:697 */        token = new Token(4, getXPath(), this.currentPosition, this.currentPosition + 2);
/* 698:    */        
/* 701:701 */        consume();
/* 702:    */      }
/* 703:    */      else
/* 704:    */      {
/* 705:705 */        token = new Token(3, getXPath(), this.currentPosition, this.currentPosition + 1);
/* 706:    */      }
/* 707:    */      
/* 711:711 */      consume();
/* 712:712 */      break;
/* 713:    */    
/* 715:    */    case '>': 
/* 716:716 */      if (LA(2) == '=')
/* 717:    */      {
/* 718:718 */        token = new Token(6, getXPath(), this.currentPosition, this.currentPosition + 2);
/* 719:    */        
/* 722:722 */        consume();
/* 723:    */      }
/* 724:    */      else
/* 725:    */      {
/* 726:726 */        token = new Token(5, getXPath(), this.currentPosition, this.currentPosition + 1);
/* 727:    */      }
/* 728:    */      
/* 732:732 */      consume();
/* 733:    */    }
/* 734:    */    
/* 735:    */    
/* 737:737 */    return token;
/* 738:    */  }
/* 739:    */  
/* 742:    */  private Token star()
/* 743:    */  {
/* 744:744 */    int tokenType = this.expectOperator ? 31 : 9;
/* 745:745 */    Token token = new Token(tokenType, getXPath(), this.currentPosition, this.currentPosition + 1);
/* 746:    */    
/* 750:750 */    consume();
/* 751:    */    
/* 752:752 */    return token;
/* 753:    */  }
/* 754:    */  
/* 755:    */  private Token literal()
/* 756:    */  {
/* 757:757 */    Token token = null;
/* 758:    */    
/* 759:759 */    char match = LA(1);
/* 760:    */    
/* 761:761 */    consume();
/* 762:    */    
/* 763:763 */    int start = this.currentPosition;
/* 764:    */    
/* 766:766 */    while ((token == null) && (hasMoreChars()))
/* 767:    */    {
/* 769:769 */      if (LA(1) == match)
/* 770:    */      {
/* 771:771 */        token = new Token(26, getXPath(), start, this.currentPosition);
/* 772:    */      }
/* 773:    */      
/* 776:776 */      consume();
/* 777:    */    }
/* 778:    */    
/* 779:779 */    return token;
/* 780:    */  }
/* 781:    */  
/* 782:    */  private Token dots()
/* 783:    */  {
/* 784:784 */    Token token = null;
/* 785:    */    
/* 786:786 */    switch (LA(2))
/* 787:    */    {
/* 789:    */    case '.': 
/* 790:790 */      token = new Token(15, getXPath(), this.currentPosition, this.currentPosition + 2);
/* 791:    */      
/* 794:794 */      consume();
/* 795:795 */      consume();
/* 796:796 */      break;
/* 797:    */    
/* 799:    */    default: 
/* 800:800 */      token = new Token(14, getXPath(), this.currentPosition, this.currentPosition + 1);
/* 801:    */      
/* 804:804 */      consume();
/* 805:    */    }
/* 806:    */    
/* 807:    */    
/* 809:809 */    return token;
/* 810:    */  }
/* 811:    */  
/* 812:    */  private Token leftBracket()
/* 813:    */  {
/* 814:814 */    Token token = new Token(21, getXPath(), this.currentPosition, this.currentPosition + 1);
/* 815:    */    
/* 819:819 */    consume();
/* 820:    */    
/* 821:821 */    return token;
/* 822:    */  }
/* 823:    */  
/* 824:    */  private Token rightBracket()
/* 825:    */  {
/* 826:826 */    Token token = new Token(22, getXPath(), this.currentPosition, this.currentPosition + 1);
/* 827:    */    
/* 831:831 */    consume();
/* 832:    */    
/* 833:833 */    return token;
/* 834:    */  }
/* 835:    */  
/* 836:    */  private Token leftParen()
/* 837:    */  {
/* 838:838 */    Token token = new Token(23, getXPath(), this.currentPosition, this.currentPosition + 1);
/* 839:    */    
/* 843:843 */    consume();
/* 844:    */    
/* 845:845 */    return token;
/* 846:    */  }
/* 847:    */  
/* 848:    */  private Token rightParen()
/* 849:    */  {
/* 850:850 */    Token token = new Token(24, getXPath(), this.currentPosition, this.currentPosition + 1);
/* 851:    */    
/* 855:855 */    consume();
/* 856:    */    
/* 857:857 */    return token;
/* 858:    */  }
/* 859:    */  
/* 860:    */  private Token slashes()
/* 861:    */  {
/* 862:862 */    Token token = null;
/* 863:    */    
/* 864:864 */    switch (LA(2))
/* 865:    */    {
/* 867:    */    case '/': 
/* 868:868 */      token = new Token(13, getXPath(), this.currentPosition, this.currentPosition + 2);
/* 869:    */      
/* 872:872 */      consume();
/* 873:873 */      consume();
/* 874:874 */      break;
/* 875:    */    
/* 877:    */    default: 
/* 878:878 */      token = new Token(12, getXPath(), this.currentPosition, this.currentPosition + 1);
/* 879:    */      
/* 882:882 */      consume();
/* 883:    */    }
/* 884:    */    
/* 885:    */    
/* 886:886 */    return token;
/* 887:    */  }
/* 888:    */  
/* 889:    */  private char LA(int i)
/* 890:    */  {
/* 891:891 */    if (this.currentPosition + (i - 1) >= this.endPosition)
/* 892:    */    {
/* 893:893 */      return 65535;
/* 894:    */    }
/* 895:    */    
/* 896:896 */    return getXPath().charAt(this.currentPosition + (i - 1));
/* 897:    */  }
/* 898:    */  
/* 899:    */  private void consume()
/* 900:    */  {
/* 901:901 */    this.currentPosition += 1;
/* 902:    */  }
/* 903:    */  
/* 904:    */  private boolean hasMoreChars()
/* 905:    */  {
/* 906:906 */    return this.currentPosition < this.endPosition;
/* 907:    */  }
/* 908:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.base.XPathLexer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */