/*     */ package org.jaxen.saxpath.base;
/*     */ 
/*     */ class Token
/*     */ {
/*     */   private int tokenType;
/*     */   private String parseText;
/*     */   private int tokenBegin;
/*     */   private int tokenEnd;
/*     */ 
/*     */   Token(int tokenType, String parseText, int tokenBegin, int tokenEnd)
/*     */   {
/*  66 */     setTokenType(tokenType);
/*  67 */     setParseText(parseText);
/*  68 */     setTokenBegin(tokenBegin);
/*  69 */     setTokenEnd(tokenEnd);
/*     */   }
/*     */ 
/*     */   private void setTokenType(int tokenType)
/*     */   {
/*  74 */     this.tokenType = tokenType;
/*     */   }
/*     */ 
/*     */   int getTokenType()
/*     */   {
/*  79 */     return this.tokenType;
/*     */   }
/*     */ 
/*     */   private void setParseText(String parseText)
/*     */   {
/*  84 */     this.parseText = parseText;
/*     */   }
/*     */ 
/*     */   String getTokenText()
/*     */   {
/*  89 */     return this.parseText.substring(getTokenBegin(), getTokenEnd());
/*     */   }
/*     */ 
/*     */   private void setTokenBegin(int tokenBegin)
/*     */   {
/*  95 */     this.tokenBegin = tokenBegin;
/*     */   }
/*     */ 
/*     */   int getTokenBegin()
/*     */   {
/* 100 */     return this.tokenBegin;
/*     */   }
/*     */ 
/*     */   private void setTokenEnd(int tokenEnd)
/*     */   {
/* 105 */     this.tokenEnd = tokenEnd;
/*     */   }
/*     */ 
/*     */   int getTokenEnd()
/*     */   {
/* 110 */     return this.tokenEnd;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 115 */     return "[ (" + this.tokenType + ") (" + getTokenText() + ")";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.base.Token
 * JD-Core Version:    0.6.2
 */