/*   1:    */package org.apache.ws.commons.serialize;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.List;
/*   5:    */import org.w3c.dom.Document;
/*   6:    */import org.w3c.dom.Element;
/*   7:    */import org.w3c.dom.EntityReference;
/*   8:    */import org.w3c.dom.Node;
/*   9:    */import org.w3c.dom.ProcessingInstruction;
/*  10:    */import org.w3c.dom.Text;
/*  11:    */import org.xml.sax.Attributes;
/*  12:    */import org.xml.sax.ContentHandler;
/*  13:    */import org.xml.sax.Locator;
/*  14:    */import org.xml.sax.SAXException;
/*  15:    */
/*  41:    */public class DOMBuilder
/*  42:    */  implements ContentHandler
/*  43:    */{
/*  44:    */  private Document document;
/*  45:    */  private Node target;
/*  46:    */  private Node currentNode;
/*  47:    */  private Locator locator;
/*  48:    */  private boolean prefixMappingIsAttribute;
/*  49:    */  private List prefixes;
/*  50:    */  
/*  51:    */  public boolean isPrefixMappingIsAttribute()
/*  52:    */  {
/*  53: 53 */    return this.prefixMappingIsAttribute;
/*  54:    */  }
/*  55:    */  
/*  61:    */  public void setPrefixMappingIsAttribute(boolean pPrefixMappingIsAttribute)
/*  62:    */  {
/*  63: 63 */    this.prefixMappingIsAttribute = pPrefixMappingIsAttribute;
/*  64:    */  }
/*  65:    */  
/*  68:    */  public void setDocument(Document pDocument)
/*  69:    */  {
/*  70: 70 */    this.document = pDocument;
/*  71:    */  }
/*  72:    */  
/*  75:    */  public Document getDocument()
/*  76:    */  {
/*  77: 77 */    return this.document;
/*  78:    */  }
/*  79:    */  
/*  82:    */  public void setDocumentLocator(Locator pLocator)
/*  83:    */  {
/*  84: 84 */    this.locator = pLocator;
/*  85:    */  }
/*  86:    */  
/*  89:    */  public Locator getDocumentLocator()
/*  90:    */  {
/*  91: 91 */    return this.locator;
/*  92:    */  }
/*  93:    */  
/*  97:    */  public void setTarget(Node pNode)
/*  98:    */  {
/*  99: 99 */    this.target = pNode;
/* 100:100 */    this.currentNode = pNode;
/* 101:101 */    if (getDocument() == null) {
/* 102:102 */      setDocument(pNode.getNodeType() == 9 ? (Document)pNode : pNode.getOwnerDocument());
/* 103:    */    }
/* 104:    */  }
/* 105:    */  
/* 110:    */  public Node getTarget()
/* 111:    */  {
/* 112:112 */    return this.target;
/* 113:    */  }
/* 114:    */  
/* 115:    */  public void startDocument() throws SAXException
/* 116:    */  {}
/* 117:    */  
/* 118:    */  public void endDocument() throws SAXException
/* 119:    */  {}
/* 120:    */  
/* 121:    */  public void startPrefixMapping(String prefix, String uri) throws SAXException
/* 122:    */  {
/* 123:123 */    if (isPrefixMappingIsAttribute()) {
/* 124:124 */      if (this.prefixes == null) {
/* 125:125 */        this.prefixes = new ArrayList();
/* 126:    */      }
/* 127:127 */      this.prefixes.add(prefix);
/* 128:128 */      this.prefixes.add(uri);
/* 129:    */    }
/* 130:    */  }
/* 131:    */  
/* 132:    */  public void endPrefixMapping(String prefix) throws SAXException
/* 133:    */  {}
/* 134:    */  
/* 135:    */  public void startElement(String pNamespaceURI, String pLocalName, String pQName, Attributes pAttr) throws SAXException
/* 136:    */  {
/* 137:137 */    Document doc = getDocument();
/* 138:    */    Element element;
/* 139:139 */    Element element; if ((pNamespaceURI == null) || (pNamespaceURI.length() == 0)) {
/* 140:140 */      element = doc.createElement(pQName);
/* 141:    */    } else {
/* 142:142 */      element = doc.createElementNS(pNamespaceURI, pQName);
/* 143:    */    }
/* 144:144 */    if (pAttr != null) {
/* 145:145 */      for (int i = 0; i < pAttr.getLength(); i++) {
/* 146:146 */        String uri = pAttr.getURI(i);
/* 147:147 */        String qName = pAttr.getQName(i);
/* 148:148 */        String value = pAttr.getValue(i);
/* 149:149 */        if ((uri == null) || (uri.length() == 0)) {
/* 150:150 */          element.setAttribute(qName, value);
/* 151:    */        } else {
/* 152:152 */          element.setAttributeNS(uri, qName, value);
/* 153:    */        }
/* 154:    */      }
/* 155:    */    }
/* 156:156 */    if (this.prefixes != null) {
/* 157:157 */      for (int i = 0; i < this.prefixes.size(); i += 2) {
/* 158:158 */        String prefix = (String)this.prefixes.get(i);
/* 159:159 */        String uri = (String)this.prefixes.get(i + 1);
/* 160:160 */        if ((prefix == null) || ("".equals(prefix))) {
/* 161:161 */          element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", uri);
/* 162:    */        }
/* 163:    */        else {
/* 164:164 */          element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + prefix, uri);
/* 165:    */        }
/* 166:    */      }
/* 167:    */      
/* 168:168 */      this.prefixes.clear();
/* 169:    */    }
/* 170:170 */    this.currentNode.appendChild(element);
/* 171:171 */    this.currentNode = element;
/* 172:    */  }
/* 173:    */  
/* 174:    */  public void endElement(String namespaceURI, String localName, String qName) throws SAXException
/* 175:    */  {
/* 176:176 */    this.currentNode = this.currentNode.getParentNode();
/* 177:    */  }
/* 178:    */  
/* 179:    */  public void characters(char[] ch, int start, int length) throws SAXException
/* 180:    */  {
/* 181:181 */    Node node = this.currentNode.getLastChild();
/* 182:182 */    String s = new String(ch, start, length);
/* 183:183 */    if ((node != null) && (node.getNodeType() == 3)) {
/* 184:184 */      ((Text)node).appendData(s);
/* 185:    */    } else {
/* 186:186 */      Text text = getDocument().createTextNode(s);
/* 187:187 */      this.currentNode.appendChild(text);
/* 188:    */    }
/* 189:    */  }
/* 190:    */  
/* 191:    */  public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException
/* 192:    */  {
/* 193:193 */    characters(ch, start, length);
/* 194:    */  }
/* 195:    */  
/* 196:    */  public void processingInstruction(String pTarget, String pData) throws SAXException
/* 197:    */  {
/* 198:198 */    ProcessingInstruction pi = getDocument().createProcessingInstruction(pTarget, pData);
/* 199:199 */    this.currentNode.appendChild(pi);
/* 200:    */  }
/* 201:    */  
/* 202:    */  public void skippedEntity(String pName) throws SAXException {
/* 203:203 */    EntityReference entity = getDocument().createEntityReference(pName);
/* 204:204 */    this.currentNode.appendChild(entity);
/* 205:    */  }
/* 206:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.ws.commons.serialize.DOMBuilder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */