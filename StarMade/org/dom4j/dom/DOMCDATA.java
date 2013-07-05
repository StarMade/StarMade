/*     */ package org.dom4j.dom;
/*     */ 
/*     */ import org.dom4j.CDATA;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.tree.DefaultCDATA;
/*     */ import org.w3c.dom.CDATASection;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.Text;
/*     */ 
/*     */ public class DOMCDATA extends DefaultCDATA
/*     */   implements CDATASection
/*     */ {
/*     */   public DOMCDATA(String text)
/*     */   {
/*  30 */     super(text);
/*     */   }
/*     */ 
/*     */   public DOMCDATA(Element parent, String text) {
/*  34 */     super(parent, text);
/*     */   }
/*     */ 
/*     */   public boolean supports(String feature, String version)
/*     */   {
/*  40 */     return DOMNodeHelper.supports(this, feature, version);
/*     */   }
/*     */ 
/*     */   public String getNamespaceURI() {
/*  44 */     return DOMNodeHelper.getNamespaceURI(this);
/*     */   }
/*     */ 
/*     */   public String getPrefix() {
/*  48 */     return DOMNodeHelper.getPrefix(this);
/*     */   }
/*     */ 
/*     */   public void setPrefix(String prefix) throws DOMException {
/*  52 */     DOMNodeHelper.setPrefix(this, prefix);
/*     */   }
/*     */ 
/*     */   public String getLocalName() {
/*  56 */     return DOMNodeHelper.getLocalName(this);
/*     */   }
/*     */ 
/*     */   public String getNodeName() {
/*  60 */     return "#cdata-section";
/*     */   }
/*     */ 
/*     */   public String getNodeValue()
/*     */     throws DOMException
/*     */   {
/*  67 */     return DOMNodeHelper.getNodeValue(this);
/*     */   }
/*     */ 
/*     */   public void setNodeValue(String nodeValue) throws DOMException {
/*  71 */     DOMNodeHelper.setNodeValue(this, nodeValue);
/*     */   }
/*     */ 
/*     */   public Node getParentNode() {
/*  75 */     return DOMNodeHelper.getParentNode(this);
/*     */   }
/*     */ 
/*     */   public NodeList getChildNodes() {
/*  79 */     return DOMNodeHelper.getChildNodes(this);
/*     */   }
/*     */ 
/*     */   public Node getFirstChild() {
/*  83 */     return DOMNodeHelper.getFirstChild(this);
/*     */   }
/*     */ 
/*     */   public Node getLastChild() {
/*  87 */     return DOMNodeHelper.getLastChild(this);
/*     */   }
/*     */ 
/*     */   public Node getPreviousSibling() {
/*  91 */     return DOMNodeHelper.getPreviousSibling(this);
/*     */   }
/*     */ 
/*     */   public Node getNextSibling() {
/*  95 */     return DOMNodeHelper.getNextSibling(this);
/*     */   }
/*     */ 
/*     */   public NamedNodeMap getAttributes() {
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */   public Document getOwnerDocument() {
/* 103 */     return DOMNodeHelper.getOwnerDocument(this);
/*     */   }
/*     */ 
/*     */   public Node insertBefore(Node newChild, Node refChild) throws DOMException
/*     */   {
/* 108 */     checkNewChildNode(newChild);
/*     */ 
/* 110 */     return DOMNodeHelper.insertBefore(this, newChild, refChild);
/*     */   }
/*     */ 
/*     */   public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/*     */   {
/* 115 */     checkNewChildNode(newChild);
/*     */ 
/* 117 */     return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/*     */   }
/*     */ 
/*     */   public Node removeChild(Node oldChild) throws DOMException
/*     */   {
/* 122 */     return DOMNodeHelper.removeChild(this, oldChild);
/*     */   }
/*     */ 
/*     */   public Node appendChild(Node newChild) throws DOMException
/*     */   {
/* 127 */     checkNewChildNode(newChild);
/*     */ 
/* 129 */     return DOMNodeHelper.appendChild(this, newChild);
/*     */   }
/*     */ 
/*     */   private void checkNewChildNode(Node newChild) throws DOMException
/*     */   {
/* 134 */     throw new DOMException((short)3, "CDATASection nodes cannot have children");
/*     */   }
/*     */ 
/*     */   public boolean hasChildNodes()
/*     */   {
/* 139 */     return DOMNodeHelper.hasChildNodes(this);
/*     */   }
/*     */ 
/*     */   public Node cloneNode(boolean deep) {
/* 143 */     return DOMNodeHelper.cloneNode(this, deep);
/*     */   }
/*     */ 
/*     */   public void normalize() {
/* 147 */     DOMNodeHelper.normalize(this);
/*     */   }
/*     */ 
/*     */   public boolean isSupported(String feature, String version) {
/* 151 */     return DOMNodeHelper.isSupported(this, feature, version);
/*     */   }
/*     */ 
/*     */   public boolean hasAttributes() {
/* 155 */     return DOMNodeHelper.hasAttributes(this);
/*     */   }
/*     */ 
/*     */   public String getData()
/*     */     throws DOMException
/*     */   {
/* 161 */     return DOMNodeHelper.getData(this);
/*     */   }
/*     */ 
/*     */   public void setData(String data) throws DOMException {
/* 165 */     DOMNodeHelper.setData(this, data);
/*     */   }
/*     */ 
/*     */   public int getLength() {
/* 169 */     return DOMNodeHelper.getLength(this);
/*     */   }
/*     */ 
/*     */   public String substringData(int offset, int count) throws DOMException {
/* 173 */     return DOMNodeHelper.substringData(this, offset, count);
/*     */   }
/*     */ 
/*     */   public void appendData(String arg) throws DOMException {
/* 177 */     DOMNodeHelper.appendData(this, arg);
/*     */   }
/*     */ 
/*     */   public void insertData(int offset, String arg) throws DOMException {
/* 181 */     DOMNodeHelper.insertData(this, offset, arg);
/*     */   }
/*     */ 
/*     */   public void deleteData(int offset, int count) throws DOMException {
/* 185 */     DOMNodeHelper.deleteData(this, offset, count);
/*     */   }
/*     */ 
/*     */   public void replaceData(int offset, int count, String arg) throws DOMException
/*     */   {
/* 190 */     DOMNodeHelper.replaceData(this, offset, count, arg);
/*     */   }
/*     */ 
/*     */   public Text splitText(int offset)
/*     */     throws DOMException
/*     */   {
/* 196 */     if (isReadOnly()) {
/* 197 */       throw new DOMException((short)7, "CharacterData node is read only: " + this);
/*     */     }
/*     */ 
/* 200 */     String text = getText();
/* 201 */     int length = text != null ? text.length() : 0;
/*     */ 
/* 203 */     if ((offset < 0) || (offset >= length)) {
/* 204 */       throw new DOMException((short)1, "No text at offset: " + offset);
/*     */     }
/*     */ 
/* 207 */     String start = text.substring(0, offset);
/* 208 */     String rest = text.substring(offset);
/* 209 */     setText(start);
/*     */ 
/* 211 */     Element parent = getParent();
/* 212 */     CDATA newText = createCDATA(rest);
/*     */ 
/* 214 */     if (parent != null) {
/* 215 */       parent.add(newText);
/*     */     }
/*     */ 
/* 218 */     return DOMNodeHelper.asDOMText(newText);
/*     */   }
/*     */ 
/*     */   protected CDATA createCDATA(String text)
/*     */   {
/* 226 */     return new DOMCDATA(text);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMCDATA
 * JD-Core Version:    0.6.2
 */