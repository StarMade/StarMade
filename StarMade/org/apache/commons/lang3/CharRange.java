/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ final class CharRange
/*     */   implements Iterable<Character>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8270183163158333422L;
/*     */   private final char start;
/*     */   private final char end;
/*     */   private final boolean negated;
/*     */   private transient String iToString;
/*     */ 
/*     */   private CharRange(char start, char end, boolean negated)
/*     */   {
/*  69 */     if (start > end) {
/*  70 */       char temp = start;
/*  71 */       start = end;
/*  72 */       end = temp;
/*     */     }
/*     */ 
/*  75 */     this.start = start;
/*  76 */     this.end = end;
/*  77 */     this.negated = negated;
/*     */   }
/*     */ 
/*     */   public static CharRange is(char ch)
/*     */   {
/*  89 */     return new CharRange(ch, ch, false);
/*     */   }
/*     */ 
/*     */   public static CharRange isNot(char ch)
/*     */   {
/* 101 */     return new CharRange(ch, ch, true);
/*     */   }
/*     */ 
/*     */   public static CharRange isIn(char start, char end)
/*     */   {
/* 114 */     return new CharRange(start, end, false);
/*     */   }
/*     */ 
/*     */   public static CharRange isNotIn(char start, char end)
/*     */   {
/* 127 */     return new CharRange(start, end, true);
/*     */   }
/*     */ 
/*     */   public char getStart()
/*     */   {
/* 138 */     return this.start;
/*     */   }
/*     */ 
/*     */   public char getEnd()
/*     */   {
/* 147 */     return this.end;
/*     */   }
/*     */ 
/*     */   public boolean isNegated()
/*     */   {
/* 159 */     return this.negated;
/*     */   }
/*     */ 
/*     */   public boolean contains(char ch)
/*     */   {
/* 171 */     return ((ch >= this.start) && (ch <= this.end)) != this.negated;
/*     */   }
/*     */ 
/*     */   public boolean contains(CharRange range)
/*     */   {
/* 183 */     if (range == null) {
/* 184 */       throw new IllegalArgumentException("The Range must not be null");
/*     */     }
/* 186 */     if (this.negated) {
/* 187 */       if (range.negated) {
/* 188 */         return (this.start >= range.start) && (this.end <= range.end);
/*     */       }
/* 190 */       return (range.end < this.start) || (range.start > this.end);
/*     */     }
/* 192 */     if (range.negated) {
/* 193 */       return (this.start == 0) && (this.end == 65535);
/*     */     }
/* 195 */     return (this.start <= range.start) && (this.end >= range.end);
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 209 */     if (obj == this) {
/* 210 */       return true;
/*     */     }
/* 212 */     if (!(obj instanceof CharRange)) {
/* 213 */       return false;
/*     */     }
/* 215 */     CharRange other = (CharRange)obj;
/* 216 */     return (this.start == other.start) && (this.end == other.end) && (this.negated == other.negated);
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 226 */     return 'S' + this.start + '\007' * this.end + (this.negated ? 1 : 0);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 236 */     if (this.iToString == null) {
/* 237 */       StringBuilder buf = new StringBuilder(4);
/* 238 */       if (isNegated()) {
/* 239 */         buf.append('^');
/*     */       }
/* 241 */       buf.append(this.start);
/* 242 */       if (this.start != this.end) {
/* 243 */         buf.append('-');
/* 244 */         buf.append(this.end);
/*     */       }
/* 246 */       this.iToString = buf.toString();
/*     */     }
/* 248 */     return this.iToString;
/*     */   }
/*     */ 
/*     */   public Iterator<Character> iterator()
/*     */   {
/* 261 */     return new CharacterIterator(this, null);
/*     */   }
/*     */ 
/*     */   private static class CharacterIterator
/*     */     implements Iterator<Character>
/*     */   {
/*     */     private char current;
/*     */     private final CharRange range;
/*     */     private boolean hasNext;
/*     */ 
/*     */     private CharacterIterator(CharRange r)
/*     */     {
/* 281 */       this.range = r;
/* 282 */       this.hasNext = true;
/*     */ 
/* 284 */       if (this.range.negated) {
/* 285 */         if (this.range.start == 0) {
/* 286 */           if (this.range.end == 65535)
/*     */           {
/* 288 */             this.hasNext = false;
/*     */           }
/* 290 */           else this.current = ((char)(this.range.end + '\001'));
/*     */         }
/*     */         else
/* 293 */           this.current = '\000';
/*     */       }
/*     */       else
/* 296 */         this.current = this.range.start;
/*     */     }
/*     */ 
/*     */     private void prepareNext()
/*     */     {
/* 304 */       if (this.range.negated) {
/* 305 */         if (this.current == 65535)
/* 306 */           this.hasNext = false;
/* 307 */         else if (this.current + '\001' == this.range.start) {
/* 308 */           if (this.range.end == 65535)
/* 309 */             this.hasNext = false;
/*     */           else
/* 311 */             this.current = ((char)(this.range.end + '\001'));
/*     */         }
/*     */         else
/* 314 */           this.current = ((char)(this.current + '\001'));
/*     */       }
/* 316 */       else if (this.current < this.range.end)
/* 317 */         this.current = ((char)(this.current + '\001'));
/*     */       else
/* 319 */         this.hasNext = false;
/*     */     }
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/* 329 */       return this.hasNext;
/*     */     }
/*     */ 
/*     */     public Character next()
/*     */     {
/* 338 */       if (!this.hasNext) {
/* 339 */         throw new NoSuchElementException();
/*     */       }
/* 341 */       char cur = this.current;
/* 342 */       prepareNext();
/* 343 */       return Character.valueOf(cur);
/*     */     }
/*     */ 
/*     */     public void remove()
/*     */     {
/* 353 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.CharRange
 * JD-Core Version:    0.6.2
 */