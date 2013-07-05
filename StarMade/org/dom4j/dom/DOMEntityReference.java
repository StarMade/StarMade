/*     */ package org.dom4j.dom;
/*     */ 
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.tree.DefaultEntity;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.EntityReference;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class DOMEntityReference extends DefaultEntity
/*     */   implements EntityReference
/*     */ {
/*     */   public DOMEntityReference(String name)
/*     */   {
/*  30 */     super(name);
/*     */   }
/*     */ 
/*     */   public DOMEntityReference(String name, String text) {
/*  34 */     super(name, text);
/*     */   }
/*     */ 
/*     */   public DOMEntityReference(Element parent, String name, String text) {
/*  38 */     super(parent, name, text);
/*     */   }
/*     */ 
/*     */   public boolean supports(String feature, String version)
/*     */   {
/*  44 */     return DOMNodeHelper.supports(this, feature, version);
/*     */   }
/*     */ 
/*     */   public String getNamespaceURI() {
/*  48 */     return DOMNodeHelper.getNamespaceURI(this);
/*     */   }
/*     */ 
/*     */   public String getPrefix() {
/*  52 */     return DOMNodeHelper.getPrefix(this);
/*     */   }
/*     */ 
/*     */   public void setPrefix(String prefix) throws DOMException {
/*  56 */     DOMNodeHelper.setPrefix(this, prefix);
/*     */   }
/*     */ 
/*     */   public String getLocalName() {
/*  60 */     return DOMNodeHelper.getLocalName(this);
/*     */   }
/*     */ 
/*     */   public String getNodeName() {
/*  64 */     return getName();
/*     */   }
/*     */ 
/*     */   public String getNodeValue()
/*     */     throws DOMException
/*     */   {
/*  71 */     return null;
/*     */   }
/*     */ 
/*     */   public void setNodeValue(String nodeValue) throws DOMException {
/*     */   }
/*     */ 
/*     */   public Node getParentNode() {
/*  78 */     return DOMNodeHelper.getParentNode(this);
/*     */   }
/*     */ 
/*     */   public NodeList getChildNodes() {
/*  82 */     return DOMNodeHelper.getChildNodes(this);
/*     */   }
/*     */ 
/*     */   public Node getFirstChild() {
/*  86 */     return DOMNodeHelper.getFirstChild(this);
/*     */   }
/*     */ 
/*     */   public Node getLastChild() {
/*  90 */     return DOMNodeHelper.getLastChild(this);
/*     */   }
/*     */ 
/*     */   public Node getPreviousSibling() {
/*  94 */     return DOMNodeHelper.getPreviousSibling(this);
/*     */   }
/*     */ 
/*     */   public Node getNextSibling() {
/*  98 */     return DOMNodeHelper.getNextSibling(this);
/*     */   }
/*     */ 
/*     */   public NamedNodeMap getAttributes() {
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */   public Document getOwnerDocument() {
/* 106 */     return DOMNodeHelper.getOwnerDocument(this);
/*     */   }
/*     */ 
/*     */   public Node insertBefore(Node newChild, Node refChild) throws DOMException
/*     */   {
/* 111 */     checkNewChildNode(newChild);
/*     */ 
/* 113 */     return DOMNodeHelper.insertBefore(this, newChild, refChild);
/*     */   }
/*     */ 
/*     */   public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/*     */   {
/* 118 */     checkNewChildNode(newChild);
/*     */ 
/* 120 */     return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/*     */   }
/*     */ 
/*     */   public Node removeChild(Node oldChild) throws DOMException
/*     */   {
/* 125 */     return DOMNodeHelper.removeChild(this, oldChild);
/*     */   }
/*     */ 
/*     */   public Node appendChild(Node newChild) throws DOMException
/*     */   {
/* 130 */     checkNewChildNode(newChild);
/*     */ 
/* 132 */     return DOMNodeHelper.appendChild(this, newChild);
/*     */   }
/*     */ 
/*     */   private void checkNewChildNode(Node newChild) throws DOMException
/*     */   {
/* 137 */     int nodeType = newChild.getNodeType();
/*     */ 
/* 139 */     if ((nodeType != 1) && (nodeType != 3) && (nodeType != 8) && (nodeType != 7) && (nodeType != 4) && (nodeType != 5))
/*     */     {
/* 145 */       throw new DOMException((short)3, "Given node cannot be a child of an entity reference");
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean hasChildNodes()
/*     */   {
/* 151 */     return DOMNodeHelper.hasChildNodes(this);
/*     */   }
/*     */ 
/*     */   public Node cloneNode(boolean deep) {
/* 155 */     return DOMNodeHelper.cloneNode(this, deep);
/*     */   }
/*     */ 
/*     */   public void normalize() {
/* 159 */     DOMNodeHelper.normalize(this);
/*     */   }
/*     */ 
/*     */   public boolean isSupported(String feature, String version) {
/* 163 */     return DOMNodeHelper.isSupported(this, feature, version);
/*     */   }
/*     */ 
/*     */   public boolean hasAttributes() {
/* 167 */     return DOMNodeHelper.hasAttributes(this);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMEntityReference
 * JD-Core Version:    0.6.2
 */