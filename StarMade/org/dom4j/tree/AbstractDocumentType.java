/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.dom4j.DocumentType;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Visitor;
/*     */ 
/*     */ public abstract class AbstractDocumentType extends AbstractNode
/*     */   implements DocumentType
/*     */ {
/*     */   public short getNodeType()
/*     */   {
/*  34 */     return 10;
/*     */   }
/*     */ 
/*     */   public String getName() {
/*  38 */     return getElementName();
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  42 */     setElementName(name);
/*     */   }
/*     */ 
/*     */   public String getPath(Element context)
/*     */   {
/*  47 */     return "";
/*     */   }
/*     */ 
/*     */   public String getUniquePath(Element context)
/*     */   {
/*  52 */     return "";
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/*  62 */     List list = getInternalDeclarations();
/*     */ 
/*  64 */     if ((list != null) && (list.size() > 0)) {
/*  65 */       StringBuffer buffer = new StringBuffer();
/*  66 */       Iterator iter = list.iterator();
/*     */ 
/*  68 */       if (iter.hasNext()) {
/*  69 */         Object decl = iter.next();
/*  70 */         buffer.append(decl.toString());
/*     */ 
/*  72 */         while (iter.hasNext()) {
/*  73 */           decl = iter.next();
/*  74 */           buffer.append("\n");
/*  75 */           buffer.append(decl.toString());
/*     */         }
/*     */       }
/*     */ 
/*  79 */       return buffer.toString();
/*     */     }
/*     */ 
/*  82 */     return "";
/*     */   }
/*     */ 
/*     */   public String toString() {
/*  86 */     return super.toString() + " [DocumentType: " + asXML() + "]";
/*     */   }
/*     */ 
/*     */   public String asXML() {
/*  90 */     StringBuffer buffer = new StringBuffer("<!DOCTYPE ");
/*  91 */     buffer.append(getElementName());
/*     */ 
/*  93 */     boolean hasPublicID = false;
/*  94 */     String publicID = getPublicID();
/*     */ 
/*  96 */     if ((publicID != null) && (publicID.length() > 0)) {
/*  97 */       buffer.append(" PUBLIC \"");
/*  98 */       buffer.append(publicID);
/*  99 */       buffer.append("\"");
/* 100 */       hasPublicID = true;
/*     */     }
/*     */ 
/* 103 */     String systemID = getSystemID();
/*     */ 
/* 105 */     if ((systemID != null) && (systemID.length() > 0)) {
/* 106 */       if (!hasPublicID) {
/* 107 */         buffer.append(" SYSTEM");
/*     */       }
/*     */ 
/* 110 */       buffer.append(" \"");
/* 111 */       buffer.append(systemID);
/* 112 */       buffer.append("\"");
/*     */     }
/*     */ 
/* 115 */     buffer.append(">");
/*     */ 
/* 117 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   public void write(Writer writer) throws IOException {
/* 121 */     writer.write("<!DOCTYPE ");
/* 122 */     writer.write(getElementName());
/*     */ 
/* 124 */     boolean hasPublicID = false;
/* 125 */     String publicID = getPublicID();
/*     */ 
/* 127 */     if ((publicID != null) && (publicID.length() > 0)) {
/* 128 */       writer.write(" PUBLIC \"");
/* 129 */       writer.write(publicID);
/* 130 */       writer.write("\"");
/* 131 */       hasPublicID = true;
/*     */     }
/*     */ 
/* 134 */     String systemID = getSystemID();
/*     */ 
/* 136 */     if ((systemID != null) && (systemID.length() > 0)) {
/* 137 */       if (!hasPublicID) {
/* 138 */         writer.write(" SYSTEM");
/*     */       }
/*     */ 
/* 141 */       writer.write(" \"");
/* 142 */       writer.write(systemID);
/* 143 */       writer.write("\"");
/*     */     }
/*     */ 
/* 146 */     List list = getInternalDeclarations();
/*     */ 
/* 148 */     if ((list != null) && (list.size() > 0)) {
/* 149 */       writer.write(" [");
/*     */ 
/* 151 */       for (Iterator iter = list.iterator(); iter.hasNext(); ) {
/* 152 */         Object decl = iter.next();
/* 153 */         writer.write("\n  ");
/* 154 */         writer.write(decl.toString());
/*     */       }
/*     */ 
/* 157 */       writer.write("\n]");
/*     */     }
/*     */ 
/* 160 */     writer.write(">");
/*     */   }
/*     */ 
/*     */   public void accept(Visitor visitor) {
/* 164 */     visitor.visit(this);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractDocumentType
 * JD-Core Version:    0.6.2
 */