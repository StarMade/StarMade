/*     */ package org.dom4j.dom;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.QName;
/*     */ import org.dom4j.tree.DefaultElement;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class DOMElement extends DefaultElement
/*     */   implements Element
/*     */ {
/*  36 */   private static final DocumentFactory DOCUMENT_FACTORY = DOMDocumentFactory.getInstance();
/*     */ 
/*     */   public DOMElement(String name)
/*     */   {
/*  40 */     super(name);
/*     */   }
/*     */ 
/*     */   public DOMElement(QName qname) {
/*  44 */     super(qname);
/*     */   }
/*     */ 
/*     */   public DOMElement(QName qname, int attributeCount) {
/*  48 */     super(qname, attributeCount);
/*     */   }
/*     */ 
/*     */   public DOMElement(String name, Namespace namespace) {
/*  52 */     super(name, namespace);
/*     */   }
/*     */ 
/*     */   public boolean supports(String feature, String version)
/*     */   {
/*  58 */     return DOMNodeHelper.supports(this, feature, version);
/*     */   }
/*     */ 
/*     */   public String getNamespaceURI() {
/*  62 */     return getQName().getNamespaceURI();
/*     */   }
/*     */ 
/*     */   public String getPrefix() {
/*  66 */     return getQName().getNamespacePrefix();
/*     */   }
/*     */ 
/*     */   public void setPrefix(String prefix) throws DOMException {
/*  70 */     DOMNodeHelper.setPrefix(this, prefix);
/*     */   }
/*     */ 
/*     */   public String getLocalName() {
/*  74 */     return getQName().getName();
/*     */   }
/*     */ 
/*     */   public String getNodeName() {
/*  78 */     return getName();
/*     */   }
/*     */ 
/*     */   public String getNodeValue()
/*     */     throws DOMException
/*     */   {
/*  85 */     return null;
/*     */   }
/*     */ 
/*     */   public void setNodeValue(String nodeValue) throws DOMException {
/*     */   }
/*     */ 
/*     */   public Node getParentNode() {
/*  92 */     return DOMNodeHelper.getParentNode(this);
/*     */   }
/*     */ 
/*     */   public NodeList getChildNodes() {
/*  96 */     return DOMNodeHelper.createNodeList(content());
/*     */   }
/*     */ 
/*     */   public Node getFirstChild() {
/* 100 */     return DOMNodeHelper.asDOMNode(node(0));
/*     */   }
/*     */ 
/*     */   public Node getLastChild() {
/* 104 */     return DOMNodeHelper.asDOMNode(node(nodeCount() - 1));
/*     */   }
/*     */ 
/*     */   public Node getPreviousSibling() {
/* 108 */     return DOMNodeHelper.getPreviousSibling(this);
/*     */   }
/*     */ 
/*     */   public Node getNextSibling() {
/* 112 */     return DOMNodeHelper.getNextSibling(this);
/*     */   }
/*     */ 
/*     */   public NamedNodeMap getAttributes() {
/* 116 */     return new DOMAttributeNodeMap(this);
/*     */   }
/*     */ 
/*     */   public Document getOwnerDocument() {
/* 120 */     return DOMNodeHelper.getOwnerDocument(this);
/*     */   }
/*     */ 
/*     */   public Node insertBefore(Node newChild, Node refChild) throws DOMException
/*     */   {
/* 125 */     checkNewChildNode(newChild);
/*     */ 
/* 127 */     return DOMNodeHelper.insertBefore(this, newChild, refChild);
/*     */   }
/*     */ 
/*     */   public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/*     */   {
/* 132 */     checkNewChildNode(newChild);
/*     */ 
/* 134 */     return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/*     */   }
/*     */ 
/*     */   public Node removeChild(Node oldChild) throws DOMException
/*     */   {
/* 139 */     return DOMNodeHelper.removeChild(this, oldChild);
/*     */   }
/*     */ 
/*     */   public Node appendChild(Node newChild) throws DOMException
/*     */   {
/* 144 */     checkNewChildNode(newChild);
/*     */ 
/* 146 */     return DOMNodeHelper.appendChild(this, newChild);
/*     */   }
/*     */ 
/*     */   private void checkNewChildNode(Node newChild) throws DOMException
/*     */   {
/* 151 */     int nodeType = newChild.getNodeType();
/*     */ 
/* 153 */     if ((nodeType != 1) && (nodeType != 3) && (nodeType != 8) && (nodeType != 7) && (nodeType != 4) && (nodeType != 5))
/*     */     {
/* 158 */       throw new DOMException((short)3, "Given node cannot be a child of element");
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean hasChildNodes()
/*     */   {
/* 164 */     return nodeCount() > 0;
/*     */   }
/*     */ 
/*     */   public Node cloneNode(boolean deep) {
/* 168 */     return DOMNodeHelper.cloneNode(this, deep);
/*     */   }
/*     */ 
/*     */   public boolean isSupported(String feature, String version) {
/* 172 */     return DOMNodeHelper.isSupported(this, feature, version);
/*     */   }
/*     */ 
/*     */   public boolean hasAttributes() {
/* 176 */     return DOMNodeHelper.hasAttributes(this);
/*     */   }
/*     */ 
/*     */   public String getTagName()
/*     */   {
/* 182 */     return getName();
/*     */   }
/*     */ 
/*     */   public String getAttribute(String name) {
/* 186 */     String answer = attributeValue(name);
/*     */ 
/* 188 */     return answer != null ? answer : "";
/*     */   }
/*     */ 
/*     */   public void setAttribute(String name, String value) throws DOMException {
/* 192 */     addAttribute(name, value);
/*     */   }
/*     */ 
/*     */   public void removeAttribute(String name) throws DOMException {
/* 196 */     Attribute attribute = attribute(name);
/*     */ 
/* 198 */     if (attribute != null)
/* 199 */       remove(attribute);
/*     */   }
/*     */ 
/*     */   public Attr getAttributeNode(String name)
/*     */   {
/* 204 */     return DOMNodeHelper.asDOMAttr(attribute(name));
/*     */   }
/*     */ 
/*     */   public Attr setAttributeNode(Attr newAttr) throws DOMException
/*     */   {
/* 209 */     if (isReadOnly()) {
/* 210 */       throw new DOMException((short)7, "No modification allowed");
/*     */     }
/*     */ 
/* 214 */     Attribute attribute = attribute(newAttr);
/*     */ 
/* 216 */     if (attribute != newAttr) {
/* 217 */       if (newAttr.getOwnerElement() != null) {
/* 218 */         throw new DOMException((short)10, "Attribute is already in use");
/*     */       }
/*     */ 
/* 222 */       Attribute newAttribute = createAttribute(newAttr);
/*     */ 
/* 224 */       if (attribute != null) {
/* 225 */         attribute.detach();
/*     */       }
/*     */ 
/* 228 */       add(newAttribute);
/*     */     }
/*     */ 
/* 231 */     return DOMNodeHelper.asDOMAttr(attribute);
/*     */   }
/*     */ 
/*     */   public Attr removeAttributeNode(Attr oldAttr) throws DOMException
/*     */   {
/* 236 */     Attribute attribute = attribute(oldAttr);
/*     */ 
/* 238 */     if (attribute != null) {
/* 239 */       attribute.detach();
/*     */ 
/* 241 */       return DOMNodeHelper.asDOMAttr(attribute);
/*     */     }
/* 243 */     throw new DOMException((short)8, "No such attribute");
/*     */   }
/*     */ 
/*     */   public String getAttributeNS(String namespaceURI, String localName)
/*     */   {
/* 249 */     Attribute attribute = attribute(namespaceURI, localName);
/*     */ 
/* 251 */     if (attribute != null) {
/* 252 */       String answer = attribute.getValue();
/*     */ 
/* 254 */       if (answer != null) {
/* 255 */         return answer;
/*     */       }
/*     */     }
/*     */ 
/* 259 */     return "";
/*     */   }
/*     */ 
/*     */   public void setAttributeNS(String namespaceURI, String qualifiedName, String value) throws DOMException
/*     */   {
/* 264 */     Attribute attribute = attribute(namespaceURI, qualifiedName);
/*     */ 
/* 266 */     if (attribute != null) {
/* 267 */       attribute.setValue(value);
/*     */     } else {
/* 269 */       QName qname = getQName(namespaceURI, qualifiedName);
/* 270 */       addAttribute(qname, value);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void removeAttributeNS(String namespaceURI, String localName) throws DOMException
/*     */   {
/* 276 */     Attribute attribute = attribute(namespaceURI, localName);
/*     */ 
/* 278 */     if (attribute != null)
/* 279 */       remove(attribute);
/*     */   }
/*     */ 
/*     */   public Attr getAttributeNodeNS(String namespaceURI, String localName)
/*     */   {
/* 285 */     Attribute attribute = attribute(namespaceURI, localName);
/*     */ 
/* 287 */     if (attribute != null) {
/* 288 */       DOMNodeHelper.asDOMAttr(attribute);
/*     */     }
/*     */ 
/* 291 */     return null;
/*     */   }
/*     */ 
/*     */   public Attr setAttributeNodeNS(Attr newAttr) throws DOMException
/*     */   {
/* 296 */     Attribute attribute = attribute(newAttr.getNamespaceURI(), newAttr.getLocalName());
/*     */ 
/* 299 */     if (attribute != null) {
/* 300 */       attribute.setValue(newAttr.getValue());
/*     */     } else {
/* 302 */       attribute = createAttribute(newAttr);
/* 303 */       add(attribute);
/*     */     }
/*     */ 
/* 306 */     return DOMNodeHelper.asDOMAttr(attribute);
/*     */   }
/*     */ 
/*     */   public NodeList getElementsByTagName(String name) {
/* 310 */     ArrayList list = new ArrayList();
/* 311 */     DOMNodeHelper.appendElementsByTagName(list, this, name);
/*     */ 
/* 313 */     return DOMNodeHelper.createNodeList(list);
/*     */   }
/*     */ 
/*     */   public NodeList getElementsByTagNameNS(String namespace, String lName) {
/* 317 */     ArrayList list = new ArrayList();
/* 318 */     DOMNodeHelper.appendElementsByTagNameNS(list, this, namespace, lName);
/*     */ 
/* 320 */     return DOMNodeHelper.createNodeList(list);
/*     */   }
/*     */ 
/*     */   public boolean hasAttribute(String name) {
/* 324 */     return attribute(name) != null;
/*     */   }
/*     */ 
/*     */   public boolean hasAttributeNS(String namespaceURI, String localName) {
/* 328 */     return attribute(namespaceURI, localName) != null;
/*     */   }
/*     */ 
/*     */   protected DocumentFactory getDocumentFactory()
/*     */   {
/* 334 */     DocumentFactory factory = getQName().getDocumentFactory();
/*     */ 
/* 336 */     return factory != null ? factory : DOCUMENT_FACTORY;
/*     */   }
/*     */ 
/*     */   protected Attribute attribute(Attr attr) {
/* 340 */     return attribute(DOCUMENT_FACTORY.createQName(attr.getLocalName(), attr.getPrefix(), attr.getNamespaceURI()));
/*     */   }
/*     */ 
/*     */   protected Attribute attribute(String namespaceURI, String localName)
/*     */   {
/* 345 */     List attributes = attributeList();
/* 346 */     int size = attributes.size();
/*     */ 
/* 348 */     for (int i = 0; i < size; i++) {
/* 349 */       Attribute attribute = (Attribute)attributes.get(i);
/*     */ 
/* 351 */       if ((localName.equals(attribute.getName())) && (((namespaceURI != null) && (namespaceURI.length() != 0)) || ((attribute.getNamespaceURI() == null) || (attribute.getNamespaceURI().length() == 0) || ((namespaceURI != null) && (namespaceURI.equals(attribute.getNamespaceURI()))))))
/*     */       {
/* 357 */         return attribute;
/*     */       }
/*     */     }
/*     */ 
/* 361 */     return null;
/*     */   }
/*     */ 
/*     */   protected Attribute createAttribute(Attr newAttr) {
/* 365 */     QName qname = null;
/* 366 */     String name = newAttr.getLocalName();
/*     */ 
/* 368 */     if (name != null) {
/* 369 */       String prefix = newAttr.getPrefix();
/* 370 */       String uri = newAttr.getNamespaceURI();
/* 371 */       qname = getDocumentFactory().createQName(name, prefix, uri);
/*     */     } else {
/* 373 */       name = newAttr.getName();
/* 374 */       qname = getDocumentFactory().createQName(name);
/*     */     }
/*     */ 
/* 377 */     return new DOMAttribute(qname, newAttr.getValue());
/*     */   }
/*     */ 
/*     */   protected QName getQName(String namespace, String qualifiedName) {
/* 381 */     int index = qualifiedName.indexOf(':');
/* 382 */     String prefix = "";
/* 383 */     String localName = qualifiedName;
/*     */ 
/* 385 */     if (index >= 0) {
/* 386 */       prefix = qualifiedName.substring(0, index);
/* 387 */       localName = qualifiedName.substring(index + 1);
/*     */     }
/*     */ 
/* 390 */     return getDocumentFactory().createQName(localName, prefix, namespace);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMElement
 * JD-Core Version:    0.6.2
 */