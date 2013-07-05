/*     */ package org.dom4j.dom;
/*     */ 
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.tree.DefaultComment;
/*     */ import org.w3c.dom.Comment;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class DOMComment extends DefaultComment
/*     */   implements Comment
/*     */ {
/*     */   public DOMComment(String text)
/*     */   {
/*  28 */     super(text);
/*     */   }
/*     */ 
/*     */   public DOMComment(Element parent, String text) {
/*  32 */     super(parent, text);
/*     */   }
/*     */ 
/*     */   public boolean supports(String feature, String version)
/*     */   {
/*  38 */     return DOMNodeHelper.supports(this, feature, version);
/*     */   }
/*     */ 
/*     */   public String getNamespaceURI() {
/*  42 */     return DOMNodeHelper.getNamespaceURI(this);
/*     */   }
/*     */ 
/*     */   public String getPrefix() {
/*  46 */     return DOMNodeHelper.getPrefix(this);
/*     */   }
/*     */ 
/*     */   public void setPrefix(String prefix) throws DOMException {
/*  50 */     DOMNodeHelper.setPrefix(this, prefix);
/*     */   }
/*     */ 
/*     */   public String getLocalName() {
/*  54 */     return DOMNodeHelper.getLocalName(this);
/*     */   }
/*     */ 
/*     */   public String getNodeName() {
/*  58 */     return "#comment";
/*     */   }
/*     */ 
/*     */   public String getNodeValue()
/*     */     throws DOMException
/*     */   {
/*  65 */     return DOMNodeHelper.getNodeValue(this);
/*     */   }
/*     */ 
/*     */   public void setNodeValue(String nodeValue) throws DOMException {
/*  69 */     DOMNodeHelper.setNodeValue(this, nodeValue);
/*     */   }
/*     */ 
/*     */   public Node getParentNode() {
/*  73 */     return DOMNodeHelper.getParentNode(this);
/*     */   }
/*     */ 
/*     */   public NodeList getChildNodes() {
/*  77 */     return DOMNodeHelper.getChildNodes(this);
/*     */   }
/*     */ 
/*     */   public Node getFirstChild() {
/*  81 */     return DOMNodeHelper.getFirstChild(this);
/*     */   }
/*     */ 
/*     */   public Node getLastChild() {
/*  85 */     return DOMNodeHelper.getLastChild(this);
/*     */   }
/*     */ 
/*     */   public Node getPreviousSibling() {
/*  89 */     return DOMNodeHelper.getPreviousSibling(this);
/*     */   }
/*     */ 
/*     */   public Node getNextSibling() {
/*  93 */     return DOMNodeHelper.getNextSibling(this);
/*     */   }
/*     */ 
/*     */   public NamedNodeMap getAttributes() {
/*  97 */     return null;
/*     */   }
/*     */ 
/*     */   public Document getOwnerDocument() {
/* 101 */     return DOMNodeHelper.getOwnerDocument(this);
/*     */   }
/*     */ 
/*     */   public Node insertBefore(Node newChild, Node refChild) throws DOMException
/*     */   {
/* 106 */     checkNewChildNode(newChild);
/*     */ 
/* 108 */     return DOMNodeHelper.insertBefore(this, newChild, refChild);
/*     */   }
/*     */ 
/*     */   public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/*     */   {
/* 113 */     checkNewChildNode(newChild);
/*     */ 
/* 115 */     return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/*     */   }
/*     */ 
/*     */   public Node removeChild(Node oldChild) throws DOMException
/*     */   {
/* 120 */     return DOMNodeHelper.removeChild(this, oldChild);
/*     */   }
/*     */ 
/*     */   public Node appendChild(Node newChild) throws DOMException
/*     */   {
/* 125 */     checkNewChildNode(newChild);
/*     */ 
/* 127 */     return DOMNodeHelper.appendChild(this, newChild);
/*     */   }
/*     */ 
/*     */   private void checkNewChildNode(Node newChild) throws DOMException
/*     */   {
/* 132 */     throw new DOMException((short)3, "Comment nodes cannot have children");
/*     */   }
/*     */ 
/*     */   public boolean hasChildNodes()
/*     */   {
/* 137 */     return DOMNodeHelper.hasChildNodes(this);
/*     */   }
/*     */ 
/*     */   public Node cloneNode(boolean deep) {
/* 141 */     return DOMNodeHelper.cloneNode(this, deep);
/*     */   }
/*     */ 
/*     */   public void normalize() {
/* 145 */     DOMNodeHelper.normalize(this);
/*     */   }
/*     */ 
/*     */   public boolean isSupported(String feature, String version) {
/* 149 */     return DOMNodeHelper.isSupported(this, feature, version);
/*     */   }
/*     */ 
/*     */   public boolean hasAttributes() {
/* 153 */     return DOMNodeHelper.hasAttributes(this);
/*     */   }
/*     */ 
/*     */   public String getData()
/*     */     throws DOMException
/*     */   {
/* 159 */     return DOMNodeHelper.getData(this);
/*     */   }
/*     */ 
/*     */   public void setData(String data) throws DOMException {
/* 163 */     DOMNodeHelper.setData(this, data);
/*     */   }
/*     */ 
/*     */   public int getLength() {
/* 167 */     return DOMNodeHelper.getLength(this);
/*     */   }
/*     */ 
/*     */   public String substringData(int offset, int count) throws DOMException {
/* 171 */     return DOMNodeHelper.substringData(this, offset, count);
/*     */   }
/*     */ 
/*     */   public void appendData(String arg) throws DOMException {
/* 175 */     DOMNodeHelper.appendData(this, arg);
/*     */   }
/*     */ 
/*     */   public void insertData(int offset, String arg) throws DOMException {
/* 179 */     DOMNodeHelper.insertData(this, offset, arg);
/*     */   }
/*     */ 
/*     */   public void deleteData(int offset, int count) throws DOMException {
/* 183 */     DOMNodeHelper.deleteData(this, offset, count);
/*     */   }
/*     */ 
/*     */   public void replaceData(int offset, int count, String arg) throws DOMException
/*     */   {
/* 188 */     DOMNodeHelper.replaceData(this, offset, count, arg);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMComment
 * JD-Core Version:    0.6.2
 */