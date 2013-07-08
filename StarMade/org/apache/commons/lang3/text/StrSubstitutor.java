/*   1:    */package org.apache.commons.lang3.text;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Enumeration;
/*   5:    */import java.util.HashMap;
/*   6:    */import java.util.List;
/*   7:    */import java.util.Map;
/*   8:    */import java.util.Properties;
/*   9:    */
/* 109:    */public class StrSubstitutor
/* 110:    */{
/* 111:    */  public static final char DEFAULT_ESCAPE = '$';
/* 112:112 */  public static final StrMatcher DEFAULT_PREFIX = StrMatcher.stringMatcher("${");
/* 113:    */  
/* 116:116 */  public static final StrMatcher DEFAULT_SUFFIX = StrMatcher.stringMatcher("}");
/* 117:    */  
/* 122:    */  private char escapeChar;
/* 123:    */  
/* 127:    */  private StrMatcher prefixMatcher;
/* 128:    */  
/* 132:    */  private StrMatcher suffixMatcher;
/* 133:    */  
/* 137:    */  private StrLookup<?> variableResolver;
/* 138:    */  
/* 142:    */  private boolean enableSubstitutionInVariables;
/* 143:    */  
/* 148:    */  public static <V> String replace(Object source, Map<String, V> valueMap)
/* 149:    */  {
/* 150:150 */    return new StrSubstitutor(valueMap).replace(source);
/* 151:    */  }
/* 152:    */  
/* 165:    */  public static <V> String replace(Object source, Map<String, V> valueMap, String prefix, String suffix)
/* 166:    */  {
/* 167:167 */    return new StrSubstitutor(valueMap, prefix, suffix).replace(source);
/* 168:    */  }
/* 169:    */  
/* 177:    */  public static String replace(Object source, Properties valueProperties)
/* 178:    */  {
/* 179:179 */    if (valueProperties == null) {
/* 180:180 */      return source.toString();
/* 181:    */    }
/* 182:182 */    Map<String, String> valueMap = new HashMap();
/* 183:183 */    Enumeration<?> propNames = valueProperties.propertyNames();
/* 184:184 */    while (propNames.hasMoreElements()) {
/* 185:185 */      String propName = (String)propNames.nextElement();
/* 186:186 */      String propValue = valueProperties.getProperty(propName);
/* 187:187 */      valueMap.put(propName, propValue);
/* 188:    */    }
/* 189:189 */    return replace(source, valueMap);
/* 190:    */  }
/* 191:    */  
/* 198:    */  public static String replaceSystemProperties(Object source)
/* 199:    */  {
/* 200:200 */    return new StrSubstitutor(StrLookup.systemPropertiesLookup()).replace(source);
/* 201:    */  }
/* 202:    */  
/* 207:    */  public StrSubstitutor()
/* 208:    */  {
/* 209:209 */    this((StrLookup)null, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
/* 210:    */  }
/* 211:    */  
/* 218:    */  public <V> StrSubstitutor(Map<String, V> valueMap)
/* 219:    */  {
/* 220:220 */    this(StrLookup.mapLookup(valueMap), DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
/* 221:    */  }
/* 222:    */  
/* 231:    */  public <V> StrSubstitutor(Map<String, V> valueMap, String prefix, String suffix)
/* 232:    */  {
/* 233:233 */    this(StrLookup.mapLookup(valueMap), prefix, suffix, '$');
/* 234:    */  }
/* 235:    */  
/* 245:    */  public <V> StrSubstitutor(Map<String, V> valueMap, String prefix, String suffix, char escape)
/* 246:    */  {
/* 247:247 */    this(StrLookup.mapLookup(valueMap), prefix, suffix, escape);
/* 248:    */  }
/* 249:    */  
/* 254:    */  public StrSubstitutor(StrLookup<?> variableResolver)
/* 255:    */  {
/* 256:256 */    this(variableResolver, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
/* 257:    */  }
/* 258:    */  
/* 267:    */  public StrSubstitutor(StrLookup<?> variableResolver, String prefix, String suffix, char escape)
/* 268:    */  {
/* 269:269 */    setVariableResolver(variableResolver);
/* 270:270 */    setVariablePrefix(prefix);
/* 271:271 */    setVariableSuffix(suffix);
/* 272:272 */    setEscapeChar(escape);
/* 273:    */  }
/* 274:    */  
/* 284:    */  public StrSubstitutor(StrLookup<?> variableResolver, StrMatcher prefixMatcher, StrMatcher suffixMatcher, char escape)
/* 285:    */  {
/* 286:286 */    setVariableResolver(variableResolver);
/* 287:287 */    setVariablePrefixMatcher(prefixMatcher);
/* 288:288 */    setVariableSuffixMatcher(suffixMatcher);
/* 289:289 */    setEscapeChar(escape);
/* 290:    */  }
/* 291:    */  
/* 299:    */  public String replace(String source)
/* 300:    */  {
/* 301:301 */    if (source == null) {
/* 302:302 */      return null;
/* 303:    */    }
/* 304:304 */    StrBuilder buf = new StrBuilder(source);
/* 305:305 */    if (!substitute(buf, 0, source.length())) {
/* 306:306 */      return source;
/* 307:    */    }
/* 308:308 */    return buf.toString();
/* 309:    */  }
/* 310:    */  
/* 322:    */  public String replace(String source, int offset, int length)
/* 323:    */  {
/* 324:324 */    if (source == null) {
/* 325:325 */      return null;
/* 326:    */    }
/* 327:327 */    StrBuilder buf = new StrBuilder(length).append(source, offset, length);
/* 328:328 */    if (!substitute(buf, 0, length)) {
/* 329:329 */      return source.substring(offset, offset + length);
/* 330:    */    }
/* 331:331 */    return buf.toString();
/* 332:    */  }
/* 333:    */  
/* 342:    */  public String replace(char[] source)
/* 343:    */  {
/* 344:344 */    if (source == null) {
/* 345:345 */      return null;
/* 346:    */    }
/* 347:347 */    StrBuilder buf = new StrBuilder(source.length).append(source);
/* 348:348 */    substitute(buf, 0, source.length);
/* 349:349 */    return buf.toString();
/* 350:    */  }
/* 351:    */  
/* 364:    */  public String replace(char[] source, int offset, int length)
/* 365:    */  {
/* 366:366 */    if (source == null) {
/* 367:367 */      return null;
/* 368:    */    }
/* 369:369 */    StrBuilder buf = new StrBuilder(length).append(source, offset, length);
/* 370:370 */    substitute(buf, 0, length);
/* 371:371 */    return buf.toString();
/* 372:    */  }
/* 373:    */  
/* 382:    */  public String replace(StringBuffer source)
/* 383:    */  {
/* 384:384 */    if (source == null) {
/* 385:385 */      return null;
/* 386:    */    }
/* 387:387 */    StrBuilder buf = new StrBuilder(source.length()).append(source);
/* 388:388 */    substitute(buf, 0, buf.length());
/* 389:389 */    return buf.toString();
/* 390:    */  }
/* 391:    */  
/* 404:    */  public String replace(StringBuffer source, int offset, int length)
/* 405:    */  {
/* 406:406 */    if (source == null) {
/* 407:407 */      return null;
/* 408:    */    }
/* 409:409 */    StrBuilder buf = new StrBuilder(length).append(source, offset, length);
/* 410:410 */    substitute(buf, 0, length);
/* 411:411 */    return buf.toString();
/* 412:    */  }
/* 413:    */  
/* 422:    */  public String replace(StrBuilder source)
/* 423:    */  {
/* 424:424 */    if (source == null) {
/* 425:425 */      return null;
/* 426:    */    }
/* 427:427 */    StrBuilder buf = new StrBuilder(source.length()).append(source);
/* 428:428 */    substitute(buf, 0, buf.length());
/* 429:429 */    return buf.toString();
/* 430:    */  }
/* 431:    */  
/* 444:    */  public String replace(StrBuilder source, int offset, int length)
/* 445:    */  {
/* 446:446 */    if (source == null) {
/* 447:447 */      return null;
/* 448:    */    }
/* 449:449 */    StrBuilder buf = new StrBuilder(length).append(source, offset, length);
/* 450:450 */    substitute(buf, 0, length);
/* 451:451 */    return buf.toString();
/* 452:    */  }
/* 453:    */  
/* 462:    */  public String replace(Object source)
/* 463:    */  {
/* 464:464 */    if (source == null) {
/* 465:465 */      return null;
/* 466:    */    }
/* 467:467 */    StrBuilder buf = new StrBuilder().append(source);
/* 468:468 */    substitute(buf, 0, buf.length());
/* 469:469 */    return buf.toString();
/* 470:    */  }
/* 471:    */  
/* 480:    */  public boolean replaceIn(StringBuffer source)
/* 481:    */  {
/* 482:482 */    if (source == null) {
/* 483:483 */      return false;
/* 484:    */    }
/* 485:485 */    return replaceIn(source, 0, source.length());
/* 486:    */  }
/* 487:    */  
/* 500:    */  public boolean replaceIn(StringBuffer source, int offset, int length)
/* 501:    */  {
/* 502:502 */    if (source == null) {
/* 503:503 */      return false;
/* 504:    */    }
/* 505:505 */    StrBuilder buf = new StrBuilder(length).append(source, offset, length);
/* 506:506 */    if (!substitute(buf, 0, length)) {
/* 507:507 */      return false;
/* 508:    */    }
/* 509:509 */    source.replace(offset, offset + length, buf.toString());
/* 510:510 */    return true;
/* 511:    */  }
/* 512:    */  
/* 520:    */  public boolean replaceIn(StrBuilder source)
/* 521:    */  {
/* 522:522 */    if (source == null) {
/* 523:523 */      return false;
/* 524:    */    }
/* 525:525 */    return substitute(source, 0, source.length());
/* 526:    */  }
/* 527:    */  
/* 539:    */  public boolean replaceIn(StrBuilder source, int offset, int length)
/* 540:    */  {
/* 541:541 */    if (source == null) {
/* 542:542 */      return false;
/* 543:    */    }
/* 544:544 */    return substitute(source, offset, length);
/* 545:    */  }
/* 546:    */  
/* 561:    */  protected boolean substitute(StrBuilder buf, int offset, int length)
/* 562:    */  {
/* 563:563 */    return substitute(buf, offset, length, null) > 0;
/* 564:    */  }
/* 565:    */  
/* 577:    */  private int substitute(StrBuilder buf, int offset, int length, List<String> priorVariables)
/* 578:    */  {
/* 579:579 */    StrMatcher prefixMatcher = getVariablePrefixMatcher();
/* 580:580 */    StrMatcher suffixMatcher = getVariableSuffixMatcher();
/* 581:581 */    char escape = getEscapeChar();
/* 582:    */    
/* 583:583 */    boolean top = priorVariables == null;
/* 584:584 */    boolean altered = false;
/* 585:585 */    int lengthChange = 0;
/* 586:586 */    char[] chars = buf.buffer;
/* 587:587 */    int bufEnd = offset + length;
/* 588:588 */    int pos = offset;
/* 589:589 */    while (pos < bufEnd) {
/* 590:590 */      int startMatchLen = prefixMatcher.isMatch(chars, pos, offset, bufEnd);
/* 591:    */      
/* 592:592 */      if (startMatchLen == 0) {
/* 593:593 */        pos++;
/* 595:    */      }
/* 596:596 */      else if ((pos > offset) && (chars[(pos - 1)] == escape))
/* 597:    */      {
/* 598:598 */        buf.deleteCharAt(pos - 1);
/* 599:599 */        chars = buf.buffer;
/* 600:600 */        lengthChange--;
/* 601:601 */        altered = true;
/* 602:602 */        bufEnd--;
/* 603:    */      }
/* 604:    */      else {
/* 605:605 */        int startPos = pos;
/* 606:606 */        pos += startMatchLen;
/* 607:607 */        int endMatchLen = 0;
/* 608:608 */        int nestedVarCount = 0;
/* 609:609 */        while (pos < bufEnd) {
/* 610:610 */          if ((isEnableSubstitutionInVariables()) && ((endMatchLen = prefixMatcher.isMatch(chars, pos, offset, bufEnd)) != 0))
/* 611:    */          {
/* 614:614 */            nestedVarCount++;
/* 615:615 */            pos += endMatchLen;
/* 616:    */          }
/* 617:    */          else
/* 618:    */          {
/* 619:619 */            endMatchLen = suffixMatcher.isMatch(chars, pos, offset, bufEnd);
/* 620:    */            
/* 621:621 */            if (endMatchLen == 0) {
/* 622:622 */              pos++;
/* 623:    */            }
/* 624:    */            else {
/* 625:625 */              if (nestedVarCount == 0) {
/* 626:626 */                String varName = new String(chars, startPos + startMatchLen, pos - startPos - startMatchLen);
/* 627:    */                
/* 629:629 */                if (isEnableSubstitutionInVariables()) {
/* 630:630 */                  StrBuilder bufName = new StrBuilder(varName);
/* 631:631 */                  substitute(bufName, 0, bufName.length());
/* 632:632 */                  varName = bufName.toString();
/* 633:    */                }
/* 634:634 */                pos += endMatchLen;
/* 635:635 */                int endPos = pos;
/* 636:    */                
/* 638:638 */                if (priorVariables == null) {
/* 639:639 */                  priorVariables = new ArrayList();
/* 640:640 */                  priorVariables.add(new String(chars, offset, length));
/* 641:    */                }
/* 642:    */                
/* 645:645 */                checkCyclicSubstitution(varName, priorVariables);
/* 646:646 */                priorVariables.add(varName);
/* 647:    */                
/* 649:649 */                String varValue = resolveVariable(varName, buf, startPos, endPos);
/* 650:    */                
/* 651:651 */                if (varValue != null)
/* 652:    */                {
/* 653:653 */                  int varLen = varValue.length();
/* 654:654 */                  buf.replace(startPos, endPos, varValue);
/* 655:655 */                  altered = true;
/* 656:656 */                  int change = substitute(buf, startPos, varLen, priorVariables);
/* 657:    */                  
/* 658:658 */                  change = change + varLen - (endPos - startPos);
/* 659:    */                  
/* 660:660 */                  pos += change;
/* 661:661 */                  bufEnd += change;
/* 662:662 */                  lengthChange += change;
/* 663:663 */                  chars = buf.buffer;
/* 664:    */                }
/* 665:    */                
/* 668:668 */                priorVariables.remove(priorVariables.size() - 1);
/* 669:    */                
/* 670:670 */                break;
/* 671:    */              }
/* 672:672 */              nestedVarCount--;
/* 673:673 */              pos += endMatchLen;
/* 674:    */            }
/* 675:    */          }
/* 676:    */        }
/* 677:    */      }
/* 678:    */    }
/* 679:    */    
/* 680:680 */    if (top) {
/* 681:681 */      return altered ? 1 : 0;
/* 682:    */    }
/* 683:683 */    return lengthChange;
/* 684:    */  }
/* 685:    */  
/* 691:    */  private void checkCyclicSubstitution(String varName, List<String> priorVariables)
/* 692:    */  {
/* 693:693 */    if (!priorVariables.contains(varName)) {
/* 694:694 */      return;
/* 695:    */    }
/* 696:696 */    StrBuilder buf = new StrBuilder(256);
/* 697:697 */    buf.append("Infinite loop in property interpolation of ");
/* 698:698 */    buf.append((String)priorVariables.remove(0));
/* 699:699 */    buf.append(": ");
/* 700:700 */    buf.appendWithSeparators(priorVariables, "->");
/* 701:701 */    throw new IllegalStateException(buf.toString());
/* 702:    */  }
/* 703:    */  
/* 720:    */  protected String resolveVariable(String variableName, StrBuilder buf, int startPos, int endPos)
/* 721:    */  {
/* 722:722 */    StrLookup<?> resolver = getVariableResolver();
/* 723:723 */    if (resolver == null) {
/* 724:724 */      return null;
/* 725:    */    }
/* 726:726 */    return resolver.lookup(variableName);
/* 727:    */  }
/* 728:    */  
/* 735:    */  public char getEscapeChar()
/* 736:    */  {
/* 737:737 */    return this.escapeChar;
/* 738:    */  }
/* 739:    */  
/* 746:    */  public void setEscapeChar(char escapeCharacter)
/* 747:    */  {
/* 748:748 */    this.escapeChar = escapeCharacter;
/* 749:    */  }
/* 750:    */  
/* 761:    */  public StrMatcher getVariablePrefixMatcher()
/* 762:    */  {
/* 763:763 */    return this.prefixMatcher;
/* 764:    */  }
/* 765:    */  
/* 776:    */  public StrSubstitutor setVariablePrefixMatcher(StrMatcher prefixMatcher)
/* 777:    */  {
/* 778:778 */    if (prefixMatcher == null) {
/* 779:779 */      throw new IllegalArgumentException("Variable prefix matcher must not be null!");
/* 780:    */    }
/* 781:781 */    this.prefixMatcher = prefixMatcher;
/* 782:782 */    return this;
/* 783:    */  }
/* 784:    */  
/* 794:    */  public StrSubstitutor setVariablePrefix(char prefix)
/* 795:    */  {
/* 796:796 */    return setVariablePrefixMatcher(StrMatcher.charMatcher(prefix));
/* 797:    */  }
/* 798:    */  
/* 808:    */  public StrSubstitutor setVariablePrefix(String prefix)
/* 809:    */  {
/* 810:810 */    if (prefix == null) {
/* 811:811 */      throw new IllegalArgumentException("Variable prefix must not be null!");
/* 812:    */    }
/* 813:813 */    return setVariablePrefixMatcher(StrMatcher.stringMatcher(prefix));
/* 814:    */  }
/* 815:    */  
/* 826:    */  public StrMatcher getVariableSuffixMatcher()
/* 827:    */  {
/* 828:828 */    return this.suffixMatcher;
/* 829:    */  }
/* 830:    */  
/* 841:    */  public StrSubstitutor setVariableSuffixMatcher(StrMatcher suffixMatcher)
/* 842:    */  {
/* 843:843 */    if (suffixMatcher == null) {
/* 844:844 */      throw new IllegalArgumentException("Variable suffix matcher must not be null!");
/* 845:    */    }
/* 846:846 */    this.suffixMatcher = suffixMatcher;
/* 847:847 */    return this;
/* 848:    */  }
/* 849:    */  
/* 859:    */  public StrSubstitutor setVariableSuffix(char suffix)
/* 860:    */  {
/* 861:861 */    return setVariableSuffixMatcher(StrMatcher.charMatcher(suffix));
/* 862:    */  }
/* 863:    */  
/* 873:    */  public StrSubstitutor setVariableSuffix(String suffix)
/* 874:    */  {
/* 875:875 */    if (suffix == null) {
/* 876:876 */      throw new IllegalArgumentException("Variable suffix must not be null!");
/* 877:    */    }
/* 878:878 */    return setVariableSuffixMatcher(StrMatcher.stringMatcher(suffix));
/* 879:    */  }
/* 880:    */  
/* 887:    */  public StrLookup<?> getVariableResolver()
/* 888:    */  {
/* 889:889 */    return this.variableResolver;
/* 890:    */  }
/* 891:    */  
/* 896:    */  public void setVariableResolver(StrLookup<?> variableResolver)
/* 897:    */  {
/* 898:898 */    this.variableResolver = variableResolver;
/* 899:    */  }
/* 900:    */  
/* 908:    */  public boolean isEnableSubstitutionInVariables()
/* 909:    */  {
/* 910:910 */    return this.enableSubstitutionInVariables;
/* 911:    */  }
/* 912:    */  
/* 922:    */  public void setEnableSubstitutionInVariables(boolean enableSubstitutionInVariables)
/* 923:    */  {
/* 924:924 */    this.enableSubstitutionInVariables = enableSubstitutionInVariables;
/* 925:    */  }
/* 926:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.StrSubstitutor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */