/*      */ package org.apache.commons.lang3.text;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.NoSuchElementException;
/*      */ import org.apache.commons.lang3.ArrayUtils;
/*      */ 
/*      */ public class StrTokenizer
/*      */   implements ListIterator<String>, Cloneable
/*      */ {
/*   92 */   private static final StrTokenizer CSV_TOKENIZER_PROTOTYPE = new StrTokenizer();
/*      */   private static final StrTokenizer TSV_TOKENIZER_PROTOTYPE;
/*      */   private char[] chars;
/*      */   private String[] tokens;
/*      */   private int tokenPos;
/*  117 */   private StrMatcher delimMatcher = StrMatcher.splitMatcher();
/*      */ 
/*  119 */   private StrMatcher quoteMatcher = StrMatcher.noneMatcher();
/*      */ 
/*  121 */   private StrMatcher ignoredMatcher = StrMatcher.noneMatcher();
/*      */ 
/*  123 */   private StrMatcher trimmerMatcher = StrMatcher.noneMatcher();
/*      */ 
/*  126 */   private boolean emptyAsNull = false;
/*      */ 
/*  128 */   private boolean ignoreEmptyTokens = true;
/*      */ 
/*      */   private static StrTokenizer getCSVClone()
/*      */   {
/*  138 */     return (StrTokenizer)CSV_TOKENIZER_PROTOTYPE.clone();
/*      */   }
/*      */ 
/*      */   public static StrTokenizer getCSVInstance()
/*      */   {
/*  151 */     return getCSVClone();
/*      */   }
/*      */ 
/*      */   public static StrTokenizer getCSVInstance(String input)
/*      */   {
/*  164 */     StrTokenizer tok = getCSVClone();
/*  165 */     tok.reset(input);
/*  166 */     return tok;
/*      */   }
/*      */ 
/*      */   public static StrTokenizer getCSVInstance(char[] input)
/*      */   {
/*  179 */     StrTokenizer tok = getCSVClone();
/*  180 */     tok.reset(input);
/*  181 */     return tok;
/*      */   }
/*      */ 
/*      */   private static StrTokenizer getTSVClone()
/*      */   {
/*  190 */     return (StrTokenizer)TSV_TOKENIZER_PROTOTYPE.clone();
/*      */   }
/*      */ 
/*      */   public static StrTokenizer getTSVInstance()
/*      */   {
/*  203 */     return getTSVClone();
/*      */   }
/*      */ 
/*      */   public static StrTokenizer getTSVInstance(String input)
/*      */   {
/*  214 */     StrTokenizer tok = getTSVClone();
/*  215 */     tok.reset(input);
/*  216 */     return tok;
/*      */   }
/*      */ 
/*      */   public static StrTokenizer getTSVInstance(char[] input)
/*      */   {
/*  227 */     StrTokenizer tok = getTSVClone();
/*  228 */     tok.reset(input);
/*  229 */     return tok;
/*      */   }
/*      */ 
/*      */   public StrTokenizer()
/*      */   {
/*  241 */     this.chars = null;
/*      */   }
/*      */ 
/*      */   public StrTokenizer(String input)
/*      */   {
/*  252 */     if (input != null)
/*  253 */       this.chars = input.toCharArray();
/*      */     else
/*  255 */       this.chars = null;
/*      */   }
/*      */ 
/*      */   public StrTokenizer(String input, char delim)
/*      */   {
/*  266 */     this(input);
/*  267 */     setDelimiterChar(delim);
/*      */   }
/*      */ 
/*      */   public StrTokenizer(String input, String delim)
/*      */   {
/*  277 */     this(input);
/*  278 */     setDelimiterString(delim);
/*      */   }
/*      */ 
/*      */   public StrTokenizer(String input, StrMatcher delim)
/*      */   {
/*  288 */     this(input);
/*  289 */     setDelimiterMatcher(delim);
/*      */   }
/*      */ 
/*      */   public StrTokenizer(String input, char delim, char quote)
/*      */   {
/*  301 */     this(input, delim);
/*  302 */     setQuoteChar(quote);
/*      */   }
/*      */ 
/*      */   public StrTokenizer(String input, StrMatcher delim, StrMatcher quote)
/*      */   {
/*  314 */     this(input, delim);
/*  315 */     setQuoteMatcher(quote);
/*      */   }
/*      */ 
/*      */   public StrTokenizer(char[] input)
/*      */   {
/*  326 */     this.chars = ArrayUtils.clone(input);
/*      */   }
/*      */ 
/*      */   public StrTokenizer(char[] input, char delim)
/*      */   {
/*  336 */     this(input);
/*  337 */     setDelimiterChar(delim);
/*      */   }
/*      */ 
/*      */   public StrTokenizer(char[] input, String delim)
/*      */   {
/*  347 */     this(input);
/*  348 */     setDelimiterString(delim);
/*      */   }
/*      */ 
/*      */   public StrTokenizer(char[] input, StrMatcher delim)
/*      */   {
/*  358 */     this(input);
/*  359 */     setDelimiterMatcher(delim);
/*      */   }
/*      */ 
/*      */   public StrTokenizer(char[] input, char delim, char quote)
/*      */   {
/*  371 */     this(input, delim);
/*  372 */     setQuoteChar(quote);
/*      */   }
/*      */ 
/*      */   public StrTokenizer(char[] input, StrMatcher delim, StrMatcher quote)
/*      */   {
/*  384 */     this(input, delim);
/*  385 */     setQuoteMatcher(quote);
/*      */   }
/*      */ 
/*      */   public int size()
/*      */   {
/*  396 */     checkTokenized();
/*  397 */     return this.tokens.length;
/*      */   }
/*      */ 
/*      */   public String nextToken()
/*      */   {
/*  408 */     if (hasNext()) {
/*  409 */       return this.tokens[(this.tokenPos++)];
/*      */     }
/*  411 */     return null;
/*      */   }
/*      */ 
/*      */   public String previousToken()
/*      */   {
/*  420 */     if (hasPrevious()) {
/*  421 */       return this.tokens[(--this.tokenPos)];
/*      */     }
/*  423 */     return null;
/*      */   }
/*      */ 
/*      */   public String[] getTokenArray()
/*      */   {
/*  432 */     checkTokenized();
/*  433 */     return (String[])this.tokens.clone();
/*      */   }
/*      */ 
/*      */   public List<String> getTokenList()
/*      */   {
/*  442 */     checkTokenized();
/*  443 */     List list = new ArrayList(this.tokens.length);
/*  444 */     for (String element : this.tokens) {
/*  445 */       list.add(element);
/*      */     }
/*  447 */     return list;
/*      */   }
/*      */ 
/*      */   public StrTokenizer reset()
/*      */   {
/*  458 */     this.tokenPos = 0;
/*  459 */     this.tokens = null;
/*  460 */     return this;
/*      */   }
/*      */ 
/*      */   public StrTokenizer reset(String input)
/*      */   {
/*  472 */     reset();
/*  473 */     if (input != null)
/*  474 */       this.chars = input.toCharArray();
/*      */     else {
/*  476 */       this.chars = null;
/*      */     }
/*  478 */     return this;
/*      */   }
/*      */ 
/*      */   public StrTokenizer reset(char[] input)
/*      */   {
/*  490 */     reset();
/*  491 */     this.chars = ArrayUtils.clone(input);
/*  492 */     return this;
/*      */   }
/*      */ 
/*      */   public boolean hasNext()
/*      */   {
/*  503 */     checkTokenized();
/*  504 */     return this.tokenPos < this.tokens.length;
/*      */   }
/*      */ 
/*      */   public String next()
/*      */   {
/*  514 */     if (hasNext()) {
/*  515 */       return this.tokens[(this.tokenPos++)];
/*      */     }
/*  517 */     throw new NoSuchElementException();
/*      */   }
/*      */ 
/*      */   public int nextIndex()
/*      */   {
/*  526 */     return this.tokenPos;
/*      */   }
/*      */ 
/*      */   public boolean hasPrevious()
/*      */   {
/*  535 */     checkTokenized();
/*  536 */     return this.tokenPos > 0;
/*      */   }
/*      */ 
/*      */   public String previous()
/*      */   {
/*  545 */     if (hasPrevious()) {
/*  546 */       return this.tokens[(--this.tokenPos)];
/*      */     }
/*  548 */     throw new NoSuchElementException();
/*      */   }
/*      */ 
/*      */   public int previousIndex()
/*      */   {
/*  557 */     return this.tokenPos - 1;
/*      */   }
/*      */ 
/*      */   public void remove()
/*      */   {
/*  566 */     throw new UnsupportedOperationException("remove() is unsupported");
/*      */   }
/*      */ 
/*      */   public void set(String obj)
/*      */   {
/*  575 */     throw new UnsupportedOperationException("set() is unsupported");
/*      */   }
/*      */ 
/*      */   public void add(String obj)
/*      */   {
/*  584 */     throw new UnsupportedOperationException("add() is unsupported");
/*      */   }
/*      */ 
/*      */   private void checkTokenized()
/*      */   {
/*  593 */     if (this.tokens == null)
/*  594 */       if (this.chars == null)
/*      */       {
/*  596 */         List split = tokenize(null, 0, 0);
/*  597 */         this.tokens = ((String[])split.toArray(new String[split.size()]));
/*      */       } else {
/*  599 */         List split = tokenize(this.chars, 0, this.chars.length);
/*  600 */         this.tokens = ((String[])split.toArray(new String[split.size()]));
/*      */       }
/*      */   }
/*      */ 
/*      */   protected List<String> tokenize(char[] chars, int offset, int count)
/*      */   {
/*  626 */     if ((chars == null) || (count == 0)) {
/*  627 */       return Collections.emptyList();
/*      */     }
/*  629 */     StrBuilder buf = new StrBuilder();
/*  630 */     List tokens = new ArrayList();
/*  631 */     int pos = offset;
/*      */ 
/*  634 */     while ((pos >= 0) && (pos < count))
/*      */     {
/*  636 */       pos = readNextToken(chars, pos, count, buf, tokens);
/*      */ 
/*  639 */       if (pos >= count) {
/*  640 */         addToken(tokens, "");
/*      */       }
/*      */     }
/*  643 */     return tokens;
/*      */   }
/*      */ 
/*      */   private void addToken(List<String> list, String tok)
/*      */   {
/*  653 */     if ((tok == null) || (tok.length() == 0)) {
/*  654 */       if (isIgnoreEmptyTokens()) {
/*  655 */         return;
/*      */       }
/*  657 */       if (isEmptyTokenAsNull()) {
/*  658 */         tok = null;
/*      */       }
/*      */     }
/*  661 */     list.add(tok);
/*      */   }
/*      */ 
/*      */   private int readNextToken(char[] chars, int start, int len, StrBuilder workArea, List<String> tokens)
/*      */   {
/*  678 */     while (start < len) {
/*  679 */       int removeLen = Math.max(getIgnoredMatcher().isMatch(chars, start, start, len), getTrimmerMatcher().isMatch(chars, start, start, len));
/*      */ 
/*  682 */       if ((removeLen == 0) || (getDelimiterMatcher().isMatch(chars, start, start, len) > 0) || (getQuoteMatcher().isMatch(chars, start, start, len) > 0))
/*      */       {
/*      */         break;
/*      */       }
/*      */ 
/*  687 */       start += removeLen;
/*      */     }
/*      */ 
/*  691 */     if (start >= len) {
/*  692 */       addToken(tokens, "");
/*  693 */       return -1;
/*      */     }
/*      */ 
/*  697 */     int delimLen = getDelimiterMatcher().isMatch(chars, start, start, len);
/*  698 */     if (delimLen > 0) {
/*  699 */       addToken(tokens, "");
/*  700 */       return start + delimLen;
/*      */     }
/*      */ 
/*  704 */     int quoteLen = getQuoteMatcher().isMatch(chars, start, start, len);
/*  705 */     if (quoteLen > 0) {
/*  706 */       return readWithQuotes(chars, start + quoteLen, len, workArea, tokens, start, quoteLen);
/*      */     }
/*  708 */     return readWithQuotes(chars, start, len, workArea, tokens, 0, 0);
/*      */   }
/*      */ 
/*      */   private int readWithQuotes(char[] chars, int start, int len, StrBuilder workArea, List<String> tokens, int quoteStart, int quoteLen)
/*      */   {
/*  729 */     workArea.clear();
/*  730 */     int pos = start;
/*  731 */     boolean quoting = quoteLen > 0;
/*  732 */     int trimStart = 0;
/*      */ 
/*  734 */     while (pos < len)
/*      */     {
/*  738 */       if (quoting)
/*      */       {
/*  745 */         if (isQuote(chars, pos, len, quoteStart, quoteLen)) {
/*  746 */           if (isQuote(chars, pos + quoteLen, len, quoteStart, quoteLen))
/*      */           {
/*  748 */             workArea.append(chars, pos, quoteLen);
/*  749 */             pos += quoteLen * 2;
/*  750 */             trimStart = workArea.size();
/*      */           }
/*      */           else
/*      */           {
/*  755 */             quoting = false;
/*  756 */             pos += quoteLen;
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*  761 */           workArea.append(chars[(pos++)]);
/*  762 */           trimStart = workArea.size();
/*      */         }
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  768 */         int delimLen = getDelimiterMatcher().isMatch(chars, pos, start, len);
/*  769 */         if (delimLen > 0)
/*      */         {
/*  771 */           addToken(tokens, workArea.substring(0, trimStart));
/*  772 */           return pos + delimLen;
/*      */         }
/*      */ 
/*  776 */         if ((quoteLen > 0) && (isQuote(chars, pos, len, quoteStart, quoteLen))) {
/*  777 */           quoting = true;
/*  778 */           pos += quoteLen;
/*      */         }
/*      */         else
/*      */         {
/*  783 */           int ignoredLen = getIgnoredMatcher().isMatch(chars, pos, start, len);
/*  784 */           if (ignoredLen > 0) {
/*  785 */             pos += ignoredLen;
/*      */           }
/*      */           else
/*      */           {
/*  792 */             int trimmedLen = getTrimmerMatcher().isMatch(chars, pos, start, len);
/*  793 */             if (trimmedLen > 0) {
/*  794 */               workArea.append(chars, pos, trimmedLen);
/*  795 */               pos += trimmedLen;
/*      */             }
/*      */             else
/*      */             {
/*  800 */               workArea.append(chars[(pos++)]);
/*  801 */               trimStart = workArea.size();
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  806 */     addToken(tokens, workArea.substring(0, trimStart));
/*  807 */     return -1;
/*      */   }
/*      */ 
/*      */   private boolean isQuote(char[] chars, int pos, int len, int quoteStart, int quoteLen)
/*      */   {
/*  822 */     for (int i = 0; i < quoteLen; i++) {
/*  823 */       if ((pos + i >= len) || (chars[(pos + i)] != chars[(quoteStart + i)])) {
/*  824 */         return false;
/*      */       }
/*      */     }
/*  827 */     return true;
/*      */   }
/*      */ 
/*      */   public StrMatcher getDelimiterMatcher()
/*      */   {
/*  838 */     return this.delimMatcher;
/*      */   }
/*      */ 
/*      */   public StrTokenizer setDelimiterMatcher(StrMatcher delim)
/*      */   {
/*  850 */     if (delim == null)
/*  851 */       this.delimMatcher = StrMatcher.noneMatcher();
/*      */     else {
/*  853 */       this.delimMatcher = delim;
/*      */     }
/*  855 */     return this;
/*      */   }
/*      */ 
/*      */   public StrTokenizer setDelimiterChar(char delim)
/*      */   {
/*  865 */     return setDelimiterMatcher(StrMatcher.charMatcher(delim));
/*      */   }
/*      */ 
/*      */   public StrTokenizer setDelimiterString(String delim)
/*      */   {
/*  875 */     return setDelimiterMatcher(StrMatcher.stringMatcher(delim));
/*      */   }
/*      */ 
/*      */   public StrMatcher getQuoteMatcher()
/*      */   {
/*  890 */     return this.quoteMatcher;
/*      */   }
/*      */ 
/*      */   public StrTokenizer setQuoteMatcher(StrMatcher quote)
/*      */   {
/*  903 */     if (quote != null) {
/*  904 */       this.quoteMatcher = quote;
/*      */     }
/*  906 */     return this;
/*      */   }
/*      */ 
/*      */   public StrTokenizer setQuoteChar(char quote)
/*      */   {
/*  919 */     return setQuoteMatcher(StrMatcher.charMatcher(quote));
/*      */   }
/*      */ 
/*      */   public StrMatcher getIgnoredMatcher()
/*      */   {
/*  934 */     return this.ignoredMatcher;
/*      */   }
/*      */ 
/*      */   public StrTokenizer setIgnoredMatcher(StrMatcher ignored)
/*      */   {
/*  947 */     if (ignored != null) {
/*  948 */       this.ignoredMatcher = ignored;
/*      */     }
/*  950 */     return this;
/*      */   }
/*      */ 
/*      */   public StrTokenizer setIgnoredChar(char ignored)
/*      */   {
/*  963 */     return setIgnoredMatcher(StrMatcher.charMatcher(ignored));
/*      */   }
/*      */ 
/*      */   public StrMatcher getTrimmerMatcher()
/*      */   {
/*  978 */     return this.trimmerMatcher;
/*      */   }
/*      */ 
/*      */   public StrTokenizer setTrimmerMatcher(StrMatcher trimmer)
/*      */   {
/*  991 */     if (trimmer != null) {
/*  992 */       this.trimmerMatcher = trimmer;
/*      */     }
/*  994 */     return this;
/*      */   }
/*      */ 
/*      */   public boolean isEmptyTokenAsNull()
/*      */   {
/* 1005 */     return this.emptyAsNull;
/*      */   }
/*      */ 
/*      */   public StrTokenizer setEmptyTokenAsNull(boolean emptyAsNull)
/*      */   {
/* 1016 */     this.emptyAsNull = emptyAsNull;
/* 1017 */     return this;
/*      */   }
/*      */ 
/*      */   public boolean isIgnoreEmptyTokens()
/*      */   {
/* 1028 */     return this.ignoreEmptyTokens;
/*      */   }
/*      */ 
/*      */   public StrTokenizer setIgnoreEmptyTokens(boolean ignoreEmptyTokens)
/*      */   {
/* 1039 */     this.ignoreEmptyTokens = ignoreEmptyTokens;
/* 1040 */     return this;
/*      */   }
/*      */ 
/*      */   public String getContent()
/*      */   {
/* 1050 */     if (this.chars == null) {
/* 1051 */       return null;
/*      */     }
/* 1053 */     return new String(this.chars);
/*      */   }
/*      */ 
/*      */   public Object clone()
/*      */   {
/*      */     try
/*      */     {
/* 1067 */       return cloneReset(); } catch (CloneNotSupportedException ex) {
/*      */     }
/* 1069 */     return null;
/*      */   }
/*      */ 
/*      */   Object cloneReset()
/*      */     throws CloneNotSupportedException
/*      */   {
/* 1082 */     StrTokenizer cloned = (StrTokenizer)super.clone();
/* 1083 */     if (cloned.chars != null) {
/* 1084 */       cloned.chars = ((char[])cloned.chars.clone());
/*      */     }
/* 1086 */     cloned.reset();
/* 1087 */     return cloned;
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1098 */     if (this.tokens == null) {
/* 1099 */       return "StrTokenizer[not tokenized yet]";
/*      */     }
/* 1101 */     return "StrTokenizer" + getTokenList();
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*   93 */     CSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(StrMatcher.commaMatcher());
/*   94 */     CSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
/*   95 */     CSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(StrMatcher.noneMatcher());
/*   96 */     CSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(StrMatcher.trimMatcher());
/*   97 */     CSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
/*   98 */     CSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);
/*      */ 
/*  100 */     TSV_TOKENIZER_PROTOTYPE = new StrTokenizer();
/*  101 */     TSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(StrMatcher.tabMatcher());
/*  102 */     TSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
/*  103 */     TSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(StrMatcher.noneMatcher());
/*  104 */     TSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(StrMatcher.trimMatcher());
/*  105 */     TSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
/*  106 */     TSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.StrTokenizer
 * JD-Core Version:    0.6.2
 */