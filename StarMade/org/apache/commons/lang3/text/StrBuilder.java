/*    1:     */package org.apache.commons.lang3.text;
/*    2:     */
/*    3:     */import java.io.Reader;
/*    4:     */import java.io.Writer;
/*    5:     */import java.util.Iterator;
/*    6:     */import java.util.List;
/*    7:     */import org.apache.commons.lang3.ArrayUtils;
/*    8:     */import org.apache.commons.lang3.ObjectUtils;
/*    9:     */import org.apache.commons.lang3.SystemUtils;
/*   10:     */
/*   89:     */public class StrBuilder
/*   90:     */  implements CharSequence, Appendable
/*   91:     */{
/*   92:     */  static final int CAPACITY = 32;
/*   93:     */  private static final long serialVersionUID = 7628716375283629643L;
/*   94:     */  protected char[] buffer;
/*   95:     */  protected int size;
/*   96:     */  private String newLine;
/*   97:     */  private String nullText;
/*   98:     */  
/*   99:     */  public StrBuilder()
/*  100:     */  {
/*  101: 101 */    this(32);
/*  102:     */  }
/*  103:     */  
/*  109:     */  public StrBuilder(int initialCapacity)
/*  110:     */  {
/*  111: 111 */    if (initialCapacity <= 0) {
/*  112: 112 */      initialCapacity = 32;
/*  113:     */    }
/*  114: 114 */    this.buffer = new char[initialCapacity];
/*  115:     */  }
/*  116:     */  
/*  123:     */  public StrBuilder(String str)
/*  124:     */  {
/*  125: 125 */    if (str == null) {
/*  126: 126 */      this.buffer = new char[32];
/*  127:     */    } else {
/*  128: 128 */      this.buffer = new char[str.length() + 32];
/*  129: 129 */      append(str);
/*  130:     */    }
/*  131:     */  }
/*  132:     */  
/*  138:     */  public String getNewLineText()
/*  139:     */  {
/*  140: 140 */    return this.newLine;
/*  141:     */  }
/*  142:     */  
/*  148:     */  public StrBuilder setNewLineText(String newLine)
/*  149:     */  {
/*  150: 150 */    this.newLine = newLine;
/*  151: 151 */    return this;
/*  152:     */  }
/*  153:     */  
/*  159:     */  public String getNullText()
/*  160:     */  {
/*  161: 161 */    return this.nullText;
/*  162:     */  }
/*  163:     */  
/*  169:     */  public StrBuilder setNullText(String nullText)
/*  170:     */  {
/*  171: 171 */    if ((nullText != null) && (nullText.length() == 0)) {
/*  172: 172 */      nullText = null;
/*  173:     */    }
/*  174: 174 */    this.nullText = nullText;
/*  175: 175 */    return this;
/*  176:     */  }
/*  177:     */  
/*  183:     */  public int length()
/*  184:     */  {
/*  185: 185 */    return this.size;
/*  186:     */  }
/*  187:     */  
/*  195:     */  public StrBuilder setLength(int length)
/*  196:     */  {
/*  197: 197 */    if (length < 0) {
/*  198: 198 */      throw new StringIndexOutOfBoundsException(length);
/*  199:     */    }
/*  200: 200 */    if (length < this.size) {
/*  201: 201 */      this.size = length;
/*  202: 202 */    } else if (length > this.size) {
/*  203: 203 */      ensureCapacity(length);
/*  204: 204 */      int oldEnd = this.size;
/*  205: 205 */      int newEnd = length;
/*  206: 206 */      this.size = length;
/*  207: 207 */      for (int i = oldEnd; i < newEnd; i++) {
/*  208: 208 */        this.buffer[i] = '\000';
/*  209:     */      }
/*  210:     */    }
/*  211: 211 */    return this;
/*  212:     */  }
/*  213:     */  
/*  219:     */  public int capacity()
/*  220:     */  {
/*  221: 221 */    return this.buffer.length;
/*  222:     */  }
/*  223:     */  
/*  229:     */  public StrBuilder ensureCapacity(int capacity)
/*  230:     */  {
/*  231: 231 */    if (capacity > this.buffer.length) {
/*  232: 232 */      char[] old = this.buffer;
/*  233: 233 */      this.buffer = new char[capacity * 2];
/*  234: 234 */      System.arraycopy(old, 0, this.buffer, 0, this.size);
/*  235:     */    }
/*  236: 236 */    return this;
/*  237:     */  }
/*  238:     */  
/*  243:     */  public StrBuilder minimizeCapacity()
/*  244:     */  {
/*  245: 245 */    if (this.buffer.length > length()) {
/*  246: 246 */      char[] old = this.buffer;
/*  247: 247 */      this.buffer = new char[length()];
/*  248: 248 */      System.arraycopy(old, 0, this.buffer, 0, this.size);
/*  249:     */    }
/*  250: 250 */    return this;
/*  251:     */  }
/*  252:     */  
/*  261:     */  public int size()
/*  262:     */  {
/*  263: 263 */    return this.size;
/*  264:     */  }
/*  265:     */  
/*  273:     */  public boolean isEmpty()
/*  274:     */  {
/*  275: 275 */    return this.size == 0;
/*  276:     */  }
/*  277:     */  
/*  288:     */  public StrBuilder clear()
/*  289:     */  {
/*  290: 290 */    this.size = 0;
/*  291: 291 */    return this;
/*  292:     */  }
/*  293:     */  
/*  303:     */  public char charAt(int index)
/*  304:     */  {
/*  305: 305 */    if ((index < 0) || (index >= length())) {
/*  306: 306 */      throw new StringIndexOutOfBoundsException(index);
/*  307:     */    }
/*  308: 308 */    return this.buffer[index];
/*  309:     */  }
/*  310:     */  
/*  320:     */  public StrBuilder setCharAt(int index, char ch)
/*  321:     */  {
/*  322: 322 */    if ((index < 0) || (index >= length())) {
/*  323: 323 */      throw new StringIndexOutOfBoundsException(index);
/*  324:     */    }
/*  325: 325 */    this.buffer[index] = ch;
/*  326: 326 */    return this;
/*  327:     */  }
/*  328:     */  
/*  337:     */  public StrBuilder deleteCharAt(int index)
/*  338:     */  {
/*  339: 339 */    if ((index < 0) || (index >= this.size)) {
/*  340: 340 */      throw new StringIndexOutOfBoundsException(index);
/*  341:     */    }
/*  342: 342 */    deleteImpl(index, index + 1, 1);
/*  343: 343 */    return this;
/*  344:     */  }
/*  345:     */  
/*  351:     */  public char[] toCharArray()
/*  352:     */  {
/*  353: 353 */    if (this.size == 0) {
/*  354: 354 */      return ArrayUtils.EMPTY_CHAR_ARRAY;
/*  355:     */    }
/*  356: 356 */    char[] chars = new char[this.size];
/*  357: 357 */    System.arraycopy(this.buffer, 0, chars, 0, this.size);
/*  358: 358 */    return chars;
/*  359:     */  }
/*  360:     */  
/*  370:     */  public char[] toCharArray(int startIndex, int endIndex)
/*  371:     */  {
/*  372: 372 */    endIndex = validateRange(startIndex, endIndex);
/*  373: 373 */    int len = endIndex - startIndex;
/*  374: 374 */    if (len == 0) {
/*  375: 375 */      return ArrayUtils.EMPTY_CHAR_ARRAY;
/*  376:     */    }
/*  377: 377 */    char[] chars = new char[len];
/*  378: 378 */    System.arraycopy(this.buffer, startIndex, chars, 0, len);
/*  379: 379 */    return chars;
/*  380:     */  }
/*  381:     */  
/*  387:     */  public char[] getChars(char[] destination)
/*  388:     */  {
/*  389: 389 */    int len = length();
/*  390: 390 */    if ((destination == null) || (destination.length < len)) {
/*  391: 391 */      destination = new char[len];
/*  392:     */    }
/*  393: 393 */    System.arraycopy(this.buffer, 0, destination, 0, len);
/*  394: 394 */    return destination;
/*  395:     */  }
/*  396:     */  
/*  406:     */  public void getChars(int startIndex, int endIndex, char[] destination, int destinationIndex)
/*  407:     */  {
/*  408: 408 */    if (startIndex < 0) {
/*  409: 409 */      throw new StringIndexOutOfBoundsException(startIndex);
/*  410:     */    }
/*  411: 411 */    if ((endIndex < 0) || (endIndex > length())) {
/*  412: 412 */      throw new StringIndexOutOfBoundsException(endIndex);
/*  413:     */    }
/*  414: 414 */    if (startIndex > endIndex) {
/*  415: 415 */      throw new StringIndexOutOfBoundsException("end < start");
/*  416:     */    }
/*  417: 417 */    System.arraycopy(this.buffer, startIndex, destination, destinationIndex, endIndex - startIndex);
/*  418:     */  }
/*  419:     */  
/*  429:     */  public StrBuilder appendNewLine()
/*  430:     */  {
/*  431: 431 */    if (this.newLine == null) {
/*  432: 432 */      append(SystemUtils.LINE_SEPARATOR);
/*  433: 433 */      return this;
/*  434:     */    }
/*  435: 435 */    return append(this.newLine);
/*  436:     */  }
/*  437:     */  
/*  442:     */  public StrBuilder appendNull()
/*  443:     */  {
/*  444: 444 */    if (this.nullText == null) {
/*  445: 445 */      return this;
/*  446:     */    }
/*  447: 447 */    return append(this.nullText);
/*  448:     */  }
/*  449:     */  
/*  456:     */  public StrBuilder append(Object obj)
/*  457:     */  {
/*  458: 458 */    if (obj == null) {
/*  459: 459 */      return appendNull();
/*  460:     */    }
/*  461: 461 */    return append(obj.toString());
/*  462:     */  }
/*  463:     */  
/*  471:     */  public StrBuilder append(CharSequence seq)
/*  472:     */  {
/*  473: 473 */    if (seq == null) {
/*  474: 474 */      return appendNull();
/*  475:     */    }
/*  476: 476 */    return append(seq.toString());
/*  477:     */  }
/*  478:     */  
/*  488:     */  public StrBuilder append(CharSequence seq, int startIndex, int length)
/*  489:     */  {
/*  490: 490 */    if (seq == null) {
/*  491: 491 */      return appendNull();
/*  492:     */    }
/*  493: 493 */    return append(seq.toString(), startIndex, length);
/*  494:     */  }
/*  495:     */  
/*  502:     */  public StrBuilder append(String str)
/*  503:     */  {
/*  504: 504 */    if (str == null) {
/*  505: 505 */      return appendNull();
/*  506:     */    }
/*  507: 507 */    int strLen = str.length();
/*  508: 508 */    if (strLen > 0) {
/*  509: 509 */      int len = length();
/*  510: 510 */      ensureCapacity(len + strLen);
/*  511: 511 */      str.getChars(0, strLen, this.buffer, len);
/*  512: 512 */      this.size += strLen;
/*  513:     */    }
/*  514: 514 */    return this;
/*  515:     */  }
/*  516:     */  
/*  525:     */  public StrBuilder append(String str, int startIndex, int length)
/*  526:     */  {
/*  527: 527 */    if (str == null) {
/*  528: 528 */      return appendNull();
/*  529:     */    }
/*  530: 530 */    if ((startIndex < 0) || (startIndex > str.length())) {
/*  531: 531 */      throw new StringIndexOutOfBoundsException("startIndex must be valid");
/*  532:     */    }
/*  533: 533 */    if ((length < 0) || (startIndex + length > str.length())) {
/*  534: 534 */      throw new StringIndexOutOfBoundsException("length must be valid");
/*  535:     */    }
/*  536: 536 */    if (length > 0) {
/*  537: 537 */      int len = length();
/*  538: 538 */      ensureCapacity(len + length);
/*  539: 539 */      str.getChars(startIndex, startIndex + length, this.buffer, len);
/*  540: 540 */      this.size += length;
/*  541:     */    }
/*  542: 542 */    return this;
/*  543:     */  }
/*  544:     */  
/*  551:     */  public StrBuilder append(StringBuffer str)
/*  552:     */  {
/*  553: 553 */    if (str == null) {
/*  554: 554 */      return appendNull();
/*  555:     */    }
/*  556: 556 */    int strLen = str.length();
/*  557: 557 */    if (strLen > 0) {
/*  558: 558 */      int len = length();
/*  559: 559 */      ensureCapacity(len + strLen);
/*  560: 560 */      str.getChars(0, strLen, this.buffer, len);
/*  561: 561 */      this.size += strLen;
/*  562:     */    }
/*  563: 563 */    return this;
/*  564:     */  }
/*  565:     */  
/*  574:     */  public StrBuilder append(StringBuffer str, int startIndex, int length)
/*  575:     */  {
/*  576: 576 */    if (str == null) {
/*  577: 577 */      return appendNull();
/*  578:     */    }
/*  579: 579 */    if ((startIndex < 0) || (startIndex > str.length())) {
/*  580: 580 */      throw new StringIndexOutOfBoundsException("startIndex must be valid");
/*  581:     */    }
/*  582: 582 */    if ((length < 0) || (startIndex + length > str.length())) {
/*  583: 583 */      throw new StringIndexOutOfBoundsException("length must be valid");
/*  584:     */    }
/*  585: 585 */    if (length > 0) {
/*  586: 586 */      int len = length();
/*  587: 587 */      ensureCapacity(len + length);
/*  588: 588 */      str.getChars(startIndex, startIndex + length, this.buffer, len);
/*  589: 589 */      this.size += length;
/*  590:     */    }
/*  591: 591 */    return this;
/*  592:     */  }
/*  593:     */  
/*  600:     */  public StrBuilder append(StrBuilder str)
/*  601:     */  {
/*  602: 602 */    if (str == null) {
/*  603: 603 */      return appendNull();
/*  604:     */    }
/*  605: 605 */    int strLen = str.length();
/*  606: 606 */    if (strLen > 0) {
/*  607: 607 */      int len = length();
/*  608: 608 */      ensureCapacity(len + strLen);
/*  609: 609 */      System.arraycopy(str.buffer, 0, this.buffer, len, strLen);
/*  610: 610 */      this.size += strLen;
/*  611:     */    }
/*  612: 612 */    return this;
/*  613:     */  }
/*  614:     */  
/*  623:     */  public StrBuilder append(StrBuilder str, int startIndex, int length)
/*  624:     */  {
/*  625: 625 */    if (str == null) {
/*  626: 626 */      return appendNull();
/*  627:     */    }
/*  628: 628 */    if ((startIndex < 0) || (startIndex > str.length())) {
/*  629: 629 */      throw new StringIndexOutOfBoundsException("startIndex must be valid");
/*  630:     */    }
/*  631: 631 */    if ((length < 0) || (startIndex + length > str.length())) {
/*  632: 632 */      throw new StringIndexOutOfBoundsException("length must be valid");
/*  633:     */    }
/*  634: 634 */    if (length > 0) {
/*  635: 635 */      int len = length();
/*  636: 636 */      ensureCapacity(len + length);
/*  637: 637 */      str.getChars(startIndex, startIndex + length, this.buffer, len);
/*  638: 638 */      this.size += length;
/*  639:     */    }
/*  640: 640 */    return this;
/*  641:     */  }
/*  642:     */  
/*  649:     */  public StrBuilder append(char[] chars)
/*  650:     */  {
/*  651: 651 */    if (chars == null) {
/*  652: 652 */      return appendNull();
/*  653:     */    }
/*  654: 654 */    int strLen = chars.length;
/*  655: 655 */    if (strLen > 0) {
/*  656: 656 */      int len = length();
/*  657: 657 */      ensureCapacity(len + strLen);
/*  658: 658 */      System.arraycopy(chars, 0, this.buffer, len, strLen);
/*  659: 659 */      this.size += strLen;
/*  660:     */    }
/*  661: 661 */    return this;
/*  662:     */  }
/*  663:     */  
/*  672:     */  public StrBuilder append(char[] chars, int startIndex, int length)
/*  673:     */  {
/*  674: 674 */    if (chars == null) {
/*  675: 675 */      return appendNull();
/*  676:     */    }
/*  677: 677 */    if ((startIndex < 0) || (startIndex > chars.length)) {
/*  678: 678 */      throw new StringIndexOutOfBoundsException("Invalid startIndex: " + length);
/*  679:     */    }
/*  680: 680 */    if ((length < 0) || (startIndex + length > chars.length)) {
/*  681: 681 */      throw new StringIndexOutOfBoundsException("Invalid length: " + length);
/*  682:     */    }
/*  683: 683 */    if (length > 0) {
/*  684: 684 */      int len = length();
/*  685: 685 */      ensureCapacity(len + length);
/*  686: 686 */      System.arraycopy(chars, startIndex, this.buffer, len, length);
/*  687: 687 */      this.size += length;
/*  688:     */    }
/*  689: 689 */    return this;
/*  690:     */  }
/*  691:     */  
/*  697:     */  public StrBuilder append(boolean value)
/*  698:     */  {
/*  699: 699 */    if (value) {
/*  700: 700 */      ensureCapacity(this.size + 4);
/*  701: 701 */      this.buffer[(this.size++)] = 't';
/*  702: 702 */      this.buffer[(this.size++)] = 'r';
/*  703: 703 */      this.buffer[(this.size++)] = 'u';
/*  704: 704 */      this.buffer[(this.size++)] = 'e';
/*  705:     */    } else {
/*  706: 706 */      ensureCapacity(this.size + 5);
/*  707: 707 */      this.buffer[(this.size++)] = 'f';
/*  708: 708 */      this.buffer[(this.size++)] = 'a';
/*  709: 709 */      this.buffer[(this.size++)] = 'l';
/*  710: 710 */      this.buffer[(this.size++)] = 's';
/*  711: 711 */      this.buffer[(this.size++)] = 'e';
/*  712:     */    }
/*  713: 713 */    return this;
/*  714:     */  }
/*  715:     */  
/*  722:     */  public StrBuilder append(char ch)
/*  723:     */  {
/*  724: 724 */    int len = length();
/*  725: 725 */    ensureCapacity(len + 1);
/*  726: 726 */    this.buffer[(this.size++)] = ch;
/*  727: 727 */    return this;
/*  728:     */  }
/*  729:     */  
/*  735:     */  public StrBuilder append(int value)
/*  736:     */  {
/*  737: 737 */    return append(String.valueOf(value));
/*  738:     */  }
/*  739:     */  
/*  745:     */  public StrBuilder append(long value)
/*  746:     */  {
/*  747: 747 */    return append(String.valueOf(value));
/*  748:     */  }
/*  749:     */  
/*  755:     */  public StrBuilder append(float value)
/*  756:     */  {
/*  757: 757 */    return append(String.valueOf(value));
/*  758:     */  }
/*  759:     */  
/*  765:     */  public StrBuilder append(double value)
/*  766:     */  {
/*  767: 767 */    return append(String.valueOf(value));
/*  768:     */  }
/*  769:     */  
/*  778:     */  public StrBuilder appendln(Object obj)
/*  779:     */  {
/*  780: 780 */    return append(obj).appendNewLine();
/*  781:     */  }
/*  782:     */  
/*  790:     */  public StrBuilder appendln(String str)
/*  791:     */  {
/*  792: 792 */    return append(str).appendNewLine();
/*  793:     */  }
/*  794:     */  
/*  804:     */  public StrBuilder appendln(String str, int startIndex, int length)
/*  805:     */  {
/*  806: 806 */    return append(str, startIndex, length).appendNewLine();
/*  807:     */  }
/*  808:     */  
/*  816:     */  public StrBuilder appendln(StringBuffer str)
/*  817:     */  {
/*  818: 818 */    return append(str).appendNewLine();
/*  819:     */  }
/*  820:     */  
/*  830:     */  public StrBuilder appendln(StringBuffer str, int startIndex, int length)
/*  831:     */  {
/*  832: 832 */    return append(str, startIndex, length).appendNewLine();
/*  833:     */  }
/*  834:     */  
/*  842:     */  public StrBuilder appendln(StrBuilder str)
/*  843:     */  {
/*  844: 844 */    return append(str).appendNewLine();
/*  845:     */  }
/*  846:     */  
/*  856:     */  public StrBuilder appendln(StrBuilder str, int startIndex, int length)
/*  857:     */  {
/*  858: 858 */    return append(str, startIndex, length).appendNewLine();
/*  859:     */  }
/*  860:     */  
/*  868:     */  public StrBuilder appendln(char[] chars)
/*  869:     */  {
/*  870: 870 */    return append(chars).appendNewLine();
/*  871:     */  }
/*  872:     */  
/*  882:     */  public StrBuilder appendln(char[] chars, int startIndex, int length)
/*  883:     */  {
/*  884: 884 */    return append(chars, startIndex, length).appendNewLine();
/*  885:     */  }
/*  886:     */  
/*  893:     */  public StrBuilder appendln(boolean value)
/*  894:     */  {
/*  895: 895 */    return append(value).appendNewLine();
/*  896:     */  }
/*  897:     */  
/*  904:     */  public StrBuilder appendln(char ch)
/*  905:     */  {
/*  906: 906 */    return append(ch).appendNewLine();
/*  907:     */  }
/*  908:     */  
/*  915:     */  public StrBuilder appendln(int value)
/*  916:     */  {
/*  917: 917 */    return append(value).appendNewLine();
/*  918:     */  }
/*  919:     */  
/*  926:     */  public StrBuilder appendln(long value)
/*  927:     */  {
/*  928: 928 */    return append(value).appendNewLine();
/*  929:     */  }
/*  930:     */  
/*  937:     */  public StrBuilder appendln(float value)
/*  938:     */  {
/*  939: 939 */    return append(value).appendNewLine();
/*  940:     */  }
/*  941:     */  
/*  948:     */  public StrBuilder appendln(double value)
/*  949:     */  {
/*  950: 950 */    return append(value).appendNewLine();
/*  951:     */  }
/*  952:     */  
/*  962:     */  public StrBuilder appendAll(Object[] array)
/*  963:     */  {
/*  964: 964 */    if ((array != null) && (array.length > 0)) {
/*  965: 965 */      for (Object element : array) {
/*  966: 966 */        append(element);
/*  967:     */      }
/*  968:     */    }
/*  969: 969 */    return this;
/*  970:     */  }
/*  971:     */  
/*  980:     */  public StrBuilder appendAll(Iterable<?> iterable)
/*  981:     */  {
/*  982: 982 */    if (iterable != null) {
/*  983: 983 */      Iterator<?> it = iterable.iterator();
/*  984: 984 */      while (it.hasNext()) {
/*  985: 985 */        append(it.next());
/*  986:     */      }
/*  987:     */    }
/*  988: 988 */    return this;
/*  989:     */  }
/*  990:     */  
/*  999:     */  public StrBuilder appendAll(Iterator<?> it)
/* 1000:     */  {
/* 1001:1001 */    if (it != null) {
/* 1002:1002 */      while (it.hasNext()) {
/* 1003:1003 */        append(it.next());
/* 1004:     */      }
/* 1005:     */    }
/* 1006:1006 */    return this;
/* 1007:     */  }
/* 1008:     */  
/* 1019:     */  public StrBuilder appendWithSeparators(Object[] array, String separator)
/* 1020:     */  {
/* 1021:1021 */    if ((array != null) && (array.length > 0)) {
/* 1022:1022 */      separator = ObjectUtils.toString(separator);
/* 1023:1023 */      append(array[0]);
/* 1024:1024 */      for (int i = 1; i < array.length; i++) {
/* 1025:1025 */        append(separator);
/* 1026:1026 */        append(array[i]);
/* 1027:     */      }
/* 1028:     */    }
/* 1029:1029 */    return this;
/* 1030:     */  }
/* 1031:     */  
/* 1041:     */  public StrBuilder appendWithSeparators(Iterable<?> iterable, String separator)
/* 1042:     */  {
/* 1043:1043 */    if (iterable != null) {
/* 1044:1044 */      separator = ObjectUtils.toString(separator);
/* 1045:1045 */      Iterator<?> it = iterable.iterator();
/* 1046:1046 */      while (it.hasNext()) {
/* 1047:1047 */        append(it.next());
/* 1048:1048 */        if (it.hasNext()) {
/* 1049:1049 */          append(separator);
/* 1050:     */        }
/* 1051:     */      }
/* 1052:     */    }
/* 1053:1053 */    return this;
/* 1054:     */  }
/* 1055:     */  
/* 1065:     */  public StrBuilder appendWithSeparators(Iterator<?> it, String separator)
/* 1066:     */  {
/* 1067:1067 */    if (it != null) {
/* 1068:1068 */      separator = ObjectUtils.toString(separator);
/* 1069:1069 */      while (it.hasNext()) {
/* 1070:1070 */        append(it.next());
/* 1071:1071 */        if (it.hasNext()) {
/* 1072:1072 */          append(separator);
/* 1073:     */        }
/* 1074:     */      }
/* 1075:     */    }
/* 1076:1076 */    return this;
/* 1077:     */  }
/* 1078:     */  
/* 1099:     */  public StrBuilder appendSeparator(String separator)
/* 1100:     */  {
/* 1101:1101 */    return appendSeparator(separator, null);
/* 1102:     */  }
/* 1103:     */  
/* 1130:     */  public StrBuilder appendSeparator(String standard, String defaultIfEmpty)
/* 1131:     */  {
/* 1132:1132 */    String str = isEmpty() ? defaultIfEmpty : standard;
/* 1133:1133 */    if (str != null) {
/* 1134:1134 */      append(str);
/* 1135:     */    }
/* 1136:1136 */    return this;
/* 1137:     */  }
/* 1138:     */  
/* 1157:     */  public StrBuilder appendSeparator(char separator)
/* 1158:     */  {
/* 1159:1159 */    if (size() > 0) {
/* 1160:1160 */      append(separator);
/* 1161:     */    }
/* 1162:1162 */    return this;
/* 1163:     */  }
/* 1164:     */  
/* 1175:     */  public StrBuilder appendSeparator(char standard, char defaultIfEmpty)
/* 1176:     */  {
/* 1177:1177 */    if (size() > 0) {
/* 1178:1178 */      append(standard);
/* 1179:     */    } else {
/* 1180:1180 */      append(defaultIfEmpty);
/* 1181:     */    }
/* 1182:1182 */    return this;
/* 1183:     */  }
/* 1184:     */  
/* 1204:     */  public StrBuilder appendSeparator(String separator, int loopIndex)
/* 1205:     */  {
/* 1206:1206 */    if ((separator != null) && (loopIndex > 0)) {
/* 1207:1207 */      append(separator);
/* 1208:     */    }
/* 1209:1209 */    return this;
/* 1210:     */  }
/* 1211:     */  
/* 1231:     */  public StrBuilder appendSeparator(char separator, int loopIndex)
/* 1232:     */  {
/* 1233:1233 */    if (loopIndex > 0) {
/* 1234:1234 */      append(separator);
/* 1235:     */    }
/* 1236:1236 */    return this;
/* 1237:     */  }
/* 1238:     */  
/* 1246:     */  public StrBuilder appendPadding(int length, char padChar)
/* 1247:     */  {
/* 1248:1248 */    if (length >= 0) {
/* 1249:1249 */      ensureCapacity(this.size + length);
/* 1250:1250 */      for (int i = 0; i < length; i++) {
/* 1251:1251 */        this.buffer[(this.size++)] = padChar;
/* 1252:     */      }
/* 1253:     */    }
/* 1254:1254 */    return this;
/* 1255:     */  }
/* 1256:     */  
/* 1268:     */  public StrBuilder appendFixedWidthPadLeft(Object obj, int width, char padChar)
/* 1269:     */  {
/* 1270:1270 */    if (width > 0) {
/* 1271:1271 */      ensureCapacity(this.size + width);
/* 1272:1272 */      String str = obj == null ? getNullText() : obj.toString();
/* 1273:1273 */      if (str == null) {
/* 1274:1274 */        str = "";
/* 1275:     */      }
/* 1276:1276 */      int strLen = str.length();
/* 1277:1277 */      if (strLen >= width) {
/* 1278:1278 */        str.getChars(strLen - width, strLen, this.buffer, this.size);
/* 1279:     */      } else {
/* 1280:1280 */        int padLen = width - strLen;
/* 1281:1281 */        for (int i = 0; i < padLen; i++) {
/* 1282:1282 */          this.buffer[(this.size + i)] = padChar;
/* 1283:     */        }
/* 1284:1284 */        str.getChars(0, strLen, this.buffer, this.size + padLen);
/* 1285:     */      }
/* 1286:1286 */      this.size += width;
/* 1287:     */    }
/* 1288:1288 */    return this;
/* 1289:     */  }
/* 1290:     */  
/* 1300:     */  public StrBuilder appendFixedWidthPadLeft(int value, int width, char padChar)
/* 1301:     */  {
/* 1302:1302 */    return appendFixedWidthPadLeft(String.valueOf(value), width, padChar);
/* 1303:     */  }
/* 1304:     */  
/* 1315:     */  public StrBuilder appendFixedWidthPadRight(Object obj, int width, char padChar)
/* 1316:     */  {
/* 1317:1317 */    if (width > 0) {
/* 1318:1318 */      ensureCapacity(this.size + width);
/* 1319:1319 */      String str = obj == null ? getNullText() : obj.toString();
/* 1320:1320 */      if (str == null) {
/* 1321:1321 */        str = "";
/* 1322:     */      }
/* 1323:1323 */      int strLen = str.length();
/* 1324:1324 */      if (strLen >= width) {
/* 1325:1325 */        str.getChars(0, width, this.buffer, this.size);
/* 1326:     */      } else {
/* 1327:1327 */        int padLen = width - strLen;
/* 1328:1328 */        str.getChars(0, strLen, this.buffer, this.size);
/* 1329:1329 */        for (int i = 0; i < padLen; i++) {
/* 1330:1330 */          this.buffer[(this.size + strLen + i)] = padChar;
/* 1331:     */        }
/* 1332:     */      }
/* 1333:1333 */      this.size += width;
/* 1334:     */    }
/* 1335:1335 */    return this;
/* 1336:     */  }
/* 1337:     */  
/* 1347:     */  public StrBuilder appendFixedWidthPadRight(int value, int width, char padChar)
/* 1348:     */  {
/* 1349:1349 */    return appendFixedWidthPadRight(String.valueOf(value), width, padChar);
/* 1350:     */  }
/* 1351:     */  
/* 1361:     */  public StrBuilder insert(int index, Object obj)
/* 1362:     */  {
/* 1363:1363 */    if (obj == null) {
/* 1364:1364 */      return insert(index, this.nullText);
/* 1365:     */    }
/* 1366:1366 */    return insert(index, obj.toString());
/* 1367:     */  }
/* 1368:     */  
/* 1378:     */  public StrBuilder insert(int index, String str)
/* 1379:     */  {
/* 1380:1380 */    validateIndex(index);
/* 1381:1381 */    if (str == null) {
/* 1382:1382 */      str = this.nullText;
/* 1383:     */    }
/* 1384:1384 */    int strLen = str == null ? 0 : str.length();
/* 1385:1385 */    if (strLen > 0) {
/* 1386:1386 */      int newSize = this.size + strLen;
/* 1387:1387 */      ensureCapacity(newSize);
/* 1388:1388 */      System.arraycopy(this.buffer, index, this.buffer, index + strLen, this.size - index);
/* 1389:1389 */      this.size = newSize;
/* 1390:1390 */      str.getChars(0, strLen, this.buffer, index);
/* 1391:     */    }
/* 1392:1392 */    return this;
/* 1393:     */  }
/* 1394:     */  
/* 1403:     */  public StrBuilder insert(int index, char[] chars)
/* 1404:     */  {
/* 1405:1405 */    validateIndex(index);
/* 1406:1406 */    if (chars == null) {
/* 1407:1407 */      return insert(index, this.nullText);
/* 1408:     */    }
/* 1409:1409 */    int len = chars.length;
/* 1410:1410 */    if (len > 0) {
/* 1411:1411 */      ensureCapacity(this.size + len);
/* 1412:1412 */      System.arraycopy(this.buffer, index, this.buffer, index + len, this.size - index);
/* 1413:1413 */      System.arraycopy(chars, 0, this.buffer, index, len);
/* 1414:1414 */      this.size += len;
/* 1415:     */    }
/* 1416:1416 */    return this;
/* 1417:     */  }
/* 1418:     */  
/* 1429:     */  public StrBuilder insert(int index, char[] chars, int offset, int length)
/* 1430:     */  {
/* 1431:1431 */    validateIndex(index);
/* 1432:1432 */    if (chars == null) {
/* 1433:1433 */      return insert(index, this.nullText);
/* 1434:     */    }
/* 1435:1435 */    if ((offset < 0) || (offset > chars.length)) {
/* 1436:1436 */      throw new StringIndexOutOfBoundsException("Invalid offset: " + offset);
/* 1437:     */    }
/* 1438:1438 */    if ((length < 0) || (offset + length > chars.length)) {
/* 1439:1439 */      throw new StringIndexOutOfBoundsException("Invalid length: " + length);
/* 1440:     */    }
/* 1441:1441 */    if (length > 0) {
/* 1442:1442 */      ensureCapacity(this.size + length);
/* 1443:1443 */      System.arraycopy(this.buffer, index, this.buffer, index + length, this.size - index);
/* 1444:1444 */      System.arraycopy(chars, offset, this.buffer, index, length);
/* 1445:1445 */      this.size += length;
/* 1446:     */    }
/* 1447:1447 */    return this;
/* 1448:     */  }
/* 1449:     */  
/* 1457:     */  public StrBuilder insert(int index, boolean value)
/* 1458:     */  {
/* 1459:1459 */    validateIndex(index);
/* 1460:1460 */    if (value) {
/* 1461:1461 */      ensureCapacity(this.size + 4);
/* 1462:1462 */      System.arraycopy(this.buffer, index, this.buffer, index + 4, this.size - index);
/* 1463:1463 */      this.buffer[(index++)] = 't';
/* 1464:1464 */      this.buffer[(index++)] = 'r';
/* 1465:1465 */      this.buffer[(index++)] = 'u';
/* 1466:1466 */      this.buffer[index] = 'e';
/* 1467:1467 */      this.size += 4;
/* 1468:     */    } else {
/* 1469:1469 */      ensureCapacity(this.size + 5);
/* 1470:1470 */      System.arraycopy(this.buffer, index, this.buffer, index + 5, this.size - index);
/* 1471:1471 */      this.buffer[(index++)] = 'f';
/* 1472:1472 */      this.buffer[(index++)] = 'a';
/* 1473:1473 */      this.buffer[(index++)] = 'l';
/* 1474:1474 */      this.buffer[(index++)] = 's';
/* 1475:1475 */      this.buffer[index] = 'e';
/* 1476:1476 */      this.size += 5;
/* 1477:     */    }
/* 1478:1478 */    return this;
/* 1479:     */  }
/* 1480:     */  
/* 1488:     */  public StrBuilder insert(int index, char value)
/* 1489:     */  {
/* 1490:1490 */    validateIndex(index);
/* 1491:1491 */    ensureCapacity(this.size + 1);
/* 1492:1492 */    System.arraycopy(this.buffer, index, this.buffer, index + 1, this.size - index);
/* 1493:1493 */    this.buffer[index] = value;
/* 1494:1494 */    this.size += 1;
/* 1495:1495 */    return this;
/* 1496:     */  }
/* 1497:     */  
/* 1505:     */  public StrBuilder insert(int index, int value)
/* 1506:     */  {
/* 1507:1507 */    return insert(index, String.valueOf(value));
/* 1508:     */  }
/* 1509:     */  
/* 1517:     */  public StrBuilder insert(int index, long value)
/* 1518:     */  {
/* 1519:1519 */    return insert(index, String.valueOf(value));
/* 1520:     */  }
/* 1521:     */  
/* 1529:     */  public StrBuilder insert(int index, float value)
/* 1530:     */  {
/* 1531:1531 */    return insert(index, String.valueOf(value));
/* 1532:     */  }
/* 1533:     */  
/* 1541:     */  public StrBuilder insert(int index, double value)
/* 1542:     */  {
/* 1543:1543 */    return insert(index, String.valueOf(value));
/* 1544:     */  }
/* 1545:     */  
/* 1554:     */  private void deleteImpl(int startIndex, int endIndex, int len)
/* 1555:     */  {
/* 1556:1556 */    System.arraycopy(this.buffer, endIndex, this.buffer, startIndex, this.size - endIndex);
/* 1557:1557 */    this.size -= len;
/* 1558:     */  }
/* 1559:     */  
/* 1568:     */  public StrBuilder delete(int startIndex, int endIndex)
/* 1569:     */  {
/* 1570:1570 */    endIndex = validateRange(startIndex, endIndex);
/* 1571:1571 */    int len = endIndex - startIndex;
/* 1572:1572 */    if (len > 0) {
/* 1573:1573 */      deleteImpl(startIndex, endIndex, len);
/* 1574:     */    }
/* 1575:1575 */    return this;
/* 1576:     */  }
/* 1577:     */  
/* 1584:     */  public StrBuilder deleteAll(char ch)
/* 1585:     */  {
/* 1586:1586 */    for (int i = 0; i < this.size; i++) {
/* 1587:1587 */      if (this.buffer[i] == ch) {
/* 1588:1588 */        int start = i;
/* 1589:1589 */        for (;;) { i++; if (i < this.size) {
/* 1590:1590 */            if (this.buffer[i] != ch)
/* 1591:1591 */              break;
/* 1592:     */          }
/* 1593:     */        }
/* 1594:1594 */        int len = i - start;
/* 1595:1595 */        deleteImpl(start, i, len);
/* 1596:1596 */        i -= len;
/* 1597:     */      }
/* 1598:     */    }
/* 1599:1599 */    return this;
/* 1600:     */  }
/* 1601:     */  
/* 1607:     */  public StrBuilder deleteFirst(char ch)
/* 1608:     */  {
/* 1609:1609 */    for (int i = 0; i < this.size; i++) {
/* 1610:1610 */      if (this.buffer[i] == ch) {
/* 1611:1611 */        deleteImpl(i, i + 1, 1);
/* 1612:1612 */        break;
/* 1613:     */      }
/* 1614:     */    }
/* 1615:1615 */    return this;
/* 1616:     */  }
/* 1617:     */  
/* 1624:     */  public StrBuilder deleteAll(String str)
/* 1625:     */  {
/* 1626:1626 */    int len = str == null ? 0 : str.length();
/* 1627:1627 */    if (len > 0) {
/* 1628:1628 */      int index = indexOf(str, 0);
/* 1629:1629 */      while (index >= 0) {
/* 1630:1630 */        deleteImpl(index, index + len, len);
/* 1631:1631 */        index = indexOf(str, index);
/* 1632:     */      }
/* 1633:     */    }
/* 1634:1634 */    return this;
/* 1635:     */  }
/* 1636:     */  
/* 1642:     */  public StrBuilder deleteFirst(String str)
/* 1643:     */  {
/* 1644:1644 */    int len = str == null ? 0 : str.length();
/* 1645:1645 */    if (len > 0) {
/* 1646:1646 */      int index = indexOf(str, 0);
/* 1647:1647 */      if (index >= 0) {
/* 1648:1648 */        deleteImpl(index, index + len, len);
/* 1649:     */      }
/* 1650:     */    }
/* 1651:1651 */    return this;
/* 1652:     */  }
/* 1653:     */  
/* 1664:     */  public StrBuilder deleteAll(StrMatcher matcher)
/* 1665:     */  {
/* 1666:1666 */    return replace(matcher, null, 0, this.size, -1);
/* 1667:     */  }
/* 1668:     */  
/* 1678:     */  public StrBuilder deleteFirst(StrMatcher matcher)
/* 1679:     */  {
/* 1680:1680 */    return replace(matcher, null, 0, this.size, 1);
/* 1681:     */  }
/* 1682:     */  
/* 1693:     */  private void replaceImpl(int startIndex, int endIndex, int removeLen, String insertStr, int insertLen)
/* 1694:     */  {
/* 1695:1695 */    int newSize = this.size - removeLen + insertLen;
/* 1696:1696 */    if (insertLen != removeLen) {
/* 1697:1697 */      ensureCapacity(newSize);
/* 1698:1698 */      System.arraycopy(this.buffer, endIndex, this.buffer, startIndex + insertLen, this.size - endIndex);
/* 1699:1699 */      this.size = newSize;
/* 1700:     */    }
/* 1701:1701 */    if (insertLen > 0) {
/* 1702:1702 */      insertStr.getChars(0, insertLen, this.buffer, startIndex);
/* 1703:     */    }
/* 1704:     */  }
/* 1705:     */  
/* 1716:     */  public StrBuilder replace(int startIndex, int endIndex, String replaceStr)
/* 1717:     */  {
/* 1718:1718 */    endIndex = validateRange(startIndex, endIndex);
/* 1719:1719 */    int insertLen = replaceStr == null ? 0 : replaceStr.length();
/* 1720:1720 */    replaceImpl(startIndex, endIndex, endIndex - startIndex, replaceStr, insertLen);
/* 1721:1721 */    return this;
/* 1722:     */  }
/* 1723:     */  
/* 1732:     */  public StrBuilder replaceAll(char search, char replace)
/* 1733:     */  {
/* 1734:1734 */    if (search != replace) {
/* 1735:1735 */      for (int i = 0; i < this.size; i++) {
/* 1736:1736 */        if (this.buffer[i] == search) {
/* 1737:1737 */          this.buffer[i] = replace;
/* 1738:     */        }
/* 1739:     */      }
/* 1740:     */    }
/* 1741:1741 */    return this;
/* 1742:     */  }
/* 1743:     */  
/* 1751:     */  public StrBuilder replaceFirst(char search, char replace)
/* 1752:     */  {
/* 1753:1753 */    if (search != replace) {
/* 1754:1754 */      for (int i = 0; i < this.size; i++) {
/* 1755:1755 */        if (this.buffer[i] == search) {
/* 1756:1756 */          this.buffer[i] = replace;
/* 1757:1757 */          break;
/* 1758:     */        }
/* 1759:     */      }
/* 1760:     */    }
/* 1761:1761 */    return this;
/* 1762:     */  }
/* 1763:     */  
/* 1771:     */  public StrBuilder replaceAll(String searchStr, String replaceStr)
/* 1772:     */  {
/* 1773:1773 */    int searchLen = searchStr == null ? 0 : searchStr.length();
/* 1774:1774 */    if (searchLen > 0) {
/* 1775:1775 */      int replaceLen = replaceStr == null ? 0 : replaceStr.length();
/* 1776:1776 */      int index = indexOf(searchStr, 0);
/* 1777:1777 */      while (index >= 0) {
/* 1778:1778 */        replaceImpl(index, index + searchLen, searchLen, replaceStr, replaceLen);
/* 1779:1779 */        index = indexOf(searchStr, index + replaceLen);
/* 1780:     */      }
/* 1781:     */    }
/* 1782:1782 */    return this;
/* 1783:     */  }
/* 1784:     */  
/* 1791:     */  public StrBuilder replaceFirst(String searchStr, String replaceStr)
/* 1792:     */  {
/* 1793:1793 */    int searchLen = searchStr == null ? 0 : searchStr.length();
/* 1794:1794 */    if (searchLen > 0) {
/* 1795:1795 */      int index = indexOf(searchStr, 0);
/* 1796:1796 */      if (index >= 0) {
/* 1797:1797 */        int replaceLen = replaceStr == null ? 0 : replaceStr.length();
/* 1798:1798 */        replaceImpl(index, index + searchLen, searchLen, replaceStr, replaceLen);
/* 1799:     */      }
/* 1800:     */    }
/* 1801:1801 */    return this;
/* 1802:     */  }
/* 1803:     */  
/* 1815:     */  public StrBuilder replaceAll(StrMatcher matcher, String replaceStr)
/* 1816:     */  {
/* 1817:1817 */    return replace(matcher, replaceStr, 0, this.size, -1);
/* 1818:     */  }
/* 1819:     */  
/* 1830:     */  public StrBuilder replaceFirst(StrMatcher matcher, String replaceStr)
/* 1831:     */  {
/* 1832:1832 */    return replace(matcher, replaceStr, 0, this.size, 1);
/* 1833:     */  }
/* 1834:     */  
/* 1853:     */  public StrBuilder replace(StrMatcher matcher, String replaceStr, int startIndex, int endIndex, int replaceCount)
/* 1854:     */  {
/* 1855:1855 */    endIndex = validateRange(startIndex, endIndex);
/* 1856:1856 */    return replaceImpl(matcher, replaceStr, startIndex, endIndex, replaceCount);
/* 1857:     */  }
/* 1858:     */  
/* 1875:     */  private StrBuilder replaceImpl(StrMatcher matcher, String replaceStr, int from, int to, int replaceCount)
/* 1876:     */  {
/* 1877:1877 */    if ((matcher == null) || (this.size == 0)) {
/* 1878:1878 */      return this;
/* 1879:     */    }
/* 1880:1880 */    int replaceLen = replaceStr == null ? 0 : replaceStr.length();
/* 1881:1881 */    char[] buf = this.buffer;
/* 1882:1882 */    for (int i = from; (i < to) && (replaceCount != 0); i++) {
/* 1883:1883 */      int removeLen = matcher.isMatch(buf, i, from, to);
/* 1884:1884 */      if (removeLen > 0) {
/* 1885:1885 */        replaceImpl(i, i + removeLen, removeLen, replaceStr, replaceLen);
/* 1886:1886 */        to = to - removeLen + replaceLen;
/* 1887:1887 */        i = i + replaceLen - 1;
/* 1888:1888 */        if (replaceCount > 0) {
/* 1889:1889 */          replaceCount--;
/* 1890:     */        }
/* 1891:     */      }
/* 1892:     */    }
/* 1893:1893 */    return this;
/* 1894:     */  }
/* 1895:     */  
/* 1901:     */  public StrBuilder reverse()
/* 1902:     */  {
/* 1903:1903 */    if (this.size == 0) {
/* 1904:1904 */      return this;
/* 1905:     */    }
/* 1906:     */    
/* 1907:1907 */    int half = this.size / 2;
/* 1908:1908 */    char[] buf = this.buffer;
/* 1909:1909 */    int leftIdx = 0; for (int rightIdx = this.size - 1; leftIdx < half; rightIdx--) {
/* 1910:1910 */      char swap = buf[leftIdx];
/* 1911:1911 */      buf[leftIdx] = buf[rightIdx];
/* 1912:1912 */      buf[rightIdx] = swap;leftIdx++;
/* 1913:     */    }
/* 1914:     */    
/* 1917:1914 */    return this;
/* 1918:     */  }
/* 1919:     */  
/* 1926:     */  public StrBuilder trim()
/* 1927:     */  {
/* 1928:1925 */    if (this.size == 0) {
/* 1929:1926 */      return this;
/* 1930:     */    }
/* 1931:1928 */    int len = this.size;
/* 1932:1929 */    char[] buf = this.buffer;
/* 1933:1930 */    int pos = 0;
/* 1934:1931 */    while ((pos < len) && (buf[pos] <= ' ')) {
/* 1935:1932 */      pos++;
/* 1936:     */    }
/* 1937:1934 */    while ((pos < len) && (buf[(len - 1)] <= ' ')) {
/* 1938:1935 */      len--;
/* 1939:     */    }
/* 1940:1937 */    if (len < this.size) {
/* 1941:1938 */      delete(len, this.size);
/* 1942:     */    }
/* 1943:1940 */    if (pos > 0) {
/* 1944:1941 */      delete(0, pos);
/* 1945:     */    }
/* 1946:1943 */    return this;
/* 1947:     */  }
/* 1948:     */  
/* 1957:     */  public boolean startsWith(String str)
/* 1958:     */  {
/* 1959:1956 */    if (str == null) {
/* 1960:1957 */      return false;
/* 1961:     */    }
/* 1962:1959 */    int len = str.length();
/* 1963:1960 */    if (len == 0) {
/* 1964:1961 */      return true;
/* 1965:     */    }
/* 1966:1963 */    if (len > this.size) {
/* 1967:1964 */      return false;
/* 1968:     */    }
/* 1969:1966 */    for (int i = 0; i < len; i++) {
/* 1970:1967 */      if (this.buffer[i] != str.charAt(i)) {
/* 1971:1968 */        return false;
/* 1972:     */      }
/* 1973:     */    }
/* 1974:1971 */    return true;
/* 1975:     */  }
/* 1976:     */  
/* 1984:     */  public boolean endsWith(String str)
/* 1985:     */  {
/* 1986:1983 */    if (str == null) {
/* 1987:1984 */      return false;
/* 1988:     */    }
/* 1989:1986 */    int len = str.length();
/* 1990:1987 */    if (len == 0) {
/* 1991:1988 */      return true;
/* 1992:     */    }
/* 1993:1990 */    if (len > this.size) {
/* 1994:1991 */      return false;
/* 1995:     */    }
/* 1996:1993 */    int pos = this.size - len;
/* 1997:1994 */    for (int i = 0; i < len; pos++) {
/* 1998:1995 */      if (this.buffer[pos] != str.charAt(i)) {
/* 1999:1996 */        return false;
/* 2000:     */      }
/* 2001:1994 */      i++;
/* 2002:     */    }
/* 2003:     */    
/* 2006:1999 */    return true;
/* 2007:     */  }
/* 2008:     */  
/* 2013:     */  public CharSequence subSequence(int startIndex, int endIndex)
/* 2014:     */  {
/* 2015:2008 */    if (startIndex < 0) {
/* 2016:2009 */      throw new StringIndexOutOfBoundsException(startIndex);
/* 2017:     */    }
/* 2018:2011 */    if (endIndex > this.size) {
/* 2019:2012 */      throw new StringIndexOutOfBoundsException(endIndex);
/* 2020:     */    }
/* 2021:2014 */    if (startIndex > endIndex) {
/* 2022:2015 */      throw new StringIndexOutOfBoundsException(endIndex - startIndex);
/* 2023:     */    }
/* 2024:2017 */    return substring(startIndex, endIndex);
/* 2025:     */  }
/* 2026:     */  
/* 2033:     */  public String substring(int start)
/* 2034:     */  {
/* 2035:2028 */    return substring(start, this.size);
/* 2036:     */  }
/* 2037:     */  
/* 2050:     */  public String substring(int startIndex, int endIndex)
/* 2051:     */  {
/* 2052:2045 */    endIndex = validateRange(startIndex, endIndex);
/* 2053:2046 */    return new String(this.buffer, startIndex, endIndex - startIndex);
/* 2054:     */  }
/* 2055:     */  
/* 2067:     */  public String leftString(int length)
/* 2068:     */  {
/* 2069:2062 */    if (length <= 0)
/* 2070:2063 */      return "";
/* 2071:2064 */    if (length >= this.size) {
/* 2072:2065 */      return new String(this.buffer, 0, this.size);
/* 2073:     */    }
/* 2074:2067 */    return new String(this.buffer, 0, length);
/* 2075:     */  }
/* 2076:     */  
/* 2089:     */  public String rightString(int length)
/* 2090:     */  {
/* 2091:2084 */    if (length <= 0)
/* 2092:2085 */      return "";
/* 2093:2086 */    if (length >= this.size) {
/* 2094:2087 */      return new String(this.buffer, 0, this.size);
/* 2095:     */    }
/* 2096:2089 */    return new String(this.buffer, this.size - length, length);
/* 2097:     */  }
/* 2098:     */  
/* 2115:     */  public String midString(int index, int length)
/* 2116:     */  {
/* 2117:2110 */    if (index < 0) {
/* 2118:2111 */      index = 0;
/* 2119:     */    }
/* 2120:2113 */    if ((length <= 0) || (index >= this.size)) {
/* 2121:2114 */      return "";
/* 2122:     */    }
/* 2123:2116 */    if (this.size <= index + length) {
/* 2124:2117 */      return new String(this.buffer, index, this.size - index);
/* 2125:     */    }
/* 2126:2119 */    return new String(this.buffer, index, length);
/* 2127:     */  }
/* 2128:     */  
/* 2136:     */  public boolean contains(char ch)
/* 2137:     */  {
/* 2138:2131 */    char[] thisBuf = this.buffer;
/* 2139:2132 */    for (int i = 0; i < this.size; i++) {
/* 2140:2133 */      if (thisBuf[i] == ch) {
/* 2141:2134 */        return true;
/* 2142:     */      }
/* 2143:     */    }
/* 2144:2137 */    return false;
/* 2145:     */  }
/* 2146:     */  
/* 2152:     */  public boolean contains(String str)
/* 2153:     */  {
/* 2154:2147 */    return indexOf(str, 0) >= 0;
/* 2155:     */  }
/* 2156:     */  
/* 2167:     */  public boolean contains(StrMatcher matcher)
/* 2168:     */  {
/* 2169:2162 */    return indexOf(matcher, 0) >= 0;
/* 2170:     */  }
/* 2171:     */  
/* 2178:     */  public int indexOf(char ch)
/* 2179:     */  {
/* 2180:2173 */    return indexOf(ch, 0);
/* 2181:     */  }
/* 2182:     */  
/* 2189:     */  public int indexOf(char ch, int startIndex)
/* 2190:     */  {
/* 2191:2184 */    startIndex = startIndex < 0 ? 0 : startIndex;
/* 2192:2185 */    if (startIndex >= this.size) {
/* 2193:2186 */      return -1;
/* 2194:     */    }
/* 2195:2188 */    char[] thisBuf = this.buffer;
/* 2196:2189 */    for (int i = startIndex; i < this.size; i++) {
/* 2197:2190 */      if (thisBuf[i] == ch) {
/* 2198:2191 */        return i;
/* 2199:     */      }
/* 2200:     */    }
/* 2201:2194 */    return -1;
/* 2202:     */  }
/* 2203:     */  
/* 2211:     */  public int indexOf(String str)
/* 2212:     */  {
/* 2213:2206 */    return indexOf(str, 0);
/* 2214:     */  }
/* 2215:     */  
/* 2225:     */  public int indexOf(String str, int startIndex)
/* 2226:     */  {
/* 2227:2220 */    startIndex = startIndex < 0 ? 0 : startIndex;
/* 2228:2221 */    if ((str == null) || (startIndex >= this.size)) {
/* 2229:2222 */      return -1;
/* 2230:     */    }
/* 2231:2224 */    int strLen = str.length();
/* 2232:2225 */    if (strLen == 1) {
/* 2233:2226 */      return indexOf(str.charAt(0), startIndex);
/* 2234:     */    }
/* 2235:2228 */    if (strLen == 0) {
/* 2236:2229 */      return startIndex;
/* 2237:     */    }
/* 2238:2231 */    if (strLen > this.size) {
/* 2239:2232 */      return -1;
/* 2240:     */    }
/* 2241:2234 */    char[] thisBuf = this.buffer;
/* 2242:2235 */    int len = this.size - strLen + 1;
/* 2243:     */    label125:
/* 2244:2237 */    for (int i = startIndex; i < len; i++) {
/* 2245:2238 */      for (int j = 0; j < strLen; j++) {
/* 2246:2239 */        if (str.charAt(j) != thisBuf[(i + j)]) {
/* 2247:     */          break label125;
/* 2248:     */        }
/* 2249:     */      }
/* 2250:2243 */      return i;
/* 2251:     */    }
/* 2252:2245 */    return -1;
/* 2253:     */  }
/* 2254:     */  
/* 2264:     */  public int indexOf(StrMatcher matcher)
/* 2265:     */  {
/* 2266:2259 */    return indexOf(matcher, 0);
/* 2267:     */  }
/* 2268:     */  
/* 2280:     */  public int indexOf(StrMatcher matcher, int startIndex)
/* 2281:     */  {
/* 2282:2275 */    startIndex = startIndex < 0 ? 0 : startIndex;
/* 2283:2276 */    if ((matcher == null) || (startIndex >= this.size)) {
/* 2284:2277 */      return -1;
/* 2285:     */    }
/* 2286:2279 */    int len = this.size;
/* 2287:2280 */    char[] buf = this.buffer;
/* 2288:2281 */    for (int i = startIndex; i < len; i++) {
/* 2289:2282 */      if (matcher.isMatch(buf, i, startIndex, len) > 0) {
/* 2290:2283 */        return i;
/* 2291:     */      }
/* 2292:     */    }
/* 2293:2286 */    return -1;
/* 2294:     */  }
/* 2295:     */  
/* 2302:     */  public int lastIndexOf(char ch)
/* 2303:     */  {
/* 2304:2297 */    return lastIndexOf(ch, this.size - 1);
/* 2305:     */  }
/* 2306:     */  
/* 2313:     */  public int lastIndexOf(char ch, int startIndex)
/* 2314:     */  {
/* 2315:2308 */    startIndex = startIndex >= this.size ? this.size - 1 : startIndex;
/* 2316:2309 */    if (startIndex < 0) {
/* 2317:2310 */      return -1;
/* 2318:     */    }
/* 2319:2312 */    for (int i = startIndex; i >= 0; i--) {
/* 2320:2313 */      if (this.buffer[i] == ch) {
/* 2321:2314 */        return i;
/* 2322:     */      }
/* 2323:     */    }
/* 2324:2317 */    return -1;
/* 2325:     */  }
/* 2326:     */  
/* 2334:     */  public int lastIndexOf(String str)
/* 2335:     */  {
/* 2336:2329 */    return lastIndexOf(str, this.size - 1);
/* 2337:     */  }
/* 2338:     */  
/* 2348:     */  public int lastIndexOf(String str, int startIndex)
/* 2349:     */  {
/* 2350:2343 */    startIndex = startIndex >= this.size ? this.size - 1 : startIndex;
/* 2351:2344 */    if ((str == null) || (startIndex < 0)) {
/* 2352:2345 */      return -1;
/* 2353:     */    }
/* 2354:2347 */    int strLen = str.length();
/* 2355:2348 */    if ((strLen > 0) && (strLen <= this.size)) {
/* 2356:2349 */      if (strLen == 1) {
/* 2357:2350 */        return lastIndexOf(str.charAt(0), startIndex);
/* 2358:     */      }
/* 2359:     */      
/* 2360:     */      label114:
/* 2361:2354 */      for (int i = startIndex - strLen + 1; i >= 0; i--) {
/* 2362:2355 */        for (int j = 0; j < strLen; j++) {
/* 2363:2356 */          if (str.charAt(j) != this.buffer[(i + j)]) {
/* 2364:     */            break label114;
/* 2365:     */          }
/* 2366:     */        }
/* 2367:2360 */        return i;
/* 2368:     */      }
/* 2369:     */    }
/* 2370:2363 */    else if (strLen == 0) {
/* 2371:2364 */      return startIndex;
/* 2372:     */    }
/* 2373:2366 */    return -1;
/* 2374:     */  }
/* 2375:     */  
/* 2385:     */  public int lastIndexOf(StrMatcher matcher)
/* 2386:     */  {
/* 2387:2380 */    return lastIndexOf(matcher, this.size);
/* 2388:     */  }
/* 2389:     */  
/* 2401:     */  public int lastIndexOf(StrMatcher matcher, int startIndex)
/* 2402:     */  {
/* 2403:2396 */    startIndex = startIndex >= this.size ? this.size - 1 : startIndex;
/* 2404:2397 */    if ((matcher == null) || (startIndex < 0)) {
/* 2405:2398 */      return -1;
/* 2406:     */    }
/* 2407:2400 */    char[] buf = this.buffer;
/* 2408:2401 */    int endIndex = startIndex + 1;
/* 2409:2402 */    for (int i = startIndex; i >= 0; i--) {
/* 2410:2403 */      if (matcher.isMatch(buf, i, 0, endIndex) > 0) {
/* 2411:2404 */        return i;
/* 2412:     */      }
/* 2413:     */    }
/* 2414:2407 */    return -1;
/* 2415:     */  }
/* 2416:     */  
/* 2449:     */  public StrTokenizer asTokenizer()
/* 2450:     */  {
/* 2451:2444 */    return new StrBuilderTokenizer();
/* 2452:     */  }
/* 2453:     */  
/* 2473:     */  public Reader asReader()
/* 2474:     */  {
/* 2475:2468 */    return new StrBuilderReader();
/* 2476:     */  }
/* 2477:     */  
/* 2498:     */  public Writer asWriter()
/* 2499:     */  {
/* 2500:2493 */    return new StrBuilderWriter();
/* 2501:     */  }
/* 2502:     */  
/* 2540:     */  public boolean equalsIgnoreCase(StrBuilder other)
/* 2541:     */  {
/* 2542:2535 */    if (this == other) {
/* 2543:2536 */      return true;
/* 2544:     */    }
/* 2545:2538 */    if (this.size != other.size) {
/* 2546:2539 */      return false;
/* 2547:     */    }
/* 2548:2541 */    char[] thisBuf = this.buffer;
/* 2549:2542 */    char[] otherBuf = other.buffer;
/* 2550:2543 */    for (int i = this.size - 1; i >= 0; i--) {
/* 2551:2544 */      char c1 = thisBuf[i];
/* 2552:2545 */      char c2 = otherBuf[i];
/* 2553:2546 */      if ((c1 != c2) && (Character.toUpperCase(c1) != Character.toUpperCase(c2))) {
/* 2554:2547 */        return false;
/* 2555:     */      }
/* 2556:     */    }
/* 2557:2550 */    return true;
/* 2558:     */  }
/* 2559:     */  
/* 2566:     */  public boolean equals(StrBuilder other)
/* 2567:     */  {
/* 2568:2561 */    if (this == other) {
/* 2569:2562 */      return true;
/* 2570:     */    }
/* 2571:2564 */    if (this.size != other.size) {
/* 2572:2565 */      return false;
/* 2573:     */    }
/* 2574:2567 */    char[] thisBuf = this.buffer;
/* 2575:2568 */    char[] otherBuf = other.buffer;
/* 2576:2569 */    for (int i = this.size - 1; i >= 0; i--) {
/* 2577:2570 */      if (thisBuf[i] != otherBuf[i]) {
/* 2578:2571 */        return false;
/* 2579:     */      }
/* 2580:     */    }
/* 2581:2574 */    return true;
/* 2582:     */  }
/* 2583:     */  
/* 2591:     */  public boolean equals(Object obj)
/* 2592:     */  {
/* 2593:2586 */    if ((obj instanceof StrBuilder)) {
/* 2594:2587 */      return equals((StrBuilder)obj);
/* 2595:     */    }
/* 2596:2589 */    return false;
/* 2597:     */  }
/* 2598:     */  
/* 2604:     */  public int hashCode()
/* 2605:     */  {
/* 2606:2599 */    char[] buf = this.buffer;
/* 2607:2600 */    int hash = 0;
/* 2608:2601 */    for (int i = this.size - 1; i >= 0; i--) {
/* 2609:2602 */      hash = 31 * hash + buf[i];
/* 2610:     */    }
/* 2611:2604 */    return hash;
/* 2612:     */  }
/* 2613:     */  
/* 2624:     */  public String toString()
/* 2625:     */  {
/* 2626:2619 */    return new String(this.buffer, 0, this.size);
/* 2627:     */  }
/* 2628:     */  
/* 2634:     */  public StringBuffer toStringBuffer()
/* 2635:     */  {
/* 2636:2629 */    return new StringBuffer(this.size).append(this.buffer, 0, this.size);
/* 2637:     */  }
/* 2638:     */  
/* 2648:     */  protected int validateRange(int startIndex, int endIndex)
/* 2649:     */  {
/* 2650:2643 */    if (startIndex < 0) {
/* 2651:2644 */      throw new StringIndexOutOfBoundsException(startIndex);
/* 2652:     */    }
/* 2653:2646 */    if (endIndex > this.size) {
/* 2654:2647 */      endIndex = this.size;
/* 2655:     */    }
/* 2656:2649 */    if (startIndex > endIndex) {
/* 2657:2650 */      throw new StringIndexOutOfBoundsException("end < start");
/* 2658:     */    }
/* 2659:2652 */    return endIndex;
/* 2660:     */  }
/* 2661:     */  
/* 2667:     */  protected void validateIndex(int index)
/* 2668:     */  {
/* 2669:2662 */    if ((index < 0) || (index > this.size)) {
/* 2670:2663 */      throw new StringIndexOutOfBoundsException(index);
/* 2671:     */    }
/* 2672:     */  }
/* 2673:     */  
/* 2679:     */  class StrBuilderTokenizer
/* 2680:     */    extends StrTokenizer
/* 2681:     */  {
/* 2682:     */    StrBuilderTokenizer() {}
/* 2683:     */    
/* 2688:     */    protected List<String> tokenize(char[] chars, int offset, int count)
/* 2689:     */    {
/* 2690:2683 */      if (chars == null) {
/* 2691:2684 */        return super.tokenize(StrBuilder.this.buffer, 0, StrBuilder.this.size());
/* 2692:     */      }
/* 2693:2686 */      return super.tokenize(chars, offset, count);
/* 2694:     */    }
/* 2695:     */    
/* 2698:     */    public String getContent()
/* 2699:     */    {
/* 2700:2693 */      String str = super.getContent();
/* 2701:2694 */      if (str == null) {
/* 2702:2695 */        return StrBuilder.this.toString();
/* 2703:     */      }
/* 2704:2697 */      return str;
/* 2705:     */    }
/* 2706:     */  }
/* 2707:     */  
/* 2711:     */  class StrBuilderReader
/* 2712:     */    extends Reader
/* 2713:     */  {
/* 2714:     */    private int pos;
/* 2715:     */    
/* 2718:     */    private int mark;
/* 2719:     */    
/* 2723:     */    StrBuilderReader() {}
/* 2724:     */    
/* 2728:     */    public void close() {}
/* 2729:     */    
/* 2733:     */    public int read()
/* 2734:     */    {
/* 2735:2728 */      if (!ready()) {
/* 2736:2729 */        return -1;
/* 2737:     */      }
/* 2738:2731 */      return StrBuilder.this.charAt(this.pos++);
/* 2739:     */    }
/* 2740:     */    
/* 2742:     */    public int read(char[] b, int off, int len)
/* 2743:     */    {
/* 2744:2737 */      if ((off < 0) || (len < 0) || (off > b.length) || (off + len > b.length) || (off + len < 0))
/* 2745:     */      {
/* 2746:2739 */        throw new IndexOutOfBoundsException();
/* 2747:     */      }
/* 2748:2741 */      if (len == 0) {
/* 2749:2742 */        return 0;
/* 2750:     */      }
/* 2751:2744 */      if (this.pos >= StrBuilder.this.size()) {
/* 2752:2745 */        return -1;
/* 2753:     */      }
/* 2754:2747 */      if (this.pos + len > StrBuilder.this.size()) {
/* 2755:2748 */        len = StrBuilder.this.size() - this.pos;
/* 2756:     */      }
/* 2757:2750 */      StrBuilder.this.getChars(this.pos, this.pos + len, b, off);
/* 2758:2751 */      this.pos += len;
/* 2759:2752 */      return len;
/* 2760:     */    }
/* 2761:     */    
/* 2763:     */    public long skip(long n)
/* 2764:     */    {
/* 2765:2758 */      if (this.pos + n > StrBuilder.this.size()) {
/* 2766:2759 */        n = StrBuilder.this.size() - this.pos;
/* 2767:     */      }
/* 2768:2761 */      if (n < 0L) {
/* 2769:2762 */        return 0L;
/* 2770:     */      }
/* 2771:2764 */      this.pos = ((int)(this.pos + n));
/* 2772:2765 */      return n;
/* 2773:     */    }
/* 2774:     */    
/* 2776:     */    public boolean ready()
/* 2777:     */    {
/* 2778:2771 */      return this.pos < StrBuilder.this.size();
/* 2779:     */    }
/* 2780:     */    
/* 2782:     */    public boolean markSupported()
/* 2783:     */    {
/* 2784:2777 */      return true;
/* 2785:     */    }
/* 2786:     */    
/* 2788:     */    public void mark(int readAheadLimit)
/* 2789:     */    {
/* 2790:2783 */      this.mark = this.pos;
/* 2791:     */    }
/* 2792:     */    
/* 2794:     */    public void reset()
/* 2795:     */    {
/* 2796:2789 */      this.pos = this.mark;
/* 2797:     */    }
/* 2798:     */  }
/* 2799:     */  
/* 2805:     */  class StrBuilderWriter
/* 2806:     */    extends Writer
/* 2807:     */  {
/* 2808:     */    StrBuilderWriter() {}
/* 2809:     */    
/* 2814:     */    public void close() {}
/* 2815:     */    
/* 2820:     */    public void flush() {}
/* 2821:     */    
/* 2826:     */    public void write(int c)
/* 2827:     */    {
/* 2828:2821 */      StrBuilder.this.append((char)c);
/* 2829:     */    }
/* 2830:     */    
/* 2832:     */    public void write(char[] cbuf)
/* 2833:     */    {
/* 2834:2827 */      StrBuilder.this.append(cbuf);
/* 2835:     */    }
/* 2836:     */    
/* 2838:     */    public void write(char[] cbuf, int off, int len)
/* 2839:     */    {
/* 2840:2833 */      StrBuilder.this.append(cbuf, off, len);
/* 2841:     */    }
/* 2842:     */    
/* 2844:     */    public void write(String str)
/* 2845:     */    {
/* 2846:2839 */      StrBuilder.this.append(str);
/* 2847:     */    }
/* 2848:     */    
/* 2850:     */    public void write(String str, int off, int len)
/* 2851:     */    {
/* 2852:2845 */      StrBuilder.this.append(str, off, len);
/* 2853:     */    }
/* 2854:     */  }
/* 2855:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.StrBuilder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */