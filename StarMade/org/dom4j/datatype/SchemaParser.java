/*     */ package org.dom4j.datatype;
/*     */ 
/*     */ import com.sun.msv.datatype.xsd.DatatypeFactory;
/*     */ import com.sun.msv.datatype.xsd.TypeIncubator;
/*     */ import com.sun.msv.datatype.xsd.XSDatatype;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.QName;
/*     */ import org.dom4j.io.SAXReader;
/*     */ import org.dom4j.util.AttributeHelper;
/*     */ import org.relaxng.datatype.DatatypeException;
/*     */ import org.relaxng.datatype.ValidationContext;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.InputSource;
/*     */ 
/*     */ public class SchemaParser
/*     */ {
/*  43 */   private static final Namespace XSD_NAMESPACE = Namespace.get("xsd", "http://www.w3.org/2001/XMLSchema");
/*     */ 
/*  47 */   private static final QName XSD_ELEMENT = QName.get("element", XSD_NAMESPACE);
/*     */ 
/*  50 */   private static final QName XSD_ATTRIBUTE = QName.get("attribute", XSD_NAMESPACE);
/*     */ 
/*  53 */   private static final QName XSD_SIMPLETYPE = QName.get("simpleType", XSD_NAMESPACE);
/*     */ 
/*  56 */   private static final QName XSD_COMPLEXTYPE = QName.get("complexType", XSD_NAMESPACE);
/*     */ 
/*  59 */   private static final QName XSD_RESTRICTION = QName.get("restriction", XSD_NAMESPACE);
/*     */ 
/*  62 */   private static final QName XSD_SEQUENCE = QName.get("sequence", XSD_NAMESPACE);
/*     */ 
/*  65 */   private static final QName XSD_CHOICE = QName.get("choice", XSD_NAMESPACE);
/*     */ 
/*  67 */   private static final QName XSD_ALL = QName.get("all", XSD_NAMESPACE);
/*     */ 
/*  69 */   private static final QName XSD_INCLUDE = QName.get("include", XSD_NAMESPACE);
/*     */   private DatatypeDocumentFactory documentFactory;
/*  79 */   private Map dataTypeCache = new HashMap();
/*     */   private NamedTypeResolver namedTypeResolver;
/*     */   private Namespace targetNamespace;
/*     */ 
/*     */   public SchemaParser()
/*     */   {
/*  88 */     this(DatatypeDocumentFactory.singleton);
/*     */   }
/*     */ 
/*     */   public SchemaParser(DatatypeDocumentFactory documentFactory) {
/*  92 */     this.documentFactory = documentFactory;
/*  93 */     this.namedTypeResolver = new NamedTypeResolver(documentFactory);
/*     */   }
/*     */ 
/*     */   public void build(Document schemaDocument)
/*     */   {
/* 103 */     this.targetNamespace = null;
/* 104 */     internalBuild(schemaDocument);
/*     */   }
/*     */ 
/*     */   public void build(Document schemaDocument, Namespace namespace) {
/* 108 */     this.targetNamespace = namespace;
/* 109 */     internalBuild(schemaDocument);
/*     */   }
/*     */ 
/*     */   private synchronized void internalBuild(Document schemaDocument) {
/* 113 */     Element root = schemaDocument.getRootElement();
/*     */ 
/* 115 */     if (root != null)
/*     */     {
/* 117 */       Iterator includeIter = root.elementIterator(XSD_INCLUDE);
/*     */ 
/* 119 */       while (includeIter.hasNext()) {
/* 120 */         Element includeElement = (Element)includeIter.next();
/* 121 */         String inclSchemaInstanceURI = includeElement.attributeValue("schemaLocation");
/*     */ 
/* 123 */         EntityResolver resolver = schemaDocument.getEntityResolver();
/*     */         try
/*     */         {
/* 126 */           if (resolver == null) {
/* 127 */             String msg = "No EntityResolver available";
/* 128 */             throw new InvalidSchemaException(msg);
/*     */           }
/*     */ 
/* 131 */           InputSource inputSource = resolver.resolveEntity(null, inclSchemaInstanceURI);
/*     */ 
/* 134 */           if (inputSource == null) {
/* 135 */             String msg = "Could not resolve the schema URI: " + inclSchemaInstanceURI;
/*     */ 
/* 137 */             throw new InvalidSchemaException(msg);
/*     */           }
/*     */ 
/* 140 */           SAXReader reader = new SAXReader();
/* 141 */           Document inclSchemaDocument = reader.read(inputSource);
/* 142 */           build(inclSchemaDocument);
/*     */         } catch (Exception e) {
/* 144 */           System.out.println("Failed to load schema: " + inclSchemaInstanceURI);
/*     */ 
/* 146 */           System.out.println("Caught: " + e);
/* 147 */           e.printStackTrace();
/* 148 */           throw new InvalidSchemaException("Failed to load schema: " + inclSchemaInstanceURI);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 154 */       Iterator iter = root.elementIterator(XSD_ELEMENT);
/*     */ 
/* 156 */       while (iter.hasNext()) {
/* 157 */         onDatatypeElement((Element)iter.next(), this.documentFactory);
/*     */       }
/*     */ 
/* 161 */       iter = root.elementIterator(XSD_SIMPLETYPE);
/*     */ 
/* 163 */       while (iter.hasNext()) {
/* 164 */         onNamedSchemaSimpleType((Element)iter.next());
/*     */       }
/*     */ 
/* 168 */       iter = root.elementIterator(XSD_COMPLEXTYPE);
/*     */ 
/* 170 */       while (iter.hasNext()) {
/* 171 */         onNamedSchemaComplexType((Element)iter.next());
/*     */       }
/*     */ 
/* 174 */       this.namedTypeResolver.resolveNamedTypes();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void onDatatypeElement(Element xsdElement, DocumentFactory parentFactory)
/*     */   {
/* 191 */     String name = xsdElement.attributeValue("name");
/* 192 */     String type = xsdElement.attributeValue("type");
/* 193 */     QName qname = getQName(name);
/*     */ 
/* 195 */     DatatypeElementFactory factory = getDatatypeElementFactory(qname);
/*     */ 
/* 197 */     if (type != null)
/*     */     {
/* 199 */       XSDatatype dataType = getTypeByName(type);
/*     */ 
/* 201 */       if (dataType != null) {
/* 202 */         factory.setChildElementXSDatatype(qname, dataType);
/*     */       } else {
/* 204 */         QName typeQName = getQName(type);
/* 205 */         this.namedTypeResolver.registerTypedElement(xsdElement, typeQName, parentFactory);
/*     */       }
/*     */ 
/* 209 */       return;
/*     */     }
/*     */ 
/* 213 */     Element xsdSimpleType = xsdElement.element(XSD_SIMPLETYPE);
/*     */ 
/* 215 */     if (xsdSimpleType != null) {
/* 216 */       XSDatatype dataType = loadXSDatatypeFromSimpleType(xsdSimpleType);
/*     */ 
/* 218 */       if (dataType != null) {
/* 219 */         factory.setChildElementXSDatatype(qname, dataType);
/*     */       }
/*     */     }
/*     */ 
/* 223 */     Element schemaComplexType = xsdElement.element(XSD_COMPLEXTYPE);
/*     */ 
/* 225 */     if (schemaComplexType != null) {
/* 226 */       onSchemaComplexType(schemaComplexType, factory);
/*     */     }
/*     */ 
/* 229 */     Iterator iter = xsdElement.elementIterator(XSD_ATTRIBUTE);
/*     */ 
/* 231 */     if (iter.hasNext())
/*     */       do {
/* 233 */         onDatatypeAttribute(xsdElement, factory, (Element)iter.next());
/*     */       }
/* 235 */       while (iter.hasNext());
/*     */   }
/*     */ 
/*     */   private void onNamedSchemaComplexType(Element schemaComplexType)
/*     */   {
/* 246 */     Attribute nameAttr = schemaComplexType.attribute("name");
/*     */ 
/* 248 */     if (nameAttr == null) {
/* 249 */       return;
/*     */     }
/*     */ 
/* 252 */     String name = nameAttr.getText();
/* 253 */     QName qname = getQName(name);
/*     */ 
/* 255 */     DatatypeElementFactory factory = getDatatypeElementFactory(qname);
/*     */ 
/* 257 */     onSchemaComplexType(schemaComplexType, factory);
/* 258 */     this.namedTypeResolver.registerComplexType(qname, factory);
/*     */   }
/*     */ 
/*     */   private void onSchemaComplexType(Element schemaComplexType, DatatypeElementFactory elementFactory)
/*     */   {
/* 271 */     Iterator iter = schemaComplexType.elementIterator(XSD_ATTRIBUTE);
/*     */ 
/* 273 */     while (iter.hasNext()) {
/* 274 */       Element xsdAttribute = (Element)iter.next();
/* 275 */       String name = xsdAttribute.attributeValue("name");
/* 276 */       QName qname = getQName(name);
/*     */ 
/* 278 */       XSDatatype dataType = dataTypeForXsdAttribute(xsdAttribute);
/*     */ 
/* 280 */       if (dataType != null)
/*     */       {
/* 284 */         elementFactory.setAttributeXSDatatype(qname, dataType);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 289 */     Element schemaSequence = schemaComplexType.element(XSD_SEQUENCE);
/*     */ 
/* 291 */     if (schemaSequence != null) {
/* 292 */       onChildElements(schemaSequence, elementFactory);
/*     */     }
/*     */ 
/* 296 */     Element schemaChoice = schemaComplexType.element(XSD_CHOICE);
/*     */ 
/* 298 */     if (schemaChoice != null) {
/* 299 */       onChildElements(schemaChoice, elementFactory);
/*     */     }
/*     */ 
/* 303 */     Element schemaAll = schemaComplexType.element(XSD_ALL);
/*     */ 
/* 305 */     if (schemaAll != null)
/* 306 */       onChildElements(schemaAll, elementFactory);
/*     */   }
/*     */ 
/*     */   private void onChildElements(Element element, DatatypeElementFactory fact)
/*     */   {
/* 311 */     Iterator iter = element.elementIterator(XSD_ELEMENT);
/*     */ 
/* 313 */     while (iter.hasNext()) {
/* 314 */       Element xsdElement = (Element)iter.next();
/* 315 */       onDatatypeElement(xsdElement, fact);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void onDatatypeAttribute(Element xsdElement, DatatypeElementFactory elementFactory, Element xsdAttribute)
/*     */   {
/* 331 */     String name = xsdAttribute.attributeValue("name");
/* 332 */     QName qname = getQName(name);
/* 333 */     XSDatatype dataType = dataTypeForXsdAttribute(xsdAttribute);
/*     */ 
/* 335 */     if (dataType != null)
/*     */     {
/* 337 */       elementFactory.setAttributeXSDatatype(qname, dataType);
/*     */     } else {
/* 339 */       String type = xsdAttribute.attributeValue("type");
/* 340 */       System.out.println("Warning: Couldn't find XSDatatype for type: " + type + " attribute: " + name);
/*     */     }
/*     */   }
/*     */ 
/*     */   private XSDatatype dataTypeForXsdAttribute(Element xsdAttribute)
/*     */   {
/* 357 */     String type = xsdAttribute.attributeValue("type");
/* 358 */     XSDatatype dataType = null;
/*     */ 
/* 360 */     if (type != null) {
/* 361 */       dataType = getTypeByName(type);
/*     */     }
/*     */     else {
/* 364 */       Element xsdSimpleType = xsdAttribute.element(XSD_SIMPLETYPE);
/*     */ 
/* 366 */       if (xsdSimpleType == null) {
/* 367 */         String name = xsdAttribute.attributeValue("name");
/* 368 */         String msg = "The attribute: " + name + " has no type attribute and does not contain a " + "<simpleType/> element";
/*     */ 
/* 371 */         throw new InvalidSchemaException(msg);
/*     */       }
/*     */ 
/* 374 */       dataType = loadXSDatatypeFromSimpleType(xsdSimpleType);
/*     */     }
/*     */ 
/* 377 */     return dataType;
/*     */   }
/*     */ 
/*     */   private void onNamedSchemaSimpleType(Element schemaSimpleType)
/*     */   {
/* 387 */     Attribute nameAttr = schemaSimpleType.attribute("name");
/*     */ 
/* 389 */     if (nameAttr == null) {
/* 390 */       return;
/*     */     }
/*     */ 
/* 393 */     String name = nameAttr.getText();
/* 394 */     QName qname = getQName(name);
/* 395 */     XSDatatype datatype = loadXSDatatypeFromSimpleType(schemaSimpleType);
/* 396 */     this.namedTypeResolver.registerSimpleType(qname, datatype);
/*     */   }
/*     */ 
/*     */   private XSDatatype loadXSDatatypeFromSimpleType(Element xsdSimpleType)
/*     */   {
/* 409 */     Element xsdRestriction = xsdSimpleType.element(XSD_RESTRICTION);
/*     */ 
/* 411 */     if (xsdRestriction != null) {
/* 412 */       String base = xsdRestriction.attributeValue("base");
/*     */ 
/* 414 */       if (base != null) {
/* 415 */         XSDatatype baseType = getTypeByName(base);
/*     */ 
/* 417 */         if (baseType == null) {
/* 418 */           onSchemaError("Invalid base type: " + base + " when trying to build restriction: " + xsdRestriction);
/*     */         }
/*     */         else
/*     */         {
/* 422 */           return deriveSimpleType(baseType, xsdRestriction);
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 427 */         Element xsdSubType = xsdSimpleType.element(XSD_SIMPLETYPE);
/*     */ 
/* 429 */         if (xsdSubType == null) {
/* 430 */           String msg = "The simpleType element: " + xsdSimpleType + " must contain a base attribute or simpleType" + " element";
/*     */ 
/* 433 */           onSchemaError(msg);
/*     */         } else {
/* 435 */           return loadXSDatatypeFromSimpleType(xsdSubType);
/*     */         }
/*     */       }
/*     */     } else {
/* 439 */       onSchemaError("No <restriction>. Could not create XSDatatype for simpleType: " + xsdSimpleType);
/*     */     }
/*     */ 
/* 443 */     return null;
/*     */   }
/*     */ 
/*     */   private XSDatatype deriveSimpleType(XSDatatype baseType, Element xsdRestriction)
/*     */   {
/* 458 */     TypeIncubator incubator = new TypeIncubator(baseType);
/* 459 */     ValidationContext context = null;
/*     */     try
/*     */     {
/* 462 */       Iterator iter = xsdRestriction.elementIterator();
/* 463 */       while (iter.hasNext()) {
/* 464 */         Element element = (Element)iter.next();
/* 465 */         String name = element.getName();
/* 466 */         String value = element.attributeValue("value");
/* 467 */         boolean fixed = AttributeHelper.booleanValue(element, "fixed");
/*     */ 
/* 470 */         incubator.addFacet(name, value, fixed, context);
/*     */       }
/*     */ 
/* 474 */       String newTypeName = null;
/*     */ 
/* 476 */       return incubator.derive("", newTypeName);
/*     */     } catch (DatatypeException e) {
/* 478 */       onSchemaError("Invalid restriction: " + e.getMessage() + " when trying to build restriction: " + xsdRestriction);
/*     */     }
/*     */ 
/* 481 */     return null;
/*     */   }
/*     */ 
/*     */   private DatatypeElementFactory getDatatypeElementFactory(QName name)
/*     */   {
/* 495 */     DatatypeElementFactory factory = this.documentFactory.getElementFactory(name);
/*     */ 
/* 498 */     if (factory == null) {
/* 499 */       factory = new DatatypeElementFactory(name);
/* 500 */       name.setDocumentFactory(factory);
/*     */     }
/*     */ 
/* 503 */     return factory;
/*     */   }
/*     */ 
/*     */   private XSDatatype getTypeByName(String type) {
/* 507 */     XSDatatype dataType = (XSDatatype)this.dataTypeCache.get(type);
/*     */ 
/* 509 */     if (dataType == null)
/*     */     {
/* 512 */       int idx = type.indexOf(':');
/*     */ 
/* 514 */       if (idx >= 0) {
/* 515 */         String localName = type.substring(idx + 1);
/*     */         try
/*     */         {
/* 518 */           dataType = DatatypeFactory.getTypeByName(localName);
/*     */         }
/*     */         catch (DatatypeException e) {
/*     */         }
/*     */       }
/* 523 */       if (dataType == null)
/*     */         try {
/* 525 */           dataType = DatatypeFactory.getTypeByName(type);
/*     */         }
/*     */         catch (DatatypeException e)
/*     */         {
/*     */         }
/* 530 */       if (dataType == null)
/*     */       {
/* 532 */         QName typeQName = getQName(type);
/* 533 */         dataType = (XSDatatype)this.namedTypeResolver.simpleTypeMap.get(typeQName);
/*     */       }
/*     */ 
/* 537 */       if (dataType != null)
/*     */       {
/* 539 */         this.dataTypeCache.put(type, dataType);
/*     */       }
/*     */     }
/*     */ 
/* 543 */     return dataType;
/*     */   }
/*     */ 
/*     */   private QName getQName(String name) {
/* 547 */     if (this.targetNamespace == null) {
/* 548 */       return this.documentFactory.createQName(name);
/*     */     }
/* 550 */     return this.documentFactory.createQName(name, this.targetNamespace);
/*     */   }
/*     */ 
/*     */   private void onSchemaError(String message)
/*     */   {
/* 568 */     throw new InvalidSchemaException(message);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.datatype.SchemaParser
 * JD-Core Version:    0.6.2
 */