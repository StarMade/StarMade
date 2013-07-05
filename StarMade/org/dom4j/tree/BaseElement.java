/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.dom4j.Branch;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.QName;
/*     */ 
/*     */ public class BaseElement extends AbstractElement
/*     */ {
/*     */   private QName qname;
/*     */   private Branch parentBranch;
/*     */   protected List content;
/*     */   protected List attributes;
/*     */ 
/*     */   public BaseElement(String name)
/*     */   {
/*  46 */     this.qname = getDocumentFactory().createQName(name);
/*     */   }
/*     */ 
/*     */   public BaseElement(QName qname) {
/*  50 */     this.qname = qname;
/*     */   }
/*     */ 
/*     */   public BaseElement(String name, Namespace namespace) {
/*  54 */     this.qname = getDocumentFactory().createQName(name, namespace);
/*     */   }
/*     */ 
/*     */   public Element getParent() {
/*  58 */     Element result = null;
/*     */ 
/*  60 */     if ((this.parentBranch instanceof Element)) {
/*  61 */       result = (Element)this.parentBranch;
/*     */     }
/*     */ 
/*  64 */     return result;
/*     */   }
/*     */ 
/*     */   public void setParent(Element parent) {
/*  68 */     if (((this.parentBranch instanceof Element)) || (parent != null))
/*  69 */       this.parentBranch = parent;
/*     */   }
/*     */ 
/*     */   public Document getDocument()
/*     */   {
/*  74 */     if ((this.parentBranch instanceof Document))
/*  75 */       return (Document)this.parentBranch;
/*  76 */     if ((this.parentBranch instanceof Element)) {
/*  77 */       Element parent = (Element)this.parentBranch;
/*     */ 
/*  79 */       return parent.getDocument();
/*     */     }
/*     */ 
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */   public void setDocument(Document document) {
/*  86 */     if (((this.parentBranch instanceof Document)) || (document != null))
/*  87 */       this.parentBranch = document;
/*     */   }
/*     */ 
/*     */   public boolean supportsParent()
/*     */   {
/*  92 */     return true;
/*     */   }
/*     */ 
/*     */   public QName getQName() {
/*  96 */     return this.qname;
/*     */   }
/*     */ 
/*     */   public void setQName(QName name) {
/* 100 */     this.qname = name;
/*     */   }
/*     */ 
/*     */   public void clearContent() {
/* 104 */     contentList().clear();
/*     */   }
/*     */ 
/*     */   public void setContent(List content) {
/* 108 */     this.content = content;
/*     */ 
/* 110 */     if ((content instanceof ContentListFacade))
/* 111 */       this.content = ((ContentListFacade)content).getBackingList();
/*     */   }
/*     */ 
/*     */   public void setAttributes(List attributes)
/*     */   {
/* 116 */     this.attributes = attributes;
/*     */ 
/* 118 */     if ((attributes instanceof ContentListFacade))
/* 119 */       this.attributes = ((ContentListFacade)attributes).getBackingList();
/*     */   }
/*     */ 
/*     */   protected List contentList()
/*     */   {
/* 126 */     if (this.content == null) {
/* 127 */       this.content = createContentList();
/*     */     }
/*     */ 
/* 130 */     return this.content;
/*     */   }
/*     */ 
/*     */   protected List attributeList() {
/* 134 */     if (this.attributes == null) {
/* 135 */       this.attributes = createAttributeList();
/*     */     }
/*     */ 
/* 138 */     return this.attributes;
/*     */   }
/*     */ 
/*     */   protected List attributeList(int size) {
/* 142 */     if (this.attributes == null) {
/* 143 */       this.attributes = createAttributeList(size);
/*     */     }
/*     */ 
/* 146 */     return this.attributes;
/*     */   }
/*     */ 
/*     */   protected void setAttributeList(List attributeList) {
/* 150 */     this.attributes = attributeList;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.BaseElement
 * JD-Core Version:    0.6.2
 */