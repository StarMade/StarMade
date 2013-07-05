/*     */ package org.dom4j.io;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.dom4j.Branch;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.QName;
/*     */ import org.dom4j.tree.NamespaceStack;
/*     */ import org.w3c.dom.DocumentType;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class DOMReader
/*     */ {
/*     */   private DocumentFactory factory;
/*     */   private NamespaceStack namespaceStack;
/*     */ 
/*     */   public DOMReader()
/*     */   {
/*  38 */     this.factory = DocumentFactory.getInstance();
/*  39 */     this.namespaceStack = new NamespaceStack(this.factory);
/*     */   }
/*     */ 
/*     */   public DOMReader(DocumentFactory factory) {
/*  43 */     this.factory = factory;
/*  44 */     this.namespaceStack = new NamespaceStack(factory);
/*     */   }
/*     */ 
/*     */   public DocumentFactory getDocumentFactory()
/*     */   {
/*  54 */     return this.factory;
/*     */   }
/*     */ 
/*     */   public void setDocumentFactory(DocumentFactory docFactory)
/*     */   {
/*  69 */     this.factory = docFactory;
/*  70 */     this.namespaceStack.setDocumentFactory(this.factory);
/*     */   }
/*     */ 
/*     */   public org.dom4j.Document read(org.w3c.dom.Document domDocument) {
/*  74 */     if ((domDocument instanceof org.dom4j.Document)) {
/*  75 */       return (org.dom4j.Document)domDocument;
/*     */     }
/*     */ 
/*  78 */     org.dom4j.Document document = createDocument();
/*     */ 
/*  80 */     clearNamespaceStack();
/*     */ 
/*  82 */     NodeList nodeList = domDocument.getChildNodes();
/*     */ 
/*  84 */     int i = 0; for (int size = nodeList.getLength(); i < size; i++) {
/*  85 */       readTree(nodeList.item(i), document);
/*     */     }
/*     */ 
/*  88 */     return document;
/*     */   }
/*     */ 
/*     */   protected void readTree(Node node, Branch current)
/*     */   {
/*  93 */     Element element = null;
/*  94 */     org.dom4j.Document document = null;
/*     */ 
/*  96 */     if ((current instanceof Element))
/*  97 */       element = (Element)current;
/*     */     else {
/*  99 */       document = (org.dom4j.Document)current;
/*     */     }
/*     */ 
/* 102 */     switch (node.getNodeType()) {
/*     */     case 1:
/* 104 */       readElement(node, current);
/*     */ 
/* 106 */       break;
/*     */     case 7:
/* 110 */       if ((current instanceof Element)) {
/* 111 */         Element currentEl = (Element)current;
/* 112 */         currentEl.addProcessingInstruction(node.getNodeName(), node.getNodeValue());
/*     */       }
/*     */       else {
/* 115 */         org.dom4j.Document currentDoc = (org.dom4j.Document)current;
/* 116 */         currentDoc.addProcessingInstruction(node.getNodeName(), node.getNodeValue());
/*     */       }
/*     */ 
/* 120 */       break;
/*     */     case 8:
/* 124 */       if ((current instanceof Element))
/* 125 */         ((Element)current).addComment(node.getNodeValue());
/*     */       else {
/* 127 */         ((org.dom4j.Document)current).addComment(node.getNodeValue());
/*     */       }
/*     */ 
/* 130 */       break;
/*     */     case 10:
/* 134 */       DocumentType domDocType = (DocumentType)node;
/*     */ 
/* 136 */       document.addDocType(domDocType.getName(), domDocType.getPublicId(), domDocType.getSystemId());
/*     */ 
/* 139 */       break;
/*     */     case 3:
/* 142 */       element.addText(node.getNodeValue());
/*     */ 
/* 144 */       break;
/*     */     case 4:
/* 147 */       element.addCDATA(node.getNodeValue());
/*     */ 
/* 149 */       break;
/*     */     case 5:
/* 154 */       Node firstChild = node.getFirstChild();
/*     */ 
/* 156 */       if (firstChild != null) {
/* 157 */         element.addEntity(node.getNodeName(), firstChild.getNodeValue());
/*     */       }
/*     */       else {
/* 160 */         element.addEntity(node.getNodeName(), "");
/*     */       }
/*     */ 
/* 163 */       break;
/*     */     case 6:
/* 166 */       element.addEntity(node.getNodeName(), node.getNodeValue());
/*     */ 
/* 168 */       break;
/*     */     case 2:
/*     */     case 9:
/*     */     default:
/* 171 */       System.out.println("WARNING: Unknown DOM node type: " + node.getNodeType());
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void readElement(Node node, Branch current)
/*     */   {
/* 177 */     int previouslyDeclaredNamespaces = this.namespaceStack.size();
/*     */ 
/* 179 */     String namespaceUri = node.getNamespaceURI();
/* 180 */     String elementPrefix = node.getPrefix();
/*     */ 
/* 182 */     if (elementPrefix == null) {
/* 183 */       elementPrefix = "";
/*     */     }
/*     */ 
/* 186 */     NamedNodeMap attributeList = node.getAttributes();
/*     */ 
/* 188 */     if ((attributeList != null) && (namespaceUri == null))
/*     */     {
/* 190 */       Node attribute = attributeList.getNamedItem("xmlns");
/*     */ 
/* 192 */       if (attribute != null) {
/* 193 */         namespaceUri = attribute.getNodeValue();
/* 194 */         elementPrefix = "";
/*     */       }
/*     */     }
/*     */ 
/* 198 */     QName qName = this.namespaceStack.getQName(namespaceUri, node.getLocalName(), node.getNodeName());
/*     */ 
/* 200 */     Element element = current.addElement(qName);
/*     */ 
/* 202 */     if (attributeList != null) {
/* 203 */       int size = attributeList.getLength();
/* 204 */       List attributes = new ArrayList(size);
/*     */ 
/* 206 */       for (int i = 0; i < size; i++) {
/* 207 */         Node attribute = attributeList.item(i);
/*     */ 
/* 210 */         String name = attribute.getNodeName();
/*     */ 
/* 212 */         if (name.startsWith("xmlns")) {
/* 213 */           String prefix = getPrefix(name);
/* 214 */           String uri = attribute.getNodeValue();
/*     */ 
/* 216 */           Namespace namespace = this.namespaceStack.addNamespace(prefix, uri);
/*     */ 
/* 218 */           element.add(namespace);
/*     */         } else {
/* 220 */           attributes.add(attribute);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 225 */       size = attributes.size();
/*     */ 
/* 227 */       for (int i = 0; i < size; i++) {
/* 228 */         Node attribute = (Node)attributes.get(i);
/*     */ 
/* 230 */         QName attributeQName = this.namespaceStack.getQName(attribute.getNamespaceURI(), attribute.getLocalName(), attribute.getNodeName());
/*     */ 
/* 233 */         element.addAttribute(attributeQName, attribute.getNodeValue());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 238 */     NodeList children = node.getChildNodes();
/*     */ 
/* 240 */     int i = 0; for (int size = children.getLength(); i < size; i++) {
/* 241 */       Node child = children.item(i);
/* 242 */       readTree(child, element);
/*     */     }
/*     */ 
/* 246 */     while (this.namespaceStack.size() > previouslyDeclaredNamespaces)
/* 247 */       this.namespaceStack.pop();
/*     */   }
/*     */ 
/*     */   protected Namespace getNamespace(String prefix, String uri)
/*     */   {
/* 252 */     return getDocumentFactory().createNamespace(prefix, uri);
/*     */   }
/*     */ 
/*     */   protected org.dom4j.Document createDocument() {
/* 256 */     return getDocumentFactory().createDocument();
/*     */   }
/*     */ 
/*     */   protected void clearNamespaceStack() {
/* 260 */     this.namespaceStack.clear();
/*     */ 
/* 262 */     if (!this.namespaceStack.contains(Namespace.XML_NAMESPACE))
/* 263 */       this.namespaceStack.push(Namespace.XML_NAMESPACE);
/*     */   }
/*     */ 
/*     */   private String getPrefix(String xmlnsDecl)
/*     */   {
/* 268 */     int index = xmlnsDecl.indexOf(':', 5);
/*     */ 
/* 270 */     if (index != -1) {
/* 271 */       return xmlnsDecl.substring(index + 1);
/*     */     }
/* 273 */     return "";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.DOMReader
 * JD-Core Version:    0.6.2
 */