/*   1:    */package org.dom4j.xpp;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Iterator;
/*   5:    */import org.dom4j.Attribute;
/*   6:    */import org.dom4j.DocumentFactory;
/*   7:    */import org.dom4j.Element;
/*   8:    */import org.dom4j.QName;
/*   9:    */import org.dom4j.tree.AbstractElement;
/*  10:    */import org.gjt.xpp.XmlPullParserException;
/*  11:    */import org.gjt.xpp.XmlStartTag;
/*  12:    */
/*  31:    */public class ProxyXmlStartTag
/*  32:    */  implements XmlStartTag
/*  33:    */{
/*  34:    */  private Element element;
/*  35: 35 */  private DocumentFactory factory = DocumentFactory.getInstance();
/*  36:    */  
/*  37:    */  public ProxyXmlStartTag() {}
/*  38:    */  
/*  39:    */  public ProxyXmlStartTag(Element element)
/*  40:    */  {
/*  41: 41 */    this.element = element;
/*  42:    */  }
/*  43:    */  
/*  45:    */  public void resetStartTag()
/*  46:    */  {
/*  47: 47 */    this.element = null;
/*  48:    */  }
/*  49:    */  
/*  50:    */  public int getAttributeCount() {
/*  51: 51 */    return this.element != null ? this.element.attributeCount() : 0;
/*  52:    */  }
/*  53:    */  
/*  54:    */  public String getAttributeNamespaceUri(int index) {
/*  55: 55 */    if (this.element != null) {
/*  56: 56 */      Attribute attribute = this.element.attribute(index);
/*  57:    */      
/*  58: 58 */      if (attribute != null) {
/*  59: 59 */        return attribute.getNamespaceURI();
/*  60:    */      }
/*  61:    */    }
/*  62:    */    
/*  63: 63 */    return null;
/*  64:    */  }
/*  65:    */  
/*  66:    */  public String getAttributeLocalName(int index) {
/*  67: 67 */    if (this.element != null) {
/*  68: 68 */      Attribute attribute = this.element.attribute(index);
/*  69:    */      
/*  70: 70 */      if (attribute != null) {
/*  71: 71 */        return attribute.getName();
/*  72:    */      }
/*  73:    */    }
/*  74:    */    
/*  75: 75 */    return null;
/*  76:    */  }
/*  77:    */  
/*  78:    */  public String getAttributePrefix(int index) {
/*  79: 79 */    if (this.element != null) {
/*  80: 80 */      Attribute attribute = this.element.attribute(index);
/*  81:    */      
/*  82: 82 */      if (attribute != null) {
/*  83: 83 */        String prefix = attribute.getNamespacePrefix();
/*  84:    */        
/*  85: 85 */        if ((prefix != null) && (prefix.length() > 0)) {
/*  86: 86 */          return prefix;
/*  87:    */        }
/*  88:    */      }
/*  89:    */    }
/*  90:    */    
/*  91: 91 */    return null;
/*  92:    */  }
/*  93:    */  
/*  94:    */  public String getAttributeRawName(int index) {
/*  95: 95 */    if (this.element != null) {
/*  96: 96 */      Attribute attribute = this.element.attribute(index);
/*  97:    */      
/*  98: 98 */      if (attribute != null) {
/*  99: 99 */        return attribute.getQualifiedName();
/* 100:    */      }
/* 101:    */    }
/* 102:    */    
/* 103:103 */    return null;
/* 104:    */  }
/* 105:    */  
/* 106:    */  public String getAttributeValue(int index) {
/* 107:107 */    if (this.element != null) {
/* 108:108 */      Attribute attribute = this.element.attribute(index);
/* 109:    */      
/* 110:110 */      if (attribute != null) {
/* 111:111 */        return attribute.getValue();
/* 112:    */      }
/* 113:    */    }
/* 114:    */    
/* 115:115 */    return null;
/* 116:    */  }
/* 117:    */  
/* 118:    */  public String getAttributeValueFromRawName(String rawName) { Iterator iter;
/* 119:119 */    if (this.element != null) {
/* 120:120 */      for (iter = this.element.attributeIterator(); iter.hasNext();) {
/* 121:121 */        Attribute attribute = (Attribute)iter.next();
/* 122:    */        
/* 123:123 */        if (rawName.equals(attribute.getQualifiedName())) {
/* 124:124 */          return attribute.getValue();
/* 125:    */        }
/* 126:    */      }
/* 127:    */    }
/* 128:    */    
/* 129:129 */    return null;
/* 130:    */  }
/* 131:    */  
/* 132:    */  public String getAttributeValueFromName(String namespaceURI, String localName) {
/* 133:    */    Iterator iter;
/* 134:134 */    if (this.element != null) {
/* 135:135 */      for (iter = this.element.attributeIterator(); iter.hasNext();) {
/* 136:136 */        Attribute attribute = (Attribute)iter.next();
/* 137:    */        
/* 138:138 */        if ((namespaceURI.equals(attribute.getNamespaceURI())) && (localName.equals(attribute.getName())))
/* 139:    */        {
/* 140:140 */          return attribute.getValue();
/* 141:    */        }
/* 142:    */      }
/* 143:    */    }
/* 144:    */    
/* 145:145 */    return null;
/* 146:    */  }
/* 147:    */  
/* 148:    */  public boolean isAttributeNamespaceDeclaration(int index) {
/* 149:149 */    if (this.element != null) {
/* 150:150 */      Attribute attribute = this.element.attribute(index);
/* 151:    */      
/* 152:152 */      if (attribute != null) {
/* 153:153 */        return "xmlns".equals(attribute.getNamespacePrefix());
/* 154:    */      }
/* 155:    */    }
/* 156:    */    
/* 157:157 */    return false;
/* 158:    */  }
/* 159:    */  
/* 169:    */  public void addAttribute(String namespaceURI, String localName, String rawName, String value)
/* 170:    */    throws XmlPullParserException
/* 171:    */  {
/* 172:172 */    QName qname = QName.get(rawName, namespaceURI);
/* 173:173 */    this.element.addAttribute(qname, value);
/* 174:    */  }
/* 175:    */  
/* 176:    */  public void addAttribute(String namespaceURI, String localName, String rawName, String value, boolean isNamespaceDeclaration)
/* 177:    */    throws XmlPullParserException
/* 178:    */  {
/* 179:179 */    if (isNamespaceDeclaration) {
/* 180:180 */      String prefix = "";
/* 181:181 */      int idx = rawName.indexOf(':');
/* 182:    */      
/* 183:183 */      if (idx > 0) {
/* 184:184 */        prefix = rawName.substring(0, idx);
/* 185:    */      }
/* 186:    */      
/* 187:187 */      this.element.addNamespace(prefix, namespaceURI);
/* 188:    */    } else {
/* 189:189 */      QName qname = QName.get(rawName, namespaceURI);
/* 190:190 */      this.element.addAttribute(qname, value);
/* 191:    */    }
/* 192:    */  }
/* 193:    */  
/* 194:    */  public void ensureAttributesCapacity(int minCapacity) throws XmlPullParserException
/* 195:    */  {
/* 196:196 */    if ((this.element instanceof AbstractElement)) {
/* 197:197 */      AbstractElement elementImpl = (AbstractElement)this.element;
/* 198:198 */      elementImpl.ensureAttributesCapacity(minCapacity);
/* 199:    */    }
/* 200:    */  }
/* 201:    */  
/* 202:    */  /**
/* 203:    */   * @deprecated
/* 204:    */   */
/* 205:    */  public void removeAtttributes()
/* 206:    */    throws XmlPullParserException
/* 207:    */  {
/* 208:208 */    removeAttributes();
/* 209:    */  }
/* 210:    */  
/* 211:    */  public void removeAttributes() throws XmlPullParserException {
/* 212:212 */    if (this.element != null) {
/* 213:213 */      this.element.setAttributes(new ArrayList());
/* 214:    */    }
/* 215:    */  }
/* 216:    */  
/* 220:    */  public String getLocalName()
/* 221:    */  {
/* 222:222 */    return this.element.getName();
/* 223:    */  }
/* 224:    */  
/* 225:    */  public String getNamespaceUri() {
/* 226:226 */    return this.element.getNamespaceURI();
/* 227:    */  }
/* 228:    */  
/* 229:    */  public String getPrefix() {
/* 230:230 */    return this.element.getNamespacePrefix();
/* 231:    */  }
/* 232:    */  
/* 233:    */  public String getRawName() {
/* 234:234 */    return this.element.getQualifiedName();
/* 235:    */  }
/* 236:    */  
/* 237:    */  public void modifyTag(String namespaceURI, String lName, String rawName) {
/* 238:238 */    this.element = this.factory.createElement(rawName, namespaceURI);
/* 239:    */  }
/* 240:    */  
/* 241:    */  public void resetTag() {
/* 242:242 */    this.element = null;
/* 243:    */  }
/* 244:    */  
/* 245:    */  public boolean removeAttributeByName(String namespaceURI, String localName) throws XmlPullParserException
/* 246:    */  {
/* 247:247 */    if (this.element != null) {
/* 248:248 */      QName qname = QName.get(localName, namespaceURI);
/* 249:249 */      Attribute attribute = this.element.attribute(qname);
/* 250:250 */      return this.element.remove(attribute);
/* 251:    */    }
/* 252:252 */    return false;
/* 253:    */  }
/* 254:    */  
/* 255:    */  public boolean removeAttributeByRawName(String rawName) throws XmlPullParserException
/* 256:    */  {
/* 257:257 */    if (this.element != null) {
/* 258:258 */      Attribute attribute = null;
/* 259:259 */      Iterator it = this.element.attributeIterator();
/* 260:260 */      while (it.hasNext()) {
/* 261:261 */        Attribute current = (Attribute)it.next();
/* 262:262 */        if (current.getQualifiedName().equals(rawName)) {
/* 263:263 */          attribute = current;
/* 264:264 */          break;
/* 265:    */        }
/* 266:    */      }
/* 267:267 */      return this.element.remove(attribute);
/* 268:    */    }
/* 269:269 */    return false;
/* 270:    */  }
/* 271:    */  
/* 273:    */  public DocumentFactory getDocumentFactory()
/* 274:    */  {
/* 275:275 */    return this.factory;
/* 276:    */  }
/* 277:    */  
/* 278:    */  public void setDocumentFactory(DocumentFactory documentFactory) {
/* 279:279 */    this.factory = documentFactory;
/* 280:    */  }
/* 281:    */  
/* 282:    */  public Element getElement() {
/* 283:283 */    return this.element;
/* 284:    */  }
/* 285:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.xpp.ProxyXmlStartTag
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */