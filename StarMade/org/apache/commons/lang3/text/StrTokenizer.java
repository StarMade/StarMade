/*    1:     */package org.apache.commons.lang3.text;
/*    2:     */
/*    3:     */import java.util.ArrayList;
/*    4:     */import java.util.Collections;
/*    5:     */import java.util.List;
/*    6:     */import java.util.ListIterator;
/*    7:     */import java.util.NoSuchElementException;
/*    8:     */import org.apache.commons.lang3.ArrayUtils;
/*    9:     */
/*   89:     */public class StrTokenizer
/*   90:     */  implements ListIterator<String>, Cloneable
/*   91:     */{
/*   92:  92 */  private static final StrTokenizer CSV_TOKENIZER_PROTOTYPE = new StrTokenizer();
/*   93:  93 */  static { CSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(StrMatcher.commaMatcher());
/*   94:  94 */    CSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
/*   95:  95 */    CSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(StrMatcher.noneMatcher());
/*   96:  96 */    CSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(StrMatcher.trimMatcher());
/*   97:  97 */    CSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
/*   98:  98 */    CSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);
/*   99:     */    
/*  100: 100 */    TSV_TOKENIZER_PROTOTYPE = new StrTokenizer();
/*  101: 101 */    TSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(StrMatcher.tabMatcher());
/*  102: 102 */    TSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
/*  103: 103 */    TSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(StrMatcher.noneMatcher());
/*  104: 104 */    TSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(StrMatcher.trimMatcher());
/*  105: 105 */    TSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
/*  106: 106 */    TSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);
/*  107:     */  }
/*  108:     */  
/*  110:     */  private static final StrTokenizer TSV_TOKENIZER_PROTOTYPE;
/*  111:     */  
/*  112:     */  private char[] chars;
/*  113:     */  
/*  114:     */  private String[] tokens;
/*  115:     */  
/*  116:     */  private int tokenPos;
/*  117: 117 */  private StrMatcher delimMatcher = StrMatcher.splitMatcher();
/*  118:     */  
/*  119: 119 */  private StrMatcher quoteMatcher = StrMatcher.noneMatcher();
/*  120:     */  
/*  121: 121 */  private StrMatcher ignoredMatcher = StrMatcher.noneMatcher();
/*  122:     */  
/*  123: 123 */  private StrMatcher trimmerMatcher = StrMatcher.noneMatcher();
/*  124:     */  
/*  126: 126 */  private boolean emptyAsNull = false;
/*  127:     */  
/*  128: 128 */  private boolean ignoreEmptyTokens = true;
/*  129:     */  
/*  136:     */  private static StrTokenizer getCSVClone()
/*  137:     */  {
/*  138: 138 */    return (StrTokenizer)CSV_TOKENIZER_PROTOTYPE.clone();
/*  139:     */  }
/*  140:     */  
/*  149:     */  public static StrTokenizer getCSVInstance()
/*  150:     */  {
/*  151: 151 */    return getCSVClone();
/*  152:     */  }
/*  153:     */  
/*  162:     */  public static StrTokenizer getCSVInstance(String input)
/*  163:     */  {
/*  164: 164 */    StrTokenizer tok = getCSVClone();
/*  165: 165 */    tok.reset(input);
/*  166: 166 */    return tok;
/*  167:     */  }
/*  168:     */  
/*  177:     */  public static StrTokenizer getCSVInstance(char[] input)
/*  178:     */  {
/*  179: 179 */    StrTokenizer tok = getCSVClone();
/*  180: 180 */    tok.reset(input);
/*  181: 181 */    return tok;
/*  182:     */  }
/*  183:     */  
/*  188:     */  private static StrTokenizer getTSVClone()
/*  189:     */  {
/*  190: 190 */    return (StrTokenizer)TSV_TOKENIZER_PROTOTYPE.clone();
/*  191:     */  }
/*  192:     */  
/*  201:     */  public static StrTokenizer getTSVInstance()
/*  202:     */  {
/*  203: 203 */    return getTSVClone();
/*  204:     */  }
/*  205:     */  
/*  212:     */  public static StrTokenizer getTSVInstance(String input)
/*  213:     */  {
/*  214: 214 */    StrTokenizer tok = getTSVClone();
/*  215: 215 */    tok.reset(input);
/*  216: 216 */    return tok;
/*  217:     */  }
/*  218:     */  
/*  225:     */  public static StrTokenizer getTSVInstance(char[] input)
/*  226:     */  {
/*  227: 227 */    StrTokenizer tok = getTSVClone();
/*  228: 228 */    tok.reset(input);
/*  229: 229 */    return tok;
/*  230:     */  }
/*  231:     */  
/*  239:     */  public StrTokenizer()
/*  240:     */  {
/*  241: 241 */    this.chars = null;
/*  242:     */  }
/*  243:     */  
/*  250:     */  public StrTokenizer(String input)
/*  251:     */  {
/*  252: 252 */    if (input != null) {
/*  253: 253 */      this.chars = input.toCharArray();
/*  254:     */    } else {
/*  255: 255 */      this.chars = null;
/*  256:     */    }
/*  257:     */  }
/*  258:     */  
/*  264:     */  public StrTokenizer(String input, char delim)
/*  265:     */  {
/*  266: 266 */    this(input);
/*  267: 267 */    setDelimiterChar(delim);
/*  268:     */  }
/*  269:     */  
/*  275:     */  public StrTokenizer(String input, String delim)
/*  276:     */  {
/*  277: 277 */    this(input);
/*  278: 278 */    setDelimiterString(delim);
/*  279:     */  }
/*  280:     */  
/*  286:     */  public StrTokenizer(String input, StrMatcher delim)
/*  287:     */  {
/*  288: 288 */    this(input);
/*  289: 289 */    setDelimiterMatcher(delim);
/*  290:     */  }
/*  291:     */  
/*  299:     */  public StrTokenizer(String input, char delim, char quote)
/*  300:     */  {
/*  301: 301 */    this(input, delim);
/*  302: 302 */    setQuoteChar(quote);
/*  303:     */  }
/*  304:     */  
/*  312:     */  public StrTokenizer(String input, StrMatcher delim, StrMatcher quote)
/*  313:     */  {
/*  314: 314 */    this(input, delim);
/*  315: 315 */    setQuoteMatcher(quote);
/*  316:     */  }
/*  317:     */  
/*  324:     */  public StrTokenizer(char[] input)
/*  325:     */  {
/*  326: 326 */    this.chars = ArrayUtils.clone(input);
/*  327:     */  }
/*  328:     */  
/*  334:     */  public StrTokenizer(char[] input, char delim)
/*  335:     */  {
/*  336: 336 */    this(input);
/*  337: 337 */    setDelimiterChar(delim);
/*  338:     */  }
/*  339:     */  
/*  345:     */  public StrTokenizer(char[] input, String delim)
/*  346:     */  {
/*  347: 347 */    this(input);
/*  348: 348 */    setDelimiterString(delim);
/*  349:     */  }
/*  350:     */  
/*  356:     */  public StrTokenizer(char[] input, StrMatcher delim)
/*  357:     */  {
/*  358: 358 */    this(input);
/*  359: 359 */    setDelimiterMatcher(delim);
/*  360:     */  }
/*  361:     */  
/*  369:     */  public StrTokenizer(char[] input, char delim, char quote)
/*  370:     */  {
/*  371: 371 */    this(input, delim);
/*  372: 372 */    setQuoteChar(quote);
/*  373:     */  }
/*  374:     */  
/*  382:     */  public StrTokenizer(char[] input, StrMatcher delim, StrMatcher quote)
/*  383:     */  {
/*  384: 384 */    this(input, delim);
/*  385: 385 */    setQuoteMatcher(quote);
/*  386:     */  }
/*  387:     */  
/*  394:     */  public int size()
/*  395:     */  {
/*  396: 396 */    checkTokenized();
/*  397: 397 */    return this.tokens.length;
/*  398:     */  }
/*  399:     */  
/*  406:     */  public String nextToken()
/*  407:     */  {
/*  408: 408 */    if (hasNext()) {
/*  409: 409 */      return this.tokens[(this.tokenPos++)];
/*  410:     */    }
/*  411: 411 */    return null;
/*  412:     */  }
/*  413:     */  
/*  418:     */  public String previousToken()
/*  419:     */  {
/*  420: 420 */    if (hasPrevious()) {
/*  421: 421 */      return this.tokens[(--this.tokenPos)];
/*  422:     */    }
/*  423: 423 */    return null;
/*  424:     */  }
/*  425:     */  
/*  430:     */  public String[] getTokenArray()
/*  431:     */  {
/*  432: 432 */    checkTokenized();
/*  433: 433 */    return (String[])this.tokens.clone();
/*  434:     */  }
/*  435:     */  
/*  440:     */  public List<String> getTokenList()
/*  441:     */  {
/*  442: 442 */    checkTokenized();
/*  443: 443 */    List<String> list = new ArrayList(this.tokens.length);
/*  444: 444 */    for (String element : this.tokens) {
/*  445: 445 */      list.add(element);
/*  446:     */    }
/*  447: 447 */    return list;
/*  448:     */  }
/*  449:     */  
/*  456:     */  public StrTokenizer reset()
/*  457:     */  {
/*  458: 458 */    this.tokenPos = 0;
/*  459: 459 */    this.tokens = null;
/*  460: 460 */    return this;
/*  461:     */  }
/*  462:     */  
/*  470:     */  public StrTokenizer reset(String input)
/*  471:     */  {
/*  472: 472 */    reset();
/*  473: 473 */    if (input != null) {
/*  474: 474 */      this.chars = input.toCharArray();
/*  475:     */    } else {
/*  476: 476 */      this.chars = null;
/*  477:     */    }
/*  478: 478 */    return this;
/*  479:     */  }
/*  480:     */  
/*  488:     */  public StrTokenizer reset(char[] input)
/*  489:     */  {
/*  490: 490 */    reset();
/*  491: 491 */    this.chars = ArrayUtils.clone(input);
/*  492: 492 */    return this;
/*  493:     */  }
/*  494:     */  
/*  501:     */  public boolean hasNext()
/*  502:     */  {
/*  503: 503 */    checkTokenized();
/*  504: 504 */    return this.tokenPos < this.tokens.length;
/*  505:     */  }
/*  506:     */  
/*  512:     */  public String next()
/*  513:     */  {
/*  514: 514 */    if (hasNext()) {
/*  515: 515 */      return this.tokens[(this.tokenPos++)];
/*  516:     */    }
/*  517: 517 */    throw new NoSuchElementException();
/*  518:     */  }
/*  519:     */  
/*  524:     */  public int nextIndex()
/*  525:     */  {
/*  526: 526 */    return this.tokenPos;
/*  527:     */  }
/*  528:     */  
/*  533:     */  public boolean hasPrevious()
/*  534:     */  {
/*  535: 535 */    checkTokenized();
/*  536: 536 */    return this.tokenPos > 0;
/*  537:     */  }
/*  538:     */  
/*  543:     */  public String previous()
/*  544:     */  {
/*  545: 545 */    if (hasPrevious()) {
/*  546: 546 */      return this.tokens[(--this.tokenPos)];
/*  547:     */    }
/*  548: 548 */    throw new NoSuchElementException();
/*  549:     */  }
/*  550:     */  
/*  555:     */  public int previousIndex()
/*  556:     */  {
/*  557: 557 */    return this.tokenPos - 1;
/*  558:     */  }
/*  559:     */  
/*  564:     */  public void remove()
/*  565:     */  {
/*  566: 566 */    throw new UnsupportedOperationException("remove() is unsupported");
/*  567:     */  }
/*  568:     */  
/*  573:     */  public void set(String obj)
/*  574:     */  {
/*  575: 575 */    throw new UnsupportedOperationException("set() is unsupported");
/*  576:     */  }
/*  577:     */  
/*  582:     */  public void add(String obj)
/*  583:     */  {
/*  584: 584 */    throw new UnsupportedOperationException("add() is unsupported");
/*  585:     */  }
/*  586:     */  
/*  591:     */  private void checkTokenized()
/*  592:     */  {
/*  593: 593 */    if (this.tokens == null) {
/*  594: 594 */      if (this.chars == null)
/*  595:     */      {
/*  596: 596 */        List<String> split = tokenize(null, 0, 0);
/*  597: 597 */        this.tokens = ((String[])split.toArray(new String[split.size()]));
/*  598:     */      } else {
/*  599: 599 */        List<String> split = tokenize(this.chars, 0, this.chars.length);
/*  600: 600 */        this.tokens = ((String[])split.toArray(new String[split.size()]));
/*  601:     */      }
/*  602:     */    }
/*  603:     */  }
/*  604:     */  
/*  624:     */  protected List<String> tokenize(char[] chars, int offset, int count)
/*  625:     */  {
/*  626: 626 */    if ((chars == null) || (count == 0)) {
/*  627: 627 */      return Collections.emptyList();
/*  628:     */    }
/*  629: 629 */    StrBuilder buf = new StrBuilder();
/*  630: 630 */    List<String> tokens = new ArrayList();
/*  631: 631 */    int pos = offset;
/*  632:     */    
/*  634: 634 */    while ((pos >= 0) && (pos < count))
/*  635:     */    {
/*  636: 636 */      pos = readNextToken(chars, pos, count, buf, tokens);
/*  637:     */      
/*  639: 639 */      if (pos >= count) {
/*  640: 640 */        addToken(tokens, "");
/*  641:     */      }
/*  642:     */    }
/*  643: 643 */    return tokens;
/*  644:     */  }
/*  645:     */  
/*  651:     */  private void addToken(List<String> list, String tok)
/*  652:     */  {
/*  653: 653 */    if ((tok == null) || (tok.length() == 0)) {
/*  654: 654 */      if (isIgnoreEmptyTokens()) {
/*  655: 655 */        return;
/*  656:     */      }
/*  657: 657 */      if (isEmptyTokenAsNull()) {
/*  658: 658 */        tok = null;
/*  659:     */      }
/*  660:     */    }
/*  661: 661 */    list.add(tok);
/*  662:     */  }
/*  663:     */  
/*  676:     */  private int readNextToken(char[] chars, int start, int len, StrBuilder workArea, List<String> tokens)
/*  677:     */  {
/*  678: 678 */    while (start < len) {
/*  679: 679 */      int removeLen = Math.max(getIgnoredMatcher().isMatch(chars, start, start, len), getTrimmerMatcher().isMatch(chars, start, start, len));
/*  680:     */      
/*  682: 682 */      if ((removeLen == 0) || (getDelimiterMatcher().isMatch(chars, start, start, len) > 0) || (getQuoteMatcher().isMatch(chars, start, start, len) > 0)) {
/*  683:     */        break;
/*  684:     */      }
/*  685:     */      
/*  687: 687 */      start += removeLen;
/*  688:     */    }
/*  689:     */    
/*  691: 691 */    if (start >= len) {
/*  692: 692 */      addToken(tokens, "");
/*  693: 693 */      return -1;
/*  694:     */    }
/*  695:     */    
/*  697: 697 */    int delimLen = getDelimiterMatcher().isMatch(chars, start, start, len);
/*  698: 698 */    if (delimLen > 0) {
/*  699: 699 */      addToken(tokens, "");
/*  700: 700 */      return start + delimLen;
/*  701:     */    }
/*  702:     */    
/*  704: 704 */    int quoteLen = getQuoteMatcher().isMatch(chars, start, start, len);
/*  705: 705 */    if (quoteLen > 0) {
/*  706: 706 */      return readWithQuotes(chars, start + quoteLen, len, workArea, tokens, start, quoteLen);
/*  707:     */    }
/*  708: 708 */    return readWithQuotes(chars, start, len, workArea, tokens, 0, 0);
/*  709:     */  }
/*  710:     */  
/*  727:     */  private int readWithQuotes(char[] chars, int start, int len, StrBuilder workArea, List<String> tokens, int quoteStart, int quoteLen)
/*  728:     */  {
/*  729: 729 */    workArea.clear();
/*  730: 730 */    int pos = start;
/*  731: 731 */    boolean quoting = quoteLen > 0;
/*  732: 732 */    int trimStart = 0;
/*  733:     */    
/*  734: 734 */    while (pos < len)
/*  735:     */    {
/*  738: 738 */      if (quoting)
/*  739:     */      {
/*  745: 745 */        if (isQuote(chars, pos, len, quoteStart, quoteLen)) {
/*  746: 746 */          if (isQuote(chars, pos + quoteLen, len, quoteStart, quoteLen))
/*  747:     */          {
/*  748: 748 */            workArea.append(chars, pos, quoteLen);
/*  749: 749 */            pos += quoteLen * 2;
/*  750: 750 */            trimStart = workArea.size();
/*  752:     */          }
/*  753:     */          else
/*  754:     */          {
/*  755: 755 */            quoting = false;
/*  756: 756 */            pos += quoteLen;
/*  757:     */          }
/*  758:     */        }
/*  759:     */        else
/*  760:     */        {
/*  761: 761 */          workArea.append(chars[(pos++)]);
/*  762: 762 */          trimStart = workArea.size();
/*  763:     */        }
/*  764:     */        
/*  765:     */      }
/*  766:     */      else
/*  767:     */      {
/*  768: 768 */        int delimLen = getDelimiterMatcher().isMatch(chars, pos, start, len);
/*  769: 769 */        if (delimLen > 0)
/*  770:     */        {
/*  771: 771 */          addToken(tokens, workArea.substring(0, trimStart));
/*  772: 772 */          return pos + delimLen;
/*  773:     */        }
/*  774:     */        
/*  776: 776 */        if ((quoteLen > 0) && (isQuote(chars, pos, len, quoteStart, quoteLen))) {
/*  777: 777 */          quoting = true;
/*  778: 778 */          pos += quoteLen;
/*  780:     */        }
/*  781:     */        else
/*  782:     */        {
/*  783: 783 */          int ignoredLen = getIgnoredMatcher().isMatch(chars, pos, start, len);
/*  784: 784 */          if (ignoredLen > 0) {
/*  785: 785 */            pos += ignoredLen;
/*  788:     */          }
/*  789:     */          else
/*  790:     */          {
/*  792: 792 */            int trimmedLen = getTrimmerMatcher().isMatch(chars, pos, start, len);
/*  793: 793 */            if (trimmedLen > 0) {
/*  794: 794 */              workArea.append(chars, pos, trimmedLen);
/*  795: 795 */              pos += trimmedLen;
/*  797:     */            }
/*  798:     */            else
/*  799:     */            {
/*  800: 800 */              workArea.append(chars[(pos++)]);
/*  801: 801 */              trimStart = workArea.size();
/*  802:     */            }
/*  803:     */          }
/*  804:     */        }
/*  805:     */      } }
/*  806: 806 */    addToken(tokens, workArea.substring(0, trimStart));
/*  807: 807 */    return -1;
/*  808:     */  }
/*  809:     */  
/*  820:     */  private boolean isQuote(char[] chars, int pos, int len, int quoteStart, int quoteLen)
/*  821:     */  {
/*  822: 822 */    for (int i = 0; i < quoteLen; i++) {
/*  823: 823 */      if ((pos + i >= len) || (chars[(pos + i)] != chars[(quoteStart + i)])) {
/*  824: 824 */        return false;
/*  825:     */      }
/*  826:     */    }
/*  827: 827 */    return true;
/*  828:     */  }
/*  829:     */  
/*  836:     */  public StrMatcher getDelimiterMatcher()
/*  837:     */  {
/*  838: 838 */    return this.delimMatcher;
/*  839:     */  }
/*  840:     */  
/*  848:     */  public StrTokenizer setDelimiterMatcher(StrMatcher delim)
/*  849:     */  {
/*  850: 850 */    if (delim == null) {
/*  851: 851 */      this.delimMatcher = StrMatcher.noneMatcher();
/*  852:     */    } else {
/*  853: 853 */      this.delimMatcher = delim;
/*  854:     */    }
/*  855: 855 */    return this;
/*  856:     */  }
/*  857:     */  
/*  863:     */  public StrTokenizer setDelimiterChar(char delim)
/*  864:     */  {
/*  865: 865 */    return setDelimiterMatcher(StrMatcher.charMatcher(delim));
/*  866:     */  }
/*  867:     */  
/*  873:     */  public StrTokenizer setDelimiterString(String delim)
/*  874:     */  {
/*  875: 875 */    return setDelimiterMatcher(StrMatcher.stringMatcher(delim));
/*  876:     */  }
/*  877:     */  
/*  888:     */  public StrMatcher getQuoteMatcher()
/*  889:     */  {
/*  890: 890 */    return this.quoteMatcher;
/*  891:     */  }
/*  892:     */  
/*  901:     */  public StrTokenizer setQuoteMatcher(StrMatcher quote)
/*  902:     */  {
/*  903: 903 */    if (quote != null) {
/*  904: 904 */      this.quoteMatcher = quote;
/*  905:     */    }
/*  906: 906 */    return this;
/*  907:     */  }
/*  908:     */  
/*  917:     */  public StrTokenizer setQuoteChar(char quote)
/*  918:     */  {
/*  919: 919 */    return setQuoteMatcher(StrMatcher.charMatcher(quote));
/*  920:     */  }
/*  921:     */  
/*  932:     */  public StrMatcher getIgnoredMatcher()
/*  933:     */  {
/*  934: 934 */    return this.ignoredMatcher;
/*  935:     */  }
/*  936:     */  
/*  945:     */  public StrTokenizer setIgnoredMatcher(StrMatcher ignored)
/*  946:     */  {
/*  947: 947 */    if (ignored != null) {
/*  948: 948 */      this.ignoredMatcher = ignored;
/*  949:     */    }
/*  950: 950 */    return this;
/*  951:     */  }
/*  952:     */  
/*  961:     */  public StrTokenizer setIgnoredChar(char ignored)
/*  962:     */  {
/*  963: 963 */    return setIgnoredMatcher(StrMatcher.charMatcher(ignored));
/*  964:     */  }
/*  965:     */  
/*  976:     */  public StrMatcher getTrimmerMatcher()
/*  977:     */  {
/*  978: 978 */    return this.trimmerMatcher;
/*  979:     */  }
/*  980:     */  
/*  989:     */  public StrTokenizer setTrimmerMatcher(StrMatcher trimmer)
/*  990:     */  {
/*  991: 991 */    if (trimmer != null) {
/*  992: 992 */      this.trimmerMatcher = trimmer;
/*  993:     */    }
/*  994: 994 */    return this;
/*  995:     */  }
/*  996:     */  
/* 1003:     */  public boolean isEmptyTokenAsNull()
/* 1004:     */  {
/* 1005:1005 */    return this.emptyAsNull;
/* 1006:     */  }
/* 1007:     */  
/* 1014:     */  public StrTokenizer setEmptyTokenAsNull(boolean emptyAsNull)
/* 1015:     */  {
/* 1016:1016 */    this.emptyAsNull = emptyAsNull;
/* 1017:1017 */    return this;
/* 1018:     */  }
/* 1019:     */  
/* 1026:     */  public boolean isIgnoreEmptyTokens()
/* 1027:     */  {
/* 1028:1028 */    return this.ignoreEmptyTokens;
/* 1029:     */  }
/* 1030:     */  
/* 1037:     */  public StrTokenizer setIgnoreEmptyTokens(boolean ignoreEmptyTokens)
/* 1038:     */  {
/* 1039:1039 */    this.ignoreEmptyTokens = ignoreEmptyTokens;
/* 1040:1040 */    return this;
/* 1041:     */  }
/* 1042:     */  
/* 1048:     */  public String getContent()
/* 1049:     */  {
/* 1050:1050 */    if (this.chars == null) {
/* 1051:1051 */      return null;
/* 1052:     */    }
/* 1053:1053 */    return new String(this.chars);
/* 1054:     */  }
/* 1055:     */  
/* 1063:     */  public Object clone()
/* 1064:     */  {
/* 1065:     */    try
/* 1066:     */    {
/* 1067:1067 */      return cloneReset();
/* 1068:     */    } catch (CloneNotSupportedException ex) {}
/* 1069:1069 */    return null;
/* 1070:     */  }
/* 1071:     */  
/* 1079:     */  Object cloneReset()
/* 1080:     */    throws CloneNotSupportedException
/* 1081:     */  {
/* 1082:1082 */    StrTokenizer cloned = (StrTokenizer)super.clone();
/* 1083:1083 */    if (cloned.chars != null) {
/* 1084:1084 */      cloned.chars = ((char[])cloned.chars.clone());
/* 1085:     */    }
/* 1086:1086 */    cloned.reset();
/* 1087:1087 */    return cloned;
/* 1088:     */  }
/* 1089:     */  
/* 1096:     */  public String toString()
/* 1097:     */  {
/* 1098:1098 */    if (this.tokens == null) {
/* 1099:1099 */      return "StrTokenizer[not tokenized yet]";
/* 1100:     */    }
/* 1101:1101 */    return "StrTokenizer" + getTokenList();
/* 1102:     */  }
/* 1103:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.StrTokenizer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */