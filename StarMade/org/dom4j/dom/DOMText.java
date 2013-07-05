/*     */ package org.dom4j.dom;
/*     */ 
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.tree.DefaultText;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class DOMText extends DefaultText
/*     */   implements org.w3c.dom.Text
/*     */ {
/*     */   public DOMText(String text)
/*     */   {
/*  29 */     super(text);
/*     */   }
/*     */ 
/*     */   public DOMText(Element parent, String text) {
/*  33 */     super(parent, text);
/*     */   }
/*     */ 
/*     */   public boolean supports(String feature, String version)
/*     */   {
/*  39 */     return DOMNodeHelper.supports(this, feature, version);
/*     */   }
/*     */ 
/*     */   public String getNamespaceURI() {
/*  43 */     return DOMNodeHelper.getNamespaceURI(this);
/*     */   }
/*     */ 
/*     */   public String getPrefix() {
/*  47 */     return DOMNodeHelper.getPrefix(this);
/*     */   }
/*     */ 
/*     */   public void setPrefix(String prefix) throws DOMException {
/*  51 */     DOMNodeHelper.setPrefix(this, prefix);
/*     */   }
/*     */ 
/*     */   public String getLocalName() {
/*  55 */     return DOMNodeHelper.getLocalName(this);
/*     */   }
/*     */ 
/*     */   public String getNodeName() {
/*  59 */     return "#text";
/*     */   }
/*     */ 
/*     */   public String getNodeValue()
/*     */     throws DOMException
/*     */   {
/*  66 */     return DOMNodeHelper.getNodeValue(this);
/*     */   }
/*     */ 
/*     */   public void setNodeValue(String nodeValue) throws DOMException {
/*  70 */     DOMNodeHelper.setNodeValue(this, nodeValue);
/*     */   }
/*     */ 
/*     */   public Node getParentNode() {
/*  74 */     return DOMNodeHelper.getParentNode(this);
/*     */   }
/*     */ 
/*     */   public NodeList getChildNodes() {
/*  78 */     return DOMNodeHelper.getChildNodes(this);
/*     */   }
/*     */ 
/*     */   public Node getFirstChild() {
/*  82 */     return DOMNodeHelper.getFirstChild(this);
/*     */   }
/*     */ 
/*     */   public Node getLastChild() {
/*  86 */     return DOMNodeHelper.getLastChild(this);
/*     */   }
/*     */ 
/*     */   public Node getPreviousSibling() {
/*  90 */     return DOMNodeHelper.getPreviousSibling(this);
/*     */   }
/*     */ 
/*     */   public Node getNextSibling() {
/*  94 */     return DOMNodeHelper.getNextSibling(this);
/*     */   }
/*     */ 
/*     */   public NamedNodeMap getAttributes() {
/*  98 */     return null;
/*     */   }
/*     */ 
/*     */   public Document getOwnerDocument() {
/* 102 */     return DOMNodeHelper.getOwnerDocument(this);
/*     */   }
/*     */ 
/*     */   public Node insertBefore(Node newChild, Node refChild) throws DOMException
/*     */   {
/* 107 */     checkNewChildNode(newChild);
/*     */ 
/* 109 */     return DOMNodeHelper.insertBefore(this, newChild, refChild);
/*     */   }
/*     */ 
/*     */   public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/*     */   {
/* 114 */     checkNewChildNode(newChild);
/*     */ 
/* 116 */     return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/*     */   }
/*     */ 
/*     */   public Node removeChild(Node oldChild) throws DOMException
/*     */   {
/* 121 */     return DOMNodeHelper.removeChild(this, oldChild);
/*     */   }
/*     */ 
/*     */   public Node appendChild(Node newChild) throws DOMException
/*     */   {
/* 126 */     checkNewChildNode(newChild);
/*     */ 
/* 128 */     return DOMNodeHelper.appendChild(this, newChild);
/*     */   }
/*     */ 
/*     */   private void checkNewChildNode(Node newChild) throws DOMException
/*     */   {
/* 133 */     throw new DOMException((short)3, "Text nodes cannot have children");
/*     */   }
/*     */ 
/*     */   public boolean hasChildNodes()
/*     */   {
/* 138 */     return DOMNodeHelper.hasChildNodes(this);
/*     */   }
/*     */ 
/*     */   public Node cloneNode(boolean deep) {
/* 142 */     return DOMNodeHelper.cloneNode(this, deep);
/*     */   }
/*     */ 
/*     */   public void normalize() {
/* 146 */     DOMNodeHelper.normalize(this);
/*     */   }
/*     */ 
/*     */   public boolean isSupported(String feature, String version) {
/* 150 */     return DOMNodeHelper.isSupported(this, feature, version);
/*     */   }
/*     */ 
/*     */   public boolean hasAttributes() {
/* 154 */     return DOMNodeHelper.hasAttributes(this);
/*     */   }
/*     */ 
/*     */   public String getData()
/*     */     throws DOMException
/*     */   {
/* 160 */     return DOMNodeHelper.getData(this);
/*     */   }
/*     */ 
/*     */   public void setData(String data) throws DOMException {
/* 164 */     DOMNodeHelper.setData(this, data);
/*     */   }
/*     */ 
/*     */   public int getLength() {
/* 168 */     return DOMNodeHelper.getLength(this);
/*     */   }
/*     */ 
/*     */   public String substringData(int offset, int count) throws DOMException {
/* 172 */     return DOMNodeHelper.substringData(this, offset, count);
/*     */   }
/*     */ 
/*     */   public void appendData(String arg) throws DOMException {
/* 176 */     DOMNodeHelper.appendData(this, arg);
/*     */   }
/*     */ 
/*     */   public void insertData(int offset, String arg) throws DOMException {
/* 180 */     DOMNodeHelper.insertData(this, offset, arg);
/*     */   }
/*     */ 
/*     */   public void deleteData(int offset, int count) throws DOMException {
/* 184 */     DOMNodeHelper.deleteData(this, offset, count);
/*     */   }
/*     */ 
/*     */   public void replaceData(int offset, int count, String arg) throws DOMException
/*     */   {
/* 189 */     DOMNodeHelper.replaceData(this, offset, count, arg);
/*     */   }
/*     */ 
/*     */   public org.w3c.dom.Text splitText(int offset)
/*     */     throws DOMException
/*     */   {
/* 195 */     if (isReadOnly()) {
/* 196 */       throw new DOMException((short)7, "CharacterData node is read only: " + this);
/*     */     }
/*     */ 
/* 199 */     String text = getText();
/* 200 */     int length = text != null ? text.length() : 0;
/*     */ 
/* 202 */     if ((offset < 0) || (offset >= length)) {
/* 203 */       throw new DOMException((short)1, "No text at offset: " + offset);
/*     */     }
/*     */ 
/* 206 */     String start = text.substring(0, offset);
/* 207 */     String rest = text.substring(offset);
/* 208 */     setText(start);
/*     */ 
/* 210 */     Element parent = getParent();
/* 211 */     org.dom4j.Text newText = createText(rest);
/*     */ 
/* 213 */     if (parent != null) {
/* 214 */       parent.add(newText);
/*     */     }
/*     */ 
/* 217 */     return DOMNodeHelper.asDOMText(newText);
/*     */   }
/*     */ 
/*     */   protected org.dom4j.Text createText(String text)
/*     */   {
/* 225 */     return new DOMText(text);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMText
 * JD-Core Version:    0.6.2
 */