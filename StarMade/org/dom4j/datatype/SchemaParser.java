package org.dom4j.datatype;

import com.sun.msv.datatype.xsd.DatatypeFactory;
import com.sun.msv.datatype.xsd.TypeIncubator;
import com.sun.msv.datatype.xsd.XSDatatype;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;
import org.dom4j.util.AttributeHelper;
import org.relaxng.datatype.DatatypeException;
import org.relaxng.datatype.ValidationContext;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

public class SchemaParser
{
  private static final Namespace XSD_NAMESPACE = Namespace.get("xsd", "http://www.w3.org/2001/XMLSchema");
  private static final QName XSD_ELEMENT = QName.get("element", XSD_NAMESPACE);
  private static final QName XSD_ATTRIBUTE = QName.get("attribute", XSD_NAMESPACE);
  private static final QName XSD_SIMPLETYPE = QName.get("simpleType", XSD_NAMESPACE);
  private static final QName XSD_COMPLEXTYPE = QName.get("complexType", XSD_NAMESPACE);
  private static final QName XSD_RESTRICTION = QName.get("restriction", XSD_NAMESPACE);
  private static final QName XSD_SEQUENCE = QName.get("sequence", XSD_NAMESPACE);
  private static final QName XSD_CHOICE = QName.get("choice", XSD_NAMESPACE);
  private static final QName XSD_ALL = QName.get("all", XSD_NAMESPACE);
  private static final QName XSD_INCLUDE = QName.get("include", XSD_NAMESPACE);
  private DatatypeDocumentFactory documentFactory;
  private Map dataTypeCache = new HashMap();
  private NamedTypeResolver namedTypeResolver;
  private Namespace targetNamespace;
  
  public SchemaParser()
  {
    this(DatatypeDocumentFactory.singleton);
  }
  
  public SchemaParser(DatatypeDocumentFactory documentFactory)
  {
    this.documentFactory = documentFactory;
    this.namedTypeResolver = new NamedTypeResolver(documentFactory);
  }
  
  public void build(Document schemaDocument)
  {
    this.targetNamespace = null;
    internalBuild(schemaDocument);
  }
  
  public void build(Document schemaDocument, Namespace namespace)
  {
    this.targetNamespace = namespace;
    internalBuild(schemaDocument);
  }
  
  private synchronized void internalBuild(Document schemaDocument)
  {
    Element root = schemaDocument.getRootElement();
    if (root != null)
    {
      Iterator includeIter = root.elementIterator(XSD_INCLUDE);
      while (includeIter.hasNext())
      {
        Element includeElement = (Element)includeIter.next();
        String inclSchemaInstanceURI = includeElement.attributeValue("schemaLocation");
        EntityResolver resolver = schemaDocument.getEntityResolver();
        try
        {
          if (resolver == null)
          {
            String msg = "No EntityResolver available";
            throw new InvalidSchemaException(msg);
          }
          InputSource msg = resolver.resolveEntity(null, inclSchemaInstanceURI);
          if (msg == null)
          {
            String msg = "Could not resolve the schema URI: " + inclSchemaInstanceURI;
            throw new InvalidSchemaException(msg);
          }
          SAXReader msg = new SAXReader();
          Document inclSchemaDocument = msg.read(msg);
          build(inclSchemaDocument);
        }
        catch (Exception msg)
        {
          System.out.println("Failed to load schema: " + inclSchemaInstanceURI);
          System.out.println("Caught: " + msg);
          msg.printStackTrace();
          throw new InvalidSchemaException("Failed to load schema: " + inclSchemaInstanceURI);
        }
      }
      Iterator includeElement = root.elementIterator(XSD_ELEMENT);
      while (includeElement.hasNext()) {
        onDatatypeElement((Element)includeElement.next(), this.documentFactory);
      }
      includeElement = root.elementIterator(XSD_SIMPLETYPE);
      while (includeElement.hasNext()) {
        onNamedSchemaSimpleType((Element)includeElement.next());
      }
      includeElement = root.elementIterator(XSD_COMPLEXTYPE);
      while (includeElement.hasNext()) {
        onNamedSchemaComplexType((Element)includeElement.next());
      }
      this.namedTypeResolver.resolveNamedTypes();
    }
  }
  
