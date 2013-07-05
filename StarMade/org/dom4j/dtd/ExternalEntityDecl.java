/*     */ package org.dom4j.dtd;
/*     */ 
/*     */ public class ExternalEntityDecl
/*     */ {
/*     */   private String name;
/*     */   private String publicID;
/*     */   private String systemID;
/*     */ 
/*     */   public ExternalEntityDecl()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ExternalEntityDecl(String name, String publicID, String systemID)
/*     */   {
/*  33 */     this.name = name;
/*  34 */     this.publicID = publicID;
/*  35 */     this.systemID = systemID;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  44 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  54 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getPublicID()
/*     */   {
/*  63 */     return this.publicID;
/*     */   }
/*     */ 
/*     */   public void setPublicID(String publicID)
/*     */   {
/*  73 */     this.publicID = publicID;
/*     */   }
/*     */ 
/*     */   public String getSystemID()
/*     */   {
/*  82 */     return this.systemID;
/*     */   }
/*     */ 
/*     */   public void setSystemID(String systemID)
/*     */   {
/*  92 */     this.systemID = systemID;
/*     */   }
/*     */ 
/*     */   public String toString() {
/*  96 */     StringBuffer buffer = new StringBuffer("<!ENTITY ");
/*     */ 
/*  98 */     if (this.name.startsWith("%")) {
/*  99 */       buffer.append("% ");
/* 100 */       buffer.append(this.name.substring(1));
/*     */     } else {
/* 102 */       buffer.append(this.name);
/*     */     }
/*     */ 
/* 105 */     if (this.publicID != null) {
/* 106 */       buffer.append(" PUBLIC \"");
/* 107 */       buffer.append(this.publicID);
/* 108 */       buffer.append("\" ");
/*     */ 
/* 110 */       if (this.systemID != null) {
/* 111 */         buffer.append("\"");
/* 112 */         buffer.append(this.systemID);
/* 113 */         buffer.append("\" ");
/*     */       }
/* 115 */     } else if (this.systemID != null) {
/* 116 */       buffer.append(" SYSTEM \"");
/* 117 */       buffer.append(this.systemID);
/* 118 */       buffer.append("\" ");
/*     */     }
/*     */ 
/* 121 */     buffer.append(">");
/*     */ 
/* 123 */     return buffer.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dtd.ExternalEntityDecl
 * JD-Core Version:    0.6.2
 */