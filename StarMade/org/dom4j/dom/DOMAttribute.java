/*     */ package org.dom4j.dom;
/*     */ 
/*     */ import org.dom4j.QName;
/*     */ import org.dom4j.tree.DefaultAttribute;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class DOMAttribute extends DefaultAttribute
/*     */   implements Attr
/*     */ {
/*     */   public DOMAttribute(QName qname)
/*     */   {
/*  30 */     super(qname);
/*     */   }
/*     */ 
/*     */   public DOMAttribute(QName qname, String value) {
/*  34 */     super(qname, value);
/*     */   }
/*     */ 
/*     */   public DOMAttribute(org.dom4j.Element parent, QName qname, String value) {
/*  38 */     super(parent, qname, value);
/*     */   }
/*     */ 
/*     */   public boolean supports(String feature, String version)
/*     */   {
/*  44 */     return DOMNodeHelper.supports(this, feature, version);
/*     */   }
/*     */ 
/*     */   public String getNamespaceURI() {
/*  48 */     return getQName().getNamespaceURI();
/*     */   }
/*     */ 
/*     */   public String getPrefix() {
/*  52 */     return getQName().getNamespacePrefix();
/*     */   }
/*     */ 
/*     */   public void setPrefix(String prefix) throws DOMException {
/*  56 */     DOMNodeHelper.setPrefix(this, prefix);
/*     */   }
/*     */ 
/*     */   public String getLocalName() {
/*  60 */     return getQName().getName();
/*     */   }
/*     */ 
/*     */   public String getNodeName() {
/*  64 */     return getName();
/*     */   }
/*     */ 
/*     */   public String getNodeValue()
/*     */     throws DOMException
/*     */   {
/*  71 */     return DOMNodeHelper.getNodeValue(this);
/*     */   }
/*     */ 
/*     */   public void setNodeValue(String nodeValue) throws DOMException {
/*  75 */     DOMNodeHelper.setNodeValue(this, nodeValue);
/*     */   }
/*     */ 
/*     */   public Node getParentNode()
/*     */   {
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */   public NodeList getChildNodes() {
/*  86 */     return DOMNodeHelper.getChildNodes(this);
/*     */   }
/*     */ 
/*     */   public Node getFirstChild() {
/*  90 */     return DOMNodeHelper.getFirstChild(this);
/*     */   }
/*     */ 
/*     */   public Node getLastChild() {
/*  94 */     return DOMNodeHelper.getLastChild(this);
/*     */   }
/*     */ 
/*     */   public Node getPreviousSibling() {
/*  98 */     return DOMNodeHelper.getPreviousSibling(this);
/*     */   }
/*     */ 
/*     */   public Node getNextSibling() {
/* 102 */     return DOMNodeHelper.getNextSibling(this);
/*     */   }
/*     */ 
/*     */   public NamedNodeMap getAttributes() {
/* 106 */     return null;
/*     */   }
/*     */ 
/*     */   public Document getOwnerDocument() {
/* 110 */     return DOMNodeHelper.getOwnerDocument(this);
/*     */   }
/*     */ 
/*     */   public Node insertBefore(Node newChild, Node refChild) throws DOMException
/*     */   {
/* 115 */     checkNewChildNode(newChild);
/*     */ 
/* 117 */     return DOMNodeHelper.insertBefore(this, newChild, refChild);
/*     */   }
/*     */ 
/*     */   public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/*     */   {
/* 122 */     checkNewChildNode(newChild);
/*     */ 
/* 124 */     return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/*     */   }
/*     */ 
/*     */   public Node removeChild(Node oldChild) throws DOMException
/*     */   {
/* 129 */     return DOMNodeHelper.removeChild(this, oldChild);
/*     */   }
/*     */ 
/*     */   public Node appendChild(Node newChild) throws DOMException
/*     */   {
/* 134 */     checkNewChildNode(newChild);
/*     */ 
/* 136 */     return DOMNodeHelper.appendChild(this, newChild);
/*     */   }
/*     */ 
/*     */   private void checkNewChildNode(Node newChild) throws DOMException
/*     */   {
/* 141 */     int nodeType = newChild.getNodeType();
/*     */ 
/* 143 */     if ((nodeType != 3) && (nodeType != 5))
/*     */     {
/* 145 */       throw new DOMException((short)3, "The node cannot be a child of attribute");
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
/*     */ 
/*     */   public boolean getSpecified()
/*     */   {
/* 174 */     return true;
/*     */   }
/*     */ 
/*     */   public org.w3c.dom.Element getOwnerElement()
/*     */   {
/* 180 */     return DOMNodeHelper.asDOMElement(getParent());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMAttribute
 * JD-Core Version:    0.6.2
 */