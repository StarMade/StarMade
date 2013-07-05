/*     */ package org.dom4j.xpp;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.QName;
/*     */ import org.dom4j.tree.AbstractElement;
/*     */ import org.gjt.xpp.XmlPullParserException;
/*     */ import org.gjt.xpp.XmlStartTag;
/*     */ 
/*     */ public class ProxyXmlStartTag
/*     */   implements XmlStartTag
/*     */ {
/*     */   private Element element;
/*  35 */   private DocumentFactory factory = DocumentFactory.getInstance();
/*     */ 
/*     */   public ProxyXmlStartTag() {
/*     */   }
/*     */ 
/*     */   public ProxyXmlStartTag(Element element) {
/*  41 */     this.element = element;
/*     */   }
/*     */ 
/*     */   public void resetStartTag()
/*     */   {
/*  47 */     this.element = null;
/*     */   }
/*     */ 
/*     */   public int getAttributeCount() {
/*  51 */     return this.element != null ? this.element.attributeCount() : 0;
/*     */   }
/*     */ 
/*     */   public String getAttributeNamespaceUri(int index) {
/*  55 */     if (this.element != null) {
/*  56 */       Attribute attribute = this.element.attribute(index);
/*     */ 
/*  58 */       if (attribute != null) {
/*  59 */         return attribute.getNamespaceURI();
/*     */       }
/*     */     }
/*     */ 
/*  63 */     return null;
/*     */   }
/*     */ 
/*     */   public String getAttributeLocalName(int index) {
/*  67 */     if (this.element != null) {
/*  68 */       Attribute attribute = this.element.attribute(index);
/*     */ 
/*  70 */       if (attribute != null) {
/*  71 */         return attribute.getName();
/*     */       }
/*     */     }
/*     */ 
/*  75 */     return null;
/*     */   }
/*     */ 
/*     */   public String getAttributePrefix(int index) {
/*  79 */     if (this.element != null) {
/*  80 */       Attribute attribute = this.element.attribute(index);
/*     */ 
/*  82 */       if (attribute != null) {
/*  83 */         String prefix = attribute.getNamespacePrefix();
/*     */ 
/*  85 */         if ((prefix != null) && (prefix.length() > 0)) {
/*  86 */           return prefix;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  91 */     return null;
/*     */   }
/*     */ 
/*     */   public String getAttributeRawName(int index) {
/*  95 */     if (this.element != null) {
/*  96 */       Attribute attribute = this.element.attribute(index);
/*     */ 
/*  98 */       if (attribute != null) {
/*  99 */         return attribute.getQualifiedName();
/*     */       }
/*     */     }
/*     */ 
/* 103 */     return null;
/*     */   }
/*     */ 
/*     */   public String getAttributeValue(int index) {
/* 107 */     if (this.element != null) {
/* 108 */       Attribute attribute = this.element.attribute(index);
/*     */ 
/* 110 */       if (attribute != null) {
/* 111 */         return attribute.getValue();
/*     */       }
/*     */     }
/*     */ 
/* 115 */     return null;
/*     */   }
/*     */ 
/*     */   public String getAttributeValueFromRawName(String rawName)
/*     */   {
/*     */     Iterator iter;
/* 119 */     if (this.element != null) {
/* 120 */       for (iter = this.element.attributeIterator(); iter.hasNext(); ) {
/* 121 */         Attribute attribute = (Attribute)iter.next();
/*     */ 
/* 123 */         if (rawName.equals(attribute.getQualifiedName())) {
/* 124 */           return attribute.getValue();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */   public String getAttributeValueFromName(String namespaceURI, String localName)
/*     */   {
/*     */     Iterator iter;
/* 134 */     if (this.element != null) {
/* 135 */       for (iter = this.element.attributeIterator(); iter.hasNext(); ) {
/* 136 */         Attribute attribute = (Attribute)iter.next();
/*     */ 
/* 138 */         if ((namespaceURI.equals(attribute.getNamespaceURI())) && (localName.equals(attribute.getName())))
/*     */         {
/* 140 */           return attribute.getValue();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 145 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean isAttributeNamespaceDeclaration(int index) {
/* 149 */     if (this.element != null) {
/* 150 */       Attribute attribute = this.element.attribute(index);
/*     */ 
/* 152 */       if (attribute != null) {
/* 153 */         return "xmlns".equals(attribute.getNamespacePrefix());
/*     */       }
/*     */     }
/*     */ 
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */   public void addAttribute(String namespaceURI, String localName, String rawName, String value)
/*     */     throws XmlPullParserException
/*     */   {
/* 172 */     QName qname = QName.get(rawName, namespaceURI);
/* 173 */     this.element.addAttribute(qname, value);
/*     */   }
/*     */ 
/*     */   public void addAttribute(String namespaceURI, String localName, String rawName, String value, boolean isNamespaceDeclaration)
/*     */     throws XmlPullParserException
/*     */   {
/* 179 */     if (isNamespaceDeclaration) {
/* 180 */       String prefix = "";
/* 181 */       int idx = rawName.indexOf(':');
/*     */ 
/* 183 */       if (idx > 0) {
/* 184 */         prefix = rawName.substring(0, idx);
/*     */       }
/*     */ 
/* 187 */       this.element.addNamespace(prefix, namespaceURI);
/*     */     } else {
/* 189 */       QName qname = QName.get(rawName, namespaceURI);
/* 190 */       this.element.addAttribute(qname, value);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void ensureAttributesCapacity(int minCapacity) throws XmlPullParserException
/*     */   {
/* 196 */     if ((this.element instanceof AbstractElement)) {
/* 197 */       AbstractElement elementImpl = (AbstractElement)this.element;
/* 198 */       elementImpl.ensureAttributesCapacity(minCapacity);
/*     */     }
/*     */   }
/*     */ 
/*     */   /** @deprecated */
/*     */   public void removeAtttributes()
/*     */     throws XmlPullParserException
/*     */   {
/* 208 */     removeAttributes();
/*     */   }
/*     */ 
/*     */   public void removeAttributes() throws XmlPullParserException {
/* 212 */     if (this.element != null)
/* 213 */       this.element.setAttributes(new ArrayList());
/*     */   }
/*     */ 
/*     */   public String getLocalName()
/*     */   {
/* 222 */     return this.element.getName();
/*     */   }
/*     */ 
/*     */   public String getNamespaceUri() {
/* 226 */     return this.element.getNamespaceURI();
/*     */   }
/*     */ 
/*     */   public String getPrefix() {
/* 230 */     return this.element.getNamespacePrefix();
/*     */   }
/*     */ 
/*     */   public String getRawName() {
/* 234 */     return this.element.getQualifiedName();
/*     */   }
/*     */ 
/*     */   public void modifyTag(String namespaceURI, String lName, String rawName) {
/* 238 */     this.element = this.factory.createElement(rawName, namespaceURI);
/*     */   }
/*     */ 
/*     */   public void resetTag() {
/* 242 */     this.element = null;
/*     */   }
/*     */ 
/*     */   public boolean removeAttributeByName(String namespaceURI, String localName) throws XmlPullParserException
/*     */   {
/* 247 */     if (this.element != null) {
/* 248 */       QName qname = QName.get(localName, namespaceURI);
/* 249 */       Attribute attribute = this.element.attribute(qname);
/* 250 */       return this.element.remove(attribute);
/*     */     }
/* 252 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean removeAttributeByRawName(String rawName) throws XmlPullParserException
/*     */   {
/* 257 */     if (this.element != null) {
/* 258 */       Attribute attribute = null;
/* 259 */       Iterator it = this.element.attributeIterator();
/* 260 */       while (it.hasNext()) {
/* 261 */         Attribute current = (Attribute)it.next();
/* 262 */         if (current.getQualifiedName().equals(rawName)) {
/* 263 */           attribute = current;
/* 264 */           break;
/*     */         }
/*     */       }
/* 267 */       return this.element.remove(attribute);
/*     */     }
/* 269 */     return false;
/*     */   }
/*     */ 
/*     */   public DocumentFactory getDocumentFactory()
/*     */   {
/* 275 */     return this.factory;
/*     */   }
/*     */ 
/*     */   public void setDocumentFactory(DocumentFactory documentFactory) {
/* 279 */     this.factory = documentFactory;
/*     */   }
/*     */ 
/*     */   public Element getElement() {
/* 283 */     return this.element;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.xpp.ProxyXmlStartTag
 * JD-Core Version:    0.6.2
 */