  private void onDatatypeElement(Element xsdElement, DocumentFactory parentFactory)
  {
    String name = xsdElement.attributeValue("name");
    String type = xsdElement.attributeValue("type");
    QName qname = getQName(name);
    DatatypeElementFactory factory = getDatatypeElementFactory(qname);
    if (type != null)
    {
      XSDatatype dataType = getTypeByName(type);
      if (dataType != null)
      {
        factory.setChildElementXSDatatype(qname, dataType);
      }
      else
      {
        QName typeQName = getQName(type);
        this.namedTypeResolver.registerTypedElement(xsdElement, typeQName, parentFactory);
      }
      return;
    }
    Element dataType = xsdElement.element(XSD_SIMPLETYPE);
    if (dataType != null)
    {
      XSDatatype typeQName = loadXSDatatypeFromSimpleType(dataType);
      if (typeQName != null) {
        factory.setChildElementXSDatatype(qname, typeQName);
      }
    }
    Element typeQName = xsdElement.element(XSD_COMPLEXTYPE);
    if (typeQName != null) {
      onSchemaComplexType(typeQName, factory);
    }
    Iterator iter = xsdElement.elementIterator(XSD_ATTRIBUTE);
    if (iter.hasNext()) {
      do
      {
        onDatatypeAttribute(xsdElement, factory, (Element)iter.next());
      } while (iter.hasNext());
    }
  }
  
  private void onNamedSchemaComplexType(Element schemaComplexType)
  {
    Attribute nameAttr = schemaComplexType.attribute("name");
    if (nameAttr == null) {
      return;
    }
    String name = nameAttr.getText();
    QName qname = getQName(name);
    DatatypeElementFactory factory = getDatatypeElementFactory(qname);
    onSchemaComplexType(schemaComplexType, factory);
    this.namedTypeResolver.registerComplexType(qname, factory);
  }
  
  private void onSchemaComplexType(Element schemaComplexType, DatatypeElementFactory elementFactory)
  {
    Iterator iter = schemaComplexType.elementIterator(XSD_ATTRIBUTE);
    while (iter.hasNext())
    {
      Element xsdAttribute = (Element)iter.next();
      String name = xsdAttribute.attributeValue("name");
      QName qname = getQName(name);
      XSDatatype dataType = dataTypeForXsdAttribute(xsdAttribute);
      if (dataType != null) {
        elementFactory.setAttributeXSDatatype(qname, dataType);
      }
    }
    Element xsdAttribute = schemaComplexType.element(XSD_SEQUENCE);
    if (xsdAttribute != null) {
      onChildElements(xsdAttribute, elementFactory);
    }
    Element name = schemaComplexType.element(XSD_CHOICE);
    if (name != null) {
      onChildElements(name, elementFactory);
    }
    Element qname = schemaComplexType.element(XSD_ALL);
    if (qname != null) {
      onChildElements(qname, elementFactory);
    }
  }
  
  private void onChildElements(Element element, DatatypeElementFactory fact)
  {
    Iterator iter = element.elementIterator(XSD_ELEMENT);
    while (iter.hasNext())
    {
      Element xsdElement = (Element)iter.next();
      onDatatypeElement(xsdElement, fact);
    }
  }
  
  private void onDatatypeAttribute(Element xsdElement, DatatypeElementFactory elementFactory, Element xsdAttribute)
  {
    String name = xsdAttribute.attributeValue("name");
    QName qname = getQName(name);
    XSDatatype dataType = dataTypeForXsdAttribute(xsdAttribute);
    if (dataType != null)
    {
      elementFactory.setAttributeXSDatatype(qname, dataType);
    }
    else
    {
      String type = xsdAttribute.attributeValue("type");
      System.out.println("Warning: Couldn't find XSDatatype for type: " + type + " attribute: " + name);
    }
  }
  
  private XSDatatype dataTypeForXsdAttribute(Element xsdAttribute)
  {
    String type = xsdAttribute.attributeValue("type");
    XSDatatype dataType = null;
    if (type != null)
    {
      dataType = getTypeByName(type);
    }
    else
    {
      Element xsdSimpleType = xsdAttribute.element(XSD_SIMPLETYPE);
      if (xsdSimpleType == null)
      {
        String name = xsdAttribute.attributeValue("name");
        String msg = "The attribute: " + name + " has no type attribute and does not contain a " + "<simpleType/> element";
        throw new InvalidSchemaException(msg);
      }
      dataType = loadXSDatatypeFromSimpleType(xsdSimpleType);
    }
    return dataType;
  }
  
