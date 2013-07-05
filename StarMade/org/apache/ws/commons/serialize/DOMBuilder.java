/*     */ package org.apache.ws.commons.serialize;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.EntityReference;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.ProcessingInstruction;
/*     */ import org.w3c.dom.Text;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class DOMBuilder
/*     */   implements ContentHandler
/*     */ {
/*     */   private Document document;
/*     */   private Node target;
/*     */   private Node currentNode;
/*     */   private Locator locator;
/*     */   private boolean prefixMappingIsAttribute;
/*     */   private List prefixes;
/*     */ 
/*     */   public boolean isPrefixMappingIsAttribute()
/*     */   {
/*  53 */     return this.prefixMappingIsAttribute;
/*     */   }
/*     */ 
/*     */   public void setPrefixMappingIsAttribute(boolean pPrefixMappingIsAttribute)
/*     */   {
/*  63 */     this.prefixMappingIsAttribute = pPrefixMappingIsAttribute;
/*     */   }
/*     */ 
/*     */   public void setDocument(Document pDocument)
/*     */   {
/*  70 */     this.document = pDocument;
/*     */   }
/*     */ 
/*     */   public Document getDocument()
/*     */   {
/*  77 */     return this.document;
/*     */   }
/*     */ 
/*     */   public void setDocumentLocator(Locator pLocator)
/*     */   {
/*  84 */     this.locator = pLocator;
/*     */   }
/*     */ 
/*     */   public Locator getDocumentLocator()
/*     */   {
/*  91 */     return this.locator;
/*     */   }
/*     */ 
/*     */   public void setTarget(Node pNode)
/*     */   {
/*  99 */     this.target = pNode;
/* 100 */     this.currentNode = pNode;
/* 101 */     if (getDocument() == null)
/* 102 */       setDocument(pNode.getNodeType() == 9 ? (Document)pNode : pNode.getOwnerDocument());
/*     */   }
/*     */ 
/*     */   public Node getTarget()
/*     */   {
/* 112 */     return this.target;
/*     */   }
/*     */ 
/*     */   public void startDocument() throws SAXException
/*     */   {
/*     */   }
/*     */ 
/*     */   public void endDocument() throws SAXException {
/*     */   }
/*     */ 
/*     */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/* 123 */     if (isPrefixMappingIsAttribute()) {
/* 124 */       if (this.prefixes == null) {
/* 125 */         this.prefixes = new ArrayList();
/*     */       }
/* 127 */       this.prefixes.add(prefix);
/* 128 */       this.prefixes.add(uri);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void endPrefixMapping(String prefix) throws SAXException
/*     */   {
/*     */   }
/*     */ 
/*     */   public void startElement(String pNamespaceURI, String pLocalName, String pQName, Attributes pAttr) throws SAXException {
/* 137 */     Document doc = getDocument();
/*     */     Element element;
/*     */     Element element;
/* 139 */     if ((pNamespaceURI == null) || (pNamespaceURI.length() == 0))
/* 140 */       element = doc.createElement(pQName);
/*     */     else {
/* 142 */       element = doc.createElementNS(pNamespaceURI, pQName);
/*     */     }
/* 144 */     if (pAttr != null) {
/* 145 */       for (int i = 0; i < pAttr.getLength(); i++) {
/* 146 */         String uri = pAttr.getURI(i);
/* 147 */         String qName = pAttr.getQName(i);
/* 148 */         String value = pAttr.getValue(i);
/* 149 */         if ((uri == null) || (uri.length() == 0))
/* 150 */           element.setAttribute(qName, value);
/*     */         else {
/* 152 */           element.setAttributeNS(uri, qName, value);
/*     */         }
/*     */       }
/*     */     }
/* 156 */     if (this.prefixes != null) {
/* 157 */       for (int i = 0; i < this.prefixes.size(); i += 2) {
/* 158 */         String prefix = (String)this.prefixes.get(i);
/* 159 */         String uri = (String)this.prefixes.get(i + 1);
/* 160 */         if ((prefix == null) || ("".equals(prefix))) {
/* 161 */           element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", uri);
/*     */         }
/*     */         else {
/* 164 */           element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + prefix, uri);
/*     */         }
/*     */       }
/*     */ 
/* 168 */       this.prefixes.clear();
/*     */     }
/* 170 */     this.currentNode.appendChild(element);
/* 171 */     this.currentNode = element;
/*     */   }
/*     */ 
/*     */   public void endElement(String namespaceURI, String localName, String qName) throws SAXException
/*     */   {
/* 176 */     this.currentNode = this.currentNode.getParentNode();
/*     */   }
/*     */ 
/*     */   public void characters(char[] ch, int start, int length) throws SAXException
/*     */   {
/* 181 */     Node node = this.currentNode.getLastChild();
/* 182 */     String s = new String(ch, start, length);
/* 183 */     if ((node != null) && (node.getNodeType() == 3)) {
/* 184 */       ((Text)node).appendData(s);
/*     */     } else {
/* 186 */       Text text = getDocument().createTextNode(s);
/* 187 */       this.currentNode.appendChild(text);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException
/*     */   {
/* 193 */     characters(ch, start, length);
/*     */   }
/*     */ 
/*     */   public void processingInstruction(String pTarget, String pData) throws SAXException
/*     */   {
/* 198 */     ProcessingInstruction pi = getDocument().createProcessingInstruction(pTarget, pData);
/* 199 */     this.currentNode.appendChild(pi);
/*     */   }
/*     */ 
/*     */   public void skippedEntity(String pName) throws SAXException {
/* 203 */     EntityReference entity = getDocument().createEntityReference(pName);
/* 204 */     this.currentNode.appendChild(entity);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.ws.commons.serialize.DOMBuilder
 * JD-Core Version:    0.6.2
 */