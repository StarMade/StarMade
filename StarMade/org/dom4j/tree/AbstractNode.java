/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.io.Writer;
/*     */ import java.util.List;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Node;
/*     */ import org.dom4j.NodeFilter;
/*     */ import org.dom4j.XPath;
/*     */ import org.dom4j.rule.Pattern;
/*     */ 
/*     */ public abstract class AbstractNode
/*     */   implements Node, Cloneable, Serializable
/*     */ {
/*  33 */   protected static final String[] NODE_TYPE_NAMES = { "Node", "Element", "Attribute", "Text", "CDATA", "Entity", "Entity", "ProcessingInstruction", "Comment", "Document", "DocumentType", "DocumentFragment", "Notation", "Namespace", "Unknown" };
/*     */ 
/*  39 */   private static final DocumentFactory DOCUMENT_FACTORY = DocumentFactory.getInstance();
/*     */ 
/*     */   public short getNodeType()
/*     */   {
/*  46 */     return 14;
/*     */   }
/*     */ 
/*     */   public String getNodeTypeName() {
/*  50 */     int type = getNodeType();
/*     */ 
/*  52 */     if ((type < 0) || (type >= NODE_TYPE_NAMES.length)) {
/*  53 */       return "Unknown";
/*     */     }
/*     */ 
/*  56 */     return NODE_TYPE_NAMES[type];
/*     */   }
/*     */ 
/*     */   public Document getDocument() {
/*  60 */     Element element = getParent();
/*     */ 
/*  62 */     return element != null ? element.getDocument() : null;
/*     */   }
/*     */ 
/*     */   public void setDocument(Document document) {
/*     */   }
/*     */ 
/*     */   public Element getParent() {
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */   public void setParent(Element parent) {
/*     */   }
/*     */ 
/*     */   public boolean supportsParent() {
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isReadOnly() {
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean hasContent() {
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   public String getPath() {
/*  88 */     return getPath(null);
/*     */   }
/*     */ 
/*     */   public String getUniquePath() {
/*  92 */     return getUniquePath(null);
/*     */   }
/*     */ 
/*     */   public Object clone() {
/*  96 */     if (isReadOnly())
/*  97 */       return this;
/*     */     try
/*     */     {
/* 100 */       Node answer = (Serializable)super.clone();
/* 101 */       answer.setParent(null);
/* 102 */       answer.setDocument(null);
/*     */ 
/* 104 */       return answer;
/*     */     }
/*     */     catch (CloneNotSupportedException e) {
/* 107 */       throw new RuntimeException("This should never happen. Caught: " + e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Node detach()
/*     */   {
/* 114 */     Element parent = getParent();
/*     */ 
/* 116 */     if (parent != null) {
/* 117 */       parent.remove(this);
/*     */     } else {
/* 119 */       Document document = getDocument();
/*     */ 
/* 121 */       if (document != null) {
/* 122 */         document.remove(this);
/*     */       }
/*     */     }
/*     */ 
/* 126 */     setParent(null);
/* 127 */     setDocument(null);
/*     */ 
/* 129 */     return this;
/*     */   }
/*     */ 
/*     */   public String getName() {
/* 133 */     return null;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 137 */     throw new UnsupportedOperationException("This node cannot be modified");
/*     */   }
/*     */ 
/*     */   public String getText() {
/* 141 */     return null;
/*     */   }
/*     */ 
/*     */   public String getStringValue() {
/* 145 */     return getText();
/*     */   }
/*     */ 
/*     */   public void setText(String text) {
/* 149 */     throw new UnsupportedOperationException("This node cannot be modified");
/*     */   }
/*     */ 
/*     */   public void write(Writer writer) throws IOException {
/* 153 */     writer.write(asXML());
/*     */   }
/*     */ 
/*     */   public Object selectObject(String xpathExpression)
/*     */   {
/* 158 */     XPath xpath = createXPath(xpathExpression);
/*     */ 
/* 160 */     return xpath.evaluate(this);
/*     */   }
/*     */ 
/*     */   public List selectNodes(String xpathExpression) {
/* 164 */     XPath xpath = createXPath(xpathExpression);
/*     */ 
/* 166 */     return xpath.selectNodes(this);
/*     */   }
/*     */ 
/*     */   public List selectNodes(String xpathExpression, String comparisonXPathExpression)
/*     */   {
/* 171 */     return selectNodes(xpathExpression, comparisonXPathExpression, false);
/*     */   }
/*     */ 
/*     */   public List selectNodes(String xpathExpression, String comparisonXPathExpression, boolean removeDuplicates)
/*     */   {
/* 176 */     XPath xpath = createXPath(xpathExpression);
/* 177 */     XPath sortBy = createXPath(comparisonXPathExpression);
/*     */ 
/* 179 */     return xpath.selectNodes(this, sortBy, removeDuplicates);
/*     */   }
/*     */ 
/*     */   public Node selectSingleNode(String xpathExpression) {
/* 183 */     XPath xpath = createXPath(xpathExpression);
/*     */ 
/* 185 */     return xpath.selectSingleNode(this);
/*     */   }
/*     */ 
/*     */   public String valueOf(String xpathExpression) {
/* 189 */     XPath xpath = createXPath(xpathExpression);
/*     */ 
/* 191 */     return xpath.valueOf(this);
/*     */   }
/*     */ 
/*     */   public Number numberValueOf(String xpathExpression) {
/* 195 */     XPath xpath = createXPath(xpathExpression);
/*     */ 
/* 197 */     return xpath.numberValueOf(this);
/*     */   }
/*     */ 
/*     */   public boolean matches(String patternText) {
/* 201 */     NodeFilter filter = createXPathFilter(patternText);
/*     */ 
/* 203 */     return filter.matches(this);
/*     */   }
/*     */ 
/*     */   public XPath createXPath(String xpathExpression) {
/* 207 */     return getDocumentFactory().createXPath(xpathExpression);
/*     */   }
/*     */ 
/*     */   public NodeFilter createXPathFilter(String patternText) {
/* 211 */     return getDocumentFactory().createXPathFilter(patternText);
/*     */   }
/*     */ 
/*     */   public Pattern createPattern(String patternText) {
/* 215 */     return getDocumentFactory().createPattern(patternText);
/*     */   }
/*     */ 
/*     */   public Node asXPathResult(Element parent) {
/* 219 */     if (supportsParent()) {
/* 220 */       return this;
/*     */     }
/*     */ 
/* 223 */     return createXPathResult(parent);
/*     */   }
/*     */ 
/*     */   protected DocumentFactory getDocumentFactory() {
/* 227 */     return DOCUMENT_FACTORY;
/*     */   }
/*     */ 
/*     */   protected Node createXPathResult(Element parent) {
/* 231 */     throw new RuntimeException("asXPathResult() not yet implemented fully for: " + this);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractNode
 * JD-Core Version:    0.6.2
 */