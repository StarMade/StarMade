/*     */ package org.dom4j.dom;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.CDATA;
/*     */ import org.dom4j.Comment;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Entity;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.ProcessingInstruction;
/*     */ import org.dom4j.QName;
/*     */ import org.dom4j.Text;
/*     */ import org.dom4j.util.SingletonStrategy;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.DOMImplementation;
/*     */ 
/*     */ public class DOMDocumentFactory extends DocumentFactory
/*     */   implements DOMImplementation
/*     */ {
/*  40 */   private static SingletonStrategy singleton = null;
/*     */ 
/*     */   public static DocumentFactory getInstance()
/*     */   {
/*  73 */     DOMDocumentFactory fact = (DOMDocumentFactory)singleton.instance();
/*  74 */     return fact;
/*     */   }
/*     */ 
/*     */   public org.dom4j.Document createDocument()
/*     */   {
/*  79 */     DOMDocument answer = new DOMDocument();
/*  80 */     answer.setDocumentFactory(this);
/*     */ 
/*  82 */     return answer;
/*     */   }
/*     */ 
/*     */   public org.dom4j.DocumentType createDocType(String name, String publicId, String systemId)
/*     */   {
/*  87 */     return new DOMDocumentType(name, publicId, systemId);
/*     */   }
/*     */ 
/*     */   public Element createElement(QName qname) {
/*  91 */     return new DOMElement(qname);
/*     */   }
/*     */ 
/*     */   public Element createElement(QName qname, int attributeCount) {
/*  95 */     return new DOMElement(qname, attributeCount);
/*     */   }
/*     */ 
/*     */   public Attribute createAttribute(Element owner, QName qname, String value) {
/*  99 */     return new DOMAttribute(qname, value);
/*     */   }
/*     */ 
/*     */   public CDATA createCDATA(String text) {
/* 103 */     return new DOMCDATA(text);
/*     */   }
/*     */ 
/*     */   public Comment createComment(String text) {
/* 107 */     return new DOMComment(text);
/*     */   }
/*     */ 
/*     */   public Text createText(String text) {
/* 111 */     return new DOMText(text);
/*     */   }
/*     */ 
/*     */   public Entity createEntity(String name) {
/* 115 */     return new DOMEntityReference(name);
/*     */   }
/*     */ 
/*     */   public Entity createEntity(String name, String text) {
/* 119 */     return new DOMEntityReference(name, text);
/*     */   }
/*     */ 
/*     */   public Namespace createNamespace(String prefix, String uri) {
/* 123 */     return new DOMNamespace(prefix, uri);
/*     */   }
/*     */ 
/*     */   public ProcessingInstruction createProcessingInstruction(String target, String data)
/*     */   {
/* 128 */     return new DOMProcessingInstruction(target, data);
/*     */   }
/*     */ 
/*     */   public ProcessingInstruction createProcessingInstruction(String target, Map data)
/*     */   {
/* 133 */     return new DOMProcessingInstruction(target, data);
/*     */   }
/*     */ 
/*     */   public boolean hasFeature(String feat, String version)
/*     */   {
/* 138 */     if (("XML".equalsIgnoreCase(feat)) || ("Core".equalsIgnoreCase(feat))) {
/* 139 */       return (version == null) || (version.length() == 0) || ("1.0".equals(version)) || ("2.0".equals(version));
/*     */     }
/*     */ 
/* 143 */     return false;
/*     */   }
/*     */ 
/*     */   public org.w3c.dom.DocumentType createDocumentType(String qualifiedName, String publicId, String systemId) throws DOMException
/*     */   {
/* 148 */     return new DOMDocumentType(qualifiedName, publicId, systemId);
/*     */   }
/*     */ 
/*     */   public org.w3c.dom.Document createDocument(String namespaceURI, String qualifiedName, org.w3c.dom.DocumentType docType)
/*     */     throws DOMException
/*     */   {
/*     */     DOMDocument document;
/*     */     DOMDocument document;
/* 156 */     if (docType != null) {
/* 157 */       DOMDocumentType documentType = asDocumentType(docType);
/* 158 */       document = new DOMDocument(documentType);
/*     */     } else {
/* 160 */       document = new DOMDocument();
/*     */     }
/*     */ 
/* 163 */     document.addElement(createQName(qualifiedName, namespaceURI));
/*     */ 
/* 165 */     return document;
/*     */   }
/*     */ 
/*     */   protected DOMDocumentType asDocumentType(org.w3c.dom.DocumentType docType)
/*     */   {
/* 170 */     if ((docType instanceof DOMDocumentType)) {
/* 171 */       return (DOMDocumentType)docType;
/*     */     }
/* 173 */     return new DOMDocumentType(docType.getName(), docType.getPublicId(), docType.getSystemId());
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/*  44 */       String defaultSingletonClass = "org.dom4j.util.SimpleSingleton";
/*  45 */       Class clazz = null;
/*     */       try {
/*  47 */         String singletonClass = defaultSingletonClass;
/*  48 */         singletonClass = System.getProperty("org.dom4j.dom.DOMDocumentFactory.singleton.strategy", singletonClass);
/*     */ 
/*  51 */         clazz = Class.forName(singletonClass);
/*     */       } catch (Exception exc1) {
/*     */         try {
/*  54 */           String singletonClass = defaultSingletonClass;
/*  55 */           clazz = Class.forName(singletonClass);
/*     */         } catch (Exception exc2) {
/*     */         }
/*     */       }
/*  59 */       singleton = (SingletonStrategy)clazz.newInstance();
/*  60 */       singleton.setSingletonClassName(DOMDocumentFactory.class.getName());
/*     */     }
/*     */     catch (Exception exc3)
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMDocumentFactory
 * JD-Core Version:    0.6.2
 */