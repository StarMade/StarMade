/*   1:    */package org.dom4j.datatype;
/*   2:    */
/*   3:    */import com.sun.msv.datatype.xsd.XSDatatype;
/*   4:    */import java.util.HashMap;
/*   5:    */import java.util.Map;
/*   6:    */import org.dom4j.Attribute;
/*   7:    */import org.dom4j.DocumentFactory;
/*   8:    */import org.dom4j.Element;
/*   9:    */import org.dom4j.QName;
/*  10:    */
/*  33:    */public class DatatypeElementFactory
/*  34:    */  extends DocumentFactory
/*  35:    */{
/*  36:    */  private QName elementQName;
/*  37: 37 */  private Map attributeXSDatatypes = new HashMap();
/*  38:    */  
/*  43: 43 */  private Map childrenXSDatatypes = new HashMap();
/*  44:    */  
/*  45:    */  public DatatypeElementFactory(QName elementQName) {
/*  46: 46 */    this.elementQName = elementQName;
/*  47:    */  }
/*  48:    */  
/*  53:    */  public QName getQName()
/*  54:    */  {
/*  55: 55 */    return this.elementQName;
/*  56:    */  }
/*  57:    */  
/*  66:    */  public XSDatatype getAttributeXSDatatype(QName attributeQName)
/*  67:    */  {
/*  68: 68 */    return (XSDatatype)this.attributeXSDatatypes.get(attributeQName);
/*  69:    */  }
/*  70:    */  
/*  79:    */  public void setAttributeXSDatatype(QName attributeQName, XSDatatype type)
/*  80:    */  {
/*  81: 81 */    this.attributeXSDatatypes.put(attributeQName, type);
/*  82:    */  }
/*  83:    */  
/*  92:    */  public XSDatatype getChildElementXSDatatype(QName qname)
/*  93:    */  {
/*  94: 94 */    return (XSDatatype)this.childrenXSDatatypes.get(qname);
/*  95:    */  }
/*  96:    */  
/*  97:    */  public void setChildElementXSDatatype(QName qname, XSDatatype dataType) {
/*  98: 98 */    this.childrenXSDatatypes.put(qname, dataType);
/*  99:    */  }
/* 100:    */  
/* 104:    */  public Element createElement(QName qname)
/* 105:    */  {
/* 106:106 */    XSDatatype dataType = getChildElementXSDatatype(qname);
/* 107:    */    
/* 108:108 */    if (dataType != null) {
/* 109:109 */      return new DatatypeElement(qname, dataType);
/* 110:    */    }
/* 111:    */    
/* 112:112 */    DocumentFactory factory = qname.getDocumentFactory();
/* 113:    */    
/* 114:114 */    if ((factory instanceof DatatypeElementFactory)) {
/* 115:115 */      DatatypeElementFactory dtFactory = (DatatypeElementFactory)factory;
/* 116:116 */      dataType = dtFactory.getChildElementXSDatatype(qname);
/* 117:    */      
/* 118:118 */      if (dataType != null) {
/* 119:119 */        return new DatatypeElement(qname, dataType);
/* 120:    */      }
/* 121:    */    }
/* 122:    */    
/* 123:123 */    return super.createElement(qname);
/* 124:    */  }
/* 125:    */  
/* 126:    */  public Attribute createAttribute(Element owner, QName qname, String value) {
/* 127:127 */    XSDatatype dataType = getAttributeXSDatatype(qname);
/* 128:    */    
/* 129:129 */    if (dataType == null) {
/* 130:130 */      return super.createAttribute(owner, qname, value);
/* 131:    */    }
/* 132:132 */    return new DatatypeAttribute(qname, dataType, value);
/* 133:    */  }
/* 134:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.datatype.DatatypeElementFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */