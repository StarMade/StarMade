/*     */ package org.dom4j.io;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.CDATA;
/*     */ import org.dom4j.DocumentException;
/*     */ import org.dom4j.Entity;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.tree.NamespaceStack;
/*     */ import org.w3c.dom.CDATASection;
/*     */ import org.w3c.dom.DOMImplementation;
/*     */ import org.w3c.dom.DocumentType;
/*     */ import org.w3c.dom.EntityReference;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ public class DOMWriter
/*     */ {
/*  36 */   private static boolean loggedWarning = false;
/*     */ 
/*  38 */   private static final String[] DEFAULT_DOM_DOCUMENT_CLASSES = { "org.apache.xerces.dom.DocumentImpl", "gnu.xml.dom.DomDocument", "org.apache.crimson.tree.XmlDocument", "com.sun.xml.tree.XmlDocument", "oracle.xml.parser.v2.XMLDocument", "oracle.xml.parser.XMLDocument", "org.dom4j.dom.DOMDocument" };
/*     */   private Class domDocumentClass;
/*  52 */   private NamespaceStack namespaceStack = new NamespaceStack();
/*     */ 
/*     */   public DOMWriter() {
/*     */   }
/*     */ 
/*     */   public DOMWriter(Class domDocumentClass) {
/*  58 */     this.domDocumentClass = domDocumentClass;
/*     */   }
/*     */ 
/*     */   public Class getDomDocumentClass() throws DocumentException {
/*  62 */     Class result = this.domDocumentClass;
/*     */ 
/*  64 */     if (result == null)
/*     */     {
/*  66 */       int size = DEFAULT_DOM_DOCUMENT_CLASSES.length;
/*     */ 
/*  68 */       for (int i = 0; i < size; i++) {
/*     */         try {
/*  70 */           String name = DEFAULT_DOM_DOCUMENT_CLASSES[i];
/*  71 */           result = Class.forName(name, true, DOMWriter.class.getClassLoader());
/*     */ 
/*  74 */           if (result != null) {
/*  75 */             break;
/*     */           }
/*     */         }
/*     */         catch (Exception e)
/*     */         {
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  84 */     return result;
/*     */   }
/*     */ 
/*     */   public void setDomDocumentClass(Class domDocumentClass)
/*     */   {
/*  96 */     this.domDocumentClass = domDocumentClass;
/*     */   }
/*     */ 
/*     */   public void setDomDocumentClassName(String name)
/*     */     throws DocumentException
/*     */   {
/*     */     try
/*     */     {
/* 112 */       this.domDocumentClass = Class.forName(name, true, DOMWriter.class.getClassLoader());
/*     */     }
/*     */     catch (Exception e) {
/* 115 */       throw new DocumentException("Could not load the DOM Document class: " + name, e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public org.w3c.dom.Document write(org.dom4j.Document document)
/*     */     throws DocumentException
/*     */   {
/* 122 */     if ((document instanceof org.w3c.dom.Document)) {
/* 123 */       return (org.w3c.dom.Document)document;
/*     */     }
/*     */ 
/* 126 */     resetNamespaceStack();
/*     */ 
/* 128 */     org.w3c.dom.Document domDocument = createDomDocument(document);
/* 129 */     appendDOMTree(domDocument, domDocument, document.content());
/* 130 */     this.namespaceStack.clear();
/*     */ 
/* 132 */     return domDocument;
/*     */   }
/*     */ 
/*     */   public org.w3c.dom.Document write(org.dom4j.Document document, DOMImplementation domImpl) throws DocumentException
/*     */   {
/* 137 */     if ((document instanceof org.w3c.dom.Document)) {
/* 138 */       return (org.w3c.dom.Document)document;
/*     */     }
/*     */ 
/* 141 */     resetNamespaceStack();
/*     */ 
/* 143 */     org.w3c.dom.Document domDocument = createDomDocument(document, domImpl);
/* 144 */     appendDOMTree(domDocument, domDocument, document.content());
/* 145 */     this.namespaceStack.clear();
/*     */ 
/* 147 */     return domDocument;
/*     */   }
/*     */ 
/*     */   protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, List content)
/*     */   {
/* 152 */     int size = content.size();
/*     */ 
/* 154 */     for (int i = 0; i < size; i++) {
/* 155 */       Object object = content.get(i);
/*     */ 
/* 157 */       if ((object instanceof org.dom4j.Element)) {
/* 158 */         appendDOMTree(domDocument, domCurrent, (org.dom4j.Element)object);
/* 159 */       } else if ((object instanceof String)) {
/* 160 */         appendDOMTree(domDocument, domCurrent, (String)object);
/* 161 */       } else if ((object instanceof org.dom4j.Text)) {
/* 162 */         org.dom4j.Text text = (org.dom4j.Text)object;
/* 163 */         appendDOMTree(domDocument, domCurrent, text.getText());
/* 164 */       } else if ((object instanceof CDATA)) {
/* 165 */         appendDOMTree(domDocument, domCurrent, (CDATA)object);
/* 166 */       } else if ((object instanceof org.dom4j.Comment)) {
/* 167 */         appendDOMTree(domDocument, domCurrent, (org.dom4j.Comment)object);
/* 168 */       } else if ((object instanceof Entity)) {
/* 169 */         appendDOMTree(domDocument, domCurrent, (Entity)object);
/* 170 */       } else if ((object instanceof org.dom4j.ProcessingInstruction)) {
/* 171 */         appendDOMTree(domDocument, domCurrent, (org.dom4j.ProcessingInstruction)object);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, org.dom4j.Element element)
/*     */   {
/* 179 */     String elUri = element.getNamespaceURI();
/* 180 */     String elName = element.getQualifiedName();
/* 181 */     org.w3c.dom.Element domElement = domDocument.createElementNS(elUri, elName);
/*     */ 
/* 184 */     int stackSize = this.namespaceStack.size();
/*     */ 
/* 187 */     Namespace elementNamespace = element.getNamespace();
/*     */ 
/* 189 */     if (isNamespaceDeclaration(elementNamespace)) {
/* 190 */       this.namespaceStack.push(elementNamespace);
/* 191 */       writeNamespace(domElement, elementNamespace);
/*     */     }
/*     */ 
/* 195 */     List declaredNamespaces = element.declaredNamespaces();
/*     */ 
/* 197 */     int i = 0; for (int size = declaredNamespaces.size(); i < size; i++) {
/* 198 */       Namespace namespace = (Namespace)declaredNamespaces.get(i);
/*     */ 
/* 200 */       if (isNamespaceDeclaration(namespace)) {
/* 201 */         this.namespaceStack.push(namespace);
/* 202 */         writeNamespace(domElement, namespace);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 207 */     int i = 0; for (int size = element.attributeCount(); i < size; i++) {
/* 208 */       Attribute attribute = element.attribute(i);
/* 209 */       String attUri = attribute.getNamespaceURI();
/* 210 */       String attName = attribute.getQualifiedName();
/* 211 */       String value = attribute.getValue();
/* 212 */       domElement.setAttributeNS(attUri, attName, value);
/*     */     }
/*     */ 
/* 216 */     appendDOMTree(domDocument, domElement, element.content());
/*     */ 
/* 218 */     domCurrent.appendChild(domElement);
/*     */ 
/* 220 */     while (this.namespaceStack.size() > stackSize)
/* 221 */       this.namespaceStack.pop();
/*     */   }
/*     */ 
/*     */   protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, CDATA cdata)
/*     */   {
/* 227 */     CDATASection domCDATA = domDocument.createCDATASection(cdata.getText());
/*     */ 
/* 229 */     domCurrent.appendChild(domCDATA);
/*     */   }
/*     */ 
/*     */   protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, org.dom4j.Comment comment)
/*     */   {
/* 234 */     org.w3c.dom.Comment domComment = domDocument.createComment(comment.getText());
/*     */ 
/* 236 */     domCurrent.appendChild(domComment);
/*     */   }
/*     */ 
/*     */   protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, String text)
/*     */   {
/* 241 */     org.w3c.dom.Text domText = domDocument.createTextNode(text);
/* 242 */     domCurrent.appendChild(domText);
/*     */   }
/*     */ 
/*     */   protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, Entity entity)
/*     */   {
/* 247 */     EntityReference domEntity = domDocument.createEntityReference(entity.getName());
/*     */ 
/* 249 */     domCurrent.appendChild(domEntity);
/*     */   }
/*     */ 
/*     */   protected void appendDOMTree(org.w3c.dom.Document domDoc, Node domCurrent, org.dom4j.ProcessingInstruction pi)
/*     */   {
/* 254 */     org.w3c.dom.ProcessingInstruction domPI = domDoc.createProcessingInstruction(pi.getTarget(), pi.getText());
/*     */ 
/* 256 */     domCurrent.appendChild(domPI);
/*     */   }
/*     */ 
/*     */   protected void writeNamespace(org.w3c.dom.Element domElement, Namespace namespace)
/*     */   {
/* 261 */     String attributeName = attributeNameForNamespace(namespace);
/*     */ 
/* 264 */     domElement.setAttribute(attributeName, namespace.getURI());
/*     */   }
/*     */ 
/*     */   protected String attributeNameForNamespace(Namespace namespace) {
/* 268 */     String xmlns = "xmlns";
/* 269 */     String prefix = namespace.getPrefix();
/*     */ 
/* 271 */     if (prefix.length() > 0) {
/* 272 */       return xmlns + ":" + prefix;
/*     */     }
/*     */ 
/* 275 */     return xmlns;
/*     */   }
/*     */ 
/*     */   protected org.w3c.dom.Document createDomDocument(org.dom4j.Document document) throws DocumentException
/*     */   {
/* 280 */     org.w3c.dom.Document result = null;
/*     */ 
/* 283 */     if (this.domDocumentClass != null) {
/*     */       try {
/* 285 */         result = (org.w3c.dom.Document)this.domDocumentClass.newInstance();
/*     */       } catch (Exception e) {
/* 287 */         throw new DocumentException("Could not instantiate an instance of DOM Document with class: " + this.domDocumentClass.getName(), e);
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 294 */       result = createDomDocumentViaJAXP();
/*     */ 
/* 296 */       if (result == null) {
/* 297 */         Class theClass = getDomDocumentClass();
/*     */         try
/*     */         {
/* 300 */           result = (org.w3c.dom.Document)theClass.newInstance();
/*     */         } catch (Exception e) {
/* 302 */           throw new DocumentException("Could not instantiate an instance of DOM Document with class: " + theClass.getName(), e);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 309 */     return result;
/*     */   }
/*     */ 
/*     */   protected org.w3c.dom.Document createDomDocumentViaJAXP() throws DocumentException
/*     */   {
/*     */     try {
/* 315 */       return JAXPHelper.createDocument(false, true);
/*     */     } catch (Throwable e) {
/* 317 */       if (!loggedWarning) {
/* 318 */         loggedWarning = true;
/*     */ 
/* 320 */         if (SAXHelper.isVerboseErrorReporting())
/*     */         {
/* 323 */           System.out.println("Warning: Caught exception attempting to use JAXP to create a W3C DOM document");
/*     */ 
/* 325 */           System.out.println("Warning: Exception was: " + e);
/* 326 */           e.printStackTrace();
/*     */         } else {
/* 328 */           System.out.println("Warning: Error occurred using JAXP to create a DOM document.");
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 334 */     return null;
/*     */   }
/*     */ 
/*     */   protected org.w3c.dom.Document createDomDocument(org.dom4j.Document document, DOMImplementation domImpl) throws DocumentException
/*     */   {
/* 339 */     String namespaceURI = null;
/* 340 */     String qualifiedName = null;
/* 341 */     DocumentType docType = null;
/*     */ 
/* 343 */     return domImpl.createDocument(namespaceURI, qualifiedName, docType);
/*     */   }
/*     */ 
/*     */   protected boolean isNamespaceDeclaration(Namespace ns) {
/* 347 */     if ((ns != null) && (ns != Namespace.NO_NAMESPACE) && (ns != Namespace.XML_NAMESPACE))
/*     */     {
/* 349 */       String uri = ns.getURI();
/*     */ 
/* 351 */       if ((uri != null) && (uri.length() > 0) && 
/* 352 */         (!this.namespaceStack.contains(ns))) {
/* 353 */         return true;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 358 */     return false;
/*     */   }
/*     */ 
/*     */   protected void resetNamespaceStack() {
/* 362 */     this.namespaceStack.clear();
/* 363 */     this.namespaceStack.push(Namespace.XML_NAMESPACE);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.DOMWriter
 * JD-Core Version:    0.6.2
 */