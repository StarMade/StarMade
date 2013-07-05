/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultDocumentType extends AbstractDocumentType
/*     */ {
/*     */   protected String elementName;
/*     */   private String publicID;
/*     */   private String systemID;
/*     */   private List internalDeclarations;
/*     */   private List externalDeclarations;
/*     */ 
/*     */   public DefaultDocumentType()
/*     */   {
/*     */   }
/*     */ 
/*     */   public DefaultDocumentType(String elementName, String systemID)
/*     */   {
/*  52 */     this.elementName = elementName;
/*  53 */     this.systemID = systemID;
/*     */   }
/*     */ 
/*     */   public DefaultDocumentType(String elementName, String publicID, String systemID)
/*     */   {
/*  71 */     this.elementName = elementName;
/*  72 */     this.publicID = publicID;
/*  73 */     this.systemID = systemID;
/*     */   }
/*     */ 
/*     */   public String getElementName() {
/*  77 */     return this.elementName;
/*     */   }
/*     */ 
/*     */   public void setElementName(String elementName) {
/*  81 */     this.elementName = elementName;
/*     */   }
/*     */ 
/*     */   public String getPublicID()
/*     */   {
/*  90 */     return this.publicID;
/*     */   }
/*     */ 
/*     */   public void setPublicID(String publicID)
/*     */   {
/* 100 */     this.publicID = publicID;
/*     */   }
/*     */ 
/*     */   public String getSystemID()
/*     */   {
/* 109 */     return this.systemID;
/*     */   }
/*     */ 
/*     */   public void setSystemID(String systemID)
/*     */   {
/* 119 */     this.systemID = systemID;
/*     */   }
/*     */ 
/*     */   public List getInternalDeclarations() {
/* 123 */     return this.internalDeclarations;
/*     */   }
/*     */ 
/*     */   public void setInternalDeclarations(List internalDeclarations) {
/* 127 */     this.internalDeclarations = internalDeclarations;
/*     */   }
/*     */ 
/*     */   public List getExternalDeclarations() {
/* 131 */     return this.externalDeclarations;
/*     */   }
/*     */ 
/*     */   public void setExternalDeclarations(List externalDeclarations) {
/* 135 */     this.externalDeclarations = externalDeclarations;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.DefaultDocumentType
 * JD-Core Version:    0.6.2
 */