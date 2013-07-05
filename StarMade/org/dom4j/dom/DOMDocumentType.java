/*     */ package org.dom4j.dom;
/*     */ 
/*     */ import org.dom4j.tree.DefaultDocumentType;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.DocumentType;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class DOMDocumentType extends DefaultDocumentType
/*     */   implements DocumentType
/*     */ {
/*     */   public DOMDocumentType()
/*     */   {
/*     */   }
/*     */ 
/*     */   public DOMDocumentType(String elementName, String systemID)
/*     */   {
/*  32 */     super(elementName, systemID);
/*     */   }
/*     */ 
/*     */   public DOMDocumentType(String name, String publicID, String systemID) {
/*  36 */     super(name, publicID, systemID);
/*     */   }
/*     */ 
/*     */   public boolean supports(String feature, String version)
/*     */   {
/*  42 */     return DOMNodeHelper.supports(this, feature, version);
/*     */   }
/*     */ 
/*     */   public String getNamespaceURI() {
/*  46 */     return DOMNodeHelper.getNamespaceURI(this);
/*     */   }
/*     */ 
/*     */   public String getPrefix() {
/*  50 */     return DOMNodeHelper.getPrefix(this);
/*     */   }
/*     */ 
/*     */   public void setPrefix(String prefix) throws DOMException {
/*  54 */     DOMNodeHelper.setPrefix(this, prefix);
/*     */   }
/*     */ 
/*     */   public String getLocalName() {
/*  58 */     return DOMNodeHelper.getLocalName(this);
/*     */   }
/*     */ 
/*     */   public String getNodeName() {
/*  62 */     return getName();
/*     */   }
/*     */ 
/*     */   public String getNodeValue()
/*     */     throws DOMException
/*     */   {
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */   public void setNodeValue(String nodeValue) throws DOMException {
/*     */   }
/*     */ 
/*     */   public Node getParentNode() {
/*  76 */     return DOMNodeHelper.getParentNode(this);
/*     */   }
/*     */ 
/*     */   public NodeList getChildNodes() {
/*  80 */     return DOMNodeHelper.getChildNodes(this);
/*     */   }
/*     */ 
/*     */   public Node getFirstChild() {
/*  84 */     return DOMNodeHelper.getFirstChild(this);
/*     */   }
/*     */ 
/*     */   public Node getLastChild() {
/*  88 */     return DOMNodeHelper.getLastChild(this);
/*     */   }
/*     */ 
/*     */   public Node getPreviousSibling() {
/*  92 */     return DOMNodeHelper.getPreviousSibling(this);
/*     */   }
/*     */ 
/*     */   public Node getNextSibling() {
/*  96 */     return DOMNodeHelper.getNextSibling(this);
/*     */   }
/*     */ 
/*     */   public NamedNodeMap getAttributes() {
/* 100 */     return null;
/*     */   }
/*     */ 
/*     */   public Document getOwnerDocument() {
/* 104 */     return DOMNodeHelper.getOwnerDocument(this);
/*     */   }
/*     */ 
/*     */   public Node insertBefore(Node newChild, Node refChild) throws DOMException
/*     */   {
/* 109 */     checkNewChildNode(newChild);
/*     */ 
/* 111 */     return DOMNodeHelper.insertBefore(this, newChild, refChild);
/*     */   }
/*     */ 
/*     */   public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/*     */   {
/* 116 */     checkNewChildNode(newChild);
/*     */ 
/* 118 */     return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/*     */   }
/*     */ 
/*     */   public Node removeChild(Node oldChild) throws DOMException
/*     */   {
/* 123 */     return DOMNodeHelper.removeChild(this, oldChild);
/*     */   }
/*     */ 
/*     */   public Node appendChild(Node newChild) throws DOMException
/*     */   {
/* 128 */     checkNewChildNode(newChild);
/*     */ 
/* 130 */     return DOMNodeHelper.appendChild(this, newChild);
/*     */   }
/*     */ 
/*     */   private void checkNewChildNode(Node newChild) throws DOMException
/*     */   {
/* 135 */     throw new DOMException((short)3, "DocumentType nodes cannot have children");
/*     */   }
/*     */ 
/*     */   public boolean hasChildNodes()
/*     */   {
/* 140 */     return DOMNodeHelper.hasChildNodes(this);
/*     */   }
/*     */ 
/*     */   public Node cloneNode(boolean deep) {
/* 144 */     return DOMNodeHelper.cloneNode(this, deep);
/*     */   }
/*     */ 
/*     */   public void normalize() {
/* 148 */     DOMNodeHelper.normalize(this);
/*     */   }
/*     */ 
/*     */   public boolean isSupported(String feature, String version) {
/* 152 */     return DOMNodeHelper.isSupported(this, feature, version);
/*     */   }
/*     */ 
/*     */   public boolean hasAttributes() {
/* 156 */     return DOMNodeHelper.hasAttributes(this);
/*     */   }
/*     */ 
/*     */   public NamedNodeMap getEntities()
/*     */   {
/* 162 */     return null;
/*     */   }
/*     */ 
/*     */   public NamedNodeMap getNotations() {
/* 166 */     return null;
/*     */   }
/*     */ 
/*     */   public String getPublicId() {
/* 170 */     return getPublicID();
/*     */   }
/*     */ 
/*     */   public String getSystemId() {
/* 174 */     return getSystemID();
/*     */   }
/*     */ 
/*     */   public String getInternalSubset() {
/* 178 */     return getElementName();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMDocumentType
 * JD-Core Version:    0.6.2
 */