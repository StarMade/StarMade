/*      */ package org.apache.commons.lang3.text;
/*      */ 
/*      */ import java.io.Reader;
/*      */ import java.io.Writer;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.apache.commons.lang3.ArrayUtils;
/*      */ import org.apache.commons.lang3.ObjectUtils;
/*      */ import org.apache.commons.lang3.SystemUtils;
/*      */ 
/*      */ public class StrBuilder
/*      */   implements CharSequence, Appendable
/*      */ {
/*      */   static final int CAPACITY = 32;
/*      */   private static final long serialVersionUID = 7628716375283629643L;
/*      */   protected char[] buffer;
/*      */   protected int size;
/*      */   private String newLine;
/*      */   private String nullText;
/*      */ 
/*      */   public StrBuilder()
/*      */   {
/*  101 */     this(32);
/*      */   }
/*      */ 
/*      */   public StrBuilder(int initialCapacity)
/*      */   {
/*  111 */     if (initialCapacity <= 0) {
/*  112 */       initialCapacity = 32;
/*      */     }
/*  114 */     this.buffer = new char[initialCapacity];
/*      */   }
/*      */ 
/*      */   public StrBuilder(String str)
/*      */   {
/*  125 */     if (str == null) {
/*  126 */       this.buffer = new char[32];
/*      */     } else {
/*  128 */       this.buffer = new char[str.length() + 32];
/*  129 */       append(str);
/*      */     }
/*      */   }
/*      */ 
/*      */   public String getNewLineText()
/*      */   {
/*  140 */     return this.newLine;
/*      */   }
/*      */ 
/*      */   public StrBuilder setNewLineText(String newLine)
/*      */   {
/*  150 */     this.newLine = newLine;
/*  151 */     return this;
/*      */   }
/*      */ 
/*      */   public String getNullText()
/*      */   {
/*  161 */     return this.nullText;
/*      */   }
/*      */ 
/*      */   public StrBuilder setNullText(String nullText)
/*      */   {
/*  171 */     if ((nullText != null) && (nullText.length() == 0)) {
/*  172 */       nullText = null;
/*      */     }
/*  174 */     this.nullText = nullText;
/*  175 */     return this;
/*      */   }
/*      */ 
/*      */   public int length()
/*      */   {
/*  185 */     return this.size;
/*      */   }
/*      */ 
/*      */   public StrBuilder setLength(int length)
/*      */   {
/*  197 */     if (length < 0) {
/*  198 */       throw new StringIndexOutOfBoundsException(length);
/*      */     }
/*  200 */     if (length < this.size) {
/*  201 */       this.size = length;
/*  202 */     } else if (length > this.size) {
/*  203 */       ensureCapacity(length);
/*  204 */       int oldEnd = this.size;
/*  205 */       int newEnd = length;
/*  206 */       this.size = length;
/*  207 */       for (int i = oldEnd; i < newEnd; i++) {
/*  208 */         this.buffer[i] = '\000';
/*      */       }
/*      */     }
/*  211 */     return this;
/*      */   }
/*      */ 
/*      */   public int capacity()
/*      */   {
/*  221 */     return this.buffer.length;
/*      */   }
/*      */ 
/*      */   public StrBuilder ensureCapacity(int capacity)
/*      */   {
/*  231 */     if (capacity > this.buffer.length) {
/*  232 */       char[] old = this.buffer;
/*  233 */       this.buffer = new char[capacity * 2];
/*  234 */       System.arraycopy(old, 0, this.buffer, 0, this.size);
/*      */     }
/*  236 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder minimizeCapacity()
/*      */   {
/*  245 */     if (this.buffer.length > length()) {
/*  246 */       char[] old = this.buffer;
/*  247 */       this.buffer = new char[length()];
/*  248 */       System.arraycopy(old, 0, this.buffer, 0, this.size);
/*      */     }
/*  250 */     return this;
/*      */   }
/*      */ 
/*      */   public int size()
/*      */   {
/*  263 */     return this.size;
/*      */   }
/*      */ 
/*      */   public boolean isEmpty()
/*      */   {
/*  275 */     return this.size == 0;
/*      */   }
/*      */ 
/*      */   public StrBuilder clear()
/*      */   {
/*  290 */     this.size = 0;
/*  291 */     return this;
/*      */   }
/*      */ 
/*      */   public char charAt(int index)
/*      */   {
/*  305 */     if ((index < 0) || (index >= length())) {
/*  306 */       throw new StringIndexOutOfBoundsException(index);
/*      */     }
/*  308 */     return this.buffer[index];
/*      */   }
/*      */ 
/*      */   public StrBuilder setCharAt(int index, char ch)
/*      */   {
/*  322 */     if ((index < 0) || (index >= length())) {
/*  323 */       throw new StringIndexOutOfBoundsException(index);
/*      */     }
/*  325 */     this.buffer[index] = ch;
/*  326 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder deleteCharAt(int index)
/*      */   {
/*  339 */     if ((index < 0) || (index >= this.size)) {
/*  340 */       throw new StringIndexOutOfBoundsException(index);
/*      */     }
/*  342 */     deleteImpl(index, index + 1, 1);
/*  343 */     return this;
/*      */   }
/*      */ 
/*      */   public char[] toCharArray()
/*      */   {
/*  353 */     if (this.size == 0) {
/*  354 */       return ArrayUtils.EMPTY_CHAR_ARRAY;
/*      */     }
/*  356 */     char[] chars = new char[this.size];
/*  357 */     System.arraycopy(this.buffer, 0, chars, 0, this.size);
/*  358 */     return chars;
/*      */   }
/*      */ 
/*      */   public char[] toCharArray(int startIndex, int endIndex)
/*      */   {
/*  372 */     endIndex = validateRange(startIndex, endIndex);
/*  373 */     int len = endIndex - startIndex;
/*  374 */     if (len == 0) {
/*  375 */       return ArrayUtils.EMPTY_CHAR_ARRAY;
/*      */     }
/*  377 */     char[] chars = new char[len];
/*  378 */     System.arraycopy(this.buffer, startIndex, chars, 0, len);
/*  379 */     return chars;
/*      */   }
/*      */ 
/*      */   public char[] getChars(char[] destination)
/*      */   {
/*  389 */     int len = length();
/*  390 */     if ((destination == null) || (destination.length < len)) {
/*  391 */       destination = new char[len];
/*      */     }
/*  393 */     System.arraycopy(this.buffer, 0, destination, 0, len);
/*  394 */     return destination;
/*      */   }
/*      */ 
/*      */   public void getChars(int startIndex, int endIndex, char[] destination, int destinationIndex)
/*      */   {
/*  408 */     if (startIndex < 0) {
/*  409 */       throw new StringIndexOutOfBoundsException(startIndex);
/*      */     }
/*  411 */     if ((endIndex < 0) || (endIndex > length())) {
/*  412 */       throw new StringIndexOutOfBoundsException(endIndex);
/*      */     }
/*  414 */     if (startIndex > endIndex) {
/*  415 */       throw new StringIndexOutOfBoundsException("end < start");
/*      */     }
/*  417 */     System.arraycopy(this.buffer, startIndex, destination, destinationIndex, endIndex - startIndex);
/*      */   }
/*      */ 
/*      */   public StrBuilder appendNewLine()
/*      */   {
/*  431 */     if (this.newLine == null) {
/*  432 */       append(SystemUtils.LINE_SEPARATOR);
/*  433 */       return this;
/*      */     }
/*  435 */     return append(this.newLine);
/*      */   }
/*      */ 
/*      */   public StrBuilder appendNull()
/*      */   {
/*  444 */     if (this.nullText == null) {
/*  445 */       return this;
/*      */     }
/*  447 */     return append(this.nullText);
/*      */   }
/*      */ 
/*      */   public StrBuilder append(Object obj)
/*      */   {
/*  458 */     if (obj == null) {
/*  459 */       return appendNull();
/*      */     }
/*  461 */     return append(obj.toString());
/*      */   }
/*      */ 
/*      */   public StrBuilder append(CharSequence seq)
/*      */   {
/*  473 */     if (seq == null) {
/*  474 */       return appendNull();
/*      */     }
/*  476 */     return append(seq.toString());
/*      */   }
/*      */ 
/*      */   public StrBuilder append(CharSequence seq, int startIndex, int length)
/*      */   {
/*  490 */     if (seq == null) {
/*  491 */       return appendNull();
/*      */     }
/*  493 */     return append(seq.toString(), startIndex, length);
/*      */   }
/*      */ 
/*      */   public StrBuilder append(String str)
/*      */   {
/*  504 */     if (str == null) {
/*  505 */       return appendNull();
/*      */     }
/*  507 */     int strLen = str.length();
/*  508 */     if (strLen > 0) {
/*  509 */       int len = length();
/*  510 */       ensureCapacity(len + strLen);
/*  511 */       str.getChars(0, strLen, this.buffer, len);
/*  512 */       this.size += strLen;
/*      */     }
/*  514 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder append(String str, int startIndex, int length)
/*      */   {
/*  527 */     if (str == null) {
/*  528 */       return appendNull();
/*      */     }
/*  530 */     if ((startIndex < 0) || (startIndex > str.length())) {
/*  531 */       throw new StringIndexOutOfBoundsException("startIndex must be valid");
/*      */     }
/*  533 */     if ((length < 0) || (startIndex + length > str.length())) {
/*  534 */       throw new StringIndexOutOfBoundsException("length must be valid");
/*      */     }
/*  536 */     if (length > 0) {
/*  537 */       int len = length();
/*  538 */       ensureCapacity(len + length);
/*  539 */       str.getChars(startIndex, startIndex + length, this.buffer, len);
/*  540 */       this.size += length;
/*      */     }
/*  542 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder append(StringBuffer str)
/*      */   {
/*  553 */     if (str == null) {
/*  554 */       return appendNull();
/*      */     }
/*  556 */     int strLen = str.length();
/*  557 */     if (strLen > 0) {
/*  558 */       int len = length();
/*  559 */       ensureCapacity(len + strLen);
/*  560 */       str.getChars(0, strLen, this.buffer, len);
/*  561 */       this.size += strLen;
/*      */     }
/*  563 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder append(StringBuffer str, int startIndex, int length)
/*      */   {
/*  576 */     if (str == null) {
/*  577 */       return appendNull();
/*      */     }
/*  579 */     if ((startIndex < 0) || (startIndex > str.length())) {
/*  580 */       throw new StringIndexOutOfBoundsException("startIndex must be valid");
/*      */     }
/*  582 */     if ((length < 0) || (startIndex + length > str.length())) {
/*  583 */       throw new StringIndexOutOfBoundsException("length must be valid");
/*      */     }
/*  585 */     if (length > 0) {
/*  586 */       int len = length();
/*  587 */       ensureCapacity(len + length);
/*  588 */       str.getChars(startIndex, startIndex + length, this.buffer, len);
/*  589 */       this.size += length;
/*      */     }
/*  591 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder append(StrBuilder str)
/*      */   {
/*  602 */     if (str == null) {
/*  603 */       return appendNull();
/*      */     }
/*  605 */     int strLen = str.length();
/*  606 */     if (strLen > 0) {
/*  607 */       int len = length();
/*  608 */       ensureCapacity(len + strLen);
/*  609 */       System.arraycopy(str.buffer, 0, this.buffer, len, strLen);
/*  610 */       this.size += strLen;
/*      */     }
/*  612 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder append(StrBuilder str, int startIndex, int length)
/*      */   {
/*  625 */     if (str == null) {
/*  626 */       return appendNull();
/*      */     }
/*  628 */     if ((startIndex < 0) || (startIndex > str.length())) {
/*  629 */       throw new StringIndexOutOfBoundsException("startIndex must be valid");
/*      */     }
/*  631 */     if ((length < 0) || (startIndex + length > str.length())) {
/*  632 */       throw new StringIndexOutOfBoundsException("length must be valid");
/*      */     }
/*  634 */     if (length > 0) {
/*  635 */       int len = length();
/*  636 */       ensureCapacity(len + length);
/*  637 */       str.getChars(startIndex, startIndex + length, this.buffer, len);
/*  638 */       this.size += length;
/*      */     }
/*  640 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder append(char[] chars)
/*      */   {
/*  651 */     if (chars == null) {
/*  652 */       return appendNull();
/*      */     }
/*  654 */     int strLen = chars.length;
/*  655 */     if (strLen > 0) {
/*  656 */       int len = length();
/*  657 */       ensureCapacity(len + strLen);
/*  658 */       System.arraycopy(chars, 0, this.buffer, len, strLen);
/*  659 */       this.size += strLen;
/*      */     }
/*  661 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder append(char[] chars, int startIndex, int length)
/*      */   {
/*  674 */     if (chars == null) {
/*  675 */       return appendNull();
/*      */     }
/*  677 */     if ((startIndex < 0) || (startIndex > chars.length)) {
/*  678 */       throw new StringIndexOutOfBoundsException("Invalid startIndex: " + length);
/*      */     }
/*  680 */     if ((length < 0) || (startIndex + length > chars.length)) {
/*  681 */       throw new StringIndexOutOfBoundsException("Invalid length: " + length);
/*      */     }
/*  683 */     if (length > 0) {
/*  684 */       int len = length();
/*  685 */       ensureCapacity(len + length);
/*  686 */       System.arraycopy(chars, startIndex, this.buffer, len, length);
/*  687 */       this.size += length;
/*      */     }
/*  689 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder append(boolean value)
/*      */   {
/*  699 */     if (value) {
/*  700 */       ensureCapacity(this.size + 4);
/*  701 */       this.buffer[(this.size++)] = 't';
/*  702 */       this.buffer[(this.size++)] = 'r';
/*  703 */       this.buffer[(this.size++)] = 'u';
/*  704 */       this.buffer[(this.size++)] = 'e';
/*      */     } else {
/*  706 */       ensureCapacity(this.size + 5);
/*  707 */       this.buffer[(this.size++)] = 'f';
/*  708 */       this.buffer[(this.size++)] = 'a';
/*  709 */       this.buffer[(this.size++)] = 'l';
/*  710 */       this.buffer[(this.size++)] = 's';
/*  711 */       this.buffer[(this.size++)] = 'e';
/*      */     }
/*  713 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder append(char ch)
/*      */   {
/*  724 */     int len = length();
/*  725 */     ensureCapacity(len + 1);
/*  726 */     this.buffer[(this.size++)] = ch;
/*  727 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder append(int value)
/*      */   {
/*  737 */     return append(String.valueOf(value));
/*      */   }
/*      */ 
/*      */   public StrBuilder append(long value)
/*      */   {
/*  747 */     return append(String.valueOf(value));
/*      */   }
/*      */ 
/*      */   public StrBuilder append(float value)
/*      */   {
/*  757 */     return append(String.valueOf(value));
/*      */   }
/*      */ 
/*      */   public StrBuilder append(double value)
/*      */   {
/*  767 */     return append(String.valueOf(value));
/*      */   }
/*      */ 
/*      */   public StrBuilder appendln(Object obj)
/*      */   {
/*  780 */     return append(obj).appendNewLine();
/*      */   }
/*      */ 
/*      */   public StrBuilder appendln(String str)
/*      */   {
/*  792 */     return append(str).appendNewLine();
/*      */   }
/*      */ 
/*      */   public StrBuilder appendln(String str, int startIndex, int length)
/*      */   {
/*  806 */     return append(str, startIndex, length).appendNewLine();
/*      */   }
/*      */ 
/*      */   public StrBuilder appendln(StringBuffer str)
/*      */   {
/*  818 */     return append(str).appendNewLine();
/*      */   }
/*      */ 
/*      */   public StrBuilder appendln(StringBuffer str, int startIndex, int length)
/*      */   {
/*  832 */     return append(str, startIndex, length).appendNewLine();
/*      */   }
/*      */ 
/*      */   public StrBuilder appendln(StrBuilder str)
/*      */   {
/*  844 */     return append(str).appendNewLine();
/*      */   }
/*      */ 
/*      */   public StrBuilder appendln(StrBuilder str, int startIndex, int length)
/*      */   {
/*  858 */     return append(str, startIndex, length).appendNewLine();
/*      */   }
/*      */ 
/*      */   public StrBuilder appendln(char[] chars)
/*      */   {
/*  870 */     return append(chars).appendNewLine();
/*      */   }
/*      */ 
/*      */   public StrBuilder appendln(char[] chars, int startIndex, int length)
/*      */   {
/*  884 */     return append(chars, startIndex, length).appendNewLine();
/*      */   }
/*      */ 
/*      */   public StrBuilder appendln(boolean value)
/*      */   {
/*  895 */     return append(value).appendNewLine();
/*      */   }
/*      */ 
/*      */   public StrBuilder appendln(char ch)
/*      */   {
/*  906 */     return append(ch).appendNewLine();
/*      */   }
/*      */ 
/*      */   public StrBuilder appendln(int value)
/*      */   {
/*  917 */     return append(value).appendNewLine();
/*      */   }
/*      */ 
/*      */   public StrBuilder appendln(long value)
/*      */   {
/*  928 */     return append(value).appendNewLine();
/*      */   }
/*      */ 
/*      */   public StrBuilder appendln(float value)
/*      */   {
/*  939 */     return append(value).appendNewLine();
/*      */   }
/*      */ 
/*      */   public StrBuilder appendln(double value)
/*      */   {
/*  950 */     return append(value).appendNewLine();
/*      */   }
/*      */ 
/*      */   public StrBuilder appendAll(Object[] array)
/*      */   {
/*  964 */     if ((array != null) && (array.length > 0)) {
/*  965 */       for (Object element : array) {
/*  966 */         append(element);
/*      */       }
/*      */     }
/*  969 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder appendAll(Iterable<?> iterable)
/*      */   {
/*  982 */     if (iterable != null) {
/*  983 */       Iterator it = iterable.iterator();
/*  984 */       while (it.hasNext()) {
/*  985 */         append(it.next());
/*      */       }
/*      */     }
/*  988 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder appendAll(Iterator<?> it)
/*      */   {
/* 1001 */     if (it != null) {
/* 1002 */       while (it.hasNext()) {
/* 1003 */         append(it.next());
/*      */       }
/*      */     }
/* 1006 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder appendWithSeparators(Object[] array, String separator)
/*      */   {
/* 1021 */     if ((array != null) && (array.length > 0)) {
/* 1022 */       separator = ObjectUtils.toString(separator);
/* 1023 */       append(array[0]);
/* 1024 */       for (int i = 1; i < array.length; i++) {
/* 1025 */         append(separator);
/* 1026 */         append(array[i]);
/*      */       }
/*      */     }
/* 1029 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder appendWithSeparators(Iterable<?> iterable, String separator)
/*      */   {
/* 1043 */     if (iterable != null) {
/* 1044 */       separator = ObjectUtils.toString(separator);
/* 1045 */       Iterator it = iterable.iterator();
/* 1046 */       while (it.hasNext()) {
/* 1047 */         append(it.next());
/* 1048 */         if (it.hasNext()) {
/* 1049 */           append(separator);
/*      */         }
/*      */       }
/*      */     }
/* 1053 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder appendWithSeparators(Iterator<?> it, String separator)
/*      */   {
/* 1067 */     if (it != null) {
/* 1068 */       separator = ObjectUtils.toString(separator);
/* 1069 */       while (it.hasNext()) {
/* 1070 */         append(it.next());
/* 1071 */         if (it.hasNext()) {
/* 1072 */           append(separator);
/*      */         }
/*      */       }
/*      */     }
/* 1076 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder appendSeparator(String separator)
/*      */   {
/* 1101 */     return appendSeparator(separator, null);
/*      */   }
/*      */ 
/*      */   public StrBuilder appendSeparator(String standard, String defaultIfEmpty)
/*      */   {
/* 1132 */     String str = isEmpty() ? defaultIfEmpty : standard;
/* 1133 */     if (str != null) {
/* 1134 */       append(str);
/*      */     }
/* 1136 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder appendSeparator(char separator)
/*      */   {
/* 1159 */     if (size() > 0) {
/* 1160 */       append(separator);
/*      */     }
/* 1162 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder appendSeparator(char standard, char defaultIfEmpty)
/*      */   {
/* 1177 */     if (size() > 0)
/* 1178 */       append(standard);
/*      */     else {
/* 1180 */       append(defaultIfEmpty);
/*      */     }
/* 1182 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder appendSeparator(String separator, int loopIndex)
/*      */   {
/* 1206 */     if ((separator != null) && (loopIndex > 0)) {
/* 1207 */       append(separator);
/*      */     }
/* 1209 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder appendSeparator(char separator, int loopIndex)
/*      */   {
/* 1233 */     if (loopIndex > 0) {
/* 1234 */       append(separator);
/*      */     }
/* 1236 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder appendPadding(int length, char padChar)
/*      */   {
/* 1248 */     if (length >= 0) {
/* 1249 */       ensureCapacity(this.size + length);
/* 1250 */       for (int i = 0; i < length; i++) {
/* 1251 */         this.buffer[(this.size++)] = padChar;
/*      */       }
/*      */     }
/* 1254 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder appendFixedWidthPadLeft(Object obj, int width, char padChar)
/*      */   {
/* 1270 */     if (width > 0) {
/* 1271 */       ensureCapacity(this.size + width);
/* 1272 */       String str = obj == null ? getNullText() : obj.toString();
/* 1273 */       if (str == null) {
/* 1274 */         str = "";
/*      */       }
/* 1276 */       int strLen = str.length();
/* 1277 */       if (strLen >= width) {
/* 1278 */         str.getChars(strLen - width, strLen, this.buffer, this.size);
/*      */       } else {
/* 1280 */         int padLen = width - strLen;
/* 1281 */         for (int i = 0; i < padLen; i++) {
/* 1282 */           this.buffer[(this.size + i)] = padChar;
/*      */         }
/* 1284 */         str.getChars(0, strLen, this.buffer, this.size + padLen);
/*      */       }
/* 1286 */       this.size += width;
/*      */     }
/* 1288 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder appendFixedWidthPadLeft(int value, int width, char padChar)
/*      */   {
/* 1302 */     return appendFixedWidthPadLeft(String.valueOf(value), width, padChar);
/*      */   }
/*      */ 
/*      */   public StrBuilder appendFixedWidthPadRight(Object obj, int width, char padChar)
/*      */   {
/* 1317 */     if (width > 0) {
/* 1318 */       ensureCapacity(this.size + width);
/* 1319 */       String str = obj == null ? getNullText() : obj.toString();
/* 1320 */       if (str == null) {
/* 1321 */         str = "";
/*      */       }
/* 1323 */       int strLen = str.length();
/* 1324 */       if (strLen >= width) {
/* 1325 */         str.getChars(0, width, this.buffer, this.size);
/*      */       } else {
/* 1327 */         int padLen = width - strLen;
/* 1328 */         str.getChars(0, strLen, this.buffer, this.size);
/* 1329 */         for (int i = 0; i < padLen; i++) {
/* 1330 */           this.buffer[(this.size + strLen + i)] = padChar;
/*      */         }
/*      */       }
/* 1333 */       this.size += width;
/*      */     }
/* 1335 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder appendFixedWidthPadRight(int value, int width, char padChar)
/*      */   {
/* 1349 */     return appendFixedWidthPadRight(String.valueOf(value), width, padChar);
/*      */   }
/*      */ 
/*      */   public StrBuilder insert(int index, Object obj)
/*      */   {
/* 1363 */     if (obj == null) {
/* 1364 */       return insert(index, this.nullText);
/*      */     }
/* 1366 */     return insert(index, obj.toString());
/*      */   }
/*      */ 
/*      */   public StrBuilder insert(int index, String str)
/*      */   {
/* 1380 */     validateIndex(index);
/* 1381 */     if (str == null) {
/* 1382 */       str = this.nullText;
/*      */     }
/* 1384 */     int strLen = str == null ? 0 : str.length();
/* 1385 */     if (strLen > 0) {
/* 1386 */       int newSize = this.size + strLen;
/* 1387 */       ensureCapacity(newSize);
/* 1388 */       System.arraycopy(this.buffer, index, this.buffer, index + strLen, this.size - index);
/* 1389 */       this.size = newSize;
/* 1390 */       str.getChars(0, strLen, this.buffer, index);
/*      */     }
/* 1392 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder insert(int index, char[] chars)
/*      */   {
/* 1405 */     validateIndex(index);
/* 1406 */     if (chars == null) {
/* 1407 */       return insert(index, this.nullText);
/*      */     }
/* 1409 */     int len = chars.length;
/* 1410 */     if (len > 0) {
/* 1411 */       ensureCapacity(this.size + len);
/* 1412 */       System.arraycopy(this.buffer, index, this.buffer, index + len, this.size - index);
/* 1413 */       System.arraycopy(chars, 0, this.buffer, index, len);
/* 1414 */       this.size += len;
/*      */     }
/* 1416 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder insert(int index, char[] chars, int offset, int length)
/*      */   {
/* 1431 */     validateIndex(index);
/* 1432 */     if (chars == null) {
/* 1433 */       return insert(index, this.nullText);
/*      */     }
/* 1435 */     if ((offset < 0) || (offset > chars.length)) {
/* 1436 */       throw new StringIndexOutOfBoundsException("Invalid offset: " + offset);
/*      */     }
/* 1438 */     if ((length < 0) || (offset + length > chars.length)) {
/* 1439 */       throw new StringIndexOutOfBoundsException("Invalid length: " + length);
/*      */     }
/* 1441 */     if (length > 0) {
/* 1442 */       ensureCapacity(this.size + length);
/* 1443 */       System.arraycopy(this.buffer, index, this.buffer, index + length, this.size - index);
/* 1444 */       System.arraycopy(chars, offset, this.buffer, index, length);
/* 1445 */       this.size += length;
/*      */     }
/* 1447 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder insert(int index, boolean value)
/*      */   {
/* 1459 */     validateIndex(index);
/* 1460 */     if (value) {
/* 1461 */       ensureCapacity(this.size + 4);
/* 1462 */       System.arraycopy(this.buffer, index, this.buffer, index + 4, this.size - index);
/* 1463 */       this.buffer[(index++)] = 't';
/* 1464 */       this.buffer[(index++)] = 'r';
/* 1465 */       this.buffer[(index++)] = 'u';
/* 1466 */       this.buffer[index] = 'e';
/* 1467 */       this.size += 4;
/*      */     } else {
/* 1469 */       ensureCapacity(this.size + 5);
/* 1470 */       System.arraycopy(this.buffer, index, this.buffer, index + 5, this.size - index);
/* 1471 */       this.buffer[(index++)] = 'f';
/* 1472 */       this.buffer[(index++)] = 'a';
/* 1473 */       this.buffer[(index++)] = 'l';
/* 1474 */       this.buffer[(index++)] = 's';
/* 1475 */       this.buffer[index] = 'e';
/* 1476 */       this.size += 5;
/*      */     }
/* 1478 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder insert(int index, char value)
/*      */   {
/* 1490 */     validateIndex(index);
/* 1491 */     ensureCapacity(this.size + 1);
/* 1492 */     System.arraycopy(this.buffer, index, this.buffer, index + 1, this.size - index);
/* 1493 */     this.buffer[index] = value;
/* 1494 */     this.size += 1;
/* 1495 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder insert(int index, int value)
/*      */   {
/* 1507 */     return insert(index, String.valueOf(value));
/*      */   }
/*      */ 
/*      */   public StrBuilder insert(int index, long value)
/*      */   {
/* 1519 */     return insert(index, String.valueOf(value));
/*      */   }
/*      */ 
/*      */   public StrBuilder insert(int index, float value)
/*      */   {
/* 1531 */     return insert(index, String.valueOf(value));
/*      */   }
/*      */ 
/*      */   public StrBuilder insert(int index, double value)
/*      */   {
/* 1543 */     return insert(index, String.valueOf(value));
/*      */   }
/*      */ 
/*      */   private void deleteImpl(int startIndex, int endIndex, int len)
/*      */   {
/* 1556 */     System.arraycopy(this.buffer, endIndex, this.buffer, startIndex, this.size - endIndex);
/* 1557 */     this.size -= len;
/*      */   }
/*      */ 
/*      */   public StrBuilder delete(int startIndex, int endIndex)
/*      */   {
/* 1570 */     endIndex = validateRange(startIndex, endIndex);
/* 1571 */     int len = endIndex - startIndex;
/* 1572 */     if (len > 0) {
/* 1573 */       deleteImpl(startIndex, endIndex, len);
/*      */     }
/* 1575 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder deleteAll(char ch)
/*      */   {
/* 1586 */     for (int i = 0; i < this.size; i++) {
/* 1587 */       if (this.buffer[i] == ch) {
/* 1588 */         int start = i;
/*      */         while (true) { i++; if (i < this.size) {
/* 1590 */             if (this.buffer[i] != ch)
/* 1591 */               break;
/*      */           }
/*      */         }
/* 1594 */         int len = i - start;
/* 1595 */         deleteImpl(start, i, len);
/* 1596 */         i -= len;
/*      */       }
/*      */     }
/* 1599 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder deleteFirst(char ch)
/*      */   {
/* 1609 */     for (int i = 0; i < this.size; i++) {
/* 1610 */       if (this.buffer[i] == ch) {
/* 1611 */         deleteImpl(i, i + 1, 1);
/* 1612 */         break;
/*      */       }
/*      */     }
/* 1615 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder deleteAll(String str)
/*      */   {
/* 1626 */     int len = str == null ? 0 : str.length();
/* 1627 */     if (len > 0) {
/* 1628 */       int index = indexOf(str, 0);
/* 1629 */       while (index >= 0) {
/* 1630 */         deleteImpl(index, index + len, len);
/* 1631 */         index = indexOf(str, index);
/*      */       }
/*      */     }
/* 1634 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder deleteFirst(String str)
/*      */   {
/* 1644 */     int len = str == null ? 0 : str.length();
/* 1645 */     if (len > 0) {
/* 1646 */       int index = indexOf(str, 0);
/* 1647 */       if (index >= 0) {
/* 1648 */         deleteImpl(index, index + len, len);
/*      */       }
/*      */     }
/* 1651 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder deleteAll(StrMatcher matcher)
/*      */   {
/* 1666 */     return replace(matcher, null, 0, this.size, -1);
/*      */   }
/*      */ 
/*      */   public StrBuilder deleteFirst(StrMatcher matcher)
/*      */   {
/* 1680 */     return replace(matcher, null, 0, this.size, 1);
/*      */   }
/*      */ 
/*      */   private void replaceImpl(int startIndex, int endIndex, int removeLen, String insertStr, int insertLen)
/*      */   {
/* 1695 */     int newSize = this.size - removeLen + insertLen;
/* 1696 */     if (insertLen != removeLen) {
/* 1697 */       ensureCapacity(newSize);
/* 1698 */       System.arraycopy(this.buffer, endIndex, this.buffer, startIndex + insertLen, this.size - endIndex);
/* 1699 */       this.size = newSize;
/*      */     }
/* 1701 */     if (insertLen > 0)
/* 1702 */       insertStr.getChars(0, insertLen, this.buffer, startIndex);
/*      */   }
/*      */ 
/*      */   public StrBuilder replace(int startIndex, int endIndex, String replaceStr)
/*      */   {
/* 1718 */     endIndex = validateRange(startIndex, endIndex);
/* 1719 */     int insertLen = replaceStr == null ? 0 : replaceStr.length();
/* 1720 */     replaceImpl(startIndex, endIndex, endIndex - startIndex, replaceStr, insertLen);
/* 1721 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder replaceAll(char search, char replace)
/*      */   {
/* 1734 */     if (search != replace) {
/* 1735 */       for (int i = 0; i < this.size; i++) {
/* 1736 */         if (this.buffer[i] == search) {
/* 1737 */           this.buffer[i] = replace;
/*      */         }
/*      */       }
/*      */     }
/* 1741 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder replaceFirst(char search, char replace)
/*      */   {
/* 1753 */     if (search != replace) {
/* 1754 */       for (int i = 0; i < this.size; i++) {
/* 1755 */         if (this.buffer[i] == search) {
/* 1756 */           this.buffer[i] = replace;
/* 1757 */           break;
/*      */         }
/*      */       }
/*      */     }
/* 1761 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder replaceAll(String searchStr, String replaceStr)
/*      */   {
/* 1773 */     int searchLen = searchStr == null ? 0 : searchStr.length();
/* 1774 */     if (searchLen > 0) {
/* 1775 */       int replaceLen = replaceStr == null ? 0 : replaceStr.length();
/* 1776 */       int index = indexOf(searchStr, 0);
/* 1777 */       while (index >= 0) {
/* 1778 */         replaceImpl(index, index + searchLen, searchLen, replaceStr, replaceLen);
/* 1779 */         index = indexOf(searchStr, index + replaceLen);
/*      */       }
/*      */     }
/* 1782 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder replaceFirst(String searchStr, String replaceStr)
/*      */   {
/* 1793 */     int searchLen = searchStr == null ? 0 : searchStr.length();
/* 1794 */     if (searchLen > 0) {
/* 1795 */       int index = indexOf(searchStr, 0);
/* 1796 */       if (index >= 0) {
/* 1797 */         int replaceLen = replaceStr == null ? 0 : replaceStr.length();
/* 1798 */         replaceImpl(index, index + searchLen, searchLen, replaceStr, replaceLen);
/*      */       }
/*      */     }
/* 1801 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder replaceAll(StrMatcher matcher, String replaceStr)
/*      */   {
/* 1817 */     return replace(matcher, replaceStr, 0, this.size, -1);
/*      */   }
/*      */ 
/*      */   public StrBuilder replaceFirst(StrMatcher matcher, String replaceStr)
/*      */   {
/* 1832 */     return replace(matcher, replaceStr, 0, this.size, 1);
/*      */   }
/*      */ 
/*      */   public StrBuilder replace(StrMatcher matcher, String replaceStr, int startIndex, int endIndex, int replaceCount)
/*      */   {
/* 1855 */     endIndex = validateRange(startIndex, endIndex);
/* 1856 */     return replaceImpl(matcher, replaceStr, startIndex, endIndex, replaceCount);
/*      */   }
/*      */ 
/*      */   private StrBuilder replaceImpl(StrMatcher matcher, String replaceStr, int from, int to, int replaceCount)
/*      */   {
/* 1877 */     if ((matcher == null) || (this.size == 0)) {
/* 1878 */       return this;
/*      */     }
/* 1880 */     int replaceLen = replaceStr == null ? 0 : replaceStr.length();
/* 1881 */     char[] buf = this.buffer;
/* 1882 */     for (int i = from; (i < to) && (replaceCount != 0); i++) {
/* 1883 */       int removeLen = matcher.isMatch(buf, i, from, to);
/* 1884 */       if (removeLen > 0) {
/* 1885 */         replaceImpl(i, i + removeLen, removeLen, replaceStr, replaceLen);
/* 1886 */         to = to - removeLen + replaceLen;
/* 1887 */         i = i + replaceLen - 1;
/* 1888 */         if (replaceCount > 0) {
/* 1889 */           replaceCount--;
/*      */         }
/*      */       }
/*      */     }
/* 1893 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder reverse()
/*      */   {
/* 1903 */     if (this.size == 0) {
/* 1904 */       return this;
/*      */     }
/*      */ 
/* 1907 */     int half = this.size / 2;
/* 1908 */     char[] buf = this.buffer;
/* 1909 */     int leftIdx = 0; for (int rightIdx = this.size - 1; leftIdx < half; rightIdx--) {
/* 1910 */       char swap = buf[leftIdx];
/* 1911 */       buf[leftIdx] = buf[rightIdx];
/* 1912 */       buf[rightIdx] = swap;
/*      */ 
/* 1909 */       leftIdx++;
/*      */     }
/*      */ 
/* 1914 */     return this;
/*      */   }
/*      */ 
/*      */   public StrBuilder trim()
/*      */   {
/* 1925 */     if (this.size == 0) {
/* 1926 */       return this;
/*      */     }
/* 1928 */     int len = this.size;
/* 1929 */     char[] buf = this.buffer;
/* 1930 */     int pos = 0;
/* 1931 */     while ((pos < len) && (buf[pos] <= ' ')) {
/* 1932 */       pos++;
/*      */     }
/* 1934 */     while ((pos < len) && (buf[(len - 1)] <= ' ')) {
/* 1935 */       len--;
/*      */     }
/* 1937 */     if (len < this.size) {
/* 1938 */       delete(len, this.size);
/*      */     }
/* 1940 */     if (pos > 0) {
/* 1941 */       delete(0, pos);
/*      */     }
/* 1943 */     return this;
/*      */   }
/*      */ 
/*      */   public boolean startsWith(String str)
/*      */   {
/* 1956 */     if (str == null) {
/* 1957 */       return false;
/*      */     }
/* 1959 */     int len = str.length();
/* 1960 */     if (len == 0) {
/* 1961 */       return true;
/*      */     }
/* 1963 */     if (len > this.size) {
/* 1964 */       return false;
/*      */     }
/* 1966 */     for (int i = 0; i < len; i++) {
/* 1967 */       if (this.buffer[i] != str.charAt(i)) {
/* 1968 */         return false;
/*      */       }
/*      */     }
/* 1971 */     return true;
/*      */   }
/*      */ 
/*      */   public boolean endsWith(String str)
/*      */   {
/* 1983 */     if (str == null) {
/* 1984 */       return false;
/*      */     }
/* 1986 */     int len = str.length();
/* 1987 */     if (len == 0) {
/* 1988 */       return true;
/*      */     }
/* 1990 */     if (len > this.size) {
/* 1991 */       return false;
/*      */     }
/* 1993 */     int pos = this.size - len;
/* 1994 */     for (int i = 0; i < len; pos++) {
/* 1995 */       if (this.buffer[pos] != str.charAt(i))
/* 1996 */         return false;
/* 1994 */       i++;
/*      */     }
/*      */ 
/* 1999 */     return true;
/*      */   }
/*      */ 
/*      */   public CharSequence subSequence(int startIndex, int endIndex)
/*      */   {
/* 2008 */     if (startIndex < 0) {
/* 2009 */       throw new StringIndexOutOfBoundsException(startIndex);
/*      */     }
/* 2011 */     if (endIndex > this.size) {
/* 2012 */       throw new StringIndexOutOfBoundsException(endIndex);
/*      */     }
/* 2014 */     if (startIndex > endIndex) {
/* 2015 */       throw new StringIndexOutOfBoundsException(endIndex - startIndex);
/*      */     }
/* 2017 */     return substring(startIndex, endIndex);
/*      */   }
/*      */ 
/*      */   public String substring(int start)
/*      */   {
/* 2028 */     return substring(start, this.size);
/*      */   }
/*      */ 
/*      */   public String substring(int startIndex, int endIndex)
/*      */   {
/* 2045 */     endIndex = validateRange(startIndex, endIndex);
/* 2046 */     return new String(this.buffer, startIndex, endIndex - startIndex);
/*      */   }
/*      */ 
/*      */   public String leftString(int length)
/*      */   {
/* 2062 */     if (length <= 0)
/* 2063 */       return "";
/* 2064 */     if (length >= this.size) {
/* 2065 */       return new String(this.buffer, 0, this.size);
/*      */     }
/* 2067 */     return new String(this.buffer, 0, length);
/*      */   }
/*      */ 
/*      */   public String rightString(int length)
/*      */   {
/* 2084 */     if (length <= 0)
/* 2085 */       return "";
/* 2086 */     if (length >= this.size) {
/* 2087 */       return new String(this.buffer, 0, this.size);
/*      */     }
/* 2089 */     return new String(this.buffer, this.size - length, length);
/*      */   }
/*      */ 
/*      */   public String midString(int index, int length)
/*      */   {
/* 2110 */     if (index < 0) {
/* 2111 */       index = 0;
/*      */     }
/* 2113 */     if ((length <= 0) || (index >= this.size)) {
/* 2114 */       return "";
/*      */     }
/* 2116 */     if (this.size <= index + length) {
/* 2117 */       return new String(this.buffer, index, this.size - index);
/*      */     }
/* 2119 */     return new String(this.buffer, index, length);
/*      */   }
/*      */ 
/*      */   public boolean contains(char ch)
/*      */   {
/* 2131 */     char[] thisBuf = this.buffer;
/* 2132 */     for (int i = 0; i < this.size; i++) {
/* 2133 */       if (thisBuf[i] == ch) {
/* 2134 */         return true;
/*      */       }
/*      */     }
/* 2137 */     return false;
/*      */   }
/*      */ 
/*      */   public boolean contains(String str)
/*      */   {
/* 2147 */     return indexOf(str, 0) >= 0;
/*      */   }
/*      */ 
/*      */   public boolean contains(StrMatcher matcher)
/*      */   {
/* 2162 */     return indexOf(matcher, 0) >= 0;
/*      */   }
/*      */ 
/*      */   public int indexOf(char ch)
/*      */   {
/* 2173 */     return indexOf(ch, 0);
/*      */   }
/*      */ 
/*      */   public int indexOf(char ch, int startIndex)
/*      */   {
/* 2184 */     startIndex = startIndex < 0 ? 0 : startIndex;
/* 2185 */     if (startIndex >= this.size) {
/* 2186 */       return -1;
/*      */     }
/* 2188 */     char[] thisBuf = this.buffer;
/* 2189 */     for (int i = startIndex; i < this.size; i++) {
/* 2190 */       if (thisBuf[i] == ch) {
/* 2191 */         return i;
/*      */       }
/*      */     }
/* 2194 */     return -1;
/*      */   }
/*      */ 
/*      */   public int indexOf(String str)
/*      */   {
/* 2206 */     return indexOf(str, 0);
/*      */   }
/*      */ 
/*      */   public int indexOf(String str, int startIndex)
/*      */   {
/* 2220 */     startIndex = startIndex < 0 ? 0 : startIndex;
/* 2221 */     if ((str == null) || (startIndex >= this.size)) {
/* 2222 */       return -1;
/*      */     }
/* 2224 */     int strLen = str.length();
/* 2225 */     if (strLen == 1) {
/* 2226 */       return indexOf(str.charAt(0), startIndex);
/*      */     }
/* 2228 */     if (strLen == 0) {
/* 2229 */       return startIndex;
/*      */     }
/* 2231 */     if (strLen > this.size) {
/* 2232 */       return -1;
/*      */     }
/* 2234 */     char[] thisBuf = this.buffer;
/* 2235 */     int len = this.size - strLen + 1;
/*      */ 
/* 2237 */     label125: for (int i = startIndex; i < len; i++) {
/* 2238 */       for (int j = 0; j < strLen; j++) {
/* 2239 */         if (str.charAt(j) != thisBuf[(i + j)]) {
/*      */           break label125;
/*      */         }
/*      */       }
/* 2243 */       return i;
/*      */     }
/* 2245 */     return -1;
/*      */   }
/*      */ 
/*      */   public int indexOf(StrMatcher matcher)
/*      */   {
/* 2259 */     return indexOf(matcher, 0);
/*      */   }
/*      */ 
/*      */   public int indexOf(StrMatcher matcher, int startIndex)
/*      */   {
/* 2275 */     startIndex = startIndex < 0 ? 0 : startIndex;
/* 2276 */     if ((matcher == null) || (startIndex >= this.size)) {
/* 2277 */       return -1;
/*      */     }
/* 2279 */     int len = this.size;
/* 2280 */     char[] buf = this.buffer;
/* 2281 */     for (int i = startIndex; i < len; i++) {
/* 2282 */       if (matcher.isMatch(buf, i, startIndex, len) > 0) {
/* 2283 */         return i;
/*      */       }
/*      */     }
/* 2286 */     return -1;
/*      */   }
/*      */ 
/*      */   public int lastIndexOf(char ch)
/*      */   {
/* 2297 */     return lastIndexOf(ch, this.size - 1);
/*      */   }
/*      */ 
/*      */   public int lastIndexOf(char ch, int startIndex)
/*      */   {
/* 2308 */     startIndex = startIndex >= this.size ? this.size - 1 : startIndex;
/* 2309 */     if (startIndex < 0) {
/* 2310 */       return -1;
/*      */     }
/* 2312 */     for (int i = startIndex; i >= 0; i--) {
/* 2313 */       if (this.buffer[i] == ch) {
/* 2314 */         return i;
/*      */       }
/*      */     }
/* 2317 */     return -1;
/*      */   }
/*      */ 
/*      */   public int lastIndexOf(String str)
/*      */   {
/* 2329 */     return lastIndexOf(str, this.size - 1);
/*      */   }
/*      */ 
/*      */   public int lastIndexOf(String str, int startIndex)
/*      */   {
/* 2343 */     startIndex = startIndex >= this.size ? this.size - 1 : startIndex;
/* 2344 */     if ((str == null) || (startIndex < 0)) {
/* 2345 */       return -1;
/*      */     }
/* 2347 */     int strLen = str.length();
/* 2348 */     if ((strLen > 0) && (strLen <= this.size)) {
/* 2349 */       if (strLen == 1) {
/* 2350 */         return lastIndexOf(str.charAt(0), startIndex);
/*      */       }
/*      */ 
/* 2354 */       label114: for (int i = startIndex - strLen + 1; i >= 0; i--) {
/* 2355 */         for (int j = 0; j < strLen; j++) {
/* 2356 */           if (str.charAt(j) != this.buffer[(i + j)]) {
/*      */             break label114;
/*      */           }
/*      */         }
/* 2360 */         return i;
/*      */       }
/*      */     }
/* 2363 */     else if (strLen == 0) {
/* 2364 */       return startIndex;
/*      */     }
/* 2366 */     return -1;
/*      */   }
/*      */ 
/*      */   public int lastIndexOf(StrMatcher matcher)
/*      */   {
/* 2380 */     return lastIndexOf(matcher, this.size);
/*      */   }
/*      */ 
/*      */   public int lastIndexOf(StrMatcher matcher, int startIndex)
/*      */   {
/* 2396 */     startIndex = startIndex >= this.size ? this.size - 1 : startIndex;
/* 2397 */     if ((matcher == null) || (startIndex < 0)) {
/* 2398 */       return -1;
/*      */     }
/* 2400 */     char[] buf = this.buffer;
/* 2401 */     int endIndex = startIndex + 1;
/* 2402 */     for (int i = startIndex; i >= 0; i--) {
/* 2403 */       if (matcher.isMatch(buf, i, 0, endIndex) > 0) {
/* 2404 */         return i;
/*      */       }
/*      */     }
/* 2407 */     return -1;
/*      */   }
/*      */ 
/*      */   public StrTokenizer asTokenizer()
/*      */   {
/* 2444 */     return new StrBuilderTokenizer();
/*      */   }
/*      */ 
/*      */   public Reader asReader()
/*      */   {
/* 2468 */     return new StrBuilderReader();
/*      */   }
/*      */ 
/*      */   public Writer asWriter()
/*      */   {
/* 2493 */     return new StrBuilderWriter();
/*      */   }
/*      */ 
/*      */   public boolean equalsIgnoreCase(StrBuilder other)
/*      */   {
/* 2535 */     if (this == other) {
/* 2536 */       return true;
/*      */     }
/* 2538 */     if (this.size != other.size) {
/* 2539 */       return false;
/*      */     }
/* 2541 */     char[] thisBuf = this.buffer;
/* 2542 */     char[] otherBuf = other.buffer;
/* 2543 */     for (int i = this.size - 1; i >= 0; i--) {
/* 2544 */       char c1 = thisBuf[i];
/* 2545 */       char c2 = otherBuf[i];
/* 2546 */       if ((c1 != c2) && (Character.toUpperCase(c1) != Character.toUpperCase(c2))) {
/* 2547 */         return false;
/*      */       }
/*      */     }
/* 2550 */     return true;
/*      */   }
/*      */ 
/*      */   public boolean equals(StrBuilder other)
/*      */   {
/* 2561 */     if (this == other) {
/* 2562 */       return true;
/*      */     }
/* 2564 */     if (this.size != other.size) {
/* 2565 */       return false;
/*      */     }
/* 2567 */     char[] thisBuf = this.buffer;
/* 2568 */     char[] otherBuf = other.buffer;
/* 2569 */     for (int i = this.size - 1; i >= 0; i--) {
/* 2570 */       if (thisBuf[i] != otherBuf[i]) {
/* 2571 */         return false;
/*      */       }
/*      */     }
/* 2574 */     return true;
/*      */   }
/*      */ 
/*      */   public boolean equals(Object obj)
/*      */   {
/* 2586 */     if ((obj instanceof StrBuilder)) {
/* 2587 */       return equals((StrBuilder)obj);
/*      */     }
/* 2589 */     return false;
/*      */   }
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 2599 */     char[] buf = this.buffer;
/* 2600 */     int hash = 0;
/* 2601 */     for (int i = this.size - 1; i >= 0; i--) {
/* 2602 */       hash = 31 * hash + buf[i];
/*      */     }
/* 2604 */     return hash;
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/* 2619 */     return new String(this.buffer, 0, this.size);
/*      */   }
/*      */ 
/*      */   public StringBuffer toStringBuffer()
/*      */   {
/* 2629 */     return new StringBuffer(this.size).append(this.buffer, 0, this.size);
/*      */   }
/*      */ 
/*      */   protected int validateRange(int startIndex, int endIndex)
/*      */   {
/* 2643 */     if (startIndex < 0) {
/* 2644 */       throw new StringIndexOutOfBoundsException(startIndex);
/*      */     }
/* 2646 */     if (endIndex > this.size) {
/* 2647 */       endIndex = this.size;
/*      */     }
/* 2649 */     if (startIndex > endIndex) {
/* 2650 */       throw new StringIndexOutOfBoundsException("end < start");
/*      */     }
/* 2652 */     return endIndex;
/*      */   }
/*      */ 
/*      */   protected void validateIndex(int index)
/*      */   {
/* 2662 */     if ((index < 0) || (index > this.size))
/* 2663 */       throw new StringIndexOutOfBoundsException(index);
/*      */   }
/*      */ 
/*      */   class StrBuilderWriter extends Writer
/*      */   {
/*      */     StrBuilderWriter()
/*      */     {
/*      */     }
/*      */ 
/*      */     public void close()
/*      */     {
/*      */     }
/*      */ 
/*      */     public void flush()
/*      */     {
/*      */     }
/*      */ 
/*      */     public void write(int c)
/*      */     {
/* 2821 */       StrBuilder.this.append((char)c);
/*      */     }
/*      */ 
/*      */     public void write(char[] cbuf)
/*      */     {
/* 2827 */       StrBuilder.this.append(cbuf);
/*      */     }
/*      */ 
/*      */     public void write(char[] cbuf, int off, int len)
/*      */     {
/* 2833 */       StrBuilder.this.append(cbuf, off, len);
/*      */     }
/*      */ 
/*      */     public void write(String str)
/*      */     {
/* 2839 */       StrBuilder.this.append(str);
/*      */     }
/*      */ 
/*      */     public void write(String str, int off, int len)
/*      */     {
/* 2845 */       StrBuilder.this.append(str, off, len);
/*      */     }
/*      */   }
/*      */ 
/*      */   class StrBuilderReader extends Reader
/*      */   {
/*      */     private int pos;
/*      */     private int mark;
/*      */ 
/*      */     StrBuilderReader()
/*      */     {
/*      */     }
/*      */ 
/*      */     public void close()
/*      */     {
/*      */     }
/*      */ 
/*      */     public int read()
/*      */     {
/* 2728 */       if (!ready()) {
/* 2729 */         return -1;
/*      */       }
/* 2731 */       return StrBuilder.this.charAt(this.pos++);
/*      */     }
/*      */ 
/*      */     public int read(char[] b, int off, int len)
/*      */     {
/* 2737 */       if ((off < 0) || (len < 0) || (off > b.length) || (off + len > b.length) || (off + len < 0))
/*      */       {
/* 2739 */         throw new IndexOutOfBoundsException();
/*      */       }
/* 2741 */       if (len == 0) {
/* 2742 */         return 0;
/*      */       }
/* 2744 */       if (this.pos >= StrBuilder.this.size()) {
/* 2745 */         return -1;
/*      */       }
/* 2747 */       if (this.pos + len > StrBuilder.this.size()) {
/* 2748 */         len = StrBuilder.this.size() - this.pos;
/*      */       }
/* 2750 */       StrBuilder.this.getChars(this.pos, this.pos + len, b, off);
/* 2751 */       this.pos += len;
/* 2752 */       return len;
/*      */     }
/*      */ 
/*      */     public long skip(long n)
/*      */     {
/* 2758 */       if (this.pos + n > StrBuilder.this.size()) {
/* 2759 */         n = StrBuilder.this.size() - this.pos;
/*      */       }
/* 2761 */       if (n < 0L) {
/* 2762 */         return 0L;
/*      */       }
/* 2764 */       this.pos = ((int)(this.pos + n));
/* 2765 */       return n;
/*      */     }
/*      */ 
/*      */     public boolean ready()
/*      */     {
/* 2771 */       return this.pos < StrBuilder.this.size();
/*      */     }
/*      */ 
/*      */     public boolean markSupported()
/*      */     {
/* 2777 */       return true;
/*      */     }
/*      */ 
/*      */     public void mark(int readAheadLimit)
/*      */     {
/* 2783 */       this.mark = this.pos;
/*      */     }
/*      */ 
/*      */     public void reset()
/*      */     {
/* 2789 */       this.pos = this.mark;
/*      */     }
/*      */   }
/*      */ 
/*      */   class StrBuilderTokenizer extends StrTokenizer
/*      */   {
/*      */     StrBuilderTokenizer()
/*      */     {
/*      */     }
/*      */ 
/*      */     protected List<String> tokenize(char[] chars, int offset, int count)
/*      */     {
/* 2683 */       if (chars == null) {
/* 2684 */         return super.tokenize(StrBuilder.this.buffer, 0, StrBuilder.this.size());
/*      */       }
/* 2686 */       return super.tokenize(chars, offset, count);
/*      */     }
/*      */ 
/*      */     public String getContent()
/*      */     {
/* 2693 */       String str = super.getContent();
/* 2694 */       if (str == null) {
/* 2695 */         return StrBuilder.this.toString();
/*      */       }
/* 2697 */       return str;
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.StrBuilder
 * JD-Core Version:    0.6.2
 */