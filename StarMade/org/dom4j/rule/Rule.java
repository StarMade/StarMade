/*     */ package org.dom4j.rule;
/*     */ 
/*     */ import org.dom4j.Node;
/*     */ 
/*     */ public class Rule
/*     */   implements Comparable
/*     */ {
/*     */   private String mode;
/*     */   private int importPrecedence;
/*     */   private double priority;
/*     */   private int appearenceCount;
/*     */   private Pattern pattern;
/*     */   private Action action;
/*     */ 
/*     */   public Rule()
/*     */   {
/*  41 */     this.priority = 0.5D;
/*     */   }
/*     */ 
/*     */   public Rule(Pattern pattern) {
/*  45 */     this.pattern = pattern;
/*  46 */     this.priority = pattern.getPriority();
/*     */   }
/*     */ 
/*     */   public Rule(Pattern pattern, Action action) {
/*  50 */     this(pattern);
/*  51 */     this.action = action;
/*     */   }
/*     */ 
/*     */   public Rule(Rule that, Pattern pattern)
/*     */   {
/*  64 */     this.mode = that.mode;
/*  65 */     this.importPrecedence = that.importPrecedence;
/*  66 */     this.priority = that.priority;
/*  67 */     this.appearenceCount = that.appearenceCount;
/*  68 */     this.action = that.action;
/*  69 */     this.pattern = pattern;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object that) {
/*  73 */     if ((that instanceof Rule)) {
/*  74 */       return compareTo((Rule)that) == 0;
/*     */     }
/*     */ 
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode() {
/*  81 */     return this.importPrecedence + this.appearenceCount;
/*     */   }
/*     */ 
/*     */   public int compareTo(Object that) {
/*  85 */     if ((that instanceof Rule)) {
/*  86 */       return compareTo((Rule)that);
/*     */     }
/*     */ 
/*  89 */     return getClass().getName().compareTo(that.getClass().getName());
/*     */   }
/*     */ 
/*     */   public int compareTo(Rule that)
/*     */   {
/* 102 */     int answer = this.importPrecedence - that.importPrecedence;
/*     */ 
/* 104 */     if (answer == 0) {
/* 105 */       answer = (int)Math.round(this.priority - that.priority);
/*     */ 
/* 107 */       if (answer == 0) {
/* 108 */         answer = this.appearenceCount - that.appearenceCount;
/*     */       }
/*     */     }
/*     */ 
/* 112 */     return answer;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 116 */     return super.toString() + "[ pattern: " + getPattern() + " action: " + getAction() + " ]";
/*     */   }
/*     */ 
/*     */   public final boolean matches(Node node)
/*     */   {
/* 129 */     return this.pattern.matches(node);
/*     */   }
/*     */ 
/*     */   public Rule[] getUnionRules()
/*     */   {
/* 141 */     Pattern[] patterns = this.pattern.getUnionPatterns();
/*     */ 
/* 143 */     if (patterns == null) {
/* 144 */       return null;
/*     */     }
/*     */ 
/* 147 */     int size = patterns.length;
/* 148 */     Rule[] answer = new Rule[size];
/*     */ 
/* 150 */     for (int i = 0; i < size; i++) {
/* 151 */       answer[i] = new Rule(this, patterns[i]);
/*     */     }
/*     */ 
/* 154 */     return answer;
/*     */   }
/*     */ 
/*     */   public final short getMatchType()
/*     */   {
/* 164 */     return this.pattern.getMatchType();
/*     */   }
/*     */ 
/*     */   public final String getMatchesNodeName()
/*     */   {
/* 178 */     return this.pattern.getMatchesNodeName();
/*     */   }
/*     */ 
/*     */   public String getMode()
/*     */   {
/* 187 */     return this.mode;
/*     */   }
/*     */ 
/*     */   public void setMode(String mode)
/*     */   {
/* 197 */     this.mode = mode;
/*     */   }
/*     */ 
/*     */   public int getImportPrecedence()
/*     */   {
/* 206 */     return this.importPrecedence;
/*     */   }
/*     */ 
/*     */   public void setImportPrecedence(int importPrecedence)
/*     */   {
/* 216 */     this.importPrecedence = importPrecedence;
/*     */   }
/*     */ 
/*     */   public double getPriority()
/*     */   {
/* 225 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(double priority)
/*     */   {
/* 235 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   public int getAppearenceCount()
/*     */   {
/* 244 */     return this.appearenceCount;
/*     */   }
/*     */ 
/*     */   public void setAppearenceCount(int appearenceCount)
/*     */   {
/* 254 */     this.appearenceCount = appearenceCount;
/*     */   }
/*     */ 
/*     */   public Pattern getPattern()
/*     */   {
/* 263 */     return this.pattern;
/*     */   }
/*     */ 
/*     */   public void setPattern(Pattern pattern)
/*     */   {
/* 273 */     this.pattern = pattern;
/*     */   }
/*     */ 
/*     */   public Action getAction()
/*     */   {
/* 282 */     return this.action;
/*     */   }
/*     */ 
/*     */   public void setAction(Action action)
/*     */   {
/* 292 */     this.action = action;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.rule.Rule
 * JD-Core Version:    0.6.2
 */