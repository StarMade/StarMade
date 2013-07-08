/*   1:    */package org.dom4j.datatype;
/*   2:    */
/*   3:    */import com.sun.msv.datatype.xsd.DatatypeFactory;
/*   4:    */import com.sun.msv.datatype.xsd.TypeIncubator;
/*   5:    */import com.sun.msv.datatype.xsd.XSDatatype;
/*   6:    */import java.io.PrintStream;
/*   7:    */import java.util.HashMap;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.Map;
/*  10:    */import org.dom4j.Attribute;
/*  11:    */import org.dom4j.Document;
/*  12:    */import org.dom4j.DocumentFactory;
/*  13:    */import org.dom4j.Element;
/*  14:    */import org.dom4j.Namespace;
/*  15:    */import org.dom4j.QName;
/*  16:    */import org.dom4j.io.SAXReader;
/*  17:    */import org.dom4j.util.AttributeHelper;
/*  18:    */import org.relaxng.datatype.DatatypeException;
/*  19:    */import org.relaxng.datatype.ValidationContext;
/*  20:    */import org.xml.sax.EntityResolver;
/*  21:    */import org.xml.sax.InputSource;
/*  22:    */
/*  41:    */public class SchemaParser
/*  42:    */{
/*  43: 43 */  private static final Namespace XSD_NAMESPACE = Namespace.get("xsd", "http://www.w3.org/2001/XMLSchema");
/*  44:    */  
/*  47: 47 */  private static final QName XSD_ELEMENT = QName.get("element", XSD_NAMESPACE);
/*  48:    */  
/*  50: 50 */  private static final QName XSD_ATTRIBUTE = QName.get("attribute", XSD_NAMESPACE);
/*  51:    */  
/*  53: 53 */  private static final QName XSD_SIMPLETYPE = QName.get("simpleType", XSD_NAMESPACE);
/*  54:    */  
/*  56: 56 */  private static final QName XSD_COMPLEXTYPE = QName.get("complexType", XSD_NAMESPACE);
/*  57:    */  
/*  59: 59 */  private static final QName XSD_RESTRICTION = QName.get("restriction", XSD_NAMESPACE);
/*  60:    */  
/*  62: 62 */  private static final QName XSD_SEQUENCE = QName.get("sequence", XSD_NAMESPACE);
/*  63:    */  
/*  65: 65 */  private static final QName XSD_CHOICE = QName.get("choice", XSD_NAMESPACE);
/*  66:    */  
/*  67: 67 */  private static final QName XSD_ALL = QName.get("all", XSD_NAMESPACE);
/*  68:    */  
/*  69: 69 */  private static final QName XSD_INCLUDE = QName.get("include", XSD_NAMESPACE);
/*  70:    */  
/*  74:    */  private DatatypeDocumentFactory documentFactory;
/*  75:    */  
/*  79: 79 */  private Map dataTypeCache = new HashMap();
/*  80:    */  
/*  81:    */  private NamedTypeResolver namedTypeResolver;
/*  82:    */  
/*  83:    */  private Namespace targetNamespace;
/*  84:    */  
/*  86:    */  public SchemaParser()
/*  87:    */  {
/*  88: 88 */    this(DatatypeDocumentFactory.singleton);
/*  89:    */  }
/*  90:    */  
/*  91:    */  public SchemaParser(DatatypeDocumentFactory documentFactory) {
/*  92: 92 */    this.documentFactory = documentFactory;
/*  93: 93 */    this.namedTypeResolver = new NamedTypeResolver(documentFactory);
/*  94:    */  }
/*  95:    */  
/* 101:    */  public void build(Document schemaDocument)
/* 102:    */  {
/* 103:103 */    this.targetNamespace = null;
/* 104:104 */    internalBuild(schemaDocument);
/* 105:    */  }
/* 106:    */  
/* 107:    */  public void build(Document schemaDocument, Namespace namespace) {
/* 108:108 */    this.targetNamespace = namespace;
/* 109:109 */    internalBuild(schemaDocument);
/* 110:    */  }
/* 111:    */  
/* 112:    */  private synchronized void internalBuild(Document schemaDocument) {
/* 113:113 */    Element root = schemaDocument.getRootElement();
/* 114:    */    
/* 115:115 */    if (root != null)
/* 116:    */    {
/* 117:117 */      Iterator includeIter = root.elementIterator(XSD_INCLUDE);
/* 118:    */      
/* 119:119 */      while (includeIter.hasNext()) {
/* 120:120 */        Element includeElement = (Element)includeIter.next();
/* 121:121 */        String inclSchemaInstanceURI = includeElement.attributeValue("schemaLocation");
/* 122:    */        
/* 123:123 */        EntityResolver resolver = schemaDocument.getEntityResolver();
/* 124:    */        try
/* 125:    */        {
/* 126:126 */          if (resolver == null) {
/* 127:127 */            String msg = "No EntityResolver available";
/* 128:128 */            throw new InvalidSchemaException(msg);
/* 129:    */          }
/* 130:    */          
/* 131:131 */          InputSource inputSource = resolver.resolveEntity(null, inclSchemaInstanceURI);
/* 132:    */          
/* 134:134 */          if (inputSource == null) {
/* 135:135 */            String msg = "Could not resolve the schema URI: " + inclSchemaInstanceURI;
/* 136:    */            
/* 137:137 */            throw new InvalidSchemaException(msg);
/* 138:    */          }
/* 139:    */          
/* 140:140 */          SAXReader reader = new SAXReader();
/* 141:141 */          Document inclSchemaDocument = reader.read(inputSource);
/* 142:142 */          build(inclSchemaDocument);
/* 143:    */        } catch (Exception e) {
/* 144:144 */          System.out.println("Failed to load schema: " + inclSchemaInstanceURI);
/* 145:    */          
/* 146:146 */          System.out.println("Caught: " + e);
/* 147:147 */          e.printStackTrace();
/* 148:148 */          throw new InvalidSchemaException("Failed to load schema: " + inclSchemaInstanceURI);
/* 149:    */        }
/* 150:    */      }
/* 151:    */      
/* 154:154 */      Iterator iter = root.elementIterator(XSD_ELEMENT);
/* 155:    */      
/* 156:156 */      while (iter.hasNext()) {
/* 157:157 */        onDatatypeElement((Element)iter.next(), this.documentFactory);
/* 158:    */      }
/* 159:    */      
/* 161:161 */      iter = root.elementIterator(XSD_SIMPLETYPE);
/* 162:    */      
/* 163:163 */      while (iter.hasNext()) {
/* 164:164 */        onNamedSchemaSimpleType((Element)iter.next());
/* 165:    */      }
/* 166:    */      
/* 168:168 */      iter = root.elementIterator(XSD_COMPLEXTYPE);
/* 169:    */      
/* 170:170 */      while (iter.hasNext()) {
/* 171:171 */        onNamedSchemaComplexType((Element)iter.next());
/* 172:    */      }
/* 173:    */      
/* 174:174 */      this.namedTypeResolver.resolveNamedTypes();
/* 175:    */    }
/* 176:    */  }
/* 177:    */  
/* 189:    */  private void onDatatypeElement(Element xsdElement, DocumentFactory parentFactory)
/* 190:    */  {
/* 191:191 */    String name = xsdElement.attributeValue("name");
/* 192:192 */    String type = xsdElement.attributeValue("type");
/* 193:193 */    QName qname = getQName(name);
/* 194:    */    
/* 195:195 */    DatatypeElementFactory factory = getDatatypeElementFactory(qname);
/* 196:    */    
/* 197:197 */    if (type != null)
/* 198:    */    {
/* 199:199 */      XSDatatype dataType = getTypeByName(type);
/* 200:    */      
/* 201:201 */      if (dataType != null) {
/* 202:202 */        factory.setChildElementXSDatatype(qname, dataType);
/* 203:    */      } else {
/* 204:204 */        QName typeQName = getQName(type);
/* 205:205 */        this.namedTypeResolver.registerTypedElement(xsdElement, typeQName, parentFactory);
/* 206:    */      }
/* 207:    */      
/* 209:209 */      return;
/* 210:    */    }
/* 211:    */    
/* 213:213 */    Element xsdSimpleType = xsdElement.element(XSD_SIMPLETYPE);
/* 214:    */    
/* 215:215 */    if (xsdSimpleType != null) {
/* 216:216 */      XSDatatype dataType = loadXSDatatypeFromSimpleType(xsdSimpleType);
/* 217:    */      
/* 218:218 */      if (dataType != null) {
/* 219:219 */        factory.setChildElementXSDatatype(qname, dataType);
/* 220:    */      }
/* 221:    */    }
/* 222:    */    
/* 223:223 */    Element schemaComplexType = xsdElement.element(XSD_COMPLEXTYPE);
/* 224:    */    
/* 225:225 */    if (schemaComplexType != null) {
/* 226:226 */      onSchemaComplexType(schemaComplexType, factory);
/* 227:    */    }
/* 228:    */    
/* 229:229 */    Iterator iter = xsdElement.elementIterator(XSD_ATTRIBUTE);
/* 230:    */    
/* 231:231 */    if (iter.hasNext()) {
/* 232:    */      do {
/* 233:233 */        onDatatypeAttribute(xsdElement, factory, (Element)iter.next());
/* 234:    */      }
/* 235:235 */      while (iter.hasNext());
/* 236:    */    }
/* 237:    */  }
/* 238:    */  
/* 244:    */  private void onNamedSchemaComplexType(Element schemaComplexType)
/* 245:    */  {
/* 246:246 */    Attribute nameAttr = schemaComplexType.attribute("name");
/* 247:    */    
/* 248:248 */    if (nameAttr == null) {
/* 249:249 */      return;
/* 250:    */    }
/* 251:    */    
/* 252:252 */    String name = nameAttr.getText();
/* 253:253 */    QName qname = getQName(name);
/* 254:    */    
/* 255:255 */    DatatypeElementFactory factory = getDatatypeElementFactory(qname);
/* 256:    */    
/* 257:257 */    onSchemaComplexType(schemaComplexType, factory);
/* 258:258 */    this.namedTypeResolver.registerComplexType(qname, factory);
/* 259:    */  }
/* 260:    */  
/* 269:    */  private void onSchemaComplexType(Element schemaComplexType, DatatypeElementFactory elementFactory)
/* 270:    */  {
/* 271:271 */    Iterator iter = schemaComplexType.elementIterator(XSD_ATTRIBUTE);
/* 272:    */    
/* 273:273 */    while (iter.hasNext()) {
/* 274:274 */      Element xsdAttribute = (Element)iter.next();
/* 275:275 */      String name = xsdAttribute.attributeValue("name");
/* 276:276 */      QName qname = getQName(name);
/* 277:    */      
/* 278:278 */      XSDatatype dataType = dataTypeForXsdAttribute(xsdAttribute);
/* 279:    */      
/* 280:280 */      if (dataType != null)
/* 281:    */      {
/* 284:284 */        elementFactory.setAttributeXSDatatype(qname, dataType);
/* 285:    */      }
/* 286:    */    }
/* 287:    */    
/* 289:289 */    Element schemaSequence = schemaComplexType.element(XSD_SEQUENCE);
/* 290:    */    
/* 291:291 */    if (schemaSequence != null) {
/* 292:292 */      onChildElements(schemaSequence, elementFactory);
/* 293:    */    }
/* 294:    */    
/* 296:296 */    Element schemaChoice = schemaComplexType.element(XSD_CHOICE);
/* 297:    */    
/* 298:298 */    if (schemaChoice != null) {
/* 299:299 */      onChildElements(schemaChoice, elementFactory);
/* 300:    */    }
/* 301:    */    
/* 303:303 */    Element schemaAll = schemaComplexType.element(XSD_ALL);
/* 304:    */    
/* 305:305 */    if (schemaAll != null) {
/* 306:306 */      onChildElements(schemaAll, elementFactory);
/* 307:    */    }
/* 308:    */  }
/* 309:    */  
/* 310:    */  private void onChildElements(Element element, DatatypeElementFactory fact) {
/* 311:311 */    Iterator iter = element.elementIterator(XSD_ELEMENT);
/* 312:    */    
/* 313:313 */    while (iter.hasNext()) {
/* 314:314 */      Element xsdElement = (Element)iter.next();
/* 315:315 */      onDatatypeElement(xsdElement, fact);
/* 316:    */    }
/* 317:    */  }
/* 318:    */  
/* 329:    */  private void onDatatypeAttribute(Element xsdElement, DatatypeElementFactory elementFactory, Element xsdAttribute)
/* 330:    */  {
/* 331:331 */    String name = xsdAttribute.attributeValue("name");
/* 332:332 */    QName qname = getQName(name);
/* 333:333 */    XSDatatype dataType = dataTypeForXsdAttribute(xsdAttribute);
/* 334:    */    
/* 335:335 */    if (dataType != null)
/* 336:    */    {
/* 337:337 */      elementFactory.setAttributeXSDatatype(qname, dataType);
/* 338:    */    } else {
/* 339:339 */      String type = xsdAttribute.attributeValue("type");
/* 340:340 */      System.out.println("Warning: Couldn't find XSDatatype for type: " + type + " attribute: " + name);
/* 341:    */    }
/* 342:    */  }
/* 343:    */  
/* 355:    */  private XSDatatype dataTypeForXsdAttribute(Element xsdAttribute)
/* 356:    */  {
/* 357:357 */    String type = xsdAttribute.attributeValue("type");
/* 358:358 */    XSDatatype dataType = null;
/* 359:    */    
/* 360:360 */    if (type != null) {
/* 361:361 */      dataType = getTypeByName(type);
/* 362:    */    }
/* 363:    */    else {
/* 364:364 */      Element xsdSimpleType = xsdAttribute.element(XSD_SIMPLETYPE);
/* 365:    */      
/* 366:366 */      if (xsdSimpleType == null) {
/* 367:367 */        String name = xsdAttribute.attributeValue("name");
/* 368:368 */        String msg = "The attribute: " + name + " has no type attribute and does not contain a " + "<simpleType/> element";
/* 369:    */        
/* 371:371 */        throw new InvalidSchemaException(msg);
/* 372:    */      }
/* 373:    */      
/* 374:374 */      dataType = loadXSDatatypeFromSimpleType(xsdSimpleType);
/* 375:    */    }
/* 376:    */    
/* 377:377 */    return dataType;
/* 378:    */  }
/* 379:    */  
/* 385:    */  private void onNamedSchemaSimpleType(Element schemaSimpleType)
/* 386:    */  {
/* 387:387 */    Attribute nameAttr = schemaSimpleType.attribute("name");
/* 388:    */    
/* 389:389 */    if (nameAttr == null) {
/* 390:390 */      return;
/* 391:    */    }
/* 392:    */    
/* 393:393 */    String name = nameAttr.getText();
/* 394:394 */    QName qname = getQName(name);
/* 395:395 */    XSDatatype datatype = loadXSDatatypeFromSimpleType(schemaSimpleType);
/* 396:396 */    this.namedTypeResolver.registerSimpleType(qname, datatype);
/* 397:    */  }
/* 398:    */  
/* 407:    */  private XSDatatype loadXSDatatypeFromSimpleType(Element xsdSimpleType)
/* 408:    */  {
/* 409:409 */    Element xsdRestriction = xsdSimpleType.element(XSD_RESTRICTION);
/* 410:    */    
/* 411:411 */    if (xsdRestriction != null) {
/* 412:412 */      String base = xsdRestriction.attributeValue("base");
/* 413:    */      
/* 414:414 */      if (base != null) {
/* 415:415 */        XSDatatype baseType = getTypeByName(base);
/* 416:    */        
/* 417:417 */        if (baseType == null) {
/* 418:418 */          onSchemaError("Invalid base type: " + base + " when trying to build restriction: " + xsdRestriction);
/* 419:    */        }
/* 420:    */        else
/* 421:    */        {
/* 422:422 */          return deriveSimpleType(baseType, xsdRestriction);
/* 423:    */        }
/* 424:    */      }
/* 425:    */      else
/* 426:    */      {
/* 427:427 */        Element xsdSubType = xsdSimpleType.element(XSD_SIMPLETYPE);
/* 428:    */        
/* 429:429 */        if (xsdSubType == null) {
/* 430:430 */          String msg = "The simpleType element: " + xsdSimpleType + " must contain a base attribute or simpleType" + " element";
/* 431:    */          
/* 433:433 */          onSchemaError(msg);
/* 434:    */        } else {
/* 435:435 */          return loadXSDatatypeFromSimpleType(xsdSubType);
/* 436:    */        }
/* 437:    */      }
/* 438:    */    } else {
/* 439:439 */      onSchemaError("No <restriction>. Could not create XSDatatype for simpleType: " + xsdSimpleType);
/* 440:    */    }
/* 441:    */    
/* 443:443 */    return null;
/* 444:    */  }
/* 445:    */  
/* 456:    */  private XSDatatype deriveSimpleType(XSDatatype baseType, Element xsdRestriction)
/* 457:    */  {
/* 458:458 */    TypeIncubator incubator = new TypeIncubator(baseType);
/* 459:459 */    ValidationContext context = null;
/* 460:    */    try
/* 461:    */    {
/* 462:462 */      Iterator iter = xsdRestriction.elementIterator();
/* 463:463 */      while (iter.hasNext()) {
/* 464:464 */        Element element = (Element)iter.next();
/* 465:465 */        String name = element.getName();
/* 466:466 */        String value = element.attributeValue("value");
/* 467:467 */        boolean fixed = AttributeHelper.booleanValue(element, "fixed");
/* 468:    */        
/* 470:470 */        incubator.addFacet(name, value, fixed, context);
/* 471:    */      }
/* 472:    */      
/* 474:474 */      String newTypeName = null;
/* 475:    */      
/* 476:476 */      return incubator.derive("", newTypeName);
/* 477:    */    } catch (DatatypeException e) {
/* 478:478 */      onSchemaError("Invalid restriction: " + e.getMessage() + " when trying to build restriction: " + xsdRestriction);
/* 479:    */    }
/* 480:    */    
/* 481:481 */    return null;
/* 482:    */  }
/* 483:    */  
/* 493:    */  private DatatypeElementFactory getDatatypeElementFactory(QName name)
/* 494:    */  {
/* 495:495 */    DatatypeElementFactory factory = this.documentFactory.getElementFactory(name);
/* 496:    */    
/* 498:498 */    if (factory == null) {
/* 499:499 */      factory = new DatatypeElementFactory(name);
/* 500:500 */      name.setDocumentFactory(factory);
/* 501:    */    }
/* 502:    */    
/* 503:503 */    return factory;
/* 504:    */  }
/* 505:    */  
/* 506:    */  private XSDatatype getTypeByName(String type) {
/* 507:507 */    XSDatatype dataType = (XSDatatype)this.dataTypeCache.get(type);
/* 508:    */    
/* 509:509 */    if (dataType == null)
/* 510:    */    {
/* 512:512 */      int idx = type.indexOf(':');
/* 513:    */      
/* 514:514 */      if (idx >= 0) {
/* 515:515 */        String localName = type.substring(idx + 1);
/* 516:    */        try
/* 517:    */        {
/* 518:518 */          dataType = DatatypeFactory.getTypeByName(localName);
/* 519:    */        }
/* 520:    */        catch (DatatypeException e) {}
/* 521:    */      }
/* 522:    */      
/* 523:523 */      if (dataType == null) {
/* 524:    */        try {
/* 525:525 */          dataType = DatatypeFactory.getTypeByName(type);
/* 526:    */        }
/* 527:    */        catch (DatatypeException e) {}
/* 528:    */      }
/* 529:    */      
/* 530:530 */      if (dataType == null)
/* 531:    */      {
/* 532:532 */        QName typeQName = getQName(type);
/* 533:533 */        dataType = (XSDatatype)this.namedTypeResolver.simpleTypeMap.get(typeQName);
/* 534:    */      }
/* 535:    */      
/* 537:537 */      if (dataType != null)
/* 538:    */      {
/* 539:539 */        this.dataTypeCache.put(type, dataType);
/* 540:    */      }
/* 541:    */    }
/* 542:    */    
/* 543:543 */    return dataType;
/* 544:    */  }
/* 545:    */  
/* 546:    */  private QName getQName(String name) {
/* 547:547 */    if (this.targetNamespace == null) {
/* 548:548 */      return this.documentFactory.createQName(name);
/* 549:    */    }
/* 550:550 */    return this.documentFactory.createQName(name, this.targetNamespace);
/* 551:    */  }
/* 552:    */  
/* 566:    */  private void onSchemaError(String message)
/* 567:    */  {
/* 568:568 */    throw new InvalidSchemaException(message);
/* 569:    */  }
/* 570:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.datatype.SchemaParser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */