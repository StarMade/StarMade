/*  1:   */package org.dom4j.datatype;
/*  2:   */
/*  3:   */import com.sun.msv.datatype.xsd.XSDatatype;
/*  4:   */import java.util.HashMap;
/*  5:   */import java.util.Iterator;
/*  6:   */import java.util.Map;
/*  7:   */import java.util.Set;
/*  8:   */import org.dom4j.DocumentFactory;
/*  9:   */import org.dom4j.Element;
/* 10:   */import org.dom4j.QName;
/* 11:   */
/* 27:   */class NamedTypeResolver
/* 28:   */{
/* 29:29 */  protected Map complexTypeMap = new HashMap();
/* 30:   */  
/* 31:31 */  protected Map simpleTypeMap = new HashMap();
/* 32:   */  
/* 33:33 */  protected Map typedElementMap = new HashMap();
/* 34:   */  
/* 35:35 */  protected Map elementFactoryMap = new HashMap();
/* 36:   */  protected DocumentFactory documentFactory;
/* 37:   */  
/* 38:   */  NamedTypeResolver(DocumentFactory documentFactory)
/* 39:   */  {
/* 40:40 */    this.documentFactory = documentFactory;
/* 41:   */  }
/* 42:   */  
/* 43:   */  void registerComplexType(QName type, DocumentFactory factory) {
/* 44:44 */    this.complexTypeMap.put(type, factory);
/* 45:   */  }
/* 46:   */  
/* 47:   */  void registerSimpleType(QName type, XSDatatype datatype) {
/* 48:48 */    this.simpleTypeMap.put(type, datatype);
/* 49:   */  }
/* 50:   */  
/* 51:   */  void registerTypedElement(Element element, QName type, DocumentFactory parentFactory)
/* 52:   */  {
/* 53:53 */    this.typedElementMap.put(element, type);
/* 54:54 */    this.elementFactoryMap.put(element, parentFactory);
/* 55:   */  }
/* 56:   */  
/* 57:   */  void resolveElementTypes() {
/* 58:58 */    Iterator iterator = this.typedElementMap.keySet().iterator();
/* 59:   */    
/* 60:60 */    while (iterator.hasNext()) {
/* 61:61 */      Element element = (Element)iterator.next();
/* 62:62 */      QName elementQName = getQNameOfSchemaElement(element);
/* 63:63 */      QName type = (QName)this.typedElementMap.get(element);
/* 64:   */      
/* 65:65 */      if (this.complexTypeMap.containsKey(type)) {
/* 66:66 */        DocumentFactory factory = (DocumentFactory)this.complexTypeMap.get(type);
/* 67:   */        
/* 68:68 */        elementQName.setDocumentFactory(factory);
/* 69:69 */      } else if (this.simpleTypeMap.containsKey(type)) {
/* 70:70 */        XSDatatype datatype = (XSDatatype)this.simpleTypeMap.get(type);
/* 71:71 */        DocumentFactory factory = (DocumentFactory)this.elementFactoryMap.get(element);
/* 72:   */        
/* 74:74 */        if ((factory instanceof DatatypeElementFactory)) {
/* 75:75 */          ((DatatypeElementFactory)factory).setChildElementXSDatatype(elementQName, datatype);
/* 76:   */        }
/* 77:   */      }
/* 78:   */    }
/* 79:   */  }
/* 80:   */  
/* 81:   */  void resolveNamedTypes()
/* 82:   */  {
/* 83:83 */    resolveElementTypes();
/* 84:   */  }
/* 85:   */  
/* 86:   */  private QName getQNameOfSchemaElement(Element element) {
/* 87:87 */    String name = element.attributeValue("name");
/* 88:   */    
/* 89:89 */    return getQName(name);
/* 90:   */  }
/* 91:   */  
/* 92:   */  private QName getQName(String name) {
/* 93:93 */    return this.documentFactory.createQName(name);
/* 94:   */  }
/* 95:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.datatype.NamedTypeResolver
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */