/*     */ package org.dom4j.datatype;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.QName;
/*     */ import org.dom4j.io.SAXReader;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.InputSource;
/*     */ 
/*     */ public class DatatypeDocumentFactory extends DocumentFactory
/*     */ {
/*     */   private static final boolean DO_INTERN_QNAME = false;
/*  36 */   protected static transient DatatypeDocumentFactory singleton = new DatatypeDocumentFactory();
/*     */ 
/*  39 */   private static final Namespace XSI_NAMESPACE = Namespace.get("xsi", "http://www.w3.org/2001/XMLSchema-instance");
/*     */ 
/*  42 */   private static final QName XSI_SCHEMA_LOCATION = QName.get("schemaLocation", XSI_NAMESPACE);
/*     */ 
/*  45 */   private static final QName XSI_NO_SCHEMA_LOCATION = QName.get("noNamespaceSchemaLocation", XSI_NAMESPACE);
/*     */   private SchemaParser schemaBuilder;
/*  52 */   private SAXReader xmlSchemaReader = new SAXReader();
/*     */ 
/*  55 */   private boolean autoLoadSchema = true;
/*     */ 
/*     */   public DatatypeDocumentFactory() {
/*  58 */     this.schemaBuilder = new SchemaParser(this);
/*     */   }
/*     */ 
/*     */   public static DocumentFactory getInstance()
/*     */   {
/*  69 */     return singleton;
/*     */   }
/*     */ 
/*     */   public void loadSchema(Document schemaDocument)
/*     */   {
/*  80 */     this.schemaBuilder.build(schemaDocument);
/*     */   }
/*     */ 
/*     */   public void loadSchema(Document schemaDocument, Namespace targetNamespace) {
/*  84 */     this.schemaBuilder.build(schemaDocument, targetNamespace);
/*     */   }
/*     */ 
/*     */   public DatatypeElementFactory getElementFactory(QName elementQName)
/*     */   {
/*  97 */     DatatypeElementFactory result = null;
/*     */ 
/* 103 */     DocumentFactory factory = elementQName.getDocumentFactory();
/* 104 */     if ((factory instanceof DatatypeElementFactory)) {
/* 105 */       result = (DatatypeElementFactory)factory;
/*     */     }
/*     */ 
/* 108 */     return result;
/*     */   }
/*     */ 
/*     */   public Attribute createAttribute(Element owner, QName qname, String value)
/*     */   {
/* 114 */     if ((this.autoLoadSchema) && (qname.equals(XSI_NO_SCHEMA_LOCATION))) {
/* 115 */       Document document = owner != null ? owner.getDocument() : null;
/* 116 */       loadSchema(document, value);
/* 117 */     } else if ((this.autoLoadSchema) && (qname.equals(XSI_SCHEMA_LOCATION))) {
/* 118 */       Document document = owner != null ? owner.getDocument() : null;
/* 119 */       String uri = value.substring(0, value.indexOf(' '));
/* 120 */       Namespace namespace = owner.getNamespaceForURI(uri);
/* 121 */       loadSchema(document, value.substring(value.indexOf(' ') + 1), namespace);
/*     */     }
/*     */ 
/* 125 */     return super.createAttribute(owner, qname, value);
/*     */   }
/*     */ 
/*     */   protected void loadSchema(Document document, String schemaInstanceURI)
/*     */   {
/*     */     try
/*     */     {
/* 132 */       EntityResolver resolver = document.getEntityResolver();
/*     */ 
/* 134 */       if (resolver == null) {
/* 135 */         String msg = "No EntityResolver available for resolving URI: ";
/* 136 */         throw new InvalidSchemaException(msg + schemaInstanceURI);
/*     */       }
/*     */ 
/* 139 */       InputSource inputSource = resolver.resolveEntity(null, schemaInstanceURI);
/*     */ 
/* 142 */       if (resolver == null) {
/* 143 */         throw new InvalidSchemaException("Could not resolve the URI: " + schemaInstanceURI);
/*     */       }
/*     */ 
/* 147 */       Document schemaDocument = this.xmlSchemaReader.read(inputSource);
/* 148 */       loadSchema(schemaDocument);
/*     */     } catch (Exception e) {
/* 150 */       System.out.println("Failed to load schema: " + schemaInstanceURI);
/* 151 */       System.out.println("Caught: " + e);
/* 152 */       e.printStackTrace();
/* 153 */       throw new InvalidSchemaException("Failed to load schema: " + schemaInstanceURI);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void loadSchema(Document document, String schemaInstanceURI, Namespace namespace)
/*     */   {
/*     */     try
/*     */     {
/* 161 */       EntityResolver resolver = document.getEntityResolver();
/*     */ 
/* 163 */       if (resolver == null) {
/* 164 */         String msg = "No EntityResolver available for resolving URI: ";
/* 165 */         throw new InvalidSchemaException(msg + schemaInstanceURI);
/*     */       }
/*     */ 
/* 168 */       InputSource inputSource = resolver.resolveEntity(null, schemaInstanceURI);
/*     */ 
/* 171 */       if (resolver == null) {
/* 172 */         throw new InvalidSchemaException("Could not resolve the URI: " + schemaInstanceURI);
/*     */       }
/*     */ 
/* 176 */       Document schemaDocument = this.xmlSchemaReader.read(inputSource);
/* 177 */       loadSchema(schemaDocument, namespace);
/*     */     } catch (Exception e) {
/* 179 */       System.out.println("Failed to load schema: " + schemaInstanceURI);
/* 180 */       System.out.println("Caught: " + e);
/* 181 */       e.printStackTrace();
/* 182 */       throw new InvalidSchemaException("Failed to load schema: " + schemaInstanceURI);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.datatype.DatatypeDocumentFactory
 * JD-Core Version:    0.6.2
 */