/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class CharSet
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5947847346149275958L;
/*  48 */   public static final CharSet EMPTY = new CharSet(new String[] { (String)null });
/*     */ 
/*  54 */   public static final CharSet ASCII_ALPHA = new CharSet(new String[] { "a-zA-Z" });
/*     */ 
/*  60 */   public static final CharSet ASCII_ALPHA_LOWER = new CharSet(new String[] { "a-z" });
/*     */ 
/*  66 */   public static final CharSet ASCII_ALPHA_UPPER = new CharSet(new String[] { "A-Z" });
/*     */ 
/*  72 */   public static final CharSet ASCII_NUMERIC = new CharSet(new String[] { "0-9" });
/*     */ 
/*  79 */   protected static final Map<String, CharSet> COMMON = Collections.synchronizedMap(new HashMap());
/*     */ 
/*  92 */   private final Set<CharRange> set = Collections.synchronizedSet(new HashSet());
/*     */ 
/*     */   public static CharSet getInstance(String[] setStrs)
/*     */   {
/* 139 */     if (setStrs == null) {
/* 140 */       return null;
/*     */     }
/* 142 */     if (setStrs.length == 1) {
/* 143 */       CharSet common = (CharSet)COMMON.get(setStrs[0]);
/* 144 */       if (common != null) {
/* 145 */         return common;
/*     */       }
/*     */     }
/* 148 */     return new CharSet(setStrs);
/*     */   }
/*     */ 
/*     */   protected CharSet(String[] set)
/*     */   {
/* 161 */     int sz = set.length;
/* 162 */     for (int i = 0; i < sz; i++)
/* 163 */       add(set[i]);
/*     */   }
/*     */ 
/*     */   protected void add(String str)
/*     */   {
/* 174 */     if (str == null) {
/* 175 */       return;
/*     */     }
/*     */ 
/* 178 */     int len = str.length();
/* 179 */     int pos = 0;
/* 180 */     while (pos < len) {
/* 181 */       int remainder = len - pos;
/* 182 */       if ((remainder >= 4) && (str.charAt(pos) == '^') && (str.charAt(pos + 2) == '-'))
/*     */       {
/* 184 */         this.set.add(CharRange.isNotIn(str.charAt(pos + 1), str.charAt(pos + 3)));
/* 185 */         pos += 4;
/* 186 */       } else if ((remainder >= 3) && (str.charAt(pos + 1) == '-'))
/*     */       {
/* 188 */         this.set.add(CharRange.isIn(str.charAt(pos), str.charAt(pos + 2)));
/* 189 */         pos += 3;
/* 190 */       } else if ((remainder >= 2) && (str.charAt(pos) == '^'))
/*     */       {
/* 192 */         this.set.add(CharRange.isNot(str.charAt(pos + 1)));
/* 193 */         pos += 2;
/*     */       }
/*     */       else {
/* 196 */         this.set.add(CharRange.is(str.charAt(pos)));
/* 197 */         pos++;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   CharRange[] getCharRanges()
/*     */   {
/* 212 */     return (CharRange[])this.set.toArray(new CharRange[this.set.size()]);
/*     */   }
/*     */ 
/*     */   public boolean contains(char ch)
/*     */   {
/* 224 */     for (CharRange range : this.set) {
/* 225 */       if (range.contains(ch)) {
/* 226 */         return true;
/*     */       }
/*     */     }
/* 229 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 247 */     if (obj == this) {
/* 248 */       return true;
/*     */     }
/* 250 */     if (!(obj instanceof CharSet)) {
/* 251 */       return false;
/*     */     }
/* 253 */     CharSet other = (CharSet)obj;
/* 254 */     return this.set.equals(other.set);
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 265 */     return 89 + this.set.hashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 275 */     return this.set.toString();
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  82 */     COMMON.put(null, EMPTY);
/*  83 */     COMMON.put("", EMPTY);
/*  84 */     COMMON.put("a-zA-Z", ASCII_ALPHA);
/*  85 */     COMMON.put("A-Za-z", ASCII_ALPHA);
/*  86 */     COMMON.put("a-z", ASCII_ALPHA_LOWER);
/*  87 */     COMMON.put("A-Z", ASCII_ALPHA_UPPER);
/*  88 */     COMMON.put("0-9", ASCII_NUMERIC);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.CharSet
 * JD-Core Version:    0.6.2
 */