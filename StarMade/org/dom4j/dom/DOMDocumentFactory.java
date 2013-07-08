/*   1:    */package org.dom4j.dom;
/*   2:    */
/*   3:    */import java.util.Map;
/*   4:    */import org.dom4j.Attribute;
/*   5:    */import org.dom4j.CDATA;
/*   6:    */import org.dom4j.Comment;
/*   7:    */import org.dom4j.DocumentFactory;
/*   8:    */import org.dom4j.Element;
/*   9:    */import org.dom4j.Entity;
/*  10:    */import org.dom4j.Namespace;
/*  11:    */import org.dom4j.ProcessingInstruction;
/*  12:    */import org.dom4j.QName;
/*  13:    */import org.dom4j.Text;
/*  14:    */import org.dom4j.util.SingletonStrategy;
/*  15:    */import org.w3c.dom.DOMException;
/*  16:    */import org.w3c.dom.DOMImplementation;
/*  17:    */
/*  36:    */public class DOMDocumentFactory
/*  37:    */  extends DocumentFactory
/*  38:    */  implements DOMImplementation
/*  39:    */{
/*  40: 40 */  private static SingletonStrategy singleton = null;
/*  41:    */  
/*  42:    */  static {
/*  43:    */    try {
/*  44: 44 */      String defaultSingletonClass = "org.dom4j.util.SimpleSingleton";
/*  45: 45 */      Class clazz = null;
/*  46:    */      try {
/*  47: 47 */        String singletonClass = defaultSingletonClass;
/*  48: 48 */        singletonClass = System.getProperty("org.dom4j.dom.DOMDocumentFactory.singleton.strategy", singletonClass);
/*  49:    */        
/*  51: 51 */        clazz = Class.forName(singletonClass);
/*  52:    */      } catch (Exception exc1) {
/*  53:    */        try {
/*  54: 54 */          String singletonClass = defaultSingletonClass;
/*  55: 55 */          clazz = Class.forName(singletonClass);
/*  56:    */        }
/*  57:    */        catch (Exception exc2) {}
/*  58:    */      }
/*  59: 59 */      singleton = (SingletonStrategy)clazz.newInstance();
/*  60: 60 */      singleton.setSingletonClassName(DOMDocumentFactory.class.getName());
/*  61:    */    }
/*  62:    */    catch (Exception exc3) {}
/*  63:    */  }
/*  64:    */  
/*  71:    */  public static DocumentFactory getInstance()
/*  72:    */  {
/*  73: 73 */    DOMDocumentFactory fact = (DOMDocumentFactory)singleton.instance();
/*  74: 74 */    return fact;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public org.dom4j.Document createDocument()
/*  78:    */  {
/*  79: 79 */    DOMDocument answer = new DOMDocument();
/*  80: 80 */    answer.setDocumentFactory(this);
/*  81:    */    
/*  82: 82 */    return answer;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public org.dom4j.DocumentType createDocType(String name, String publicId, String systemId)
/*  86:    */  {
/*  87: 87 */    return new DOMDocumentType(name, publicId, systemId);
/*  88:    */  }
/*  89:    */  
/*  90:    */  public Element createElement(QName qname) {
/*  91: 91 */    return new DOMElement(qname);
/*  92:    */  }
/*  93:    */  
/*  94:    */  public Element createElement(QName qname, int attributeCount) {
/*  95: 95 */    return new DOMElement(qname, attributeCount);
/*  96:    */  }
/*  97:    */  
/*  98:    */  public Attribute createAttribute(Element owner, QName qname, String value) {
/*  99: 99 */    return new DOMAttribute(qname, value);
/* 100:    */  }
/* 101:    */  
/* 102:    */  public CDATA createCDATA(String text) {
/* 103:103 */    return new DOMCDATA(text);
/* 104:    */  }
/* 105:    */  
/* 106:    */  public Comment createComment(String text) {
/* 107:107 */    return new DOMComment(text);
/* 108:    */  }
/* 109:    */  
/* 110:    */  public Text createText(String text) {
/* 111:111 */    return new DOMText(text);
/* 112:    */  }
/* 113:    */  
/* 114:    */  public Entity createEntity(String name) {
/* 115:115 */    return new DOMEntityReference(name);
/* 116:    */  }
/* 117:    */  
/* 118:    */  public Entity createEntity(String name, String text) {
/* 119:119 */    return new DOMEntityReference(name, text);
/* 120:    */  }
/* 121:    */  
/* 122:    */  public Namespace createNamespace(String prefix, String uri) {
/* 123:123 */    return new DOMNamespace(prefix, uri);
/* 124:    */  }
/* 125:    */  
/* 126:    */  public ProcessingInstruction createProcessingInstruction(String target, String data)
/* 127:    */  {
/* 128:128 */    return new DOMProcessingInstruction(target, data);
/* 129:    */  }
/* 130:    */  
/* 131:    */  public ProcessingInstruction createProcessingInstruction(String target, Map data)
/* 132:    */  {
/* 133:133 */    return new DOMProcessingInstruction(target, data);
/* 134:    */  }
/* 135:    */  
/* 136:    */  public boolean hasFeature(String feat, String version)
/* 137:    */  {
/* 138:138 */    if (("XML".equalsIgnoreCase(feat)) || ("Core".equalsIgnoreCase(feat))) {
/* 139:139 */      return (version == null) || (version.length() == 0) || ("1.0".equals(version)) || ("2.0".equals(version));
/* 140:    */    }
/* 141:    */    
/* 143:143 */    return false;
/* 144:    */  }
/* 145:    */  
/* 146:    */  public org.w3c.dom.DocumentType createDocumentType(String qualifiedName, String publicId, String systemId) throws DOMException
/* 147:    */  {
/* 148:148 */    return new DOMDocumentType(qualifiedName, publicId, systemId);
/* 149:    */  }
/* 150:    */  
/* 151:    */  public org.w3c.dom.Document createDocument(String namespaceURI, String qualifiedName, org.w3c.dom.DocumentType docType)
/* 152:    */    throws DOMException
/* 153:    */  {
/* 154:    */    DOMDocument document;
/* 155:    */    DOMDocument document;
/* 156:156 */    if (docType != null) {
/* 157:157 */      DOMDocumentType documentType = asDocumentType(docType);
/* 158:158 */      document = new DOMDocument(documentType);
/* 159:    */    } else {
/* 160:160 */      document = new DOMDocument();
/* 161:    */    }
/* 162:    */    
/* 163:163 */    document.addElement(createQName(qualifiedName, namespaceURI));
/* 164:    */    
/* 165:165 */    return document;
/* 166:    */  }
/* 167:    */  
/* 168:    */  protected DOMDocumentType asDocumentType(org.w3c.dom.DocumentType docType)
/* 169:    */  {
/* 170:170 */    if ((docType instanceof DOMDocumentType)) {
/* 171:171 */      return (DOMDocumentType)docType;
/* 172:    */    }
/* 173:173 */    return new DOMDocumentType(docType.getName(), docType.getPublicId(), docType.getSystemId());
/* 174:    */  }
/* 175:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMDocumentFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */