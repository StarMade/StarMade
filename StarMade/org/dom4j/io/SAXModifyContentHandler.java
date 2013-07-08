/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import org.dom4j.DocumentFactory;
/*   5:    */import org.dom4j.Element;
/*   6:    */import org.dom4j.ElementHandler;
/*   7:    */import org.xml.sax.Attributes;
/*   8:    */import org.xml.sax.Locator;
/*   9:    */import org.xml.sax.SAXException;
/*  10:    */
/*  27:    */class SAXModifyContentHandler
/*  28:    */  extends SAXContentHandler
/*  29:    */{
/*  30:    */  private XMLWriter xmlWriter;
/*  31:    */  
/*  32:    */  public SAXModifyContentHandler() {}
/*  33:    */  
/*  34:    */  public SAXModifyContentHandler(DocumentFactory documentFactory)
/*  35:    */  {
/*  36: 36 */    super(documentFactory);
/*  37:    */  }
/*  38:    */  
/*  39:    */  public SAXModifyContentHandler(DocumentFactory documentFactory, ElementHandler elementHandler)
/*  40:    */  {
/*  41: 41 */    super(documentFactory, elementHandler);
/*  42:    */  }
/*  43:    */  
/*  44:    */  public SAXModifyContentHandler(DocumentFactory documentFactory, ElementHandler elementHandler, ElementStack elementStack)
/*  45:    */  {
/*  46: 46 */    super(documentFactory, elementHandler, elementStack);
/*  47:    */  }
/*  48:    */  
/*  49:    */  public void setXMLWriter(XMLWriter writer) {
/*  50: 50 */    this.xmlWriter = writer;
/*  51:    */  }
/*  52:    */  
/*  53:    */  public void startCDATA() throws SAXException {
/*  54: 54 */    super.startCDATA();
/*  55:    */    
/*  56: 56 */    if ((!activeHandlers()) && (this.xmlWriter != null)) {
/*  57: 57 */      this.xmlWriter.startCDATA();
/*  58:    */    }
/*  59:    */  }
/*  60:    */  
/*  61:    */  public void startDTD(String name, String publicId, String systemId) throws SAXException
/*  62:    */  {
/*  63: 63 */    super.startDTD(name, publicId, systemId);
/*  64:    */    
/*  65: 65 */    if (this.xmlWriter != null) {
/*  66: 66 */      this.xmlWriter.startDTD(name, publicId, systemId);
/*  67:    */    }
/*  68:    */  }
/*  69:    */  
/*  70:    */  public void endDTD() throws SAXException {
/*  71: 71 */    super.endDTD();
/*  72:    */    
/*  73: 73 */    if (this.xmlWriter != null) {
/*  74: 74 */      this.xmlWriter.endDTD();
/*  75:    */    }
/*  76:    */  }
/*  77:    */  
/*  78:    */  public void comment(char[] characters, int parm2, int parm3) throws SAXException
/*  79:    */  {
/*  80: 80 */    super.comment(characters, parm2, parm3);
/*  81:    */    
/*  82: 82 */    if ((!activeHandlers()) && (this.xmlWriter != null)) {
/*  83: 83 */      this.xmlWriter.comment(characters, parm2, parm3);
/*  84:    */    }
/*  85:    */  }
/*  86:    */  
/*  87:    */  public void startEntity(String name) throws SAXException {
/*  88: 88 */    super.startEntity(name);
/*  89:    */    
/*  90: 90 */    if (this.xmlWriter != null) {
/*  91: 91 */      this.xmlWriter.startEntity(name);
/*  92:    */    }
/*  93:    */  }
/*  94:    */  
/*  95:    */  public void endCDATA() throws SAXException {
/*  96: 96 */    super.endCDATA();
/*  97:    */    
/*  98: 98 */    if ((!activeHandlers()) && (this.xmlWriter != null)) {
/*  99: 99 */      this.xmlWriter.endCDATA();
/* 100:    */    }
/* 101:    */  }
/* 102:    */  
/* 103:    */  public void endEntity(String name) throws SAXException {
/* 104:104 */    super.endEntity(name);
/* 105:    */    
/* 106:106 */    if (this.xmlWriter != null) {
/* 107:107 */      this.xmlWriter.endEntity(name);
/* 108:    */    }
/* 109:    */  }
/* 110:    */  
/* 111:    */  public void unparsedEntityDecl(String name, String publicId, String systemId, String notation) throws SAXException
/* 112:    */  {
/* 113:113 */    super.unparsedEntityDecl(name, publicId, systemId, notation);
/* 114:    */    
/* 115:115 */    if ((!activeHandlers()) && (this.xmlWriter != null)) {
/* 116:116 */      this.xmlWriter.unparsedEntityDecl(name, publicId, systemId, notation);
/* 117:    */    }
/* 118:    */  }
/* 119:    */  
/* 120:    */  public void notationDecl(String name, String publicId, String systemId) throws SAXException
/* 121:    */  {
/* 122:122 */    super.notationDecl(name, publicId, systemId);
/* 123:    */    
/* 124:124 */    if (this.xmlWriter != null) {
/* 125:125 */      this.xmlWriter.notationDecl(name, publicId, systemId);
/* 126:    */    }
/* 127:    */  }
/* 128:    */  
/* 129:    */  public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException
/* 130:    */  {
/* 131:131 */    super.startElement(uri, localName, qName, atts);
/* 132:    */    
/* 133:133 */    if ((!activeHandlers()) && (this.xmlWriter != null)) {
/* 134:134 */      this.xmlWriter.startElement(uri, localName, qName, atts);
/* 135:    */    }
/* 136:    */  }
/* 137:    */  
/* 138:    */  public void startDocument() throws SAXException {
/* 139:139 */    super.startDocument();
/* 140:    */    
/* 141:141 */    if (this.xmlWriter != null) {
/* 142:142 */      this.xmlWriter.startDocument();
/* 143:    */    }
/* 144:    */  }
/* 145:    */  
/* 146:    */  public void ignorableWhitespace(char[] parm1, int parm2, int parm3) throws SAXException
/* 147:    */  {
/* 148:148 */    super.ignorableWhitespace(parm1, parm2, parm3);
/* 149:    */    
/* 150:150 */    if ((!activeHandlers()) && (this.xmlWriter != null)) {
/* 151:151 */      this.xmlWriter.ignorableWhitespace(parm1, parm2, parm3);
/* 152:    */    }
/* 153:    */  }
/* 154:    */  
/* 155:    */  public void processingInstruction(String target, String data) throws SAXException
/* 156:    */  {
/* 157:157 */    super.processingInstruction(target, data);
/* 158:    */    
/* 159:159 */    if ((!activeHandlers()) && (this.xmlWriter != null)) {
/* 160:160 */      this.xmlWriter.processingInstruction(target, data);
/* 161:    */    }
/* 162:    */  }
/* 163:    */  
/* 164:    */  public void setDocumentLocator(Locator locator) {
/* 165:165 */    super.setDocumentLocator(locator);
/* 166:    */    
/* 167:167 */    if (this.xmlWriter != null) {
/* 168:168 */      this.xmlWriter.setDocumentLocator(locator);
/* 169:    */    }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public void skippedEntity(String name) throws SAXException {
/* 173:173 */    super.skippedEntity(name);
/* 174:    */    
/* 175:175 */    if ((!activeHandlers()) && (this.xmlWriter != null)) {
/* 176:176 */      this.xmlWriter.skippedEntity(name);
/* 177:    */    }
/* 178:    */  }
/* 179:    */  
/* 180:    */  public void endDocument() throws SAXException {
/* 181:181 */    super.endDocument();
/* 182:    */    
/* 183:183 */    if (this.xmlWriter != null) {
/* 184:184 */      this.xmlWriter.endDocument();
/* 185:    */    }
/* 186:    */  }
/* 187:    */  
/* 188:    */  public void startPrefixMapping(String prefix, String uri) throws SAXException
/* 189:    */  {
/* 190:190 */    super.startPrefixMapping(prefix, uri);
/* 191:    */    
/* 192:192 */    if (this.xmlWriter != null) {
/* 193:193 */      this.xmlWriter.startPrefixMapping(prefix, uri);
/* 194:    */    }
/* 195:    */  }
/* 196:    */  
/* 197:    */  public void endElement(String uri, String localName, String qName) throws SAXException
/* 198:    */  {
/* 199:199 */    ElementHandler currentHandler = getElementStack().getDispatchHandler().getHandler(getElementStack().getPath());
/* 200:    */    
/* 202:202 */    super.endElement(uri, localName, qName);
/* 203:    */    
/* 204:204 */    if ((!activeHandlers()) && 
/* 205:205 */      (this.xmlWriter != null)) {
/* 206:206 */      if (currentHandler == null) {
/* 207:207 */        this.xmlWriter.endElement(uri, localName, qName);
/* 208:208 */      } else if ((currentHandler instanceof SAXModifyElementHandler)) {
/* 209:209 */        SAXModifyElementHandler modifyHandler = (SAXModifyElementHandler)currentHandler;
/* 210:    */        
/* 211:211 */        Element modifiedElement = modifyHandler.getModifiedElement();
/* 212:    */        
/* 213:    */        try
/* 214:    */        {
/* 215:215 */          this.xmlWriter.write(modifiedElement);
/* 216:    */        } catch (IOException ex) {
/* 217:217 */          throw new SAXModifyException(ex);
/* 218:    */        }
/* 219:    */      }
/* 220:    */    }
/* 221:    */  }
/* 222:    */  
/* 223:    */  public void endPrefixMapping(String prefix) throws SAXException
/* 224:    */  {
/* 225:225 */    super.endPrefixMapping(prefix);
/* 226:    */    
/* 227:227 */    if (this.xmlWriter != null) {
/* 228:228 */      this.xmlWriter.endPrefixMapping(prefix);
/* 229:    */    }
/* 230:    */  }
/* 231:    */  
/* 232:    */  public void characters(char[] parm1, int parm2, int parm3) throws SAXException
/* 233:    */  {
/* 234:234 */    super.characters(parm1, parm2, parm3);
/* 235:    */    
/* 236:236 */    if ((!activeHandlers()) && (this.xmlWriter != null)) {
/* 237:237 */      this.xmlWriter.characters(parm1, parm2, parm3);
/* 238:    */    }
/* 239:    */  }
/* 240:    */  
/* 241:    */  protected XMLWriter getXMLWriter() {
/* 242:242 */    return this.xmlWriter;
/* 243:    */  }
/* 244:    */  
/* 245:    */  private boolean activeHandlers() {
/* 246:246 */    DispatchHandler handler = getElementStack().getDispatchHandler();
/* 247:    */    
/* 248:248 */    return handler.getActiveHandlerCount() > 0;
/* 249:    */  }
/* 250:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXModifyContentHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */