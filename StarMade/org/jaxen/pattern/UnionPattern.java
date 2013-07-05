/*     */ package org.jaxen.pattern;
/*     */ 
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.JaxenException;
/*     */ 
/*     */ public class UnionPattern extends Pattern
/*     */ {
/*     */   private Pattern lhs;
/*     */   private Pattern rhs;
/*  62 */   private short nodeType = 0;
/*  63 */   private String matchesNodeName = null;
/*     */ 
/*     */   public UnionPattern()
/*     */   {
/*     */   }
/*     */ 
/*     */   public UnionPattern(Pattern lhs, Pattern rhs)
/*     */   {
/*  72 */     this.lhs = lhs;
/*  73 */     this.rhs = rhs;
/*  74 */     init();
/*     */   }
/*     */ 
/*     */   public Pattern getLHS()
/*     */   {
/*  80 */     return this.lhs;
/*     */   }
/*     */ 
/*     */   public void setLHS(Pattern lhs)
/*     */   {
/*  85 */     this.lhs = lhs;
/*  86 */     init();
/*     */   }
/*     */ 
/*     */   public Pattern getRHS()
/*     */   {
/*  91 */     return this.rhs;
/*     */   }
/*     */ 
/*     */   public void setRHS(Pattern rhs)
/*     */   {
/*  96 */     this.rhs = rhs;
/*  97 */     init();
/*     */   }
/*     */ 
/*     */   public boolean matches(Object node, Context context)
/*     */     throws JaxenException
/*     */   {
/* 108 */     return (this.lhs.matches(node, context)) || (this.rhs.matches(node, context));
/*     */   }
/*     */ 
/*     */   public Pattern[] getUnionPatterns()
/*     */   {
/* 113 */     return new Pattern[] { this.lhs, this.rhs };
/*     */   }
/*     */ 
/*     */   public short getMatchType()
/*     */   {
/* 119 */     return this.nodeType;
/*     */   }
/*     */ 
/*     */   public String getMatchesNodeName()
/*     */   {
/* 125 */     return this.matchesNodeName;
/*     */   }
/*     */ 
/*     */   public Pattern simplify()
/*     */   {
/* 131 */     this.lhs = this.lhs.simplify();
/* 132 */     this.rhs = this.rhs.simplify();
/* 133 */     init();
/* 134 */     return this;
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/* 139 */     return this.lhs.getText() + " | " + this.rhs.getText();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 144 */     return super.toString() + "[ lhs: " + this.lhs + " rhs: " + this.rhs + " ]";
/*     */   }
/*     */ 
/*     */   private void init()
/*     */   {
/* 151 */     short type1 = this.lhs.getMatchType();
/* 152 */     short type2 = this.rhs.getMatchType();
/* 153 */     this.nodeType = (type1 == type2 ? type1 : 0);
/*     */ 
/* 155 */     String name1 = this.lhs.getMatchesNodeName();
/* 156 */     String name2 = this.rhs.getMatchesNodeName();
/*     */ 
/* 158 */     this.matchesNodeName = null;
/* 159 */     if ((name1 != null) && (name2 != null) && (name1.equals(name2)))
/*     */     {
/* 161 */       this.matchesNodeName = name1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.UnionPattern
 * JD-Core Version:    0.6.2
 */