  private void onNamedSchemaSimpleType(Element schemaSimpleType)
  {
    Attribute nameAttr = schemaSimpleType.attribute("name");
    if (nameAttr == null) {
      return;
    }
    String name = nameAttr.getText();
    QName qname = getQName(name);
    XSDatatype datatype = loadXSDatatypeFromSimpleType(schemaSimpleType);
    this.namedTypeResolver.registerSimpleType(qname, datatype);
  }
  
  private XSDatatype loadXSDatatypeFromSimpleType(Element xsdSimpleType)
  {
    Element xsdRestriction = xsdSimpleType.element(XSD_RESTRICTION);
    if (xsdRestriction != null)
    {
      String base = xsdRestriction.attributeValue("base");
      if (base != null)
      {
        XSDatatype baseType = getTypeByName(base);
        if (baseType == null) {
          onSchemaError("Invalid base type: " + base + " when trying to build restriction: " + xsdRestriction);
        } else {
          return deriveSimpleType(baseType, xsdRestriction);
        }
      }
      else
      {
        Element baseType = xsdSimpleType.element(XSD_SIMPLETYPE);
        if (baseType == null)
        {
          String msg = "The simpleType element: " + xsdSimpleType + " must contain a base attribute or simpleType" + " element";
          onSchemaError(msg);
        }
        else
        {
          return loadXSDatatypeFromSimpleType(baseType);
        }
      }
    }
    else
    {
      onSchemaError("No <restriction>. Could not create XSDatatype for simpleType: " + xsdSimpleType);
    }
    return null;
  }
  
  private XSDatatype deriveSimpleType(XSDatatype baseType, Element xsdRestriction)
  {
    TypeIncubator incubator = new TypeIncubator(baseType);
    ValidationContext context = null;
    try
    {
      Iterator iter = xsdRestriction.elementIterator();
      while (iter.hasNext())
      {
        Element element = (Element)iter.next();
        String name = element.getName();
        String value = element.attributeValue("value");
        boolean fixed = AttributeHelper.booleanValue(element, "fixed");
        incubator.addFacet(name, value, fixed, context);
      }
      String iter = null;
      return incubator.derive("", iter);
    }
    catch (DatatypeException iter)
    {
      onSchemaError("Invalid restriction: " + iter.getMessage() + " when trying to build restriction: " + xsdRestriction);
    }
    return null;
  }
  
  private DatatypeElementFactory getDatatypeElementFactory(QName name)
  {
    DatatypeElementFactory factory = this.documentFactory.getElementFactory(name);
    if (factory == null)
    {
      factory = new DatatypeElementFactory(name);
      name.setDocumentFactory(factory);
    }
    return factory;
  }
  
  private XSDatatype getTypeByName(String type)
  {
    XSDatatype dataType = (XSDatatype)this.dataTypeCache.get(type);
    if (dataType == null)
    {
      int idx = type.indexOf(':');
      if (idx >= 0)
      {
        String localName = type.substring(idx + 1);
        try
        {
          dataType = DatatypeFactory.getTypeByName(localName);
        }
        catch (DatatypeException local_e) {}
      }
      if (dataType == null) {
        try
        {
          dataType = DatatypeFactory.getTypeByName(type);
        }
        catch (DatatypeException localName) {}
      }
      if (dataType == null)
      {
        QName localName = getQName(type);
        dataType = (XSDatatype)this.namedTypeResolver.simpleTypeMap.get(localName);
      }
      if (dataType != null) {
        this.dataTypeCache.put(type, dataType);
      }
    }
    return dataType;
  }
  
  private QName getQName(String name)
  {
    if (this.targetNamespace == null) {
      return this.documentFactory.createQName(name);
    }
    return this.documentFactory.createQName(name, this.targetNamespace);
  }
  
  private void onSchemaError(String message)
  {
    throw new InvalidSchemaException(message);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.datatype.SchemaParser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */