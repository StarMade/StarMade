/*     */ package org.dom4j.dtd;
/*     */ 
/*     */ public class InternalEntityDecl
/*     */ {
/*     */   private String name;
/*     */   private String value;
/*     */ 
/*     */   public InternalEntityDecl()
/*     */   {
/*     */   }
/*     */ 
/*     */   public InternalEntityDecl(String name, String value)
/*     */   {
/*  30 */     this.name = name;
/*  31 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  40 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  50 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getValue()
/*     */   {
/*  59 */     return this.value;
/*     */   }
/*     */ 
/*     */   public void setValue(String value)
/*     */   {
/*  69 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public String toString() {
/*  73 */     StringBuffer buffer = new StringBuffer("<!ENTITY ");
/*     */ 
/*  75 */     if (this.name.startsWith("%")) {
/*  76 */       buffer.append("% ");
/*  77 */       buffer.append(this.name.substring(1));
/*     */     } else {
/*  79 */       buffer.append(this.name);
/*     */     }
/*     */ 
/*  82 */     buffer.append(" \"");
/*  83 */     buffer.append(escapeEntityValue(this.value));
/*  84 */     buffer.append("\">");
/*     */ 
/*  86 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   private String escapeEntityValue(String text) {
/*  90 */     StringBuffer result = new StringBuffer();
/*     */ 
/*  92 */     for (int i = 0; i < text.length(); i++) {
/*  93 */       char c = text.charAt(i);
/*     */ 
/*  95 */       switch (c) {
/*     */       case '<':
/*  97 */         result.append("&#38;#60;");
/*     */ 
/*  99 */         break;
/*     */       case '>':
/* 102 */         result.append("&#62;");
/*     */ 
/* 104 */         break;
/*     */       case '&':
/* 107 */         result.append("&#38;#38;");
/*     */ 
/* 109 */         break;
/*     */       case '\'':
/* 112 */         result.append("&#39;");
/*     */ 
/* 114 */         break;
/*     */       case '"':
/* 117 */         result.append("&#34;");
/*     */ 
/* 119 */         break;
/*     */       default:
/* 123 */         if (c < ' ')
/* 124 */           result.append("&#" + c + ";");
/*     */         else {
/* 126 */           result.append(c);
/*     */         }
/*     */         break;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 133 */     return result.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dtd.InternalEntityDecl
 * JD-Core Version:    0.6.2
 */