/*     */ package org.apache.ws.commons.serialize;
/*     */ 
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.ext.LexicalHandler;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ 
/*     */ public class DOMSerializer
/*     */ {
/*     */   private boolean namespaceDeclarationAttribute;
/*     */   private boolean parentsNamespaceDeclarationDisabled;
/*  32 */   private boolean startingDocument = true;
/*     */ 
/*     */   public void setNamespaceDeclarationAttribute(boolean pXmlDeclarationAttribute)
/*     */   {
/*  40 */     this.namespaceDeclarationAttribute = pXmlDeclarationAttribute;
/*     */   }
/*     */ 
/*     */   public boolean isNamespaceDeclarationAttribute()
/*     */   {
/*  49 */     return this.namespaceDeclarationAttribute;
/*     */   }
/*     */ 
/*     */   public void setParentsNamespaceDeclarationDisabled(boolean pParentsXmlDeclarationDisabled)
/*     */   {
/*  61 */     this.parentsNamespaceDeclarationDisabled = pParentsXmlDeclarationDisabled;
/*     */   }
/*     */ 
/*     */   public boolean isParentsNamespaceDeclarationDisabled()
/*     */   {
/*  73 */     return this.parentsNamespaceDeclarationDisabled;
/*     */   }
/*     */ 
/*     */   public boolean isStartingDocument()
/*     */   {
/*  84 */     return this.startingDocument;
/*     */   }
/*     */ 
/*     */   public void setStartingDocument(boolean pStartingDocument)
/*     */   {
/*  96 */     this.startingDocument = pStartingDocument;
/*     */   }
/*     */ 
/*     */   protected void doSerializeChilds(Node pNode, ContentHandler pHandler)
/*     */     throws SAXException
/*     */   {
/* 106 */     for (Node child = pNode.getFirstChild(); child != null; 
/* 107 */       child = child.getNextSibling())
/* 108 */       doSerialize(child, pHandler);
/*     */   }
/*     */ 
/*     */   private void parentsStartPrefixMappingEvents(Node pNode, ContentHandler pHandler)
/*     */     throws SAXException
/*     */   {
/* 122 */     if (pNode != null) {
/* 123 */       parentsStartPrefixMappingEvents(pNode.getParentNode(), pHandler);
/* 124 */       if (pNode.getNodeType() == 1)
/* 125 */         startPrefixMappingEvents(pNode, pHandler);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void parentsEndPrefixMappingEvents(Node pNode, ContentHandler pHandler)
/*     */     throws SAXException
/*     */   {
/* 140 */     if (pNode != null) {
/* 141 */       if (pNode.getNodeType() == 1) {
/* 142 */         endPrefixMappingEvents(pNode, pHandler);
/*     */       }
/* 144 */       parentsEndPrefixMappingEvents(pNode.getParentNode(), pHandler);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void startPrefixMappingEvents(Node pNode, ContentHandler pHandler)
/*     */     throws SAXException
/*     */   {
/* 155 */     NamedNodeMap nnm = pNode.getAttributes();
/* 156 */     if (nnm != null)
/* 157 */       for (int i = 0; i < nnm.getLength(); i++) {
/* 158 */         Node attr = nnm.item(i);
/* 159 */         if ("http://www.w3.org/2000/xmlns/".equals(attr.getNamespaceURI()))
/*     */         {
/*     */           String prefix;
/* 161 */           if ("xmlns".equals(attr.getPrefix())) {
/* 162 */             prefix = attr.getLocalName();
/*     */           }
/*     */           else
/*     */           {
/*     */             String prefix;
/* 163 */             if ("xmlns".equals(attr.getNodeName()))
/* 164 */               prefix = "";
/*     */             else
/* 166 */               throw new IllegalStateException("Unable to parse namespace declaration: " + attr.getNodeName());
/*     */           }
/*     */           String prefix;
/* 168 */           String uri = attr.getNodeValue();
/* 169 */           if (uri == null) {
/* 170 */             uri = "";
/*     */           }
/* 172 */           pHandler.startPrefixMapping(prefix, uri);
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   private void endPrefixMappingEvents(Node pNode, ContentHandler pHandler)
/*     */     throws SAXException
/*     */   {
/* 185 */     NamedNodeMap nnm = pNode.getAttributes();
/* 186 */     if (nnm != null)
/* 187 */       for (int i = nnm.getLength() - 1; i >= 0; i--) {
/* 188 */         Node attr = nnm.item(i);
/* 189 */         if ("http://www.w3.org/2000/xmlns/".equals(attr.getNamespaceURI())) {
/* 190 */           String prefix = attr.getLocalName();
/* 191 */           pHandler.endPrefixMapping(prefix);
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   private void characters(ContentHandler pHandler, String pValue, boolean pCdata)
/*     */     throws SAXException
/*     */   {
/*     */     LexicalHandler lh;
/*     */     LexicalHandler lh;
/* 200 */     if (pCdata)
/* 201 */       lh = (pHandler instanceof LexicalHandler) ? (LexicalHandler)pHandler : null;
/*     */     else {
/* 203 */       lh = null;
/*     */     }
/* 205 */     if (lh != null) {
/* 206 */       lh.startCDATA();
/*     */     }
/* 208 */     pHandler.characters(pValue.toCharArray(), 0, pValue.length());
/* 209 */     if (lh != null)
/* 210 */       lh.endCDATA();
/*     */   }
/*     */ 
/*     */   public void serialize(Node pNode, ContentHandler pHandler)
/*     */     throws SAXException
/*     */   {
/* 223 */     if ((!isNamespaceDeclarationAttribute()) && (!isParentsNamespaceDeclarationDisabled()))
/*     */     {
/* 225 */       parentsStartPrefixMappingEvents(pNode.getParentNode(), pHandler);
/*     */     }
/* 227 */     doSerialize(pNode, pHandler);
/* 228 */     if ((!isNamespaceDeclarationAttribute()) && (!isParentsNamespaceDeclarationDisabled()))
/*     */     {
/* 230 */       parentsEndPrefixMappingEvents(pNode.getParentNode(), pHandler);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void doSerialize(Node pNode, ContentHandler pHandler)
/*     */     throws SAXException
/*     */   {
/* 248 */     switch (pNode.getNodeType()) {
/*     */     case 9:
/* 250 */       boolean startDocumentEvent = isStartingDocument();
/* 251 */       if (startDocumentEvent) {
/* 252 */         pHandler.startDocument();
/*     */       }
/* 254 */       doSerializeChilds(pNode, pHandler);
/* 255 */       if (startDocumentEvent)
/* 256 */         pHandler.endDocument(); break;
/*     */     case 11:
/* 260 */       doSerializeChilds(pNode, pHandler);
/* 261 */       break;
/*     */     case 1:
/* 263 */       AttributesImpl attr = new AttributesImpl();
/* 264 */       boolean isNamespaceDeclarationAttribute = isNamespaceDeclarationAttribute();
/* 265 */       if (!isNamespaceDeclarationAttribute) {
/* 266 */         startPrefixMappingEvents(pNode, pHandler);
/*     */       }
/* 268 */       NamedNodeMap nnm = pNode.getAttributes();
/* 269 */       if (nnm != null) {
/* 270 */         for (int i = 0; i < nnm.getLength(); i++) {
/* 271 */           Node a = nnm.item(i);
/* 272 */           if ((isNamespaceDeclarationAttribute) || (!"http://www.w3.org/2000/xmlns/".equals(a.getNamespaceURI())))
/*     */           {
/* 274 */             String aUri = a.getNamespaceURI();
/* 275 */             String aLocalName = a.getLocalName();
/* 276 */             String aNodeName = a.getNodeName();
/* 277 */             if (aLocalName == null) {
/* 278 */               if ((aUri == null) || (aUri.length() == 0))
/* 279 */                 aLocalName = aNodeName;
/*     */               else {
/* 281 */                 throw new IllegalStateException("aLocalName is null");
/*     */               }
/*     */             }
/* 284 */             attr.addAttribute(aUri == null ? "" : aUri, aNodeName, aLocalName, "CDATA", a.getNodeValue());
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 289 */       String nUri = pNode.getNamespaceURI();
/* 290 */       if (nUri == null) {
/* 291 */         nUri = "";
/*     */       }
/* 293 */       pHandler.startElement(nUri, pNode.getLocalName(), pNode.getNodeName(), attr);
/*     */ 
/* 295 */       doSerializeChilds(pNode, pHandler);
/* 296 */       pHandler.endElement(nUri, pNode.getLocalName(), pNode.getNodeName());
/*     */ 
/* 298 */       if (!isNamespaceDeclarationAttribute)
/* 299 */         endPrefixMappingEvents(pNode, pHandler); break;
/*     */     case 3:
/* 303 */       characters(pHandler, pNode.getNodeValue(), false);
/* 304 */       break;
/*     */     case 4:
/* 306 */       characters(pHandler, pNode.getNodeValue(), true);
/* 307 */       break;
/*     */     case 7:
/* 309 */       pHandler.processingInstruction(pNode.getNodeName(), pNode.getNodeValue());
/* 310 */       break;
/*     */     case 5:
/* 312 */       pHandler.skippedEntity(pNode.getNodeName());
/* 313 */       break;
/*     */     case 8:
/* 315 */       if ((pHandler instanceof LexicalHandler)) {
/* 316 */         String s = pNode.getNodeValue();
/* 317 */         ((LexicalHandler)pHandler).comment(s.toCharArray(), 0, s.length());
/* 318 */       }break;
/*     */     case 2:
/*     */     case 6:
/*     */     case 10:
/*     */     default:
/* 321 */       throw new IllegalStateException("Unknown node type: " + pNode.getNodeType());
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.ws.commons.serialize.DOMSerializer
 * JD-Core Version:    0.6.2
 */