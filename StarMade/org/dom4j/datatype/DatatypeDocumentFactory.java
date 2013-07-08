/*   1:    */package org.dom4j.datatype;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import org.dom4j.Attribute;
/*   5:    */import org.dom4j.Document;
/*   6:    */import org.dom4j.DocumentFactory;
/*   7:    */import org.dom4j.Element;
/*   8:    */import org.dom4j.Namespace;
/*   9:    */import org.dom4j.QName;
/*  10:    */import org.dom4j.io.SAXReader;
/*  11:    */import org.xml.sax.EntityResolver;
/*  12:    */import org.xml.sax.InputSource;
/*  13:    */
/*  32:    */public class DatatypeDocumentFactory
/*  33:    */  extends DocumentFactory
/*  34:    */{
/*  35:    */  private static final boolean DO_INTERN_QNAME = false;
/*  36: 36 */  protected static transient DatatypeDocumentFactory singleton = new DatatypeDocumentFactory();
/*  37:    */  
/*  39: 39 */  private static final Namespace XSI_NAMESPACE = Namespace.get("xsi", "http://www.w3.org/2001/XMLSchema-instance");
/*  40:    */  
/*  42: 42 */  private static final QName XSI_SCHEMA_LOCATION = QName.get("schemaLocation", XSI_NAMESPACE);
/*  43:    */  
/*  45: 45 */  private static final QName XSI_NO_SCHEMA_LOCATION = QName.get("noNamespaceSchemaLocation", XSI_NAMESPACE);
/*  46:    */  
/*  49:    */  private SchemaParser schemaBuilder;
/*  50:    */  
/*  52: 52 */  private SAXReader xmlSchemaReader = new SAXReader();
/*  53:    */  
/*  55: 55 */  private boolean autoLoadSchema = true;
/*  56:    */  
/*  57:    */  public DatatypeDocumentFactory() {
/*  58: 58 */    this.schemaBuilder = new SchemaParser(this);
/*  59:    */  }
/*  60:    */  
/*  67:    */  public static DocumentFactory getInstance()
/*  68:    */  {
/*  69: 69 */    return singleton;
/*  70:    */  }
/*  71:    */  
/*  78:    */  public void loadSchema(Document schemaDocument)
/*  79:    */  {
/*  80: 80 */    this.schemaBuilder.build(schemaDocument);
/*  81:    */  }
/*  82:    */  
/*  83:    */  public void loadSchema(Document schemaDocument, Namespace targetNamespace) {
/*  84: 84 */    this.schemaBuilder.build(schemaDocument, targetNamespace);
/*  85:    */  }
/*  86:    */  
/*  95:    */  public DatatypeElementFactory getElementFactory(QName elementQName)
/*  96:    */  {
/*  97: 97 */    DatatypeElementFactory result = null;
/*  98:    */    
/* 103:103 */    DocumentFactory factory = elementQName.getDocumentFactory();
/* 104:104 */    if ((factory instanceof DatatypeElementFactory)) {
/* 105:105 */      result = (DatatypeElementFactory)factory;
/* 106:    */    }
/* 107:    */    
/* 108:108 */    return result;
/* 109:    */  }
/* 110:    */  
/* 112:    */  public Attribute createAttribute(Element owner, QName qname, String value)
/* 113:    */  {
/* 114:114 */    if ((this.autoLoadSchema) && (qname.equals(XSI_NO_SCHEMA_LOCATION))) {
/* 115:115 */      Document document = owner != null ? owner.getDocument() : null;
/* 116:116 */      loadSchema(document, value);
/* 117:117 */    } else if ((this.autoLoadSchema) && (qname.equals(XSI_SCHEMA_LOCATION))) {
/* 118:118 */      Document document = owner != null ? owner.getDocument() : null;
/* 119:119 */      String uri = value.substring(0, value.indexOf(' '));
/* 120:120 */      Namespace namespace = owner.getNamespaceForURI(uri);
/* 121:121 */      loadSchema(document, value.substring(value.indexOf(' ') + 1), namespace);
/* 122:    */    }
/* 123:    */    
/* 125:125 */    return super.createAttribute(owner, qname, value);
/* 126:    */  }
/* 127:    */  
/* 128:    */  protected void loadSchema(Document document, String schemaInstanceURI)
/* 129:    */  {
/* 130:    */    try
/* 131:    */    {
/* 132:132 */      EntityResolver resolver = document.getEntityResolver();
/* 133:    */      
/* 134:134 */      if (resolver == null) {
/* 135:135 */        String msg = "No EntityResolver available for resolving URI: ";
/* 136:136 */        throw new InvalidSchemaException(msg + schemaInstanceURI);
/* 137:    */      }
/* 138:    */      
/* 139:139 */      InputSource inputSource = resolver.resolveEntity(null, schemaInstanceURI);
/* 140:    */      
/* 142:142 */      if (resolver == null) {
/* 143:143 */        throw new InvalidSchemaException("Could not resolve the URI: " + schemaInstanceURI);
/* 144:    */      }
/* 145:    */      
/* 147:147 */      Document schemaDocument = this.xmlSchemaReader.read(inputSource);
/* 148:148 */      loadSchema(schemaDocument);
/* 149:    */    } catch (Exception e) {
/* 150:150 */      System.out.println("Failed to load schema: " + schemaInstanceURI);
/* 151:151 */      System.out.println("Caught: " + e);
/* 152:152 */      e.printStackTrace();
/* 153:153 */      throw new InvalidSchemaException("Failed to load schema: " + schemaInstanceURI);
/* 154:    */    }
/* 155:    */  }
/* 156:    */  
/* 157:    */  protected void loadSchema(Document document, String schemaInstanceURI, Namespace namespace)
/* 158:    */  {
/* 159:    */    try
/* 160:    */    {
/* 161:161 */      EntityResolver resolver = document.getEntityResolver();
/* 162:    */      
/* 163:163 */      if (resolver == null) {
/* 164:164 */        String msg = "No EntityResolver available for resolving URI: ";
/* 165:165 */        throw new InvalidSchemaException(msg + schemaInstanceURI);
/* 166:    */      }
/* 167:    */      
/* 168:168 */      InputSource inputSource = resolver.resolveEntity(null, schemaInstanceURI);
/* 169:    */      
/* 171:171 */      if (resolver == null) {
/* 172:172 */        throw new InvalidSchemaException("Could not resolve the URI: " + schemaInstanceURI);
/* 173:    */      }
/* 174:    */      
/* 176:176 */      Document schemaDocument = this.xmlSchemaReader.read(inputSource);
/* 177:177 */      loadSchema(schemaDocument, namespace);
/* 178:    */    } catch (Exception e) {
/* 179:179 */      System.out.println("Failed to load schema: " + schemaInstanceURI);
/* 180:180 */      System.out.println("Caught: " + e);
/* 181:181 */      e.printStackTrace();
/* 182:182 */      throw new InvalidSchemaException("Failed to load schema: " + schemaInstanceURI);
/* 183:    */    }
/* 184:    */  }
/* 185:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.datatype.DatatypeDocumentFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */