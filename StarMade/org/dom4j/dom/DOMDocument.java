/*     */ package org.dom4j.dom;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.QName;
/*     */ import org.dom4j.tree.DefaultDocument;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.CDATASection;
/*     */ import org.w3c.dom.Comment;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.DOMImplementation;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.DocumentFragment;
/*     */ import org.w3c.dom.DocumentType;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.EntityReference;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.ProcessingInstruction;
/*     */ import org.w3c.dom.Text;
/*     */ 
/*     */ public class DOMDocument extends DefaultDocument
/*     */   implements Document
/*     */ {
/*  36 */   private static final DOMDocumentFactory DOCUMENT_FACTORY = (DOMDocumentFactory)DOMDocumentFactory.getInstance();
/*     */ 
/*     */   public DOMDocument()
/*     */   {
/*  40 */     init();
/*     */   }
/*     */ 
/*     */   public DOMDocument(String name) {
/*  44 */     super(name);
/*  45 */     init();
/*     */   }
/*     */ 
/*     */   public DOMDocument(DOMElement rootElement) {
/*  49 */     super(rootElement);
/*  50 */     init();
/*     */   }
/*     */ 
/*     */   public DOMDocument(DOMDocumentType docType) {
/*  54 */     super(docType);
/*  55 */     init();
/*     */   }
/*     */ 
/*     */   public DOMDocument(DOMElement rootElement, DOMDocumentType docType) {
/*  59 */     super(rootElement, docType);
/*  60 */     init();
/*     */   }
/*     */ 
/*     */   public DOMDocument(String name, DOMElement rootElement, DOMDocumentType docType)
/*     */   {
/*  65 */     super(name, rootElement, docType);
/*  66 */     init();
/*     */   }
/*     */ 
/*     */   private void init() {
/*  70 */     setDocumentFactory(DOCUMENT_FACTORY);
/*     */   }
/*     */ 
/*     */   public boolean supports(String feature, String version)
/*     */   {
/*  76 */     return DOMNodeHelper.supports(this, feature, version);
/*     */   }
/*     */ 
/*     */   public String getNamespaceURI() {
/*  80 */     return DOMNodeHelper.getNamespaceURI(this);
/*     */   }
/*     */ 
/*     */   public String getPrefix() {
/*  84 */     return DOMNodeHelper.getPrefix(this);
/*     */   }
/*     */ 
/*     */   public void setPrefix(String prefix) throws DOMException {
/*  88 */     DOMNodeHelper.setPrefix(this, prefix);
/*     */   }
/*     */ 
/*     */   public String getLocalName() {
/*  92 */     return DOMNodeHelper.getLocalName(this);
/*     */   }
/*     */ 
/*     */   public String getNodeName() {
/*  96 */     return "#document";
/*     */   }
/*     */ 
/*     */   public String getNodeValue()
/*     */     throws DOMException
/*     */   {
/* 103 */     return null;
/*     */   }
/*     */ 
/*     */   public void setNodeValue(String nodeValue) throws DOMException {
/*     */   }
/*     */ 
/*     */   public Node getParentNode() {
/* 110 */     return DOMNodeHelper.getParentNode(this);
/*     */   }
/*     */ 
/*     */   public NodeList getChildNodes() {
/* 114 */     return DOMNodeHelper.createNodeList(content());
/*     */   }
/*     */ 
/*     */   public Node getFirstChild() {
/* 118 */     return DOMNodeHelper.asDOMNode(node(0));
/*     */   }
/*     */ 
/*     */   public Node getLastChild() {
/* 122 */     return DOMNodeHelper.asDOMNode(node(nodeCount() - 1));
/*     */   }
/*     */ 
/*     */   public Node getPreviousSibling() {
/* 126 */     return DOMNodeHelper.getPreviousSibling(this);
/*     */   }
/*     */ 
/*     */   public Node getNextSibling() {
/* 130 */     return DOMNodeHelper.getNextSibling(this);
/*     */   }
/*     */ 
/*     */   public NamedNodeMap getAttributes() {
/* 134 */     return null;
/*     */   }
/*     */ 
/*     */   public Document getOwnerDocument() {
/* 138 */     return null;
/*     */   }
/*     */ 
/*     */   public Node insertBefore(Node newChild, Node refChild) throws DOMException
/*     */   {
/* 143 */     checkNewChildNode(newChild);
/*     */ 
/* 145 */     return DOMNodeHelper.insertBefore(this, newChild, refChild);
/*     */   }
/*     */ 
/*     */   public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/*     */   {
/* 150 */     checkNewChildNode(newChild);
/*     */ 
/* 152 */     return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/*     */   }
/*     */ 
/*     */   public Node removeChild(Node oldChild) throws DOMException
/*     */   {
/* 157 */     return DOMNodeHelper.removeChild(this, oldChild);
/*     */   }
/*     */ 
/*     */   public Node appendChild(Node newChild) throws DOMException
/*     */   {
/* 162 */     checkNewChildNode(newChild);
/*     */ 
/* 164 */     return DOMNodeHelper.appendChild(this, newChild);
/*     */   }
/*     */ 
/*     */   private void checkNewChildNode(Node newChild) throws DOMException
/*     */   {
/* 169 */     int nodeType = newChild.getNodeType();
/*     */ 
/* 171 */     if ((nodeType != 1) && (nodeType != 8) && (nodeType != 7) && (nodeType != 10))
/*     */     {
/* 175 */       throw new DOMException((short)3, "Given node cannot be a child of document");
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean hasChildNodes()
/*     */   {
/* 181 */     return nodeCount() > 0;
/*     */   }
/*     */ 
/*     */   public Node cloneNode(boolean deep) {
/* 185 */     return DOMNodeHelper.cloneNode(this, deep);
/*     */   }
/*     */ 
/*     */   public boolean isSupported(String feature, String version) {
/* 189 */     return DOMNodeHelper.isSupported(this, feature, version);
/*     */   }
/*     */ 
/*     */   public boolean hasAttributes() {
/* 193 */     return DOMNodeHelper.hasAttributes(this);
/*     */   }
/*     */ 
/*     */   public NodeList getElementsByTagName(String name)
/*     */   {
/* 199 */     ArrayList list = new ArrayList();
/* 200 */     DOMNodeHelper.appendElementsByTagName(list, this, name);
/*     */ 
/* 202 */     return DOMNodeHelper.createNodeList(list);
/*     */   }
/*     */ 
/*     */   public NodeList getElementsByTagNameNS(String namespace, String name) {
/* 206 */     ArrayList list = new ArrayList();
/* 207 */     DOMNodeHelper.appendElementsByTagNameNS(list, this, namespace, name);
/*     */ 
/* 209 */     return DOMNodeHelper.createNodeList(list);
/*     */   }
/*     */ 
/*     */   public DocumentType getDoctype() {
/* 213 */     return DOMNodeHelper.asDOMDocumentType(getDocType());
/*     */   }
/*     */ 
/*     */   public DOMImplementation getImplementation() {
/* 217 */     if ((getDocumentFactory() instanceof DOMImplementation)) {
/* 218 */       return (DOMImplementation)getDocumentFactory();
/*     */     }
/* 220 */     return DOCUMENT_FACTORY;
/*     */   }
/*     */ 
/*     */   public Element getDocumentElement()
/*     */   {
/* 225 */     return DOMNodeHelper.asDOMElement(getRootElement());
/*     */   }
/*     */ 
/*     */   public Element createElement(String name) throws DOMException {
/* 229 */     return (Element)getDocumentFactory().createElement(name);
/*     */   }
/*     */ 
/*     */   public DocumentFragment createDocumentFragment() {
/* 233 */     DOMNodeHelper.notSupported();
/*     */ 
/* 235 */     return null;
/*     */   }
/*     */ 
/*     */   public Text createTextNode(String data) {
/* 239 */     return (Text)getDocumentFactory().createText(data);
/*     */   }
/*     */ 
/*     */   public Comment createComment(String data) {
/* 243 */     return (Comment)getDocumentFactory().createComment(data);
/*     */   }
/*     */ 
/*     */   public CDATASection createCDATASection(String data) throws DOMException {
/* 247 */     return (CDATASection)getDocumentFactory().createCDATA(data);
/*     */   }
/*     */ 
/*     */   public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException
/*     */   {
/* 252 */     return (ProcessingInstruction)getDocumentFactory().createProcessingInstruction(target, data);
/*     */   }
/*     */ 
/*     */   public Attr createAttribute(String name) throws DOMException
/*     */   {
/* 257 */     QName qname = getDocumentFactory().createQName(name);
/*     */ 
/* 259 */     return (Attr)getDocumentFactory().createAttribute(null, qname, "");
/*     */   }
/*     */ 
/*     */   public EntityReference createEntityReference(String name) throws DOMException
/*     */   {
/* 264 */     return (EntityReference)getDocumentFactory().createEntity(name, null);
/*     */   }
/*     */ 
/*     */   public Node importNode(Node importedNode, boolean deep) throws DOMException
/*     */   {
/* 269 */     DOMNodeHelper.notSupported();
/*     */ 
/* 271 */     return null;
/*     */   }
/*     */ 
/*     */   public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException
/*     */   {
/* 276 */     QName qname = getDocumentFactory().createQName(qualifiedName, namespaceURI);
/*     */ 
/* 279 */     return (Element)getDocumentFactory().createElement(qname);
/*     */   }
/*     */ 
/*     */   public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException
/*     */   {
/* 284 */     QName qname = getDocumentFactory().createQName(qualifiedName, namespaceURI);
/*     */ 
/* 287 */     return (Attr)getDocumentFactory().createAttribute(null, qname, null);
/*     */   }
/*     */ 
/*     */   public Element getElementById(String elementId)
/*     */   {
/* 292 */     return DOMNodeHelper.asDOMElement(elementByID(elementId));
/*     */   }
/*     */ 
/*     */   protected DocumentFactory getDocumentFactory()
/*     */   {
/* 298 */     if (super.getDocumentFactory() == null) {
/* 299 */       return DOCUMENT_FACTORY;
/*     */     }
/* 301 */     return super.getDocumentFactory();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMDocument
 * JD-Core Version:    0.6.2
 */