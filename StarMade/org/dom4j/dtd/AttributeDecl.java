/*     */ package org.dom4j.dtd;
/*     */ 
/*     */ public class AttributeDecl
/*     */ {
/*     */   private String elementName;
/*     */   private String attributeName;
/*     */   private String type;
/*     */   private String value;
/*     */   private String valueDefault;
/*     */ 
/*     */   public AttributeDecl()
/*     */   {
/*     */   }
/*     */ 
/*     */   public AttributeDecl(String elementName, String attributeName, String type, String valueDefault, String value)
/*     */   {
/*  39 */     this.elementName = elementName;
/*  40 */     this.attributeName = attributeName;
/*  41 */     this.type = type;
/*  42 */     this.value = value;
/*  43 */     this.valueDefault = valueDefault;
/*     */   }
/*     */ 
/*     */   public String getElementName()
/*     */   {
/*  52 */     return this.elementName;
/*     */   }
/*     */ 
/*     */   public void setElementName(String elementName)
/*     */   {
/*  62 */     this.elementName = elementName;
/*     */   }
/*     */ 
/*     */   public String getAttributeName()
/*     */   {
/*  71 */     return this.attributeName;
/*     */   }
/*     */ 
/*     */   public void setAttributeName(String attributeName)
/*     */   {
/*  81 */     this.attributeName = attributeName;
/*     */   }
/*     */ 
/*     */   public String getType()
/*     */   {
/*  90 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type)
/*     */   {
/* 100 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getValue()
/*     */   {
/* 109 */     return this.value;
/*     */   }
/*     */ 
/*     */   public void setValue(String value)
/*     */   {
/* 119 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public String getValueDefault()
/*     */   {
/* 128 */     return this.valueDefault;
/*     */   }
/*     */ 
/*     */   public void setValueDefault(String valueDefault)
/*     */   {
/* 138 */     this.valueDefault = valueDefault;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 142 */     StringBuffer buffer = new StringBuffer("<!ATTLIST ");
/* 143 */     buffer.append(this.elementName);
/* 144 */     buffer.append(" ");
/* 145 */     buffer.append(this.attributeName);
/* 146 */     buffer.append(" ");
/* 147 */     buffer.append(this.type);
/* 148 */     buffer.append(" ");
/*     */ 
/* 150 */     if (this.valueDefault != null) {
/* 151 */       buffer.append(this.valueDefault);
/*     */ 
/* 153 */       if (this.valueDefault.equals("#FIXED")) {
/* 154 */         buffer.append(" \"");
/* 155 */         buffer.append(this.value);
/* 156 */         buffer.append("\"");
/*     */       }
/*     */     } else {
/* 159 */       buffer.append("\"");
/* 160 */       buffer.append(this.value);
/* 161 */       buffer.append("\"");
/*     */     }
/*     */ 
/* 164 */     buffer.append(">");
/*     */ 
/* 166 */     return buffer.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dtd.AttributeDecl
 * JD-Core Version:    0.6.2
 */