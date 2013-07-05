/*     */ package org.apache.commons.lang3.text;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class StrSubstitutor
/*     */ {
/*     */   public static final char DEFAULT_ESCAPE = '$';
/* 112 */   public static final StrMatcher DEFAULT_PREFIX = StrMatcher.stringMatcher("${");
/*     */ 
/* 116 */   public static final StrMatcher DEFAULT_SUFFIX = StrMatcher.stringMatcher("}");
/*     */   private char escapeChar;
/*     */   private StrMatcher prefixMatcher;
/*     */   private StrMatcher suffixMatcher;
/*     */   private StrLookup<?> variableResolver;
/*     */   private boolean enableSubstitutionInVariables;
/*     */ 
/*     */   public static <V> String replace(Object source, Map<String, V> valueMap)
/*     */   {
/* 150 */     return new StrSubstitutor(valueMap).replace(source);
/*     */   }
/*     */ 
/*     */   public static <V> String replace(Object source, Map<String, V> valueMap, String prefix, String suffix)
/*     */   {
/* 167 */     return new StrSubstitutor(valueMap, prefix, suffix).replace(source);
/*     */   }
/*     */ 
/*     */   public static String replace(Object source, Properties valueProperties)
/*     */   {
/* 179 */     if (valueProperties == null) {
/* 180 */       return source.toString();
/*     */     }
/* 182 */     Map valueMap = new HashMap();
/* 183 */     Enumeration propNames = valueProperties.propertyNames();
/* 184 */     while (propNames.hasMoreElements()) {
/* 185 */       String propName = (String)propNames.nextElement();
/* 186 */       String propValue = valueProperties.getProperty(propName);
/* 187 */       valueMap.put(propName, propValue);
/*     */     }
/* 189 */     return replace(source, valueMap);
/*     */   }
/*     */ 
/*     */   public static String replaceSystemProperties(Object source)
/*     */   {
/* 200 */     return new StrSubstitutor(StrLookup.systemPropertiesLookup()).replace(source);
/*     */   }
/*     */ 
/*     */   public StrSubstitutor()
/*     */   {
/* 209 */     this((StrLookup)null, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
/*     */   }
/*     */ 
/*     */   public <V> StrSubstitutor(Map<String, V> valueMap)
/*     */   {
/* 220 */     this(StrLookup.mapLookup(valueMap), DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
/*     */   }
/*     */ 
/*     */   public <V> StrSubstitutor(Map<String, V> valueMap, String prefix, String suffix)
/*     */   {
/* 233 */     this(StrLookup.mapLookup(valueMap), prefix, suffix, '$');
/*     */   }
/*     */ 
/*     */   public <V> StrSubstitutor(Map<String, V> valueMap, String prefix, String suffix, char escape)
/*     */   {
/* 247 */     this(StrLookup.mapLookup(valueMap), prefix, suffix, escape);
/*     */   }
/*     */ 
/*     */   public StrSubstitutor(StrLookup<?> variableResolver)
/*     */   {
/* 256 */     this(variableResolver, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
/*     */   }
/*     */ 
/*     */   public StrSubstitutor(StrLookup<?> variableResolver, String prefix, String suffix, char escape)
/*     */   {
/* 269 */     setVariableResolver(variableResolver);
/* 270 */     setVariablePrefix(prefix);
/* 271 */     setVariableSuffix(suffix);
/* 272 */     setEscapeChar(escape);
/*     */   }
/*     */ 
/*     */   public StrSubstitutor(StrLookup<?> variableResolver, StrMatcher prefixMatcher, StrMatcher suffixMatcher, char escape)
/*     */   {
/* 286 */     setVariableResolver(variableResolver);
/* 287 */     setVariablePrefixMatcher(prefixMatcher);
/* 288 */     setVariableSuffixMatcher(suffixMatcher);
/* 289 */     setEscapeChar(escape);
/*     */   }
/*     */ 
/*     */   public String replace(String source)
/*     */   {
/* 301 */     if (source == null) {
/* 302 */       return null;
/*     */     }
/* 304 */     StrBuilder buf = new StrBuilder(source);
/* 305 */     if (!substitute(buf, 0, source.length())) {
/* 306 */       return source;
/*     */     }
/* 308 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public String replace(String source, int offset, int length)
/*     */   {
/* 324 */     if (source == null) {
/* 325 */       return null;
/*     */     }
/* 327 */     StrBuilder buf = new StrBuilder(length).append(source, offset, length);
/* 328 */     if (!substitute(buf, 0, length)) {
/* 329 */       return source.substring(offset, offset + length);
/*     */     }
/* 331 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public String replace(char[] source)
/*     */   {
/* 344 */     if (source == null) {
/* 345 */       return null;
/*     */     }
/* 347 */     StrBuilder buf = new StrBuilder(source.length).append(source);
/* 348 */     substitute(buf, 0, source.length);
/* 349 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public String replace(char[] source, int offset, int length)
/*     */   {
/* 366 */     if (source == null) {
/* 367 */       return null;
/*     */     }
/* 369 */     StrBuilder buf = new StrBuilder(length).append(source, offset, length);
/* 370 */     substitute(buf, 0, length);
/* 371 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public String replace(StringBuffer source)
/*     */   {
/* 384 */     if (source == null) {
/* 385 */       return null;
/*     */     }
/* 387 */     StrBuilder buf = new StrBuilder(source.length()).append(source);
/* 388 */     substitute(buf, 0, buf.length());
/* 389 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public String replace(StringBuffer source, int offset, int length)
/*     */   {
/* 406 */     if (source == null) {
/* 407 */       return null;
/*     */     }
/* 409 */     StrBuilder buf = new StrBuilder(length).append(source, offset, length);
/* 410 */     substitute(buf, 0, length);
/* 411 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public String replace(StrBuilder source)
/*     */   {
/* 424 */     if (source == null) {
/* 425 */       return null;
/*     */     }
/* 427 */     StrBuilder buf = new StrBuilder(source.length()).append(source);
/* 428 */     substitute(buf, 0, buf.length());
/* 429 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public String replace(StrBuilder source, int offset, int length)
/*     */   {
/* 446 */     if (source == null) {
/* 447 */       return null;
/*     */     }
/* 449 */     StrBuilder buf = new StrBuilder(length).append(source, offset, length);
/* 450 */     substitute(buf, 0, length);
/* 451 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public String replace(Object source)
/*     */   {
/* 464 */     if (source == null) {
/* 465 */       return null;
/*     */     }
/* 467 */     StrBuilder buf = new StrBuilder().append(source);
/* 468 */     substitute(buf, 0, buf.length());
/* 469 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public boolean replaceIn(StringBuffer source)
/*     */   {
/* 482 */     if (source == null) {
/* 483 */       return false;
/*     */     }
/* 485 */     return replaceIn(source, 0, source.length());
/*     */   }
/*     */ 
/*     */   public boolean replaceIn(StringBuffer source, int offset, int length)
/*     */   {
/* 502 */     if (source == null) {
/* 503 */       return false;
/*     */     }
/* 505 */     StrBuilder buf = new StrBuilder(length).append(source, offset, length);
/* 506 */     if (!substitute(buf, 0, length)) {
/* 507 */       return false;
/*     */     }
/* 509 */     source.replace(offset, offset + length, buf.toString());
/* 510 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean replaceIn(StrBuilder source)
/*     */   {
/* 522 */     if (source == null) {
/* 523 */       return false;
/*     */     }
/* 525 */     return substitute(source, 0, source.length());
/*     */   }
/*     */ 
/*     */   public boolean replaceIn(StrBuilder source, int offset, int length)
/*     */   {
/* 541 */     if (source == null) {
/* 542 */       return false;
/*     */     }
/* 544 */     return substitute(source, offset, length);
/*     */   }
/*     */ 
/*     */   protected boolean substitute(StrBuilder buf, int offset, int length)
/*     */   {
/* 563 */     return substitute(buf, offset, length, null) > 0;
/*     */   }
/*     */ 
/*     */   private int substitute(StrBuilder buf, int offset, int length, List<String> priorVariables)
/*     */   {
/* 579 */     StrMatcher prefixMatcher = getVariablePrefixMatcher();
/* 580 */     StrMatcher suffixMatcher = getVariableSuffixMatcher();
/* 581 */     char escape = getEscapeChar();
/*     */ 
/* 583 */     boolean top = priorVariables == null;
/* 584 */     boolean altered = false;
/* 585 */     int lengthChange = 0;
/* 586 */     char[] chars = buf.buffer;
/* 587 */     int bufEnd = offset + length;
/* 588 */     int pos = offset;
/* 589 */     while (pos < bufEnd) {
/* 590 */       int startMatchLen = prefixMatcher.isMatch(chars, pos, offset, bufEnd);
/*     */ 
/* 592 */       if (startMatchLen == 0) {
/* 593 */         pos++;
/*     */       }
/* 596 */       else if ((pos > offset) && (chars[(pos - 1)] == escape))
/*     */       {
/* 598 */         buf.deleteCharAt(pos - 1);
/* 599 */         chars = buf.buffer;
/* 600 */         lengthChange--;
/* 601 */         altered = true;
/* 602 */         bufEnd--;
/*     */       }
/*     */       else {
/* 605 */         int startPos = pos;
/* 606 */         pos += startMatchLen;
/* 607 */         int endMatchLen = 0;
/* 608 */         int nestedVarCount = 0;
/* 609 */         while (pos < bufEnd) {
/* 610 */           if ((isEnableSubstitutionInVariables()) && ((endMatchLen = prefixMatcher.isMatch(chars, pos, offset, bufEnd)) != 0))
/*     */           {
/* 614 */             nestedVarCount++;
/* 615 */             pos += endMatchLen;
/*     */           }
/*     */           else
/*     */           {
/* 619 */             endMatchLen = suffixMatcher.isMatch(chars, pos, offset, bufEnd);
/*     */ 
/* 621 */             if (endMatchLen == 0) {
/* 622 */               pos++;
/*     */             }
/*     */             else {
/* 625 */               if (nestedVarCount == 0) {
/* 626 */                 String varName = new String(chars, startPos + startMatchLen, pos - startPos - startMatchLen);
/*     */ 
/* 629 */                 if (isEnableSubstitutionInVariables()) {
/* 630 */                   StrBuilder bufName = new StrBuilder(varName);
/* 631 */                   substitute(bufName, 0, bufName.length());
/* 632 */                   varName = bufName.toString();
/*     */                 }
/* 634 */                 pos += endMatchLen;
/* 635 */                 int endPos = pos;
/*     */ 
/* 638 */                 if (priorVariables == null) {
/* 639 */                   priorVariables = new ArrayList();
/* 640 */                   priorVariables.add(new String(chars, offset, length));
/*     */                 }
/*     */ 
/* 645 */                 checkCyclicSubstitution(varName, priorVariables);
/* 646 */                 priorVariables.add(varName);
/*     */ 
/* 649 */                 String varValue = resolveVariable(varName, buf, startPos, endPos);
/*     */ 
/* 651 */                 if (varValue != null)
/*     */                 {
/* 653 */                   int varLen = varValue.length();
/* 654 */                   buf.replace(startPos, endPos, varValue);
/* 655 */                   altered = true;
/* 656 */                   int change = substitute(buf, startPos, varLen, priorVariables);
/*     */ 
/* 658 */                   change = change + varLen - (endPos - startPos);
/*     */ 
/* 660 */                   pos += change;
/* 661 */                   bufEnd += change;
/* 662 */                   lengthChange += change;
/* 663 */                   chars = buf.buffer;
/*     */                 }
/*     */ 
/* 668 */                 priorVariables.remove(priorVariables.size() - 1);
/*     */ 
/* 670 */                 break;
/*     */               }
/* 672 */               nestedVarCount--;
/* 673 */               pos += endMatchLen;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 680 */     if (top) {
/* 681 */       return altered ? 1 : 0;
/*     */     }
/* 683 */     return lengthChange;
/*     */   }
/*     */ 
/*     */   private void checkCyclicSubstitution(String varName, List<String> priorVariables)
/*     */   {
/* 693 */     if (!priorVariables.contains(varName)) {
/* 694 */       return;
/*     */     }
/* 696 */     StrBuilder buf = new StrBuilder(256);
/* 697 */     buf.append("Infinite loop in property interpolation of ");
/* 698 */     buf.append((String)priorVariables.remove(0));
/* 699 */     buf.append(": ");
/* 700 */     buf.appendWithSeparators(priorVariables, "->");
/* 701 */     throw new IllegalStateException(buf.toString());
/*     */   }
/*     */ 
/*     */   protected String resolveVariable(String variableName, StrBuilder buf, int startPos, int endPos)
/*     */   {
/* 722 */     StrLookup resolver = getVariableResolver();
/* 723 */     if (resolver == null) {
/* 724 */       return null;
/*     */     }
/* 726 */     return resolver.lookup(variableName);
/*     */   }
/*     */ 
/*     */   public char getEscapeChar()
/*     */   {
/* 737 */     return this.escapeChar;
/*     */   }
/*     */ 
/*     */   public void setEscapeChar(char escapeCharacter)
/*     */   {
/* 748 */     this.escapeChar = escapeCharacter;
/*     */   }
/*     */ 
/*     */   public StrMatcher getVariablePrefixMatcher()
/*     */   {
/* 763 */     return this.prefixMatcher;
/*     */   }
/*     */ 
/*     */   public StrSubstitutor setVariablePrefixMatcher(StrMatcher prefixMatcher)
/*     */   {
/* 778 */     if (prefixMatcher == null) {
/* 779 */       throw new IllegalArgumentException("Variable prefix matcher must not be null!");
/*     */     }
/* 781 */     this.prefixMatcher = prefixMatcher;
/* 782 */     return this;
/*     */   }
/*     */ 
/*     */   public StrSubstitutor setVariablePrefix(char prefix)
/*     */   {
/* 796 */     return setVariablePrefixMatcher(StrMatcher.charMatcher(prefix));
/*     */   }
/*     */ 
/*     */   public StrSubstitutor setVariablePrefix(String prefix)
/*     */   {
/* 810 */     if (prefix == null) {
/* 811 */       throw new IllegalArgumentException("Variable prefix must not be null!");
/*     */     }
/* 813 */     return setVariablePrefixMatcher(StrMatcher.stringMatcher(prefix));
/*     */   }
/*     */ 
/*     */   public StrMatcher getVariableSuffixMatcher()
/*     */   {
/* 828 */     return this.suffixMatcher;
/*     */   }
/*     */ 
/*     */   public StrSubstitutor setVariableSuffixMatcher(StrMatcher suffixMatcher)
/*     */   {
/* 843 */     if (suffixMatcher == null) {
/* 844 */       throw new IllegalArgumentException("Variable suffix matcher must not be null!");
/*     */     }
/* 846 */     this.suffixMatcher = suffixMatcher;
/* 847 */     return this;
/*     */   }
/*     */ 
/*     */   public StrSubstitutor setVariableSuffix(char suffix)
/*     */   {
/* 861 */     return setVariableSuffixMatcher(StrMatcher.charMatcher(suffix));
/*     */   }
/*     */ 
/*     */   public StrSubstitutor setVariableSuffix(String suffix)
/*     */   {
/* 875 */     if (suffix == null) {
/* 876 */       throw new IllegalArgumentException("Variable suffix must not be null!");
/*     */     }
/* 878 */     return setVariableSuffixMatcher(StrMatcher.stringMatcher(suffix));
/*     */   }
/*     */ 
/*     */   public StrLookup<?> getVariableResolver()
/*     */   {
/* 889 */     return this.variableResolver;
/*     */   }
/*     */ 
/*     */   public void setVariableResolver(StrLookup<?> variableResolver)
/*     */   {
/* 898 */     this.variableResolver = variableResolver;
/*     */   }
/*     */ 
/*     */   public boolean isEnableSubstitutionInVariables()
/*     */   {
/* 910 */     return this.enableSubstitutionInVariables;
/*     */   }
/*     */ 
/*     */   public void setEnableSubstitutionInVariables(boolean enableSubstitutionInVariables)
/*     */   {
/* 924 */     this.enableSubstitutionInVariables = enableSubstitutionInVariables;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.StrSubstitutor
 * JD-Core Version:    0.6.2
 */